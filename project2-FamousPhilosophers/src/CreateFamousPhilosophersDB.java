package FamousPhilosophers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *<pre>
 * Class        FamousPhilosophers.java
 * Project      Famous Philosophers
 * Description  A class representing the GUI used in a maintaining a philosophers 
 *              database obtained from a text file, philosophers.txt with 6 fields:
 *              Name, birthPlace, year born, major work, Sypnosis, link to YouTube. 
 *              Functionalities include: viewing of the data for selected philosophers,
 *              add, delete, edit, sort by name, year, and search for cpecified 
 *              philosophers.
 * File         Famousphilosophers.java
 * Platform     jdk 1.8.0_214; NetBeans IDE 11.3; Windows 10
 * Course       CS 142, Edmonds Community College
 * Date         5/12/2021
 * @author	<i>Nguyen Vi Cao</i>
 * @version 	%1% %2%
 * @see     	javax.swing.JFrame
 * @see         java.awt.Toolkit
 * @see         java.util.ArrayList
 *</pre>
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class CreateFamousPhilosophersDB implements MySQLConnection {
    private static final String PERSONS_TEXT_FILE = "src/FamousPhilosophers/Philosophers.txt";
    private static final ArrayList<Philosopher> philosophers = new ArrayList<Philosopher>();
    private Philosopher myPhilosopher = new Philosopher();
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
     * Method       main()
     * Description  reads data from external text file, creates the FamousPhilosophers
     * @param       args are the command line strings
     * @author      <i>Nguyen Vi Cao</i>
     * Date         5/12/2021
     *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static void main(String[] args) {
        try
        {
            readFromFile(PERSONS_TEXT_FILE);
            String url = DB_URL;        //"jdbc:mysql://localhost:3306/javabook"
            String user = USER;         //"root"
            String password = PASS;     //"Caonguyenvi3132="
            //class.forName("com.mysql.cj.jdbc.Driver");        //not needed
            Connection con = DriverManager.getConnection(url, user, password);

            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            DatabaseMetaData dbm = con.getMetaData();
            ResultSet table;
            
            //check to see if tables already exist
            table = dbm.getTables(null, null, "FamousPhilosophers", null);
            if(table.next())
            {
                //the tables exists, so kill it so we can remake it
                stmt.executeUpdate("DROP TABLE FamousPhilosophers");
                //return;
            }
            
            //create table AddressBook
            stmt.executeUpdate("CREATE TABLE FamousPhilosophers (philosopherID"
                + " SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT, Name"
                + " VARCHAR(20), place VARCHAR(20), year INTEGER,"
                + " major work VARCHAR(30), sypnosis"
                + " VARCHAR(30), link VARCHAR(30))"
            );
            
            for(int i = 0; i < philosophers.size(); i++)
            {
                stmt.executeUpdate
                        ("INSERT INTO FamousPhilosophers VALUES('"
                            + philosophers.get(i).getName() + "',"
                            + "'" + philosophers.get(i).getPlace() + "',"
                            + philosophers.get(i).getYear() + ","
                            + "'" + philosophers.get(i).getWork() + "'" + ","
                            + "'" + philosophers.get(i).getSypnosis() + "'" + ","
                            + "'" + philosophers.get(i).getLink() + "')");
            }
            stmt.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "SQL Error",
                    "SQL Error!", JOptionPane.ERROR_MESSAGE);
        }
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method       readFromFile
     * Description  Reads philosophers from a text file that is "|" delimited and
     *              creates an instance of the philosophers class with the data read.
     *              Then the newly created philosophers is added to the philosophers database.
     *              Uses an object from the ReadFile class to read record.
     * @author      <i>Nguyen Vi Cao</i>
     * @param       textFile
     * Date         1/27/2021
     * History log  1/10/2021, 1/20/2021
     * @see         java.util.Scanner
     *</pre>   
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private static void readFromFile (String textFile)
    {
        try
        {            
            FileReader freader = new FileReader(textFile);
            BufferedReader input = new BufferedReader(freader);
            String line = input.readLine();
            
            while(line != null)
            {
                Philosopher tempPhilosopher = new Philosopher();
                StringTokenizer token = new StringTokenizer(line, "|");
                while(token.hasMoreElements())
                {
                    tempPhilosopher.setName(token.nextToken());
                    tempPhilosopher.setPlace(token.nextToken());
                    tempPhilosopher.setYear(Integer.parseInt(token.nextToken()));
                    tempPhilosopher.setWork(token.nextToken());
                    tempPhilosopher.setSypnosis(token.nextToken());
                    tempPhilosopher.setLink(token.nextToken());
                }
                philosophers.add(tempPhilosopher);            //add philosopher to arraylist
                line = input.readLine();
            }
            input.close();                      //close the bufferedReader
        }
        catch(FileNotFoundException fnfexp)
        {
            JOptionPane.showMessageDialog(null, "Input error -- File not found.",
                    "File Not Found Error!", JOptionPane.ERROR_MESSAGE);
        }
        catch(IOException | NumberFormatException exp)
        {
            JOptionPane.showMessageDialog(null, "Input error -- File could not be read.",
                    "File Read Error!", JOptionPane.ERROR_MESSAGE);
        }
    }
}
