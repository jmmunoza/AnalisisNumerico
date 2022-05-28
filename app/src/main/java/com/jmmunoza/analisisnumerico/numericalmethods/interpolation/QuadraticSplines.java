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
                System.arraycopy(A[i], 1, newA[i], 0, A.length - 1);
            }

            if (b.length - 1 >= 0) System.arraycopy(b, 0, newB, 0, b.length - 1);

            double[] coefficients = CompletePivoting.pivoting(newA, newB);
            double[] newCoefficients = new double[A.length];
            System.arraycopy(coefficients, 0, newCoefficients, 1, coefficients.length);

            double[] results = new double[x_values.length];
            for(int i = 0; i < x_values.length; i++){
                for(int j = 0; j < x.length - 1; j++){
                    if(x_values[i] == x[j]){
                        results[i] = y[j];
                        break;
                    } else if(x_values[i] == x[j+1]){
                        results[i] = y[j+1];
                        break;
                    } else if(x_values[i] > x[j] && x_values[i] < x[j+1]){
                        results[i] += newCoefficients[j*3]   * x_values[i] * x_values[i];
                        results[i] += newCoefficients[j*3+1] * x_values[i];
                        results[i] += newCoefficients[j*3+2] ;
                        break;
                    } else if(x_values[i] < x[0]){
                        results[i] += newCoefficients[0] * x_values[i] * x_values[i];
                        results[i] += newCoefficients[1] * x_values[i];
                        results[i] += newCoefficients[2];
                        break;
                    } else if(x_values[i] > x[x.length-1]){
                        results[i] += newCoefficients[newCoefficients.length-3] * x_values[i] * x_values[i];
                        results[i] += newCoefficients[newCoefficients.length-2] * x_values[i];
                        results[i] += newCoefficients[newCoefficients.length-1];
                        break;
                    }
                }
            }

            return results;
        }
        return null;
    }
}
