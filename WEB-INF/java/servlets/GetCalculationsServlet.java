package servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.IOException;

import data.DBInterface;
import data.Calculation;

public class GetCalculationsServlet extends HttpServlet {
    
    public void doPost(HttpServletRequest req, HttpServletResponse res)
        throws IOException, ServletException {
        
        ArrayList< Calculation > calculations = null;

        DBInterface dao = DBInterface.getDAO();
        
        try{
            calculations = dao.getLast10Calculations();
        }catch(Exception e){
            throw new ServletException( "Exception getting calculations: " + e.getLocalizedMessage() );
        }

        String strRes = "{ \"error\": false, \n"
            + " \"calculations\": [";

        for(int i =0; i < calculations.size(); i++){
            try{
                strRes += calculations.get(i).toJSON();
            }catch(Exception e ){
                throw new ServletException( "Exception getting calculations: " + e.getLocalizedMessage() );
            }
            if( i < calculations.size() - 1 ){
                strRes += ", ";
            }
        }

        strRes += "] }";

        res.setContentType("application/json");
        res.getWriter().write(strRes);
        
    }

}
