// 
// Decompiled by Procyon v0.5.30
// 

package a.a.c;

import java.util.Enumeration;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;

public class c
{
    private static final Class a;
    private static Properties b;
    private static Hashtable c;
    static /* synthetic */ Class d;
    private static String z;
    
    public static Properties a() throws IOException {
        if (a.a.c.c.b != null) {
            return a.a.c.c.b;
        }
        b();
        return a.a.c.c.b;
    }
    
    public static void a(final String s, final String s2) {
        synchronized (a.a.c.c.c) {
            if (a.a.c.c.b != null) {
                ((Hashtable<String, String>)a.a.c.c.b).put(s, s2);
            }
            a.a.c.c.c.put(s, s2);
        }
    }
    
    protected static void b() throws IOException {
        (a.a.c.c.b = new Properties()).load(a.a.c.c.a.getResourceAsStream(a.a.c.c.z));
        synchronized (a.a.c.c.c) {
            final Enumeration<String> keys = a.a.c.c.c.keys();
            while (keys.hasMoreElements()) {
                final String s = keys.nextElement();
                ((Hashtable<String, String>)a.a.c.c.b).put(s, a.a.c.c.c.get(s));
            }
        }
    }
    
    static /* synthetic */ Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        a.a.c.c.z = z(z("e8\u0007\u001enP\u001b\u0012\u0006\u007fM&\u0010\u0001%T:\u0018\u0002nV<\u001e\u0017x"));
        a = ((a.a.c.c.d == null) ? (a.a.c.c.d = a(z(z("Ef\u0016\\l\n*")))) : a.a.c.c.d);
        a.a.c.c.c = new Hashtable();
        a(z(z("g'\u0019\u001cnG<\u001e\u001de\n\u001a\u0012\u0003~A;\u0003\\CA)\u0013\u0017y")), z(z("q;\u0012\u0000&e/\u0012\u001c\u007f\u001eh:\u001dqM$\u001b\u0013$\u0010fGR#G'\u001a\u0002jP!\u0015\u001en\u001fh6\u0002{H-\u0003RIE;\u0012\u0016+v-\u0006\u0007nW<W?d@=\u001b\u0017\")B6\u0011hA8\u0003_NJ+\u0018\u0016bJ/MRaE>\u0016_xKe\u0018\u0010aA+\u0003\u007f\u0001g)\u0014\u001an\t\u000b\u0018\u001c\u007fV'\u001bH+J'Z\u0011jG \u0012\u007f\u0001")));
        a(z(z("g'\u0019\u001cnG<\u001e\u001de\n\u000b\u0018\u001d`M-Y!nW;\u001e\u001deo-\u000e")), z(z("i\r3'Xv")));
        a(z(z("g'\u0019\u001cnG<\u001e\u001de\n\u000b\u0018\u001d`M-Y!nW;\u001e\u001dew!\r\u0017")), z(z("\u0015~")));
        a(z(z("g'\u0019\u001cnG<\u001e\u001de\n\f\u0012\u001ej]f#\u001bfA")), z(z("\u0015xGB")));
        a(z(z("g'\u0019\u001cnG<\u001e\u001de\n\u001a\u0012\u0003~A;\u0003\\FE05\u0007e@$\u0012!b^-")), z(z("\u0010q")));
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= '\u000b';
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
                    c2 = '$';
                    break;
                }
                case 1: {
                    c2 = 'H';
                    break;
                }
                case 2: {
                    c2 = 'w';
                    break;
                }
                case 3: {
                    c2 = 'r';
                    break;
                }
                default: {
                    c2 = '\u000b';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
