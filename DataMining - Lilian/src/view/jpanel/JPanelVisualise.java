package view.jpanel;

import controller.FacadeHost;
import controller.Host;
import interfaces.Base;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class JPanelVisualise extends javax.swing.JPanel implements Observer{


    private final ArrayList<Base> arrayListBases = new ArrayList<>();
     
    public JPanelVisualise() {
        FacadeHost.getHost().addObserver(this);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object o1) {
        if (o instanceof Host) {
            if ((o1 instanceof Base) && ((Base) o1).hasMeta()) {
                arrayListBases.add((Base) o1);
            }
        }
    }

}
