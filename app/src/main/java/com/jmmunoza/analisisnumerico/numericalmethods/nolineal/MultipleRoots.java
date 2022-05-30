package com.jmmunoza.analisisnumerico.numericalmethods.nolineal;

import com.jmmunoza.analisisnumerico.listeners.NoLinealResultsListener;
import com.jmmunoza.analisisnumerico.numericalmethods.F;
import com.jmmunoza.analisisnumerico.numericalmethods.derivatives.CentralDerivative;

public class MultipleRoots {
    public static boolean calculate(String f, double xi, int i_max, double tol, boolean errorType, NoLinealResultsListener listener){
        if(F.eval(f, xi) == 0){
            listener.onResultAdded(0, xi, 0);
            return true;
        }

        int i = 0;
        double E = tol + 1;
        listener.onResultAdded(i, xi, E);
        while (E >= tol && i < i_max){
            double fx   = F.eval(f,xi);
            double dfx  = CentralDerivative.calculateFirstDerivative(f, xi);
            double d2fx = CentralDerivative.calculateSecondDerivative(f, xi);
            double xn   = xi - ((fx*dfx)/((dfx*dfx)-(fx*d2fx)));

            if(errorType) E = Math.abs(xi - xn);
            else          E = Math.abs((xi - xn)/xi);

            xi = xn;
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
