// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.cb;
import neat.kb;
import neat.system.f;
import neat.bb;

public class cc extends bb
{
    private static f d;
    public kb e;
    public neat.cb f;
    public neat.cb g;
    public neat.cb h;
    public neat.cb i;
    public neat.cb j;
    public neat.cb k;
    public neat.cb l;
    public neat.cb m;
    public neat.cb n;
    public neat.cb o;
    public int p;
    public int q;
    public int r;
    public int s;
    private static /* synthetic */ Class t;
    private static String z;
    
    public void b() {
        cc.d.a(this);
    }
    
    public static bb newShadow() {
        return (bb)cc.d.a();
    }
    
    public static cc a() {
        return (cc)cc.d.a();
    }
    
    public void g() {
        super.g();
        this.p = -1;
        this.q = -1;
        this.r = -1;
        this.s = -1;
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
    
    public cc() {
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
    }
    
    static {
        final char[] charArray = "?T6ius^;".toCharArray();
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
                            c2 = ']';
                            break;
                        }
                        case 1: {
                            c2 = '=';
                            break;
                        }
                        case 2: {
                            c2 = 'X';
                            break;
                        }
                        case 3: {
                            c2 = '\u000e';
                            break;
                        }
                        default: {
                            c2 = '\u001a';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                cc.z = new String(charArray).intern();
                cc.d = new f((cc.t != null) ? cc.t : (cc.t = a(cc.z)));
                return;
            }
            continue;
        }
    }
}
