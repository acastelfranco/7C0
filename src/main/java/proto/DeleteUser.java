package proto;

public class DeleteUser implements Command
{
	private UserReceiver userReceiver;
	private String       username;
	private int          retCode;
	
	public DeleteUser(UserReceiver userReceiver, String username)
	{
		this.userReceiver = userReceiver;
		this.username = username;
		this.retCode = 0;
	}

	@Override
	public void execute()
	{
		retCode = userReceiver.deleteUser(username);
	}

	public int getRetCode() {
		return retCode;
	}

}
