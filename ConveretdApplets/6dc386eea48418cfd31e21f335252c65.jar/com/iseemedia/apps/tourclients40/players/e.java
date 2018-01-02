// 
// Decompiled by Procyon v0.5.30
// 

package com.iseemedia.apps.tourclients40.players;

import java.net.MalformedURLException;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import com.iseemedia.apps.tourclients40.toolbar.g;
import com.iseemedia.apps.tourclients40.iip.d;
import java.awt.Component;
import java.awt.LayoutManager;
import com.iseemedia.apps.tourclients40.toolbar.a;
import java.awt.Frame;
import java.applet.AppletContext;
import java.net.URL;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Image;
import java.awt.Font;
import java.awt.Dimension;
import com.iseemedia.apps.tourclients40.iip.f;
import com.iseemedia.apps.tourclients40.iip.b;

public final class e extends h implements b
{
    private boolean P;
    private String Q;
    private String R;
    private boolean S;
    private boolean T;
    private String U;
    private f V;
    private f W;
    private int X;
    private float Y;
    private float Z;
    private float aa;
    private float ab;
    private float ac;
    private float ad;
    private float ae;
    private float af;
    private int ag;
    private int ah;
    private Dimension[] ai;
    private Dimension[] aj;
    private float ak;
    private boolean al;
    private boolean am;
    private float an;
    private float ao;
    private float ap;
    private float aq;
    private float ar;
    public Font a;
    private Image as;
    private com.iseemedia.image.b at;
    private Rectangle au;
    private Color av;
    private com.iseemedia.apps.tourclients40.util.b aw;
    private boolean ax;
    private boolean ay;
    private int az;
    private int aA;
    private int aB;
    private int aC;
    private int aD;
    private int aE;
    private boolean aF;
    private float aG;
    private float aH;
    private float aI;
    private float aJ;
    private float aK;
    private float aL;
    private boolean aM;
    private float aN;
    private float aO;
    private int aP;
    private int aQ;
    private int aR;
    private int aS;
    private int aT;
    private int aU;
    private int aV;
    private int aW;
    private int aX;
    private float aY;
    private float aZ;
    private float ba;
    private float bb;
    private float bc;
    private float bd;
    private int be;
    private int bf;
    private float bg;
    private float bh;
    private float bi;
    private float bj;
    private long bk;
    float[] b;
    float[] c;
    float[] d;
    float[] e;
    float[] f;
    float[] g;
    float[] h;
    float[] i;
    long j;
    private int bl;
    double k;
    private boolean bm;
    private boolean bn;
    private boolean bo;
    private float bp;
    private g bq;
    private c br;
    private c bs;
    private Thread bt;
    private Thread bu;
    
    public e(final URL url, final AppletContext o, final Dimension dimension, final Frame q, final boolean af, final String s, final com.iseemedia.apps.tourclients40.applets.b n, final a v) {
        this.P = false;
        this.S = true;
        this.T = false;
        this.U = null;
        this.V = null;
        this.W = null;
        this.ag = 0;
        this.ah = 0;
        this.ai = new Dimension[32];
        this.aj = new Dimension[32];
        this.ak = 0.75f;
        this.al = false;
        this.am = false;
        this.an = 0.0f;
        this.ao = 1.0f;
        this.aq = 1.5f;
        this.ar = 0.0f;
        this.as = null;
        this.at = null;
        this.au = null;
        this.aw = null;
        this.ax = false;
        this.ay = false;
        this.b = new float[2];
        this.c = new float[2];
        this.d = new float[2];
        this.e = new float[2];
        this.f = new float[2];
        this.g = new float[2];
        this.h = new float[2];
        this.i = new float[2];
        this.j = 10L;
        this.bl = 1;
        this.bm = false;
        this.bn = true;
        this.bp = 0.0f;
        this.br = null;
        this.bs = null;
        this.bt = null;
        this.bu = null;
        super.n = n;
        this.aF = af;
        super.o = o;
        this.setLayout(null);
        this.aM = false;
        super.q = q;
        (super.v = v).a();
        this.c(0);
        this.reshape(0, 0, dimension.width, dimension.height);
        this.aw = new com.iseemedia.apps.tourclients40.util.b(this, super.o, !this.aF, v);
        super.E = false;
        final int n2 = -1000;
        this.aA = n2;
        this.az = n2;
        this.aE = n2;
        this.aD = n2;
        this.aC = n2;
        this.aB = n2;
        this.aG = 0.0f;
        this.aH = 3.1415927f;
        this.aI = 0.0f;
        this.aJ = 3.1415927f;
        this.aK = this.aJ;
        this.Y = -1.5707964f;
        this.Z = 1.5707964f;
        this.aa = 0.0f;
        this.ab = 0.0f;
        this.aL = 0.0f;
        this.bq = new Object(this) {
            private boolean a = false;
            
            public final synchronized void a() {
                while (this.a) {
                    try {
                        this.wait();
                    }
                    catch (InterruptedException ex) {}
                }
            }
            
            public final synchronized void b() {
                this.a = false;
                this.notifyAll();
            }
            
            public final synchronized void c() {
                this.a = true;
                this.notifyAll();
            }
        };
        this.bo = false;
    }
    
    protected final void a() {
        this.bm = false;
        super.u = (super.t == 0.0f);
        if (this.S) {
            if (this.au == null) {
                if (com.iseemedia.apps.tourclients40.players.h.l == 1) {
                    this.au = new Rectangle(0, 0, this.bounds().width, this.bounds().height - super.v.c);
                }
                else {
                    this.au = new Rectangle(0, 0, this.bounds().width, this.bounds().height);
                }
            }
            if (this.U == null) {
                this.a("Could not load the FlashPix image.", "Verify that the URL is correct in the html file.");
                return;
            }
            this.getGraphics();
            this.V = null;
            this.W = null;
            try {
                this.V = new f(this.U, this, this, 0, 0);
            }
            catch (d d) {
                this.a("Image Exception ... ", d.getMessage());
                System.out.println("***********error iip Image Exception ...  " + d.getMessage());
                this.V = null;
            }
            catch (Exception ex) {
                this.V = null;
                this.a("Could not load the FlashPix image.", "Verify that the URL is correct in the html file.");
            }
            try {
                this.W = new f(this.U, this, this, 0, 1);
            }
            catch (d d2) {
                this.a("Image Exception ... ", d2.getMessage());
                System.out.println("***********error iip Image Exception ...  " + d2.getMessage());
                this.W = null;
            }
            catch (Exception ex2) {
                this.W = null;
                this.a("Could not load the FlashPix image.", "Verify that the URL is correct in the html file.");
            }
        }
        if (super.y == null) {
            (super.y = new com.iseemedia.apps.tourclients40.toolbar.g(this, this.bounds().height, super.o, true)).a(2);
        }
        this.c(2);
        this.requestFocus();
        this.j = 20L;
        this.k = 0.0;
        if (!super.u) {
            this.b();
        }
    }
    
    protected final void b() {
        if (super.t < 0.0f) {
            this.bl = 1;
        }
        else {
            this.bl = -1;
        }
        this.k = 0.0;
        if (this.ag != 0) {
            this.k = 1.0 / (this.as.getWidth(this) / (2.0 * Math.sin((this.Z - this.Y) / 1.2)));
        }
    }
    
    protected final void c() {
        super.w = false;
        this.bm = true;
        super.o = null;
        if (this.V != null) {
            this.V.a(true);
            this.V.g();
            this.V = null;
        }
        if (this.W != null) {
            this.W.a(true);
            this.W.g();
            this.W = null;
        }
        if (this.aw != null) {
            this.aw = null;
        }
        if (this.as != null) {
            this.as.flush();
        }
        if (this.br != null) {
            if (this.br.a != null) {
                this.br.a.b();
            }
            this.br.a = null;
        }
        this.br = null;
        if (this.bs != null) {
            if (this.bs.a != null) {
                this.bs.a.b();
            }
            this.bs.a = null;
        }
        this.bs = null;
        this.removeAll();
        this.as = null;
        this.at = null;
        this.bu = null;
        this.au = null;
        this.bq = null;
    }
    
    public final float d() {
        if (this.aM) {
            return this.aH;
        }
        return -1.0f;
    }
    
    protected final boolean e() {
        if (this.Q != null) {
            return false;
        }
        if (super.K) {
            this.m();
            super.K = false;
        }
        if (!super.u) {
            if (super.D && this.p()) {
                this.o();
                super.n.e();
            }
            final float[] array;
            (array = new float[3])[0] = 0.0f;
            array[2] = (array[1] = 0.0f);
            final float[] i = this.i();
            long bk = System.currentTimeMillis();
            if (this.S) {
                this.bk = System.currentTimeMillis();
                bk = this.bk;
            }
            if (this.T) {
                this.T = false;
                this.bk = System.currentTimeMillis();
                bk = this.bk;
            }
            final float[] array2 = i;
            final int n = 1;
            array2[n] += (float)(this.bl * this.k * ((bk - this.bk) * Math.abs(super.t) / 1000.0f));
            this.bk = bk;
            i[0] = this.an;
            i[2] = this.ap;
            this.a(this.aq);
            try {
                this.g();
            }
            catch (Exception ex) {}
            this.a(i);
            this.i();
            try {
                Thread.currentThread();
                Thread.sleep(this.j);
            }
            catch (InterruptedException ex2) {
                this.f();
            }
            return true;
        }
        if (super.E) {
            if (super.r == 0) {
                this.k();
                this.y();
            }
            else if (super.r == 1) {
                this.l();
                this.y();
            }
            this.d(4);
        }
        if (super.w && !this.bm) {
            super.w = false;
            this.g();
            return true;
        }
        return false;
    }
    
    public final void a(final int n) {
        if (n == 100) {
            this.aw.b();
        }
        super.w = true;
    }
    
    public final void f() {
        super.u = true;
    }
    
    public final void a(final String u, final int x, final float y, final float z, final float aa, final float ab, final float ac, final float ad, final float ae, final float af, final float ar, final boolean z2) {
        this.U = u;
        super.z = z2;
        this.X = x;
        if (this.X != 90 && this.X != 91) {
            this.a("This player only supports", "Sphere and Cylinder Panoramas.");
        }
        this.b(this.ar = ar);
        this.Y = y;
        this.Z = z;
        this.aa = aa;
        this.ab = ab;
        this.ac = ac;
        this.ad = ad;
        this.ae = ae;
        this.af = af;
        if (this.X == 91) {
            if (this.Y < -1.4137167f) {
                this.Y = -1.4137167f;
            }
            if (this.Z > 1.4137167f) {
                this.Z = 1.4137167f;
            }
        }
        else if (this.X == 90) {
            if (this.Y < -1.5707964f) {
                this.Y = -1.5707964f;
            }
            if (this.Z > 1.5707964f) {
                this.Z = 1.5707964f;
            }
        }
    }
    
    private boolean w() {
        if (this.V == null) {
            return false;
        }
        if (this.W == null) {
            return false;
        }
        if (this.ah > 0) {
            return true;
        }
        final Dimension dimension = new Dimension();
        if (!this.V.a(dimension)) {
            return false;
        }
        if (!this.W.a(dimension)) {
            return false;
        }
        this.ag = dimension.width;
        this.ah = this.V.j;
        if (this.ah <= 0) {
            return false;
        }
        this.ah = this.W.j;
        if (this.ah <= 0) {
            return false;
        }
        for (int i = this.ah; i >= 0; --i) {
            this.ai[i] = this.V.b(i);
            this.aj[i] = this.V.a(i);
        }
        this.aW = this.z();
        this.b();
        return true;
    }
    
    private void a(final String q, final String r) {
        if (this.Q != null) {
            return;
        }
        this.Q = q;
        this.R = r;
        super.w = true;
    }
    
    public final void paint(final Graphics graphics) {
        if ((this.S || this.T) && super.z) {
            this.a(graphics);
        }
        if (this.Q != null) {
            this.g();
        }
        super.w = true;
    }
    
    private void c(final Graphics graphics) {
        graphics.setColor(this.av);
        graphics.fillRect(0, 0, this.size().width, this.au.y);
        graphics.fillRect(0, 0, this.au.x, this.size().height);
        graphics.fillRect(this.au.x + this.au.width, 0, this.size().width - (this.au.x + this.au.width), this.size().height);
        graphics.fillRect(0, this.au.y + this.au.height, this.size().width, this.size().height - (this.au.y + this.au.height));
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private void d(final int n) {
        super.u = true;
        final float n2 = 0.3f;
        final float n3 = 0.3f;
        float n5;
        float n4 = n5 = 0.0f;
        final float j = this.j();
        final float[] i = this.i();
        if (super.E) {
            final float n6 = this.aD - this.aB;
            final float n7 = this.aC - this.aE;
            final float n8 = n6 / this.bg;
            final float n9 = n7 / this.bh;
            float n10;
            if (n8 < 0.0f) {
                n10 = n8 * -n8;
            }
            else {
                n10 = n8 * n8;
            }
            float n11;
            if (n9 < 0.0f) {
                n11 = n9 * -n9;
            }
            else {
                n11 = n9 * n9;
            }
            final float[] array = i;
            final int n12 = 0;
            array[n12] += n11 * n3 * j;
            final float[] array2 = i;
            final int n13 = 1;
            array2[n13] += n10 * n2 * j;
        }
        else {
            switch (n) {
                case 0: {
                    n5 = 0.0f - 0.1f;
                    break;
                }
                case 1: {
                    n5 = 0.0f + 0.1f;
                    break;
                }
                case 2: {
                    n4 = 0.0f + 0.1f;
                    break;
                }
                case 3: {
                    n4 = 0.0f - 0.1f;
                    break;
                }
            }
            final float[] array3 = i;
            final int n14 = 0;
            array3[n14] += n4 * j;
            final float[] array4 = i;
            final int n15 = 1;
            array4[n15] += n5 * j;
        }
        i[2] = 0.0f;
        this.a(i);
        super.w = true;
    }
    
    public final void a(final Color av) {
        this.av = av;
    }
    
    public final void g() {
        if (this.au == null) {
            return;
        }
        if (this.Q != null) {
            final Graphics graphics;
            (graphics = this.getGraphics()).setFont(this.a);
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            graphics.setColor(this.av);
            graphics.fillRect(this.au.x, this.au.y, this.au.width, this.au.height);
            graphics.setColor(this.av);
            graphics.drawString(this.Q, this.au.x + (this.au.width >> 1) - (fontMetrics.stringWidth(this.Q) >> 1), this.au.y + (this.au.height >> 1));
            if (this.R != null) {
                graphics.drawString(this.R, this.au.x + (this.au.width >> 1) - (fontMetrics.stringWidth(this.R) >> 1), this.au.y + (this.au.height >> 1) + fontMetrics.getHeight());
            }
            graphics.dispose();
            return;
        }
        if (this.as == null) {
            this.as = this.createImage(this.size().width, this.size().height);
            final Graphics graphics2;
            (graphics2 = this.as.getGraphics()).setColor(this.av);
            graphics2.fillRect(0, 0, this.size().width, this.size().height);
            this.a(graphics2);
            graphics2.dispose();
        }
        if (this.at == null) {
            this.bg = this.au.width * 0.5f;
            this.bh = this.au.height * 0.5f;
            this.bi = this.au.width;
            this.bj = this.au.height;
        }
        if (super.m && super.M && super.L) {
            super.L = false;
            new Thread(new com.iseemedia.apps.tourclients40.players.d(this), "Player").start();
        }
        try {
            if (!this.h()) {
                return;
            }
            super.m = false;
            super.A = 0;
        }
        catch (Exception ex) {}
        final Graphics graphics3 = this.as.getGraphics();
        if (!this.T) {
            this.c(graphics3);
            if (this.br != null) {
                graphics3.drawImage(this.br.a.a, this.au.x, this.au.y, this.au.width, this.au.height / 2, null);
            }
            if (this.bs != null) {
                graphics3.drawImage(this.bs.a.a, this.au.x, this.au.y + this.au.height / 2, this.au.width, this.au.height / 2, null);
            }
        }
        graphics3.dispose();
        final Graphics graphics4;
        (graphics4 = this.getGraphics()).drawImage(this.as, 0, 0, null);
        if (super.s) {
            super.n.b();
            super.s = false;
        }
        graphics4.dispose();
    }
    
    public final void a(final float an, final float ao, final float ap, final float aq) {
        this.an = an;
        this.ao = ao;
        this.ap = ap;
        this.aq = aq;
    }
    
    private void a(final int n, final int n2, final float[] array) {
        final float n3 = n / this.bi;
        final float n4 = n2 / this.bj;
        final float n5 = this.aJ * 0.5f;
        final float n6 = (float)Math.atan(this.bg * Math.tan(n5) / this.bh);
        float n7;
        if ((n7 = (n3 - 0.5f) * 2.0f) < 0.005 && n7 > -0.005) {
            n7 = 0.0f;
        }
        float n8;
        if ((n8 = (0.5f - n4) * 2.0f) < 0.005 && n8 > -0.005) {
            n8 = 0.0f;
        }
        final float ba = this.ba;
        final float bb = this.bb;
        final float n9 = (float)Math.tan(n5);
        final float n10 = ba - n9 * n8 * bb;
        final float n11 = bb + n9 * n8 * ba;
        final float n12 = (float)Math.tan(n6) * n7;
        float n13;
        if (n10 < 1.0E-4f && n10 > -1.0E-4f) {
            n13 = 0.0f;
        }
        else if (n10 < 0.0f) {
            n13 = (float)Math.atan(n12 / n10) + 3.1415927f;
        }
        else {
            n13 = (float)Math.atan(n12 / n10);
        }
        final float n14;
        float n15;
        if ((n14 = n10 / (float)Math.cos(n13)) < 1.0E-4f && n14 > -1.0E-4f) {
            if (n11 > 0.0f) {
                n15 = 1.5707964f;
            }
            else {
                n15 = -1.5707964f;
            }
        }
        else {
            n15 = (float)Math.atan(n11 / n14);
        }
        float n16 = n13 + this.aH;
        if (n15 <= this.Y) {
            n15 = -n15 - 3.1415927f;
        }
        else if (n15 > this.Z) {
            n15 = 3.1415927f - n15;
        }
        if (this.ab - this.aa < 0.001f) {
            if (n16 < 0.0f) {
                n16 += 6.2831855f;
            }
            else if (n16 > 6.2831855f) {
                n16 -= 6.2831855f;
            }
        }
        final float[] array2 = new float[2];
        this.a(n16, n15, array2);
        final float[] array3 = new float[2];
        final float[] array4 = new float[2];
        if (this.ab - this.aa < 0.001f) {
            this.a(0.0f, this.Y, array3);
            this.a(6.2831855f, this.Z, array4);
        }
        else {
            this.a(this.aa, this.Y, array3);
            this.a(this.ab, this.Z, array4);
        }
        if (Math.abs(array4[0] - array3[0]) > 1.0E-6f) {
            array[0] = (array2[0] - array3[0]) / (array4[0] - array3[0]);
            array[1] = (array4[1] - array2[1]) / (array4[1] - array3[1]);
            return;
        }
        array[0] = array2[0];
        array[1] = array2[1];
    }
    
    private void a(final float n, final float n2, final float[] array) {
        if (this.X == 90) {
            array[0] = n;
            array[1] = n2;
            return;
        }
        array[0] = n;
        final float n3 = (float)Math.tan(this.Z);
        if (n2 > this.Z) {
            array[1] = n3 + 1.0f - n3 * (float)Math.tan(1.5707964f - n2);
            return;
        }
        if (n2 < -this.Z) {
            array[1] = -n3 - (1.0f - n3 * (float)Math.tan(1.5707964f + n2));
            return;
        }
        array[1] = (float)Math.tan(n2);
    }
    
    private float a(final float n, final float n2, final float n3) {
        if (n < n2) {
            return n2;
        }
        if (n > n3) {
            return n3;
        }
        return n;
    }
    
    private int a(final int n, final int n2, final int n3) {
        if (n < n2) {
            return n2;
        }
        if (n > n3) {
            return n3;
        }
        return n;
    }
    
    public final void a(final float[] array) {
        this.aG = array[0];
        this.aH = array[1];
        this.aI = array[2];
        final float n = this.aJ * 0.5f;
        if (Math.abs(this.af - this.ae) > 0.001f) {
            this.aH = this.a(this.aH, this.ae, this.af);
        }
        if (this.aG > 1.5697963f) {
            this.aG = 1.5697963f;
        }
        if (this.aG < -1.5697963f) {
            this.aG = -1.5697963f;
        }
        if (this.ab - this.aa > 0.001f) {
            final float n2 = (float)Math.atan(this.bg * Math.tan(this.aJ * 0.5) / this.bh);
            if (this.aH + n2 > this.ab - this.bp) {
                this.aH = this.ab - n2 - this.bp;
                this.bl *= -1;
            }
            if (this.aH - n2 < this.aa + this.bp) {
                this.aH = this.aa + n2 + this.bp;
                this.bl *= -1;
            }
            if (this.aH > this.ab || this.aH < this.aa) {
                this.aH = (this.ab - this.aa) / 2.0f;
            }
        }
        while (this.aH < 0.0f) {
            this.aH += 6.2831855f;
        }
        while (this.aH > 6.2831855f) {
            this.aH -= 6.2831855f;
        }
        if (this.ad < 1.5707964f && this.aG + n > this.ad - 1.0E-4f) {
            this.aG = this.ad - n - 1.0E-4f;
        }
        if (this.ac > -1.5707964f && this.aG - n < this.ac + 1.0E-4f) {
            this.aG = this.ac + n + 1.0E-4f;
        }
        this.aY = (float)Math.cos(this.aH);
        this.aZ = (float)Math.sin(this.aH);
        this.ba = (float)Math.cos(this.aG);
        this.bb = (float)Math.sin(this.aG);
        this.bc = (float)Math.cos(this.aI);
        this.bd = (float)Math.sin(this.aI);
    }
    
    public final void a(final float aj) {
        this.aK = this.aJ;
        this.aJ = aj;
        if (this.aJ < this.aL) {
            this.aJ = this.aL;
            if (this.aw != null) {
                this.aw.c();
            }
        }
        if (this.aJ > this.Z - this.Y - 1.0E-4f) {
            this.aJ = this.Z - this.Y - 1.0E-4f;
        }
        if (this.aJ > this.ad - this.ac - 1.0E-4f) {
            this.aJ = this.ad - this.ac - 1.0E-4f;
        }
        float n;
        if ((n = 2.0f * (float)Math.atan(this.bg * Math.tan(this.aJ * 0.5) / this.bh)) > 2.094f) {
            n = 2.094f;
            this.aJ = 2.0f * (float)Math.atan(this.bh * Math.tan(n * 0.5) / this.bg);
        }
        if (this.ab - this.aa > 0.05f && n > this.ab - this.aa - 0.05f) {
            n = this.ab - this.aa - 0.05f;
            this.aJ = 2.0f * (float)Math.atan(this.bh * Math.tan(n * 0.5) / this.bg);
        }
        if (this.af - this.ae > 0.05f && n > this.af - this.ae - 0.05f) {
            this.aJ = 2.0f * (float)Math.atan(this.bh * Math.tan((this.af - this.ae - 0.05f) * 0.5) / this.bg);
        }
    }
    
    private void x() {
        final float[] array = new float[3];
        final float[] i = this.i();
        array[0] = 0.0f;
        array[1] = i[1];
        array[2] = i[2];
        this.a(array);
        this.a(0, this.au.height, this.b);
        this.a(this.au.width, this.au.height, this.d);
        this.a(this.au.width, 0, this.e);
        this.a(0, 0, this.g);
        float n = Math.min(this.b[0], this.g[0]);
        float n2 = Math.max(this.d[0], this.e[0]);
        if (Math.max(this.b[0], this.g[0]) - Math.min(this.b[0], this.g[0]) > 0.5f) {
            n = Math.max(this.b[0], this.g[0]);
        }
        else if (Math.max(this.d[0], this.e[0]) - Math.min(this.d[0], this.e[0]) > 0.5f) {
            n2 = Math.min(this.d[0], this.e[0]);
        }
        final float a = this.a(n, 0.0f, 1.0f);
        final float a2 = this.a(n2, 0.0f, 1.0f);
        float n3;
        if (a > a2) {
            n3 = (a2 - a + 1.0f) * this.ag;
        }
        else {
            n3 = (a2 - a) * this.ag;
        }
        if (n3 <= this.bi * this.ak && this.aV == this.ah) {
            this.aL = this.aK;
        }
        this.a(i);
    }
    
    private void y() {
        final float[] array = new float[3];
        final float[] i = this.i();
        array[0] = 3.0f;
        array[1] = (this.aa + this.ab) / 2.0f;
        array[2] = i[2];
        this.a(array);
        final float[] array2 = new float[2];
        final float[] array3 = new float[2];
        this.a(this.au.width, 0, array2);
        this.a(this.au.width, this.au.height, array3);
        this.bp = (this.ab - this.aa) * (array2[0] - array3[0]);
        this.bp += 0.05f;
        this.a(i);
    }
    
    private int z() {
        final float[] array = new float[3];
        final float[] i = this.i();
        array[0] = 0.0f;
        array[1] = i[1];
        array[2] = i[2];
        this.a(array);
        this.a(0, this.au.height, this.b);
        this.a(this.au.width, this.au.height, this.d);
        this.a(this.au.width, 0, this.e);
        this.a(0, 0, this.g);
        float n = Math.min(this.b[0], this.g[0]);
        float n2 = Math.max(this.d[0], this.e[0]);
        if (Math.max(this.b[0], this.g[0]) - Math.min(this.b[0], this.g[0]) > 0.5f) {
            n = Math.max(this.b[0], this.g[0]);
        }
        else if (Math.max(this.d[0], this.e[0]) - Math.min(this.d[0], this.e[0]) > 0.5f) {
            n2 = Math.min(this.d[0], this.e[0]);
        }
        final float a = this.a(n, 0.0f, 1.0f);
        final float a2 = this.a(n2, 0.0f, 1.0f);
        float n3;
        if (a > a2) {
            n3 = (a2 - a + 1.0f) * this.ag;
        }
        else {
            n3 = (a2 - a) * this.ag;
        }
        int j = this.ah;
        if (n3 > this.bi * this.ak) {
            j = this.ah;
            while (j >= 3) {
                if (n3 >= this.bi * this.ak) {
                    n3 /= 2.0f;
                    if (j == 3) {
                        break;
                    }
                    --j;
                }
                else {
                    if (j < this.ah) {
                        ++j;
                        break;
                    }
                    break;
                }
            }
        }
        this.a(i);
        return j;
    }
    
    private void A() {
        if (this.ag <= 3000) {
            this.aX = 32;
        }
        else {
            this.aX = 64;
        }
        final float[] array = new float[3];
        final float[] i = this.i();
        array[0] = 0.0f;
        array[1] = i[1];
        array[2] = i[2];
        this.a(array);
        this.a(0, this.au.height, this.b);
        this.a(this.au.width, this.au.height, this.d);
        this.a(this.au.width, 0, this.e);
        this.a(0, 0, this.g);
        float n = Math.min(this.b[0], this.g[0]);
        float n2 = Math.max(this.d[0], this.e[0]);
        if (Math.max(this.b[0], this.g[0]) - Math.min(this.b[0], this.g[0]) > 0.5f) {
            n = Math.max(this.b[0], this.g[0]);
        }
        else if (Math.max(this.d[0], this.e[0]) - Math.min(this.d[0], this.e[0]) > 0.5f) {
            n2 = Math.min(this.d[0], this.e[0]);
        }
        final float a = this.a(n, 0.0f, 1.0f);
        final float a2 = this.a(n2, 0.0f, 1.0f);
        float n3;
        if (a > a2) {
            n3 = (a2 - a + 1.0f) * this.ag;
        }
        else {
            n3 = (a2 - a) * this.ag;
        }
        this.aV = this.ah;
        if (n3 > this.bi * this.ak) {
            this.aV = this.ah;
            while (this.aV >= 3) {
                if (n3 < this.bi * this.ak) {
                    if (this.aV < this.ah) {
                        ++this.aV;
                        break;
                    }
                    break;
                }
                else {
                    n3 /= 2.0f;
                    if (this.aV == this.aW) {
                        break;
                    }
                    this.aX *= 2;
                    --this.aV;
                }
            }
            this.aX = this.a(this.aX, 32, 64);
        }
        this.aN = this.ai[this.aV].height / 64.0f;
        this.aO = this.ai[this.aV].width / 64.0f;
        if (this.aX == 32) {
            this.aN *= 2.0f;
            this.aO *= 2.0f;
        }
        this.a(i);
    }
    
    public final boolean h() {
        this.P = false;
        if (this.au.height <= 0 || this.au.width <= 0) {
            return false;
        }
        if (!this.w()) {
            return false;
        }
        this.V.q = 1;
        this.V.a();
        this.W.q = 1;
        this.W.a();
        this.x();
        this.a(this.aJ);
        if (this.ag < 500.0f) {
            this.a("FlashPix panoramas must be", "greater than 500 pixels wide.");
            return false;
        }
        this.bf = (int)(1.0f / (float)Math.tan(this.aJ * 0.5f) * 16384.0f);
        this.be = (int)(this.bf / (this.bg / this.bh));
        this.a(0, this.au.height, this.b);
        this.a(this.au.width / 2, this.au.height, this.c);
        this.a(this.au.width, this.au.height, this.d);
        this.a(this.au.width, 0, this.e);
        this.a(this.au.width / 2, 0, this.f);
        this.a(0, 0, this.g);
        this.a(0, this.au.height / 2, this.h);
        this.a(this.au.width / 2, this.au.height / 2, this.i);
        float max = Math.max(Math.max(this.c[1], this.d[1]), this.e[1]);
        float min = Math.min(Math.min(this.f[1], this.e[1]), this.d[1]);
        float n = Math.min(this.b[0], this.g[0]);
        float n2 = Math.max(this.d[0], this.e[0]);
        if (Math.max(this.b[0], this.g[0]) - Math.min(this.b[0], this.g[0]) > 0.5f) {
            n = Math.max(this.b[0], this.g[0]);
        }
        else if (Math.max(this.d[0], this.e[0]) - Math.min(this.d[0], this.e[0]) > 0.5f) {
            n2 = Math.min(this.d[0], this.e[0]);
        }
        if (n > n2) {
            this.P = true;
        }
        if (this.X == 90 && (this.f[0] > this.c[0] + 0.1f || this.f[0] < this.c[0] - 0.1f)) {
            if (this.aG > 0.0f) {
                n2 = 1.0f;
                n = 0.0f;
                min = 0.0f;
            }
            else if (this.aG < 0.0f) {
                n2 = 1.0f;
                n = 0.0f;
                max = 1.0f;
            }
        }
        final float a = this.a(max, 0.0f, 1.0f);
        final float a2 = this.a(min, 0.0f, 1.0f);
        final float a3 = this.a(n, 0.0f, 1.0f);
        final float a4 = this.a(n2, 0.0f, 1.0f);
        this.A();
        if (this.S) {
            this.aw.a();
            try {
                this.V.d(3);
            }
            catch (d d) {
                this.a("Image Exception ... ", d.getMessage());
                System.out.println("***********error iip Image Exception ...  " + d.getMessage());
                this.V = null;
            }
            catch (Exception ex) {
                this.V = null;
                this.a("Could not load the FlashPix image.", "Verify that the URL is correct in the html file.");
            }
            try {
                this.W.d(3);
            }
            catch (d d2) {
                this.a("Image Exception ... ", d2.getMessage());
                System.out.println("***********error iip Image Exception ...  " + d2.getMessage());
                this.W = null;
            }
            catch (Exception ex2) {
                this.W = null;
                this.a("Could not load the FlashPix image.", "Verify that the URL is correct in the html file.");
            }
            this.y();
            this.aw.b();
        }
        final int n3 = (int)Math.ceil(this.aN);
        final int n4 = (int)Math.ceil(this.aO);
        float n5;
        float n6;
        if (this.h[1] >= this.i[1]) {
            n5 = this.i[1];
            n6 = this.h[1];
        }
        else {
            n5 = this.h[1];
            n6 = this.i[1];
        }
        if (this.P) {
            this.aP = (int)(a3 * this.aO);
            this.aP -= 3;
            this.aP = this.a(this.aP, 0, n4);
            this.aQ = (int)Math.ceil(this.aO);
            this.aR = (int)(a2 * this.aN);
            --this.aR;
            this.aR = this.a(this.aR, 0, n3);
            this.aS = (int)Math.ceil(n6 * this.aN) + 1;
            ++this.aS;
            this.aT = (int)(n5 * this.aN) - 1;
            --this.aT;
            this.aS = this.a(this.aS, 0, n3);
            this.aT = this.a(this.aT, 0, n3);
            this.aU = (int)Math.ceil(a * this.aN);
            ++this.aU;
            this.aU = this.a(this.aU, 0, n3);
            int ap = this.aP;
            int aq = this.aQ;
            this.B();
            this.C();
            this.D();
            this.aP = 0;
            this.aQ = (int)Math.ceil(a4 * this.aO);
            this.aQ += 3;
            this.aQ = this.a(this.aQ, 0, n4);
            this.B();
            this.C();
            this.D();
            if (this.al && !com.iseemedia.apps.tourclients40.iip.a.d()) {
                if (this.aX == 32) {
                    this.aR = (this.aR + 1) / 2;
                    this.aP = (this.aP + 1) / 2;
                    this.aS = (this.aS + 1) / 2;
                    this.aQ = (this.aQ + 1) / 2;
                    ap = (ap + 1) / 2;
                    aq = (aq + 1) / 2;
                }
                final Rectangle rectangle = new Rectangle(0, this.aR, this.aj[this.aV].width, this.aS - this.aR);
                this.V.a(true);
                this.V.c(this.aV);
                rectangle.x = this.a(ap, 0, this.aj[this.aV].width);
                rectangle.width = this.a(aq - ap, 0, this.aj[this.aV].width - rectangle.x);
                if (this.S) {
                    this.V.b(this.aV, rectangle, false);
                }
                else {
                    this.V.a(this.aV, rectangle, false);
                }
                rectangle.x = 0;
                rectangle.width = this.a(this.aQ, 0, this.aj[this.aV].width);
                if (this.S) {
                    this.aw.a();
                    this.V.b(this.aV, rectangle);
                    this.aw.b();
                }
                else {
                    this.V.a(this.aV, rectangle);
                    this.aw.a();
                }
            }
            if (this.am && !com.iseemedia.apps.tourclients40.iip.a.e()) {
                if (this.aX == 32) {
                    this.aT = (this.aT + 1) / 2;
                    this.aP = (this.aP + 1) / 2;
                    this.aU = (this.aU + 1) / 2;
                    this.aQ = (this.aQ + 1) / 2;
                    ap = (ap + 1) / 2;
                    aq = (aq + 1) / 2;
                }
                final Rectangle rectangle2 = new Rectangle(0, this.aT, this.aj[this.aV].width, this.aU - this.aT);
                this.W.a(true);
                this.W.c(this.aV);
                rectangle2.x = this.a(ap, 0, this.aj[this.aV].width);
                rectangle2.width = this.a(aq - ap, 0, this.aj[this.aV].width - rectangle2.x);
                if (this.S) {
                    this.W.b(this.aV, rectangle2, false);
                }
                else {
                    this.W.a(this.aV, rectangle2, false);
                }
                rectangle2.x = 0;
                rectangle2.width = this.a(this.aQ, 0, this.aj[this.aV].width);
                if (this.S) {
                    this.S = false;
                    this.T = true;
                    this.aw.a();
                    this.W.b(this.aV, rectangle2);
                    this.aw.b();
                }
                else {
                    this.W.a(this.aV, rectangle2);
                    this.aw.a();
                }
            }
            this.aM = true;
        }
        else {
            this.aP = (int)(a3 * this.aO);
            this.aP -= 3;
            this.aP = this.a(this.aP, 0, n4);
            this.aQ = (int)Math.ceil(a4 * this.aO);
            this.aQ += 3;
            this.aQ = this.a(this.aQ, 0, n4);
            this.aR = (int)(a2 * this.aN);
            --this.aR;
            this.aR = this.a(this.aR, 0, n3);
            this.aS = (int)Math.ceil(n6 * this.aN) + 1;
            ++this.aS;
            this.aT = (int)(n5 * this.aN) - 1;
            --this.aT;
            this.aS = this.a(this.aS, 0, n3);
            this.aT = this.a(this.aT, 0, n3);
            this.aU = (int)Math.ceil(a * this.aN);
            ++this.aU;
            this.aU = this.a(this.aU, 0, n3);
            this.B();
            this.C();
            this.D();
            if (this.al && !com.iseemedia.apps.tourclients40.iip.a.d()) {
                if (this.aX == 32) {
                    this.aR = (this.aR + 1) / 2;
                    this.aP = (this.aP + 1) / 2;
                    this.aS = (this.aS + 1) / 2;
                    this.aQ = (this.aQ + 1) / 2;
                }
                final Rectangle rectangle3 = new Rectangle(0, this.aR, this.aj[this.aV].width, this.aS - this.aR);
                this.V.a(true);
                this.V.c(this.aV);
                rectangle3.x = this.a(this.aP, 0, this.aj[this.aV].width);
                rectangle3.width = this.a(this.aQ - this.aP, 0, this.aj[this.aV].width - rectangle3.x);
                if (this.S) {
                    this.aw.a();
                    this.V.b(this.aV, rectangle3);
                    this.aw.b();
                }
                else {
                    this.V.a(this.aV, rectangle3);
                    this.aw.a();
                }
            }
            if (this.am && !com.iseemedia.apps.tourclients40.iip.a.e()) {
                if (this.aX == 32) {
                    this.aT = (this.aT + 1) / 2;
                    this.aP = (this.aP + 1) / 2;
                    this.aU = (this.aU + 1) / 2;
                    this.aQ = (this.aQ + 1) / 2;
                }
                final Rectangle rectangle4 = new Rectangle(0, this.aT, this.aj[this.aV].width, this.aU - this.aT);
                this.W.a(true);
                this.W.c(this.aV);
                rectangle4.x = this.a(this.aP, 0, this.aj[this.aV].width);
                rectangle4.width = this.a(this.aQ - this.aP, 0, this.aj[this.aV].width - rectangle4.x);
                if (this.S) {
                    this.S = false;
                    this.T = true;
                    this.aw.a();
                    this.W.b(this.aV, rectangle4);
                    this.aw.b();
                }
                else {
                    this.W.a(this.aV, rectangle4);
                    this.aw.a();
                }
            }
            this.aM = true;
        }
        this.al = false;
        this.am = false;
        return this.bo = true;
    }
    
    private void B() {
        boolean b = false;
        if (this.aS - this.aR <= 0) {
            return;
        }
        if (this.aQ - this.aP <= 0) {
            return;
        }
        if (this.br == null) {
            this.br = new Runnable(this, 0) {
                private com.iseemedia.apps.tourclients40.players.b b;
                private int[] c;
                private int[] d;
                private int[] e;
                private int[] f;
                private int[] g;
                private int[] h;
                private int[] i;
                public com.iseemedia.image.b a;
                private int[] j;
                private int[] k;
                private int l;
                private int m;
                private int n;
                private int o;
                private int p;
                private int q;
                private i[] r;
                private i[] s;
                private f t;
                private Dimension[] u;
                private Dimension[] v;
                private final e w;
                
                {
                    this.w = w;
                    this.i = new int[4225];
                    this.a = null;
                    this.u = new Dimension[32];
                    this.v = new Dimension[32];
                    this.q = q;
                    this.b = new com.iseemedia.apps.tourclients40.players.b();
                    this.c = new int[4];
                    this.d = new int[3];
                    this.e = new int[3];
                    this.f = new int[3];
                    this.g = new int[3];
                    if (this.q == 0) {
                        this.t = com.iseemedia.apps.tourclients40.players.e.a(w);
                    }
                    else {
                        this.t = com.iseemedia.apps.tourclients40.players.e.b(w);
                    }
                    (this.a = new com.iseemedia.image.b(com.iseemedia.apps.tourclients40.players.e.c(w).width, com.iseemedia.apps.tourclients40.players.e.c(w).height / 2)).a();
                    if (this.a.b == null) {
                        System.out.print("destination not created \n\r");
                    }
                    this.j = new int[10000];
                    this.l = -1;
                    this.k = new int[156];
                    this.r = new i[200];
                    this.s = new i[200];
                    for (int i = 0; i < 200; ++i) {
                        this.r[i] = new i();
                        this.s[i] = new i();
                    }
                    for (int j = com.iseemedia.apps.tourclients40.players.e.d(w); j >= 0; --j) {
                        this.u[j] = com.iseemedia.apps.tourclients40.players.e.e(w)[j];
                        this.v[j] = com.iseemedia.apps.tourclients40.players.e.f(w)[j];
                    }
                    for (int k = 0; k < 156; ++k) {
                        this.k[k] = -1;
                    }
                }
                
                private void a(final int[] array, final int[] array2, final int[] array3, final int[] array4, final int n) {
                    long n2;
                    if ((n2 = array2[1] - array[1] << 2) == 0L) {
                        n2 = 65536L;
                    }
                    final long n3 = (n << 16) + 32768 - (array[1] << 2);
                    final long n4 = 4294967296L / n2;
                    array4[0] = (int)((array2[0] - array[0]) * n4 >> 16);
                    array3[0] = (int)(array[0] + (array4[0] * n3 >> 16));
                    array4[1] = (int)((array2[2] - array[2]) * n4 >> 16);
                    array3[1] = (int)(array[2] + (array4[1] * n3 >> 16));
                    array4[2] = (int)((array2[3] - array[3]) * n4 >> 16);
                    array3[2] = (int)(array[3] + (array4[2] * n3 >> 16));
                }
                
                private void a(int n, final int[] array, final int[] array2) {
                    int n2 = array[0] - 8192 >> 14;
                    ++n2;
                    int n3;
                    if ((n3 = array2[0] - 8192 >> 14) < 0) {
                        --n3;
                    }
                    if (n2 > n3) {
                        return;
                    }
                    if (n2 < 0) {
                        n2 = 0;
                    }
                    if (n3 >= this.a.c) {
                        n3 = this.a.c - 1;
                    }
                    long n4;
                    if ((n4 = array2[0] - array[0] << 2) == 0L) {
                        n4 = 65536L;
                    }
                    final long n5 = (n2 << 14) + 8192 - array[0];
                    final long n6 = 4294967296L / n4;
                    final int n7 = (int)((array2[1] - array[1]) * n6 >> 14);
                    final int n8 = (int)((array2[2] - array[2]) * n6 >> 14);
                    int n9 = (int)((array[1] << 2) + (n7 * n5 >> 14));
                    int n10 = (int)((array[2] << 2) + (n8 * n5 >> 14));
                    final int n11 = n3 + 1;
                    if (this.q == 0 && n >= this.a.d) {
                        return;
                    }
                    if (this.q == 1) {
                        if (n < this.a.d) {
                            return;
                        }
                        n -= this.a.d;
                    }
                    final int n12 = this.a.c * n;
                    int n13 = n10 >>> 16;
                    int n14 = n9 >>> 16;
                    int n15 = (n13 << 6) + n13 + n14;
                    int n16 = n12 + n2;
                    try {
                        for (int n17 = n2; n17 < n11 && n15 < 4225; n15 = (n13 << 6) + n13 + n14, ++n16, ++n17) {
                            if (n13 < 65 && n14 < 65) {
                                final int n18 = (n9 & 0xFFFF) >> 8;
                                final int[] i;
                                final int n19 = (i = this.i)[n15 + 0];
                                final int n20 = i[n15 + 1];
                                final int n21 = (n19 & 0xFF0000) >> 16;
                                final int n22 = (n19 & 0xFF00) >> 8;
                                final int n23 = n19 & 0xFF;
                                final int n24 = (n20 & 0xFF0000) >> 16;
                                final int n25 = (n20 & 0xFF00) >> 8;
                                final int n26 = n20 & 0xFF;
                                final int n27 = 256 - n18;
                                final int n28 = n21 * n27 + n24 * n18;
                                final int n29 = n22 * n27 + n25 * n18;
                                final int n30 = n23 * n27 + n26 * n18;
                                final int n31 = i[n15 + 65];
                                final int n32 = i[n15 + 66];
                                final int n33 = (n31 & 0xFF0000) >> 16;
                                final int n34 = (n31 & 0xFF00) >> 8;
                                final int n35 = n31 & 0xFF;
                                final int n36 = (n32 & 0xFF0000) >> 16;
                                final int n37 = (n32 & 0xFF00) >> 8;
                                final int n38 = n32 & 0xFF;
                                final int n39 = n33 * n27 + n36 * n18;
                                final int n40 = n34 * n27 + n37 * n18;
                                final int n41 = n35 * n27 + n38 * n18;
                                final int n42 = (n10 & 0xFFFF) >> 8;
                                final int n43 = 256 - n42;
                                this.a.b[n16] = (((0xFF00 | n28 * n43 + n39 * n42 >> 16) << 8 | n29 * n43 + n40 * n42 >> 16) << 8 | n30 * n43 + n41 * n42 >> 16);
                            }
                            else {
                                this.a.b[n16] = this.i[n15];
                            }
                            n9 += n7;
                            n13 = (n10 += n8) >>> 16;
                            n14 = n9 >>> 16;
                        }
                    }
                    catch (Exception ex) {}
                }
                
                private void a(final com.iseemedia.apps.tourclients40.players.b b) {
                    final long n = 64L;
                    final long n2 = 64L;
                    long n4;
                    long n3 = n4 = 2000000000L;
                    long n6;
                    long n5 = n6 = -2000000000L;
                    int n7 = 0;
                    for (int i = 0; i < 4; ++i) {
                        b.a[i][0] = (int)(b.a[i][0] * com.iseemedia.apps.tourclients40.players.e.g(this.w)) + (int)(com.iseemedia.apps.tourclients40.players.e.g(this.w) * 16384.0f);
                        b.a[i][1] = (int)(-b.a[i][1] * com.iseemedia.apps.tourclients40.players.e.h(this.w)) + (int)(com.iseemedia.apps.tourclients40.players.e.h(this.w) * 16384.0f);
                        b.a[i][2] *= (int)n;
                        b.a[i][3] *= (int)n2;
                        if (b.a[i][0] < n4) {
                            n4 = b.a[i][0];
                        }
                        if (b.a[i][0] > n6) {
                            n6 = b.a[i][0];
                        }
                        if (b.a[i][1] < n3) {
                            n3 = b.a[i][1];
                            n7 = i;
                        }
                        if (b.a[i][1] > n5) {
                            n5 = b.a[i][1];
                        }
                    }
                    if (n4 > com.iseemedia.apps.tourclients40.players.e.i(this.w) * 16384.0f) {
                        return;
                    }
                    if (n3 > com.iseemedia.apps.tourclients40.players.e.j(this.w) * 16384.0f) {
                        return;
                    }
                    if (n6 < 0L) {
                        return;
                    }
                    if (n5 < 0L) {
                        return;
                    }
                    int n9;
                    int n8 = n9 = n7;
                    int j = 4;
                    int n10 = (int)(n3 - 8192L >> 14);
                    ++n10;
                    int l;
                    int k = l = n10 - 1;
                    final int n11 = this.a.d * 2;
                    while (j > 0) {
                        while (l <= n10) {
                            if (j <= 0) {
                                break;
                            }
                            --j;
                            int n12;
                            if ((n12 = n9 - 1) < 0) {
                                n12 = 3;
                            }
                            this.a(b.a[n9], b.a[n12], this.d, this.f, n10);
                            final long n13;
                            l = (int)(n13 = b.a[n12][1] + 8192 >> 14);
                            if (n13 < 0L) {
                                --l;
                            }
                            n9 = n12;
                        }
                        while (k <= n10) {
                            if (j <= 0) {
                                break;
                            }
                            --j;
                            int n14;
                            if ((n14 = n8 + 1) >= 4) {
                                n14 = 0;
                            }
                            this.a(b.a[n8], b.a[n14], this.e, this.g, n10);
                            final long n15;
                            k = (int)(n15 = b.a[n14][1] + 8192 >> 14);
                            if (n15 < 0L) {
                                --k;
                            }
                            n8 = n14;
                        }
                        while (n10 < l && n10 < k && n10 < n11) {
                            if (n10 >= 0) {
                                if (this.d[0] < this.e[0]) {
                                    this.a(n10, this.d, this.e);
                                }
                                else {
                                    this.a(n10, this.e, this.d);
                                }
                            }
                            ++n10;
                            final int[] d = this.d;
                            final int n16 = 0;
                            d[n16] += this.f[0];
                            final int[] e = this.e;
                            final int n17 = 0;
                            e[n17] += this.g[0];
                            final int[] d2 = this.d;
                            final int n18 = 1;
                            d2[n18] += this.f[1];
                            final int[] e2 = this.e;
                            final int n19 = 1;
                            e2[n19] += this.g[1];
                            final int[] d3 = this.d;
                            final int n20 = 2;
                            d3[n20] += this.f[2];
                            final int[] e3 = this.e;
                            final int n21 = 2;
                            e3[n21] += this.g[2];
                        }
                        if (n10 >= n11) {
                            return;
                        }
                    }
                }
                
                public final void a(final int m, final int n, final int o, final int p4) {
                    this.n = n;
                    this.p = p4;
                    this.m = m;
                    this.o = o;
                }
                
                private void a(final com.iseemedia.apps.tourclients40.players.b b, final int[] array, final int n, final int n2) {
                    boolean b2 = false;
                    boolean b3 = false;
                    if (n == this.v[com.iseemedia.apps.tourclients40.players.e.k(this.w)].width - 1) {
                        b2 = true;
                    }
                    if (n2 == this.v[com.iseemedia.apps.tourclients40.players.e.k(this.w)].height - 1) {
                        b3 = true;
                    }
                    if (array[0] == com.iseemedia.apps.tourclients40.players.e.k(this.w)) {
                        b.a[0][2] = 0;
                        if (b3 && (this.u[com.iseemedia.apps.tourclients40.players.e.k(this.w)].height & 0x3F) > 0) {
                            b.a[0][3] = (int)((this.u[com.iseemedia.apps.tourclients40.players.e.k(this.w)].height & 0x3F) / 64.0f * 16384.0f);
                        }
                        else {
                            b.a[0][3] = 16384;
                        }
                        if (b2 && (this.u[com.iseemedia.apps.tourclients40.players.e.k(this.w)].width & 0x3F) > 0) {
                            b.a[1][2] = (int)(((this.u[com.iseemedia.apps.tourclients40.players.e.k(this.w)].width & 0x3F) / 64.0f - 0.015625f) * 16384.0f);
                        }
                        else {
                            b.a[1][2] = 16384;
                        }
                        b.a[1][3] = b.a[0][3];
                        b.a[2][2] = b.a[1][2];
                        b.a[2][3] = 0;
                        b.a[3][2] = 0;
                        b.a[3][3] = 0;
                        return;
                    }
                    if (array[0] < com.iseemedia.apps.tourclients40.players.e.k(this.w)) {
                        final float n3 = this.u[com.iseemedia.apps.tourclients40.players.e.d(this.w)].height;
                        final float n4 = this.u[com.iseemedia.apps.tourclients40.players.e.d(this.w)].width;
                        final float n5 = this.u[com.iseemedia.apps.tourclients40.players.e.k(this.w)].height;
                        final float n6 = this.u[com.iseemedia.apps.tourclients40.players.e.k(this.w)].width;
                        final float n7 = this.u[array[0]].height;
                        final float n8 = this.u[array[0]].width;
                        final float n9 = n4 / n6;
                        final float n10 = n3 / n5;
                        final float n11 = n4 / n8;
                        final float n12 = n3 / n7;
                        final float n13 = array[1] * 64.0f * n11 / n4;
                        final float n14 = array[2] * 64.0f * n12 / n3;
                        final float n15 = (array[1] + 1) * 64.0f * n11 / n4;
                        final float n16 = (array[2] + 1) * 64.0f * n12 / n3;
                        final float n17 = n * 64.0f * n9 / n4;
                        final float n18 = n2 * 64.0f * n10 / n3;
                        float n19 = (n + 1) * 64.0f * n9 / n4;
                        float n20 = (n2 + 1) * 64.0f * n10 / n3;
                        if (b2) {
                            n19 = 1.0f;
                        }
                        if (b3) {
                            n20 = 1.0f;
                        }
                        final float a = com.iseemedia.apps.tourclients40.players.e.a(this.w, n17, n13, n15);
                        final float a2 = com.iseemedia.apps.tourclients40.players.e.a(this.w, n18, n14, n16);
                        final float a3 = com.iseemedia.apps.tourclients40.players.e.a(this.w, n19, a, n15);
                        final float a4 = com.iseemedia.apps.tourclients40.players.e.a(this.w, n20, a2, n16);
                        b.a[3][2] = (int)((a - n13) / (n15 - n13) * 16384.0f);
                        b.a[3][3] = (int)((a2 - n14) / (n16 - n14) * 16384.0f);
                        b.a[2][2] = (int)((a3 - n13) / (n15 - n13) * 16384.0f);
                        if (b2 && (this.u[array[0]].width & 0x3F) > 0) {
                            b.a[2][2] = (int)(((this.u[array[0]].width & 0x3F) - 1.5f) / 64.0f * 16384.0f);
                            if (b.a[2][2] < 0) {
                                b.a[2][2] = 0;
                            }
                        }
                        b.a[2][3] = b.a[3][3];
                        b.a[1][3] = (int)((a4 - n14) / (n16 - n14) * 16384.0f);
                        b.a[1][2] = b.a[2][2];
                        b.a[0][2] = (int)((a - n13) / (n15 - n13) * 16384.0f);
                        b.a[0][3] = b.a[1][3];
                    }
                }
                
                public final void run() {
                    i[] array = this.r;
                    i[] array2 = this.s;
                    int n;
                    int n2;
                    if (this.q == 0) {
                        n = 16384;
                        n2 = -819;
                    }
                    else {
                        n = 819;
                        n2 = -16384;
                    }
                    if (this.m > 0) {
                        --this.m;
                    }
                    com.iseemedia.apps.tourclients40.players.e.a(this.w, array, this.m);
                    com.iseemedia.apps.tourclients40.players.e.b(this.w, array, this.p - this.n);
                    for (int i = this.m; i < this.o; ++i) {
                        Thread.yield();
                        com.iseemedia.apps.tourclients40.players.e.a(this.w, array2, i + 1);
                        com.iseemedia.apps.tourclients40.players.e.b(this.w, array2, this.p - this.n);
                        for (int j = 0; j < this.p - this.n; ++j) {
                            if (array2[j].a[7] >= 163 && array2[j + 1].a[7] >= 163 && array[j].a[7] >= 163) {
                                if (array[j + 1].a[7] >= 163) {
                                    if (array2[j].a[5] <= 16384 || array2[j + 1].a[5] <= 16384 || array[j].a[5] <= 16384 || array[j + 1].a[5] <= 16384) {
                                        if (array2[j].a[5] >= -16384 || array2[j + 1].a[5] >= -16384 || array[j].a[5] >= -16384 || array[j + 1].a[5] >= -16384) {
                                            if (array2[j].a[6] <= n || array2[j + 1].a[6] <= n || array[j].a[6] <= n || array[j + 1].a[6] <= n) {
                                                if (array2[j].a[6] >= n2 || array2[j + 1].a[6] >= n2 || array[j].a[6] >= n2 || array[j + 1].a[6] >= n2) {
                                                    this.b.a[0][0] = array2[j].a[5];
                                                    this.b.a[0][1] = array2[j].a[6];
                                                    this.b.a[1][0] = array2[j + 1].a[5];
                                                    this.b.a[1][1] = array2[j + 1].a[6];
                                                    this.b.a[2][0] = array[j + 1].a[5];
                                                    this.b.a[2][1] = array[j + 1].a[6];
                                                    this.b.a[3][0] = array[j].a[5];
                                                    this.b.a[3][1] = array[j].a[6];
                                                    this.c[0] = com.iseemedia.apps.tourclients40.players.e.k(this.w);
                                                    this.c[1] = j + this.n;
                                                    this.c[2] = i;
                                                    if (com.iseemedia.apps.tourclients40.players.e.l(this.w) == 32) {
                                                        final int[] c = this.c;
                                                        final int n3 = 1;
                                                        c[n3] /= 2;
                                                        final int[] c2 = this.c;
                                                        final int n4 = 2;
                                                        c2[n4] /= 2;
                                                    }
                                                    final int n5 = this.c[1];
                                                    final int n6 = this.c[2];
                                                    if (com.iseemedia.apps.tourclients40.players.e.m(this.w) && j == 0) {
                                                        this.h = this.t.a(this.c, !this.w.E || !com.iseemedia.apps.tourclients40.players.e.n(this.w));
                                                        for (int k = 1; k < 65; ++k) {
                                                            for (int l = 0; l < 64; ++l) {
                                                                this.i[k * 65 + l + 1] = this.h[(k - 1) * 64 + l];
                                                            }
                                                        }
                                                        for (int n7 = 0; n7 < 65; ++n7) {
                                                            this.i[n7 * 65] = this.i[n7 * 65 + 1];
                                                        }
                                                    }
                                                    else {
                                                        this.h = this.t.a(this.c, !this.w.E || !com.iseemedia.apps.tourclients40.players.e.n(this.w));
                                                        for (int n8 = 1; n8 < 65; ++n8) {
                                                            this.i[n8 * 65] = this.i[n8 * 65 + 64];
                                                        }
                                                        for (int n9 = 1; n9 < 65; ++n9) {
                                                            for (int n10 = 0; n10 < 64; ++n10) {
                                                                this.i[n9 * 65 + n10 + 1] = this.h[(n9 - 1) * 64 + n10];
                                                            }
                                                        }
                                                    }
                                                    if (this.c[3] == 2) {
                                                        if (this.q == 0) {
                                                            com.iseemedia.apps.tourclients40.players.e.a(this.w, true);
                                                        }
                                                        else {
                                                            com.iseemedia.apps.tourclients40.players.e.b(this.w, true);
                                                        }
                                                    }
                                                    if (this.l != this.c[3] || this.l <= 3) {
                                                        for (int n11 = 0; n11 < 65; ++n11) {
                                                            this.i[n11 * 65] = this.i[n11 * 65 + 1];
                                                        }
                                                    }
                                                    this.l = this.c[3];
                                                    if (this.k[j] == this.c[3] && this.k[j] > 3) {
                                                        for (int n12 = 0; n12 < 65; ++n12) {
                                                            this.i[n12] = this.j[j * 65 + n12];
                                                        }
                                                    }
                                                    else {
                                                        for (int n13 = 0; n13 < 65; ++n13) {
                                                            this.i[n13] = this.i[65 + n13];
                                                        }
                                                    }
                                                    this.k[j] = this.c[3];
                                                    this.a(this.b, this.c, n5, n6);
                                                    if (com.iseemedia.apps.tourclients40.players.e.l(this.w) < 64) {
                                                        com.iseemedia.apps.tourclients40.players.e.a(this.w, this.b, this.c, j + this.n, i);
                                                    }
                                                    this.a(this.b);
                                                    for (int n14 = 0; n14 < 65; ++n14) {
                                                        this.j[j * 65 + n14] = this.i[4160 + n14];
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if ((i & 0x1) == 0x0) {
                            array = this.s;
                            array2 = this.r;
                        }
                        else {
                            array = this.r;
                            array2 = this.s;
                        }
                    }
                    if ((com.iseemedia.apps.tourclients40.players.e.m(this.w) && this.n == 0) || !com.iseemedia.apps.tourclients40.players.e.m(this.w)) {
                        this.a.a();
                    }
                    if (this.q == 0) {
                        com.iseemedia.apps.tourclients40.players.e.o(this.w).b();
                    }
                }
                
                protected final void finalize() throws Throwable {
                    try {
                        this.b = null;
                        this.c = null;
                        this.d = null;
                        this.f = null;
                        this.h = null;
                        this.i = null;
                        this.a = null;
                        this.j = null;
                        this.r = null;
                        this.s = null;
                        this.t = null;
                        this.u = null;
                        this.v = null;
                    }
                    finally {
                        super.finalize();
                    }
                }
            };
        }
        else {
            if (this.bt != null) {
                while (!b) {
                    if (!this.bt.isAlive()) {
                        b = true;
                    }
                }
            }
            this.bt = null;
        }
        this.br.a(this.aR, this.aP, this.aS, this.aQ);
        this.bt = new Thread(this.br, "Thread Upper");
        this.bq.c();
        if (this.bn) {
            this.bt.start();
        }
        else {
            this.bt.run();
        }
        Thread.currentThread();
        Thread.yield();
    }
    
    private void C() {
        if (this.aU - this.aT <= 0) {
            return;
        }
        if (this.aQ - this.aP <= 0) {
            return;
        }
        boolean b = false;
        if (this.bs == null) {
            this.bs = new Runnable(this, 1) {
                private com.iseemedia.apps.tourclients40.players.b b = new com.iseemedia.apps.tourclients40.players.b();
                private int[] c = new int[4];
                private int[] d = new int[3];
                private int[] e = new int[3];
                private int[] f = new int[3];
                private int[] g = new int[3];
                private int[] h;
                private int[] i = new int[4225];
                public com.iseemedia.image.b a = null;
                private int[] j;
                private int[] k;
                private int l;
                private int m;
                private int n;
                private int o;
                private int p;
                private int q = q;
                private i[] r;
                private i[] s;
                private f t;
                private Dimension[] u = new Dimension[32];
                private Dimension[] v = new Dimension[32];
                private final e w = w;
                
                {
                    if (this.q == 0) {
                        this.t = com.iseemedia.apps.tourclients40.players.e.a(w);
                    }
                    else {
                        this.t = com.iseemedia.apps.tourclients40.players.e.b(w);
                    }
                    (this.a = new com.iseemedia.image.b(com.iseemedia.apps.tourclients40.players.e.c(w).width, com.iseemedia.apps.tourclients40.players.e.c(w).height / 2)).a();
                    if (this.a.b == null) {
                        System.out.print("destination not created \n\r");
                    }
                    this.j = new int[10000];
                    this.l = -1;
                    this.k = new int[156];
                    this.r = new i[200];
                    this.s = new i[200];
                    for (int i = 0; i < 200; ++i) {
                        this.r[i] = new i();
                        this.s[i] = new i();
                    }
                    for (int j = com.iseemedia.apps.tourclients40.players.e.d(w); j >= 0; --j) {
                        this.u[j] = com.iseemedia.apps.tourclients40.players.e.e(w)[j];
                        this.v[j] = com.iseemedia.apps.tourclients40.players.e.f(w)[j];
                    }
                    for (int k = 0; k < 156; ++k) {
                        this.k[k] = -1;
                    }
                }
                
                private void a(final int[] array, final int[] array2, final int[] array3, final int[] array4, final int n) {
                    long n2;
                    if ((n2 = array2[1] - array[1] << 2) == 0L) {
                        n2 = 65536L;
                    }
                    final long n3 = (n << 16) + 32768 - (array[1] << 2);
                    final long n4 = 4294967296L / n2;
                    array4[0] = (int)((array2[0] - array[0]) * n4 >> 16);
                    array3[0] = (int)(array[0] + (array4[0] * n3 >> 16));
                    array4[1] = (int)((array2[2] - array[2]) * n4 >> 16);
                    array3[1] = (int)(array[2] + (array4[1] * n3 >> 16));
                    array4[2] = (int)((array2[3] - array[3]) * n4 >> 16);
                    array3[2] = (int)(array[3] + (array4[2] * n3 >> 16));
                }
                
                private void a(int n, final int[] array, final int[] array2) {
                    int n2 = array[0] - 8192 >> 14;
                    ++n2;
                    int n3;
                    if ((n3 = array2[0] - 8192 >> 14) < 0) {
                        --n3;
                    }
                    if (n2 > n3) {
                        return;
                    }
                    if (n2 < 0) {
                        n2 = 0;
                    }
                    if (n3 >= this.a.c) {
                        n3 = this.a.c - 1;
                    }
                    long n4;
                    if ((n4 = array2[0] - array[0] << 2) == 0L) {
                        n4 = 65536L;
                    }
                    final long n5 = (n2 << 14) + 8192 - array[0];
                    final long n6 = 4294967296L / n4;
                    final int n7 = (int)((array2[1] - array[1]) * n6 >> 14);
                    final int n8 = (int)((array2[2] - array[2]) * n6 >> 14);
                    int n9 = (int)((array[1] << 2) + (n7 * n5 >> 14));
                    int n10 = (int)((array[2] << 2) + (n8 * n5 >> 14));
                    final int n11 = n3 + 1;
                    if (this.q == 0 && n >= this.a.d) {
                        return;
                    }
                    if (this.q == 1) {
                        if (n < this.a.d) {
                            return;
                        }
                        n -= this.a.d;
                    }
                    final int n12 = this.a.c * n;
                    int n13 = n10 >>> 16;
                    int n14 = n9 >>> 16;
                    int n15 = (n13 << 6) + n13 + n14;
                    int n16 = n12 + n2;
                    try {
                        for (int n17 = n2; n17 < n11 && n15 < 4225; n15 = (n13 << 6) + n13 + n14, ++n16, ++n17) {
                            if (n13 < 65 && n14 < 65) {
                                final int n18 = (n9 & 0xFFFF) >> 8;
                                final int[] i;
                                final int n19 = (i = this.i)[n15 + 0];
                                final int n20 = i[n15 + 1];
                                final int n21 = (n19 & 0xFF0000) >> 16;
                                final int n22 = (n19 & 0xFF00) >> 8;
                                final int n23 = n19 & 0xFF;
                                final int n24 = (n20 & 0xFF0000) >> 16;
                                final int n25 = (n20 & 0xFF00) >> 8;
                                final int n26 = n20 & 0xFF;
                                final int n27 = 256 - n18;
                                final int n28 = n21 * n27 + n24 * n18;
                                final int n29 = n22 * n27 + n25 * n18;
                                final int n30 = n23 * n27 + n26 * n18;
                                final int n31 = i[n15 + 65];
                                final int n32 = i[n15 + 66];
                                final int n33 = (n31 & 0xFF0000) >> 16;
                                final int n34 = (n31 & 0xFF00) >> 8;
                                final int n35 = n31 & 0xFF;
                                final int n36 = (n32 & 0xFF0000) >> 16;
                                final int n37 = (n32 & 0xFF00) >> 8;
                                final int n38 = n32 & 0xFF;
                                final int n39 = n33 * n27 + n36 * n18;
                                final int n40 = n34 * n27 + n37 * n18;
                                final int n41 = n35 * n27 + n38 * n18;
                                final int n42 = (n10 & 0xFFFF) >> 8;
                                final int n43 = 256 - n42;
                                this.a.b[n16] = (((0xFF00 | n28 * n43 + n39 * n42 >> 16) << 8 | n29 * n43 + n40 * n42 >> 16) << 8 | n30 * n43 + n41 * n42 >> 16);
                            }
                            else {
                                this.a.b[n16] = this.i[n15];
                            }
                            n9 += n7;
                            n13 = (n10 += n8) >>> 16;
                            n14 = n9 >>> 16;
                        }
                    }
                    catch (Exception ex) {}
                }
                
                private void a(final com.iseemedia.apps.tourclients40.players.b b) {
                    final long n = 64L;
                    final long n2 = 64L;
                    long n4;
                    long n3 = n4 = 2000000000L;
                    long n6;
                    long n5 = n6 = -2000000000L;
                    int n7 = 0;
                    for (int i = 0; i < 4; ++i) {
                        b.a[i][0] = (int)(b.a[i][0] * com.iseemedia.apps.tourclients40.players.e.g(this.w)) + (int)(com.iseemedia.apps.tourclients40.players.e.g(this.w) * 16384.0f);
                        b.a[i][1] = (int)(-b.a[i][1] * com.iseemedia.apps.tourclients40.players.e.h(this.w)) + (int)(com.iseemedia.apps.tourclients40.players.e.h(this.w) * 16384.0f);
                        b.a[i][2] *= (int)n;
                        b.a[i][3] *= (int)n2;
                        if (b.a[i][0] < n4) {
                            n4 = b.a[i][0];
                        }
                        if (b.a[i][0] > n6) {
                            n6 = b.a[i][0];
                        }
                        if (b.a[i][1] < n3) {
                            n3 = b.a[i][1];
                            n7 = i;
                        }
                        if (b.a[i][1] > n5) {
                            n5 = b.a[i][1];
                        }
                    }
                    if (n4 > com.iseemedia.apps.tourclients40.players.e.i(this.w) * 16384.0f) {
                        return;
                    }
                    if (n3 > com.iseemedia.apps.tourclients40.players.e.j(this.w) * 16384.0f) {
                        return;
                    }
                    if (n6 < 0L) {
                        return;
                    }
                    if (n5 < 0L) {
                        return;
                    }
                    int n9;
                    int n8 = n9 = n7;
                    int j = 4;
                    int n10 = (int)(n3 - 8192L >> 14);
                    ++n10;
                    int l;
                    int k = l = n10 - 1;
                    final int n11 = this.a.d * 2;
                    while (j > 0) {
                        while (l <= n10) {
                            if (j <= 0) {
                                break;
                            }
                            --j;
                            int n12;
                            if ((n12 = n9 - 1) < 0) {
                                n12 = 3;
                            }
                            this.a(b.a[n9], b.a[n12], this.d, this.f, n10);
                            final long n13;
                            l = (int)(n13 = b.a[n12][1] + 8192 >> 14);
                            if (n13 < 0L) {
                                --l;
                            }
                            n9 = n12;
                        }
                        while (k <= n10) {
                            if (j <= 0) {
                                break;
                            }
                            --j;
                            int n14;
                            if ((n14 = n8 + 1) >= 4) {
                                n14 = 0;
                            }
                            this.a(b.a[n8], b.a[n14], this.e, this.g, n10);
                            final long n15;
                            k = (int)(n15 = b.a[n14][1] + 8192 >> 14);
                            if (n15 < 0L) {
                                --k;
                            }
                            n8 = n14;
                        }
                        while (n10 < l && n10 < k && n10 < n11) {
                            if (n10 >= 0) {
                                if (this.d[0] < this.e[0]) {
                                    this.a(n10, this.d, this.e);
                                }
                                else {
                                    this.a(n10, this.e, this.d);
                                }
                            }
                            ++n10;
                            final int[] d = this.d;
                            final int n16 = 0;
                            d[n16] += this.f[0];
                            final int[] e = this.e;
                            final int n17 = 0;
                            e[n17] += this.g[0];
                            final int[] d2 = this.d;
                            final int n18 = 1;
                            d2[n18] += this.f[1];
                            final int[] e2 = this.e;
                            final int n19 = 1;
                            e2[n19] += this.g[1];
                            final int[] d3 = this.d;
                            final int n20 = 2;
                            d3[n20] += this.f[2];
                            final int[] e3 = this.e;
                            final int n21 = 2;
                            e3[n21] += this.g[2];
                        }
                        if (n10 >= n11) {
                            return;
                        }
                    }
                }
                
                public final void a(final int m, final int n, final int o, final int p4) {
                    this.n = n;
                    this.p = p4;
                    this.m = m;
                    this.o = o;
                }
                
                private void a(final com.iseemedia.apps.tourclients40.players.b b, final int[] array, final int n, final int n2) {
                    boolean b2 = false;
                    boolean b3 = false;
                    if (n == this.v[com.iseemedia.apps.tourclients40.players.e.k(this.w)].width - 1) {
                        b2 = true;
                    }
                    if (n2 == this.v[com.iseemedia.apps.tourclients40.players.e.k(this.w)].height - 1) {
                        b3 = true;
                    }
                    if (array[0] == com.iseemedia.apps.tourclients40.players.e.k(this.w)) {
                        b.a[0][2] = 0;
                        if (b3 && (this.u[com.iseemedia.apps.tourclients40.players.e.k(this.w)].height & 0x3F) > 0) {
                            b.a[0][3] = (int)((this.u[com.iseemedia.apps.tourclients40.players.e.k(this.w)].height & 0x3F) / 64.0f * 16384.0f);
                        }
                        else {
                            b.a[0][3] = 16384;
                        }
                        if (b2 && (this.u[com.iseemedia.apps.tourclients40.players.e.k(this.w)].width & 0x3F) > 0) {
                            b.a[1][2] = (int)(((this.u[com.iseemedia.apps.tourclients40.players.e.k(this.w)].width & 0x3F) / 64.0f - 0.015625f) * 16384.0f);
                        }
                        else {
                            b.a[1][2] = 16384;
                        }
                        b.a[1][3] = b.a[0][3];
                        b.a[2][2] = b.a[1][2];
                        b.a[2][3] = 0;
                        b.a[3][2] = 0;
                        b.a[3][3] = 0;
                        return;
                    }
                    if (array[0] < com.iseemedia.apps.tourclients40.players.e.k(this.w)) {
                        final float n3 = this.u[com.iseemedia.apps.tourclients40.players.e.d(this.w)].height;
                        final float n4 = this.u[com.iseemedia.apps.tourclients40.players.e.d(this.w)].width;
                        final float n5 = this.u[com.iseemedia.apps.tourclients40.players.e.k(this.w)].height;
                        final float n6 = this.u[com.iseemedia.apps.tourclients40.players.e.k(this.w)].width;
                        final float n7 = this.u[array[0]].height;
                        final float n8 = this.u[array[0]].width;
                        final float n9 = n4 / n6;
                        final float n10 = n3 / n5;
                        final float n11 = n4 / n8;
                        final float n12 = n3 / n7;
                        final float n13 = array[1] * 64.0f * n11 / n4;
                        final float n14 = array[2] * 64.0f * n12 / n3;
                        final float n15 = (array[1] + 1) * 64.0f * n11 / n4;
                        final float n16 = (array[2] + 1) * 64.0f * n12 / n3;
                        final float n17 = n * 64.0f * n9 / n4;
                        final float n18 = n2 * 64.0f * n10 / n3;
                        float n19 = (n + 1) * 64.0f * n9 / n4;
                        float n20 = (n2 + 1) * 64.0f * n10 / n3;
                        if (b2) {
                            n19 = 1.0f;
                        }
                        if (b3) {
                            n20 = 1.0f;
                        }
                        final float a = com.iseemedia.apps.tourclients40.players.e.a(this.w, n17, n13, n15);
                        final float a2 = com.iseemedia.apps.tourclients40.players.e.a(this.w, n18, n14, n16);
                        final float a3 = com.iseemedia.apps.tourclients40.players.e.a(this.w, n19, a, n15);
                        final float a4 = com.iseemedia.apps.tourclients40.players.e.a(this.w, n20, a2, n16);
                        b.a[3][2] = (int)((a - n13) / (n15 - n13) * 16384.0f);
                        b.a[3][3] = (int)((a2 - n14) / (n16 - n14) * 16384.0f);
                        b.a[2][2] = (int)((a3 - n13) / (n15 - n13) * 16384.0f);
                        if (b2 && (this.u[array[0]].width & 0x3F) > 0) {
                            b.a[2][2] = (int)(((this.u[array[0]].width & 0x3F) - 1.5f) / 64.0f * 16384.0f);
                            if (b.a[2][2] < 0) {
                                b.a[2][2] = 0;
                            }
                        }
                        b.a[2][3] = b.a[3][3];
                        b.a[1][3] = (int)((a4 - n14) / (n16 - n14) * 16384.0f);
                        b.a[1][2] = b.a[2][2];
                        b.a[0][2] = (int)((a - n13) / (n15 - n13) * 16384.0f);
                        b.a[0][3] = b.a[1][3];
                    }
                }
                
                public final void run() {
                    i[] array = this.r;
                    i[] array2 = this.s;
                    int n;
                    int n2;
                    if (this.q == 0) {
                        n = 16384;
                        n2 = -819;
                    }
                    else {
                        n = 819;
                        n2 = -16384;
                    }
                    if (this.m > 0) {
                        --this.m;
                    }
                    com.iseemedia.apps.tourclients40.players.e.a(this.w, array, this.m);
                    com.iseemedia.apps.tourclients40.players.e.b(this.w, array, this.p - this.n);
                    for (int i = this.m; i < this.o; ++i) {
                        Thread.yield();
                        com.iseemedia.apps.tourclients40.players.e.a(this.w, array2, i + 1);
                        com.iseemedia.apps.tourclients40.players.e.b(this.w, array2, this.p - this.n);
                        for (int j = 0; j < this.p - this.n; ++j) {
                            if (array2[j].a[7] >= 163 && array2[j + 1].a[7] >= 163 && array[j].a[7] >= 163) {
                                if (array[j + 1].a[7] >= 163) {
                                    if (array2[j].a[5] <= 16384 || array2[j + 1].a[5] <= 16384 || array[j].a[5] <= 16384 || array[j + 1].a[5] <= 16384) {
                                        if (array2[j].a[5] >= -16384 || array2[j + 1].a[5] >= -16384 || array[j].a[5] >= -16384 || array[j + 1].a[5] >= -16384) {
                                            if (array2[j].a[6] <= n || array2[j + 1].a[6] <= n || array[j].a[6] <= n || array[j + 1].a[6] <= n) {
                                                if (array2[j].a[6] >= n2 || array2[j + 1].a[6] >= n2 || array[j].a[6] >= n2 || array[j + 1].a[6] >= n2) {
                                                    this.b.a[0][0] = array2[j].a[5];
                                                    this.b.a[0][1] = array2[j].a[6];
                                                    this.b.a[1][0] = array2[j + 1].a[5];
                                                    this.b.a[1][1] = array2[j + 1].a[6];
                                                    this.b.a[2][0] = array[j + 1].a[5];
                                                    this.b.a[2][1] = array[j + 1].a[6];
                                                    this.b.a[3][0] = array[j].a[5];
                                                    this.b.a[3][1] = array[j].a[6];
                                                    this.c[0] = com.iseemedia.apps.tourclients40.players.e.k(this.w);
                                                    this.c[1] = j + this.n;
                                                    this.c[2] = i;
                                                    if (com.iseemedia.apps.tourclients40.players.e.l(this.w) == 32) {
                                                        final int[] c = this.c;
                                                        final int n3 = 1;
                                                        c[n3] /= 2;
                                                        final int[] c2 = this.c;
                                                        final int n4 = 2;
                                                        c2[n4] /= 2;
                                                    }
                                                    final int n5 = this.c[1];
                                                    final int n6 = this.c[2];
                                                    if (com.iseemedia.apps.tourclients40.players.e.m(this.w) && j == 0) {
                                                        this.h = this.t.a(this.c, !this.w.E || !com.iseemedia.apps.tourclients40.players.e.n(this.w));
                                                        for (int k = 1; k < 65; ++k) {
                                                            for (int l = 0; l < 64; ++l) {
                                                                this.i[k * 65 + l + 1] = this.h[(k - 1) * 64 + l];
                                                            }
                                                        }
                                                        for (int n7 = 0; n7 < 65; ++n7) {
                                                            this.i[n7 * 65] = this.i[n7 * 65 + 1];
                                                        }
                                                    }
                                                    else {
                                                        this.h = this.t.a(this.c, !this.w.E || !com.iseemedia.apps.tourclients40.players.e.n(this.w));
                                                        for (int n8 = 1; n8 < 65; ++n8) {
                                                            this.i[n8 * 65] = this.i[n8 * 65 + 64];
                                                        }
                                                        for (int n9 = 1; n9 < 65; ++n9) {
                                                            for (int n10 = 0; n10 < 64; ++n10) {
                                                                this.i[n9 * 65 + n10 + 1] = this.h[(n9 - 1) * 64 + n10];
                                                            }
                                                        }
                                                    }
                                                    if (this.c[3] == 2) {
                                                        if (this.q == 0) {
                                                            com.iseemedia.apps.tourclients40.players.e.a(this.w, true);
                                                        }
                                                        else {
                                                            com.iseemedia.apps.tourclients40.players.e.b(this.w, true);
                                                        }
                                                    }
                                                    if (this.l != this.c[3] || this.l <= 3) {
                                                        for (int n11 = 0; n11 < 65; ++n11) {
                                                            this.i[n11 * 65] = this.i[n11 * 65 + 1];
                                                        }
                                                    }
                                                    this.l = this.c[3];
                                                    if (this.k[j] == this.c[3] && this.k[j] > 3) {
                                                        for (int n12 = 0; n12 < 65; ++n12) {
                                                            this.i[n12] = this.j[j * 65 + n12];
                                                        }
                                                    }
                                                    else {
                                                        for (int n13 = 0; n13 < 65; ++n13) {
                                                            this.i[n13] = this.i[65 + n13];
                                                        }
                                                    }
                                                    this.k[j] = this.c[3];
                                                    this.a(this.b, this.c, n5, n6);
                                                    if (com.iseemedia.apps.tourclients40.players.e.l(this.w) < 64) {
                                                        com.iseemedia.apps.tourclients40.players.e.a(this.w, this.b, this.c, j + this.n, i);
                                                    }
                                                    this.a(this.b);
                                                    for (int n14 = 0; n14 < 65; ++n14) {
                                                        this.j[j * 65 + n14] = this.i[4160 + n14];
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if ((i & 0x1) == 0x0) {
                            array = this.s;
                            array2 = this.r;
                        }
                        else {
                            array = this.r;
                            array2 = this.s;
                        }
                    }
                    if ((com.iseemedia.apps.tourclients40.players.e.m(this.w) && this.n == 0) || !com.iseemedia.apps.tourclients40.players.e.m(this.w)) {
                        this.a.a();
                    }
                    if (this.q == 0) {
                        com.iseemedia.apps.tourclients40.players.e.o(this.w).b();
                    }
                }
                
                protected final void finalize() throws Throwable {
                    try {
                        this.b = null;
                        this.c = null;
                        this.d = null;
                        this.f = null;
                        this.h = null;
                        this.i = null;
                        this.a = null;
                        this.j = null;
                        this.r = null;
                        this.s = null;
                        this.t = null;
                        this.u = null;
                        this.v = null;
                    }
                    finally {
                        super.finalize();
                    }
                }
            };
        }
        else {
            if (this.bu != null) {
                while (!b) {
                    if (!this.bu.isAlive()) {
                        b = true;
                    }
                }
            }
            this.bu = null;
        }
        this.bs.a(this.aT, this.aP, this.aU, this.aQ);
        (this.bu = new Thread(this.bs, "Thread Lower")).run();
    }
    
    private void D() {
        this.bq.a();
        this.bt = null;
        this.bu = null;
    }
    
    private void a(final i[] array, final int n) {
        for (int i = 0; i <= n; ++i) {
            array[i].a[5] = (int)(-(this.aY * array[i].a[0] + this.aZ * array[i].a[2]));
            array[i].a[6] = array[i].a[1];
            array[i].a[7] = (int)(this.aY * array[i].a[2] - this.aZ * array[i].a[0]);
            final float n2 = array[i].a[6];
            final float n3 = array[i].a[7];
            array[i].a[6] = (int)(this.ba * n2 - this.bb * n3);
            array[i].a[7] = (int)(this.ba * n3 + this.bb * n2);
            final float n4 = array[i].a[5];
            final float n5 = array[i].a[6];
            array[i].a[5] = (int)(this.bc * n4 - this.bd * n5);
            array[i].a[6] = (int)(this.bc * n5 + this.bd * n4);
            if (array[i].a[7] > 163) {
                array[i].a[5] = array[i].a[5] * this.be / array[i].a[7];
                array[i].a[6] = array[i].a[6] * this.bf / array[i].a[7];
            }
        }
    }
    
    private void b(final i[] array, final int n) {
        float an = n;
        int n2 = 0;
        if (an > this.aN) {
            an = this.aN;
        }
        if (this.X == 90) {
            float n3;
            if ((n3 = 1.5707964f - (this.Z + (this.Y - this.Z) * an / this.aN)) > 3.1415927f) {
                n3 = 3.1415927f;
            }
            final float n4 = (float)Math.sin(n3);
            for (int i = this.aP; i <= this.aQ; ++i) {
                final float n5 = i;
                float n6;
                if (this.ab - this.aa < 0.001f) {
                    n6 = 6.2831855f * n5 / this.aO;
                }
                else {
                    n6 = this.aa + (this.ab - this.aa) * n5 / this.aO;
                }
                if (n6 > 6.2831855f) {
                    n6 = 6.2831855f;
                }
                final float a = this.a(n6, -6.2831855f, 6.2831855f);
                array[n2].a[0] = (int)(-n4 * (float)Math.sin(a) * 16384.0f);
                array[n2].a[1] = (int)(Math.cos(n3) * 16384.0);
                array[n2].a[2] = (int)(n4 * (float)Math.cos(a) * 16384.0f);
                array[n2].a[3] = (int)(this.a(n5 / this.aO, 0.0f, 1.0f) * 16384.0f);
                array[n2].a[4] = (int)(this.a(an / this.aN, 0.0f, 1.0f) * 16384.0f);
                array[n2].a[5] = 0;
                array[n2].a[6] = 0;
                array[n2].a[7] = 0;
                ++n2;
            }
            return;
        }
        final float n7 = (float)Math.tan(this.Y);
        final float n8 = (float)Math.tan(this.Z);
        for (int j = this.aP; j <= this.aQ; ++j) {
            final float n9 = j;
            float n10;
            if (this.ab - this.aa < 0.001f) {
                n10 = 6.2831855f * n9 / this.aO;
            }
            else {
                n10 = this.aa + (this.ab - this.aa) * n9 / this.aO;
            }
            final float a2 = this.a(n10, 0.0f, 6.2831855f);
            array[n2].a[0] = (int)((float)(-Math.sin(a2)) * 16384.0f);
            array[n2].a[1] = (int)((n8 + (n7 - n8) * an / this.aN) * 16384.0f);
            array[n2].a[2] = (int)(Math.cos(a2) * 16384.0);
            array[n2].a[3] = (int)(this.a(n9 / this.aO, 0.0f, 1.0f) * 16384.0f);
            array[n2].a[4] = (int)(this.a(an / this.aN, 0.0f, 1.0f) * 16384.0f);
            array[n2].a[5] = 0;
            array[n2].a[6] = 0;
            array[n2].a[7] = 0;
            ++n2;
        }
    }
    
    private void a(final com.iseemedia.apps.tourclients40.players.b b, final int[] array, final int n, final int n2) {
        if (this.aX == 32) {
            final int n3 = (int)(0.5f / (float)Math.pow(2.0, this.aV - array[0]) * 16384.0f);
            if ((n & 0x1) == 0x1) {
                final int[] array2 = b.a[0];
                final int n4 = 2;
                array2[n4] += n3;
                final int[] array3 = b.a[3];
                final int n5 = 2;
                array3[n5] += n3;
            }
            else if (b.a[1][2] > b.a[0][2] + n3) {
                b.a[1][2] = b.a[0][2] + n3;
                b.a[2][2] = b.a[0][2] + n3;
            }
            if ((n2 & 0x1) == 0x1) {
                final int[] array4 = b.a[2];
                final int n6 = 3;
                array4[n6] += n3;
                final int[] array5 = b.a[3];
                final int n7 = 3;
                array5[n7] += n3;
                return;
            }
            if (b.a[0][3] > b.a[2][3] + n3) {
                b.a[0][3] = b.a[2][3] + n3;
                b.a[1][3] = b.a[2][3] + n3;
            }
        }
    }
    
    public final float[] i() {
        final float[] array;
        (array = new float[3])[0] = this.aG;
        array[1] = this.aH;
        array[2] = this.aI;
        return array;
    }
    
    public final float j() {
        return this.aJ;
    }
    
    public final void a(final float n, final boolean b) {
        if (b) {
            this.a(this.j());
        }
        super.w = true;
    }
    
    public final void k() {
        final float n = -1.0f;
        final float n2 = 0.0314f;
        super.u = true;
        final float j = this.j();
        this.a(j + n * (n2 * j));
        super.w = true;
    }
    
    public final void l() {
        final float n = 0.0314f;
        super.u = true;
        final float j = this.j();
        this.a(j + 1.0f * (n * j));
        super.w = true;
    }
    
    public final void m() {
        this.D();
        final float[] array = { this.an, this.ao, 0.0f };
        this.a(this.aq);
        this.a(array);
        this.a(this.aq);
        this.b(this.ar);
        super.n.b();
        if (super.y != null) {
            super.y.a(2);
        }
        this.c(2);
        super.w = true;
        super.A = 0;
    }
    
    public final void processKeyEvent(final KeyEvent keyEvent) {
        Label_0093: {
            switch (keyEvent.getID()) {
                case 401: {
                    switch (keyEvent.getKeyCode()) {
                        case 16: {
                            this.ay = true;
                            break;
                        }
                    }
                    break;
                }
                case 402: {
                    switch (keyEvent.getKeyCode()) {
                        case 16: {
                            this.ay = false;
                            break Label_0093;
                        }
                    }
                    break;
                }
            }
        }
        super.processKeyEvent(keyEvent);
    }
    
    public final void processMouseEvent(final MouseEvent mouseEvent) {
        if (mouseEvent.isPopupTrigger()) {
            super.F = true;
        }
        else if (super.F) {
            super.F = false;
        }
        else {
            switch (mouseEvent.getID()) {
                case 501: {
                    switch (mouseEvent.getModifiers() & 0x10) {
                        case 16: {
                            super.E = true;
                        }
                        case 4: {
                            if (this.bo) {
                                super.u = true;
                                final int x = mouseEvent.getX();
                                this.az = x;
                                this.aD = x;
                                this.aB = x;
                                final int y = mouseEvent.getY();
                                this.aA = y;
                                this.aE = y;
                                this.aC = y;
                                break;
                            }
                            break;
                        }
                    }
                    break;
                }
                case 502: {
                    if ((mouseEvent.getModifiers() & 0x10) == 0x10) {
                        super.E = false;
                        this.aD = mouseEvent.getX();
                        this.aE = mouseEvent.getY();
                        if (Math.abs(this.aD - this.az) < 3 && Math.abs(this.aE - this.aA) < 3) {
                            if (!this.ax && super.y != null && !super.y.b()) {
                                this.ax = true;
                                super.y.d();
                            }
                            if (super.r == 0) {
                                this.k();
                            }
                            else if (super.r == 1) {
                                this.l();
                            }
                        }
                    }
                    super.w = true;
                    break;
                }
            }
        }
        super.processMouseEvent(mouseEvent);
    }
    
    public final void processMouseMotionEvent(final MouseEvent mouseEvent) {
        switch (mouseEvent.getID()) {
            case 506: {
                if ((mouseEvent.getModifiers() & 0x4) != 0x4) {
                    this.aD = mouseEvent.getX();
                    this.aE = mouseEvent.getY();
                    super.w = true;
                    break;
                }
                break;
            }
        }
        super.processMouseMotionEvent(mouseEvent);
    }
    
    public final void n() {
        try {
            super.o.showDocument(new URL(com.iseemedia.apps.tourclients40.players.h.G), "iseemedia");
        }
        catch (MalformedURLException ex) {}
    }
    
    static final f a(final e e) {
        return e.V;
    }
    
    static final f b(final e e) {
        return e.W;
    }
    
    static final Rectangle c(final e e) {
        return e.au;
    }
    
    static final int d(final e e) {
        return e.ah;
    }
    
    static final Dimension[] e(final e e) {
        return e.ai;
    }
    
    static final Dimension[] f(final e e) {
        return e.aj;
    }
    
    static final float g(final e e) {
        return e.bg;
    }
    
    static final float h(final e e) {
        return e.bh;
    }
    
    static final float i(final e e) {
        return e.bi;
    }
    
    static final float j(final e e) {
        return e.bj;
    }
    
    static final int k(final e e) {
        return e.aV;
    }
    
    static final float a(final e e, final float n, final float n2, final float n3) {
        return e.a(n, n2, n3);
    }
    
    static final void a(final e e, final i[] array, final int n) {
        e.b(array, n);
    }
    
    static final void b(final e e, final i[] array, final int n) {
        e.a(array, n);
    }
    
    static final int l(final e e) {
        return e.aX;
    }
    
    static final boolean m(final e e) {
        return e.P;
    }
    
    static final boolean n(final e e) {
        return e.ay;
    }
    
    static final boolean a(final e e, final boolean al) {
        return e.al = al;
    }
    
    static final boolean b(final e e, final boolean am) {
        return e.am = am;
    }
    
    static final void a(final e e, final com.iseemedia.apps.tourclients40.players.b b, final int[] array, final int n, final int n2) {
        e.a(b, array, n, n2);
    }
    
    static final g o(final e e) {
        return e.bq;
    }
}
