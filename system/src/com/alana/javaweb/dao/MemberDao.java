package com.alana.javaweb.dao;

import com.alana.javaweb.model.Group;
import com.alana.javaweb.model.Member;
import com.alana.javaweb.utils.DButil;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDao {

    public int insert(Member member) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DButil.getConnection();
            conn.setAutoCommit(true);
            String sql = "INSERT INTO Members (name, group_id) VALUES(?, ?)";
            ps = conn.prepareStatement(sql);

            ps.setString(1, member.getName());
            ps.setInt(2, member.getGroupId());
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.close(conn, ps, null);
        }
        return count;
    }

    public int update(Member mem) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DButil.getConnection();
            String sql = "UPDATE Members SET name = ?, group_id = ? WHERE member_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, mem.getName());
            ps.setInt(2, mem.getGroupId());
            ps.setInt(3, mem.getMemberId());
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.close(conn, ps, null);
        }
        return count;
    }

    public int delete(int memberId) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DButil.getConnection();
            String sql = "DELETE FROM Members WHERE member_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, memberId);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.close(conn, ps, null);
        }
        return count;
    }

    public Member selectById(int memberId) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Member member = null;
        try {
            conn = DButil.getConnection();
            String sql = "SELECT member_id, name, group_id FROM Members where member_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,memberId);
            rs = ps.executeQuery();
            if (rs.next()) {
                member = new Member();
                member.setMemberId(rs.getInt("member_id"));
                member.setName(rs.getString("name"));
                member.setGroupId(rs.getInt("group_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.close(conn, ps, rs);
        }
        return member;
    }

    public Member selectByName(String name) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Member member = null;
        try {
            conn = DButil.getConnection();
            String sql = "SELECT a.member_id, a.name, a.group_id, b.group_name " +
                    "FROM members a " +
                    "JOIN ms_groups b ON a.group_id = b.group_id " +
                    "WHERE a.name = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,name);
            rs = ps.executeQuery();
            if (rs.next()) {
                member = new Member();
                member.setMemberId(rs.getInt("member_id"));
                member.setName(rs.getString("name"));
                member.setGroupId(rs.getInt("group_id"));
                member.setGroupName(rs.getString("group_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.close(conn, ps, rs);
        }
        return member;
    }


    public List<Member> selectAll() {
        List<Member> memberList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DButil.getConnection();
            String sql = "SELECT member_id, name, group_id , join_time, updated_at FROM members order by group_id";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            // 遍历结果集
            while (rs.next()) {
                Member member = new Member();
                member.setMemberId(rs.getInt("member_id"));
                member.setName(rs.getString("name"));
                member.setGroupId(rs.getInt("group_id"));
                member.setJoinTime(rs.getTimestamp("join_time"));
                member.setUpdatedAt(rs.getTimestamp("updated_at"));
                // 将对象放到list集合当中
                memberList.add(member);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DButil.close(conn, ps, null);
        }
        return memberList;

    }

    public List<Member> selectByGroupId(int groupId){
        List<Member> memberList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DButil.getConnection();
            String sql = "SELECT member_id, name, group_id , join_time, updated_at FROM members where group_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,groupId);
            rs = ps.executeQuery();
            // 遍历结果集
            while (rs.next()) {
                Member member = new Member();
                member.setMemberId(rs.getInt("member_id"));
                member.setName(rs.getString("name"));
                member.setGroupId(rs.getInt("group_id"));
                member.setJoinTime(rs.getTimestamp("join_time"));
                member.setUpdatedAt(rs.getTimestamp("updated_at"));
                // 将对象放到list集合当中
                memberList.add(member);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DButil.close(conn, ps, null);
        }
        return memberList;
    }
}
