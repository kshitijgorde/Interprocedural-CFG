// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.g;

import flaxchat.c.a;
import flaxchat.c.e;
import java.util.StringTokenizer;
import java.util.Hashtable;
import flaxchat.f.g;
import flaxchat.n;

public class i extends c
{
    private static String[] z;
    
    public i(final n n) {
        super(n);
    }
    
    public void a(final Object o, final String s, final g g, final String s2, final Hashtable hashtable) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s2);
        stringTokenizer.nextToken();
        final String nextToken = stringTokenizer.nextToken();
        final a e = super.a.e();
        if (e instanceof flaxchat.c.c) {
            final flaxchat.c.c c = (flaxchat.c.c)e;
            if (i.z[3].equalsIgnoreCase(nextToken)) {
                c.i(null);
                return;
            }
            if (i.z[2].equalsIgnoreCase(nextToken)) {
                c.g();
                return;
            }
            if (i.z[1].equalsIgnoreCase(nextToken)) {
                c.h();
                return;
            }
            if (i.z[4].equalsIgnoreCase(nextToken)) {
                c.i();
            }
        }
        else if (e instanceof e) {
            final e e2 = (e)e;
            if (i.z[3].equalsIgnoreCase(nextToken)) {
                e2.i((String)null);
                return;
            }
            if (i.z[2].equalsIgnoreCase(nextToken)) {
                e2.a();
                return;
            }
            if (i.z[1].equalsIgnoreCase(nextToken)) {
                e2.g();
                return;
            }
            if (i.z[4].equalsIgnoreCase(nextToken)) {
                e2.i();
            }
        }
    }
    
    public String a() {
        return i.z[0];
    }
    
    static {
        i.z = new String[] { z(z("\u0000&\u0017\u0018")), z(z("\u0010!\u0014\u0000C")), z(z("\u0000#\u0011\u0003H")), z(z("\u0010!\b\u0016")), z(z("\u0010\"\u001d\u000eC")) };
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= '1';
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
                    c2 = 's';
                    break;
                }
                case 1: {
                    c2 = 'N';
                    break;
                }
                case 2: {
                    c2 = 'x';
                    break;
                }
                case 3: {
                    c2 = 'o';
                    break;
                }
                default: {
                    c2 = '1';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
