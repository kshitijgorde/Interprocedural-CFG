// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.bb;
import neat.system.f;
import neat.db;

public class gd extends db
{
    public static transient hc _default;
    public static transient hc saveData;
    private static f h;
    private static /* synthetic */ Class i;
    private static String z;
    
    public void b() {
        gd.h.a(this);
    }
    
    public static bb newShadow() {
        return (bb)gd.h.a();
    }
    
    public static gd a() {
        return (gd)gd.h.a();
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
        final char[] charArray = "r\u001b{!1>\u0015q".toCharArray();
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
                            c2 = '\u0010';
                            break;
                        }
                        case 1: {
                            c2 = 'r';
                            break;
                        }
                        case 2: {
                            c2 = '\u0015';
                            break;
                        }
                        case 3: {
                            c2 = 'F';
                            break;
                        }
                        default: {
                            c2 = '^';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                gd.z = new String(charArray).intern();
                gd.h = new f((gd.i != null) ? gd.i : (gd.i = b(gd.z)));
                return;
            }
            continue;
        }
    }
}
