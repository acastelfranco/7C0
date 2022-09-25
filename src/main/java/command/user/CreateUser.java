package command.user;

import command.Command;
import dao.UserEntry;

public class CreateUser implements Command
{
	private UserReceiver receiver;
	private UserEntry         user;
	private int          retCode;
	
	public CreateUser(UserReceiver receiver, UserEntry user)
	{
		this.receiver = receiver;
		this.user = user;
		this.retCode = 0;
	}

	@Override
	public void execute()
	{
		retCode = receiver.createEntry(user);
	}

	public int getRetCode() {
		return retCode;
	}
}
