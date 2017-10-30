/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces.mining.classify;

import interfaces.ModuleInterface;
import java.util.Collection;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * evaluation metric
 * @author evaristowb
 */
public interface EvaluationMetricModuleInterface extends ModuleInterface {
    
    /**
     * returns the configuration panel
     * @return
     */
    public JPanel getPainelConfig();

    /**
     * 
     * @param jDialogConfig
     */
    public void setJDialogConfig(JDialog jDialogConfig);

    /**
     * 
     * @param jTextArea
     */
    public void setTextArea(JTextArea jTextArea);

    /**
     * evaluation
     * @param confusionMatrix
     */
    public void evaluation(ConfusionMatrix[] confusionMatrix);
}
