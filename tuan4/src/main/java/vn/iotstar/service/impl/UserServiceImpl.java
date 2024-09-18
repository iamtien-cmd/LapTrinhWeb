package vn.iotstar.service.impl;

import vn.iotstar.dao.UserDao;
import vn.iotstar.dao.impl.UserDaoImpl;
import vn.iotstar.model.User;
import vn.iotstar.service.UserService;

public class UserServiceImpl implements UserService{
	// lay tat ca ham trong tang Dao
	public UserDao userDao = new UserDaoImpl();

	public User login(String username, String password) {
		User user = this.findByUserName(username);
		
		if (user != null && password.equals(user.getPassWord())) {
		return user;
		}
		return null;
	}
	public User findByUserName(String username) {
		return userDao.findByUserName(username);
		}

	@Override
	public User get(String username) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void insert(User user) {
		userDao.insert(user);
		
	}
	@Override
	public boolean register(String email, String password, String username, String fullname, String phone) {
		if (userDao.checkExistUsername(username)) {
			return false;
			}
		if (userDao.checkExistEmail(email)) {
			return false;
		}
		if (userDao.checkExistPhone(phone)) {
			return false;
		}
			long millis=System.currentTimeMillis();
			java.sql.Date date=new java.sql.Date(millis);
			userDao.insert(new User(email, username, fullname,password,null,5,phone,date));
			return true;
	}
	@Override
	public boolean checkExistEmail(String email) {
		return userDao.checkExistEmail(email);
	}
	@Override
	public boolean checkExistUsername(String username) {
		return userDao.checkExistUsername(username);
	}
	@Override
	public boolean checkExistPhone(String phone) {
		return userDao.checkExistPhone(phone);
	}
	@Override
	public boolean updatePassword(String username, String newpassword) {
		return userDao.updatePassword(username,newpassword);
	}
}
