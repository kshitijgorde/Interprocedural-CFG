// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.bb;
import a.b;
import neat.cb;
import neat.kb;
import neat.system.f;
import a.hb;

public class rc extends hb
{
    private static f h;
    public kb i;
    public neat.cb j;
    public neat.cb k;
    public bc l;
    public xb m;
    public yb n;
    public zb o;
    private static /* synthetic */ Class p;
    private static String z;
    
    public b a() {
        return q.N();
    }
    
    public void b() {
        rc.h.a(this);
    }
    
    public static bb newShadow() {
        return (bb)rc.h.a();
    }
    
    public static rc a() {
        return (rc)rc.h.a();
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
    
    public rc() {
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
    }
    
    static {
        final char[] charArray = "M\rg8\u0017\u0001\u0016j".toCharArray();
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
                            c2 = '/';
                            break;
                        }
                        case 1: {
                            c2 = 'd';
                            break;
                        }
                        case 2: {
                            c2 = '\t';
                            break;
                        }
                        case 3: {
                            c2 = '_';
                            break;
                        }
                        default: {
                            c2 = 'x';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                rc.z = new String(charArray).intern();
                rc.h = new f((rc.p != null) ? rc.p : (rc.p = a(rc.z)));
                return;
            }
            continue;
        }
    }
}
