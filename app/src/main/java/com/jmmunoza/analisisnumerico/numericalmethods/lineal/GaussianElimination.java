package com.jmmunoza.analisisnumerico.numericalmethods.lineal;

public class GaussianElimination {
    public static double[] gauss(double[][] A, double[] b) {
        if(A.length >= 1 && A.length + 1 == A[0].length){
            for(int k = 0; k < A.length-1; k++) {
                for(int i = k + 1; i < A.length; i++) {
                    double m = A[i][k]/A[k][k];
                    A[i][k] = 0;
                    for(int j = k + 1; j < A[0].length; j++) {
                        A[i][j] -= A[k][j] * m;
                    }
                }
            }

            return Substitution.backward(A);
        }

        return null;
    }
}
