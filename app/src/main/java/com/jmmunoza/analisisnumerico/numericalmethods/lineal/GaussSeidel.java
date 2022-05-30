package com.jmmunoza.analisisnumerico.numericalmethods.lineal;

import com.jmmunoza.analisisnumerico.listeners.LinealResultsSpecialListener;
import com.jmmunoza.analisisnumerico.numericalmethods.matrixoperations.Addition;
import com.jmmunoza.analisisnumerico.numericalmethods.matrixoperations.Elementary;
import com.jmmunoza.analisisnumerico.numericalmethods.matrixoperations.Inverted;
import com.jmmunoza.analisisnumerico.numericalmethods.matrixoperations.Multiplication;
import com.jmmunoza.analisisnumerico.numericalmethods.matrixoperations.Subtraction;

import java.util.Arrays;

public class GaussSeidel {
    public static double[] gaussSeidel(double[][] A, double[] b, int i_max, double tol, boolean errorType, LinealResultsSpecialListener listener){
        if(A.length >= 1 && A.length == A[0].length && b.length >= 1 && b.length == A.length){
            // creating  D L U
            double [][]
                    D = new double[A.length][A.length],
                    L = new double[A.length][A.length],
                    U = new double[A.length][A.length];

            for(int i = 0; i < A.length; i++){
                for(int j = 0; j < A[0].length; j++){
                    if(i == j){
                        D[i][j] = A[i][j];
                    } else if(i < j){
                        U[i][j] = A[i][j];
                    } else {
                        L[i][j] = A[i][j];
                    }
                }
            }

            // Creating (D-L)^(-1)
            double[][] DL_inv = Inverted.invert(Addition.addArrays(D,L));

            // creating T
            double [][] I_negative = new double[A.length][A.length];
            for(int i = 0; i < A.length; i++) I_negative[i][i] = -1;

            double[][] T = Multiplication.multiplyArrays(DL_inv, U);
            T = Multiplication.multiplyArrays(I_negative, T);

            // creating C
            double[] C = Multiplication.multiplyVectorAndArray(DL_inv, b);

            // creating x
            double[] x = new double[b.length];
            Arrays.fill(x, 1);

            int i      = 0;
            double E   = 1 + tol;
            listener.onResultAdded(i, x, E);

            while (E >= tol && i < i_max){
                double[] xn = Addition.addVectors(Multiplication.multiplyVectorAndArray(T, x), C);
                E  = getMaxError(x, xn, errorType);
                x  = xn;
                i++;
                listener.onResultAdded(i, x, E);
            }

            if (i != i_max) {
                if (E < tol)
                    return x;
            }
            return null;
        }

        return null;
    }

    private static double getMaxError(double[] x0, double[] x1, boolean errorType){
        double E = 0;
        for(int i = 0; i < x0.length; i++){
            if(errorType){
                if(E < Math.abs(x0[i] - x1[i]))
                    E = Math.abs(x0[i] - x1[i]);
            } else {
                if(E < Math.abs((x0[i] - x1[i])/x0[i]))
                    E = Math.abs((x0[i] - x1[i])/x0[i]);
            }
        }

        return E;
    }
}
