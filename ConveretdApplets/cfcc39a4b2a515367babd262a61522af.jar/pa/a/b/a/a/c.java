// 
// Decompiled by Procyon v0.5.30
// 

package pa.a.b.a.a;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Cursor;
import java.awt.FontMetrics;
import java.awt.event.KeyEvent;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import pa.a.b.a.g;
import java.awt.Graphics;
import java.awt.image.PixelGrabber;
import java.awt.Component;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import java.awt.Toolkit;
import java.awt.LayoutManager;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.PropertyResourceBundle;
import java.awt.Dimension;
import java.util.ResourceBundle;
import java.awt.Frame;
import pa.a.b.a.e;
import java.awt.Color;
import java.awt.Rectangle;
import pa.a.c.b;
import java.awt.MediaTracker;
import java.awt.Image;
import java.applet.AppletContext;
import java.net.URL;
import pa.a.a.b.a;
import java.awt.event.KeyListener;
import pa.a.b.a.d;

public class c extends d implements KeyListener
{
    private static final String k = "panoapplet.properties";
    private static final String aL = "images/splashscreen.gif";
    public static final int C = 0;
    public static final int aA = 1;
    public static final int c = 2;
    public static final int for = 64;
    public static final int g = 200;
    public static final int u = 0;
    public static final int A = 1;
    public static final int ao = 2;
    public static final float L = 10.0f;
    private static final float H = 1.5707964f;
    private static final float aq = 3.1415927f;
    private static final float V = 6.2831855f;
    private static final int aG = 63;
    private static final float an = 1.0E-4f;
    private static final int aJ = 0;
    private static final int Z = 1;
    private static final int aj = 2;
    private static final int d = 3;
    private static final int p = 4;
    private pa.a.a.b.a ab;
    private URL void;
    private String M;
    private String h;
    private String try;
    private String U;
    private AppletContext n;
    private boolean D;
    private String v;
    private Image b;
    private boolean K;
    private MediaTracker goto;
    private int ap;
    private float ak;
    private float m;
    private float j;
    private float al;
    private float int;
    private float E;
    private float ax;
    private float char;
    private float am;
    private int av;
    private int ae;
    private int[] q;
    private float l;
    private float O;
    private float aw;
    private float z;
    private Image aM;
    private Image r;
    private Image aI;
    private Image case;
    private Image ay;
    private Image au;
    private b t;
    private Rectangle aK;
    private boolean f;
    private Color G;
    private boolean as;
    private boolean aF;
    public boolean ar;
    private float e;
    private e[] o;
    private boolean ag;
    private boolean i;
    private int J;
    private int I;
    private int T;
    private int S;
    private int af;
    private int ad;
    private int W;
    private Frame at;
    private float N;
    private float ac;
    private float ah;
    private float F;
    private float R;
    private float new;
    private float P;
    private float s;
    private float long;
    private float byte;
    private float[] X;
    private float[] Q;
    private float[] B;
    private float[] w;
    private float[] null;
    private float[] aa;
    private int aB;
    private int az;
    private float[][][] aE;
    private pa.a.b.a.a.b aH;
    private int Y;
    private ResourceBundle aD;
    private URL else;
    private float aC;
    private boolean ai;
    
    public c(final URL void1, final AppletContext n, final String s, final int n2, final int n3, final int n4, final int n5, final boolean f, final int n6, final int n7, final int n8, final Dimension dimension, final Frame at, final String s2, final String s3, final String try1, final String u, final pa.a.a.b.a ab) {
        this.try = null;
        this.U = null;
        this.D = true;
        this.v = null;
        this.b = null;
        this.av = 0;
        this.ae = 0;
        this.aM = null;
        this.r = null;
        this.aI = null;
        this.case = null;
        this.ay = null;
        this.au = null;
        this.t = null;
        this.aK = null;
        this.f = false;
        this.as = false;
        this.aF = true;
        this.e = 20.0f;
        this.at = null;
        this.Y = -1;
        this.ai = false;
        try {
            this.aD = new PropertyResourceBundle(this.getClass().getResourceAsStream("panoapplet.properties"));
            this.else = new URL(this.aD.getString("url.info"));
        }
        catch (MalformedURLException ex) {}
        catch (IOException ex2) {}
        this.ab = ab;
        this.void = void1;
        this.n = n;
        this.setBackground(this.G = new Color(n6, n7, n8));
        this.setForeground(this.G);
        this.try = try1;
        this.U = u;
        this.setLayout(null);
        this.o = new e[64];
        this.at = at;
        pa.a.b.a.b.a();
        this.a(2);
        this.reshape(0, 0, dimension.width, dimension.height);
        if (s != null) {
            this.f = f;
            try {
                this.aM = Toolkit.getDefaultToolkit().getImage(new URL(this.void, s));
            }
            catch (MalformedURLException ex3) {
                this.aM = null;
                this.f = false;
            }
            if (this.aM != null) {
                Toolkit.getDefaultToolkit().prepareImage(this.aM, dimension.width, dimension.height, this);
                this.aK = new Rectangle(n2, n3, n4, n5);
            }
            else {
                this.f = false;
            }
        }
        else {
            this.f = false;
        }
        this.ag = false;
        final int n9 = -1000;
        this.I = n9;
        this.J = n9;
        this.ad = n9;
        this.af = n9;
        this.S = n9;
        this.T = n9;
        this.void();
        this.X = new float[3];
        this.Q = new float[3];
        this.B = new float[3];
        this.w = new float[3];
        this.null = new float[3];
        this.aa = new float[3];
        this.aH = new pa.a.b.a.a.b();
        this.N = 0.0f;
        this.F = 3.1415927f;
        this.R = 0.0f;
        this.new = 0.87266463f;
        this.m = -1.5707964f;
        this.j = 1.5707964f;
        this.al = 0.0f;
        this.int = 0.0f;
        this.ak = 0.17453294f;
        final a a = new a();
        this.addMouseListener(a);
        this.addMouseMotionListener(a);
        this.addKeyListener(this);
    }
    
    public void a(final int n, final float byte1, final float new1, final float a, final float if1, final String do1, final String try1, final String int1, final boolean for1) {
        if (n >= 63 || do1 == null) {
            return;
        }
        this.o[n] = new e();
        this.o[n].byte = byte1;
        this.o[n].new = new1;
        this.o[n].a = a;
        this.o[n].if = if1;
        this.o[n].do = do1;
        this.o[n].try = try1;
        this.o[n].int = int1;
        this.o[n].for = for1;
    }
    
    private void a(float n, final float n2, final int[] array) {
        final float n3 = this.new * 0.5f;
        final float n4 = n3 * this.P / this.s;
        n -= this.F;
        final float n5 = (float)Math.tan(n3);
        final float n6 = (float)Math.tan(n2);
        final float n7 = (float)Math.cos(n);
        final float n8 = (n6 * this.ac - this.ah * n7) / ((n7 * this.ac + n6 * this.ah) * n5);
        array[0] = (int)(this.long * ((float)Math.tan(n) * (this.ac - n5 * n8 * this.ah) / (float)Math.tan(n4) / 2.0f + 0.5f));
        array[1] = (int)(this.byte * (0.5f - n8 / 2.0f));
    }
    
    private void if(final float n, final float n2, final float[] array) {
        if (this.ap == 0) {
            array[0] = n;
            array[1] = n2;
        }
        else if (this.ap == 1) {
            array[0] = n;
            final float n3 = (float)Math.tan(this.j);
            if (n2 > this.j) {
                array[1] = n3 + 1.0f - n3 * (float)Math.tan(1.5707964f - n2);
            }
            else if (n2 < -this.j) {
                array[1] = -n3 - (1.0f - n3 * (float)Math.tan(1.5707964f + n2));
            }
            else {
                array[1] = (float)Math.tan(n2);
            }
        }
        else if (this.ap == 2) {
            final float n4 = (float)Math.sin(n2);
            final float n5 = (float)Math.cos(n2);
            final float n6 = -n5 * (float)Math.sin(n);
            final float n7 = n5 * (float)Math.cos(n);
            float n8 = n6;
            int n9;
            if (n8 < 0.0f) {
                n9 = 3;
            }
            else {
                n9 = 1;
            }
            if (Math.abs(n4) > Math.abs(n8)) {
                n8 = n4;
                if (n8 < 0.0f) {
                    n9 = 5;
                }
                else {
                    n9 = 4;
                }
            }
            if (Math.abs(n7) > Math.abs(n8)) {
                n8 = n7;
                if (n8 < 0.0f) {
                    n9 = 0;
                }
                else {
                    n9 = 2;
                }
            }
            final float n10 = 1.0f / Math.abs(n8);
            final float n11 = n6 * n10;
            final float n12 = n4 * n10;
            final float n13 = n7 * n10;
            switch (n9) {
                case 0: {
                    array[0] = (n11 + 1.0f) * 0.5f;
                    array[1] = (-n12 + 1.0f) * 0.5f * 0.167f;
                    break;
                }
                case 1: {
                    array[0] = (n13 + 1.0f) * 0.5f;
                    array[1] = (-n12 + 1.0f) * 0.5f * 0.167f + 0.167f;
                    break;
                }
                case 2: {
                    array[0] = (-n11 + 1.0f) * 0.5f;
                    array[1] = (-n12 + 1.0f) * 0.5f * 0.167f + 0.333f;
                    break;
                }
                case 3: {
                    array[0] = (-n13 + 1.0f) * 0.5f;
                    array[1] = (-n12 + 1.0f) * 0.5f * 0.167f + 0.5f;
                    break;
                }
                case 4: {
                    array[0] = (n11 + 1.0f) * 0.5f;
                    array[1] = (-n13 + 1.0f) * 0.5f * 0.167f + 0.667f;
                    break;
                }
                case 5: {
                    array[0] = (n11 + 1.0f) * 0.5f;
                    array[1] = (n13 + 1.0f) * 0.5f * 0.167f + 0.833f;
                    break;
                }
            }
        }
    }
    
    public void d() {
        final float n = -1.0f;
        final float n2 = 0.03f;
        this.try();
        final float c = this.c();
        this.a(c + n * (n2 * (c * 1.5f * 1.5f)));
        this.ar = true;
    }
    
    public void else() {
        final float n = 1.0f;
        final float n2 = 0.03f;
        this.try();
        final float c = this.c();
        this.a(c + n * (n2 * (c * 1.5f * 1.5f)));
        this.ar = true;
    }
    
    private void a(final int n, final int n2, final float[] array) {
        final float n3 = n / this.long;
        final float n4 = n2 / this.byte;
        final float n5 = this.new * 0.5f;
        final float n6 = n5 * this.P / this.s;
        float n7 = (n3 - 0.5f) * 2.0f;
        if (n7 < 0.005 && n7 > -0.005) {
            n7 = 0.0f;
        }
        float n8 = (0.5f - n4) * 2.0f;
        if (n8 < 0.005 && n8 > -0.005) {
            n8 = 0.0f;
        }
        final float ac = this.ac;
        final float ah = this.ah;
        final float n9 = (float)Math.tan(n5);
        final float n10 = ac - n9 * n8 * ah;
        final float n11 = ah + n9 * n8 * ac;
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
        float n16 = n13 + this.F;
        if (n15 <= this.m) {
            n15 = -n15 - 3.1415927f;
        }
        else if (n15 > this.j) {
            n15 = 3.1415927f - n15;
        }
        if (this.int - this.al < 0.001f) {
            if (n16 < 0.0f) {
                n16 += 6.2831855f;
            }
            else if (n16 > 6.2831855f) {
                n16 -= 6.2831855f;
            }
        }
        final float[] array2 = new float[2];
        this.if(n16, n15, array2);
        final float[] array3 = new float[2];
        final float[] array4 = new float[2];
        if (this.int - this.al < 0.001f) {
            this.if(0.0f, this.m, array3);
            this.if(6.2831855f, this.j, array4);
        }
        else {
            this.if(this.al, this.m, array3);
            this.if(this.int, this.j, array4);
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
    
    private void a(final pa.a.b.a.a.b b) {
        final int a = b.a;
        float n2;
        float n = n2 = 1000000.0f;
        float n4;
        float n3 = n4 = -1000000.0f;
        int n5 = 0;
        for (int i = 0; i < a; ++i) {
            b.if[i][0] = b.if[i][0] * this.P + this.P;
            b.if[i][1] = -b.if[i][1] * this.s + this.s;
            b.if[i][2] *= this.av - 1;
            b.if[i][3] *= this.ae - 1;
            if (b.if[i][0] < n2) {
                n2 = b.if[i][0];
            }
            if (b.if[i][0] > n4) {
                n4 = b.if[i][0];
            }
            if (b.if[i][1] < n) {
                n = b.if[i][1];
                n5 = i;
            }
            if (b.if[i][1] > n3) {
                n3 = b.if[i][1];
            }
        }
        if (n2 > this.long) {
            return;
        }
        if (n > this.byte) {
            return;
        }
        if (n4 < 0.0f) {
            return;
        }
        if (n3 < 0.0f) {
            return;
        }
        int n7;
        int n6 = n7 = n5;
        int j = a;
        final float n8 = n - 0.5f;
        int n9 = (int)n8;
        if (n8 > 0.0f) {
            ++n9;
        }
        int l;
        int k = l = n9 - 1;
        final int for1 = this.t.for;
        while (j > 0) {
            while (l <= n9) {
                if (j <= 0) {
                    break;
                }
                --j;
                int n10 = n7 - 1;
                if (n10 < 0) {
                    n10 = a - 1;
                }
                this.a(b.if[n7], b.if[n10], this.X, this.B, n9);
                final float n11 = b.if[n10][1] + 0.5f;
                l = (int)n11;
                if (n11 < 0.0f) {
                    --l;
                }
                n7 = n10;
            }
            while (k <= n9) {
                if (j <= 0) {
                    break;
                }
                --j;
                int n12 = n6 + 1;
                if (n12 >= a) {
                    n12 = 0;
                }
                this.a(b.if[n6], b.if[n12], this.Q, this.w, n9);
                final float n13 = b.if[n12][1] + 0.5f;
                k = (int)n13;
                if (n13 < 0.0f) {
                    --k;
                }
                n6 = n12;
            }
            while (n9 < l && n9 < k && n9 < for1) {
                if (n9 >= 0) {
                    if (this.X[0] < this.Q[0]) {
                        this.a(n9, this.X, this.Q);
                    }
                    else {
                        this.a(n9, this.Q, this.X);
                    }
                }
                ++n9;
                final float[] x = this.X;
                final int n14 = 0;
                x[n14] += this.B[0];
                final float[] q = this.Q;
                final int n15 = 0;
                q[n15] += this.w[0];
                final float[] x2 = this.X;
                final int n16 = 1;
                x2[n16] += this.B[1];
                final float[] q2 = this.Q;
                final int n17 = 1;
                q2[n17] += this.w[1];
                final float[] x3 = this.X;
                final int n18 = 2;
                x3[n18] += this.B[2];
                final float[] q3 = this.Q;
                final int n19 = 2;
                q3[n19] += this.w[2];
            }
            if (n9 >= for1) {
                break;
            }
        }
    }
    
    private void f() {
        if (this.aK == null) {
            this.aK = new Rectangle(0, 0, this.bounds().width, this.bounds().height);
        }
        if (this.b != null) {
            return;
        }
        if (this.v == null) {
            this.a("Could not load the ivr file.", "Verify that the URL is correct in the html file.");
        }
        try {
            this.b = Toolkit.getDefaultToolkit().getImage(new URL(this.void, this.v));
        }
        catch (Exception ex) {
            this.b = null;
        }
        if (this.b != null) {
            (this.goto = new MediaTracker(this)).addImage(this.b, 0);
            this.K = true;
        }
    }
    
    public float b() {
        return this.ak;
    }
    
    public void if(final float[] array) {
        array[0] = this.N;
        array[1] = this.F;
        array[2] = this.R;
    }
    
    public float c() {
        return this.new;
    }
    
    private void new() {
        if (this.b == null) {
            return;
        }
        try {
            this.av = this.b.getWidth(this);
            this.ae = this.b.getHeight(this);
            if (this.q == null && this.av > 0 && this.ae > 0) {
                this.q = new int[this.av * this.ae];
            }
        }
        catch (Exception ex) {
            this.q = null;
            this.a("Not enough memory to display the panorama.", null);
        }
        catch (OutOfMemoryError outOfMemoryError) {
            this.a("Not enough memory to display the panorama.", null);
        }
        if (this.q != null) {
            try {
                new PixelGrabber(this.b, 0, 0, this.av, this.ae, this.q, 0, this.av).grabPixels();
            }
            catch (InterruptedException ex2) {}
            catch (OutOfMemoryError outOfMemoryError2) {
                this.a("Not enough memory to display the panorama.", null);
            }
        }
    }
    
    public void void() {
        this.i = false;
        this.ab.a("hotspot", pa.a.a.a.a.goto);
        this.ar = true;
    }
    
    private void if(final int n) {
        final float[] array = new float[3];
        final float n2 = 0.3f;
        final float n3 = 0.3f;
        float n5;
        float n4 = n5 = 0.0f;
        final float c = this.c();
        this.if(array);
        if (this.ag) {
            final float n6 = this.af - this.T;
            final float n7 = this.S - this.ad;
            final float n8 = n6 / this.P;
            final float n9 = n7 / this.s;
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
            array2[n12] += n11 * n3 * c;
            final float[] array3 = array;
            final int n13 = 1;
            array3[n13] += n10 * n2 * c;
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
            array4[n14] += n4 * c;
            final float[] array5 = array;
            final int n15 = 1;
            array5[n15] += n5 * c;
        }
        array[2] = 0.0f;
        this.a(array);
        this.ar = true;
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
    
    public void do(final int n) {
        this.o[n] = null;
        for (int i = 63; i >= 0; --i) {
            if (this.o[i] != null) {
                return;
            }
        }
    }
    
    public void long() {
        this.a(2);
        this.Y = -1;
        final float[] array = { this.l, this.O, 0.0f };
        this.a(this.z);
        this.a(array);
        this.a(this.z);
        this.aF = (this.e == 0.0f);
        this.ar = true;
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
        if (n5 >= this.t.a) {
            n5 = this.t.a - 1;
        }
        float n6 = array2[0] - array[0];
        if (n6 == 0.0f) {
            n6 = 1.0f;
        }
        final float n7 = n3 + 0.5f - array[0];
        final float n8 = 1.0f / n6;
        this.aa[1] = (array2[1] - array[1]) * n8;
        this.aa[2] = (array2[2] - array[2]) * n8;
        this.null[1] = array[1] + this.aa[1] * n7;
        this.null[2] = array[2] + this.aa[2] * n7;
        int n9 = (int)(this.null[1] * 65536.0f);
        int n10 = (int)(this.null[2] * 65536.0f);
        final int n11 = (int)(this.aa[1] * 65536.0f);
        final int n12 = (int)(this.aa[2] * 65536.0f);
        final int n13 = 1 + n5 - n3;
        final int n14 = n * this.t.a + n3;
        for (int n15 = n13 + n14, i = n14; i < n15; ++i) {
            this.t.do[i] = this.q[(n10 >> 16) * this.av + (n9 >> 16)];
            n9 += n11;
            n10 += n12;
        }
    }
    
    public void a(final float l, final float o, final float aw, final float z) {
        this.l = l;
        this.O = o;
        this.aw = aw;
        this.z = z;
    }
    
    public void a(final float ak, final boolean b) {
        this.ak = ak;
        if (b) {
            this.a(this.c());
        }
        this.ar = true;
    }
    
    public void a(final float[] array) {
        this.N = array[0];
        this.F = array[1];
        this.R = array[2];
        final float n = this.new * 0.5f;
        if (Math.abs(this.am - this.char) > 0.001f) {
            this.F = this.a(this.F, this.char, this.am);
        }
        if (this.N > 1.5707964f) {
            this.N = 1.5707964f;
        }
        if (this.N < -1.5707964f) {
            this.N = -1.5707964f;
        }
        if (this.int - this.al > 0.001f) {
            final float n2 = n * (this.P / this.s);
            if (this.F + n2 > this.int - 0.1f) {
                this.F = this.int - n2 - 0.1f;
            }
            if (this.F - n2 < this.al + 0.1f) {
                this.F = this.al + n2 + 0.1f;
            }
            if (this.F > this.int || this.F < this.al) {
                this.F = (this.int - this.al) / 2.0f;
            }
        }
        while (this.F < 0.0f) {
            this.F += 6.2831855f;
        }
        while (this.F > 6.2831855f) {
            this.F -= 6.2831855f;
        }
        if (this.ax < 1.5707964f && this.N + n > this.ax - 1.0E-4f) {
            this.N = this.ax - n - 1.0E-4f;
        }
        if (this.E > -1.5707964f && this.N - n < this.E + 1.0E-4f) {
            this.N = this.E + n + 1.0E-4f;
        }
        this.ac = (float)Math.cos(this.N);
        this.ah = (float)Math.sin(this.N);
    }
    
    public void a(final String s, final int ap, final float m, final float j, float al, float int1, final float e, final float ax, final float char1, final float am, final float e2, final boolean b) {
        this.v = s;
        this.q = null;
        this.v = s;
        this.f();
        this.ap = ap;
        this.e = e2;
        this.aF = (this.e == 0.0f);
        if (b) {
            this.byte();
        }
        else {
            this.void();
        }
        this.m = m;
        this.j = j;
        this.al = al;
        this.int = int1;
        this.E = e;
        this.ax = ax;
        this.char = char1;
        this.am = am;
        if (this.ap == 1) {
            this.m = this.a(this.m, -1.4137167f, 1.4137167f);
        }
        else if (this.ap == 2) {
            this.m = -1.5707964f;
            this.j = 1.5707964f;
            final float n = 0.0f;
            this.int = n;
            this.al = n;
        }
        else {
            this.ap = 0;
        }
        if (this.ap == 0) {
            this.aB = 17;
            this.az = 33;
            this.aE = new float[this.aB][this.az][8];
            for (int i = 0; i < this.aB; ++i) {
                final float n2 = 1.5707964f - (this.j + (this.m - this.j) * i / (this.aB - 1));
                final float n3 = (float)Math.sin(n2);
                for (int k = 0; k < this.az; ++k) {
                    float n4;
                    if (this.int - this.al < 0.001f) {
                        n4 = 6.2831855f * k / (this.az - 1);
                    }
                    else {
                        n4 = this.al + (this.int - this.al) * k / (this.az - 1);
                    }
                    this.aE[i][k][0] = -n3 * (float)Math.sin(n4);
                    this.aE[i][k][1] = (float)Math.cos(n2);
                    this.aE[i][k][2] = n3 * (float)Math.cos(n4);
                    this.aE[i][k][3] = k / (this.az - 1);
                    this.aE[i][k][4] = i / (this.aB - 1);
                    this.aE[i][k][5] = 0.0f;
                    this.aE[i][k][6] = 0.0f;
                    this.aE[i][k][7] = 0.0f;
                }
            }
        }
        else if (this.ap == 1) {
            this.aB = 17;
            this.az = 33;
            this.aE = new float[this.aB][this.az][8];
            al = (float)Math.tan(this.m);
            int1 = (float)Math.tan(this.j);
            for (int l = 0; l < this.aB; ++l) {
                for (int n5 = 0; n5 < this.az; ++n5) {
                    float n6;
                    if (this.int - this.al < 0.001f) {
                        n6 = 6.2831855f * n5 / (this.az - 1);
                    }
                    else {
                        n6 = this.al + (this.int - this.al) * n5 / (this.az - 1);
                    }
                    this.aE[l][n5][0] = -(float)Math.sin(n6);
                    this.aE[l][n5][1] = int1 + (al - int1) * l / (this.aB - 1);
                    this.aE[l][n5][2] = (float)Math.cos(n6);
                    this.aE[l][n5][3] = n5 / (this.az - 1);
                    this.aE[l][n5][4] = l / (this.aB - 1);
                    this.aE[l][n5][5] = 0.0f;
                    this.aE[l][n5][6] = 0.0f;
                    this.aE[l][n5][7] = 0.0f;
                }
            }
        }
        else if (this.ap == 2) {
            this.aB = 54;
            this.az = 9;
            this.aE = new float[this.aB][this.az][8];
            final int n7 = 0;
            final int n8 = this.aB / 6;
            for (int n9 = n7; n9 < n8; ++n9) {
                float n11;
                final float n10 = n11 = (n9 - n7) / (n8 - n7 - 1);
                if (n9 == n7) {
                    n11 += 0.01f;
                }
                if (n9 == n8 - 1) {
                    n11 -= 0.01f;
                }
                for (int n12 = 0; n12 < this.az; ++n12) {
                    final float n13 = n12 / (this.az - 1);
                    this.aE[n9][n12][0] = n13 * 2.0f - 1.0f;
                    this.aE[n9][n12][1] = 1.0f - n10 * 2.0f;
                    this.aE[n9][n12][2] = -1.0f;
                    this.aE[n9][n12][3] = n13;
                    this.aE[n9][n12][4] = n11 / 6.0f;
                    this.aE[n9][n12][5] = 0.0f;
                    this.aE[n9][n12][6] = 0.0f;
                    this.aE[n9][n12][7] = 0.0f;
                }
            }
            final int n14 = n8;
            final int n15 = n8 + this.aB / 6;
            for (int n16 = n14; n16 < n15; ++n16) {
                float n18;
                final float n17 = n18 = (n16 - n14) / (n15 - n14 - 1);
                if (n16 == n14) {
                    n18 += 0.01f;
                }
                if (n16 == n15 - 1) {
                    n18 -= 0.01f;
                }
                for (int n19 = 0; n19 < this.az; ++n19) {
                    final float n20 = n19 / (this.az - 1);
                    this.aE[n16][n19][0] = 1.0f;
                    this.aE[n16][n19][1] = 1.0f - n17 * 2.0f;
                    this.aE[n16][n19][2] = n20 * 2.0f - 1.0f;
                    this.aE[n16][n19][3] = n20;
                    this.aE[n16][n19][4] = 0.16666667f + n18 / 6.0f;
                    this.aE[n16][n19][5] = 0.0f;
                    this.aE[n16][n19][6] = 0.0f;
                    this.aE[n16][n19][7] = 0.0f;
                }
            }
            final int n21 = n15;
            final int n22 = n15 + this.aB / 6;
            for (int n23 = n21; n23 < n22; ++n23) {
                float n25;
                final float n24 = n25 = (n23 - n21) / (n22 - n21 - 1);
                if (n23 == n21) {
                    n25 += 0.01f;
                }
                if (n23 == n22 - 1) {
                    n25 -= 0.01f;
                }
                for (int n26 = 0; n26 < this.az; ++n26) {
                    final float n27 = n26 / (this.az - 1);
                    this.aE[n23][n26][0] = 1.0f - n27 * 2.0f;
                    this.aE[n23][n26][1] = 1.0f - n24 * 2.0f;
                    this.aE[n23][n26][2] = 1.0f;
                    this.aE[n23][n26][3] = n27;
                    this.aE[n23][n26][4] = 0.33333334f + n25 / 6.0f;
                    this.aE[n23][n26][5] = 0.0f;
                    this.aE[n23][n26][6] = 0.0f;
                    this.aE[n23][n26][7] = 0.0f;
                }
            }
            final int n28 = n22;
            final int n29 = n22 + this.aB / 6;
            for (int n30 = n28; n30 < n29; ++n30) {
                float n32;
                final float n31 = n32 = (n30 - n28) / (n29 - n28 - 1);
                if (n30 == n28) {
                    n32 += 0.01f;
                }
                if (n30 == n29 - 1) {
                    n32 -= 0.01f;
                }
                for (int n33 = 0; n33 < this.az; ++n33) {
                    final float n34 = n33 / (this.az - 1);
                    this.aE[n30][n33][0] = -1.0f;
                    this.aE[n30][n33][1] = 1.0f - n31 * 2.0f;
                    this.aE[n30][n33][2] = 1.0f - n34 * 2.0f;
                    this.aE[n30][n33][3] = n34;
                    this.aE[n30][n33][4] = 0.5f + n32 / 6.0f;
                    this.aE[n30][n33][5] = 0.0f;
                    this.aE[n30][n33][6] = 0.0f;
                    this.aE[n30][n33][7] = 0.0f;
                }
            }
            final int n35 = n29;
            final int n36 = n29 + this.aB / 6;
            for (int n37 = n35; n37 < n36; ++n37) {
                float n39;
                final float n38 = n39 = (n37 - n35) / (n36 - n35 - 1);
                if (n37 == n35) {
                    n39 += 0.01f;
                }
                if (n37 == n36 - 1) {
                    n39 -= 0.01f;
                }
                for (int n40 = 0; n40 < this.az; ++n40) {
                    final float n41 = n40 / (this.az - 1);
                    this.aE[n37][n40][0] = n41 * 2.0f - 1.0f;
                    this.aE[n37][n40][1] = 1.0f;
                    this.aE[n37][n40][2] = 1.0f - n38 * 2.0f;
                    this.aE[n37][n40][3] = n41;
                    this.aE[n37][n40][4] = 0.6666667f + n39 / 6.0f;
                    this.aE[n37][n40][5] = 0.0f;
                    this.aE[n37][n40][6] = 0.0f;
                    this.aE[n37][n40][7] = 0.0f;
                }
            }
            final int n42 = n36;
            for (int n43 = n36 + this.aB / 6, n44 = n42; n44 < n43; ++n44) {
                float n46;
                final float n45 = n46 = (n44 - n42) / (n43 - n42 - 1);
                if (n44 == n42) {
                    n46 += 0.01f;
                }
                if (n44 == n43 - 1) {
                    n46 -= 0.01f;
                }
                for (int n47 = 0; n47 < this.az; ++n47) {
                    final float n48 = n47 / (this.az - 1);
                    this.aE[n44][n47][0] = n48 * 2.0f - 1.0f;
                    this.aE[n44][n47][1] = -1.0f;
                    this.aE[n44][n47][2] = n45 * 2.0f - 1.0f;
                    this.aE[n44][n47][3] = n48;
                    this.aE[n44][n47][4] = 0.8333333f + n46 / 6.0f;
                    this.aE[n44][n47][5] = 0.0f;
                    this.aE[n44][n47][6] = 0.0f;
                    this.aE[n44][n47][7] = 0.0f;
                }
            }
        }
    }
    
    public void a(final float new1) {
        this.new = new1;
        if (this.new < this.ak) {
            this.new = this.ak;
        }
        if (this.new > 2.094f) {
            this.new = 2.094f;
        }
        if (this.new > this.j - this.m - 1.0E-4f) {
            this.new = this.j - this.m - 1.0E-4f;
        }
        if (this.new > this.ax - this.E - 1.0E-4f) {
            this.new = this.ax - this.E - 1.0E-4f;
        }
        float n = this.new * this.P / this.s;
        if (n > 2.094f) {
            n = 2.094f;
            this.new = n * this.s / this.P;
        }
        if (this.int - this.al > 0.05f && n > this.int - this.al - 0.05f) {
            n = this.int - this.al - 0.05f;
            this.new = n * this.s / this.P;
        }
        if (this.am - this.char > 0.05f && n > this.am - this.char - 0.05f) {
            this.new = (this.am - this.char - 0.05f) * this.s / this.P;
        }
        this.if(-1);
    }
    
    public void byte() {
        this.i = true;
        this.ab.a("hotspot", pa.a.a.a.a.int);
        this.ar = true;
    }
    
    public void e() {
        this.n.showDocument(this.else, "mgizoom");
    }
    
    private void a(final float n, final float n2, final float[] array) {
        final float[] array2 = new float[2];
        final float[] array3 = new float[2];
        this.if(this.al, this.m, array2);
        if (this.int - this.al < 0.001f) {
            this.if(6.2831855f, this.j, array3);
        }
        else {
            this.if(this.int, this.j, array3);
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
        this.do(n3, n4, array);
    }
    
    private void do(final float n, final float n2, final float[] array) {
        if (this.ap == 0) {
            array[0] = n2;
            array[1] = n;
        }
        else if (this.ap == 1) {
            final float n3 = (float)Math.tan(this.j);
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
        else if (this.ap == 2) {
            final float n4 = 1.0f;
            final float n5 = 0.16666667f;
            float n7;
            float n8;
            float n9;
            if (n2 > 0.833f) {
                final float n6 = n2 - 0.833f;
                n7 = -1.0f;
                n8 = 2.0f * n / n4 - 1.0f;
                n9 = 2.0f * n6 / n5 - 1.0f;
            }
            else if (n2 > 0.667f) {
                final float n10 = n2 - 0.667f;
                n7 = 1.0f;
                n8 = 2.0f * n / n4 - 1.0f;
                n9 = 1.0f - 2.0f * n10 / n5;
            }
            else if (n2 > 0.5f) {
                final float n11 = n2 - 0.5f;
                n8 = -1.0f;
                n7 = 1.0f - 2.0f * n11 / n5;
                n9 = 1.0f - 2.0f * n / n4;
            }
            else if (n2 > 0.333f) {
                final float n12 = n2 - 0.333f;
                n9 = 1.0f;
                n8 = 1.0f - 2.0f * n / n4;
                n7 = 1.0f - 2.0f * n12 / n5;
            }
            else if (n2 > 0.167f) {
                final float n13 = n2 - 0.167f;
                n8 = 1.0f;
                n7 = 1.0f - 2.0f * n13 / n5;
                n9 = 2.0f * n / n4 - 1.0f;
            }
            else {
                n9 = -1.0f;
                n8 = 2.0f * n / n4 - 1.0f;
                n7 = 1.0f - 2.0f * n2 / n5;
            }
            array[0] = (float)Math.atan2(n7, (float)Math.sqrt(n8 * n8 + n9 * n9));
            array[1] = (float)Math.atan2(-n8, n9);
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
    
    static /* synthetic */ void access$1(final c c, final boolean ag) {
        c.ag = ag;
    }
    
    static /* synthetic */ void access$11(final c c, final int ad) {
        c.ad = ad;
    }
    
    static /* synthetic */ void access$13(final c c, final int s) {
        c.S = s;
    }
    
    static /* synthetic */ void access$3(final c c, final int j) {
        c.J = j;
    }
    
    static /* synthetic */ void access$5(final c c, final int af) {
        c.af = af;
    }
    
    static /* synthetic */ void access$7(final c c, final int t) {
        c.T = t;
    }
    
    static /* synthetic */ void access$9(final c c, final int i) {
        c.I = i;
    }
    
    private void a(final Graphics graphics) {
        final int width = this.bounds().width;
        final int height = this.bounds().height;
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, width, height);
        if (this.U != null) {
            try {
                final Image image = Toolkit.getDefaultToolkit().getImage(new URL(this.void, this.U));
                if (!this.a(image, this)) {
                    return;
                }
                final int height2 = image.getHeight(this);
                final int width2 = image.getWidth(this);
                if (width2 > 0 && height2 > 0) {
                    graphics.drawImage(image, (width >> 1) - (width2 >> 1), (height >> 1) - (height2 >> 1), this);
                }
                return;
            }
            catch (MalformedURLException ex) {
                return;
            }
        }
        Image image2 = null;
        try {
            final InputStream a = pa.a.b.a.g.a("images/splashscreen.gif");
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
    
    private void char() {
        final float[] array = new float[2];
        this.a(this.af, this.ad, array);
        final int[] array2 = new int[2];
        final float[] array3 = new float[3];
        for (int i = 0; i < 64; ++i) {
            if (this.o[i] != null && this.o[i].for) {
                this.a((this.o[i].a + this.o[i].byte) * 0.5f, (this.o[i].if + this.o[i].new) * 0.5f, array3);
                this.a(array3[1], array3[0], array2);
                if (this.o[i].a(array[0], array[1], this.af, this.ad, array2[0], array2[1], this.n)) {
                    break;
                }
            }
        }
    }
    
    private void if(final Graphics graphics) {
        graphics.setColor(this.G);
        if (this.aM == null) {
            graphics.fillRect(0, 0, this.size().width, this.aK.y);
            graphics.fillRect(0, 0, this.aK.x, this.size().height);
            graphics.fillRect(this.aK.x + this.aK.width, 0, this.size().width - (this.aK.x + this.aK.width), this.size().height);
            graphics.fillRect(0, this.aK.y + this.aK.height, this.size().width, this.size().height - (this.aK.y + this.aK.height));
        }
    }
    
    private void do(final Graphics graphics) {
        if (this.aM != null && (this.aK.width != this.size().width || this.aK.height != this.size().height)) {
            final int width = this.bounds().width;
            final int height = this.bounds().height;
            if (this.ay == null && this.a(this.aM, this)) {
                this.ay = this.createImage(width, height - (this.aK.y + this.aK.height));
                final Graphics graphics2 = this.ay.getGraphics();
                graphics2.setColor(this.G);
                graphics2.fillRect(0, 0, width, height - (this.aK.y + this.aK.height));
                graphics2.drawImage(this.aM, 0, -(this.aK.y + this.aK.height), width, height, this);
                graphics2.dispose();
                this.aI = this.createImage(width, this.aK.y);
                final Graphics graphics3 = this.aI.getGraphics();
                graphics3.setColor(this.G);
                graphics3.fillRect(0, 0, width, this.aK.y);
                graphics3.drawImage(this.aM, 0, 0, width, height, this);
                graphics3.dispose();
                this.r = this.createImage(this.aK.x, height);
                final Graphics graphics4 = this.r.getGraphics();
                graphics4.setColor(this.G);
                graphics4.fillRect(0, 0, this.aK.x, height);
                graphics4.drawImage(this.aM, 0, 0, width, height, this);
                graphics4.dispose();
                this.case = this.createImage(width - (this.aK.x + this.aK.width), height);
                final Graphics graphics5 = this.case.getGraphics();
                graphics5.setColor(this.G);
                graphics5.fillRect(0, 0, width - (this.aK.x + this.aK.width), height);
                graphics5.drawImage(this.aM, -(this.aK.x + this.aK.width), 0, width, height, this);
                graphics5.dispose();
                this.aM.flush();
            }
            graphics.drawImage(this.aI, 0, 0, this);
            graphics.drawImage(this.r, 0, 0, this);
            graphics.drawImage(this.ay, 0, this.aK.y + this.aK.height, this);
            graphics.drawImage(this.case, this.aK.x + this.aK.width, 0, this);
        }
    }
    
    private void null() {
        final int[] array = new int[2];
        final float[] array2 = new float[3];
        for (int i = 0; i < 64; ++i) {
            if (this.o[i] != null && this.o[i].for && (Math.abs(this.o[i].a - this.o[i].byte) <= 0.95f || Math.abs(this.o[i].if - this.o[i].new) <= 0.95f)) {
                this.a((this.o[i].a + this.o[i].byte) * 0.5f, (this.o[i].if + this.o[i].new) * 0.5f, array2);
                if (Math.abs(array2[0] - this.N) <= 1.22f && (Math.abs(array2[1] - this.F) <= 1.22f || Math.abs(array2[1] - this.F) >= 5.0631857f)) {
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
                    if (n4 >= this.t.a) {
                        n4 = this.t.a - 1;
                    }
                    if (n6 >= this.t.for) {
                        n6 = this.t.for - 1;
                    }
                    final int n7 = n3 - n;
                    final int n8 = n4 - n;
                    final int n9 = n5 - n2;
                    for (int n10 = n6 - n2, j = n9; j <= n10; ++j) {
                        for (int k = n7; k <= n8; ++k) {
                            final int n11 = k * k + j * j;
                            if ((n11 <= 144 && n11 >= 70) || (n11 <= 36 && n11 >= 6)) {
                                this.t.do[(n2 + j) * this.t.a + (n + k)] = -65536;
                            }
                        }
                    }
                }
            }
        }
    }
    
    private synchronized boolean goto() {
        if (this.t == null || this.q == null) {
            return false;
        }
        if (this.t.for <= 0 || this.t.a <= 0) {
            return false;
        }
        final float n = 1.0f / (float)Math.tan(this.new * 0.5f);
        final float n2 = n / (this.P / this.s);
        final float n3 = (float)Math.cos(this.F);
        final float n4 = (float)Math.sin(this.F);
        final float n5 = (float)Math.cos(this.N);
        final float n6 = (float)Math.sin(this.N);
        final float n7 = (float)Math.cos(this.R);
        final float n8 = (float)Math.sin(this.R);
        for (int i = 0; i < this.aB; ++i) {
            for (int j = 0; j < this.az; ++j) {
                this.aE[i][j][5] = -(n3 * this.aE[i][j][0] + n4 * this.aE[i][j][2]);
                this.aE[i][j][6] = this.aE[i][j][1];
                this.aE[i][j][7] = n3 * this.aE[i][j][2] - n4 * this.aE[i][j][0];
                final float n9 = this.aE[i][j][6];
                final float n10 = this.aE[i][j][7];
                this.aE[i][j][6] = n5 * n9 - n6 * n10;
                this.aE[i][j][7] = n5 * n10 + n6 * n9;
                final float n11 = this.aE[i][j][5];
                final float n12 = this.aE[i][j][6];
                this.aE[i][j][5] = n7 * n11 - n8 * n12;
                this.aE[i][j][6] = n7 * n12 + n8 * n11;
                if (this.aE[i][j][7] > 0.1f) {
                    this.aE[i][j][5] = this.aE[i][j][5] * n2 / this.aE[i][j][7];
                    this.aE[i][j][6] = this.aE[i][j][6] * n / this.aE[i][j][7];
                }
            }
        }
        for (int k = 0; k < this.aB - 1; ++k) {
            if (this.ap != 2 || (k + 1) % this.az != 0) {
                for (int l = 0; l < this.az - 1; ++l) {
                    if (this.aE[k][l][7] >= 0.1f && this.aE[k][l + 1][7] >= 0.1f && this.aE[k + 1][l][7] >= 0.1f) {
                        if (this.aE[k + 1][l + 1][7] >= 0.1f) {
                            this.aH.a = 4;
                            this.aH.if[0][0] = this.aE[k][l][5];
                            this.aH.if[0][1] = this.aE[k][l][6];
                            this.aH.if[0][2] = this.aE[k][l][3];
                            this.aH.if[0][3] = this.aE[k][l][4];
                            this.aH.if[1][0] = this.aE[k][l + 1][5];
                            this.aH.if[1][1] = this.aE[k][l + 1][6];
                            this.aH.if[1][2] = this.aE[k][l + 1][3];
                            this.aH.if[1][3] = this.aE[k][l + 1][4];
                            this.aH.if[2][0] = this.aE[k + 1][l + 1][5];
                            this.aH.if[2][1] = this.aE[k + 1][l + 1][6];
                            this.aH.if[2][2] = this.aE[k + 1][l + 1][3];
                            this.aH.if[2][3] = this.aE[k + 1][l + 1][4];
                            this.aH.if[3][0] = this.aE[k + 1][l][5];
                            this.aH.if[3][1] = this.aE[k + 1][l][6];
                            this.aH.if[3][2] = this.aE[k + 1][l][3];
                            this.aH.if[3][3] = this.aE[k + 1][l][4];
                            this.a(this.aH);
                        }
                    }
                }
            }
        }
        return true;
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        if (keyEvent.isShiftDown()) {
            if (this.Y == -1) {
                this.Y = this.W;
            }
            this.a(0);
        }
        else if (keyEvent.isControlDown()) {
            if (this.Y == -1) {
                this.Y = this.W;
            }
            this.a(1);
        }
        this.try();
        switch (keyEvent.getKeyCode()) {
            case 10: {
                if (this.aK.inside(this.af, this.ad) && this.i) {
                    final float[] array = new float[2];
                    this.a(this.af, this.ad, array);
                    final int[] array2 = new int[2];
                    final float[] array3 = new float[3];
                    for (int i = 0; i < 64; ++i) {
                        if (this.o[i] != null) {
                            this.a((this.o[i].a + this.o[i].byte) * 0.5f, (this.o[i].if + this.o[i].new) * 0.5f, array3);
                            this.a(array3[1], array3[0], array2);
                            if (this.o[i].a(array[0], array[1], this.af, this.ad, array2[0], array2[1], this.n, this.void)) {
                                break;
                            }
                        }
                    }
                }
            }
            case 72:
            case 104: {
                if (this.i) {
                    this.void();
                }
                else {
                    this.byte();
                }
                this.ar = true;
            }
            case 73:
            case 105: {
                this.e();
            }
            case 86:
            case 118: {
                this.long();
            }
            case 37: {
                this.if(0);
            }
            case 39: {
                this.if(1);
            }
            case 38: {
                this.if(2);
            }
            case 40: {
                this.if(3);
            }
            case 33:
            case 65:
            case 97: {
                this.d();
            }
            case 34:
            case 90:
            case 122: {
                this.else();
            }
            default: {}
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        if (this.Y != -1) {
            this.a(this.Y);
            this.Y = -1;
        }
        this.ar = true;
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void paint(final Graphics graphics) {
        if (this.D) {
            this.D = false;
            this.a(graphics);
        }
        this.ar = true;
    }
    
    protected boolean a() {
        if (this.M != null) {
            return false;
        }
        if (!this.aF) {
            final float[] array = new float[3];
            this.if(array);
            final float n = array[1];
            if (this.ai) {
                final float[] array2 = array;
                final int n2 = 1;
                array2[n2] += this.aC;
            }
            else {
                final float[] array3 = array;
                final int n3 = 1;
                array3[n3] -= this.aC;
            }
            array[0] = this.l;
            array[2] = this.aw;
            this.a(this.z);
            try {
                this.case();
            }
            catch (Exception ex) {}
            this.a(array);
            this.if(array);
            if (Math.abs(array[1] - n) < 1.0E-4f) {
                this.ai ^= true;
            }
            try {
                Thread.sleep(20L);
            }
            catch (InterruptedException ex2) {
                this.try();
            }
            return true;
        }
        if (this.ag) {
            if (this.W == 0) {
                this.d();
            }
            else if (this.W == 1) {
                this.else();
            }
            this.if(4);
        }
        if (this.ar) {
            this.ar = false;
            this.case();
            return true;
        }
        return false;
    }
    
    private void a(final String m, final String h) {
        if (this.M != null) {
            return;
        }
        this.M = m;
        this.h = h;
        this.ar = true;
    }
    
    public void case() {
        if (this.aK == null) {
            return;
        }
        if (this.M != null) {
            final Graphics graphics = this.getGraphics();
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            graphics.setColor(Color.white);
            graphics.fillRect(this.aK.x, this.aK.y, this.aK.width, this.aK.height);
            graphics.setColor(Color.black);
            graphics.drawString(this.M, this.aK.x + (this.aK.width >> 1) - (fontMetrics.stringWidth(this.M) >> 1), this.aK.y + (this.aK.height >> 1));
            if (this.h != null) {
                graphics.drawString(this.h, this.aK.x + (this.aK.width >> 1) - (fontMetrics.stringWidth(this.h) >> 1), this.aK.y + (this.aK.height >> 1) + fontMetrics.getHeight());
            }
            graphics.dispose();
            return;
        }
        if ((this.f && this.au == null) || (!pa.a.b.a.c.if && pa.a.b.a.c.a && this.au == null)) {
            this.au = this.createImage(this.size().width, this.size().height);
        }
        if (this.t == null) {
            try {
                this.t = new b(this.aK.width, this.aK.height);
            }
            catch (Exception ex) {
                this.a("Not enough memory to display the panorama.", null);
                return;
            }
            this.P = this.aK.width * 0.5f;
            this.s = this.aK.height * 0.5f;
            this.long = this.aK.width;
            this.byte = this.aK.height;
        }
        if (this.K && this.goto != null) {
            if ((this.goto.statusAll(true) & 0x8) != 0x0) {
                this.new();
                this.K = false;
                this.goto = null;
                this.b.flush();
                this.b = null;
                System.gc();
            }
            else {
                if ((this.goto.statusAll(true) & 0x4) != 0x0) {
                    this.K = false;
                    this.goto = null;
                    this.b.flush();
                    this.b = null;
                    System.gc();
                    this.a("Could not load the ivr file.", "Verify that the URL is correct in the html file.");
                    return;
                }
                this.new();
                this.ar = true;
            }
        }
        if (this.q == null) {
            return;
        }
        try {
            if (!this.goto()) {
                return;
            }
            if (this.i) {
                this.null();
                this.char();
            }
            this.t.a();
        }
        catch (Exception ex2) {}
        final Graphics graphics2 = this.getGraphics();
        Graphics graphics3;
        if (this.f || (!pa.a.b.a.c.if && pa.a.b.a.c.a)) {
            graphics3 = this.au.getGraphics();
        }
        else {
            graphics3 = graphics2;
        }
        this.if(graphics3);
        if (this.aM != null && !this.f) {
            this.do(graphics3);
        }
        graphics3.drawImage(this.t.new, this.aK.x, this.aK.y, this.aK.width, this.aK.height, null);
        if (this.f) {
            graphics3.drawImage(this.aM, 0, 0, this.size().width, this.size().height, this);
            graphics3.dispose();
            graphics2.drawImage(this.au, 0, 0, null);
        }
        else if (!pa.a.b.a.c.if && pa.a.b.a.c.a) {
            graphics3.dispose();
            graphics2.drawImage(this.au, 0, 0, null);
        }
        graphics2.dispose();
    }
    
    protected void if() {
        this.aF = (this.e == 0.0f);
        this.requestFocus();
        this.ai = (this.e < 0.0f);
        this.aC = 0.0f;
        if (!this.aF) {
            if (this.int == 0.0f && this.al == 0.0f) {
                this.aC = 6.2831855f / (Math.abs(this.e) * 15.0f);
            }
            else {
                this.aC = (this.int - this.al) / (Math.abs(this.e) * 15.0f);
            }
        }
    }
    
    protected void do() {
        this.n = null;
        this.b = null;
        this.removeAll();
        this.aM = null;
        this.r = null;
        this.aI = null;
        this.case = null;
        this.ay = null;
        this.au = null;
        this.q = null;
        this.t = null;
    }
    
    public void a(final int w) {
        this.W = w;
        if (this.at == null) {
            return;
        }
        switch (this.W) {
            case 0: {
                this.setCursor(Cursor.getPredefinedCursor(1));
                this.ab.do("zoomIn");
                break;
            }
            case 1: {
                this.setCursor(Cursor.getPredefinedCursor(1));
                this.ab.do("zoomOut");
                break;
            }
            case 2: {
                this.setCursor(Cursor.getPredefinedCursor(0));
                this.ab.do("pan");
                break;
            }
            default: {
                this.setCursor(Cursor.getPredefinedCursor(0));
                break;
            }
        }
        this.requestFocus();
    }
    
    public void try() {
        this.aF = true;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private class a extends MouseAdapter implements MouseMotionListener
    {
        public void mouseDragged(final MouseEvent mouseEvent) {
            if (pa.a.b.a.a.c.this.ag) {
                pa.a.b.a.a.c.access$5(pa.a.b.a.a.c.this, mouseEvent.getX());
                pa.a.b.a.a.c.access$11(pa.a.b.a.a.c.this, mouseEvent.getY());
            }
            pa.a.b.a.a.c.this.ar = true;
        }
        
        public void mouseMoved(final MouseEvent mouseEvent) {
            final c this$0 = pa.a.b.a.a.c.this;
            final c this$2 = pa.a.b.a.a.c.this;
            final int x = mouseEvent.getX();
            pa.a.b.a.a.c.access$5(this$2, x);
            pa.a.b.a.a.c.access$7(this$0, x);
            final c this$3 = pa.a.b.a.a.c.this;
            final c this$4 = pa.a.b.a.a.c.this;
            final int y = mouseEvent.getY();
            pa.a.b.a.a.c.access$11(this$4, y);
            pa.a.b.a.a.c.access$13(this$3, y);
            if (pa.a.b.a.a.c.this.i) {
                pa.a.b.a.a.c.this.ar = true;
            }
        }
        
        public void mousePressed(final MouseEvent mouseEvent) {
            pa.a.b.a.a.c.this.try();
            pa.a.b.a.a.c.access$1(pa.a.b.a.a.c.this, true);
            final c this$0 = pa.a.b.a.a.c.this;
            final c this$2 = pa.a.b.a.a.c.this;
            final c this$3 = pa.a.b.a.a.c.this;
            final int x = mouseEvent.getX();
            pa.a.b.a.a.c.access$3(this$3, x);
            pa.a.b.a.a.c.access$5(this$2, x);
            pa.a.b.a.a.c.access$7(this$0, x);
            final c this$4 = pa.a.b.a.a.c.this;
            final c this$5 = pa.a.b.a.a.c.this;
            final c this$6 = pa.a.b.a.a.c.this;
            final int y = mouseEvent.getY();
            pa.a.b.a.a.c.access$9(this$6, y);
            pa.a.b.a.a.c.access$11(this$5, y);
            pa.a.b.a.a.c.access$13(this$4, y);
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
            pa.a.b.a.a.c.access$1(pa.a.b.a.a.c.this, false);
            final int x = mouseEvent.getX();
            final int y = mouseEvent.getY();
            pa.a.b.a.a.c.access$5(pa.a.b.a.a.c.this, x);
            pa.a.b.a.a.c.access$11(pa.a.b.a.a.c.this, y);
            if (Math.abs(pa.a.b.a.a.c.this.af - pa.a.b.a.a.c.this.J) < 3 && Math.abs(pa.a.b.a.a.c.this.ad - pa.a.b.a.a.c.this.I) < 3) {
                if (!pa.a.b.a.a.c.this.aK.inside(x, y)) {
                    return;
                }
                if (pa.a.b.a.a.c.this.i) {
                    final float[] array = new float[2];
                    pa.a.b.a.a.c.this.a(pa.a.b.a.a.c.this.af, pa.a.b.a.a.c.this.ad, array);
                    final int[] array2 = new int[2];
                    final float[] array3 = new float[3];
                    for (int i = 0; i < 64; ++i) {
                        if (pa.a.b.a.a.c.this.o[i] != null) {
                            pa.a.b.a.a.c.this.a((pa.a.b.a.a.c.this.o[i].a + pa.a.b.a.a.c.this.o[i].byte) * 0.5f, (pa.a.b.a.a.c.this.o[i].if + pa.a.b.a.a.c.this.o[i].new) * 0.5f, array3);
                            pa.a.b.a.a.c.this.a(array3[1], array3[0], array2);
                            if (pa.a.b.a.a.c.this.o[i].a(array[0], array[1], pa.a.b.a.a.c.this.af, pa.a.b.a.a.c.this.ad, array2[0], array2[1], pa.a.b.a.a.c.this.n, pa.a.b.a.a.c.this.void)) {
                                return;
                            }
                        }
                    }
                }
                if (pa.a.b.a.a.c.this.W == 0) {
                    pa.a.b.a.a.c.this.d();
                }
                else if (pa.a.b.a.a.c.this.W == 1) {
                    pa.a.b.a.a.c.this.else();
                }
            }
            pa.a.b.a.a.c.this.ar = true;
        }
    }
}
