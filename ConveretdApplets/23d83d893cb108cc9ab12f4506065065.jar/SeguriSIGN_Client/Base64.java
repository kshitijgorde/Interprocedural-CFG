// 
// Decompiled by Procyon v0.5.30
// 

package SeguriSIGN_Client;

import java.io.ByteArrayOutputStream;

public class Base64
{
    public static byte[] BaseTable2;
    
    public String encode(final byte[] array, final int n) {
        final String s = new String();
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final int length = array.length;
        if (length < 1) {
            return null;
        }
        final byte[] array2 = new byte[4];
        final int n2 = length / 3;
        final int n3 = length % 3;
        final int n4 = n2 * 3;
        int n5 = 0;
        for (int i = 0; i < n4; i += 3) {
            array2[0] = (byte)((array[i] & 0xFC) >> 2);
            array2[1] = (byte)((array[i] & 0x3) << 4 | (array[i + 1] & 0xF0) >> 4);
            array2[2] = (byte)((array[i + 1] & 0xF) << 2 | (array[i + 2] & 0xC0) >> 6);
            array2[3] = (byte)(array[i + 2] & 0x3F);
            byteArrayOutputStream.write(Base64.BaseTable2[array2[0]]);
            byteArrayOutputStream.write(Base64.BaseTable2[array2[1]]);
            byteArrayOutputStream.write(Base64.BaseTable2[array2[2]]);
            byteArrayOutputStream.write(Base64.BaseTable2[array2[3]]);
            n5 += 4;
            if (n5 >= 64) {
                if (n == 1) {
                    byteArrayOutputStream.write(10);
                }
                n5 = 0;
            }
        }
        if (n3 == 2) {
            array2[0] = (byte)((array[n4] & 0xFC) >> 2);
            array2[1] = (byte)((array[n4] & 0x3) << 4 | (array[n4 + 1] & 0xF0) >> 4);
            array2[2] = (byte)((array[n4 + 1] & 0xF) << 2);
            n5 += 4;
            if (n5 >= 64 && n == 1) {
                byteArrayOutputStream.write(10);
            }
            byteArrayOutputStream.write(Base64.BaseTable2[array2[0]]);
            byteArrayOutputStream.write(Base64.BaseTable2[array2[1]]);
            byteArrayOutputStream.write(Base64.BaseTable2[array2[2]]);
            byteArrayOutputStream.write(61);
        }
        else if (n3 == 1) {
            array2[0] = (byte)((array[n4] & 0xFC) >> 2);
            array2[1] = (byte)((array[n4] & 0x3) << 4);
            n5 += 4;
            if (n5 >= 64 && n == 1) {
                byteArrayOutputStream.write(10);
            }
            byteArrayOutputStream.write(Base64.BaseTable2[array2[0]]);
            byteArrayOutputStream.write(Base64.BaseTable2[array2[1]]);
            byteArrayOutputStream.write(61);
            byteArrayOutputStream.write(61);
        }
        return byteArrayOutputStream.toString();
    }
    
    static {
        Base64.BaseTable2 = new byte[] { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47 };
    }
}
