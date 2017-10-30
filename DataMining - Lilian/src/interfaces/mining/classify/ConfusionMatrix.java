/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces.mining.classify;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author rafael
 */
public class ConfusionMatrix {

    private ArrayList classes;
    private int[][] matrix;

    /** tp the number of correctly classified positives*/
    private double[] tp;

    /** fp the number of incorrectly classified negatives*/
    private double[] fp;

    /** fn the number of incorrectly classified positives*/
    private double[] fn;

    /** tn the number of correctly classified negatives*/
    private double[] tn;

    private boolean updated;

    /**
     * constructor confusion matrix
     */
    public ConfusionMatrix() {
        classes = new ArrayList();
        matrix = null;
    }

    /**
     * sets the class
     * @param classes
     */
    public void setClasses(Collection classes){
        this.classes = new ArrayList(classes);
        matrix = new int[this.classes.size()][this.classes.size()];
        updated=true;
    }

    /**
     * returns the class
     * @return
     */
    public Collection getClasses(){
        return classes;
    }

    /**
     * calculates
     */
    private void calc() {
        //realizando os calculos
        fp = new double[matrix.length];
        tp = new double[matrix.length];
        fn = new double[matrix.length];
        tn = new double[matrix.length];
        for (int classe = 0; classe < matrix.length; classe++) {
            fp[classe] = 0;
            tp[classe] = 0;
            fn[classe] = 0;
            tn[classe] = 0;
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 1; j < matrix[i].length; j++) {
                    if (i == classe) {
                        if (j - 1 == classe) {
                            tp[classe] += matrix[i][j];
                        } else {
                            fn[classe] += matrix[i][j];
                        }
                    } else {
                        if (j - 1 == classe) {
                            fp[classe] += matrix[i][j];
                        } else {
                            tn[classe] += matrix[i][j];
                        }
                    }
                }
            }
        }
        updated=false;
    }

    /**
     * adds value
     * @param real
     * @param predict
     */
    public void addValue(Object real, Object predict){
        matrix[classes.indexOf(real)][classes.indexOf(predict)]++;
        updated=true;
    }
    public void setValue(Object real, Object predict, Integer value){
        matrix[classes.indexOf(real)][classes.indexOf(predict)] = value;
        updated=true;
    }

    /**
     * returns the value
     * @param real
     * @param predict
     * @return
     */
    public int getValue(Object real, Object predict){
        return matrix[classes.indexOf(real)][classes.indexOf(predict)];
    }

    /**
     * returns the number of incorrectly classified positives
     * @return
     */
    public double[] getFn() {
        if(updated) calc();
        return fn.clone();
    }

    /**
     * reutnrs the number of incorrectly classified negatives
     * @return
     */
    public double[] getFp() {
        if(updated) calc();
        return fp.clone();
    }

    /**
     * returns the number of correctly classified negatives
     * @return
     */
    public double[] getTn() {
        if(updated) calc();
        return tn.clone();
    }

    /**
     * returns the number of correctly classified positives
     * @return
     */
    public double[] getTp() {
        if(updated) calc();
        return tp.clone();
    }

    /**
     * returns the confision matrix
     * @return
     */
    public int[][] getConfusionMatrix(){
        return (int[][])matrix.clone();
    }

    @Override
    public String toString(){
        Integer maior = 0;
        for (int i= 0; i < classes.size(); i++) {
            for (int j= 0; j < classes.size(); j++) {
                maior = maior < matrix[i][j] ? matrix[i][j]:maior;
            }
        }
        Integer sp = maior.toString().length();
        
        String str = "";
        for (int i= 0; i < classes.size(); i++) {
            str += space(sp);
            str+= new Character((char)('a' + i));// + "\t";
        }
        str+= "\n";
        for (int i= 0; i < classes.size(); i++) {
            for (int j= 0; j < classes.size(); j++) {
                str+= space(sp+1-new Integer(matrix[i][j]).toString().length());
                str+= matrix[i][j];//+"\t";
            }
            str+= "  "+new Character((char)('a' + i)) + " = " + classes.get(i) + "\n";
        }
        return str;
    }
    
    private String space(Integer n){
        String s = "";
        for (int i = 0; i < n; i++) {
            s+=" ";
        }
        return s;
    }
    
}
