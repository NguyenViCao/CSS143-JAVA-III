package FamousPhilosophers;

import static FamousPhilosophers.MySQLConnection.DB_URL;
import static FamousPhilosophers.MySQLConnection.PASS;
import static FamousPhilosophers.MySQLConnection.USER;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

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
public class FamousPhilosophers extends javax.swing.JFrame implements MySQLConnection
{
    private final String PHILOSOPHERS_TEXT_FILE = "src/FamousPhilosophers/Philosophers.txt";
    //class instance ArrayList of philosophers objects
    private ArrayList<Philosopher> philosophers = new ArrayList<Philosopher>();
    //external file name for philosophers
    private Philosopher myPhilosopher = new Philosopher();
    private int currentID = 1, sizeOfDB;
    private final Color white = Color.WHITE;    // Default background color for input textfield
    private final Color pink = Color.PINK;      // Background color for bad input textfield
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Constructor     Famousphilosophers()-default constructor
     * Description     Create an instance of the Famousphilosophers class , 
     *                 set the default JButton to be addJButton, set icon image,
     *                 centers form.
     * @author         <i>Nguyen Vi Cao</i>
     * Date            1/27/2021
     * History Log     1/10/2021, 1/20/2021
     *</pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
    public FamousPhilosophers() 
    {
        try
        {
            initComponents();
            this.getRootPane().setDefaultButton(addJButton); //set addJButton as default
            this.setIconImage(Toolkit.getDefaultToolkit().
                    getImage("src/Images/Sanzio.png"));
            //centers the form at start.
            setLocationRelativeTo(null);
            //Read from an external text file philosophers.txt and populate the ArrayList 
            //of philosophers type
            readFromFile(PHILOSOPHERS_TEXT_FILE);
            createDB();
            //obtain connections constants from interface
//            String url = DB_URL;        //"jdbc:mysql://localhost:3306/javabook"
//            String user = USER;         //"root"
//            String password = PASS;     //"Caonguyenvi3132="
            //class.forName("com.mysql.cj.jdbc.Driver");        //not needed
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            //Statement stmt = con.createStatement();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            
            //retrieveing the data to get count on how many records are in the DB
            ResultSet rs = stmt.executeQuery("SELECT count(*) FROM FamousPhilosophers");
            rs.next();
            //moving cursor to the last row
            sizeOfDB = rs.getInt("count(*)");   //  =friends.size();
            
            String query = "SELECT * FROM FamousPhilosophers";
            rs = stmt.executeQuery(query);
            rs.next();      //move to first record
            
            int firstIndex = rs.getInt("philosopherID");
            Philosopher tempPhilosopher = searchPhilosopher(firstIndex);
            
            displayPhilosophers();//tempPhilosopher        //show philosopher's data
        }
        catch(SQLException exp)
        {
            exp.printStackTrace();
            // Show error message
            JOptionPane.showMessageDialog(null, "Input error -- SQL error.",
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
    private void readFromFile (String textFile)
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
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       createDB()
     * Description  Make connection, drop existing table, and create database 
     *              table.
     * Date:        5/12/2021
     * @author      <i>Nguyen Vi Cao</i>
     * @param       textFile String
     *</pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/   
    private void createDB()
    {
        try
        {
//            String url = DB_URL;        //"jdbc:mysql://localhost:3306/javabook"
//            String user = USER;         //"root"
//            String password = PASS;     //"Caonguyenvi3132="
            //class.forName("com.mysql.cj.jdbc.Driver");        //not needed
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);

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
     *<pre>>
     * Method       displayPhilosophers
     * Description  Show information about the philosopher passed as parameter.
     * @author      <i>Nguyen Vi Cao</i>
     * @param       myPhilosopher Philosopher
     * Date         1/27/2021
     * </pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void displayPhilosophers()
    {
        nameJTextField.setText(myPhilosopher.getName());
        placeJTextField.setText(myPhilosopher.getPlace());
        yearJTextField.setText(String.valueOf(myPhilosopher.getYear()));
        workJTextField.setText(myPhilosopher.getWork());
        sypnosisJTextField.setText(myPhilosopher.getSypnosis());
        linkJTextField.setText(myPhilosopher.getLink());
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>>
     * Method       showPhilosopherData()
     * Description  display information about selected philosopher
     * @param       index int
     * @author      <i>Nguyen Vi Cao</i>
     * Date         1/27/2021
     * History log  1/10/2021, 1/20/2021
     * </pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
    private void showPhilosopherData (int index)
    {
        if(index >= 0 && index < philosophers.size())
        {
            nameJTextField.setText(philosophers.get(index).getName());
            placeJTextField.setText(philosophers.get(index).getPlace());
            yearJTextField.setText(String.valueOf(philosophers.get(index).getYear()));
            workJTextField.setText(philosophers.get(index).getWork());
            sypnosisJTextField.setText(philosophers.get(index).getSypnosis());
            linkJTextField.setText(philosophers.get(index).getLink());
        }
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       insertionSort
     * Description  Sorts ArrayList philosophers in ascending order by name. Uses 
     *              the insertion sort algorithm which inserts city at correct 
     *              position and shuffles everyone else below that position.
     * @param       philosophers ArrayList
     * @author      <i>Nguyen Vi Cao</i>
     * Date         1/27/2021
     * History log  1/10/2021, 1/20/2021
     * </pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
    public static void insertionSort(ArrayList <Philosopher> philosophers)
    {
        int i, j;
        for (i = 0; i < philosophers.size(); i++)
        {
            Philosopher temp = philosophers.get(i);
            j = i - 1;
            while (j >= 0 && philosophers.get(j).getName().compareToIgnoreCase(temp.getName()) > 0)
            {
                philosophers.set (j + 1, philosophers.get(j));
                j--;
            }
            philosophers.set(j + 1, temp);
        }
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       selectionSort
     * Description  Sorts ArrayList philosophers in descending order by population. 
     *              Calls findsMaximum to find city with maximum population in 
     *              each pass and swap to exchange cities when necessary.
     * @param       philosophers ArrayList
     * @author      <i>Nguyen Vi Cao</i>
     * Date         1/27/2021
     * History log  1/10/2021, 1/20/2021
     * </pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/             
    public void selectionSort(ArrayList < Philosopher > philosophers)
    {
        int max = 0;
        for (int i = 0; i < philosophers.size(); i++)
        {
            max = findMaximum(philosophers, i);
            //swap(philosophers, i , max)
            Philosopher temp = philosophers.get(i);
            philosophers.set(i, philosophers.get(max));
            philosophers.set(max, temp);
        }
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       findMaximum
     * Description  Called by selectionSort to find the index of the member 
     *              with the most recent year from a given index to the end 
     *              of the ArrayList
     * @param       philosophers ArrayList
     * @param       i index from which to search for the max greater than 0
     * @return      int i
     * @author      <i>Nguyen Vi Cao</i>
     * Date         1/27/2021
     * History log  1/10/2021, 1/20/2021
     * </pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
    public int findMaximum (ArrayList < Philosopher > philosophers, int i)
    {
        int j, max = i;
        for (j = i + 1; j < philosophers.size(); j++)
        {
            if (philosophers.get(j).getYear() > philosophers.get(max).getYear())
                max = j;
        }
        return max;
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        searchingJButtonGroup = new javax.swing.ButtonGroup();
        titleJPanel = new javax.swing.JPanel();
        titleJLabel = new javax.swing.JLabel();
        logoJLabel1 = new javax.swing.JLabel();
        displayJPanel = new javax.swing.JPanel();
        nameJLabel = new javax.swing.JLabel();
        nameJTextField = new javax.swing.JTextField();
        placeJLabel = new javax.swing.JLabel();
        placeJTextField = new javax.swing.JTextField();
        yearJLabel = new javax.swing.JLabel();
        yearJTextField = new javax.swing.JTextField();
        workJLabel = new javax.swing.JLabel();
        workJTextField = new javax.swing.JTextField();
        synopsisJLabel = new javax.swing.JLabel();
        sypnosisJTextField = new javax.swing.JTextField();
        linkJLabel = new javax.swing.JLabel();
        linkJTextField = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        addJButton = new javax.swing.JButton();
        editJButton = new javax.swing.JButton();
        deleteJButton = new javax.swing.JButton();
        exitJButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        philosophersJList = new javax.swing.JList<>();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileJMenu = new javax.swing.JMenu();
        newJMenuItem = new javax.swing.JMenuItem();
        printFormJMenuItem = new javax.swing.JMenuItem();
        printPhilosopherJMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        exitJMenuItem = new javax.swing.JMenuItem();
        sortingJMenu = new javax.swing.JMenu();
        byNameJRadioButtonMenuItem = new javax.swing.JRadioButtonMenuItem();
        byYearJRadioButtonMenuItem = new javax.swing.JRadioButtonMenuItem();
        databaseJMenu = new javax.swing.JMenu();
        addJMenuItem = new javax.swing.JMenuItem();
        deleteJMenuItem = new javax.swing.JMenuItem();
        editJMenuItem = new javax.swing.JMenuItem();
        searchJMenuItem = new javax.swing.JMenuItem();
        helpJMenu = new javax.swing.JMenu();
        aboutJMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Famous Philosophers");

        titleJLabel.setFont(new java.awt.Font("Tempus Sans ITC", 2, 48)); // NOI18N
        titleJLabel.setForeground(new java.awt.Color(0, 204, 204));
        titleJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleJLabel.setText("Famous Philosophers");

        logoJLabel1.setFont(new java.awt.Font("Tahoma", 2, 24)); // NOI18N
        logoJLabel1.setForeground(new java.awt.Color(51, 0, 0));
        logoJLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Philosophers.jpg"))); // NOI18N

        javax.swing.GroupLayout titleJPanelLayout = new javax.swing.GroupLayout(titleJPanel);
        titleJPanel.setLayout(titleJPanelLayout);
        titleJPanelLayout.setHorizontalGroup(
            titleJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titleJPanelLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(logoJLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(titleJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        titleJPanelLayout.setVerticalGroup(
            titleJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titleJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(titleJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(titleJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(logoJLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        displayJPanel.setLayout(new java.awt.GridLayout(6, 2, 3, 3));

        nameJLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nameJLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        nameJLabel.setText("Name of Philosophers: ");
        displayJPanel.add(nameJLabel);

        nameJTextField.setEditable(false);
        nameJTextField.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nameJTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameJTextFieldActionPerformed(evt);
            }
        });
        displayJPanel.add(nameJTextField);

        placeJLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        placeJLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        placeJLabel.setText("Birth Place: ");
        displayJPanel.add(placeJLabel);

        placeJTextField.setEditable(false);
        placeJTextField.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        displayJPanel.add(placeJTextField);

        yearJLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        yearJLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        yearJLabel.setText("Year born: ");
        displayJPanel.add(yearJLabel);

        yearJTextField.setEditable(false);
        yearJTextField.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        displayJPanel.add(yearJTextField);

        workJLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        workJLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        workJLabel.setText("Major work: ");
        displayJPanel.add(workJLabel);

        workJTextField.setEditable(false);
        workJTextField.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        displayJPanel.add(workJTextField);

        synopsisJLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        synopsisJLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        synopsisJLabel.setText("Sypnosis of philosophy: ");
        displayJPanel.add(synopsisJLabel);

        sypnosisJTextField.setEditable(false);
        sypnosisJTextField.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        sypnosisJTextField.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                sypnosisJTextFieldMouseWheelMoved(evt);
            }
        });
        displayJPanel.add(sypnosisJTextField);

        linkJLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        linkJLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        linkJLabel.setText("Link to YouTube: ");
        displayJPanel.add(linkJLabel);

        linkJTextField.setEditable(false);
        linkJTextField.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        displayJPanel.add(linkJTextField);

        jPanel3.setLayout(new java.awt.GridLayout(1, 4, 3, 3));

        addJButton.setMnemonic('a');
        addJButton.setText("Add");
        addJButton.setToolTipText("Add a new Philosopher");
        addJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addJButtonActionPerformed(evt);
            }
        });
        jPanel3.add(addJButton);

        editJButton.setMnemonic('e');
        editJButton.setText("Edit");
        editJButton.setToolTipText("Edit the Philosopher");
        editJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editJButtonActionPerformed(evt);
            }
        });
        jPanel3.add(editJButton);

        deleteJButton.setMnemonic('d');
        deleteJButton.setText("Delete");
        deleteJButton.setToolTipText("Delete the philosopher");
        deleteJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteJButtonActionPerformed(evt);
            }
        });
        jPanel3.add(deleteJButton);

        exitJButton.setMnemonic('e');
        exitJButton.setText("Exit");
        exitJButton.setToolTipText("Quit the program");
        exitJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitJButtonActionPerformed(evt);
            }
        });
        jPanel3.add(exitJButton);

        philosophersJList.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        philosophersJList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                philosophersJListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(philosophersJList);

        fileJMenu.setText("File");

        newJMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        newJMenuItem.setText("New");
        newJMenuItem.setToolTipText("Open a new Database");
        newJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newJMenuItemActionPerformed(evt);
            }
        });
        fileJMenu.add(newJMenuItem);

        printFormJMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        printFormJMenuItem.setText("Print Form");
        printFormJMenuItem.setToolTipText("Print the GUI");
        printFormJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printFormJMenuItemActionPerformed(evt);
            }
        });
        fileJMenu.add(printFormJMenuItem);

        printPhilosopherJMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        printPhilosopherJMenuItem.setText("Print Philosopher");
        printPhilosopherJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printPhilosopherJMenuItemActionPerformed(evt);
            }
        });
        fileJMenu.add(printPhilosopherJMenuItem);
        fileJMenu.add(jSeparator1);

        exitJMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        exitJMenuItem.setText("Exit");
        exitJMenuItem.setToolTipText("Quit the program");
        exitJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitJMenuItemActionPerformed(evt);
            }
        });
        fileJMenu.add(exitJMenuItem);

        jMenuBar1.add(fileJMenu);

        sortingJMenu.setText("Sorting");

        byNameJRadioButtonMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        searchingJButtonGroup.add(byNameJRadioButtonMenuItem);
        byNameJRadioButtonMenuItem.setSelected(true);
        byNameJRadioButtonMenuItem.setText("By Name");
        byNameJRadioButtonMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                byNameJRadioButtonMenuItemActionPerformed(evt);
            }
        });
        sortingJMenu.add(byNameJRadioButtonMenuItem);

        byYearJRadioButtonMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_MASK));
        searchingJButtonGroup.add(byYearJRadioButtonMenuItem);
        byYearJRadioButtonMenuItem.setText("By Year");
        byYearJRadioButtonMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                byYearJRadioButtonMenuItemActionPerformed(evt);
            }
        });
        sortingJMenu.add(byYearJRadioButtonMenuItem);

        jMenuBar1.add(sortingJMenu);

        databaseJMenu.setText("Database Management");

        addJMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        addJMenuItem.setText("Add new Philosopher");
        addJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addJMenuItemActionPerformed(evt);
            }
        });
        databaseJMenu.add(addJMenuItem);

        deleteJMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        deleteJMenuItem.setText("Delete Philosopher");
        deleteJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteJMenuItemActionPerformed(evt);
            }
        });
        databaseJMenu.add(deleteJMenuItem);

        editJMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        editJMenuItem.setText("Edit Philosopher");
        editJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editJMenuItemActionPerformed(evt);
            }
        });
        databaseJMenu.add(editJMenuItem);

        searchJMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        searchJMenuItem.setText("Search Philosopher");
        searchJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchJMenuItemActionPerformed(evt);
            }
        });
        databaseJMenu.add(searchJMenuItem);

        jMenuBar1.add(databaseJMenu);

        helpJMenu.setText("Help");

        aboutJMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        aboutJMenuItem.setText("About");
        aboutJMenuItem.setToolTipText("Show the About form");
        aboutJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutJMenuItemActionPerformed(evt);
            }
        });
        helpJMenu.add(aboutJMenuItem);

        jMenuBar1.add(helpJMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titleJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(displayJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 571, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titleJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(displayJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nameJTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameJTextFieldActionPerformed
    }//GEN-LAST:event_nameJTextFieldActionPerformed

    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    * Method        aboutJMenuItemActionPerformed()
    * Description   event handler for aboutJMenuItem to show the About form
    * @parem        evt ActionEvent
    * @author       <i>Nguyen Vi Cao</i>
    * Date          1/27/2021
     * History log  1/10/2021, 1/20/2021
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void aboutJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutJMenuItemActionPerformed
        //display About form
        About aboutWindow = new About(this,true);
        aboutWindow.setVisible(true);
    }//GEN-LAST:event_aboutJMenuItemActionPerformed

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       exists()
     * Description  Check if parameter-given person exists in the DB. 
     * @param       myPhilosopher Philosopher
     * @author      <i>Niko Culevski</i>
     * Date         4/5/2021
     * History Log  7/18/2018, 5/7/2020
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private boolean exists(Philosopher myPhilosopher)
    {
        boolean found = false;
        try
        {
            
        }
        catch(Exception exp)
        {
            exp.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error in exists.", 
                    "Error!", JOptionPane.ERROR_MESSAGE);
        }
        return found;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    * Method        exitJButtonActionPerformed()
    * Description   Event handler to close the application
    * @param        evt ActionWvent
    * @see          java.awt.event.ActionEvent
    * @author       <i>Nguyen Vi Cao</i>
    * Date          1/27/2021
     * History log  1/10/2021, 1/20/2021
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void exitJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitJButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitJButtonActionPerformed

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    * Method        exitJMenuItemActionPerformed()
    * Description   Call the exitJButtonActionPerformed event handler to exit
    * @param        evt ActionWvent
    * @see          java.awt.event.ActionEvent
    * @author       <i>Nguyen Vi Cao</i>
    * Date          1/27/2021
     * History log  1/10/2021, 1/20/2021 
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void exitJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitJMenuItemActionPerformed
        exitJButtonActionPerformed(evt);
    }//GEN-LAST:event_exitJMenuItemActionPerformed

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    * Method        philosophersJListValueChanged()
    * Description   Event handler for philosophersJListValueChanged to update 
    *               information on selected philosophers
    * @parem        evt ListSelectionEvent
    * @author       <i>Nguyen Vi Cao</i>
    * Date          1/27/2021
     * History log  1/10/2021, 1/20/2021
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void philosophersJListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_philosophersJListValueChanged
        int index = (philosophersJList.getSelectedIndex());
        if (index >= 0)
            showPhilosopherData(index);
    }//GEN-LAST:event_philosophersJListValueChanged

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    * Method        byNameJRadioButtonMenuItemActionPerformed()
    * Description   event handler for byNameJRadioButtonMenuItemActionPerformed 
    *               to display philosophers
    * @parem        evt ActionWvent
    * @author       <i>Nguyen Vi Cao</i>
    * Date          1/27/2021
     * History log  1/10/2021, 1/20/2021
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void byNameJRadioButtonMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_byNameJRadioButtonMenuItemActionPerformed
        //display philosophers sorted by last name
        displayPhilosophers();
    }//GEN-LAST:event_byNameJRadioButtonMenuItemActionPerformed

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    * Method        byYearJRadioButtonMenuItemActionPerformed()
    * Description   event handler for byYearJRadioButtonMenuItemActionPerformed 
    *               to display philosophers
    * @parem        evt ActionWvent
    * @author       <i>Nguyen Vi Cao</i>
    * Date          1/27/2021
     * History log  1/10/2021, 1/20/2021
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void byYearJRadioButtonMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_byYearJRadioButtonMenuItemActionPerformed
        displayPhilosophers();
    }//GEN-LAST:event_byYearJRadioButtonMenuItemActionPerformed

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    * Method        printFormJMenuItemActionPerformed()
    * Description   event handler for printFormJMenuItemActionPerformed to print 
    *               the form as a GUI
    * @parem        evt--ActionWvent
    * @author       <i>Nguyen Vi Cao</i>
    * Date          1/27/2021
     * History log  1/10/2021, 1/20/2021  
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void printFormJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printFormJMenuItemActionPerformed
        PrintUtilities.printComponent(this);
    }//GEN-LAST:event_printFormJMenuItemActionPerformed

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    * Method        printphilosopherJMenuItemActionPerformed()
    * Description   event handler for printphilosopherJMenuItemActionPerformed to print 
    *               the philosopher
    * @parem        evt ActionWvent
    * @author       <i>Nguyen Vi Cao</i>
    * Date          1/27/2021
     * History log  1/10/2021, 1/20/2021  
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void printPhilosopherJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printPhilosopherJMenuItemActionPerformed
        int index = philosophersJList.getSelectedIndex();
        JTextArea printPhilosopher = new JTextArea();
        if (index >= 0)
        {
            try
            {
                Philosopher temp = new Philosopher(philosophers.get(index));
                String output = "Name: " + temp.getName() + "\n" +
                "Birth Place: " + temp.getPlace() + "\n" + 
                "Year: " + temp.getYear() + "\n" +
                "Major Work: " + temp.getWork() + "\n" + 
                "Sypnosis: " + temp.getSypnosis() + "\n" +
                "Link: " + temp.getLink() +  "\n";
                printPhilosopher.setText(output);
                printPhilosopher.print();
            }
            catch (PrinterException ex)
            {
                JOptionPane.showMessageDialog(null, "Philosopher not printed",
                        "Print Error", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_printPhilosopherJMenuItemActionPerformed

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    * Method        newJMenuItemActionPerformed()
    * Description   show a JFileChooser with an OpenDialog to delect a different
    *               philosophers Database
    * @parem        evt ActionWvent
    * @author       <i>Nguyen Vi Cao</i>
    * Date          1/27/2021
     * History log  1/10/2021, 1/20/2021 
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void newJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newJMenuItemActionPerformed
        JFileChooser chooser = new JFileChooser("src/FamousPhilosphers");
        //Filter only txt files
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Txt files", "txt");
        chooser.setFileFilter(filter);
        int choice = chooser.showOpenDialog(null);
        if (choice == JFileChooser.APPROVE_OPTION)
        {
            //clear existing philosophers ArrayList and JList
            philosophers.clear();
            philosophersJList.removeAll();
            File chosenFile = chooser.getSelectedFile();
            String file = "src/FamousPhilosphers/" + chosenFile.getName();
            
            System.out.println("file = " + file);
            readFromFile(file);
            displayPhilosophers();
        }
        else
        {
//            JOptionPane.showMessageDialog(null, "Unable to read file",
//                    "File input error", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_newJMenuItemActionPerformed

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *   Method          deleteJMenuItemActionPerformed()
    *	Description     event handler for deleteJButton to delete selected philosophers
    *	Date            1/27/2021
    *   History log     1/10/2021, 1/20/2021 
    *	@author         <i>Nguyen Vi Cao</i>	
    *	@parem		evt ActionEvent
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void deleteJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteJButtonActionPerformed
        //get the selected philosopher
        int index = currentID;
        Philosopher philosopherToDelete = searchPhilosopher(index);
        //determine if the user still wants to delte the person
        int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete "
            + philosopherToDelete.getName() + " " 
            +"?", "Delete philosopher", JOptionPane.YES_NO_OPTION);
        if(result == JOptionPane.YES_OPTION)
        {
            //remove the philosopher from the DB && update DB
            try
            {
                //make connection to MySQL DB
                String url = DB_URL;        //"jdbc:mysql://localhost:3306/javabook"
                String user = USER;         //"root"
                String password = PASS;     //"Caonguyenvi3132="
                //class.forName("com.mysql.cj.jdbc.Driver");        //not needed
                Connection conn = DriverManager.getConnection(url, user, password);
                Statement stmt = conn.createStatement();
                
                //set the person information
                String query = "DELETE FROM FamousPhilosophers WHERE currentID = ?";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setInt(1, index);
                //execute the preparedstatement
                pstmt.execute();
                
                //friends.remove(--index);
                //saveFile();
                //sizeOfDB;
                
                query = "SELECT * FROM FamousPhilosophers";
                ResultSet rs = stmt.executeQuery(query);
                rs.next();
                index = rs.getInt("currentID");
                
                displayPhilosophers();//searchPhilosopher(index)       //display first record
                conn.close();
            }
            catch(SQLException exp)
            {
                exp.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error Deleting.",
                        "Error!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_deleteJButtonActionPerformed

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *   Method          searchJMenuItemActionPerformed()
    *	Description     event handler for searchJMenuItem. It calls serachPhilosopher
    *                   method
    *	Date            1/27/2021
    *   History log     1/10/2021, 1/20/2021 
    *	@author         <i>Nguyen Vi Cao</i>	
    *	@parem		evt ActionEvent
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void searchJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchJMenuItemActionPerformed
        String philosopherName = JOptionPane.showInputDialog("Enter name of Philosopher");
        String philosopherPlace = JOptionPane.showInputDialog("Enter place of birth of Philosopher");
        String philosopherYear = JOptionPane.showInputDialog("Enter year of birth of Philosopher");
        try
        {
            //obtain connections constants from interface
            String url = DB_URL;        //"jdbc:mysql://localhost:3306/javabook"
            String user = USER;         //"root"
            String password = PASS;     //"Caonguyenvi3132="
            //class.forName("com.mysql.cj.jdbc.Driver");        //not needed
            Connection con = DriverManager.getConnection(url, user, password);
            //Statement stmt = con.createStatement();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            
            //retrieveing the data to get count on how many records are in the DB
            ResultSet rs = stmt.executeQuery("SELECT count(*) FROM FamousPhilosophers");
            rs.next();
            //moving cursor to the last row
            sizeOfDB = rs.getInt("count(*)");   //  =friends.size();
            
            String query = "SELECT * FROM FamousPhilosophers";
            rs = stmt.executeQuery(query);
            rs.next();      //move to first record
            
            int firstIndex = rs.getInt("philosopherID");
            Philosopher tempPhilosopher = searchPhilosopher(firstIndex);
        }
        catch(SQLException exp)
        {
            // Show error message
            JOptionPane.showMessageDialog(null, "Input error -- SQL error.",
                    "SQL Error!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_searchJMenuItemActionPerformed

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    * Method        addJButtonActionPerformed()
    * Description   event handler for adding a philosopher by invoking the AddPhilosopher 
    *               form. No empty or duplicate philosopher is added. The new philosopher is
    *               added to the JList and saved in the philosophers.txt file
    * @parem        evt--ActionEvent
    * @author       <i>Nguyen Vi Cao</i>
    * Date          5/12/2021
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void addJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addJButtonActionPerformed
        String message = "Philosopher not added.";
        try
        {
            AddPhilosopher myAddForm = new AddPhilosopher(this, true);
            myAddForm.setVisible(true);
            int lastIndex = 0;
            //get the new person
            Philosopher newPhilosopher = myAddForm.getPhilosopher();
            
            if(newPhilosopher != null && !exists(newPhilosopher))
            {
                //add newPhilosopher to arraylist and DB
                String url = DB_URL;        //"jdbc:mysql://localhost:3306/javabook"
                String user = USER;         //"root"
                String password = PASS;     //"Caonguyenvi3132="
                //class.forName("com.mysql.cj.jdbc.Driver");        //not needed
                Connection con = DriverManager.getConnection(url, user, password);
                Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
                String query = "SELECT * FROM FamousPhilosophers";
                
                ResultSet rs = stmt.executeQuery(query);
                rs.last();          //move to last record
                
                lastIndex = sizeOfDB;       //rs.getInt("personID"); //friends.size()+1;
                //newPhilosopher.setID(lastIndex + 1);
                
                stmt.executeUpdate(
                    "INSERT INTO FamousPhilosophers VALUES ('" + newPhilosopher.getName() + "','" +
                            newPhilosopher.getPlace() + "'," + newPhilosopher.getYear() +
                            ",'" + newPhilosopher.getWork()+ "','" + newPhilosopher.getSypnosis() +
                            "','" + newPhilosopher.getLink() + "')"
                );
                //friends.add(newPhilosopher);
                //currentID = ++sizeOfDB;
                displayPhilosophers();//newPhilosopher
                sizeOfDB++;
                con.close();
            }
            else
            {
                message = "Person not added";
                //newPhilosopher.setID(lastIndex);
                displayPhilosophers();//newPhilosopher
                throw new NullPointerException();
            }
        }
        catch(NullPointerException exp)
        {
            JOptionPane.showMessageDialog(null, message, "Input Error",
                    JOptionPane.WARNING_MESSAGE);            
        }
        catch(Exception exp)
        {
            exp.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error updating to database", 
                    "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_addJButtonActionPerformed

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    * Method        editJButtonActionPerformed()
    * Description   event handler for editJButton to edit selected philosopher
    * @parem        evt--ActionEvent
    * @author       <i>Nguyen Vi Cao</i>
    * Date          1/27/2021
    * History log   1/10/2021, 1/20/2021  
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void editJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editJButtonActionPerformed
        String message = "Philosopher not edited.";
        try
        {
            //currentID = Integer.parseInt(personNumberJLabel.getText());
            myPhilosopher = searchPhilosopher(currentID);
            
            //use the same Add form for editing
            EditPhilosopher myEditForm = new EditPhilosopher(myPhilosopher);
            myEditForm.setVisible(true);
            
            Philosopher editPhilosopher = myEditForm.getPhilosopher();
            //editPhilosopher.setID(currentID);
            if(editPhilosopher != null)  //&& !exists(editPhilosopher))
            {
                //update edited FamousPhilosophers to DB
                //myPhilosopher.setID(currentID);
                myPhilosopher.setName(editPhilosopher.getName());
                myPhilosopher.setPlace(editPhilosopher.getPlace());
                myPhilosopher.setYear(editPhilosopher.getYear());
                myPhilosopher.setWork(editPhilosopher.getWork());
                myPhilosopher.setSypnosis(editPhilosopher.getSypnosis());
                myPhilosopher.setLink(editPhilosopher.getLink());
                
                //make connection to FamousPhilosophers DB
                String url = DB_URL;        //"jdbc:mysql://localhost:3306/javabook"
                String user = USER;         //"root"
                String password = PASS;     //"Caonguyenvi3132="
                //class.forName("com.mysql.cj.jdbc.Driver");        //not needed
                Connection conn = DriverManager.getConnection(url, user, password);
                Statement stmt = conn.createStatement();
                //set query to update record
                String query = "UPDATE FamousPhilosophers SET Name = " + "'" + 
                        myPhilosopher.getName() + "', Place = '" + 
                        myPhilosopher.getPlace() + "', Year = " + 
                        myPhilosopher.getYear() + ", Major Work = '" + 
                        myPhilosopher.getWork() + "', Sypnosis = '" + 
                        myPhilosopher.getSypnosis() + "', Link = '" + 
                        myPhilosopher.getLink() + "' where Name = '" +
                        myPhilosopher.getName() + "'";
                stmt.executeUpdate(query);
                
                displayPhilosophers();//editPhilosopher
                stmt.close();
                conn.close();
            }
            else
            {
                JOptionPane.showMessageDialog(null, message,
                        "Error!", JOptionPane.ERROR_MESSAGE);
            }
        }
        catch(SQLException exp)
        {
            exp.printStackTrace();
            JOptionPane.showMessageDialog(null, message, 
                    "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_editJButtonActionPerformed

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    * Method        addJMenuItemActionPerformed()
    * Description   calls the addJButtonActionPerformed
    * @parem        evt--ActionEvent
    * @author       <i>Nguyen Vi Cao</i>
    * Date          1/27/2021
     * History log  1/10/2021, 1/20/2021   
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void addJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addJMenuItemActionPerformed
        addJButtonActionPerformed(evt);        
    }//GEN-LAST:event_addJMenuItemActionPerformed

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    * Method        editJMenuItemActionPerformed()
    * Description   calls the editJMenuItemActionPerformed
    * @parem        evt--ActionEvent
    * @author       <i>Nguyen Vi Cao</i>
    * Date          1/27/2021
    * History log  1/10/2021, 1/20/2021 
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void editJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editJMenuItemActionPerformed
        editJButtonActionPerformed(evt);
    }//GEN-LAST:event_editJMenuItemActionPerformed

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *   Method          deleteJMenuItemActionPerformed()
    *	Description     event handler for addJMenuItem to invoke the deleteJMenuItem
    *	Date            1/27/2021
    *   History log     1/10/2021, 1/20/2021   
    *	@author         <i>Nguyen Vi Cao</i>	
    *	@param		evt ActionEvent
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void deleteJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteJMenuItemActionPerformed
        deleteJButtonActionPerformed(evt);
    }//GEN-LAST:event_deleteJMenuItemActionPerformed

    private void sypnosisJTextFieldMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_sypnosisJTextFieldMouseWheelMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_sypnosisJTextFieldMouseWheelMoved
            
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *   Method          findPhilosopher()
    *	Description     Search for an philosopher by name and return the philosopher if found
    *	Date            1/27/2021
    *   History log     1/10/2021, 1/20/2021 
    *	@author         <i>Nguyen Vi Cao</i>	
    *	@parem		philosopherName String
    *   @return         Philosophers
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private Philosopher findPhilosopher(String philosopherName)
    {
        int index = -1;
        //make sure JList is sorted by name
        byNameJRadioButtonMenuItem.doClick();
        for (int i = 0; i < philosophers.size(); i++)
        {
            if (philosopherName.equals(philosophers.get(i).getName()))
                index = i;
        }
        if (index >= 0)
        {
            philosophersJList.setSelectedIndex(index);
            return philosophers.get(index);
        }
        else 
            return null;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *   Method          philosopherExists()
    *	Description     boolean method to determine if an philosopher to be added exists
    *	Date            1/27/2021
    *   History log     1/10/2021, 1/20/2021 
    *	@author         <i>Nguyen Vi Cao</i>	
    *	@param		metropolis Philosopher
    *   @return         thereIsOne boolean
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private boolean philosopherExists(Philosopher metropolis)
    {
        boolean thereIsOne = false;
        for(int index = 0; index < philosophers.size() && !thereIsOne; index++)
        {
            if (philosophers.get(index).equals(metropolis))
            thereIsOne = true;
        }
        return thereIsOne;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *   Method          searchPhilosopher()
    *	Description     Search for an philosopher by name and highlight if found
    *   @param          id int
    *	Date            1/8/2021
    *	@author         <i>Nguyen Vi Cao</i>	
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private Philosopher searchPhilosopher(int id)
    {
        try
        { 
            String url = DB_URL;        //"jdbc:mysql://localhost:3306/javabook"
            String user = USER;         //"root"
            String password = PASS;     //"Caonguyenvi3132="
            //class.forName("com.mysql.cj.jdbc.Driver");        //not needed
            Connection conn = DriverManager.getConnection(url, user, password);
            myPhilosopher = new Philosopher();        //create a new philosopher
            
            //set prepared statement query to search for philosopher by id
            String query = "SELECT * FROM FamousPhilosophers WHERE philosopherID = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet results = pstmt.executeQuery();
            results.next();             //move to first record
            myPhilosopher.setName(results.getString(1));
            myPhilosopher.setPlace(results.getString(2));
            myPhilosopher.setYear(results.getInt(3));
            myPhilosopher.setWork(results.getString(4));
            myPhilosopher.setSypnosis(results.getString(5));
            myPhilosopher.setLink(results.getString(6));
            
            results.close();    //close the result set
            pstmt.close();      //close the preared statement
            conn.close();       //close the connection
            //return the statement
            return myPhilosopher;
        }
        catch(SQLException exp)
        {
            //exp.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error searching database for philosopher", 
                    "Search Error", JOptionPane.ERROR_MESSAGE);
            return new Philosopher();
        }        
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *   Method          linearSearch()
    *	Description     event handler to search philosopher by name using the linear
    *                   serach algorithm and to display its index if found and 
    *                   -1 if not found
    *   @return         index int
    *   @param          philosopherArray String[]
    *   @param          philosopherName String
    *	Date            1/27/2021
    *   History log     1/10/2021, 1/20/2021 
    *	@author         <i>Nguyen Vi Cao</i>
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private int linearSearch (String[] philosopherArray, String philosopherName)
    {
        int index = -1;
        boolean found = false;
        for (int i = 0; i < philosopherArray.length && !found; i++)
        {
            if(philosopherArray[i].toLowerCase().contains(philosopherName.toLowerCase()))
            {
                index = i;
                found = true;
            }
        }
        return index;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *   Method          binarySearch()
    *	Description     search by philosopher name using the binary search method.
    *                   Returns -1 if philosopher is not found
    *   @param          array String[]
    *   @param          key String
    *   @return         middle int, index of where philosopher is found
    *	Date            1/27/2021
    *   History log     1/10/2021, 1/20/2021 
    *	@author         <i>Nguyen Vi Cao</i>
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static int binarySearch(String[] array, String key)
    {
        int low = 0;
        int high = array.length - 1;
        int middle;
        
        while (low <= high)
        {
            middle = (low + high) / 2;
            //if (key.equalsIgnoreCase(array([middle]))     //exact match
            if (array[middle].contains(key))                //partial match
                return middle;
            else if (key.compareToIgnoreCase(array[middle]) < 0)
                high = middle - 1;              //search low end of array
            else 
                low = middle + 1;               //search high end of array
        }
        return -1;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *   Method          savePhilosophers()
    *	Description     write philosopher to a text file that is comma delimited
    *   @parem          file String
    *	Date            1/27/2021
    *   History log     1/10/2021, 1/20/2021  
    *	@author         <i>Nguyen Vi Cao</i>	
    *	@see            java.io.FileWriter
    *   @see            java.io.PrintWriter
    *   @see            City
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void savePhilosophers (String file)
    {
        try
        {
            //writeToFile(fileName);
            FileWriter filePointer = new FileWriter (file, false);
            PrintWriter output = new PrintWriter (filePointer);
            for (int index = 0; index < philosophers.size(); index++)
            {
                Philosopher tempPhilosopher = philosophers.get(index);
                String line = tempPhilosopher.getName() + "|" + 
                       tempPhilosopher.getPlace() + "|" + tempPhilosopher.getYear() + 
                       "|" + tempPhilosopher.getWork() + "|" + tempPhilosopher.getSypnosis()
                       + "|" + tempPhilosopher.getLink();
                // don't add an extra blank line to end of file
                if (index == philosophers.size() - 1)
                    output.write(line);
                else
                    output.write(line + "\n");
            }
            output.close();
        }
        catch (IOException ex)
        {
            JOptionPane.showMessageDialog(null, "Philosopher not saved", "Save Error",
                    JOptionPane.WARNING_MESSAGE);
            philosophersJList.setVisible(true);
            philosophersJList.setSelectedIndex(0);
        }
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    * Method        main()
    * Description   Call the contructor to create an instance of the form
    * @param        args are the command line strings
    * @author       <i>Nguyen Vi Cao</i>
    * Date          1/27/2021
    * History log   1/10/2021, 1/20/2021 
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
    public static void main(String args[]) {
        //</editor-fold>
        Splash mySplash = new Splash(3000);
        mySplash.showSplash();
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FamousPhilosophers().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutJMenuItem;
    private javax.swing.JButton addJButton;
    private javax.swing.JMenuItem addJMenuItem;
    private javax.swing.JRadioButtonMenuItem byNameJRadioButtonMenuItem;
    private javax.swing.JRadioButtonMenuItem byYearJRadioButtonMenuItem;
    private javax.swing.JMenu databaseJMenu;
    private javax.swing.JButton deleteJButton;
    private javax.swing.JMenuItem deleteJMenuItem;
    private javax.swing.JPanel displayJPanel;
    private javax.swing.JButton editJButton;
    private javax.swing.JMenuItem editJMenuItem;
    private javax.swing.JButton exitJButton;
    private javax.swing.JMenuItem exitJMenuItem;
    private javax.swing.JMenu fileJMenu;
    private javax.swing.JMenu helpJMenu;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JLabel linkJLabel;
    private javax.swing.JTextField linkJTextField;
    private javax.swing.JLabel logoJLabel1;
    private javax.swing.JLabel nameJLabel;
    private javax.swing.JTextField nameJTextField;
    private javax.swing.JMenuItem newJMenuItem;
    private javax.swing.JList<String> philosophersJList;
    private javax.swing.JLabel placeJLabel;
    private javax.swing.JTextField placeJTextField;
    private javax.swing.JMenuItem printFormJMenuItem;
    private javax.swing.JMenuItem printPhilosopherJMenuItem;
    private javax.swing.JMenuItem searchJMenuItem;
    private javax.swing.ButtonGroup searchingJButtonGroup;
    private javax.swing.JMenu sortingJMenu;
    private javax.swing.JLabel synopsisJLabel;
    private javax.swing.JTextField sypnosisJTextField;
    private javax.swing.JLabel titleJLabel;
    private javax.swing.JPanel titleJPanel;
    private javax.swing.JLabel workJLabel;
    private javax.swing.JTextField workJTextField;
    private javax.swing.JLabel yearJLabel;
    private javax.swing.JTextField yearJTextField;
    // End of variables declaration//GEN-END:variables
}
