package proto;

import dao.User;

public class UpdateUser implements Command
{
	private UserReceiver userReceiver;
	private User         user;
	private int          retCode;
	
	public UpdateUser(UserReceiver userReceiver, User user)
	{
		this.userReceiver = userReceiver;
		this.user = user;
		this.retCode = 0;
	}

	@Override
	public void execute()
	{
		userReceiver.updateUser(user);
	}

	public int getRetCode()
	{
		return retCode;
	}

}
