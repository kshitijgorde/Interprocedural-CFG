// 
// Decompiled by Procyon v0.5.30
// 

package seguriSign_Client;

public class RC2
{
    byte[] expanded_key;
    int[] K;
    public static byte[] PITABLE;
    
    public RC2() {
        this.K = new int[64];
    }
    
    public void keyExpansion(final byte[] array, final int n) throws Exception {
        final int length = array.length;
        if (length < 1 || length > 128) {
            throw new Exception("key length in bytes must be in range [1,128]");
        }
        this.expanded_key = new byte[16];
        final int n2 = (int)Math.ceil(n / 8);
        final int n3 = 255 >> (0x7 & -n);
        final byte[] array2 = new byte[128];
        for (int i = 0; i < length; ++i) {
            array2[i] = array[i];
        }
        for (int j = length; j < 128; ++j) {
            array2[j] = RC2.PITABLE[((array2[j - 1] & 0xFF) + (array2[j - length] & 0xFF)) % 256];
        }
        final int n4 = n + 7 >> 3;
        array2[128 - n4] = RC2.PITABLE[array2[128 - n4] & 0xFF & n3];
        int k;
        int n5;
        for (n5 = (k = 127 - n4); k >= 0; --k) {
            array2[k] = RC2.PITABLE[(array2[k + 1] & 0xFF) ^ (array2[k + n4] & 0xFF)];
        }
        ++n5;
        for (int n6 = 0, n7 = n5; n7 < 128 && n6 <= 15; ++n7, ++n6) {
            this.expanded_key[n6] = array2[n7];
        }
        for (int l = 0; l < this.K.length; ++l) {
            this.K[l] = (array2[2 * l] & 0xFF) + 256 * (array2[2 * l + 1] & 0xFF);
        }
    }
    
    public int ror(int n, final int n2) {
        n &= 0xFFFF;
        return n << n2 | n >> 16 - n2;
    }
    
    public byte[] decryptBlock(final byte[] array) throws Exception {
        if (array.length != 8) {
            throw new Exception("decryptBlock operation not defined for " + array.length + " bytes");
        }
        if (this.expanded_key == null) {
            throw new Exception("before trying to decrypt a block you must call keyExpansion function");
        }
        final byte[] array2 = new byte[8];
        int n = 63;
        final byte[] array3 = { 1, 2, 3, 5 };
        int n2 = ((array[7] & 0xFF) << 8) + (array[6] & 0xFF);
        int n3 = ((array[5] & 0xFF) << 8) + (array[4] & 0xFF);
        int n4 = ((array[3] & 0xFF) << 8) + (array[2] & 0xFF);
        int n5 = ((array[1] & 0xFF) << 8) + (array[0] & 0xFF);
        for (int i = 0; i < 5; ++i) {
            final int ror = this.ror(n2, 11);
            final int n6 = (n5 & ~n3) + (n4 & n3);
            final int[] k = this.K;
            final int n7 = n;
            final int n8 = n7 - 1;
            n2 = ror - (n6 + k[n7]);
            final int ror2 = this.ror(n3, 13);
            final int n9 = (n2 & ~n4) + (n5 & n4);
            final int[] j = this.K;
            final int n10 = n8;
            final int n11 = n10 - 1;
            n3 = ror2 - (n9 + j[n10]);
            final int ror3 = this.ror(n4, 14);
            final int n12 = (n3 & ~n5) + (n2 & n5);
            final int[] l = this.K;
            final int n13 = n11;
            final int n14 = n13 - 1;
            n4 = ror3 - (n12 + l[n13]);
            final int ror4 = this.ror(n5, 15);
            final int n15 = (n4 & ~n2) + (n3 & n2);
            final int[] m = this.K;
            final int n16 = n14;
            n = n16 - 1;
            n5 = ror4 - (n15 + m[n16]);
        }
        int n17 = n2 - this.K[n3 & 0x3F];
        int n18 = n3 - this.K[n4 & 0x3F];
        int n19 = n4 - this.K[n5 & 0x3F];
        int n20 = n5 - this.K[n17 & 0x3F];
        for (int n21 = 0; n21 < 6; ++n21) {
            final int ror5 = this.ror(n17, 11);
            final int n22 = (n20 & ~n18) + (n19 & n18);
            final int[] k2 = this.K;
            final int n23 = n;
            final int n24 = n23 - 1;
            n17 = ror5 - (n22 + k2[n23]);
            final int ror6 = this.ror(n18, 13);
            final int n25 = (n17 & ~n19) + (n20 & n19);
            final int[] k3 = this.K;
            final int n26 = n24;
            final int n27 = n26 - 1;
            n18 = ror6 - (n25 + k3[n26]);
            final int ror7 = this.ror(n19, 14);
            final int n28 = (n18 & ~n20) + (n17 & n20);
            final int[] k4 = this.K;
            final int n29 = n27;
            final int n30 = n29 - 1;
            n19 = ror7 - (n28 + k4[n29]);
            final int ror8 = this.ror(n20, 15);
            final int n31 = (n19 & ~n17) + (n18 & n17);
            final int[] k5 = this.K;
            final int n32 = n30;
            n = n32 - 1;
            n20 = ror8 - (n31 + k5[n32]);
        }
        int n33 = n17 - this.K[n18 & 0x3F];
        int n34 = n18 - this.K[n19 & 0x3F];
        int n35 = n19 - this.K[n20 & 0x3F];
        int n36 = n20 - this.K[n33 & 0x3F];
        for (int n37 = 0; n37 < 5; ++n37) {
            final int ror9 = this.ror(n33, 11);
            final int n38 = (n36 & ~n34) + (n35 & n34);
            final int[] k6 = this.K;
            final int n39 = n;
            final int n40 = n39 - 1;
            n33 = ror9 - (n38 + k6[n39]);
            final int ror10 = this.ror(n34, 13);
            final int n41 = (n33 & ~n35) + (n36 & n35);
            final int[] k7 = this.K;
            final int n42 = n40;
            final int n43 = n42 - 1;
            n34 = ror10 - (n41 + k7[n42]);
            final int ror11 = this.ror(n35, 14);
            final int n44 = (n34 & ~n36) + (n33 & n36);
            final int[] k8 = this.K;
            final int n45 = n43;
            final int n46 = n45 - 1;
            n35 = ror11 - (n44 + k8[n45]);
            final int ror12 = this.ror(n36, 15);
            final int n47 = (n35 & ~n33) + (n34 & n33);
            final int[] k9 = this.K;
            final int n48 = n46;
            n = n48 - 1;
            n36 = ror12 - (n47 + k9[n48]);
        }
        array2[0] = (byte)n36;
        array2[1] = (byte)(n36 >> 8);
        array2[2] = (byte)n35;
        array2[3] = (byte)(n35 >> 8);
        array2[4] = (byte)n34;
        array2[5] = (byte)(n34 >> 8);
        array2[6] = (byte)n33;
        array2[7] = (byte)(n33 >> 8);
        return array2;
    }
    
    public byte[] decryptBlock(final byte[] array, final byte[] array2, final byte[] array3, final byte[] array4, final byte[] array5, final int n) throws Exception {
        this.keyExpansion(array5, n);
        int n2 = 63;
        if (array4.length != 2) {
            throw new Exception("decryptBlock operation not defined for " + array4.length + " bytes");
        }
        final byte[] array6 = { 1, 2, 3, 5 };
        int n3 = ((array4[1] & 0xFF) << 8) + (array4[0] & 0xFF);
        int n4 = ((array3[1] & 0xFF) << 8) + (array3[0] & 0xFF);
        int n5 = ((array2[1] & 0xFF) << 8) + (array2[0] & 0xFF);
        int n6 = ((array[1] & 0xFF) << 8) + (array[0] & 0xFF);
        for (int i = 0; i < 5; ++i) {
            final int ror = this.ror(n3, 11);
            final int n7 = (n6 & ~n4) + (n5 & n4);
            final int[] k = this.K;
            final int n8 = n2;
            final int n9 = n8 - 1;
            n3 = ror - (n7 + k[n8]);
            final int ror2 = this.ror(n4, 13);
            final int n10 = (n3 & ~n5) + (n6 & n5);
            final int[] j = this.K;
            final int n11 = n9;
            final int n12 = n11 - 1;
            n4 = ror2 - (n10 + j[n11]);
            final int ror3 = this.ror(n5, 14);
            final int n13 = (n4 & ~n6) + (n3 & n6);
            final int[] l = this.K;
            final int n14 = n12;
            final int n15 = n14 - 1;
            n5 = ror3 - (n13 + l[n14]);
            final int ror4 = this.ror(n6, 15);
            final int n16 = (n5 & ~n3) + (n4 & n3);
            final int[] m = this.K;
            final int n17 = n15;
            n2 = n17 - 1;
            n6 = ror4 - (n16 + m[n17]);
        }
        int n18 = n3 - this.K[n4 & 0x3F];
        int n19 = n4 - this.K[n5 & 0x3F];
        int n20 = n5 - this.K[n6 & 0x3F];
        int n21 = n6 - this.K[n18 & 0x3F];
        for (int n22 = 0; n22 < 6; ++n22) {
            final int ror5 = this.ror(n18, 11);
            final int n23 = (n21 & ~n19) + (n20 & n19);
            final int[] k2 = this.K;
            final int n24 = n2;
            final int n25 = n24 - 1;
            n18 = ror5 - (n23 + k2[n24]);
            final int ror6 = this.ror(n19, 13);
            final int n26 = (n18 & ~n20) + (n21 & n20);
            final int[] k3 = this.K;
            final int n27 = n25;
            final int n28 = n27 - 1;
            n19 = ror6 - (n26 + k3[n27]);
            final int ror7 = this.ror(n20, 14);
            final int n29 = (n19 & ~n21) + (n18 & n21);
            final int[] k4 = this.K;
            final int n30 = n28;
            final int n31 = n30 - 1;
            n20 = ror7 - (n29 + k4[n30]);
            final int ror8 = this.ror(n21, 15);
            final int n32 = (n20 & ~n18) + (n19 & n18);
            final int[] k5 = this.K;
            final int n33 = n31;
            n2 = n33 - 1;
            n21 = ror8 - (n32 + k5[n33]);
        }
        int n34 = n18 - this.K[n19 & 0x3F];
        int n35 = n19 - this.K[n20 & 0x3F];
        int n36 = n20 - this.K[n21 & 0x3F];
        int n37 = n21 - this.K[n34 & 0x3F];
        for (int n38 = 0; n38 < 5; ++n38) {
            final int ror9 = this.ror(n34, 11);
            final int n39 = (n37 & ~n35) + (n36 & n35);
            final int[] k6 = this.K;
            final int n40 = n2;
            final int n41 = n40 - 1;
            n34 = ror9 - (n39 + k6[n40]);
            final int ror10 = this.ror(n35, 13);
            final int n42 = (n34 & ~n36) + (n37 & n36);
            final int[] k7 = this.K;
            final int n43 = n41;
            final int n44 = n43 - 1;
            n35 = ror10 - (n42 + k7[n43]);
            final int ror11 = this.ror(n36, 14);
            final int n45 = (n35 & ~n37) + (n34 & n37);
            final int[] k8 = this.K;
            final int n46 = n44;
            final int n47 = n46 - 1;
            n36 = ror11 - (n45 + k8[n46]);
            final int ror12 = this.ror(n37, 15);
            final int n48 = (n36 & ~n34) + (n35 & n34);
            final int[] k9 = this.K;
            final int n49 = n47;
            n2 = n49 - 1;
            n37 = ror12 - (n48 + k9[n49]);
        }
        return new byte[] { (byte)n37, (byte)(n37 >> 8), (byte)n36, (byte)(n36 >> 8), (byte)n35, (byte)(n35 >> 8), (byte)n34, (byte)(n34 >> 8) };
    }
    
    public byte[] rc2Decrypt(final byte[] array, final int n, final byte[] array2) throws Exception {
        this.keyExpansion(array, n);
        return this.decryptBlock(array2);
    }
    
    public byte[] rc2DecryptCBC(final byte[] array, final byte[] array2) throws Exception {
        if (array.length % 8 != 0) {
            throw new Exception("block is not properly padded");
        }
        final byte[] array3 = new byte[array.length];
        final int n = array.length / 8;
        int n2 = 0;
        for (int i = 0; i < n; ++i) {
            final byte[] array4 = new byte[8];
            System.arraycopy(array, n2, array4, 0, 8);
            final byte[] xor = this.XOR(this.decryptBlock(array4), array2);
            System.arraycopy(array4, 0, array2, 0, 8);
            System.arraycopy(xor, 0, array3, n2, 8);
            n2 += 8;
        }
        final byte b = array3[array.length - 1];
        if (b >= 1 && b <= 8) {
            final byte[] array5 = new byte[array.length - b];
            System.arraycopy(array3, 0, array5, 0, array5.length);
            return array5;
        }
        return array3;
    }
    
    public void rc2DecryptCBC(final byte[] array, final byte[] array2, final byte[] array3) {
        final byte[] array4 = new byte[8];
        int n = array3.length / 8;
        int n2 = 0;
        while (n-- != 0) {
            System.arraycopy(array3, n2, array4, 0, 8);
            n2 += 8;
        }
    }
    
    private byte[] XOR(final byte[] array, final byte[] array2) throws Exception {
        final int length = array.length;
        if (length != array2.length) {
            System.out.println("a.length = " + array.length);
            System.out.println("b.length = " + array2.length);
            throw new Exception("Cannot XOR arrays with different length");
        }
        final byte[] array3 = new byte[length];
        for (int i = 0; i < length; ++i) {
            array3[i] = (byte)(array[i] ^ array2[i]);
        }
        return array3;
    }
    
    static {
        RC2.PITABLE = new byte[] { -39, 120, -7, -60, 25, -35, -75, -19, 40, -23, -3, 121, 74, -96, -40, -99, -58, 126, 55, -125, 43, 118, 83, -114, 98, 76, 100, -120, 68, -117, -5, -94, 23, -102, 89, -11, -121, -77, 79, 19, 97, 69, 109, -115, 9, -127, 125, 50, -67, -113, 64, -21, -122, -73, 123, 11, -16, -107, 33, 34, 92, 107, 78, -126, 84, -42, 101, -109, -50, 96, -78, 28, 115, 86, -64, 20, -89, -116, -15, -36, 18, 117, -54, 31, 59, -66, -28, -47, 66, 61, -44, 48, -93, 60, -74, 38, 111, -65, 14, -38, 70, 105, 7, 87, 39, -14, 29, -101, -68, -108, 67, 3, -8, 17, -57, -10, -112, -17, 62, -25, 6, -61, -43, 47, -56, 102, 30, -41, 8, -24, -22, -34, -128, 82, -18, -9, -124, -86, 114, -84, 53, 77, 106, 42, -106, 26, -46, 113, 90, 21, 73, 116, 75, -97, -48, 94, 4, 24, -92, -20, -62, -32, 65, 110, 15, 81, -53, -52, 36, -111, -81, 80, -95, -12, 112, 57, -103, 124, 58, -123, 35, -72, -76, 122, -4, 2, 54, 91, 37, 85, -105, 49, 45, 93, -6, -104, -29, -118, -110, -82, 5, -33, 41, 16, 103, 108, -70, -55, -45, 0, -26, -49, -31, -98, -88, 44, 99, 22, 1, 63, 88, -30, -119, -87, 13, 56, 52, 27, -85, 51, -1, -80, -69, 72, 12, 95, -71, -79, -51, 46, -59, -13, -37, 71, -27, -91, -100, 119, 10, -90, 32, 104, -2, 127, -63, -83 };
    }
}
