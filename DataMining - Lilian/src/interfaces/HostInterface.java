/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces;

import interfaces.util.WaitingDialogInterface;

/**
 *
 * @author evaristowb
 */
public interface HostInterface {

    /**
     * add base
     * @param base - base to be added
     */
    public void addBase(Base base);

    /**
     * awaits dialogue
     * @param b
     * @param msg
     * @return
     */
    public WaitingDialogInterface getWaitingDialog(boolean b, String msg);
}
