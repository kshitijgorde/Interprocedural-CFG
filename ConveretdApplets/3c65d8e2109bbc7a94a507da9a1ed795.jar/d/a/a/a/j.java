// 
// Decompiled by Procyon v0.5.30
// 

package d.a.a.a;

public class j
{
    private static final short[] a;
    
    public static short a(final int n) {
        return (short)((n < -32768) ? -32768 : ((n > 32767) ? 32767 : n));
    }
    
    public static short a(final long n) {
        return (short)((n < -32768L) ? -32768L : ((n > 32767L) ? 32767L : n));
    }
    
    public static short if(final int n, final int n2) {
        return (short)(n >> n2);
    }
    
    public static short new(final short n, final short n2) {
        return a(n + n2);
    }
    
    public static short if(final short n, final short n2) {
        return a(n - n2);
    }
    
    public static short for(final short n, final short n2) {
        if (n == -32768 && n2 == -32768) {
            return 32767;
        }
        return if(n * n2, 15);
    }
    
    public static short do(final short n, final short n2) {
        if (n == -32768 && n2 == -32768) {
            return 32767;
        }
        return (short)(n * n2 + 16384 >> 15 & 0xFFFF);
    }
    
    public static short a(final short n) {
        return (short)((n < 0) ? ((n == -32768) ? 32767 : (-n)) : n);
    }
    
    public static int int(final short n, final short n2) throws IllegalArgumentException {
        if (n != -32768 || n2 != -32768) {
            throw new IllegalArgumentException("One of the aruments must equal -32768");
        }
        return n * n2 << 1;
    }
    
    public static int a(final int n, final int n2) {
        if (n <= 0) {
            if (n2 >= 0) {
                return n + n2;
            }
            final long n3 = -(n + 1) + -(n2 + 1);
            return (n3 >= 2147483647L) ? Integer.MIN_VALUE : (-(int)n3 - 2);
        }
        else {
            if (n2 <= 0) {
                return n + n2;
            }
            final long n4 = n + n2;
            return (int)((n4 > 2147483647L) ? 2147483647L : n4);
        }
    }
    
    public static short if(int n) throws IllegalArgumentException {
        if (n == 0) {
            throw new IllegalArgumentException("gsm_norm: a cannot = 0.");
        }
        if (n < 0) {
            if (n <= -1073741824) {
                return 0;
            }
            n ^= -1;
        }
        return (short)(((n & 0xFFFF0000) != 0x0) ? (((n & 0xFF000000) != 0x0) ? (-1 + j.a[0xFF & n >> 24]) : (7 + j.a[0xFF & n >> 16])) : (((n & 0xFF00) != 0x0) ? (15 + j.a[0xFF & n >> 8]) : (23 + j.a[0xFF & n])));
    }
    
    public static short if(final short n, final int n2) {
        if (n2 >= 16) {
            return 0;
        }
        if (n2 <= -16) {
            if (n < 0) {
                return -1;
            }
            return 0;
        }
        else {
            if (n2 < 0) {
                return a(n, -n2);
            }
            return (short)(n << n2);
        }
    }
    
    public static short a(final short n, final int n2) {
        if (n2 >= 16) {
            if (n < 0) {
                return -1;
            }
            return 0;
        }
        else {
            if (n2 <= -16) {
                return 0;
            }
            if (n2 < 0) {
                return (short)(n << -n2);
            }
            return (short)(n >> n2);
        }
    }
    
    public static short a(final short n, final short n2) throws IllegalArgumentException {
        int n3 = n;
        short n4 = 0;
        int i = 15;
        if (n < 0 || n2 < n) {
            throw new IllegalArgumentException("gsm_div: num >= 0 && denum >= num");
        }
        if (n == 0) {
            return 0;
        }
        while (i != 0) {
            --i;
            n4 <<= 1;
            n3 <<= 1;
            if (n3 >= n2) {
                n3 -= n2;
                ++n4;
            }
        }
        return n4;
    }
    
    static {
        a = new short[] { 8, 7, 6, 6, 5, 5, 5, 5, 4, 4, 4, 4, 4, 4, 4, 4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    }
}
