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

public class qc extends hb
{
    private static f h;
    public int i;
    public int j;
    public int k;
    public neat.cb l;
    public boolean m;
    public int n;
    public neat.cb o;
    public kb p;
    public kb q;
    public kb r;
    public kb s;
    public kb t;
    public int u;
    public kb v;
    public kb w;
    public kb x;
    public kb y;
    public int z;
    public int A;
    private static /* synthetic */ Class B;
    private static String C;
    
    public b a() {
        return bingo.s.Jb();
    }
    
    public void b() {
        qc.h.a(this);
    }
    
    public static bb newShadow() {
        return (bb)qc.h.a();
    }
    
    public static qc a() {
        return (qc)qc.h.a();
    }
    
    public void g() {
        super.g();
        this.i = 1000;
        this.j = 0;
        this.k = 1000;
        this.m = true;
        this.n = 0;
        this.u = 1000;
        this.z = 1000;
        this.A = 0;
    }
    
    public void h() {
        if (this.l != null) {
            this.l.f();
            this.l = null;
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
    
    public qc() {
        this.l = null;
        this.o = null;
        this.p = null;
        this.q = null;
        this.r = null;
        this.s = null;
        this.t = null;
        this.v = null;
        this.w = null;
        this.x = null;
        this.y = null;
    }
    
    static {
        final char[] charArray = "\u00023[19N+V".toCharArray();
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
                            c2 = 'Z';
                            break;
                        }
                        case 2: {
                            c2 = '5';
                            break;
                        }
                        case 3: {
                            c2 = 'V';
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
                qc.C = new String(charArray).intern();
                qc.h = new f((qc.B != null) ? qc.B : (qc.B = a(qc.C)));
                return;
            }
            continue;
        }
    }
}
