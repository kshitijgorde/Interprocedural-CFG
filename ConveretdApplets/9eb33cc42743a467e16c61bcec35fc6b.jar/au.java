import java.io.ByteArrayOutputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public final class au extends ByteArrayOutputStream
{
    public au() {
    }
    
    public au(final int n) {
        super(n);
    }
    
    public final byte[] ht() {
        return super.buf;
    }
    
    public final void hs(final int count) {
        super.count = count;
    }
}
