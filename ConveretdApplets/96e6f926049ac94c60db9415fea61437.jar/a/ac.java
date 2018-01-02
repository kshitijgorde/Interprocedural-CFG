// 
// Decompiled by Procyon v0.5.30
// 

package a;

import neat.bb;
import neat.system.f;
import neat.db;

public class ac extends db
{
    public static transient qb _default;
    public static transient qb chunk;
    private static f h;
    private static /* synthetic */ Class i;
    private static String z;
    
    public void b() {
        ac.h.a(this);
    }
    
    public static bb newShadow() {
        return (bb)ac.h.a();
    }
    
    public static ac a() {
        return (ac)ac.h.a();
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
        final char[] charArray = "DrW\u001b".toCharArray();
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
                            c2 = '%';
                            break;
                        }
                        case 1: {
                            c2 = '\\';
                            break;
                        }
                        case 2: {
                            c2 = '6';
                            break;
                        }
                        case 3: {
                            c2 = 'x';
                            break;
                        }
                        default: {
                            c2 = '4';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                ac.z = new String(charArray).intern();
                ac.h = new f((ac.i != null) ? ac.i : (ac.i = b(ac.z)));
                return;
            }
            continue;
        }
    }
}
