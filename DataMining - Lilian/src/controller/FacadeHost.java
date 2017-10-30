/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import javax.swing.JFrame;

/**
 *
 * @author evaristowb
 */
public class FacadeHost {
    private static Host host = new Host();

    /**
     * constructor default
     */
    private FacadeHost() {
    }

    /**
     * return the host
     * @return - host
     */
    public static Host getHost() {
        return host;
    }

    /**
     * sets the viewport
     * @param viewMain
     */
    public static void setViewMain(JFrame viewMain){
        host.setViewMain(viewMain);
    }

    /**
     * returns the viewport
     * @return viewmain
     */
    public static JFrame getViewMain(){
        return host.getViewMain();
    }

}
