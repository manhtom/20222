import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.config.Configuration;

/**
 * Created by IntelliJ IDEA.
 * User: raj
 * Date: May 15, 2010
 * Time: 9:45:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class CreateStudent {
    public static void main(String[] args) {
        Configuration config = Db4o.configure();
        ObjectContainer db = Db4o.openFile(config,"student.db4o");
        createFewStudents(db);
        db.close();
    }
    public static void createFewStudents(ObjectContainer db) {
        //Create few student objects and store them in the database
        Student s1 = new Student(1000,"Smith","Josh", (float) 3.00);
        Student s2 = new Student(1001,"Harvey", "Derek", (float) 4.00);
        Student s3 = new Student(1002,"Lemon", "Don", (float) 3.50);
        db.store(s1);
        db.store(s2);
        db.store(s3);
    }
}
