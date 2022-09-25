package command.user;

import command.Command;
import dao.UserEntry;

public class ReadUser implements Command
{
	private UserReceiver receiver;
	private String       username;
	private UserEntry         user;
	
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

	public UserEntry getUser() {
		return user;
	}
}
