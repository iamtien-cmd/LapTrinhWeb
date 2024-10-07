package vn.iotstar.dao;

import java.util.List;

import vn.iotstar.model.User;

public interface UserDao {
	User findByUserName(String username);
	User get(String username);
	void insert(User user);
	boolean checkExistEmail(String email);
	boolean checkExistUsername(String username);
	boolean checkExistPhone(String phone);
	List<User> findAll();
	boolean updatePassword(String username, String newpassword);
	boolean updateProfile(String username, String fullname, String phone, String avatar);
}
