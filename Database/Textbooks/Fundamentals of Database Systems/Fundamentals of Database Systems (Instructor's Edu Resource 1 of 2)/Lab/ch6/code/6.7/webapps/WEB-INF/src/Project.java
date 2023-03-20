import java.util.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: raj
 * Date: May 9, 2010
 * Time: 9:33:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class Project {
// simple attributes
    private int pnumber;
    private String pname;
    private String location;
// relationship attributes
    Department controlledBy;
    Vector<WorksOn> worksOn;

    public Project(int pnumber, String pname, String location) {
        this.pnumber = pnumber;
        this.pname = pname;
        this.location = location;
        this.controlledBy = null;
        this.worksOn = new Vector<WorksOn>();
    }

    public int getPnumber() {
        return pnumber;
    }

    public void setPnumber(int num) {
        pnumber = num;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String nm) {
        pname = nm;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String loc) {
        location = loc;
    }

    public Department getControlledBy() {
        return controlledBy;
    }

    public void setControlledBy(Department dept) {
        controlledBy = dept;
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

    public String toString() {
        return pname;
    }


}
