// 
// Decompiled by Procyon v0.5.30
// 

package edu.wise.utils;

public class FormatUtils
{
    public static int word_length(final double skor, int MAX_CHAR) {
        final String str = Double.toString(skor);
        final int MAX_LEN = str.length();
        final int MAX_DEC = 0;
        int DIGITS = 1;
        final int MIN_VALUE = Integer.toString((int)skor).length();
        if (MAX_CHAR < MIN_VALUE) {
            MAX_CHAR = MIN_VALUE;
        }
        rounder(skor, 3);
        if (str.length() == 0) {
            return 0;
        }
        if (str.length() == 1) {
            return 1;
        }
        for (int i = 0; i < MAX_CHAR && i < str.length(); ++i) {
            final char temp = str.charAt(i);
            if (temp == '.') {
                MAX_CHAR = 4;
            }
            ++DIGITS;
        }
        if (DIGITS > str.length()) {
            DIGITS = str.length();
        }
        return DIGITS;
    }
    
    public static double rounder(double score, int RHS_dec) {
        if (score == 0.0) {
            return 0.0;
        }
        RHS_dec = (int)Math.pow(10.0, RHS_dec);
        score *= RHS_dec;
        score = Math.round(score);
        score /= RHS_dec;
        return score;
    }
    
    public static String rounder_str(final double score, final int RHS_dec) {
        final double temp_d = rounder(score, RHS_dec);
        String temp = String.valueOf(temp_d);
        String rhs_temp = "";
        final int LHS = temp.indexOf(".");
        int RHS = temp.length() - 1;
        switch (RHS_dec) {
            case 1: {
                rhs_temp = ".0";
                break;
            }
            case 2: {
                rhs_temp = ".00";
                break;
            }
            case 3: {
                rhs_temp = ".000";
                break;
            }
            case 4: {
                rhs_temp = ".0000";
                break;
            }
            default: {
                rhs_temp = ".00000";
                break;
            }
        }
        if (temp_d == 0.0) {
            return "0".concat(rhs_temp);
        }
        if (RHS - LHS > RHS_dec) {
            return temp.substring(0, RHS);
        }
        while (RHS - LHS < RHS_dec) {
            temp = temp.concat("0");
            ++RHS;
        }
        return temp;
    }
}
