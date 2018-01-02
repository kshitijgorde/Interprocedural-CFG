// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.bb;
import neat.system.f;
import neat.db;

public class fd extends db
{
    public static transient ac _default;
    private static f h;
    private static /* synthetic */ Class i;
    private static String z;
    
    public void b() {
        fd.h.a(this);
    }
    
    public static bb newShadow() {
        return (bb)fd.h.a();
    }
    
    public static fd a() {
        return (fd)fd.h.a();
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
        final char[] charArray = "x/\u0019\u001f,4 \u0013".toCharArray();
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
                            c2 = '\u001a';
                            break;
                        }
                        case 1: {
                            c2 = 'F';
                            break;
                        }
                        case 2: {
                            c2 = 'w';
                            break;
                        }
                        case 3: {
                            c2 = 'x';
                            break;
                        }
                        default: {
                            c2 = 'C';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                fd.z = new String(charArray).intern();
                fd.h = new f((fd.i != null) ? fd.i : (fd.i = b(fd.z)));
                return;
            }
            continue;
        }
    }
}
