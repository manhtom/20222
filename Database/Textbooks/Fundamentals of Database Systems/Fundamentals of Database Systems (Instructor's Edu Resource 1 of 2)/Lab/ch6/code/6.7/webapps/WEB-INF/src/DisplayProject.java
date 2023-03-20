import com.db4o.*;
import com.db4o.query.Predicate;

import java.io.*;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

public class DisplayProject extends HttpServlet {

  public void doGet (HttpServletRequest request,
                     HttpServletResponse response)
      throws ServletException, IOException {

    doPost(request,response);
  }

  public void doPost (HttpServletRequest request,
                     HttpServletResponse response)
      throws ServletException, IOException {

    final int pno = Integer.parseInt(request.getParameter("pno"));

    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    ServletContext context = getServletContext();
    ObjectServer server=(ObjectServer)context.getAttribute("db4oServer");
    ObjectContainer db = server.openClient();

    out.println("<html>");
    out.println("<head>");
    out.println("<title>Project View</title>");
    out.println("</head>");
    out.println("<body>");
    try {
      List<Project> prjs = db.query(new Predicate<Project>() {
        public boolean match(Project prj) {
          return (prj.getPnumber() == pno);
        }
      });
      Project p = prjs.get(0);

      out.println("<B>Project: </B>"+p.getPname()+"</br>");
      out.println("Project Location: "+p.getLocation()+"</br>");
      out.println("<P>Controlling Department: <a href=\"DisplayDepartment?dno="+
                  p.getControlledBy().getDnumber()+"\">"+p.getControlledBy().getDnumber()+
                  "</a></br>");

      out.println("<h4>Employees Working in Project:</h4>");

      out.println("<table border=2 cellspacing=2 cellpadding=2>");
      out.println("<tr>");
      out.println("<th><font face=\"Arial, Helvetica, sans-serif\">"+
                  "Employee SSN</font></th>");
      out.println("<th><font face=\"Arial, Helvetica, sans-serif\">"+
                  "Employee First Name</font></th>");
      out.println("<th><font face=\"Arial, Helvetica, sans-serif\">"+
                  "Employee Last Name</font></th>");
      out.println("<th><font face=\"Arial, Helvetica, sans-serif\">"+
                  "Hours</font></th>");
      out.println("</tr>");

      for (int i=0; i<p.getWorksOn().size(); i++) {
        Employee e = p.getWorksOn().get(i).getEmployee();
        out.println("<tr>");
        out.println("<td><font face=\"Arial, Helvetica, sans-serif\">"+
                    "<a href=\"DisplayEmployee?ssn="+e.getSsn()+"\">"+
                    e.getSsn()+"</a></font></td>");
        out.println("<td><font face=\"Arial, Helvetica, sans-serif\">"+
                    e.getFname()+"</font></td>");
        out.println("<td><font face=\"Arial, Helvetica, sans-serif\">"+
                    e.getLname()+"</font></td>");
        out.println("<td><font face=\"Arial, Helvetica, sans-serif\">"+
                    p.getWorksOn().get(i).getHours()+"</font></td>");
        out.println("</tr>");
      }
      out.println("</table>");

    } catch (Exception e) {
        out.println("Exception: " + e.getMessage());
      }
      finally {
        db.close();
      }

    out.println("<P>");
    out.println("<a href=\"../company.html\">Return to main page</a>");

    out.println("</body>");
    out.println("</html>");
    out.close();

    db.close();
  }

}
