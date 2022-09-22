package proto.user;

import java.io.IOException;
import java.io.PrintWriter;

import org.json.simple.parser.ParseException;

import dao.User;
import jakarta.servlet.http.HttpServletResponse;
import proto.Invoker;
import servlets.Utils;

public class UserClient {

	public static void readUser(HttpServletResponse response, String username) throws IOException, ParseException
	{
		PrintWriter writer = response.getWriter();
		UserReceiver receiver = new UserReceiver();
		ReadUser command = new ReadUser(receiver, username);
		Invoker invoker = new Invoker(command);
		invoker.execute();
		User user = command.getUser();
		writer.append(user.toJSON().toJSONString());
		response.setContentType("application/json");
		writer.flush();
		writer.close();
	}

	public static void createUser(HttpServletResponse response, User user) throws IOException
	{
		UserReceiver receiver = new UserReceiver();
		CreateUser command = new CreateUser(receiver, user);
		Invoker invoker = new Invoker(command);
		invoker.execute();
		int result = command.getRetCode();
		
		if (result != 0) 
		{
			Utils.reportError(response, 500, "Unable to create the user: " + user.getUsername());
			return;
		}
		
		Utils.reportSuccess(response, 200);
	}
	
	public static void updateUser(HttpServletResponse response, User user) throws IOException
	{
		UserReceiver receiver = new UserReceiver();
		UpdateUser command = new UpdateUser(receiver, user);
		Invoker invoker = new Invoker(command);
		invoker.execute();
		int result = command.getRetCode();
		
		if (result != 0) 
		{
			Utils.reportError(response, 500, "Unable to update the user: " + user.getUsername());
			return;
		}
		
		Utils.reportSuccess(response, 200);
	}
	
	public static void deleteUser(HttpServletResponse response, String username) throws IOException
	{
		UserReceiver receiver = new UserReceiver();
		DeleteUser command = new DeleteUser(receiver, username);
		Invoker invoker = new Invoker(command);
		invoker.execute();
		int result = command.getRetCode();
		
		if (result != 0) 
		{
			Utils.reportError(response, 500, "Unable to delete the user: " + username);
			return;
		}
		
		Utils.reportSuccess(response, 200);
	}
}
