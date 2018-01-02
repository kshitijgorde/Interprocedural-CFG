// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.b;

import flaxchat.i.d;
import java.awt.Rectangle;
import java.util.Hashtable;
import java.awt.Image;

public class g
{
    public boolean a;
    public boolean b;
    String c;
    String d;
    Image e;
    Hashtable f;
    Rectangle g;
    private static String[] z;
    
    public g(final String c, final Hashtable f, final String d) {
        this.c = c;
        this.f = f;
        this.d = d;
        this.e = flaxchat.i.d.f(d);
    }
    
    public void a(final Rectangle g) {
        this.g = g;
    }
    
    public String toString() {
        return "[" + this.c + flaxchat.b.g.z[1] + this.b + flaxchat.b.g.z[2] + this.a + flaxchat.b.g.z[0] + this.g + "]";
    }
    
    static {
        g.z = new String[] { z(z(">:")), z(z(">:W\u0003n(:")), z(z(">:W\u001cn(:")) };
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= 'N';
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
                    c2 = '\u0012';
                    break;
                }
                case 1: {
                    c2 = '\u001a';
                    break;
                }
                case 2: {
                    c2 = '\u001a';
                    break;
                }
                case 3: {
                    c2 = 'L';
                    break;
                }
                default: {
                    c2 = 'N';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
