package StateCapitalsQuiz;

import java.text.DecimalFormat;

/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *<pre>
 * Class        Player.java
 * Description  A class representing the GUI used in a maintaining a Player 
 *              database obtained from a text file, Players.txt with 3 fields:
 *              Name, age, and balance.
 * Project      Two Cards Game
 * Platform     jdk 1.8.0_214; NetBeans IDE 11.3; Windows 10
 * Course       CS 142, Edmonds Community College
 * Date         3/9/2021
 * History log  3/9/2021
 * @author	<i>Nguyen Vi Cao</i>
 * @version 	%1% %5%
 *</pre>
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

public class Player extends Person implements Comparable
{
    private int totalQuestion;      //number of correct questions
    private int noCorrect;          //number of total questions
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Constructor     Player()-default constructor
     * Description     Create an instance of the Player class and assign default
     *                 values to all fields
     * @author         <i>Nguyen Vi Cao</i>
     * Date            3/9/2021
     * History log     3/9/2021
     *</pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public Player()
    {
        this(0, 0);
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       Player()
     * Description  assign parameter to balance
     * @param       total int
     * @param       correct int
     * @author      <i>Nguyen Vi Cao</i>
     * Date         3/9/2021
     * History log  3/9/2021
     * </pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
    public Player(int total, int correct) {
        this.totalQuestion = total;
        this.noCorrect = correct;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Constructor  Player()-overloaded constructor
     * Description  Create an instance of the Plyaer class and assign values via 
     *              parameters to all fields
     * @param       name String
     * @param       age int
     * @param       total int
     * @param       correct int
     * @author      <i>Nguyen Vi Cao</i>
     * Date         3/9/2021
     * History log  3/9/2021
     * </pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
    public Player (String name, int age, int total, int correct)
    {
        super(name, age);
        this.totalQuestion = total;
        this.noCorrect = correct;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Constructor  Player()-overloaded copy constructor
     * Description  Create an instance of the Player class and assign values via
     *              parameters from another opera to all fields.
     * @param       another Player
     * @author      <i>Nguyen Vi Cao</i>
     * Date         3/9/2021
     * History log  3/9/2021
     * </pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
    public Player(Player another)
    {
        this.name = another.name;
        this.age = another.age;
        this.totalQuestion = another.totalQuestion;
        this.noCorrect = another.noCorrect;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Constructor  compareTo()
     * Description  required method to compare two players
     * @param       obj Object
     * @author      <i>Nguyen Vi Cao</i>
     * Date         3/9/2021
     * History log  3/9/2021
     * </pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
    @Override
    public int compareTo(Object obj)
    {
        Player otherPerson = (Player) obj;
        if((this.getName()).equalsIgnoreCase(otherPerson.getName()))
            return this.age - ((Person) obj).age;
        else
            return (this.getName()).compareTo(otherPerson.getName());
    } 
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Constructor  compareTo()
     * Description  overloaded method to compare two players
     * @param       obj Object
     * @param       partial boolean
     * @author      <i>Nguyen Vi Cao</i>
     * Date         3/9/2021
     * History log  3/9/2021
     * </pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
    public int compareTo(Object obj, boolean partial)
    {
        Player otherPerson = (Player) obj;
        if((this.getName()).equalsIgnoreCase(otherPerson.getName()))
            return this.age - ((Person) obj).age;
        else
            return (this.getName()).compareTo(otherPerson.getName());
    } 

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       getTotalQuestion()
     * Description  get method to return no of total questions
     * @return      totalQuestion
     * @author      <i>Nguyen Vi Cao</i>
     * Date         3/9/2021
     * History log  3/9/2021
     * </pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
    public int getTotalQuestion() {
        return totalQuestion;
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       setTotalQuestion()
     * Description  set method to return no of total questions
     * @param       total int
     * @author      <i>Nguyen Vi Cao</i>
     * Date         3/9/2021
     * History log  3/9/2021
     * </pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
    public void setTotalQuestion(int total) {
        if(total >= 0)
            totalQuestion = total;
        else
            total = 0;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       getNoCorrect()
     * Description  get method to return no of correct questions
     * @return      noCorrect
     * @author      <i>Nguyen Vi Cao</i>
     * Date         3/9/2021
     * History log  3/9/2021
     * </pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
    public int getNoCorrect() {
        return noCorrect;
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       setNoCorrect()
     * Description  set method to return no of correct questions
     * @param       correct int
     * @author      <i>Nguyen Vi Cao</i>
     * Date         3/9/2021
     * History log  3/9/2021
     * </pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
    public void setNoCorrect(int correct) {
        if(correct >= 0)
            noCorrect = correct;
        else
            correct = 0;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       toString()
     * Description  overriden toString() method to display a Player object
     * @return      Player object String
     * @author      <i>Nguyen Vi Cao</i>
     * Date         3/9/2021
     * History log  3/9/2021
     * </pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public String toString()
    {
        return super.toString() + "," + noCorrect + "," + totalQuestion;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       equals()
     * Description  own method to check if one Player object equals to another
     * @return      true or false boolean
     * @author      <i>Nguyen Vi Cao</i>
     * Date         3/9/2021
     * History log  3/9/2021
     * </pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public boolean equals (Object obj)
    {
        Player player = new Player();
        if(obj instanceof Player)
        {
            player = (Player) obj;
            if  (this.getName().equalsIgnoreCase(player.getName()) &&  
                (closeEnough(this.getAge(), player.getAge())) &&
                (closeEnough(this.getTotalQuestion(), player.getTotalQuestion()))
                && (closeEnough(this.getNoCorrect(), player.getNoCorrect())))
                return true;
            else
                return false;
        }
        else
            return false;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>>
     * Method       closeEnough()
     * Description  method to check if two floating numbers are within EPISON of 
     *              each other. Used of comparison of floating point values
     * @parem       x double
     * @parem       y double
     * @return      true or false boolean
     * @author      <i>Nguyen Vi Cao</i>
     * Date         3/9/2021
     * History log  3/9/2021
     * </pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private boolean closeEnough(double x, double y)
    {
        final double EPSILON = 1E-9;
        return Math.abs(x - y) < EPSILON;
    }
}
