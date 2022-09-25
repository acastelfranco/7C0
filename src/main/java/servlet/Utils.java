package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Utils
{
    public static String getBody(HttpServletRequest request) throws IOException
    {
    	BufferedReader reader = request.getReader();
		Stream<String> stream = reader.lines();
		String body = stream.collect(Collectors.joining());
		
		return body;
    }
    
    public static void dumpRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	response.setContentType("text/html");
    	
    	PrintWriter writer = response.getWriter();
    	
		String body = getBody(request);
			
		writer.append(body);
		writer.flush();
		writer.close();
    }
    
    public static void reportError(HttpServletResponse response, int httpCode, String message) throws IOException
	{
    	PrintWriter writer = response.getWriter();
		response.setStatus(httpCode);
		response.setContentType("application/json");
		writer.append("{ \"reason\": " + "\"" + message + "\" }");
		writer.flush();
		writer.close();
	}
    
    public static void reportSuccess(HttpServletResponse response, int httpCode) throws IOException
	{
    	PrintWriter writer = response.getWriter();
		response.setStatus(httpCode);
		response.setContentType("application/json");
		writer.append("{ }");
		writer.flush();
		writer.close();
	}
}
