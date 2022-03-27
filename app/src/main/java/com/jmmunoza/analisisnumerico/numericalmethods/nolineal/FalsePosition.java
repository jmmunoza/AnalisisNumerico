package com.jmmunoza.analisisnumerico.numericalmethods.nolineal;

import com.jmmunoza.analisisnumerico.numericalmethods.F;

public class FalsePosition {
    public static void calculate(String f, double xi, double xf, double tol){
        if(F.eval(f,xi)*F.eval(f,xf) == 0){
            if(F.eval(f,xi) == 0)
                System.out.println(xi);
            else
                System.out.println(xf);

        } else {

            double xm = xi - ((F.eval(f,xi)*(xi-xf))/(F.eval(f,xi)-F.eval(f,xf)));
            double E = Math.abs(xm-xi);
            int i = 1;

            while(E >= tol && F.eval(f,xm) != 0) {
                if(F.eval(f,xi)*F.eval(f,xm) < 0)
                    xf = xm;
                else
                    xi = xm;

                xm = xi - ((F.eval(f,xi)*(xi-xf))/(F.eval(f,xi)-F.eval(f,xf)));
                E = Math.abs(xm-xi);
                i++;
            }

            if(F.eval(f,xm) == 0)
                System.out.println(xm);
            else
                System.out.println(xm + " con un error de " + E);
        }
    }
}
