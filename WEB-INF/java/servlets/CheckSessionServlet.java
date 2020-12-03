package servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import java.io.PrintWriter;
import java.io.IOException;

import data.DBInterface;

public class CheckSessionServlet extends HttpServlet {

    //Handles the POST request to /CheckSession
    //Returns {
    // error: (bool) whether there was an error,
    // hasSession: (bool) whether the client has a session
    //}
    public void doPost(HttpServletRequest req, HttpServletResponse res )
        throws ServletException, IOException {
        
        //Check for a session
        boolean hasSession = req.getSession().getAttribute("userId") != null;
        
        PrintWriter pw = res.getWriter();

        //Write response as json
        pw.write(
            "{ error: false, hasSession: " + String.valueOf(hasSession) + " }"
        );
    }
    
}
