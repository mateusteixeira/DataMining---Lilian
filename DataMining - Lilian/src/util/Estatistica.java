/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author rafael
 */
public class Estatistica {

    private double  avg;
    private double  min;
    private double  max;
    private int     count;

    /**
     * constructor statistically
     * @param avg
     * @param min
     * @param max
     * @param count
     */
    public Estatistica(double avg, double min, double max, int count) {
        this.avg    = avg;
        this.min    = min;
        this.max    = max;
        this.count  = count;
    }

    /**
     * returns avg
     * @return
     */
    public double getAvg() {
        return avg;
    }

    /**
     * returns minimum
     * @return
     */
    public double getMin() {
        return min;
    }

    /**
     * returns maximo
     * @return
     */
    public double getMax() {
        return max;
    }

    /**
     * returns counter
     * @return
     */
    public int getCount() {
        return count;
    }
    
    @Override
    public String toString() {
        return "Média: " + avg +
                "\nMínimo: " + min +
                "\nMáximo: " + max +
                "\nNº de elementos: " + count;
    }
}
