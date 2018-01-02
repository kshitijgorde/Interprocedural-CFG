// 
// Decompiled by Procyon v0.5.30
// 

package com.bitcoinplus.applet.a;

public final class a
{
    private static final int[] a;
    
    public static int[] a() {
        final int[] array;
        (array = new int[8])[0] = 1779033703;
        array[1] = -1150833019;
        array[2] = 1013904242;
        array[3] = -1521486534;
        array[4] = 1359893119;
        array[5] = -1694144372;
        array[6] = 528734635;
        array[7] = 1541459225;
        return array;
    }
    
    public static int[] a(final int[] array, final int[] array2) {
        final int[] array3 = new int[64];
        System.arraycopy(array2, 0, array3, 0, 16);
        for (int i = 16; i <= 63; ++i) {
            final int n;
            final int n2;
            array3[i] = (((n = array3[i - 2]) >>> 17 | n << 15) ^ (n >>> 19 | n << 13) ^ n >>> 10) + array3[i - 7] + (((n2 = array3[i - 15]) >>> 7 | n2 << 25) ^ (n2 >>> 18 | n2 << 14) ^ n2 >>> 3) + array3[i - 16];
        }
        int n3 = array[0];
        int n4 = array[1];
        int n5 = array[2];
        int n6 = array[3];
        int n7 = array[4];
        int n8 = array[5];
        int n9 = array[6];
        int n10 = array[7];
        int n11 = 0;
        for (int j = 0; j < 8; ++j) {
            final int n12 = n10 + (b(n7) + a(n7, n8, n9) + com.bitcoinplus.applet.a.a.a[n11] + array3[n11]);
            final int n13 = n6 + n12;
            final int n14 = n12 + (a(n3) + b(n3, n4, n5));
            ++n11;
            final int n15 = n9 + (b(n13) + a(n13, n7, n8) + com.bitcoinplus.applet.a.a.a[n11] + array3[n11]);
            final int n16 = n5 + n15;
            final int n17 = n15 + (a(n14) + b(n14, n3, n4));
            ++n11;
            final int n18 = n8 + (b(n16) + a(n16, n13, n7) + com.bitcoinplus.applet.a.a.a[n11] + array3[n11]);
            final int n19 = n4 + n18;
            final int n20 = n18 + (a(n17) + b(n17, n14, n3));
            ++n11;
            final int n21 = n7 + (b(n19) + a(n19, n16, n13) + com.bitcoinplus.applet.a.a.a[n11] + array3[n11]);
            final int n22 = n3 + n21;
            final int n23 = n21 + (a(n20) + b(n20, n17, n14));
            ++n11;
            final int n24 = n13 + (b(n22) + a(n22, n19, n16) + com.bitcoinplus.applet.a.a.a[n11] + array3[n11]);
            n10 = n14 + n24;
            n6 = n24 + (a(n23) + b(n23, n20, n17));
            ++n11;
            final int n25 = n16 + (b(n10) + a(n10, n22, n19) + com.bitcoinplus.applet.a.a.a[n11] + array3[n11]);
            n9 = n17 + n25;
            n5 = n25 + (a(n6) + b(n6, n23, n20));
            ++n11;
            final int n26 = n19 + (b(n9) + a(n9, n10, n22) + com.bitcoinplus.applet.a.a.a[n11] + array3[n11]);
            n8 = n20 + n26;
            n4 = n26 + (a(n5) + b(n5, n6, n23));
            ++n11;
            final int n27 = n22 + (b(n8) + a(n8, n9, n10) + com.bitcoinplus.applet.a.a.a[n11] + array3[n11]);
            n7 = n23 + n27;
            n3 = n27 + (a(n4) + b(n4, n5, n6));
            ++n11;
        }
        final int n28 = 0;
        array[n28] += n3;
        final int n29 = 1;
        array[n29] += n4;
        final int n30 = 2;
        array[n30] += n5;
        final int n31 = 3;
        array[n31] += n6;
        final int n32 = 4;
        array[n32] += n7;
        final int n33 = 5;
        array[n33] += n8;
        final int n34 = 6;
        array[n34] += n9;
        final int n35 = 7;
        array[n35] += n10;
        return array;
    }
    
    private static int a(final int n, final int n2, final int n3) {
        return (n & n2) ^ (~n & n3);
    }
    
    private static int b(final int n, final int n2, final int n3) {
        return (n & n2) ^ (n & n3) ^ (n2 & n3);
    }
    
    private static int a(final int n) {
        return (n >>> 2 | n << 30) ^ (n >>> 13 | n << 19) ^ (n >>> 22 | n << 10);
    }
    
    private static int b(final int n) {
        return (n >>> 6 | n << 26) ^ (n >>> 11 | n << 21) ^ (n >>> 25 | n << 7);
    }
    
    static {
        a = new int[] { 1116352408, 1899447441, -1245643825, -373957723, 961987163, 1508970993, -1841331548, -1424204075, -670586216, 310598401, 607225278, 1426881987, 1925078388, -2132889090, -1680079193, -1046744716, -459576895, -272742522, 264347078, 604807628, 770255983, 1249150122, 1555081692, 1996064986, -1740746414, -1473132947, -1341970488, -1084653625, -958395405, -710438585, 113926993, 338241895, 666307205, 773529912, 1294757372, 1396182291, 1695183700, 1986661051, -2117940946, -1838011259, -1564481375, -1474664885, -1035236496, -949202525, -778901479, -694614492, -200395387, 275423344, 430227734, 506948616, 659060556, 883997877, 958139571, 1322822218, 1537002063, 1747873779, 1955562222, 2024104815, -2067236844, -1933114872, -1866530822, -1538233109, -1090935817, -965641998 };
    }
}
