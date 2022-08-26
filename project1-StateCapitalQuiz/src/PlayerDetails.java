
package StateCapitalsQuiz;

import java.awt.Toolkit;
import java.text.DecimalFormat;

/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*<pre>
* @author           <i>Nguyen Vi Cao</i>
* Project Number    3
* Due date          3/16/2021
*</pre>
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*<pre>
 Class         PlayerDetails
 File          PlayerDetails.java
 Description   Information about the Player
* @author       <i>Nguyen Vi Cao</i>
* Environment   PC, Windows 10, jdk1.8.0_241, NetBeans 11.3
* Date          3/16/2021
* @version      1.0.0
* History Log:  3/16/2021
*</pre>
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class PlayerDetails extends javax.swing.JFrame {
    private Player myPlayer;
    @SuppressWarnings("OverridableMethodCallInConstructor")
    
/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*<pre>
* Constructor      About()--default constructor
* Description      Create a GUI form and displayed it centered and set
*                  CloseJButton as default.
* @author          <i>Nguyen Vi Cao</i>
* Date             11/30/2020
* @param           parent java.awt.Frame
* @param           modal boolean
*</pre>
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public PlayerDetails(java.awt.Frame parent, boolean modal) 
    {
        initComponents();
        // center the form at start
        this.setLocationRelativeTo(null);
        this.setIconImage(Toolkit.getDefaultToolkit().
                getImage("src/StateCapitalsQuiz/State Capitals Small.png"));
        // set CloseJButton as default
        this.getRootPane().setDefaultButton(CloseJButton);
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Constructor     Quote()-default constructor
     * Description     create an instance of the GUI form, display all information
     *                 of selected player
     * @param          aPlayer Player
     * @author         <i>Nguyen Vi Cao</i>
     * Date            3/9/2021
     * History log     3/9/2021
     *</pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public PlayerDetails(Player aPlayer)
    {
        myPlayer = new Player (aPlayer);
        String name = myPlayer.getName();
        this.setTitle("Details of " + name);
        displayInfo();
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Constructor     displayInfo()-default constructor
     * Description     display info of player
     * @author         <i>Nguyen Vi Cao</i>
     * Date            3/9/2021
     * History log     3/9/2021
     *</pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void displayInfo()
    {
        String output = "Player: " + myPlayer.getName() + 
                "\nAge of player: " + myPlayer.getAge() + "\n" + 
                "Number of total questions: " + myPlayer.getTotalQuestion() + "\n"
                + "Number of Correct: " + myPlayer.getNoCorrect();
        quoteJTextArea.setText(output);
        quoteJTextArea.setCaretPosition(0);
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        AboutJLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        quoteJTextArea = new javax.swing.JTextArea();
        CloseJButton = new javax.swing.JButton();
        CopyrightJLabel = new javax.swing.JLabel();
        CopyrightJLabel1 = new javax.swing.JLabel();
        AuthorJLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Player Details");
        setResizable(false);

        AboutJLabel.setFont(new java.awt.Font("Tempus Sans ITC", 2, 36)); // NOI18N
        AboutJLabel.setForeground(new java.awt.Color(51, 0, 0));
        AboutJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AboutJLabel.setText("Player Detail");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Project3/person-icon.png"))); // NOI18N

        quoteJTextArea.setEditable(false);
        quoteJTextArea.setColumns(20);
        quoteJTextArea.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        quoteJTextArea.setLineWrap(true);
        quoteJTextArea.setRows(5);
        quoteJTextArea.setWrapStyleWord(true);
        jScrollPane1.setViewportView(quoteJTextArea);

        CloseJButton.setBackground(new java.awt.Color(153, 255, 153));
        CloseJButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        CloseJButton.setMnemonic('c');
        CloseJButton.setText("Close");
        CloseJButton.setToolTipText("Close the About Form");
        CloseJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseJButtonActionPerformed(evt);
            }
        });

        CopyrightJLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        CopyrightJLabel.setText("Copyright: Free");

        CopyrightJLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        CopyrightJLabel1.setText("Version 3.1.1");

        AuthorJLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        AuthorJLabel1.setText("Date: 3/16/2021");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CopyrightJLabel)
                            .addComponent(CopyrightJLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                        .addComponent(CloseJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(AuthorJLabel1)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(AboutJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(AboutJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CloseJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(CopyrightJLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(CopyrightJLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(AuthorJLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    * Method        CloseJButtonActionPerformed()
    * Description   Closes the About form only
    * Date          3/16/2021
    * History Log   3/16/2021
    * @author       <i>Nguyen Vi Cao</i>	
    * @param        evt java.awt.event.ActionEvent
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void CloseJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseJButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_CloseJButtonActionPerformed

    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PlayerDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PlayerDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PlayerDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PlayerDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PlayerDetails dialog = new PlayerDetails(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AboutJLabel;
    private javax.swing.JLabel AuthorJLabel1;
    private javax.swing.JButton CloseJButton;
    private javax.swing.JLabel CopyrightJLabel;
    private javax.swing.JLabel CopyrightJLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea quoteJTextArea;
    // End of variables declaration//GEN-END:variables
}
