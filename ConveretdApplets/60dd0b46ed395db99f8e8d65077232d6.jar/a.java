// 
// Decompiled by Procyon v0.5.30
// 

public class a
{
    public static int a;
    public static int b;
    public static int c;
    
    public static void a(int a) {
        final int c = a.c;
        final int n = a;
        if (c == 0 && n < 0) {
            a += 262144;
            goto Label_0019;
        }
        final short[] array = new short[n];
        array[0] = 0;
        array[1] = 1606;
        array[2] = 3196;
        array[3] = 4756;
        array[4] = 6270;
        array[5] = 7723;
        array[6] = 9102;
        array[7] = 10394;
        array[8] = 11585;
        array[9] = 12665;
        array[10] = 13623;
        array[11] = 14449;
        array[12] = 15137;
        array[13] = 15679;
        array[14] = 16069;
        array[15] = 16305;
        array[16] = 16384;
        array[17] = 0;
        final short[] array2 = array;
        final int n2 = a;
        if (c == 0) {
            if (n2 >= 262144) {
                a -= 262144;
            }
            a.a = a;
            a.b = a.a;
        }
        int n3 = n2;
        int n4 = 1;
        int n7;
        int n6;
        final int n5 = n6 = (n7 = a);
        int b;
        int n9;
        final int n8 = n9 = (b = 196608);
        Label_0283: {
            if (c == 0) {
                if (n5 > n8) {
                    a.a = 262144 - a;
                    n4 = -1;
                    a.b -= 196608;
                    if (c == 0) {
                        break Label_0283;
                    }
                }
                final int n10;
                n6 = (n10 = (n7 = a));
                final int n11;
                n9 = (n11 = (b = 131072));
            }
            if (c == 0) {
                if (n5 > n8) {
                    a.a = a - 131072;
                    n4 = -1;
                    a.b = 196608 - a.b;
                    n3 = -1;
                    if (c == 0) {
                        break Label_0283;
                    }
                }
                n7 = (n6 = a);
                b = (n9 = 65536);
            }
            if (c == 0) {
                if (n6 > n9) {
                    a.a = 131072 - a;
                    a.b -= 65536;
                    n3 = -1;
                    if (c == 0) {
                        break Label_0283;
                    }
                }
                n7 = 65536;
                b = a.b;
            }
            a.b = n7 - b;
        }
        final int n12 = a.a / 4096;
        final int n13 = a.a - n12 * 4096;
        a.a = array2[n12] * (4096 - n13) + array2[n12 + 1] * n13;
        a.a /= 256;
        a.a *= n4;
        final int n14 = a.b / 4096;
        final int n15 = a.b - n14 * 4096;
        a.b = array2[n14] * (4096 - n15) + array2[n14 + 1] * n15;
        a.b /= 256;
        a.b *= n3;
    }
}
