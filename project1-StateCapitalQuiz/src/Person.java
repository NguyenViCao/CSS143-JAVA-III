
package StateCapitalsQuiz;

import java.util.Objects;

/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *<pre>
 * Class        Person
 * File         Person.java
 * Description  A class encasolute a very simple version of Person
 * Project      Two Cards Simulation
 * Environment  PC, Windows 10, NetBeans IDE 11.3, jdk 1.8.0_241
 * Date         3/9/2021
 * History Log  3/9/2021
 * @author      <i>Nguyen Vi Cao</i>
 * @version:    %1% %2%
 * </pre>
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public abstract class Person {
    protected String name;
    protected int age;
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Constructor     Person()-default constructor
     * Description     assign default values to instance variables.
     * @author         <i>Nguyen Vi Cao</i>
     * Date            3/9/2021
     * History log     3/9/2021
     *</pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public Person()
    {
        name = "";
        age = 0;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Constructor  Person() -- Overloded constructor
     * Description  assigns parameter values to instance variables
     * @param       name String
     * @param       age int
     * @author      <i>Nguyen Vi Cao</i>
     * @see         java.awt.Toolkit
     * Date         3/9/2021
     * History Log  3/9/2021
     *</pre>
     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public Person (String name, int age)
    {
        this.name = name;
        this.age = age;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       getName()
     * Description  Getter method to return name
     * @author      <i>Nguyen Vi Cao</i>
     * @return      name String
     * Date         3/9/2021
     * History Log  3/9/2021
     *</pre>
     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public String getName()
    {
        return name;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       getAge()
     * Description  Getter method to return age
     * @author      <i>Nguyen Vi Cao</i>
     * @return      age int
     * Date         3/9/2021
     * History Log  3/9/2021
     *</pre>
     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public int getAge()
    {
        return age;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       setName()
     * Description  Getter method to set name
     * @author      <i>Nguyen Vi Cao</i>
     * @param       name String
     * Date         3/9/2021
     * History Log  3/9/2021
     *</pre>
     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void setName(String name)
    {
        this.name = name;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       setAge()
     * Description  Getter method to set age
     * @author      <i>Nguyen Vi Cao</i>
     * @param       age int
     * Date         3/9/2021
     * History Log  3/9/2021
     *</pre>
     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void setAge(int age)
    {
        this.age = age;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       toString()
     * Description  overriden toString() method to return
     * @return      person String
     * @author      <i>Nguyen Vi Cao</i>
     * Date         3/9/2021
     * History log  3/9/2021
     * </pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public String toString()
    {
        return name + "," + age;
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
        final Person other = (Person) obj;
        if (this.age != other.age)
        {
            return false;
        }
        if (!Objects.equals(this.name, other.name))
        {
            return false;
        }
        return true;
    }
}
