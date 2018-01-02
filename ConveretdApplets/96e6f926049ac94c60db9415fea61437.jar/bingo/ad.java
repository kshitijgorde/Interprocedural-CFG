// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.bb;
import a.b;
import neat.kb;
import neat.cb;
import neat.system.f;
import a.hb;

public class ad extends hb
{
    private static f h;
    public neat.cb i;
    public kb j;
    public kb k;
    public kb l;
    public kb m;
    public kb n;
    public kb o;
    public neat.cb p;
    public kb q;
    public neat.cb r;
    public neat.cb s;
    public neat.cb t;
    public kb u;
    public neat.cb v;
    public kb w;
    public neat.cb x;
    public kb y;
    public neat.cb z;
    public kb A;
    public kb B;
    private static /* synthetic */ Class C;
    private static String D;
    
    public b a() {
        return ab.Q();
    }
    
    public void b() {
        ad.h.a(this);
    }
    
    public static bb newShadow() {
        return (bb)ad.h.a();
    }
    
    public static ad a() {
        return (ad)ad.h.a();
    }
    
    public void g() {
        super.g();
    }
    
    public void h() {
        if (this.i != null) {
            this.i.f();
            this.i = null;
        }
        if (this.j != null) {
            this.j.f();
            this.j = null;
        }
        if (this.k != null) {
            this.k.f();
            this.k = null;
        }
        if (this.l != null) {
            this.l.f();
            this.l = null;
        }
        if (this.m != null) {
            this.m.f();
            this.m = null;
        }
        if (this.n != null) {
            this.n.f();
            this.n = null;
        }
        if (this.o != null) {
            this.o.f();
            this.o = null;
        }
        if (this.p != null) {
            this.p.f();
            this.p = null;
        }
        if (this.q != null) {
            this.q.f();
            this.q = null;
        }
        if (this.r != null) {
            this.r.f();
            this.r = null;
        }
        if (this.s != null) {
            this.s.f();
            this.s = null;
        }
        if (this.t != null) {
            this.t.f();
            this.t = null;
        }
        if (this.u != null) {
            this.u.f();
            this.u = null;
        }
        if (this.v != null) {
            this.v.f();
            this.v = null;
        }
        if (this.w != null) {
            this.w.f();
            this.w = null;
        }
        if (this.x != null) {
            this.x.f();
            this.x = null;
        }
        if (this.y != null) {
            this.y.f();
            this.y = null;
        }
        if (this.z != null) {
            this.z.f();
            this.z = null;
        }
        if (this.A != null) {
            this.A.f();
            this.A = null;
        }
        if (this.B != null) {
            this.B.f();
            this.B = null;
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
    
    public ad() {
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = null;
        this.q = null;
        this.r = null;
        this.s = null;
        this.t = null;
        this.u = null;
        this.v = null;
        this.w = null;
        this.x = null;
        this.y = null;
        this.z = null;
        this.A = null;
        this.B = null;
    }
    
    static {
        final char[] charArray = "d~@5c(vJ".toCharArray();
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
                            c2 = '\u0006';
                            break;
                        }
                        case 1: {
                            c2 = '\u0017';
                            break;
                        }
                        case 2: {
                            c2 = '.';
                            break;
                        }
                        case 3: {
                            c2 = 'R';
                            break;
                        }
                        default: {
                            c2 = '\f';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                ad.D = new String(charArray).intern();
                ad.h = new f((ad.C != null) ? ad.C : (ad.C = a(ad.D)));
                return;
            }
            continue;
        }
    }
}
