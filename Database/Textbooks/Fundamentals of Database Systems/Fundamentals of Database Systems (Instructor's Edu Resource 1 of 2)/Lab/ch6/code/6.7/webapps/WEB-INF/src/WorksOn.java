/**
 * Created by IntelliJ IDEA.
 * User: raj
 * Date: May 9, 2010
 * Time: 9:45:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class WorksOn {
    //simple attribute
    float hours;
    //owner attributes
    Employee employee;
    Project project;

    public WorksOn(float hours) {
        this.hours = hours;
    }

    public float getHours() {
        return hours;
    }

    public void setHours(float hours) {
        this.hours = hours;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

}
