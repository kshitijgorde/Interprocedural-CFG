// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.tt;

import com.daysofwonder.req.k;
import com.daysofwonder.util.t;
import com.daysofwonder.util.G;
import java.util.StringTokenizer;

public class f
{
    public static final f a;
    private e b;
    private String c;
    private String d;
    private c e;
    private c f;
    private c[] g;
    private int[] h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;
    private boolean n;
    private boolean o;
    
    public f(final e b) {
        this.b = b;
    }
    
    public boolean a(final String s) {
        try {
            this.k = -1;
            final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
            this.c = stringTokenizer.nextToken();
            this.d = stringTokenizer.nextToken();
            final int int1 = Integer.parseInt(stringTokenizer.nextToken());
            this.i = int1;
            this.j = int1;
            if (stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                this.n = nextToken.equalsIgnoreCase("long");
                if (!this.n && nextToken.length() > 0) {
                    this.o = true;
                    final G g = new G();
                    final G g2 = new G();
                    g.c(this.d);
                    g2.c((Object)this.i);
                    g.c(nextToken);
                    while (stringTokenizer.hasMoreTokens()) {
                        g2.c((Object)Integer.parseInt(stringTokenizer.nextToken()));
                        if (stringTokenizer.hasMoreTokens()) {
                            g.c(stringTokenizer.nextToken());
                        }
                    }
                    this.g = new c[g.a()];
                    this.h = new int[g.a()];
                    this.j = 123456789;
                    for (int i = 0; i < g.a(); ++i) {
                        this.g[i] = this.b.a((String)g.b(i));
                        this.h[i] = (int)g2.b(i);
                        if (this.h[i] < this.j) {
                            this.j = this.h[i];
                        }
                        if (this.h[i] > this.k) {
                            this.k = this.h[i];
                            this.l = i;
                        }
                    }
                }
            }
        }
        catch (Exception ex) {
            t.a(ex);
            return false;
        }
        this.e = this.b.a(this.c);
        this.f = this.b.a(this.d);
        return true;
    }
    
    public int a() {
        return (this.k == -1) ? this.i : this.k;
    }
    
    public int a(final c c, final c c2) {
        for (int i = 0; i < this.g.length; ++i) {
            if (this.g[i] == c2 || this.g[i] == c) {
                return this.h[i];
            }
        }
        return 0;
    }
    
    public int b() {
        return this.j;
    }
    
    public boolean c() {
        return this.o;
    }
    
    public boolean d() {
        return this.n;
    }
    
    public c a(final int n) {
        return this.g[n];
    }
    
    public int e() {
        return this.g.length;
    }
    
    public c f() {
        return this.e;
    }
    
    public c g() {
        return this.f;
    }
    
    public String h() {
        return this.c;
    }
    
    public String i() {
        return this.d;
    }
    
    public int j() {
        return this.m;
    }
    
    public void b(final int m) {
        this.m = m;
    }
    
    public void a(final k k) {
        k.writeByte(this.m);
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append(this.c).append('-').append(this.d).append('[').append(this.i).append(')');
        return sb.toString();
    }
    
    static {
        (a = new f(null)).b(-1);
    }
}
