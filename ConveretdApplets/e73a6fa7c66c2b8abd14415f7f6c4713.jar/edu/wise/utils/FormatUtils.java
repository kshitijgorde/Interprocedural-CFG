// 
// Decompiled by Procyon v0.5.30
// 

package edu.wise.utils;

import java.text.DecimalFormat;

public class FormatUtils
{
    public static int word_length(final double n, int n2) {
        final String string = Double.toString(n);
        string.length();
        int length = 1;
        final int length2 = Integer.toString((int)n).length();
        if (n2 < length2) {
            n2 = length2;
        }
        rounder(n, 3);
        if (string.length() == 0) {
            return 0;
        }
        if (string.length() == 1) {
            return 1;
        }
        for (int n3 = 0; n3 < n2 && n3 < string.length(); ++n3) {
            if (string.charAt(n3) == '.') {
                n2 = 4;
            }
            ++length;
        }
        if (length > string.length()) {
            length = string.length();
        }
        return length;
    }
    
    public static double rounder(final double n, final int n2) {
        return Math.round(n * Math.pow(10.0, n2)) / Math.pow(10.0, n2);
    }
    
    public static String rounder_str(final double n, final int n2) {
        return rounder_str(n, n2, false);
    }
    
    public static String rounder_str(final double n, final int n2, final boolean b) {
        final DecimalFormat decimalFormat = new DecimalFormat();
        if (b) {
            decimalFormat.applyPattern(".##");
        }
        else {
            decimalFormat.applyPattern("########.##");
        }
        decimalFormat.setMaximumFractionDigits(n2);
        decimalFormat.setMinimumFractionDigits(n2);
        return decimalFormat.format(rounder(n, n2));
    }
}
