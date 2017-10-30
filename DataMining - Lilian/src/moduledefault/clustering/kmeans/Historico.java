/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moduledefault.clustering.kmeans;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 *
 * @author Mateus
 */
public class Historico {
    
    ArrayList<Centroide> historicoCentroide;
    int [] historicoPadroes;


    public Historico(ArrayList<Centroide> historicoCentroide, int[] historicoPadroes) {
        this.historicoCentroide = historicoCentroide;
        this.historicoPadroes = new int[historicoPadroes.length];
        for(int i = 0;i < historicoPadroes.length;i++){
        this.historicoPadroes[i] = historicoPadroes[i];
        }

    }

    public ArrayList<Centroide> getHistoricoCentroide() {
        return historicoCentroide;
    }

    public int[] getHistoricoPadroes() {
        return historicoPadroes;
    }


    
 
    
}
