package StateCapitalsQuiz;
import java.util.Objects;
/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *<pre>
 * Class        Capital.java
 * Description  A class representing the Capital in Capital quiz  game. 
 *              Implements the Comparable interface.
 * Project      Composers Database
 * Platform     jdk 1.8.0_214; NetBeans IDE 11.3; Windows 10
 * Course       CS 142, EdCC
 * Date         4/26/2021
 * @author	<i>Niko Culevski</i>
 * @version 	%1% %0%
 * @see         java.lang.Comparable
 *</pre>
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class Capital implements Comparable
{
    private String state;
    private String capital;
    private int year;
    private double area;
    private double population;
    private int rank;
    
     /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Constructor  Capital()-default constructor
     * Description  Creates defaul (empty) Capital.
     * @author      <i>Niko Culevski</i>
     * Date         3/6/2021
     * History Log  7/18/2018, 5/10/2020
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
    public Capital()
    {
        state = "";
        capital = "";
        year = 0;
        area = 0;
        population = 0;
        rank = 0;
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Constructor  Artist()-overloaded constructor
     * Description  Assigs parameters to instance variables
     * @param       state String
     * @param       capital String
     * @param       year int
     * @param       area double
     * @param       population double
     * @param       rank int
     * @author      <i>Niko Culevski</i>
     * Date         3/6/2021
     * History Log  7/18/2018, 5/10/2020
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public Capital(String state, String capital, int year, double area, 
            double population,int rank )
    {
        this.state = state;
        this.capital = capital;
        this.year = year;
        this.area = area;
        this.population = population;
        this.rank = rank;
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Constructor  Capital()-copy constructor
     * Description  Assigs parameters from another Capital.
     * @param       another Capital
     * @author      <i>Niko Culevski</i>
     * Date         3/6/2021
     * History Log  7/18/2018, 5/10/2020
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public Capital(Capital another)
    {        
        this.state = another.state;
        this.capital = another.capital;
        this.year = another.year;
        this.area = another.area;
        this.population = another.population;
        this.rank = another.rank;

    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       getState()
     * Description  Getter method to return state of capital.
     * @return      state String
     * @author      <i>Niko Culevski</i>
     * Date         3/6/2021
     * History Log  7/18/2018, 5/10/2020
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public String getState()
    {
        return state;
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       setState()
     * Description  Setter method to set capital's state
     * @param       state String
     * @author      <i>Niko Culevski</i>
     * Date         3/6/2021
     * History Log  7/18/2018, 5/10/2020
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void setState(String state)
    {
        this.state = state;
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       getCapital()
     * Description  Getter method to return the capital's name
     * @return      capital String
     * @author      <i>Niko Culevski</i>
     * Date         3/6/2021
     * History Log  7/18/2018, 5/10/2020
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public String getCapital()
    {
        return capital;
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       setCapital()
     * Description  Setter method to set capital's name
     * @param       capital String
     * @author      <i>Niko Culevski</i>
     * Date         3/6/2021
     * History Log  7/18/2018, 5/10/2020
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void setCapital(String capital)
    {
        this.capital = capital;
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       getYear()
     * Description  Getter method to return the year the city became the capital
     * @return      year int
     * @author      <i>Niko Culevski</i>
     * Date         3/6/2021
     * History Log  7/18/2018, 5/10/2020
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public int getYear()
    {
        return year;
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       setYear()
     * Description  Setter method to set capital's birth year
     * @param       year int
     * @author      <i>Niko Culevski</i>
     * Date         3/6/2021
     * History Log  7/18/2018, 5/10/2020
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void setYear(int year)
    {
        this.year = year;
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       getArea()
     * Description  Getter method to return the area of the capital
     * @return      area double
     * @author      <i>Niko Culevski</i>
     * Date         3/6/2021
     * History Log  7/18/2018, 5/10/2020
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public double getArea()
    {
        return area;
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       setArea()
     * Description  Setter method to set the area of the capital
     * @param       area double
     * @author      <i>Niko Culevski</i>
     * Date         3/6/2021
     * History Log  7/18/2018, 5/10/2020
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void setArea(double area)
    {
        this.area = area;
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       getPopulation()
     * Description  Getter method to return the population of the capital
     * @return      population double
     * @author      <i>Niko Culevski</i>
     * Date         3/6/2021
     * History Log  7/18/2018, 5/10/2020
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public double getPopulation()
    {
        return population;
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       setPopulation()
     * Description  Setter method to set the population of the capital
     * @param       population double
     * @author      <i>Niko Culevski</i>
     * Date         3/6/2021
     * History Log  7/18/2018, 5/10/2020
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void setPopulation(double population)
    {
        this.population = population;
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       getRank()
     * Description  Getter method to return the rank based on population
     * @return      rank int
     * @author      <i>Niko Culevski</i>
     * Date         3/6/2021
     * History Log  7/18/2018, 5/10/2020
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public int getRank()
    {
        return rank;
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       setRank()
     * Description  Setter method to set capital's rank
     * @param       rank int
     * @author      <i>Niko Culevski</i>
     * Date         3/6/2021
     * History Log  7/18/2018, 5/10/2020
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void setRank(int rank)
    {
        this.rank = rank;
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       hashCode()
     * Description  Overridden method to set hash code
     * @return      hashcode int
     * @author      <i>Niko Culevski</i>
     * Date         3/6/2021
     * History Log  7/18/2018, 5/10/2020
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    
    @Override
    public String toString()
    {
        return state + "," + capital + "," + year + "," + area + "," + population
                + "," + rank;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       equals()
     * Description  Overridden method to check equality between capitals.
     * @return      true or flase boolean
     * @author      <i>Niko Culevski</i>
     * Date         3/6/2021
     * History Log  7/18/2018, 5/10/2020
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final Capital other = (Capital) obj;
        if (this.year != other.year)
        {
            return false;
        }
        if (!Objects.equals(this.capital, other.capital))
        {
            return false;
        }
        return true;
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Constructor  compareTo()
     * Description  Required method to compare two capitals by names and if names
     *              are equal then by year.
     * @param       obj Object
     * @author      <i>Niko Culevski</i>
     * Date         3/6/2021
     * History Log  7/18/2018, 5/10/2020
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public int compareTo(Object obj)
    {
        Capital otherCity = (Capital) obj;
        // if names are equal compare then by age
        if((this.getCapital()).equalsIgnoreCase(otherCity.getCapital()))        
            return this.year - ((Capital) obj).year;
        else    // otherwise, compare by name
           return  (this.getCapital().toLowerCase()).compareTo(otherCity.getCapital().toLowerCase());
    }    
}
