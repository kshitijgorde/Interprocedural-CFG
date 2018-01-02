import java.awt.Event;
import java.awt.image.ImageProducer;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.image.PixelGrabber;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Graphics;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.MemoryImageSource;
import java.awt.image.ImageObserver;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class titlewater extends Applet implements Runnable, ImageObserver
{
    anfy a;
    MemoryImageSource b;
    boolean c;
    Toolkit d;
    int e;
    long f;
    Thread g;
    int h;
    int i;
    int j;
    int k;
    int l;
    int m;
    int[] n;
    int[] o;
    int p;
    int q;
    int r;
    int s;
    private Image t;
    int u;
    int v;
    Frame w;
    float[] x;
    float[] y;
    float z;
    int A;
    int B;
    int C;
    int D;
    int E;
    double F;
    double G;
    int H;
    int I;
    float J;
    int K;
    int L;
    int M;
    int N;
    int O;
    int P;
    int Q;
    int R;
    boolean S;
    int T;
    int U;
    boolean V;
    int W;
    int X;
    int Y;
    int Z;
    short[] ba;
    short[] bb;
    short[] bc;
    int bd;
    int be;
    int bf;
    int bg;
    int bh;
    int bi;
    int bj;
    int bk;
    int bl;
    int bm;
    int bn;
    int bo;
    int bp;
    int bq;
    private Image br;
    private Graphics bs;
    private Image bt;
    private Graphics bu;
    int bv;
    int bw;
    int bx;
    public static int by;
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        return true;
    }
    
    Image a() {
        final Image image = this.getImage(this.getCodeBase(), a(")C_\u0017+-LBZ>!O"));
        final MediaTracker mediaTracker = new MediaTracker(this);
        try {
            mediaTracker.addImage(image, 0);
            mediaTracker.waitForID(0);
        }
        catch (InterruptedException ex) {}
        int n = 0;
        if (image.getWidth(this) < 0 && n < 20) {
            this.notifyAll();
            ++n;
            try {
                Thread.currentThread();
                Thread.sleep(200L);
            }
            catch (InterruptedException ex2) {}
        }
        return image;
    }
    
    public void init() {
        final int by = titlewater.by;
        this.d = this.getToolkit();
        this.e = 20;
        this.i = 182;
        this.j = 150;
        this.m = 1;
        this.J = 400.0f;
        this.bv = -20;
        this.bw = 120;
        this.k = this.i;
        this.l = this.j;
        this.J = 10.0f;
        this.L = 0;
        this.M = 0;
        this.P = 0;
        this.br = this.a();
        this.z = 32.0f;
        this.Z = this.i * this.j;
        final int n = this.i + 1;
        final int n2 = this.Z - n;
        final int n3 = this.Z - 1;
        this.x = new float[this.i * (this.j + 2) + n + 1];
        this.y = new float[this.i * (this.j + 2) + n + 1];
        this.n = new int[this.i * this.j];
        this.o = new int[this.i * this.j + 2];
        final PixelGrabber pixelGrabber = new PixelGrabber(this.br, 0, 0, this.i, this.j, this.n, 0, this.i);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {}
        this.ba = new short[this.i * this.j + 2];
        this.bb = new short[this.i * this.j + 2];
        this.bc = new short[this.i * this.j + 2];
        this.K = 0;
    Label_0319_Outer:
        while (true) {
            while (true) {
                Label_0393: {
                    if (by == 0) {
                        break Label_0393;
                    }
                    final titlewater titlewater = this;
                    final int n4 = titlewater.n[this.K];
                    this.ba[this.K] = (short)(n4 >> 16 & 0xFF);
                    this.bb[this.K] = (short)(n4 >> 8 & 0xFF);
                    this.bc[this.K] = (short)(n4 & 0xFF);
                    ++this.K;
                }
                if (this.K >= this.Z) {
                    try {
                        final titlewater titlewater = this;
                        if (by != 0) {
                            continue;
                        }
                        this.b();
                    }
                    catch (NoSuchMethodError noSuchMethodError) {
                        this.b();
                    }
                    this.bt = this.createImage(this.k, this.l);
                    this.bu = this.bt.getGraphics();
                    return;
                }
                break;
            }
            continue Label_0319_Outer;
        }
    }
    
    void b() {
        this.b = new MemoryImageSource(this.i, this.j, new DirectColorModel(24, 16711680, 65280, 255), this.o, 0, this.i);
        String s;
        try {
            s = System.getProperty(a("\"HZ\u0015w>L^\u00070'G"));
        }
        catch (SecurityException ex) {
            s = a("=GG");
        }
        if (!s.startsWith(a("y\u0007\u001c"))) {
            try {
                this.b.setAnimated(true);
                this.b.setFullBufferUpdates(true);
                this.br = this.createImage(this.b);
                this.b.newPixels();
                this.c = true;
            }
            catch (NoSuchMethodError noSuchMethodError) {
                this.c = false;
            }
        }
        if (!this.c) {
            this.b = null;
            this.a = new anfy(this.i, this.j, new DirectColorModel(24, 16711680, 65280, 255), this.o, 0, this.i);
            this.br = this.createImage(this.a);
        }
    }
    
    public void start() {
        if (this.g == null) {
            (this.g = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.g != null && this.g.isAlive()) {
            this.g.stop();
        }
        this.g = null;
    }
    
    public void run() {
        this.g.setPriority(3);
        System.gc();
        this.f = System.currentTimeMillis();
        final Graphics graphics = this.getGraphics();
        while (this.g != null) {
            this.Water();
            if (this.W < 50) {
                this.J = 50.0f;
                this.M = 1;
                this.O = 5;
            }
            if (this.W < 100) {
                this.J = 100.0f;
                this.M = 2;
                this.O = 5;
            }
            else if (this.W < 200) {
                this.J = 200.0f;
                this.M = 3;
                this.O = 4;
            }
            else if (this.W < 300) {
                this.J = 300.0f;
                this.M = 3;
                this.O = 5;
            }
            else if (this.W < 400) {
                this.J = 350.0f;
                this.M = 4;
                this.O = 3;
            }
            else if (this.W < 500) {
                this.J = 380.0f;
                this.M = 2;
                this.O = 20;
            }
            else if (this.W < 600) {
                this.J = 400.0f;
                this.M = 0;
                this.L = 1;
            }
            else if (this.W < 700) {
                this.L = 2;
                this.P = 2;
                this.R = 20;
            }
            else if (this.W < 800) {
                this.L = 1;
                this.P = 4;
                this.R = 10;
            }
            else if (this.W < 900) {
                this.L = 0;
                this.P = 2;
                this.R = 20;
            }
            else if (this.W < 1000) {
                this.M = 1;
            }
            else if (this.W < 1100) {
                this.L = 1;
                this.M = 2;
                this.O = 30;
                this.W = 1200;
            }
            ++this.W;
            if (this.L > 0) {
                this.a(this.L);
            }
            if (this.M > 0 && this.N++ >= this.O) {
                this.b(this.M);
                this.N = 0;
            }
            if (this.P > 0 && this.Q++ >= this.R) {
                this.c(this.P);
                this.Q = 0;
            }
            try {
                this.producefixed();
            }
            catch (NoSuchMethodError noSuchMethodError) {}
            this.bu.drawImage(this.br, 0, 0, this);
            graphics.drawImage(this.bt, 0, 0, this);
            this.waitsync();
        }
    }
    
    public final void producefixed() {
        try {
            if (this.c) {
                this.b.newPixels();
                return;
            }
            this.a.startProduction(this.a.getConsumer());
        }
        catch (NoSuchMethodError noSuchMethodError) {}
    }
    
    public synchronized void waitsync() {
        Thread.yield();
        this.d.sync();
        final long n = 10L - (System.currentTimeMillis() - this.f);
        if (n > 0L) {
            try {
                Thread.sleep(n);
            }
            catch (InterruptedException ex) {}
        }
        else {
            try {
                Thread.sleep(1L);
            }
            catch (InterruptedException ex2) {}
        }
        this.f = System.currentTimeMillis();
        try {
            Thread.sleep(this.e);
        }
        catch (InterruptedException ex3) {}
    }
    
    public final void paint(final Graphics graphics) {
        if (this.br != null) {
            this.bu.drawImage(this.br, 0, 0, this);
            graphics.drawImage(this.bt, 0, 0, this);
        }
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final void Water() {
        final int[] o = this.o;
        final int[] n = this.n;
        float[] array = this.x;
        float[] array2 = this.y;
        if (this.D == 1) {
            array = this.y;
            array2 = this.x;
        }
        final short[] ba = this.ba;
        final short[] bb = this.bb;
        final short[] bc = this.bc;
        final int i = this.i;
        final int n2 = this.i + 1;
        final int n3 = this.Z - n2;
        final int n4 = this.Z - 1;
        final int n5 = this.i * 2;
        final int bv = this.bv;
        final int bw = this.bw;
        final int n6 = i;
        for (int j = 0; j < n2; ++j) {
            final float n7 = array2[j + n6] - array2[j + n6 + 2];
            int n8 = (i * (int)((array2[j + n6] - array2[j + n5]) / 2.8f) + (int)(n7 / 13.0f) + j) % n4;
            if (n8 < 0) {
                n8 = -n8;
            }
            int n9 = (int)(n7 / 1.7f) << 1;
            if (n9 < bv) {
                n9 = bv;
            }
            else if (n9 > bw) {
                n9 = bw;
            }
            int n10 = ba[n8] + n9;
            int n11 = bb[n8] + n9;
            int n12 = bc[n8] + n9;
            if (n10 > 255) {
                n10 = 255;
            }
            else if (n10 < 0) {
                n10 = 0;
            }
            if (n11 > 255) {
                n11 = 255;
            }
            else if (n11 < 0) {
                n11 = 0;
            }
            if (n12 > 255) {
                n12 = 255;
            }
            else if (n12 < 0) {
                n12 = 0;
            }
            o[j] = (n10 << 16 | n11 << 8 | n12);
            array[j] = 0.0f;
        }
        for (int k = n2; k < n4; ++k) {
            final float n13 = array2[k] - array2[k + 2];
            int n14 = (i * (int)((array2[k] - array2[k + n5]) / 8.0f) + (int)(n13 / 8.0f) + k) % n4;
            if (n14 < 0) {
                n14 = -n14;
            }
            int n15 = (int)n13 << 1;
            if (n15 < bv) {
                n15 = bv;
            }
            else if (n15 > bw) {
                n15 = bw;
            }
            int n16 = ba[n14] + n15;
            int n17 = bb[n14] + n15;
            int n18 = bc[n14] + n15;
            if (n16 > 255) {
                n16 = 255;
            }
            else if (n16 < 0) {
                n16 = 0;
            }
            if (n17 > 255) {
                n17 = 255;
            }
            else if (n17 < 0) {
                n17 = 0;
            }
            if (n18 > 255) {
                n18 = 255;
            }
            else if (n18 < 0) {
                n18 = 0;
            }
            o[k] = (n16 << 16 | n17 << 8 | n18);
            array[k] = (array2[k + i] + array2[k - i] + array2[k + 1] + array2[k - 1]) / 2.0f - array[k];
            final float[] array3 = array;
            final int n19 = k;
            array3[n19] -= array[k] / this.z;
        }
        o[n4] = n[n4];
        for (int l = n3 + i; l < this.Z; ++l) {
            array[l + i] = 0.0f;
            array[l] /= 8.0f;
        }
        for (int n20 = 0; n20 < n2; ++n20) {
            array2[n20] = 0.0f;
        }
        for (int n21 = n2; n21 < this.Z; ++n21) {
            array2[n21] = (array[n21 + i] + array[n21 - i] + array[n21 + 1] + array[n21 - 1]) / 2.0f - array2[n21];
            final float[] array4 = array2;
            final int n22 = n21;
            array4[n22] -= array2[n21] / this.z;
        }
        for (int n23 = n3 + i; n23 < this.Z; ++n23) {
            array2[n23 + i] = 0.0f;
            array2[n23] /= 8.0f;
        }
    }
    
    final void a(final int n) {
        final float j = this.J;
        final float n2 = this.J / 2.0f;
        float[] array;
        if (this.D == 1) {
            array = this.y;
        }
        else {
            array = this.x;
        }
        final int n3 = this.i / 2;
        final int n4 = this.j / 2;
        final double n5 = this.i / 3.0;
        final double n6 = this.j / 2.5;
        final int n7 = n3 + (int)(n5 * Math.cos(this.F));
        final int n8 = n4 + (int)(n6 * Math.sin(this.F * 1.2 + 1.0));
        this.F += 0.05;
        final int n9 = this.i * n8;
        array[n9 + n7] = j;
        final float[] array2 = array;
        final int n10 = this.i * (n8 - 1) + n7;
        array2[n10] += n2;
        final float[] array3 = array;
        final int n11 = this.i * (n8 + 1) + n7;
        array3[n11] += n2;
        final float[] array4 = array;
        final int n12 = n9 + n7 + this.i;
        array4[n12] += n2;
        final float[] array5 = array;
        final int n13 = n9 + n7 - this.i;
        array5[n13] += n2;
        if (n == 1) {
            return;
        }
        final int n14 = n3 + (int)(n5 * Math.cos(this.G * 1.3 + 1.0));
        final int n15 = n4 + (int)(n6 * Math.sin(this.G));
        final int n16 = this.i * n15;
        this.G += 0.05;
        array[n16 + n14] = j;
        final float[] array6 = array;
        final int n17 = this.i * (n15 - 1) + n14;
        array6[n17] += n2;
        final float[] array7 = array;
        final int n18 = this.i * (n15 + 1) + n14;
        array7[n18] += n2;
        final float[] array8 = array;
        final int n19 = n16 + n14 + this.i;
        array8[n19] += n2;
        final float[] array9 = array;
        final int n20 = n16 + n14 - this.i;
        array9[n20] += n2;
    }
    
    final void b(int n) {
        ++n;
        final float j = this.J;
        float[] array;
        if (this.D == 1) {
            array = this.y;
        }
        else {
            array = this.x;
        }
        final float n2 = n - 1.5f;
        final int a = this.i - n - 1;
        final int b = this.j - n - 1;
        this.A = (int)(Math.random() * this.i);
        this.B = (int)(Math.random() * this.j);
        if (this.A < n) {
            this.A = n;
        }
        else if (this.A > a) {
            this.A = a;
        }
        if (this.B < n) {
            this.B = n;
        }
        else if (this.B > b) {
            this.B = b;
        }
        if (n == 2) {
            array[this.i * this.B + this.A] = (int)(Math.random() * j) % j;
            return;
        }
        this.bl = n * n;
        final float n3 = n2 * n2;
        final float n4 = this.bl - n3;
        this.bp = -n;
        while (this.bp < n) {
            this.bq = this.bp * this.bp;
            this.bo = -n;
            while (this.bo < n) {
                final int n5 = this.bo * this.bo + this.bq;
                if (n5 < this.bl) {
                    if (n5 > n3) {
                        final float n6 = (n5 - n3) / n4;
                        final float[] array2 = array;
                        final int n7 = this.i * (this.bp + this.B) + (this.bo + this.A);
                        array2[n7] += j - n6 * j;
                    }
                    else {
                        final float[] array3 = array;
                        final int n8 = this.i * (this.bp + this.B) + (this.bo + this.A);
                        array3[n8] += j;
                    }
                }
                ++this.bo;
            }
            ++this.bp;
        }
    }
    
    final void c(final int n) {
        final float j = this.J;
        float[] array;
        if (this.D == 1) {
            array = this.y;
        }
        else {
            array = this.x;
        }
        final int a = this.i - n * 2 - 1;
        final int b = this.j - n * 2 - 1;
        this.A = (int)(Math.random() * this.i);
        this.B = (int)(Math.random() * this.j);
        if (this.A < 1) {
            this.A = 1;
        }
        else if (this.A > a) {
            this.A = a;
        }
        if (this.B < 1) {
            this.B = 1;
        }
        else if (this.B > b) {
            this.B = b;
        }
        final int n2 = n * 2;
        final int n3 = n2 - 1;
        for (int i = this.B + 1; i < this.B + n3; ++i) {
            for (int k = this.A + 1; k < this.A + n3; ++k) {
                final float[] array2 = array;
                final int n4 = this.i * i + k;
                array2[n4] += j;
            }
        }
        final float n5 = j / 2.0f;
        final int n6 = this.A + n3;
        for (int l = this.B; l < this.B + n2; ++l) {
            final float[] array3 = array;
            final int n7 = this.i * l + this.A;
            array3[n7] += n5;
        }
        for (int b2 = this.B; b2 < this.B + n2; ++b2) {
            final float[] array4 = array;
            final int n8 = this.i * b2 + n6;
            array4[n8] += n5;
        }
        final int n9 = this.i * this.B;
        final int n10 = this.i * (this.B + n3);
        for (int a2 = this.A; a2 < this.A + n2; ++a2) {
            final float[] array5 = array;
            final int n11 = n9 + a2;
            array5[n11] += n5;
        }
        for (int a3 = this.A; a3 < this.A + n2; ++a3) {
            final float[] array6 = array;
            final int n12 = n10 + a3;
            array6[n12] += n5;
        }
    }
    
    final synchronized void d(int n) {
        ++n;
        final float j = this.J;
        float[] array;
        if (this.D == 1) {
            array = this.y;
        }
        else {
            array = this.x;
        }
        final float n2 = n - 1.5f;
        this.A = this.p;
        this.B = this.q;
        if (n == 2) {
            array[this.i * this.B + this.A] = (int)(Math.random() * j) % j;
            return;
        }
        this.bl = n * n;
        final float n3 = n2 * n2;
        final float n4 = this.bl - n3;
        this.bp = -n;
        while (this.bp < n) {
            this.bq = this.bp * this.bp;
            this.bo = -n;
            while (this.bo < n) {
                final int n5 = this.bo * this.bo + this.bq;
                if (n5 < this.bl) {
                    if (n5 > n3) {
                        final float n6 = (n5 - n3) / n4;
                        final float[] array2 = array;
                        final int n7 = this.i * (this.bp + this.B) + (this.bo + this.A);
                        array2[n7] += j - n6 * j;
                    }
                    else {
                        final float[] array3 = array;
                        final int n8 = this.i * (this.bp + this.B) + (this.bo + this.A);
                        array3[n8] += j;
                    }
                }
                ++this.bo;
            }
            ++this.bp;
        }
    }
    
    public synchronized boolean mouseMove(final Event event, int p3, int q) {
        if (p3 < 6) {
            p3 = 6;
        }
        else if (p3 > this.i - 6) {
            p3 = this.i - 6;
        }
        if (q < 6) {
            q = 6;
        }
        else if (q > this.j - 6) {
            q = this.j - 6;
        }
        final int p4 = this.p;
        final int q2 = this.q;
        final float j = this.J;
        this.p = p3;
        this.q = q;
        this.J = 90.0f;
        this.d(2);
        this.p = p4;
        this.q = q2;
        this.J = j;
        return true;
    }
    
    public titlewater() {
        this.c = false;
        this.m = 1;
        this.G = -0.6;
        this.V = false;
    }
    
    private static String a(final String s) {
        final char[] charArray = s.toCharArray();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = 'H';
                    break;
                }
                case 1: {
                    c2 = ')';
                    break;
                }
                case 2: {
                    c2 = ',';
                    break;
                }
                case 3: {
                    c2 = 't';
                    break;
                }
                default: {
                    c2 = 'Y';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
