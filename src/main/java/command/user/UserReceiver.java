package command.user;

import command.BaseReceiver;
import dao.UserEntry;
import dao.mysql.UserDao;

public class UserReceiver extends BaseReceiver<UserEntry, String>
{
	public UserReceiver() {
		super(new UserDao());
	}
}