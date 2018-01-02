// 
// Decompiled by Procyon v0.5.30
// 

package blackmagic.swing;

import java.text.ParsePosition;
import java.text.DecimalFormat;

class DoubleParser
{
    public static double toDouble(final String s) throws NullPointerException {
        String s2 = s.trim();
        if (s2.length() == 0) {
            throw new NullPointerException();
        }
        int n = 0;
        if (s2.charAt(s2.length() - 1) == '%') {
            n = 1;
            s2 = s2.replace('%', ' ').trim();
        }
        double doubleValue = new DecimalFormat().parse(s2, new ParsePosition(0)).doubleValue();
        if (n == 1) {
            doubleValue /= 100.0;
        }
        return doubleValue;
    }
}
