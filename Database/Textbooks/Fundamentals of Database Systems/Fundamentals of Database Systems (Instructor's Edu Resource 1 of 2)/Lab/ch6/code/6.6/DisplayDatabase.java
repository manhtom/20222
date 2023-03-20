import com.db4o.Db4o;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.Configuration;
import com.db4o.config.EmbeddedConfiguration;

/**
 * Created by IntelliJ IDEA.
 * User: raj
 * Date: May 10, 2010
 * Time: 8:59:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class DisplayDatabase {
    public static void main(String[] args) {
        String DB4OFILENAME = args[0];
        Configuration config = Db4o.configure();
        ObjectContainer db = Db4o.openFile(config,DB4OFILENAME);

        try {
            displayDepartments(db);
            displayProjects(db);
            displayEmployees(db);
            displayDependents(db);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        finally {
            db.close();
        }

        System.out.println("DONE");
    }

    public static void displayEmployees(ObjectContainer db) {
        ObjectSet result = db.queryByExample(Employee.class);
        System.out.println("*********************************************");
        System.out.println("Number of employees: " + result.size());
        while (result.hasNext()) {
            Employee e = (Employee) result.next();
            if (e.getManages() != null)
                System.out.println(e + " manages department " + e.getManages().getDname());
            else
                System.out.println(e);
            System.out.println("Works for " + e.getWorksFor().getDname());
            if (e.getSupervisor() != null)
                System.out.println("Supervised by " + e.getSupervisor().getLname());
            for (int i = 0; i < e.getSupervisees().size(); i++) {
                Employee s = e.getSupervisees().get(i);
                System.out.println("Boss of: " + s.getLname());
            }
            for (int i = 0; i < e.getWorksOn().size(); i++) {
                WorksOn w = e.getWorksOn().get(i);
                System.out.println("Works in project: " + w.getProject().getPname() +
                        " for " + w.getHours() + " hours");
            }
            for (int i = 0; i < e.getDependents().size(); i++) {
                Dependent dp = e.getDependents().get(i);
                System.out.println("Employee of dependent: " + dp.getName());
            }
        }
    }

    public static void displayDependents(ObjectContainer db) {
        ObjectSet result = db.queryByExample(Dependent.class);
        System.out.println("*********************************************");
        System.out.println("Number of dependents: " + result.size());
        while (result.hasNext()) {
            Dependent d = (Dependent) result.next();
            System.out.println(
                    d + " " + d.getRelationship() + " of " +
                            d.getDependentOf().getFname() + " " +
                            d.getDependentOf().getLname());
        }
    }

    public static void displayDepartments(ObjectContainer db) {
        ObjectSet result = db.queryByExample(Department.class);
        System.out.println("*********************************************");
        System.out.println("Number of departments: " + result.size());
        while (result.hasNext()) {
            Department d = (Department) result.next();
            if (d.getManager() == null)
                System.out.println(d);
            else
                System.out.println(d + " managed by " + d.getManager().getFname() +
                        " " + d.getManager().getLname() + " started on " +
                        d.getMgrStartDate());
            for (int i = 0; i < d.getProjects().size(); i++) {
                Project p = d.getProjects().get(i);
                System.out.println("Controls project: " + p.getPname());
            }
            for (int i = 0; i < d.getEmployees().size(); i++) {
                Employee e = d.getEmployees().get(i);
                System.out.println("Employee: " + e.getLname());
            }
        }
    }

    public static void displayProjects(ObjectContainer db) {
        ObjectSet result = db.queryByExample(Project.class);
        System.out.println("*********************************************");
        System.out.println("Number of projects: " + result.size());
        while (result.hasNext()) {
            Project p = (Project) result.next();
            System.out.println(p + " controlled by " + p.getControlledBy().getDname());
            for (int i = 0; i < p.getWorksOn().size(); i++) {
                WorksOn w = p.getWorksOn().get(i);
                System.out.println("Employee: " + w.getEmployee().getLname() +
                        " for " + w.getHours() + " hours");
            }
        }
    }
}
