package com.alana.javaweb.dao;

import com.alana.javaweb.model.User;
import com.alana.javaweb.utils.DButil;

import java.sql.*;
import java.util.List;

public class UserDao {


    /*
    @param user 用户名
    @return 1 表示键入成功
    * */
    public int insert(User user) {
        Connection conn = null;
        PreparedStatement ps =null;
        int count =0;
        try {
            conn = DButil.getConnection();
            String sql = "INSERT INTO User (username, password) VALUES(?,?)";
             ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            count = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DButil.close(conn,ps,null);
        }
        return count;
    }

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    public int update(User user) {
        Connection conn = null;
        PreparedStatement ps =null;
        int count =0;
        try {
            conn = DButil.getConnection();
            String sql = "update  User set username = ?,password=? where user_id =?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getUserId());

            count = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DButil.close(conn,ps,null);
        }
        return count;
    }

    /**
     *删除用户信息
     * @param username
     * @return
     */
    public int deleteByUsername(String username) {

        Connection conn = null;
        PreparedStatement ps =null;
        int count =0;
        try {
            conn = DButil.getConnection();
            String sql = "delete from User where username = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DButil.close(conn,ps,null);
        }
        return count;
    }


    /**
     * 通过username查询用户
     * @param username
     * @return
     */
    public User selectByUsername(String username) {
        Connection conn = null;
        PreparedStatement ps =null;
        ResultSet rs = null;
        int count =0;
        try {
            conn = DButil.getConnection();
            String sql = "SELECT user_id, username, password, email, created_at, updated_at FROM User where username =?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,username);

             rs = ps.executeQuery();
             if(rs.next()){
                 Integer userId = rs.getInt("user_id");
                 String foundUsername = rs.getString("username");
                 String email = rs.getString("email");
                 String password = rs.getString("password");
                 Timestamp created_at = rs.getTimestamp("created_at");
                 Timestamp updated_at = rs.getTimestamp("updated_at");

                 User user =new User(userId,username,password,email,created_at,updated_at);
             }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DButil.close(conn,ps,null);
        }
        return null;

    }

    public List<User> selectAll() {
        Connection conn = null;
        PreparedStatement ps =null;
        ResultSet rs = null;
        int count =0;
        try {
            conn = DButil.getConnection();
            String sql = "update  User set username = ?,password=? where user_id =?";
            ps = conn.prepareStatement(sql);


            count = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DButil.close(conn,ps,null);
        }
        return null;
    }

}
