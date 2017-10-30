/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moduledefault.clustering.visualization.panels;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import moduledefault.clustering.kmeans.Centroide;
import moduledefault.clustering.uteis.Base;
import moduledefault.clustering.view.jpanel.PanelKmeans;

/**
 *
 * @author Mateus
 */
public class KmeansSimulacao extends javax.swing.JPanel {

    /**
     * Creates new form KmeansSimulacao
     */
    ArrayList<Centroide> centroides;
    int[] padroesClusters;
    Base dados;
    private ArrayList<Color> cores;

    public KmeansSimulacao() {
        initComponents();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = PanelKmeans.getPanelSimulacao().getWidth();
        int height = PanelKmeans.getPanelSimulacao().getHeight();
        float w = width / 2;
        float h = height / 2;
        int x2 = 50 + (int) 0 * (width - 120);
        int x0 = 50 + (int) 0 * (width - 120);
        int x1 = 50 + (int) 1 * (width - 120) + 3;
        int tamPixel = (x1 - x0) / dados.getDataSet().size();
        int y0 = (height - 70) - (int) 0 * (height - 120) + tamPixel;
        int y1 = (height - 70) - (int) 0 * (height - 120) + tamPixel;
        int y2 = (height - 70) - (int) 1 * (height - 120) - tamPixel;
        iniciaVetorCores();
        if (dados != null) {
            if (padroesClusters != null && centroides != null) {
                g.setColor(Color.black);
                g.drawLine(x0, y0, x1, y1);//x
                g.drawLine(x0, y0, x2, y2);//y
                //escala para y
                int aux = 0;
                int escala = (int) (y0 - y2) / 10;
                int soma = y2;
                String[] valores1 = {"1", "0.9", "0.8", "0.7", "0.6", "0.5", "0.4", "0.3", "0.2", "0.1", "0"};
                while (soma <= y0) {
                    if (valores1[aux] != "0") {
                        g.drawLine(x0 - 5, soma, x0 + 5, soma);
                    }
                    g.drawString(valores1[aux], x0 - 25, soma + 5);
                    aux++;
                    soma += escala;
                }
                //escala para x
                aux = 0;
                escala = (int) (x1 - x0) / 10;
                soma = x0;
                String[] valores2 = {"0", "0.1", "0.2", "0.3", "0.4", "0.5", "0.6", "0.7", "0.8", "0.9", "1"};
                while (soma <= x1) {
                    g.drawLine(soma, y0 - 5, soma, y0 + 5);
                    g.drawString(valores2[aux], soma - 7, y0 + 20);
                    aux++;
                    soma += escala;
                }

                for (int i = 0; i < centroides.size(); i++) {
                    //pintar Centroide
                    int x = 50 + (int) (centroides.get(i).getAtributos().get(0) * (width - 120));
                    int y = (height - 70) - (int) (centroides.get(i).getAtributos().get(1) * (height - 120));
                    g.setColor(cores.get(i));
                    g.fillOval(x, y, tamPixel + 5, tamPixel + 5);
                }

                /////////////////////////////////////////////////////////////////

                //ploto pontos
                for (int i = 0; i < padroesClusters.length; i++) {
                    int x = 50 + (int) (dados.getDataSet().get(i).getAtributos().get((0)) * (width - 120));
                    int y = (height - 70) - (int) (dados.getDataSet().get(i).getAtributos().get((1)) * (height - 120));
                    g.setColor(cores.get(padroesClusters[i]));
                    g.fillOval(x, y, tamPixel + 2, tamPixel + 2);
                }
            }
        }
    }

    private void iniciaVetorCores() {
        cores = new ArrayList<>();
        cores.add(Color.RED);
        cores.add(Color.BLUE);
        cores.add(Color.GREEN);
        cores.add(Color.YELLOW);
        cores.add(Color.ORANGE);
        cores.add(Color.MAGENTA);
        cores.add(new Color(130, 232, 163));
        cores.add(new Color(213, 22, 183));
        cores.add(new Color(169, 180, 52));
        cores.add(new Color(213, 95, 93));
        cores.add(new Color(230, 25, 140));
        cores.add(new Color(100, 63, 240));
        cores.add(new Color(181, 95, 9));
        cores.add(new Color(73, 148, 124));
        cores.add(new Color(35, 31, 20));
        cores.add(new Color(0, 204, 27));
        cores.add(new Color(7, 73, 106));
        cores.add(new Color(146, 87, 204));
        cores.add(new Color(254, 59, 3));
        cores.add(new Color(155, 197, 79));
        cores.add(new Color(193, 42, 211));
        cores.add(new Color(77, 49, 85));
        cores.add(new Color(2, 184, 186));
        cores.add(new Color(178, 35, 209));
        cores.add(new Color(151, 39, 76));
        cores.add(new Color(51, 84, 125));
        cores.add(new Color(7, 31, 105));
        cores.add(new Color(108, 218, 92));
        cores.add(new Color(13, 241, 51));
        cores.add(new Color(211, 99, 134));
        cores.add(new Color(41, 22, 149));
        cores.add(new Color(142, 221, 111));
        cores.add(new Color(173, 98, 79));
        cores.add(new Color(99, 150, 164));
        cores.add(new Color(5, 17, 239));
        cores.add(new Color(55, 117, 211));
        cores.add(new Color(65, 53, 45));
        cores.add(new Color(92, 167, 195));
        cores.add(new Color(240, 54, 30));
        cores.add(new Color(8, 139, 102));
        cores.add(new Color(31, 8, 219));
        cores.add(new Color(181, 128, 206));
        cores.add(new Color(241, 180, 70));
        cores.add(new Color(55, 84, 23));
        cores.add(new Color(31, 93, 36));
        cores.add(new Color(127, 128, 37));
        cores.add(new Color(32, 236, 223));
        cores.add(new Color(43, 79, 144));
        cores.add(new Color(123, 31, 126));
        cores.add(new Color(217, 248, 241));
        cores.add(new Color(125, 9, 207));
        cores.add(new Color(15, 232, 28));
        cores.add(new Color(152, 196, 47));
        cores.add(new Color(27, 239, 234));
        cores.add(new Color(188, 65, 254));
        cores.add(new Color(27, 54, 202));
        cores.add(new Color(115, 167, 153));
        cores.add(new Color(122, 114, 45));
        cores.add(new Color(24, 94, 21));
        cores.add(new Color(149, 60, 95));
        cores.add(new Color(187, 243, 65));
        cores.add(new Color(178, 252, 32));
        cores.add(new Color(42, 83, 92));
        cores.add(new Color(10, 250, 94));
        cores.add(new Color(14, 100, 100));
        cores.add(new Color(128, 148, 127));
        cores.add(new Color(93, 122, 60));
        cores.add(new Color(190, 8, 167));
        cores.add(new Color(188, 101, 52));
        cores.add(new Color(247, 187, 46));
        cores.add(new Color(11, 193, 231));
        cores.add(new Color(72, 16, 178));
        cores.add(new Color(24, 131, 45));
        cores.add(new Color(234, 211, 69));
        cores.add(new Color(75, 172, 5));
        cores.add(new Color(140, 194, 146));
        cores.add(new Color(129, 63, 141));
        cores.add(new Color(26, 153, 219));
        cores.add(new Color(59, 226, 131));
        cores.add(new Color(51, 128, 142));
        cores.add(new Color(241, 233, 152));
        cores.add(new Color(141, 89, 76));
        cores.add(new Color(75, 207, 10));
        cores.add(new Color(55, 130, 237));
        cores.add(new Color(129, 212, 209));
        cores.add(new Color(39, 254, 17));
        cores.add(new Color(204, 24, 159));
        cores.add(new Color(143, 248, 141));
        cores.add(new Color(201, 101, 136));
        cores.add(new Color(248, 14, 126));
        cores.add(new Color(86, 80, 97));
        cores.add(new Color(131, 17, 5));
        cores.add(new Color(210, 247, 58));
        cores.add(new Color(170, 26, 84));
        cores.add(new Color(52, 13, 124));
        cores.add(new Color(165, 103, 160));
        cores.add(new Color(139, 102, 19));
        cores.add(new Color(97, 168, 127));
        cores.add(new Color(187, 97, 172));
        cores.add(new Color(210, 237, 162));
        cores.add(new Color(93, 214, 156));
        cores.add(new Color(162, 197, 20));
        cores.add(new Color(51, 233, 71));
        cores.add(new Color(232, 47, 74));
        cores.add(new Color(234, 175, 37));
        cores.add(new Color(74, 93, 98));
        cores.add(new Color(223, 48, 241));
        cores.add(new Color(96, 211, 14));
        cores.add(new Color(198, 136, 136));
        cores.add(new Color(212, 163, 33));
        cores.add(new Color(63, 35, 100));
        cores.add(new Color(13, 234, 108));
        cores.add(new Color(36, 96, 116));
        cores.add(new Color(63, 196, 70));
        cores.add(new Color(205, 237, 192));
        cores.add(new Color(121, 31, 102));
        cores.add(new Color(76, 204, 16));
        cores.add(new Color(247, 80, 8));
        cores.add(new Color(196, 57, 208));
        cores.add(new Color(142, 18, 80));
        cores.add(new Color(225, 233, 153));
        cores.add(new Color(76, 122, 147));
        cores.add(new Color(6, 157, 252));
        cores.add(new Color(23, 8, 50));
        cores.add(new Color(26, 175, 170));
        cores.add(new Color(128, 106, 17));
        cores.add(new Color(184, 71, 234));
        cores.add(new Color(115, 195, 71));
        cores.add(new Color(121, 9, 94));
        cores.add(new Color(114, 123, 18));
        cores.add(new Color(248, 30, 239));
        cores.add(new Color(203, 223, 157));
        cores.add(new Color(202, 97, 99));
        cores.add(new Color(69, 206, 176));
        cores.add(new Color(112, 115, 145));
        cores.add(new Color(131, 21, 221));
        cores.add(new Color(118, 86, 251));
        cores.add(new Color(33, 217, 18));
        cores.add(new Color(228, 111, 164));
        cores.add(new Color(226, 211, 201));
        cores.add(new Color(40, 86, 249));
        cores.add(new Color(15, 35, 238));
        cores.add(new Color(6, 195, 131));
        cores.add(new Color(147, 125, 235));
        cores.add(new Color(143, 193, 245));
        cores.add(new Color(66, 117, 181));
        cores.add(new Color(57, 178, 254));
        cores.add(new Color(186, 91, 28));
        cores.add(new Color(156, 70, 18));
        cores.add(new Color(239, 200, 35));
        cores.add(new Color(141, 166, 46));
        cores.add(new Color(61, 25, 95));
        cores.add(new Color(78, 237, 85));
        cores.add(new Color(184, 123, 26));
        cores.add(new Color(66, 110, 120));
        cores.add(new Color(246, 49, 97));
        cores.add(new Color(165, 248, 229));
        cores.add(new Color(42, 71, 62));
        cores.add(new Color(104, 57, 22));
        cores.add(new Color(251, 233, 216));
        cores.add(new Color(222, 238, 82));
        cores.add(new Color(44, 75, 14));
        cores.add(new Color(146, 138, 228));
        cores.add(new Color(199, 11, 56));
        cores.add(new Color(151, 27, 194));
        cores.add(new Color(40, 199, 244));
        cores.add(new Color(235, 63, 241));
        cores.add(new Color(122, 55, 61));
        cores.add(new Color(243, 187, 218));
        cores.add(new Color(183, 102, 36));
        cores.add(new Color(190, 9, 192));
        cores.add(new Color(16, 244, 114));
        cores.add(new Color(250, 113, 198));
        cores.add(new Color(216, 204, 64));
        cores.add(new Color(119, 101, 177));
        cores.add(new Color(98, 6, 160));
        cores.add(new Color(84, 232, 30));
        cores.add(new Color(134, 134, 113));
        cores.add(new Color(212, 208, 16));
        cores.add(new Color(154, 215, 49));
        cores.add(new Color(225, 162, 131));
        cores.add(new Color(149, 244, 216));
        cores.add(new Color(173, 195, 135));
        cores.add(new Color(103, 235, 227));
        cores.add(new Color(173, 196, 220));
        cores.add(new Color(183, 45, 142));
        cores.add(new Color(24, 251, 30));
        cores.add(new Color(129, 63, 98));
        cores.add(new Color(163, 191, 35));
        cores.add(new Color(108, 175, 73));
        cores.add(new Color(10, 64, 102));
        cores.add(new Color(17, 35, 153));
        cores.add(new Color(154, 2, 245));
        cores.add(new Color(52, 117, 229));
        cores.add(new Color(57, 79, 239));
        cores.add(new Color(69, 236, 28));
        cores.add(new Color(155, 194, 188));
        cores.add(new Color(114, 166, 21));
        cores.add(new Color(53, 51, 95));
        cores.add(new Color(102, 132, 197));
        cores.add(new Color(92, 175, 119));
        cores.add(new Color(219, 203, 44));
        cores.add(new Color(12, 44, 183));
        cores.add(new Color(99, 24, 190));
        cores.add(new Color(178, 108, 93));
        cores.add(new Color(121, 177, 12));
        cores.add(new Color(118, 102, 15));
        cores.add(new Color(154, 96, 26));
        cores.add(new Color(199, 43, 23));
        cores.add(new Color(101, 186, 137));
        cores.add(new Color(132, 235, 31));
        cores.add(new Color(211, 50, 178));
        cores.add(new Color(49, 158, 41));
        cores.add(new Color(57, 180, 170));
        cores.add(new Color(56, 181, 153));
        cores.add(new Color(205, 106, 102));
        cores.add(new Color(236, 9, 43));
        cores.add(new Color(100, 236, 31));
        cores.add(new Color(46, 161, 218));
        cores.add(new Color(187, 13, 73));
        cores.add(new Color(59, 204, 32));
        cores.add(new Color(182, 218, 4));
        cores.add(new Color(180, 75, 233));
        cores.add(new Color(239, 139, 131));
        cores.add(new Color(49, 97, 148));
        cores.add(new Color(62, 176, 232));
        cores.add(new Color(9, 23, 150));
        cores.add(new Color(38, 41, 85));
        cores.add(new Color(4, 126, 223));
        cores.add(new Color(103, 84, 76));
        cores.add(new Color(2, 136, 83));
        cores.add(new Color(56, 108, 97));
        cores.add(new Color(186, 184, 44));
        cores.add(new Color(156, 61, 86));
        cores.add(new Color(10, 19, 3));
        cores.add(new Color(0, 193, 29));
        cores.add(new Color(85, 22, 39));
        cores.add(new Color(35, 113, 126));
        cores.add(new Color(12, 118, 144));
        cores.add(new Color(73, 48, 7));
        cores.add(new Color(181, 192, 246));
        cores.add(new Color(148, 220, 126));
        cores.add(new Color(229, 108, 246));
        cores.add(new Color(103, 98, 99));
        cores.add(new Color(81, 47, 11));
        cores.add(new Color(58, 157, 71));
        cores.add(new Color(26, 145, 139));
        cores.add(new Color(5, 110, 63));
        cores.add(new Color(81, 46, 201));
        cores.add(new Color(248, 15, 109));
        cores.add(new Color(21, 55, 242));
        cores.add(new Color(148, 164, 64));
        cores.add(new Color(212, 68, 151));
        cores.add(new Color(31, 47, 157));
        cores.add(new Color(22, 127, 230));
        cores.add(new Color(207, 63, 148));
        cores.add(new Color(20, 39, 174));
        cores.add(new Color(127, 62, 205));
        cores.add(new Color(53, 129, 24));
        cores.add(new Color(123, 145, 138));
        cores.add(new Color(121, 57, 15));
        cores.add(new Color(167, 169, 184));
        cores.add(new Color(64, 198, 49));
        cores.add(new Color(199, 226, 214));
        cores.add(new Color(145, 145, 122));
        cores.add(new Color(5, 51, 127));
        cores.add(new Color(146, 158, 11));
        cores.add(new Color(236, 100, 33));
        cores.add(new Color(51, 118, 45));
        cores.add(new Color(25, 164, 157));
        cores.add(new Color(252, 195, 209));
        cores.add(new Color(27, 66, 171));
        cores.add(new Color(86, 233, 140));
        cores.add(new Color(2, 247, 150));
        cores.add(new Color(51, 247, 165));
        cores.add(new Color(228, 35, 207));
        cores.add(new Color(32, 88, 4));
        cores.add(new Color(53, 193, 128));
        cores.add(new Color(145, 21, 217));
        cores.add(new Color(37, 31, 57));
        cores.add(new Color(246, 217, 63));
        cores.add(new Color(55, 24, 232));
        cores.add(new Color(165, 100, 77));
        cores.add(new Color(158, 18, 17));
        cores.add(new Color(103, 146, 191));
        cores.add(new Color(169, 89, 111));
        cores.add(new Color(10, 92, 150));
        cores.add(new Color(64, 185, 199));
        cores.add(new Color(145, 101, 213));
        cores.add(new Color(233, 220, 208));
        cores.add(new Color(88, 126, 70));
        cores.add(new Color(60, 37, 85));
        cores.add(new Color(14, 226, 3));
        cores.add(new Color(55, 186, 111));
        cores.add(new Color(159, 42, 111));
        cores.add(new Color(140, 192, 178));
        cores.add(new Color(174, 62, 148));
        cores.add(new Color(143, 185, 203));
        cores.add(new Color(124, 92, 125));
        cores.add(new Color(78, 98, 137));
    }

    public ArrayList<Centroide> getCentroides() {
        return centroides;
    }

    public void setCentroides(ArrayList<Centroide> _centroides) {
        centroides = _centroides;
    }

    public int[] getPadroesClusters() {
        return padroesClusters;
    }

    public void setPadroesClusters(int[] _padroesClusters) {
        padroesClusters = _padroesClusters;
        this.repaint();
    }

    public void setDados(Base d) {
        dados = d;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
