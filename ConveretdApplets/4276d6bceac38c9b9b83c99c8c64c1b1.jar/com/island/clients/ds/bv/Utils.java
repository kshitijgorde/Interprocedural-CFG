// 
// Decompiled by Procyon v0.5.30
// 

package com.island.clients.ds.bv;

import java.io.IOException;
import java.io.InputStream;

public class Utils
{
    public static String commaFormat(long l) {
        if (l == 0L) {
            return "0";
        }
        final char[] ac = new char[30];
        int i = 30;
        final byte byte0 = 30;
        int j = 3;
        for (long l2 = 0L; l != 0L; l = l2) {
            if (j == 0) {
                ac[--i] = ',';
                j = 2;
            }
            else {
                --j;
            }
            l2 = l / 10L;
            final int k = (int)(l - l2 * 10L);
            ac[--i] = Character.forDigit(k, 10);
        }
        return new String(ac, i, byte0 - i);
    }
    
    public static String timeString(final int i) {
        final int j = i / 3600;
        final int k = (i - j * 3600) / 60;
        final int l = i - j * 3600 - k * 60;
        final String s = String.valueOf(j) + ((k >= 10) ? ":" : ":0") + k + ((l >= 10) ? ":" : ":0") + l;
        return s;
    }
    
    public static String priceString(final long price) {
        final String frac = "0000" + price % 10000L;
        return String.valueOf(price / 10000L) + "." + frac.substring(frac.length() - 4);
    }
    
    public static long readNum(final InputStream inputstream, final int size) throws IOException {
        long num = 0L;
        for (int i = 0; i < size; ++i) {
            num += readByte(inputstream) << i * 8;
        }
        return num;
    }
    
    public static final int readByte(final InputStream inputstream) throws IOException {
        final int i = inputstream.read();
        if (i < 0) {
            throw new IOException("Self generated IOException on negative read() return");
        }
        return i;
    }
}
