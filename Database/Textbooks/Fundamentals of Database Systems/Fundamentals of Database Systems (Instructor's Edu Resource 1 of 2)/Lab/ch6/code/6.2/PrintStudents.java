import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.Configuration;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: raj
 * Date: May 15, 2010
 * Time: 10:02:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class PrintStudents {
    public static void main(String[] args) {
        Configuration config = Db4o.configure();
        ObjectContainer db = Db4o.openFile(config,"student.db4o");
        printStudents(db);
        db.close();
    }
    public static void printStudents(ObjectContainer db) {
        //ObjectSet result = db.queryByExample(Student.class);
        List <Student> result1 = db.queryByExample(Student.class);
        System.out.println("Number of students: " + result1.size()+"\n");
        for (int i=0; i<result1.size(); i++) {
            Student s = result1.get(i);
            System.out.println(s);
        }
        System.out.println("Query By Example");
        Student s = new Student(0,"Smith",null,(float) 0.0);
//        ObjectSet result2 = db.queryByExample(s);
//        while (result2.hasNext()) {
//            s = (Student) result2.next();
//            System.out.println(s);
//        }
        List <Student> result2 = db.queryByExample(s);
        for (int i=0; i<result2.size(); i++) {
            s = (Student) result2.get(i);
            System.out.println(s);
        }
    }
}
