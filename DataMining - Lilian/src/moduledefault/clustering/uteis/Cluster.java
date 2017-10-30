/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moduledefault.clustering.uteis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Thiago
 */
public class Cluster {

    private ArrayList<Padrao> grupo;
    private ArrayList<Double> atributos;
    private ArrayList<Double> centroide;
    private String nomeGrupo;
    private int pai;
    private int posicaoDend;
    private String nomeClasse;

    public Cluster() {
        grupo = new ArrayList<>();
        nomeGrupo = null;
    }

    public void addPadrao(Padrao n) {
        grupo.add(n);
    }

    public ArrayList<Padrao> getGrupo() {
        return grupo;
    }

    public void setGrupo(ArrayList<Padrao> grupo) {
        this.grupo = grupo;
    }

    public void centroids() {
        atributos = new ArrayList<>();

        for (int i = 0; i < grupo.get(0).getAtributos().size(); i++) {
            atributos.add(0.0);
        }

        for (int i = 0; i < grupo.size(); i++) {
            for (int j = 0; j < grupo.get(i).getAtributos().size(); j++) {
                Double aux = atributos.get(j) + grupo.get(i).getAtributos().get(j);
                atributos.set(j, aux);
            }
        }

        for (int i = 0; i < atributos.size(); i++) {
            Double aux = atributos.get(i) / grupo.size();
            atributos.set(i, aux);
        }
    }

    public ArrayList<Double> getAtributos() {
        return atributos;
    }

    public void setAtributos(ArrayList<Double> centroides) {
        this.atributos = centroides;
    }

    public ArrayList<Integer> getSortGrupo() {
        ArrayList<Integer> aux = new ArrayList<>();
        for (int j = 0; j < grupo.size(); j++) {
            aux.add(grupo.get(j).getNumero());
        }
        Collections.sort(aux);
        return aux;
    }

    public void setNomeGrupo(List<String> classes) {
        int num = 0;
        String nome = null;

        for (int i = 0; i < classes.size(); i++) {
            int aux = 0;
            for (int j = 0; j < grupo.size(); j++) {
                if (grupo.get(j).getClasse().equals(classes.get(i))) {
                    ++aux;
                }
            }
            if (aux > num) {
                num = aux;
                nome = classes.get(i);
            }
        }
        nomeGrupo = nome;
    }

    public void setNomeGrupo(String nome) {
        nomeGrupo = nome;
    }

    public String getNomeGrupo() {
        return nomeGrupo;
    }

    public int getNumClasse(String classe) {
        int aux = 0;
        for (int i = 0; i < grupo.size(); i++) {
            if (grupo.get(i).getClasse().equals(classe)) {
                ++aux;
            }
        }
        return aux;
    }

    public int numPadroes() {
        return grupo.size();
    }

    public int getPai() {
        return pai;
    }

    public void setPai(int pai) {
        this.pai = pai;
    }

    public int getPosicaoDend() {
        return posicaoDend;
    }

    public void setPosicaoDend(int posicaoDend) {
        this.posicaoDend = posicaoDend;
    }

    public String getNomeClasse() {
        return nomeClasse;
    }

    public void setNomeClasse(String nomeClasse) {
        this.nomeClasse = nomeClasse;
    }

    public ArrayList<Double> getCentroide() {
        return centroide;
    }

    public void setCentroide(ArrayList<Double> centroide) {
        this.centroide = centroide;
    }

    public void calcNewCentroide() {
        System.out.println("=======================================================================");
        System.out.println("cluster = "+this.nomeGrupo);
//        for (int j = 0; j < this.grupo.size(); j++) {
//            for (int i = 0; i < this.grupo.get(0).getAtributos().size(); i++) {
//                System.out.print(this.grupo.get(j).getAtributos().get(i)+" ");
//            }
//            System.out.println("");
//        }
        for (int i = 0; i < this.grupo.get(0).getAtributos().size(); i++) {
            double soma = 0;
            for (int j = 0; j < this.grupo.size(); j++) {
                soma += this.grupo.get(j).getAtributos().get(i);
            }
            soma /= this.grupo.size();
            this.centroide.set(i, soma);
        }
        System.out.println("centroide = ");
        for (int i = 0; i < this.centroide.size(); i++) {
            System.out.print(this.centroide.get(i)+" ");
        }
        
        System.out.println("\n===========================================================================");
    }

}
