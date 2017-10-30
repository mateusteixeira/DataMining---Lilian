/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moduledefault.clustering.hierarquicos.visualization;

import java.util.ArrayList;

/**
 *
 * @author Thiago M. Faino
 */
public class BinTree {

    private NodeD root;
    ArrayList<NodeD> base;
    String xml;

    public BinTree(NodeD root) {
        this.root = root;
        xml = "";
    }

    public BinTree() {
        this.root = null;
    }

    public void createTree(int[][] dendograma) {

        ArrayList<Integer> baseAux = ordena(dendograma);
        base = new ArrayList<>();

        for (int i = 0; i < baseAux.size(); i++) {
            base.add(new NodeD(baseAux.get(i), 0, 0));
        }
        for (int i = 0; i < dendograma.length - 1; i++) {
            int analisado = -1;
            for (int j = 0; j < dendograma[0].length; j++) {
                if (dendograma[i][j] != dendograma[i + 1][j] && dendograma[i][j] != analisado) {
                    analisado = dendograma[i][j];
                    NodeD n = new NodeD(dendograma[i + 1][j], 0, 0); //pai
                    int indexLeft = indexOf(dendograma[i + 1][j]);
                    int indexPai = indexLeft;
                    int indexRight = indexOf(dendograma[i][j]);
                    if (indexLeft != -1) {
                        n.setLeft(base.get(indexLeft));
                    }
                    if (indexRight != -1) {
                        n.setRight(base.get(indexRight));
                        base.remove(indexRight);
                    }
                    base.set(indexPai, n);
                }
            }
        }
        root = base.remove(0);

    }

    public void showEmOrdem(NodeD root) { //RED
        if (root != null) {
            showEmOrdem(root.getLeft());
            System.out.println(root.getPadrao());
            showEmOrdem(root.getRight());
        }
    }

    public void showPreOrdem(NodeD root) { //ERD
        if (root != null) {
            System.out.println(root.getPadrao());
            showPreOrdem(root.getLeft());
            showPreOrdem(root.getRight());
        }
    }

    public void showPosOrdem(NodeD root) { //EDR
        if (root != null) {
            showPosOrdem(root.getLeft());
            showPosOrdem(root.getRight());
            System.out.println(root.getPadrao());
        }
    }

    public ArrayList<Integer> ordena(int[][] dendograma) {

        ArrayList<Integer> base = new ArrayList<>();
        ArrayList<Integer> baseAux = new ArrayList<>();
        ArrayList<Integer> aux = new ArrayList<>();

        for (int i = 0; i < dendograma.length; i++) {
            base.add(dendograma[0][i]);
            baseAux.add(dendograma[0][i]);
        }

        for (int i = 0; i < dendograma.length - 1; i++) {
            for (int j = 0; j < dendograma[0].length; j++) {
                aux.clear();
                if (dendograma[i][j] != dendograma[i + 1][j]) {
//                    System.out.println("");
                    for (int k = 0; k < dendograma[0].length; k++) {
                        if (dendograma[i][k] == dendograma[i][j]) {
                            aux.add(k);
                        }
                    }

                    int pos = 0;
                    for (int k = baseAux.size() - 1; k >= 0; k--) {
                        if (baseAux.get(k) == dendograma[i + 1][j]) {
                            pos = k + 1;
                            break;
                        }
                    }

                    for (int k = 0; k < aux.size(); k++) {
                        int x = base.indexOf(dendograma[0][aux.get(k)]);
                        int coco = base.remove(x);
                        if (pos + k >= base.size()) {
                            base.add(coco);
                            baseAux.add(dendograma[i + 1][j]);
                        } else {
                            base.add(pos + k, coco);
                            baseAux.add(pos + k, dendograma[i + 1][j]);
                        }
                    }
                }
            }

        }

        return base;
    }

    public NodeD getRoot() {
        return root;
    }

    public void setRoot(NodeD root) {
        this.root = root;
    }

    public ArrayList<NodeD> getBase() {
        return base;
    }

    private int indexOf(int i) {
        int index = 0;
        for (NodeD n : base) {
            if (i == n.getPadrao()) {
                return index;
            }
            index++;
        }
        return -1;
    }

    public void treeAnalysis(NodeD root) {
        if (root != null) {
            xml += "<branch> <attribute name=\"name\" value=\"" + root.getPadrao() + "\" />\n";
            treeAnalysis(root.getLeft());
            treeAnalysis(root.getRight());
            xml += "</branch>\n";
        }
    }

    public String getXml() {
        String aux = "<tree> <declarations> <attributeDecl name=\"name\" type=\"String\"/>  </declarations> \n";
        aux += xml;
        aux += " </tree>";
        return aux;
    }
}
