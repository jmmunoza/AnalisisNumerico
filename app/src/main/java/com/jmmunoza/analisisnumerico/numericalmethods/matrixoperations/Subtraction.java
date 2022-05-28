package com.jmmunoza.analisisnumerico.numericalmethods.matrixoperations;

public class Subtraction {
    public static double[] subtractVectors(double[] A, double[] B){
        if(A.length == B.length){
            double[] result = new double[A.length];
            for(int i = 0; i < A.length; i++){
                result[i] = A[i] - B[i];
            }
            return result;
        }

        return null;
    }

    public static double[][] subtractArrays(double[][] A, double[][] B){
        if(A.length == B.length && A[0].length == B[0].length){
            double[][] result = new double[A.length][A[0].length];
            for(int i = 0; i < A.length; i++){
                for (int j = 0; j < A[0].length; j++){
                    result[i][j] = A[i][j] - B[i][j];
                }
            }

            return result;
        }

        return null;
    }
}
