package command.user;

import command.Command;

public class DeleteUser implements Command
{
	private UserReceiver receiver;
	private String       username;
	private int          retCode;
	
	public DeleteUser(UserReceiver receiver, String username)
	{
		this.receiver = receiver;
		this.username = username;
		this.retCode = 0;
	}

	@Override
	public void execute()
	{
		retCode = receiver.deleteEntry(username);
	}

	public int getRetCode() {
		return retCode;
	}

}
