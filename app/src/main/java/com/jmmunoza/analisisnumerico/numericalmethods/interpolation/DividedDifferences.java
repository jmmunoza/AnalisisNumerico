package com.jmmunoza.analisisnumerico.numericalmethods.interpolation;

public class DividedDifferences {
    public static double[] dividedDifferences(double[] x, double[] y, double[] x_values){
        if(x.length == y.length){
            double[][] A = new double[x.length][x.length + 1];
            for(int i = 0; i < x.length; i++){
                A[i][0] = x[i];
                A[i][1] = y[i];
            }

            double[] coefficients = new double[A.length];
            coefficients[0]       = y[0];

            for(int j = 2; j < A[0].length; j++){
                for(int i = j - 1; i < A.length; i++){
                    A[i][j] = (A[i][j-1] - A[i-1][j-1])/(A[i][0] - A[i-j+1][0]);
                    if(i == j - 1) coefficients[i] = A[i][j];
                }
            }

            double[] results = new double[x_values.length];
            for(int i = 0; i < x_values.length; i++){
                for(int j = 0; j < coefficients.length; j++){
                    // (x-x0)(x-x1)(x-x2)(x-x3)(x-x4)
                    double product = 1;
                    for(int k = 0; k < j; k++){
                        product *= x_values[i] - x[k];
                    }
                    results[i] += coefficients[j] * product;
                }
            }

            return results;
        }
        return null;
    }
}
