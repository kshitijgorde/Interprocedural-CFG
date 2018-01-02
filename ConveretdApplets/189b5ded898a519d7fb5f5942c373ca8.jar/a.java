import java.awt.image.PixelGrabber;
import java.awt.image.ColorModel;
import java.awt.Font;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.MouseAdapter;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.ImageObserver;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class a extends Applet implements Runnable
{
    Thread d;
    Image b;
    Graphics new;
    Image case;
    Graphics if;
    int goto;
    int a;
    boolean null;
    int[][] else;
    String char;
    Color do;
    int c;
    int int;
    c long;
    int byte;
    int try;
    boolean for;
    boolean void;
    
    public a() {
        this.null = true;
        this.int = 0;
        this.byte = 0;
        this.try = 0;
        this.for = false;
        this.void = false;
    }
    
    public void init() {
        this.goto = this.size().width;
        this.a = this.size().height;
        this.char = this.getParameter("message");
        this.do = Color.decode("#ffffff");
        this.c = Integer.parseInt(this.getParameter("delay"));
        this.b = this.createImage(this.goto, this.a);
        (this.new = this.b.getGraphics()).setColor(this.do);
        this.new.fillRect(0, 0, this.goto, this.a);
        this.case = this.createImage(this.goto, this.a);
        this.if = this.case.getGraphics();
        this.long = new c();
        this.else = new int[this.goto][this.a];
        for (int i = 0; i < this.goto; ++i) {
            for (int j = 0; j < this.a; ++j) {
                this.else[i][j] = (this.do.getRed() << 22 | this.do.getGreen() << 12 | this.do.getBlue() << 2);
            }
        }
        this.addMouseListener(new a());
        this.addMouseMotionListener(new b());
        this.int = 1;
    }
    
    public void start() {
        if (this.d == null) {
            (this.d = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.d != null) {
            this.d.stop();
            this.d = null;
            this.null = false;
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.int >= 1) {
            graphics.drawImage(this.b, 0, 0, this);
        }
    }
    
    public void run() {
        double n = 30.0;
        double n2 = 30.0;
        for (int i = 0; i < this.char.length(); ++i) {
            if (this.char.charAt(i) == ' ') {
                n += 26.0;
                if (n > this.goto - 30.0) {
                    n = 30.0;
                    n2 += 40.0;
                }
            }
            else {
                final double random = Math.random();
                int n3;
                if (random < 0.1) {
                    n3 = 2;
                }
                else if (random < 0.2) {
                    n3 = 3;
                }
                else if (random < 0.7) {
                    n3 = 1;
                }
                else {
                    n3 = 0;
                }
                final double random2 = Math.random();
                String s;
                if (random2 < 0.4) {
                    s = "SansSerif";
                }
                else if (random2 < 0.8) {
                    s = "Serif";
                }
                else {
                    s = "Monospaced";
                }
                final double random3 = Math.random();
                Color color;
                if (random3 < 0.2) {
                    color = new Color(200, 0, 0);
                }
                else if (random3 < 0.7) {
                    color = new Color(0, 0, 0);
                }
                else {
                    color = new Color(140, 140, 140);
                }
                final double random4 = Math.random();
                Color color2;
                if (random4 < 0.35) {
                    color2 = new Color(247, 247, 247);
                }
                else if (random4 < 0.7) {
                    color2 = new Color(235, 235, 235);
                }
                else {
                    color2 = new Color(254, 243, 216);
                }
                this.long.a(this.char.charAt(i), n, n2, (int)(20.0 + 20.0 * Math.random()), -14.0 + 28.0 * Math.random(), s, n3, color, color2, this.new);
                n += 23.0;
                if (n > this.goto - 30.0) {
                    n = 30.0;
                    n2 += 40.0;
                }
            }
            this.new.setColor(Color.white);
            this.new.drawString("www.eigelb.at", this.goto - 82, this.a - 6);
            this.new.drawString("www.eigelb.at", this.goto - 82, this.a - 8);
            this.new.drawString("www.eigelb.at", this.goto - 84, this.a - 6);
            this.new.drawString("www.eigelb.at", this.goto - 84, this.a - 8);
            this.new.setColor(Color.black);
            this.new.drawString("www.eigelb.at", this.goto - 83, this.a - 7);
            this.repaint();
            try {
                Thread.sleep(this.c);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    class b extends MouseMotionAdapter
    {
        public void mouseMoved(final MouseEvent mouseEvent) {
            a.this.byte = mouseEvent.getX();
            a.this.try = mouseEvent.getY();
            if (a.this.try > a.this.a - 21 && a.this.byte > a.this.goto - 88) {
                if (!a.this.void) {
                    a.this.setCursor(new Cursor(12));
                    a.this.void = true;
                }
            }
            else if (a.this.void) {
                a.this.setCursor(new Cursor(0));
                a.this.void = false;
            }
        }
        
        public void mouseDragged(final MouseEvent mouseEvent) {
            this.mouseMoved(mouseEvent);
        }
    }
    
    class a extends MouseAdapter
    {
        public void mousePressed(final MouseEvent mouseEvent) {
            a.this.byte = mouseEvent.getX();
            a.this.try = mouseEvent.getY();
            a.this.for = true;
            if (a.this.try > a.this.a - 21 && a.this.byte > a.this.goto - 83) {
                try {
                    a.this.getAppletContext().showDocument(new URL("http://www.eigelb.at"), "_blank");
                }
                catch (MalformedURLException ex) {}
            }
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
            a.this.for = false;
        }
    }
    
    class c
    {
        int long;
        int d;
        int j;
        int for;
        int m;
        int l;
        int r;
        int n;
        int h;
        Font else;
        int char;
        int try;
        int g;
        int b;
        int int;
        int c;
        double goto;
        double k;
        double p;
        double void;
        double case;
        double do;
        double byte;
        double a;
        double if;
        double q;
        double s;
        int o;
        int null;
        int i;
        int f;
        int new;
        int t;
        Color e;
        
        public void a(final char c, final double n, final double n2, final double n3, final double n4, final String s, final int n5, final Color color, final Color color2, final Graphics graphics) {
            this.long = (int)(n3 * 2.0);
            a.this.if.setColor(color2);
            a.this.if.fillRect(0, 0, a.this.goto, a.this.a);
            a.this.if.setColor(color);
            this.else = new Font(s, n5, this.long);
            a.this.if.setFont(this.else);
            this.n = a.this.getFontMetrics(this.else).stringWidth("" + c);
            this.h = a.this.getFontMetrics(this.else).getHeight();
            a.this.if.drawString("" + c, a.this.goto / 2 - this.n / 2, a.this.a / 2 + (int)(this.h * 0.3));
            this.char = a.this.goto / 2 - this.n / 2 - this.long / 16;
            this.try = a.this.goto / 2 + this.n / 2 + this.long / 8;
            this.g = a.this.a / 2 - (int)(this.h * 0.3);
            this.b = a.this.a / 2 + (int)(this.h * 0.45);
            a.this.if.setColor(new Color(200, 200, 200));
            a.this.if.drawLine(this.try, this.g, this.try, this.b);
            a.this.if.drawLine(this.char, this.b, this.try, this.b);
            final int n6 = a.this.goto * a.this.a;
            final int[] array = new int[n6];
            final int[] array2 = new int[n6];
            final ColorModel rgBdefault = ColorModel.getRGBdefault();
            final PixelGrabber pixelGrabber = new PixelGrabber(a.this.case, 0, 0, a.this.goto, a.this.a, array, 0, a.this.goto);
            try {
                pixelGrabber.grabPixels();
            }
            catch (InterruptedException ex) {
                System.err.println("interrupted waiting for pixels!");
                return;
            }
            for (int i = 0; i < n6; ++i) {
                array2[i] = (rgBdefault.getRed(array[i]) << 22 | rgBdefault.getGreen(array[i]) << 12 | rgBdefault.getBlue(array[i]) << 2);
            }
            this.case = n4 / 180.0 * 3.141592653589793;
            this.int = this.char;
            while (this.int <= this.try) {
                this.c = this.g;
                while (this.c <= this.b) {
                    this.goto = this.int - a.this.goto / 2;
                    this.k = this.c - a.this.a / 2;
                    this.do = Math.cos(this.case);
                    this.byte = Math.sin(this.case);
                    this.p = this.goto * this.do + this.k * this.byte;
                    this.void = -this.goto * this.byte + this.k * this.do;
                    this.goto = this.p;
                    this.k = this.void;
                    this.goto *= 0.5;
                    this.k *= 0.5;
                    this.goto += n;
                    this.k += n2;
                    if (this.goto >= 0.0 && this.goto < a.this.goto - 1 && this.k >= 0.0 && this.k < a.this.a - 1) {
                        this.i = (int)this.goto;
                        this.f = this.i + 1;
                        this.new = (int)this.k;
                        this.t = this.new + 1;
                        this.a = (this.f - this.goto) * (this.t - this.k);
                        this.if = (this.goto - this.i) * (this.t - this.k);
                        this.q = (this.f - this.goto) * (this.k - this.new);
                        this.s = (this.goto - this.i) * (this.k - this.new);
                        this.m = 1023 - ((array2[this.c * a.this.goto + this.int] & 0x3FF00000) >> 20);
                        this.l = 1023 - ((array2[this.c * a.this.goto + this.int] & 0xFFC00) >> 10);
                        this.r = 1023 - (array2[this.c * a.this.goto + this.int] & 0x3FF);
                        this.d = (a.this.else[this.i][this.new] & 0x3FF00000) >> 20;
                        this.j = (a.this.else[this.i][this.new] & 0xFFC00) >> 10;
                        this.for = (a.this.else[this.i][this.new] & 0x3FF);
                        this.d -= (int)(this.a * (this.m >> 2));
                        if (this.d < 0) {
                            this.d = 0;
                        }
                        this.j -= (int)(this.a * (this.l >> 2));
                        if (this.j < 0) {
                            this.j = 0;
                        }
                        this.for -= (int)(this.a * (this.r >> 2));
                        if (this.for < 0) {
                            this.for = 0;
                        }
                        a.this.else[this.i][this.new] = (this.d << 20 | this.j << 10 | this.for);
                        graphics.setColor(new Color(this.d >> 2, this.j >> 2, this.for >> 2));
                        graphics.fillRect(this.i, this.new, 1, 1);
                        this.d = (a.this.else[this.f][this.new] & 0x3FF00000) >> 20;
                        this.j = (a.this.else[this.f][this.new] & 0xFFC00) >> 10;
                        this.for = (a.this.else[this.f][this.new] & 0x3FF);
                        this.d -= (int)(this.if * (this.m >> 2));
                        if (this.d < 0) {
                            this.d = 0;
                        }
                        this.j -= (int)(this.if * (this.l >> 2));
                        if (this.j < 0) {
                            this.j = 0;
                        }
                        this.for -= (int)(this.if * (this.r >> 2));
                        if (this.for < 0) {
                            this.for = 0;
                        }
                        a.this.else[this.f][this.new] = (this.d << 20 | this.j << 10 | this.for);
                        graphics.setColor(new Color(this.d >> 2, this.j >> 2, this.for >> 2));
                        graphics.fillRect(this.f, this.new, 1, 1);
                        this.d = (a.this.else[this.i][this.t] & 0x3FF00000) >> 20;
                        this.j = (a.this.else[this.i][this.t] & 0xFFC00) >> 10;
                        this.for = (a.this.else[this.i][this.t] & 0x3FF);
                        this.d -= (int)(this.q * (this.m >> 2));
                        if (this.d < 0) {
                            this.d = 0;
                        }
                        this.j -= (int)(this.q * (this.l >> 2));
                        if (this.j < 0) {
                            this.j = 0;
                        }
                        this.for -= (int)(this.q * (this.r >> 2));
                        if (this.for < 0) {
                            this.for = 0;
                        }
                        a.this.else[this.i][this.t] = (this.d << 20 | this.j << 10 | this.for);
                        graphics.setColor(new Color(this.d >> 2, this.j >> 2, this.for >> 2));
                        graphics.fillRect(this.i, this.t, 1, 1);
                        this.d = (a.this.else[this.f][this.t] & 0x3FF00000) >> 20;
                        this.j = (a.this.else[this.f][this.t] & 0xFFC00) >> 10;
                        this.for = (a.this.else[this.f][this.t] & 0x3FF);
                        this.d -= (int)(this.s * (this.m >> 2));
                        if (this.d < 0) {
                            this.d = 0;
                        }
                        this.j -= (int)(this.s * (this.l >> 2));
                        if (this.j < 0) {
                            this.j = 0;
                        }
                        this.for -= (int)(this.s * (this.r >> 2));
                        if (this.for < 0) {
                            this.for = 0;
                        }
                        a.this.else[this.f][this.t] = (this.d << 20 | this.j << 10 | this.for);
                        graphics.setColor(new Color(this.d >> 2, this.j >> 2, this.for >> 2));
                        graphics.fillRect(this.f, this.t, 1, 1);
                    }
                    ++this.c;
                }
                ++this.int;
            }
        }
    }
}
