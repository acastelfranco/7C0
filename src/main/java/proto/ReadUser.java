package proto;

import dao.User;

public class ReadUser implements Command
{
	private UserReceiver userReceiver;
	private String       username;
	private User         user;
	
	public ReadUser(UserReceiver userReceiver, String username)
	{
		this.userReceiver = userReceiver;
		this.username = username;
		this.user = null;
	}

	@Override
	public void execute()
	{
		user = userReceiver.readUser(username);
	}

	public User getUser() {
		return user;
	}
}
