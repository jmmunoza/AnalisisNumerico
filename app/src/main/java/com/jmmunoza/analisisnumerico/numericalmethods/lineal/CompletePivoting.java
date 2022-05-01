package com.jmmunoza.analisisnumerico.numericalmethods.lineal;

import com.jmmunoza.analisisnumerico.numericalmethods.matrixoperations.Elementary;
import com.jmmunoza.analisisnumerico.view.fragments.FragmentMain;

public class CompletePivoting {
    public static double[] pivoting(double[][] A, double[] b){
        if(A.length >= 1 && A.length + 1 == A[0].length){
            int[] changes = new int[A.length];
            for(int i = 0; i < changes.length; i++) changes[i] = i;

            for(int k = 0; k < A.length-1; k++) {
                double max = Math.abs(A[k][k]);
                int maxPosCol = k, maxPosRow = k;
                for(int i = k; i < A.length; i++){
                    for(int j = k; j < A.length; j++){
                        if(max < Math.abs(A[i][j])) {
                            max       = Math.abs(A[i][j]);
                            maxPosCol = j;
                            maxPosRow = i;
                        }
                    }
                }

                changes = Elementary.changeRow(changes, k, maxPosCol);
                A       = Elementary.changeRow(A, k, maxPosRow);
                A       = Elementary.changeColumn(A, k, maxPosCol);

                for(int i = k + 1; i < A.length; i++) {
                    double m = A[i][k]/A[k][k];
                    A[i][k] = 0;
                    for(int j = k + 1; j < A[0].length; j++) {
                        A[i][j] -= A[k][j] * m;
                    }
                }
            }

            return Substitution.backward(A, changes);
        }

        return null;
    }
}
