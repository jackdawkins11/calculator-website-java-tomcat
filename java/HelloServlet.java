
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.PrintWriter;
import java.sql.*;

public class HelloServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp ){
        PrintWriter pw = null;
        try{
            pw = resp.getWriter();
        }catch(Exception e){
            e.printStackTrace();
        }
        pw.write("Hello from HelloServlet.java");
        
        String url = "jdbc:mysql://localhost:3306";
        String user = "jack";
        String pass = "jack";

        
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(Exception e){
            pw.write("EXception: " + e.getLocalizedMessage());
        }

        try{
            Connection con = DriverManager.getConnection(url, user, pass);
            pw.write("Connection created");
        }catch(Exception e){
            pw.write("Exception: " + e.getLocalizedMessage() );
        }
        
        
        pw.close();


    }
}