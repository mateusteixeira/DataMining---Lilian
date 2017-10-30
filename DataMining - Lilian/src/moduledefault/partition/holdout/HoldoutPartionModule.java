/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package moduledefault.partition.holdout;

import annotations.ModuleAnnotation;
import annotations.PartitionModuleAnnotation;
import interfaces.Base;
import interfaces.HostInterface;
import interfaces.mining.classify.BaseClassify;
import interfaces.mining.classify.PartitionModuleInterface;
import java.util.ArrayList;
import java.util.Observable;
import javax.swing.JDialog;
import javax.swing.JPanel;
import moduledefault.partition.holdout.view.JPanelHoldoutConfig;

@ModuleAnnotation(name="Holdout")
@PartitionModuleAnnotation
public class HoldoutPartionModule extends Observable implements PartitionModuleInterface {
    private HostInterface host = null;
    private JDialog jDialogConfig = null;
    private double split = 0.666;

    public HoldoutPartionModule() {
        FacadeHoldoutPartion.setHoudoutPartionModule(this);
    }

    /**
     * returns the painel configuration
     * @return
     */
    public JPanel getPainelConfig() {
        return new JPanelHoldoutConfig();
    }

    /**
     * sets the host
     * @param hi
     */
    public void setHost(HostInterface hi) {
        host = hi;
    }

    /**
     *sets the painel configuration
     * @param jd
     */
    public void setJDialogConfig(JDialog jd) {
        jDialogConfig = jd;
    }

    /**
     * returns the painel configuration
     * @return
     */
    public JDialog getJDialogConfig(){
        return jDialogConfig;
    }

    /**
     * returns the split
     * @return
     */
    public double getSplit() {
        return split;
    }

    /**
     * sets the split
     * @param split
     */
    public void setSplit(double split) {
        this.split = split;
    }


    /**
     * partitioning base
     * @param base - base to be partitioned
     * @return partitioned base
     */
    public BaseClassify[] partition(Base base) {
        ArrayList<Object[]> arrayListInputTrain = new ArrayList<Object[]>();
        ArrayList<Object[]> arrayListInputTest = new ArrayList<Object[]>();
        for(int i=0;i<base.getInput().length;i++){
            if(i<base.getInput().length*split)
                arrayListInputTrain.add(base.getInput()[i]);
            else
                arrayListInputTest.add(base.getInput()[i]);
        }

        ArrayList<Object> arrayListOutputTrain = new ArrayList<Object>();
        ArrayList<Object> arrayListOutputTest = new ArrayList<Object>();
        for(int i=0;i<base.getOutput().length;i++){
            if(i<base.getOutput().length*split)
                arrayListOutputTrain.add(base.getOutput()[i]);
            else
                arrayListOutputTest.add(base.getOutput()[i]);
        }

        Base baseTrain = new Base();
        baseTrain.setAtributes(base.getAtributes());
        baseTrain.setInput((Object[][])arrayListInputTrain.toArray(new Object[0][0]));
        baseTrain.setOutput(arrayListOutputTrain.toArray());
        Base baseTest = new Base();
        baseTest.setAtributes(base.getAtributes());
        baseTest.setInput((Object[][])arrayListInputTest.toArray(new Object[0][0]));
        baseTest.setOutput(arrayListOutputTest.toArray());

        BaseClassify baseClassify[] = new BaseClassify[1];
        baseClassify[0] = new BaseClassify();
        baseClassify[0].setTest(baseTest);
        baseClassify[0].setTrain(baseTrain);

        return baseClassify;
    }
}
