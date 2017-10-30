/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moduledefault.classify.knn;

import java.util.HashSet;
import java.util.Vector;
import java.util.concurrent.CancellationException;

/**
 *
 * @author evaristowb
 */
public class Knn {

    private static final String MANHATAM_DISTANCE_FUNCTION = "Manhatam";
    private static final String EUCLIDEAN_DISTANCE_FUNCTION = "Euclidiana";
    private static final String MAJORITY_VOTE = "Maioria por Votação";
    private static final String WEIGHT_BY_DISTANCE = "Peso pela Distância";
    public static final String[] DISTANCE_FUNCTION = {MANHATAM_DISTANCE_FUNCTION, EUCLIDEAN_DISTANCE_FUNCTION};
    public static final String[] INFERENCE_RULE = {MAJORITY_VOTE, WEIGHT_BY_DISTANCE};
    public static String distance_function = MANHATAM_DISTANCE_FUNCTION;
    public static String inference_rule = WEIGHT_BY_DISTANCE;
    public static int k_default = 1;
    private Vector input;
    private Vector output;
    private int k;

    public Knn() {
        input = new Vector();
        output = new Vector();
    }

    public void train(Object[][] input, Object[] output) {
        this.input.clear();
        this.output.clear();
        for (int i = 0; i < input.length; i++) {
            if (!this.input.contains(input[i])) {
                this.input.add(input[i]);
                this.output.add(output[i]);
            }
        }
    }

    public Object test(Object[] input) {
        Vector distance = new Vector();
        for (int i = 0; i < this.input.size(); i++) {
            distance.add(distance((Object[]) this.input.get(i), input));
        }
        sort(distance);
        return InferenceRule(distance);
    }

    private double distance(Object[] o1, Object[] o2) {
        if (distance_function.compareTo(MANHATAM_DISTANCE_FUNCTION) == 0) {
            return Manhatam(o1, o2);
        } else if (distance_function.compareTo(EUCLIDEAN_DISTANCE_FUNCTION) == 0) {
            return Euclidean(o1, o2);
        } else {
            return 0.0;
        }
    }

    private double Manhatam(Object[] o1, Object[] o2) {
        double d = 0.0;
        for (int i = 0; i < o1.length && i < o2.length; i++) {
            if(o1[i] instanceof Number)
//            try {
                d += Math.abs(new Double(o1[i].toString()) - new Double(o2[i].toString()));
//            } catch (NumberFormatException ex) {
//            }
        }
        return d;
    }

    private double Euclidean(Object[] o1, Object[] o2) {
        double d = 0.0;
        for (int i = 0; i < o1.length && i < o2.length; i++) {
            if(o1[i] instanceof Number)
//            try {
                d += Math.pow(new Double(o1[i].toString()) - new Double(o2[i].toString()), 2);
//            } catch (NumberFormatException ex) {
//            }
        }
        return Math.sqrt(d);
    }

    private void sort(Vector distance) {
        QuickSort(distance, 0, distance.size() - 1);
    }

    private void QuickSort(Vector v, int Lo, int Hi) {
        int i = Lo;
        int j = Hi;
        double H = 0;
//        System.out.println(v.get((Lo + Hi) / 2).toString());
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

                Object o = input.get(i);
                input.set(i, input.get(j));
                input.set(j, o);

                o = output.get(i);
                output.set(i, output.get(j));
                output.set(j, o);

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

    private Object InferenceRule(Vector distance) {
        if (inference_rule.compareTo(MAJORITY_VOTE) == 0) {
            return majoriyVote(distance);
        } else if (inference_rule.compareTo(WEIGHT_BY_DISTANCE) == 0) {
            return weightByDistance(distance);
        } else {
            return null;
        }
    }

    private Object majoriyVote(Vector distance) {
        Vector v = new Vector();
        for (int i = 0; i < output.size(); i++) {
            if (!v.contains(output.get(i))) {
                v.add(output.get(i));
            }
        }
        int vote[] = new int[v.size()];
        for (int i = 0; i < distance.size() && i < k; i++) {
            vote[v.indexOf(output.get(i))]++;
        }
        int max = 0;
        Object c = null;
        for (int i = 0; i < vote.length; i++) {
            if (i == 0 || vote[i] > max) {
                max = vote[i];
                c = v.get(i);
            }
        }
        return c;
    }

    private Object weightByDistance(Vector distance) {
        Vector v = new Vector();
        for (int i = 0; i < output.size(); i++) {
            if (!v.contains(output.get(i))) {
                v.add(output.get(i));
            }
        }
        double weight[] = new double[v.size()];
        for (int i = 0; i < output.size() && i < k; i++) {
            weight[v.indexOf(output.get(i))] +=
                    ((Double) distance.get(k - 1) - (Double) distance.get(i))
                    / ((Double) distance.get(k - 1) - (Double) distance.get(0));
        }
        double max = 0;
        Object c = null;
        for (int i = 0; i < weight.length; i++) {
            if (i == 0 || weight[i] > max) {
                max = weight[i];
                c = v.get(i);
            }
        }
        return c;
    }

    public String getDistanceFunction() {
        return distance_function;
    }

    public void setDistanceFunction(String distance_function) {
        this.distance_function = distance_function;
    }

    public String getInferenceRule() {
        return inference_rule;
    }

    public void setInferenceRule(String inference_rule) {
        this.inference_rule = inference_rule;
    }

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }
}
