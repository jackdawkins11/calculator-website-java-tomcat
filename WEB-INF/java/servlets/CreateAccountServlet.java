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
    
    /*
        Handles the POST request to /CreateAccount.
        If the username and password given are valid, an account
        is created. Returns:
        {
            error: (bool) whether there was an error,
            createdAccount: (bool) whether an account was created,
            message (string) Why an account was not created, if it was not
        }
    */
    public void doPost(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        
        /*
            Check if username and password were supplied
        */
        if( username == null || password == null){
            res.sendError(400, "Must include username and password params.");
            return;
        }

        PrintWriter pw = res.getWriter();       

        /*
            Check if they are valid
        */
        ValidationResult valid = null;
        
        try{
            valid = UserValidator.validate(username, password);
        }catch(Exception e){
            res.sendError(500, "Exception checking for username.");
            return;
        }
        
        //return the problem if they are invalid
        if( !valid.getValid() ){
            pw.write(
                "{ error: false, \n"
                + "createdAccount: false, \n"
                + "message: \"" + valid.getMessage() + "\"}"
            );
            return;
        }

        DBInterface dao = DBInterface.getDAO();
        try{
            dao.insertUser(username, password);
        }catch(Exception e){
            throw new ServletException(e.getLocalizedMessage());
        }

        pw.write(
            "{ error: false, createdAccount: true, message: \"\"}"
        );
    }
}
