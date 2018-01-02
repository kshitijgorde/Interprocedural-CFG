// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.util.Hashtable;

public class ch
{
    public static final int a = 5;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;
    public static final int f = 10;
    private volatile Hashtable g;
    private volatile Hashtable h;
    private volatile int i;
    private volatile cg j;
    private ec k;
    private TWViewer l;
    private ck m;
    private boolean n;
    private volatile int o;
    private volatile ci p;
    
    public ch(final TWViewer l, final ck m) {
        this.g = new Hashtable();
        this.h = new Hashtable();
        this.i = 0;
        this.j = null;
        this.k = new ec(this);
        this.n = false;
        this.o = -1;
        this.p = new ci(this);
        this.l = l;
        this.m = m;
        this.k.setPriority(3);
        this.k.start();
    }
    
    public static void a(final byte[] array) {
        final boolean q = g.q;
        final byte[] xorKey = cs.xorKey;
        final int length = array.length;
        final int length2 = xorKey.length;
        final int n = length;
        if (q || n > 0) {
            int n2 = n;
            int n3 = 0;
            while (true) {
                Label_0067: {
                    if (!q) {
                        break Label_0067;
                    }
                    final int n4 = n2;
                    array[n4] ^= xorKey[n3];
                    ++n2;
                    ++n3;
                    int i = 0;
                    while (i >= length2) {
                        i = 0;
                        if (!q) {
                            n3 = i;
                            break;
                        }
                    }
                }
                if (n2 < length) {
                    continue;
                }
                break;
            }
            final byte[] tableKey = cs.tableKey;
            final int length3 = tableKey.length;
            final int n6;
            final int n5 = n6 = length - length3;
            if (q || n6 > 0) {
                final byte[] array2 = new byte[n6];
                int n7 = 0;
            Label_0138_Outer:
                while (true) {
                    Label_0158: {
                        if (!q) {
                            break Label_0158;
                        }
                        System.arraycopy(array, n7, array2, 0, length3);
                        int n8 = 0;
                        while (true) {
                            while (true) {
                                Label_0141: {
                                    if (!q) {
                                        break Label_0141;
                                    }
                                    array[n7 + n8] = array2[tableKey[n8]];
                                    ++n8;
                                }
                                if (n8 < length3) {
                                    continue Label_0138_Outer;
                                }
                                break;
                            }
                            if (q) {
                                continue;
                            }
                            break;
                        }
                        n7 += length3;
                    }
                    if (n7 < n5) {
                        continue;
                    }
                    break;
                }
            }
        }
    }
    
    public void destroyResource() {
        this.k.a();
        this.k = null;
        this.h.clear();
        this.h = null;
        this.g.clear();
        this.g = null;
        this.l = null;
    }
    
    public void a() {
        this.n = true;
    }
    
    public void b() {
        this.n = false;
    }
    
    public void a(final URL url, final cg j, final int n, final Object o) {
        final boolean q = com.easypano.tw.g.q;
        URL value = url;
        Label_0049: {
            Label_0025: {
                if (!q) {
                    if (url == null) {
                        break Label_0025;
                    }
                    value = this.h.get(url);
                }
                if (value == null) {
                    break Label_0049;
                }
            }
            cg cg = j;
            if (!q) {
                if (j == null) {
                    return;
                }
                cg = j;
            }
            cg.a(100, "");
            if (!q) {
                return;
            }
        }
        boolean b = n != 0;
        final ec k;
        Label_0184: {
            if (!q) {
                switch (n) {
                    case 2: {
                        this.h.put(url, this.l.getAudioClip(url));
                        if (q) {
                            break;
                        }
                        return;
                    }
                }
                this.k.a(false);
                k = this.k;
                if (q) {
                    break Label_0184;
                }
                b = k.b();
            }
            ch ch = null;
            Label_0181: {
                if (b) {
                    this.k.c();
                    ch = this;
                    if (q) {
                        break Label_0181;
                    }
                    if (this.j != null) {
                        this.j.a();
                    }
                }
                this.i = 0;
                this.j = j;
                this.k.a(url);
                this.k.a(n);
                this.k.a(o);
                ch = this;
            }
            final ec i = ch.k;
        }
        k.a(true);
    }
    
    public void a(final URL url, final cg cg, final int n) {
        this.a(url, cg, n, null);
    }
    
    private void a(final int i) {
        this.i = i;
        final cg j = this.j;
        if (!com.easypano.tw.g.q) {
            if (j == null) {
                return;
            }
            final cg k = this.j;
        }
        j.a(i, "");
    }
    
    public synchronized int c() {
        return this.i;
    }
    
    public Object a(final Object o, final int n, final Object o2) {
        final boolean q = com.easypano.tw.g.q;
        Object value = o;
        if (!q) {
            if (o == null) {
                return null;
            }
            value = this.h.get(o);
        }
        Object image = value;
        Object o3;
        final byte[] array = (byte[])(o3 = image);
        int c = 0;
        Label_0157: {
            Label_0148: {
                if (!q) {
                    if (array == null) {
                        o3 = o;
                        if (q) {
                            break Label_0148;
                        }
                        if (o instanceof URL) {
                            ch ch = this;
                            Object value2 = null;
                            Label_0144: {
                                while (true) {
                                    int n2 = 0;
                                    Label_0113: {
                                        Label_0109: {
                                            if (!q) {
                                                if (this.k.d() != null) {
                                                    final boolean b = (n2 = (this.k.d().equals(o) ? 1 : 0)) != 0;
                                                    if (q) {
                                                        break Label_0113;
                                                    }
                                                    if (b) {
                                                        break Label_0109;
                                                    }
                                                }
                                                ch = this;
                                            }
                                            ch.a((URL)o, null, n, o2);
                                            if (!q) {
                                                break Label_0109;
                                            }
                                            try {
                                                Thread.sleep(10L);
                                            }
                                            catch (InterruptedException ex) {}
                                        }
                                        n2 = this.c();
                                    }
                                    if (n2 != 100) {
                                        value2 = this;
                                        if (q) {
                                            break Label_0144;
                                        }
                                        c = this.c();
                                        if (q) {
                                            break Label_0157;
                                        }
                                        if (c != -1) {
                                            continue;
                                        }
                                    }
                                    break;
                                }
                                value2 = this.h.get(o);
                            }
                            image = value2;
                        }
                    }
                    final byte[] array2;
                    o3 = (array2 = (byte[])image);
                }
            }
            if (q) {
                return o3;
            }
            if (array == null) {
                return image;
            }
        }
        Label_0243: {
            switch (c) {
                case 1: {
                    image = Toolkit.getDefaultToolkit().createImage((byte[])image);
                    dt.a((Image)image);
                    if (q) {
                        break Label_0243;
                    }
                    break;
                }
                case 2: {
                    if (q) {
                        break Label_0243;
                    }
                    break;
                }
                case 3: {
                    image = new String((byte[])image);
                    break;
                }
            }
        }
        o3 = image;
        return o3;
    }
    
    public Object a(final Object o, final int n) {
        return this.a(o, n, null);
    }
    
    public boolean a(final Object o) {
        Object value = o;
        if (!com.easypano.tw.g.q) {
            if (o == null) {
                return false;
            }
            value = this.h.get(o);
        }
        return value != null;
    }
    
    private int d() {
        final boolean q = com.easypano.tw.g.q;
        int o = -1;
        int n = 0;
        ch ch = this;
        final int n5;
        if (!q) {
            if (this.m.a() > 0) {
                o = this.o;
            Label_0104:
                while (true) {
                    Label_0086: {
                        if (!q) {
                            break Label_0086;
                        }
                        ++o;
                        do {
                            int o2;
                            final int n2 = o2 = o;
                            int a;
                            final int n3 = a = this.m.a();
                            if (!q) {
                                if (n2 >= n3) {
                                    o = 0;
                                }
                                final int n4;
                                o2 = (n4 = o);
                                final int o3;
                                a = (o3 = this.o);
                            }
                            if (!q) {
                                if (n2 == n3) {
                                    ++n;
                                    if (!q) {
                                        break Label_0104;
                                    }
                                }
                                o2 = this.o;
                                a = -1;
                            }
                            if (o2 == a) {
                                continue;
                            }
                            break Label_0086;
                        } while (q);
                        if (!q) {
                            break Label_0104;
                        }
                    }
                    if (this.a(this.m.a(o).k)) {
                        continue;
                    }
                    break;
                }
                n5 = o;
                if (q) {
                    return n5;
                }
                if (n5 != this.o) {
                    return n5;
                }
                final int n6 = n;
                if (q) {
                    return n5;
                }
                if (n6 <= 0) {
                    return n5;
                }
                this.b();
                if (!q) {
                    return n5;
                }
            }
            ch = this;
        }
        ch.b();
        return n5;
    }
    
    static void a(final ch ch, final int o) {
        ch.o = o;
    }
    
    static boolean a(final ch ch) {
        return ch.n;
    }
    
    static int b(final ch ch) {
        return ch.o;
    }
    
    static TWViewer c(final ch ch) {
        return ch.l;
    }
    
    static int d(final ch ch) {
        return ch.d();
    }
    
    static ci e(final ch ch) {
        return ch.p;
    }
    
    static void a(final ch ch, final cg j) {
        ch.j = j;
    }
    
    static ck f(final ch ch) {
        return ch.m;
    }
    
    static void b(final ch ch, final int n) {
        ch.a(n);
    }
    
    static Hashtable g(final ch ch) {
        return ch.h;
    }
}
