package com.jmmunoza.analisisnumerico.numericalmethods.lineal;

import com.jmmunoza.analisisnumerico.listeners.LinealResultListener;
import com.jmmunoza.analisisnumerico.numericalmethods.matrixoperations.Elementary;

public class Doolittle {
    public static double[] doolittle(double[][] A, double[] b, LinealResultListener listener) {
        if (A.length == A[0].length && A.length == b.length) {
            //
            double[][] U = new double[A.length][A.length];
            double[][] L = new double[A.length][A.length];
            for (int k = 0; k < L.length; k++) L[k][k] = 1;

            // Ui1 = Ai1/A11
            for (int i = 0; i < A.length; i++) L[i][0] = A[i][0]/A[0][0];

            // U1j = A1j
            for (int i = 0; i < A.length; i++) U[0][i] = A[0][i];

            for (int i = 1; i < A.length; i++) {
                for (int j = 1; j < A[0].length; j++) {
                    double sum = 0;
                    if (j >= i) {
                        for (int k = 0; k < j; k++) sum += L[i][k] * U[k][j];
                        U[i][j] = A[i][j] - sum;
                    } else {
                        for (int k = 0; k < i; k++) sum += L[i][k] * U[k][j];
                        L[i][j] = (A[i][j] - sum) / U[j][j];
                    }
                }
            }

            listener.onResultAdded(L, 1);
            listener.onResultAdded(U, 2);

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
