// 
// Decompiled by Procyon v0.5.30
// 

package de.ts.b.a;

public class d extends a
{
    public static final int new = 0;
    public static final int g = 1;
    public static final int s = 2;
    public static final int l = 3;
    public static final int j = 4;
    public static final int q = 5;
    public static final int b = 6;
    public static final int r = 7;
    public static final int k = 8;
    public static final int f = 9;
    public static final int null = 10;
    public static final int t = 11;
    public static final int void = 12;
    public static final int n = 13;
    public static final int i = 14;
    public static final int c = 15;
    public static final int v = 16;
    public static final int p = 17;
    public static final int u = 18;
    public static final int d = 19;
    public static final int goto = 20;
    public static final int h = 21;
    private static final String[] m;
    String o;
    long long;
    short e;
    
    public d(final String o, final long long1, final short e) {
        this.o = null;
        this.long = 0L;
        this.e = 0;
        this.o = o;
        this.long = long1;
        this.e = e;
    }
    
    public int do() {
        int n = 0;
        for (int i = 0; i < de.ts.b.a.d.m.length; ++i) {
            if (this.o.equals(de.ts.b.a.d.m[i])) {
                n = i;
                break;
            }
        }
        return n;
    }
    
    public long int() {
        return this.long;
    }
    
    public short for() {
        return this.e;
    }
    
    static {
        m = new String[] { "none", "fadein", "fadeout", "wait", "show", "clear", "hscroll_l", "hscrollin_l", "hscrollout_l", "vscroll_d", "vscrollin_d", "vscrollout_d", "hscroll_r", "hscrollin_r", "hscrollout_r", "vscroll_u", "vscrollin_u", "vscrollout_u", "ishow", "ihide", "zoomin", "zoomout" };
    }
}
