import java.util.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: raj
 * Date: May 9, 2010
 * Time: 9:28:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class Department {
// simple attributes
    private int dnumber;
    private String dname;
    private Vector<String> locations;
// one-to-many relationship (manager) attribute
    private String mgrStartDate;
// relationship attributes
    private Vector<Employee> employees;
    private Employee manager;
    private Vector<Project> projects;

    public Department(int dnumber, String dname, Vector<String> locations) {
        this.dnumber = dnumber;
        this.dname = dname;
        this.locations = locations;
        this.mgrStartDate = null;
        this.employees = new Vector<Employee>();
        this.manager = null;
        this.projects = new Vector<Project>();
    }

    public int getDnumber() {
        return dnumber;
    }

    public void setDnumber(int dnum) {
        dnumber = dnum;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String nm) {
        dname = nm;
    }

    public Vector<String> getLocations() {
        return locations;
    }

    public void setLocations(Vector<String> locs) {
        locations = locs;
    }

    public String getMgrStartDate() {
        return mgrStartDate;
    }

    public void setMgrStartDate(String sd) {
        mgrStartDate = sd;
    }

    public Vector<Employee> getEmployees() {
        return employees;
    }

    public void addEmployee(Employee e) {
        employees.add(e);
    }

    public void setEmployees(Vector<Employee> emps) {
        employees = emps;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee mgr) {
        manager = mgr;
    }

    public Vector<Project> getProjects() {
        return projects;
    }

    public void addProject(Project p) {
        projects.add(p);
    }

    public void setProjects(Vector<Project> prjs) {
        projects = prjs;
    }

    public String toString() {
        return dname;
    }
}
