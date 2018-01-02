// 
// Decompiled by Procyon v0.5.30
// 

package ji.util;

import ji.res.s;
import java.util.Vector;

public class eu
{
    public int a;
    public Vector b;
    public long c;
    private String d;
    public int e;
    public double f;
    public boolean g;
    public int h;
    private static long i;
    
    public eu(final int a, final Vector b, final String d, final int e, final double f) {
        this.a = 0;
        this.c = -1L;
        this.d = null;
        this.e = -1;
        this.f = 0.0;
        this.g = false;
        this.h = -1;
        this.a = a;
        this.b = b;
        this.d = d;
        this.e = e;
        this.f = f;
        this.c = eu.i++;
    }
    
    public final String a(final String s) {
        return s.a(1325, s, new Object[] { "".concat(String.valueOf(String.valueOf(this.a))), this.d });
    }
    
    static {
        eu.i = 0L;
    }
}
