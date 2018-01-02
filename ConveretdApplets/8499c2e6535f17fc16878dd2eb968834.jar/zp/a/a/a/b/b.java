// 
// Decompiled by Procyon v0.5.30
// 

package zp.a.a.a.b;

import java.awt.event.MouseEvent;
import java.awt.MediaTracker;
import java.awt.FontMetrics;
import java.awt.event.KeyEvent;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.image.ImageObserver;
import java.net.MalformedURLException;
import java.awt.Toolkit;
import zp.a.a.a.c.e;
import java.awt.LayoutManager;
import java.applet.AppletContext;
import java.awt.Frame;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Image;
import java.awt.Dimension;
import zp.a.a.a.e.g;
import java.net.URL;
import zp.a.a.a.e.f;

public class b extends a implements f
{
    private static final boolean ac = true;
    private static String l;
    private static final String a4 = "zoompanoapplet.properties";
    public static final int aK = 64;
    public static final int bp = 200;
    public static final int bf = 0;
    public static final int aV = 1;
    public static final int g = 2;
    public static final float K = 5.0f;
    private static final float k = 1.5707964f;
    private static final float o = 3.1415927f;
    private static final float aD = 6.2831855f;
    private static final int ah = 63;
    private static final float aM = 1.0E-4f;
    private static final int i = 0;
    private static final int z = 1;
    private static final int aZ = 2;
    private static final int aX = 3;
    private static final int R = 4;
    private URL v;
    private String bi;
    private String af;
    private String F;
    private String aA;
    private boolean bn;
    private String a7;
    private g D;
    private int aY;
    private float aU;
    private float aB;
    private float az;
    private float aQ;
    private float ao;
    private float bj;
    private float bo;
    private float p;
    private float av;
    private int J;
    private int a6;
    private int ar;
    private Dimension[] u;
    private Dimension[] Y;
    private float aC;
    private boolean aj;
    private int[] ai;
    private float P;
    private float a1;
    private float U;
    private float h;
    private Image t;
    private Image bl;
    private Image M;
    private Image au;
    private Image bd;
    private Image an;
    private zp.a.b.b B;
    private Rectangle as;
    private boolean am;
    private Color ae;
    private zp.a.a.a.c.a else;
    private zp.a.a.a.a.a m;
    public boolean O;
    private boolean aP;
    public boolean try;
    private float V;
    private Thread aO;
    private int bk;
    private zp.a.a.a.a.b[] bm;
    private int aF;
    private boolean void;
    private boolean long;
    private boolean int;
    private int a5;
    private int a3;
    private int ax;
    private int at;
    private int be;
    private int bc;
    private int ay;
    private boolean bh;
    private Frame ag;
    private float W;
    private float aq;
    private float aa;
    private float q;
    private float E;
    private float aw;
    private int ab;
    private int a9;
    private int L;
    private int aS;
    private int j;
    private int aH;
    private int w;
    private d[] bg;
    private d[] aL;
    private float I;
    private float aN;
    private float aE;
    private float T;
    private float A;
    private float X;
    private float G;
    private float n;
    private float al;
    private float N;
    private float aT;
    private float bq;
    private float[] H;
    private float[] C;
    private float[] aJ;
    private float[] aG;
    private float[] a0;
    private float[] a8;
    c bb;
    int[] Q;
    float[] aR;
    float[] aW;
    float[] ba;
    float[] ap;
    float[] ad;
    float[] Z;
    long r;
    boolean s;
    float S;
    float[] ak;
    protected volatile boolean aI;
    private boolean a2;
    
    public b(final URL v, final AppletContext do1, final String s, final int n, final int n2, final int n3, final int n4, final boolean am, final int n5, final int n6, final int n7, final int n8, final Dimension dimension, final Frame ag, final boolean bh, final String s2, final String s3, final String f, final String aa) {
        this.F = null;
        this.aA = null;
        this.bn = true;
        this.a7 = null;
        this.D = null;
        this.J = 0;
        this.a6 = 0;
        this.ar = 0;
        this.u = new Dimension[32];
        this.Y = new Dimension[32];
        this.aC = 0.7f;
        this.aj = false;
        this.t = null;
        this.bl = null;
        this.M = null;
        this.au = null;
        this.bd = null;
        this.an = null;
        this.B = null;
        this.as = null;
        this.am = false;
        this.else = null;
        this.m = null;
        this.O = true;
        this.try = false;
        this.V = 20.0f;
        this.aO = null;
        this.bk = 2;
        this.long = false;
        this.ag = null;
        this.bg = new d[200];
        this.aL = new d[200];
        this.bb = new c();
        this.Q = new int[4];
        this.aR = new float[2];
        this.aW = new float[2];
        this.ba = new float[2];
        this.ap = new float[2];
        this.ad = new float[2];
        this.Z = new float[2];
        this.r = 50L;
        this.s = false;
        this.ak = new float[3];
        this.aI = false;
        this.a2 = false;
        b.l = zp.a.a.a.d.a.a(zp.a.a.a.d.a.a("zoompanoapplet.properties"));
        this.bh = bh;
        this.v = v;
        super.do = do1;
        this.aF = n8;
        this.setBackground(this.ae = new Color(n5, n6, n7));
        this.setForeground(this.ae);
        this.F = f;
        this.aA = aa;
        this.setLayout(null);
        this.bm = new zp.a.a.a.a.b[64];
        this.bk = n8;
        this.ag = ag;
        zp.a.a.a.c.e.a();
        this.if(0);
        this.reshape(0, 0, dimension.width, dimension.height);
        if (s != null) {
            this.am = am;
            try {
                this.t = Toolkit.getDefaultToolkit().getImage(new URL(this.v, s));
            }
            catch (MalformedURLException ex) {
                this.t = null;
                this.am = false;
            }
            if (this.t != null) {
                Toolkit.getDefaultToolkit().prepareImage(this.t, dimension.width, dimension.height, this);
                this.as = new Rectangle(n, n2, n3, n4);
            }
            else {
                this.am = false;
            }
        }
        else {
            this.am = false;
        }
        this.m = new zp.a.a.a.a.a(this, super.do, !this.bh);
        this.void = false;
        final int n9 = -1000;
        this.a3 = n9;
        this.a5 = n9;
        this.bc = n9;
        this.be = n9;
        this.at = n9;
        this.ax = n9;
        this.int = false;
        this.H = new float[3];
        this.C = new float[3];
        this.aJ = new float[3];
        this.aG = new float[3];
        this.a0 = new float[3];
        this.a8 = new float[3];
        for (int i = 0; i < 200; ++i) {
            this.bg[i] = new d();
            this.aL[i] = new d();
        }
        this.W = 0.0f;
        this.aq = 3.1415927f;
        this.aa = 0.0f;
        this.q = 0.87266463f;
        this.aB = -1.5707964f;
        this.az = 1.5707964f;
        this.aQ = 0.0f;
        this.ao = 0.0f;
        this.aU = 0.08726647f;
    }
    
    public void int(final int n) {
        this.bm[n] = null;
        for (int i = 63; i >= 0; --i) {
            if (this.bm[i] != null) {
                return;
            }
        }
        if (this.else != null) {
            this.else.if(1);
        }
    }
    
    protected void else() {
        this.O = (this.V == 0.0f);
        if (this.as == null) {
            this.as = new Rectangle(0, 0, this.bounds().width, this.bounds().height - 24);
        }
        if (this.a7 == null) {
            this.a(zp.a.a.a.b.a.d, zp.a.a.a.b.a.c);
            return;
        }
        this.D = null;
        try {
            this.D = new g(this.a7, this, this, 120);
        }
        catch (zp.a.a.a.e.c c) {
            this.a(zp.a.a.a.b.a.b, c.getMessage());
            this.D = null;
        }
        catch (Exception ex) {
            this.D = null;
            this.a(zp.a.a.a.b.a.d, zp.a.a.a.b.a.c);
        }
        if (this.else == null) {
            (this.else = new zp.a.a.a.c.a(this, this.bounds().height, super.do, this.bk, this.v, this.F, false, true, true)).if(1);
            for (int i = 63; i >= 0; --i) {
                if (this.bm[i] != null) {
                    this.else.do(1);
                    break;
                }
            }
            if (this.int) {
                this.else.if(1, 3);
            }
        }
        this.requestFocus();
        this.r = 20L;
        this.s = (this.V < 0.0f);
        this.S = 0.0f;
        if (!this.O) {
            if (this.ao == 0.0f && this.aQ == 0.0f) {
                this.S = 6.2831855f / (Math.abs(this.V) * 15.0f);
            }
            else {
                this.S = (this.ao - this.aQ) / (Math.abs(this.V) * 15.0f);
            }
        }
    }
    
    protected void int() {
        super.do = null;
        if (this.D != null) {
            this.D.a(true);
        }
        this.D = null;
        if (this.m != null) {
            this.m = null;
        }
        if (this.t != null) {
            this.t.flush();
        }
        if (this.bl != null) {
            this.bl.flush();
        }
        if (this.M != null) {
            this.M.flush();
        }
        if (this.au != null) {
            this.au.flush();
        }
        if (this.bd != null) {
            this.bd.flush();
        }
        if (this.an != null) {
            this.an.flush();
        }
        if (this.B != null && this.B.if != null) {
            this.B.if.flush();
        }
        this.removeAll();
        this.t = null;
        this.bl = null;
        this.M = null;
        this.au = null;
        this.bd = null;
        this.an = null;
        this.B = null;
    }
    
    protected boolean a() {
        if (!this.O) {
            final float[] array = new float[3];
            this.if(array);
            final float n = array[1];
            if (this.s) {
                final float[] array2 = array;
                final int n2 = 1;
                array2[n2] += this.S;
            }
            else {
                final float[] array3 = array;
                final int n3 = 1;
                array3[n3] -= this.S;
            }
            array[0] = this.P;
            array[2] = this.U;
            this.a(this.h);
            try {
                this.void();
            }
            catch (Exception ex) {}
            this.a(array);
            this.if(this.ak);
            this.if(array);
            if (Math.abs(array[1] - n) < 1.0E-4f) {
                this.s = !this.s;
            }
            try {
                Thread.sleep(this.r);
            }
            catch (InterruptedException ex2) {
                this.try();
            }
            return true;
        }
        if (this.void) {
            this.O = true;
            this.a2 = true;
            this.else.if(0, 3);
            this.for(4);
        }
        if (this.try) {
            this.try = false;
            this.void();
            return true;
        }
        return false;
    }
    
    public void do(final int n) {
        if (n == 100) {
            this.m.if();
        }
        this.try = true;
    }
    
    public void try() {
        this.O = true;
        this.try = true;
    }
    
    public void a(final String a7, final int ay, final float ab, final float az, final float aq, final float ao, final float bj, final float bo, final float p12, final float av, final float v, final boolean int1) {
        this.a7 = a7;
        this.aY = ay;
        if (zp.a.a.a.d.a.if && !this.goto()) {
            this.a("You need the MRJ2.2", "Get it from following URL");
            this.D = null;
            return;
        }
        if (this.aY != 0 && this.aY != 1) {
            this.a("This player only supports", "Sphere and Cylinder Panoramas.");
        }
        this.V = v;
        if (this.V == 0.0f) {
            this.O = true;
        }
        else {
            this.O = false;
        }
        this.int = int1;
        this.aB = ab;
        this.az = az;
        this.aQ = aq;
        this.ao = ao;
        this.bj = bj;
        this.bo = bo;
        this.p = p12;
        this.av = av;
        if (this.aY == 1) {
            if (this.aB < -1.4137167f) {
                this.aB = -1.4137167f;
            }
            if (this.az > 1.4137167f) {
                this.az = 1.4137167f;
            }
        }
    }
    
    private boolean j() {
        if (this.D == null) {
            return false;
        }
        if (this.ar > 0) {
            return true;
        }
        final Dimension dimension = new Dimension();
        if (!this.D.a(dimension)) {
            return false;
        }
        this.J = dimension.width;
        this.a6 = dimension.height;
        this.ar = this.D.try();
        if (this.ar <= 0) {
            return false;
        }
        for (int i = this.ar; i >= 0; --i) {
            this.u[i] = this.D.do(i);
            this.Y[i] = this.D.a(i);
        }
        this.j = this.ar;
        while (this.j >= 0) {
            if (this.Y[this.j].width <= 16) {
                if (this.j < this.ar) {
                    ++this.j;
                    break;
                }
                break;
            }
            else {
                --this.j;
            }
        }
        this.aH = this.j;
        return true;
    }
    
    private void a(final Graphics graphics) {
        final int width = this.bounds().width;
        final int height = this.bounds().height;
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, width, height);
        if (this.aA != null) {
            try {
                final Image image = Toolkit.getDefaultToolkit().getImage(new URL(this.v, this.aA));
                if (this.a(image, this)) {
                    final int height2 = image.getHeight(this);
                    final int width2 = image.getWidth(this);
                    if (width2 > 0 && height2 > 0) {
                        graphics.drawImage(image, (width >> 1) - (width2 >> 1), (height >> 1) - (height2 >> 1), this);
                    }
                }
            }
            catch (MalformedURLException ex) {}
        }
        else {
            Image image2 = null;
            try {
                final InputStream a = zp.a.a.a.d.a.a("splashscreen.gif");
                final byte[] array = new byte[32768];
                final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                int read;
                while ((read = a.read(array)) > 0) {
                    byteArrayOutputStream.write(array, 0, read);
                }
                image2 = Toolkit.getDefaultToolkit().createImage(byteArrayOutputStream.toByteArray());
            }
            catch (Throwable t) {}
            if (this.a(image2, this)) {
                final int height3 = image2.getHeight(this);
                final int width3 = image2.getWidth(this);
                if (width3 > 0 && height3 > 0) {
                    graphics.drawImage(image2, (width >> 1) - (width3 >> 1), (height >> 1) - (height3 >> 1), this);
                }
            }
        }
    }
    
    private void a(final String bi, final String af) {
        if (this.bi != null) {
            return;
        }
        this.bi = bi;
        this.af = af;
        this.try = true;
    }
    
    public void paint(final Graphics graphics) {
        if (this.bn) {
            this.bn = false;
            this.a(graphics);
        }
        this.try = true;
    }
    
    private void do(final Graphics graphics) {
        if (this.t != null && (this.as.width != this.size().width || this.as.height != this.size().height)) {
            final int width = this.bounds().width;
            final int height = this.bounds().height;
            if (this.bd == null && this.a(this.t, this)) {
                this.bd = this.createImage(width, height - (this.as.y + this.as.height));
                final Graphics graphics2 = this.bd.getGraphics();
                graphics2.setColor(this.ae);
                graphics2.fillRect(0, 0, width, height - (this.as.y + this.as.height));
                graphics2.drawImage(this.t, 0, -(this.as.y + this.as.height), width, height, this);
                graphics2.dispose();
                this.M = this.createImage(width, this.as.y);
                final Graphics graphics3 = this.M.getGraphics();
                graphics3.setColor(this.ae);
                graphics3.fillRect(0, 0, width, this.as.y);
                graphics3.drawImage(this.t, 0, 0, width, height, this);
                graphics3.dispose();
                this.bl = this.createImage(this.as.x, height);
                final Graphics graphics4 = this.bl.getGraphics();
                graphics4.setColor(this.ae);
                graphics4.fillRect(0, 0, this.as.x, height);
                graphics4.drawImage(this.t, 0, 0, width, height, this);
                graphics4.dispose();
                this.au = this.createImage(width - (this.as.x + this.as.width), height);
                final Graphics graphics5 = this.au.getGraphics();
                graphics5.setColor(this.ae);
                graphics5.fillRect(0, 0, width - (this.as.x + this.as.width), height);
                graphics5.drawImage(this.t, -(this.as.x + this.as.width), 0, width, height, this);
                graphics5.dispose();
                this.t.flush();
            }
            graphics.drawImage(this.M, 0, 0, this);
            graphics.drawImage(this.bl, 0, 0, this);
            graphics.drawImage(this.bd, 0, this.as.y + this.as.height, this);
            graphics.drawImage(this.au, this.as.x + this.as.width, 0, this);
        }
    }
    
    private void if(final Graphics graphics) {
        graphics.setColor(this.ae);
        if (this.t == null) {
            graphics.fillRect(0, 0, this.size().width, this.as.y);
            graphics.fillRect(0, 0, this.as.x, this.size().height);
            graphics.fillRect(this.as.x + this.as.width, 0, this.size().width - (this.as.x + this.as.width), this.size().height);
            graphics.fillRect(0, this.as.y + this.as.height, this.size().width, this.size().height - (this.as.y + this.as.height));
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private void d() {
        final int[] array = new int[2];
        final float[] array2 = new float[3];
        for (int i = 0; i < 64; ++i) {
            if (this.bm[i] != null) {
                if (this.bm[i].a) {
                    if (Math.abs(this.bm[i].byte - this.bm[i].if) <= 0.95f || Math.abs(this.bm[i].new - this.bm[i].try) <= 0.95f) {
                        this.do((this.bm[i].byte + this.bm[i].if) * 0.5f, (this.bm[i].new + this.bm[i].try) * 0.5f, array2);
                        if (Math.abs(array2[0] - this.W) <= 1.22f) {
                            if (Math.abs(array2[1] - this.aq) <= 1.22f || Math.abs(array2[1] - this.aq) >= 5.0631857f) {
                                this.a(array2[1], array2[0], array);
                                final int n = array[0];
                                final int n2 = array[1];
                                int n3 = n - 12;
                                int n4 = n + 12;
                                int n5 = n2 - 12;
                                int n6 = n2 + 12;
                                if (n3 < 0) {
                                    n3 = 0;
                                }
                                if (n5 < 0) {
                                    n5 = 0;
                                }
                                if (n4 >= this.B.new) {
                                    n4 = this.B.new - 1;
                                }
                                if (n6 >= this.B.do) {
                                    n6 = this.B.do - 1;
                                }
                                final int n7 = n3 - n;
                                final int n8 = n4 - n;
                                final int n9 = n5 - n2;
                                for (int n10 = n6 - n2, j = n9; j <= n10; ++j) {
                                    for (int k = n7; k <= n8; ++k) {
                                        final int n11 = k * k + j * j;
                                        if ((n11 <= 144 && n11 >= 70) || (n11 <= 36 && n11 >= 6)) {
                                            this.B.a[(n2 + j) * this.B.new + (n + k)] = -65536;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    private void e() {
        final float[] array = new float[2];
        this.a(this.be, this.bc, array);
        final int[] array2 = new int[2];
        final float[] array3 = new float[3];
        for (int i = 0; i < 64; ++i) {
            if (this.bm[i] != null) {
                if (this.bm[i].a) {
                    this.do((this.bm[i].byte + this.bm[i].if) * 0.5f, (this.bm[i].new + this.bm[i].try) * 0.5f, array3);
                    this.a(array3[1], array3[0], array2);
                    if (this.bm[i].a(array[0], array[1], this.be, this.bc, array2[0], array2[1], super.do)) {
                        break;
                    }
                }
            }
        }
    }
    
    public void processKeyEvent(final KeyEvent keyEvent) {
        this.O = true;
        this.O = true;
        this.a2 = true;
        this.else.if(0, 3);
        Label_0307: {
            switch (keyEvent.getID()) {
                case 401: {
                    switch (keyEvent.getKeyCode()) {
                        case 38: {
                            this.O = true;
                            this.long = true;
                            this.for(2);
                            break Label_0307;
                        }
                        case 40: {
                            this.O = true;
                            this.long = true;
                            this.for(3);
                            break Label_0307;
                        }
                        case 37: {
                            this.O = true;
                            this.long = true;
                            this.for(0);
                            break Label_0307;
                        }
                        case 39: {
                            this.long = true;
                            this.O = true;
                            this.for(1);
                            break Label_0307;
                        }
                        case 33:
                        case 65:
                        case 97: {
                            this.g();
                            this.long = true;
                            break Label_0307;
                        }
                        case 34:
                        case 90:
                        case 122: {
                            this.b();
                            this.long = true;
                            break Label_0307;
                        }
                        case 73:
                        case 105: {
                            this.a(0);
                            this.long = true;
                            break Label_0307;
                        }
                        case 86:
                        case 118: {
                            this.long = false;
                            this.void = false;
                            this.new();
                            break Label_0307;
                        }
                    }
                    break;
                }
                case 400: {
                    break;
                }
                case 402: {
                    this.long = false;
                    this.try = true;
                    break;
                }
            }
        }
        super.processKeyEvent(keyEvent);
    }
    
    private void for(final int n) {
        this.O = true;
        final float[] array = new float[3];
        final float n2 = 0.3f;
        final float n3 = 0.3f;
        float n5;
        float n4 = n5 = 0.0f;
        final float i = this.i();
        this.if(array);
        if (this.void) {
            final float n6 = this.be - this.ax;
            final float n7 = this.at - this.bc;
            final float n8 = n6 / this.al;
            final float n9 = n7 / this.N;
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
            final float[] array2 = array;
            final int n12 = 0;
            array2[n12] += n11 * n3 * i;
            final float[] array3 = array;
            final int n13 = 1;
            array3[n13] += n10 * n2 * i;
        }
        else {
            switch (n) {
                case 0: {
                    n5 -= 0.1f;
                    break;
                }
                case 1: {
                    n5 += 0.1f;
                    break;
                }
                case 2: {
                    n4 += 0.1f;
                    break;
                }
                case 3: {
                    n4 -= 0.1f;
                    break;
                }
            }
            final float[] array4 = array;
            final int n14 = 0;
            array4[n14] += n4 * i;
            final float[] array5 = array;
            final int n15 = 1;
            array5[n15] += n5 * i;
        }
        array[2] = 0.0f;
        this.a(array);
        this.try = true;
    }
    
    public void void() {
        if (this.as == null) {
            return;
        }
        if (this.bi != null) {
            final Graphics graphics = this.getGraphics();
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            graphics.setColor(Color.white);
            graphics.fillRect(this.as.x, this.as.y, this.as.width, this.as.height);
            graphics.setColor(Color.black);
            graphics.drawString(this.bi, this.as.x + (this.as.width >> 1) - (fontMetrics.stringWidth(this.bi) >> 1), this.as.y + (this.as.height >> 1));
            if (this.af != null) {
                graphics.drawString(this.af, this.as.x + (this.as.width >> 1) - (fontMetrics.stringWidth(this.af) >> 1), this.as.y + (this.as.height >> 1) + fontMetrics.getHeight());
            }
            if (zp.a.a.a.d.a.if && !this.goto()) {
                final String s = "http://register.mgisoft.com/viewers/";
                final String s2 = "menu.asp?product=mac_mrj";
                graphics.setColor(Color.blue);
                graphics.drawString(s, this.as.x + (this.as.width >> 1) - (fontMetrics.stringWidth(s) >> 1), this.as.y + (this.as.height >> 1) + 2 * fontMetrics.getHeight());
                graphics.drawString(s2, this.as.x + (this.as.width >> 1) - (fontMetrics.stringWidth(s2) >> 1), this.as.y + (this.as.height >> 1) + 3 * fontMetrics.getHeight());
            }
            graphics.dispose();
            return;
        }
        if ((this.am && this.an == null) || (!zp.a.a.a.d.a.byte && zp.a.a.a.d.a.case && this.an == null)) {
            this.an = this.createImage(this.size().width, this.size().height);
        }
        if (this.B == null) {
            this.B = new zp.a.b.b(this.as.width, this.as.height);
            this.al = this.as.width * 0.5f;
            this.N = this.as.height * 0.5f;
            this.aT = this.as.width;
            this.bq = this.as.height;
        }
        try {
            if (!this.f()) {
                return;
            }
            if (this.int) {
                this.d();
                this.e();
            }
            this.B.a();
        }
        catch (Exception ex) {}
        final Graphics graphics2 = this.getGraphics();
        Graphics graphics3;
        if (this.am || (!zp.a.a.a.d.a.byte && zp.a.a.a.d.a.case)) {
            graphics3 = this.an.getGraphics();
        }
        else {
            graphics3 = graphics2;
        }
        this.if(graphics3);
        if (this.t != null && !this.am) {
            this.do(graphics3);
        }
        graphics3.drawImage(this.B.if, this.as.x, this.as.y, this.as.width, this.as.height, null);
        if (this.am) {
            graphics3.drawImage(this.t, 0, 0, this.size().width, this.size().height, this);
            graphics3.dispose();
            graphics2.drawImage(this.an, 0, 0, null);
        }
        else if (!zp.a.a.a.d.a.byte && zp.a.a.a.d.a.case) {
            graphics3.dispose();
            graphics2.drawImage(this.an, 0, 0, null);
        }
        graphics2.dispose();
    }
    
    public void a(final float p4, final float a1, final float u, final float h) {
        this.P = p4;
        this.a1 = a1;
        this.U = u;
        this.h = h;
    }
    
    public void if(final int ay) {
        this.ay = ay;
        if (this.ag == null) {
            return;
        }
        switch (this.ay) {
            case 0: {
                this.ag.setCursor(0);
                break;
            }
            case 1: {
                this.ag.setCursor(0);
                break;
            }
            case 3: {
                this.ag.setCursor(0);
                break;
            }
            default: {
                this.ag.setCursor(0);
                break;
            }
        }
    }
    
    private boolean a(final Image image, final Component component) {
        if (image == null) {
            return false;
        }
        final MediaTracker mediaTracker = new MediaTracker(component);
        mediaTracker.addImage(image, 1);
        try {
            mediaTracker.waitForID(1);
        }
        catch (InterruptedException ex) {
            return false;
        }
        return !mediaTracker.isErrorAny();
    }
    
    private void a(final int n, final int n2, final float[] array) {
        final float n3 = n / this.aT;
        final float n4 = n2 / this.bq;
        final float n5 = this.q * 0.5f;
        final float n6 = n5 * this.al / this.N;
        float n7 = (n3 - 0.5f) * 2.0f;
        if (n7 < 0.005 && n7 > -0.005) {
            n7 = 0.0f;
        }
        float n8 = (0.5f - n4) * 2.0f;
        if (n8 < 0.005 && n8 > -0.005) {
            n8 = 0.0f;
        }
        final float ae = this.aE;
        final float t = this.T;
        final float n9 = (float)Math.tan(n5);
        final float n10 = ae - n9 * n8 * t;
        final float n11 = t + n9 * n8 * ae;
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
        final float n14 = n10 / (float)Math.cos(n13);
        float n15;
        if (n14 < 1.0E-4f && n14 > -1.0E-4f) {
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
        float n16 = n13 + this.aq;
        if (n15 <= this.aB) {
            n15 = -n15 - 3.1415927f;
        }
        else if (n15 > this.az) {
            n15 = 3.1415927f - n15;
        }
        if (this.ao - this.aQ < 0.001f) {
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
        if (this.ao - this.aQ < 0.001f) {
            this.a(0.0f, this.aB, array3);
            this.a(6.2831855f, this.az, array4);
        }
        else {
            this.a(this.aQ, this.aB, array3);
            this.a(this.ao, this.az, array4);
        }
        if (Math.abs(array4[0] - array3[0]) > 1.0E-6f) {
            array[0] = (array2[0] - array3[0]) / (array4[0] - array3[0]);
            array[1] = (array4[1] - array2[1]) / (array4[1] - array3[1]);
        }
        else {
            array[0] = array2[0];
            array[1] = array2[1];
        }
    }
    
    private void do(final float n, final float n2, final float[] array) {
        final float[] array2 = new float[2];
        final float[] array3 = new float[2];
        this.a(this.aQ, this.aB, array2);
        if (this.ao - this.aQ < 0.001f) {
            this.a(6.2831855f, this.az, array3);
        }
        else {
            this.a(this.ao, this.az, array3);
        }
        float n3;
        float n4;
        if (Math.abs(array3[0] - array2[0]) > 1.0E-6f) {
            n3 = n * (array3[0] - array2[0]) + array2[0];
            n4 = array3[1] - n2 * (array3[1] - array2[1]);
        }
        else {
            n3 = n;
            n4 = n2;
        }
        this.if(n3, n4, array);
    }
    
    private void if(final float n, final float n2, final float[] array) {
        if (this.aY == 0) {
            array[0] = n2;
            array[1] = n;
        }
        else {
            final float n3 = (float)Math.tan(this.az);
            array[1] = n;
            if (n2 > n3) {
                array[0] = 1.5707964f - (float)Math.atan(-(n2 - n3 - 1.0f) / n3);
            }
            else if (n2 < -n3) {
                array[0] = (float)Math.atan((n2 + n3 + 1.0f) / n3) - 1.5707964f;
            }
            else {
                array[0] = (float)Math.atan(n2);
            }
        }
    }
    
    private void a(float n, final float n2, final int[] array) {
        final float n3 = this.q * 0.5f;
        final float n4 = n3 * this.al / this.N;
        n -= this.aq;
        final float n5 = (float)Math.tan(n3);
        final float n6 = (float)Math.tan(n2);
        final float n7 = (float)Math.cos(n);
        final float n8 = (n6 * this.aE - this.T * n7) / ((n7 * this.aE + n6 * this.T) * n5);
        array[0] = (int)(this.aT * ((float)Math.tan(n) * (this.aE - n5 * n8 * this.T) / (float)Math.tan(n4) / 2.0f + 0.5f));
        array[1] = (int)(this.bq * (0.5f - n8 / 2.0f));
    }
    
    private void a(final float n, final float n2, final float[] array) {
        if (this.aY == 0) {
            array[0] = n;
            array[1] = n2;
        }
        else {
            array[0] = n;
            final float n3 = (float)Math.tan(this.az);
            if (n2 > this.az) {
                array[1] = n3 + 1.0f - n3 * (float)Math.tan(1.5707964f - n2);
            }
            else if (n2 < -this.az) {
                array[1] = -n3 - (1.0f - n3 * (float)Math.tan(1.5707964f + n2));
            }
            else {
                array[1] = (float)Math.tan(n2);
            }
        }
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
    
    public void a(final float[] array) {
        this.W = array[0];
        this.aq = array[1];
        this.aa = array[2];
        final float n = this.q * 0.5f;
        if (Math.abs(this.av - this.p) > 0.001f) {
            this.aq = this.a(this.aq, this.p, this.av);
        }
        if (this.W > 1.5707964f) {
            this.W = 1.5707964f;
        }
        if (this.W < -1.5707964f) {
            this.W = -1.5707964f;
        }
        if (this.ao - this.aQ > 0.001f) {
            final float n2 = n * (this.al / this.N);
            if (this.aq + n2 > this.ao - 0.1f) {
                this.aq = this.ao - n2 - 0.1f;
            }
            if (this.aq - n2 < this.aQ + 0.1f) {
                this.aq = this.aQ + n2 + 0.1f;
            }
            if (this.aq > this.ao || this.aq < this.aQ) {
                this.aq = (this.ao - this.aQ) / 2.0f;
            }
        }
        while (this.aq < 0.0f) {
            this.aq += 6.2831855f;
        }
        while (this.aq > 6.2831855f) {
            this.aq -= 6.2831855f;
        }
        if (this.bo < 1.5707964f && this.W + n > this.bo - 1.0E-4f) {
            this.W = this.bo - n - 1.0E-4f;
        }
        if (this.bj > -1.5707964f && this.W - n < this.bj + 1.0E-4f) {
            this.W = this.bj + n + 1.0E-4f;
        }
        this.I = (float)Math.cos(this.aq);
        this.aN = (float)Math.sin(this.aq);
        this.aE = (float)Math.cos(this.W);
        this.T = (float)Math.sin(this.W);
        this.A = (float)Math.cos(this.aa);
        this.X = (float)Math.sin(this.aa);
    }
    
    public void a(final float q) {
        this.q = q;
        if (this.q < this.aU) {
            this.q = this.aU;
        }
        if (this.q > 2.094f) {
            this.q = 2.094f;
        }
        if (this.q > this.az - this.aB - 1.0E-4f) {
            this.q = this.az - this.aB - 1.0E-4f;
        }
        if (this.q > this.bo - this.bj - 1.0E-4f) {
            this.q = this.bo - this.bj - 1.0E-4f;
        }
        float n = this.q * this.al / this.N;
        if (n > 2.094f) {
            n = 2.094f;
            this.q = n * this.N / this.al;
        }
        if (this.ao - this.aQ > 0.05f && n > this.ao - this.aQ - 0.05f) {
            n = this.ao - this.aQ - 0.05f;
            this.q = n * this.N / this.al;
        }
        if (this.av - this.p > 0.05f && n > this.av - this.p - 0.05f) {
            this.q = (this.av - this.p - 0.05f) * this.N / this.al;
        }
    }
    
    private void null() {
        if (this.J <= 3000) {
            this.w = 32;
        }
        else {
            this.w = 64;
        }
        final float[] array = new float[3];
        final float[] array2 = new float[3];
        this.if(array);
        array2[0] = 0.0f;
        array2[1] = array[1];
        array2[2] = array[2];
        this.a(array2);
        this.a(0, this.B.do - 1, this.aR);
        this.a(this.B.new, this.B.do, this.ba);
        this.a(this.B.new, 0, this.ap);
        this.a(0, 0, this.Z);
        float n = Math.min(this.aR[0], this.Z[0]);
        float n2 = Math.max(this.ba[0], this.ap[0]);
        if (Math.max(this.aR[0], this.Z[0]) - Math.min(this.aR[0], this.Z[0]) > 0.5f) {
            n = Math.max(this.aR[0], this.Z[0]);
        }
        else if (Math.max(this.ba[0], this.ap[0]) - Math.min(this.ba[0], this.ap[0]) > 0.5f) {
            n2 = Math.min(this.ba[0], this.ap[0]);
        }
        final float a = this.a(n, 0.0f, 1.0f);
        final float a2 = this.a(n2, 0.0f, 1.0f);
        float n3;
        if (a > a2) {
            n3 = (a2 - a + 1.0f) * this.J;
        }
        else {
            n3 = (a2 - a) * this.J;
        }
        this.j = this.ar;
        if (n3 > this.aT * this.aC) {
            this.j = this.ar;
            while (this.j >= 3) {
                if (n3 < this.aT * this.aC) {
                    if (this.j < this.ar) {
                        ++this.j;
                        break;
                    }
                    break;
                }
                else {
                    n3 /= 2.0f;
                    if (this.j == this.aH) {
                        break;
                    }
                    this.w *= 2;
                    --this.j;
                }
            }
            this.w = this.a(this.w, 32, 64);
        }
        this.E = this.u[this.j].height / 64.0f;
        this.aw = this.u[this.j].width / 64.0f;
        if (this.w == 32) {
            this.E *= 2.0f;
            this.aw *= 2.0f;
        }
        this.a(array);
    }
    
    private synchronized boolean f() {
        int n = 0;
        if (this.B == null) {
            return false;
        }
        if (this.B.do <= 0 || this.B.new <= 0) {
            return false;
        }
        if (!this.j()) {
            return false;
        }
        this.D.if(1);
        this.D.int();
        this.a(this.q);
        if (this.J < 500.0f) {
            this.a("FlashPix panoramas must be", "greater than 500 pixels wide.");
            return false;
        }
        this.n = 1.0f / (float)Math.tan(this.q * 0.5f);
        this.G = this.n / (this.al / this.N);
        this.a(0, this.B.do - 1, this.aR);
        this.a(this.B.new / 2, this.B.do, this.aW);
        this.a(this.B.new, this.B.do, this.ba);
        this.a(this.B.new, 0, this.ap);
        this.a(this.B.new / 2, 0, this.ad);
        this.a(0, 0, this.Z);
        float max = Math.max(Math.max(this.aW[1], this.ba[1]), this.ap[1]);
        float min = Math.min(Math.min(this.ad[1], this.ap[1]), this.ba[1]);
        float n2 = Math.min(this.aR[0], this.Z[0]);
        float n3 = Math.max(this.ba[0], this.ap[0]);
        if (Math.max(this.aR[0], this.Z[0]) - Math.min(this.aR[0], this.Z[0]) > 0.5f) {
            n2 = Math.max(this.aR[0], this.Z[0]);
        }
        else if (Math.max(this.ba[0], this.ap[0]) - Math.min(this.ba[0], this.ap[0]) > 0.5f) {
            n3 = Math.min(this.ba[0], this.ap[0]);
        }
        if (n2 > n3) {
            n = 1;
        }
        if (this.aY == 0 && (this.ad[0] > this.aW[0] + 0.1f || this.ad[0] < this.aW[0] - 0.1f)) {
            if (this.W > 0.0f) {
                n3 = 1.0f;
                n2 = 0.0f;
                min = 0.0f;
            }
            else if (this.W < 0.0f) {
                n3 = 1.0f;
                n2 = 0.0f;
                max = 1.0f;
            }
        }
        final float a = this.a(max, 0.0f, 1.0f);
        final float a2 = this.a(min, 0.0f, 1.0f);
        final float a3 = this.a(n2, 0.0f, 1.0f);
        final float a4 = this.a(n3, 0.0f, 1.0f);
        this.null();
        final int n4 = (int)Math.ceil(this.E);
        final int n5 = (int)Math.ceil(this.aw);
        if (n == 1) {
            this.ab = (int)(a2 * this.E);
            --this.ab;
            this.ab = this.a(this.ab, 0, n4);
            this.a9 = (int)(a3 * this.aw);
            --this.a9;
            this.a9 = this.a(this.a9, 0, n5);
            this.L = (int)Math.ceil(a * this.E);
            ++this.L;
            this.L = this.a(this.L, 0, n4);
            this.aS = (int)Math.ceil(this.aw);
            int a5 = this.a9;
            int as = this.aS;
            this.h();
            this.a9 = 0;
            this.aS = (int)Math.ceil(a4 * this.aw);
            ++this.aS;
            this.aS = this.a(this.aS, 0, n5);
            this.h();
            if (this.aj && !this.void && !this.long) {
                if (this.w == 32) {
                    this.ab = (this.ab + 1) / 2;
                    this.a9 = (this.a9 + 1) / 2;
                    this.L = (this.L + 1) / 2;
                    this.aS = (this.aS + 1) / 2;
                    a5 = (a5 + 1) / 2;
                    as = (as + 1) / 2;
                }
                final Rectangle rectangle = new Rectangle(0, this.ab, this.Y[this.j].width, this.L - this.ab);
                this.D.a(true);
                if (this.O) {
                    rectangle.x = this.a(a5, 0, this.Y[this.j].width);
                    rectangle.width = this.a(as - a5, 0, this.Y[this.j].width - rectangle.x);
                    this.D.a(this.j, rectangle);
                    rectangle.x = 0;
                    rectangle.width = this.a(this.aS, 0, this.Y[this.j].width);
                }
                this.D.if(this.j, rectangle);
            }
        }
        else {
            this.ab = (int)(a2 * this.E);
            --this.ab;
            this.ab = this.a(this.ab, 0, n4);
            this.a9 = (int)(a3 * this.aw);
            --this.a9;
            this.a9 = this.a(this.a9, 0, n5);
            this.L = (int)Math.ceil(a * this.E);
            ++this.L;
            this.L = this.a(this.L, 0, n4);
            this.aS = (int)Math.ceil(a4 * this.aw);
            ++this.aS;
            this.aS = this.a(this.aS, 0, n5);
            this.h();
            if (this.aj && !this.void && !this.long) {
                if (this.w == 32) {
                    this.ab = (this.ab + 1) / 2;
                    this.a9 = (this.a9 + 1) / 2;
                    this.L = (this.L + 1) / 2;
                    this.aS = (this.aS + 1) / 2;
                }
                final Rectangle rectangle2 = new Rectangle(0, this.ab, this.Y[this.j].width, this.L - this.ab);
                this.D.a(true);
                if (this.O) {
                    rectangle2.x = this.a(this.a9, 0, this.Y[this.j].width);
                    rectangle2.width = this.a(this.aS - this.a9, 0, this.Y[this.j].width - rectangle2.x);
                }
                this.D.if(this.j, rectangle2);
            }
        }
        this.aj = false;
        return true;
    }
    
    private void h() {
        if (this.L - this.ab <= 0) {
            return;
        }
        if (this.aS - this.a9 <= 0) {
            return;
        }
        d[] array = this.bg;
        d[] array2 = this.aL;
        this.if(array, 0);
        this.a(array, this.aS - this.a9);
        for (int i = 0; i < this.L - this.ab; ++i) {
            this.if(array2, i + 1);
            this.a(array2, this.aS - this.a9);
            for (int j = 0; j < this.aS - this.a9; ++j) {
                if (array2[j].a[7] >= 0.01f && array2[j + 1].a[7] >= 0.01f && array[j].a[7] >= 0.01f) {
                    if (array[j + 1].a[7] >= 0.01f) {
                        if (array2[j].a[5] <= 1.0f || array2[j + 1].a[5] <= 1.0f || array[j].a[5] <= 1.0f || array[j + 1].a[5] <= 1.0f) {
                            if (array2[j].a[5] >= -1.0f || array2[j + 1].a[5] >= -1.0f || array[j].a[5] >= -1.0f || array[j + 1].a[5] >= -1.0f) {
                                if (array2[j].a[6] <= 1.0f || array2[j + 1].a[6] <= 1.0f || array[j].a[6] <= 1.0f || array[j + 1].a[6] <= 1.0f) {
                                    if (array2[j].a[6] >= -1.0f || array2[j + 1].a[6] >= -1.0f || array[j].a[6] >= -1.0f || array[j + 1].a[6] >= -1.0f) {
                                        this.bb.a[0][0] = array2[j].a[5];
                                        this.bb.a[0][1] = array2[j].a[6];
                                        this.bb.a[1][0] = array2[j + 1].a[5];
                                        this.bb.a[1][1] = array2[j + 1].a[6];
                                        this.bb.a[2][0] = array[j + 1].a[5];
                                        this.bb.a[2][1] = array[j + 1].a[6];
                                        this.bb.a[3][0] = array[j].a[5];
                                        this.bb.a[3][1] = array[j].a[6];
                                        this.Q[0] = this.j;
                                        this.Q[1] = j + this.a9;
                                        this.Q[2] = i + this.ab;
                                        if (this.w == 32) {
                                            final int[] q = this.Q;
                                            final int n = 1;
                                            q[n] /= 2;
                                            final int[] q2 = this.Q;
                                            final int n2 = 2;
                                            q2[n2] /= 2;
                                        }
                                        final int n3 = this.Q[1];
                                        final int n4 = this.Q[2];
                                        this.ai = this.D.a(this.Q, !this.void && !this.long);
                                        if (this.Q[3] == 2) {
                                            this.aj = true;
                                        }
                                        this.a(this.bb, this.Q, n3, n4);
                                        if (this.w < 64) {
                                            this.if(this.bb, this.Q, j + this.a9, i + this.ab);
                                        }
                                        this.a(this.bb);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if ((i & 0x1) == 0x0) {
                array = this.aL;
                array2 = this.bg;
            }
            else {
                array = this.bg;
                array2 = this.aL;
            }
        }
    }
    
    private void a(final d[] array, final int n) {
        for (int i = 0; i <= n; ++i) {
            array[i].a[5] = -(this.I * array[i].a[0] + this.aN * array[i].a[2]);
            array[i].a[6] = array[i].a[1];
            array[i].a[7] = this.I * array[i].a[2] - this.aN * array[i].a[0];
            final float n2 = array[i].a[6];
            final float n3 = array[i].a[7];
            array[i].a[6] = this.aE * n2 - this.T * n3;
            array[i].a[7] = this.aE * n3 + this.T * n2;
            final float n4 = array[i].a[5];
            final float n5 = array[i].a[6];
            array[i].a[5] = this.A * n4 - this.X * n5;
            array[i].a[6] = this.A * n5 + this.X * n4;
            if (array[i].a[7] > 0.01f) {
                array[i].a[5] = array[i].a[5] * this.G / array[i].a[7];
                array[i].a[6] = array[i].a[6] * this.n / array[i].a[7];
            }
        }
    }
    
    private void if(final d[] array, final int n) {
        float e = this.ab + n;
        int n2 = 0;
        if (e > this.E) {
            e = this.E;
        }
        if (this.aY == 0) {
            float n3 = 1.5707964f - (this.az + (this.aB - this.az) * e / this.E);
            if (n3 > 3.1415927f) {
                n3 = 3.1415927f;
            }
            final float n4 = (float)Math.sin(n3);
            for (int i = this.a9; i <= this.aS; ++i) {
                final float n5 = i;
                float n6;
                if (this.ao - this.aQ < 0.001f) {
                    n6 = 6.2831855f * n5 / this.aw;
                }
                else {
                    n6 = this.aQ + (this.ao - this.aQ) * n5 / this.aw;
                }
                if (n6 > 6.2831855f) {
                    n6 = 6.2831855f;
                }
                final float a = this.a(n6, -6.2831855f, 6.2831855f);
                array[n2].a[0] = -n4 * (float)Math.sin(a);
                array[n2].a[1] = (float)Math.cos(n3);
                array[n2].a[2] = n4 * (float)Math.cos(a);
                array[n2].a[3] = this.a(n5 / this.aw, 0.0f, 1.0f);
                array[n2].a[4] = this.a(e / this.E, 0.0f, 1.0f);
                array[n2].a[5] = 0.0f;
                array[n2].a[6] = 0.0f;
                array[n2].a[7] = 0.0f;
                ++n2;
            }
        }
        else {
            final float n7 = (float)Math.tan(this.aB);
            final float n8 = (float)Math.tan(this.az);
            for (int j = this.a9; j <= this.aS; ++j) {
                final float n9 = j;
                float n10;
                if (this.ao - this.aQ < 0.001f) {
                    n10 = 6.2831855f * n9 / this.aw;
                }
                else {
                    n10 = this.aQ + (this.ao - this.aQ) * n9 / this.aw;
                }
                final float a2 = this.a(n10, 0.0f, 6.2831855f);
                array[n2].a[0] = (float)(-Math.sin(a2));
                array[n2].a[1] = n8 + (n7 - n8) * e / this.E;
                array[n2].a[2] = (float)Math.cos(a2);
                array[n2].a[3] = this.a(n9 / this.aw, 0.0f, 1.0f);
                array[n2].a[4] = this.a(e / this.E, 0.0f, 1.0f);
                array[n2].a[5] = 0.0f;
                array[n2].a[6] = 0.0f;
                array[n2].a[7] = 0.0f;
                ++n2;
            }
        }
    }
    
    private void a(final c c) {
        final int n = 4;
        final float n2 = 63.0f;
        final float n3 = 63.0f;
        float n5;
        float n4 = n5 = 1000000.0f;
        float n7;
        float n6 = n7 = -1000000.0f;
        int n8 = 0;
        for (int i = 0; i < n; ++i) {
            c.a[i][0] = c.a[i][0] * this.al + this.al;
            c.a[i][1] = -c.a[i][1] * this.N + this.N;
            c.a[i][2] *= n2;
            c.a[i][3] *= n3;
            if (c.a[i][0] < n5) {
                n5 = c.a[i][0];
            }
            if (c.a[i][0] > n7) {
                n7 = c.a[i][0];
            }
            if (c.a[i][1] < n4) {
                n4 = c.a[i][1];
                n8 = i;
            }
            if (c.a[i][1] > n6) {
                n6 = c.a[i][1];
            }
        }
        if (n5 > this.aT) {
            return;
        }
        if (n4 > this.bq) {
            return;
        }
        if (n7 < 0.0f) {
            return;
        }
        if (n6 < 0.0f) {
            return;
        }
        int n10;
        int n9 = n10 = n8;
        int j = n;
        final float n11 = n4 - 0.5f;
        int n12 = (int)n11;
        if (n11 > 0.0f) {
            ++n12;
        }
        int n14;
        int n13 = n14 = n12 - 1;
        final int do1 = this.B.do;
        while (j > 0) {
            while (n14 <= n12 && j > 0) {
                --j;
                int n15 = n10 - 1;
                if (n15 < 0) {
                    n15 = n - 1;
                }
                this.a(c.a[n10], c.a[n15], this.H, this.aJ, n12);
                final float n16 = c.a[n15][1] + 0.5f;
                n14 = (int)n16;
                if (n16 < 0.0f) {
                    --n14;
                }
                n10 = n15;
            }
            while (n13 <= n12 && j > 0) {
                --j;
                int n17 = n9 + 1;
                if (n17 >= n) {
                    n17 = 0;
                }
                this.a(c.a[n9], c.a[n17], this.C, this.aG, n12);
                final float n18 = c.a[n17][1] + 0.5f;
                n13 = (int)n18;
                if (n18 < 0.0f) {
                    --n13;
                }
                n9 = n17;
            }
            while (n12 < n14 && n12 < n13 && n12 < do1) {
                if (n12 >= 0) {
                    if (this.H[0] < this.C[0]) {
                        this.a(n12, this.H, this.C);
                    }
                    else {
                        this.a(n12, this.C, this.H);
                    }
                }
                ++n12;
                final float[] h = this.H;
                final int n19 = 0;
                h[n19] += this.aJ[0];
                final float[] c2 = this.C;
                final int n20 = 0;
                c2[n20] += this.aG[0];
                final float[] h2 = this.H;
                final int n21 = 1;
                h2[n21] += this.aJ[1];
                final float[] c3 = this.C;
                final int n22 = 1;
                c3[n22] += this.aG[1];
                final float[] h3 = this.H;
                final int n23 = 2;
                h3[n23] += this.aJ[2];
                final float[] c4 = this.C;
                final int n24 = 2;
                c4[n24] += this.aG[2];
            }
            if (n12 >= do1) {
                break;
            }
        }
    }
    
    private void a(final float[] array, final float[] array2, final float[] array3, final float[] array4, final int n) {
        float n2 = array2[1] - array[1];
        if (n2 == 0.0f) {
            n2 = 1.0f;
        }
        final float n3 = n + 0.5f - array[1];
        final float n4 = 1.0f / n2;
        array4[0] = (array2[0] - array[0]) * n4;
        array3[0] = array[0] + array4[0] * n3;
        array4[1] = (array2[2] - array[2]) * n4;
        array3[1] = array[2] + array4[1] * n3;
        array4[2] = (array2[3] - array[3]) * n4;
        array3[2] = array[3] + array4[2] * n3;
    }
    
    private void a(final int n, final float[] array, final float[] array2) {
        final float n2 = array[0] - 0.5f;
        int n3 = (int)n2;
        if (n2 > 0.0f) {
            ++n3;
        }
        final float n4 = array2[0] - 0.5f;
        int n5 = (int)n4;
        if (n4 < 0.0f) {
            --n5;
        }
        if (n3 > n5) {
            return;
        }
        if (n3 < 0) {
            n3 = 0;
        }
        if (n5 >= this.B.new) {
            n5 = this.B.new - 1;
        }
        float n6 = array2[0] - array[0];
        if (n6 == 0.0f) {
            n6 = 1.0f;
        }
        final float n7 = n3 + 0.5f - array[0];
        final float n8 = 1.0f / n6;
        this.a8[1] = (array2[1] - array[1]) * n8;
        this.a8[2] = (array2[2] - array[2]) * n8;
        this.a0[1] = array[1] + this.a8[1] * n7;
        this.a0[2] = array[2] + this.a8[2] * n7;
        int n9 = (int)(this.a0[1] * 65536.0f);
        int n10 = (int)(this.a0[2] * 65536.0f);
        final int n11 = (int)(this.a8[1] * 65536.0f);
        final int n12 = (int)(this.a8[2] * 65536.0f);
        final int n13 = 1 + n5;
        final int n14 = this.B.new * n;
        int n15 = (n10 >>> 16 << 6) + (n9 >>> 16);
        final zp.a.b.a.a a = this.D.a();
        final boolean b = a != null && a.if() > 0;
        for (int n16 = n14 + n3, n17 = n3; n17 < n13 && n15 < 4096; n15 = (n10 >>> 16 << 6) + (n9 >>> 16), ++n16, ++n17) {
            if (n9 >> 16 < 63 && n10 >> 16 < 63) {
                final int n18 = n9 & 0xFFFF;
                final int[] ai = this.ai;
                this.B.a[n16] = if(if(ai[n15 + 0], ai[n15 + 1], n18), if(ai[n15 + 64], ai[n15 + 65], n18), n10 & 0xFFFF);
            }
            else {
                this.B.a[n16] = this.ai[n15];
            }
            if (b) {
                this.B.a[n16] = a.filterRGB(0, 0, this.B.a[n16]);
            }
            n9 += n11;
            n10 += n12;
        }
    }
    
    static int if(final int n, final int n2, final int n3) {
        int n4 = 0;
        switch (n3 >> 13) {
            case 0: {
                return n;
            }
            case 1:
            case 2: {
                n4 = (3 * (n >> 2 & 0x3F3F3F3F) + (n2 >> 2 & 0x3F3F3F3F) | 0xFF000000);
                break;
            }
            case 3:
            case 4: {
                n4 = ((n >> 1 & 0x7F7F7F7F) + (n2 >> 1 & 0x7F7F7F7F) | 0xFF000000);
                break;
            }
            case 5:
            case 6: {
                n4 = ((n >> 2 & 0x3F3F3F3F) + 3 * (n2 >> 2 & 0x3F3F3F3F) | 0xFF000000);
                break;
            }
            default: {
                return n2;
            }
        }
        return n4;
    }
    
    private void a(final c c, final int[] array, final int n, final int n2) {
        boolean b = false;
        boolean b2 = false;
        if (n == this.Y[this.j].width - 1) {
            b = true;
        }
        if (n2 == this.Y[this.j].height - 1) {
            b2 = true;
        }
        if (array[0] == this.j) {
            c.a[0][2] = 0.0f;
            if (b2 && (this.u[this.j].height & 0x3F) > 0) {
                c.a[0][3] = (this.u[this.j].height & 0x3F) / 64.0f;
            }
            else {
                c.a[0][3] = 1.0f;
            }
            if (b && (this.u[this.j].width & 0x3F) > 0) {
                c.a[1][2] = (this.u[this.j].width & 0x3F) / 64.0f - 0.015625f;
            }
            else {
                c.a[1][2] = 1.0f;
            }
            c.a[1][3] = c.a[0][3];
            c.a[2][2] = c.a[1][2];
            c.a[2][3] = 0.0f;
            c.a[3][2] = 0.0f;
            c.a[3][3] = 0.0f;
        }
        else if (array[0] < this.j) {
            final float n3 = this.u[this.ar].height;
            final float n4 = this.u[this.ar].width;
            final float n5 = this.u[this.j].height;
            final float n6 = this.u[this.j].width;
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
            if (b) {
                n19 = 1.0f;
            }
            if (b2) {
                n20 = 1.0f;
            }
            final float a = this.a(n17, n13, n15);
            final float a2 = this.a(n18, n14, n16);
            final float a3 = this.a(n19, a, n15);
            final float a4 = this.a(n20, a2, n16);
            c.a[3][2] = (a - n13) / (n15 - n13);
            c.a[3][3] = (a2 - n14) / (n16 - n14);
            c.a[2][2] = (a3 - n13) / (n15 - n13);
            if (b && (this.u[array[0]].width & 0x3F) > 0) {
                c.a[2][2] = ((this.u[array[0]].width & 0x3F) - 1.5f) / 64.0f;
                if (c.a[2][2] < 0.0f) {
                    c.a[2][2] = 0.0f;
                }
            }
            c.a[2][3] = c.a[3][3];
            c.a[1][3] = (a4 - n14) / (n16 - n14);
            c.a[1][2] = c.a[2][2];
            c.a[0][2] = (a - n13) / (n15 - n13);
            c.a[0][3] = c.a[1][3];
        }
    }
    
    private void if(final c c, final int[] array, final int n, final int n2) {
        if (this.w == 32) {
            final float n3 = 0.5f / (float)Math.pow(2.0, this.j - array[0]);
            if ((n & 0x1) == 0x1) {
                final float[] array2 = c.a[0];
                final int n4 = 2;
                array2[n4] += n3;
                final float[] array3 = c.a[3];
                final int n5 = 2;
                array3[n5] += n3;
            }
            else if (c.a[1][2] > c.a[0][2] + n3) {
                c.a[1][2] = c.a[0][2] + n3;
                c.a[2][2] = c.a[0][2] + n3;
            }
            if ((n2 & 0x1) == 0x1) {
                final float[] array4 = c.a[2];
                final int n6 = 3;
                array4[n6] += n3;
                final float[] array5 = c.a[3];
                final int n7 = 3;
                array5[n7] += n3;
            }
            else if (c.a[0][3] > c.a[2][3] + n3) {
                c.a[0][3] = c.a[2][3] + n3;
                c.a[1][3] = c.a[2][3] + n3;
            }
        }
    }
    
    public void if(final float[] array) {
        array[0] = this.W;
        array[1] = this.aq;
        array[2] = this.aa;
    }
    
    public float i() {
        return this.q;
    }
    
    public float c() {
        return this.aU;
    }
    
    public void a(final float au, final boolean b) {
        this.aU = au;
        if (b) {
            this.a(this.i());
        }
        this.try = true;
    }
    
    public void g() {
        final float n = -1.0f;
        final float n2 = 0.03f;
        this.O = true;
        final float i = this.i();
        this.a(i + n * (n2 * (i * 1.5f * 1.5f)));
        this.try = true;
    }
    
    public void b() {
        final float n = 1.0f;
        final float n2 = 0.03f;
        this.O = true;
        final float i = this.i();
        this.a(i + n * (n2 * (i * 1.5f * 1.5f)));
        this.try = true;
    }
    
    public void new() {
        if (!this.a2) {
            this.O = true;
            this.a2 = true;
            this.else.if(0, 3);
        }
        else {
            this.O = false;
            this.a2 = false;
            this.else.if(0, 4);
        }
        if (this.else != null) {
            this.else.new();
        }
        this.try = true;
    }
    
    public void processMouseEvent(final MouseEvent mouseEvent) {
        if (mouseEvent.isPopupTrigger()) {
            this.aI = true;
        }
        else if (this.aI) {
            this.aI = false;
        }
        else {
            switch (mouseEvent.getID()) {
                case 501: {
                    this.O = true;
                    this.void = true;
                    final int x = mouseEvent.getX();
                    this.a5 = x;
                    this.be = x;
                    this.ax = x;
                    final int y = mouseEvent.getY();
                    this.a3 = y;
                    this.bc = y;
                    this.at = y;
                    break;
                }
                case 502: {
                    this.void = false;
                    this.be = mouseEvent.getX();
                    this.bc = mouseEvent.getY();
                    this.try = true;
                    break;
                }
            }
        }
        super.processMouseEvent(mouseEvent);
    }
    
    public void processMouseMotionEvent(final MouseEvent mouseEvent) {
        switch (mouseEvent.getID()) {
            case 506: {
                this.be = mouseEvent.getX();
                this.bc = mouseEvent.getY();
                this.try = true;
                break;
            }
            case 503: {
                final int x = mouseEvent.getX();
                this.be = x;
                this.ax = x;
                final int y = mouseEvent.getY();
                this.bc = y;
                this.at = y;
                if (this.int) {
                    this.try = true;
                    break;
                }
                break;
            }
        }
        super.processMouseMotionEvent(mouseEvent);
    }
    
    public void for() {
        this.int = true;
        if (this.else != null) {
            this.else.if(1, 3);
        }
        this.try = true;
    }
    
    public void if() {
        this.int = false;
        if (this.else != null) {
            this.else.if(1, 4);
        }
        this.try = true;
    }
    
    public void a(final int n) {
        try {
            if (n < 100) {
                super.do.showDocument(new URL("http://www.vrxstudios.com"), "VRX");
            }
            if (n > 110 && n < 160) {
                super.do.showDocument(new URL(b.l), "iSee");
            }
        }
        catch (MalformedURLException ex) {}
    }
    
    public void a(final int n, final float if1, final float try1, final float byte1, final float new1, final String for1, final String do1, final String int1, final boolean a) {
        if (n >= 63 || for1 == null) {
            return;
        }
        this.bm[n] = new zp.a.a.a.a.b();
        this.bm[n].if = if1;
        this.bm[n].try = try1;
        this.bm[n].byte = byte1;
        this.bm[n].new = new1;
        this.bm[n].for = for1;
        this.bm[n].do = do1;
        this.bm[n].int = int1;
        this.bm[n].a = a;
        if (this.else != null) {
            this.else.do(1);
        }
    }
    
    static {
        b.l = "http://www.mgisoft.com/support";
    }
}
