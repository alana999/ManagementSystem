package com.alana.javaweb.model;
import java.sql.Timestamp;

public class Member {
    private int memberId;
    private String name; // 假设 name 也是一个存储在数据库中的列
    private int groupId;
    private Timestamp joinTime;
    private Timestamp updatedAt;
    private int userId;
    private int roleId;
    private String groupName;
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }



    // 构造函数
    public Member() {}

    // 带参数的构造函数
    public Member(int memberId, String name, int groupId, Timestamp joinTime, Timestamp updatedAt, int userId, int roleId) {
        this.memberId = memberId;
        this.name = name;
        this.groupId = groupId;
        this.joinTime = joinTime;
        this.updatedAt = updatedAt;
        this.userId = userId;
        this.roleId = roleId;
    }

    // Getter 和 Setter 方法
    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public Timestamp getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(Timestamp joinTime) {
        this.joinTime = joinTime;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
