package proto;

import dao.User;
import dao.mysql.UserDao;

public class UserReceiver
{
	public int createUser(User user)
	{
		UserDao userDao = new UserDao();
		
		return userDao.save(user);
	}

	public int updateUser(User user)
	{
		UserDao userDao = new UserDao();
		
		return userDao.update(user);
	}
	
	public int deleteUser(String username)
	{
		UserDao userDao = new UserDao();
		
		return userDao.delete(username);
	}
	
	public User readUser(String username)
	{
		UserDao userDao = new UserDao();
		
		return userDao.get(username);
	}
}