// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.io.DataOutputStream;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.io.DataInputStream;
import com.hw.client.util.a;
import java.io.InputStream;

public class bP extends da
{
    private VT_6_1_0_11.e b;
    
    public bP(final String s) {
        this.a(new dA(s, "audioproxy", "1.0.4"));
        this.b(false);
        this.c(false);
    }
    
    final void a(final boolean b, final boolean b2, final char c, final char c2, final int n) {
        this.a(dx.a(b, b2, c, c2, n));
    }
    
    final void a(final String s, final String s2, final String s3) {
        this.a(dx.a(s, s2, s3));
    }
    
    final void a(final int n) {
        this.a(dx.a(n));
    }
    
    protected final void a(final InputStream inputStream) {
        final am a = am.a(inputStream);
        if (this.b != null) {
            this.b.a(a);
        }
    }
    
    protected final void b(final InputStream inputStream) {
        final String e = this.e(inputStream);
        if (com.hw.client.util.a.b()) {
            com.hw.client.util.a.c("AUDIOPROXY_ERR " + e);
        }
    }
    
    protected final Object d() {
        return dx.c();
    }
    
    protected final Object e() {
        return dx.d();
    }
    
    protected final InputStream c(final InputStream inputStream) {
        return new DataInputStream(inputStream);
    }
    
    protected final InputStream d(final InputStream inputStream) {
        return inputStream;
    }
    
    protected final OutputStream a(final OutputStream outputStream) {
        return new DataOutputStream(new BufferedOutputStream(outputStream));
    }
    
    protected final void a(final OutputStream outputStream, final Object o, final boolean b) {
        dx.a(outputStream, o, b);
    }
    
    public final void a(final VT_6_1_0_11.e b) {
        this.b = b;
    }
}
