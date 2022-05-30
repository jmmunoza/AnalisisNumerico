package com.jmmunoza.analisisnumerico.numericalmethods.nolineal;

import com.jmmunoza.analisisnumerico.listeners.NoLinealResultsListener;
import com.jmmunoza.analisisnumerico.numericalmethods.F;

public class Secant {
    public static boolean calculate(String f, double xi, double xf, double tol, int i_max, boolean errorType, NoLinealResultsListener listener){
        if(F.eval(f, xi)*F.eval(f, xf) == 0){
            if(F.eval(f, xi) == 0) {
                listener.onResultAdded(0, xi, 0);
            } else {
                listener.onResultAdded(0, xf, 0);
            }
            return true;
        } else {

            double E;
            if(errorType) E = Math.abs(xi - xf);
            else          E = Math.abs((xi - xf)/xi);
            int i = 1;
            listener.onResultAdded(i, xi, E);

            while(E >= tol && i < i_max) {
                xf = (xi*F.eval(f,xf)-xf*F.eval(f,xf))/(F.eval(f,xf) - F.eval(f,xi));
                xi = xf;

                if(errorType) E = Math.abs(xi - xf);
                else          E = Math.abs((xi - xf)/xi);

                i++;
                listener.onResultAdded(i, xi, E);
            }

            if(i == i_max){
                return false;
            } else {
                return E < tol;
            }
        }
    }
}
