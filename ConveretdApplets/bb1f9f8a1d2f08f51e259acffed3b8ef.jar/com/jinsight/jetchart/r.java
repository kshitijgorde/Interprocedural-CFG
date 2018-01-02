// 
// Decompiled by Procyon v0.5.30
// 

package com.jinsight.jetchart;

import java.awt.Image;
import java.util.Hashtable;
import java.io.IOException;
import java.awt.image.ImageProducer;
import java.io.OutputStream;
import java.awt.image.ColorModel;
import java.awt.image.ImageConsumer;
import java.util.Observable;

abstract class r extends Observable implements ImageConsumer
{
    protected static final String a = "";
    private static final ColorModel b;
    protected OutputStream c;
    protected boolean d;
    private ImageProducer e;
    private int f;
    private int g;
    private int h;
    private boolean i;
    private boolean j;
    private IOException k;
    private Hashtable l;
    private boolean m;
    private int[] n;
    
    void a(final Image image) {
        this.e = image.getSource();
    }
    
    void a(final OutputStream c) {
        try {
            if (this.c != null) {
                this.c.close();
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
            if (GraphSerie.G) {}
        }
        this.c = c;
    }
    
    abstract void a(final int p0, final int p1) throws IOException;
    
    abstract void a(final int p0, final int p1, final int p2, final int p3, final int[] p4, final int p5, final int p6) throws IOException;
    
    abstract void a() throws IOException;
    
    void b() {
        this.d = true;
    }
    
    synchronized void c() throws IOException {
        final boolean g = GraphSerie.G;
        this.j = true;
        this.k = null;
        this.e.startProduction(this);
        while (true) {
            while (true) {
                Label_0042: {
                    if (!g) {
                        break Label_0042;
                    }
                    try {
                        final r r = this;
                        r.wait();
                    }
                    catch (InterruptedException ex) {
                        if (g) {}
                    }
                }
                if (this.j) {
                    continue;
                }
                break;
            }
            final r r = this;
            if (g) {
                continue;
            }
            break;
        }
        if (this.k != null) {
            throw this.k;
        }
    }
    
    private void b(final int n, final int n2, final int n3, final int n4, final int[] array, final int n5, final int n6) throws IOException {
        if (!this.i) {
            this.i = true;
            this.a(this.f, this.g);
            if ((this.h & 0x2) == 0x0) {
                this.m = true;
                this.n = new int[this.f * this.g];
            }
        }
        if (this.m) {
            int n7 = 0;
            while (true) {
                Label_0101: {
                    if (!GraphSerie.G) {
                        break Label_0101;
                    }
                    System.arraycopy(array, n7 * n6 + n5, this.n, (n2 + n7) * this.f + n, n3);
                    ++n7;
                }
                if (n7 < n4) {
                    continue;
                }
                break;
            }
        }
        else {
            this.a(n, n2, n3, n4, array, n5, n6);
        }
    }
    
    private void d() throws IOException {
        if (this.m) {
            this.a(0, 0, this.f, this.g, this.n, 0, this.f);
            this.n = null;
            this.m = false;
        }
    }
    
    private synchronized void e() {
        this.j = false;
        this.notifyAll();
    }
    
    void f() throws IOException {
        if (this.c != null) {
            this.c.flush();
            this.c.close();
        }
    }
    
    public void setDimensions(final int f, final int g) {
        this.f = f;
        this.g = g;
    }
    
    public void setProperties(final Hashtable l) {
        this.l = l;
    }
    
    public void setColorModel(final ColorModel colorModel) {
    }
    
    public void setHints(final int h) {
        this.h = h;
    }
    
    public void setPixels(final int n, final int n2, final int n3, final int n4, final ColorModel colorModel, final byte[] array, final int n5, final int n6) {
        final boolean g = GraphSerie.G;
        final int[] array2 = new int[n3];
        int n7 = 0;
    Label_0058_Outer:
        while (true) {
            Label_0108: {
                if (!g) {
                    break Label_0108;
                }
                final int n8 = n5 + n7 * n6;
                int n9 = 0;
                while (true) {
                    while (true) {
                        Label_0061: {
                            if (!g) {
                                break Label_0061;
                            }
                            array2[n9] = colorModel.getRGB(array[n8 + n9] & 0xFF);
                            ++n9;
                        }
                        if (n9 < n3) {
                            continue Label_0058_Outer;
                        }
                        break;
                    }
                    try {
                        this.b(n, n2 + n7, n3, 1, array2, 0, n3);
                        if (g) {
                            continue;
                        }
                    }
                    catch (IOException k) {
                        this.k = k;
                        this.e();
                        return;
                    }
                    break;
                }
                ++n7;
            }
            if (n7 >= n4) {
                return;
            }
            continue;
        }
    }
    
    public void setPixels(final int n, final int n2, final int n3, final int n4, final ColorModel colorModel, final int[] array, final int n5, final int n6) {
        final boolean g = GraphSerie.G;
        if (colorModel == r.b) {
            try {
                this.b(n, n2, n3, n4, array, n5, n6);
            }
            catch (IOException k) {
                this.k = k;
                this.e();
                return;
            }
            if (!g) {
                return;
            }
        }
        final int[] array2 = new int[n3];
        int n7 = 0;
    Label_0100_Outer:
        while (true) {
            Label_0150: {
                if (!g) {
                    break Label_0150;
                }
                final int n8 = n5 + n7 * n6;
                int n9 = 0;
                while (true) {
                    while (true) {
                        Label_0103: {
                            if (!g) {
                                break Label_0103;
                            }
                            array2[n9] = colorModel.getRGB(array[n8 + n9]);
                            ++n9;
                        }
                        if (n9 < n3) {
                            continue Label_0100_Outer;
                        }
                        break;
                    }
                    try {
                        this.b(n, n2 + n7, n3, 1, array2, 0, n3);
                        if (g) {
                            continue;
                        }
                    }
                    catch (IOException i) {
                        this.k = i;
                        this.e();
                        return;
                    }
                    break;
                }
                ++n7;
            }
            if (n7 < n4) {
                continue;
            }
            break;
        }
    }
    
    public void imageComplete(final int n) {
        final boolean g = GraphSerie.G;
        this.e.removeConsumer(this);
        Label_0062: {
            if (n == 4) {
                this.k = new IOException(a("2{WM\u0006{wTE\u0011/sR"));
                if (!g) {
                    break Label_0062;
                }
            }
            try {
                this.d();
                this.a();
            }
            catch (IOException k) {
                this.k = k;
                if (g) {}
            }
        }
        this.e();
    }
    
    r(final Image image, final OutputStream outputStream) throws IOException {
        this(image.getSource(), outputStream);
    }
    
    r(final ImageProducer e, final OutputStream c) throws IOException {
        this.f = -1;
        this.g = -1;
        this.h = 0;
        this.i = false;
        this.l = null;
        this.m = false;
        this.e = e;
        this.c = c;
    }
    
    r() {
        this.f = -1;
        this.g = -1;
        this.h = 0;
        this.i = false;
        this.l = null;
        this.m = false;
    }
    
    static {
        b = ColorModel.getRGBdefault();
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
                            c2 = '[';
                            break;
                        }
                        case 1: {
                            c2 = '\u0016';
                            break;
                        }
                        case 2: {
                            c2 = '6';
                            break;
                        }
                        case 3: {
                            c2 = '*';
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
