import java.util.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: raj
 * Date: May 9, 2010
 * Time: 9:30:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class Employee {
    // simple attributes
    private int ssn;
    private String fname;
    private char minit;
    private String lname;
    private String address;
    private String bdate;
    private float salary;
    private char sex;
//relationship attributes
    private Department worksFor;
    private Department manages;
    private Vector<WorksOn> worksOn;
    private Vector<Dependent> dependents;
    private Employee supervisor;
    private Vector<Employee> supervisees;

    public Employee(int ssn, String fname, char minit, String lname,
                    String address, String bdate, float salary, char sex) {
        this.ssn = ssn;
        this.fname = fname;
        this.minit = minit;
        this.lname = lname;
        this.address = address;
        this.bdate = bdate;
        this.salary = salary;
        this.sex = sex;
        worksFor = null;
        manages = null;
        worksOn = new Vector<WorksOn>();
        dependents = new Vector<Dependent>();
        supervisor = null;
        supervisees = new Vector<Employee>();
    }

    public int getSsn() {
        return ssn;
    }

    public void setSsn(int n) {
        ssn = n;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fn) {
        fname = fn;
    }

    public char getMinit() {
        return minit;
    }

    public void setMinit(char mi) {
        minit = mi;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String ln) {
        lname = ln;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String addr) {
        address = addr;
    }

    public String getBdate() {
        return bdate;
    }

    public void setBdate(String bd) {
        bdate = bd;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float sal) {
        salary = sal;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char x) {
        sex = x;
    }

    public Department getWorksFor() {
        return worksFor;
    }

    public void setWorksFor(Department dept) {
        worksFor = dept;
    }

    public Department getManages() {
        return manages;
    }

    public void setManages(Department dept) {
        manages = dept;
    }

    public Vector<WorksOn> getWorksOn() {
        return worksOn;
    }

    public void addWorksOn(WorksOn w) {
        worksOn.add(w);
    }

    public void setWorksOn(Vector<WorksOn> ws) {
        worksOn = ws;
    }

    public Vector<Dependent> getDependents() {
        return dependents;
    }

    public void setDependents(Vector<Dependent> deps) {
        dependents = deps;
    }

    public void addDependent(Dependent d) {
        dependents.add(d);
    }

    public Employee getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Employee emp) {
        supervisor = emp;
    }

    public Vector<Employee> getSupervisees() {
        return supervisees;
    }

    public void addSupervisee(Employee e) {
        supervisees.add(e);
    }

    public void setSupervisees(Vector<Employee> emps) {
        supervisees = emps;
    }

    public String toString() {
        return fname + " " + lname;
    }
}
