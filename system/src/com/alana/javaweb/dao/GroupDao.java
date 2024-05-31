package com.alana.javaweb.dao;


import com.alana.javaweb.model.Group;
import com.alana.javaweb.model.User;
import com.alana.javaweb.utils.DButil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class GroupDao {

    /**
     * 插入小组信息
     *
     * @param group 小组对象
     * @return 插入的行数
     */
    public int insert(Group group) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DButil.getConnection();
            String sql = "INSERT INTO ms_groups (group_name, description) VALUES(?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, group.getGroupName());
            ps.setString(2, group.getDescription());
            count = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DButil.close(conn, ps, null);
        }
        return count;
    }

    /**
     * 更新小组信息
     *
     * @param group 小组对象
     * @return 更新的行数
     */
    public int update(Group group) {


        // 连接数据库执行更新语句
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DButil.getConnection();
            String sql = "UPDATE ms_groups SET group_name = ?, description = ? WHERE group_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, group.getGroupName());
            ps.setString(2, group.getDescription());
            ps.setInt(3, group.getGroupId());
            count = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DButil.close(conn, ps, null);
        }
        return count;
    }

    /**
     * 删除小组信息
     *
     * @param groupId 小组ID
     * @return 删除的行数
     */
    public int delete(int groupId) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DButil.getConnection();
            String sql = "DELETE FROM ms_groups WHERE group_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, groupId);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DButil.close(conn, ps, null);
        }
        return count;
    }

    /**
     * 通过小组ID查询小组信息
     *
     * @param groupId 小组ID
     * @return 小组对象
     */
    public Group selectById(int groupId) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Group group = null;
        try {
            conn = DButil.getConnection();
            String sql = "SELECT group_id, group_name, description, created_at, updated_at FROM ms_groups WHERE group_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, groupId);

            rs = ps.executeQuery();
            if (rs.next()) {
                group = new Group();
                group.setGroupId(rs.getInt("group_id"));
                group.setGroupName(rs.getString("group_name"));
                group.setDescription(rs.getString("description"));

                group.setCreatedAt(rs.getTimestamp("created_at"));
                group.setUpdatedAt(rs.getTimestamp("updated_at"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DButil.close(conn, ps, rs);
        }
        return group;
    }


    public Group selectByName(String groupName){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Group group = null;
        try {
            conn = DButil.getConnection();
            String sql = "SELECT group_id, group_name, description, created_at, updated_at FROM ms_groups WHERE group_name = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, groupName);

            rs = ps.executeQuery();
            if (rs.next()) {
                group = new Group();
                group.setGroupId(rs.getInt("group_id"));
                group.setGroupName(rs.getString("group_name"));
                group.setDescription(rs.getString("description"));

                group.setCreatedAt(rs.getTimestamp("created_at"));
                group.setUpdatedAt(rs.getTimestamp("updated_at"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DButil.close(conn, ps, rs);
        }
        return group;
    }

    public List<Group> selectAll() {
        List<Group> groupList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DButil.getConnection();
            String sql = "SELECT group_id, group_name, description, created_at, updated_at FROM ms_groups";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            // 遍历结果集
            while (rs.next()) {
                Group group = new Group();
                group.setGroupId(rs.getInt("group_id"));
                group.setGroupName(rs.getString("group_name"));
                group.setDescription(rs.getString("description"));

                group.setCreatedAt(rs.getTimestamp("created_at"));
                group.setUpdatedAt(rs.getTimestamp("updated_at"));
                // 将小组对象放到list集合当中
                groupList.add(group);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DButil.close(conn, ps, null);
        }
        return groupList;

    }

}

