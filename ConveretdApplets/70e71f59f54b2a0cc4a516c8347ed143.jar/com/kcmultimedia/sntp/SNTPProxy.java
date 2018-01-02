// 
// Decompiled by Procyon v0.5.30
// 

package com.kcmultimedia.sntp;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.TimeZone;
import java.util.Calendar;

public class SNTPProxy
{
    String a;
    int b;
    
    public SNTPProxy(final String a, final int b) {
        this.a = a;
        this.b = b;
        System.out.println(a("<4hC/\u001c4hC/\u001c4hC/\u001c4hC/\u001c4hC/\u001c4hC/\u001c4hC/\u001c4hC/\u001c4hC/\u001c\u0014hIVxJ\u0012IDfWb,sWr7\bq_q,I(\u0016H'\u001bv_q,I4\u0018/bI%\u0016>bI%\u00164HC%uq2\u0010w_y*\u001d%\u001e]kI4\u000f'{INu>\u000f\u001ciBw/\fa_\u007fbO%r{1\u0000bX>hc/\u0016J*\u0000v\u0016n#\nnWy'IlE>$\u0006w\u0016w,\u001d`Dp#\u0005%Sh#\u0005pWj+\u0006k\u0016>bC\u000f\u001c>2\u001cwFq1\fv\u0016q,\u0005|\u0018>\u000e\u0000fSp1\u0000kQ>+\u0007cYl/\bq_q,IlE>bI/<4b\bsWw.\bgZ{b\u0006kZw,\f%Wjb\u001erA0)\nhCr6\u0000hSz+\b+Uq/I%\u001c\u0014hC/\u001c4hC/\u001c4hC/\u001c4hC/\u001c4hC/\u001c4hC/\u001c4hC/\u001c4hC/\u001c4hC/\u001c4H"));
    }
    
    private double a() {
        final Calendar instance = Calendar.getInstance();
        final TimeZone timeZone = instance.getTimeZone();
        double n = timeZone.getRawOffset() / 3600000.0;
        if (timeZone.inDaylightTime(instance.getTime())) {
            ++n;
        }
        return n;
    }
    
    private double a(final Calendar calendar, final double n) {
        int n2 = calendar.get(2) + 1;
        int value = calendar.get(1);
        final int value2 = calendar.get(5);
        final int value3 = calendar.get(11);
        final int value4 = calendar.get(12);
        final double n3 = calendar.get(13) + calendar.get(14) / 1000.0;
        if (n2 <= 2) {
            --value;
            n2 += 12;
        }
        final double n4 = value / 100;
        return (int)(365.25 * value) + (int)(30.6001 * (n2 + 1)) + value2 + 1720994.5 + (2.0 - n4 + (int)(n4 / 4.0)) + (value3 - n) / 24.0 + value4 / 1440.0 + n3 / 86400.0;
    }
    
    public SNTPCorrection getCorrection() throws Exception {
        Socket socket = null;
        final double a = this.a();
        final byte[] array = new byte[48];
        for (int i = 0; i < 48; ++i) {
            array[i] = 0;
        }
        array[0] = 11;
        final a a2 = new a();
        final a a3 = new a();
        double a4;
        double a5;
        double julianDate;
        double julianDate2;
        try {
            socket = new Socket(this.a, this.b);
            final OutputStream outputStream = socket.getOutputStream();
            final InputStream inputStream = socket.getInputStream();
            outputStream.write(array);
            outputStream.flush();
            final Calendar instance = Calendar.getInstance();
            final byte[] array2 = new byte[48];
            if (inputStream.read(array2) != 48) {
                throw new Exception(a("xqb\u001b`En-\u0007vS>$\u001bj[>2\u001bjNg"));
            }
            final Calendar instance2 = Calendar.getInstance();
            a4 = this.a(instance, a);
            a5 = this.a(instance2, a);
            a2.setData(array2, 32);
            julianDate = a2.getJulianDate();
            a3.setData(array2, 40);
            if ((long)a3.getSeconds() == 0L) {
                throw new Exception(a("xJ\u0012IVSl4\fw\u0016w1IjCjb\u0006c\u0016m'\u001bs_}'G"));
            }
            julianDate2 = a3.getJulianDate();
        }
        finally {
            try {
                socket.close();
            }
            catch (Exception ex) {}
        }
        final double n = a4;
        final double n2 = julianDate;
        final double n3 = julianDate2;
        final double n4 = a5;
        final SNTPCorrection sntpCorrection = new SNTPCorrection();
        sntpCorrection.b((n2 - n + (n3 - n4)) / 2.0 * 86400.0);
        sntpCorrection.a((n4 - n - (n2 - n3)) * 86400.0);
        return sntpCorrection;
    }
    
    private static String a(final String s) {
        final char[] charArray = s.toCharArray();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = '6';
                    break;
                }
                case 1: {
                    c2 = '\u001e';
                    break;
                }
                case 2: {
                    c2 = 'B';
                    break;
                }
                case 3: {
                    c2 = 'i';
                    break;
                }
                default: {
                    c2 = '\u0005';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
