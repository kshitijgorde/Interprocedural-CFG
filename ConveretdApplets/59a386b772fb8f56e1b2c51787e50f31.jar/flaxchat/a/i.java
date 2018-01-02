// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.a;

import flaxchat.c.a;
import flaxchat.c.e;
import java.util.StringTokenizer;
import flaxchat.h.g;
import flaxchat.m;

public class i extends c
{
    private static String[] z;
    
    public i(final m m) {
        super(m);
    }
    
    public void a(final Object o, final String s, final g g, final String s2) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s2);
        stringTokenizer.nextToken();
        final String nextToken = stringTokenizer.nextToken();
        final a e = super.a.e();
        if (e instanceof flaxchat.c.c) {
            final flaxchat.c.c c = (flaxchat.c.c)e;
            if (i.z[2].equalsIgnoreCase(nextToken)) {
                c.j();
                return;
            }
            if (i.z[3].equalsIgnoreCase(nextToken)) {
                c.g();
                return;
            }
            if (i.z[0].equalsIgnoreCase(nextToken)) {
                c.i();
                return;
            }
            if (i.z[1].equalsIgnoreCase(nextToken)) {
                c.k();
            }
        }
        else if (e instanceof e) {
            final e e2 = (e)e;
            if (i.z[2].equalsIgnoreCase(nextToken)) {
                e2.l();
                return;
            }
            if (i.z[3].equalsIgnoreCase(nextToken)) {
                e2.g();
                return;
            }
            if (i.z[0].equalsIgnoreCase(nextToken)) {
                e2.h();
                return;
            }
            if (i.z[1].equalsIgnoreCase(nextToken)) {
                e2.k();
            }
        }
    }
    
    public String a() {
        return i.z[4];
    }
    
    static {
        i.z = new String[] { z(z("{\u0002$6N")), z(z("{\u0001-8N")), z(z("{\u00028 ")), z(z("k\u0000!5E")), z(z("k\u0005'.")) };
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= '<';
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
                    c2 = '\u0018';
                    break;
                }
                case 1: {
                    c2 = 'm';
                    break;
                }
                case 2: {
                    c2 = 'H';
                    break;
                }
                case 3: {
                    c2 = 'Y';
                    break;
                }
                default: {
                    c2 = '<';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
