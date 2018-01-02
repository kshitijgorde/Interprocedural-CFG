import java.awt.Component;
import java.awt.MediaTracker;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Event;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.Polygon;
import java.awt.FontMetrics;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Graphics;
import java.applet.AppletContext;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class image3dcube extends Applet implements Runnable
{
    private AppletContext A;
    private Graphics B;
    private Graphics C;
    private Thread D;
    private Image E;
    private Image F;
    private Image G;
    private String H;
    private Dimension I;
    private int J;
    private int K;
    private int L;
    private int M;
    private String[] N;
    private long O;
    private long P;
    private int[] Q;
    private int[] R;
    private int[] S;
    private int[] T;
    private int[] U;
    private int[] V;
    private int[] W;
    private int[] X;
    private int[] Y;
    private double[] Z;
    private double[] a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private double k;
    private double l;
    private double m;
    private double n;
    private double o;
    private double p;
    private double q;
    private double r;
    private double s;
    private double t;
    private double u;
    private double v;
    private double w;
    private double x;
    private double y;
    private double z;
    private double \u00c0;
    private double \u00c1;
    private double \u00c2;
    private double \u00c3;
    private double \u00c4;
    private double \u00c5;
    private double \u00c6;
    private double \u00c7;
    private double \u00c8;
    private double \u00c9;
    private double \u00ca;
    private double \u00cb;
    private double \u00cc;
    private double \u00cd;
    private double \u00ce;
    private double \u00cf;
    private double \u00d0;
    private double \u00d1;
    private double \u00d2;
    private int \u00d3;
    private int \u00d4;
    private int \u00d5;
    private int \u00d6;
    private int \u00d8;
    private int \u00d9;
    private int \u00da;
    private int \u00db;
    private int \u00dc;
    private int \u00dd;
    private int[] \u00de;
    private int[] \u00df;
    private boolean \u00e0;
    private boolean \u00e1;
    private boolean \u00e2;
    private boolean \u00e3;
    private boolean \u00e4;
    private boolean \u00e5;
    private boolean \u00e6;
    private boolean \u00e7;
    private boolean \u00e8;
    private boolean \u00e9;
    
    public void init() {
        super.init();
        this.setLayout(null);
        this.B();
        this.I = this.size();
        this.\u00d8 = ((this.I.width < this.I.height) ? this.I.width : this.I.height);
        this.\u00d9 = this.\u00d8 / 2;
        final double n = this.\u00d9;
        this.a = new double[] { -n, -n, -n, n, -n, -n, n, n, -n, -n, n, -n, -n, -n, n, n, -n, n, n, n, n, -n, n, n };
        this.J = this.I.width / 2;
        this.K = this.I.height / 2;
        this.y = 1.0;
        this.z = -1.0;
        this.S = new int[this.I.height];
        this.T = new int[this.I.height];
        this.Q = new int[this.I.width * this.I.height];
        this.W = new int[this.I.width * this.I.height];
        this.X = new int[this.I.width * this.I.height];
        this.\u00df = new int[this.I.width];
        for (int i = 0; i < this.I.width; ++i) {
            this.\u00df[i] = -16777216 + this.\u00d5;
        }
        this.V = new int[this.I.height];
        for (int j = 0; j < this.I.height; ++j) {
            this.V[j] = 0;
        }
        this.B = this.getGraphics();
        this.d = 512;
        this.e = 900;
        this.\u00dd = 6;
        this.g = 30;
        this.f = this.a.length / 3;
        this.Z = new double[this.a.length];
        this.G = this.createImage(this.I.width, this.I.height);
        (this.C = this.G.getGraphics()).setColor(new Color(this.\u00d3));
        this.C.fillRect(0, 0, this.I.width, this.I.height);
        if (this.\u00e0) {
            this.C(this.F = this.getImage(this.getDocumentBase(), "on.gif"));
            this.C.drawImage(this.F, this.I.width - 18, this.I.height - 32, this);
        }
        final PixelGrabber pixelGrabber = new PixelGrabber(this.G, 0, 0, this.I.width, this.I.height, this.W, 0, this.I.width);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {}
        if (this.\u00e0) {
            this.C(this.F = this.getImage(this.getDocumentBase(), "off.gif"));
            this.C.drawImage(this.F, this.I.width - 18, this.I.height - 32, this);
        }
        final PixelGrabber pixelGrabber2 = new PixelGrabber(this.G, 0, 0, this.I.width, this.I.height, this.X, 0, this.I.width);
        try {
            pixelGrabber2.grabPixels();
        }
        catch (InterruptedException ex2) {}
    }
    
    public void run() {
        this.E = this.createImage(this.I.width, this.I.height);
        final Graphics graphics = this.E.getGraphics();
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        this.\u00de = new int[this.I.width * this.I.height * 6];
        this.\u00e5 = true;
        for (int i = 0; i < 6; ++i) {
            graphics.setColor(new Color(this.\u00d3));
            graphics.fillRect(0, 0, this.I.width, this.I.height);
            graphics.setColor(new Color(this.\u00d4));
            final String string = "Loading Image " + Integer.toString(i + 1);
            graphics.drawString(string, this.J - fontMetrics.stringWidth(string) / 2, this.K);
            this.update(this.B);
            if (this.getParameter("image" + Integer.toString(i)) != null) {
                this.C(this.F = this.getImage(this.getDocumentBase(), this.getParameter("image" + Integer.toString(i))));
                this.F = this.I(this.F, this.\u00d8, this.\u00d8);
                System.arraycopy(this.U, 0, this.\u00de, i * this.\u00d8 * this.\u00d8, this.\u00d8 * this.\u00d8);
            }
        }
        this.\u00e5 = false;
        this.\u00d2 = 0.0;
        this.\u00d0 = 50.0;
        this.\u00d1 = 50.0;
        this.\u00e3 = false;
        this.\u00e4 = false;
        this.\u00e5 = false;
        this.\u00e6 = false;
        this.\u00e7 = false;
        this.\u00e8 = false;
        this.\u00e9 = false;
        this.M();
        System.arraycopy(this.a, 0, this.Z, 0, this.a.length);
        final double n = 0.0;
        this.v = n;
        this.r = n;
        this.n = n;
        final double k = 1.0;
        this.u = k;
        this.p = k;
        this.k = k;
        final double n2 = 0.0;
        this.t = n2;
        this.s = n2;
        this.q = n2;
        this.o = n2;
        this.m = n2;
        this.l = n2;
        final double \u00e4 = 0.0;
        this.\u00c6 = \u00e4;
        this.\u00c5 = \u00e4;
        this.\u00c4 = \u00e4;
        this.F();
        this.\u00e7 = false;
        while (true) {
            this.\u00e2 = this.\u00e1;
            final double n3 = 0.0;
            this.v = n3;
            this.r = n3;
            this.n = n3;
            if (this.\u00e8) {
                this.v = 750.0;
                final double asin = Math.asin(Math.sqrt(this.\u00ca * this.\u00ca + this.\u00cb * this.\u00cb));
                final double sqrt = Math.sqrt(this.\u00ca * this.\u00ca + this.\u00cb * this.\u00cb);
                final double n4 = (int)(asin / this.\u00c2);
                for (int n5 = 0; n5 < n4; ++n5) {
                    this.D(-this.\u00cb / sqrt, this.\u00ca / sqrt, 0.0, this.\u00c2, 1.0);
                    this.A();
                    this.\u00e5 = true;
                    this.update(this.B);
                    this.\u00e5 = false;
                }
                this.D(-this.\u00cb / sqrt, this.\u00ca / sqrt, 0.0, asin - n4 * this.\u00c2, 1.0);
                this.A();
                this.\u00e5 = true;
                this.update(this.B);
                this.\u00e5 = false;
                double acos = Math.acos(this.\u00cd);
                if (this.\u00ce > 0.0) {
                    acos = -acos;
                }
                final int max = Math.max((int)(Math.abs(acos) / this.\u00c2 / 3.0), 5);
                final double exp = Math.exp(Math.log(1.45) / max);
                for (int j = 1; j <= max; ++j) {
                    this.D(0.0, 0.0, -1.0, -acos / max, exp);
                    this.A();
                    this.\u00e5 = true;
                    this.update(this.B);
                    this.\u00e5 = false;
                }
                while (!this.\u00e4) {
                    this.\u00e5 = true;
                    try {
                        Thread.sleep(100L);
                    }
                    catch (InterruptedException ex) {}
                }
                for (int l = 1; l <= max; ++l) {
                    this.D(0.0, 0.0, -1.0, acos / max, 1.0 / exp);
                    this.A();
                    this.\u00e5 = true;
                    this.update(this.B);
                    this.\u00e5 = false;
                }
                this.v = 750.0;
                final double sqrt2 = Math.sqrt(this.\u00ca * this.\u00ca + this.\u00cb * this.\u00cb);
                for (int n6 = 0; n6 < n4; ++n6) {
                    this.D(-this.\u00cb / sqrt2, this.\u00ca / sqrt2, 0.0, -this.\u00c2, 1.0);
                    this.A();
                    this.\u00e5 = true;
                    this.update(this.B);
                    this.\u00e5 = false;
                }
                this.D(-this.\u00cb / sqrt2, this.\u00ca / sqrt2, 0.0, -asin + n4 * this.\u00c2, 1.0);
                this.A();
                this.\u00e5 = true;
                this.update(this.B);
                this.\u00e5 = false;
                final double n7 = 0.0;
                this.v = n7;
                this.r = n7;
                this.n = n7;
                this.\u00c4 = this.w;
                this.\u00c5 = this.x;
                this.\u00e8 = false;
                this.\u00e3 = false;
            }
            else if (this.\u00e6) {
                this.\u00c4 = this.w;
                this.\u00c5 = this.x;
            }
            else {
                if (this.\u00e7) {
                    this.O = System.currentTimeMillis();
                    final double n8 = 0.0;
                    this.x = n8;
                    this.w = n8;
                    this.\u00c5 = n8;
                    this.\u00c4 = n8;
                    this.\u00e7 = false;
                }
                if (System.currentTimeMillis() - this.O > 5000L) {
                    this.\u00c5 = this.\u00c3;
                    this.\u00c4 = 2.0 * this.\u00c3 / 3.0;
                }
            }
            this.E();
            this.G();
            this.\u00c9 = 750.0;
            this.J();
            this.F();
            this.A();
            this.\u00e5 = true;
            this.update(this.B);
            this.\u00e5 = false;
            try {
                Thread.sleep(this.b);
            }
            catch (InterruptedException ex2) {}
        }
    }
    
    public void A() {
        this.E.flush();
        this.\u00e9 = false;
        if (this.\u00e2) {
            System.arraycopy(this.W, 0, this.Q, 0, this.I.width * this.I.height);
        }
        else {
            System.arraycopy(this.X, 0, this.Q, 0, this.I.width * this.I.height);
        }
        int n = 0;
        final int[] array = new int[this.f];
        final int[] array2 = new int[this.f];
        int n2 = 0;
        for (int i = 0; i < this.f * 3; i += 3) {
            final int n3 = (int)this.Z[i];
            final int n4 = (int)this.Z[i + 1];
            final int n5 = (int)this.Z[i + 2];
            array[n2] = n3 * this.d / n5 + this.J;
            array2[n2] = n4 * this.d / n5 + this.K;
            ++n2;
        }
        Polygon polygon = new Polygon();
        int n6 = this.Y[0];
        for (int j = 0; j < this.g; ++j) {
            final int n7 = this.Y[j];
            if (n7 < 0) {
                polygon.addPoint(array[n6], array2[n6]);
                n6 = this.Y[j + 1];
                int n8 = 0;
                int n9 = 0;
                int n10 = 0;
                int n11 = 0;
                switch (n) {
                    case 0: {
                        n8 = 0;
                        n9 = 1;
                        n10 = 3;
                        n11 = 2;
                        break;
                    }
                    case 1: {
                        n8 = 5;
                        n9 = 4;
                        n10 = 6;
                        n11 = 7;
                        break;
                    }
                    case 2: {
                        n8 = 4;
                        n9 = 5;
                        n10 = 0;
                        n11 = 1;
                        break;
                    }
                    case 3: {
                        n8 = 3;
                        n9 = 2;
                        n10 = 7;
                        n11 = 6;
                        break;
                    }
                    case 4: {
                        n8 = 1;
                        n9 = 5;
                        n10 = 2;
                        n11 = 6;
                        break;
                    }
                    case 5: {
                        n8 = 4;
                        n9 = 0;
                        n10 = 7;
                        n11 = 3;
                        break;
                    }
                }
                final Polygon polygon2 = new Polygon();
                final double n12 = this.e - this.\u00d2;
                final double n13 = (this.Z[n9 * 3] - this.\u00d0) * n12 / (this.Z[n9 * 3 + 2] - this.\u00d2);
                final double n14 = (this.Z[n9 * 3 + 1] - this.\u00d1) * n12 / (this.Z[n9 * 3 + 2] - this.\u00d2);
                final double n15 = (this.Z[n8 * 3] - this.\u00d0) * n12 / (this.Z[n8 * 3 + 2] - this.\u00d2);
                final double n16 = (this.Z[n8 * 3 + 1] - this.\u00d1) * n12 / (this.Z[n8 * 3 + 2] - this.\u00d2);
                final double n17 = (this.Z[n10 * 3] - this.\u00d0) * n12 / (this.Z[n10 * 3 + 2] - this.\u00d2);
                final double n18 = (this.Z[n10 * 3 + 1] - this.\u00d1) * n12 / (this.Z[n10 * 3 + 2] - this.\u00d2);
                final double n19 = (this.Z[n11 * 3] - this.\u00d0) * n12 / (this.Z[n11 * 3 + 2] - this.\u00d2);
                final double n20 = (this.Z[n11 * 3 + 1] - this.\u00d1) * n12 / (this.Z[n11 * 3 + 2] - this.\u00d2);
                polygon2.addPoint((int)(n13 * this.d / n12 + this.J), (int)(n14 * this.d / n12 + this.J));
                polygon2.addPoint((int)(n15 * this.d / n12 + this.J), (int)(n16 * this.d / n12 + this.J));
                polygon2.addPoint((int)(n17 * this.d / n12 + this.J), (int)(n18 * this.d / n12 + this.J));
                polygon2.addPoint((int)(n19 * this.d / n12 + this.J), (int)(n20 * this.d / n12 + this.J));
                final int[] ypoints = polygon2.ypoints;
                final int[] array3 = new int[2];
                final int[] n21 = this.N(polygon2.xpoints, polygon2.ypoints);
                for (int n22 = n21[0], k = Math.max(ypoints[n21[1]], 0); k < Math.min(ypoints[n22], this.I.height); ++k) {
                    final int min = Math.min(this.S[k], this.T[k]);
                    System.arraycopy(this.\u00df, 0, this.Q, min + k * this.I.width, Math.max(this.S[k], this.T[k]) - min);
                }
                ++n;
                polygon = new Polygon();
            }
            else {
                polygon.addPoint(array[n7], array2[n7]);
            }
        }
        int n23 = 0;
        Polygon polygon3 = new Polygon();
        int n24 = this.Y[0];
        int n25 = 0;
        for (int l = 0; l < this.g; ++l) {
            final int n26 = this.Y[l];
            if (n26 < 0) {
                polygon3.addPoint(array[n24], array2[n24]);
                n24 = this.Y[l + 1];
                int n27 = 0;
                int n28 = 0;
                int n29 = 0;
                switch (n25) {
                    case 0: {
                        n27 = 0;
                        n28 = 1;
                        n29 = 3;
                        break;
                    }
                    case 1: {
                        n27 = 5;
                        n28 = 4;
                        n29 = 6;
                        break;
                    }
                    case 2: {
                        n27 = 4;
                        n28 = 5;
                        n29 = 0;
                        break;
                    }
                    case 3: {
                        n27 = 3;
                        n28 = 2;
                        n29 = 7;
                        break;
                    }
                    case 4: {
                        n27 = 1;
                        n28 = 5;
                        n29 = 2;
                        break;
                    }
                    case 5: {
                        n27 = 4;
                        n28 = 0;
                        n29 = 7;
                        break;
                    }
                }
                if ((array[n29] - array[n27]) * (array2[n28] - array2[n27]) - (array2[n29] - array2[n27]) * (array[n28] - array[n27]) <= 0) {
                    final double n30 = (this.Z[n28 * 3] - this.Z[n27 * 3]) / this.\u00d8;
                    final double n31 = (this.Z[n28 * 3 + 1] - this.Z[n27 * 3 + 1]) / this.\u00d8;
                    final double n32 = (this.Z[n28 * 3 + 2] - this.Z[n27 * 3 + 2]) / this.\u00d8;
                    final double n33 = (this.Z[n29 * 3] - this.Z[n27 * 3]) / this.\u00d8;
                    final double n34 = (this.Z[n29 * 3 + 1] - this.Z[n27 * 3 + 1]) / this.\u00d8;
                    final double n35 = (this.Z[n29 * 3 + 2] - this.Z[n27 * 3 + 2]) / this.\u00d8;
                    final double n36 = this.Z[n27 * 3];
                    final double n37 = this.Z[n27 * 3 + 1];
                    final double n38 = this.Z[n27 * 3 + 2];
                    final double n39 = n34 * n32 - n35 * n31;
                    final double n40 = n35 * n30 - n33 * n32;
                    final double n41 = n33 * n31 - n34 * n30;
                    final double sqrt = Math.sqrt(n39 * n39 + n40 * n40 + n41 * n41);
                    final double \u00ea = n39 / sqrt;
                    final double \u00eb = n40 / sqrt;
                    final double \u00ec = n41 / sqrt;
                    final double n42 = this.\u00da - (n36 + this.\u00d9 * n30 + this.\u00d9 * n33);
                    final double n43 = this.\u00db - (n37 + this.\u00d9 * n31 + this.\u00d9 * n34);
                    final double n44 = this.\u00dc - (n38 + this.\u00d9 * n32 + this.\u00d9 * n35);
                    int n45 = n23;
                    final Polygon polygon4 = new Polygon();
                    int n46 = this.Y[n45];
                    do {
                        polygon4.addPoint(array[n46], array2[n46]);
                        n46 = this.Y[++n45];
                    } while (n46 >= 0);
                    final double n47 = (n42 * \u00ea + n43 * \u00eb + n44 * \u00ec) / Math.sqrt(n42 * n42 + n43 * n43 + n44 * n44);
                    final int n48 = (this.\u00e2 & !this.\u00e8) ? 255 : Math.max(Math.min((int)(255.0 * n47 * 0.9 + 25.5), 255), 20);
                    final int[] array4 = new int[2];
                    final int[] n49 = this.N(polygon4.xpoints, polygon4.ypoints);
                    final int min2 = Math.min(polygon4.ypoints[n49[0]], this.I.height);
                    final int max = Math.max(polygon4.ypoints[n49[1]], 0);
                    final double n50 = this.d * (n30 * n38 - n32 * n36);
                    final double n51 = this.d * (n32 * n33 - n30 * n35);
                    final double n52 = this.d * (n32 * n37 - n31 * n38);
                    final double n53 = this.d * (n31 * n35 - n32 * n34);
                    final double n54 = this.d * this.d * (n31 * n36 - n30 * n37);
                    final double n55 = this.d * this.d * (n30 * n34 - n31 * n33);
                    final double n56 = this.d * (n33 * n38 - n35 * n36);
                    final double n57 = this.d * (n35 * n30 - n33 * n32);
                    final double n58 = this.d * (n35 * n37 - n34 * n38);
                    final double n59 = this.d * (n34 * n32 - n35 * n31);
                    final double n60 = this.d * this.d * (n34 * n36 - n33 * n37);
                    final double n61 = this.d * this.d * (n33 * n31 - n34 * n30);
                    final int width = this.I.width;
                    final int n62 = n25 * this.\u00d8 * this.\u00d8;
                    if (!this.\u00e2 || this.\u00e8) {
                        for (int n63 = max; n63 < min2; ++n63) {
                            final double n64 = (n63 - this.K) * n50 + n54;
                            final double n65 = (n63 - this.K) * n51 + n55;
                            final double n66 = (n63 - this.K) * n56 + n60;
                            final double n67 = (n63 - this.K) * n57 + n61;
                            final int min3 = Math.min(this.S[n63], this.T[n63]);
                            final int max2 = Math.max(this.S[n63], this.T[n63]);
                            double n68 = (min3 - this.J) * n58 + n66;
                            double n69 = (min3 - this.J) * n59 + n67;
                            double n70 = (min3 - this.J) * n52 + n64;
                            double n71 = (min3 - this.J) * n53 + n65;
                            final int n72 = n63 * width;
                            for (int n73 = min3; n73 <= max2; ++n73) {
                                if (n73 >= 0 && n73 < width) {
                                    final double n74 = n68 / n69;
                                    final double n75 = n70 / n71;
                                    final int n76 = (int)n74;
                                    final int n77 = (int)n75;
                                    final int n78 = this.\u00de[n62 + ((n76 < 0) ? 0 : ((n76 > this.\u00d8 - 1) ? (this.\u00d8 - 1) : n76)) + this.\u00d8 * ((n77 < 0) ? 0 : ((n77 > this.\u00d8 - 1) ? (this.\u00d8 - 1) : n77))];
                                    this.Q[n73 + n72] = -16777216 + ((((n78 & 0xFF0000) >> 16) * n48 & 0xFF00) << 8) + (((n78 & 0xFF00) >> 8) * n48 & 0xFF00) + (((n78 & 0xFF) * n48 & 0xFF00) >> 8);
                                }
                                n68 += n58;
                                n69 += n59;
                                n70 += n52;
                                n71 += n53;
                            }
                        }
                    }
                    else {
                        final double sqrt2 = Math.sqrt((0.0 - this.\u00d0) * (0.0 - this.\u00d0) + (0.0 - this.\u00d1) * (0.0 - this.\u00d1) + (750.0 - this.\u00d2) * (750.0 - this.\u00d2));
                        final double n79 = -this.\u00d0 / sqrt2;
                        final double n80 = -this.\u00d1 / sqrt2;
                        final double n81 = (750.0 - this.\u00d2) / sqrt2;
                        for (int n82 = max; n82 < min2; ++n82) {
                            final int min4 = Math.min(this.S[n82], this.T[n82]);
                            final int max3 = Math.max(this.S[n82], this.T[n82]);
                            final double n83 = (n82 - this.K) * n50 + n54;
                            final double n84 = (n82 - this.K) * n51 + n55;
                            final double n85 = (n82 - this.K) * n56 + n60;
                            final double n86 = (n82 - this.K) * n57 + n61;
                            double n87 = (min4 - this.J) * n58 + n85;
                            double n88 = (min4 - this.J) * n59 + n86;
                            double n89 = (min4 - this.J) * n52 + n83;
                            double n90 = (min4 - this.J) * n53 + n84;
                            final int n91 = n82 * width;
                            for (int n92 = min4; n92 <= max3; ++n92) {
                                if (n92 >= 0 && n92 < width) {
                                    final double n93 = n87 / n88;
                                    final double n94 = n89 / n90;
                                    final double n95 = n36 + n30 * n93 + n33 * n94 - this.\u00d0;
                                    final double n96 = n37 + n31 * n93 + n34 * n94 - this.\u00d1;
                                    final double n97 = n38 + n32 * n93 + n35 * n94 - this.\u00d2;
                                    final double sqrt3 = Math.sqrt(n95 * n95 + n96 * n96 + n97 * n97);
                                    final double n98 = n95 / sqrt3;
                                    final double n99 = n96 / sqrt3;
                                    final double n100 = n97 / sqrt3;
                                    final double n101 = n79 * n98 + n80 * n99 + n81 * n100;
                                    final double n102 = -(\u00ea * n98 + \u00eb * n99 + \u00ec * n100);
                                    final double max4 = Math.max(35.0 * (n101 - 0.96), 0.0);
                                    final int n103 = (int)(n48 * (n102 * (max4 * max4 + 1.0)) / 2.0) - 255;
                                    final int n104 = (int)(255.0 * (50.0 * Math.max(n102 - 0.96, 0.025)));
                                    final int n105 = (int)n93;
                                    final int n106 = (int)n94;
                                    final int n107 = this.\u00de[n62 + ((n105 < 0) ? 0 : ((n105 > this.\u00d8 - 1) ? (this.\u00d8 - 1) : n105)) + this.\u00d8 * ((n106 < 0) ? 0 : ((n106 > this.\u00d8 - 1) ? (this.\u00d8 - 1) : n106))];
                                    final int n108 = n107 & 0xFF;
                                    final int n109 = (n107 & 0xFF00) >> 8;
                                    final int n110 = (n107 & 0xFF0000) >> 16;
                                    final int n111 = n108 * n104;
                                    final int n112 = n109 * n104;
                                    final int n113 = n110 * n104;
                                    final int n114 = n111 >> 8;
                                    final int n115 = n112 >> 8;
                                    final int n116 = n113 >> 8;
                                    final int n117 = n114 + n103;
                                    final int n118 = n115 + n103;
                                    final int n119 = n116 + n103;
                                    final int n120 = (n117 > 255) ? 255 : n117;
                                    final int n121 = (n118 > 255) ? 255 : n118;
                                    final int n122 = (n119 > 255) ? 255 : n119;
                                    this.Q[n92 + n91] = -16777216 + (((n122 < 0) ? 0 : n122) << 16) + (((n121 < 0) ? 0 : n121) << 8) + ((n120 < 0) ? 0 : n120);
                                }
                                n87 += n58;
                                n88 += n59;
                                n89 += n52;
                                n90 += n53;
                            }
                        }
                    }
                    if (polygon4.inside(this.L, this.M)) {
                        this.\u00d6 = n25;
                        this.\u00e9 = true;
                        if (this.\u00e4 && !this.\u00e8) {
                            this.\u00ca = \u00ea;
                            this.\u00cb = \u00eb;
                            this.\u00cc = \u00ec;
                            final double sqrt4 = Math.sqrt(n30 * n30 + n31 * n31 + n32 * n32);
                            this.\u00cd = n30 / sqrt4;
                            this.\u00ce = n31 / sqrt4;
                            this.\u00cf = n32 / sqrt4;
                            this.\u00d6 = n25;
                            this.\u00e8 = true;
                            this.\u00e4 = false;
                        }
                        else {
                            this.\u00e4 = false;
                        }
                    }
                }
                ++n25;
                polygon3 = new Polygon();
                n23 = l + 1;
            }
            else {
                polygon3.addPoint(array[n26], array2[n26]);
            }
        }
        this.C(this.E = this.createImage(new MemoryImageSource(this.I.width, this.I.height, this.Q, 0, this.I.width)));
        if (!this.\u00e9) {
            this.\u00d6 = 7;
        }
    }
    
    public int[] N(final int[] array, final int[] array2) {
        System.arraycopy(this.V, 0, this.S, 0, this.I.height);
        System.arraycopy(this.V, 0, this.T, 0, this.I.height);
        int n = -16777215;
        int n2 = 16777215;
        int n3 = 5;
        int n4 = 5;
        for (int i = 0; i < 4; ++i) {
            if (array2[i] < n2) {
                n2 = array2[i];
                n4 = i;
            }
            if (array2[i] > n) {
                n = array2[i];
                n3 = i;
            }
        }
        final int n5 = (n4 + 1) % 4;
        final int n6 = (4 + n4 - 1) % 4;
        if (array[n5] < array[n6]) {
            this.K(array[n5], array2[n5], array[n4], array2[n4], true);
            this.K(array[n6], array2[n6], array[n4], array2[n4], false);
            if (n5 == n3) {
                final int n7 = (4 + n4 - 2) % 4;
                this.K(array[n6], array2[n6], array[n7], array2[n7], false);
                this.K(array[n7], array2[n7], array[n3], array2[n3], false);
            }
            else if (n6 == n3) {
                final int n8 = (n4 + 2) % 4;
                this.K(array[n5], array2[n5], array[n8], array2[n8], true);
                this.K(array[n8], array2[n8], array[n3], array2[n3], true);
            }
            else {
                this.K(array[n5], array2[n5], array[n3], array2[n3], true);
                this.K(array[n6], array2[n6], array[n3], array2[n3], false);
            }
        }
        else {
            this.K(array[n5], array2[n5], array[n4], array2[n4], false);
            this.K(array[n6], array2[n6], array[n4], array2[n4], true);
            if (n5 == n3) {
                final int n9 = (4 + n4 - 2) % 4;
                this.K(array[n6], array2[n6], array[n9], array2[n9], true);
                this.K(array[n9], array2[n9], array[n3], array2[n3], true);
            }
            else if (n6 == n3) {
                final int n10 = (n4 + 2) % 4;
                this.K(array[n5], array2[n5], array[n10], array2[n10], false);
                this.K(array[n10], array2[n10], array[n3], array2[n3], false);
            }
            else {
                this.K(array[n5], array2[n5], array[n3], array2[n3], false);
                this.K(array[n6], array2[n6], array[n3], array2[n3], true);
            }
        }
        return new int[] { n3, n4 };
    }
    
    public void K(int n, int n2, int n3, int n4, final boolean b) {
        final int n5 = n3 - n;
        final int n6 = n4 - n2;
        if (Math.abs(n5) > Math.abs(n6)) {
            if (n5 < 0) {
                final int n7 = n;
                n = n3;
                n3 = n7;
                final int n8 = n2;
                n2 = n4;
                n4 = n8;
            }
            int n9;
            if (n4 > n2) {
                n9 = 1;
            }
            else {
                n9 = -1;
            }
            final int n10 = n3 - n;
            final int abs = Math.abs(n4 - n2);
            int i = n;
            int n11 = n2;
            int n12 = -(n10 / 2);
            if (i >= 0 && n11 >= 0 && n11 < this.I.height) {
                if (b) {
                    this.S[n11] = Math.min(i, this.I.width - 1);
                }
                else {
                    this.T[n11] = Math.min(i, this.I.width - 1);
                }
            }
            while (i < n3) {
                boolean b2 = false;
                n12 += abs;
                if (n12 >= 0) {
                    n11 += n9;
                    n12 -= n10;
                    b2 = true;
                }
                if (++i >= 0 && n11 >= 0 && n11 < this.I.height && b2) {
                    if (b) {
                        this.S[n11] = Math.min(i, this.I.width - 1);
                    }
                    else {
                        this.T[n11] = Math.min(i, this.I.width - 1);
                    }
                }
            }
            return;
        }
        if (n6 < 0) {
            final int n13 = n;
            n = n3;
            n3 = n13;
            final int n14 = n2;
            n2 = n4;
            n4 = n14;
        }
        int n15;
        if (n3 > n) {
            n15 = 1;
        }
        else {
            n15 = -1;
        }
        final int abs2 = Math.abs(n3 - n);
        final int n16 = n4 - n2;
        int n17 = n;
        int j = n2;
        int n18 = -(n16 / 2);
        if (n17 >= 0 && j >= 0 && j < this.I.height) {
            if (b) {
                this.S[j] = Math.min(n17, this.I.width - 1);
            }
            else {
                this.T[j] = Math.min(n17, this.I.width - 1);
            }
        }
        while (j < n4) {
            n18 += abs2;
            if (n18 >= 0) {
                n17 += n15;
                n18 -= n16;
            }
            ++j;
            if (n17 >= 0 && j >= 0 && j < this.I.height) {
                if (b) {
                    this.S[j] = Math.min(n17, this.I.width - 1);
                }
                else {
                    this.T[j] = Math.min(n17, this.I.width - 1);
                }
            }
        }
    }
    
    public void M() {
        if (this.f > 0) {
            double n = 0.0;
            for (int i = 0; i < this.f * 3; i += 3) {
                final double n2 = this.a[i];
                final double n3 = this.a[i + 1];
                final double n4 = this.a[i + 2];
                final double n5 = Math.sqrt(n2 * n2 + n3 * n3 + n4 * n4) * Math.sqrt(2.0);
                if (n5 > n) {
                    n = n5;
                }
            }
            final double n6 = ((this.I.width > this.I.height) ? this.I.height : this.I.width) / n;
            for (int j = 0; j < this.f * 3; j += 3) {
                final double[] a = this.a;
                final int n7 = j;
                a[n7] *= n6;
                final double[] a2 = this.a;
                final int n8 = j + 1;
                a2[n8] *= n6;
                final double[] a3 = this.a;
                final int n9 = j + 2;
                a3[n9] *= n6;
            }
        }
    }
    
    public void B() {
        this.b = this.L("sleeptime", 10);
        this.\u00d5 = this.L("shadowcolor", 16);
        this.\u00d4 = this.L("textcolor", 16);
        this.\u00d3 = this.L("background", 16);
        this.N = new String[6];
        for (int i = 0; i < 6; ++i) {
            this.N[i] = this.getParameter("url" + Integer.toString(i));
            if (this.N[i] == null) {
                this.N[i] = "";
            }
        }
        if (this.getParameter("showlightbutton") != null) {
            this.\u00e0 = this.getParameter("showlightbutton").substring(0, 1).equalsIgnoreCase("y");
        }
        this.H = this.getParameter("target");
        this.\u00c3 = this.L("anglestep", 10) * 3.141592653589793 / 180.0;
        this.\u00c1 = this.L("mouseresponse", 10) * 3.141592653589793 / 180.0;
        this.\u00c2 = this.L("zoomspeed", 10) * 3.141592653589793 / 180.0;
        this.\u00e1 = this.getParameter("spotlight").substring(0, 1).equalsIgnoreCase("y");
    }
    
    public void F() {
        int i = this.f * 3;
        Label_0143: {
            break Label_0143;
            do {
                final double n = this.a[i];
                final double n2 = this.a[i + 1];
                final double n3 = this.a[i + 2];
                this.Z[i] = n * this.k + n2 * this.l + n3 * this.m + this.n;
                this.Z[i + 1] = n * this.o + n2 * this.p + n3 * this.q + this.r;
                this.Z[i + 2] = n * this.s + n2 * this.t + n3 * this.u + this.v;
                i -= 3;
            } while (i >= 0);
        }
    }
    
    public void J() {
        this.n += this.\u00c7;
        this.r += this.\u00c8;
        this.v += this.\u00c9;
    }
    
    public void D(final double n, final double n2, final double n3, final double n4, final double n5) {
        final double cos = Math.cos(n4);
        final double sin = Math.sin(n4);
        int i = this.f * 3;
        Label_0247: {
            break Label_0247;
            do {
                final double n6 = this.Z[i];
                final double n7 = this.Z[i + 1];
                final double n8 = this.Z[i + 2] - this.v;
                final double n9 = n * n6 + n2 * n7 + n3 * n8;
                this.Z[i] = n9 * n + cos * (n6 - n9 * n) + sin * (n2 * n8 - n3 * n7);
                this.Z[i + 1] = n9 * n2 + cos * (n7 - n9 * n2) + sin * (n3 * n6 - n * n8);
                this.Z[i + 2] = n9 * n3 + cos * (n8 - n9 * n3) + sin * (n * n7 - n2 * n6);
                final double[] z = this.Z;
                final int n10 = i;
                z[n10] *= n5;
                final double[] z2 = this.Z;
                final int n11 = i + 1;
                z2[n11] *= n5;
                final double[] z3 = this.Z;
                final int n12 = i + 2;
                z3[n12] *= n5;
                final double[] z4 = this.Z;
                final int n13 = i + 2;
                z4[n13] += this.v;
                i -= 3;
            } while (i >= 0);
        }
        final double \u00ed = this.\u00cd;
        final double \u00ee = this.\u00ce;
        final double \u00ef = this.\u00cf;
        final double n14 = n * \u00ed + n2 * \u00ee + n3 * \u00ef;
        this.\u00cd = n14 * n + cos * (\u00ed - n14 * n) + sin * (n2 * \u00ef - n3 * \u00ee);
        this.\u00ce = n14 * n2 + cos * (\u00ee - n14 * n2) + sin * (n3 * \u00ed - n * \u00ef);
        this.\u00cf = n14 * n3 + cos * (\u00ef - n14 * n3) + sin * (n * \u00ee - n2 * \u00ed);
    }
    
    public void E() {
        final double cos = Math.cos(this.\u00c4);
        final double sin = Math.sin(this.\u00c4);
        final double o = this.o * cos + this.s * sin;
        final double p = this.p * cos + this.t * sin;
        final double q = this.q * cos + this.u * sin;
        final double s = this.s * cos - this.o * sin;
        final double t = this.t * cos - this.p * sin;
        final double u = this.u * cos - this.q * sin;
        this.o = o;
        this.p = p;
        this.q = q;
        this.s = s;
        this.t = t;
        this.u = u;
    }
    
    public void G() {
        final double cos = Math.cos(this.\u00c5);
        final double sin = Math.sin(this.\u00c5);
        final double k = this.k * cos + this.s * sin;
        final double l = this.l * cos + this.t * sin;
        final double m = this.m * cos + this.u * sin;
        final double s = this.s * cos - this.k * sin;
        final double t = this.t * cos - this.l * sin;
        final double u = this.u * cos - this.m * sin;
        this.k = k;
        this.l = l;
        this.m = m;
        this.s = s;
        this.t = t;
        this.u = u;
    }
    
    public void H() {
        final double cos = Math.cos(this.\u00c6);
        final double sin = Math.sin(this.\u00c6);
        final double o = this.o * cos + this.k * sin;
        final double p = this.p * cos + this.l * sin;
        final double q = this.q * cos + this.m * sin;
        final double k = this.k * cos - this.o * sin;
        final double l = this.l * cos - this.p * sin;
        final double m = this.m * cos - this.q * sin;
        this.o = o;
        this.p = p;
        this.q = q;
        this.k = k;
        this.l = l;
        this.m = m;
    }
    
    public Image I(final Image image, final int n, final int n2) {
        final int width = image.getWidth(this);
        final int height = image.getHeight(this);
        final int[] array = new int[width * height];
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, width, height, array, 0, width);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {}
        this.U = new int[n * n2];
        final int n3 = 65536 * height / n2;
        final int n4 = 65536 * width / n;
        int n5 = 0;
        int n6 = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n2; ++j) {
                this.U[i + j * n] = array[(n5 >> 16) + (n6 >> 16) * width];
                n6 += n3;
            }
            n5 += n4;
            n6 = 0;
        }
        return this.createImage(new MemoryImageSource(n, n2, this.U, 0, n));
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.\u00e0 && this.I.height - n2 < 32 && this.I.width - n < 18) {
            this.\u00e1 = !this.\u00e1;
        }
        else if (this.\u00d6 != 7) {
            if (System.currentTimeMillis() - this.P < 500L) {
                this.\u00e3 = true;
                if (this.N[this.\u00d6] != null) {
                    URL url = null;
                    try {
                        url = new URL(this.N[this.\u00d6]);
                    }
                    catch (MalformedURLException ex) {
                        System.out.println("Invalid URL");
                        this.showStatus("Invalid URL");
                    }
                    this.getAppletContext().showDocument(url, this.H);
                }
            }
            else {
                this.\u00e4 = true;
                this.P = System.currentTimeMillis();
            }
        }
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        return this.\u00e6 = true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.\u00e7 = true;
        this.\u00e6 = false;
        return true;
    }
    
    public boolean mouseMove(final Event event, final int l, final int m) {
        this.\u00e6 = true;
        this.L = l;
        this.M = m;
        if (this.\u00e0) {
            if (this.I.width - l >= 18 || this.I.height - m >= 32) {
                this.w = (this.I.height / 2 - m) * this.\u00c1 / this.I.width * 2.0;
                this.x = (this.I.width / 2 - l) * this.\u00c1 / this.I.height * 2.0;
            }
        }
        else {
            this.w = (this.I.height / 2 - m) * this.\u00c1 / this.I.width * 2.0;
            this.x = (this.I.width / 2 - l) * this.\u00c1 / this.I.height * 2.0;
        }
        return true;
    }
    
    void C(final Image image) {
        final MediaTracker mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(image, 0);
        try {
            mediaTracker.waitForID(0);
        }
        catch (InterruptedException ex) {}
    }
    
    public synchronized void paint(final Graphics graphics) {
        if (this.E != null && this.\u00e5) {
            graphics.drawImage(this.E, 0, 0, this);
        }
    }
    
    public synchronized void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public int L(final String s, final int n) {
        try {
            return Integer.parseInt(this.getParameter(s), n);
        }
        catch (NumberFormatException ex) {
            return 0;
        }
    }
    
    public void start() {
        if (this.D == null) {
            (this.D = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.D != null && this.D.isAlive()) {
            this.D.stop();
        }
        this.D = null;
    }
    
    public image3dcube() {
        this.Y = new int[] { 3, 2, 1, 0, -1, 4, 5, 6, 7, -1, 0, 1, 5, 4, -1, 2, 3, 7, 6, -1, 1, 2, 6, 5, -1, 0, 4, 7, 3, -1, -1 };
        this.c = 6;
        this.\u00e0 = false;
        this.\u00e1 = false;
        this.\u00e2 = false;
        this.\u00e3 = false;
        this.\u00e4 = false;
        this.\u00e5 = false;
        this.\u00e6 = false;
        this.\u00e7 = false;
        this.\u00e8 = false;
        this.\u00e9 = false;
    }
}
