package com.jmmunoza.analisisnumerico.numericalmethods.lineal;

import com.jmmunoza.analisisnumerico.numericalmethods.matrixoperations.Elementary;

public class PartialPivoting {
    public static double[] pivoting(double[][] A, double[] b){
        if(A.length >= 1 && A.length + 1 == A[0].length){
            for(int k = 0; k < A.length-1; k++) {
                // buscar valor mas alto y su posicion
                double max = Math.abs(A[k][k]);
                int maxPos = k;
                for(int i = k; i < A.length; i++){
                    if(max < Math.abs(A[i][k])) {
                        max    = Math.abs(A[i][k]);
                        maxPos = i;
                    }
                }

                A = Elementary.changeRow(A, k, maxPos);

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
