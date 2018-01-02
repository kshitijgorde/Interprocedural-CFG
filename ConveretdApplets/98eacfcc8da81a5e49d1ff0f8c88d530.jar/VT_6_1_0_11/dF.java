// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.io.InputStream;

public final class dF extends x
{
    public dF(final int n, final int n2) {
        super(49, 1, n2, n2 * 65 / 320, 65, 0, 320, ((n < 0) ? 0 : n) * 320, null, ((n < 0) ? 0 : n) * 65);
    }
    
    public dF(final InputStream inputStream) {
        super(inputStream);
    }
    
    protected final void a(final InputStream inputStream) {
        super.a(inputStream);
        if (49 != this.b().b()) {
            throw new y("The Audio .wav file does not use GSM encoding, but encoding #" + this.b().b());
        }
    }
}
