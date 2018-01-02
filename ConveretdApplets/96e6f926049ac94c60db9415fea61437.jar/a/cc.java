// 
// Decompiled by Procyon v0.5.30
// 

package a;

import neat.bb;
import neat.system.f;
import neat.kb;
import neat.db;

public class cc extends db
{
    public static transient kb _default;
    private static f h;
    private static /* synthetic */ Class i;
    private static String z;
    
    public void b() {
        cc.h.a(this);
    }
    
    public static bb newShadow() {
        return (bb)cc.h.a();
    }
    
    public static cc a() {
        return (cc)cc.h.a();
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
        final char[] charArray = "Kr=L".toCharArray();
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
                            c2 = '\\';
                            break;
                        }
                        case 2: {
                            c2 = '^';
                            break;
                        }
                        case 3: {
                            c2 = '/';
                            break;
                        }
                        default: {
                            c2 = 'x';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                cc.z = new String(charArray).intern();
                cc.h = new f((cc.i != null) ? cc.i : (cc.i = b(cc.z)));
                return;
            }
            continue;
        }
    }
}
