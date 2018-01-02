// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.cb;
import neat.kb;
import neat.system.f;
import neat.bb;

public class nc extends bb
{
    private static f d;
    public int e;
    public int f;
    public kb g;
    public int h;
    public int i;
    public neat.cb j;
    public int k;
    public int l;
    public int m;
    public boolean n;
    public int o;
    public int p;
    public int q;
    public int r;
    public kb s;
    public int t;
    public int u;
    public boolean v;
    private static /* synthetic */ Class w;
    private static String z;
    
    public void b() {
        nc.d.a(this);
    }
    
    public static bb newShadow() {
        return (bb)nc.d.a();
    }
    
    public static nc a() {
        return (nc)nc.d.a();
    }
    
    public void g() {
        super.g();
        this.e = 0;
        this.f = 0;
        this.h = 0;
        this.i = 0;
        this.k = 0;
        this.l = 0;
        this.m = -1;
        this.n = false;
        this.o = 1000;
        this.p = -1;
        this.q = -1;
        this.r = -1;
        this.t = 0;
        this.u = 0;
        this.v = false;
    }
    
    public void h() {
        if (this.g != null) {
            this.g.f();
            this.g = null;
        }
        if (this.j != null) {
            this.j.f();
            this.j = null;
        }
        if (this.s != null) {
            this.s.f();
            this.s = null;
        }
        super.h();
    }
    
    static /* synthetic */ Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public nc() {
        this.g = null;
        this.j = null;
        this.s = null;
    }
    
    static {
        final char[] charArray = "&\u0005\tP9j\u0002\u0004".toCharArray();
        int length;
        int n2;
        final int n = n2 = (length = charArray.length);
        int n3 = 0;
        while (true) {
            Label_0094: {
                if (n > 1) {
                    break Label_0094;
                }
                length = (n2 = n3);
                do {
                    final char c = charArray[n2];
                    char c2 = '\0';
                    switch (n3 % 5) {
                        case 0: {
                            c2 = 'D';
                            break;
                        }
                        case 1: {
                            c2 = 'l';
                            break;
                        }
                        case 2: {
                            c2 = 'g';
                            break;
                        }
                        case 3: {
                            c2 = '7';
                            break;
                        }
                        default: {
                            c2 = 'V';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                nc.z = new String(charArray).intern();
                nc.d = new f((nc.w != null) ? nc.w : (nc.w = a(nc.z)));
                return;
            }
            continue;
        }
    }
}
