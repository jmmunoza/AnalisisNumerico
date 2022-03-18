package com.jmmunoza.analisisnumerico.numericalmethods.nolineal;

import com.jmmunoza.analisisnumerico.numericalmethods.F;

public class Secant {
    public static void calculate(String f, double xi, double xf, double tol, int i_max){
        if(F.eval(f, xi)*F.eval(f, xf) == 0){
            if(F.eval(f, xi) == 0)
                System.out.println(xi);
            else
                System.out.println(xf);

        } else {
            double E = Math.abs(xf-xi);
            int i = 1;
            while(E >= tol && i < i_max) {
                xf = (xi*F.eval(f,xf)-xf*F.eval(f,xf))/(F.eval(f,xf) - F.eval(f,xi));
                xi = xf;
                E = Math.abs(xf-xi);
                i++;
            }

            if(F.eval(f, xf) == 0)
                System.out.println(xf);
            else
                System.out.println(xf + " con un error de " + E);
        }
    }
}
