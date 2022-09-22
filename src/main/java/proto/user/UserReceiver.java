package proto.user;

import dao.User;
import dao.mysql.UserDao;
import proto.BaseReceiver;

public class UserReceiver extends BaseReceiver<User, String>
{
	public UserReceiver() {
		super(new UserDao());
	}
}