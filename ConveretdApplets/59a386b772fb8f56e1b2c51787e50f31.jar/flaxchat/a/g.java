// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.a;

import flaxchat.c.a;
import flaxchat.e.e;
import java.awt.Component;
import flaxchat.e.j;
import java.util.StringTokenizer;
import flaxchat.m;

public class g extends c
{
    private static String[] z;
    
    public g(final m m) {
        super(m);
    }
    
    public void a(final Object o, final String s, final flaxchat.h.g g, final String s2) {
        final boolean b = c.b;
        final StringTokenizer stringTokenizer = new StringTokenizer(s2);
        stringTokenizer.nextToken();
        final String nextToken = stringTokenizer.nextToken();
        final String nextToken2 = stringTokenizer.nextToken();
        a a = super.a.c(g.d());
        if (a == null) {
            a = super.a.c(s);
            if (a == null) {
                j.b(super.a, g.z[0] + g.d() + g.z[3]);
                return;
            }
        }
        final String b2 = a.b(nextToken2, nextToken);
        if (b2 != null) {
            j.b(super.a, b2);
            return;
        }
        final String a2 = j.a(super.a, g.z[4]);
        if (a2 == null) {
            return;
        }
        super.a.h().a(nextToken, g.z[2] + nextToken2 + g.z[1] + a2);
        if (e.c) {
            c.b = !b;
        }
    }
    
    public String a() {
        return g.z[5];
    }
    
    static {
        g.z = new String[] { z(z("\u001bqq9Lt\b")), z(z("n\u0005]K")), z(z("(DH\f%c\\\u0010\u0002k8\b\u001d\u0005%")), z(z("nZE\u0006p4]\u0010\u0002i+\bC\u000ev=A_\u0005%!DE\u0018q;ZE\u0007d#IT\u0002+")), z(z("\u0001QE\u0005%\nIF\u000eq'")), z(z(")I]\u000e")) };
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= '\u0005';
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
                    c2 = 'N';
                    break;
                }
                case 1: {
                    c2 = '(';
                    break;
                }
                case 2: {
                    c2 = '0';
                    break;
                }
                case 3: {
                    c2 = 'k';
                    break;
                }
                default: {
                    c2 = '\u0005';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
