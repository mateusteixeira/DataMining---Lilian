/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moduledefault.clustering.uteis;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Thiago Magalhães Faino
 */
public class Base {

    String Nome;
    List<Padrao> dataSet = new ArrayList<>();
    List<String> atributos = new ArrayList<>();
    List<String> classes = new ArrayList<>();
    int dimensaoMatriz;
    private int linhas;
    private int colunas;

    public Base() {
        this.Nome = null;
        this.dataSet.clear();
        this.atributos.clear();
        this.classes.clear();
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public List<Padrao> getDataSet() {
        return dataSet;
    }

    public void setDataSet(List<Padrao> dataSet) {
        this.dataSet = dataSet;
    }

    public void setDataSet() {
        this.dataSet = new ArrayList<>();
    }

    public void addDataSet(Padrao pad) {
        this.dataSet.add(pad);
    }

    public List<String> getAtributos() {
        return atributos;
    }

    public void addAtributos(String att) {
        atributos.add(att);
    }

    public String getAtributo(int i) {
        return atributos.get(i);
    }

    public List<String> getClasses() {
        return classes;
    }

    public void setClasses(List<String> grupos) {
        this.classes = grupos;
    }

    public void setDimensaoMatriz() {
        dimensaoMatriz = (int) Math.sqrt((10 * this.dataSet.size()));
    }

    public int getDimensaoMatriz() {
        return dimensaoMatriz;
    }

    public void setLinhas(int numElemento) {
        linhas = numElemento;
    }

    public void setColunas(int colunas) {
        this.colunas = colunas;
    }

    public Base copy() {
        Base resultado = new Base();
        resultado.setNome(this.getNome());
        resultado.dataSet = this.dataSet;
        resultado.atributos = this.atributos;
        resultado.classes = this.classes;
        resultado.setDimensaoMatriz();
        return resultado;
    }
}
