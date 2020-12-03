package servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import java.io.PrintWriter;
import java.io.IOException;

import data.DBInterface;
import data.Calculation;


public class InsertCalculationServlet extends HttpServlet {
    
    public void doPost(HttpServletRequest req, HttpServletResponse res )
        throws IOException, ServletException {
        
        String x = req.getParameter("x");
        String op = req.getParameter("op");
        String y = req.getParameter("y");
        String val = req.getParameter("val");
        String date = req.getParameter("date");
        int userId = (Integer) req.getSession().getAttribute("userId");

        if( x == null || op == null || y == null
            || val == null || date == null ){
            throw new ServletException("Must supply calculation params");
        }

        Calculation calculation = new Calculation(
            userId,
            Float.parseFloat(x), op, Float.parseFloat(y), Float.parseFloat(val),
            date
        );

        DBInterface dao = DBInterface.getDAO();

        try{
            dao.insertCalculation( calculation );
        }catch( Exception e){
            throw new ServletException("Exception inserting calculation: " + e.getLocalizedMessage() );
        }

        res.setContentType("application/json");
        res.getWriter().write("{ \"error\": false }");
        
    }
}
