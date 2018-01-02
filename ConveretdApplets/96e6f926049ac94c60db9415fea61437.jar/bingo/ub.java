// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.kb;
import neat.cb;
import neat.system.f;
import neat.bb;

public class ub extends bb
{
    private static f d;
    public neat.cb e;
    public neat.cb f;
    public neat.cb g;
    public neat.cb h;
    public neat.cb i;
    public int j;
    public int k;
    public int l;
    public neat.cb m;
    public kb n;
    private static /* synthetic */ Class o;
    private static String z;
    
    public void b() {
        ub.d.a(this);
    }
    
    public static bb newShadow() {
        return (bb)ub.d.a();
    }
    
    public static ub a() {
        return (ub)ub.d.a();
    }
    
    public void g() {
        super.g();
        this.j = 0;
        this.k = 0;
        this.l = 0;
    }
    
    public void h() {
        if (this.e != null) {
            this.e.f();
            this.e = null;
        }
        if (this.f != null) {
            this.f.f();
            this.f = null;
        }
        if (this.g != null) {
            this.g.f();
            this.g = null;
        }
        if (this.h != null) {
            this.h.f();
            this.h = null;
        }
        if (this.i != null) {
            this.i.f();
            this.i = null;
        }
        if (this.m != null) {
            this.m.f();
            this.m = null;
        }
        if (this.n != null) {
            this.n.f();
            this.n = null;
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
    
    public ub() {
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.m = null;
        this.n = null;
    }
    
    static {
        final char[] charArray = "ura@.9nm".toCharArray();
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
                            c2 = '\u0017';
                            break;
                        }
                        case 1: {
                            c2 = '\u001b';
                            break;
                        }
                        case 2: {
                            c2 = '\u000f';
                            break;
                        }
                        case 3: {
                            c2 = '\'';
                            break;
                        }
                        default: {
                            c2 = 'A';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                ub.z = new String(charArray).intern();
                ub.d = new f((ub.o != null) ? ub.o : (ub.o = a(ub.z)));
                return;
            }
            continue;
        }
    }
}
