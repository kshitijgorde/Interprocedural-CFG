// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.bb;
import a.b;
import neat.eb;
import neat.kb;
import neat.cb;
import neat.system.f;
import a.hb;

public class tc extends hb
{
    private static f h;
    public hd i;
    public neat.cb j;
    public kb k;
    public eb l;
    public kb m;
    public kb n;
    public int o;
    public id p;
    public id q;
    public int r;
    private static /* synthetic */ Class s;
    private static String z;
    
    public b a() {
        return t.C();
    }
    
    public void b() {
        tc.h.a(this);
    }
    
    public static bb newShadow() {
        return (bb)tc.h.a();
    }
    
    public static tc a() {
        return (tc)tc.h.a();
    }
    
    public void g() {
        super.g();
        this.o = 1000;
        this.r = 10000;
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
        if (this.p != null) {
            this.p.f();
            this.p = null;
        }
        if (this.q != null) {
            this.q.f();
            this.q = null;
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
    
    public tc() {
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.p = null;
        this.q = null;
    }
    
    static {
        final char[] charArray = " gD\u0017\u0015lzI".toCharArray();
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
                            c2 = 'B';
                            break;
                        }
                        case 1: {
                            c2 = '\u000e';
                            break;
                        }
                        case 2: {
                            c2 = '*';
                            break;
                        }
                        case 3: {
                            c2 = 'p';
                            break;
                        }
                        default: {
                            c2 = 'z';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                tc.z = new String(charArray).intern();
                tc.h = new f((tc.s != null) ? tc.s : (tc.s = a(tc.z)));
                return;
            }
            continue;
        }
    }
}
