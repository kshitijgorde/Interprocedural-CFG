import java.io.OutputStream;
import java.io.FilterOutputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class PostMethodFilter extends FilterOutputStream
{
    public PostMethodFilter(final OutputStream outputStream) {
        super(outputStream);
    }
    
    public final void write(final int n) {
        if (n < 32 || n == 37 || n > 127) {
            super.write(37);
            final int n2 = n & 0xF;
            final int n3 = (n & 0xF0) >> 4;
            final int n4 = (n2 < 10) ? (n2 + 48) : (n2 + 65 - 10);
            super.write((n3 < 10) ? (n3 + 48) : (n3 + 65 - 10));
            super.write(n4);
        }
        else {
            super.write(n);
        }
    }
    
    public final void write(final byte[] array, final int n, final int n2) {
        for (int i = 0; i < n2; ++i) {
            this.write(array[n + i]);
        }
    }
}
