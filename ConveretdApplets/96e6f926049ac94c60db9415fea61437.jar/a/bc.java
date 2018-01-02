// 
// Decompiled by Procyon v0.5.30
// 

package a;

import neat.bb;
import neat.system.f;
import neat.db;

public class bc extends db
{
    public static transient zb _default;
    public static transient zb link;
    private static f h;
    private static /* synthetic */ Class i;
    private static String z;
    
    public void b() {
        bc.h.a(this);
    }
    
    public static bb newShadow() {
        return (bb)bc.h.a();
    }
    
    public static bc a() {
        return (bc)bc.h.a();
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
        final char[] charArray = "\u0003\u007f|7".toCharArray();
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
                            c2 = 'b';
                            break;
                        }
                        case 1: {
                            c2 = 'Q';
                            break;
                        }
                        case 2: {
                            c2 = '\u001e';
                            break;
                        }
                        case 3: {
                            c2 = 'T';
                            break;
                        }
                        default: {
                            c2 = '7';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                bc.z = new String(charArray).intern();
                bc.h = new f((bc.i != null) ? bc.i : (bc.i = b(bc.z)));
                return;
            }
            continue;
        }
    }
}
