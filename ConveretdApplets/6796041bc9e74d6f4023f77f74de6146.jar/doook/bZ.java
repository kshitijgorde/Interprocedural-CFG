// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.util.TimeZone;
import java.util.Date;
import java.text.DateFormat;

public class bZ extends aF
{
    public static DateFormat a;
    
    public Object a(final String s) {
        if (ao.e("Max").equals(s)) {
            return String.valueOf(super.i);
        }
        if (ao.e("Current").equals(s)) {
            return String.valueOf(super.b);
        }
        if (ao.e("Hits").equals(s)) {
            return String.valueOf(super.a);
        }
        if (ao.e("Auto").equals(s)) {
            return String.valueOf(super.ar);
        }
        if (!ao.e("Last Connection").equals(s)) {
            return super.a(s);
        }
        if (super.k == 0L) {
            return ao.e("N/A");
        }
        return bZ.a.format(new Date(super.k));
    }
    
    public bZ(final int n, final String s) {
        super(n, s);
    }
    
    static {
        (bZ.a = DateFormat.getDateTimeInstance()).setTimeZone(TimeZone.getDefault());
    }
}
