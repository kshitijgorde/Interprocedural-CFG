import java.awt.Event;
import java.net.URL;
import java.util.Date;
import java.awt.Component;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.util.StringTokenizer;
import java.awt.image.ImageProducer;
import java.awt.Font;
import java.awt.Color;
import java.awt.image.MemoryImageSource;
import java.awt.Graphics;
import java.awt.MediaTracker;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SlideOnDemandFade extends Applet implements Runnable
{
    Thread a;
    Image b;
    Image c;
    MediaTracker d;
    Graphics e;
    int f;
    int g;
    MemoryImageSource h;
    int i;
    int j;
    int k;
    Color l;
    int m;
    int[] n;
    int o;
    int p;
    String q;
    Color r;
    Font s;
    String[][] t;
    int[] u;
    int[] v;
    boolean w;
    boolean x;
    int y;
    int z;
    int A;
    Color B;
    boolean C;
    boolean D;
    int E;
    int F;
    int G;
    int H;
    int I;
    float J;
    float K;
    boolean L;
    int M;
    int N;
    boolean O;
    int P;
    Image[] Q;
    int[][] R;
    int[] S;
    int[] T;
    int U;
    boolean V;
    
    public SlideOnDemandFade() {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = 0;
        this.g = 0;
        this.h = null;
        this.o = 12;
        this.p = 0;
        this.q = "TimesRoman";
        this.r = Color.red;
        this.w = false;
        this.x = true;
        this.y = 100;
        this.z = 100;
        this.A = 0;
        this.C = true;
        this.D = false;
        this.E = 1;
        this.F = 0;
        this.G = 0;
        this.H = 0;
        this.I = 1;
        this.J = 0.0f;
        this.K = 0.1f;
        this.L = false;
        this.M = 0;
        this.N = 0;
        this.O = true;
        this.P = 1;
        this.U = 0;
        this.V = false;
    }
    
    public final void init() {
        super.init();
        this.f = this.size().width;
        this.g = this.size().height;
        this.L = new a(this, 384, this.getParameter("regcode")).b;
        Integer.parseInt(this.getParameter("loading"));
        final int[] b = this.b(this.getParameter("bgcolor"));
        this.i = b[0];
        this.j = b[1];
        this.k = b[2];
        this.l = new Color(this.i, this.j, this.k);
        this.y = Integer.parseInt(this.getParameter("pause"));
        this.z = Integer.parseInt(this.getParameter("fadedelay"));
        this.M = Integer.parseInt(this.getParameter("nrot"));
        this.N = 0;
        this.O = true;
        final String[] a = this.a(this.getParameter("border"));
        this.A = this.b(a[0])[0];
        final int[] b2 = this.b(a[1]);
        this.B = new Color(b2[0], b2[1], b2[2]);
        if (a[2].trim().toUpperCase().startsWith("OUT")) {
            this.C = true;
        }
        else {
            this.C = false;
        }
        final String[] a2 = this.a(this.getParameter("text"));
        this.q = a2[0];
        this.p = this.b(a2[1])[0];
        this.o = this.b(a2[2])[0];
        final int[] b3 = this.b(a2[3]);
        this.r = new Color(b3[0], b3[1], b3[2]);
        this.E = Integer.parseInt(this.getParameter("canvasfit"));
        this.F = Integer.parseInt(this.getParameter("interact"));
        this.m = Integer.parseInt(this.getParameter("total"));
        this.R = new int[2][];
        this.Q = new Image[2];
        this.S = new int[2];
        this.T = new int[2];
        this.t = new String[this.m][5];
        this.u = new int[this.m];
        this.v = new int[this.m];
        for (int i = 0; i < this.m; ++i) {
            this.showStatus("Reading item " + i + "...");
            final String[] a3 = this.a(this.getParameter("item" + i));
            for (int j = 0; j < 5; ++j) {
                this.t[i][j] = a3[j].trim();
            }
            final int[] b4 = this.b(a3[2]);
            this.u[i] = b4[0];
            this.v[i] = b4[1];
        }
        this.resize(this.f, this.g);
        this.b = this.createImage(this.f, this.g);
        this.e = this.b.getGraphics();
        this.n = new int[this.f * this.g];
        (this.h = new MemoryImageSource(this.f, this.g, this.n, 0, this.f)).setAnimated(true);
        this.c = this.createImage(this.h);
    }
    
    final String[] a(final String s) {
        final StringTokenizer stringTokenizer;
        final String[] array = new String[(stringTokenizer = new StringTokenizer(s, "|")).countTokens()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = stringTokenizer.nextToken();
        }
        return array;
    }
    
    final int[] b(final String s) {
        final StringTokenizer stringTokenizer;
        final int[] array = new int[(stringTokenizer = new StringTokenizer(s, " ")).countTokens()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        return array;
    }
    
    public final void start() {
        if (this.a == null) {
            (this.a = new Thread(this)).start();
        }
    }
    
    public final void stop() {
        if (this.a != null && this.a.isAlive()) {
            this.a.stop();
        }
        this.a = null;
        this.c = null;
        if (this.b != null) {
            this.b.flush();
        }
        this.b = null;
        if (this.e != null) {
            this.e.dispose();
        }
        this.e = null;
        this.d = null;
        this.h = null;
        System.gc();
    }
    
    public final void destroy() {
        System.gc();
    }
    
    final void a(final int n) {
        this.showStatus("Loading image '" + this.t[n][0] + "'...");
        this.Q[this.P] = null;
        this.Q[this.P] = this.getImage(this.getDocumentBase(), this.t[n][0]);
        this.d.addImage(this.Q[this.P], n);
        try {
            this.d.waitForAll();
        }
        catch (InterruptedException ex) {}
        this.S[this.P] = this.Q[this.P].getWidth(this);
        this.T[this.P] = this.Q[this.P].getHeight(this);
        this.R[this.P] = null;
        this.R[this.P] = new int[this.S[this.P] * this.T[this.P]];
        final PixelGrabber pixelGrabber = new PixelGrabber(this.Q[this.P], 0, 0, this.S[this.P], this.T[this.P], this.R[this.P], 0, this.S[this.P]);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex2) {}
    }
    
    public final void run() {
        if (!this.w) {
            this.d = new MediaTracker(this);
            this.a(this.P = 0);
            this.a(this.P = 1);
            this.G = 1;
            this.H = 0;
            this.P = 0;
            this.w = true;
        }
        System.gc();
        final Graphics graphics = this.getGraphics();
        int n = 0;
        while (Thread.currentThread() == this.a) {
            this.paint(graphics);
            try {
                if (this.J >= 1.2f) {
                    this.J = 0.0f;
                    this.G = (this.G + this.I) % this.m;
                    this.H = (this.G - this.I + this.m) % this.m;
                    Thread.sleep(this.y);
                    this.a(this.G);
                    this.P = 1 - this.P;
                    if (this.M > 0 && this.G == this.m - 1) {
                        ++this.N;
                        if (this.N == this.M && this.O) {
                            this.O = false;
                        }
                    }
                }
                else {
                    Thread.sleep(this.z);
                }
            }
            catch (InterruptedException ex2) {}
            if (this.O) {
                this.a();
            }
            else {
                this.G = this.m - 1;
                this.H = this.m - 1;
                this.I = 0;
            }
            if (n++ > 1000) {
                n = 0;
                System.gc();
            }
            this.J += this.K;
            if (!this.L) {
                final Date date;
                if ((date = new Date()).getMinutes() % 10 == 0 && date.getSeconds() <= 20) {
                    this.U = 20 - date.getSeconds();
                    if (this.U >= 3 || this.V) {
                        continue;
                    }
                    try {
                        this.getAppletContext().showDocument(new URL(this.getDocumentBase(), "http://www.thejmaker.com/unregistered.html"));
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                else {
                    this.U = 0;
                }
            }
        }
    }
    
    public final void a() {
        float n = 1.0f;
        float n2 = 1.0f;
        float n3 = 1.0f;
        float n4 = 1.0f;
        final float n5 = this.f / 2.0f;
        final float n6 = this.g / 2.0f;
        this.H = (this.G - this.I + this.m) % this.m;
        final int p = this.P;
        int n7 = 1 - p;
        if (this.I == 0) {
            n7 = p;
        }
        if (this.E == 1) {
            n = this.f / this.S[p];
            n2 = this.g / this.T[p];
            n3 = this.f / this.S[n7];
            n4 = this.g / this.T[n7];
        }
        final float n8 = this.S[p] / 2.0f;
        final float n9 = this.T[p] / 2.0f;
        final float n10 = this.S[n7] / 2.0f;
        final float n11 = this.T[n7] / 2.0f;
        for (int i = 0; i < this.g; ++i) {
            for (int j = 0; j < this.f; ++j) {
                final int n12 = (int)((i - n6) / n2 + n9);
                final int n13 = (int)((j - n5) / n + n8);
                final int n14 = n12 * this.S[p] + n13;
                int k;
                int l;
                int m;
                if (n13 < 0 || n13 > this.S[p] - 1 || n12 < 0 || n12 > this.T[p] - 1) {
                    k = this.i;
                    l = this.j;
                    m = this.k;
                }
                else {
                    k = (this.R[p][n14] & 0xFF0000) >> 16;
                    l = (this.R[p][n14] & 0xFF00) >> 8;
                    m = (this.R[p][n14] & 0xFF);
                }
                final int n15 = (int)((i - n6) / n4 + n11);
                final int n16 = (int)((j - n5) / n3 + n10);
                final int n17 = n15 * this.S[n7] + n16;
                int i2;
                int j2;
                int k2;
                if (n16 < 0 || n16 > this.S[n7] - 1 || n15 < 0 || n15 > this.T[n7] - 1) {
                    i2 = this.i;
                    j2 = this.j;
                    k2 = this.k;
                }
                else {
                    i2 = (this.R[n7][n17] & 0xFF0000) >> 16;
                    j2 = (this.R[n7][n17] & 0xFF00) >> 8;
                    k2 = (this.R[n7][n17] & 0xFF);
                }
                int n18;
                int n19;
                int n20;
                if (this.J > 0.95f) {
                    n18 = i2;
                    n19 = j2;
                    n20 = k2;
                }
                else {
                    final int n21 = (int)((1.0f - this.J) * k + this.J * i2);
                    final int n22 = (int)((1.0f - this.J) * l + this.J * j2);
                    final int n23 = (int)((1.0f - this.J) * m + this.J * k2);
                    n18 = ((n21 > 255) ? 255 : ((n21 < 0) ? 0 : n21));
                    n19 = ((n22 > 255) ? 255 : ((n22 < 0) ? 0 : n22));
                    n20 = ((n23 > 255) ? 255 : ((n23 < 0) ? 0 : n23));
                }
                this.n[i * this.f + j] = new Color(n18, n19, n20).getRGB();
            }
        }
        this.h.newPixels();
    }
    
    public final synchronized void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final synchronized void paint(final Graphics graphics) {
        if (!this.w) {
            return;
        }
        if (this.U > 0) {
            this.e.setColor(Color.black);
            this.e.fillRect(0, 0, this.f, this.g);
            this.e.setColor(Color.white);
            final String[] array = { "\"SlideOnDemandFade\"", "by", "www.thejmaker.com", "Click to register: " + this.U };
            int n = this.g / 2 - 30;
            for (int i = 0; i < 4; ++i) {
                if (i == 0) {
                    this.e.setFont(new Font("Helvetica", 1, 14));
                }
                else {
                    this.e.setFont(new Font("Helvetica", 1, 11));
                }
                this.e.drawString(array[i], (this.f - this.e.getFontMetrics().stringWidth(array[i])) / 2, n);
                if (i == 0) {
                    n += 16;
                }
                else if (i == 2) {
                    this.e.setColor(Color.yellow);
                    n += 20;
                }
                else {
                    n += 13;
                }
            }
            graphics.drawImage(this.b, 0, 0, this);
            return;
        }
        if (this.x) {
            this.e.setColor(this.r);
            if (this.p == 1) {
                this.s = new Font(this.q, 1, this.o);
            }
            else if (this.p == 2) {
                this.s = new Font(this.q, 2, this.o);
            }
            else if (this.p == 3) {
                this.s = new Font(this.q, 3, this.o);
            }
            else {
                this.s = new Font(this.q, 0, this.o);
            }
            this.e.setFont(this.s);
            this.e.getFontMetrics().getHeight();
            this.e.getFontMetrics().getDescent();
            this.x = false;
        }
        if (this.J > 0.95f) {
            if (!this.t[this.G][3].trim().toUpperCase().startsWith("NONE")) {
                this.showStatus(this.t[this.G][3]);
            }
            else {
                this.showStatus("");
            }
        }
        else if (!this.t[this.H][3].trim().toUpperCase().startsWith("NONE")) {
            this.showStatus(this.t[this.H][3]);
        }
        else {
            this.showStatus("");
        }
        if (this.c != null) {
            this.e.drawImage(this.c, 0, 0, this);
        }
        else {
            this.e.setColor(this.l);
            this.e.fillRect(0, 0, this.f, this.g);
        }
        this.e.setFont(this.s);
        int n2;
        int n3;
        if (this.J > 0.95f) {
            this.e.getFontMetrics().stringWidth(this.t[this.G][1]);
            n2 = this.u[this.G];
            n3 = this.v[this.G];
        }
        else {
            this.e.getFontMetrics().stringWidth(this.t[this.H][1]);
            n2 = this.u[this.H];
            n3 = this.v[this.H];
        }
        if (this.J > 0.95f && !this.t[this.G][1].trim().toUpperCase().startsWith("NONE")) {
            this.e.setColor(Color.black);
            this.e.drawString(this.t[this.G][1], n2 - 1, n3);
            this.e.drawString(this.t[this.G][1], n2 + 1, n3);
            this.e.drawString(this.t[this.G][1], n2, n3 - 1);
            this.e.drawString(this.t[this.G][1], n2, n3 + 1);
            this.e.setColor(this.r);
            this.e.drawString(this.t[this.G][1], n2, n3);
        }
        else if (!this.t[this.H][1].trim().toUpperCase().startsWith("NONE")) {
            this.e.setColor(Color.black);
            this.e.drawString(this.t[this.H][1], n2 - 1, n3);
            this.e.drawString(this.t[this.H][1], n2 + 1, n3);
            this.e.drawString(this.t[this.H][1], n2, n3 - 1);
            this.e.drawString(this.t[this.H][1], n2, n3 + 1);
            this.e.setColor(this.r);
            this.e.drawString(this.t[this.H][1], n2, n3);
        }
        if (this.A != 0) {
            this.e.setColor(this.B);
            for (int j = 0; j < this.A; ++j) {
                this.e.draw3DRect(j, j, this.f - 1 - j * 2, this.g - 1 - j * 2, this.C);
            }
        }
        if (!this.L && this.D) {
            this.e.setFont(new Font("Helvetica", 1, 11));
            final String s = "SlideOnDemandFade (C) thejmaker.com 2005";
            final int n4 = this.f - this.e.getFontMetrics().stringWidth(s) - 3 - this.A;
            final int n5 = this.g - 3 - this.A;
            this.e.setColor(Color.blue);
            this.e.drawString(s, n4, n5 - 1);
            this.e.drawString(s, n4, n5 + 1);
            this.e.drawString(s, n4 - 1, n5);
            this.e.drawString(s, n4 + 1, n5);
            this.e.setColor(Color.cyan);
            this.e.drawString(s, n4, n5);
        }
        graphics.drawImage(this.b, 0, 0, this);
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        if (!this.L) {
            this.showStatus("SlideOnDemandFade (C) thejmaker.com 2005");
        }
        else {
            this.showStatus("");
        }
        this.D = false;
        this.I = 1;
        this.repaint();
        return true;
    }
    
    public final boolean mouseMove(final Event event, final int n, final int n2) {
        this.D = true;
        if (this.F == 1) {
            this.I = 0;
        }
        if (this.J > 0.5f && !this.t[this.G][3].trim().toUpperCase().startsWith("NONE")) {
            this.H = this.G;
            this.showStatus(this.t[this.G][3]);
        }
        else if (!this.t[this.H][3].trim().toUpperCase().startsWith("NONE")) {
            this.showStatus(this.t[this.H][3]);
        }
        this.repaint();
        return true;
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.U > 0) {
            try {
                this.getAppletContext().showDocument(new URL(this.getDocumentBase(), "http://www.thejmaker.com/unregistered.html"), "_blank");
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            return this.V = true;
        }
        this.repaint();
        this.I = 0;
        if (this.J > 0.5f && !this.t[this.G][3].trim().toUpperCase().startsWith("NONE")) {
            try {
                this.showStatus("Connecting " + this.t[this.G][3] + "...");
                this.getAppletContext().showDocument(new URL(this.getDocumentBase(), this.t[this.G][3]));
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
            }
        }
        else if (!this.t[this.H][3].trim().toUpperCase().startsWith("NONE")) {
            try {
                this.showStatus("Connecting " + this.t[this.H][3] + "...");
                this.getAppletContext().showDocument(new URL(this.getDocumentBase(), this.t[this.H][3]), this.t[this.H][4]);
            }
            catch (Exception ex3) {
                ex3.printStackTrace();
            }
        }
        return true;
    }
}
