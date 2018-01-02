// 
// Decompiled by Procyon v0.5.30
// 

package wordle.core.e;

import java.text.ParseException;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.text.DecimalFormat;

public final class c
{
    private static final DecimalFormat a;
    
    static {
        a = new DecimalFormat("0.00", new DecimalFormatSymbols(Locale.US));
    }
    
    public static String a(final double n) {
        return c.a.format(n);
    }
    
    public static double a(final String s) {
        try {
            if (s.contains(",")) {
                return c.a.parse(s.replace(',', '.')).doubleValue();
            }
            return c.a.parse(s).doubleValue();
        }
        catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
    }
}
