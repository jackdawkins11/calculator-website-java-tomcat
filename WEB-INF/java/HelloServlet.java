
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.PrintWriter;

public class HelloServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp ){
        PrintWriter pw = null;
        try{
            pw = resp.getWriter();
        }catch(Exception e){
            e.printStackTrace();
        }
        pw.write("Hello from HelloServlet.java");
        pw.close();
    }
}