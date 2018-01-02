// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.cb;
import neat.system.f;
import neat.bb;

public class ic extends bb
{
    private static f d;
    public int e;
    public int f;
    public neat.cb g;
    public int h;
    public int i;
    public neat.cb j;
    public int k;
    public int l;
    public neat.cb m;
    public int n;
    public int o;
    public neat.cb p;
    public int q;
    public int r;
    public int s;
    public int t;
    public int u;
    public int v;
    public neat.cb w;
    public int x;
    public int y;
    public boolean z;
    private static /* synthetic */ Class A;
    private static String B;
    
    public void b() {
        ic.d.a(this);
    }
    
    public static bb newShadow() {
        return (bb)ic.d.a();
    }
    
    public static ic a() {
        return (ic)ic.d.a();
    }
    
    public void g() {
        super.g();
        this.e = 0;
        this.f = 0;
        this.h = 0;
        this.i = 0;
        this.k = 0;
        this.l = 0;
        this.n = 0;
        this.o = 0;
        this.q = 0;
        this.r = 0;
        this.s = 0;
        this.t = 0;
        this.u = 0;
        this.v = 0;
        this.x = 0;
        this.y = 0;
        this.z = false;
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
        if (this.m != null) {
            this.m.f();
            this.m = null;
        }
        if (this.p != null) {
            this.p.f();
            this.p = null;
        }
        if (this.w != null) {
            this.w.f();
            this.w = null;
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
    
    public ic() {
        this.g = null;
        this.j = null;
        this.m = null;
        this.p = null;
        this.w = null;
    }
    
    static {
        final char[] charArray = "\u0002pyLtNpt".toCharArray();
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
                            c2 = '`';
                            break;
                        }
                        case 1: {
                            c2 = '\u0019';
                            break;
                        }
                        case 2: {
                            c2 = '\u0017';
                            break;
                        }
                        case 3: {
                            c2 = '+';
                            break;
                        }
                        default: {
                            c2 = '\u001b';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                ic.B = new String(charArray).intern();
                ic.d = new f((ic.A != null) ? ic.A : (ic.A = a(ic.B)));
                return;
            }
            continue;
        }
    }
}
