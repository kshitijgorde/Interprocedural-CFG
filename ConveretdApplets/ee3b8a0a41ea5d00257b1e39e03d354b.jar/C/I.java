// 
// Decompiled by Procyon v0.5.30
// 

package C;

public class I
{
    private static final int[] h;
    private static final int[] g;
    private static final int[] f;
    private static final int[] e;
    private static final int[] d;
    private static final int[] c;
    private static final int[] b;
    private static final int[] a;
    
    public static final void a(final byte[] array, final byte[] array2, final byte[] array3, final int[] array4, final int n, final int n2, final int n3, final int n4) {
        final int n5 = n - n2;
        final int n6 = n4 << 24;
        int n7 = n2;
        int n8 = n;
        int n9 = 0;
        int n10 = 0;
        int n11 = 0;
        int n12 = 0;
        for (int i = 0; i < n3; i += 2) {
            for (int j = 0; j < n2; j += 2) {
                final byte b = array[n10++];
                final byte b2 = array[n10++];
                final byte b3 = array[n8++];
                final byte b4 = array[n8++];
                final int n13 = I.d[0xFF & b];
                final int n14 = 0xFF & array2[n11++];
                final int n15 = 0xFF & array3[n12++];
                final int n16 = I.h[n15];
                final int n17 = n13 + n16;
                final int n18 = I.g[n14] + I.f[n15];
                final int n19 = n13 + n18;
                final int n20 = I.e[n14];
                final int n21 = n13 + n20;
                final int n22 = n9++;
                final int n23 = n6 | I.c[n17 & 0x3FF] | I.b[n19 & 0x3FF] | I.a[n21 & 0x3FF];
                array4[n22] = n23;
                final int n24 = n23;
                final int n25;
                array4[n9++] = ((b == b2) ? n24 : (n6 | I.c[(n25 = I.d[b2 & 0xFF]) + n16 & 0x3FF] | I.b[n25 + n18 & 0x3FF] | I.a[n25 + n20 & 0x3FF]));
                final int n26;
                array4[n7++] = ((b == b3) ? n24 : (n6 | I.c[(n26 = I.d[b3 & 0xFF]) + n16 & 0x3FF] | I.b[n26 + n18 & 0x3FF] | I.a[n26 + n20 & 0x3FF]));
                final int n27;
                array4[n7++] = ((b == b4) ? n24 : (n6 | I.c[(n27 = I.d[b4 & 0xFF]) + n16 & 0x3FF] | I.b[n27 + n18 & 0x3FF] | I.a[n27 + n20 & 0x3FF]));
            }
            n10 = n8 + n5;
            n8 += n + n5;
            n9 = n7;
            n7 += n2;
            n11 += n5 / 2;
            n12 += n5 / 2;
        }
    }
    
    static {
        h = new int[256];
        g = new int[256];
        f = new int[256];
        e = new int[256];
        d = new int[256];
        c = new int[1024];
        b = new int[1024];
        a = new int[1024];
        for (int i = 0; i < 256; ++i) {
            I.h[i] = (int)(1.596 * (i - 128));
            I.g[i] = (int)(-0.392 * (i - 128));
            I.f[i] = (int)(-0.813 * (i - 128));
            I.e[i] = (int)(2.017 * (i - 128));
            I.d[i] = (int)(1.164 * (i - 16));
        }
        for (int j = 0; j < 1024; ++j) {
            final int n = (j >= 256) ? ((j >= 512) ? 0 : 255) : j;
            I.a[j] = n;
            I.b[j] = n << 8;
            I.c[j] = n << 16;
        }
    }
}
