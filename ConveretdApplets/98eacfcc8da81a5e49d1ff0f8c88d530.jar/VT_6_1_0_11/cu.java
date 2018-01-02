// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.io.OutputStream;
import java.io.FilterOutputStream;

final class cu extends FilterOutputStream
{
    cu(final aj aj, final OutputStream outputStream) {
        super(outputStream);
    }
    
    public final void write(final byte[] array, int n, int i) {
        while (i > 20000) {
            this.out.write(array, n, 20000);
            n += 20000;
            i -= 20000;
        }
        this.out.write(array, n, i);
    }
}
