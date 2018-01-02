// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.a;

import flaxchat.c.a;
import java.awt.Point;
import java.awt.Component;
import flaxchat.h.g;
import flaxchat.m;

public class h extends c
{
    private static String z;
    
    public h(final m m) {
        super(m);
    }
    
    public void a(final Object o, final String s, final g g, String trim) {
        final int index = trim.indexOf(32);
        if (index == -1) {
            if (h.z.toLowerCase().equals(trim.toLowerCase())) {
                return;
            }
        }
        else {
            trim = trim.substring(index + 1).trim();
        }
        final Component component = (Component)o;
        flaxchat.c.a.a(super.a, component, trim, s, g, new Point(0, component.getSize().height));
    }
    
    public String a() {
        return h.z;
    }
    
    static {
        h.z = z(z("P>tTa"));
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= '\u0011';
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
                    c2 = ' ';
                    break;
                }
                case 1: {
                    c2 = 'Q';
                    break;
                }
                case 2: {
                    c2 = '\u0004';
                    break;
                }
                case 3: {
                    c2 = '!';
                    break;
                }
                default: {
                    c2 = '\u0011';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
