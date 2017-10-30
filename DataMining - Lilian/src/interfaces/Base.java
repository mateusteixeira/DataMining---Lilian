/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * object database
 * @author evaristowb
 */
public class Base {

    /**
     * attributes of a database
     */
    private String[] atributes = null;
    /**
     * input data
     */
    private Object[][] input = null;
    /**
     * output data
     */
    private Object[] output = null;

    /**
     * name data
     */
    private Object name = null;

    /**
     * class constructor
     */
    public Base() {
    }

    /**
     * returns attributes
     * @return atributes - array of Strings
     */
    public String[] getAtributes() {
        return atributes;
    }

    /**
     * setting attributes
     * @param atributes - array of Strings
     */
    public void setAtributes(String[] atributes) {
        this.atributes = atributes;
    }

    /**
     * returns input data
     * @return input - object array
     */
    public Object[][] getInput() {
        return input;
    }

    /**
     * setting input
     * @param input - object array
     */
    public void setInput(Object[][] input) {
        this.input = input;
    }

    /**
     * returns the input attributes of a given line
     * @param line - required line
     * @return input[line] - input data of a given line
     */
    public Object[] getInputLine(int line) {
        return input[line];
    }

    /**
     * returns output data
     * @return output - array of Strings
     */
    public Object[] getOutput() {
        return output;
    }

    /**
     * setting output
     * @param output - object array
     */
    public void setOutput(Object[] output) {
        this.output = output;
    }

    /**
     * return output attributs of a given line
     * @param line - required line
     * @return output[line] - output data of a given line
     */
    public Object getOutputLine(int line){
        return output[line];
    }

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    /**
     * verifies that the output has metadata
     * @return true or false
     */
    public boolean hasMeta(){
        return output != null || output.length == 0;
    }

    /**
     * returns the possible classes in the output
     * @return classes - array of classes
     */
    public Collection getClasses() {
        ArrayList classes = new ArrayList();
        if (output!=null) {
            for (int i = 0; i < output.length; i++) {
                if (!classes.contains(output[i])) {
                    classes.add(output[i]);
                }
            }
        }
        Collections.sort(classes);        
        return classes;
    }
}
