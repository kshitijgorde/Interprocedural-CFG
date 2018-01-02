// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.kb;
import neat.system.f;
import neat.bb;

public class lc extends bb
{
    private static f d;
    public int e;
    public int f;
    public kb g;
    public kb h;
    private static /* synthetic */ Class i;
    private static String z;
    
    public void b() {
        lc.d.a(this);
    }
    
    public static bb newShadow() {
        return (bb)lc.d.a();
    }
    
    public static lc a() {
        return (lc)lc.d.a();
    }
    
    public void g() {
        super.g();
        this.e = -1;
        this.f = -1;
    }
    
    public void h() {
        if (this.g != null) {
            this.g.f();
            this.g = null;
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
    
    public lc() {
        this.g = null;
        this.h = null;
    }
    
    static {
        final char[] charArray = "^E\u0014\u000e\t\u0012@\u0019".toCharArray();
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
                            c2 = '<';
                            break;
                        }
                        case 1: {
                            c2 = ',';
                            break;
                        }
                        case 2: {
                            c2 = 'z';
                            break;
                        }
                        case 3: {
                            c2 = 'i';
                            break;
                        }
                        default: {
                            c2 = 'f';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                lc.z = new String(charArray).intern();
                lc.d = new f((lc.i != null) ? lc.i : (lc.i = a(lc.z)));
                return;
            }
            continue;
        }
    }
}
