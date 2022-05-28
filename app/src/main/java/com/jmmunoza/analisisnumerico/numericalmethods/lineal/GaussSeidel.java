package com.jmmunoza.analisisnumerico.numericalmethods.lineal;

import com.jmmunoza.analisisnumerico.numericalmethods.matrixoperations.Addition;
import com.jmmunoza.analisisnumerico.numericalmethods.matrixoperations.Elementary;
import com.jmmunoza.analisisnumerico.numericalmethods.matrixoperations.Inverted;
import com.jmmunoza.analisisnumerico.numericalmethods.matrixoperations.Multiplication;
import com.jmmunoza.analisisnumerico.numericalmethods.matrixoperations.Subtraction;

public class GaussSeidel {
    public static double[] gaussSeidel(double[][] A, double[] b, int max){
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

            for(int k = 0; k < max; k++){
                // x(n+1) = Tx(n) + C
                x = Addition.addVectors(Multiplication.multiplyVectorAndArray(T, x), C);
            }

            return x;
        }

        return null;
    }

    public static void print2D(double mat[][])
    {
        for (int i = 0; i < mat.length; i++){
            for (int j = 0; j < mat[i].length; j++){
                System.out.print(mat[i][j] + "            ");
            }
            System.out.println("");
        }
        System.out.println("----------------------------------------------------------------");
    }
}
