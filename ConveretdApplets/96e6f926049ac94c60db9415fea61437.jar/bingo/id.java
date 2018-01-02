// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.bb;
import neat.system.f;
import neat.db;

public class id extends db
{
    public static transient jc _default;
    private static f h;
    private static /* synthetic */ Class i;
    private static String z;
    
    public void b() {
        id.h.a(this);
    }
    
    public static bb newShadow() {
        return (bb)id.h.a();
    }
    
    public static id a() {
        return (id)id.h.a();
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
        final char[] charArray = "\u007f7`(937j".toCharArray();
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
                            c2 = '\u001d';
                            break;
                        }
                        case 1: {
                            c2 = '^';
                            break;
                        }
                        case 2: {
                            c2 = '\u000e';
                            break;
                        }
                        case 3: {
                            c2 = 'O';
                            break;
                        }
                        default: {
                            c2 = 'V';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                id.z = new String(charArray).intern();
                id.h = new f((id.i != null) ? id.i : (id.i = b(id.z)));
                return;
            }
            continue;
        }
    }
}
