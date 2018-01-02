// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.util.TimeZone;
import java.util.Date;
import java.text.DateFormat;

public class aF extends cF
{
    public static DateFormat a;
    public int h;
    public int i;
    public int a;
    public int b;
    public int Z;
    public long k;
    public long l;
    public long m;
    public String S;
    public String T;
    public boolean ai;
    public boolean aj;
    public String U;
    public String V;
    public int ar;
    
    public Object a(final String s) {
        if (ao.e("Max").equals(s)) {
            return String.valueOf(this.i);
        }
        if (ao.e("Current").equals(s)) {
            return String.valueOf(this.b);
        }
        if (ao.e("Hits").equals(s)) {
            return String.valueOf(this.a);
        }
        if (ao.e("Auto").equals(s)) {
            return String.valueOf(this.ar);
        }
        if (ao.e("Last Connection").equals(s)) {
            return aF.a.format(new Date(this.k));
        }
        if (ao.e("Service End Date").equals(s)) {
            return String.valueOf(this.T);
        }
        return super.a(s);
    }
    
    public int a(final aU au, final String s) {
        if (au instanceof aF) {
            final aF af = (aF)au;
            if (ao.e("Last Connection").equals(s)) {
                return (int)(af.k - this.k);
            }
            if (ao.e("Max").equals(s)) {
                return af.i - this.i;
            }
            if (ao.e("Current").equals(s)) {
                return af.b - this.b;
            }
            if (ao.e("Hits").equals(s)) {
                return af.a - this.a;
            }
            if (ao.e("Hits").equals(s)) {
                return af.ar - this.ar;
            }
        }
        return super.a(au, s);
    }
    
    public aF(final int n, final String s) {
        super(n, s);
        this.ai = true;
        this.i = 0;
        this.a = 0;
        this.b = 0;
        this.Z = 1023;
        this.k = 0L;
        this.l = 0L;
        this.m = 0L;
    }
    
    static {
        (aF.a = DateFormat.getDateTimeInstance()).setTimeZone(TimeZone.getDefault());
    }
}
