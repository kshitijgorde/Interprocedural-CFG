// 
// Decompiled by Procyon v0.5.30
// 

package ABLjemsty;

public class StylerField
{
    public int a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public String g;
    public byte h;
    public Object i;
    public boolean j;
    public boolean k;
    public String l;
    public String m;
    public boolean n;
    public int o;
    public int[] p;
    public int[] q;
    public String[] r;
    public String[] s;
    public String[] t;
    public String u;
    public String v;
    public char w;
    public int x;
    public int y;
    public String z;
    
    public StylerField(final Object i, final byte h, final int b, final int c, final int d, final int e, final int f, final String g) {
        this.j = false;
        this.k = false;
        this.i = i;
        this.h = h;
        this.b = b;
        this.a = c - 1;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
    }
    
    public boolean a(final String s, final String s2) {
        return this.e == Math.max(s.length(), s2.length()) && (this.g.equals(s) || this.g.equals(s2)) && this.l == null && this.p == null && this.t == null && this.u == null;
    }
}
