// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.io.InputStream;

public final class cy extends x
{
    public cy(final int n, final int n2, final int n3, final int n4) {
        super(1, n4, n3, n2, (n << 3) / (n4 * n2), n);
    }
    
    protected final void a(final InputStream inputStream) {
        super.a(inputStream);
        if (1 != this.b().b()) {
            throw new y("The Audio .wav file does not use PCM encoding, but encoding #" + this.b().b());
        }
    }
}
