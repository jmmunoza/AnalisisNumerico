package com.jmmunoza.analisisnumerico.numericalmethods.lineal;

import com.jmmunoza.analisisnumerico.listeners.LinealResultsSpecialListener;
import com.jmmunoza.analisisnumerico.numericalmethods.matrixoperations.Addition;
import com.jmmunoza.analisisnumerico.numericalmethods.matrixoperations.Multiplication;

import java.util.Arrays;

public class Jacobi {
    public static double[] jacobi(double[][] A, double[] b, int i_max, double tol, boolean errorType, LinealResultsSpecialListener listener){
        if(A.length >= 1 && A.length == A[0].length && b.length >= 1 && b.length == A.length){
            // creating inverted D
            double[][] Dinv = new double[A.length][A.length];
            for(int i = 0; i < Dinv.length; i++) Dinv[i][i] = 1/A[i][i];

            // creating L + U
            double[][] LU = A;
            for(int i = 0; i < LU.length; i++){
                for (int j = 0; j < LU[0].length; j++){
                    if(j == i) LU[j][i] = 0;
                    else       LU[j][i] = -1 * A[j][i];
                }
            }

            // creating T
            double[][] T = Multiplication.multiplyArrays(Dinv, LU);

            // creating C
            double[] C = Multiplication.multiplyVectorAndArray(Dinv, b);

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
