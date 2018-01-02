import java.io.PipedInputStream;

// 
// Decompiled by Procyon v0.5.30
// 

class II extends PipedInputStream
{
    volatile int I;
    private boolean read;
    
    public II(final int n, final int i) {
        this.read = true;
        super.buffer = new byte[n];
        this.I = i;
    }
    
    public final synchronized int read() {
        final int read = super.read();
        if (this.read && 0 < read) {
            ++this.I;
        }
        return read;
    }
    
    public final synchronized int read(final byte[] array, final int n, final int n2) {
        this.read = false;
        final int read = super.read(array, n, n2);
        this.read = true;
        if (0 < read) {
            this.I += read;
        }
        return read;
    }
}
