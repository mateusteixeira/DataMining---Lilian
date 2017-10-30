/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moduledefault.clustering.kmeans;

import java.util.ArrayList;

/**
 *
 * @author Mateus
 */
public class Centroide {

    private ArrayList<Double> atributos;
    private int grupo;
    private int qntCluster;
    private int numAtributos;

    public Centroide(int numAtributos) {
        this.grupo = 0;
        this.qntCluster = 0;
        this.numAtributos = numAtributos;
        atributos = new ArrayList<Double>();
        for(int i = 0;i < this.numAtributos;i++){
            atributos.add((double)-1);
        }
    }

    public void setAtributos(int i, double atributo) {
        this.atributos.set(i, atributo);
    }

    public ArrayList<Double> getAtributos() {
        return atributos;
    }

    public int getNumAtributos() {
        return numAtributos;
    }

    public void setNumAtributos(int numAtributos) {
        this.numAtributos = numAtributos;
    }


    public int getGrupo() {
        return grupo;
    }

    public void setGrupo(int grupo) {
        this.grupo = grupo;
    }

    public int getQntCluster() {
        return qntCluster;
    }

    public void setQntCluster() {
        this.qntCluster++;
    }

    public void setQntCluster(int i) {
        this.qntCluster = i;
    }
}
