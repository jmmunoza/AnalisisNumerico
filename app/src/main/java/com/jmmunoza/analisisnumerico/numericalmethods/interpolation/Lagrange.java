package com.jmmunoza.analisisnumerico.numericalmethods.interpolation;

public class Lagrange {
    public static double[] lagrange(double[] x, double[] y, double[] x_values){
        if(x.length == y.length){
            double[] results = new double[x_values.length];
            for(int i = 0; i < x_values.length; i++) {
                // Evaluando cada x_value

                // Polinomio de lagrange
                double L = 0;
                for(int j = 0; j < x.length; j++){
                    // Bases polinomicas de Lagrange
                    double l = 1;
                    for(int k = 0; k < x.length; k++){
                        if(k != j)
                            l *= (x_values[i] - x[k])/(x[j] - x[k]);
                    }
                    L += l * y[j];
                }

                results[i] = L;
            }

            return results;
        }
        return null;
    }
}
