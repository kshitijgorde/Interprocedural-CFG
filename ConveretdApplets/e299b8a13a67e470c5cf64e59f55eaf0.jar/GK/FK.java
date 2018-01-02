// 
// Decompiled by Procyon v0.5.30
// 

package GK;

import java.util.StringTokenizer;
import java.util.Hashtable;

public class FK
{
    private static Hashtable TT;
    private static char[] MK;
    
    static {
        FK.TT = ST();
        FK.MK = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
    }
    
    private static void YS(final StringBuffer sb, int n) {
        for (int i = 0; i < 8; ++i) {
            sb.append(FK.MK[n >>> 28]);
            n <<= 4;
        }
    }
    
    public static Class AP(final String s) {
        boolean b = false;
        try {
            final String s2 = FK.TT.get(UT(s));
            final Class<?> forName = Class.forName((s2 != null) ? s2 : s);
            b = true;
            if (s2 != null) {}
            return forName;
        }
        finally {
            if (!b) {
                System.err.println("*** Class.forName(\"" + s + "\") failed");
            }
        }
    }
    
    public static String UT(final String s) {
        final byte[] array = new byte[s.length()];
        s.getBytes(0, array.length, array, 0);
        return UT(array);
    }
    
    private static String UT(final byte[] array) {
        final int length = array.length;
        final byte[] array2 = new byte[(length + 1 + 8 + 63) / 64 * 64 - length];
        array2[0] = -128;
        final long n = length * 8L;
        for (int i = 0; i < 8; ++i) {
            array2[array2.length - 1 - i] = (byte)(0xFFL & n >> i * 8);
        }
        int n2 = 1732584193;
        int n3 = -271733879;
        int n4 = -1732584194;
        int n5 = 271733878;
        int n6 = -1009589776;
        final int[] array3 = new int[80];
        final int n7 = length + array2.length;
        int j = 0;
        while (j < n7) {
            for (int k = 0; k < 16; ++k) {
                int n8 = 0;
                for (int l = 0; l < 4; ++l) {
                    n8 = (n8 << 8 | (0xFF & ((j < length) ? array[j] : array2[j - length])));
                    ++j;
                }
                array3[k] = n8;
            }
            for (int n9 = 16; n9 < 80; ++n9) {
                final int n10 = array3[n9 - 3] ^ array3[n9 - 8] ^ array3[n9 - 14] ^ array3[n9 - 16];
                array3[n9] = (n10 << 1 | n10 >>> -1);
            }
            int n11 = n2;
            int n12 = n3;
            int n13 = n4;
            int n14 = n5;
            int n15 = n6;
            for (int n16 = 0; n16 < 20; ++n16) {
                final int n17 = (n11 << 5 | n11 >>> -5) + ((n12 & n13) | (~n12 & n14)) + n15 + array3[n16] + 1518500249;
                n15 = n14;
                n14 = n13;
                n13 = (n12 << 30 | n12 >>> -30);
                n12 = n11;
                n11 = n17;
            }
            for (int n18 = 20; n18 < 40; ++n18) {
                final int n19 = (n11 << 5 | n11 >>> -5) + (n12 ^ n13 ^ n14) + n15 + array3[n18] + 1859775393;
                n15 = n14;
                n14 = n13;
                n13 = (n12 << 30 | n12 >>> -30);
                n12 = n11;
                n11 = n19;
            }
            for (int n20 = 40; n20 < 60; ++n20) {
                final int n21 = (n11 << 5 | n11 >>> -5) + ((n12 & n13) | (n12 & n14) | (n13 & n14)) + n15 + array3[n20] - 1894007588;
                n15 = n14;
                n14 = n13;
                n13 = (n12 << 30 | n12 >>> -30);
                n12 = n11;
                n11 = n21;
            }
            for (int n22 = 60; n22 < 80; ++n22) {
                final int n23 = (n11 << 5 | n11 >>> -5) + (n12 ^ n13 ^ n14) + n15 + array3[n22] - 899497514;
                n15 = n14;
                n14 = n13;
                n13 = (n12 << 30 | n12 >>> -30);
                n12 = n11;
                n11 = n23;
            }
            n2 += n11;
            n3 += n12;
            n4 += n13;
            n5 += n14;
            n6 += n15;
        }
        final StringBuffer sb = new StringBuffer(40);
        YS(sb, n2);
        YS(sb, n3);
        YS(sb, n4);
        YS(sb, n5);
        YS(sb, n6);
        return sb.toString();
    }
    
    private static Hashtable ST() {
        final Hashtable<String, String> hashtable = new Hashtable<String, String>();
        final StringTokenizer stringTokenizer = new StringTokenizer("C0907A93D07CFA6978DF23AE30B50CF6C77A5247=EK\tB2C7C0CAA10A0CCA5EA7D69E54018AE0C0389DD6=JK\tD672995A14650D0E018026B64F297663D8C71C8D=KK\tB78A6D1C8191C80CD634E3AD14AE6317324DA3AD=IK\t95F92B2F0CB530542D16D90A6C2AF59E20759430=DK\tD016D19D375FF21EF20557AEDFC0028212366231=HK", "\t");
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            final int index = nextToken.indexOf(61);
            if (index > 0) {
                hashtable.put(nextToken.substring(0, index), nextToken.substring(index + 1));
            }
        }
        return hashtable;
    }
}
