// 
// Decompiled by Procyon v0.5.30
// 

public class DesCipher
{
    private int[] encryptKeys;
    private int[] decryptKeys;
    private int[] tempInts;
    private static byte[] bytebit;
    private static int[] bigbyte;
    private static byte[] pc1;
    private static int[] totrot;
    private static byte[] pc2;
    private static int[] SP1;
    private static int[] SP2;
    private static int[] SP3;
    private static int[] SP4;
    private static int[] SP5;
    private static int[] SP6;
    private static int[] SP7;
    private static int[] SP8;
    
    public DesCipher(final byte[] key) {
        this.encryptKeys = new int[32];
        this.decryptKeys = new int[32];
        this.tempInts = new int[2];
        this.setKey(key);
    }
    
    public void setKey(final byte[] array) {
        this.deskey(array, true, this.encryptKeys);
        this.deskey(array, false, this.decryptKeys);
    }
    
    private void deskey(final byte[] array, final boolean b, final int[] array2) {
        final int[] array3 = new int[56];
        final int[] array4 = new int[56];
        final int[] array5 = new int[32];
        for (int i = 0; i < 56; ++i) {
            final byte b2 = DesCipher.pc1[i];
            array3[i] = (((array[b2 >>> 3] & DesCipher.bytebit[b2 & 0x7]) != 0x0) ? 1 : 0);
        }
        for (int j = 0; j < 16; ++j) {
            int n;
            if (b) {
                n = j << 1;
            }
            else {
                n = 15 - j << 1;
            }
            final int n2 = n + 1;
            array5[n] = (array5[n2] = 0);
            for (int k = 0; k < 28; ++k) {
                final int n3 = k + DesCipher.totrot[j];
                if (n3 < 28) {
                    array4[k] = array3[n3];
                }
                else {
                    array4[k] = array3[n3 - 28];
                }
            }
            for (int l = 28; l < 56; ++l) {
                final int n4 = l + DesCipher.totrot[j];
                if (n4 < 56) {
                    array4[l] = array3[n4];
                }
                else {
                    array4[l] = array3[n4 - 28];
                }
            }
            for (int n5 = 0; n5 < 24; ++n5) {
                if (array4[DesCipher.pc2[n5]] != 0) {
                    final int[] array6 = array5;
                    final int n6 = n;
                    array6[n6] |= DesCipher.bigbyte[n5];
                }
                if (array4[DesCipher.pc2[n5 + 24]] != 0) {
                    final int[] array7 = array5;
                    final int n7 = n2;
                    array7[n7] |= DesCipher.bigbyte[n5];
                }
            }
        }
        this.cookey(array5, array2);
    }
    
    private void cookey(final int[] array, final int[] array2) {
        int i = 0;
        int n = 0;
        int n2 = 0;
        while (i < 16) {
            final int n3 = array[n++];
            final int n4 = array[n++];
            array2[n2] = (n3 & 0xFC0000) << 6;
            final int n5 = n2;
            array2[n5] |= (n3 & 0xFC0) << 10;
            final int n6 = n2;
            array2[n6] |= (n4 & 0xFC0000) >>> 10;
            final int n7 = n2;
            array2[n7] |= (n4 & 0xFC0) >>> 6;
            ++n2;
            array2[n2] = (n3 & 0x3F000) << 12;
            final int n8 = n2;
            array2[n8] |= (n3 & 0x3F) << 16;
            final int n9 = n2;
            array2[n9] |= (n4 & 0x3F000) >>> 4;
            final int n10 = n2;
            array2[n10] |= (n4 & 0x3F);
            ++n2;
            ++i;
        }
    }
    
    public void encrypt(final byte[] array, final int n, final byte[] array2, final int n2) {
        squashBytesToInts(array, n, this.tempInts, 0, 2);
        this.des(this.tempInts, this.tempInts, this.encryptKeys);
        spreadIntsToBytes(this.tempInts, 0, array2, n2, 2);
    }
    
    public void decrypt(final byte[] array, final int n, final byte[] array2, final int n2) {
        squashBytesToInts(array, n, this.tempInts, 0, 2);
        this.des(this.tempInts, this.tempInts, this.decryptKeys);
        spreadIntsToBytes(this.tempInts, 0, array2, n2, 2);
    }
    
    private void des(final int[] array, final int[] array2, final int[] array3) {
        int n = 0;
        final int n2 = array[0];
        final int n3 = array[1];
        final int n4 = (n2 >>> 4 ^ n3) & 0xF0F0F0F;
        final int n5 = n3 ^ n4;
        final int n6 = n2 ^ n4 << 4;
        final int n7 = (n6 >>> 16 ^ n5) & 0xFFFF;
        final int n8 = n5 ^ n7;
        final int n9 = n6 ^ n7 << 16;
        final int n10 = (n8 >>> 2 ^ n9) & 0x33333333;
        final int n11 = n9 ^ n10;
        final int n12 = n8 ^ n10 << 2;
        final int n13 = (n12 >>> 8 ^ n11) & 0xFF00FF;
        final int n14 = n11 ^ n13;
        final int n15 = n12 ^ n13 << 8;
        final int n16 = n15 << 1 | (n15 >>> 31 & 0x1);
        final int n17 = (n14 ^ n16) & 0xAAAAAAAA;
        final int n18 = n14 ^ n17;
        int n19 = n16 ^ n17;
        int n20 = n18 << 1 | (n18 >>> 31 & 0x1);
        for (int i = 0; i < 8; ++i) {
            final int n21 = (n19 << 28 | n19 >>> 4) ^ array3[n++];
            final int n22 = DesCipher.SP7[n21 & 0x3F] | DesCipher.SP5[n21 >>> 8 & 0x3F] | DesCipher.SP3[n21 >>> 16 & 0x3F] | DesCipher.SP1[n21 >>> 24 & 0x3F];
            final int n23 = n19 ^ array3[n++];
            n20 ^= (n22 | DesCipher.SP8[n23 & 0x3F] | DesCipher.SP6[n23 >>> 8 & 0x3F] | DesCipher.SP4[n23 >>> 16 & 0x3F] | DesCipher.SP2[n23 >>> 24 & 0x3F]);
            final int n24 = (n20 << 28 | n20 >>> 4) ^ array3[n++];
            final int n25 = DesCipher.SP7[n24 & 0x3F] | DesCipher.SP5[n24 >>> 8 & 0x3F] | DesCipher.SP3[n24 >>> 16 & 0x3F] | DesCipher.SP1[n24 >>> 24 & 0x3F];
            final int n26 = n20 ^ array3[n++];
            n19 ^= (n25 | DesCipher.SP8[n26 & 0x3F] | DesCipher.SP6[n26 >>> 8 & 0x3F] | DesCipher.SP4[n26 >>> 16 & 0x3F] | DesCipher.SP2[n26 >>> 24 & 0x3F]);
        }
        final int n27 = n19 << 31 | n19 >>> 1;
        final int n28 = (n20 ^ n27) & 0xAAAAAAAA;
        final int n29 = n20 ^ n28;
        final int n30 = n27 ^ n28;
        final int n31 = n29 << 31 | n29 >>> 1;
        final int n32 = (n31 >>> 8 ^ n30) & 0xFF00FF;
        final int n33 = n30 ^ n32;
        final int n34 = n31 ^ n32 << 8;
        final int n35 = (n34 >>> 2 ^ n33) & 0x33333333;
        final int n36 = n33 ^ n35;
        final int n37 = n34 ^ n35 << 2;
        final int n38 = (n36 >>> 16 ^ n37) & 0xFFFF;
        final int n39 = n37 ^ n38;
        final int n40 = n36 ^ n38 << 16;
        final int n41 = (n40 >>> 4 ^ n39) & 0xF0F0F0F;
        final int n42 = n39 ^ n41;
        array2[0] = (n40 ^ n41 << 4);
        array2[1] = n42;
    }
    
    public static void squashBytesToInts(final byte[] array, final int n, final int[] array2, final int n2, final int n3) {
        for (int i = 0; i < n3; ++i) {
            array2[n2 + i] = ((array[n + i * 4] & 0xFF) << 24 | (array[n + i * 4 + 1] & 0xFF) << 16 | (array[n + i * 4 + 2] & 0xFF) << 8 | (array[n + i * 4 + 3] & 0xFF));
        }
    }
    
    public static void spreadIntsToBytes(final int[] array, final int n, final byte[] array2, final int n2, final int n3) {
        for (int i = 0; i < n3; ++i) {
            array2[n2 + i * 4] = (byte)(array[n + i] >>> 24);
            array2[n2 + i * 4 + 1] = (byte)(array[n + i] >>> 16);
            array2[n2 + i * 4 + 2] = (byte)(array[n + i] >>> 8);
            array2[n2 + i * 4 + 3] = (byte)array[n + i];
        }
    }
    
    static {
        DesCipher.bytebit = new byte[] { 1, 2, 4, 8, 16, 32, 64, -128 };
        DesCipher.bigbyte = new int[] { 8388608, 4194304, 2097152, 1048576, 524288, 262144, 131072, 65536, 32768, 16384, 8192, 4096, 2048, 1024, 512, 256, 128, 64, 32, 16, 8, 4, 2, 1 };
        DesCipher.pc1 = new byte[] { 56, 48, 40, 32, 24, 16, 8, 0, 57, 49, 41, 33, 25, 17, 9, 1, 58, 50, 42, 34, 26, 18, 10, 2, 59, 51, 43, 35, 62, 54, 46, 38, 30, 22, 14, 6, 61, 53, 45, 37, 29, 21, 13, 5, 60, 52, 44, 36, 28, 20, 12, 4, 27, 19, 11, 3 };
        DesCipher.totrot = new int[] { 1, 2, 4, 6, 8, 10, 12, 14, 15, 17, 19, 21, 23, 25, 27, 28 };
        DesCipher.pc2 = new byte[] { 13, 16, 10, 23, 0, 4, 2, 27, 14, 5, 20, 9, 22, 18, 11, 3, 25, 7, 15, 6, 26, 19, 12, 1, 40, 51, 30, 36, 46, 54, 29, 39, 50, 44, 32, 47, 43, 48, 38, 55, 33, 52, 45, 41, 49, 35, 28, 31 };
        DesCipher.SP1 = new int[] { 16843776, 0, 65536, 16843780, 16842756, 66564, 4, 65536, 1024, 16843776, 16843780, 1024, 16778244, 16842756, 16777216, 4, 1028, 16778240, 16778240, 66560, 66560, 16842752, 16842752, 16778244, 65540, 16777220, 16777220, 65540, 0, 1028, 66564, 16777216, 65536, 16843780, 4, 16842752, 16843776, 16777216, 16777216, 1024, 16842756, 65536, 66560, 16777220, 1024, 4, 16778244, 66564, 16843780, 65540, 16842752, 16778244, 16777220, 1028, 66564, 16843776, 1028, 16778240, 16778240, 0, 65540, 66560, 0, 16842756 };
        DesCipher.SP2 = new int[] { -2146402272, -2147450880, 32768, 1081376, 1048576, 32, -2146435040, -2147450848, -2147483616, -2146402272, -2146402304, Integer.MIN_VALUE, -2147450880, 1048576, 32, -2146435040, 1081344, 1048608, -2147450848, 0, Integer.MIN_VALUE, 32768, 1081376, -2146435072, 1048608, -2147483616, 0, 1081344, 32800, -2146402304, -2146435072, 32800, 0, 1081376, -2146435040, 1048576, -2147450848, -2146435072, -2146402304, 32768, -2146435072, -2147450880, 32, -2146402272, 1081376, 32, 32768, Integer.MIN_VALUE, 32800, -2146402304, 1048576, -2147483616, 1048608, -2147450848, -2147483616, 1048608, 1081344, 0, -2147450880, 32800, Integer.MIN_VALUE, -2146435040, -2146402272, 1081344 };
        DesCipher.SP3 = new int[] { 520, 134349312, 0, 134348808, 134218240, 0, 131592, 134218240, 131080, 134217736, 134217736, 131072, 134349320, 131080, 134348800, 520, 134217728, 8, 134349312, 512, 131584, 134348800, 134348808, 131592, 134218248, 131584, 131072, 134218248, 8, 134349320, 512, 134217728, 134349312, 134217728, 131080, 520, 131072, 134349312, 134218240, 0, 512, 131080, 134349320, 134218240, 134217736, 512, 0, 134348808, 134218248, 131072, 134217728, 134349320, 8, 131592, 131584, 134217736, 134348800, 134218248, 520, 134348800, 131592, 8, 134348808, 131584 };
        DesCipher.SP4 = new int[] { 8396801, 8321, 8321, 128, 8396928, 8388737, 8388609, 8193, 0, 8396800, 8396800, 8396929, 129, 0, 8388736, 8388609, 1, 8192, 8388608, 8396801, 128, 8388608, 8193, 8320, 8388737, 1, 8320, 8388736, 8192, 8396928, 8396929, 129, 8388736, 8388609, 8396800, 8396929, 129, 0, 0, 8396800, 8320, 8388736, 8388737, 1, 8396801, 8321, 8321, 128, 8396929, 129, 1, 8192, 8388609, 8193, 8396928, 8388737, 8193, 8320, 8388608, 8396801, 128, 8388608, 8192, 8396928 };
        DesCipher.SP5 = new int[] { 256, 34078976, 34078720, 1107296512, 524288, 256, 1073741824, 34078720, 1074266368, 524288, 33554688, 1074266368, 1107296512, 1107820544, 524544, 1073741824, 33554432, 1074266112, 1074266112, 0, 1073742080, 1107820800, 1107820800, 33554688, 1107820544, 1073742080, 0, 1107296256, 34078976, 33554432, 1107296256, 524544, 524288, 1107296512, 256, 33554432, 1073741824, 34078720, 1107296512, 1074266368, 33554688, 1073741824, 1107820544, 34078976, 1074266368, 256, 33554432, 1107820544, 1107820800, 524544, 1107296256, 1107820800, 34078720, 0, 1074266112, 1107296256, 524544, 33554688, 1073742080, 524288, 0, 1074266112, 34078976, 1073742080 };
        DesCipher.SP6 = new int[] { 536870928, 541065216, 16384, 541081616, 541065216, 16, 541081616, 4194304, 536887296, 4210704, 4194304, 536870928, 4194320, 536887296, 536870912, 16400, 0, 4194320, 536887312, 16384, 4210688, 536887312, 16, 541065232, 541065232, 0, 4210704, 541081600, 16400, 4210688, 541081600, 536870912, 536887296, 16, 541065232, 4210688, 541081616, 4194304, 16400, 536870928, 4194304, 536887296, 536870912, 16400, 536870928, 541081616, 4210688, 541065216, 4210704, 541081600, 0, 541065232, 16, 16384, 541065216, 4210704, 16384, 4194320, 536887312, 0, 541081600, 536870912, 4194320, 536887312 };
        DesCipher.SP7 = new int[] { 2097152, 69206018, 67110914, 0, 2048, 67110914, 2099202, 69208064, 69208066, 2097152, 0, 67108866, 2, 67108864, 69206018, 2050, 67110912, 2099202, 2097154, 67110912, 67108866, 69206016, 69208064, 2097154, 69206016, 2048, 2050, 69208066, 2099200, 2, 67108864, 2099200, 67108864, 2099200, 2097152, 67110914, 67110914, 69206018, 69206018, 2, 2097154, 67108864, 67110912, 2097152, 69208064, 2050, 2099202, 69208064, 2050, 67108866, 69208066, 69206016, 2099200, 0, 2, 69208066, 0, 2099202, 69206016, 2048, 67108866, 67110912, 2048, 2097154 };
        DesCipher.SP8 = new int[] { 268439616, 4096, 262144, 268701760, 268435456, 268439616, 64, 268435456, 262208, 268697600, 268701760, 266240, 268701696, 266304, 4096, 64, 268697600, 268435520, 268439552, 4160, 266240, 262208, 268697664, 268701696, 4160, 0, 0, 268697664, 268435520, 268439552, 266304, 262144, 266304, 262144, 268701696, 4096, 64, 268697664, 4096, 266304, 268439552, 64, 268435520, 268697600, 268697664, 268435456, 262144, 268439616, 0, 268701760, 262208, 268435520, 268697600, 268439552, 268439616, 0, 268701760, 266240, 266240, 4160, 4160, 262208, 268435456, 268701696 };
    }
}
