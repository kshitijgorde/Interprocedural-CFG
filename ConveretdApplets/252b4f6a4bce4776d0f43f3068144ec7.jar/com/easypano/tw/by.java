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
        final int q = com.easypano.tw.h.q;
        cf.a(0, a("\u0002\u001f\b\u0005\u0013)\u0011\u001b\u0002\u0011!P\u000b\u0003\u001af\u0003\u0014\u0002\u0011f\u0011\r\b\u0017/\u0006\u001aEQh"));
        this.d.a(url, null, 4, o);
        int n = 0;
        String s2 = null;
        Label_0157: {
            Object a;
            while (true) {
                Label_0058: {
                    if ((n = this.d.c()) >= 100) {
                        a = cf;
                        if (q != 0) {
                            break;
                        }
                        cf.a(15, a("\u0001\u0015\u0011\u000e\r'\u0004\u0016\u0005\u0018f\u0004\u0017\u000e_/\u001e\u0016\u001f\u0016'\u001c_\u0002\u0011 \u001f\r\u0006\u001e2\u0019\u0010\u0005Qh^"));
                        if (q == 0) {
                            if (b) {
                                final byte[] array = (byte[])this.d.a(s, 10);
                                cg.a(array);
                                s2 = new String(array);
                                if (q == 0) {
                                    break Label_0157;
                                }
                            }
                            a = this.d.a(s, 3);
                            break;
                        }
                        break Label_0058;
                    }
                    cf.a((int)(n * 0.15), a("\u0002\u001f\b\u0005\u0013)\u0011\u001b\u0002\u0011!P\u000b\u0003\u001af\u0003\u0014\u0002\u0011f\u0011\r\b\u0017/\u0006\u001aEQh"));
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
            this.b = this.a.a(a("/\u001e\u0016\u001d\u001a4\u0003\u0016\u0004\u0011"));
            cf.a(18, a("\u0001\u0015\u0011\u000e\r'\u0004\u001aK\u000b.\u0015_\n\n\"\u0019\u0010K\u0016(\u0016\u0010\u0019\u0012'\u0004\u0016\u0004\u0011h^Q"));
            this.h.a(this.a, cf);
            cf.a(20, a("\u0001\u0015\u0011\u000e\r'\u0004\u001aK\u000b.\u0015_\u0018\u001c#\u001e\u001aK\u0016(\u0016\u0010\u0019\u0012'\u0004\u0016\u0004\u0011h^Q"));
            this.g.a(this.a, cf);
            cf.a(30, a("\u0001\u0015\u0011\u000e\r'\u0004\u001aK\u000f'\u0004\u0017K\u0016(\u0016\u0010\u0019\u0012'\u0004\u0016\u0004\u0011h^Q"));
            this.f.a(this.a, this.g, cf);
            cf.a(40, a("\u0001\u0015\u0011\u000e\r'\u0004\u001aK\u000b.\u0015_\u0018\u0014/\u001e_\u0002\u0011 \u001f\r\u0006\u001e2\u0019\u0010\u0005Qh^"));
            this.e.a(this.a, cf);
            cf.a(90, a("\u0002\u001f\b\u0005\u0013)\u0011\u001b\u0002\u0011!P\u000b\u0003\u001af\u0016\u0016\u0019\f2P\f\b\u001a(\u0015QEQ"));
            final cd a2 = this.f.a(this.f.b());
            int d = -1;
            final cd cd = a2;
            int a3 = 0;
            Label_0423: {
                Label_0421: {
                    final int n2;
                    Label_0410: {
                        if (q != 0 || cd != null) {
                            n2 = (a3 = cd.a());
                            if (q != 0) {
                                break Label_0410;
                            }
                            if (n2 > 0) {
                                d = a2.a(0).d;
                                if (q == 0) {
                                    break Label_0421;
                                }
                            }
                        }
                        final int a4;
                        a3 = (a4 = this.g.a());
                    }
                    if (q != 0) {
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
                            if (q == 0) {
                                break Label_0489;
                            }
                            cf.a((int)(90.0 + n * 0.1), a("\u0002\u001f\b\u0005\u0013)\u0011\u001b\u0002\u0011!P\u000b\u0003\u001af\u0016\u0016\u0019\f2P\f\b\u001a(\u0015_\u0002\u0012'\u0017\u001aEQh"));
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
                if (q != 0) {
                    continue;
                }
                break;
            }
            this.d.a();
        }
        cf.a(100, a("\u0005\u001f\u0012\u001b\u0013#\u0004\u001a\u000f^"));
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
                            c2 = 'F';
                            break;
                        }
                        case 1: {
                            c2 = 'p';
                            break;
                        }
                        case 2: {
                            c2 = '\u007f';
                            break;
                        }
                        case 3: {
                            c2 = 'k';
                            break;
                        }
                        default: {
                            c2 = '\u007f';
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
