package proto.user;

import dao.User;
import proto.Command;

public class UpdateUser implements Command
{
	private UserReceiver receiver;
	private User         user;
	private int          retCode;
	
	public UpdateUser(UserReceiver receiver, User user)
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
