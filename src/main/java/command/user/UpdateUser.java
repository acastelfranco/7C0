package command.user;

import command.Command;
import dao.UserEntry;

public class UpdateUser implements Command
{
	private UserReceiver receiver;
	private UserEntry         user;
	private int          retCode;
	
	public UpdateUser(UserReceiver receiver, UserEntry user)
	{
		this.receiver = receiver;
		this.user = user;
		this.retCode = 0;
	}

	@Override
	public void execute()
	{
		receiver.updateEntry(user);
	}

	public int getRetCode()
	{
		return retCode;
	}

}
