// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.util.zip.GZIPInputStream;
import java.io.InputStream;

final class eq extends InputStream
{
    private InputStream a;
    
    eq(final InputStream inputStream) {
        this.a = new GZIPInputStream(inputStream);
    }
    
    public final int read() {
        return this.a.read();
    }
    
    public final void close() {
        this.a.close();
    }
}
