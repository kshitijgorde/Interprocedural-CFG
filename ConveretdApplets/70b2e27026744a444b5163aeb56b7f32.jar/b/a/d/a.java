// 
// Decompiled by Procyon v0.5.30
// 

package b.a.d;

import java.text.MessageFormat;
import java.util.ResourceBundle;
import java.util.Locale;
import java.text.DecimalFormatSymbols;

public class a
{
    private static DecimalFormatSymbols a;
    private static Locale b;
    private static ResourceBundle c;
    
    public static void a(final Locale b) {
        b.a.d.a.b = b;
        b.a.d.a.c = ResourceBundle.getBundle("org-shetline-util", b);
        b.a.d.a.a = new DecimalFormatSymbols(b);
    }
    
    public static ResourceBundle a() {
        if (b.a.d.a.c == null) {
            a(Locale.getDefault());
        }
        return b.a.d.a.c;
    }
    
    public static DecimalFormatSymbols b() {
        if (b.a.d.a.a == null) {
            b.a.d.a.a = new DecimalFormatSymbols();
        }
        return b.a.d.a.a;
    }
    
    public static String a(final String s, final Object o) {
        return a(s, new Object[] { o });
    }
    
    public static String a(final String s, final Object o, final Object o2) {
        return a(s, new Object[] { o, o2 });
    }
    
    public static String a(final String s, final Object o, final Object o2, final Object o3) {
        return a(s, new Object[] { o, o2, o3 });
    }
    
    public static String a(final String s, final Object[] array) {
        return new MessageFormat(s).format(array);
    }
    
    static {
        b.a.d.a.a = null;
        b.a.d.a.b = Locale.getDefault();
    }
}
