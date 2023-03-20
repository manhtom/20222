/**
 * Created by IntelliJ IDEA.
 * User: raj
 * Date: May 15, 2010
 * Time: 9:41:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class Student {
    int sid;
    String lname;
    String fname;
    float gpa;

    public Student(int sid, String lname, String fname, float gpa) {
        this.sid = sid;
        this.lname = lname;
        this.fname = fname;
        this.gpa = gpa;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public float getGpa() {
        return gpa;
    }

    public void setGpa(float gpa) {
        this.gpa = gpa;
    }

    public String toString() {
        return sid+" "+fname+" "+lname+" "+gpa;
    }
}
