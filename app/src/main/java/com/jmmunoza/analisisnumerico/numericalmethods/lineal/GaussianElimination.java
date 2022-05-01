package com.jmmunoza.analisisnumerico.numericalmethods.lineal;

import com.jmmunoza.analisisnumerico.numericalmethods.matrixoperations.Elementary;

public class GaussianElimination {
    public static double[] gauss(double[][] A, double[] b) {
        double[][] Ab = Elementary.createAugmentedMatrix(A,b);
        if(Ab.length >= 1 && Ab.length + 1 == Ab[0].length){
            for(int k = 0; k < Ab.length-1; k++) {
                for(int i = k + 1; i < Ab.length; i++) {
                    double m = Ab[i][k]/Ab[k][k];
                    Ab[i][k] = 0;
                    for(int j = k + 1; j < Ab[0].length; j++) {
                        Ab[i][j] -= Ab[k][j] * m;
                    }
                }
            }

            return Substitution.backward(Ab);
        }

        return null;
    }
}
