package com.alana.javaweb.service;

import com.alana.javaweb.dao.GroupDao;
import com.alana.javaweb.exception.BusinessException;
import com.alana.javaweb.model.Group;

import java.sql.SQLException;
import java.util.List;

public class GroupService {
    private GroupDao groupDao = new GroupDao();


    public boolean addGroup(Group group) {

        try {
            return groupDao.insert(group) > 0;
            //groupDao.insert(group) > 0 表达式用于判断插入操作是否成功。如果插入成功，则返回 true；否则返回 false。

        } catch (SQLException e) {
            // 转换 SQLException 为 BusinessException
            throw new BusinessException(e.getMessage());
        }
    }

    public boolean updateGroup(Group group) {
        return groupDao.update(group) > 0;
    }

    public boolean deleteGroup(int groupId) {
        MemberService memberService = new MemberService();
        if(memberService.checkMemberExists(groupId)){
            throw new BusinessException("小组内还有成员");
        }
        return groupDao.delete(groupId) > 0;
    }

    public Group getGroupById(int groupId) {
        return groupDao.selectById(groupId);
    }


    public Group getGroupByName(String groupName) {
        return groupDao.selectByName(groupName);
    }
    public List<Group> listGroups(){
        return groupDao.selectAll();
    }

    public boolean checkGroupExists(int groupId) {
        return (groupDao.selectById(groupId) != null);  //不等于null→存在→直值
    }
}

