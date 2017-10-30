package moduledefault.elicitedbases.arff.view;

import controller.FacadeHost;
import interfaces.Base;
import java.io.File;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import moduledefault.elicitedbases.arff.ArffFileReader;
import moduledefault.elicitedbases.arff.FacadeArffElicitedBases;

public class JPanelArffElicitedBases extends javax.swing.JPanel {

    private ArffFileReader fr;
    private String name = null;

    public JPanelArffElicitedBases() {
        initComponents();
        interactiveDoubleList.setTitleA("Colunas Disponíveis");
        interactiveDoubleList.setTitleB("Colunas Selecionadas");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jButtonOpenFile = new javax.swing.JButton();
        jPanelContent = new javax.swing.JPanel();
        interactiveDoubleList = new moduledefault.elicitedbases.arff.view.InteractiveDoubleList();
        jButtonAddBase = new javax.swing.JButton();
        jButtonVizualizeData = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        jButtonOpenFile.setText("Abrir arquivo ARFF...");
        jButtonOpenFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOpenFileActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 29, 3, 0);
        add(jButtonOpenFile, gridBagConstraints);

        jPanelContent.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelContent.add(interactiveDoubleList, gridBagConstraints);

        jButtonAddBase.setText("Adicionar Base");
        jButtonAddBase.setEnabled(false);
        jButtonAddBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddBaseActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelContent.add(jButtonAddBase, gridBagConstraints);

        jButtonVizualizeData.setText("Visualizar Dados");
        jButtonVizualizeData.setEnabled(false);
        jButtonVizualizeData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVizualizeDataActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.8;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelContent.add(jButtonVizualizeData, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jPanelContent, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonOpenFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOpenFileActionPerformed
        getArffFile();
    }//GEN-LAST:event_jButtonOpenFileActionPerformed

    private void jButtonAddBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddBaseActionPerformed
        addBase();
        JOptionPane.showMessageDialog(null, "Base de Dados carregada com sucesso!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
//        Object[][] dados = getData(InteractiveDoubleList1.getListModelB().toArray());
//        for (int i= 0; i < dados.length; i++) {
//            for (int j= 0; j < dados[i].length; j++) {
//                System.out.print(dados[i][j] + " | ");
//            }
//            System.out.println("");
//        }
//        System.out.println("------------------------------------------------------");
    }//GEN-LAST:event_jButtonAddBaseActionPerformed

    private void jButtonVizualizeDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVizualizeDataActionPerformed
        // TODO add your handling code here:
        String[] atributos = new String[interactiveDoubleList.getListModelB().size()];
        for (int j = 0; j < interactiveDoubleList.getListModelB().size(); j++) {
            atributos[j] = fr.getAttributes().get(fr.getAttributes().indexOf(interactiveDoubleList.getListModelB().get(j)));
        }

        Object[][] data = new Object[fr.getData().length][interactiveDoubleList.getListModelB().size()];
        for (int i = 0; i < fr.getData().length; i++) {
            for (int j = 0; j < interactiveDoubleList.getListModelB().size(); j++) {
                data[i][j] = fr.getData()[i][fr.getAttributes().indexOf(interactiveDoubleList.getListModelB().get(j))];
            }
        }
        new JDialogData(FacadeHost.getViewMain(), true, data, atributos).setVisible(true);
        
    }//GEN-LAST:event_jButtonVizualizeDataActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private moduledefault.elicitedbases.arff.view.InteractiveDoubleList interactiveDoubleList;
    private javax.swing.JButton jButtonAddBase;
    private javax.swing.JButton jButtonOpenFile;
    private javax.swing.JButton jButtonVizualizeData;
    private javax.swing.JPanel jPanelContent;
    // End of variables declaration//GEN-END:variables


    /**
     * selected arff file
     */
    private void getArffFile() {
        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(false);
        int returnVal = chooser.showOpenDialog(this.getParent());
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String s = chooser.getSelectedFile().getName();
            int i = s.lastIndexOf('.');
            String ext = "";
            if (i > 0 && i < s.length() - 1) {
                ext = s.substring(i + 1).toLowerCase();
            }
            if (!ext.equals("arff")) {
                JOptionPane.showMessageDialog(this.getParent(),
                        "Arquivo selecionado não é do tipo ARFF.\nTente novamente.",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
                getArffFile();
            } else {
                File file = chooser.getSelectedFile();                
                fr = new ArffFileReader(file);
                name = file.getName();
                loadList(fr.getAttributes());
                //loadData(fr.getAttributes(), fr.getData());
                //jButtonExcluir.setEnabled(true);
                jButtonAddBase.setEnabled(true);
                jButtonVizualizeData.setEnabled(true);
            }
        }
    }

    /**
     * load list
     * @param attributes - attributes list
     */
    private void loadList(List<String> attributes) {
        DefaultListModel dl = new DefaultListModel();
        for (int i = 0; i < attributes.size(); i++) {
            dl.addElement(attributes.get(i));
            //System.out.println(attributes[i]);
        }
        interactiveDoubleList.setListModelB(dl);
        interactiveDoubleList.setListModelA(new DefaultListModel());

    }

//    private void loadData(String[] attributes, Object[][] data) {
//        jTableDados.setModel(new javax.swing.table.DefaultTableModel(data, attributes));
//
//        jTableDadosGeral.setModel(new javax.swing.table.DefaultTableModel(data, attributes));
//    }
//    private void removeRows(int[] selectedRows) {
//        for (int i = selectedRows.length - 1; i >= 0; i--) {
//            ((DefaultTableModel) jTableDados.getModel()).removeRow(selectedRows[i]);
//        }
//    }
//    public Object[][] getData(Object[] toArray) {
//        Object[][] dados = new Object[jTableDados.getRowCount()][toArray.length];
//        for (int j = 0; j < toArray.length; j++) {
//            int coluna = jTableDados.getColumn(toArray[j]).getModelIndex();
//            for (int i = 0; i < jTableDados.getRowCount(); i++) {
//                dados[i][j] = jTableDados.getValueAt(i, coluna);
//            }
//        }
//        return dados;
//    }
//    public Object[][] getData() {
//        Object[][] dados = new Object[jTableDados.getRowCount()][jTableDados.getColumnCount()];
//        for (int i = 0; i < jTableDados.getRowCount(); i++) {
//            for (int j = 0; j < jTableDados.getColumnCount(); j++) {
//                dados[i][j] = jTableDados.getValueAt(i, j);
//            }
//        }
//        return dados;
//    }

    /**
     * returns the input
     * @return input
     */
    public Object[][] getInput() {
        DefaultListModel dlm = interactiveDoubleList.getListModelB();
        Object[][] input = new Object[fr.getData().length][dlm.size()-1];
        for (int i = 0; i < fr.getData().length; i++) {
            for (int j = 0; j < dlm.size() - 1; j++) {
                input[i][j] = fr.getData()[i][fr.getAttributes().indexOf(dlm.get(j))];
            }
        }
        return input;
//        Object[][] dados = new Object[jTableDados.getRowCount()][jTableDados.getColumnCount() - 1];
//        for (int i = 0; i < jTableDados.getRowCount(); i++) {
//            for (int j = 0; j < jTableDados.getColumnCount() - 1; j++) {
//                dados[i][j] = jTableDados.getValueAt(i, j);
//            }
//        }
//        return dados;
    }

    /**
     * returns the output
     * @return output
     */
    public Object[] getOutput() {
        DefaultListModel dlm = interactiveDoubleList.getListModelB();
        Object[] output = new Object[fr.getData().length];
        for (int i = 0; i < fr.getData().length; i++) {
            output[i] = fr.getData()[i][fr.getAttributes().indexOf(dlm.get(dlm.size()-1))];
        }
        return output;

//        Object[] dados = new Object[jTableDados.getRowCount()];
//        for (int i = 0; i < jTableDados.getRowCount(); i++) {
//            dados[i] = jTableDados.getValueAt(i, jTableDados.getColumnCount() - 1);
//        }
//        return dados;
    }

    /**
     * add base
     */
    private void addBase() {
        Base base = new Base();
        base.setName(name);
        base.setAtributes(interactiveDoubleList.getSelectedClasses());
        base.setInput(getInput());
        base.setOutput(getOutput());
        FacadeArffElicitedBases.addBase(base);
    }
}
