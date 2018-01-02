// 
// Decompiled by Procyon v0.5.30
// 

package neat.system.graphics;

import java.io.InputStream;

class a extends InputStream
{
    public void reset() {
    }
    
    public int read() {
        return 127;
    }
    
    public int read(final byte[] array, int n, final int n2) {
        int i;
        for (i = n, n += n2; i < n; ++i) {
            array[i] = 127;
        }
        return n2;
    }
}
