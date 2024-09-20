package vn.iotstar.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vn.iotstar.dao.DBConnection;
import vn.iotstar.dao.UserDao;
import vn.iotstar.model.User;

public class UserDaoImpl implements UserDao {
    public Connection conn = null;
    public PreparedStatement ps = null;
    public ResultSet rs = null;

    @Override
    public List<User> findAll() {
        String sql = "select * from users";
        List<User> list = new ArrayList<>();
        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                list.add(new User(
				/*
				 * rs.getString("email"), rs.getString("username"), rs.getString("fullname"),
				 * rs.getString("password"), rs.getString("avatar"), rs.getInt("roleid"),
				 * rs.getString("phone"), rs.getDate("createdDate")
				 */
                ));
            }
            return list;
        } catch (Exception e) {
            System.out.print(e);
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) { }
            try { if (ps != null) ps.close(); } catch (Exception e) { }
            try { if (conn != null) conn.close(); } catch (Exception e) { }
        }
        return null;
    }

    @Override
    public User findByUserName(String username) {
        String sql = "SELECT * FROM [Users] WHERE username = ?";
        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setUserName(rs.getString("userName"));
                user.setFullName(rs.getString("fullname"));
                user.setPassWord(rs.getString("passWord"));
                user.setAvatar(rs.getString("avatar"));
                user.setRoleid(rs.getInt("roleid"));
                user.setPhone(rs.getString("phone"));
                user.setCreateDate(rs.getDate("createdDate"));
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
		} /*
			 * finally { try { if (rs != null) rs.close(); } catch (Exception e) { } try {
			 * if (ps != null) ps.close(); } catch (Exception e) { } try { if (conn != null)
			 * conn.close(); } catch (Exception e) { } }
			 */
        return null;
    }

    @Override
    public void insert(User user) {
        String sql = "INSERT INTO [Users](email, username, fullname, password, avatar, roleid, phone, createddate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getUserName());
            ps.setString(3, user.getFullName());
            ps.setString(4, user.getPassWord());
            ps.setString(5, user.getAvatar());
            ps.setInt(6, user.getRoleid());
            ps.setString(7, user.getPhone());
            ps.setDate(8, user.getCreateDate());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (ps != null) ps.close(); } catch (Exception e) { }
            try { if (conn != null) conn.close(); } catch (Exception e) { }
        }
    }

    @Override
    public boolean checkExistEmail(String email) {
        boolean duplicate = false;
        String query = "select * from [users] where email = ?";
        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                duplicate = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) { }
            try { if (ps != null) ps.close(); } catch (Exception e) { }
            try { if (conn != null) conn.close(); } catch (Exception e) { }
        }
        return duplicate;
    }

    @Override
    public boolean checkExistUsername(String username) {
        boolean duplicate = false;
        String query = "select * from [Users] where username = ?";
        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                duplicate = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) { }
            try { if (ps != null) ps.close(); } catch (Exception e) { }
            try { if (conn != null) conn.close(); } catch (Exception e) { }
        }
        return duplicate;
    }

    @Override
    public boolean checkExistPhone(String phone) {
        boolean duplicate = false;
        String query = "select * from [Users] where phone = ?";
        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, phone);
            rs = ps.executeQuery();
            if (rs.next()) {
                duplicate = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) { }
            try { if (ps != null) ps.close(); } catch (Exception e) { }
            try { if (conn != null) conn.close(); } catch (Exception e) { }
        }
        return duplicate;
    }

   

    @Override
    public User get(String username) {
        // TODO Auto-generated method stub
        return null;
    }

	@Override
	public boolean updatePassword(String username, String newPassword) {
	    boolean isUpdated = false;
	  
	    String query = "UPDATE [users] SET passWord = ? WHERE userName = ?";
	    System.out.print(" "+username+" "+ newPassword);
	    
	    try {
	        conn = new DBConnection().getConnection();
	        ps = conn.prepareStatement(query);
	
	        ps.setString(1, newPassword);
	        ps.setString(2, username);
	        int rowsAffected = ps.executeUpdate();
	        if (rowsAffected > 0) {
	            isUpdated = true;
	        }
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    } finally {
	        try { if (ps != null) ps.close(); } catch (Exception e) { }
	        try { if (conn != null) conn.close(); } catch (Exception e) { }
	    }
	    return isUpdated;
	}


}
