// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.bb;
import neat.system.f;
import neat.db;

public class kd extends db
{
    public static transient oc _default;
    private static f h;
    private static /* synthetic */ Class i;
    private static String z;
    
    public void b() {
        kd.h.a(this);
    }
    
    public static bb newShadow() {
        return (bb)kd.h.a();
    }
    
    public static kd a() {
        return (kd)kd.h.a();
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
        final char[] charArray = "E\b9\u0003U\t\n3".toCharArray();
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
                            c2 = '\'';
                            break;
                        }
                        case 1: {
                            c2 = 'a';
                            break;
                        }
                        case 2: {
                            c2 = 'W';
                            break;
                        }
                        case 3: {
                            c2 = 'd';
                            break;
                        }
                        default: {
                            c2 = ':';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                kd.z = new String(charArray).intern();
                kd.h = new f((kd.i != null) ? kd.i : (kd.i = b(kd.z)));
                return;
            }
            continue;
        }
    }
}