// 
// Decompiled by Procyon v0.5.30
// 

package core;

import java.net.URL;
import java.util.Hashtable;

public final class II
{
    private static final II c;
    public final int a;
    public final int b;
    private final int[] d;
    private static final Hashtable e;
    
    static {
        c = new II(0, 0, null);
        e = new Hashtable();
    }
    
    private II(final int a, final int b, final int[] d) {
        this.a = a;
        this.b = b;
        this.d = d;
    }
    
    private static synchronized void a(final String s, final II ii) {
        II.e.put(s, ii);
    }
    
    public static final II a(final URL url) {
        final String externalForm = url.toExternalForm();
        II c;
        if ((c = II.e.get(externalForm)) == null) {
            try {
                final byte[] a;
                final boolean b = (a = K.a(url.openStream()))[0] == 4;
                final int a2 = K.a(a[1], a[2]);
                final int a3 = K.a(a[3], a[4]);
                final int n;
                final int[] array = new int[n = a2 * a3];
                int n2 = 5;
                for (int i = 0; i < n; ++i) {
                    array[i] = (((b ? a[n2++] : 0) & 0xFF) << 24 | (a[n2++] & 0xFF) << 16 | (a[n2++] & 0xFF) << 8 | (a[n2++] & 0xFF));
                }
                c = new II(a2, a3, array);
            }
            catch (Exception ex) {
                c = II.c;
            }
            a(externalForm, c);
        }
        return c;
    }
    
    public final int a(final int n) {
        return this.d[n];
    }
    
    static final int[] a(final II ii) {
        return ii.d;
    }
}
