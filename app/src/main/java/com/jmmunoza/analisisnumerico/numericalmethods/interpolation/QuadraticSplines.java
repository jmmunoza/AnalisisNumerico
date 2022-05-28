package com.jmmunoza.analisisnumerico.numericalmethods.interpolation;

import com.jmmunoza.analisisnumerico.numericalmethods.lineal.CompletePivoting;

public class QuadraticSplines {
    public static double[] quadraticSplines(double[] x, double[] y, double[] x_values){
        if(x.length == y.length){
            // Creado y resolviendo la matriz
            double[][] A = new double[3*(x.length-1)][3*(x.length-1)];
            double[]   b = new double[3*(x.length-1)];
            for(int i = 0; i < x.length-1; i++){
                A[i*2  ][i*3]   = x[i]*x[i];
                A[i*2+1][i*3]   = x[i+1]*x[i+1];
                A[i*2  ][i*3+1] = x[i];
                A[i*2+1][i*3+1] = x[i+1];
                A[i*2  ][i*3+2] = 1;
                A[i*2+1][i*3+2] = 1;
            }

            for(int i = 0; i < x.length-2; i++){
                A[2*x.length  - 2 + i][i*3]     = 2*x[i+1];
                A[2*x.length  - 2 + i][i*3+1]   = 1;
                A[2*x.length  - 2 + i][i*3 + 3] = -2*x[i+1];
                A[2*x.length  - 2 + i][i*3+4]   = -1;
            }

            for(int i = 0; i < y.length; i++){
                if(i == 0){
                    b[0] = y[0];
                } else if(i == y.length -1){
                    b[b.length - y.length] = y[y.length-1];
                } else {
                    b[2 * i - 1] = y[i];
                    b[2 * i]     = y[i];
                }
            }

            // Cutting the matrix A and the vector b
            double[][] newA = new double[A.length-1][A.length-1];
            double[]   newB = new double[b.length-1];
            for(int i = 0; i < A.length - 1; i++){
                for(int j = 1; j < A.length; j++){
                    newA[i][j-1] = A[i][j];
                }
            }

            for(int i = 0; i < b.length - 1; i++) newB[i] = b[i];

            return CompletePivoting.pivoting(newA, newB);
        }
        return null;
    }
}
