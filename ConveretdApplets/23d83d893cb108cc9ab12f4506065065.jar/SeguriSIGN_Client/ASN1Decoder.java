// 
// Decompiled by Procyon v0.5.30
// 

package SeguriSIGN_Client;

public class ASN1Decoder
{
    public String tag;
    public int len;
    public int lenOfLen;
    public byte[] value;
    public byte[] remaining;
    
    public ASN1Decoder(final byte[] array) {
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
        else if (array[n] == 19) {
            this.tag = "PrintableString";
        }
        else if (array[n] == 23) {
            this.tag = "UTCTime";
        }
        else {
            if (byte2Int(array[n]) != 160) {
                this.tag = "unknown tag";
                this.lenOfLen = 0;
                this.len = 0;
                this.value = null;
                this.remaining = null;
                return;
            }
            this.tag = "EXPLICIT";
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
    
    public String getTag() {
        return this.tag;
    }
    
    public int getLength() {
        return this.len;
    }
    
    public byte[] getValue() {
        return this.value;
    }
    
    public byte[] getRemaining() {
        return this.value;
    }
    
    public void printRemaining() {
        System.out.println(ByteArraytoHexString(this.remaining));
    }
    
    public void printValue() {
        System.out.println(ByteArraytoHexString(this.value));
    }
    
    public static String ByteArraytoHexString(final byte[] array) {
        final StringBuffer sb = new StringBuffer();
        for (int length = array.length, i = 0; i < length; ++i) {
            if (i % 16 == 0) {
                sb.append("\n");
            }
            byte2hex(array[i], sb);
            if (i < length - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }
    
    public static void byte2hex(final byte b, final StringBuffer sb) {
        final char[] array = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        final int n = (b & 0xF0) >> 4;
        final byte b2 = (byte)(b & 0xF);
        sb.append(array[n]);
        sb.append(array[b2]);
    }
    
    public static String byte2hex(final byte b) {
        final String[] array = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F" };
        return array[(b & 0xF0) >> 4] + array[b & 0xF];
    }
    
    public static int byte2Int(final byte b) {
        if (b >= 0) {
            return b;
        }
        return (b & 0x7F) + 128;
    }
}
