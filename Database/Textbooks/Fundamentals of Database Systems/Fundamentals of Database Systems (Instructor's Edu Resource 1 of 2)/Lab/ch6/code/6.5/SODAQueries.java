import com.db4o.Db4o;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.Configuration;
import com.db4o.query.Constraint;
import com.db4o.query.Query;
/**
 * Created by IntelliJ IDEA.
 * User: raj
 * Date: May 31, 2010
 * Time: 7:12:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class SODAQueries {
    public static void main(String args[]) {
        String DB4OFILENAME = args[0];
        Configuration config = Db4o.configure();
        ObjectContainer db = Db4o.openFile(config,DB4OFILENAME);
        //Find employees with lname="King"
        Query query=db.query();
        query.constrain(Employee.class);
        query.descend("lname").constrain("King");
        ObjectSet result=query.execute();
        printResult(result);
        //Find employees with 25000 salary
        query=db.query();
        query.constrain(Employee.class);
        query.descend("salary").constrain(new Integer(25000));
        result=query.execute();
        printResult(result);
        // retrieve by negation; Find projects not located in Houston
        query=db.query();
        query.constrain(Project.class);
        query.descend("location").constrain("Houston").not();
        result=query.execute();
        printResult(result);
        // retrieve by conjunction; Find employees with lname="King" and
        // with salary=44000
        query=db.query();
        query.constrain(Employee.class);
        Constraint constr = query.descend("lname").constrain("King");
        query.descend("salary").constrain(new Integer(44000)).and(constr);
        result=query.execute();
        printResult(result);
        // comparison; Find projects with pnumber > 90 
        query=db.query();
        query.constrain(Project.class);
        query.descend("pnumber").constrain(new Integer(90)).greater();
        result=query.execute();
        printResult(result);
        // or constraint; Find projects located in Houston or with pnumber > 90
        query=db.query();
        query.constrain(Project.class);
        constr=query.descend("location").constrain("Houston");
        query.descend("pnumber").constrain(new Integer(90)).greater().or(constr);
        result=query.execute();
        printResult(result);
        // sorting
        query=db.query();
        query.constrain(Project.class);
        query.descend("pname").orderAscending();
        result=query.execute();
        printResult(result);
        query.descend("pname").orderDescending();
        result=query.execute();
        printResult(result);
        // Find departments managed by employee with last name "Wong"
        query=db.query();
        query.constrain(Department.class);
        query.descend("manager").descend("lname").constrain("Wong");
        result=query.execute();
        Department d = (Department) result.next();
        System.out.println("Wong managed department: "+d);

    }
    public static void printResult(ObjectSet result) {
        System.out.println(result.size());
        while(result.hasNext()) {
            System.out.println(result.next());
    }
}

}
