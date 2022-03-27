package com.jmmunoza.analisisnumerico.numericalmethods.derivatives;

import com.udojava.evalex.Expression;

import java.math.BigDecimal;

public class CentralDerivative {
    public static double calculateFirstDerivative(String f, double x){
        double h = 0.1;
        double x0 = getFirstDerivative(f, x, h);
        h/=10;
        double x1 = getFirstDerivative(f, x, h);
        double E = Math.abs(x0-x1);
        while (Math.abs((x0-x1)) >= E && E != 0){
            E = Math.abs(x0-x1);
            x0 = x1;
            h/=10;
            if(h == 0){
                return x0;
            }
            x1 = getFirstDerivative(f, x, h);
        }

        return x1;
    }

    public static double calculateSecondDerivative(String f, double x){
        double h = 0.1;
        double x0 = getSecondDerivative(f, x, h);
        h/=10;
        double x1 = getSecondDerivative(f, x, h);
        double E = Math.abs(x0-x1);
        while (Math.abs((x0-x1)) >= E && E != 0){
            E = Math.abs(x0-x1);
            x0 = x1;
            h/=10;
            if(h == 0){
                return x0;
            }
            x1 = getSecondDerivative(f, x, h);
        }

        return x1;
    }

    private static double getFirstDerivative(String f, double x, double h){
        String first = f.replace("x", "(x+h)");
        String second = f.replace("x", "(x-h)");
        String df = "((" + first + ")-(" + second + "))/(2*h)";
        return new Expression(df)
                .with("x", BigDecimal.valueOf(x))
                .and("h", String.valueOf(h))
                .setPrecision(15)
                .eval()
                .doubleValue();
    }

    private static double getSecondDerivative(String f, double x, double h){
        String first = f.replace("x", "(x+h)");
        String second = f.replace("x", "(x-h)");
        String df = "((" + first + ")-(" + second + "))/(2*h)";
        first = df.replace("x", "(x+h)");
        second = df.replace("x", "(x-h)");
        String df2 = "((" + first + ")-(" + second + "))/(2*h)";
        return new Expression(df2)
                .with("x", BigDecimal.valueOf(x))
                .and("h", String.valueOf(h))
                .setPrecision(15)
                .eval()
                .doubleValue();
    }
}
