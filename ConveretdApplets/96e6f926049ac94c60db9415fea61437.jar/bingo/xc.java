// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.bb;
import a.b;
import neat.cb;
import neat.system.f;
import a.hb;

public class xc extends hb
{
    private static f h;
    public neat.cb i;
    public int j;
    public int k;
    public neat.cb l;
    public int m;
    public int n;
    public neat.cb o;
    public int p;
    public int q;
    public int r;
    public neat.cb s;
    public int t;
    private static /* synthetic */ Class u;
    private static String z;
    
    public b a() {
        return x.r();
    }
    
    public void b() {
        xc.h.a(this);
    }
    
    public static bb newShadow() {
        return (bb)xc.h.a();
    }
    
    public static xc a() {
        return (xc)xc.h.a();
    }
    
    public void g() {
        super.g();
        this.j = 0;
        this.k = 0;
        this.m = 0;
        this.n = 0;
        this.p = 0;
        this.q = 0;
        this.r = 0;
        this.t = 0;
    }
    
    public void h() {
        if (this.i != null) {
            this.i.f();
            this.i = null;
        }
        if (this.l != null) {
            this.l.f();
            this.l = null;
        }
        if (this.o != null) {
            this.o.f();
            this.o = null;
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
    
    public xc() {
        this.i = null;
        this.l = null;
        this.o = null;
        this.s = null;
    }
    
    static {
        final char[] charArray = "\u007fQ\\}\u00053@Q".toCharArray();
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
                            c2 = '\u001d';
                            break;
                        }
                        case 1: {
                            c2 = '8';
                            break;
                        }
                        case 2: {
                            c2 = '2';
                            break;
                        }
                        case 3: {
                            c2 = '\u001a';
                            break;
                        }
                        default: {
                            c2 = 'j';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                xc.z = new String(charArray).intern();
                xc.h = new f((xc.u != null) ? xc.u : (xc.u = a(xc.z)));
                return;
            }
            continue;
        }
    }
}
