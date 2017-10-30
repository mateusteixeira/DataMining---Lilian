/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import interfaces.Base;
import interfaces.HostInterface;
import interfaces.ModuleInterface;
import interfaces.util.WaitingDialogInterface;
import java.util.ArrayList;
import java.util.Observable;
import javax.swing.JFrame;
import view.jdialog.Espera;

/**
 *
 * @author evaristowb
 */
public class Host extends Observable implements HostInterface {

    private ArrayList<Base> arrayListBaseSupervised = new ArrayList<Base>();
    private JFrame viewMain = null;

    /**
     * add database
     * @param base - database
     */
    public void addBase(Base base) {
        if (base.hasMeta()) {
            arrayListBaseSupervised.add(base);
        }
        setChanged();
        notifyObservers(base);
    }

    /**
     * add module
     * @param o
     */
    public void addModule(Object o) {
//        if(ModuleInterface.class.isAssignableFrom(o.getClass())){
        if (o instanceof ModuleInterface) {
            setChanged();
            notifyObservers(o);
        }
    }

    /**
     * waits for loading data
     * @param bln
     * @param string
     * @return
     */
    public WaitingDialogInterface getWaitingDialog(boolean bln, String string) {
        return new Espera(viewMain, bln, string);
    }

    /**
     * return frame viewing
     * @return viewMain
     */
    public JFrame getViewMain() {
        return viewMain;
    }

    /**
     * sets frame viewing
     * @param viewMain
     */
    public void setViewMain(JFrame viewMain) {
        this.viewMain = viewMain;
    }

    /**
     * checks for database
     * @return
     */
    public boolean hasBaseSupervised() {
        return !arrayListBaseSupervised.isEmpty();
    }
}
