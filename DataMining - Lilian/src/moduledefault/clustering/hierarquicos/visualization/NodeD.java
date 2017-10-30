/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moduledefault.clustering.hierarquicos.visualization;

/**
 *
 * @author Thiago M. Faino
 */
public class NodeD {

    private int x;
    private int y;
    private int padrao;
    private NodeD pai;
    private NodeD left;
    private NodeD right;

    public NodeD(int p, int x, int y) {
        this.padrao = p;
        this.x = x;
        this.y = y;
        this.pai = null;
        this.left = null;
        this.right = null;
    }

    public void setCoordenates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getPadrao() {
        return padrao;
    }

    public void setPadrao(int padrao) {
        this.padrao = padrao;
    }

    public NodeD getPai() {
        return pai;
    }

    public void setPai(NodeD pai) {
        this.pai = pai;
    }

    public NodeD getLeft() {
        return left;
    }

    public void setLeft(NodeD left) {
        this.left = left;
    }

    public NodeD getRight() {
        return right;
    }

    public void setRight(NodeD right) {
        this.right = right;
    }


}
