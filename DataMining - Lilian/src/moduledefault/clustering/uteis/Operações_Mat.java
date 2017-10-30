/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moduledefault.clustering.uteis;

import java.io.IOException;

public class Operações_Mat {

    double qmais, qmenos, rmais, rmenos;
//          Operações operações = new Operações();
    double fun1;
    double fun2;
    final double kp = 0.1;
    final double kd = 0.3;

    public void Padronização(Base matriz) {
        double menor = 0;
        double maior = 0;
        int cont = 0;
        while (cont < matriz.getAtributos().size()-1) {
            menor = 10000;
            maior = -10000;
            for (int i = 0; i < matriz.getDataSet().size(); i++) {
                if (matriz.getDataSet().get(i).getAtributos().get(cont) < menor) {
                    menor = matriz.getDataSet().get(i).getAtributos().get(cont);
                }

                if (matriz.getDataSet().get(i).getAtributos().get(cont) > maior) {
                    maior = matriz.getDataSet().get(i).getAtributos().get(cont);
                }
            }
            for (int i = 0; i < matriz.getDataSet().size(); i++) {
                matriz.getDataSet().get(i).getAtributos().set(cont, (matriz.getDataSet().get(i).getAtributos().get(cont) - menor));
            }
            maior -= menor;
            for (int i = 0; i < matriz.getDataSet().size(); i++) {

                matriz.getDataSet().get(i).getAtributos().set(cont, (matriz.getDataSet().get(i).getAtributos().get(cont) / maior));
            }
            cont++;
        }



    }

    public void PA_PG(int cont2, double percent1, double sigmaminimo, double sigmamaximo, double alfaminimo, double alfamaximo, double controlesigma, double controlealfa) {
        rmais = (alfamaximo - alfaminimo) / (cont2 * percent1);
        rmenos = (controlealfa - alfamaximo) / (cont2 * (1 - percent1));
        qmais = Math.pow((sigmamaximo / sigmaminimo), (1 / (cont2 * percent1)));
        qmenos = Math.pow((controlesigma / sigmamaximo), (1 / (cont2 * (1 - percent1))));
    }

    public double fPdrop(int i) {
        if (i == 1) {
            return Math.pow((fun1 / (fun1 + kd)), 2);
        } else {
            return Math.pow((fun2 / (fun2 + kd)), 2);
        }


    }

    public double fPpick() {
        return Math.pow((kp / (fun1 + kp)), 2);
    }

    public void calculos(int i, int cont_vizinhos, Base matriz_dados, double[] distâncias, int sigma, double alfa, int índice) throws IOException {
        double soma1 = 0;
        double somatoria1 = 0;
        double somatoria2 = 0;
        int testesoma = 0;
        double soma2 = 0;
        if (i == 1) {
            if (cont_vizinhos > 0) {
                for (int j = 0; j < índice; j++) {
//                        soma1 = (1 - (distâncias[j])); //sem divisao alfa
                    soma1 = (1 - (distâncias[j] / alfa)); //sem divisao alfa
                    somatoria1 += soma1;
                    if (soma1 <= 0) {
                        fun1 = 0;
                        testesoma = 1;
                        break;
                    }
                }
                if (testesoma != 1) {
                    fun1 = (1 / ((Math.pow((2 * sigma + 1), 2)))) * somatoria1; //numero celulas ocupadas
//                        fun1 = (1 / cont_vizinhos) * somatoria1;
                }
            } else {
                if (cont_vizinhos <= 0) {
                    fun1 = 0;
                }
            }
        } else {
            if (i == 2) {
                if (cont_vizinhos > 0) {
                    for (int j = 0; j < índice; j++) {
//                            soma2 = (1 - (distâncias[j]));
                        soma2 = (1 - (distâncias[j] / alfa));
                        somatoria2 += soma2;
                        if (soma2 <= 0) {
                            fun2 = 0;
                            testesoma = 1;
                            break;
                        }
                    }
                    if (testesoma != 1) {

                        fun2 = (1 / ((Math.pow((2 * sigma + 1), 2)))) * somatoria2;
//                            fun2 = (1 / cont_vizinhos) * somatoria2;
                    }
                } else {
                    if (cont_vizinhos <= 0) {
                        fun2 = 0;
                    }
                }

            }
        }

    }

    public double getFun1() {
        return fun1;
    }

    public double getFun2() {
        return fun2;
    }

    public double getKd() {
        return kd;
    }

    public double getKp() {
        return kp;
    }

    public double getQmais() {
        return qmais;
    }

    public double getQmenos() {
        return qmenos;
    }

    public double getRmais() {
        return rmais;
    }

    public double getRmenos() {
        return rmenos;
    }
}
