package com.jmmunoza.analisisnumerico.numericalmethods.nolineal;

import com.jmmunoza.analisisnumerico.numericalmethods.F;

public class NewtonRaphson {
    public static void calculate(String f, double xi, int i_max, double tol){
        if(F.eval(f,xi) == 0){
            System.out.println(xi);
        }

        /*
        int i = 0;
        double E = tol + 1;
        while (E >= tol && i < i_max){
            double xn = xi - (F.eval(f,xi)/calcDerivative(f, xi));
            E = Math.abs(xi - xn);
            xi = xn;
            i++;
        }

        if(E < tol)
            System.out.println(xi + " es una raiz con un error de " + E);
        else
            System.out.println(xi + "Sin solucion");

         */
    }
}
