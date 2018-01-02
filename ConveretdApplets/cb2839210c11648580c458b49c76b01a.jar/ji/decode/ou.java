// 
// Decompiled by Procyon v0.5.30
// 

package ji.decode;

import ji.io.fg;
import ji.res.s;
import java.io.IOException;
import ji.io.h;
import ji.util.d;
import java.util.Hashtable;
import ji.image.dx;
import ji.io.ac;

public class ou
{
    private static final String[] a;
    
    public static dx a(final ac ac, final dx dx, final String s, final boolean b) throws Exception {
        final long r = ac.r();
        dx dx2 = a(ac, dx, s, b, false);
        if (dx.m <= 0) {
            ac.a(r);
            dx2 = a(ac, dx, s, b, true);
        }
        return dx2;
    }
    
    private static dx a(final ac ac, final dx dx, final String s, final boolean b, final boolean b2) throws Exception {
        if (dx.bk == null) {
            dx.bk = new Hashtable();
        }
        dx.u = 1;
        dx.v = 1;
        ac.a(new byte[2]);
        boolean b3 = false;
        int n = ac.f() & 0xFF;
        while (n == 255 && !ac.g()) {
            final int n2 = ac.f() & 0xFF;
            if (d.cy()) {
                h.d(s, ou.a[n2]);
            }
            switch (n2) {
                case 224: {
                    a(ac, dx);
                    b3 = true;
                    break;
                }
                case 254: {
                    dx.bk.put("Comment", a(ac, b2));
                    break;
                }
                case 192:
                case 193:
                case 194:
                case 195:
                case 197:
                case 198:
                case 199:
                case 202:
                case 203:
                case 205:
                case 206:
                case 207: {
                    a(ac, dx, n2, s);
                    break;
                }
                case 201: {
                    dx.bk.put("Encoding", "Arithmetic");
                    a(ac, dx, n2, s);
                    break;
                }
                case 208:
                case 209:
                case 210:
                case 211:
                case 212:
                case 213:
                case 214:
                case 215: {
                    break;
                }
                default: {
                    ac.a(Math.max(a(ac) - 2, 0));
                    break;
                }
            }
            n = (ac.f() & 0xFF);
            if (ac.r() == 0) {
                h.d(s, "Missing JPEG header!");
                break;
            }
        }
        if (!b3) {
            dx.l = "Non JFIF Compliant JPEG";
            dx.bk.put("Format", "".concat(String.valueOf(String.valueOf(dx.l))));
        }
        if (dx.ac == 1.0) {
            dx.ac = 100.0;
            dx.bk.put("xResolution", "100.0 DPI (assumed)");
        }
        if (dx.ad == 1.0) {
            dx.ad = 100.0;
            dx.bk.put("yResolution", "100.0 DPI (assumed)");
        }
        if (dx.l == null) {
            dx.l = "Non-JFIF compliant JPEG";
            a(dx.bk, "Format", dx.l);
        }
        return dx;
    }
    
    private static final void a(final Hashtable hashtable, final String s, final Object o) {
        hashtable.put(s, o);
    }
    
    private static void a(final ac ac, final dx dx) throws Exception {
        final int a = a(ac);
        if (a < 16) {
            throw new IOException("Invalid JFIF Marker Segment");
        }
        final byte[] array = new byte[a - 2];
        ac.a(array);
        if (array[0] != 74 || array[1] != 70 || array[2] != 73 || array[3] != 70 || array[4] != 0) {
            return;
        }
        if (array[5] != 1) {
            if (array[5] == 2 && array[6] == 1) {
                dx.l = "Invalid JFIF Version 2.1 (Should be 1.2)";
            }
            else {
                dx.l = String.valueOf(String.valueOf(new StringBuffer("Invalid JFIF Version ").append(array[5]).append(".").append(array[6])));
            }
        }
        else {
            dx.l = "JFIF Compliant JPEG v1.".concat(String.valueOf(String.valueOf(array[6])));
        }
        a(dx.bk, "Format", "".concat(String.valueOf(String.valueOf(dx.l))));
        int n = (array[8] & 0xFF) << 8 | (array[9] & 0xFF);
        int n2 = (array[10] & 0xFF) << 8 | (array[11] & 0xFF);
        if (n <= 0) {
            n = 100;
        }
        if (n2 <= 0) {
            n2 = 100;
        }
        switch (array[7]) {
            case 0:
            case 1: {
                dx.ac = n;
                dx.ad = n2;
                a(dx.bk, "xResolution", String.valueOf(String.valueOf(dx.ac)).concat(" DPI"));
                a(dx.bk, "yResolution", String.valueOf(String.valueOf(dx.ad)).concat(" DPI"));
                break;
            }
            case 2: {
                dx.ac = n / 2.54;
                dx.ad = n2 / 2.54;
                a(dx.bk, "xResolution", String.valueOf(String.valueOf(dx.ac)).concat(" DPI"));
                a(dx.bk, "yResolution", String.valueOf(String.valueOf(dx.ad)).concat(" DPI"));
                break;
            }
            default: {
                dx.ac = 100.0;
                a(dx.bk, "xResolution", "100.0 DPI (assumed)");
                dx.ad = 100.0;
                a(dx.bk, "yResolution", "100.0 DPI (assumed)");
                break;
            }
        }
    }
    
    private static String a(final ac ac, final boolean b) throws Exception {
        final int a = a(ac);
        byte[] array;
        if (b) {
            array = new byte[a];
        }
        else {
            array = new byte[a - 2];
        }
        ac.a(array);
        return new String(array).trim();
    }
    
    private static void a(final ac ac, final dx dx, final int n, final String s) throws Exception {
        final byte[] array = new byte[a(ac) - 2];
        ac.a(array);
        dx.z = array[0];
        dx.n = ((array[1] & 0xFF) << 8 | (array[2] & 0xFF));
        dx.m = ((array[3] & 0xFF) << 8 | (array[4] & 0xFF));
        dx.aa = array[5];
        if (dx.aa == 4) {
            h.c(s, String.valueOf(String.valueOf(new StringBuffer("The built-in JPEG Decoder cannot support JPEGs with ").append(dx.aa).append(" color components"))));
            throw new fg(String.valueOf(String.valueOf(s.a(1295, s))).concat(String.valueOf(String.valueOf(dx.aa))), false);
        }
        dx.am = dx.aa * dx.z;
        a(dx.bk, "SamplesPerPixel", "".concat(String.valueOf(String.valueOf(dx.aa))));
        a(dx.bk, "BitsPerSample", "".concat(String.valueOf(String.valueOf(dx.z))));
        a(dx.bk, "C0 Sample Factor", new Integer(array[7] >> 4));
    }
    
    private static int a(final ac ac) throws Exception {
        final byte[] array = new byte[2];
        ac.a(array);
        return (array[0] & 0xFF) << 8 | (array[1] & 0xFF);
    }
    
    static {
        a = new String[255];
        for (int i = 0; i < ou.a.length; ++i) {
            ou.a[i] = "RESERVED";
        }
        ou.a[216] = "SOI";
        ou.a[217] = "EOI";
        ou.a[224] = "JFIF";
        ou.a[254] = "COM";
        ou.a[192] = "SOF0";
        ou.a[193] = "SOF1";
        ou.a[208] = "RST0";
        ou.a[209] = "RST1";
        ou.a[196] = "DHT";
        ou.a[200] = "JPG";
        ou.a[204] = "DAC";
        ou.a[218] = "SOS";
        ou.a[219] = "DQT";
        ou.a[220] = "DNL";
        ou.a[221] = "DRI";
        ou.a[222] = "DHP";
        ou.a[223] = "EXP";
        ou.a[240] = "JPG0";
        ou.a[253] = "JPG13";
    }
}
