package FamousPhilosophers;
import java.awt.*;
import javax.swing.*;
import java.awt.print.*;

/** A simple utility class that lets you very simply print
 *  an arbitrary component. Just pass the component to the
 *  PrintUtilities.printComponent. The component you want to
 *  print doesn't need a print method and doesn't have to
 *  implement any interface or do anything special at all.
 *  <P>
 *  If you are going to be printing many times, it is marginally more 
 *  efficient to first do the following:
 *  <PRE>
 *    PrintUtilities printHelper = new PrintUtilities(theComponent);
 *  </PRE>
 *  then later do printHelper.print(). But this is a very tiny
 *  difference, so in most cases just do the simpler
 *  PrintUtilities.printComponent(componentToBePrinted).
 *
 *  7/99 Marty Hall, http://www.apl.jhu.edu/~hall/java/
 *  May be freely used or adapted.
 */
public class PrintUtilities implements Printable
{
    //class instance variable
    private Component componentToBePrinted;

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     *     Method       printComponent()
     *     Description  Method to invoke the static print method
     *     @author      <i>Niko Culevski</i>
     *     @param       c Component
     *     Date         12/13/2019
     *     History Log  7/18/2018
     *</pre>~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static void printComponent(Component c)
    {
        new PrintUtilities(c).print();
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     *     Constructor  
     *     Description  Assign componentToBePrinted to new created PrintUtilities
     *                  object
     *     @author      <i>Niko Culevski</i>
     *     @param       componentToBePrinted Component
     *     Date         12/13/2019
     *     History Log  7/18/2018
     *</pre>~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public PrintUtilities(Component componentToBePrinted)
    {
        this.componentToBePrinted = componentToBePrinted;
    }
  
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     *     Method       print()  
     *     Description  Creates a PrinterJob  and invoke its print method
     *     @author      <i>Niko Culevski</i>
     *     Date         12/13/2019
     *     History Log  7/18/2018
     *</pre>~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void print() 
    {
        PrinterJob printJob = PrinterJob.getPrinterJob();
        printJob.setPrintable(this);
        if (printJob.printDialog())
            try 
            {
                printJob.print();
            } 
            catch(PrinterException pe) 
            {
                System.out.println("Error printing: " + pe);
            }
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     *     Method       print()  
     *     Description  Overloaded print() method
     *     @param       g Graphics
     *     @param       pageFormat PageFormat
     *     @param       pageIndex int
     *     @author      <i>Niko Culevski</i>
     *     @return      NO_SUCH_PAGE or PAGE_EXISTS
     *     Date         12/13/2019
     *     History Log  7/18/2018
     *</pre>~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public int print(Graphics g, PageFormat pageFormat, int pageIndex)
    {
        if (pageIndex > 0) 
        {
            return(NO_SUCH_PAGE);
        } 
        else 
        {
            Graphics2D g2d = (Graphics2D)g;
            g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
            disableDoubleBuffering(componentToBePrinted);
            componentToBePrinted.paint(g2d);
            enableDoubleBuffering(componentToBePrinted);
            return(PAGE_EXISTS);
        }
    }

    /** The speed and quality of printing suffers dramatically if
     *  any of the containers have double buffering turned on.So this turns if off globally.
     *  @param c Component
     *  @see enableDoubleBuffering
     */
    public static void disableDoubleBuffering(Component c) 
    {
        RepaintManager currentManager = RepaintManager.currentManager(c);
        currentManager.setDoubleBufferingEnabled(false);
    }

    /** Re-enables double buffering globally.
     * @param c Component*/

    public static void enableDoubleBuffering(Component c) 
    {
        RepaintManager currentManager = RepaintManager.currentManager(c);
        currentManager.setDoubleBufferingEnabled(true);
    }
}
