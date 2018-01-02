// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Date;
import java.text.DateFormat;

public class aK extends p
{
    private static DateFormat b;
    
    public Object a(final String s) {
        if (aS.a(270).equals(s)) {
            return String.valueOf(super.b);
        }
        if (aS.a(271).equals(s)) {
            return String.valueOf(super.d);
        }
        if (aS.a(272).equals(s)) {
            return String.valueOf(super.c);
        }
        if ("virtual".equals(s)) {
            return String.valueOf(super.h);
        }
        if (!aS.a(273).equals(s)) {
            return super.a(s);
        }
        if (super.a == 0L) {
            return aS.a(29);
        }
        return aK.b.format(new Date(super.a));
    }
    
    public aK(final int n, final String s) {
        super(n, s);
    }
    
    static {
        aK.b = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aa", Locale.US);
    }
}
