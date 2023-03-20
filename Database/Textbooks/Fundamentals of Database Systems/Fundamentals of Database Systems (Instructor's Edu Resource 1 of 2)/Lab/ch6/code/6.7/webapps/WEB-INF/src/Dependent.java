/**
 * Created by IntelliJ IDEA.
 * User: raj
 * Date: May 9, 2010
 * Time: 9:32:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class Dependent {
    // simple attributes
    private String name;
    private char sex;
    private String bdate;
    private String relationship;
// relationship attributes
    private Employee dependentOf;

    public Dependent(String name, char sex, String bdate, String relationship) {
        this.name = name;
        this.sex = sex;
        this.bdate = bdate;
        this.relationship = relationship;
    }

    public String getName() {
        return name;
    }

    public void setName(String nm) {
        name = nm;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char x) {
        sex = x;
    }

    public String getBdate() {
        return bdate;
    }

    public void setBdate(String bd) {
        bdate = bd;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String rel) {
        relationship = rel;
    }

    public Employee getDependentOf() {
        return dependentOf;
    }

    public void setDependentOf(Employee emp) {
        dependentOf = emp;
    }

    public String toString() {
        return name;
    }
}
