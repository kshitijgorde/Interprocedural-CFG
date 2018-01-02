// 
// Decompiled by Procyon v0.5.30
// 

package doook;

public final class G
{
    private long h;
    private long i;
    private int x;
    public long j;
    
    public G() {
        this.h = 180799207L;
        this.i = 10787047L;
        this.x = (int)(127.0 * Math.random());
    }
    
    public final long b() {
        return this.a(this.i, this.x, this.h);
    }
    
    public final long a(final long n) {
        return this.j = this.a(n, this.x, this.h);
    }
    
    private long a(final long n, final long n2, final long n3) {
        if (n2 == 0L) {
            return 1L;
        }
        long a = this.a(n * n % n3, n2 / 2L, n3);
        if (n2 % 2L != 0L) {
            a = a * n % n3;
        }
        return a;
    }
    
    public static byte[] a(long n) {
        final byte[] array = new byte[8];
        array[0] = (byte)(n & 0xFFL);
        n >>= 8;
        array[1] = (byte)(n & 0xFFL);
        n >>= 8;
        array[2] = (byte)(n & 0xFFL);
        n >>= 8;
        array[3] = (byte)(n & 0xFFL);
        n >>= 8;
        array[4] = (byte)(n & 0xFFL);
        n >>= 8;
        array[5] = (byte)(n & 0xFFL);
        n >>= 8;
        array[6] = (byte)(n & 0xFFL);
        n >>= 8;
        array[7] = (byte)(n & 0xFFL);
        for (int i = 0; i < 8; ++i) {
            if (array[i] == 0) {
                array[i] = array[7 - i];
            }
            if (array[i] == -128) {
                array[i] = 127;
            }
            else if (array[i] < 0) {
                final byte[] array2 = array;
                final int n2 = i;
                array2[n2] *= -1;
            }
        }
        return array;
    }
}
