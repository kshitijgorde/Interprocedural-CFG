// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.kb;
import neat.cb;
import neat.system.f;
import neat.bb;

public class lb extends bb
{
    private static f d;
    public int e;
    public neat.cb f;
    public neat.cb g;
    public neat.cb h;
    public neat.cb i;
    public neat.cb j;
    public kb k;
    private static /* synthetic */ Class l;
    private static String z;
    
    public void b() {
        lb.d.a(this);
    }
    
    public static bb newShadow() {
        return (bb)lb.d.a();
    }
    
    public static lb a() {
        return (lb)lb.d.a();
    }
    
    public void g() {
        super.g();
        this.e = -1;
    }
    
    public void h() {
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
    
    public lb() {
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
    }
    
    static {
        final char[] charArray = "H\u000b/V\u0019\u0004\u000e#".toCharArray();
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
                            c2 = '*';
                            break;
                        }
                        case 1: {
                            c2 = 'b';
                            break;
                        }
                        case 2: {
                            c2 = 'A';
                            break;
                        }
                        case 3: {
                            c2 = '1';
                            break;
                        }
                        default: {
                            c2 = 'v';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                lb.z = new String(charArray).intern();
                lb.d = new f((lb.l != null) ? lb.l : (lb.l = a(lb.z)));
                return;
            }
            continue;
        }
    }
}
