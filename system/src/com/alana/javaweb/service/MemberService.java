package com.alana.javaweb.service;

import com.alana.javaweb.dao.GroupDao;
import com.alana.javaweb.dao.MemberDao;
import com.alana.javaweb.exception.BusinessException;
import com.alana.javaweb.model.Group;
import com.alana.javaweb.model.Member;

import java.util.List;

public class MemberService {
    private MemberDao memberDao = new MemberDao();

    //用于校验小组是否存在
    GroupService groupService =new GroupService();

    public boolean addMember(Member member) {
        if (!groupService.checkGroupExists(member.getGroupId())) {
            throw new BusinessException("Specified group does not exist.");
        }
        return memberDao.insert(member) > 0;
    }

    public boolean updateMember(Member member) {

        if (!groupService.checkGroupExists(member.getGroupId())) {
            throw new BusinessException("Specified group does not exist.");
        }
        return memberDao.update(member) > 0;
    }

    public boolean deleteMember(int memberId) {
        return memberDao.delete(memberId) > 0;
    }

    public Member getMemberById(int memberId) {
        return memberDao.selectById(memberId);
    }
    public Member getMemberByName(String name) {
        return memberDao.selectByName(name);
    }
    public List<Member> listMembers(){
        return memberDao.selectAll();
    }

    public boolean checkMemberExists(int groupId) {
        return (memberDao.selectByGroupId(groupId).size()!=0);  //!=0 →存在→真值
    }
}
