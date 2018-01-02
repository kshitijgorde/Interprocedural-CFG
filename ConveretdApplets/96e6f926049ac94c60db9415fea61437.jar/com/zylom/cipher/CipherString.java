// 
// Decompiled by Procyon v0.5.30
// 

package com.zylom.cipher;

import java.util.zip.CRC32;

public class CipherString extends CipherObject
{
    private static final CipherString cipherObject;
    private static final char[] ALPHA_DIGITS;
    private static final char[] HEX_DIGITS;
    private static final CipherString instance;
    private String value;
    
    public CipherString() {
        this("");
    }
    
    public CipherString(final String value) {
        this.setValue(value);
    }
    
    public String getValue() {
        String concat = "";
        for (int i = 0; i < this.value.length(); ++i) {
            concat = String.valueOf(String.valueOf(concat)).concat(String.valueOf(String.valueOf((char)(this.value.charAt(i) ^ (char)super.state[(super.currentCryptIndex + i) % 256]))));
        }
        return concat;
    }
    
    public void setValue(final String s) {
        super.currentCryptIndex = (int)(super.random.nextFloat() * 256.0f);
        this.value = "";
        for (int i = 0; i < s.length(); ++i) {
            this.value = String.valueOf(String.valueOf(this.value)).concat(String.valueOf(String.valueOf((char)(s.charAt(i) ^ (char)super.state[(super.currentCryptIndex + i) % 256]))));
        }
    }
    
    public static String cipher(final String s) {
        return cipher(s, 0);
    }
    
    public static String cipher(final String s, int abs) {
        if (abs < 0) {
            abs = Math.abs(abs);
        }
        final byte[] bytes = s.getBytes();
        final byte[] array = new byte[bytes.length];
        for (int i = 0; i < s.length(); ++i) {
            array[i] = (byte)(bytes[i] ^ (CipherObject.staticState[(i + abs) % 256] & 0x7F));
        }
        return new String(array);
    }
    
    public String encrypt(final String s, final byte[] array, int abs) {
        if (abs < 0) {
            abs = Math.abs(abs);
        }
        this.init(array);
        final byte[] bytes = s.getBytes();
        final byte[] array2 = new byte[bytes.length];
        for (int i = 0; i < s.length(); ++i) {
            array2[i] = (byte)(bytes[i] ^ (super.state[(i + abs) % 256] & 0x7F));
        }
        final CRC32 crc32 = new CRC32();
        crc32.update(array2);
        return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(new String(array2)))).append("C").append(String.valueOf(crc32.getValue()))));
    }
    
    public String encrypt(final String s, int abs) {
        if (abs < 0) {
            abs = Math.abs(abs);
        }
        this.init(CipherObject.superSeed);
        final byte[] bytes = s.getBytes();
        final byte[] array = new byte[bytes.length];
        final CRC32 crc32 = new CRC32();
        crc32.update(bytes);
        for (int i = 0; i < s.length(); ++i) {
            array[i] = (byte)(bytes[i] ^ (super.state[(i + abs) % 256] & 0x7F));
        }
        return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(new String(array)))).append("C").append(String.valueOf(crc32.getValue()))));
    }
    
    public String decrypt(String substring, final String s, int abs) {
        if (abs < 0) {
            abs = Math.abs(abs);
        }
        this.init(s);
        final int lastIndex = substring.lastIndexOf(67);
        if (lastIndex >= 0) {
            try {
                final long long1 = Long.parseLong(substring.substring(lastIndex + 1));
                substring = substring.substring(0, lastIndex);
                final byte[] bytes = substring.getBytes();
                final byte[] array = new byte[bytes.length];
                for (int i = 0; i < substring.length(); ++i) {
                    array[i] = (byte)(bytes[i] ^ (super.state[(i + abs) % 256] & 0x7F));
                }
                final CRC32 crc32 = new CRC32();
                crc32.update(array);
                if (crc32.getValue() == long1) {
                    return new String(array);
                }
            }
            catch (Exception ex) {
                return null;
            }
        }
        return null;
    }
    
    public String decrypt(String substring, final byte[] array, int abs) {
        if (abs < 0) {
            abs = Math.abs(abs);
        }
        this.init(array);
        final int lastIndex = substring.lastIndexOf(67);
        if (lastIndex >= 0) {
            try {
                final long long1 = Long.parseLong(substring.substring(lastIndex + 1));
                substring = substring.substring(0, lastIndex);
                final byte[] bytes = substring.getBytes();
                final byte[] array2 = new byte[bytes.length];
                for (int i = 0; i < substring.length(); ++i) {
                    array2[i] = (byte)(bytes[i] ^ (super.state[(i + abs) % 256] & 0x7F));
                }
                final CRC32 crc32 = new CRC32();
                crc32.update(array2);
                if (crc32.getValue() == long1) {
                    return new String(array2);
                }
            }
            catch (Exception ex) {
                return null;
            }
        }
        return null;
    }
    
    public static String alpha16Encode(final String s) {
        final byte[] bytes = s.getBytes();
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; ++i) {
            int n = bytes[i];
            if (n < 0) {
                n += 256;
            }
            sb.append(CipherString.ALPHA_DIGITS[n / 16]);
            sb.append(CipherString.ALPHA_DIGITS[n % 16]);
        }
        return sb.toString();
    }
    
    public static String alpha16Decode(final String s) {
        final byte[] array = new byte[s.length() / 2];
        for (int i = 0; i < array.length; ++i) {
            final char char1 = s.charAt(2 * i);
            final char char2 = s.charAt(2 * i + 1);
            int n = 0;
            int n2 = 0;
            for (int j = 0; j < 16; ++j) {
                if (CipherString.ALPHA_DIGITS[j] == char1) {
                    n = j;
                }
                if (CipherString.ALPHA_DIGITS[j] == char2) {
                    n2 = j;
                }
            }
            array[i] = (byte)(n * 16 + n2);
        }
        return new String(array);
    }
    
    public static String hexEncode(final String s) {
        final byte[] bytes = s.getBytes();
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; ++i) {
            int n = bytes[i];
            if (n < 0) {
                n += 256;
            }
            sb.append(CipherString.HEX_DIGITS[n / 16]);
            sb.append(CipherString.HEX_DIGITS[n % 16]);
        }
        return sb.toString();
    }
    
    public static String hexDecode(final String s) {
        final byte[] array = new byte[s.length() / 2];
        for (int i = 0; i < array.length; ++i) {
            array[i] = (byte)(Integer.parseInt(s.substring(2 * i, 2 * i + 2), 16) & 0xFF);
        }
        return new String(array);
    }
    
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
    
    static {
        cipherObject = new CipherString();
        ALPHA_DIGITS = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'K', 'M', 'N', 'P', 'W', 'R', 'S', 'T' };
        HEX_DIGITS = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        instance = new CipherString();
    }
}
