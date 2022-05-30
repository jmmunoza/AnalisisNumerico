package com.jmmunoza.analisisnumerico.numericalmethods.lineal;

import com.jmmunoza.analisisnumerico.listeners.LinealResultListener;
import com.jmmunoza.analisisnumerico.numericalmethods.matrixoperations.Elementary;

public class PartialPivoting {
    public static double[] pivoting(double[][] A, double[] b, LinealResultListener listener){
        double[][] Ab = Elementary.createAugmentedMatrix(A,b);
        if(Ab.length >= 1 && Ab.length + 1 == Ab[0].length){
            for(int k = 0; k < Ab.length-1; k++) {
                // buscar valor mas alto y su posicion
                double max = Math.abs(Ab[k][k]);
                int maxPos = k;
                for(int i = k; i < Ab.length; i++){
                    if(max < Math.abs(Ab[i][k])) {
                        max    = Math.abs(Ab[i][k]);
                        maxPos = i;
                    }
                }

                Ab = Elementary.changeRow(Ab, k, maxPos);

                for(int i = k + 1; i < Ab.length; i++) {
                    double m = Ab[i][k]/Ab[k][k];
                    Ab[i][k] = 0;
                    for(int j = k + 1; j < Ab[0].length; j++) {
                        Ab[i][j] -= Ab[k][j] * m;
                    }
                }

                listener.onResultAdded(Ab, k + 1);
            }
            return Substitution.backward(Ab);
        }

        return null;
    }
}
