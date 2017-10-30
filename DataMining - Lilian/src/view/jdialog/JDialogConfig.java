/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package view.jdialog;

import javax.swing.JPanel;

/**
 *
 * @author evaristowb
 */
public class JDialogConfig extends javax.swing.JDialog {

    /** Creates new form JDialogConfig */
    public JDialogConfig(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public void addPanel(JPanel jPanel){

//        GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
//        gridBagConstraints.gridx = 0;
//        gridBagConstraints.gridy = 0;
//        gridBagConstraints.weightx = 0.01;
//        gridBagConstraints.weighty = 0.01;
////        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
//        getContentPane().add(jPanel, new java.awt.GridBagConstraints());

        getContentPane().add(jPanel);
        //setSize(jPanel.getPreferredSize());

        pack();
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-getWidth())/2, (screenSize.height-getHeight())/2, getWidth(), getHeight());
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
    }
}
