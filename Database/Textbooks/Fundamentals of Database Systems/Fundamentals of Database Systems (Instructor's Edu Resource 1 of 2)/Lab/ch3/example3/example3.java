import java.sql.*;
import java.io.*;

public class example3 {

  public static void main (String args[]) throws Exception,
                     IOException, SQLException {
    try {
      Class.forName ("oracle.jdbc.driver.OracleDriver");
    } catch (ClassNotFoundException e) {
        System.out.println ("Could not load the driver");
      }
    String user = readEntry("Enter userid: ");
    String pass = readEntry("Enter password: ");
    Connection conn = DriverManager.getConnection (
                      "jdbc:oracle:thin:@tinman.cs.gsu.edu:1521:sid9ir2",user,pass);
  
    /* create new table called tempSSN */
    String sqlString = "create table tempSSN (" +
                       "ssn char(9) not null, " +
                       "primary key(ssn))";
    Statement stmt1 = conn.createStatement();
    try {
       stmt1.executeUpdate(sqlString);
    } catch (SQLException e) {
        System.out.println("Could not create tempSSN table");
        stmt1.close();
        return;
      }

    boolean done = false;
    do {
      printMenu();
      System.out.print("Type in your option: ");
      System.out.flush();
      String ch = readLine();
      System.out.println();
      switch (ch.charAt(0)) {
        case 'a': findSupervisees(conn);
                  break;
        case 'b': findHighestPaid(conn);
                  break;
        case 'c': findMostWorked(conn);
                  break; 
        case 'q': done = true;
                  break;
        default : System.out.println(" Not a valid option ");
      } //switch
    } while(!done);

    /* drop table called tmpSSN */
    sqlString = "drop table tempSSN";
    try {
       stmt1.executeUpdate(sqlString);
    } catch (SQLException e) {
      }
    stmt1.close();
    conn.close();
  } // main

  private static void findSupervisees(Connection conn) 
       throws SQLException, IOException {

    String sqlString = null;

    Statement stmt = conn.createStatement();
    // Delete tuples from tempssn from previous request.
    sqlString = "delete from tempssn";
    try {
      stmt.executeUpdate(sqlString);
    } catch (SQLException e) {
        System.out.println("Could not execute Delete");
        stmt.close();
        return;
      }
    
    /* Get the ssn for the employee */    
    sqlString = "select lname, fname, ssn " +
                "from   employee " +
                "where  lname = '";
    String lname = readEntry( "Enter last name of employee : ").trim();
    sqlString += lname;
    sqlString += "'";
    ResultSet rset1;
    try {
      rset1 = stmt.executeQuery(sqlString);
    } catch (SQLException e) {
        System.out.println("Could not execute Query");
        stmt.close();
        return;
    }
    String samelName[] = new String[40];
    String fName[] = new String[40];
    String empssn[] = new String[40];
    String ssn;
    int nNames = 0;
    while (rset1.next()) {
      samelName[nNames] = rset1.getString(1);
      fName[nNames] = rset1.getString(2);
      empssn[nNames] = rset1.getString(3);
      nNames++;
    }
    if (nNames == 0) {
      System.out.println("Name does not exist in database.");
      stmt.close();
      return;
    }
    else if (nNames > 1) {
      for(int i = 0; i < nNames; i++) {
        System.out.println(samelName[i] + "," +
                           fName[i] + " " + empssn[i]);
      }
      ssn = readEntry("Select ssn from list : ");
      ResultSet r = stmt.executeQuery(
                      "select ssn from employee where ssn = '" +
                        ssn + "'");
      if( !r.next()) {
        System.out.println("SSN does not exist in database.");
        stmt.close();
        return;
      }  
    }
    else {
      ssn = empssn[0];
    }

    /* Find immediate supervisees for that employee */
    sqlString = "select distinct ssn from employee where superssn = '";
    sqlString += ssn;
    sqlString += "'";
    try {
      rset1 = stmt.executeQuery(sqlString);
    } catch (SQLException e) {
        System.out.println("Could not execute query");
        stmt.close();
        return;
    }

    /* Insert result into tempSSN table*/
    Statement stmt1 = conn.createStatement();
    while (rset1.next()) {
      String sqlString2 = "insert into tempSSN values ('";
      sqlString2 += rset1.getString(1);
      sqlString2 += "')";
      try {
        stmt1.executeUpdate(sqlString2);
      } catch (SQLException e) {
       }
    }


    /* Recursive Querying */
    ResultSet rset2;
    boolean newrowsadded;
    sqlString = "select employee.ssn from employee, tempSSN " +
                "where superssn = tempSSN.ssn";
    do {
      newrowsadded = false;
      try {
        rset2 = stmt.executeQuery(sqlString);
      } catch (SQLException e) {
          System.out.println("Could not execute Query");
          stmt.close();
          stmt1.close();
          return;
      }
      while ( rset2.next()) {
        try {
          String sqlString2 = "insert into tempSSN values ('";
          sqlString2 += rset2.getString(1);
          sqlString2 += "')";
          stmt1.executeUpdate(sqlString2);
          newrowsadded = true;
        } catch (SQLException e) {
          }
      }
    } while (newrowsadded);
    stmt1.close();

    /* Print Results */
    sqlString = "select fname, lname, e.ssn from " +
                "employee e, tempSSN t where e.ssn = t.ssn";
    ResultSet rset3;
    try {
      rset3 = stmt.executeQuery(sqlString);
    } catch (SQLException e) {
        System.out.println("Could not execute Query");
        stmt.close();
        return;
     }
     System.out.println("     SUPERVISEES ");
     System.out.print("FNAME");
     for (int i = 0; i < 10; i++)
       System.out.print(" ");
     System.out.print("LNAME");
     for (int i = 0; i < 10; i++)
       System.out.print(" ");
     System.out.print("SSN");
     for (int i = 0; i < 6; i++)
       System.out.print(" ");
     System.out.println("\n----------------------------------------\n");

     while(rset3.next()) {
       System.out.print(rset3.getString(1));
       for (int i = 0; i < (15 - rset3.getString(1).length()); i++)
         System.out.print(" ");
       System.out.print(rset3.getString(2));
       for (int i = 0; i < (15 - rset3.getString(2).length()); i++)
         System.out.print(" ");
       System.out.println(rset3.getString(3));

     }
     stmt.close();
  }

  private static void findHighestPaid(Connection conn) 
     throws SQLException, IOException {
  
    Statement stmt = conn.createStatement();
    String query = "select ssn, fname, lname, salary " +
                   "from employee " +
                   "where salary is not null " +
                   "order by salary desc";
    ResultSet rset;
    try {
      rset = stmt.executeQuery(query);
    } catch (SQLException e) {
        System.out.println("could not execute query ");
        while (e!= null) {
          System.out.println ("Message  :" + e.getMessage());
          e = e.getNextException();
        }
        stmt.close();
        return;
      }
    System.out.println("    HIGHEST PAID WORKERS");
    System.out.println("--------------------------------------------------\n");

    for (int i = 0; i < 5 && rset.next(); i++) {
      System.out.print(rset.getString(1) + " " );
      System.out.print(rset.getString(2));
      for (int j=0; j<16-rset.getString(2).length(); j++)
        System.out.print(" ");
      System.out.print(rset.getString(3));
      for (int j=0; j<16-rset.getString(3).length(); j++)
        System.out.print(" ");
      System.out.println(rset.getDouble(4));
    }
    stmt.close();
  }


  private static void findMostWorked(Connection conn) 
       throws SQLException, IOException {
  
    Statement stmt = conn.createStatement();
    String query = "select essn, fname, lname, sum(Hours) TotalHours " +
                   "from employee, works_on " +
                   "where ssn = essn and Hours is not null " +
                   "group by essn, fname, lname " +
                   "order by TotalHours desc";
    ResultSet rset;
    try {
      rset = stmt.executeQuery(query);
    } catch (SQLException e) {
        System.out.println("could not execute query ");
        while (e!= null) {
          System.out.println ("Message  :" + e.getMessage());
          e = e.getNextException();
        }
       stmt.close();
       return;
      }

    System.out.println("    MOST WORKED WORKERS");
    System.out.println("--------------------------------------------------\n");


    for (int i = 0; i < 5 && rset.next(); i++) {
      System.out.print(rset.getString(1) + " " );
      System.out.print(rset.getString(2));
      for (int j=0; j<16-rset.getString(2).length(); j++)
        System.out.print(" ");
      System.out.print(rset.getString(3));
      for (int j=0; j<16-rset.getString(3).length(); j++)
        System.out.print(" ");
      System.out.println(rset.getFloat(4));

    }
    stmt.close();
  }


  private static String readEntry(String prompt) {
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
 

  private static String readLine() {
    InputStreamReader isr = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(isr, 1);
    String line = "";

    try {
      line = br.readLine();
    } catch (IOException e) {
        System.out.println("Error in SimpleIO.readLine: " +
                         "IOException was thrown");
        System.exit(1);
      }
    return line;
  }
 

  private static void printMenu() {
    System.out.println("\n        QUERY OPTIONS ");
    System.out.println("(a) Find Supervisees at all levels. ");
    System.out.println("(b) Find Highest paid workers. ");
    System.out.println("(c) Find the most worked workers. ");
    System.out.println("(q) Quit. \n");
  }

}    
