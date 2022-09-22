package proto.user;

import dao.User;
import proto.Command;

public class ReadUser implements Command
{
	private UserReceiver receiver;
	private String       username;
	private User         user;
	
	public ReadUser(UserReceiver receiver, String username)
	{
		this.receiver = receiver;
		this.username = username;
		this.user = null;
	}

	@Override
	public void execute()
	{
		user = receiver.readEntry(username);
	}

	public User getUser() {
		return user;
	}
}
