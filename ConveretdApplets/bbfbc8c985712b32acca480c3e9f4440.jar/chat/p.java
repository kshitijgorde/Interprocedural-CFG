// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Date;
import java.text.DateFormat;

public class p extends U
{
    public static DateFormat a;
    public int a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public long a;
    public String a;
    public String b;
    public String c;
    
    public Object a(final String s) {
        if (aS.a(270).equals(s)) {
            return String.valueOf(this.b);
        }
        if (aS.a(271).equals(s)) {
            return String.valueOf(this.d);
        }
        if (aS.a(272).equals(s)) {
            return String.valueOf(this.c);
        }
        if (aS.a(273).equals(s)) {
            return p.a.format(new Date(this.a));
        }
        return super.a(s);
    }
    
    public final int a(final a a, final String s) {
        if (a instanceof p) {
            final p p2 = (p)a;
            if (aS.a(273).equals(s)) {
                return (int)(p2.a - this.a);
            }
            if (aS.a(270).equals(s)) {
                return p2.b - this.b;
            }
            if (aS.a(271).equals(s)) {
                return p2.d - this.d;
            }
            if ("virtual".equals(s)) {
                return p2.h - this.h;
            }
            if (aS.a(272).equals(s)) {
                return p2.c - this.c;
            }
        }
        return super.a(a, s);
    }
    
    public p(final int n, final String s) {
        super(n, s);
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.h = 0;
        this.e = 1023;
        this.a = 0L;
        this.a = null;
        this.b = null;
        this.c = null;
    }
    
    static {
        p.a = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aa", Locale.US);
    }
}
