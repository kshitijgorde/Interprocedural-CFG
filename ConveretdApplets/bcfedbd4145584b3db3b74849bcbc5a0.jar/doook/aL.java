// 
// Decompiled by Procyon v0.5.30
// 

package doook;

public final class aL
{
    private long i;
    private long j;
    private int x;
    public long k;
    
    public aL() {
        this.i = 180799207L;
        this.j = 10787047L;
        this.x = (int)(127.0 * Math.random());
    }
    
    public final long d() {
        return this.a(this.j, this.x, this.i);
    }
    
    public final long a(final long n) {
        return this.k = this.a(n, this.x, this.i);
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
