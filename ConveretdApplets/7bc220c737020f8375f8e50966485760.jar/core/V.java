// 
// Decompiled by Procyon v0.5.30
// 

package core;

import java.net.URL;
import java.io.IOException;
import I.I;

public final class V
{
    private final D[] c;
    public final int a;
    public static final J b;
    
    static {
        b = new DI();
    }
    
    public V(final RevolverEngine revolverEngine) {
        this.c = new D[128];
        final URL resource = revolverEngine.getClass().getResource(I.I(702));
        byte[] a = null;
        try {
            a = E.a(resource.openStream());
        }
        catch (IOException ex) {}
        int i = 0;
        this.a = a[0];
        ++i;
        do {
            final byte b = a[i];
            ++i;
            final byte b2 = a[i];
            ++i;
            final int n;
            final byte[] array = new byte[n = this.a * b2];
            for (int j = 0; j < n; ++j) {
                array[j] = a[i];
                ++i;
            }
            this.c[b] = new D(b2, this.a, array);
        } while (i < a.length);
    }
    
    public final J a(final String s) {
        if (s != null && s.length() > 0) {
            return new R(this, s);
        }
        return V.b;
    }
    
    public final boolean a(final char c) {
        final byte b;
        return (b = (byte)c) > 0 && this.c[b] != null;
    }
    
    static final D[] a(final V v) {
        return v.c;
    }
}
