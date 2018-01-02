// 
// Decompiled by Procyon v0.5.30
// 

package seguriSign_Client;

import java.io.ByteArrayOutputStream;

public class Base64
{
    public static byte[] BaseTable2;
    
    public String encode(final byte[] array, final int n) throws OutOfMemoryError {
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
    
    public byte[] decode(final byte[] array) {
        final String s = new String();
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (array == null) {
            return null;
        }
        final int length = array.length;
        final byte[] array2 = new byte[3];
        for (int n = length / 4 * 4, i = 0; i < n; i += 4) {
            array[i] = aux_decode(array[i]);
            array[i + 1] = aux_decode(array[i + 1]);
            if (array[i + 2] == 61) {
                array[i + 2] = 0;
            }
            else {
                array[i + 2] = aux_decode(array[i + 2]);
            }
            if (array[i + 3] == 61) {
                array[i + 3] = 0;
            }
            else {
                array[i + 3] = aux_decode(array[i + 3]);
            }
            array2[0] = (byte)(array[i] << 2 | (array[i + 1] << 2 & 0xC0) >>> 6);
            array2[1] = (byte)(array[i + 1] << 4 | (array[i + 2] << 2 & 0xF0) >>> 4);
            array2[2] = (byte)(array[i + 2] << 6 | (array[i + 3] & 0x3F));
            byteArrayOutputStream.write(array2[0]);
            byteArrayOutputStream.write(array2[1]);
            byteArrayOutputStream.write(array2[2]);
        }
        return byteArrayOutputStream.toByteArray();
    }
    
    protected String printBinary(final byte b) {
        String s = "";
        for (int i = 7; i >= 0; --i) {
            if ((1 << i & b) != 0x0) {
                s += "1";
            }
            else {
                s += "0";
            }
        }
        return s;
    }
    
    protected static byte aux_decode(final byte b) {
        if (b >= 65 && b <= 90) {
            return (byte)(b - 65);
        }
        if (b >= 97 && b <= 122) {
            return (byte)(b - 71);
        }
        if (b >= 48 && b <= 57) {
            return (byte)(b + 4);
        }
        if (b == 43) {
            return 62;
        }
        return 63;
    }
    
    static {
        Base64.BaseTable2 = new byte[] { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47 };
    }
}
