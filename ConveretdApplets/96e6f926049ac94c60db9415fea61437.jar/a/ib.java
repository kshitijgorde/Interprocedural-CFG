// 
// Decompiled by Procyon v0.5.30
// 

package a;

import neat.bb;
import neat.kb;
import neat.cb;
import neat.system.f;

public class ib extends hb
{
    private static f h;
    public neat.cb i;
    public int j;
    public int k;
    public int l;
    public int m;
    public boolean n;
    public int o;
    public int p;
    public kb q;
    public kb r;
    public int s;
    public boolean t;
    private static /* synthetic */ Class u;
    private static String z;
    
    public b a() {
        return a.d.t();
    }
    
    public void b() {
        ib.h.a(this);
    }
    
    public static bb newShadow() {
        return (bb)ib.h.a();
    }
    
    public static ib a() {
        return (ib)ib.h.a();
    }
    
    public void g() {
        super.g();
        this.j = 0;
        this.k = 0;
        this.l = 0;
        this.m = 0;
        this.n = false;
        this.o = 250;
        this.p = 1000;
        this.s = 3;
        this.t = false;
    }
    
    public void h() {
        if (this.i != null) {
            this.i.f();
            this.i = null;
        }
        if (this.q != null) {
            this.q.f();
            this.q = null;
        }
        if (this.r != null) {
            this.r.f();
            this.r = null;
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
    
    public ib() {
        this.i = null;
        this.q = null;
        this.r = null;
    }
    
    static {
        final char[] charArray = "4I\u007f]".toCharArray();
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
                            c2 = 'U';
                            break;
                        }
                        case 1: {
                            c2 = 'g';
                            break;
                        }
                        case 2: {
                            c2 = '\u0016';
                            break;
                        }
                        case 3: {
                            c2 = '?';
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
                ib.z = new String(charArray).intern();
                ib.h = new f((ib.u != null) ? ib.u : (ib.u = a(ib.z)));
                return;
            }
            continue;
        }
    }
}
