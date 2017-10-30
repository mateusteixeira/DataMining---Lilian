/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moduledefault.clustering.uteis;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Thiago
 */
public class Padrao {

    String classe;
    int numero;
    List<Double> atributos;

    public Padrao() {
        classe = "";
        atributos = new ArrayList<>();        
    }

    public List<Double> getAtributos() {
        return atributos;
    }

    public void setAtributos(List<Double> atributos) {
        this.atributos = atributos;
    }

    public void addAtributos(Double add) {
        this.atributos.add(add);
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}
