// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.cb;
import neat.kb;
import neat.system.f;
import neat.bb;

public class qb extends bb
{
    private static f d;
    public int e;
    public kb f;
    public int g;
    public int h;
    public int i;
    public int j;
    public neat.cb k;
    public int l;
    public int m;
    public neat.cb n;
    public int o;
    public int p;
    public boolean q;
    public boolean r;
    public boolean s;
    public boolean t;
    public int u;
    public int v;
    public int w;
    public int x;
    public kb y;
    public int z;
    private static /* synthetic */ Class A;
    private static String B;
    
    public void b() {
        qb.d.a(this);
    }
    
    public static bb newShadow() {
        return (bb)qb.d.a();
    }
    
    public static qb a() {
        return (qb)qb.d.a();
    }
    
    public void g() {
        super.g();
        this.e = 0;
        this.g = -1;
        this.h = -1;
        this.i = -1;
        this.j = -1;
        this.l = 0;
        this.m = 0;
        this.o = 0;
        this.p = 0;
        this.q = false;
        this.r = false;
        this.s = false;
        this.t = false;
        this.u = -1;
        this.v = -1;
        this.w = -1;
        this.x = -1;
        this.z = -1;
    }
    
    public void h() {
        if (this.f != null) {
            this.f.f();
            this.f = null;
        }
        if (this.k != null) {
            this.k.f();
            this.k = null;
        }
        if (this.n != null) {
            this.n.f();
            this.n = null;
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
    
    public qb() {
        this.f = null;
        this.k = null;
        this.l = 0;
        this.m = 0;
        this.n = null;
        this.o = 0;
        this.p = 0;
        this.y = null;
    }
    
    static {
        final char[] charArray = "$\u000be4#h\u0013i".toCharArray();
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
                            c2 = 'F';
                            break;
                        }
                        case 1: {
                            c2 = 'b';
                            break;
                        }
                        case 2: {
                            c2 = '\u000b';
                            break;
                        }
                        case 3: {
                            c2 = 'S';
                            break;
                        }
                        default: {
                            c2 = 'L';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                qb.B = new String(charArray).intern();
                qb.d = new f((qb.A != null) ? qb.A : (qb.A = a(qb.B)));
                return;
            }
            continue;
        }
    }
}
