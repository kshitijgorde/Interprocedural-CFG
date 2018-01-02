// 
// Decompiled by Procyon v0.5.30
// 

package ji.secure;

public class ei
{
    public static String a;
    public static String b;
    public static int c;
    public static String d;
    public static int e;
    public static String f;
    public static int g;
    public String h;
    public int i;
    public int j;
    public int k;
    public String l;
    public String m;
    public static boolean n;
    public static boolean o;
    public static String p;
    private static int q;
    private int r;
    
    public ei() {
        this.h = "(ANYONE)";
        this.i = 0;
        this.j = 0;
        this.k = 2;
        this.l = "owner";
        this.m = "";
        this.r = 0;
    }
    
    public ei(final String h, final int i, final int j, final int k) {
        this.h = "(ANYONE)";
        this.i = 0;
        this.j = 0;
        this.k = 2;
        this.l = "owner";
        this.m = "";
        this.r = 0;
        this.r = ei.q;
        ++ei.q;
        this.h = h;
        this.i = i;
        this.j = j;
        this.k = k;
    }
    
    public ei(final String h, final String m, final int i, final String l, final int k) {
        this.h = "(ANYONE)";
        this.i = 0;
        this.j = 0;
        this.k = 2;
        this.l = "owner";
        this.m = "";
        this.r = 0;
        this.r = ei.q;
        ++ei.q;
        this.h = h;
        this.i = i;
        this.l = l;
        this.m = m;
        this.k = k;
    }
    
    public ei(final String h, final int j, final int k) {
        this.h = "(ANYONE)";
        this.i = 0;
        this.j = 0;
        this.k = 2;
        this.l = "owner";
        this.m = "";
        this.r = 0;
        this.r = ei.q;
        ++ei.q;
        this.h = h;
        this.j = j;
        this.k = k;
    }
    
    public String a() {
        final StringBuffer sb = new StringBuffer("");
        sb.append("".concat(String.valueOf(String.valueOf(this.h))));
        sb.append("".concat(String.valueOf(String.valueOf(this.i))));
        sb.append("".concat(String.valueOf(String.valueOf(this.j))));
        sb.append("".concat(String.valueOf(String.valueOf(this.l))));
        sb.append("".concat(String.valueOf(String.valueOf(this.m))));
        sb.append("".concat(String.valueOf(String.valueOf(this.k))));
        return sb.toString();
    }
    
    public ei b() {
        final ei ei = new ei();
        ei.h = this.h;
        ei.i = this.i;
        ei.j = this.j;
        ei.l = this.l;
        ei.m = this.m;
        ei.k = this.k;
        return ei;
    }
    
    public String toString() {
        return String.valueOf(String.valueOf(new StringBuffer("jiItemPermission ").append(this.r).append(", '").append(this.h).append("', '").append(this.m).append("', ").append(this.i).append(", ").append(this.j).append(", ").append(this.l).append(", ").append(this.k)));
    }
    
    static {
        ei.a = "admin";
        ei.b = "(ANYONE)";
        ei.c = 0;
        ei.d = "(ANYONE)";
        ei.e = 0;
        ei.f = "(ANYONE)";
        ei.g = 0;
        ei.n = true;
        ei.o = true;
        ei.p = "";
        ei.q = 0;
    }
}
