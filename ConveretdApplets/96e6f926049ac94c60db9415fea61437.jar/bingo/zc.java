// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.bb;
import a.b;
import neat.kb;
import neat.system.f;
import a.hb;

public class zc extends hb
{
    private static f h;
    public kb i;
    public kb j;
    public kb k;
    public kb l;
    public kb m;
    public kb n;
    public kb o;
    public kb p;
    public kb q;
    public kb r;
    public kb s;
    private static /* synthetic */ Class t;
    private static String z;
    
    public b a() {
        return bingo.z.s();
    }
    
    public void b() {
        zc.h.a(this);
    }
    
    public static bb newShadow() {
        return (bb)zc.h.a();
    }
    
    public static zc a() {
        return (zc)zc.h.a();
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
        if (this.p != null) {
            this.p.f();
            this.p = null;
        }
        if (this.q != null) {
            this.q.f();
            this.q = null;
        }
        if (this.n != null) {
            this.n.f();
            this.n = null;
        }
        if (this.o != null) {
            this.o.f();
            this.o = null;
        }
        if (this.r != null) {
            this.r.f();
            this.r = null;
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
    
    public zc() {
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
    }
    
    static {
        final char[] charArray = "\u001c5O\t\u001eP&B".toCharArray();
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
                            c2 = '~';
                            break;
                        }
                        case 1: {
                            c2 = '\\';
                            break;
                        }
                        case 2: {
                            c2 = '!';
                            break;
                        }
                        case 3: {
                            c2 = 'n';
                            break;
                        }
                        default: {
                            c2 = 'q';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                zc.z = new String(charArray).intern();
                zc.h = new f((zc.t != null) ? zc.t : (zc.t = a(zc.z)));
                return;
            }
            continue;
        }
    }
}
