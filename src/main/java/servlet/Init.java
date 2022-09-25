package servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class Init implements ServletContextListener
{
	public static Connection connection;
	
	public void contextInitialized(ServletContextEvent event) {
		try {
			ServletContext context = event.getServletContext();
			String dburl = context.getInitParameter("dburl");
	    	String username = context.getInitParameter("username");
	    	String password = context.getInitParameter("password");
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(dburl, username, password);
		} 
		
		catch (ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void contextDestroyed(ServletContextEvent arg0)
	{
		
	}
}
