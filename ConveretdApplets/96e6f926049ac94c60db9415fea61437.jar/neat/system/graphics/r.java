// 
// Decompiled by Procyon v0.5.30
// 

package neat.system.graphics;

import neat.bb;
import neat.system.f;

public class r extends q
{
    private static f h;
    private static /* synthetic */ Class i;
    private static String z;
    
    public void b() {
        r.h.a(this);
    }
    
    public static bb newShadow() {
        return (bb)r.h.a();
    }
    
    public static r a() {
        return (r)r.h.a();
    }
    
    public void g() {
        super.g();
    }
    
    public void h() {
        super.h();
    }
    
    static /* synthetic */ Class b(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        final char[] charArray = "g\u001c=@\u001ez\u0000/@UdW;FQy\u00115WC'\u000b".toCharArray();
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
                            c2 = '\t';
                            break;
                        }
                        case 1: {
                            c2 = 'y';
                            break;
                        }
                        case 2: {
                            c2 = '\\';
                            break;
                        }
                        case 3: {
                            c2 = '4';
                            break;
                        }
                        default: {
                            c2 = '0';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                r.z = new String(charArray).intern();
                r.h = new f((r.i != null) ? r.i : (r.i = b(r.z)));
                return;
            }
            continue;
        }
    }
}
