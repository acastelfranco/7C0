package proto.user;

import dao.User;
import proto.Command;

public class CreateUser implements Command
{
	private UserReceiver receiver;
	private User         user;
	private int          retCode;
	
	public CreateUser(UserReceiver receiver, User user)
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
