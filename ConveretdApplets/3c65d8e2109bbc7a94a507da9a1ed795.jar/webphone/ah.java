// 
// Decompiled by Procyon v0.5.30
// 

package webphone;

public class ah
{
    private static final boolean do = true;
    private static final short int = 132;
    private static final int for = 32635;
    private static final int[] a;
    private static short[] char;
    private static final byte case = 15;
    private static final byte byte = 4;
    private static final short[] try;
    private static short[] new;
    private static byte[] if;
    private static byte[] else;
    
    public static void do(final byte[] array, final int n, int n2) {
        n2 += n;
        for (int i = n; i < n2; ++i) {
            final int n3 = i;
            array[n3] += 128;
        }
    }
    
    public static void if(final byte[] array, final int n, final int n2) {
        byte b;
        for (int n3 = n2 * 2 + n - 1, i = n; i < n3; array[i] = array[++i], array[i++] = b) {
            b = array[i];
        }
    }
    
    public static void for(final byte[] array, final int n, final int n2) {
        byte b;
        for (int n3 = n2 * 3 + n - 2, i = n; i < n3; array[i] = array[++i + 1], array[++i] = b, ++i) {
            b = array[i];
        }
    }
    
    public static void new(final byte[] array, final int n, final int n2) {
        byte b2;
        for (int n3 = n2 * 4 + n - 3, i = n; i < n3; ++i, b2 = array[i], array[i] = array[++i], array[i++] = b2, ++i) {
            final byte b = array[i];
            array[i] = array[i + 3];
            array[i + 3] = b;
        }
    }
    
    public static void int(final byte[] array, int n, final byte[] array2, int n2, int i) {
        while (i > 0) {
            array2[n2++] = (byte)(array[n++] + 128);
            --i;
        }
    }
    
    public static void a(final byte[] array, int n, final byte[] array2, int n2, int i) {
        while (i > 0) {
            array2[n2++] = array[n + 1];
            array2[n2++] = array[n++];
            ++n;
            --i;
        }
    }
    
    public static void new(final byte[] array, int n, final byte[] array2, int n2, int i) {
        while (i > 0) {
            array2[n2++] = array[n + 2];
            ++n2;
            array2[n2++] = array[n++];
            ++n;
            ++n;
            --i;
        }
    }
    
    public static void for(final byte[] array, int n, final byte[] array2, int n2, int i) {
        while (i > 0) {
            array2[n2++] = array[n + 3];
            array2[n2++] = array[n + 2];
            array2[n2++] = array[n + 1];
            array2[n2++] = array[n++];
            ++n;
            ++n;
            ++n;
            --i;
        }
    }
    
    public static short a(final byte b, final byte b2) {
        return (short)(b << 8 | (b2 & 0xFF));
    }
    
    public static short a(final byte[] array, final int n, final boolean b) {
        return b ? ((short)(array[n] << 8 | (array[n + 1] & 0xFF))) : ((short)(array[n + 1] << 8 | (array[n] & 0xFF)));
    }
    
    public static int if(final byte b, final byte b2) {
        return b << 8 | (b2 & 0xFF);
    }
    
    public static int if(final byte[] array, final int n, final boolean b) {
        return b ? (array[n] << 8 | (array[n + 1] & 0xFF)) : (array[n + 1] << 8 | (array[n] & 0xFF));
    }
    
    public static int do(final byte[] array, final int n, final boolean b) {
        return b ? (array[n] << 16 | (array[n + 1] & 0xFF) << 8 | (array[n + 2] & 0xFF)) : (array[n + 2] << 16 | (array[n + 1] & 0xFF) << 8 | (array[n] & 0xFF));
    }
    
    public static int for(final byte[] array, final int n, final boolean b) {
        return b ? (array[n] << 24 | (array[n + 1] & 0xFF) << 16 | (array[n + 2] & 0xFF) << 8 | (array[n + 3] & 0xFF)) : (array[n + 3] << 24 | (array[n + 2] & 0xFF) << 16 | (array[n + 1] & 0xFF) << 8 | (array[n] & 0xFF));
    }
    
    public static void a(final short n, final byte[] array, final int n2, final boolean b) {
        do(n, array, n2, b);
    }
    
    public static void do(final int n, final byte[] array, int n2, final boolean b) {
        if (b) {
            array[n2++] = (byte)(n >> 8);
            array[n2] = (byte)(n & 0xFF);
        }
        else {
            array[n2++] = (byte)(n & 0xFF);
            array[n2] = (byte)(n >> 8);
        }
    }
    
    public static void a(final int n, final byte[] array, int n2, final boolean b) {
        if (b) {
            array[n2++] = (byte)(n >> 16);
            array[n2++] = (byte)(n >>> 8 & 0xFF);
            array[n2] = (byte)(n & 0xFF);
        }
        else {
            array[n2++] = (byte)(n & 0xFF);
            array[n2++] = (byte)(n >>> 8 & 0xFF);
            array[n2] = (byte)(n >> 16);
        }
    }
    
    public static void if(final int n, final byte[] array, int n2, final boolean b) {
        if (b) {
            array[n2++] = (byte)(n >> 24);
            array[n2++] = (byte)(n >>> 16 & 0xFF);
            array[n2++] = (byte)(n >>> 8 & 0xFF);
            array[n2] = (byte)(n & 0xFF);
        }
        else {
            array[n2++] = (byte)(n & 0xFF);
            array[n2++] = (byte)(n >>> 8 & 0xFF);
            array[n2++] = (byte)(n >>> 16 & 0xFF);
            array[n2] = (byte)(n >> 24);
        }
    }
    
    public static byte a(int n) {
        if (n > 32767) {
            n = 32767;
        }
        else if (n < -32768) {
            n = -32768;
        }
        final int n2 = n >> 8 & 0x80;
        if (n2 != 0) {
            n = -n;
        }
        if (n > 32635) {
            n = 32635;
        }
        n += 132;
        final int n3 = ah.a[n >> 7 & 0xFF];
        int n4 = ~(n2 | n3 << 4 | (n >> n3 + 3 & 0xF));
        if (n4 == 0) {
            n4 = 2;
        }
        return (byte)n4;
    }
    
    public static short for(final byte b) {
        return ah.char[b & 0xFF];
    }
    
    public static void new(final byte[] array, final int n, int i, final boolean b) {
        int n3;
        int n2 = n3 = n;
        if (b) {
            while (i > 0) {
                array[n3++] = a(if(array[n2], array[n2 + 1]));
                ++n2;
                ++n2;
                --i;
            }
        }
        else {
            while (i > 0) {
                array[n3++] = a(if(array[n2 + 1], array[n2]));
                ++n2;
                ++n2;
                --i;
            }
        }
    }
    
    public static void try(final byte[] array, final int n, final byte[] array2, final int n2, int i, final boolean b) {
        int n3 = n;
        int n4 = n2;
        if (b) {
            while (i > 0) {
                array2[n4++] = a(if(array[n3], array[n3 + 1]));
                ++n3;
                ++n3;
                --i;
            }
        }
        else {
            while (i > 0) {
                array2[n4++] = a(if(array[n3 + 1], array[n3]));
                ++n3;
                ++n3;
                --i;
            }
        }
    }
    
    public static void for(final byte[] array, final int n, int n2, final boolean b) {
        n2 += n;
        if (b) {
            for (int i = n; i < n2; ++i) {
                array[i] = a(array[i] << 8);
            }
        }
        else {
            for (int j = n; j < n2; ++j) {
                array[j] = a((byte)(array[j] + 128) << 8);
            }
        }
    }
    
    public static void new(final byte[] array, final int n, final byte[] array2, final int n2, int i, final boolean b) {
        int n3 = n2;
        int n4 = n;
        if (b) {
            while (i > 0) {
                array2[n3++] = a(array[n4++] << 8);
                --i;
            }
        }
        else {
            while (i > 0) {
                array2[n3++] = a((byte)(array[n4++] + 128) << 8);
                --i;
            }
        }
    }
    
    public static void int(final byte[] array, final int n, final byte[] array2, final int n2, int i, final boolean b) {
        int n3 = n2;
        int n4 = n;
        while (i > 0) {
            do(ah.char[array[n4++] & 0xFF], array2, n3++, b);
            ++n3;
            --i;
        }
    }
    
    public static void try(final byte[] array, final int n, int n2, final boolean b) {
        n2 += n;
        if (b) {
            for (int i = n; i < n2; ++i) {
                array[i] = (byte)(ah.char[array[i] & 0xFF] >> 8 & 0xFF);
            }
        }
        else {
            for (int j = n; j < n2; ++j) {
                array[j] = (byte)((ah.char[array[j] & 0xFF] >> 8) + 128);
            }
        }
    }
    
    public static void do(final byte[] array, final int n, final byte[] array2, final int n2, int i, final boolean b) {
        int n3 = n;
        int n4 = n2;
        if (b) {
            while (i > 0) {
                array2[n4++] = (byte)(ah.char[array[n3++] & 0xFF] >> 8 & 0xFF);
                --i;
            }
        }
        else {
            while (i > 0) {
                array2[n4++] = (byte)((ah.char[array[n3++] & 0xFF] >> 8) + 128);
                --i;
            }
        }
    }
    
    public static byte a(short n) {
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
            if (n <= ah.try[i]) {
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
    
    public static short if(final byte b) {
        return ah.new[b & 0xFF];
    }
    
    public static void if(final byte[] array, final int n, int i, final boolean b) {
        int n3;
        int n2 = n3 = n;
        if (b) {
            while (i > 0) {
                array[n3++] = a(a(array[n2], array[n2 + 1]));
                ++n2;
                ++n2;
                --i;
            }
        }
        else {
            while (i > 0) {
                array[n3++] = a(a(array[n2 + 1], array[n2]));
                ++n2;
                ++n2;
                --i;
            }
        }
    }
    
    public static void if(final byte[] array, final int n, final byte[] array2, final int n2, int i, final boolean b) {
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
    
    public static void int(final byte[] array, final int n, int n2, final boolean b) {
        n2 += n;
        if (b) {
            for (int i = n; i < n2; ++i) {
                array[i] = a((short)(array[i] << 8));
            }
        }
        else {
            for (int j = n; j < n2; ++j) {
                array[j] = a((short)((byte)(array[j] + 128) << 8));
            }
        }
    }
    
    public static void a(final byte[] array, final int n, final byte[] array2, final int n2, int i, final boolean b) {
        int n3 = n2;
        int n4 = n;
        if (b) {
            while (i > 0) {
                array2[n3++] = a((short)(array[n4++] << 8));
                --i;
            }
        }
        else {
            while (i > 0) {
                array2[n3++] = a((short)((byte)(array[n4++] + 128) << 8));
                --i;
            }
        }
    }
    
    public static void a(final byte[] array, final int n, int n2, final boolean b) {
        n2 += n;
        if (b) {
            for (int i = n; i < n2; ++i) {
                array[i] = (byte)(ah.new[array[i] & 0xFF] >> 8 & 0xFF);
            }
        }
        else {
            for (int j = n; j < n2; ++j) {
                array[j] = (byte)((ah.new[array[j] & 0xFF] >> 8) + 128);
            }
        }
    }
    
    public static void byte(final byte[] array, final int n, final byte[] array2, final int n2, int i, final boolean b) {
        int n3 = n;
        int n4 = n2;
        if (b) {
            while (i > 0) {
                array2[n4++] = (byte)(ah.new[array[n3++] & 0xFF] >> 8 & 0xFF);
                --i;
            }
        }
        else {
            while (i > 0) {
                array2[n4++] = (byte)((ah.new[array[n3++] & 0xFF] >> 8) + 128);
                --i;
            }
        }
    }
    
    public static void for(final byte[] array, final int n, final byte[] array2, final int n2, int i, final boolean b) {
        int n3 = n2;
        int n4 = n;
        while (i > 0) {
            do(ah.new[array[n4++] & 0xFF], array2, n3++, b);
            ++n3;
            --i;
        }
    }
    
    public static byte do(final byte b) {
        return ah.if[b & 0xFF];
    }
    
    public static void a(final byte[] array, final int n, int n2) {
        n2 += n;
        for (int i = n; i < n2; ++i) {
            array[i] = ah.if[array[i] & 0xFF];
        }
    }
    
    public static void if(final byte[] array, final int n, final byte[] array2, final int n2, int i) {
        int n3 = n2;
        int n4 = n;
        while (i > 0) {
            array2[n4++] = ah.if[array[n3++] & 0xFF];
            --i;
        }
    }
    
    public static byte a(final byte b) {
        return ah.else[b & 0xFF];
    }
    
    public static void int(final byte[] array, final int n, int n2) {
        n2 += n;
        for (int i = n; i < n2; ++i) {
            array[i] = ah.else[array[i] & 0xFF];
        }
    }
    
    public static void do(final byte[] array, final int n, final byte[] array2, final int n2, int i) {
        int n3 = n2;
        int n4 = n;
        while (i > 0) {
            array2[n3++] = ah.else[array[n4++] & 0xFF];
            --i;
        }
    }
    
    public static void a(final byte[] array, final int n, final int n2, final int n3) {
        switch (n3) {
            case 1: {
                do(array, n, n2);
                break;
            }
            case 2: {
                if(array, n, n2 / 2);
                break;
            }
            case 3: {
                for(array, n, n2 / 3);
                break;
            }
            case 4: {
                new(array, n, n2 / 4);
                break;
            }
        }
    }
    
    public static void a(final byte[] array, final int n, final byte[] array2, final int n2, final int n3, final int n4) {
        switch (n4) {
            case 1: {
                int(array, n, array2, n2, n3);
                break;
            }
            case 2: {
                a(array, n, array2, n2, n3 / 2);
                break;
            }
            case 3: {
                new(array, n, array2, n2, n3 / 3);
                break;
            }
            case 4: {
                for(array, n, array2, n2, n3 / 4);
                break;
            }
        }
    }
    
    public static byte[] a(final short n, final boolean b) {
        final byte[] array = new byte[2];
        if (b) {
            array[0] = (byte)(n & 0xFF);
            array[1] = (byte)(n >>> 8 & 0xFF);
        }
        else {
            array[0] = (byte)(n >>> 8 & 0xFF);
            array[1] = (byte)(n & 0xFF);
        }
        return array;
    }
    
    public static void a(final short[] array, final byte[] array2, final boolean b) {
        int n = 0;
        for (int i = 0; i < array.length; ++i) {
            final byte[] a = a(array[i], b);
            array2[n++] = a[0];
            array2[n++] = a[1];
        }
    }
    
    public static short[] do(final byte[] array, final int n, final int n2, final boolean b) throws ArrayIndexOutOfBoundsException {
        if (0 < n2 && n + n2 <= array.length) {
            final int n3 = n2 / 2;
            final short[] array2 = new short[n3];
            int n4 = n;
            for (int i = 0; i < n3; ++i) {
                int n5;
                if (b) {
                    n5 = ((array[n4++] & 0xFF) | (0xFF00 & array[n4++] << 8));
                }
                else {
                    n5 = (array[n4++] << 8 | (0xFF & array[n4++]));
                }
                array2[i] = (short)n5;
            }
            return array2;
        }
        throw new ArrayIndexOutOfBoundsException("offset: " + n + ", length: " + n2 + ", array length: " + array.length);
    }
    
    static {
        a = new int[] { 0, 0, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7 };
        ah.char = new short[] { -32124, -31100, -30076, -29052, -28028, -27004, -25980, -24956, -23932, -22908, -21884, -20860, -19836, -18812, -17788, -16764, -15996, -15484, -14972, -14460, -13948, -13436, -12924, -12412, -11900, -11388, -10876, -10364, -9852, -9340, -8828, -8316, -7932, -7676, -7420, -7164, -6908, -6652, -6396, -6140, -5884, -5628, -5372, -5116, -4860, -4604, -4348, -4092, -3900, -3772, -3644, -3516, -3388, -3260, -3132, -3004, -2876, -2748, -2620, -2492, -2364, -2236, -2108, -1980, -1884, -1820, -1756, -1692, -1628, -1564, -1500, -1436, -1372, -1308, -1244, -1180, -1116, -1052, -988, -924, -876, -844, -812, -780, -748, -716, -684, -652, -620, -588, -556, -524, -492, -460, -428, -396, -372, -356, -340, -324, -308, -292, -276, -260, -244, -228, -212, -196, -180, -164, -148, -132, -120, -112, -104, -96, -88, -80, -72, -64, -56, -48, -40, -32, -24, -16, -8, 0, 32124, 31100, 30076, 29052, 28028, 27004, 25980, 24956, 23932, 22908, 21884, 20860, 19836, 18812, 17788, 16764, 15996, 15484, 14972, 14460, 13948, 13436, 12924, 12412, 11900, 11388, 10876, 10364, 9852, 9340, 8828, 8316, 7932, 7676, 7420, 7164, 6908, 6652, 6396, 6140, 5884, 5628, 5372, 5116, 4860, 4604, 4348, 4092, 3900, 3772, 3644, 3516, 3388, 3260, 3132, 3004, 2876, 2748, 2620, 2492, 2364, 2236, 2108, 1980, 1884, 1820, 1756, 1692, 1628, 1564, 1500, 1436, 1372, 1308, 1244, 1180, 1116, 1052, 988, 924, 876, 844, 812, 780, 748, 716, 684, 652, 620, 588, 556, 524, 492, 460, 428, 396, 372, 356, 340, 324, 308, 292, 276, 260, 244, 228, 212, 196, 180, 164, 148, 132, 120, 112, 104, 96, 88, 80, 72, 64, 56, 48, 40, 32, 24, 16, 8, 0 };
        try = new short[] { 255, 511, 1023, 2047, 4095, 8191, 16383, 32767 };
        ah.new = new short[] { -5504, -5248, -6016, -5760, -4480, -4224, -4992, -4736, -7552, -7296, -8064, -7808, -6528, -6272, -7040, -6784, -2752, -2624, -3008, -2880, -2240, -2112, -2496, -2368, -3776, -3648, -4032, -3904, -3264, -3136, -3520, -3392, -22016, -20992, -24064, -23040, -17920, -16896, -19968, -18944, -30208, -29184, -32256, -31232, -26112, -25088, -28160, -27136, -11008, -10496, -12032, -11520, -8960, -8448, -9984, -9472, -15104, -14592, -16128, -15616, -13056, -12544, -14080, -13568, -344, -328, -376, -360, -280, -264, -312, -296, -472, -456, -504, -488, -408, -392, -440, -424, -88, -72, -120, -104, -24, -8, -56, -40, -216, -200, -248, -232, -152, -136, -184, -168, -1376, -1312, -1504, -1440, -1120, -1056, -1248, -1184, -1888, -1824, -2016, -1952, -1632, -1568, -1760, -1696, -688, -656, -752, -720, -560, -528, -624, -592, -944, -912, -1008, -976, -816, -784, -880, -848, 5504, 5248, 6016, 5760, 4480, 4224, 4992, 4736, 7552, 7296, 8064, 7808, 6528, 6272, 7040, 6784, 2752, 2624, 3008, 2880, 2240, 2112, 2496, 2368, 3776, 3648, 4032, 3904, 3264, 3136, 3520, 3392, 22016, 20992, 24064, 23040, 17920, 16896, 19968, 18944, 30208, 29184, 32256, 31232, 26112, 25088, 28160, 27136, 11008, 10496, 12032, 11520, 8960, 8448, 9984, 9472, 15104, 14592, 16128, 15616, 13056, 12544, 14080, 13568, 344, 328, 376, 360, 280, 264, 312, 296, 472, 456, 504, 488, 408, 392, 440, 424, 88, 72, 120, 104, 24, 8, 56, 40, 216, 200, 248, 232, 152, 136, 184, 168, 1376, 1312, 1504, 1440, 1120, 1056, 1248, 1184, 1888, 1824, 2016, 1952, 1632, 1568, 1760, 1696, 688, 656, 752, 720, 560, 528, 624, 592, 944, 912, 1008, 976, 816, 784, 880, 848 };
        ah.if = new byte[] { -86, -85, -88, -87, -82, -81, -84, -83, -94, -93, -96, -95, -90, -89, -92, -91, -70, -69, -72, -71, -66, -65, -68, -67, -78, -77, -80, -79, -74, -73, -76, -75, -118, -117, -120, -119, -114, -113, -116, -115, -126, -125, -128, -127, -122, -121, -124, -123, -101, -104, -103, -98, -97, -100, -99, -110, -109, -112, -111, -106, -105, -108, -107, -22, -24, -23, -18, -17, -20, -19, -30, -29, -32, -31, -26, -25, -28, -27, -6, -8, -2, -1, -4, -3, -14, -13, -16, -15, -10, -9, -12, -11, -53, -55, -49, -51, -62, -61, -64, -63, -58, -57, -60, -59, -38, -37, -40, -39, -34, -33, -36, -35, -46, -46, -45, -45, -48, -48, -47, -47, -42, -42, -41, -41, -44, -44, -43, -43, 42, 43, 40, 41, 46, 47, 44, 45, 34, 35, 32, 33, 38, 39, 36, 37, 58, 59, 56, 57, 62, 63, 60, 61, 50, 51, 48, 49, 54, 55, 52, 53, 10, 11, 8, 9, 14, 15, 12, 13, 2, 3, 0, 1, 6, 7, 4, 5, 27, 24, 25, 30, 31, 28, 29, 18, 19, 16, 17, 22, 23, 20, 21, 106, 104, 105, 110, 111, 108, 109, 98, 99, 96, 97, 102, 103, 100, 101, 122, 120, 126, 127, 124, 125, 114, 115, 112, 113, 118, 119, 116, 117, 75, 73, 79, 77, 66, 67, 64, 65, 70, 71, 68, 69, 90, 91, 88, 89, 94, 95, 92, 93, 82, 82, 83, 83, 80, 80, 81, 81, 86, 86, 87, 87, 84, 84, 85, 85 };
        ah.else = new byte[] { -86, -85, -88, -87, -82, -81, -84, -83, -94, -93, -96, -95, -90, -89, -92, -91, -71, -70, -73, -72, -67, -66, -69, -68, -79, -78, -80, -80, -75, -74, -77, -76, -118, -117, -120, -119, -114, -113, -116, -115, -126, -125, -128, -127, -122, -121, -124, -123, -102, -101, -104, -103, -98, -97, -100, -99, -110, -109, -112, -111, -106, -105, -108, -107, -30, -29, -32, -31, -26, -25, -28, -27, -35, -35, -36, -36, -33, -33, -34, -34, -12, -10, -16, -14, -4, -2, -8, -6, -22, -21, -24, -23, -18, -17, -20, -19, -56, -55, -58, -57, -52, -51, -54, -53, -64, -63, -65, -65, -60, -59, -62, -61, -42, -41, -44, -43, -38, -37, -40, -39, -49, -49, -50, -50, -46, -45, -48, -47, 42, 43, 40, 41, 46, 47, 44, 45, 34, 35, 32, 33, 38, 39, 36, 37, 57, 58, 55, 56, 61, 62, 59, 60, 49, 50, 48, 48, 53, 54, 51, 52, 10, 11, 8, 9, 14, 15, 12, 13, 2, 3, 0, 1, 6, 7, 4, 5, 26, 27, 24, 25, 30, 31, 28, 29, 18, 19, 16, 17, 22, 23, 20, 21, 98, 99, 96, 97, 102, 103, 100, 101, 93, 93, 92, 92, 95, 95, 94, 94, 116, 118, 112, 114, 124, 126, 120, 122, 106, 107, 104, 105, 110, 111, 108, 109, 72, 73, 70, 71, 76, 77, 74, 75, 64, 65, 63, 63, 68, 69, 66, 67, 86, 87, 84, 85, 90, 91, 88, 89, 79, 79, 78, 78, 82, 83, 80, 81 };
    }
}
