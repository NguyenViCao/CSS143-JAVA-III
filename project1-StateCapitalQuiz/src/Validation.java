package StateCapitalsQuiz;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTextField;
/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *<pre>~
 * Class        Validation
 * File         Validation.java
 * Description  Validates entered values
 * @author      <i>Niko Culevski</i>
 * Environment  PC, Windows Vista Business, jdk 7.0, NetBeans 7.3.1
 * Date         4/7/2020
 * History log  6/10/2013
 * @version     1.2.1
 * @see         java.util.regex.Matcher
 * @see         java.util.regex.Pattern
 * </pre>
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class Validation 
{   
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       isDouble()
     * Description  Validates that double value is entered
     * @return      boolean
     * @param       fieldValue String
     * @author      <i>Niko Culevski</i>
     * @see         java.util.regex.Matcher
     * @see         java.util.regex.Pattern
     * Date         4/7/2020
     * History log  6/10/2013
     *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static boolean isDouble(String fieldValue)
    {
        Pattern pat = Pattern.compile("\\d+(\\.\\d+)?");
        Matcher mat = pat.matcher(fieldValue);
        return mat.matches();
    }
   
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
     * Method       isDouble()
     * Description  Overloaded, validates that double value is entered within
     *              the required range
     * @param       fieldValue String, input
     * @param       lower double, lower bound
     * @param       upper double, upper bound
     * @return      boolean
     * @author      <i>Niko Culevski</i>
     * @see         java.util.regex.Matcher
     * @see         java.util.regex.Pattern
     * Date         4/7/2020
     * History log  6/10/2013
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static boolean isDouble(String fieldValue, double lower, double upper)
    {
        boolean result = true;
        Pattern pat = Pattern.compile("\\d+(\\.\\d+)?");
        Matcher mat = pat.matcher(fieldValue);
        if(mat.matches())
        {
            try
            {
                //check range
                double num = Double.parseDouble(fieldValue);
                if(num < lower || num > upper)
                    result = false;
            }
            catch(NumberFormatException ex)
            {
                //something went wrong
                result = false;
            }
        }
        else
        {
            result = false;
        }
        return result;
        //return mat.matches();
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       isInteger()
     * Description  Validates that entered value is an integer
     * @return      boolean
     * @param       fieldValue String, input
     * @author      <i>Niko Culevski</i>
     * @see         java.util.regex.Matcher
     * @see         java.util.regex.Pattern
     * Date         4/7/2020
     * History log  6/10/2013
     *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static boolean isInteger(String fieldValue)
    {
        Pattern pat = Pattern.compile("\\d+");
        Matcher mat = pat.matcher(fieldValue);
        return mat.matches();   
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       isInteger()
     * Description  Overloaded, validates that entered value is an integer
     *              within the required range
     * @return      boolean
     * @param       fieldValue String, input
     * @param       lower int, lower bound
     * @param       upper int, upper bound
     * @author      <i>Niko Culevski</i>
     * @see         java.util.regex.Matcher
     * @see         java.util.regex.Pattern
     * Date         4/7/2020
     * History log  6/10/2013
     *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static boolean isInteger(String fieldValue, int lower, int upper)
    {
        boolean result = true;
        Pattern pat = Pattern.compile("\\d+");
        Matcher mat = pat.matcher(fieldValue);
        if(mat.matches())
        {
            try
            {
                //check range
                int num = Integer.parseInt(fieldValue);
                if(num < lower || num > upper)
                    result = false;
            }
            catch(NumberFormatException ex)
            {
                //something went wrong
                result = false;
            }
        }
        else
        {
            result = false;
        }
        return result;           
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       isEmpty()
     * Description  Validates that JTextField is not empty
     * @return      boolean
     * @param       fieldValue: JTextField, imput
     * @author      <i>Niko Culevski</i>
     * @see         java.util.regex.Matcher
     * @see         java.util.regex.Pattern
     * @see         javax.swing.JTextField
     * Date         4/7/2020
     * History log  6/10/2013
     *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static boolean isEmpty(JTextField fieldValue)
    {
        String input = fieldValue.getText();
        if(input.length() <= 0 || input.equals(""))
            return true;
        else
            return false;
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       isValidName()
     * Description  Validates that JTextField is a valid name consisting of 
     *              only letters and spaces with minimum 2 and maximum 40
     *              letters.
     * Date         4/7/2020
     * History log  6/10/2013
     * </pre>
     * @return      result
     * @param       input: JTextField, input
     * @author      <i>Niko Culevski</i>
     * @see         java.util.regex.Matcher
     * @see         java.util.regex.Pattern
     * @see         javax.swing.JTextField
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static boolean isValidName(String input)
    {
        final short MAX_LENGTH = 40;
        final short MIN_LENGTH = 2;
        boolean result = true;
        if(input.equals("") || input.length() <= MIN_LENGTH || 
                input.length() > MAX_LENGTH || input.equals(null))
            return false;
        //Lettera and spaces in unicode only
        //String regx = "\\^[a-zA-Z '.-]*$"; //"\\^[a-zA-Z '.-]{3,30}$";      
        //String regx = "\\[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*$";
        String regx =  "^[\\p{L} _.'-]+$";     //"^\\p{Lu}\\p{L}*(?:[\\s-]\\p{Lu}\\p{L}*)*$";
        Pattern pat = Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
        Matcher mat = pat.matcher(input);    
        result = mat.matches();
        return result;
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       isValidName()--overloaded
     * Description  Validates that JTextField is a valid name consisting of 
     *              only letters and spaces with minimum and maximum as provide
     *              parameters.
     * Date         4/7/2020
     * History log  6/10/2013
     *</pre>
     * @return      true or false boolean
     * @param       input JTextField
     * @param       lower int
     * @param       upper int
     * @author      <i>Niko Culevski</i>
     * @see         java.util.regex.Matcher
     * @see         java.util.regex.Pattern
     * @see         javax.swing.JTextField
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static boolean isValidName(String input, int lower, int upper)
    {
        boolean result = true;
        if(input.equals("") || input.length() <= lower || 
                input.length() > upper || input.equals(null))
            return false;
        //Lettera and spaces in unicode only
        //String regx = "\\^[a-zA-Z '.-]*$"; //"\\^[a-zA-Z '.-]{3,30}$";      
        //String regx = "\\[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*$";
        String regx =  "^[\\p{L} _.'-]+$";     //"^\\p{Lu}\\p{L}*(?:[\\s-]\\p{Lu}\\p{L}*)*$";
        Pattern pat = Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
        Matcher mat = pat.matcher(input);    
        result = mat.matches();
        return result;
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       isDigit()
     * Description  Validates that entered value is an integer in range [1,50]
     * @return      boolean
     * @param       fieldValue String, input
     * @author      <i>Niko Culevski</i>
     * @see         java.util.regex.Matcher
     * @see         java.util.regex.Pattern
     * Date         4/7/2020
     * History log  6/10/2013
     *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static boolean isDigit(String fieldValue)
    {
        Pattern pat = Pattern.compile("\\d+");
        Matcher mat = pat.matcher(fieldValue);
        return mat.matches();   
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       isDigit()
     * Description  Overloaded, validates that entered value is an integer
     *              within the required range
     * @return      boolean
     * @param       fieldValue String
     * @param       upper int
     * @param       lower int
     * @author      <i>Niko Culevski</i>
     * @see         java.util.regex.Matcher
     * @see         java.util.regex.Pattern
     * Date         4/7/2020
     * History log  6/10/2013
     *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static boolean isDigit(String fieldValue, int lower, int upper)
    {
        boolean result = true;
        Pattern pat = Pattern.compile("\\d+");
        Matcher mat = pat.matcher(fieldValue);
        if(mat.matches())
        {
            try
            {
                //check range
                int num = Integer.parseInt(fieldValue);
                if(num < 1 || num > 50)
                    result = false;
            }
            catch(NumberFormatException ex)
            {
                //something went wrong
                result = false;
            }
        }
        else
        {
            result = false;
        }
        return result;           
    }
}
   

