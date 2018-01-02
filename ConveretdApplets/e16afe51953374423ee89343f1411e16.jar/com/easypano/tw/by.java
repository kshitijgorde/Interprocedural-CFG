// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.io.DataInputStream;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.net.URL;

public class by
{
    bx a;
    String b;
    TWViewer c;
    cg d;
    cl e;
    ce f;
    cj g;
    bw h;
    
    public by(final TWViewer c) {
        this.a = null;
        this.b = "";
        this.c = c;
        this.h = new bw(c);
        this.g = new cj(this, c);
        this.d = new cg(c, this.g);
        this.f = new ce(this.g);
        this.e = new cl(this, this.g, c);
    }
    
    public void a(final URL url, final Object o, final String s, final cf cf, final boolean b) {
        final boolean q = com.easypano.tw.h.q;
        cf.a(0, a("\u00018!3k*624i\"w\"5be$=4ie6$>o,!3s)k"));
        this.d.a(url, null, 4, o);
        int n = 0;
        String s2 = null;
        Label_0157: {
            Object a;
            while (true) {
                Label_0058: {
                    if ((n = this.d.c()) >= 100) {
                        a = cf;
                        if (q) {
                            break;
                        }
                        cf.a(15, a("\u0002288u$#?3`e#>8',9?)n$;v4i#8$0f1>93)ky"));
                        if (!q) {
                            if (b) {
                                final byte[] array = (byte[])this.d.a(s, 10);
                                cg.a(array);
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
                    cf.a((int)(n * 0.15), a("\u00018!3k*624i\"w\"5be$=4ie6$>o,!3s)k"));
                    try {
                        Thread.sleep(10L);
                    }
                    catch (Exception ex) {}
                }
            }
            s2 = (String)a;
        }
        if (s2 != null) {
            this.a = new bx(new DataInputStream(new BufferedInputStream(new ByteArrayInputStream(s2.getBytes()))));
            this.b = this.a.a(a(",9?+b7$?2i"));
            cf.a(18, a("\u0002288u$#3}s-2v<r!>9}n+19/j$#?2ikyx"));
            this.h.a(this.a, cf);
            cf.a(20, a("\u0002288u$#3}s-2v.d 93}n+19/j$#?2ikyx"));
            this.g.a(this.a, cf);
            cf.a(30, a("\u0002288u$#3}w$#>}n+19/j$#?2ikyx"));
            this.f.a(this.a, this.g, cf);
            cf.a(40, a("\u0002288u$#3}s-2v.l,9v4i#8$0f1>93)ky"));
            this.e.a(this.a, cf);
            cf.a(90, a("\u00018!3k*624i\"w\"5be1?/t1w%>b+2xs)"));
            final cd a2 = this.f.a(this.f.b());
            int d = -1;
            final cd cd = a2;
            int a3 = 0;
            Label_0423: {
                Label_0421: {
                    final int n2;
                    Label_0410: {
                        if (q || cd != null) {
                            n2 = (a3 = cd.a());
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
                            cf.a((int)(90.0 + n * 0.1), a("\u00018!3k*624i\"w\"5be1?/t1w%>b+2v4j$03s)k"));
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
        cf.a(100, a("\u00068;-k #39&"));
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
                            c2 = 'E';
                            break;
                        }
                        case 1: {
                            c2 = 'W';
                            break;
                        }
                        case 2: {
                            c2 = 'V';
                            break;
                        }
                        case 3: {
                            c2 = ']';
                            break;
                        }
                        default: {
                            c2 = '\u0007';
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
