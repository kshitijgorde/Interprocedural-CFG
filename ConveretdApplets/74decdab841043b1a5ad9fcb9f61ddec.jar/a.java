import java.io.ByteArrayOutputStream;

// 
// Decompiled by Procyon v0.5.30
// 

final class a extends ByteArrayOutputStream
{
    a(final c c, final int n) {
        super(n);
    }
    
    public final synchronized byte[] toByteArray() {
        return this.buf;
    }
}
