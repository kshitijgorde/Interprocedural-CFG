// 
// Decompiled by Procyon v0.5.30
// 

package neat;

import neat.system.f;

public class eb extends db
{
    public static transient neat.cb _default;
    public static transient neat.cb link;
    private static f h;
    private static /* synthetic */ Class i;
    private static String z;
    
    public void b() {
        eb.h.a(this);
    }
    
    public static bb newShadow() {
        return (bb)eb.h.a();
    }
    
    public static eb a() {
        return (eb)eb.h.a();
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
        final char[] charArray = "\u0007b+Oo\fe".toCharArray();
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
                            c2 = 'i';
                            break;
                        }
                        case 1: {
                            c2 = '\u0007';
                            break;
                        }
                        case 2: {
                            c2 = 'J';
                            break;
                        }
                        case 3: {
                            c2 = ';';
                            break;
                        }
                        default: {
                            c2 = 'A';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                eb.z = new String(charArray).intern();
                eb.h = new f((eb.i != null) ? eb.i : (eb.i = b(eb.z)));
                return;
            }
            continue;
        }
    }
}
