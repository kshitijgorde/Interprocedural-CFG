// 
// Decompiled by Procyon v0.5.30
// 

package neat;

import neat.system.f;

public final class fb extends db
{
    public static transient kb _default;
    private static f h;
    private static /* synthetic */ Class i;
    private static String z;
    
    public void b() {
        fb.h.a(this);
    }
    
    public static bb newShadow() {
        return (bb)fb.h.a();
    }
    
    public static fb a() {
        return (fb)fb.h.a();
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
        final char[] charArray = "c5}gMk2".toCharArray();
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
                            c2 = '\r';
                            break;
                        }
                        case 1: {
                            c2 = 'P';
                            break;
                        }
                        case 2: {
                            c2 = '\u001c';
                            break;
                        }
                        case 3: {
                            c2 = '\u0013';
                            break;
                        }
                        default: {
                            c2 = 'c';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                fb.z = new String(charArray).intern();
                fb.h = new f((fb.i != null) ? fb.i : (fb.i = b(fb.z)));
                return;
            }
            continue;
        }
    }
}
