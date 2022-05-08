package com.jmmunoza.analisisnumerico.numericalmethods.lineal;

public class Substitution {
    public static double[] backward(double[][] A){
        int[] changes = new int[A.length];
        for(int i = 0; i < A.length; i++){
            changes[i] = i;
        }

        return backward(A, changes);
    }

    public static double[] backward(double[][] A, int[] changes){
        double[] r = new double[A.length];
        for(int i = A.length-1; i >= 0; i--) {
            double aux = 0;
            for(int j = A.length-1; j > i; j--){
                aux += A[i][j]*r[changes[j]];
            }

            r[changes[i]] = (A[i][A.length]-aux)/A[i][i];
        }

        return r;
    }

    public static double[] progressive(double[][] A){
        int[] changes = new int[A.length];
        for(int i = 0; i < A.length; i++){
            changes[i] = i;
        }

        return progressive(A, changes);
    }

    public static double[] progressive(double[][] A, int[] changes){
        double r[] = new double[A.length];
        for(int i=0; i < A.length; i++) {
            double aux = 0;
            for(int j=0; j < i; j++){
                aux += A[i][j]*r[changes[j]];
            }

            r[changes[i]] = (A[i][A.length]-aux)/A[i][i];
        }

        return r;
    }
}
