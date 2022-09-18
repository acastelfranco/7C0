package proto;

import dao.User;

public class CreateUser implements Command
{
	private UserReceiver userReceiver;
	private User         user;
	private int          retCode;
	
	public CreateUser(UserReceiver userReceiver, User user)
	{
		this.userReceiver = userReceiver;
		this.user = user;
		this.retCode = 0;
	}

	@Override
	public void execute()
	{
		retCode = userReceiver.createUser(user);
	}

	public int getRetCode() {
		return retCode;
	}
}
