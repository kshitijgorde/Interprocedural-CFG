import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.image.ImageProducer;
import java.awt.Component;
import java.awt.MediaTracker;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Lenz extends Applet implements Runnable
{
    private boolean E;
    private int F;
    private int G;
    private int H;
    private int I;
    private int J;
    private int K;
    private int L;
    private int M;
    private int N;
    private Thread O;
    private b P;
    private a Q;
    private a R;
    private Image S;
    private Image T;
    private double U;
    private Color V;
    private String W;
    private boolean X;
    
    public void init() {
        super.init();
        this.setLayout(null);
        this.addNotify();
        System.out.println("# Lenz v1.1 by Jos van Ouwerkerk");
        System.out.println("# This applet is part of the Jvo Java package");
        System.out.println("# Contact me at jvohome@dds.nl");
        System.out.println("# More information at http://come.to/jvo");
        this.W = "";
    }
    
    public void start() {
        if (this.O == null) {
            (this.O = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.O != null) {
            this.O.stop();
            this.O = null;
        }
    }
    
    public void run() {
        if (this.getParameter("image") != null) {
            this.W = "loading background image";
            this.C();
            try {
                this.T = this.getImage(new URL(this.getCodeBase(), this.getParameter("image")));
            }
            catch (MalformedURLException ex) {}
            final MediaTracker mediaTracker = new MediaTracker(this);
            mediaTracker.addImage(this.T, 0);
            try {
                mediaTracker.waitForID(0);
            }
            catch (InterruptedException ex2) {}
        }
        else {
            this.T = this.createImage(new a(this.size().width, this.size().height, new Color(255, 0, 0), new Color(255, 255, 0), new Color(0, 255, 0), new Color(0, 0, 255)));
        }
        this.paint(this.getGraphics());
        this.W = "raytracing lens";
        this.C();
        this.R = new a(this.T);
        this.H = this.R.B;
        this.I = this.R.C;
        this.J = 8;
        final String parameter;
        if ((parameter = this.getParameter("size")) != null) {
            this.G = Math.min(60, Integer.parseInt(parameter));
        }
        else {
            this.G = 40;
        }
        this.G = Math.min(this.G, Math.min(this.H - 2 * this.J, this.I - 2 * this.J));
        final String parameter2;
        if ((parameter2 = this.getParameter("transparency")) != null) {
            this.U = Math.min(Math.max(Double.valueOf(parameter2), 0.0), 1.0);
        }
        else {
            this.U = 0.7000000000000001;
        }
        final String parameter3;
        if ((parameter3 = this.getParameter("color")) != null) {
            this.V = this.D(parameter3);
        }
        else {
            this.V = new Color(255);
        }
        this.P = new b(this.G, this.U, this.V, 1.3330000000000002, 0.7853981633974483, 1.0471975511965976, this.J);
        this.Q = new a((this.G + this.J) * 2, (this.G + this.J) * 2);
        this.S = this.createImage(this.Q);
        this.F = 0;
        this.K = this.H / 2;
        this.L = this.I / 2;
        System.gc();
        this.W = "";
        this.C();
        this.E = true;
        while (true) {
            if (this.E) {
                ++this.F;
                if (this.X) {
                    this.G(this.M, this.N);
                }
                else {
                    this.G((int)(this.H / 2.0 + (this.H / 2 - this.G - this.J) * Math.sin(this.F / (this.H / 12.73))), (int)(this.I / 2.0 + (this.I / 2 - this.G - this.J) * Math.sin(this.F / (this.I / 14.91))));
                }
                this.P.I(this.R, this.Q, this.K, this.L);
                this.Q.B();
                this.E = false;
                this.repaint();
                try {
                    Thread.sleep(25L);
                }
                catch (InterruptedException ex3) {}
            }
        }
    }
    
    public void G(final int k, final int l) {
        final int abs = Math.abs(k - this.K);
        final int abs2 = Math.abs(l - this.L);
        if (abs <= 8 && abs2 <= 8) {
            this.K = k;
            this.L = l;
        }
        else if (abs > abs2) {
            this.K += (k - this.K) * 8 / abs;
            this.L += (l - this.L) * 8 / abs;
        }
        else {
            this.K += (k - this.K) * 8 / abs2;
            this.L += (l - this.L) * 8 / abs2;
        }
        this.K = Math.min(Math.max(this.K, 8 + this.G), this.H - 9 - this.G);
        this.L = Math.min(Math.max(this.L, 8 + this.G), this.I - 9 - this.G);
    }
    
    public void update(final Graphics graphics) {
        if (this.S != null) {
            graphics.drawImage(this.S, this.K - this.G - this.J, this.L - this.G - this.J, null);
        }
        this.E = true;
    }
    
    public void paint(final Graphics graphics) {
        if (this.T != null) {
            graphics.drawImage(this.T, 0, 0, null);
        }
    }
    
    private Color D(final String s) {
        s.toUpperCase();
        if (s.length() != 6) {
            return new Color(0);
        }
        return new Color(this.F(s.charAt(0)) << 4 | this.F(s.charAt(1)), this.F(s.charAt(2)) << 4 | this.F(s.charAt(3)), this.F(s.charAt(4)) << 4 | this.F(s.charAt(5)));
    }
    
    private int F(final char c) {
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
    
    private void E(final String w) {
        this.W = w;
        this.C();
    }
    
    private void C() {
        this.getAppletContext().showStatus("Lenz v1.1" + (this.W.equals("") ? "" : " -> ") + this.W);
    }
    
    public boolean handleEvent(final Event event) {
        if (event.target == this) {
            if (event.id == 504 || event.id == 503) {
                this.C();
                this.X = true;
                this.M = event.x;
                this.N = event.y;
                return true;
            }
            if (event.id == 505) {
                this.X = false;
            }
        }
        return super.handleEvent(event);
    }
}
