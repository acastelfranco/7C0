package servlet.board;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import command.board.BoardClient;
import dao.BoardEntry;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servlet.Utils;

/**
 * Servlet implementation class UpdateBoardEntry
 */
public class UpdateBoardEntry extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateBoardEntry() {
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
		response.getWriter().append("<mark><b>UpdateBoardEntry command syntax</b></mark><br><br>");
		response.getWriter().append("</td>");
		response.getWriter().append("<tr>");
		response.getWriter().append("<td>");
		response.getWriter().append("{<br>"
				+ "&nbsp;&nbsp;&nbsp;&nbsp;\"countryId\"&nbsp;: \"country id of the country we want to update\"<br>"
				+ "&nbsp;&nbsp;&nbsp;&nbsp;\"username\"&nbsp;&nbsp;: \"username of the user that added a tank in the country\"<br>"
				+ "&nbsp;&nbsp;&nbsp;&nbsp;\"game\"&nbsp;&nbsp;: \"game name\"<br>"
				+ "}");
		response.getWriter().append("</td>");
		response.getWriter().append("</tr>");
		response.getWriter().append("</table>");
		response.getWriter().append("</p>");
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest request, HttpServletResponse response)
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
			BoardClient.updateBoardEntry(response, (String) json.get("countryId"), (String) json.get("username"), (String) json.get("game"));
		}
		
		catch (ParseException e) {
			Utils.reportError(response, 500, "malformed command");
		}
	}

}
