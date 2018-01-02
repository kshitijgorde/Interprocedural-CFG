// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.security;

public final class IDEA extends ci
{
    public int[] nh;
    public int ng;
    public int nf;
    
    public final synchronized void nb(final byte[] array, final int n, final byte[] array2, final int n2, final int n3) {
        final int[] array3 = new int[2];
        int ng = this.ng;
        int nf = this.nf;
        for (int n4 = n + n3, i = n, n5 = n2; i < n4; i += 8, n5 += 8) {
            this.nb(ng, nf, array3);
            final int n6 = array3[0];
            final int n7 = array3[1];
            ng = (n6 ^ ((array[i + 3] & 0xFF) | (array[i + 2] & 0xFF) << 8 | (array[i + 1] & 0xFF) << 16 | (array[i] & 0xFF) << 24));
            nf = (n7 ^ ((array[i + 7] & 0xFF) | (array[i + 6] & 0xFF) << 8 | (array[i + 5] & 0xFF) << 16 | (array[i + 4] & 0xFF) << 24));
            if (n5 + 8 <= n4) {
                array2[n5 + 3] = (byte)(ng & 0xFF);
                array2[n5 + 2] = (byte)(ng >>> 8 & 0xFF);
                array2[n5 + 1] = (byte)(ng >>> 16 & 0xFF);
                array2[n5] = (byte)(ng >>> 24 & 0xFF);
                array2[n5 + 7] = (byte)(nf & 0xFF);
                array2[n5 + 6] = (byte)(nf >>> 8 & 0xFF);
                array2[n5 + 5] = (byte)(nf >>> 16 & 0xFF);
                array2[n5 + 4] = (byte)(nf >>> 24 & 0xFF);
            }
            else {
                switch (n4 - n5) {
                    case 7: {
                        array2[n5 + 6] = (byte)(nf >>> 8 & 0xFF);
                    }
                    case 6: {
                        array2[n5 + 5] = (byte)(nf >>> 16 & 0xFF);
                    }
                    case 5: {
                        array2[n5 + 4] = (byte)(nf >>> 24 & 0xFF);
                    }
                    case 4: {
                        array2[n5 + 3] = (byte)(ng & 0xFF);
                    }
                    case 3: {
                        array2[n5 + 2] = (byte)(ng >>> 8 & 0xFF);
                    }
                    case 2: {
                        array2[n5 + 1] = (byte)(ng >>> 16 & 0xFF);
                    }
                    case 1: {
                        array2[n5] = (byte)(ng >>> 24 & 0xFF);
                        break;
                    }
                }
            }
        }
        this.ng = ng;
        this.nf = nf;
    }
    
    public final synchronized void na(final byte[] array, final int n, final byte[] array2, final int n2, final int n3) {
        final int[] array3 = new int[2];
        int ng = this.ng;
        int nf = this.nf;
        for (int n4 = n + n3, i = n, n5 = n2; i < n4; i += 8, n5 += 8) {
            this.na(ng, nf, array3);
            ng = ((array[i + 3] & 0xFF) | (array[i + 2] & 0xFF) << 8 | (array[i + 1] & 0xFF) << 16 | (array[i] & 0xFF) << 24);
            nf = ((array[i + 7] & 0xFF) | (array[i + 6] & 0xFF) << 8 | (array[i + 5] & 0xFF) << 16 | (array[i + 4] & 0xFF) << 24);
            final int n6 = array3[0] ^ ng;
            final int n7 = array3[1] ^ nf;
            if (n5 + 8 <= n4) {
                array2[n5 + 3] = (byte)(n6 & 0xFF);
                array2[n5 + 2] = (byte)(n6 >>> 8 & 0xFF);
                array2[n5 + 1] = (byte)(n6 >>> 16 & 0xFF);
                array2[n5] = (byte)(n6 >>> 24 & 0xFF);
                array2[n5 + 7] = (byte)(n7 & 0xFF);
                array2[n5 + 6] = (byte)(n7 >>> 8 & 0xFF);
                array2[n5 + 5] = (byte)(n7 >>> 16 & 0xFF);
                array2[n5 + 4] = (byte)(n7 >>> 24 & 0xFF);
            }
            else {
                switch (n4 - n5) {
                    case 7: {
                        array2[n5 + 6] = (byte)(n7 >>> 8 & 0xFF);
                    }
                    case 6: {
                        array2[n5 + 5] = (byte)(n7 >>> 16 & 0xFF);
                    }
                    case 5: {
                        array2[n5 + 4] = (byte)(n7 >>> 24 & 0xFF);
                    }
                    case 4: {
                        array2[n5 + 3] = (byte)(n6 & 0xFF);
                    }
                    case 3: {
                        array2[n5 + 2] = (byte)(n6 >>> 8 & 0xFF);
                    }
                    case 2: {
                        array2[n5 + 1] = (byte)(n6 >>> 16 & 0xFF);
                    }
                    case 1: {
                        array2[n5] = (byte)(n6 >>> 24 & 0xFF);
                        break;
                    }
                }
            }
        }
        this.ng = ng;
        this.nf = nf;
    }
    
    public final void m9(final byte[] array) {
        int n = 0;
        for (int i = 0; i < 8; ++i) {
            this.nh[i] = ((array[2 * i] & 0xFF) << 8 | (array[2 * i + 1] & 0xFF));
        }
        int j = 8;
        int n2 = 0;
        while (j < 52) {
            ++n2;
            this.nh[n + n2 + 7] = ((this.nh[n + (n2 & 0x7)] << 9 | this.nh[n + (n2 + 1 & 0x7)] >>> 7) & 0xFFFF);
            n += (n2 & 0x8);
            n2 &= 0x7;
            ++j;
        }
    }
    
    public final void nb(final int n, final int n2, final int[] array) {
        int n3 = 0;
        int n4 = 0;
        int n5 = n >>> 16;
        int n6 = n & 0xFFFF;
        int n7 = n2 >>> 16;
        int n8 = n2 & 0xFFFF;
        for (int i = 0; i < 8; ++i) {
            final int nm = nm(n5 & 0xFFFF, this.nh[n4++]);
            final int n9 = n6 + this.nh[n4++];
            final int n10 = n7 + this.nh[n4++];
            final int nm2 = nm(n8 & 0xFFFF, this.nh[n4++]);
            final int n11 = nm ^ n10;
            final int n12 = n9 ^ nm2;
            final int nm3 = nm(n11 & 0xFFFF, this.nh[n4++]);
            final int nm4 = nm(nm3 + n12 & 0xFFFF, this.nh[n4++]);
            final int n13 = nm3 + nm4;
            n5 = (nm ^ nm4);
            n8 = (nm2 ^ n13);
            n3 = (n13 ^ n9);
            n6 = (nm4 ^ n10);
            n7 = n3;
        }
        final int n14 = n6;
        final int nm5 = nm(n5 & 0xFFFF, this.nh[n4++]);
        final int n15 = n3 + this.nh[n4++];
        final int n16 = n14 + this.nh[n4++] & 0xFFFF;
        final int nm6 = nm(n8 & 0xFFFF, this.nh[n4]);
        array[0] = (nm5 << 16 | (n15 & 0xFFFF));
        array[1] = (n16 << 16 | (nm6 & 0xFFFF));
    }
    
    public final void na(final int n, final int n2, final int[] array) {
        this.nb(n, n2, array);
    }
    
    public static final int nm(final int n, final int n2) {
        final int n3 = n * n2;
        if (n3 != 0) {
            final int n4 = n3 & 0xFFFF;
            final int n5 = n3 >>> 16 & 0xFFFF;
            return n4 - n5 + ((n4 < n5) ? 1 : 0);
        }
        if (n == 0) {
            return 1 - n2;
        }
        return 1 - n;
    }
    
    public IDEA() {
        this.nh = new int[52];
    }
}
