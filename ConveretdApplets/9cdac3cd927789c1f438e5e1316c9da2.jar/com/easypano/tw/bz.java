// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.io.DataInputStream;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.net.URL;

public class bz
{
    by a;
    String b;
    TWViewer c;
    ch d;
    cm e;
    cf f;
    ck g;
    bx h;
    public static byte[] i;
    public static byte[] j;
    
    static {
        bz.i = new byte[14723];
        bz.j = new byte[] { 71, 73, 70, 56, 57, 97, 104, 1, -16, 0, -43, 50, 0, 45, 91, -115, 14, 83, -119, 14, 82, -119, 98, -124, -86, 64, 64, 64, -113, -82, -56, -54, -42, -30, 78, -128, -88, 0, 0, 0, -65, -47, -32, -106, -83, -58, 58, 101, -108, -65, -65, -65, 85, 122, -94, -14, -11, -8, 127, 127, 127, 124, -103, -72, -33, -24, -17, 30, 94, -111, -41, -32, -22, 71, 112, -101, -49, -36, -25, -80, -63, -44, -17, -13, -9, -97, -70, -48, 46, 105, -103, -33, -33, -33, 111, -114, -79, -27, -22, -15, 110, -105, -72, -93, -73, -51, -97, -97, -97, -49, -49, -49, -67, -52, -37, -119, -93, -65, -81, -59, -40, 94, -116, -80, 16, 16, 16, 32, 32, 32, -17, -17, -17, 62, 117, -95, 112, 112, 112, 48, 48, 48, -81, -81, -81, 126, -93, -64, 96, 96, 96, -113, -113, -113, 80, 80, 80, -3, -2, -2, -1, -1, -1, -1, -1, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 33, -1, 11, 78, 69, 84, 83, 67, 65, 80, 69, 50, 46, 48, 3, 1, 0, 0, 0, 33, -7, 4, 5, 50, 0, 50, 0, 44, 0, 0, 0, 0, 104, 1, -16, 0, 0, 6, -1, -64, -104, 112, 72, 44, 26, -113, -56, -92, 114, -55, 108, 58, -97, -48, -88, 116, 74, -83, 90, -81, -40, -84, 118, -53, -19, 122, -65, -32, -80, 120, 76, 46, -101, -49, -24, -76, 122, -51, 110, -69, -33, -16, -72, 124, 78, -81, -37, -17, -8, -68, 126, -49, -17, -5, -1, -128, -127, -126, -125, -124, -123, -122, -121, -120, -119, -118, -117, -116, -115, -114, -113, -112, -111, -110, -109, -108, -107, -106, -105, -104, -103, -102, -101, -100, -99, -98, -97, -96, -95, -94, -93, -92, -91, -90, -89, -88, -87, -86, -85, -84, -83, -82, -81, -80, -79, -78, -77, -76, -75, -74, -73, -72, -78, 1, -69, 1, -71, -66, -81, -68, -63, -67, -65, -60, -88, -62, -68, -59, -55, -92, -57, -63, -54, -50, -97, -52, -56, -49, -45, -102, -47, -69, -44, -40, -105, -42, -61, -39, -35, -110, -47, -34, -31, -111, -32, -30, -27, -114, -51, -26, -23, -22, -21, -20, -19, -18, -17, -16, -15, -14, -13, -12, -11, -10, -9, -8, -7, -6, -5, -4, -3, -2, -1, 0, 3, 10, 28, 72, -80, -96, -63, -125, 8, 19, 42, 92, -56, -80, -95, -61, -121, 16, 35, 74, -100, 72, -79, -94, -59, -117, 24, 51, 106, -36, -56, -79, -93, -57, -113, 32, 67, -118, 28, 73, -78, -92, -55, -109, 40, 83, -86, 92, -55, -78, -91, -53, -105, 48, 99, -54, -100, 73, -77, -90, -51, -101, 56, 115, 98, -31, 16, 66, -63, -122, -1, 1, 0, -126, 10, 29, 10, 96, 0, 4, 15, 28, 116, -50, -30, 96, 1, 2, 5, -94, 80, -93, 6, 109, 96, 65, 105, -85, 9, 34, 26, 72, -35, -70, -75, -127, 3, -85, -89, 56, 40, 120, 26, -75, -127, 81, 8, 3, -56, 114, 29, -22, 21, -20, 40, 3, 16, -94, 82, 16, 97, -32, -120, 1, -96, 107, -123, 42, 112, 11, -22, 110, -44, 1, 33, -104, 88, -56, 27, 116, 1, -33, 78, 28, -30, 66, 93, 80, -43, -119, -30, -68, -121, 55, 121, 88, -16, -9, -21, 19, 5, -124, 13, 71, -66, -60, 1, 47, 84, 8, 82, 48, -25, -35, -69, -71, 82, 8, -54, 81, 65, 75, 81, -69, -107, -126, -27, -46, -110, 68, -1, -99, -30, 33, -17, -126, 9, -80, 37, 57, -40, -80, 117, -63, -21, 39, 6, 80, -73, -58, -99, 27, -110, 3, -83, 91, -21, 70, -111, -67, 117, -61, -17, -30, -116, 38, 32, -105, -86, 26, 56, 107, -71, -54, -95, 59, -102, 32, 60, -86, 111, -32, -98, -91, 82, 104, -84, -35, -111, -125, -18, 81 };
    }
    
    public bz(final TWViewer c) {
        this.a = null;
        this.b = "";
        this.c = c;
        this.h = new bx(c);
        this.g = new ck(this, c);
        this.d = new ch(c, this.g);
        this.f = new cf(this.g);
        this.e = new cm(this, this.g, c);
    }
    
    public void a(final URL url, final Object o, final String s, final cg cg, final boolean b) {
        final boolean q = com.easypano.tw.g.q;
        cg.a(0, a("ilM \u0012Bb^'\u0010J#N&\u001b\rpQ'\u0010\rbH-\u0016Du_`P\u0003"));
        this.d.a(url, null, 4, o);
        int n = 0;
        String s2 = null;
        Label_0157: {
            Object a;
            while (true) {
                Label_0058: {
                    if ((n = this.d.c()) >= 100) {
                        a = cg;
                        if (q) {
                            break;
                        }
                        cg.a(15, a("jfT+\fLwS \u0019\rwR+^DmS:\u0017Lo\u001a'\u0010KlH#\u001fYjU P\u0003-"));
                        if (!q) {
                            if (b) {
                                final byte[] array = (byte[])this.d.a(s, 10);
                                ch.a(array);
                                s2 = new String(array);
                                if (!q) {
                                    break Label_0157;
                                }
                            }
                            a = this.d.a(s, 3);
                            break;
                        }
                        break Label_0058;
                    }
                    cg.a((int)(n * 0.15), a("ilM \u0012Bb^'\u0010J#N&\u001b\rpQ'\u0010\rbH-\u0016Du_`P\u0003"));
                    try {
                        Thread.sleep(10L);
                    }
                    catch (Exception ex) {}
                }
            }
            s2 = (String)a;
        }
        if (s2 != null) {
            this.a = new by(new DataInputStream(new BufferedInputStream(new ByteArrayInputStream(s2.getBytes()))));
            this.b = this.a.a(a("DmS8\u001b_pS!\u0010"));
            cg.a(18, a("jfT+\fLw_n\nEf\u001a/\u000bIjUn\u0017CeU<\u0013LwS!\u0010\u0003-\u0014"));
            this.h.a(this.a, cg);
            cg.a(20, a("jfT+\fLw_n\nEf\u001a=\u001dHm_n\u0017CeU<\u0013LwS!\u0010\u0003-\u0014"));
            this.g.a(this.a, cg);
            cg.a(30, a("jfT+\fLw_n\u000eLwRn\u0017CeU<\u0013LwS!\u0010\u0003-\u0014"));
            this.f.a(this.a, this.g, cg);
            cg.a(40, a("jfT+\fLw_n\nEf\u001a=\u0015Dm\u001a'\u0010KlH#\u001fYjU P\u0003-"));
            this.e.a(this.a, cg);
            cg.a(90, a("ilM \u0012Bb^'\u0010J#N&\u001b\reS<\rY#I-\u001bCf\u0014`P"));
            final ce a2 = this.f.a(this.f.b());
            int d = -1;
            final ce ce = a2;
            int a3 = 0;
            Label_0423: {
                Label_0421: {
                    final int n2;
                    Label_0410: {
                        if (q || ce != null) {
                            n2 = (a3 = ce.a());
                            if (q) {
                                break Label_0410;
                            }
                            if (n2 > 0) {
                                d = a2.a(0).d;
                                if (!q) {
                                    break Label_0421;
                                }
                            }
                        }
                        final int a4;
                        a3 = (a4 = this.g.a());
                    }
                    if (q) {
                        break Label_0423;
                    }
                    if (n2 > 0) {
                        d = 0;
                    }
                }
                a3 = d;
            }
            while (true) {
                Label_0504: {
                    if (a3 == -1) {
                        break Label_0504;
                    }
                    this.d.a(this.g.a(d).k, null, 10);
                    while (true) {
                        Label_0489: {
                            if (!q) {
                                break Label_0489;
                            }
                            cg.a((int)(90.0 + n * 0.1), a("ilM \u0012Bb^'\u0010J#N&\u001b\reS<\rY#I-\u001bCf\u001a'\u0013Ld_`P\u0003"));
                            try {
                                Thread.sleep(10L);
                            }
                            catch (Exception ex2) {}
                        }
                        if ((n = this.d.c()) < 100) {
                            continue;
                        }
                        break;
                    }
                }
                if (q) {
                    continue;
                }
                break;
            }
            this.d.a();
        }
        cg.a(100, a("nlW>\u0012Hw_*_"));
    }
    
    public void destroyResource() {
        this.c = null;
        this.d.destroyResource();
        this.h.destroyResource();
        this.g.b();
        this.e.destroyResource();
        this.f.destroyResource();
    }
    
    private static String a(final String s) {
        final char[] charArray = s.toCharArray();
        final int length = charArray.length;
        int n = 0;
        while (true) {
            Label_0089: {
                if (length > 1) {
                    break Label_0089;
                }
                char[] array2;
                char[] array = array2 = charArray;
                int n3;
                int n2 = n3 = n;
                while (true) {
                    final char c = array2[n3];
                    char c2 = '\0';
                    switch (n % 5) {
                        case 0: {
                            c2 = '-';
                            break;
                        }
                        case 1: {
                            c2 = '\u0003';
                            break;
                        }
                        case 2: {
                            c2 = ':';
                            break;
                        }
                        case 3: {
                            c2 = 'N';
                            break;
                        }
                        default: {
                            c2 = '~';
                            break;
                        }
                    }
                    array[n2] = (char)(c ^ c2);
                    ++n;
                    if (length != 0) {
                        break;
                    }
                    array = (array2 = charArray);
                    n2 = (n3 = length);
                }
            }
            if (n >= length) {
                return new String(charArray);
            }
            continue;
        }
    }
}
