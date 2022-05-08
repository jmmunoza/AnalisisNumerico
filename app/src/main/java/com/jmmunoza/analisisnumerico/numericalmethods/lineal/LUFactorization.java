package com.jmmunoza.analisisnumerico.numericalmethods.lineal;

import com.jmmunoza.analisisnumerico.numericalmethods.matrixoperations.Elementary;

public class LUFactorization {
    public static double[] LU(double[][] A, double[] b) {
        if(A.length == A[0].length && A.length == b.length){
            // Creating L
            double[][] L = new double[A.length][A.length];
            for(int i = 0; i < L.length; i++) L[i][i] = 1;
            for(int k = 0; k < A.length; k++) {
                for(int i = k + 1; i < A.length; i++) {
                    double m = A[i][k]/A[k][k];
                    L[i][k] = m;
                    A[i][k] = 0;
                    for(int j = k + 1; j < A[0].length; j++) {
                        A[i][j] -= A[k][j] * m;
                    }
                }
            }

            // Creating U
            double[][] U = A;

            // creating Lb extended
            double[][] Lb = Elementary.createAugmentedMatrix(L, b);

            // solving Lz = b
            double[] z = Substitution.progressive(Lb);

            // creating Uz extended
            double[][] Uz = Elementary.createAugmentedMatrix(U, z);

            // solving Ux = z
            double[] x = Substitution.backward(Uz);

            return x;
        }

        return null;
    }
}
