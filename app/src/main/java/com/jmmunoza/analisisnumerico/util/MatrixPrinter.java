package com.jmmunoza.analisisnumerico.util;

public class MatrixPrinter {
    public static String printMatrix(double[][] matrix) {
        int cols = matrix[0].length;
        int[] iWidths = new int[cols];
        int[] fWidths = new int[cols];
        for (double[] row : matrix) {
            for (int c = 0; c < cols; c++) {
                String[] parts = String.valueOf(row[c]).split("\\.");
                if(parts[1].length() > 3) parts[1] = parts[1].substring(0,3);

                iWidths[c] = Math.max(iWidths[c], parts[0].length());
                fWidths[c] = Math.max(fWidths[c], parts[1].length());
            }
        }
        String string = "";

        for (double[] row : matrix) {
            for (int c = 0; c < cols; c++) {
                String[] parts = String.valueOf(row[c]).split("\\.");
                if(parts[1].length() > 3) parts[1] = parts[1].substring(0,3);

                int lp = iWidths[c] - parts[0].length();
                int rp = fWidths[c] - parts[1].length();
                String fmt = String.format("%s%%%ss%%s.%%s%%%ss%s",
                        c == 0 ? "|" : "  ",
                        lp == 0 ? "" : lp,
                        rp == 0 ? "" : rp,
                        c < cols - 1 ? "" : "|%n");

                string += String.format(fmt, "", parts[0], parts[1], "");
                System.out.printf(fmt, "", parts[0], parts[1], "");
            }
        }

        return string;
    }
}
