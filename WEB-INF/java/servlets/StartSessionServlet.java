package servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import java.io.PrintWriter;
import java.io.IOException;

import data.DBInterface;

public class StartSessionServlet extends HttpServlet {
    
    /*
        Handles the POST request to /StartSession. Checks if the supplied
        username and password are associated with an account, and if so
        starts a session with the user.
        Return {
            error: (bool) whether there was an error
            hasSession: (bool) whether a session was created
        }
    */
    public void doPost(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //Check if username and password were supplied
        if( username == null || password == null ){
            throw new ServletException("Error: username and password were not supplied");
        }

        //Check if authorized
        DBInterface dao = DBInterface.getDAO();

        boolean authorized = false;

        try{
            authorized = dao.authorizeUser(username, password);
        }catch( Exception e){
            throw new ServletException("Exception authorizing user: " + e.getLocalizedMessage() );
        }

        //If so, add session with the users DB id
        if( authorized ){
            HttpSession sess = req.getSession();

            int userId = 0;

            try{
                userId = dao.getIdByUsername( username );
            }catch(Exception e){
                throw new ServletException("Exception getting user id: " + e.getLocalizedMessage() );
            }

            sess.setAttribute("userId", userId);
        }

        //Return whether a session was created
        PrintWriter pw = res.getWriter();
        
        res.setContentType("application/json");
        pw.write("{ \"error\": false, \"hasSession\": " + String.valueOf(authorized) + " }" );

    }
}
