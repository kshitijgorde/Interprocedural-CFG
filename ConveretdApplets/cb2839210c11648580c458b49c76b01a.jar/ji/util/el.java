// 
// Decompiled by Procyon v0.5.30
// 

package ji.util;

import java.util.StringTokenizer;
import ji.io.h;

public class el
{
    public long a;
    double b;
    public String c;
    public int d;
    public long e;
    public long f;
    public int g;
    public String h;
    public String i;
    public int j;
    public int k;
    public double l;
    public double m;
    public String n;
    public long o;
    public long p;
    public long q;
    public long r;
    public long s;
    public long t;
    public long u;
    public long v;
    public long w;
    public long x;
    public long y;
    public long z;
    public long aa;
    public int ab;
    public int ac;
    public int ad;
    public int ae;
    public double af;
    public int ag;
    public int ah;
    public long ai;
    public long aj;
    public long ak;
    public String al;
    
    public el() {
        this.a = 0L;
        this.b = 0.0;
        this.c = null;
        this.d = 0;
        this.e = 0L;
        this.f = 0L;
        this.g = 0;
        this.h = null;
        this.i = null;
        this.j = 0;
        this.k = 0;
        this.l = 0.0;
        this.m = 0.0;
        this.n = null;
        this.o = 0L;
        this.p = 0L;
        this.q = 0L;
        this.r = 0L;
        this.s = 0L;
        this.t = 0L;
        this.u = 0L;
        this.v = 0L;
        this.w = 0L;
        this.x = 0L;
        this.y = 0L;
        this.z = 0L;
        this.aa = 0L;
        this.ab = 0;
        this.ac = 0;
        this.ad = 0;
        this.ae = 0;
        this.af = 0.0;
        this.ag = 0;
        this.ah = 0;
        this.ai = 0L;
        this.aj = 0L;
        this.ak = 0L;
        this.al = null;
    }
    
    public final void a(final String s) {
        try {
            ji.io.h.d(s, "> Performance log start..............");
            ji.io.h.d(s, ">");
            ji.io.h.d(s, "> Filename: ".concat(String.valueOf(String.valueOf(this.c))));
            ji.io.h.d(s, "> Mime type: ".concat(String.valueOf(String.valueOf(this.h))));
            ji.io.h.d(s, String.valueOf(String.valueOf(new StringBuffer("> File size: ").append(ji.util.d.a(this.e, s)).append(" (").append(this.e).append(" bytes)"))));
            if (this.f > 0) {
                ji.io.h.d(s, String.valueOf(String.valueOf(new StringBuffer("> Network activity: ").append(ji.util.d.a(this.f, s)).append(" (").append(this.f).append(" bytes)"))));
            }
            ji.io.h.d(s, "> Page: ".concat(String.valueOf(String.valueOf(this.ae))));
            ji.io.h.d(s, "> Num pages: ".concat(String.valueOf(String.valueOf(this.d))));
            ji.io.h.d(s, "> Format: ".concat(String.valueOf(String.valueOf(this.i))));
            ji.io.h.d(s, "> Width: ".concat(String.valueOf(String.valueOf(this.j))));
            ji.io.h.d(s, "> Height: ".concat(String.valueOf(String.valueOf(this.k))));
            ji.io.h.d(s, "> ");
            if (!ji.util.d.by(this.al)) {
                try {
                    final StringTokenizer stringTokenizer = new StringTokenizer(this.al, ":");
                    if (stringTokenizer.countTokens() > 0) {
                        while (stringTokenizer.hasMoreTokens()) {
                            ji.io.h.d(s, "> ".concat(String.valueOf(String.valueOf(ji.util.d.bc(stringTokenizer.nextToken())))));
                        }
                    }
                }
                catch (Exception ex) {}
                ji.io.h.d(s, "> Network time for this file = ".concat(String.valueOf(String.valueOf(this.a(this.v)))));
                if (ji.util.d.z() > 0) {
                    ji.io.h.d(s, String.valueOf(String.valueOf(new StringBuffer("> General network: ").append(ji.util.d.a((long)(int)ji.util.d.z(), s)).append("/second"))));
                }
                ji.io.h.d(s, String.valueOf(String.valueOf(new StringBuffer("> Total network activity so far: ").append(ji.util.d.a(ji.util.d.y(), s)).append(" bytes"))));
            }
            else {
                ji.io.h.d(s, "> network times not available (local file)");
            }
            ji.io.h.d(s, "> ");
            ji.io.h.d(s, String.valueOf(String.valueOf(new StringBuffer("> ").append(ji.util.d.b(678, s)).append(" = ").append(this.a(this.ai)))));
            ji.io.h.d(s, String.valueOf(String.valueOf(new StringBuffer("> ").append(ji.util.d.b(679, s)).append(" = ").append(this.a(this.a)))));
            ji.io.h.d(s, "> ");
            if (this.q > 0) {
                if (this.w == 0) {
                    ji.io.h.d(s, "> Time to display this page (network not used) = ".concat(String.valueOf(String.valueOf(this.a(this.q)))));
                }
                else {
                    ji.io.h.d(s, "> Time to display this page excluding networking = ".concat(String.valueOf(String.valueOf(this.a(this.q)))));
                }
            }
            if (this.w > 0) {
                ji.io.h.d(s, "> Time to display this page including networking = ".concat(String.valueOf(String.valueOf(this.a(this.w)))));
            }
            if (this.y > 0 || this.aa > 0) {
                if (this.y == this.aa) {
                    ji.io.h.d(s, "> Time to display this page since key press = ".concat(String.valueOf(String.valueOf(this.a(this.y)))));
                }
                else {
                    ji.io.h.d(s, "> Time to display this page since mouse press = ".concat(String.valueOf(String.valueOf(this.a(this.y)))));
                    ji.io.h.d(s, "> Time to display this page since mouse release = ".concat(String.valueOf(String.valueOf(this.a(this.aa)))));
                }
            }
            ji.io.h.d(s, "> ");
            ji.io.h.d(s, "> Performance log end................");
        }
        catch (Exception ex2) {}
    }
    
    private final String a(final long n) {
        String s = "0";
        if (n > 0) {
            final double n2 = n / 1000.0;
            String s2 = "".concat(String.valueOf(String.valueOf(n2)));
            final int index = s2.indexOf(".");
            if (index > 0) {
                s2 = s2.substring(0, Math.min(index + 4, s2.length()));
            }
            if (n2 == 1.0) {
                s = String.valueOf(String.valueOf(s2)).concat(" second");
            }
            else {
                s = String.valueOf(String.valueOf(s2)).concat(" seconds");
            }
        }
        return s;
    }
}
