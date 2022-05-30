package com.jmmunoza.analisisnumerico.numericalmethods.nolineal;

import com.jmmunoza.analisisnumerico.listeners.NoLinealResultsListener;
import com.jmmunoza.analisisnumerico.numericalmethods.F;

public class FixedPoint {
    public static boolean calculate(String f, String g, double xi, int i_max, double tol, boolean errorType, NoLinealResultsListener listener){
        if(g == null || g.equals("")){
            g = "("+f+")+x";
        }

        if(F.eval(f,xi) == 0){
            listener.onResultAdded(0, xi, 0);
            return true;
        }

        int i = 0;
        double E = tol + 1;
        listener.onResultAdded(i, xi, E);
        while (E >= tol && i < i_max){
            double xn = F.eval(g, xi);

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