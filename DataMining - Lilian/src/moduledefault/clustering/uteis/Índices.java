/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moduledefault.clustering.uteis;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;


/**
 *
 * @author Mateus
 */
public class Índices {

    int op = 1;
    int pos = 0;
    int busca = 0;
    int contgrupo = 0;
    int sizepos;
    Scanner in = new Scanner(System.in);
    int[][] m;// = new int[matriz.length][matriz.length];
    int[][] maux;// = new int[matriz.length][matriz.length];
    int linhas;
    int[][] mpos;
    Base mpadrao1;
    int numpad = 0;
    int[][] mconfusao;
    float vtotal = 0, idunn = 999999999;
    float r = 0;
    StringBuffer string3;
    int[][] matrizResultado;
    private float medidaF;
    private int acerto;

    public Índices(int[][] matriz, Base mpadrao, int[][] mpos_par, int contgrupo_par, int pos_par) {
        string3 = new StringBuffer();
        mpadrao1 = mpadrao;
        linhas = matriz.length;
        m = new int[matriz.length][matriz.length];
        maux = new int[matriz.length][matriz.length];
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < linhas; j++) {
                m[i][j] = matriz[i][j];
            }
        }
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < linhas; j++) {
                maux[i][j] = m[i][j];
            }
        }

        sizepos = (linhas * linhas) * 2;

        mpos = new int[2][mpos_par[0].length];
//        System.out.println("tamanho mpos = "+mpos[0].length);
        for (int i = 0; i < mpos_par.length; i++) {
            for (int j = 0; j < mpos_par[0].length; j++) {
                mpos[i][j] = mpos_par[i][j];
            }
        }
        matrizResultado = new int[2][mpos[0].length];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < matrizResultado[0].length; j++) {
                matrizResultado[i][j] = mpos[i][j];
            }
        }
        pos = pos_par;
        contgrupo = contgrupo_par;


    }

    public void inicio() throws IOException {

        variancia(mpos);
        dunn(mpos);
        matrizConfusao(mpadrao1);
        medidaF();
        acerto();
    }

    public final void acerto() {
        for (int i = 0; i < mconfusao.length; i++) {
            for (int j = 0; j < mconfusao[0].length; j++) {
                if (i == j) {
                    acerto += mconfusao[i][i];
                }
            }

        }
        acerto = acerto * 100;
        acerto = acerto / mpadrao1.getDataSet().size();
    }

    void variancia(int[][] mpos) {
        float centro = 0, qdesvio = 0, sqdesvio = 0, v = 0, somaqdesvio = 0;
        int qpadrao = 0, somapadrao = 0;


        for (int i = 1; i <= contgrupo; i++) {
            for (int j = 0; j < pos; j++) {
                if (mpos[1][j] == i) {
                    ++qpadrao;//conta quantos padroes existem no grupo
                    ++somapadrao;//somatorio de todos os padroes da matriz 
                    centro += mpos[0][j];//faz a soma dos padroes 
                }
            }
            centro = centro / qpadrao;//calcula o centroide/média
            for (int j = 0; j < pos; j++) {
                if (mpos[1][j] == i) {
                    qdesvio = mpos[0][j] - centro;
                    qdesvio = (float) Math.pow(qdesvio, 2);
                    sqdesvio += qdesvio;
                    qdesvio = 0;
                }
            }
            somaqdesvio += sqdesvio;//somatorios dos quadrados dos desvios entre todos os padroes de todos os grupos
            v = sqdesvio / centro;
            qpadrao = 0;
            centro = 0;
            qdesvio = 0;
            sqdesvio = 0;
            v = 0;
            //system("pause");
        }
        ////////////Fazer a variancia para todos os grupos
        vtotal = somaqdesvio / somapadrao;//variancia total, de todos os grupos da matriz 

    }

    void dunn(int[][] mpos) {
        float centro1 = 0, qdesvio1 = 0, diam1 = 0;
        float centro2 = 0, qdesvio2 = 0, diam2 = 0;
        float mdistancia = 0, fdistancia = 0, diamfinal = 0;
        int qpadrao1 = 0, qpadrao2 = 0;

        for (int i = 1; i < contgrupo; i++) {
            ///////////Primeiro grupo
            for (int j = 0; j < pos; j++) {
                if (mpos[1][j] == i) {
                    ++qpadrao1;//conta quantos padroes existem no grupo
                    centro1 += mpos[0][j];//faz a soma dos padroes 
                }
            }
            centro1 = centro1 / qpadrao1;//calcula o centroide/média
            for (int j = 0; j < pos; j++) {
                if (mpos[1][j] == i) {
                    qdesvio1 = mpos[0][j] - centro1;
                    qdesvio1 = (float) Math.pow(qdesvio1, 2);
                    if (qdesvio1 > diam1) {
                        diam1 = qdesvio1;
                    }
                    qdesvio1 = 0;

                }
            }
            ///////////Segundo grupo
            for (int x = i + 1; x <= contgrupo; x++) {
                for (int j = 0; j < pos; j++) {
                    if (mpos[1][j] == x) {
                        ++qpadrao2;//conta quantos padroes existem no grupo
                        centro2 += mpos[0][j];//faz a soma dos padroes 
                    }
                }
                centro2 = centro2 / qpadrao2;//calcula o centroide/média
                for (int j = 0; j < pos; j++) {
                    if (mpos[1][j] == x) {
                        qdesvio2 = mpos[0][j] - centro2;
                        qdesvio2 = (float) Math.pow(qdesvio2, 2);
                        if (qdesvio2 > diam2) {
                            diam2 = qdesvio2;
                        }
                        qdesvio2 = 0;
                    }
                }
                ////Distancia entre as médias
                mdistancia = (float) Math.sqrt((Math.pow(centro1, 2)) - (Math.pow(centro2, 2)));
                if (diam1 >= diam2) {
                    diamfinal = diam1;
                } else {
                    diamfinal = diam2;
                }
                fdistancia = mdistancia / (diamfinal);///arrumar pegar o maior diam entre todos os grupos
                if (fdistancia < idunn) {
                    idunn = fdistancia;
                }
                ////Zera as variaveis para prox iteração
                centro2 = 0;
                qdesvio2 = 0;
                diam2 = 0;
                qpadrao2 = 0;
                mdistancia = 0;
                fdistancia = 0;
            }
            ////Zera as variaveis para prox iteração
            centro1 = 0;
            qdesvio1 = 0;
            diam1 = 0;
            qpadrao1 = 0;
        }
    }

    public final void medidaF() {
        int numpadF = mpadrao1.getClasses().size();
        int contGrupoF = mpadrao1.getClasses().size();
        float[][] p = new float[numpadF][contGrupoF];
        float[][] r = new float[numpadF][contGrupoF];
        float[][] f = new float[numpadF][contGrupoF];
        float[] gerado = new float[contGrupoF];
        float[] desejados = new float[numpadF];
        float b = 1, somapad = 0;

        for (int j = 0; j < contGrupoF; j++) {
            gerado[j] = 0;
        }
        for (int i = 0; i < numpadF; i++) {
            desejados[i] = 0;
        }
        for (int j = 0; j < contGrupoF; j++) {
            for (int i = 0; i < numpadF; i++) {
                gerado[j] = gerado[j] + mconfusao[i][j];
            }
        }
        for (int i = 0; i < numpadF; i++) {
            for (int j = 0; j < contGrupoF; j++) {
                desejados[i] = desejados[i] + mconfusao[i][j];
            }
        }
        for (int i = 0; i < numpadF; i++) {
            somapad = somapad + desejados[i];
        }
        for (int i = 0; i < numpadF; i++) {
            for (int j = 0; j < contGrupoF; j++) {
                p[i][j] = 0;
                r[i][j] = 0;
                f[i][j] = 0;
            }
        }
        ///CALCULO P
        for (int i = 0; i < numpadF; i++) {
            for (int j = 0; j < contGrupoF; j++) {
                p[i][j] = mconfusao[i][j] / gerado[j];
            }
        }
        ///CALCULO R
        for (int i = 0; i < numpadF; i++) {
            for (int j = 0; j < contGrupoF; j++) {
                r[i][j] = mconfusao[i][j] / desejados[i];
            }
        }
        ///CALCULO F
        for (int i = 0; i < numpadF; i++) {
            for (int j = 0; j < contGrupoF; j++) {
                f[i][j] = (((b * b) + 1) * p[i][j] * r[i][j]) / ((b * b) * p[i][j] + r[i][j]);
            }
        }

        float maxj = 0;
        for (int i = 0; i < numpadF; i++) {
            for (int j = 0; j < contGrupoF; j++) {
                if (f[i][j] > maxj) {
                    maxj = f[i][j];
                }
            }
            medidaF = medidaF + ((desejados[i] / somapad) * maxj);
            maxj = 0;
        }

        indice_aleatorio(mpos);
    }

    void indice_aleatorio(int[][] mpos) {
        int[] classepad = new int[mpadrao1.getDataSet().size()];
        int z = 0;
        for (int i = 0; i < mpadrao1.getDataSet().size(); i++) {
            classepad[i] = 0;
        }
        int col2 = 0, col1 = 0;

        col1 = mpadrao1.getDataSet().get(0).getAtributos().size();

        for (int i = 0; i < mpadrao1.getDataSet().size(); i++) {
            for (int j = 0; j < col1; j++) {
                if (j == 0) {
                    classepad[i] = mpadrao1.getDataSet().get(i).getAtributos().get(j).intValue();
                }
            }
        }
        int[][] newvet = new int[2][mpadrao1.getDataSet().size()]; //
        float a = 0, b = 0, c = 0, d = 0;


        for (int i = 0; i < mpadrao1.getDataSet().size(); i++) {
            for (int j = 0; j < pos; j++) {
                if (mpos[0][j] == (i + 1)) {
                    newvet[0][i] = mpos[0][j];
                    newvet[1][i] = mpos[1][j];
                }
            }
        }

        for (int i = 0; i < mpadrao1.getDataSet().size(); i++) {
            for (int j = 0; j < mpadrao1.getDataSet().size(); j++) {
                if ((i != j) && (j > i)) {
                    if ((classepad[i] == classepad[j]) && (newvet[1][i] == newvet[1][j])) {
                        ++a;
                    }
                    if ((classepad[i] == classepad[j]) && (newvet[1][i] != newvet[1][j])) {
                        ++b;
                    }
                    if ((classepad[i] != classepad[j]) && (newvet[1][i] == newvet[1][j])) {
                        ++c;
                    }
                    if ((classepad[i] != classepad[j]) && (newvet[1][i] != newvet[1][j])) {
                        ++d;
                    }
                }
            }
        }
        r = (a + d) / (a + b + c + d);
//        x.string.append("\n-----------------------------------------------------------");
//        x.string.append("\nINDICE ALEATORIO\n");
//        x.string.append("\nR = ").append(r);
//        x.string2.append("\n-----------------------------------------------------------");
//        x.string2.append("\nINDICE ALEATORIO\n");
//        x.string2.append("\nR = ").append(r).append("\n");
//        string3.append("\n-----------------------------------------------------------");
//        string3.append("\nINDICE ALEATORIO\n");
//        string3.append("\nR = ").append(r).append("\n");

    }

    public float getFfinal() {
        return medidaF;
    }

    public float getIdunn() {
        return idunn;
    }

    public float getR() {
        return r;
    }

    public float getVtotal() {
        return vtotal;
    }

    public int getContgrupo() {
        return contgrupo;
    }

    public float getPorcentagem() {
        return acerto;
    }

    public int getNumpad() {
        return numpad;
    }

    public int[][] getMconfusao() {
        return mconfusao;
    }

    public final int[][] matrizConfusao(Base dados) {
        int gruposDesejados = dados.getClasses().size();
        int gruposFormados = this.contgrupo;

        mconfusao = new int[gruposDesejados][gruposFormados];


        for (int j = 0; j < gruposFormados; j++) { //Coluna = formados
            for (int i = 0; i < gruposDesejados; i++) {
                mconfusao[i][j] = getQntiaClasses(dados.getClasses().get(i), dados, j + 1);
            }
        }

        //Ajusta as posições da matriz confusao
        for (int i = 0; i < gruposDesejados; i++) {
            int aux = 0;
            int grupo = -1;
            for (int j = i; j < gruposFormados; j++) {
                if (mconfusao[i][j] > aux) {
                    boolean maior = true;
                    for (int k = i + 1; k < gruposDesejados; k++) {
                        if (mconfusao[i][j] < mconfusao[k][j]) {
                            maior = false;
                        }
                    }
                    if (maior) {
                        aux = mconfusao[i][j];
                        grupo = j;
                    }
                }
            }
            if (grupo != i && grupo != -1) {
                for (int k = 0; k < gruposDesejados; k++) {
                    int swap = mconfusao[k][grupo];
                    mconfusao[k][grupo] = mconfusao[k][i];
                    mconfusao[k][i] = swap;
                }
            }
        }

        return mconfusao;
    }

    private int getQntiaClasses(String get, Base dados, int grupo) {
        int aux = 0;
        for (int i = 0; i < dados.getDataSet().size(); i++) {
            if (get.equals(dados.getDataSet().get(i).getClasse()) && matrizResultado[1][i] == grupo) {
                aux++;
            }
        }
        return aux;
    }
    
//      public float centroide(Cluster cluster) {
//        float centroide = 0;
//
//        for (int i = 0; i < cluster.getGrupo().size(); i++) {
//            centroide += cluster.getGrupo().get(i).getNumero(); //soma dos padroes
//        }
//        centroide = centroide / cluster.getGrupo().size(); //calcula centroide/media
//
//        return centroide;
//    }
}