// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.b;

import flaxchat.a.q;
import java.util.Hashtable;
import flaxchat.d.b;
import flaxchat.f.g;
import flaxchat.n;
import java.util.Vector;

public class h
{
    Vector a;
    boolean b;
    n c;
    String d;
    g e;
    public static boolean f;
    private static String[] z;
    
    public h(final n c, final g e, final String d, final String s) {
        final boolean f = h.f;
        this.a = new Vector();
        this.b = false;
        this.c = c;
        this.e = e;
        this.d = d;
        final Hashtable a = flaxchat.d.b.a(s);
        if (a == null || a.size() == 0) {
            return;
        }
        int n = 1;
    Label_0132:
        while (true) {
            Label_0125: {
                if (!f) {
                    break Label_0125;
                }
                final String s2 = a.get("n" + n);
                if (s2 == null) {
                    break Label_0132;
                }
                final String trim = s2.trim();
                if (trim.length() == 0) {
                    break Label_0132;
                }
                this.a(trim);
                ++n;
            }
            if (n < 100) {
                continue;
            }
            break;
        }
        this.a(h.z[0]);
    }
    
    private boolean a(final Hashtable hashtable) {
        if (!h.z[4].equals(String.valueOf(hashtable.get(h.z[5])).trim())) {
            return false;
        }
        if (this.b) {
            return true;
        }
        this.b = true;
        return false;
    }
    
    private void a(final String s) {
        if (s.startsWith(h.z[3])) {
            this.a.addElement(new a());
            return;
        }
        final Hashtable a = q.a(this.e, this.d, s);
        if (this.a(a)) {
            return;
        }
        this.a.addElement(new flaxchat.b.g(a.get(h.z[1]), a, a.get(h.z[2])));
    }
    
    static {
        h.z = new String[] { z(z("\u001aZuAW<_\u007f\u0010\u00113Yq_Jr\u001fw\u0017\u001c3Yq_Jp")), z(z("\u001czS")), z(z("\u001bvY")), z(z("\u007f\u0016")), z(z("3Yq_J")), z(z("\u0011vZ")) };
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= '>';
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
                    c2 = 'R';
                    break;
                }
                case 1: {
                    c2 = ';';
                    break;
                }
                case 2: {
                    c2 = '\u001e';
                    break;
                }
                case 3: {
                    c2 = '*';
                    break;
                }
                default: {
                    c2 = '>';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
