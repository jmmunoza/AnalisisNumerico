package com.jmmunoza.analisisnumerico.numericalmethods.nolineal;

import com.jmmunoza.analisisnumerico.listeners.NoLinealResultsListener;
import com.jmmunoza.analisisnumerico.numericalmethods.F;

public class IncrementalSearch {
    public static boolean calculate(String f, double xi, double dx, int i_max, NoLinealResultsListener listener){
        if(F.eval(f, xi) == 0){
            listener.onResultAdded(0, xi, xi);
            return true;
        } else {
            double xf = xi + dx;
            int i = 0;
            listener.onResultAdded(i, xi, xf);
            while (F.eval(f, xi)*F.eval(f,xf) > 0 && i < i_max){
                xi = xf;
                xf = xi + dx;
                i++;
                listener.onResultAdded(i, xi, xf);
            }


            if(F.eval(f, xf) == 0) {
                listener.onResultAdded(i, xf, xf);
                return true;
            } else if(F.eval(f, xf)*F.eval(f, xi) < 0) {
                listener.onResultAdded(i, xi, xf);
                return true;
            } else {
                return false;
            }
        }
    }
}
