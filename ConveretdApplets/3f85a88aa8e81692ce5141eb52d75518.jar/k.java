import java.net.InetAddress;
import java.util.Date;

// 
// Decompiled by Procyon v0.5.30
// 

public class k
{
    public String a;
    
    public k(final String s) {
        this.a = zkmToString("]\u0014W\u001a\"X\u001fZnV,c&iS'm(cY!w2}G;r5xL6}");
        if (s != null && s.length() == 32) {
            this.a = s.toUpperCase();
        }
    }
    
    private int a(final String s, final int n, final int[] array) {
        s.length();
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        for (int i = n; i < s.length(); ++i) {
            final int index = this.a.indexOf(s.charAt(i));
            int n5 = 5;
            if (n3 + 5 > 32) {
                n5 = 5 - (32 - n3);
                array[n4] = (n2 | index >> n5);
                n3 = 0;
                n2 = 0;
                ++n4;
            }
            if (n3 + n5 <= 32) {
                n2 |= index << 32 - n5 - n3;
                n3 += n5;
            }
        }
        array[n4] = n2;
        return n3 + n4 * 32;
    }
    
    private String a(final String s, final String s2, final int n) {
        int index = 0;
        int n2 = 0;
        while ((index = s.indexOf(s2, index)) != -1) {
            if (++n2 > n) {
                if (index == 0) {
                    return "";
                }
                return s.substring(0, index);
            }
            else {
                ++index;
            }
        }
        return s;
    }
    
    private String b(final String s, final String s2, final int n) {
        int n2 = s.length();
        int n3 = 0;
        while ((n2 = s.lastIndexOf(s2, n2)) != -1) {
            if (++n3 > n) {
                return s.substring(n2 + 1);
            }
            --n2;
        }
        return s;
    }
    
    private void a(final int[] array, final int[] array2, final int n, final int n2, final int n3) {
        int n4 = array[n + 0];
        int n5 = array[n + 1];
        for (int n6 = 930658718, i = n6 * n3; i != 0; i -= n6, n4 -= ((n5 << 4 ^ n5 >> 5) + n5 ^ i + array2[(i & 0x3) + n2])) {
            n5 -= ((n4 << 4 ^ n4 >> 5) + n4 ^ i + array2[(i >> 11 & 0x3) + n2]);
        }
        array[n + 0] = n4;
        array[n + 1] = n5;
    }
    
    private static int a(final byte[] array, final int n) {
        return (array[n + 3] & 0xFF) | (array[n + 2] << 8 & 0xFF00) | (array[n + 1] << 16 & 0xFF0000) | (array[n + 0] << 24 & 0xFF000000);
    }
    
    private static void a(final int n, final byte[] array, final int n2) {
        array[n2 + 0] = (byte)(n >> 24 & 0xFF);
        array[n2 + 1] = (byte)(n >> 16 & 0xFF);
        array[n2 + 2] = (byte)(n >> 8 & 0xFF);
        array[n2 + 3] = (byte)(n >> 0 & 0xFF);
    }
    
    public static void a(final byte[] array, final int[] array2, final int n, final int n2, final int n3) {
        int a = a(array, n);
        int a2 = a(array, n + 4);
        for (int n4 = 930658718, i = n4 * n3; i != 0; i -= n4, a -= ((a2 << 4 ^ a2 >> 5) + a2 ^ i + array2[i & 0x3])) {
            a2 -= ((a << 4 ^ a >> 5) + a ^ i + array2[i >> 11 & 0x3]);
        }
        a(a, array, n);
        a(a2, array, n + 4);
    }
    
    public double a(final String s, final String s2, final int[] array) {
        final int[] array2 = new int[16];
        final int[] array3 = new int[16];
        final int[] array4 = new int[16];
        final int[] array5 = new int[32];
        final Date date = new Date();
        array4[0] = 305419896;
        array4[1] = -1412567295;
        array4[2] = 591751049;
        array4[3] = -1126240238;
        double n = Math.log(date.getTime()) + 1398.0;
        double n2 = 1.35478951E-4 * (date.getTime() & 0x3FL);
        if ((date.getTime() & 0x400L) == 0x0L) {
            n2 = -n2;
        }
        int n3;
        if (s.length() > 26) {
            n3 = this.a(s, 26, array2);
            if (n3 >= 96) {
                this.a(array2, array4, 1, 0, 17);
            }
            this.a(array2, array4, 0, 0, 17);
            if (array != null) {
                this.a(s, 0, array);
                array5[0] = (array5[2] = array2[0]);
                array5[1] = (array5[3] = array2[1]);
            }
        }
        else {
            n3 = this.a(s, 0, array2);
        }
        final boolean b = n3 >= 96;
        if (b) {
            this.a(array2, array4, 1, 0, 41);
        }
        this.a(array2, array4, 0, 0, 41);
        if (b) {
            this.a(array2, array4, 1, 0, 41);
        }
        final int n4 = array2[0] >> 7 & 0x1;
        final int n5 = array2[0] >> 5 & 0x3;
        final int n6 = array2[0] >> 0 & 0x1F;
        final int n7 = array2[2] >> 28 & 0xF;
        final int n8 = array2[2] >> 24 & 0xF;
        final int n9 = array2[2] >> 19 & 0x1F;
        final int n10 = array2[2] >> 9 & 0x3FF;
        final int n11 = array2[2] >> 5 & 0xF;
        final int n12 = array2[2] >> 0 & 0x1F;
        final int n13 = n10 + 2000;
        final int n14 = n7 + n13;
        try {
            final Date date2 = new Date(n13 - 1900, n8 - 1, n12);
            final Date date3 = new Date(n14 - 1900, n11 - 1, n9);
            if (b && (date.before(date2) || date.after(date3))) {
                n -= n2;
            }
        }
        catch (Exception ex) {}
        int index = s2.indexOf(zkmToString("U\bL"));
        if (index == -1) {
            index = 0;
        }
        else {
            index += 3;
        }
        final int index2 = s2.indexOf("/", index);
        int length;
        if (index2 == -1) {
            length = s2.length();
        }
        else {
            length = index2;
        }
        String s3 = s2.substring(index, length);
        final int index3;
        if ((index3 = s3.indexOf(58)) != -1) {
            s3 = s3.substring(0, index3);
        }
        String substring;
        if (index2 != -1) {
            substring = s2.substring(index2);
        }
        else {
            substring = null;
        }
        final String s4 = s3;
        String s5 = this.b(s3, ".", n4 + 1);
        if (substring != null) {
            s5 += this.a(substring, "/", n5);
        }
        final String lowerCase = s5.toLowerCase();
        final int length2 = lowerCase.length();
        final int[] array6 = new int[4 + length2 / 4];
        int i = 0;
        int n15 = 0;
        while (i < length2) {
            array6[n15] = (array6[n15] << 8 | lowerCase.charAt(i));
            if (++i % 4 == 0) {
                ++n15;
            }
        }
        if (n15 < 4) {
            n15 = 4;
        }
        array3[0] = 779999848;
        array3[1] = -1008293463;
        for (int j = 0; j <= n15 - 4; ++j) {
            this.a(array3, array6, 0, j, 37);
        }
        final int n16 = array3[0] & 0xFFFFFF00;
        byte[] address;
        try {
            address = InetAddress.getByName(s4).getAddress();
        }
        catch (Exception ex2) {
            final byte[] array7;
            address = (array7 = new byte[4]);
            final int n17 = 0;
            final byte[] array8 = address;
            final int n18 = 1;
            final byte[] array9 = address;
            final int n19 = 2;
            final byte[] array10 = address;
            final int n20 = 3;
            final boolean b2 = false;
            array9[n19] = (array10[n20] = (byte)(b2 ? 1 : 0));
            array7[n17] = (array8[n18] = (byte)(b2 ? 1 : 0));
        }
        final int n21 = ((address[0] & 0xFF) << 24 | (address[1] & 0xFF) << 16 | (address[2] & 0xFF) << 8 | (address[3] & 0xFF) << 0) & -1 << n6;
        final int[] array11 = array2;
        final int n22 = 1;
        array11[n22] &= -1 << n6;
        if (n16 != (array2[0] & 0xFFFFFF00) && n21 != array2[1]) {
            n -= n2;
        }
        if (n6 > 16) {
            n -= n2;
        }
        if (s.length() > 26 && array != null) {
            this.a(array, array5, 0, 0, 33);
            this.a(array, array5, 1, 0, 35);
            this.a(array, array5, 2, 0, 37);
            this.a(array, array5, 1, 0, 39);
            this.a(array, array5, 0, 0, 41);
            array5[0] = (array5[2] = 305419896);
            array5[1] = (array5[3] = -1413405883);
            this.a(array, array5, 2, 0, 37);
            this.a(array, array5, 1, 0, 37);
            this.a(array, array5, 0, 0, 37);
        }
        return n;
    }
    
    private static String zkmToString(final String s) {
        final char[] charArray = s.toCharArray();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = 'o';
                    break;
                }
                case 1: {
                    c2 = '\'';
                    break;
                }
                case 2: {
                    c2 = 'c';
                    break;
                }
                case 3: {
                    c2 = '/';
                    break;
                }
                default: {
                    c2 = '\u0014';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
