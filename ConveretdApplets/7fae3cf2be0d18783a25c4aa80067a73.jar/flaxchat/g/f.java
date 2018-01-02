// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.g;

import flaxchat.a.s;
import java.util.Hashtable;
import flaxchat.f.g;
import flaxchat.n;

public class f extends c implements Runnable
{
    private static String[] z;
    
    public f(final n n) {
        super(n);
    }
    
    public void a(final Object o, final String s, final g g, final String s2, final Hashtable hashtable) {
        super.a.d(f.z[1]);
        new s(this).start();
    }
    
    public void run() {
        if (super.a.h().i()) {
            return;
        }
        super.a.l().r();
    }
    
    public String a() {
        return f.z[0];
    }
    
    static {
        f.z = new String[] { z(z("WH\tKXWS")), z(z("gS\u0006QHG")) };
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= '=';
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
                    c2 = '4';
                    break;
                }
                case 1: {
                    c2 = '\'';
                    break;
                }
                case 2: {
                    c2 = 'g';
                    break;
                }
                case 3: {
                    c2 = '%';
                    break;
                }
                default: {
                    c2 = '=';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
