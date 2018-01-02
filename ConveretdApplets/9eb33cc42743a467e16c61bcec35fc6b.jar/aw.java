import java.io.ByteArrayInputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public final class aw extends ByteArrayInputStream
{
    public aw(final byte[] array) {
        super(array);
    }
    
    public final int h3() {
        return super.pos;
    }
    
    public final void h2(final int pos) {
        super.pos = pos;
    }
    
    public final void h1(final byte[] buf) {
        super.buf = buf;
    }
}
