// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

import java.awt.image.PixelGrabber;
import java.awt.MediaTracker;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.net.URL;
import java.awt.Image;

public class i implements c
{
    private Shout3DViewer a;
    private boolean b;
    protected boolean c;
    protected boolean d;
    protected int[][] e;
    private short[][] f;
    private short[][] g;
    private short[][] h;
    private short[][] i;
    protected short j;
    protected short k;
    protected short l;
    protected Image m;
    protected Image n;
    private URL o;
    private URL p;
    private boolean q;
    public boolean r;
    
    public Object a() {
        return this;
    }
    
    public short f() {
        return this.l;
    }
    
    public short g() {
        return this.k;
    }
    
    public i(final Shout3DViewer a, final URL o) {
        this.b = false;
        this.c = false;
        this.d = false;
        this.m = null;
        this.n = null;
        this.r = false;
        this.a = a;
        this.o = o;
    }
    
    public i(final Shout3DViewer a, final URL o, final boolean b) {
        this.b = false;
        this.c = false;
        this.d = false;
        this.m = null;
        this.n = null;
        this.r = false;
        this.a = a;
        this.o = o;
        this.b = b;
    }
    
    public int[][] h() {
        return this.e;
    }
    
    public int b() {
        return 2;
    }
    
    public short[][] i() {
        if (!this.c) {
            this.l();
        }
        return this.g;
    }
    
    public short[][] j() {
        if (!this.c) {
            this.l();
        }
        return this.i;
    }
    
    void l() {
        this.g = new short[this.k][this.l];
        this.h = new short[this.k][this.l];
        this.i = new short[this.k][this.l];
        for (short n = 0; n < this.l; ++n) {
            for (short n2 = 0; n2 < this.k; ++n2) {
                if (this.j == 2 || this.j == 4) {
                    final float n3 = this.f[n2][n] / 255.0f;
                    this.g[n2][n] = (short)((this.e[n2][n] >> 16 & 0xFF) * n3 + 0.4999f);
                    this.h[n2][n] = (short)((this.e[n2][n] >> 8 & 0xFF) * n3 + 0.4999f);
                    this.i[n2][n] = (short)((this.e[n2][n] & 0xFF) * n3 + 0.4999f);
                }
                else {
                    this.g[n2][n] = (short)(this.e[n2][n] >> 16 & 0xFF);
                    this.h[n2][n] = (short)(this.e[n2][n] >> 8 & 0xFF);
                    this.i[n2][n] = (short)(this.e[n2][n] & 0xFF);
                }
            }
        }
        this.c = true;
    }
    
    public void m() {
        if (this.m == null) {
            return;
        }
        this.e = null;
        boolean b = false;
        this.c = false;
        this.d = false;
        this.j = 0;
        this.k = 0;
        this.l = 0;
        if (this.a instanceof Component) {
            this.k = (short)this.m.getWidth(this.a.b());
            this.l = (short)this.m.getHeight(this.a.b());
        }
        final int[] a = this.a(this.m, 0, 0, this.k, this.l);
        this.e = new int[this.k][this.l];
        int n = 0;
        this.j = 1;
        for (short n2 = 0; n2 < this.l; ++n2) {
            for (short n3 = 0; n3 < this.k; ++n3) {
                final int n4 = a[n] >> 16 & 0xFF;
                final int n5 = a[n] >> 8 & 0xFF;
                final int n6 = a[n] & 0xFF;
                if (this.j == 1 && (n4 != n5 || n4 != n6 || n5 != n6)) {
                    this.j = 3;
                }
                this.e[n3][n2] = a[n++];
            }
        }
        boolean b2 = false;
        if (this.b && this.n != null && this.n.getHeight(this.a.b()) == this.l && this.n.getWidth(this.a.b()) == this.k) {
            int n7 = 0;
            final int[] a2 = this.a(this.n, 0, 0, this.k, this.l);
            this.f = new short[this.k][this.l];
            b2 = true;
            b = true;
            for (short n8 = 0; n8 < this.l; ++n8) {
                for (short n9 = 0; n9 < this.k; ++n9) {
                    this.f[n9][n8] = (short)(a2[n7++] & 0xFF);
                }
            }
        }
        if (this.o.toString().toLowerCase().endsWith(".gif")) {
            for (short n10 = 0; n10 < this.l; ++n10) {
                for (short n11 = 0; n11 < this.k; ++n11) {
                    final int n12 = this.e[n11][n10] >> 24 & 0xFF;
                    if (b2) {
                        if (n12 == 0) {
                            this.f[n11][n10] = 0;
                        }
                    }
                    else {
                        b = true;
                        if (this.f == null) {
                            this.f = new short[this.k][this.l];
                        }
                        this.f[n11][n10] = (short)n12;
                    }
                }
            }
        }
        if (b) {
            ++this.j;
        }
    }
    
    public void c() {
        this.r = true;
        this.m = null;
        if (this.a != null) {
            this.a.a().a(this.a, this);
        }
    }
    
    public void d() {
        final MediaTracker mediaTracker = new MediaTracker(this.a.b());
        this.m = this.a.b().getToolkit().getImage(this.e());
        if (this.b) {
            this.n = this.a.b().getToolkit().getImage(this.o());
        }
        if (mediaTracker != null) {
            try {
                mediaTracker.addImage(this.m, 0);
                mediaTracker.waitForAll();
                if (this.b) {
                    mediaTracker.addImage(this.n, 0);
                    mediaTracker.waitForAll();
                }
            }
            catch (InterruptedException ex) {}
        }
        if (this.b) {
            if (this.n.getWidth(this.a.b()) < 0 || this.n.getHeight(this.a.b()) < 0) {
                this.n = null;
                System.out.println("Failed to load alpha image: " + this.o());
            }
            else {
                System.out.println("Successfully loaded alpha image: " + this.o());
            }
        }
        if (this.m.getWidth(this.a.b()) < 0 || this.m.getHeight(this.a.b()) < 0) {
            this.m = null;
            System.out.println("Failed to load image: " + this.e());
            return;
        }
        System.out.println("Successfully loaded image: " + this.e());
    }
    
    public URL e() {
        return this.o;
    }
    
    public void a(final Object o) {
        this.m();
        this.r = false;
        this.m = null;
    }
    
    public short[][] n() {
        if (!this.c) {
            this.l();
        }
        return this.h;
    }
    
    public URL o() {
        final String string = this.e().toString();
        final String substring = string.substring(0, string.length() - 4);
        try {
            return new URL(substring + "_alpha.gif");
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    void p() {
        this.l();
        this.d = true;
    }
    
    public short[][] q() {
        if (!this.d) {
            this.p();
        }
        return this.f;
    }
    
    int[] a(final Image image, final int n, final int n2, final int n3, final int n4) {
        final int[] array = new int[n3 * n4];
        final PixelGrabber pixelGrabber = new PixelGrabber(image, n, n2, n3, n4, array, 0, n3);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {
            System.err.println(ex);
        }
        return array;
    }
}
