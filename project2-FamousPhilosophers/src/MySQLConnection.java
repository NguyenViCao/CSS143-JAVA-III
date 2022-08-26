package FamousPhilosophers;
/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *<pre>
 * Class        MySQLConnection.java
 * Description  an interface representing the connections constans needed for
 *              the famous philosophers application
 * File         FamousPhilosophers.java
 * Platform     jdk 1.8.0_214; NetBeans IDE 11.3; Windows 10
 * Course       CS 142, Edmonds Community College
 * Date         5/12/2021
 * @author	<i>Nguyen Vi Cao</i>
 * @version 	%1% %2%
 * @see     	javax.swing.JFrame
 * @see         java.awt.Toolkit
 *</pre>
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public interface MySQLConnection 
{
    public static final String DB_URL = "jdbc:mysql://localhost:3306/javabook";
    public static final String USER = "root";
    public static final String PASS = "Caonguyenvi3132=";
    public static final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
}
