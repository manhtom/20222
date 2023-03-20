import com.db4o.*;
import com.db4o.query.Predicate;

import java.io.*;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

public class DisplayDepartment extends HttpServlet {

  public void doGet (HttpServletRequest request,
                     HttpServletResponse response)
      throws ServletException, IOException {

    doPost(request,response);
  }

  public void doPost (HttpServletRequest request,
                     HttpServletResponse response)
      throws ServletException, IOException {

    final int dno = Integer.parseInt(request.getParameter("dno"));

    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    ServletContext context = getServletContext();
    ObjectServer server=(ObjectServer)context.getAttribute("db4oServer");
    ObjectContainer db = server.openClient();

    out.println("<html>");
    out.println("<head>");
    out.println("<title>Department View</title>");
    out.println("</head>");
    out.println("<body>");
    try {
      List<Department> depts = db.query(new Predicate<Department>() {
        public boolean match(Department dept) {
          return (dept.getDnumber() == dno);
        }
      });
      Department d = depts.get(0);

      out.println("<B>Department: </B>" + d.getDname());
      out.println("<P>Manager: <a href=\"DisplayEmployee?ssn="+
                  d.getManager().getSsn()+"\">"+d.getManager().getLname()+", "+
                  d.getManager().getFname()+"</a></br>");
      out.println("Manager Start Date: "+d.getMgrStartDate());

      out.println("<h4>Department Locations:</h4>");
      for (int i=0; i<d.getLocations().size(); i++)
        out.println(d.getLocations().get(i)+"<BR>");

      out.println("<h4>Employees:</h4>");

      out.println("<table border=2 cellspacing=2 cellpadding=2>");
      out.println("<tr>");
      out.println("<th><font face=\"Arial, Helvetica, sans-serif\">"+
                  "Employee SSN</font></th>");
      out.println("<th><font face=\"Arial, Helvetica, sans-serif\">"+
                  "First Name</font></th>");
      out.println("<th><font face=\"Arial, Helvetica, sans-serif\">"+
                  "Last Name</font></th>");
      out.println("</tr>");

      for (int i=0; i<d.getEmployees().size(); i++) {
        Employee e = d.getEmployees().get(i);
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
      out.println("</table>");

      out.println("<h4>Projects:</h4>");

      out.println("<table border=2 cellspacing=2 cellpadding=2>");
      out.println("<tr>");
      out.println("<th><font face=\"Arial, Helvetica, sans-serif\">"+
                  "Project Number</font></th>");
      out.println("<th><font face=\"Arial, Helvetica, sans-serif\">"+
                  "Project Name</font></th>");
      out.println("</tr>");

      for (int i=0; i<d.getProjects().size(); i++) {
        Project p = d.getProjects().get(i);
        out.println("<tr>");
        out.println("<td><font face=\"Arial, Helvetica, sans-serif\">"+
                    "<a href=\"DisplayProject?pno="+p.getPnumber()+"\">"+
                    p.getPnumber()+"</a></font></td>");
        out.println("<td><font face=\"Arial, Helvetica, sans-serif\">"+
                    p.getPname()+"</font></td>");
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
