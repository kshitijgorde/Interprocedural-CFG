import java.io.BufferedInputStream;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class W extends InputStream
{
    private int[] read;
    private int I;
    private int Z;
    private InputStream C;
    
    public W(final InputStream inputStream, final int[] array) {
        this.read = new int[65536];
        this.C = new BufferedInputStream(inputStream);
        for (int i = 0; i < 8; ++i) {
            this.I ^= 1 << array[i] - 1;
        }
        this.I();
    }
    
    public final int read() {
        final int read = this.C.read();
        int n;
        if (-1 == read) {
            n = read;
        }
        else {
            n = this.read[read + this.Z * 256];
            this.Z = read;
        }
        return n;
    }
    
    private int read(final int n) {
        int n2 = 0;
        for (int i = 0; i < 8; ++i) {
            n2 |= (n & 1 << i);
        }
        return n2;
    }
    
    private int I(final int n, final int n2, final int n3) {
        final int n4 = (n2 << 8 | n3) >> n;
        int n5 = n4 & 0x1;
        final int read = this.read(n4 >> 1);
        for (int i = 0; i < 8; ++i) {
            n5 ^= ((0 < (this.I & read & 1 << i)) ? 1 : 0);
        }
        return (0 < n5) ? 1 : 0;
    }
    
    private int[] I() {
        for (int i = 0; i < 256; ++i) {
            for (int j = 0; j < 256; ++j) {
                int n = i;
                for (int k = 0; k < 8; ++k) {
                    final int n2 = n << 1 & 0xFF;
                    if (1 == this.I(k, i, j)) {
                        n = (n2 | 0x1);
                    }
                    else {
                        n = (n2 & 0xFFFFFFFE);
                    }
                }
                this.read[i << 8 | j] = n;
            }
        }
        return this.read;
    }
}
