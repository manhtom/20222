import com.db4o.Db4o;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.Configuration;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.query.Predicate;

import java.io.*;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: raj
 * Date: May 12, 2010
 * Time: 2:09:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class DisplayDepartment {
    public static void main(String[] args) {
        String DB4OFILENAME = args[0];
        Configuration config = Db4o.configure();
        ObjectContainer db = Db4o.openFile(config,DB4OFILENAME);
        try {
            final int dno = Integer.parseInt(readEntry("Department Number: "));
            List<Department> depts = db.query(new Predicate<Department>() {
                public boolean match(Department dept) {
                    return (dept.getDnumber() == dno);
                }
            });
            Department d = depts.get(0);
            System.out.println("*******************************************************");
            System.out.println("Department: " + d.getDname());
            System.out.println("");
            System.out.println("Manager: "+d.getManager().getLname()+", "+
                               d.getManager().getFname());
            System.out.println("Manager Start Date: "+d.getMgrStartDate());
            System.out.println("");
            System.out.println("Department Locations:");
            System.out.println("");
            for (int i=0; i<d.getLocations().size(); i++)
                System.out.println(d.getLocations().get(i));
            System.out.println("\nEmployees:\n");
            for (int i=0; i<d.getEmployees().size(); i++) {
                Employee e = d.getEmployees().get(i);
                System.out.println(e.getSsn()+":"+e.getLname()+":"+e.getFname());
            }
            System.out.println("\nProjects:\n");
            for (int i=0; i<d.getProjects().size(); i++) {
                Project p = d.getProjects().get(i);
                System.out.println(p.getPnumber()+":"+p.getPname()+":"+p.getLocation());
            }
            System.out.println("*******************************************************");
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        finally {
            db.close();
        }
        
    }

    //readEntry function -- to read input string

    static String readEntry(String prompt) {
        try {
            StringBuffer buffer = new StringBuffer();
            System.out.print(prompt);
            System.out.flush();
            int c = System.in.read();
            while (c != '\n' && c != -1) {
                buffer.append((char) c);
                c = System.in.read();
            }
            return buffer.toString().trim();
        } catch (IOException e) {
            return "";
        }
    }
}
