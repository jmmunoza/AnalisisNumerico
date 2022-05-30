package com.jmmunoza.analisisnumerico.numericalmethods.lineal;

import com.jmmunoza.analisisnumerico.listeners.LinealResultListener;
import com.jmmunoza.analisisnumerico.numericalmethods.matrixoperations.Elementary;

public class Crout {
    public static double[] crout(double[][] A, double[] b, LinealResultListener listener){
        if(A.length == A[0].length && A.length == b.length){

            double[][] U = new double[A.length][A.length];
            double[][] L = new double[A.length][A.length];
            for(int k = 0; k < U.length; k++) U[k][k] = 1;

            // Li1 = Ai1
            for(int i = 0; i < A.length; i++) L[i][0] = A[i][0];

            // U1j = A1j/A11
            for(int j = 0; j < A.length; j++) U[0][j] = A[0][j]/A[0][0];

            for(int i = 1; i < A.length; i++){
                for(int j = 1; j < A[0].length; j++){
                    double sum = 0;
                    if(i >= j){
                        for(int k = 0; k < j; k++) sum += L[i][k]*U[k][j];
                        L[i][j] = A[i][j] - sum;
                    } else {
                        for(int k = 0; k < i; k++) sum += L[i][k]*U[k][j];
                        U[i][j] = (A[i][j] - sum)/L[i][i];
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
