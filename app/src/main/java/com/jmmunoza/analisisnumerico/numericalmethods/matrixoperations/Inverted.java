package com.jmmunoza.analisisnumerico.numericalmethods.matrixoperations;

public class Inverted {
    public static double[][] invert(double[][] A){
        if(A.length == A[0].length){
            double [][] B = new double[A.length][A.length];
            for(int i = 0; i < B.length; i++) B[i][i] = 1;
            double [][] AB = Elementary.createAugmentedMatrix(A, B);

            for(int j = 0; j < A.length; j++){
                double m = 1/AB[j][j];
                for(int k = 0; k < AB[0].length; k++) AB[j][k] *= m;

                for(int i = 0; i < AB.length; i++){
                    if(i != j){
                        m = AB[i][j];
                        for(int k = 0; k < AB[0].length; k++) AB[i][k] -= m * AB[j][k];
                    }
                }
            }

            double[][] Ainv = new double[A.length][A.length];
            for(int i = 0; i < Ainv.length; i++){
                for(int j = 0; j < Ainv[0].length; j++){
                    Ainv[i][j] = AB[i][j + Ainv.length];
                }
            }

            return Ainv;
        }

        return null;
    }
}
