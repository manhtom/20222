import com.db4o.Db4o;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.Configuration;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.query.Predicate;

import java.io.*;
import java.util.List;
import java.util.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: raj
 * Date: May 12, 2010
 * Time: 2:09:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class DisplayDept2 {
    public static void main(String[] args) {
        String DB4OFILENAME = args[0];
        Configuration config = Db4o.configure();
        ObjectContainer db = Db4o.openFile(config,DB4OFILENAME);
        try {
            // Find departments that have a location in Houston or
            // have less than 4 employees or controls a project located in
            // Phoenix
            List<Department> depts = db.query(new Predicate<Department>() {
                public boolean match(Department dept) {
                  int nEmps = dept.getEmployees().size();
                  Vector<Project> prjs = dept.getProjects();
                  boolean foundPhoenix = false;
                  for (int i=0; i<prjs.size(); i++) {
                    Project p = prjs.get(i);
                    if (p.getLocation().equals("Phoenix")) {
                      foundPhoenix = true;
                      break;
                    }
                  }
                  return dept.getLocations().contains("Houston") ||
                         (nEmps < 4) ||
                         foundPhoenix;
                }
            });
            for (int i=0; i<depts.size(); i++)
                System.out.println("Department: "+depts.get(i));
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

