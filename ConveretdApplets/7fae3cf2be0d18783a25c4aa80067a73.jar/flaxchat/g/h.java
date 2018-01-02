// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.g;

import java.awt.Dimension;
import flaxchat.c.a;
import java.awt.Point;
import java.awt.Component;
import java.util.Hashtable;
import flaxchat.f.g;
import flaxchat.n;

public class h extends c
{
    private static String z;
    
    public h(final n n) {
        super(n);
    }
    
    public void a(final Object o, final String s, final g g, String trim, final Hashtable hashtable) {
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
        final Dimension size = component.getSize();
        Point point = null;
        if (hashtable != null) {
            point = hashtable.get("p");
        }
        if (point == null) {
            point = new Point(0, size.height);
        }
        point.y = size.height;
        flaxchat.c.a.a(super.a, component, trim, s, g, point);
    }
    
    public String a() {
        return h.z;
    }
    
    static {
        h.z = z(z("2,UC9"));
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= 'I';
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
                    c2 = 'B';
                    break;
                }
                case 1: {
                    c2 = 'C';
                    break;
                }
                case 2: {
                    c2 = '%';
                    break;
                }
                case 3: {
                    c2 = '6';
                    break;
                }
                default: {
                    c2 = 'I';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
