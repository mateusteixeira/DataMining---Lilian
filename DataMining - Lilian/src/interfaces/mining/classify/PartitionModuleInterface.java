/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces.mining.classify;

import interfaces.Base;
import interfaces.ModuleInterface;
import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 *
 * @author evaristowb
 */
public interface PartitionModuleInterface extends ModuleInterface {

    /**
     * returns the painel configuration
     * @return
     */
    public JPanel getPainelConfig();

    /**
     * partition
     * @param base
     * @return
     */
    public BaseClassify[] partition(Base base);

    /**
     * sets the painel configuration
     * @param jDialogConfig
     */
    public void setJDialogConfig(JDialog jDialogConfig);
}
