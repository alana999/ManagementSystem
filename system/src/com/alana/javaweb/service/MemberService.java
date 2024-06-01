package com.alana.javaweb.service;

import com.alana.javaweb.dao.MemberDao;
import com.alana.javaweb.model.Group;
import com.alana.javaweb.model.Member;

import java.util.List;

public class MemberService {
    private MemberDao memberDao = new MemberDao();

    public boolean addMember(Member member) {
        return memberDao.insert(member) > 0;
    }

    public boolean updateMember(Member member) {
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
}
