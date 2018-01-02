// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.bb;
import a.b;
import neat.eb;
import neat.cb;
import neat.system.f;

public class vc extends uc
{
    private static f j;
    public neat.cb k;
    public eb l;
    public int m;
    public jd n;
    public kc o;
    public lc p;
    private static /* synthetic */ Class q;
    private static String z;
    
    public b a() {
        return v.q();
    }
    
    public void b() {
        vc.j.a(this);
    }
    
    public static bb newShadow() {
        return (bb)vc.j.a();
    }
    
    public static vc a() {
        return (vc)vc.j.a();
    }
    
    public void g() {
        super.g();
        this.m = 1000;
    }
    
    public void h() {
        super.h();
        if (this.k != null) {
            this.k.f();
            this.k = null;
        }
        if (this.l != null) {
            this.l.f();
            this.l = null;
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
    }
    
    static /* synthetic */ Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public vc() {
        this.k = null;
        this.l = null;
        this.n = null;
        this.o = null;
        this.p = null;
    }
    
    static {
        final char[] charArray = "\u0017dLIc[{A".toCharArray();
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
                            c2 = 'u';
                            break;
                        }
                        case 1: {
                            c2 = '\r';
                            break;
                        }
                        case 2: {
                            c2 = '\"';
                            break;
                        }
                        case 3: {
                            c2 = '.';
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
                vc.z = new String(charArray).intern();
                vc.j = new f((vc.q != null) ? vc.q : (vc.q = a(vc.z)));
                return;
            }
            continue;
        }
    }
}
