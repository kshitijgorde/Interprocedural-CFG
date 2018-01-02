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
        final boolean q = com.easypano.tw.g.q;
        cf.a(0, a("v\u0019n;\u000f]\u0017}<\rUVm=\u0006\u0012\u0005r<\r\u0012\u0017k6\u000b[\u0000|{M\u001c"));
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
                        cf.a(15, a("u\u0013w0\u0011S\u0002p;\u0004\u0012\u0002q0C[\u0018p!\nS\u001a9<\rT\u0019k8\u0002F\u001fv;M\u001cX"));
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
                    cf.a((int)(n * 0.15), a("v\u0019n;\u000f]\u0017}<\rUVm=\u0006\u0012\u0005r<\r\u0012\u0017k6\u000b[\u0000|{M\u001c"));
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
            this.b = this.a.a(a("[\u0018p#\u0006@\u0005p:\r"));
            cf.a(18, a("u\u0013w0\u0011S\u0002|u\u0017Z\u001394\u0016V\u001fvu\n\\\u0010v'\u000eS\u0002p:\r\u001cX7"));
            this.h.a(this.a, cf);
            cf.a(20, a("u\u0013w0\u0011S\u0002|u\u0017Z\u00139&\u0000W\u0018|u\n\\\u0010v'\u000eS\u0002p:\r\u001cX7"));
            this.g.a(this.a, cf);
            cf.a(30, a("u\u0013w0\u0011S\u0002|u\u0013S\u0002qu\n\\\u0010v'\u000eS\u0002p:\r\u001cX7"));
            this.f.a(this.a, this.g, cf);
            cf.a(40, a("u\u0013w0\u0011S\u0002|u\u0017Z\u00139&\b[\u00189<\rT\u0019k8\u0002F\u001fv;M\u001cX"));
            this.e.a(this.a, cf);
            cf.a(90, a("v\u0019n;\u000f]\u0017}<\rUVm=\u0006\u0012\u0010p'\u0010FVj6\u0006\\\u00137{M"));
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
                            cf.a((int)(90.0 + n * 0.1), a("v\u0019n;\u000f]\u0017}<\rUVm=\u0006\u0012\u0010p'\u0010FVj6\u0006\\\u00139<\u000eS\u0011|{M\u001c"));
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
        cf.a(100, a("q\u0019t%\u000fW\u0002|1B"));
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
                            c2 = '2';
                            break;
                        }
                        case 1: {
                            c2 = 'v';
                            break;
                        }
                        case 2: {
                            c2 = '\u0019';
                            break;
                        }
                        case 3: {
                            c2 = 'U';
                            break;
                        }
                        default: {
                            c2 = 'c';
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
