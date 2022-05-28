package com.jmmunoza.analisisnumerico.numericalmethods.interpolation;

public class LinealSplines {
    public static double[] linealSplines(double[] x, double[] y, double[] x_values){
        if(x.length == y.length){
            double[] results = new double[x_values.length];
            for(int i = 0; i < x_values.length; i++){
                for(int j = 0; j < x.length - 1; j++){
                    if(x_values[i] == x[j]){
                        results[i] = y[j];
                        break;
                    } else if(x_values[i] == x[j+1]){
                        results[i] = y[j+1];
                        break;
                    } else if(x_values[i] > x[j] && x_values[i] < x[j+1]){
                        results[i] = ((y[j+1]-y[j])/(x[j+1]-x[j]))*(x_values[i]-x[j])+y[j];
                        break;
                    } else if(x_values[i] < x[0]){
                        results[i] = ((y[1]-y[0])/(x[1]-x[0]))*(x_values[i]-x[0])+y[0];
                        break;
                    } else if(x_values[i] > x[x.length-1]){
                        results[i] = ((y[x.length-1]-y[x.length-2])/(x[x.length-1]-x[x.length-2]))*(x_values[i]-x[x.length-2])+y[x.length-2];
                        break;
                    }
                }
            }

            return results;
        }
        return null;
    }
}
