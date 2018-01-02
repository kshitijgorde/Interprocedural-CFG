// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.bb;
import a.b;
import neat.cb;
import neat.system.f;
import a.mb;

public class cd extends mb
{
    private static f k;
    public neat.cb l;
    public neat.cb m;
    public neat.cb n;
    public neat.cb o;
    public neat.cb p;
    public neat.cb q;
    public neat.cb r;
    public int s;
    public int t;
    public int u;
    public int v;
    public int w;
    public neat.cb x;
    private static /* synthetic */ Class y;
    private static String z;
    
    public b a() {
        return bingo.cb.u();
    }
    
    public void b() {
        cd.k.a(this);
    }
    
    public static bb newShadow() {
        return (bb)cd.k.a();
    }
    
    public static cd a() {
        return (cd)cd.k.a();
    }
    
    public void g() {
        super.g();
        this.s = 0;
        this.t = 0;
        this.u = 0;
        this.v = 0;
        this.w = -1;
    }
    
    public void h() {
        super.h();
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
        if (this.x != null) {
            this.x.f();
            this.x = null;
        }
    }
    
    static /* synthetic */ Class b(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public cd() {
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = null;
        this.q = null;
        this.r = null;
        this.x = null;
    }
    
    static {
        final char[] charArray = "a-5c&-'?".toCharArray();
        int length;
        int n2;
        final int n = n2 = (length = charArray.length);
        int n3 = 0;
        while (true) {
            Label_0092: {
                if (n > 1) {
                    break Label_0092;
                }
                length = (n2 = n3);
                do {
                    final char c = charArray[n2];
                    char c2 = '\0';
                    switch (n3 % 5) {
                        case 0: {
                            c2 = '\u0003';
                            break;
                        }
                        case 1: {
                            c2 = 'D';
                            break;
                        }
                        case 2: {
                            c2 = '[';
                            break;
                        }
                        case 3: {
                            c2 = '\u0004';
                            break;
                        }
                        default: {
                            c2 = 'I';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                cd.z = new String(charArray).intern();
                cd.k = new f((cd.y != null) ? cd.y : (cd.y = b(cd.z)));
                return;
            }
            continue;
        }
    }
}
