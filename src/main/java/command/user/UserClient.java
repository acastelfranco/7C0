package command.user;

import java.io.IOException;
import java.io.PrintWriter;

import org.json.simple.parser.ParseException;

import command.Invoker;
import dao.UserEntry;
import jakarta.servlet.http.HttpServletResponse;
import servlet.Utils;

public class UserClient {
	
	public static UserEntry readUser(String username)
	{
		UserReceiver userReceiver = new UserReceiver();
		ReadUser read = new ReadUser(userReceiver, username);
		Invoker invoker = new Invoker(read);
		invoker.execute();
		return read.getUser();
	}

	public static void readUser(HttpServletResponse response, String username) throws IOException, ParseException
	{
		PrintWriter writer = response.getWriter();
		UserEntry user = readUser(username);
		writer.append(user.toJSON().toJSONString());
		response.setContentType("application/json");
		writer.flush();
		writer.close();
	}
	
	public static int createUser(UserEntry user)
	{
		UserReceiver receiver = new UserReceiver();
		CreateUser command = new CreateUser(receiver, user);
		Invoker invoker = new Invoker(command);
		invoker.execute();
		return command.getRetCode();
	}

	public static void createUser(HttpServletResponse response, UserEntry user) throws IOException
	{
		int result = createUser(user);
		
		if (result != 0) 
		{
			Utils.reportError(response, 500, "Unable to create the user: " + user.getUsername());
			return;
		}
		
		Utils.reportSuccess(response, 200);
	}
	
	public static int updateUser(UserEntry user)
	{
		UserReceiver receiver = new UserReceiver();
		UpdateUser command = new UpdateUser(receiver, user);
		Invoker invoker = new Invoker(command);
		invoker.execute();
		
		return command.getRetCode();
	}
	
	public static void updateUser(HttpServletResponse response, UserEntry user) throws IOException
	{
		int result = updateUser(user);
		
		if (result != 0) 
		{
			Utils.reportError(response, 500, "Unable to update the user: " + user.getUsername());
			return;
		}
		
		Utils.reportSuccess(response, 200);
	}
	
	public static int deleteUser(String username)
	{
		UserReceiver receiver = new UserReceiver();
		DeleteUser command = new DeleteUser(receiver, username);
		Invoker invoker = new Invoker(command);
		invoker.execute();
		return command.getRetCode();
	}
	
	public static void deleteUser(HttpServletResponse response, String username) throws IOException
	{
		int result = deleteUser(username);
		
		if (result != 0) 
		{
			Utils.reportError(response, 500, "Unable to delete the user: " + username);
			return;
		}
		
		Utils.reportSuccess(response, 200);
	}
}
