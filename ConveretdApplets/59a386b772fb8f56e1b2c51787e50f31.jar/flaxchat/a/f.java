// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.a;

import flaxchat.e.r;
import flaxchat.h.g;
import flaxchat.m;

public class f extends c implements Runnable
{
    private static String[] z;
    
    public f(final m m) {
        super(m);
    }
    
    public void a(final Object o, final String s, final g g, final String s2) {
        super.a.d(f.z[1]);
        new r(this).start();
    }
    
    public void run() {
        if (super.a.h().i()) {
            return;
        }
        super.a.l().q();
    }
    
    public String a() {
        return f.z[0];
    }
    
    static {
        f.z = new String[] { z(z("w\b_THw\u0013")), z(z("G\u0013PNXg")) };
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= '-';
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
                    c2 = '\u0014';
                    break;
                }
                case 1: {
                    c2 = 'g';
                    break;
                }
                case 2: {
                    c2 = '1';
                    break;
                }
                case 3: {
                    c2 = ':';
                    break;
                }
                default: {
                    c2 = '-';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
