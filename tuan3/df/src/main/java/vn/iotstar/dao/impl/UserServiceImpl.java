package vn.iotstar.dao.impl;

import vn.iotstar.dao.UserDao;
import vn.iotstar.dao.UserService;
import vn.iotstar.model.User;

public class UserServiceImpl implements UserService{
	public UserDao userDao = new UserDaoImpl();

	@Override
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
}
