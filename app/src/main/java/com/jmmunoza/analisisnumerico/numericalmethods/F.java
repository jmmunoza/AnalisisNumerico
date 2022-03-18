package com.jmmunoza.analisisnumerico.numericalmethods;

import com.udojava.evalex.Expression;

public class F {
    public static double eval(String f, double x){
        return new Expression(f)
                .with("x", String.valueOf(x))
                .setPrecision(15)
                .eval()
                .doubleValue();
    }
}
