package com.jmmunoza.analisisnumerico.numericalmethods.matrixoperations;

public class Elementary {
    public static double[][] changeRow(double[][] A, int row1, int row2){
        if(row1 != row2 && row1 >= 0 && row2 >= 0 && row1 < A.length && row2 < A.length){
            double[] auxRow = A[row1];
            A[row1]         = A[row2];
            A[row2]         = auxRow;
        }

        return A;
    }

    public static int[] changeRow(int[] a, int row1, int row2){
        if(row1 != row2 && row1 >= 0 && row2 >= 0 && row1 < a.length && row2 < a.length){
            int auxRow = a[row1];
            a[row1]         = a[row2];
            a[row2]         = auxRow;
        }

        return a;
    }

    public static double[][] changeColumn(double[][] A, int col1, int col2){
        if(col1 != col2 && col1 >= 0 && col2 >= 0 && col1 < A[0].length && col2 < A[0].length){
            for(int i = 0; i < A.length; i++){
                double auxCol = A[i][col1];
                A[i][col1]    = A[i][col2];
                A[i][col2]    = auxCol;
            }
        }

        return A;
    }

    public static double[][] createAugmentedMatrix(double[][] A, double[] b){
        if(A.length == b.length){
            double[][] Ab = new double[A.length][A[0].length + 1];
            for(int i = 0; i < Ab.length; i++){
                for(int j = 0; j < Ab[0].length; j++){
                    if(j == A[0].length){
                        Ab[i][j] = b[i];
                    } else {
                        Ab[i][j] = A[i][j];
                    }
                }
            }

            return Ab;
        }

        return null;
    }

    public static double[][] createAugmentedMatrix(double[][] A, double[][] B){
        if(A.length == B.length){
            double[][] AB = new double[A.length][A[0].length + B[0].length];
            for(int i = 0; i < AB.length; i++){
                for(int j = 0; j < AB[0].length; j++){
                    if(j >= A[0].length){
                        AB[i][j] = B[i][j - A[0].length];
                    } else {
                        AB[i][j] = A[i][j];
                    }
                }
            }

            return AB;
        }

        return null;
    }
}
