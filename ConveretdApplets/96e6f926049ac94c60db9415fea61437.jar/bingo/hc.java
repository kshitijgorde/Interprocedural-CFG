// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.kb;
import neat.system.f;
import neat.bb;

public class hc extends bb
{
    private static f d;
    public kb e;
    public kb f;
    public boolean g;
    public kb h;
    private static /* synthetic */ Class i;
    private static String z;
    
    public void b() {
        hc.d.a(this);
    }
    
    public static bb newShadow() {
        return (bb)hc.d.a();
    }
    
    public static hc a() {
        return (hc)hc.d.a();
    }
    
    public void g() {
        super.g();
        this.g = false;
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
        if (this.h != null) {
            this.h.f();
            this.h = null;
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
    
    public hc() {
        this.e = null;
        this.f = null;
        this.h = null;
    }
    
    static {
        final char[] charArray = "\u001b/l=-W.a".toCharArray();
        int length;
        int n2;
        final int n = n2 = (length = charArray.length);
        int n3 = 0;
        while (true) {
            Label_0093: {
                if (n > 1) {
                    break Label_0093;
                }
                length = (n2 = n3);
                do {
                    final char c = charArray[n2];
                    char c2 = '\0';
                    switch (n3 % 5) {
                        case 0: {
                            c2 = 'y';
                            break;
                        }
                        case 1: {
                            c2 = 'F';
                            break;
                        }
                        case 2: {
                            c2 = '\u0002';
                            break;
                        }
                        case 3: {
                            c2 = 'Z';
                            break;
                        }
                        default: {
                            c2 = 'B';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                hc.z = new String(charArray).intern();
                hc.d = new f((hc.i != null) ? hc.i : (hc.i = a(hc.z)));
                return;
            }
            continue;
        }
    }
}
