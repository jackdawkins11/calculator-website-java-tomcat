package servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import java.io.PrintWriter;
import java.io.IOException;

public class EndSessionServlet extends HttpServlet {
    
    public void doPost(HttpServletRequest req, HttpServletResponse res )
        throws IOException, ServletException {
        
        req.getSession().invalidate();

        res.getWriter().write(
            "{ error: false }"
        );
    }
}
