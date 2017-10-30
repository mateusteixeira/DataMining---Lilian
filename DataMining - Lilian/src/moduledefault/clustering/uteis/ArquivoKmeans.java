/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moduledefault.clustering.uteis;

/**
 *
 * @author MateusFelipe
 */
public class ArquivoKmeans {

    int k;
    boolean paradaAutomatica;
    int iteracoes;
    int maxIteracoes;
    boolean seedAleatorios;
    int seeds;

    public ArquivoKmeans(int k, boolean paradaAutomatica, int iteracoes, int maxIteracoes, boolean seedAleatorios, int seeds) {
        this.k = k;
        this.paradaAutomatica = paradaAutomatica;
        this.iteracoes = iteracoes;
        this.maxIteracoes = maxIteracoes;
        this.seedAleatorios = seedAleatorios;
        this.seeds = seeds;
    }

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }

    public boolean isParadaAutomatica() {
        return paradaAutomatica;
    }

    public void setParadaAutomatica(boolean paradaAutomatica) {
        this.paradaAutomatica = paradaAutomatica;
    }

    public int getIteracoes() {
        return iteracoes;
    }

    public void setIteracoes(int iteracoes) {
        this.iteracoes = iteracoes;
    }

    public int getMaxIteracoes() {
        return maxIteracoes;
    }

    public void setMaxIteracoes(int maxIteracoes) {
        this.maxIteracoes = maxIteracoes;
    }

    public boolean isSeedAleatorios() {
        return seedAleatorios;
    }

    public void setSeedAleatorios(boolean seedAleatorios) {
        this.seedAleatorios = seedAleatorios;
    }

    public int getSeeds() {
        return seeds;
    }

    public void setSeeds(int seeds) {
        this.seeds = seeds;
    }

}
