// 
// Decompiled by Procyon v0.5.30
// 

package pa.a.a.a.a;

import java.awt.FontMetrics;
import java.awt.Event;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.awt.Graphics;
import java.awt.image.PixelGrabber;
import java.awt.Component;
import java.awt.image.ImageObserver;
import java.net.MalformedURLException;
import java.awt.Toolkit;
import java.awt.LayoutManager;
import java.awt.Dimension;
import java.applet.AppletContext;
import java.awt.Frame;
import java.awt.Color;
import java.awt.Rectangle;
import pa.a.b.b;
import java.awt.MediaTracker;
import java.awt.Image;
import java.net.URL;

public class c extends a
{
    boolean aN;
    private static String aM;
    private static final String u = "panoapplet.properties";
    public static final int c = 64;
    public static final int r = 200;
    public static final int G = 0;
    public static final int K = 1;
    public static final int az = 2;
    public static final float V = 10.0f;
    private static final float R = 1.5707964f;
    private static final float aB = 3.1415927f;
    private static final float af = 6.2831855f;
    private static final int aQ = 63;
    private static final float ay = 1.0E-4f;
    private static final int aT = 0;
    private static final int ai = 1;
    private static final int au = 2;
    private static final int o = 3;
    private static final int B = 4;
    private URL m;
    private String W;
    private String s;
    private String f;
    private String ae;
    private boolean M;
    private String H;
    private Image n;
    private boolean U;
    private MediaTracker j;
    private int aA;
    private float av;
    private float w;
    private float t;
    private float aw;
    private float d;
    private float N;
    private float aH;
    private float i;
    private float ax;
    private int aF;
    private int an;
    private int[] C;
    private float v;
    private float Y;
    private float aG;
    private float J;
    private Image aV;
    private Image D;
    private Image aS;
    private Image h;
    private Image aI;
    private Image aE;
    private b F;
    private Rectangle aU;
    private boolean q;
    private Color Q;
    private pa.a.a.a.d.a for;
    private boolean aP;
    private boolean ar;
    public boolean new;
    private float p;
    private Thread aC;
    private int z;
    private pa.a.a.a.b.a[] A;
    private int ak;
    private boolean ap;
    private boolean at;
    private boolean if;
    private int T;
    private int S;
    private int ad;
    private int ac;
    private int ao;
    private int am;
    private int ag;
    private Frame aD;
    private float X;
    private float al;
    private float aq;
    private float O;
    private float ab;
    private float e;
    private float Z;
    private float E;
    private float k;
    private float g;
    private float[] ah;
    private float[] aa;
    private float[] L;
    private float[] I;
    private float[] l;
    private float[] aj;
    private int aK;
    private int aJ;
    private float[][][] aO;
    private pa.a.a.a.a.b aR;
    long P;
    boolean as;
    float aL;
    
    static {
        pa.a.a.a.a.c.aM = "http://www.mgisoft.com/support";
    }
    
    public c(final URL m, final AppletContext b, final String s, final int n, final int n2, final int n3, final int n4, final boolean q, final int n5, final int n6, final int n7, final int n8, final Dimension dimension, final Frame ad, final String s2, final String s3, final String f, final String ae) {
        this.aN = true;
        this.f = null;
        this.ae = null;
        this.M = true;
        this.H = null;
        this.n = null;
        this.aF = 0;
        this.an = 0;
        this.aV = null;
        this.D = null;
        this.aS = null;
        this.h = null;
        this.aI = null;
        this.aE = null;
        this.F = null;
        this.aU = null;
        this.q = false;
        this.for = null;
        this.aP = true;
        this.p = 20.0f;
        this.aC = null;
        this.z = 2;
        this.at = false;
        this.aD = null;
        this.P = 50L;
        this.as = false;
        pa.a.a.a.a.c.aM = pa.a.a.a.c.a.a(pa.a.a.a.c.a.a("panoapplet.properties"));
        this.m = m;
        super.b = b;
        this.ak = n8;
        this.setBackground(this.Q = new Color(n5, n6, n7));
        this.setForeground(this.Q);
        this.f = f;
        this.ae = ae;
        this.setLayout(null);
        this.A = new pa.a.a.a.b.a[64];
        this.z = n8;
        this.aD = ad;
        pa.a.a.a.d.b.a();
        this.a(0);
        this.reshape(0, 0, dimension.width, dimension.height);
        if (s != null) {
            this.q = q;
            try {
                this.aV = Toolkit.getDefaultToolkit().getImage(new URL(this.m, s));
            }
            catch (MalformedURLException ex) {
                this.aV = null;
                this.q = false;
            }
            if (this.aV != null) {
                Toolkit.getDefaultToolkit().prepareImage(this.aV, dimension.width, dimension.height, this);
                this.aU = new Rectangle(n, n2, n3, n4);
            }
            else {
                this.q = false;
            }
        }
        else {
            this.q = false;
        }
        this.ap = false;
        final int n9 = -1000;
        this.S = n9;
        this.T = n9;
        this.am = n9;
        this.ao = n9;
        this.ac = n9;
        this.ad = n9;
        this.if = false;
        this.ah = new float[3];
        this.aa = new float[3];
        this.L = new float[3];
        this.I = new float[3];
        this.l = new float[3];
        this.aj = new float[3];
        this.aR = new pa.a.a.a.a.b();
        this.X = 0.0f;
        this.O = 3.1415927f;
        this.ab = 0.0f;
        this.e = 0.87266463f;
        this.w = -1.5707964f;
        this.t = 1.5707964f;
        this.aw = 0.0f;
        this.d = 0.0f;
        this.av = 0.17453294f;
    }
    
    public void a(final int n, final float byte1, final float new1, final float a, final float if1, final String do1, final String try1, final String int1, final boolean for1) {
        if (n >= 63 || do1 == null) {
            return;
        }
        this.A[n] = new pa.a.a.a.b.a();
        this.A[n].byte = byte1;
        this.A[n].new = new1;
        this.A[n].a = a;
        this.A[n].if = if1;
        this.A[n].do = do1;
        this.A[n].try = try1;
        this.A[n].int = int1;
        this.A[n].for = for1;
        if (this.for != null) {
            this.for.if(1);
        }
    }
    
    private void a(float n, final float n2, final int[] array) {
        final float n3 = this.e * 0.5f;
        final float n4 = n3 * this.Z / this.E;
        n -= this.O;
        final float n5 = (float)Math.tan(n3);
        final float n6 = (float)Math.tan(n2);
        final float n7 = (float)Math.cos(n);
        final float n8 = (n6 * this.al - this.aq * n7) / ((n7 * this.al + n6 * this.aq) * n5);
        array[0] = (int)(this.k * ((float)Math.tan(n) * (this.al - n5 * n8 * this.aq) / (float)Math.tan(n4) / 2.0f + 0.5f));
        array[1] = (int)(this.g * (0.5f - n8 / 2.0f));
    }
    
    private void if(final float n, final float n2, final float[] array) {
        if (this.aA == 0) {
            array[0] = n;
            array[1] = n2;
        }
        else if (this.aA == 1) {
            array[0] = n;
            final float n3 = (float)Math.tan(this.t);
            if (n2 > this.t) {
                array[1] = n3 + 1.0f - n3 * (float)Math.tan(1.5707964f - n2);
            }
            else if (n2 < -this.t) {
                array[1] = -n3 - (1.0f - n3 * (float)Math.tan(1.5707964f + n2));
            }
            else {
                array[1] = (float)Math.tan(n2);
            }
        }
        else if (this.aA == 2) {
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
    
    public void h() {
        final float n = -1.0f;
        final float n2 = 0.03f;
        this.aP = true;
        final float g = this.g();
        this.a(g + n * (n2 * (g * 1.5f * 1.5f)));
        this.new = true;
    }
    
    public void c() {
        final float n = 1.0f;
        final float n2 = 0.03f;
        this.aP = true;
        final float g = this.g();
        this.a(g + n * (n2 * (g * 1.5f * 1.5f)));
        this.new = true;
    }
    
    private void a(final int n, final int n2, final float[] array) {
        final float n3 = n / this.k;
        final float n4 = n2 / this.g;
        final float n5 = this.e * 0.5f;
        final float n6 = n5 * this.Z / this.E;
        float n7 = (n3 - 0.5f) * 2.0f;
        if (n7 < 0.005 && n7 > -0.005) {
            n7 = 0.0f;
        }
        float n8 = (0.5f - n4) * 2.0f;
        if (n8 < 0.005 && n8 > -0.005) {
            n8 = 0.0f;
        }
        final float al = this.al;
        final float aq = this.aq;
        final float n9 = (float)Math.tan(n5);
        final float n10 = al - n9 * n8 * aq;
        final float n11 = aq + n9 * n8 * al;
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
        float n16 = n13 + this.O;
        if (n15 <= this.w) {
            n15 = -n15 - 3.1415927f;
        }
        else if (n15 > this.t) {
            n15 = 3.1415927f - n15;
        }
        if (this.d - this.aw < 0.001f) {
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
        if (this.d - this.aw < 0.001f) {
            this.if(0.0f, this.w, array3);
            this.if(6.2831855f, this.t, array4);
        }
        else {
            this.if(this.aw, this.w, array3);
            this.if(this.d, this.t, array4);
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
    
    private void a(final pa.a.a.a.a.b b) {
        final int a = b.a;
        float n2;
        float n = n2 = 1000000.0f;
        float n4;
        float n3 = n4 = -1000000.0f;
        int n5 = 0;
        for (int i = 0; i < a; ++i) {
            b.if[i][0] = b.if[i][0] * this.Z + this.Z;
            b.if[i][1] = -b.if[i][1] * this.E + this.E;
            b.if[i][2] *= this.aF - 1;
            b.if[i][3] *= this.an - 1;
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
        if (n2 > this.k) {
            return;
        }
        if (n > this.g) {
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
        final int for1 = this.F.for;
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
                this.a(b.if[n7], b.if[n10], this.ah, this.L, n9);
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
                this.a(b.if[n6], b.if[n12], this.aa, this.I, n9);
                final float n13 = b.if[n12][1] + 0.5f;
                k = (int)n13;
                if (n13 < 0.0f) {
                    --k;
                }
                n6 = n12;
            }
            while (n9 < l && n9 < k && n9 < for1) {
                if (n9 >= 0) {
                    if (this.ah[0] < this.aa[0]) {
                        this.a(n9, this.ah, this.aa);
                    }
                    else {
                        this.a(n9, this.aa, this.ah);
                    }
                }
                ++n9;
                final float[] ah = this.ah;
                final int n14 = 0;
                ah[n14] += this.L[0];
                final float[] aa = this.aa;
                final int n15 = 0;
                aa[n15] += this.I[0];
                final float[] ah2 = this.ah;
                final int n16 = 1;
                ah2[n16] += this.L[1];
                final float[] aa2 = this.aa;
                final int n17 = 1;
                aa2[n17] += this.I[1];
                final float[] ah3 = this.ah;
                final int n18 = 2;
                ah3[n18] += this.L[2];
                final float[] aa3 = this.aa;
                final int n19 = 2;
                aa3[n19] += this.I[2];
            }
            if (n9 >= for1) {
                break;
            }
        }
    }
    
    private void i() {
        if (this.aU == null) {
            this.aU = new Rectangle(0, 0, this.bounds().width, this.bounds().height - 24);
        }
        if (this.n != null) {
            return;
        }
        if (this.H == null) {
            this.a("Could not load the FlashPix image.", "Verify that the URL is correct in the html file.");
        }
        try {
            this.n = Toolkit.getDefaultToolkit().getImage(new URL(this.m, this.H));
        }
        catch (Exception ex) {
            this.n = null;
        }
        if (this.n != null) {
            (this.j = new MediaTracker(this)).addImage(this.n, 0);
            this.U = true;
        }
    }
    
    public float f() {
        return this.av;
    }
    
    public void if(final float[] array) {
        array[0] = this.X;
        array[1] = this.O;
        array[2] = this.ab;
    }
    
    public float g() {
        return this.e;
    }
    
    private void long() {
        if (this.n == null) {
            return;
        }
        try {
            this.aF = this.n.getWidth(this);
            this.an = this.n.getHeight(this);
            if (this.C == null && this.aF > 0 && this.an > 0) {
                this.C = new int[this.aF * this.an];
            }
        }
        catch (Exception ex) {
            this.C = null;
            this.a("This player only supports", null);
        }
        catch (OutOfMemoryError outOfMemoryError) {
            this.a("This player only supports", null);
        }
        if (this.C != null) {
            try {
                new PixelGrabber(this.n, 0, 0, this.aF, this.an, this.C, 0, this.aF).grabPixels();
            }
            catch (InterruptedException ex2) {}
            catch (OutOfMemoryError outOfMemoryError2) {
                this.a("This player only supports", null);
            }
        }
    }
    
    public void char() {
        this.if = false;
        if (this.for != null) {
            this.for.if(1, 4);
        }
        this.new = true;
    }
    
    private void if(final int n) {
        this.aP = true;
        final float[] array = new float[3];
        final float n2 = 0.3f;
        final float n3 = 0.3f;
        float n5;
        float n4 = n5 = 0.0f;
        final float g = this.g();
        this.if(array);
        if (this.ap) {
            final float n6 = this.ao - this.ad;
            final float n7 = this.ac - this.am;
            final float n8 = n6 / this.Z;
            final float n9 = n7 / this.E;
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
            array2[n12] += n11 * n3 * g;
            final float[] array3 = array;
            final int n13 = 1;
            array3[n13] += n10 * n2 * g;
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
            array4[n14] += n4 * g;
            final float[] array5 = array;
            final int n15 = 1;
            array5[n15] += n5 * g;
        }
        array[2] = 0.0f;
        this.a(array);
        this.new = true;
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
        this.A[n] = null;
        for (int i = 63; i >= 0; --i) {
            if (this.A[i] != null) {
                return;
            }
        }
        if (this.for != null) {
            this.for.a(1);
        }
    }
    
    public void byte() {
        final float[] array = { this.v, this.Y, 0.0f };
        this.a(this.J);
        this.a(array);
        this.a(this.J);
        this.aP = (this.p == 0.0f);
        if (this.for != null) {
            this.for.new();
        }
        this.new = true;
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
        if (n5 >= this.F.a) {
            n5 = this.F.a - 1;
        }
        float n6 = array2[0] - array[0];
        if (n6 == 0.0f) {
            n6 = 1.0f;
        }
        final float n7 = n3 + 0.5f - array[0];
        final float n8 = 1.0f / n6;
        this.aj[1] = (array2[1] - array[1]) * n8;
        this.aj[2] = (array2[2] - array[2]) * n8;
        this.l[1] = array[1] + this.aj[1] * n7;
        this.l[2] = array[2] + this.aj[2] * n7;
        int n9 = (int)(this.l[1] * 65536.0f);
        int n10 = (int)(this.l[2] * 65536.0f);
        final int n11 = (int)(this.aj[1] * 65536.0f);
        final int n12 = (int)(this.aj[2] * 65536.0f);
        final int n13 = 1 + n5 - n3;
        final int n14 = n * this.F.a + n3;
        for (int n15 = n13 + n14, i = n14; i < n15; ++i) {
            final int n16 = n9 >> 16;
            final int n17 = (n10 >> 16) * this.aF;
            if (this.aN && n16 < this.aF - 1 && n10 >> 16 < this.aF - 1) {
                final int n18 = n9 & 0xFFFF;
                final int[] c = this.C;
                this.F.do[i] = if(if(c[n16 + n17], c[n16 + n17 + 1], n18), if(c[n16 + n17 + this.aF], c[n16 + n17 + this.aF + 1], n18), n10 & 0xFFFF);
            }
            else {
                this.F.do[i] = this.C[(n10 >> 16) * this.aF + (n9 >> 16)];
            }
            n9 += n11;
            n10 += n12;
        }
    }
    
    public void a(final float v, final float y, final float ag, final float j) {
        this.v = v;
        this.Y = y;
        this.aG = ag;
        this.J = j;
    }
    
    public void a(final float av, final boolean b) {
        this.av = av;
        if (b) {
            this.a(this.g());
        }
        this.new = true;
    }
    
    public void a(final float[] array) {
        this.X = array[0];
        this.O = array[1];
        this.ab = array[2];
        final float n = this.e * 0.5f;
        if (Math.abs(this.ax - this.i) > 0.001f) {
            this.O = this.a(this.O, this.i, this.ax);
        }
        if (this.X > 1.5707964f) {
            this.X = 1.5707964f;
        }
        if (this.X < -1.5707964f) {
            this.X = -1.5707964f;
        }
        if (this.d - this.aw > 0.001f) {
            final float n2 = n * (this.Z / this.E);
            if (this.O + n2 > this.d - 0.1f) {
                this.O = this.d - n2 - 0.1f;
            }
            if (this.O - n2 < this.aw + 0.1f) {
                this.O = this.aw + n2 + 0.1f;
            }
            if (this.O > this.d || this.O < this.aw) {
                this.O = (this.d - this.aw) / 2.0f;
            }
        }
        while (this.O < 0.0f) {
            this.O += 6.2831855f;
        }
        while (this.O > 6.2831855f) {
            this.O -= 6.2831855f;
        }
        if (this.aH < 1.5707964f && this.X + n > this.aH - 1.0E-4f) {
            this.X = this.aH - n - 1.0E-4f;
        }
        if (this.N > -1.5707964f && this.X - n < this.N + 1.0E-4f) {
            this.X = this.N + n + 1.0E-4f;
        }
        this.al = (float)Math.cos(this.X);
        this.aq = (float)Math.sin(this.X);
    }
    
    public void a(final String s, final int aa, final float w, final float t, float aw, float d, final float n, final float ah, final float i, final float ax, final float p12, final boolean if1) {
        this.H = s;
        this.C = null;
        this.H = s;
        this.i();
        this.aA = aa;
        this.p = p12;
        if (this.p == 0.0f) {
            this.aP = true;
        }
        else {
            this.aP = false;
        }
        this.if = if1;
        this.w = w;
        this.t = t;
        this.aw = aw;
        this.d = d;
        this.N = n;
        this.aH = ah;
        this.i = i;
        this.ax = ax;
        if (pa.a.a.a.c.a.case && !this.do()) {
            this.a("You need the MRJ2.2", "Get it from following URL");
            this.n = null;
            return;
        }
        if (this.aA == 1) {
            this.w = this.a(this.w, -1.4137167f, 1.4137167f);
        }
        else if (this.aA == 2) {
            this.w = -1.5707964f;
            this.t = 1.5707964f;
            final float n2 = 0.0f;
            this.d = n2;
            this.aw = n2;
        }
        else {
            this.aA = 0;
        }
        if (this.aA == 0) {
            this.aK = 17;
            this.aJ = 33;
            this.aO = new float[this.aK][this.aJ][8];
            for (int j = 0; j < this.aK; ++j) {
                final float n3 = 1.5707964f - (this.t + (this.w - this.t) * j / (this.aK - 1));
                final float n4 = (float)Math.sin(n3);
                for (int k = 0; k < this.aJ; ++k) {
                    float n5;
                    if (this.d - this.aw < 0.001f) {
                        n5 = 6.2831855f * k / (this.aJ - 1);
                    }
                    else {
                        n5 = this.aw + (this.d - this.aw) * k / (this.aJ - 1);
                    }
                    this.aO[j][k][0] = -n4 * (float)Math.sin(n5);
                    this.aO[j][k][1] = (float)Math.cos(n3);
                    this.aO[j][k][2] = n4 * (float)Math.cos(n5);
                    this.aO[j][k][3] = k / (this.aJ - 1);
                    this.aO[j][k][4] = j / (this.aK - 1);
                    this.aO[j][k][5] = 0.0f;
                    this.aO[j][k][6] = 0.0f;
                    this.aO[j][k][7] = 0.0f;
                }
            }
        }
        else if (this.aA == 1) {
            this.aK = 17;
            this.aJ = 33;
            this.aO = new float[this.aK][this.aJ][8];
            aw = (float)Math.tan(this.w);
            d = (float)Math.tan(this.t);
            for (int l = 0; l < this.aK; ++l) {
                for (int n6 = 0; n6 < this.aJ; ++n6) {
                    float n7;
                    if (this.d - this.aw < 0.001f) {
                        n7 = 6.2831855f * n6 / (this.aJ - 1);
                    }
                    else {
                        n7 = this.aw + (this.d - this.aw) * n6 / (this.aJ - 1);
                    }
                    this.aO[l][n6][0] = -(float)Math.sin(n7);
                    this.aO[l][n6][1] = d + (aw - d) * l / (this.aK - 1);
                    this.aO[l][n6][2] = (float)Math.cos(n7);
                    this.aO[l][n6][3] = n6 / (this.aJ - 1);
                    this.aO[l][n6][4] = l / (this.aK - 1);
                    this.aO[l][n6][5] = 0.0f;
                    this.aO[l][n6][6] = 0.0f;
                    this.aO[l][n6][7] = 0.0f;
                }
            }
        }
        else if (this.aA == 2) {
            this.aK = 54;
            this.aJ = 9;
            this.aO = new float[this.aK][this.aJ][8];
            final int n8 = 0;
            final int n9 = this.aK / 6;
            for (int n10 = n8; n10 < n9; ++n10) {
                float n12;
                final float n11 = n12 = (n10 - n8) / (n9 - n8 - 1);
                if (n10 == n8) {
                    n12 += 0.01f;
                }
                if (n10 == n9 - 1) {
                    n12 -= 0.01f;
                }
                for (int n13 = 0; n13 < this.aJ; ++n13) {
                    final float n14 = n13 / (this.aJ - 1);
                    this.aO[n10][n13][0] = n14 * 2.0f - 1.0f;
                    this.aO[n10][n13][1] = 1.0f - n11 * 2.0f;
                    this.aO[n10][n13][2] = -1.0f;
                    this.aO[n10][n13][3] = n14;
                    this.aO[n10][n13][4] = n12 / 6.0f;
                    this.aO[n10][n13][5] = 0.0f;
                    this.aO[n10][n13][6] = 0.0f;
                    this.aO[n10][n13][7] = 0.0f;
                }
            }
            final int n15 = n9;
            final int n16 = n9 + this.aK / 6;
            for (int n17 = n15; n17 < n16; ++n17) {
                float n19;
                final float n18 = n19 = (n17 - n15) / (n16 - n15 - 1);
                if (n17 == n15) {
                    n19 += 0.01f;
                }
                if (n17 == n16 - 1) {
                    n19 -= 0.01f;
                }
                for (int n20 = 0; n20 < this.aJ; ++n20) {
                    final float n21 = n20 / (this.aJ - 1);
                    this.aO[n17][n20][0] = 1.0f;
                    this.aO[n17][n20][1] = 1.0f - n18 * 2.0f;
                    this.aO[n17][n20][2] = n21 * 2.0f - 1.0f;
                    this.aO[n17][n20][3] = n21;
                    this.aO[n17][n20][4] = 0.16666667f + n19 / 6.0f;
                    this.aO[n17][n20][5] = 0.0f;
                    this.aO[n17][n20][6] = 0.0f;
                    this.aO[n17][n20][7] = 0.0f;
                }
            }
            final int n22 = n16;
            final int n23 = n16 + this.aK / 6;
            for (int n24 = n22; n24 < n23; ++n24) {
                float n26;
                final float n25 = n26 = (n24 - n22) / (n23 - n22 - 1);
                if (n24 == n22) {
                    n26 += 0.01f;
                }
                if (n24 == n23 - 1) {
                    n26 -= 0.01f;
                }
                for (int n27 = 0; n27 < this.aJ; ++n27) {
                    final float n28 = n27 / (this.aJ - 1);
                    this.aO[n24][n27][0] = 1.0f - n28 * 2.0f;
                    this.aO[n24][n27][1] = 1.0f - n25 * 2.0f;
                    this.aO[n24][n27][2] = 1.0f;
                    this.aO[n24][n27][3] = n28;
                    this.aO[n24][n27][4] = 0.33333334f + n26 / 6.0f;
                    this.aO[n24][n27][5] = 0.0f;
                    this.aO[n24][n27][6] = 0.0f;
                    this.aO[n24][n27][7] = 0.0f;
                }
            }
            final int n29 = n23;
            final int n30 = n23 + this.aK / 6;
            for (int n31 = n29; n31 < n30; ++n31) {
                float n33;
                final float n32 = n33 = (n31 - n29) / (n30 - n29 - 1);
                if (n31 == n29) {
                    n33 += 0.01f;
                }
                if (n31 == n30 - 1) {
                    n33 -= 0.01f;
                }
                for (int n34 = 0; n34 < this.aJ; ++n34) {
                    final float n35 = n34 / (this.aJ - 1);
                    this.aO[n31][n34][0] = -1.0f;
                    this.aO[n31][n34][1] = 1.0f - n32 * 2.0f;
                    this.aO[n31][n34][2] = 1.0f - n35 * 2.0f;
                    this.aO[n31][n34][3] = n35;
                    this.aO[n31][n34][4] = 0.5f + n33 / 6.0f;
                    this.aO[n31][n34][5] = 0.0f;
                    this.aO[n31][n34][6] = 0.0f;
                    this.aO[n31][n34][7] = 0.0f;
                }
            }
            final int n36 = n30;
            final int n37 = n30 + this.aK / 6;
            for (int n38 = n36; n38 < n37; ++n38) {
                float n40;
                final float n39 = n40 = (n38 - n36) / (n37 - n36 - 1);
                if (n38 == n36) {
                    n40 += 0.01f;
                }
                if (n38 == n37 - 1) {
                    n40 -= 0.01f;
                }
                for (int n41 = 0; n41 < this.aJ; ++n41) {
                    final float n42 = n41 / (this.aJ - 1);
                    this.aO[n38][n41][0] = n42 * 2.0f - 1.0f;
                    this.aO[n38][n41][1] = 1.0f;
                    this.aO[n38][n41][2] = 1.0f - n39 * 2.0f;
                    this.aO[n38][n41][3] = n42;
                    this.aO[n38][n41][4] = 0.6666667f + n40 / 6.0f;
                    this.aO[n38][n41][5] = 0.0f;
                    this.aO[n38][n41][6] = 0.0f;
                    this.aO[n38][n41][7] = 0.0f;
                }
            }
            final int n43 = n37;
            for (int n44 = n37 + this.aK / 6, n45 = n43; n45 < n44; ++n45) {
                float n47;
                final float n46 = n47 = (n45 - n43) / (n44 - n43 - 1);
                if (n45 == n43) {
                    n47 += 0.01f;
                }
                if (n45 == n44 - 1) {
                    n47 -= 0.01f;
                }
                for (int n48 = 0; n48 < this.aJ; ++n48) {
                    final float n49 = n48 / (this.aJ - 1);
                    this.aO[n45][n48][0] = n49 * 2.0f - 1.0f;
                    this.aO[n45][n48][1] = -1.0f;
                    this.aO[n45][n48][2] = n46 * 2.0f - 1.0f;
                    this.aO[n45][n48][3] = n49;
                    this.aO[n45][n48][4] = 0.8333333f + n47 / 6.0f;
                    this.aO[n45][n48][5] = 0.0f;
                    this.aO[n45][n48][6] = 0.0f;
                    this.aO[n45][n48][7] = 0.0f;
                }
            }
        }
    }
    
    public void a(final float e) {
        this.e = e;
        if (this.e < this.av) {
            this.e = this.av;
        }
        if (this.e > 2.094f) {
            this.e = 2.094f;
        }
        if (this.e > this.t - this.w - 1.0E-4f) {
            this.e = this.t - this.w - 1.0E-4f;
        }
        if (this.e > this.aH - this.N - 1.0E-4f) {
            this.e = this.aH - this.N - 1.0E-4f;
        }
        float n = this.e * this.Z / this.E;
        if (n > 2.094f) {
            n = 2.094f;
            this.e = n * this.E / this.Z;
        }
        if (this.d - this.aw > 0.05f && n > this.d - this.aw - 0.05f) {
            n = this.d - this.aw - 0.05f;
            this.e = n * this.E / this.Z;
        }
        if (this.ax - this.i > 0.05f && n > this.ax - this.i - 0.05f) {
            this.e = (this.ax - this.i - 0.05f) * this.E / this.Z;
        }
    }
    
    public void if() {
        this.if = true;
        if (this.for != null) {
            this.for.if(1, 3);
        }
        this.new = true;
    }
    
    public void goto() {
        try {
            super.b.showDocument(new URL(pa.a.a.a.a.c.aM), "mgiZoom");
        }
        catch (MalformedURLException ex) {}
    }
    
    private void a(final float n, final float n2, final float[] array) {
        final float[] array2 = new float[2];
        final float[] array3 = new float[2];
        this.if(this.aw, this.w, array2);
        if (this.d - this.aw < 0.001f) {
            this.if(6.2831855f, this.t, array3);
        }
        else {
            this.if(this.d, this.t, array3);
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
        if (this.aA == 0) {
            array[0] = n2;
            array[1] = n;
        }
        else if (this.aA == 1) {
            final float n3 = (float)Math.tan(this.t);
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
        else if (this.aA == 2) {
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
    
    private void a(final Graphics graphics) {
        final int width = this.bounds().width;
        final int height = this.bounds().height;
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, width, height);
        if (this.ae != null) {
            try {
                final Image image = Toolkit.getDefaultToolkit().getImage(new URL(this.m, this.ae));
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
            final InputStream a = pa.a.a.a.c.a.a("splashscreen.gif");
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
    
    private void b() {
        final float[] array = new float[2];
        this.a(this.ao, this.am, array);
        final int[] array2 = new int[2];
        final float[] array3 = new float[3];
        for (int i = 0; i < 64; ++i) {
            if (this.A[i] != null && this.A[i].for) {
                this.a((this.A[i].a + this.A[i].byte) * 0.5f, (this.A[i].if + this.A[i].new) * 0.5f, array3);
                this.a(array3[1], array3[0], array2);
                if (this.A[i].a(array[0], array[1], this.ao, this.am, array2[0], array2[1], super.b)) {
                    break;
                }
            }
        }
    }
    
    private void if(final Graphics graphics) {
        graphics.setColor(this.Q);
        if (this.aV == null) {
            graphics.fillRect(0, 0, this.size().width, this.aU.y);
            graphics.fillRect(0, 0, this.aU.x, this.size().height);
            graphics.fillRect(this.aU.x + this.aU.width, 0, this.size().width - (this.aU.x + this.aU.width), this.size().height);
            graphics.fillRect(0, this.aU.y + this.aU.height, this.size().width, this.size().height - (this.aU.y + this.aU.height));
        }
    }
    
    private void do(final Graphics graphics) {
        if (this.aV != null && (this.aU.width != this.size().width || this.aU.height != this.size().height)) {
            final int width = this.bounds().width;
            final int height = this.bounds().height;
            if (this.aI == null && this.a(this.aV, this)) {
                this.aI = this.createImage(width, height - (this.aU.y + this.aU.height));
                final Graphics graphics2 = this.aI.getGraphics();
                graphics2.setColor(this.Q);
                graphics2.fillRect(0, 0, width, height - (this.aU.y + this.aU.height));
                graphics2.drawImage(this.aV, 0, -(this.aU.y + this.aU.height), width, height, this);
                graphics2.dispose();
                this.aS = this.createImage(width, this.aU.y);
                final Graphics graphics3 = this.aS.getGraphics();
                graphics3.setColor(this.Q);
                graphics3.fillRect(0, 0, width, this.aU.y);
                graphics3.drawImage(this.aV, 0, 0, width, height, this);
                graphics3.dispose();
                this.D = this.createImage(this.aU.x, height);
                final Graphics graphics4 = this.D.getGraphics();
                graphics4.setColor(this.Q);
                graphics4.fillRect(0, 0, this.aU.x, height);
                graphics4.drawImage(this.aV, 0, 0, width, height, this);
                graphics4.dispose();
                this.h = this.createImage(width - (this.aU.x + this.aU.width), height);
                final Graphics graphics5 = this.h.getGraphics();
                graphics5.setColor(this.Q);
                graphics5.fillRect(0, 0, width - (this.aU.x + this.aU.width), height);
                graphics5.drawImage(this.aV, -(this.aU.x + this.aU.width), 0, width, height, this);
                graphics5.dispose();
                this.aV.flush();
            }
            graphics.drawImage(this.aS, 0, 0, this);
            graphics.drawImage(this.D, 0, 0, this);
            graphics.drawImage(this.aI, 0, this.aU.y + this.aU.height, this);
            graphics.drawImage(this.h, this.aU.x + this.aU.width, 0, this);
        }
    }
    
    private void e() {
        final int[] array = new int[2];
        final float[] array2 = new float[3];
        for (int i = 0; i < 64; ++i) {
            if (this.A[i] != null && this.A[i].for && (Math.abs(this.A[i].a - this.A[i].byte) <= 0.95f || Math.abs(this.A[i].if - this.A[i].new) <= 0.95f)) {
                this.a((this.A[i].a + this.A[i].byte) * 0.5f, (this.A[i].if + this.A[i].new) * 0.5f, array2);
                if (Math.abs(array2[0] - this.X) <= 1.22f && (Math.abs(array2[1] - this.O) <= 1.22f || Math.abs(array2[1] - this.O) >= 5.0631857f)) {
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
                    if (n4 >= this.F.a) {
                        n4 = this.F.a - 1;
                    }
                    if (n6 >= this.F.for) {
                        n6 = this.F.for - 1;
                    }
                    final int n7 = n3 - n;
                    final int n8 = n4 - n;
                    final int n9 = n5 - n2;
                    for (int n10 = n6 - n2, j = n9; j <= n10; ++j) {
                        for (int k = n7; k <= n8; ++k) {
                            final int n11 = k * k + j * j;
                            if ((n11 <= 144 && n11 >= 70) || (n11 <= 36 && n11 >= 6)) {
                                this.F.do[(n2 + j) * this.F.a + (n + k)] = -65536;
                            }
                        }
                    }
                }
            }
        }
    }
    
    private synchronized boolean d() {
        if (this.F == null || this.C == null) {
            return false;
        }
        if (this.F.for <= 0 || this.F.a <= 0) {
            return false;
        }
        final float n = 1.0f / (float)Math.tan(this.e * 0.5f);
        final float n2 = n / (this.Z / this.E);
        final float n3 = (float)Math.cos(this.O);
        final float n4 = (float)Math.sin(this.O);
        final float n5 = (float)Math.cos(this.X);
        final float n6 = (float)Math.sin(this.X);
        final float n7 = (float)Math.cos(this.ab);
        final float n8 = (float)Math.sin(this.ab);
        for (int i = 0; i < this.aK; ++i) {
            for (int j = 0; j < this.aJ; ++j) {
                this.aO[i][j][5] = -(n3 * this.aO[i][j][0] + n4 * this.aO[i][j][2]);
                this.aO[i][j][6] = this.aO[i][j][1];
                this.aO[i][j][7] = n3 * this.aO[i][j][2] - n4 * this.aO[i][j][0];
                final float n9 = this.aO[i][j][6];
                final float n10 = this.aO[i][j][7];
                this.aO[i][j][6] = n5 * n9 - n6 * n10;
                this.aO[i][j][7] = n5 * n10 + n6 * n9;
                final float n11 = this.aO[i][j][5];
                final float n12 = this.aO[i][j][6];
                this.aO[i][j][5] = n7 * n11 - n8 * n12;
                this.aO[i][j][6] = n7 * n12 + n8 * n11;
                if (this.aO[i][j][7] > 0.1f) {
                    this.aO[i][j][5] = this.aO[i][j][5] * n2 / this.aO[i][j][7];
                    this.aO[i][j][6] = this.aO[i][j][6] * n / this.aO[i][j][7];
                }
            }
        }
        for (int k = 0; k < this.aK - 1; ++k) {
            if (this.aA != 2 || (k + 1) % this.aJ != 0) {
                for (int l = 0; l < this.aJ - 1; ++l) {
                    if (this.aO[k][l][7] >= 0.1f && this.aO[k][l + 1][7] >= 0.1f && this.aO[k + 1][l][7] >= 0.1f) {
                        if (this.aO[k + 1][l + 1][7] >= 0.1f) {
                            this.aR.a = 4;
                            this.aR.if[0][0] = this.aO[k][l][5];
                            this.aR.if[0][1] = this.aO[k][l][6];
                            this.aR.if[0][2] = this.aO[k][l][3];
                            this.aR.if[0][3] = this.aO[k][l][4];
                            this.aR.if[1][0] = this.aO[k][l + 1][5];
                            this.aR.if[1][1] = this.aO[k][l + 1][6];
                            this.aR.if[1][2] = this.aO[k][l + 1][3];
                            this.aR.if[1][3] = this.aO[k][l + 1][4];
                            this.aR.if[2][0] = this.aO[k + 1][l + 1][5];
                            this.aR.if[2][1] = this.aO[k + 1][l + 1][6];
                            this.aR.if[2][2] = this.aO[k + 1][l + 1][3];
                            this.aR.if[2][3] = this.aO[k + 1][l + 1][4];
                            this.aR.if[3][0] = this.aO[k + 1][l][5];
                            this.aR.if[3][1] = this.aO[k + 1][l][6];
                            this.aR.if[3][2] = this.aO[k + 1][l][3];
                            this.aR.if[3][3] = this.aO[k + 1][l][4];
                            this.a(this.aR);
                        }
                    }
                }
            }
        }
        return true;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.shiftDown()) {
            this.a(0);
        }
        else if (event.controlDown()) {
            this.a(1);
        }
        else if (this.for != null) {
            this.a(this.for.do());
        }
        else {
            this.a(0);
        }
        return super.handleEvent(event);
    }
    
    public boolean keyDown(final Event event, final int n) {
        this.aP = true;
        switch ((char)n) {
            case '\n': {
                if (this.aU.inside(this.ao, this.am) && this.if) {
                    final float[] array = new float[2];
                    this.a(this.ao, this.am, array);
                    final int[] array2 = new int[2];
                    final float[] array3 = new float[3];
                    for (int i = 0; i < 64; ++i) {
                        if (this.A[i] != null) {
                            this.a((this.A[i].a + this.A[i].byte) * 0.5f, (this.A[i].if + this.A[i].new) * 0.5f, array3);
                            this.a(array3[1], array3[0], array2);
                            if (this.A[i].a(array[0], array[1], this.ao, this.am, array2[0], array2[1], super.b, this.m)) {
                                break;
                            }
                        }
                    }
                }
                return true;
            }
            case 'H':
            case 'h': {
                this.if ^= true;
                if (this.for != null) {
                    this.for.if(1, this.if ? 3 : 4);
                }
                return this.new = true;
            }
            case 'I':
            case 'i': {
                this.goto();
                return true;
            }
            case 'V':
            case 'v': {
                this.byte();
                return true;
            }
            case '\u03ee': {
                this.if(0);
                return this.at = true;
            }
            case '\u03ef': {
                this.if(1);
                return this.at = true;
            }
            case '\u03ec': {
                this.if(2);
                return this.at = true;
            }
            case '\u03ed': {
                this.if(3);
                return this.at = true;
            }
            case 'A':
            case 'a':
            case '\u03ea': {
                this.h();
                return this.at = true;
            }
            case 'Z':
            case 'z':
            case '\u03eb': {
                this.c();
                return this.at = true;
            }
            default: {
                if (event.shiftDown()) {
                    this.a(0);
                }
                else if (event.controlDown()) {
                    this.a(1);
                }
                else if (this.for != null) {
                    this.a(this.for.do());
                }
                else {
                    this.a(3);
                }
                return false;
            }
        }
    }
    
    public boolean keyUp(final Event event, final int n) {
        this.at = false;
        this.new = true;
        return false;
    }
    
    public boolean mouseDown(final Event event, final int ad, final int ac) {
        this.aP = true;
        this.ap = true;
        this.T = ad;
        this.ao = ad;
        this.ad = ad;
        this.S = ac;
        this.am = ac;
        this.ac = ac;
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int ao, final int am) {
        if (this.ap) {
            this.ao = ao;
            this.am = am;
        }
        return this.new = true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        if (pa.a.a.a.c.a.case && !pa.a.a.a.c.a.for) {
            this.ap = false;
        }
        return this.new = true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        this.ao = n;
        this.ad = n;
        this.am = n2;
        this.ac = n2;
        if (this.if) {
            this.new = true;
        }
        return true;
    }
    
    public boolean mouseUp(final Event event, final int ao, final int am) {
        this.ap = false;
        this.ao = ao;
        this.am = am;
        if (Math.abs(this.ao - this.T) < 3 && Math.abs(this.am - this.S) < 3) {
            if (!this.aU.inside(ao, am)) {
                return true;
            }
            if (this.if) {
                final float[] array = new float[2];
                this.a(this.ao, this.am, array);
                final int[] array2 = new int[2];
                final float[] array3 = new float[3];
                for (int i = 0; i < 64; ++i) {
                    if (this.A[i] != null) {
                        this.a((this.A[i].a + this.A[i].byte) * 0.5f, (this.A[i].if + this.A[i].new) * 0.5f, array3);
                        this.a(array3[1], array3[0], array2);
                        if (this.A[i].a(array[0], array[1], this.ao, this.am, array2[0], array2[1], super.b, this.m)) {
                            return true;
                        }
                    }
                }
            }
            if (this.ag == 0) {
                this.h();
            }
            else if (this.ag == 1) {
                this.c();
            }
        }
        return this.new = true;
    }
    
    public void paint(final Graphics graphics) {
        if (this.M) {
            this.M = false;
            this.a(graphics);
        }
        this.new = true;
    }
    
    protected boolean a() {
        if (!this.aP) {
            final float[] array = new float[3];
            this.if(array);
            final float n = array[1];
            if (this.as) {
                final float[] array2 = array;
                final int n2 = 1;
                array2[n2] += this.aL;
            }
            else {
                final float[] array3 = array;
                final int n3 = 1;
                array3[n3] -= this.aL;
            }
            array[0] = this.v;
            array[2] = this.aG;
            this.a(this.J);
            try {
                this.void();
            }
            catch (Exception ex) {}
            this.a(array);
            this.if(array);
            if (Math.abs(array[1] - n) < 1.0E-4f) {
                this.as ^= true;
            }
            try {
                Thread.sleep(20L);
            }
            catch (InterruptedException ex2) {
                this.null();
            }
            return true;
        }
        if (this.ap) {
            if (this.ag == 0) {
                this.h();
            }
            else if (this.ag == 1) {
                this.c();
            }
            this.if(4);
        }
        if (this.new) {
            this.new = false;
            this.void();
            return true;
        }
        return false;
    }
    
    private void a(final String w, final String s) {
        if (this.W != null) {
            return;
        }
        this.W = w;
        this.s = s;
        this.new = true;
    }
    
    public void void() {
        if (this.aU == null) {
            return;
        }
        if (this.W != null) {
            final Graphics graphics = this.getGraphics();
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            graphics.setColor(Color.white);
            graphics.fillRect(this.aU.x, this.aU.y, this.aU.width, this.aU.height);
            graphics.setColor(Color.black);
            graphics.drawString(this.W, this.aU.x + (this.aU.width >> 1) - (fontMetrics.stringWidth(this.W) >> 1), this.aU.y + (this.aU.height >> 1));
            if (this.s != null) {
                graphics.drawString(this.s, this.aU.x + (this.aU.width >> 1) - (fontMetrics.stringWidth(this.s) >> 1), this.aU.y + (this.aU.height >> 1) + fontMetrics.getHeight());
            }
            if (pa.a.a.a.c.a.case && !this.do()) {
                final String s = "http://register.mgisoft.com/viewers/";
                final String s2 = "menu.asp?product=mac_mrj";
                graphics.setColor(Color.blue);
                graphics.drawString(s, this.aU.x + (this.aU.width >> 1) - (fontMetrics.stringWidth(s) >> 1), this.aU.y + (this.aU.height >> 1) + 2 * fontMetrics.getHeight());
                graphics.drawString(s2, this.aU.x + (this.aU.width >> 1) - (fontMetrics.stringWidth(s2) >> 1), this.aU.y + (this.aU.height >> 1) + 3 * fontMetrics.getHeight());
            }
            graphics.dispose();
            return;
        }
        if ((this.q && this.aE == null) || (!pa.a.a.a.c.a.for && pa.a.a.a.c.a.a && this.aE == null)) {
            this.aE = this.createImage(this.size().width, this.size().height);
        }
        if (this.F == null) {
            try {
                this.F = new b(this.aU.width, this.aU.height);
            }
            catch (Exception ex) {
                this.a("This player only supports", null);
                return;
            }
            this.Z = this.aU.width * 0.5f;
            this.E = this.aU.height * 0.5f;
            this.k = this.aU.width;
            this.g = this.aU.height;
        }
        if (this.U && this.j != null) {
            if ((this.j.statusAll(true) & 0x8) != 0x0) {
                this.long();
                this.U = false;
                this.j = null;
                this.n.flush();
                this.n = null;
                System.gc();
            }
            else {
                if ((this.j.statusAll(true) & 0x4) != 0x0) {
                    this.U = false;
                    this.j = null;
                    this.n.flush();
                    this.n = null;
                    System.gc();
                    this.a("Could not load the FlashPix image.", "Verify that the URL is correct in the html file.");
                    return;
                }
                this.long();
                this.new = true;
            }
        }
        if (this.C == null) {
            return;
        }
        try {
            if (!this.d()) {
                return;
            }
            if (this.if) {
                this.e();
                this.b();
            }
            this.F.a();
        }
        catch (Exception ex2) {}
        final Graphics graphics2 = this.getGraphics();
        Graphics graphics3;
        if (this.q || (!pa.a.a.a.c.a.for && pa.a.a.a.c.a.a)) {
            graphics3 = this.aE.getGraphics();
        }
        else {
            graphics3 = graphics2;
        }
        this.if(graphics3);
        if (this.aV != null && !this.q) {
            this.do(graphics3);
        }
        graphics3.drawImage(this.F.new, this.aU.x, this.aU.y, this.aU.width, this.aU.height, null);
        if (this.q) {
            graphics3.drawImage(this.aV, 0, 0, this.size().width, this.size().height, this);
            graphics3.dispose();
            graphics2.drawImage(this.aE, 0, 0, null);
        }
        else if (!pa.a.a.a.c.a.for && pa.a.a.a.c.a.a) {
            graphics3.dispose();
            graphics2.drawImage(this.aE, 0, 0, null);
        }
        graphics2.dispose();
    }
    
    protected void for() {
        this.aP = (this.p == 0.0f);
        if (this.for == null) {
            (this.for = new pa.a.a.a.d.a(this, this.bounds().height, super.b, this.z, this.m, this.f, false, true)).a(1);
            for (int i = 63; i >= 0; --i) {
                if (this.A[i] != null) {
                    this.for.if(1);
                    break;
                }
            }
            if (this.if) {
                this.for.if(1, 3);
            }
        }
        this.requestFocus();
        this.as = (this.p < 0.0f);
        this.aL = 0.0f;
        if (!this.aP) {
            if (this.d == 0.0f && this.aw == 0.0f) {
                this.aL = 6.2831855f / (Math.abs(this.p) * 15.0f);
            }
            else {
                this.aL = (this.d - this.aw) / (Math.abs(this.p) * 15.0f);
            }
        }
    }
    
    protected void int() {
        super.b = null;
        this.n = null;
        this.removeAll();
        this.aV = null;
        this.D = null;
        this.aS = null;
        this.h = null;
        this.aI = null;
        this.aE = null;
        this.C = null;
        this.F = null;
    }
    
    public void a(final int ag) {
        this.ag = ag;
        if (this.aD == null) {
            return;
        }
        switch (this.ag) {
            case 0: {
                this.aD.setCursor(1);
                break;
            }
            case 1: {
                this.aD.setCursor(1);
                break;
            }
            case 3: {
                this.aD.setCursor(0);
                break;
            }
            default: {
                this.aD.setCursor(0);
                break;
            }
        }
    }
    
    public void null() {
        this.aP = true;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
