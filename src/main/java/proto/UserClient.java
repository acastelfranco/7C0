package proto;

import java.io.IOException;
import java.io.PrintWriter;

import org.json.simple.parser.ParseException;

import dao.User;
import jakarta.servlet.http.HttpServletResponse;
import servlets.Utils;

public class UserClient {

	public static void readUser(HttpServletResponse response, String username) throws IOException, ParseException
	{
		PrintWriter writer = response.getWriter();
		UserReceiver userReceiver = new UserReceiver();
		ReadUser readUserCommand = new ReadUser(userReceiver, username);
		Invoker invoker = new Invoker(readUserCommand);
		invoker.execute();
		User user = readUserCommand.getUser();
		writer.append(user.toJSON().toJSONString());
		response.setContentType("application/json");
		writer.flush();
		writer.close();
	}

	public static void createUser(HttpServletResponse response, User user) throws IOException
	{
		UserReceiver userReceiver = new UserReceiver();
		CreateUser createUserCommand = new CreateUser(userReceiver, user);
		Invoker invoker = new Invoker(createUserCommand);
		invoker.execute();
		int result = createUserCommand.getRetCode();
		
		if (result != 0) 
		{
			Utils.reportError(response, 500, "Unable to create the user: " + user.getUsername());
			return;
		}
		
		Utils.reportSuccess(response, 200);
	}
	
	public static void updateUser(HttpServletResponse response, User user) throws IOException
	{
		UserReceiver userReceiver = new UserReceiver();
		UpdateUser updateUserCommand = new UpdateUser(userReceiver, user);
		Invoker invoker = new Invoker(updateUserCommand);
		invoker.execute();
		int result = updateUserCommand.getRetCode();
		
		if (result != 0) 
		{
			Utils.reportError(response, 500, "Unable to update the user: " + user.getUsername());
			return;
		}
		
		Utils.reportSuccess(response, 200);
	}
	
	public static void deleteUser(HttpServletResponse response, String username) throws IOException
	{
		UserReceiver userReceiver = new UserReceiver();
		DeleteUser deleteUserCommand = new DeleteUser(userReceiver, username);
		Invoker invoker = new Invoker(deleteUserCommand);
		invoker.execute();
		int result = deleteUserCommand.getRetCode();
		
		if (result != 0) 
		{
			Utils.reportError(response, 500, "Unable to delete the user: " + username);
			return;
		}
		
		Utils.reportSuccess(response, 200);
	}
}
