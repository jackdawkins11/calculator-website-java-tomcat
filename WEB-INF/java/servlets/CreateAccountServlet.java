package servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class CreateAccountServlet extends HttpServlet {
    
    public void doPost(HttpServletRequest req, HttpServletResponse res){

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if( username == null || password == null){
            res.sendError(400, "Must include username and password params.");
            return;
        }

    }
}
