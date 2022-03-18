package com.jmmunoza.analisisnumerico.numericalmethods.nolineal;

import com.jmmunoza.analisisnumerico.numericalmethods.F;

public class IncrementalSearch {
    public static void calculate(String f, double xi, double dx, int i_max){
        if(F.eval(f, xi) == 0){
            System.out.println(xi);
        } else {
            double xf = xi + dx;
            int i = 0;
            while (F.eval(f, xi)*F.eval(f,xf) > 0 && i < i_max){
                xi = xf;
                xf = xi + dx;
                i++;
            }

            if(F.eval(f, xf) == 0)
                System.out.println(xf + " es una raiz");
            else if(F.eval(f, xf)*F.eval(f, xi) < 0)
                System.out.println("hay una raiz en [" + xi + "," + xf + ")");
            else
                System.out.println("sin solucion");
        }
    }
}
