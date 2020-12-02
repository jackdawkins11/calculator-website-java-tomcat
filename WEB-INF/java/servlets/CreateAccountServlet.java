package servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.PrintWriter;
import java.io.IOException;

import data.DBInterface;
import util.userValidation.UserValidator;
import util.userValidation.ValidationResult;

public class CreateAccountServlet extends HttpServlet {
    
    public void doPost(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if( username == null || password == null){
            res.sendError(400, "Must include username and password params.");
            return;
        }

        PrintWriter pw = res.getWriter();       

        ValidationResult valid = null;
        
        try{
            valid = UserValidator.validate(username, password);
        }catch(Exception e){
            res.sendError(500, "Exception checking for username.");
            return;
        }
        
        if( !valid.getValid() ){
            pw.write(
                "{ error: false, \n"
                + "createAccount: false, \n"
                + "message: \"" + valid.getMessage() + "\"}"
            );
            return;
        }

    }
}
