// 
// Decompiled by Procyon v0.5.30
// 

package com.solutionslab.stocktrader.e2e.encryptor;

import java.util.Vector;

public class Encryptor
{
    public static final int INVALID_HEX_CHAR_ERROR = 1;
    public static final int NOT_VALID_HEX_CHARACTER = -1;
    public static final int NO_HEX_CONVERSION_ERRORS = 0;
    private static final int ERR_NO_ERROR = 0;
    private static final int HEX_RADIX = 16;
    private String C_String;
    private String P_String;
    
    public Encryptor() {
        this.C_String = "";
        this.P_String = "";
    }
    
    public void Encryptor() {
    }
    
    public int encodeAndEncryptUserLoginMsg(final String s, final String s2) {
        try {
            final OAEPEncodedMessage oaepEncodedMessage = new OAEPEncodedMessage(new PINMessage(new PINBlock(s), s2));
            this.P_String = oaepEncodedMessage.getEncodingParameter();
            this.C_String = convertHexArrayToString(new EncryptedMessage(oaepEncodedMessage).getBytes());
            return 0;
        }
        catch (PINBlockException ex) {
            return this.getErrorCode(ex.getMessage());
        }
        catch (PINMessageException ex2) {
            return this.getErrorCode(ex2.getMessage());
        }
        catch (EncryptedMessageException ex3) {
            return this.getErrorCode(ex3.getMessage());
        }
        catch (OAEPEncodedMsgException ex4) {
            return this.getErrorCode(ex4.getMessage());
        }
    }
    
    public int encodeAndEncryptChangePINMsg(final String s, final String s2, final String s3) {
        try {
            final OAEPEncodedMessage oaepEncodedMessage = new OAEPEncodedMessage(new PINMessage(new PINBlock(s), new PINBlock(s2), s3));
            this.P_String = oaepEncodedMessage.getEncodingParameter();
            this.C_String = convertHexArrayToString(new EncryptedMessage(oaepEncodedMessage).getBytes());
            return 0;
        }
        catch (PINBlockException ex) {
            return this.getErrorCode(ex.getMessage());
        }
        catch (PINMessageException ex2) {
            return this.getErrorCode(ex2.getMessage());
        }
        catch (EncryptedMessageException ex3) {
            return this.getErrorCode(ex3.getMessage());
        }
        catch (OAEPEncodedMsgException ex4) {
            return this.getErrorCode(ex4.getMessage());
        }
    }
    
    public String getEncryptedUserLoginMsg() {
        return this.C_String;
    }
    
    public String getEncodingParameter() {
        return this.P_String;
    }
    
    private int getErrorCode(final String s) {
        final String s2 = "Error no : ";
        final int n = 2;
        final int n2 = s.indexOf(s2) + s2.length();
        final int int1 = Integer.parseInt(s.substring(n2, n2 + n));
        System.out.println(s);
        return int1;
    }
    
    public static void fillByteArray(final byte[] array, final int n) {
        for (int length = array.length, i = 0; i < length; ++i) {
            array[i] = (byte)n;
        }
    }
    
    public static void convertAsciiArrayToHexByteArray(final byte[] array, final byte[] array2, final int n, final int n2) {
        final int n3 = (n2 + 1) / 2;
        int i = 0;
        int n4 = 0;
        while (i < n3) {
            int n5;
            if (n4 < n2 - 1) {
                n5 = ((array[n4] & 0xF) << 4 | (array[n4 + 1] & 0xF));
            }
            else {
                n5 = ((array2[i + n] & 0xF) | (array[n4] & 0xF) << 4);
            }
            array2[i + n] = (byte)n5;
            n4 += 2;
            ++i;
        }
    }
    
    public static String convertHexArrayToString(final byte[] array) {
        final StringBuffer sb = new StringBuffer();
        for (int length = array.length, i = 0; i < length; ++i) {
            sb.append(Character.toUpperCase(forDigit((array[i] & 0xF0) >> 4, 16)));
            sb.append(Character.toUpperCase(forDigit(array[i] & 0xF, 16)));
        }
        return new String(sb);
    }
    
    public static int convertStringToPackedHexByteArray(final String s, final byte[] array, final int n) {
        for (int length = s.length(), i = 0, n2 = 0; i < length; ++i, ++n2) {
            final int digit = Character.digit(s.charAt(i), 16);
            if (digit == -1) {
                return 1;
            }
            final int n3 = digit << 4;
            if (++i < length) {
                final int digit2 = Character.digit(s.charAt(i), 16);
                if (digit2 == -1) {
                    return 1;
                }
                array[n2 + n] = (byte)(n3 | digit2);
            }
            else {
                array[n2 + n] = (byte)(n3 | 0xF);
            }
        }
        return 0;
    }
    
    public static void convertStringtoHexByteArray(final String s, final byte[] array) {
        for (int length = s.length(), i = 0; i < length; ++i) {
            array[i] = (byte)s.charAt(i);
        }
    }
    
    public static String convertHexByteVectorToString(final Vector vector) {
        final StringBuffer sb = new StringBuffer();
        for (int size = vector.size(), i = 0; i < size; ++i) {
            final Byte b = vector.elementAt(i);
            sb.append(Character.toUpperCase(forDigit((b & 0xF0) >> 4, 16)));
            sb.append(Character.toUpperCase(forDigit(b & 0xF, 16)));
        }
        return new String(sb);
    }
    
    public static char forDigit(final int n, final int n2) {
        if (n >= n2 || n < 0) {
            return '\0';
        }
        if (n2 < 2 || n2 > 36) {
            return '\0';
        }
        if (n < 10) {
            return (char)(48 + n);
        }
        return (char)(87 + n);
    }
}
