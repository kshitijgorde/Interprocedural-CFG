// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.bb;
import neat.system.f;
import neat.db;

public class hd extends db
{
    public static transient ic _default;
    private static f h;
    private static /* synthetic */ Class i;
    private static String z;
    
    public void b() {
        hd.h.a(this);
    }
    
    public static bb newShadow() {
        return (bb)hd.h.a();
    }
    
    public static hd a() {
        return (hd)hd.h.a();
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
        final char[] charArray = "D\u001a{\u0014\u001f\b\u001bq".toCharArray();
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
                            c2 = '&';
                            break;
                        }
                        case 1: {
                            c2 = 's';
                            break;
                        }
                        case 2: {
                            c2 = '\u0015';
                            break;
                        }
                        case 3: {
                            c2 = 's';
                            break;
                        }
                        default: {
                            c2 = 'p';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                hd.z = new String(charArray).intern();
                hd.h = new f((hd.i != null) ? hd.i : (hd.i = b(hd.z)));
                return;
            }
            continue;
        }
    }
}
