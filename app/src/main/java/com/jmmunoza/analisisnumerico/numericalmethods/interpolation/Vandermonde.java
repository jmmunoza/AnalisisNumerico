package com.jmmunoza.analisisnumerico.numericalmethods.interpolation;

import com.jmmunoza.analisisnumerico.listeners.LinealResultListener;
import com.jmmunoza.analisisnumerico.numericalmethods.lineal.CompletePivoting;

public class Vandermonde {
    public static double[] vandermonde(double[] x, double[] y, double[] x_values){
        if(x.length == y.length){
            // Creado y resolviendo la matriz
            double[][] A = new double[x.length][x.length];
            for(int i = 0; i < x.length; i++){
                for(int j = 0; j < x.length; j++){
                    A[i][j] = Math.pow(x[i], x.length - j - 1);
                }
            }
            double[] polynomial = CompletePivoting.pivoting(A, y, (A1, k) -> {

            });

            // Evaluando el polinomio
            double[] results    = new double[x_values.length];
            for(int i = 0; i < x_values.length; i++){
                for(int j = 0; j < polynomial.length; j++){
                    results[i] += polynomial[j] * Math.pow(x_values[i], polynomial.length - j - 1);
                }
            }
            return results;
        }
        return null;
    }
}
