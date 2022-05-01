package com.jmmunoza.analisisnumerico.numericalmethods.matrixoperations;

public class Multiplication {
    public static double[] multiplyVectorAndArray(double[][] A, double[]b){
        double[] product = new double[b.length];
        if(A[0].length == b.length){
            for(int k = 0; k < A.length; k++){
                product[k] = multiplyVectors(A[k], b);
            }
        }

        return product;
    }

    public static double multiplyVectors(double[] A, double[] B){
        double product = 0.0;
        if(A.length == B.length){
            for(int i = 0; i < A.length; i++){
                product += A[i] * B[i];
            }
        }
        return product;
    }

    public static double[][] multiplyArrays(double[][] A, double[][] B){
        if(A[0].length == B.length){
            double[][] product = new double[A.length][B[0].length];
            for(int i = 0; i < product.length; i++){
                for(int j = 0; j < product[0].length; j++){
                    double[] Arow = A[i];
                    double[] Bcol = new double[B.length];
                    for(int k = 0; k < Bcol.length; k++){
                        Bcol[k] = B[k][j];
                    }

                    product[i][j] = multiplyVectors(Arow, Bcol);
                }
            }

            return product;
        }

        return null;
    }
}
