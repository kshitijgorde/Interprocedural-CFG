// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Date;
import java.text.DateFormat;

public final class bX extends s implements a
{
    private static DateFormat a;
    
    public final Object a(final String s) {
        if ("name".equals(s)) {
            return super.d;
        }
        if ("ID".equals(s)) {
            return new Integer(super.i);
        }
        if ("date".equals(s)) {
            if (super.a == 0L) {
                return aS.a(29);
            }
            return bX.a.format(new Date(super.a));
        }
        else if ("time".equals(s)) {
            if (super.a == 0) {
                return aS.a(632);
            }
            return aS.a(608 + super.a);
        }
        else {
            if (aS.a("user").equals(s)) {
                return super.a;
            }
            return null;
        }
    }
    
    public final int a(final a a, final String s) {
        if (a instanceof s) {
            final s s2 = (s)a;
            if ("date".equals(s)) {
                return (int)(s2.a - super.a);
            }
            if ("user".equals(s)) {
                return ((super.a == null) ? "" : super.a).toLowerCase().compareTo(((s2.a == null) ? "" : s2.a).toLowerCase());
            }
        }
        return super.a(a, s);
    }
    
    public bX(final String s) {
        super(-999, s);
    }
    
    public bX(final s s) {
        super(s.i, s.d);
        super.a = s.a;
        super.a = s.a;
        super.a = s.a;
    }
    
    static {
        bX.a = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aa", Locale.US);
    }
}
