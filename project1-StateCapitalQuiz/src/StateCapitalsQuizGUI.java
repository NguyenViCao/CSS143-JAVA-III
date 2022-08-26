package StateCapitalsQuiz;
import com.sun.glass.events.KeyEvent;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*<pre>
 * Class        StateCapitalsQuizGUI.java
 * Description  A class representing the GUI used in a state capital quiz 
 *              application.
 * Project      FamousArtistsGUI Quiz
 * Platform     jdk 1.8.0_241; NetBeans IDE 11.3; PC Windows 10
 * Course       CS 141
 * Hourse       8 hours and 45 minutes
 * Date         4/26/2021 
 * @author	<i>Nguyen Vi Cao</i>
 * @version 	%1% %2%
 * @see     	javax.swing.JFrame
 * @see        java.awt.Toolkit 
 *</pre>
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class StateCapitalsQuizGUI extends javax.swing.JFrame
{
    //class instance variables
    private final int MAX_STATES = 50;    //maximum number of questions
    private int numberOfQuestions;
    //data structures for state capitals, players, and hash map
    private BinarySearchTree playersTree = new BinarySearchTree();
    private List<Capital> stateCapitalsList = new LinkedList<Capital>();
    private Map<String,String> capitalsHashMap = new HashMap<String,String>();
    //private String[][] stateCapitals =;       //states descriptions
    
    //replace next line with read from external file
    private String fileName = "src/StateCapitalsQuiz/Capitals.txt";
    private String playersFileName = "src/StateCapitalsQuiz/Players_1.txt";
    
    //define DefaultListModel in order to add statesNamed directly into JList
    DefaultListModel<String> playersJListModel = new DefaultListModel<String>();
    //parallel boolean ArrayList tracks displayed capitals
    private ArrayList<Boolean> statesUsedArrayList = new ArrayList<Boolean>();
    private ArrayList<Integer> numbersArrayList = new ArrayList<Integer>();
    
    private int currentIndex;              //contains the index of current state
    private String correctCapital = "";    
    private int countCorrect = 0;          //number of correct answers
    private int numberOfStates = MAX_STATES;   //used in alternate unique number
    
    //tracks the number ofcapitals that have been displayed
    private int count = 0;
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Constructor  StateCapitalsQuizGUI()-default constructor
     * Description  Create an instance of the GUI form, set the default
     *              JButton to be submitJButton, set icon image, center form,
     *              read capitals from external file.
     * Date         4/26/2021   
     * @author      <i>Nguyen Vi Cao</i>
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/  
    public StateCapitalsQuizGUI()
    {
        initComponents();
        playerJList.setModel(playersJListModel);
        this.getRootPane().setDefaultButton(submitJButton);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("src/Images/State Capitals Small.png"));
        this.setLocationRelativeTo(null);
        readCapitals(fileName);
        readPlayers(playersFileName);
        playerJList.setSelectedIndex(0);
        
        nextJButton.setEnabled(false);
        playJButton.setEnabled(false);
        submitJButton.setEnabled(false);
        capitalJTextField.setEnabled(false);
        numberJFormattedTextField.requestFocus();
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method       readPlayers
     * Description  Reads players from a text file that is comma delimited and
     *              creates an instance of the Player class with the data read.
     *              Then the newly created player is added to the players database.
     *              Uses an object from the ReadFile class to read record.
     * @author      <i>Nguyen Vi Cao</i>
     * @param       playersFileName String
     * Date         3/9/2021
     * History log  3/9/2021
     * @see         java.util.Scanner
     *</pre>   
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void readPlayers (String playersFileName){
        int counter = 0;
        playersTree.removeAll();             //clear the ArrayList
        playersJListModel.clear();
        try 
        {
            //read while there is data
            File file = new File(playersFileName);
            Scanner fileScanner = new Scanner(file);
            String line = "";
            String playersName = "";         //read first line
            int age = 0; int correct = 0; int questions = 0;
            
            Player player = new Player();
            while(fileScanner.hasNextLine())
            {
                line = fileScanner.nextLine();
                StringTokenizer stringTokenizer = new StringTokenizer(line, ",");
                while (stringTokenizer.hasMoreElements())
                {
                    playersName = stringTokenizer.nextElement().toString();
                    playersJListModel.addElement(playersName);
                    age = Integer.parseInt(stringTokenizer.nextElement().toString());
                    correct = Integer.parseInt(stringTokenizer.nextElement().toString());
                    questions = Integer.parseInt(stringTokenizer.nextElement().toString());
                    player = new Player(playersName, age, correct, questions);
                }
                //add player to arraylist
                playersTree.insertNode(player);
            }
            playerJList.setSelectedIndex(0);
            fileScanner.close();
            playerJList.setCellRenderer(new DefaultListCellRenderer());
            playerJList.setVisible(true);
        }
        catch(FileNotFoundException e)
        {
            JOptionPane.showMessageDialog(null, playersFileName + " does not exist",
                    "File input Error", JOptionPane.WARNING_MESSAGE);
            //bring up JFileChooser to select file in current directory
            JFileChooser chooser = new JFileChooser("src/StateCapitalsQuiz");
            //filter only txt file
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Txt Files", "txt");
            chooser.setFileFilter(filter);
            int choice = chooser.showOpenDialog(null);
            if (choice == JFileChooser.APPROVE_OPTION)
            {
                File chosenFile = chooser.getSelectedFile();
                playersFileName = "src/StateCapitalsQuiz/" + chosenFile.getName();
                //playersFileName = playersFileName;
                //System.out.println("file = " + playersFileName);
                readPlayers(playersFileName);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Unable to read file",
                        "File input error", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       readCapitals
     * Description  Reads capitals from a file Capitals.txt (and fill artistsJComboBox).  
     * Date         4/26/2021 
     * @author      <i>Nguyen Vi Cao</i>
     * @param       fileName String
     * @see         java.io.File
     * @see         java.util.Scanner
     *</pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void readCapitals(String fileName)
    {   
        int counter = 0;
        numbersArrayList.clear();
        statesUsedArrayList.clear();

        try
        {
            File file = new File(fileName);
            Scanner fileScanner = new Scanner(file);
            String line = ""; 
            String state = ""; String city = ""; int year = 0; double area = 0;
            int pop = 0; int rank = 0;
            Capital capital = new Capital();
            
            while(fileScanner.hasNextLine())
            {
                line = fileScanner.nextLine();
                StringTokenizer stringTokenizer = new StringTokenizer(line, ",");
                while (stringTokenizer.hasMoreElements())
                {
                    state = stringTokenizer.nextElement().toString();
                    city = stringTokenizer.nextElement().toString();
                    capitalsHashMap.put(state, city);
                    
                    year = Integer.parseInt(stringTokenizer.nextElement().toString());
                    area = Double.parseDouble(stringTokenizer.nextElement().toString());
                    pop = Integer.parseInt(stringTokenizer.nextElement().toString());
                    rank = Integer.parseInt(stringTokenizer.nextElement().toString());
                    capital = new Capital(state, city, year, area, pop, rank);
                }
                stateCapitalsList.add(capital);
                numbersArrayList.add(counter);
                statesUsedArrayList.add(false);
                counter++;
            }
            fileScanner.close();
        }
        catch(FileNotFoundException exp)
        {
            JOptionPane.showMessageDialog(null, fileName + "does not exist",
                    "File Input Error", JOptionPane.WARNING_MESSAGE);
            JFileChooser chooser = new JFileChooser("src/StateCapitalsQuiz");
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Txt Files", "txt");
            chooser.setFileFilter(filter);
            int choice = chooser.showOpenDialog(null);
            if (choice == JFileChooser.APPROVE_OPTION)
            {
                File choosenFile = chooser.getSelectedFile();
                fileName = "src/StateCapitalsQuiz/" + choosenFile.getName();
                //System.out.println("file = " + fileName);
                readCapitals(fileName);
            }
            else
            {
                System.exit(0);
            }
        }
        catch(IOException exp)
        {
            JOptionPane.showMessageDialog(null, "Unable to read file", 
                    "File Input Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       displayCapital
     * Description  Choose a random and unused capital and display it in the 
     *              capitalJLabel.
     * Date         4/26/2021 
     * @author      <i>Nguyen Vi Cao</i>
     *</pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void displayCapital()
    {
        currentIndex = getUniqueAlternate();
        String selectedState = stateCapitalsList.get(currentIndex).getState();
        
        correctCapital = capitalsHashMap.get(selectedState).toString();
        stateJLabel.setText(selectedState);
        String capitalPath = "src/Images/" + correctCapital + ".jpg";
        
        capitalJLabel.setIcon(new ImageIcon(capitalPath));
        capitalJLabel.setToolTipText(correctCapital + "," + selectedState);
    } 
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       getUniqueRandomNumber
     * Description  Return an unused random number by a blind repetition of
     *              random generation and checking for unused artist
     * Date         4/26/2021 
     * @return      random int
     * @author      <i>Nguyen Vi Cao</i>
     *</pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private int getUniqueRandomNumber()
    {
        Random generator = new Random();
        int randomNumber = 0;
        do
        {
            randomNumber = generator.nextInt(capitalsHashMap.size());
        }
        while (statesUsedArrayList.get(randomNumber));
        statesUsedArrayList.set(randomNumber, true);
        return randomNumber;
    } 
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       getUniqueAlternate
     * Description  A better way to select unique and unused random integer 
     *              for unused sign
     * Date         4/26/2021 
     * @return      random int
     * @author      <i>Nguyen Vi Cao</i>
     *</pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private int getUniqueAlternate()
    {
        int randomNumber = 0;
        Random rand = new Random();
        randomNumber = rand.nextInt(numberOfStates);
        int uniqueRandomNumber = numbersArrayList.get(randomNumber);
        
        numbersArrayList.set(randomNumber, numberOfStates - 1);
        numberOfStates--;
        return uniqueRandomNumber;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        capitalJLabel = new javax.swing.JLabel();
        controlJPanel = new javax.swing.JPanel();
        submitJButton = new javax.swing.JButton();
        nextJButton = new javax.swing.JButton();
        playJButton = new javax.swing.JButton();
        questionJLabel = new javax.swing.JLabel();
        enterJLabel = new javax.swing.JLabel();
        capitalJTextField = new javax.swing.JTextField();
        playerJScrollPane = new javax.swing.JScrollPane();
        playerJList = new javax.swing.JList<>();
        resultJLabel = new javax.swing.JLabel();
        stateJLabel = new javax.swing.JLabel();
        numberJFormattedTextField = new javax.swing.JFormattedTextField();
        artistsJMenuBar = new javax.swing.JMenuBar();
        fileJMenu = new javax.swing.JMenu();
        newJMenuItem = new javax.swing.JMenuItem();
        clearJMenuItem = new javax.swing.JMenuItem();
        printJMenuItem = new javax.swing.JMenuItem();
        printPlayerJMenuItem = new javax.swing.JMenuItem();
        fileJSeparator = new javax.swing.JPopupMenu.Separator();
        exitJMenuItem = new javax.swing.JMenuItem();
        dataJMenu = new javax.swing.JMenu();
        addJMenuItem = new javax.swing.JMenuItem();
        editJMenuItem = new javax.swing.JMenuItem();
        deleteJMenuItem = new javax.swing.JMenuItem();
        searchJMenuItem = new javax.swing.JMenuItem();
        playerJMenuItem = new javax.swing.JMenuItem();
        helpJMenu = new javax.swing.JMenu();
        aboutJMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("State Capital Quiz");
        setResizable(false);

        capitalJLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Charleston.jpg"))); // NOI18N
        capitalJLabel.setFocusable(false);

        controlJPanel.setLayout(new java.awt.GridLayout(1, 3, 3, 5));

        submitJButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        submitJButton.setMnemonic('S');
        submitJButton.setText("Submit");
        submitJButton.setToolTipText("Click to submit your answer");
        submitJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitJButtonActionPerformed(evt);
            }
        });
        controlJPanel.add(submitJButton);

        nextJButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        nextJButton.setMnemonic('N');
        nextJButton.setText("Next State");
        nextJButton.setToolTipText("Click to see next sign");
        nextJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextJButtonActionPerformed(evt);
            }
        });
        controlJPanel.add(nextJButton);

        playJButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        playJButton.setMnemonic('P');
        playJButton.setText("Play Again");
        playJButton.setToolTipText("Play all over again!");
        playJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playJButtonActionPerformed(evt);
            }
        });
        controlJPanel.add(playJButton);

        questionJLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        questionJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        questionJLabel.setText("Number Of Questions:");

        enterJLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        enterJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        enterJLabel.setText("Enter Name of Capital:");

        capitalJTextField.setBackground(new java.awt.Color(240, 240, 240));
        capitalJTextField.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        capitalJTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        playerJList.setBackground(new java.awt.Color(240, 240, 240));
        playerJList.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Players", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13), new java.awt.Color(255, 51, 51))); // NOI18N
        playerJList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                playerJListValueChanged(evt);
            }
        });
        playerJScrollPane.setViewportView(playerJList);

        resultJLabel.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        resultJLabel.setForeground(new java.awt.Color(0, 153, 153));
        resultJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        stateJLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        stateJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        numberJFormattedTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numberJFormattedTextFieldActionPerformed(evt);
            }
        });
        numberJFormattedTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                numberJFormattedTextFieldKeyTyped(evt);
            }
        });

        fileJMenu.setMnemonic('F');
        fileJMenu.setText("File");

        newJMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        newJMenuItem.setMnemonic('N');
        newJMenuItem.setText("New");
        newJMenuItem.setToolTipText("Open a new set of art works");
        newJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newJMenuItemActionPerformed(evt);
            }
        });
        fileJMenu.add(newJMenuItem);

        clearJMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        clearJMenuItem.setMnemonic('C');
        clearJMenuItem.setText("Clear");
        clearJMenuItem.setToolTipText("Clear player, start a new quiz");
        clearJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearJMenuItemActionPerformed(evt);
            }
        });
        fileJMenu.add(clearJMenuItem);

        printJMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        printJMenuItem.setMnemonic('P');
        printJMenuItem.setText("Print Form");
        printJMenuItem.setToolTipText("Print Form as GUI");
        printJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printJMenuItemActionPerformed(evt);
            }
        });
        fileJMenu.add(printJMenuItem);

        printPlayerJMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        printPlayerJMenuItem.setMnemonic('r');
        printPlayerJMenuItem.setText("Print Player");
        printPlayerJMenuItem.setToolTipText("Print the selected Player");
        printPlayerJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printPlayerJMenuItemActionPerformed(evt);
            }
        });
        fileJMenu.add(printPlayerJMenuItem);
        fileJMenu.add(fileJSeparator);

        exitJMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        exitJMenuItem.setMnemonic('X');
        exitJMenuItem.setText("Exit");
        exitJMenuItem.setToolTipText("End application");
        exitJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitJMenuItemActionPerformed(evt);
            }
        });
        fileJMenu.add(exitJMenuItem);

        artistsJMenuBar.add(fileJMenu);

        dataJMenu.setMnemonic('D');
        dataJMenu.setText("Database Operations");
        dataJMenu.setToolTipText("Add, Edit, Delete, Search and other operations");

        addJMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        addJMenuItem.setMnemonic('A');
        addJMenuItem.setText("Add Player");
        addJMenuItem.setToolTipText("Add new player");
        addJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addJMenuItemActionPerformed(evt);
            }
        });
        dataJMenu.add(addJMenuItem);

        editJMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        editJMenuItem.setMnemonic('E');
        editJMenuItem.setText("Edit Player");
        editJMenuItem.setToolTipText("Edit current Player");
        editJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editJMenuItemActionPerformed(evt);
            }
        });
        dataJMenu.add(editJMenuItem);

        deleteJMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        deleteJMenuItem.setMnemonic('t');
        deleteJMenuItem.setText("Delete Player");
        deleteJMenuItem.setToolTipText("Delete selected player");
        deleteJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteJMenuItemActionPerformed(evt);
            }
        });
        dataJMenu.add(deleteJMenuItem);

        searchJMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        searchJMenuItem.setMnemonic('s');
        searchJMenuItem.setText("Search Player");
        searchJMenuItem.setToolTipText("Search for Player");
        searchJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchJMenuItemActionPerformed(evt);
            }
        });
        dataJMenu.add(searchJMenuItem);

        playerJMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        playerJMenuItem.setMnemonic('d');
        playerJMenuItem.setText("Player Detail");
        playerJMenuItem.setToolTipText("Show Player Detail");
        playerJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playerJMenuItemActionPerformed(evt);
            }
        });
        dataJMenu.add(playerJMenuItem);

        artistsJMenuBar.add(dataJMenu);

        helpJMenu.setText("Help");

        aboutJMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        aboutJMenuItem.setMnemonic('A');
        aboutJMenuItem.setText("About");
        aboutJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutJMenuItemActionPerformed(evt);
            }
        });
        helpJMenu.add(aboutJMenuItem);

        artistsJMenuBar.add(helpJMenu);

        setJMenuBar(artistsJMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(capitalJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(controlJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(capitalJTextField)
                    .addComponent(resultJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(playerJScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(questionJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(numberJFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(enterJLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(stateJLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 13, Short.MAX_VALUE)
                        .addComponent(capitalJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(controlJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(questionJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(numberJFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stateJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(enterJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(capitalJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(resultJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(playerJScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       submitJButtonActionPerformed
     * Description  Event handler to check if the user's answer is correct. The
     *              correct answer is held in class instance variable 
     *              currentIndex.
     * Date         4/26/2021 
     * @param       evt ActionEvent
     * @author      <i>Nguyen Vi Cao</i>
     *</pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void submitJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitJButtonActionPerformed
        count++;
        numberJFormattedTextField.setEnabled(false);
        resultJLabel.setText("");
        String answer = capitalJTextField.getText().trim().toLowerCase();
        String selectedState = stateCapitalsList.get(currentIndex).getState();
        correctCapital = capitalsHashMap.get(selectedState).toString();
        if (answer.equalsIgnoreCase(correctCapital))
        {
            countCorrect++;
            resultJLabel.setText("Correct! " + countCorrect + "/" + count);
        }
        else
        {
            resultJLabel.setText("Incorrect! " + countCorrect + "/" + count);
        }
        if (count == numberOfQuestions)
        {
            resultJLabel.setText("Quiz Score: " + countCorrect + "/" + numberOfQuestions + " Correct!");
            submitJButton.setEnabled(false);
            nextJButton.setEnabled(false);
            playJButton.setEnabled(true);
            playJButton.requestFocus();
            searchJMenuItem.setEnabled(true);
            numberJFormattedTextField.setText("");
        }
        else
        {
            submitJButton.setEnabled(false);
            nextJButton.setEnabled(true);
            nextJButton.requestFocus();
            playJButton.setEnabled(false);
            searchJMenuItem.setEnabled(false);
            capitalJTextField.setText("");
        }
    }//GEN-LAST:event_submitJButtonActionPerformed
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       nextJButtonActionPerformed
     * Description  Event handler to select next unused sign randomly by 
     *              calling the displayArtist() method.
     * Date         4/26/2021 
     * @param       evt ActionEvent
     * @author      <i>Nguyen Vi Cao</i>
     *</pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void nextJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextJButtonActionPerformed
        displayCapital();
        resultJLabel.setText("");
        submitJButton.setEnabled(true);
        nextJButton.setEnabled(false);
    }//GEN-LAST:event_nextJButtonActionPerformed
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       playJButtonActionPerformed
     * Description  Event handler to start the game all over again.
     * Date         4/26/2021 
     * @param       evt ActionEvent
     * @author      <i>Nguyen Vi Cao</i>
     *</pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void playJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playJButtonActionPerformed
        // Start game all over
       countCorrect = 0;
       count = 0;
       numberJFormattedTextField.setText("");
       numberJFormattedTextField.setEnabled(true);
       numberJFormattedTextField.requestFocus();
       stateJLabel.setText("");
       capitalJTextField.setText("");
       resultJLabel.setText("");
       submitJButton.setEnabled(false);
       nextJButton.setEnabled(false);
       playJButton.setEnabled(false);
       searchJMenuItem.setEnabled(false);
       capitalJTextField.setEnabled(false);
       playerJList.setEnabled(true);
       numberOfStates = stateCapitalsList.size();
       for (int i = 0; i < stateCapitalsList.size(); i++)
       {
           statesUsedArrayList.set(i, false);
           numbersArrayList.set(i, i);
       }
       savePlayers(playersFileName);
    }//GEN-LAST:event_playJButtonActionPerformed
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       openJMenuItemActionPerformed
     * Description  Event handler to chose a separate file of artists.
     * Date         4/26/2021 
     * @param       evt ActionEvent
     * @author      <i>Nguyen Vi Cao</i>
     *</pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void newJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newJMenuItemActionPerformed
        JFileChooser fileJFileChooser = new JFileChooser("src/StateCapitalsQuiz");
        FileNameExtensionFilter filter = new  FileNameExtensionFilter(
            "Txt Files", "txt");
        fileJFileChooser.setFileFilter(filter);
        int returnVal = fileJFileChooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION)
        {
            File file = fileJFileChooser.getSelectedFile();
            playersFileName = file.getName();
            statesUsedArrayList.clear();
            numbersArrayList.clear();
            
            readPlayers("src/StateCapitalsQuiz/" + playersFileName);
            clearJMenuItemActionPerformed(evt);
        }
        else
        {
            System.out.println("File access cancelled by user");
        }
    }//GEN-LAST:event_newJMenuItemActionPerformed
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       aboutJMenuItemActionPerformed()
     * Description  Create an About form and show it. 
     * Date         4/26/2021   
    *</pre>
     * @param       evt java.awt.event.ActionEvent
     * @author      <i>Nguyen Vi Cao</i>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void aboutJMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_aboutJMenuItemActionPerformed
    {//GEN-HEADEREND:event_aboutJMenuItemActionPerformed
        About aboutWindow = new About(this, true);
        aboutWindow.setVisible(true);
    }//GEN-LAST:event_aboutJMenuItemActionPerformed
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       printJMenuItemActionPerformed()
     * Description  Event handler to print the for as a GUI. Calls the
     *              PrintUtilities class printComponent method.
     * @author      <i>Nguyen Vi Cao</i>
     * Date         4/26/2021      
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void printJMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_printJMenuItemActionPerformed
    {//GEN-HEADEREND:event_printJMenuItemActionPerformed
        PrintUtilities.printComponent(this);
    }//GEN-LAST:event_printJMenuItemActionPerformed
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       clearJMenuItemActionPerformed()
     * Description  Event handler to clear the form and start anew. Calls the
     *              playJButtonActionPerformed event handler.
     * @author      <i>Nguyen Vi Cao</i>
     * Date         4/26/2021      
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void clearJMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_clearJMenuItemActionPerformed
    {//GEN-HEADEREND:event_clearJMenuItemActionPerformed
        playJButtonActionPerformed(evt);
    }//GEN-LAST:event_clearJMenuItemActionPerformed
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       exitJMenuItemActionPerformed()
     * Description  Event handler to end the application.
     * @author      <i>Nguyen Vi Cao</i>
     * Date         4/26/2021      
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void exitJMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_exitJMenuItemActionPerformed
    {//GEN-HEADEREND:event_exitJMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitJMenuItemActionPerformed
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       addJMenuItemActionPerformed()
     * Description  Event handler to add new artist do the DB.
     * @author      <i>Nguyen Vi Cao</i>
     * Date         4/26/2021   
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @SuppressWarnings("unchecked")
    private void addJMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_addJMenuItemActionPerformed
    {//GEN-HEADEREND:event_addJMenuItemActionPerformed
        try
        {
            AddPlayer playerDialog = new AddPlayer();
            playerDialog.setVisible(true);
            
            Player newPlayer = playerDialog.getPlayer();
            if (newPlayer != null && !(playersTree.contains(newPlayer)))
            {
                playersTree.insertNode(newPlayer);
                playerJList.setSelectedValue(newPlayer.getName(), true);
                savePlayers(playersFileName);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Player " + 
                        newPlayer.getName() + " exist!",
                        "Not Saved", JOptionPane.INFORMATION_MESSAGE);
                playerJList.setVisible(true);
                playerJList.setSelectedIndex(0);
            }
        }
        catch (NullPointerException exp)
        {
            JOptionPane.showMessageDialog(null, "Player not Added",
                    "Input Error", JOptionPane.WARNING_MESSAGE);
            playerJList.setVisible(true);
            playerJList.setSelectedIndex(0);
        }
    }//GEN-LAST:event_addJMenuItemActionPerformed

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       savePlayers()
     * Description  Write player to a text file that is pipe (,) delimited.
     * @param       playersfile String
     * @author      <i>Nguyen Vi Cao</i>
     * Date         4/26/2021   
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void savePlayers(String playersfile)
    {
        try
        {
            FileWriter filePointer = new FileWriter(playersfile, false);
            PrintWriter writeFile = new PrintWriter(filePointer, false);
            BinarySearchTreeNode root = playersTree.getRoot();//get root of BinarySearchTree
            
            //initialize buffer
            playersTree.setBuffer(new StringBuilder());
            playersTree.buildBuffer(root);
            
            //delete the last '\n'
            String buffer = playersTree.getBuffer().substring(0, 
                    playersTree.getBuffer().length() - 1);
            //System.out.println("In Save Buffer = " + buffer);
            writeFile.print(buffer);        //write to file
            writeFile.close();
        }
        catch (IOException ex)
        {
            JOptionPane.showMessageDialog(this, "Unable to write to file",
                    "Write File Error", JOptionPane.ERROR_MESSAGE);
        }
    }
        
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       searchPlayer()
     * Description  Search for player by name and return node that contains it.
     * @param       name String
     * @return      Player node BinarySearchTreeNode
     * @author      <i>Nguyen Vi Cao</i>
     * Date         4/26/2021  
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private BinarySearchTreeNode searchPlayer(String name)
    {
        return playersTree.nodeWith(name, playersTree.getRoot());           
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       editJMenuItemActionPerformed()
     * Description  Edit selected artist.
     * @param       evt java.awt.event.ActionEvent
     * @author      <i>Nguyen Vi Cao</i>
     * Date         4/26/2021 
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void editJMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_editJMenuItemActionPerformed
    {//GEN-HEADEREND:event_editJMenuItemActionPerformed
        try
        {
            //get name of selected player
            String playerName = playerJList.getSelectedValue().toString();
            //remove population if edit mode is on sorted operas by population
            if (playerName.contains("'"))
                playerName = playerName.substring(0, playerName.indexOf(','));
            
            //create a temp player to populate fields of form
            Player playerToEdit = new Player(searchPlayer(playerName).data);
            int index = playerJList.getSelectedIndex();
            
            //pass player info to EditPlayer constructor and view Edit form
            EditPlayer editedPlayer = new EditPlayer(playerToEdit);
            editedPlayer.setVisible(true);
            
            //get edited player and add to array list
            if (editedPlayer.getPlayer() != null)
            {
                //remove old player from ArrayList
                playerJList.remove(index);
                
                //add edited player to BST
                playersTree.insertNode(editedPlayer.getPlayer());
                
                //save new player list to file and display new player in JList
                savePlayers(playersFileName);
            }
        }
        catch (NullPointerException nullex)
        {
            JOptionPane.showMessageDialog(null, "Player not edited",
                    "Input Error", JOptionPane.WARNING_MESSAGE);
            playerJList.setVisible(true);
            playerJList.setSelectedIndex(0);
        }
    }//GEN-LAST:event_editJMenuItemActionPerformed

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       deleteJButtonActionPerformed()
     * Description  Delete an existing artist with confirmation dialog
     * @param       evt java.awt.event.ActionEvent
     * @author      <i>Nguyen Vi Cao</i>
     * Date         4/26/2021 
     *</pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void deleteJMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_deleteJMenuItemActionPerformed
    {//GEN-HEADEREND:event_deleteJMenuItemActionPerformed
        String playerName = playerJList.getSelectedValue();
//        int commaIndex = playerName.indexOf(',');
//        if (commaIndex > -1)
//            playerName = playerName.substring(0, commaIndex);
        int result = JOptionPane.showConfirmDialog(null, 
                "Are you sure you want to delete " + playerName + "?", "Delete Player",
                JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if (result == JOptionPane.OK_OPTION)
        {
            Player playerToBeRemoved = searchPlayer(playerName).data;
            playersTree.remove(playerToBeRemoved);
            savePlayers(playersFileName);
        }
        playerJList.setSelectedIndex(0);
    }//GEN-LAST:event_deleteJMenuItemActionPerformed
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       numberJFormattedTextFieldActionPerformed()
     * Description  declare a constant for a limit number of question.
     * @param       evt java.awt.event.ActionEvent
     * @author      <i>Nguyen Vi Cao</i>
     * Date         4/26/2021 
     *</pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void numberJFormattedTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numberJFormattedTextFieldActionPerformed
        try
        {
            if(!Validation.isInteger(numberJFormattedTextField.getText(), 1, MAX_STATES))
                throw new NumberFormatException();
            numberOfQuestions = Integer.parseInt(numberJFormattedTextField.getText());
            if(numberOfQuestions < 1 || numberOfQuestions > MAX_STATES)
                throw new NumberFormatException();
            numberJFormattedTextField.setEnabled(false);
            capitalJTextField.setEnabled(true);
            submitJButton.setEnabled(true);
            displayCapital();
            capitalJTextField.requestFocus();
            playerJList.setEnabled(false);
        }
        catch(NumberFormatException exp)
        {
            JOptionPane.showMessageDialog(null, "Input must be an integer in " + 
                    "the range [1, " + MAX_STATES + " ].", "Input Error",
                    JOptionPane.WARNING_MESSAGE);
            numberJFormattedTextField.requestFocus();
            numberJFormattedTextField.selectAll();
        }
    }//GEN-LAST:event_numberJFormattedTextFieldActionPerformed
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       numberJFormattedTextFieldKeyTyped()
     * Description  allow only digits, backspace and delete key.
     * @param       evt java.awt.event.ActionEvent
     * @author      <i>Nguyen Vi Cao</i>
     * Date         4/26/2021 
     *</pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void numberJFormattedTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_numberJFormattedTextFieldKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c)) || c == KeyEvent.VK_BACKSPACE || c == KeyEvent.VK_DELETE)
        {
            evt.consume();
            //numberJFormattedTextFieldActionPerformed(evt);
        }
    }//GEN-LAST:event_numberJFormattedTextFieldKeyTyped
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *   Method          searchJMenuItemActionPerformed()
    *	Description     event handler for searchJMenuItem. It calls searchPlayer 
    *                   method
    *	Date            4/26/2021
    *	@author         <i>Nguyen Vi Cao</i>	
    *	@parem		evt ActionEvent
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void searchJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchJMenuItemActionPerformed
        String playerName = JOptionPane.showInputDialog(null, 
                "Enter name of player: ", "Search Player by Name", 
                JOptionPane.INFORMATION_MESSAGE);
        Player aPlayer = searchPlayer(playerName).data;
        if (aPlayer != null)
            playerJList.setSelectedValue(aPlayer.getName(), true);
        else
            JOptionPane.showConfirmDialog(null, playerName +
                    " is not in the Database!", "Player Not Found",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_searchJMenuItemActionPerformed
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    * Method        detailsJMenuItemActionPerformed()
    * Description   Creates and shows the playerDetail form
    * Date          4/26/2021
    * @author       <i>Nguyen Vi Cao</i>	
    * @param        evt java.awt.event.ActionEvent
    * @see          java.awt.event.ActionEvent
    * @see          java.text.DecimalFormat
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void playerJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playerJMenuItemActionPerformed
        int index = playerJList.getSelectedIndex();
        if(index >= 0)
        {
            String playerName = playerJList.getSelectedValue();
            BinarySearchTreeNode playerNode = searchPlayer(playerName);
            if(playerNode != null && playerNode.data != null)
            {
                PlayerDetails playerQuote = new PlayerDetails(playerNode.data);
                playerQuote.setVisible(true);
            }
        }
    }//GEN-LAST:event_playerJMenuItemActionPerformed
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    * Method        playerJListValueChanged()
    * Description   reset form to original state by calling the clearJMenuItem
    * Date          4/26/2021
    * @author       <i>Nguyen Vi Cao</i>	
    * @param        evt java.awt.event.ActionEvent
    * @see          java.awt.event.ActionEvent
    * @see          java.text.DecimalFormat
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void playerJListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_playerJListValueChanged
        numberJFormattedTextField.setText("");
        numberJFormattedTextField.requestFocus();
    }//GEN-LAST:event_playerJListValueChanged
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method          printPlayerJMenuItemActionPerformed()
    *	Description     Print the selected player.
    *	Date            4/26/2021
    *	@author         <i>Nguyen Vi Cao</i>	
    *	@param		evt java.awt.event.ActionEvent
    *   @see            java.awt.event.ActionEvent
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void printPlayerJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printPlayerJMenuItemActionPerformed
        int index = playerJList.getSelectedIndex();
        PrintUtilities.printComponent(this);
    }//GEN-LAST:event_printPlayerJMenuItemActionPerformed
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
     * Method       main()
     * Description  Displays splash screen and the main RoadSign GUI form.
     * Date         4/26/2021 
     * @param       args are the command line strings
     * @author      <i>Nguyen Vi Cao</i>
     *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static void main(String args[])
    {
        // Show splash screen
        Splash mySplash = new Splash(5000);     // duration = 4 seconds
        mySplash.showSplash();                  // show splash screen
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run() 
            {
                StateCapitalsQuizGUI flagQuiz = new StateCapitalsQuizGUI();                
                flagQuiz.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutJMenuItem;
    private javax.swing.JMenuItem addJMenuItem;
    private javax.swing.JMenuBar artistsJMenuBar;
    private javax.swing.JLabel capitalJLabel;
    private javax.swing.JTextField capitalJTextField;
    private javax.swing.JMenuItem clearJMenuItem;
    private javax.swing.JPanel controlJPanel;
    private javax.swing.JMenu dataJMenu;
    private javax.swing.JMenuItem deleteJMenuItem;
    private javax.swing.JMenuItem editJMenuItem;
    private javax.swing.JLabel enterJLabel;
    private javax.swing.JMenuItem exitJMenuItem;
    private javax.swing.JMenu fileJMenu;
    private javax.swing.JPopupMenu.Separator fileJSeparator;
    private javax.swing.JMenu helpJMenu;
    private javax.swing.JMenuItem newJMenuItem;
    private javax.swing.JButton nextJButton;
    private javax.swing.JFormattedTextField numberJFormattedTextField;
    private javax.swing.JButton playJButton;
    private javax.swing.JList<String> playerJList;
    private javax.swing.JMenuItem playerJMenuItem;
    private javax.swing.JScrollPane playerJScrollPane;
    private javax.swing.JMenuItem printJMenuItem;
    private javax.swing.JMenuItem printPlayerJMenuItem;
    private javax.swing.JLabel questionJLabel;
    private javax.swing.JLabel resultJLabel;
    private javax.swing.JMenuItem searchJMenuItem;
    private javax.swing.JLabel stateJLabel;
    private javax.swing.JButton submitJButton;
    // End of variables declaration//GEN-END:variables
}