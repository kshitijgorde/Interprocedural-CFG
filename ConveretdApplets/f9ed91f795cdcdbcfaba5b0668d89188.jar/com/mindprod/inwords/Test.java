// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.inwords;

import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public final class Test
{
    public static void test(final ToWords language) {
        test(language, new DecimalCommas());
    }
    
    public static void test(final ToWords language, final ToWords numeric) {
        try {
            final OutputStreamWriter eosw = new OutputStreamWriter(System.out, "UTF-8");
            final PrintWriter prw = new PrintWriter(eosw, false);
            prw.println();
            final long[][] arr$;
            final long[][] range = arr$ = new long[][] { { -1L, 135L }, { 199L, 235L }, { 299L, 302L }, { 399L, 402L }, { 499L, 502L }, { 599L, 602L }, { 699L, 702L }, { 799L, 802L }, { 899L, 902L }, { 999L, 1035L }, { 1099L, 1135L }, { 1199L, 1235L }, { 1969L, 2002L }, { 2099L, 2102L }, { 2199L, 2202L }, { 2999L, 3002L }, { 3099L, 3102L }, { 3199L, 3202L }, { 3999L, 4002L }, { 4099L, 4102L }, { 4199L, 4202L }, { 4999L, 5002L }, { 5099L, 5102L }, { 5199L, 5202L }, { 5999L, 6002L }, { 6099L, 6102L }, { 6199L, 6202L }, { 6999L, 7002L }, { 7099L, 7102L }, { 7199L, 7202L }, { 7999L, 8002L }, { 8099L, 8102L }, { 8199L, 8202L }, { 8999L, 9002L }, { 9099L, 9102L }, { 9199L, 9202L }, { 9999L, 10035L }, { 21199L, 21235L }, { 99999L, 100001L }, { 999999L, 1000001L }, { 9999999L, 10000001L }, { 20999999L, 21000001L }, { 99999999L, 100000002L }, { 100001999L, 100002002L }, { 100031999L, 100032002L }, { 100100999L, 100101002L }, { 100199999L, 100200002L }, { 100219999L, 100220002L }, { 123456789L, 123456789L }, { 999999999L, 1000000002L }, { 1199999999L, 1200000002L }, { 9999999999L, 10000000002L }, { 99999999999L, 100000000002L }, { 999999999999L, 1000000000002L }, { 9999999999999L, 10000000000002L }, { 99999999999999L, 100000000000002L }, { 999999999999999L, 1000000000000002L }, { 9999999999999999L, 10000000000000002L }, { 99999999999999999L, 100000000000000002L }, { 999999999999999999L, 1000000000000000002L }, { 9223372036854775805L, 9223372036854775806L }, { -9223372036854775807L, -9223372036854775805L }, { -12345L, -12345L }, { -123456L, -123456L }, { -1234567L, -1234567L }, { -12345678L, -12345678L }, { -123456789L, -123456789L } };
            for (final long[] aRange : arr$) {
                final long from = aRange[0];
                for (long to = aRange[1], i = from; i <= to; ++i) {
                    final String words = language.toWords(i);
                    if (words.indexOf("  ") >= 0) {
                        prw.println("\u0007OOps! double space!");
                    }
                    String numerics;
                    if (numeric == null) {
                        numerics = Long.toString(i);
                    }
                    else {
                        numerics = numeric.toWords(i);
                    }
                    prw.println(numerics + " " + words);
                }
            }
            prw.close();
        }
        catch (UnsupportedEncodingException e) {
            System.err.println("nissing support for UTF-8");
        }
    }
}
