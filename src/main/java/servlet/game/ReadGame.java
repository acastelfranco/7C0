package servlet.game;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import command.game.GameClient;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servlet.Utils;

/**
 * Servlet implementation class ReadGame
 */
public class ReadGame extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReadGame() {
        super();
        // TODO Auto-generated constructor stub
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
		response.getWriter().append("<mark><b>ReadUser command syntax</b></mark><br><br>");
		response.getWriter().append("</td>");
		response.getWriter().append("<tr>");
		response.getWriter().append("<td>");
		response.getWriter().append("{<br>"
				+ "&nbsp;&nbsp;&nbsp;&nbsp;\"name\"&nbsp;: \"name of the game we want to get the information\"<br>"
				+ "&nbsp;&nbsp;&nbsp;&nbsp;\"username\"&nbsp;: \"username of the logged user\"<br>"
				+ "}");
		response.getWriter().append("</td>");
		response.getWriter().append("</tr>");
		response.getWriter().append("</table>");
		response.getWriter().append("</p>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
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
			GameClient.readGame(response, (String) json.get("name"), (String) json.get("username"));
		}
		
		catch (ParseException e) {
			Utils.reportError(response, 500, "malformed command");
		}
	}

}
