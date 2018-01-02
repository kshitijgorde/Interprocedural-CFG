// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.g;

import flaxchat.c.a;
import flaxchat.a.e;
import java.awt.Component;
import flaxchat.a.k;
import java.util.StringTokenizer;
import java.util.Hashtable;
import flaxchat.n;

public class g extends c
{
    private static String[] z;
    
    public g(final n n) {
        super(n);
    }
    
    public void a(final Object o, final String s, final flaxchat.f.g g, final String s2, final Hashtable hashtable) {
        int b = c.b;
        final StringTokenizer stringTokenizer = new StringTokenizer(s2);
        stringTokenizer.nextToken();
        final String nextToken = stringTokenizer.nextToken();
        final String nextToken2 = stringTokenizer.nextToken();
        a a = super.a.c(g.g());
        if (a == null) {
            a = super.a.c(s);
            if (a == null) {
                k.b(super.a, g.z[0] + g.g() + g.z[3]);
                return;
            }
        }
        final String b2 = a.b(nextToken2, nextToken);
        if (b2 != null) {
            k.b(super.a, b2);
            return;
        }
        final String a2 = k.a(super.a, g.z[1]);
        if (a2 == null) {
            return;
        }
        super.a.h().a(nextToken, g.z[2] + nextToken2 + g.z[4] + a2);
        if (e.c != 0) {
            c.b = ++b;
        }
    }
    
    public String a() {
        return g.z[5];
    }
    
    static {
        g.z = new String[] { z(z("\u000e\u0005\ro\u0002a|")), z(z("\u0014%9Sk\u001f=:X?2")), z(z("=04Zkv(lT%-|aSk")), z(z("{.9P>!)lT'>|?X8(5#Sk409N?..9Q*6=(Te")), z(z("{q!\u001d")), z(z("<=!X")) };
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= 'K';
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
                    c2 = '[';
                    break;
                }
                case 1: {
                    c2 = '\\';
                    break;
                }
                case 2: {
                    c2 = 'L';
                    break;
                }
                case 3: {
                    c2 = '=';
                    break;
                }
                default: {
                    c2 = 'K';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
