// 
// Decompiled by Procyon v0.5.30
// 

package com.island.clients.ds.bv;

public class AppletPriceConverter
{
    public static final int NUMBER_TOO_LARGE = 12480000;
    static final String DEFAULT_ROUNDING_INDICATOR = "#";
    static final String DEFAULT_NON_ROUNDING_INDICATOR = " ";
    public static String[] lookupDecimalStringRoundDown;
    public static String[] lookupDecimalStringRoundUp;
    
    static {
        AppletPriceConverter.lookupDecimalStringRoundDown = new String[1248];
        AppletPriceConverter.lookupDecimalStringRoundUp = new String[1248];
        int i = 0;
        long l = 0L;
        long l2 = 0L;
        final byte[] abyte0 = new byte[4];
        do {
            if (l2 < l) {
                final long l3 = l2;
                final int j = (int)(l3 / 10000L);
                if (l3 - j * 10000L == 0L) {
                    long2bytes(j, abyte0, 0, 4);
                    AppletPriceConverter.lookupDecimalStringRoundDown[i] = String.valueOf(new String(abyte0)) + " ";
                    AppletPriceConverter.lookupDecimalStringRoundUp[i] = AppletPriceConverter.lookupDecimalStringRoundDown[i];
                }
                else {
                    long2bytes(j, abyte0, 0, 4);
                    AppletPriceConverter.lookupDecimalStringRoundDown[i] = String.valueOf(new String(abyte0)) + "#";
                    long2bytes(j + 1, abyte0, 0, 4);
                    AppletPriceConverter.lookupDecimalStringRoundUp[i] = String.valueOf(new String(abyte0)) + "#";
                }
                l2 += 390625L;
            }
            else {
                final long l4 = l;
                long2bytes(l4 / 10000L, abyte0, 0, 4);
                AppletPriceConverter.lookupDecimalStringRoundDown[i] = String.valueOf(new String(abyte0)) + " ";
                AppletPriceConverter.lookupDecimalStringRoundUp[i] = AppletPriceConverter.lookupDecimalStringRoundDown[i];
                if (l2 == l) {
                    l2 += 390625L;
                }
                l += 100000L;
            }
        } while (++i < 1248);
    }
    
    static void long2bytes(long l, final byte[] abyte0, final int i, int j) {
        while (j-- > 0) {
            final long l2 = l / 10L;
            final byte byte0 = (byte)(l - l2 * 10L);
            abyte0[i + j] = (byte)(48 + byte0);
            l = l2;
        }
    }
    
    static String priceString(final int i, final boolean flag) {
        final StringBuffer stringbuffer = new StringBuffer();
        final int j = i / 1248;
        final int k = i - j * 1248;
        stringbuffer.append(j);
        stringbuffer.append('.');
        if (flag) {
            stringbuffer.append(AppletPriceConverter.lookupDecimalStringRoundUp[k]);
        }
        else {
            stringbuffer.append(AppletPriceConverter.lookupDecimalStringRoundDown[k]);
        }
        return stringbuffer.toString();
    }
}
