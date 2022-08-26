package FamousPhilosophers;

import java.util.Objects;

/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *<pre>
 * Class        Philosopher.java
 * Description  A class representing the GUI used in a maintaining a operas 
 *              database obtained from a text file, Operas.txt with 6 fields:
 *              Composer, Name of famous composition, year first performed, 
 *              city where first performed, Sypnosis, link to YouTube. 
 * Project      Famous Operas Database
 * Platform     jdk 1.8.0_214; NetBeans IDE 11.3; Windows 10
 * Course       CS 142, Edmonds Community College
 * Date         1/27/2021
 * History log  1/10/2021, 1/20/2021
 *
 * @author	<i>Nguyen Vi Cao</i>
 * @version 	%1% %5%
 *</pre>
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

public class Philosopher 
{
    //instance variables
    private String name;
    private String place;
    private int year;
    private String work;
    private String sypnosis;
    private String link;
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Constructor     Philosopher()-default constructor
     * Description     Create an instance of the Opera class and assign default
     *                 values to all fields
     * @author         <i>Nguyen Vi Cao</i>
     * Date            1/27/2021
     * History log     1/10/2021, 1/20/2021
     *</pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public Philosopher()
    {
        name = "";
        place = "";
        year = 0;
        work = "";
        sypnosis = "";
        link = "";
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Constructor  Philosopher ()-overloaded constructor
     * Description  Create an instance of the opera class and assign values via 
     *              parameters to all fields
     * @param       name String
     * @param       place String
     * @param       year int
     * @param       work String
     * @param       sypnosis String
     * @param       link String
     * @author      <i>Nguyen Vi Cao</i>
     * Date         1/27/2021
     * History log  1/10/2021, 1/20/2021
     * </pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
    public Philosopher (String name, String place, int year, String sypnosis,
            String work, String link)
    {
        this.name = name;
        this.place = place;
        this.year = year;
        this.work = work;
        this.sypnosis = sypnosis;
        this.link = link;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Constructor  Philosopher ()-overloaded copy constructor
     * Description  Create an instance of the opera class and assign values via
     *              parameters from another opera to all fields.
     * @param       anotherPhilosopher Philosopher
     * @author      <i>Nguyen Vi Cao</i>
     * Date         1/27/2021
     * History log  1/10/2021, 1/20/2021
     * </pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
    public Philosopher(Philosopher anotherPhilosopher)
    {
        this.name = anotherPhilosopher.name;
        this.place = anotherPhilosopher.place;
        this.year = anotherPhilosopher.year;
        this.work = anotherPhilosopher.work;
        this.sypnosis = anotherPhilosopher.sypnosis;
        this.link = anotherPhilosopher.link;
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       getPlace()
     * Description  Get method to set instance variable place.
     * @return      place String
     * @author      <i>Nguyen Vi Cao</i>
     * Date         1/27/2021
     * History log  1/10/2021, 1/20/2021
     * </pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
    public String getPlace() {
        return place;
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       setPlace()
     * Description  set method to set instance variable place.
     * @param       place String
     * @author      <i>Nguyen Vi Cao</i>
     * Date         1/27/2021
     * History log  1/10/2021, 1/20/2021
     * </pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
    public void setPlace(String place) {
        this.place = place;
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       getName()
     * Description  get method to return instance variable name.
     * @return      name String
     * @author      <i>Nguyen Vi Cao</i>
     * Date         1/27/2021
     * History log  1/10/2021, 1/20/2021
     * </pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
    public String getName() {
        return name;
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       setName()
     * Description  get method to return instance variable name.
     * @param       name String
     * @author      <i>Nguyen Vi Cao</i>
     * Date         1/27/2021
     * History log  1/10/2021, 1/20/2021
     * </pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
    public void setName(String name) {
        this.name = name;
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       getYear()
     * Description  get method to return instance variable year.
     * @return      year
     * @author      <i>Nguyen Vi Cao</i>
     * Date         1/27/2021
     * History log  1/10/2021, 1/20/2021
     * </pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
    public int getYear() {
        return year;
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       setYear()
     * Description  set method to set instance variable year.
     * @param       year int
     * @author      <i>Nguyen Vi Cao</i>
     * Date         1/27/2021
     * History log  1/10/2021, 1/20/2021
     * </pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
    public void setYear(int year) {
        this.year = year;
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       getWork()
     * Description  get method to return instance variable work.
     * @return      work String
     * @author      <i>Nguyen Vi Cao</i>
     * Date         1/27/2021
     * History log  1/10/2021, 1/20/2021
     * </pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
    public String getWork() {
        return work;
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       setWork()
     * Description  set method to set instance variable work.
     * @param       work String
     * @author      <i>Nguyen Vi Cao</i>
     * Date         1/27/2021
     * History log  1/10/2021, 1/20/2021
     * </pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
    public void setWork(String work) {
        this.work = work;
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       getSypnosis()
     * Description  get method to return instance variable sypnosis.
     * @return      sypnosis String
     * @author      <i>Nguyen Vi Cao</i>
     * Date         1/27/2021
     * History log  1/10/2021, 1/20/2021
     * </pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
    public String getSypnosis() {
        return sypnosis;
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       setSypnosis()
     * Description  set method to set instance variable sypnosis.
     * @param       sypnosis String
     * @author      <i>Nguyen Vi Cao</i>
     * Date         1/27/2021
     * History log  1/10/2021, 1/20/2021
     * </pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
    public void setSypnosis(String sypnosis) {
        this.sypnosis = sypnosis;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       getLink()
     * Description  get method to return instance variable link.
     * @return      link String
     * @author      <i>Nguyen Vi Cao</i>
     * Date         1/27/2021
     * History log  1/10/2021, 1/20/2021
     * </pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
    public String getLink() {
        return link;
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       setLink()
     * Description  set method to set instance variable link.
     * @param       link String
     * @author      <i>Nguyen Vi Cao</i>
     * Date         1/27/2021
     * History log  1/10/2021, 1/20/2021
     * </pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
    public void setLink(String link) {
        this.link = link;
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       toString()
     * Description  overriden toString() method to display a Opera object
     * @return      Opera object String
     * @author      <i>Nguyen Vi Cao</i>
     * Date         1/27/2021
     * History log  1/10/2021, 1/20/2021
     * </pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public String toString()
    {
        return "\nName of famous philosopher: " + name + "Place of birth: " + place
                + "\nYear born: " + year + 
                "\nMajor work description: " + work +
                "\nSypnosis: " + sypnosis + "\nLink to YouTube: " + link;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       equals()
     * Description  own method to check if one Opera object equals to another
     * @return      true or false boolean
     * @author      <i>Nguyen Vi Cao</i>
     * Date         1/27/2021
     * History log  1/10/2021, 1/20/2021
     * </pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public boolean equals (Object obj)
    {
        Philosopher philosopher = new Philosopher();
        if(obj instanceof Philosopher)
        {
            philosopher = (Philosopher) obj;
            if  (this.getName().equalsIgnoreCase(philosopher.getName()) && 
                (this.getPlace().equalsIgnoreCase(philosopher.getPlace()) && 
                (closeEnough(this.getYear(), philosopher.getYear())) &&
                (this.getWork().equalsIgnoreCase(philosopher.getWork()) && 
                (this.getSypnosis().equalsIgnoreCase(philosopher.getSypnosis()) && 
                (this.getLink().equalsIgnoreCase(philosopher.getLink()) ))))
                )
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
     * @parem       x int
     * @parem       y int
     * @return      true or false boolean
     * @author      <i>Nguyen Vi Cao</i>
     * Date         1/27/2021
     * History log  1/10/2021, 1/20/2021
     * </pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private boolean closeEnough(int x, int y)
    {
        final double EPSILON = 1E-9;
        return Math.abs(x - y) < EPSILON;
    }
}
