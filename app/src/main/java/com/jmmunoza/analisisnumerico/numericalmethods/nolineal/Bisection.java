package com.jmmunoza.analisisnumerico.numericalmethods.nolineal;

import com.jmmunoza.analisisnumerico.listeners.NoLinealResultsListener;
import com.jmmunoza.analisisnumerico.numericalmethods.F;

public class Bisection {
    public static boolean calculate(String f, double xi, double xf, double tol, boolean errorType, NoLinealResultsListener listener){
        if(F.eval(f,xi)*F.eval(f,xf) == 0){
            if(F.eval(f, xi) == 0) {
                listener.onResultAdded(0, xi, 0);
            } else {
                listener.onResultAdded(0, xf, 0);
            }
            return true;

        } else {
            double xm = (xi+xf)/2;
            double E;

            if(errorType) E = Math.abs(xi - xm);
            else          E = Math.abs((xi - xm)/xm);

            int i = 1;
            listener.onResultAdded(i, xm, E);
            while(E >= tol && F.eval(f,xm) != 0) {
                if(F.eval(f,xi)*F.eval(f,xm) < 0)
                    xf = xm;
                else
                    xi = xm;

                xm = (xi+xf)/2;

                if(errorType) E = Math.abs(xi - xm);
                else          E = Math.abs((xi - xm)/xm);

                i++;
                listener.onResultAdded(i, xm, E);
            }

            return E < tol;
        }
    }
}
