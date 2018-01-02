import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.image.ImageProducer;
import java.awt.Color;
import java.awt.Component;
import java.awt.MediaTracker;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.LayoutManager;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Bumpz extends Applet implements Runnable
{
    private boolean S;
    private int T;
    private int U;
    private int V;
    private int W;
    private int X;
    private int Y;
    private int Z;
    private int a;
    private int b;
    private int c;
    private int d;
    private Thread e;
    private b f;
    private a g;
    private a h;
    private a i;
    private a j;
    private int[] k;
    private int[] l;
    private Image m;
    private Image n;
    private Image o;
    private String p;
    private boolean q;
    
    public void init() {
        super.init();
        this.setLayout(null);
        this.addNotify();
        this.resize(430, 270);
        System.out.println("# Bumpz v1.0 by Jos van Ouwerkerk");
        System.out.println("# This applet is part of the Jvo Java package");
        System.out.println("# Contact me at jvohome@dds.nl");
        System.out.println("# More information at http://come.to/jvo");
        this.p = "";
    }
    
    public void start() {
        if (this.e == null) {
            (this.e = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.e != null) {
            this.e.stop();
            this.e = null;
        }
    }
    
    public void run() {
        if (this.getParameter("image") != null) {
            this.p = "loading background image";
            this.H();
            try {
                this.n = this.getImage(new URL(this.getCodeBase(), this.getParameter("image")));
            }
            catch (MalformedURLException ex) {}
            final MediaTracker mediaTracker = new MediaTracker(this);
            mediaTracker.addImage(this.n, 0);
            try {
                mediaTracker.waitForID(0);
            }
            catch (InterruptedException ex2) {}
        }
        else {
            this.n = this.createImage(new a(this.size().width, this.size().height, new Color(255, 0, 0), new Color(255, 255, 0), new Color(0, 255, 0), new Color(0, 0, 255)));
        }
        this.h = new a(this.n);
        this.W = this.h.B;
        this.X = this.h.C;
        this.i = new a(this.W, this.X);
        for (int i = 0; i < this.W * this.X; ++i) {
            this.i.A[i] = (0xFF000000 | ((this.h.A[i] & 0xFFFFFF) >> 2 & 0x3F3F3F));
        }
        this.o = this.createImage(this.i);
        this.paint(this.getGraphics());
        this.p = "creating bumps";
        this.H();
        this.d = 32;
        this.j = new a(this.W, this.X);
        for (int j = 0; j < this.W * this.X; ++j) {
            this.j.A[j] = (this.h.A[j] & 0xFF);
        }
        this.k = new int[this.W * this.X];
        this.l = new int[this.W * this.X];
        int n = 0;
        for (int k = 0; k < this.X; ++k) {
            for (int l = 0; l < this.W; ++l) {
                if (l == 0 || l == this.W - 1) {
                    this.k[n] = 0;
                }
                else {
                    this.k[n] = this.j.A[n - 1] - this.j.A[n + 1];
                }
                if (k == 0 || k == this.X - 1) {
                    this.l[n] = 0;
                }
                else {
                    this.l[n] = this.j.A[n - this.W] - this.j.A[n + this.W];
                }
                ++n;
            }
        }
        int max = 0;
        for (int n2 = 0; n2 < this.W * this.X; ++n2) {
            max = Math.max(max, Math.max(Math.abs(this.k[n2]), Math.abs(this.l[n2])));
        }
        for (int n3 = 0; n3 < this.W * this.X; ++n3) {
            this.k[n3] = this.k[n3] * (this.d - 1) / max;
            this.l[n3] = this.l[n3] * (this.d - 1) / max;
        }
        this.p = "rendering light";
        this.H();
        this.Y = 8;
        final String parameter;
        if ((parameter = this.getParameter("size")) != null) {
            this.U = Math.min(80, Integer.parseInt(parameter));
        }
        else {
            this.U = 40;
        }
        this.U = Math.min(this.U, Math.min(this.W / 2, this.X / 2));
        this.V = this.U + this.d + this.Y;
        this.f = new b(this.U, this.Y, this.d);
        this.g = new a(this.V * 2, this.V * 2);
        this.m = this.createImage(this.g);
        this.T = 0;
        this.Z = this.W / 2;
        this.a = this.X / 2;
        System.gc();
        this.p = "";
        this.H();
        this.S = true;
        while (true) {
            if (this.S) {
                ++this.T;
                if (this.q) {
                    this.L(this.b, this.c);
                }
                else {
                    this.L((int)(this.W / 2.0 + this.W / 2 * Math.sin(this.T / (this.W / 12.73))), (int)(this.X / 2.0 + this.X / 2 * Math.sin(this.T / (this.X / 14.91))));
                }
                this.f.F(this.h, this.i, this.k, this.l, this.g, this.Z, this.a);
                this.g.B();
                this.S = false;
                this.repaint();
                try {
                    Thread.sleep(25L);
                }
                catch (InterruptedException ex3) {}
            }
        }
    }
    
    public void L(final int z, final int a) {
        final int abs = Math.abs(z - this.Z);
        final int abs2 = Math.abs(a - this.a);
        if (abs <= 8 && abs2 <= 8) {
            this.Z = z;
            this.a = a;
            return;
        }
        if (abs > abs2) {
            this.Z += (z - this.Z) * 8 / abs;
            this.a += (a - this.a) * 8 / abs;
            return;
        }
        this.Z += (z - this.Z) * 8 / abs2;
        this.a += (a - this.a) * 8 / abs2;
    }
    
    public void update(final Graphics graphics) {
        if (this.m != null) {
            graphics.drawImage(this.m, this.Z - this.V, this.a - this.V, null);
        }
        this.S = true;
    }
    
    public void paint(final Graphics graphics) {
        if (this.o != null) {
            graphics.drawImage(this.o, 0, 0, null);
        }
    }
    
    private Color I(final String s) {
        s.toUpperCase();
        if (s.length() != 6) {
            return new Color(0);
        }
        return new Color(this.K(s.charAt(0)) << 4 | this.K(s.charAt(1)), this.K(s.charAt(2)) << 4 | this.K(s.charAt(3)), this.K(s.charAt(4)) << 4 | this.K(s.charAt(5)));
    }
    
    private int K(final char c) {
        switch (c) {
            case '1': {
                return 1;
            }
            case '2': {
                return 2;
            }
            case '3': {
                return 3;
            }
            case '4': {
                return 4;
            }
            case '5': {
                return 5;
            }
            case '6': {
                return 6;
            }
            case '7': {
                return 7;
            }
            case '8': {
                return 8;
            }
            case '9': {
                return 9;
            }
            case 'A': {
                return 10;
            }
            case 'B': {
                return 11;
            }
            case 'C': {
                return 12;
            }
            case 'D': {
                return 13;
            }
            case 'E': {
                return 14;
            }
            case 'F': {
                return 15;
            }
            default: {
                return 0;
            }
        }
    }
    
    private void J(final String p) {
        this.p = p;
        this.H();
    }
    
    private void H() {
        this.getAppletContext().showStatus("Bumpz v1.0" + (this.p.equals("") ? "" : " -> ") + this.p);
    }
    
    public boolean handleEvent(final Event event) {
        if (event.target == this) {
            if (event.id == 504 || event.id == 503) {
                this.H();
                this.q = true;
                this.b = event.x;
                this.c = event.y;
                return true;
            }
            if (event.id == 505) {
                this.q = false;
            }
        }
        return super.handleEvent(event);
    }
}
