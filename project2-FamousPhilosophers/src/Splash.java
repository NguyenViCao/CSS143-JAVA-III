package FamousPhilosophers;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.UIManager;

/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File         Splash.java
 * Description  A class representing the Splash screen used by the 
 *              FamousOperasDatabase.java. GUI used in a maintaining a
 *              famous operas database
 *<pre>
 * Project      Sorting and Searching
 * Platform     jdk 1.8.0_241, NetBeans IDE 11.3, Windows 10
 * Course       CS 142
 * Hours        20 hours and 45 minutes
 * Date         1/27/2021
 * History log  1/10/2021, 1/20/2021
 *</pre>
 *
 * @author	<i>Nguyen Vi Cao</i>
 * @version 	%1% %2%
 * @see     	java.awt.Color
 * @see     	java.awt.Toolkit
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

public class Splash extends JWindow
{
    //duration is integer value in milliseconds for how long the Splash screen is visible
    private int duration;
    private JProgressBar loading = new JProgressBar();
    private int progress;
    
    public Splash (int dur)
    {
        UIManager.put("ProgressBar.selectionForeground", Color.gray.darker());
        duration = dur;
        loading = new JProgressBar(0, 100);
        loading.setStringPainted(true);
    }
    
    public void showSplash()
    {

        JPanel content = (JPanel)getContentPane();
        content.setBackground(Color.lightGray);

        // Set the window's bounds, centering the window
        int width = 600;
        int height = 730;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width-width)/2;
        int y = (screen.height-height)/2;
        setBounds(x,y,width,height);

        // Build the splash screen
        JLabel label = new JLabel(new ImageIcon("src/Images/Sanzio.png"));
        JLabel copyrt = new JLabel
            ("Copyright Famous Philosophers Inc., 2021",
                    JLabel.CENTER);
        copyrt.setFont(new Font("Sans-Serif", Font.BOLD, 12));
        content.add(label, BorderLayout.CENTER);
        //content.add(copyrt, BorderLayout.SOUTH);
        content.add(loading, BorderLayout.SOUTH);
        Color border = new Color(50, 20, 20,  55);
        content.setBorder(BorderFactory.createLineBorder(border, 10));

        // Display it
        setVisible(true);

        // Wait a little while, maybe while loading resources
        try 
        {
            //Increment the progress bar's value to 100 starting from 0
            incProgress(20);
            Thread.sleep(duration);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        setVisible(false);
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Creates and runs a new thread to manage the incrementation of the 
     * progress bar while the splash screen is showing
     * @param   amount
     * @throws  InterruptedException
    */ 
    public void incProgress(int amount) throws InterruptedException
    {
        //Instantiate and start new thread
        ProgressThread up = new ProgressThread(amount);
        up.thread.start();
    }
    
     class ProgressThread 
    {
        private int amount;
        public ProgressThread(int amount)
        {
            this.amount = amount;
        }

        private Thread thread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                {
                    //Increment the progress bar until it's value hits 100
                    while (progress < 100) 
                    {
                        progress++;
                        loading.setString(String.valueOf(progress + "%"));
                        try 
                        {
                            Thread.sleep(25);
                        } 
                        catch (InterruptedException ex) 
                        {

                        }
                        loading.setValue(progress);
                    }
                }
            }
        });
    }
}
