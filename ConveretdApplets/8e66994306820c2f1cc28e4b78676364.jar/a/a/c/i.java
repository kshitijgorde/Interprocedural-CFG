// 
// Decompiled by Procyon v0.5.30
// 

package a.a.c;

import a.b.n.a;

public class i extends a
{
    private static final Class g;
    static /* synthetic */ Class h;
    private static String z;
    
    protected String a() {
        return i.z;
    }
    
    protected Class b() {
        return i.g;
    }
    
    public static void b(final boolean b) {
        new i().a(b);
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
        i.z = z(z(".\u001a\u0005@%h\u0018\r\t2s\u0019\u001aB0u\u001f\u000fT"));
        g = ((i.h == null) ? (i.h = b(z(z("`X\b\t,/\u0017")))) : i.h);
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= 'B';
        }
        return charArray;
    }
    
    private static String z(final char[] array) {
        final int i = array.length;
        for (int n = 0; i > n; ++n) {
            final int n2 = n;
            final char c = array[n2];
            char c2 = '\0';
            switch (n % 5) {
                case 0: {
                    c2 = '\u0001';
                    break;
                }
                case 1: {
                    c2 = 'v';
                    break;
                }
                case 2: {
                    c2 = 'j';
                    break;
                }
                case 3: {
                    c2 = '\'';
                    break;
                }
                default: {
                    c2 = 'B';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
