package com.jmmunoza.analisisnumerico.numericalmethods.lineal;

import com.jmmunoza.analisisnumerico.numericalmethods.matrixoperations.Elementary;
import com.jmmunoza.analisisnumerico.view.fragments.FragmentMain;

public class CompletePivoting {
    public static double[] pivoting(double[][] A, double[] b){
        double[][] Ab = Elementary.createAugmentedMatrix(A,b);
        if(Ab.length >= 1 && Ab.length + 1 == Ab[0].length){
            int[] changes = new int[Ab.length];
            for(int i = 0; i < changes.length; i++) changes[i] = i;

            for(int k = 0; k < Ab.length-1; k++) {
                double max = Math.abs(Ab[k][k]);
                int maxPosCol = k, maxPosRow = k;
                for(int i = k; i < Ab.length; i++){
                    for(int j = k; j < Ab.length; j++){
                        if(max < Math.abs(Ab[i][j])) {
                            max       = Math.abs(Ab[i][j]);
                            maxPosCol = j;
                            maxPosRow = i;
                        }
                    }
                }

                changes = Elementary.changeRow(changes, k, maxPosCol);
                Ab      = Elementary.changeRow(Ab, k, maxPosRow);
                Ab      = Elementary.changeColumn(Ab, k, maxPosCol);

                for(int i = k + 1; i < Ab.length; i++) {
                    double m = Ab[i][k]/Ab[k][k];
                    Ab[i][k] = 0;
                    for(int j = k + 1; j < Ab[0].length; j++) {
                        Ab[i][j] -= Ab[k][j] * m;
                    }
                }
            }

            return Substitution.backward(Ab, changes);
        }

        return null;
    }
}
