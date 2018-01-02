import java.awt.event.MouseMotionAdapter;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.image.PixelGrabber;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.image.ImageProducer;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.awt.Image;
import java.awt.Dimension;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Tunnel extends Applet implements Runnable
{
    private int[] for;
    private int[] f;
    private int o;
    private int int;
    private int null;
    private int J;
    private int c;
    private float w;
    private int t;
    private String try;
    private short[][] q;
    private float[][] do;
    private float[][] m;
    private long v;
    private int n;
    private Thread A;
    private boolean r;
    private Dimension F;
    private int d;
    private int g;
    private Image I;
    private MemoryImageSource E;
    private BufferedImage else;
    private a G;
    private boolean goto;
    private static final Font byte;
    private static final Cursor long;
    private static final Cursor l;
    private static final String C = "http://scb.freehomepage.com";
    private int b;
    private int h;
    private static final int[] z;
    private static final int[] e;
    private static final int[] p;
    private static final int[] B;
    private static final int[] a;
    private static String j;
    private static String case;
    private static String u;
    private static String void;
    private static String D;
    private static final float char = 3.1415927f;
    private static final int if = 12;
    private static final int k = 4096;
    private static final int H = 4095;
    private static final int s = 1024;
    private static final float i = 651.8986f;
    private static float[] new;
    
    public Tunnel() {
        this.v = -1L;
        this.r = false;
        this.G = new a();
        this.b = 100;
        this.h = 10;
    }
    
    private static String a(final int[] array) {
        final char[] array2 = new char[array.length];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = (char)(361 - array[i]);
        }
        return new String(array2);
    }
    
    public void init() {
        try {
            this.o = Integer.parseInt(this.getParameter(Tunnel.case));
            this.int = 1 << this.o;
        }
        catch (Exception ex) {
            this.int = 255;
            this.o = 8;
        }
        this.null = this.int - 2;
        this.J = this.int >> 1;
        this.c = this.int * this.int - 1;
        try {
            this.t = Integer.parseInt(this.getParameter(Tunnel.D));
        }
        catch (Exception ex2) {
            this.t = 3;
        }
        this.setBackground(Color.black);
        this.setForeground(Color.white);
        this.for = new int[this.int * this.int];
        (this.E = new MemoryImageSource(this.int, this.int, new DirectColorModel(32, 16711680, 65280, 255, 0), this.for, 0, this.int)).setAnimated(true);
        this.E.setFullBufferUpdates(true);
        this.I = this.createImage(this.E);
        this.addMouseListener(new a());
        this.addMouseMotionListener(new b());
        System.gc();
    }
    
    public void start() {
        if (!this.r) {
            if (this.A == null) {
                this.A = new Thread(this);
            }
            this.A.start();
        }
    }
    
    public void stop() {
        this.A = null;
        this.I = null;
    }
    
    public void a() {
        if (this.r) {
            this.r = false;
            this.start();
        }
        else {
            this.r = true;
            this.A = null;
        }
    }
    
    public void run() {
        this.try = "Loading Image";
        this.repaint();
        try {
            String s = this.getParameter(Tunnel.j);
            if (s == null) {
                s = Tunnel.void;
            }
            final Image image = this.getImage(this.getCodeBase(), s);
            final MediaTracker mediaTracker = new MediaTracker(this);
            mediaTracker.addImage(image, 0);
            mediaTracker.waitForAll();
            while (!mediaTracker.checkAll()) {
                Thread.sleep(500L);
            }
            this.f = new int[this.int * this.int];
            final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, this.int, this.int, this.f, 0, this.int);
            pixelGrabber.grabPixels();
            if ((pixelGrabber.getStatus() & 0x80) != 0x0) {
                this.try = "Error loading image";
                this.f = null;
                this.repaint();
                return;
            }
            image.flush();
        }
        catch (Exception ex) {
            this.try = "Error loading image";
            this.f = null;
            this.repaint();
            return;
        }
        this.try = "Loading Calculation Tables";
        this.repaint();
        this.q = new short[256][256];
        for (int i = 0; i < 256; ++i) {
            for (int j = 0; j < 256; ++j) {
                this.q[i][j] = (short)(i * j / 255);
            }
        }
        Tunnel.new = new float[4096];
        for (int k = 0; k < 4096; ++k) {
            Tunnel.new[k] = (float)Math.sin(k / 651.8986f);
        }
        final int n = this.int * 3 + 1;
        this.do = new float[n][n];
        for (int l = 0; l < n; ++l) {
            for (int n2 = 0; n2 < n; ++n2) {
                this.do[l][n2] = (float)Math.sqrt(l * l + n2 * n2);
            }
        }
        final int n3 = this.int * 4 + 1;
        this.m = new float[n3][n3];
        for (int n4 = 0; n4 < n3; ++n4) {
            for (int n5 = 0; n5 < n3; ++n5) {
                this.m[n5][n4] = (float)Math.atan2(n5, n4);
            }
        }
        this.try = null;
        Thread.currentThread().setPriority(1);
        long currentTimeMillis = System.currentTimeMillis();
        while (Thread.currentThread() == this.A) {
            ++this.v;
            final long n6 = System.currentTimeMillis() - currentTimeMillis;
            currentTimeMillis += n6;
            this.if(this.G.a(n6));
            this.E.newPixels();
            this.paint(this.getGraphics());
        }
    }
    
    public void if(final float n) {
        float n2 = 0.0f;
        int n3 = 0;
        int n4 = 0;
        this.w += n * 0.001f;
        final float n5 = 2.5f;
        final float n6 = this.t * 651.8986f;
        final float do1 = do(this.w * 2.645645f);
        final float n7 = do(this.w * 0.1212f) * do(this.w * 0.4444f);
        final float n8 = for(this.w * 0.1212f) * for(this.w * 0.4444f);
        final int n9 = (int)(do(this.w * 0.5f) * for(this.w * 0.2f) * this.int) + this.J;
        final int n10 = (int)(for(this.w * 0.5f) * for(this.w * 0.2f) * this.int) + this.J;
        final int n11 = (int)(for(this.w * 0.75f + 1.0f) * this.int) + n9;
        final int n12 = (int)(do(this.w * 0.75f + 1.0f) * this.int) + n10;
        for (int i = 0; i < this.int; ++i) {
            for (int j = 0; j < this.int; ++j) {
                int n13 = j - n11;
                int n14 = i - n12;
                final int n15 = j - n9;
                final int n16 = i - n10;
                final float n17 = do1 * (n7 * n13 - n8 * n14);
                if (n14 < 0) {
                    n14 = -n14;
                    if (n13 < 0) {
                        n13 = -n13;
                        n2 = this.m[n14][n13] - 3.1415927f;
                    }
                    else if (n13 > 0) {
                        n2 = -this.m[n14][n13];
                    }
                }
                else if (n13 < 0) {
                    n13 = -n13;
                    n2 = 3.1415927f - this.m[n14][n13];
                }
                else if (n13 > 0) {
                    n2 = this.m[n14][n13];
                }
                if (n16 < 0) {
                    if (n15 < 0) {
                        n4 = (int)((this.m[-n16][-n15] - 3.1415927f - n2) * n6);
                    }
                    else if (n15 > 0) {
                        n4 = (int)((this.m[-n16][n15] + n2) * -n6);
                    }
                }
                else if (n15 < 0) {
                    n4 = (int)((3.1415927f - this.m[n16][-n15] - n2) * n6);
                }
                else if (n15 > 0) {
                    n4 = (int)((this.m[n16][n15] - n2) * n6);
                }
                final float n18 = this.do[n13][n14] * n5;
                this.for[n3++] = this.f[n11 + (int)(Tunnel.new[1024 - n4 & 0xFFF] * n18 + n17) + (n12 + (int)(Tunnel.new[n4 & 0xFFF] * n18 + n17) << this.o) & this.c];
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        graphics.setFont(Tunnel.byte);
        if (this.try != null) {
            graphics.setColor(Color.black);
            graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
            graphics.setColor(Color.white);
            graphics.drawString(this.try, 6, 12);
            return;
        }
        this.F = this.getSize();
        this.d = (this.F.width - this.int) / 2;
        this.g = (this.F.height - this.int) / 2;
        graphics.drawImage(this.I, this.d, this.g, null);
        graphics.setColor(Color.white);
        graphics.drawString("FPS: " + Math.round(this.G.a()), 6, 12);
        if (this.goto) {
            graphics.setColor(Color.yellow);
        }
        this.b = graphics.getFontMetrics().stringWidth("http://scb.freehomepage.com") + 12;
        graphics.drawString("http://scb.freehomepage.com", this.F.width - this.b, this.F.height - this.h);
    }
    
    public static float a(final int n) {
        return Tunnel.new[1024 - n & 0xFFF];
    }
    
    public static float if(final int n) {
        return Tunnel.new[n & 0xFFF];
    }
    
    public static float do(final float n) {
        return Tunnel.new[1024 - (int)(n * 651.8986f) & 0xFFF];
    }
    
    public static float for(final float n) {
        return Tunnel.new[(int)(n * 651.8986f) & 0xFFF];
    }
    
    public static int a(final float n) {
        return (int)(n * 651.8986f);
    }
    
    static {
        byte = new Font("Monospaced", 0, 12);
        long = new Cursor(12);
        l = new Cursor(0);
        z = new int[] { 256, 252, 264, 258, 260, 266, 251, 264, 252, 260 };
        e = new int[] { 246, 256, 239, 260, 266, 263, 256, 245, 246 };
        p = new int[] { 240, 260, 246 };
        B = new int[] { 256, 252, 264, 258, 260 };
        a = new int[] { 252, 244, 253, 245, 256, 249, 253, 256, 260, 247 };
        Tunnel.j = a(Tunnel.z);
        Tunnel.case = a(Tunnel.e);
        Tunnel.u = a(Tunnel.p);
        Tunnel.void = a(Tunnel.B);
        Tunnel.D = a(Tunnel.a);
    }
    
    private class a extends MouseAdapter
    {
        public void mousePressed(final MouseEvent mouseEvent) {
            if (Tunnel.this.goto) {
                try {
                    Tunnel.this.getAppletContext().showDocument(new URL("http://scb.freehomepage.com"), "_blank");
                }
                catch (MalformedURLException ex) {}
            }
        }
    }
    
    private class b extends MouseMotionAdapter
    {
        public void mouseMoved(final MouseEvent mouseEvent) {
            if (mouseEvent.getX() > Tunnel.this.F.width - Tunnel.this.b && mouseEvent.getY() > Tunnel.this.F.height - Tunnel.this.h * 2) {
                if (!Tunnel.this.goto) {
                    Tunnel.this.setCursor(Tunnel.long);
                    Tunnel.this.goto = true;
                }
            }
            else if (Tunnel.this.goto) {
                Tunnel.this.setCursor(Tunnel.l);
                Tunnel.this.goto = false;
            }
        }
    }
}
