import java.sql.*; 
import java.io.*; 

class getEmpInfo { 
  public static void main (String args []) 
      throws SQLException, IOException { 

    try {
      Class.forName ("oracle.jdbc.driver.OracleDriver");
    } catch (ClassNotFoundException e) {
        System.out.println ("Could not load the driver"); 
      }

    String user, pass;
    user = readEntry("userid  : ");
    pass = readEntry("password: ");
    Connection conn = DriverManager.getConnection
        ("jdbc:oracle:thin:@tinman.cs.gsu.edu:1521:sid9ir2",user,pass);

    String query = "select LNAME,SALARY from EMPLOYEE where SSN = ?";
    PreparedStatement p = conn.prepareStatement (query); 
    String ssn = readEntry("Enter a Social Security Number: ");
    p.clearParameters();
    p.setString(1,ssn);
    ResultSet r = p.executeQuery();
    while (r.next ()) { 
      String lname = r.getString(1);
      double salary = r.getDouble(2);
      System.out.println(lname + "  " + salary);
    } 
    conn.close();
  } 

  //readEntry function -- to read input string
  static String readEntry(String prompt) {
     try {
       StringBuffer buffer = new StringBuffer();
       System.out.print(prompt);
       System.out.flush();
       int c = System.in.read();
       while(c != '\n' && c != -1) {
         buffer.append((char)c);
         c = System.in.read();
       }
       return buffer.toString().trim();
     } catch (IOException e) {
       return "";
       }
   }
} 
