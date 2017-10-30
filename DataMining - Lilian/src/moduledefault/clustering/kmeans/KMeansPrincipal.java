/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moduledefault.clustering.kmeans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import moduledefault.clustering.distancias.DistanciaEuclidiana;
import moduledefault.clustering.uteis.Base;
import moduledefault.clustering.uteis.Cluster;
import moduledefault.clustering.visualization.panels.KmeansSimulacao;

/**
 *
 * @author Mateus
 */
public class KMeansPrincipal {

    Base dados;
    double[][] matrizAtributos;
    ArrayList<Double> minimoAtributo;
    ArrayList<Double> maximoAtributo;
    int numK;
    public ArrayList<Centroide> centroides;
    double[][] matrizDistancia;
    int[] padroesClusters;
    public boolean lock = true;
    private ArrayList<Historico> historico;
    private double perct;
    int[][] m;
    KmeansSimulacao SK;
    public boolean paradaAutomatica;
    public int maxI;
    public int it;

    public KMeansPrincipal(Base teste, int numK, boolean paradaAutomatica, boolean seedAleatorios, double seeds, int maxIteracoes, int iteracoesParada, KmeansSimulacao sk) {
        this.dados = teste;
        this.numK = numK;
        setMatrizAtributos();
        setMinMax();
        if (seedAleatorios) {
            setCentroidesInicialAleatorio();
        } else {
            perct = seeds / 100.0;
            setCentroidesInicialPorcentagem();
        }
        this.paradaAutomatica = paradaAutomatica;
        maxI = maxIteracoes;
        it = iteracoesParada;
        historico = new ArrayList<Historico>();
        this.padroesClusters = new int[this.dados.getDataSet().size()];
        for (int i = 0; i < this.padroesClusters.length; i++) {
            this.padroesClusters[i] = -1;
        }
        SK = sk;
    }


    public void start() {

        calculaMatrizDistancia();
        int[] auxMatrizCluster = new int[this.padroesClusters.length];
        for (int i = 0; i < auxMatrizCluster.length; i++) {
            auxMatrizCluster[i] = this.padroesClusters[i];
        }
        atribuiCentroides();
        Historico hist = new Historico(this.centroides, this.padroesClusters);
        this.historico.add(hist);
        avaliaMudanca(auxMatrizCluster);
        this.centroides = calcularNewCentroides();

    }

    private void setMatrizAtributos() {
        this.matrizAtributos = new double[this.dados.getDataSet().size()][(this.dados.getDataSet().get(0).getAtributos().size())];
        for (int i = 0; i < this.dados.getDataSet().size(); i++) {
            for (int j = 0; j < this.dados.getDataSet().get(0).getAtributos().size(); j++) {
                this.matrizAtributos[i][j] = (this.dados.getDataSet().get(i).getAtributos().get(j));
            }
        }

    }

    private void setMinMax() {
        this.minimoAtributo = new ArrayList<Double>();
        this.maximoAtributo = new ArrayList<Double>();

        for (int i = 0; i < this.dados.getDataSet().get(0).getAtributos().size(); i++) {
            double minimo = Float.MAX_VALUE;
            double maximo = Float.MIN_VALUE;
            for (int j = 0; j < this.dados.getDataSet().size(); j++) {
                if (this.matrizAtributos[j][i] > maximo) {
                    maximo = this.matrizAtributos[j][i];
                }
                if (this.matrizAtributos[j][i] < minimo) {
                    minimo = this.matrizAtributos[j][i];
                }
            }
            minimoAtributo.add(minimo);
            maximoAtributo.add(maximo);
        }
    }

    private void setCentroidesInicialAleatorio() {
        this.centroides = new ArrayList<Centroide>();
        for (int i = 0; i < this.numK; i++) {
            Centroide newCentroide = new Centroide(this.dados.getDataSet().get(0).getAtributos().size());
            for (int j = 0; j < newCentroide.getNumAtributos(); j++) {
                newCentroide.setAtributos(j, sorteia(minimoAtributo.get(j), maximoAtributo.get(j)));
            }
            this.centroides.add(newCentroide);
        }
    }

    private void setCentroidesInicialPorcentagem() {
        this.centroides = new ArrayList<Centroide>();
        int porcentagem = (int) (this.dados.getDataSet().size() * perct);
        Random r = new Random();
        for (int i = 0; i < this.numK; i++) {
            Centroide newCentroide = new Centroide(this.dados.getAtributos().size() - 1);
            double[] medias = new double[this.dados.getAtributos().size() - 1];
            for (int w = 0; w < porcentagem; w++) {
                int padraoSorteado = r.nextInt(150);
                for (int e = 0; e < this.dados.getAtributos().size() - 1; e++) {
                    medias[e] += this.dados.getDataSet().get(padraoSorteado).getAtributos().get(e);
                }
            }
            for (int e = 0; e < this.dados.getAtributos().size() - 1; e++) {
                medias[e] /= porcentagem;
            }
            for (int j = 0; j < newCentroide.getNumAtributos(); j++) {
                newCentroide.setAtributos(j, medias[j]);
            }
            this.centroides.add(newCentroide);
        }
    }

    public double sorteia(double minimo, double maximo) {
        Random r = new Random();
        final double H = maximo; // sorteia entre 1 e 60
        final double L = minimo;
        return (r.nextDouble() * (H - L)) + L;
    }

    private void calculaMatrizDistancia() {
        setMatrizDistancia(DistanciaEuclidiana.distanciaKmeans(centroides, numK, matrizAtributos, dados.getDataSet().size()));
    }

    private void setMatrizDistancia(double[][] resultado) {
        this.matrizDistancia = resultado;
    }

    private void atribuiCentroides() {
        for (int i = 0; i < this.dados.getDataSet().size(); i++) {
            int indexCentroide = -1;
            double menor = Double.MAX_VALUE;
            for (int j = 0; j < this.numK; j++) {
                if (this.matrizDistancia[i][j] < menor) {
                    menor = this.matrizDistancia[i][j];
                    indexCentroide = j;
                }
            }
            this.padroesClusters[i] = indexCentroide;
        }
    }

    private ArrayList<Centroide> calcularNewCentroides() {
        double[][] vetorMedias = new double[this.numK][this.dados.getDataSet().get(0).getAtributos().size()];
        int[] vetorSomatoria = new int[this.numK];

        for (int i = 0; i < this.padroesClusters.length; i++) {
            Centroide aux = this.centroides.get(this.padroesClusters[i]);
            for (int j = 0; j < aux.getAtributos().size(); j++) {
                vetorMedias[this.padroesClusters[i]][j] += this.dados.getDataSet().get(i).getAtributos().get(j);
            }
            vetorSomatoria[this.padroesClusters[i]]++;
        }
        ArrayList<Centroide> auxArray = new ArrayList<Centroide>();
        for (int i = 0; i < this.numK; i++) {
            Centroide aux = new Centroide((this.dados.getAtributos().size() - 1));
            for (int j = 0; j < aux.getAtributos().size(); j++) {
                if (vetorSomatoria[i] != 0) {
                    aux.getAtributos().set(j, (vetorMedias[i][j] / vetorSomatoria[i]));
                } else {
                    double zero = 0;
                    aux.getAtributos().set(j, zero);
                }
            }
            auxArray.add(aux);
        }
        return auxArray;

    }

    private void avaliaMudanca(int[] auxMatrizCluster) {
        for (int i = 0; i < this.padroesClusters.length; i++) {
            if (auxMatrizCluster[i] != this.padroesClusters[i]) {
                return;
            }
        }
        this.lock = false;
    }

    public StringBuffer imprimiHistorico(ArrayList<Cluster> clusters) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("\n\n===========================Resultados==================================");
        for (int i = 0; i < historico.size(); i++) {
            buffer.append("\n\nIteração: " + (i + 1) + "\n");
            buffer.append("\nCentroídes: \n");
            for (int j = 0; j < historico.get(i).historicoCentroide.size(); j++) {
                buffer.append("\nCentroide: " + j);
                buffer.append("\n\nAtributos: ");
                for (int w = 0; w < historico.get(i).historicoCentroide.get(j).getAtributos().size(); w++) {
                    buffer.append(historico.get(i).historicoCentroide.get(j).getAtributos().get(w) + " ");
                }
            }
            int[][] m = new int[2][historico.get(i).historicoPadroes.length];
            buffer.append("\n\nGrupos Formados:\n");
            for (int j = 0; j < historico.get(i).historicoPadroes.length; j++) {
                m[0][j] = j;
            }
            for (int j = 0; j < historico.get(i).historicoPadroes.length; j++) {
                m[1][j] = historico.get(i).historicoPadroes[j];
            }

//            for (int j = m[0].length - 1; j >= 1; j--) {
//                for (int y = 0; y < j; y++) {
//                    if (m[0][y] > m[0][y + 1]) {
//                        int auxLinha = m[0][y];
//                        int auxColuna = m[1][y];
//                        m[0][y] = m[0][y + 1];
//                        m[1][y] = m[1][y + 1];
//                        m[0][y + 1] = auxLinha;
//                        m[1][y + 1] = auxColuna;
//                    }
//                }
//            }
            ArrayList<Integer> grupo;

            //imprime em tela o agrupamento realizado
            String padrao;
            for (int w = 0; w < clusters.size(); w++) {
////            clusters.get(i).setNomeGrupo(dados.getClasses());
//            clusters.get(w).setNomeGrupo("Grupo " + (w + 1));
                buffer.append("\nGrupo " + (w + 1) + ":");
                grupo = clusters.get(w).getSortGrupo();
                for (int j = 0; j < grupo.size(); j++) {
                    padrao = grupo.get(j) + "";
                    switch (padrao.length()) {
                        case 1:
                            padrao += "   ";
                            break;
                        case 2:
                            padrao += "  ";
                            break;
                        case 3:
                            padrao += " ";
                            break;
                    }
                    if (j % 10 == 0) {
                        buffer.append("\n");
                    }
                    buffer.append(padrao);
                }
                buffer.append("\n");
                buffer.append("\n\n-------------------------------------------------------\n\n");

            }
            int[] vetorGruposFinal = new int[this.numK];
            for (int w = 0; w < this.padroesClusters.length; w++) {
                for (int j = 0; j < this.numK; j++) {
                    if (this.historico.get(i).historicoPadroes[w] == j) {
                        vetorGruposFinal[j]++;
                    }
                }
            }
            buffer.append("\n\nFormação Final da Iteração:\n\n ");
            for (int w = 0; w < this.numK; w++) {
                buffer.append("Grupo " + (w + 1) + " -> " + vetorGruposFinal[w]);
                buffer.append("\n");
            }
            buffer.append(("============================================================="));
        }
//        int[] vetorGruposFinal = new int[this.numK];
//        for (int i = 0; i < this.padroesClusters.length; i++) {
//            for (int j = 0; j < this.numK; j++) {
//                if (this.padroesClusters[i] == j) {
//                    vetorGruposFinal[j]++;
//                }
//            }
//        }
//        System.out.println("\n\nFormação Final: ");
//        for (int i = 0; i < this.numK; i++) {
//            System.out.println("Grupo " + i + " -> " + vetorGruposFinal[i]);
//        }
        return buffer;
    }

    public StringBuffer imprimi(ArrayList<Cluster> clusters) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("\n\n===================Resultados======================");
        buffer.append("\n\nCentroídes: \n");
        for (int j = 0; j < historico.get(historico.size() - 1).historicoCentroide.size(); j++) {
            buffer.append("\n\nCentroide: " + j);
            buffer.append("\nAtributos: ");
            for (int w = 0; w < historico.get(historico.size() - 1).historicoCentroide.get(j).getAtributos().size(); w++) {
                buffer.append(historico.get(historico.size() - 1).historicoCentroide.get(j).getAtributos().get(w) + " ");
            }
        }
        buffer.append("\n\n");
//        for (int j = m[0].length - 1; j >= 1; j--) {
//            for (int y = 0; y < j; y++) {
//                if (m[0][y] > m[0][y + 1]) {
//                    int auxLinha = m[0][y];
//                    int auxColuna = m[1][y];
//                    m[0][y] = m[0][y + 1];
//                    m[1][y] = m[1][y + 1];
//                    m[0][y + 1] = auxLinha;
//                    m[1][y + 1] = auxColuna;
//                }
//            }
//        }

        ArrayList<Integer> grupo;

        //imprime em tela o agrupamento realizado
        String padrao;
        for (int i = 0; i < clusters.size(); i++) {
//            clusters.get(i).setNomeGrupo(dados.getClasses());
            clusters.get(i).setNomeGrupo("Cluster_" + (i + 1));
            buffer.append("\nGrupo " + (i + 1) + ":");
            grupo = clusters.get(i).getSortGrupo();
            for (int j = 0; j < grupo.size(); j++) {
                padrao = grupo.get(j) + "";
                switch (padrao.length()) {
                    case 1:
                        padrao += "   ";
                        break;
                    case 2:
                        padrao += "  ";
                        break;
                    case 3:
                        padrao += " ";
                        break;
                }
                if (j % 10 == 0) {
                    buffer.append("\n");
                }
                buffer.append(padrao);
            }
            buffer.append("\n");
            buffer.append("\n\n-------------------------------------------------------\n\n");

        }
        int[] vetorGruposFinal = new int[this.numK];
        for (int w = 0; w < this.padroesClusters.length; w++) {
            for (int j = 0; j < this.numK; j++) {
                if (this.historico.get(historico.size() - 1).historicoPadroes[w] == j) {
                    vetorGruposFinal[j]++;
                }
            }
        }
        buffer.append("\nFormação Final: \n");
        for (int w = 0; w < this.numK; w++) {
            buffer.append("Grupo " + (w + 1) + " -> " + vetorGruposFinal[w]);
            buffer.append("\n");
        }

//        int[] vetorGruposFinal = new int[this.numK];
//        for (int i = 0; i < this.padroesClusters.length; i++) {
//            for (int j = 0; j < this.numK; j++) {
//                if (this.padroesClusters[i] == j) {
//                    vetorGruposFinal[j]++;
//                }
//            }
//        }
//        System.out.println("\n\nFormação Final: ");
//        for (int i = 0; i < this.numK; i++) {
//            System.out.println("Grupo " + i + " -> " + vetorGruposFinal[i]);
//        }
        return buffer;
    }

    public int[][] mConfusao() {
        int[][] mConfusao = new int[this.numK][this.numK];

        for (int j = 0; j < this.numK; j++) { //Coluna = formados
            for (int i = 0; i < this.numK; i++) {
                if (i < dados.getClasses().size()) {
                    mConfusao[i][j] = getQntiaClasses(dados.getClasses().get(i), dados, j);
                }
            }
        }
        //Ajusta as posições da matriz confusao
        for (int i = 0; i < this.numK; i++) {
            int aux = 0;
            int grupo = -1;
            for (int j = i; j < this.numK; j++) {
                if (mConfusao[i][j] > aux) {
                    boolean maior = true;
                    for (int k = i + 1; k < this.numK; k++) {
                        if (mConfusao[i][j] < mConfusao[k][j]) {
                            maior = false;
                        }
                    }
                    if (maior) {
                        aux = mConfusao[i][j];
                        grupo = j;
                    }
                }
            }
            if (grupo != i && grupo != -1) {
                for (int k = 0; k < this.numK; k++) {
                    int swap = mConfusao[k][grupo];
                    mConfusao[k][grupo] = mConfusao[k][i];
                    mConfusao[k][i] = swap;
                }
            }
        }
        return mConfusao;

    }

    public int getNumK() {
        return numK;
    }

    public void setNumK(int numK) {
        this.numK = numK;
    }

    public int[] getPadroesClusters() {
        return padroesClusters;
    }

    public void setPadroesClusters(int[] padroesClusters) {
        this.padroesClusters = padroesClusters;
    }

    public int[][] getM() {
        m = new int[2][historico.get(historico.size() - 1).historicoPadroes.length];
        for (int j = 0; j < historico.get(historico.size() - 1).historicoPadroes.length; j++) {
            m[0][j] = j;
        }
        for (int j = 0; j < historico.get(historico.size() - 1).historicoPadroes.length; j++) {
            m[1][j] = historico.get(historico.size() - 1).historicoPadroes[j];
        }

        return m;
    }

    private int getQntiaClasses(String get, Base dados, int grupo) {
        int aux = 0;
        for (int i = 0; i < dados.getDataSet().size(); i++) {
            if (get.equals(dados.getDataSet().get(i).getClasse()) && padroesClusters[i] == grupo) {
                aux++;
            }
        }
        return aux;
    }
}
