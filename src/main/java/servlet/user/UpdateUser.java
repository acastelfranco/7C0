package servlet.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servlet.Utils;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import command.user.UserClient;
import dao.UserEntry;

public class UpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUser() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.getWriter().append("<head>");
		response.getWriter().append("<link id=\"style\" rel=\"stylesheet\" href=\"../css/7c0.css\">");
		response.getWriter().append("</head>");
		response.getWriter().append("<p class=\"centered\" style=\"font-family:monospace\">");
		response.getWriter().append("<table>");
		response.getWriter().append("<tr>");
		response.getWriter().append("<td>");
		response.getWriter().append("<mark><b>UpdateUser command syntax</b></mark><br><br>");
		response.getWriter().append("</td>");
		response.getWriter().append("<tr>");
		response.getWriter().append("<td>");
		response.getWriter().append("{<br>"
				+ "&nbsp;&nbsp;&nbsp;&nbsp;\"username\"&nbsp;: \"username of the user we want to update\"<br>"
				+ "&nbsp;&nbsp;&nbsp;&nbsp;\"password\"&nbsp;: \"password of the user we want to update\"<br>"
				+ "&nbsp;&nbsp;&nbsp;&nbsp;\"name\"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;: \"name of the user we want to update\"<br>"
				+ "&nbsp;&nbsp;&nbsp;&nbsp;\"surname\"&nbsp;&nbsp;: \"surname of the user we want to update\"<br>"
				+ "}");
		response.getWriter().append("</td>");
		response.getWriter().append("</tr>");
		response.getWriter().append("</table>");
		response.getWriter().append("</p>");
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!request.getContentType().equals("application/json")) {
			Utils.reportError(response, 400, "bad request");
			return;
		}
		
		JSONParser parser = new JSONParser();
		JSONObject json = null;
		
		try
		{
			json = (JSONObject) parser.parse(Utils.getBody(request));
			UserEntry existingUser = new UserEntry();
			existingUser.fromJSON(json);
			UserClient.updateUser(response, existingUser);
		}
		
		catch (ParseException e) {
			Utils.reportError(response, 500, "malformed command");
		}
	}

}
