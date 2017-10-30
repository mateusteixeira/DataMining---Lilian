/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces.util;

/**
 *
 * @author rafael
 */
public interface WaitingDialogInterface {
    public void begin(int nMaximo);
    public void processing(int valor);
    public void end();
    public void visible();
}
