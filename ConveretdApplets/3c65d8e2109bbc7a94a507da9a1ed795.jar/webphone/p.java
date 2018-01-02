// 
// Decompiled by Procyon v0.5.30
// 

package webphone;

public class p
{
    private int for;
    private boolean if;
    private static final byte int = 15;
    private static final byte do = 4;
    private static final short[] a;
    
    public p() {
        this.if = false;
    }
    
    private int a(int n) {
        if (this.for == 16) {
            n /= 2;
        }
        return n;
    }
    
    private static byte a(short n) {
        int n2 = 8;
        int n3;
        if (n >= 0) {
            n3 = -43;
        }
        else {
            n3 = 85;
            n = (short)(-n - 8);
        }
        for (int i = 0; i < 8; ++i) {
            if (n <= p.a[i]) {
                n2 = (byte)i;
                break;
            }
        }
        if (n2 >= 8) {
            return (byte)((0x7F ^ n3) & 0xFF);
        }
        final byte b = (byte)(n2 << 4);
        byte b2;
        if (n2 < 2) {
            b2 = (byte)(b | (n >> 4 & 0xF));
        }
        else {
            b2 = (byte)(b | (n >> n2 + 3 & 0xF));
        }
        return (byte)((b2 ^ n3) & 0xFF);
    }
    
    public static void a(final byte[] array, final int n, final byte[] array2, final int n2, int i, final boolean b) {
        int n3 = n;
        int n4 = n2;
        if (b) {
            while (i > 0) {
                array2[n4++] = a(a(array[n3], array[n3 + 1]));
                ++n3;
                ++n3;
                --i;
            }
        }
        else {
            while (i > 0) {
                array2[n4++] = a(a(array[n3 + 1], array[n3]));
                ++n3;
                ++n3;
                --i;
            }
        }
    }
    
    public static short a(final byte b, final byte b2) {
        return (short)(b << 8 | (b2 & 0xFF));
    }
    
    static {
        a = new short[] { 255, 511, 1023, 2047, 4095, 8191, 16383, 32767 };
    }
}
