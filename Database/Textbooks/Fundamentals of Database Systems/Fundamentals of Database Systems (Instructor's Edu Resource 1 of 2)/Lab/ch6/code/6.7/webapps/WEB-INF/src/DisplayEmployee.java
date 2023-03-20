import com.db4o.*;
import com.db4o.query.Predicate;

import java.io.*;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

public class DisplayEmployee extends HttpServlet {

  public void doGet (HttpServletRequest request,
                     HttpServletResponse response)
      throws ServletException, IOException {

    doPost(request,response);
  }

  public void doPost (HttpServletRequest request,
                     HttpServletResponse response)
      throws ServletException, IOException {

    final int ssn = Integer.parseInt(request.getParameter("ssn"));

    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    ServletContext context = getServletContext();
    ObjectServer server=(ObjectServer)context.getAttribute("db4oServer");
    ObjectContainer db = server.openClient();

    out.println("<html>");
    out.println("<head>");
    out.println("<title>Employee View</title>");
    out.println("</head>");
    out.println("<body>");
    try {
      List<Employee> emps = db.query(new Predicate<Employee>() {
        public boolean match(Employee emp) {
          return (emp.getSsn() == ssn);
        }
      });
      Employee e = emps.get(0);

      out.println("<B>Employee: </B>"+e.getLname()+", "+e.getFname()+"</br>");
      out.println("<B>Birth Date: </B>"+e.getBdate()+"</br>");
      out.println("<B>Address: </B>"+e.getAddress()+"</br>");
      out.println("<B>Salary: </B>"+e.getSalary()+"</br>");
      out.println("<P>Department: <a href=\"DisplayDepartment?dno="+
                  e.getWorksFor().getDnumber()+"\">"+e.getWorksFor().getDnumber()+
                  "</a></br>");

      out.println("<h4>Projects Involved In:</h4>");

      out.println("<table border=2 cellspacing=2 cellpadding=2>");
      out.println("<tr>");
      out.println("<th><font face=\"Arial, Helvetica, sans-serif\">"+
                  "Project Number</font></th>");
      out.println("<th><font face=\"Arial, Helvetica, sans-serif\">"+
                  "Project Name</font></th>");
      out.println("<th><font face=\"Arial, Helvetica, sans-serif\">"+
                  "Hours</font></th>");
      out.println("</tr>");

      for (int i=0; i<e.getWorksOn().size(); i++) {
        Project p = e.getWorksOn().get(i).getProject();
        out.println("<tr>");
        out.println("<td><font face=\"Arial, Helvetica, sans-serif\">"+
                    "<a href=\"DisplayProject?pno="+p.getPnumber()+"\">"+
                    p.getPnumber()+"</a></font></td>");
        out.println("<td><font face=\"Arial, Helvetica, sans-serif\">"+
                    p.getPname()+"</font></td>");
        out.println("<td><font face=\"Arial, Helvetica, sans-serif\">"+
                    e.getWorksOn().get(i).getHours()+"</font></td>");
        out.println("</tr>");
      }
      out.println("</table>");

      out.println("<h4>Dependents:</h4>");

      out.println("<table border=2 cellspacing=2 cellpadding=2>");
      out.println("<tr>");
      out.println("<th><font face=\"Arial, Helvetica, sans-serif\">"+
                  "Dependent Name</font></th>");
      out.println("<th><font face=\"Arial, Helvetica, sans-serif\">"+
                  "Sex</font></th>");
      out.println("<th><font face=\"Arial, Helvetica, sans-serif\">"+
                  "Birth Date</font></th>");
      out.println("<th><font face=\"Arial, Helvetica, sans-serif\">"+
                  "Relationship</font></th>");
      out.println("</tr>");

      for (int i=0; i<e.getDependents().size(); i++) {
        Dependent dp = e.getDependents().get(i);
        out.println("<tr>");
        out.println("<td><font face=\"Arial, Helvetica, sans-serif\">"+
                    dp.getName()+"</font></td>");
        out.println("<td><font face=\"Arial, Helvetica, sans-serif\">"+
                    dp.getSex()+"</font></td>");
        out.println("<td><font face=\"Arial, Helvetica, sans-serif\">"+
                    dp.getBdate()+"</font></td>");
        out.println("<td><font face=\"Arial, Helvetica, sans-serif\">"+
                    dp.getRelationship()+"</font></td>");
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
