import java.io.UnsupportedEncodingException;

// 
// Decompiled by Procyon v0.5.30
// 

public class extends
{
    private static final int tna = 76;
    private static final byte una = 61;
    private static final byte vna = 10;
    private static final String wna = "UTF-8";
    private static final byte[] xna;
    
    private static byte[] _(final byte[] array, final int n, final int n2, final byte[] array2, final int n3) {
        final int n4 = ((n2 > 0) ? (array[n] << 24 >>> 8) : 0) | ((n2 > 1) ? (array[n + 1] << 24 >>> 16) : 0) | ((n2 > 2) ? (array[n + 2] << 24 >>> 24) : 0);
        switch (n2) {
            case 3: {
                array2[n3] = extends.xna[n4 >>> 18];
                array2[n3 + 1] = extends.xna[n4 >>> 12 & 0x3F];
                array2[n3 + 2] = extends.xna[n4 >>> 6 & 0x3F];
                array2[n3 + 3] = extends.xna[n4 & 0x3F];
                return array2;
            }
            case 2: {
                array2[n3] = extends.xna[n4 >>> 18];
                array2[n3 + 1] = extends.xna[n4 >>> 12 & 0x3F];
                array2[n3 + 2] = extends.xna[n4 >>> 6 & 0x3F];
                array2[n3 + 3] = 61;
                return array2;
            }
            case 1: {
                array2[n3] = extends.xna[n4 >>> 18];
                array2[n3 + 1] = extends.xna[n4 >>> 12 & 0x3F];
                array2[n3 + 3] = (array2[n3 + 2] = 61);
                return array2;
            }
            default: {
                return array2;
            }
        }
    }
    
    public static String b(final byte[] array) {
        return b(array, 0, array.length);
    }
    
    public static String b(final byte[] array, final int n, final int n2) {
        final int n3 = n2 * 4 / 3;
        final byte[] array2 = new byte[n3 + ((n2 % 3 > 0) ? 4 : 0) + n3 / 76];
        int i = 0;
        int n4 = 0;
        final int n5 = n2 - 2;
        int n6 = 0;
        while (i < n5) {
            _(array, i + n, 3, array2, n4);
            n6 += 4;
            if (n6 == 76) {
                array2[n4 + 4] = 10;
                ++n4;
                n6 = 0;
            }
            i += 3;
            n4 += 4;
        }
        if (i < n2) {
            _(array, i + n, n2 - i, array2, n4);
            n4 += 4;
        }
        try {
            return new String(array2, 0, n4, "UTF-8");
        }
        catch (UnsupportedEncodingException ex) {
            return new String(array2, 0, n4);
        }
    }
    
    static {
        xna = new byte[] { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47 };
    }
}
