// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.util.Vector;

public class van
{
    public static String a(final String s, final Vector vector) {
        final int length = s.length();
        final StringBuffer sb = new StringBuffer();
        int n = 0;
        for (int i = s.indexOf(37); i != -1; i = s.indexOf(37, i + 1)) {
            sb.append(s.substring(n, i));
            final String substring = s.substring(i + 1, i + 2);
            try {
                sb.append(vector.elementAt(Integer.parseInt(substring)));
            }
            catch (RuntimeException ex) {
                sb.append('%' + substring);
            }
            n = i + 2;
        }
        if (n < length) {
            sb.append(s.substring(n, length));
        }
        return sb.toString();
    }
    
    public static String a(final String s, final Object o) {
        final Vector<Object> vector = new Vector<Object>(1);
        vector.addElement(o);
        return a(s, vector);
    }
}
