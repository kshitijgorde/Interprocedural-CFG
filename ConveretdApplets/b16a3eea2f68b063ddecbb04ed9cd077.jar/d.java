import java.io.IOException;
import java.io.InputStream;
import java.io.FilterInputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public final class d extends FilterInputStream
{
    private static final int[][] a;
    private int b;
    private long c;
    private boolean d;
    
    public d(final InputStream inputStream) {
        super(inputStream);
        this.b = 0;
        this.c = 0L;
        this.d = false;
    }
    
    public final void close() throws IOException {
        this.d = true;
        this.in.close();
    }
    
    public final boolean markSupported() {
        return false;
    }
    
    public final int read(final byte[] array, final int n, final int n2) throws IOException {
        int n3 = n;
        int read;
        do {
            read = this.read();
            array[n3++] = (byte)(read & 0xFFL);
        } while (read >= 0 && n3 < n2 && read != 10);
        if (read < 0) {
            --n3;
        }
        if (n3 != n) {
            return n3 - n;
        }
        return -1;
    }
    
    public final int read() throws IOException {
        int n = 126;
        int n2;
        do {
            if (this.b == 0) {
                this.a();
            }
            n2 = (int)(this.c & 0x1L);
            this.c >>= 1;
            --this.b;
        } while ((n = d.a[n][n2]) >= 0 && !this.d);
        if (n < 0) {
            int n3;
            if ((n3 = -(n + 1)) == 23) {
                n3 = 10;
                final int n4 = this.b % 8;
                this.b -= n4;
                this.c >>= n4;
                return n3;
            }
            if (n3 != 4) {
                return n3;
            }
        }
        this.d = true;
        return -1;
    }
    
    private void a() throws IOException {
        int n = (this.in.available() > 0) ? this.in.available() : 1;
        while (this.b < 56 && n > 0) {
            final long n2 = this.in.read();
            --n;
            if (n2 < 0L) {
                if (this.b == 0) {
                    this.d = true;
                }
                return;
            }
            final long n3 = n2 << this.b;
            this.b += 8;
            this.c += n3;
        }
    }
    
    public final synchronized void reset() throws IOException {
        throw new IOException("mark/reset not supported");
    }
    
    public final long skip(final long n) throws IOException {
        return 0L;
    }
    
    static {
        a = new int[][] { { -2, -3 }, { -4, -6 }, { -7, -8 }, { -9, -10 }, { -11, -12 }, { -13, -14 }, { -15, -16 }, { -17, -18 }, { -19, -20 }, { -21, -22 }, { -23, -25 }, { -26, -27 }, { -28, -29 }, { -30, -31 }, { -32, -34 }, { -35, -36 }, { -37, -38 }, { -39, -40 }, { -41, -42 }, { -43, -45 }, { -60, -61 }, { -62, -63 }, { -64, -65 }, { -92, -93 }, { -94, -95 }, { -97, -107 }, { -114, -124 }, { -125, -126 }, { -127, -128 }, { 0, 1 }, { 2, 3 }, { 4, 5 }, { 6, 7 }, { 8, 9 }, { 10, 11 }, { 12, 13 }, { 14, 15 }, { 16, 17 }, { 18, 19 }, { 20, 21 }, { 22, 23 }, { 24, 25 }, { 26, 27 }, { 28, 29 }, { 30, 31 }, { 32, 33 }, { 34, 35 }, { 36, 37 }, { 38, 39 }, { 40, 41 }, { 42, 43 }, { 44, 45 }, { 46, 47 }, { 48, 49 }, { 50, 51 }, { 52, 53 }, { 54, 55 }, { 56, -5 }, { -123, 57 }, { 58, -119 }, { -103, -108 }, { -120, 59 }, { -99, -96 }, { -121, -122 }, { 60, 61 }, { -104, -105 }, { 62, 63 }, { 64, -101 }, { 65, -118 }, { 66, 67 }, { -110, -109 }, { -113, 68 }, { -117, -116 }, { -98, -106 }, { 69, -102 }, { -100, -115 }, { 70, -111 }, { 71, 72 }, { 73, -112 }, { 74, 75 }, { 76, 77 }, { 78, 79 }, { -76, -91 }, { -73, 80 }, { -90, 81 }, { 82, 83 }, { -75, 84 }, { -87, -88 }, { -82, -72 }, { -71, 85 }, { -81, -89 }, { 86, -69 }, { 87, 88 }, { -67, -70 }, { 89, 90 }, { -44, 91 }, { -83, -77 }, { 92, -79 }, { 93, -85 }, { -66, 94 }, { -80, -78 }, { 95, 96 }, { 97, 98 }, { 99, -86 }, { 100, -59 }, { -68, -58 }, { 101, -48 }, { 102, -24 }, { -47, -46 }, { -55, 103 }, { -54, -52 }, { -57, 104 }, { 105, -53 }, { -56, -33 }, { 106, 107 }, { 108, 109 }, { 110, 111 }, { 112, -49 }, { 113, -51 }, { 114, -74 }, { -50, 115 }, { 116, -84 }, { 117, 118 }, { 119, 120 }, { -1, 121 }, { 122, 123 }, { 124, 125 } };
    }
}
