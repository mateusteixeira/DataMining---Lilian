/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moduledefault.clustering.distancias;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import moduledefault.clustering.kmeans.Centroide;
import moduledefault.clustering.uteis.Base;

/**
 *
 * @author Mateus
 */
public class DistanciaEuclidiana {

    Base base;
    double[][] matrizDistancias;

    public DistanciaEuclidiana(Base teste) {
        base = teste.copy();
        setMatrizDistancias(teste.getDataSet().size());
    }

    public DistanciaEuclidiana() {
    }

    public void distancia() {
        double acumulador = 0;
        for (int i = 0; i < base.getDataSet().size(); i++) {
            for (int j = 0; j < base.getDataSet().size(); j++) {
                if (i != j) {
                    for (int w = 0; w < base.getDataSet().get(0).getAtributos().size(); w++) {
                        acumulador += Math.pow(base.getDataSet().get(i).getAtributos().get(w) - base.getDataSet().get(j).getAtributos().get(w), 2);
                    }
                    matrizDistancias[i][j] = Math.sqrt(acumulador);
                    acumulador = 0;
                }
            }
        }
        padronizacaDistancias(matrizDistancias);

    }

    public double distancia(List<Double> vet1, List<Double> vet2) {
        double acumulador = 0;
        for (int w = 0; w < vet1.size(); w++) {
            System.out.println("vet1 = " + vet1.get(w) + "    " + "vet2 = " + vet2.get(w));
            acumulador += Math.pow((vet1.get(w) - vet2.get(w)), 2);
        }
        System.out.println("resultado = " + Math.sqrt(acumulador));
        return Math.sqrt(acumulador);
    }

    public static double[][] distanciaKmeans(ArrayList<Centroide> centroides, int numK, double[][] matrizAtributos, int linhas) {
        double[][] resultado = new double[linhas][numK];
        for (int i = 0; i < linhas; i++) {
            for (int w = 0; w < numK; w++) {
                double acumulador = 0;
                for (int j = 0; j < centroides.get(w).getAtributos().size(); j++) {
                    acumulador += Math.pow(matrizAtributos[i][j] - centroides.get(w).getAtributos().get(j), 2);
                }
                resultado[i][w] = ((float) Math.sqrt((acumulador)));
            }
        }
        return resultado;
    }

    public void padronizacaDistancias(double[][] matriz) {
        double maior = 0;
        maior = Double.MIN_VALUE;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                if (matriz[i][j] > maior) {
                    maior = matriz[i][j];
                }
            }
        }

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                matriz[i][j] /= maior;
            }
        }
    }

    public double[][] getMatrizDistancias() {
        return matrizDistancias;
    }

    public void setMatrizDistancias(int linhas) {
        this.matrizDistancias = new double[linhas][linhas];
    }
}
