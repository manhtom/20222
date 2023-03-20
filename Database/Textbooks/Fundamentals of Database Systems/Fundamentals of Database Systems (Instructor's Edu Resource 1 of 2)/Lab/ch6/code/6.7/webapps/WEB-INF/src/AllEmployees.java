import com.db4o.*;
import com.db4o.query.*;

import java.io.*;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

public class AllEmployees extends HttpServlet {

  public void doGet (HttpServletRequest request,
                     HttpServletResponse response)
      throws ServletException, IOException {

    doPost(request,response);
  }

  public void doPost (HttpServletRequest request,
                     HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    ServletContext context = getServletContext();
    ObjectServer server=(ObjectServer)context.getAttribute("db4oServer");
    ObjectContainer db = server.openClient();

    out.println("<html>");
    out.println("<head>");
    out.println("<title>All Employees</title>");
    out.println("</head>");
    out.println("<body>");

    out.println("<h2>Employees of Company</h2>");
    out.println("<table border=\"2\" cellspacing=\"2\" cellpadding=\"2\">");
    out.println("<tr>");
    out.println("<th><font face=\"Arial, Helvetica, sans-serif\">"+
                "Employee SSN</font></th>");
    out.println("<th><font face=\"Arial, Helvetica, sans-serif\">"+
                "Employee First Name</font></th>");
    out.println("<th><font face=\"Arial, Helvetica, sans-serif\">"+
                "Employee Last Name</font></th>");
    out.println("</tr>");

    try {
      Query query = db.query();
      query.constrain(Employee.class);
      query.descend("lname").orderAscending();
      ObjectSet results = query.execute();
      while (results.hasNext()) {
        Employee e = (Employee) results.next();
        out.println("<tr>");
        out.println("<td><font face=\"Arial, Helvetica, sans-serif\">"+
                    "<a href=\"DisplayEmployee?ssn="+e.getSsn()+"\">"+
                    e.getSsn()+"</a></font></td>");
        out.println("<td><font face=\"Arial, Helvetica, sans-serif\">"+
                    e.getFname()+"</font></td>");
        out.println("<td><font face=\"Arial, Helvetica, sans-serif\">"+
                    e.getLname()+"</font></td>");
        out.println("</tr>");
      }
    } catch (Exception e) {
        out.println("Exception: " + e.getMessage());
      }

    out.println("</body>");
    out.println("</html>");
    out.close();

    db.close();
  }

}
