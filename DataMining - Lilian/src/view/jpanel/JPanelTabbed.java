package view.jpanel;

import controller.FacadeHost;
import controller.Host;
import interfaces.Base;
import java.awt.Component;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JTabbedPane;


public class JPanelTabbed extends javax.swing.JPanel implements Observer {


    public static final int JPANELPREPROCESS = 0;
    public static final int JPANELCLASSIFY = 1;
    public static final int JPANELCLUSTER = 2;

    public JPanelTabbed() {
        initComponents();
        FacadeHost.getHost().addObserver(this);

        jTabbedPane1.addTab("Pré-Processamento", new JPanelPreprocess());

        jTabbedPane1.addTab("Classificação", new JPanelClassify());
        setEnabledAt(JPANELCLASSIFY, false);

        jTabbedPane1.addTab("Clustering", new JPanelClustering());
        setEnabledAt(JPANELCLUSTER, false);
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();

        jTabbedPane1.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        jTabbedPane2.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 761, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 761, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents
//
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    // End of variables declaration//GEN-END:variables

    public void setEnabledAt(int i, boolean a) {
        jTabbedPane1.setEnabledAt(i, a);
    }

    
    public void setAba(int index, Component jPanel) {
        jTabbedPane1.setComponentAt(index, (Component) jPanel);
    }

    
    public JTabbedPane getJTabbedPane1() {
        return jTabbedPane1;
    }

    
    public Component getAba(int index) {
        return jTabbedPane1.getComponentAt(index);
    }

    
    public Component getSelectedConponent() {
        return jTabbedPane1.getSelectedComponent();
    }

  
    public void update(Observable o, Object o1) {
        if (o instanceof Host) {
            Host h = (Host) o;
            if ((o1 instanceof Base) && ((Base) o1).hasMeta()) {
                setEnabledAt(JPANELCLASSIFY, false);
                setEnabledAt(JPANELCLUSTER, verificaBase((Base) o1));
                revalidate();
            }
        }
    }

    //Verfifica se a base é composta somente por atributos numéricos
    public boolean verificaBase(Base b) {
        double teste = 0;
        boolean base = true;
        for (int i = 0; i < b.getInput().length; i++) {
            for (int j = 0; j < b.getInput()[0].length; j++) {
                try {
                    teste = Double.valueOf(b.getInput()[i][j] + "").doubleValue();
                } catch (NumberFormatException e) {
                    base = false;
                }
            }
        }
        return base;
    }
}
