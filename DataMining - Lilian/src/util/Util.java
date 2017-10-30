/*
 * Enum.java
 * Created on 17 de Maio de 2008, 19:50
 *
 */
package util;

import java.util.Vector;

/**
 *
 * @author Rafael
 * @version
 */
public abstract class Util {

    /**
     * return the class
     * @param output
     * @return classes
     */
    public static Vector nClasses(Object[] output) {
        Vector classes = new Vector();
        for (int i = 0; i < output.length; i++) {
            if (!classes.contains(output[i])) {
                classes.add(output[i]);
                System.out.println(output[i]);
            }
        }
        Util.QuickSort(classes, 0, classes.size() - 1);
        return classes;
    }


//
//    public static void setCombo(JComboBox jCombo) {
//        String[] bancos = new String[1];
//        bancos[0] = "PostgreSQL";
//
//        for (int i = 0; i < bancos.length; i++) {
//            //String enumBaseDados = bancos[i].toString().replaceAll("_", " (") + ")";
//            jCombo.addItem(bancos[i]);
//        }
//    }

    /**
     * write 
     * @param c
     * @return
     */
    public static String toString(char[] c) {
        String s = new String();
        for (int i = 0; i < c.length; i++) {
            s += c[i] + "";
        }
        return s;
    }

    /*    public static EnumBaseDados StringToEnumBaseDados(String selectedItem) {
    EnumBaseDados ebd = null;
    EnumBaseDados[] ebd2 = ebd.values();
    for (int i = 0; i < ebd2.length; i++) {
    String enumBaseDados = ebd2[i].toString().replaceAll("_", " (") + ")";
    if (enumBaseDados.compareTo(selectedItem) == 0) {
    return ebd2[i];
    }
    }
    return null;
    }*/


    /**
     * sorting data
     * @param v vector to be ordained
     * @param Lo
     * @param Hi
     */
    public static void QuickSort(Vector v, int Lo, int Hi) {
        int i = Lo;
        int j = Hi;
        double H = 0;
        System.out.println(v.get((Lo + Hi) / 2).toString());
        double x = Double.parseDouble(v.get((Lo + Hi) / 2).toString());
        do {
            while (Double.parseDouble(v.get(i).toString()) < x) {
                i++;
            }
            while (Double.parseDouble(v.get(j).toString()) > x) {
                j--;
            }
            if (i <= j) {
                H = Double.parseDouble(v.get(i).toString());
                v.set(i, v.get(j));
                v.set(j, H);
                i++;
                j--;
            }
        } while (i <= j);

        //passo recursivo
        if (Lo < j) {
            QuickSort(v, Lo, j);
        }
        if (i < Hi) {
            QuickSort(v, i, Hi);
        }
    }

    /**
     * returns hour, minute, second and millisecond
     * @param milisegundo - millisecond
     * @return string with the time
     */
    public static String getHoraMinSegMiliSeg(long milisegundo) {
        long hora = 0;
        long min = 0;
        long seg = 0;
        long miliseg = 0;

        String str = new String();

        hora = milisegundo / 3600000;
        milisegundo = milisegundo % 3600000;

        min = milisegundo / 60000;
        milisegundo = milisegundo % 60000;

        seg = milisegundo / 1000;

        miliseg = milisegundo % 1000;

        if (hora == 0) {
            if (min == 0) {
                if (seg == 0) {
                    str = String.format("%dms", miliseg);
                } else {
                    str = String.format("%ds:%03dms", seg, miliseg);
                }

            } else {
                str = String.format("%dmin:%02ds:%03dms", min, seg, miliseg);
            }

        } else {
            str = String.format("%dhs:%02dmin:%02ds:%03dms", hora, min, seg, miliseg);
        }

        return str;
    }

    /**
     * normalized vector
     * @param input - vector to be normalized
     * @return normalized vector
     */
    public static double[][] normaliza(double[][] input) {
        for (int i = 0; i < input.length; i++) {
            double[] elementos = mMElemento(input[i]);
            double maiorElemento = elementos[0];
            double menorElemento = elementos[1];
            for (int j = 0; j < input[i].length; j++) {
                input[i][j] = (input[i][j] - menorElemento) / (maiorElemento - menorElemento);
            }
        }
        return input;
    }

    /**
     * returns the largest and the smallest element in column
     * @param coluna - column you want to know the values
     * @return - vector with the largest and the smallest element in column
     */
    public static double[] mMElemento(double[] coluna) {
        double maiorElemento = 0;
        double menorElemento = Double.MAX_VALUE;
        for (int j = 0; j < coluna.length; j++) {
            if (coluna[j] < menorElemento) {
                menorElemento = coluna[j];
            }
            if (coluna[j] > maiorElemento) {
                maiorElemento = coluna[j];
            }
        }
        double[] elementos = {maiorElemento, menorElemento};
        return elementos;
    }

    /**
     * formats the data     base
     * @param str
     * @return
     */
    public static String formatDataBase(String str){
        if(str.compareTo(str.toLowerCase()) != 0)
            return "\"" + str + "\"";
        for(int i=0;i<str.length();i++)
            if(!Character.isLetterOrDigit(str.charAt(i)) && str.charAt(i) != '_')
                return "\"" + str + "\"";
        return str;
    }
    
    public static void padronizacao(double matriz[][]) {
        double menor = 0;
        double maior = 0;
        int cont = 0;
        while (cont < matriz[0].length) {
            menor = 10000;
            maior = -10000;
            for (int i = 0; i < matriz.length; i++) {
                if (matriz[i][cont] < menor) {
                    menor = matriz[i][cont];
                }

                if (matriz[i][cont] > maior) {
                    maior = matriz[i][cont];
                }
            }
            for (int i = 0; i < matriz.length; i++) {
                matriz[i][cont] -= menor;
            }
            maior -= menor;
            for (int i = 0; i < matriz.length; i++) {
                matriz[i][cont] /= maior;
            }
            cont++;
        }
    }
}
