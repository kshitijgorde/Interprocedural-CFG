// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.security;

public final class RC4 extends ci
{
    public int m5;
    public int m4;
    public byte[] m3;
    
    public final int nc() {
        final int m5 = this.m5 + 1 & 0xFF;
        final byte b = this.m3[m5];
        final int m6 = b + this.m4 & 0xFF;
        final byte b2 = this.m3[m6];
        this.m5 = m5;
        this.m4 = m6;
        this.m3[m6] = (byte)(b & 0xFF);
        this.m3[m5] = (byte)(b2 & 0xFF);
        return this.m3[b + b2 & 0xFF];
    }
    
    public final synchronized void nb(final byte[] array, final int n, final byte[] array2, final int n2, final int n3) {
        for (int n4 = n + n3, i = n, n5 = n2; i < n4; ++i, ++n5) {
            array2[n5] = (byte)((array[i] ^ this.nc()) & 0xFF);
        }
    }
    
    public final void na(final byte[] array, final int n, final byte[] array2, final int n2, final int n3) {
        this.nb(array, n, array2, n2, n3);
    }
    
    public final void m9(final byte[] array) {
        for (int i = 0; i < 256; ++i) {
            this.m3[i] = (byte)i;
        }
        int n = 0;
        int n2 = 0;
        for (int j = 0; j < 256; ++j) {
            final byte b = this.m3[j];
            n2 = (n2 + array[n] + b & 0xFF);
            final byte b2 = this.m3[n2];
            this.m3[n2] = (byte)(b & 0xFF);
            this.m3[j] = (byte)(b2 & 0xFF);
            if (++n >= array.length) {
                n = 0;
            }
        }
    }
    
    public RC4() {
        this.m3 = new byte[256];
    }
}
