// 
// Decompiled by Procyon v0.5.30
// 

package JAVACharter.a;

import java.io.DataInputStream;
import JAVACharter.util.a;
import java.net.URLEncoder;
import JAVACharter.util.b;
import java.util.Hashtable;
import java.util.Date;
import java.net.URL;

public class e
{
    String a;
    int if;
    int do;
    
    public e(final URL url) {
        this.if = 0;
        this.do = -1;
        this.a = url.toString();
    }
    
    public void a(final int if1) {
        this.if = if1;
    }
    
    public Hashtable a(final String s, final int n, final int n2, final Date date, final Date date2, final int n3) {
        final Hashtable<String, Date[]> hashtable = new Hashtable<String, Date[]>();
        try {
            final String string = "" + this.a + "stuff.javadatafeed?";
            String s2;
            if (n == 1) {
                s2 = string + "ver=2&freq=" + n + "&symb=" + s + "&StartExtra=" + n2 + "&LeftFill=1&RightFill=1&MockTick=1&StartDate=" + URLEncoder.encode(b.new(date)) + "&enddate=" + URLEncoder.encode(b.new(b.else(date2))) + "&datatype=" + n3;
            }
            else {
                s2 = string + "ver=2&freq=" + n + "&symb=" + s + "&StartExtra=" + n2 + "&LeftFill=1&RightFill=1&MockTick=1&StartDate=" + URLEncoder.encode(b.new(date)) + "&enddate=" + URLEncoder.encode(b.new(b.for(date2, 1))) + "&datatype=" + n3;
            }
            System.out.println("Lower: " + date + " Upper: " + date2);
            System.out.println("Requesting Data From: " + s2);
            System.out.println("Requesting Data In Memory Format");
            final DataInputStream dataInputStream = new DataInputStream(JAVACharter.util.a.a(s2));
            final int if1 = if(dataInputStream);
            if (if1 >= 0) {
                a(dataInputStream);
                final int if2 = if(dataInputStream);
                final int if3 = if(dataInputStream);
                System.out.println("Title Str Length: " + if3);
                System.out.println(a(dataInputStream, if3));
                if (n3 == 0) {
                    final Date[] array = new Date[if1];
                    final float[] array2 = new float[if1];
                    final float[] array3 = new float[if1];
                    final float[] array4 = new float[if1];
                    final float[] array5 = new float[if1];
                    final long[] array6 = new long[if1];
                    final float[] array7 = new float[if1];
                    final float[] array8 = new float[if1];
                    final float[] array9 = new float[if1];
                    final float[] array10 = new float[if1];
                    final float[] array11 = new float[if1];
                    final float[] array12 = new float[if1];
                    final float[] array13 = new float[if1];
                    final float[] array14 = new float[if1];
                    final float[] array15 = new float[if1];
                    final float[] array16 = new float[if1];
                    for (int i = 0; i < if1; ++i) {
                        array[i] = b.a(if(dataInputStream));
                    }
                    for (int j = 0; j < if1; ++j) {
                        array2[j] = (float)do(dataInputStream);
                        if (array2[j] == 0.0f) {
                            array2[j] = 9.223372E18f;
                        }
                    }
                    for (int k = 0; k < if1; ++k) {
                        array3[k] = (float)do(dataInputStream);
                        if (array3[k] == 0.0f) {
                            array3[k] = 9.223372E18f;
                        }
                    }
                    for (int l = 0; l < if1; ++l) {
                        array4[l] = (float)do(dataInputStream);
                        if (array4[l] == 0.0f) {
                            array4[l] = 9.223372E18f;
                        }
                    }
                    for (int n4 = 0; n4 < if1; ++n4) {
                        array5[n4] = (float)do(dataInputStream);
                        if (array5[n4] == 0.0f) {
                            array5[n4] = 9.223372E18f;
                        }
                    }
                    for (int n5 = 0; n5 < if1; ++n5) {
                        array6[n5] = (long)do(dataInputStream);
                        if (array6[n5] == 0.0f) {
                            array6[n5] = Long.MAX_VALUE;
                        }
                    }
                    for (int n6 = 0; n6 < if1; ++n6) {
                        if (if2 > 7) {
                            array7[n6] = (float)do(dataInputStream);
                        }
                        else {
                            array7[n6] = 9.223372E18f;
                        }
                    }
                    for (int n7 = 0; n7 < if1; ++n7) {
                        if (if2 > 7) {
                            array8[n7] = (float)do(dataInputStream);
                        }
                        else {
                            array8[n7] = 9.223372E18f;
                        }
                    }
                    for (int n8 = 0; n8 < if1; ++n8) {
                        if (if2 > 7) {
                            array9[n8] = (float)do(dataInputStream);
                        }
                        else {
                            array9[n8] = 9.223372E18f;
                        }
                    }
                    for (int n9 = 0; n9 < if1; ++n9) {
                        if (if2 > 7) {
                            array10[n9] = (float)do(dataInputStream);
                        }
                        else {
                            array10[n9] = 9.223372E18f;
                        }
                    }
                    for (int n10 = 0; n10 < if1; ++n10) {
                        if (if2 > 7) {
                            array11[n10] = (float)do(dataInputStream);
                        }
                        else {
                            array11[n10] = 9.223372E18f;
                        }
                    }
                    for (int n11 = 0; n11 < if1; ++n11) {
                        if (if2 > 7) {
                            array12[n11] = (float)do(dataInputStream);
                        }
                        else {
                            array12[n11] = 9.223372E18f;
                        }
                    }
                    for (int n12 = 0; n12 < if1; ++n12) {
                        if (if2 > 7) {
                            array13[n12] = (float)do(dataInputStream);
                        }
                        else {
                            array13[n12] = 9.223372E18f;
                        }
                    }
                    for (int n13 = 0; n13 < if1; ++n13) {
                        if (if2 > 7) {
                            array14[n13] = (float)do(dataInputStream);
                        }
                        else {
                            array14[n13] = 9.223372E18f;
                        }
                    }
                    for (int n14 = 0; n14 < if1; ++n14) {
                        if (if2 > 7) {
                            array15[n14] = (float)do(dataInputStream);
                        }
                        else {
                            array15[n14] = 9.223372E18f;
                        }
                    }
                    for (int n15 = 0; n15 < if1; ++n15) {
                        if (if2 > 7) {
                            array16[n15] = (float)do(dataInputStream);
                        }
                        else {
                            array16[n15] = 9.223372E18f;
                        }
                    }
                    hashtable.put("date", array);
                    hashtable.put("open", (Date[])array2);
                    hashtable.put("high", (Date[])array3);
                    hashtable.put("low", (Date[])array4);
                    hashtable.put("close", (Date[])array5);
                    hashtable.put("volume", (Date[])array6);
                    hashtable.put("adj", (Date[])array7);
                    hashtable.put("sharesout", (Date[])array8);
                    hashtable.put("rollingearnings", (Date[])array9);
                    hashtable.put("shortints", (Date[])array10);
                    hashtable.put("earnings", (Date[])array11);
                    hashtable.put("earningsdelta", (Date[])array12);
                    hashtable.put("dividend", (Date[])array13);
                    hashtable.put("rollingdividends", (Date[])array14);
                    hashtable.put("bids", (Date[])array15);
                    hashtable.put("asks", (Date[])array16);
                }
                else if (n3 == 1) {
                    final Date[] array17 = new Date[if1];
                    final long[] array18 = new long[if1];
                    final long[] array19 = new long[if1];
                    final long[] array20 = new long[if1];
                    final long[] array21 = new long[if1];
                    final float[] array22 = new float[if1];
                    final float[] array23 = new float[if1];
                    for (int n16 = 0; n16 < if1; ++n16) {
                        array17[n16] = b.a(if(dataInputStream));
                    }
                    for (int n17 = 0; n17 < if1; ++n17) {
                        array18[n17] = if(dataInputStream);
                    }
                    for (int n18 = 0; n18 < if1; ++n18) {
                        array19[n18] = if(dataInputStream);
                    }
                    for (int n19 = 0; n19 < if1; ++n19) {
                        array20[n19] = if(dataInputStream);
                    }
                    for (int n20 = 0; n20 < if1; ++n20) {
                        array21[n20] = if(dataInputStream);
                    }
                    for (int n21 = 0; n21 < if1; ++n21) {
                        array22[n21] = (float)do(dataInputStream);
                    }
                    for (int n22 = 0; n22 < if1; ++n22) {
                        array23[n22] = (float)do(dataInputStream);
                    }
                    hashtable.put("date", array17);
                    hashtable.put("adv", (Date[])array18);
                    hashtable.put("dec", (Date[])array19);
                    hashtable.put("unch", (Date[])array20);
                    hashtable.put("countactive", (Date[])array21);
                    hashtable.put("voladv", (Date[])array22);
                    hashtable.put("voldec", (Date[])array23);
                }
            }
            else if (if1 == -1) {
                hashtable.put("error", (Date[])new String[1]);
            }
            else {
                hashtable.put("error", (Date[])new String[1]);
            }
        }
        catch (Exception ex) {
            hashtable.put("error", (Date[])new String[1]);
            System.out.println("ChartDataFetcher Exception: " + ex);
            ex.printStackTrace(System.out);
        }
        return hashtable;
    }
    
    public static int if(final DataInputStream dataInputStream) {
        int n = 0;
        try {
            for (int i = 0; i < 4; ++i) {
                n += dataInputStream.readUnsignedByte() << i * 8;
            }
        }
        catch (Exception ex) {
            System.out.println("GetInt: " + ex);
            ex.printStackTrace(System.out);
            n = -1;
        }
        return n;
    }
    
    public static double do(final DataInputStream dataInputStream) {
        long n = 0L;
        try {
            for (int i = 0; i < 8; ++i) {
                n += dataInputStream.readUnsignedByte() << i * 8;
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return Double.longBitsToDouble(n);
    }
    
    public static long a(final DataInputStream dataInputStream) {
        long n = 0L;
        try {
            for (int i = 0; i < 8; ++i) {
                n += dataInputStream.readUnsignedByte() << i * 8;
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return n;
    }
    
    public static String a(final DataInputStream dataInputStream, final int n) {
        final byte[] array = new byte[n];
        try {
            for (int i = 0; i < n; ++i) {
                array[i] = dataInputStream.readByte();
                System.out.println("Just read a byte: " + (char)array[i]);
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        final String s = new String(array);
        System.out.println("Returning: " + s);
        return s;
    }
}
