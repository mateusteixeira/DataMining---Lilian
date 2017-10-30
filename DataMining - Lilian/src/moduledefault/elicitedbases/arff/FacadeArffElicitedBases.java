/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package moduledefault.elicitedbases.arff;

import interfaces.Base;
import moduledefault.elicitedbases.arff.view.JPanelArffElicitedBases;

/**
 *
 * @author evaristowb
 */
public class FacadeArffElicitedBases {

    /**
     * bases arff elicited
     */
    private static ArffElicitedBases arffElicitedBases;

    /**
     * panel bases elicited
     */
    private static JPanelArffElicitedBases jPanelArffElicitedBases;

    /**
     * constructor
     */
    private FacadeArffElicitedBases() {
    }

    /**
     * returns teh bases arff elicited
     * @return
     */
    public static ArffElicitedBases getArffElicitedBases() {
        return arffElicitedBases;
    }

    /**
     * sets the bases arff elicited 
     * @param arffElicitedBases
     */
    public static void setArffElicitedBases(ArffElicitedBases arffElicitedBases) {
        FacadeArffElicitedBases.arffElicitedBases = arffElicitedBases;
    }

    /**
     * returns the panel elicitation of bases ARFF
     * @return
     */
    public static JPanelArffElicitedBases getJPanelArffElicitedBases() {
        return jPanelArffElicitedBases;
    }

    /**
     * sets panel elicitation of bases ARFF
     * @param jPanelArffElicitedBases
     */
    public static void setJPanelArffElicitedBases(JPanelArffElicitedBases jPanelArffElicitedBases) {
        FacadeArffElicitedBases.jPanelArffElicitedBases = jPanelArffElicitedBases;
    }

    /**
     * add base
     * @param base
     */
    public static void addBase(Base base){
        arffElicitedBases.addBase(base);
    }
}
