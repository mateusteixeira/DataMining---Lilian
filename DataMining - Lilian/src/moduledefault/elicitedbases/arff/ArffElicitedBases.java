/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package moduledefault.elicitedbases.arff;

import moduledefault.elicitedbases.arff.view.JPanelArffElicitedBases;
import annotations.ElicitedBasesModuleAnnotation;
import annotations.ModuleAnnotation;
import interfaces.Base;
import interfaces.HostInterface;
import interfaces.preprocess.ElicitedBasesModuleInterface;
import javax.swing.JPanel;

@ModuleAnnotation(name="Bases Arquivo Arff")
@ElicitedBasesModuleAnnotation
public class ArffElicitedBases implements ElicitedBasesModuleInterface{
    HostInterface hostInterface = null;
    JPanelArffElicitedBases jPanelArffElicitedBases;

    /**
     * constructor
     */
    public ArffElicitedBases() {
        FacadeArffElicitedBases.setArffElicitedBases(this);
        jPanelArffElicitedBases = new JPanelArffElicitedBases();
        FacadeArffElicitedBases.setJPanelArffElicitedBases(jPanelArffElicitedBases);
    }

    /**
     * returns the panel main
     * @return jPanelArffElicitedBases
     */
    public JPanel getMainPanel() {
        return jPanelArffElicitedBases;
    }

    /**
     * sets the host
     * @param hi
     */
    public void setHost(HostInterface hi) {
        hostInterface = hi;
    }

    /**
     * add base
     * @param base - base to be added
     */
    public void addBase(Base base){
        hostInterface.addBase(base);
    }
    
}
