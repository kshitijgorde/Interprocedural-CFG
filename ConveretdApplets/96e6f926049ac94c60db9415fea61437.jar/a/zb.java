// 
// Decompiled by Procyon v0.5.30
// 

package a;

import neat.bb;
import neat.kb;
import neat.system.f;
import neat.cb;

public class zb extends cb
{
    private static f o;
    public boolean p;
    public kb q;
    public kb r;
    public cb s;
    private static /* synthetic */ Class t;
    private static String z;
    
    public void b() {
        zb.o.a(this);
    }
    
    public static bb newShadow() {
        return (bb)zb.o.a();
    }
    
    public static zb a() {
        return (zb)zb.o.a();
    }
    
    public void g() {
        super.g();
        this.p = false;
    }
    
    public void h() {
        super.h();
        if (this.s != null) {
            this.s.f();
            this.s = null;
        }
        if (this.q != null) {
            this.q.f();
            this.q = null;
        }
        if (this.r != null) {
            this.r.f();
            this.r = null;
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
    
    public zb() {
        this.q = null;
        this.r = null;
        this.s = null;
    }
    
    static {
        final char[] charArray = "U~dk".toCharArray();
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
                            c2 = '4';
                            break;
                        }
                        case 1: {
                            c2 = 'P';
                            break;
                        }
                        case 2: {
                            c2 = '\u001e';
                            break;
                        }
                        case 3: {
                            c2 = '\t';
                            break;
                        }
                        default: {
                            c2 = 'X';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                zb.z = new String(charArray).intern();
                zb.o = new f((zb.t != null) ? zb.t : (zb.t = b(zb.z)));
                return;
            }
            continue;
        }
    }
}
