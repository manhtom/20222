import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.config.Configuration;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: raj
 * Date: May 17, 2010
 * Time: 9:10:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class UpdateDeleteStudents {
    public static void main(String[] args) {
        Configuration config = Db4o.configure();
        ObjectContainer db = Db4o.openFile(config,"student.db4o");
        updateDeleteStudents(db);
        db.close();
    }
    public static void updateDeleteStudents(ObjectContainer db) {
        // Update Smith GPA
        Student s = new Student(1000,null,null,(float) 0.0);
        List <Student> result1 = db.queryByExample(s);
        s = (Student) result1.get(0);
        s.setGpa((float) 3.67);
        db.store(s);
        // Delete Student with id 1002
        s = new Student(1002,null,null,(float) 0.0);
        List <Student> result2 = db.queryByExample(s);
        s = (Student) result2.get(0);
        db.delete(s);
    }
}
