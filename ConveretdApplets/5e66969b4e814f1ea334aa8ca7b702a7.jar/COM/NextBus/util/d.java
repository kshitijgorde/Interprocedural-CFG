// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.util;

import java.io.InputStream;
import java.io.FilterInputStream;

public final class d extends FilterInputStream
{
    private long a;
    
    public d(final InputStream inputStream) {
        super(inputStream);
        this.a = 0L;
    }
    
    public final synchronized long a() {
        return this.a;
    }
    
    public final synchronized int read() {
        final int read;
        if ((read = super.read()) >= 0) {
            ++this.a;
        }
        return read;
    }
    
    public final synchronized int read(final byte[] array, final int n, final int n2) {
        final int read;
        if ((read = super.read(array, n, n2)) > 0) {
            this.a += read;
        }
        return read;
    }
    
    public final synchronized long skip(final long n) {
        final long skip;
        if ((skip = super.skip(n)) > 0L) {
            this.a += skip;
        }
        return skip;
    }
}
