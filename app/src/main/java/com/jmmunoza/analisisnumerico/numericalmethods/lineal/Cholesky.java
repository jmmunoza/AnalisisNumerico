package com.jmmunoza.analisisnumerico.numericalmethods.lineal;

import com.jmmunoza.analisisnumerico.numericalmethods.matrixoperations.Elementary;

public class Cholesky {
    public static double[] cholesky(double[][] A, double[] b) {
        if (A.length == A[0].length && A.length == b.length) {
            //
            double[][] U = new double[A.length][A.length];
            double[][] L = new double[A.length][A.length];

            for(int i = 0; i < L.length; i++){
                for(int j = 0; j <= i; j++){
                    double sum = 0;
                    if(i == j){
                        for(int k = 0; k < i; k++) sum += L[i][k]*L[i][k];
                        L[i][j] = Math.sqrt(A[i][j] - sum);
                    } else {
                        for(int k = 0; k < j; k++) sum += L[j][k]*L[i][k];
                        L[i][j] = (A[i][j] - sum)/L[j][j];
                    }
                    U[j][i] = L[i][j];
                }
            }

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
