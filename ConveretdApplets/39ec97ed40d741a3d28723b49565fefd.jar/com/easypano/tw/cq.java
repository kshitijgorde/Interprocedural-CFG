// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.image.ImageProducer;
import java.awt.Toolkit;
import java.awt.image.MemoryImageSource;
import java.awt.Dimension;
import java.awt.Image;
import com.easypano.tw.c.c;
import java.awt.Component;

public class cq
{
    public static final int a = 1;
    public static final int b = 2;
    public static final int c = 3;
    private Component d;
    private c e;
    private Image f;
    private Image g;
    private Dimension h;
    private Image i;
    private int[] j;
    private MemoryImageSource k;
    private int l;
    boolean m;
    private int n;
    private Dimension o;
    
    public cq() {
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = 0;
        this.m = true;
        this.n = 1;
        this.o = null;
    }
    
    public void a(final Component d) {
        this.d = d;
    }
    
    public void a(final c e) {
        this.e = e;
    }
    
    public void a(final Image f, final Image g) {
        this.f = f;
        this.g = g;
    }
    
    public void a(final Dimension h) {
        this.h = h;
    }
    
    public void a() throws Exception {
        final boolean q = com.easypano.tw.g.q;
        cq cq = this;
        Label_0447: {
            if (!q) {
                Label_0446: {
                    if (this.f != null) {
                        cq = this;
                        if (q) {
                            break Label_0447;
                        }
                        if (this.g != null) {
                            cq = this;
                            if (q) {
                                break Label_0447;
                            }
                            if (this.h != null) {
                                this.f.flush();
                                this.g.flush();
                                this.e.a(this.f, this.g);
                                final int n = this.n;
                                final c e;
                                Label_0437: {
                                    int a2 = 0;
                                    Label_0398: {
                                        cq cq4 = null;
                                        Label_0386: {
                                            Label_0385: {
                                                Label_0361: {
                                                    cq cq2 = null;
                                                    Label_0348: {
                                                        Label_0207: {
                                                            if (!q) {
                                                                cq cq3 = null;
                                                                Label_0195: {
                                                                    Label_0173: {
                                                                        if (n == this.e.a()) {
                                                                            cq2 = this;
                                                                            cq3 = this;
                                                                            if (q) {
                                                                                break Label_0195;
                                                                            }
                                                                            if (this.o != null) {
                                                                                final boolean equals;
                                                                                final boolean b = equals = this.o.equals(this.h);
                                                                                if (q) {
                                                                                    break Label_0207;
                                                                                }
                                                                                if (b) {
                                                                                    final int a = this.e.a();
                                                                                    final int n2 = 2;
                                                                                    if (!q) {
                                                                                        if (a == n2) {
                                                                                            cq2 = this;
                                                                                            cq3 = this;
                                                                                            if (q) {
                                                                                                break Label_0195;
                                                                                            }
                                                                                            if (this.j == null) {
                                                                                                break Label_0173;
                                                                                            }
                                                                                        }
                                                                                        final int a3;
                                                                                        a2 = (a3 = this.e.a());
                                                                                        if (q) {
                                                                                            break Label_0398;
                                                                                        }
                                                                                    }
                                                                                    if (a != n2) {
                                                                                        break Label_0385;
                                                                                    }
                                                                                    cq4 = this;
                                                                                    if (q) {
                                                                                        break Label_0386;
                                                                                    }
                                                                                    if (this.i != null) {
                                                                                        break Label_0385;
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                    this.n = this.e.a();
                                                                    this.o = this.h;
                                                                    cq2 = this;
                                                                    cq3 = this;
                                                                }
                                                                if (q) {
                                                                    break Label_0348;
                                                                }
                                                                cq3.e.a();
                                                            }
                                                        }
                                                        switch (n) {
                                                            case 1: {
                                                                cq cq5 = this;
                                                                if (!q) {
                                                                    if (this.d == null) {
                                                                        this.i = null;
                                                                        if (!q) {
                                                                            break Label_0361;
                                                                        }
                                                                    }
                                                                    cq5 = this;
                                                                }
                                                                cq5.i = this.d.createImage(this.h.width, this.h.height);
                                                                if (q) {
                                                                    break;
                                                                }
                                                                break Label_0361;
                                                            }
                                                        }
                                                        this.j = new int[this.h.width * this.h.height];
                                                        (this.k = new MemoryImageSource(this.h.width, this.h.height, this.j, 0, this.h.width)).setFullBufferUpdates(true);
                                                        this.k.setAnimated(true);
                                                        cq2 = this;
                                                    }
                                                    cq2.i = Toolkit.getDefaultToolkit().createImage(this.k);
                                                }
                                                cq4 = this;
                                                if (q) {
                                                    break Label_0386;
                                                }
                                                if (this.i == null) {
                                                    throw new Exception(a("/\fG\u0004S\t^G\u0003A\t\u001dVEN\u0001\u001fE\u0000\u0007\t\fP\nUB"));
                                                }
                                            }
                                            cq4 = this;
                                        }
                                        e = cq4.e;
                                        if (q) {
                                            break Label_0437;
                                        }
                                        a2 = e.a();
                                    }
                                    switch (a2) {
                                        case 1: {
                                            this.e.a(this.i);
                                            if (q) {
                                                break;
                                            }
                                            break Label_0446;
                                        }
                                    }
                                    final c e2 = this.e;
                                }
                                e.a(this.j);
                            }
                        }
                    }
                }
                cq = this;
            }
        }
        cq.m = true;
    }
    
    public Image b() {
        return this.i;
    }
    
    public int[] c() {
        return this.j;
    }
    
    public void a(final int n) {
        final boolean q = com.easypano.tw.g.q;
        final c e = this.e;
        if (!q) {
            if (e == null) {
                return;
            }
            this.e.a(n);
            final c e2 = this.e;
        }
        final int a = e.a();
        cq cq = null;
        Label_0084: {
            if (!q) {
                switch (a) {
                    case 1: {
                        if (q) {
                            break;
                        }
                        return;
                    }
                }
                cq = this;
                if (q) {
                    break Label_0084;
                }
                final boolean m = this.m;
            }
            if (a != 0) {
                this.i.flush();
                this.m = false;
            }
            cq = this;
        }
        cq.k.newPixels();
    }
    
    public int d() {
        int a = 2;
        final c e = this.e;
        if (!com.easypano.tw.g.q) {
            if (e == null) {
                return a;
            }
            final c e2 = this.e;
        }
        a = e.a();
        return a;
    }
    
    public void e() {
        cq cq = this;
        if (!com.easypano.tw.g.q) {
            if (this.e != null) {
                this.e.b();
                this.e = null;
            }
            this.f = null;
            cq = this;
        }
        cq.g = null;
        ds.e();
    }
    
    public void f() {
        cq cq = this;
        if (!com.easypano.tw.g.q) {
            if (this.e != null) {
                this.e.b();
                this.e = null;
            }
            this.h = null;
            this.f = null;
            this.g = null;
            this.i = null;
            this.j = null;
            cq = this;
        }
        cq.k = null;
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
                            c2 = 'l';
                            break;
                        }
                        case 1: {
                            c2 = '~';
                            break;
                        }
                        case 2: {
                            c2 = '\"';
                            break;
                        }
                        case 3: {
                            c2 = 'e';
                            break;
                        }
                        default: {
                            c2 = '\'';
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
