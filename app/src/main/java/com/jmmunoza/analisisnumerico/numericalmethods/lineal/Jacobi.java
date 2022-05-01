package com.jmmunoza.analisisnumerico.numericalmethods.lineal;

import com.jmmunoza.analisisnumerico.numericalmethods.matrixoperations.Addition;
import com.jmmunoza.analisisnumerico.numericalmethods.matrixoperations.Multiplication;

public class Jacobi {
    public static double[] jacobi(double[][] A, double[] b, int max){
        if(A.length >= 1 && A.length == A[0].length && b.length >= 1 && b.length == A.length){
            // creating inverted D
            double[][] Dinv = new double[A.length][A.length];
            for(int i = 0; i < Dinv.length; i++){
                Dinv[i][i] = 1/A[i][i];
            }

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

            for(int k = 0; k < max; k++){
                // x(n+1) = Tx(n) + C
                x = Addition.addVectors(Multiplication.multiplyVectorAndArray(T, x), C);
            }

            return x;
        }

        return null;
    }
}
