// 
// Decompiled by Procyon v0.5.30
// 

package com.postx.util;

import java.io.UnsupportedEncodingException;
import java.util.Vector;
import java.util.Enumeration;
import java.util.Hashtable;

public class StringConversion
{
    public static final String Ident = "$Id: StringConversion.java,v 1.5 2009/06/12 20:34:24 blm Exp $";
    public static final String LATIN_1 = "ISO-8859-1";
    public static final Hashtable HEX_DUMP_SEPS;
    
    public static String bytesToHexString(final byte[] array) {
        return bytesToHexString(array, 0, array.length, null);
    }
    
    public static String bytesToHexString(final byte[] array, final Hashtable hashtable) {
        return bytesToHexString(array, 0, array.length, hashtable);
    }
    
    public static String bytesToHexString(final byte[] array, int n, int n2, final Hashtable hashtable) {
        if (n < 0) {
            n += array.length;
        }
        if (n2 < 0) {
            n2 += array.length;
        }
        final int n3 = n2 - n;
        if (n < 0 || n3 < 0) {
            throw new IndexOutOfBoundsException("end < start");
        }
        String[] array2 = null;
        int[] array3 = null;
        int size = 0;
        if (hashtable != null) {
            final Enumeration<Integer> keys = hashtable.keys();
            size = hashtable.size();
            array2 = new String[size];
            array3 = new int[size];
            int n4 = 0;
            while (keys.hasMoreElements()) {
                final Integer n5 = keys.nextElement();
                array3[n4] = n5;
                array2[n4] = hashtable.get(n5);
                ++n4;
            }
        }
        final StringBuffer sb = new StringBuffer(n3 * 2);
        int i = 0;
        while (i < n3) {
            final int n6 = array[i + n] & 0xFF;
            if (n6 < 16) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(n6).toUpperCase());
            ++i;
            if (size > 0) {
                int n7 = 0;
                String s = null;
                for (int j = 0; j < size; ++j) {
                    if (array3[j] > n7 && i % array3[j] == 0) {
                        s = array2[j];
                        n7 = array3[j];
                    }
                }
                if (n7 <= 0) {
                    continue;
                }
                sb.append(s);
            }
        }
        return sb.toString();
    }
    
    public static String[] split(final String s, final String s2) {
        final Vector vector = new Vector<String>();
        int i;
        int length;
        int length2;
        int index;
        for (i = 0, length = s.length(), length2 = s2.length(); i < length; i = index + length2) {
            if (length2 == 0) {
                index = i + 1;
            }
            else if ((index = s.indexOf(s2, i)) == -1) {
                break;
            }
            vector.addElement(s.substring(i, index));
        }
        if (i <= length && length2 > 0) {
            vector.addElement(s.substring(i));
        }
        final String[] array = new String[vector.size()];
        vector.copyInto(array);
        return array;
    }
    
    static {
        (HEX_DUMP_SEPS = new Hashtable()).put(new Integer(1), " ");
        StringConversion.HEX_DUMP_SEPS.put(new Integer(4), "  ");
        StringConversion.HEX_DUMP_SEPS.put(new Integer(16), System.getProperty("line.separator"));
    }
    
    public static byte[] stringToBytes(final String s) {
        try {
            return s.getBytes("ISO-8859-1");
        }
        catch (UnsupportedEncodingException ex) {
            return null;
        }
    }
    
    public static byte[] hexStringToBytes(final String s) {
        final int length = s.length();
        if (length % 2 != 0) {
            throw new IllegalArgumentException("hex string must be an even number of characters long");
        }
        final int n = length / 2;
        final byte[] array = new byte[n];
        for (int i = 0, n2 = 0; i < n; ++i, n2 += 2) {
            array[i] = (byte)Integer.parseInt(s.substring(n2, n2 + 2), 16);
        }
        return array;
    }
    
    public static boolean hasContents(final String s) {
        throw new IllegalStateException("hasContents not available");
    }
    
    public static String HTMLUnescape(final String s) {
        throw new IllegalStateException("HTMLUnescape not available");
    }
    
    public static String bytesToString(final byte[] array) {
        return bytesToString(array, 0, array.length);
    }
    
    public static String bytesToString(final byte[] array, int n, int n2) {
        if (n < 0) {
            n += array.length;
        }
        if (n2 < 0) {
            n2 += array.length;
        }
        try {
            return new String(array, n, n2 - n, "ISO-8859-1");
        }
        catch (UnsupportedEncodingException ex) {
            return null;
        }
    }
}
