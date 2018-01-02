// 
// Decompiled by Procyon v0.5.30
// 

package seguriSign_Client;

public class ASN1
{
    public String tag;
    public int len;
    public int lenOfLen;
    public byte[] value;
    public byte[] remaining;
    
    public ASN1(final byte[] array) {
        if (array == null) {
            this.tag = null;
            this.lenOfLen = 0;
            this.len = 0;
            this.value = null;
            this.remaining = null;
            return;
        }
        int n = 0;
        if (array[n] == 48) {
            this.tag = "SEQUENCE";
        }
        else if (array[n] == 49) {
            this.tag = "SET";
        }
        else if (array[n] == 1) {
            this.tag = "BOOLEAN";
        }
        else if (array[n] == 2) {
            this.tag = "INTEGER";
        }
        else if (array[n] == 3) {
            this.tag = "BIT STRING";
        }
        else if (array[n] == 4) {
            this.tag = "OCTET STRING";
        }
        else if (array[n] == 5) {
            this.tag = "NULL";
        }
        else if (array[n] == 6) {
            this.tag = "OBJECT IDENTIFIER";
        }
        else if (array[n] == 10) {
            this.tag = "ENUMERATED";
        }
        else if (array[n] == 19) {
            this.tag = "PrintableString";
        }
        else if (array[n] == 20) {
            this.tag = "T61String";
        }
        else if (array[n] == 22) {
            this.tag = "IA5tring";
        }
        else if (array[n] == 23) {
            this.tag = "UTCTime";
        }
        else if (array[n] == 24) {
            this.tag = "GeneralizedTime";
        }
        else if (byte2Int(array[n]) == 160) {
            this.tag = "EXPLICIT";
        }
        else if (byte2Int(array[n]) == 161) {
            this.tag = "EXPLICIT";
        }
        else if (byte2Int(array[n]) == 162) {
            this.tag = "EXPLICIT";
        }
        else if (byte2Int(array[n]) == 163) {
            this.tag = "EXPLICIT";
        }
        else if (byte2Int(array[n]) == 128) {
            this.tag = "IMPLICIT";
        }
        else if (byte2Int(array[n]) == 129) {
            this.tag = "IMPLICIT";
        }
        else if (byte2Int(array[n]) == 130) {
            this.tag = "IMPLICIT";
        }
        else if (byte2Int(array[n]) == 131) {
            this.tag = "IMPLICIT";
        }
        else if (byte2Int(array[n]) == 132) {
            this.tag = "IMPLICIT";
        }
        else if (byte2Int(array[n]) == 133) {
            this.tag = "IMPLICIT";
        }
        else {
            if (byte2Int(array[n]) != 134) {
                this.tag = "unknown tag";
                this.lenOfLen = 0;
                this.len = 0;
                this.value = null;
                this.remaining = null;
                return;
            }
            this.tag = "IMPLICIT";
        }
        ++n;
        if (byte2Int(array[n]) > 127) {
            this.lenOfLen = (byte2Int(array[n]) ^ 0x80);
            ++n;
            final byte[] array2 = new byte[this.lenOfLen];
            String string = "0x";
            for (int i = 0; i < this.lenOfLen; ++i) {
                array2[i] = array[n];
                ++n;
                string += byte2hex(array2[i]);
            }
            this.len = Integer.decode(string);
            this.value = new byte[this.len];
            for (int j = 0; j < this.len; ++j) {
                this.value[j] = array[n];
                ++n;
            }
            this.remaining = new byte[array.length - n];
            for (int k = 0; k < this.remaining.length; ++k) {
                this.remaining[k] = array[n + k];
            }
        }
        else {
            this.lenOfLen = 0;
            this.len = byte2Int(array[n]);
            ++n;
            this.value = new byte[this.len];
            for (int l = 0; l < this.len; ++l) {
                this.value[l] = array[n];
                ++n;
            }
            this.remaining = new byte[array.length - n];
            for (int n2 = 0; n2 < this.remaining.length; ++n2) {
                this.remaining[n2] = array[n + n2];
            }
        }
    }
    
    public void printRemaining() {
        System.out.println(Utilities.PrintBytes(this.remaining));
    }
    
    public void printValue() {
        System.out.println(Utilities.PrintBytes(this.value));
    }
    
    private static String byte2hex(final byte b) {
        final String[] array = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F" };
        return array[(b & 0xF0) >> 4] + array[b & 0xF];
    }
    
    private static int byte2Int(final byte b) {
        if (b >= 0) {
            return b;
        }
        return (b & 0x7F) + 128;
    }
    
    public static byte[] encode(final byte[] array, final String s) {
        if (s.equalsIgnoreCase("NULL")) {
            final byte[] array2 = new byte[array.length + 1];
            array2[0] = translate(s);
            for (int i = 0; i < array.length; ++i) {
                array2[i + 1] = array[i];
            }
            return array2;
        }
        if (array != null) {
            final byte[] arrayIntToByte = ArrayIntToByte(calcula_longitud(array.length));
            final byte[] array3 = new byte[array.length + arrayIntToByte.length + 1];
            array3[0] = translate(s);
            for (int j = 0; j < arrayIntToByte.length; ++j) {
                array3[j + 1] = arrayIntToByte[j];
            }
            for (int k = 0; k < array.length; ++k) {
                array3[k + 1 + arrayIntToByte.length] = array[k];
            }
            return array3;
        }
        return new byte[0];
    }
    
    public static byte[] encode(final byte[] array, final int n) {
        if (n == 5) {
            final byte[] array2 = new byte[array.length + 1];
            array2[0] = (byte)n;
            for (int i = 0; i < array.length; ++i) {
                array2[i + 1] = array[i];
            }
            return array2;
        }
        final byte[] arrayIntToByte = ArrayIntToByte(calcula_longitud(array.length));
        final byte[] array3 = new byte[array.length + arrayIntToByte.length + 1];
        array3[0] = (byte)n;
        for (int j = 0; j < arrayIntToByte.length; ++j) {
            array3[j + 1] = arrayIntToByte[j];
        }
        for (int k = 0; k < array.length; ++k) {
            array3[k + 1 + arrayIntToByte.length] = array[k];
        }
        return array3;
    }
    
    public static byte[] encode(final byte[][] array, final String s) {
        int n = 0;
        for (int i = 0; i < array.length; ++i) {
            if (array[i] != null) {
                n += array[i].length;
            }
        }
        final byte[] arrayIntToByte = ArrayIntToByte(calcula_longitud(n));
        final byte[] array2 = new byte[n + arrayIntToByte.length + 1];
        array2[0] = translate(s);
        for (int j = 0; j < arrayIntToByte.length; ++j) {
            array2[j + 1] = arrayIntToByte[j];
        }
        int n2 = 1 + arrayIntToByte.length;
        for (int k = 0; k < array.length; ++k) {
            if (array[k] != null) {
                for (int l = 0; l < array[k].length; ++l) {
                    array2[l + n2] = array[k][l];
                }
                n2 += array[k].length;
            }
        }
        return array2;
    }
    
    public static byte[] encode(final byte[][] array, final int n) {
        int n2 = 0;
        for (int i = 0; i < array.length; ++i) {
            n2 += array[i].length;
        }
        final byte[] arrayIntToByte = ArrayIntToByte(calcula_longitud(n2));
        final byte[] array2 = new byte[n2 + arrayIntToByte.length + 1];
        array2[0] = (byte)n;
        for (int j = 0; j < arrayIntToByte.length; ++j) {
            array2[j + 1] = arrayIntToByte[j];
        }
        int n3 = 1 + arrayIntToByte.length;
        for (int k = 0; k < array.length; ++k) {
            for (int l = 0; l < array[k].length; ++l) {
                array2[l + n3] = array[k][l];
            }
            n3 += array[k].length;
        }
        return array2;
    }
    
    private static byte translate(final String s) {
        if (s.equalsIgnoreCase("BOOLEAN")) {
            return 1;
        }
        if (s.equalsIgnoreCase("INTEGER")) {
            return 2;
        }
        if (s.equalsIgnoreCase("BIT STRING")) {
            return 3;
        }
        if (s.equalsIgnoreCase("OCTET STRING")) {
            return 4;
        }
        if (s.equalsIgnoreCase("NULL")) {
            return 5;
        }
        if (s.equalsIgnoreCase("OBJECT IDENTIFIER")) {
            return 6;
        }
        if (s.equalsIgnoreCase("ENUMERATED")) {
            return 10;
        }
        if (s.equalsIgnoreCase("SEQUENCE")) {
            return 48;
        }
        if (s.equalsIgnoreCase("SET")) {
            return 49;
        }
        if (s.equalsIgnoreCase("PrintableString")) {
            return 19;
        }
        if (s.equalsIgnoreCase("T61String")) {
            return 20;
        }
        if (s.equalsIgnoreCase("IA5tring")) {
            return 22;
        }
        if (s.equalsIgnoreCase("UTCTime")) {
            return 23;
        }
        if (s.equalsIgnoreCase("GeneralizedTime")) {
            return 24;
        }
        if (s.equalsIgnoreCase("EXPLICIT")) {
            return -96;
        }
        if (s.equalsIgnoreCase("IMPLICIT")) {
            return -128;
        }
        return 0;
    }
    
    private static int[] calcula_longitud(int n) {
        final int[] array = { 0 };
        if (n < 128) {
            return new int[] { n };
        }
        if (n < 256) {
            return new int[] { 129, n };
        }
        if (n < 65536) {
            final int[] array2 = { 0, 0, n & 0xFF };
            n >>>= 8;
            array2[1] = (n & 0xFF);
            array2[0] = 130;
            return array2;
        }
        if (n < 16777216) {
            final int[] array3 = { 0, 0, 0, n & 0xFFFF };
            n >>>= 8;
            array3[2] = (n & 0xFFFF);
            n >>>= 8;
            array3[1] = (n & 0xFFFF);
            array3[0] = 131;
            return array3;
        }
        if (n <= Integer.MAX_VALUE) {
            final int[] array4 = { 0, 0, 0, 0, n & 0xFFFFFF };
            n >>>= 8;
            array4[3] = (n & 0xFFFFFF);
            n >>>= 8;
            array4[2] = (n & 0xFFFFFF);
            n >>>= 8;
            array4[1] = (n & 0xFFFFFF);
            array4[0] = 132;
            return array4;
        }
        System.out.println("No se programo el algoritmo para calcular una longitud tan grande");
        return array;
    }
    
    public static byte[] ArrayIntToByte(final int[] array) {
        final byte[] array2 = new byte[array.length];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = (byte)(array[i] & 0xFF);
        }
        return array2;
    }
}
