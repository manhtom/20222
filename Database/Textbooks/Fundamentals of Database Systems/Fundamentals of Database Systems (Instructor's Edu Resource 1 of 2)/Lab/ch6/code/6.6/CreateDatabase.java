import java.util.*;

import com.db4o.*;
import com.db4o.config.Configuration;
import com.db4o.query.*;

public class CreateDatabase {
    public static void main(String[] args) {
        String DB4OFILENAME = args[0];
        Configuration config = Db4o.configure();
        config.objectClass(Employee.class).cascadeOnUpdate(true);
        config.objectClass(Department.class).cascadeOnUpdate(true);
        config.objectClass(Project.class).cascadeOnUpdate(true);
        config.objectClass(Dependent.class).cascadeOnUpdate(true);
        config.objectClass(WorksOn.class).cascadeOnUpdate(true);
        config.updateDepth(1000);
        ObjectContainer db = Db4o.openFile(config,DB4OFILENAME);
        
        try {
            createEmployees(db);
            createDependents(db);
            createDepartments(db);
            createProjects(db);
            setManagers(db);
            setControls(db);
            setWorksfor(db);
            setSupervisors(db);
            createWorksOn(db);
            db.commit();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        finally {
            db.close();
        }
        System.out.println("DONE");
    }


    public static void createEmployees(ObjectContainer db)
            throws Exception {
        InputFile fin = new InputFile();
        if (fin.openFile("data/employee.dat")) {
            int nEmps = Integer.parseInt(fin.readLine());
            for (int i = 0; i < nEmps; i++) {
                String line = fin.readLine();
                String[] fields = line.split(":");
                String fname = fields[0];
                char minit = fields[1].charAt(0);
                String lname = fields[2];
                int ssn = Integer.parseInt(fields[3]);
                String bdate = fields[4];
                String address = fields[5];
                char sex = fields[6].charAt(0);
                float salary = Float.parseFloat(fields[7]);
                Employee e =
                        new Employee(ssn, fname, minit, lname, address, bdate, salary, sex);
                db.store(e);
            }
        }
    }

    public static void createDependents(ObjectContainer db)
            throws Exception {
        InputFile fin = new InputFile();
        if (fin.openFile("data/dependent.dat")) {
            int nDeps = Integer.parseInt(fin.readLine());
            for (int i = 0; i < nDeps; i++) {
                String line = fin.readLine();
                String[] fields = line.split(",");
                final int essn = Integer.parseInt(fields[0]);
                String name = fields[1];
                char sex = fields[2].charAt(0);
                String bdate = fields[3];
                String relationship = fields[4];
                List<Employee> emps = db.query(new Predicate<Employee>() {
                    public boolean match(Employee emp) {
                        return (emp.getSsn() == essn);
                    }
                });
                Employee e = emps.get(0);
                Dependent d = new Dependent(name, sex, bdate, relationship);
                d.setDependentOf(e);
                db.store(d);
                e.addDependent(d);
                db.store(e);
            }
        }
    }

    public static void createDepartments(ObjectContainer db)
            throws Exception {
        InputFile fin = new InputFile();
        if (fin.openFile("data/department.dat")) {
            int nDepts = Integer.parseInt(fin.readLine());
            for (int i = 0; i < nDepts; i++) {
                String line = fin.readLine();
                String[] fields = line.split(":");
                int dnumber = Integer.parseInt(fields[0]);
                String dname = fields[1];
                String[] ls = fields[2].split(",");
                Vector<String> locs = new Vector<String>();
                for (int j = 0; j < ls.length; j++)
                    locs.add(ls[j]);
                Department d = new Department(dnumber, dname, locs);
                db.store(d);
            }
        }
    }

    public static void createProjects(ObjectContainer db)
            throws Exception {
        InputFile fin = new InputFile();
        if (fin.openFile("data/project.dat")) {
            int nProjs = Integer.parseInt(fin.readLine());
            for (int i = 0; i < nProjs; i++) {
                String line = fin.readLine();
                String[] fields = line.split(",");
                int pnumber = Integer.parseInt(fields[0]);
                String pname = fields[1];
                String loc = fields[2];
                Project p = new Project(pnumber, pname, loc);
                db.store(p);
            }
        }
    }

    public static void setManagers(ObjectContainer db)
            throws Exception {
        InputFile fin = new InputFile();
        if (fin.openFile("data/manager.dat")) {
            int nMgrs = Integer.parseInt(fin.readLine());
            for (int i = 0; i < nMgrs; i++) {
                String line = fin.readLine();
                String[] fields = line.split(",");
                final int dno = Integer.parseInt(fields[0]);
                final int essn = Integer.parseInt(fields[1]);
                String startDate = fields[2];
                List<Department> depts = db.query(new Predicate<Department>() {
                    public boolean match(Department dept) {
                        return (dept.getDnumber() == dno);
                    }
                });
                Department d = depts.get(0);
                List<Employee> emps = db.query(new Predicate<Employee>() {
                    public boolean match(Employee emp) {
                        return (emp.getSsn() == essn);
                    }
                });
                Employee e = emps.get(0);
                d.setMgrStartDate(startDate);
                e.setManages(d);
                d.setManager(e);
                db.store(d);
                db.store(e);
            }
        }
    }

    public static void setControls(ObjectContainer db)
            throws Exception {

        InputFile fin = new InputFile();
        if (fin.openFile("data/controls.dat")) {
            int nControls = Integer.parseInt(fin.readLine());
            for (int i = 0; i < nControls; i++) {
                String line = fin.readLine();
                String[] fields = line.split(":");
                final int dno = Integer.parseInt(fields[0]);
                String[] projects = fields[1].split(",");
                List<Department> depts = db.query(new Predicate<Department>() {
                    public boolean match(Department dept) {
                        return (dept.getDnumber() == dno);
                    }
                });
                Department d = depts.get(0);
                for (int j = 0; j < projects.length; j++) {
                    final int pno = Integer.parseInt(projects[j]);
                    List<Project> prjs = db.query(new Predicate<Project>() {
                        public boolean match(Project prj) {
                            return (prj.getPnumber() == pno);
                        }
                    });
                    Project p = prjs.get(0);
                    p.setControlledBy(d);
                    db.store(p);
                    d.addProject(p);
                }
                db.store(d);
            }
        }
    }

    private static void setWorksfor(ObjectContainer db) throws Exception {
        InputFile fin = new InputFile();
        if (fin.openFile("data/worksFor.dat")) {
            int nWorksFor = Integer.parseInt(fin.readLine());
            for (int i = 0; i < nWorksFor; i++) {
                String line = fin.readLine();
                String[] fields = line.split(":");
                final int dno = Integer.parseInt(fields[0]);
                String[] emps = fields[1].split(",");
                List<Department> depts = db.query(new Predicate<Department>() {
                    public boolean match(Department dept) {
                        return (dept.getDnumber() == dno);
                    }
                });
                Department d = depts.get(0);
                for (int j = 0; j < emps.length; j++) {
                    final int ssn = Integer.parseInt(emps[j]);
                    List<Employee> es = db.query(new Predicate<Employee>() {
                        public boolean match(Employee emp) {
                            return (emp.getSsn() == ssn);
                        }
                    });
                    Employee e = es.get(0);
                    e.setWorksFor(d);
                    db.store(e);
                    d.addEmployee(e);
                }
                db.store(d);
            }
        }
    }

    private static void setSupervisors(ObjectContainer db) throws Exception {
        InputFile fin = new InputFile();
        if (fin.openFile("data/sups.dat")) {
            int nSups = Integer.parseInt(fin.readLine());
            for (int i = 0; i < nSups; i++) {
                String line = fin.readLine();
                String[] fields = line.split(":");
                final int superssn = Integer.parseInt(fields[0]);
                String[] subs = fields[1].split(",");
                List<Employee> emps = db.query(new Predicate<Employee>() {
                    public boolean match(Employee emp) {
                        return (emp.getSsn() == superssn);
                    }
                });
                Employee s = emps.get(0);
                for (int j = 0; j < subs.length; j++) {
                    final int essn = Integer.parseInt(subs[j]);
                    List<Employee> subworkers = db.query(new Predicate<Employee>() {
                        public boolean match(Employee emp) {
                            return (emp.getSsn() == essn);
                        }
                    });
                    Employee e = subworkers.get(0);
                    e.setSupervisor(s);
                    db.store(e);
                    s.addSupervisee(e);
                }
                db.store(s);
            }
        }
    }

    private static void createWorksOn(ObjectContainer db) throws Exception {
        InputFile fin = new InputFile();
        if (fin.openFile("data/worksOn.dat")) {
            int nWorksOn = Integer.parseInt(fin.readLine());
            for (int i = 0; i < nWorksOn; i++) {
                String line = fin.readLine();
                String[] fields = line.split(",");
                final int essn = Integer.parseInt(fields[0]);
                final int pno = Integer.parseInt(fields[1]);
                float hours = Float.parseFloat(fields[2]);
                List<Employee> emps = db.query(new Predicate<Employee>() {
                    public boolean match(Employee emp) {
                        return (emp.getSsn() == essn);
                    }
                });
                Employee e = emps.get(0);
                List<Project> prjs = db.query(new Predicate<Project>() {
                    public boolean match(Project prj) {
                        return (prj.getPnumber() == pno);
                    }
                });
                Project p = prjs.get(0);
                WorksOn won = new WorksOn(hours);
                won.setEmployee(e);
                won.setProject(p);
                db.store(won);
                e.addWorksOn(won);
                p.addWorksOn(won);
                db.store(e);
                db.store(p);
                //System.out.println(i+" Just added "+essn+" "+pno+" "+hours);
            }
        }
    }

}
