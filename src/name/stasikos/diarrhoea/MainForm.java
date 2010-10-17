/*
 *  Copyright (C) 2010 Stanislav Kogut (stasikos@gmail.com)
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * MainForm.java
 *
 * Created on Oct 17, 2010, 6:02:30 PM
 */
package name.stasikos.diarrhoea;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;

/**
 *
 * @author Stanislav Kogut (stasikos@gmail.com)
 */
public class MainForm extends javax.swing.JFrame implements TimedActionListener, DocumentListener {

    private int targetWords;
    private int targetTime;
    private TargetsDialog dialog;
    private WriterTimer timer;
    private boolean targetReached = false;
    private int currentCount = 0;
    private Thread timerThread;
    private SecureTextArea textArea;

    /** Creates new form MainForm */
    public MainForm() {
        Preferences prefs = Preferences.userNodeForPackage(MainForm.class);
        targetWords = prefs.getInt("targetWords", 50);
        targetTime = prefs.getInt("targetTime", 5);
        initComponents();
        textArea = new SecureTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setRows(15);
        mainPanel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        this.pack();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        toolBar = new javax.swing.JToolBar();
        btTargets = new javax.swing.JButton();
        btStart = new javax.swing.JButton();
        btSave = new javax.swing.JButton();
        statusBar = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lbCurrCount = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lbTargetCount = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbWordRamaining = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        progressBar = new javax.swing.JProgressBar();
        lbTimeLeft = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Writer's Diarrhoea");
        setFocusCycleRoot(false);

        mainPanel.setName("mainPanel"); // NOI18N
        mainPanel.setLayout(new java.awt.BorderLayout());
        getContentPane().add(mainPanel, java.awt.BorderLayout.CENTER);

        toolBar.setFloatable(false);
        toolBar.setRollover(true);
        toolBar.setName("toolBar"); // NOI18N

        btTargets.setText("Set targets");
        btTargets.setFocusable(false);
        btTargets.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btTargets.setName("btTargets"); // NOI18N
        btTargets.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btTargets.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTargetsActionPerformed(evt);
            }
        });
        toolBar.add(btTargets);

        btStart.setText("Start");
        btStart.setFocusable(false);
        btStart.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btStart.setName("btStart"); // NOI18N
        btStart.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btStartActionPerformed(evt);
            }
        });
        toolBar.add(btStart);

        btSave.setText("Save");
        btSave.setFocusable(false);
        btSave.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btSave.setName("btSave"); // NOI18N
        btSave.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(btSave);

        getContentPane().add(toolBar, java.awt.BorderLayout.NORTH);

        statusBar.setName("statusBar"); // NOI18N
        statusBar.setLayout(new java.awt.BorderLayout());

        jPanel1.setName("jPanel1"); // NOI18N

        jLabel1.setText("Current:");
        jLabel1.setName("jLabel1"); // NOI18N
        jPanel1.add(jLabel1);

        lbCurrCount.setText("0");
        lbCurrCount.setBorder(null);
        lbCurrCount.setName("lbCurrCount"); // NOI18N
        jPanel1.add(lbCurrCount);

        jLabel2.setText("Target:");
        jLabel2.setName("jLabel2"); // NOI18N
        jPanel1.add(jLabel2);

        lbTargetCount.setText(Integer.toString(targetWords));
        lbTargetCount.setBorder(null);
        lbTargetCount.setName("lbTargetCount"); // NOI18N
        jPanel1.add(lbTargetCount);

        jLabel3.setText("Remaining:");
        jLabel3.setName("jLabel3"); // NOI18N
        jPanel1.add(jLabel3);

        lbWordRamaining.setText(Integer.toString(targetWords - currentCount));
        lbWordRamaining.setBorder(null);
        lbWordRamaining.setName("lbWordRamaining"); // NOI18N
        jPanel1.add(lbWordRamaining);

        statusBar.add(jPanel1, java.awt.BorderLayout.WEST);

        jPanel2.setName("jPanel2"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N
        jPanel2.add(progressBar);

        lbTimeLeft.setText(String.format("%d:%02d", targetTime, 0));
        lbTimeLeft.setName("lbTimeLeft"); // NOI18N
        jPanel2.add(lbTimeLeft);

        statusBar.add(jPanel2, java.awt.BorderLayout.EAST);

        getContentPane().add(statusBar, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btTargetsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTargetsActionPerformed
        dialog = new TargetsDialog(this, true, targetTime, targetWords);
        dialog.setVisible(true);
        if (!dialog.isCanceled()) {
            targetWords = dialog.getWordCount();
            targetTime = dialog.getTimeLimit();
            Preferences prefs = Preferences.userNodeForPackage(MainForm.class);
            prefs.putInt("targetWords", targetWords);
            prefs.putInt("targetTime", targetTime);
            lbTargetCount.setText(Integer.toString(targetWords));
            lbWordRamaining.setText(Integer.toString(targetWords - currentCount));
            lbTimeLeft.setText(String.format("%d:%02d", targetTime, 0));
        }
    }//GEN-LAST:event_btTargetsActionPerformed

    private void btStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btStartActionPerformed
        if (textArea.getDocument().getLength() > 0) {
            // Warn user about document
        }
        textArea.setEditable(true);
        textArea.setCopyPasteDisabled(true);
        textArea.getDocument().addDocumentListener(this);
        btStart.setEnabled(false);
        btSave.setEnabled(false);
        timer = new WriterTimer(this, targetTime * 60);
        timerThread = new Thread(timer);
        timerThread.start();
        timer.start();
        progressBar.getModel().setMaximum(targetTime * 60);
        progressBar.getModel().setValue(0);
        textArea.setCaret(new DisabledCaret());
        textArea.requestFocusInWindow();
    }//GEN-LAST:event_btStartActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btSave;
    private javax.swing.JButton btStart;
    private javax.swing.JButton btTargets;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbCurrCount;
    private javax.swing.JLabel lbTargetCount;
    private javax.swing.JLabel lbTimeLeft;
    private javax.swing.JLabel lbWordRamaining;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JPanel statusBar;
    private javax.swing.JToolBar toolBar;
    // End of variables declaration//GEN-END:variables

    public void deadLineReached() {
        if (!targetReached) {
            textArea.setEditable(false);
            textArea.setCopyPasteDisabled(false);
        }
        textArea.getDocument().removeDocumentListener(this);
        textArea.setCaret(new DefaultCaret());
        btStart.setEnabled(true);
        btSave.setEnabled(true);
    }

    public void insertUpdate(DocumentEvent e) {
        checkTarget();
    }

    public void removeUpdate(DocumentEvent e) {
        checkTarget();
    }

    public void changedUpdate(DocumentEvent e) {
        checkTarget();
    }

    private void checkTarget() {
        try {
            String doc = textArea.getDocument().getText(0, textArea.getDocument().getLength());
            currentCount = doc.split("\\W+").length;
            lbCurrCount.setText(Integer.toString(currentCount));
            lbWordRamaining.setText(Integer.toString(targetWords - currentCount));
            if (targetWords - currentCount <= 0) {
                btSave.setEnabled(true);
                targetReached = true;
                textArea.setCaret(new DefaultCaret());
            } else {
                btSave.setEnabled(false);
                targetReached = false;
                textArea.setCaret(new DisabledCaret());
            }
        } catch (BadLocationException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void tick() {
        final int timeM = timer.getTime() / 60;
        final int timeS = timer.getTime() % 60;
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                lbTimeLeft.setText(String.format("%d:%02d", timeM, timeS));
                progressBar.getModel().setValue(timer.getTime());
            }
        });

    }
}
