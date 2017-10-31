package interfaces.mining.classify;

import interfaces.Base;
import interfaces.mining.MinerModuleInterface;
import java.io.Serializable;
import java.util.Collection;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public interface ClassifierModuleInterface extends MinerModuleInterface, Cloneable, Serializable {

    /**
     * returns the configuration panel
     * @return Jpanel - configuration panel
     */
    public JPanel getPainelConfig();

    /**
     * sets the painel configuration
     * @param jDialogConfig
     */
    public void setJDialogConfig(JDialog jDialogConfig);

    /**
     * sets the text output area
     * @param jTextArea
     */
    public void setTextArea(JTextArea jTextArea);

    /**
     * returns the text area
     * @return
     */
    public JTextArea getJTextArea();

    /**
     * 
     * @return
     */
    public JPanel getCreatedModel();
    //public void train(Base base);
//    public Base test(Base base);

    /**
     * train the classification algorithm
     * @param input - data to classify
     * @param output - data classe (labels)
     */
    public void train(Object[][] input, Object[] output);

    /**
     * test the classification algorithm
     * @param inputTest - input object
     * @return - object
     */
    public Object test(Object[] inputTest);

    /**
     * 
     * @return
     */
    public ClassifierModuleInterface clone();

    /**
     * 
     * @param attributes
     * @param classes
     */
    public void createInstanceClissify(String[] attributes,Collection classes);
}
