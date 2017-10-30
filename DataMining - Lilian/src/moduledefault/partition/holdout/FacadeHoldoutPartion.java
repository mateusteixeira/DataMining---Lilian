/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package moduledefault.partition.holdout;

import moduledefault.evaluationmetric.accuracy.*;
import javax.swing.JDialog;

/**
 *
 * @author evaristowb
 */
public class FacadeHoldoutPartion {
    private static HoldoutPartionModule holdoutPartionModule;

    public FacadeHoldoutPartion() {
    }

    public static HoldoutPartionModule getHoudoutPartionModule() {
        return holdoutPartionModule;
    }

    public static void setHoudoutPartionModule(HoldoutPartionModule holdoutPartionModule) {
        FacadeHoldoutPartion.holdoutPartionModule = holdoutPartionModule;
    }

    /**
     * returns the frame configuration
     * @return
     */
    public static JDialog getJDialogConfig(){
        return holdoutPartionModule.getJDialogConfig();
    }

    /**
     * returns the split
     * @return
     */
    public static double getSplit(){
        return holdoutPartionModule.getSplit();
    }

    /**
     * sets the split
     * @param split
     */
    public static void setSplit(double split){
        holdoutPartionModule.setSplit(split);
    }
}
