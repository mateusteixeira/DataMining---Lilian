/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moduledefault.evaluationmetric.precision;

import annotations.EvaluationMetricModuleAnnotation;
import annotations.ModuleAnnotation;
import interfaces.HostInterface;
import interfaces.mining.classify.ConfusionMatrix;
import interfaces.mining.classify.EvaluationMetricModuleInterface;
import java.text.DecimalFormat;
import java.util.Observable;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author evaristowb
 */
@ModuleAnnotation(name = "Precisão")
@EvaluationMetricModuleAnnotation
public class PrecisionEvaluationMetricModule extends Observable implements EvaluationMetricModuleInterface {

    /**
     * host
     */
    private HostInterface host = null;

    /**
     * frame configuration
     */
    private JDialog jDialogConfig = null;

    /**
     * text area
     */
    private JTextArea jTextArea = null;

    
    public JPanel getPainelConfig() {
        return null;
    }

    /**
     * sets the host
     * @param hi
     */
    public void setHost(HostInterface hi) {
        host = hi;
    }

    /**
     * sets the frame configuration
     * @param jd
     */
    public void setJDialogConfig(JDialog jd) {
        jDialogConfig = jd;
    }

    /**
     * returns the frame configuration
     * @return
     */
    public JDialog getJDialogConfig() {
        return jDialogConfig;
    }

    /**
     * sets the text area
     * @param jTextArea
     */
    public void setTextArea(JTextArea jTextArea) {
        this.jTextArea = jTextArea;
    }

    /**
     * evaluation
     * diagonal / tudo
     * @return
     */
    public void evaluation(ConfusionMatrix[] confusionMatrix) {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(10);
        df.setMinimumFractionDigits(10);
        double right = 0, line_column = 0, accuracy_global = 0, accuracy = 0;

        int mat[][] = new int[confusionMatrix[0].getClasses().size()][confusionMatrix[0].getClasses().size()];
        for (ConfusionMatrix matrix : confusionMatrix) {
            for (int i = 0; i < matrix.getClasses().size(); i++) {
                for (int j = 0; j < matrix.getClasses().size(); j++) {
                    mat[i][j] += matrix.getConfusionMatrix()[i][j];
                }
            }
        }

        jTextArea.append("Precisao\n");
        for (int i = 0; i < mat.length; i++) {
            line_column = right = mat[i][i];
            for (int j = 0; j < mat[0].length; j++) {
                if (j != i) {
                    line_column += mat[i][j];
                }
            }
            if(line_column == 0)
                accuracy = 0;
            else
                accuracy = right / line_column;
            accuracy_global += accuracy;
            jTextArea.append(df.format(accuracy) + "\t: "
                    + confusionMatrix[0].getClasses().toArray()[i] + "\n");
        }
        jTextArea.append(df.format(accuracy_global / confusionMatrix[0].getClasses().size()) + "\t: Geral\n");
    }
}
