import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.MouseAdapter;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.PixelGrabber;
import java.awt.image.ColorModel;
import java.awt.Component;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.MediaTracker;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class a extends Applet implements Runnable
{
    Thread s;
    Image q;
    Image try;
    Graphics long;
    MediaTracker byte;
    int goto;
    double g;
    double f;
    double e;
    double m;
    double j;
    double h;
    int[] new;
    int[] c;
    int[] int;
    int[] b;
    int[] if;
    int a;
    char[] for;
    String[] null;
    String l;
    int d;
    int else;
    int void;
    int r;
    int char;
    boolean p;
    int i;
    int do;
    int o;
    int k;
    boolean n;
    int case;
    
    public a() {
        this.n = true;
        this.case = 0;
    }
    
    public void init() {
        this.i = this.size().width;
        this.do = this.size().height;
        this.g = 0.01 * Integer.parseInt(this.getParameter("sin1"));
        this.f = 0.01 * Integer.parseInt(this.getParameter("sin2"));
        this.e = 0.01 * Integer.parseInt(this.getParameter("sin3"));
        this.m = 0.01 * Integer.parseInt(this.getParameter("sinb1"));
        this.j = 0.01 * Integer.parseInt(this.getParameter("sinb2"));
        this.h = 0.01 * Integer.parseInt(this.getParameter("sinb3"));
        this.goto = Integer.parseInt(this.getParameter("LetterSpacing"));
        this.k = Integer.parseInt(this.getParameter("speed"));
        this.o = Integer.parseInt(this.getParameter("delay"));
        this.void = Integer.parseInt(this.getParameter("GridSize"));
        this.l = this.getParameter("Text");
        this.if = new int[this.l.length()];
        this.a = this.l.length();
        this.d = 0;
        for (int n = 0; n < 10 && this.getParameter("FontLayoutRow" + (n + 1)) != null; ++n) {
            ++this.d;
        }
        this.null = new String[this.d];
        this.else = 0;
        int length = 0;
        for (int i = 0; i < this.d; ++i) {
            this.null[i] = this.getParameter("FontLayoutRow" + (i + 1));
            this.else += this.null[i].length();
            if (this.null[i].length() > length) {
                length = this.null[i].length();
            }
        }
        this.new = new int[this.else];
        this.c = new int[this.else];
        this.int = new int[this.else];
        this.b = new int[this.else];
        this.for = new char[this.else];
        this.r = length * this.void;
        this.char = this.d * this.void;
        this.else = 0;
        for (int j = 0; j < this.d; ++j) {
            for (int k = 0; k < this.null[j].length(); ++k) {
                this.for[this.else + k] = this.null[j].charAt(k);
            }
            this.else += this.null[j].length();
        }
        for (int l = 0; l < this.a; ++l) {
            this.if[l] = -1;
            for (int n2 = 0; n2 < this.else; ++n2) {
                if (this.for[n2] == this.l.charAt(l)) {
                    this.if[l] = n2;
                    break;
                }
            }
        }
        this.q = this.createImage(this.i, this.do);
        this.long = this.q.getGraphics();
        this.addMouseListener(new a());
        this.addMouseMotionListener(new b());
    }
    
    public void start() {
        if (this.s == null) {
            (this.s = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.s != null) {
            this.s.stop();
            this.s = null;
            this.n = false;
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.case > 0) {
            graphics.drawImage(this.q, 0, 0, this);
        }
    }
    
    public void run() {
        this.long.setColor(Color.black);
        this.long.fillRect(0, 0, this.i, this.do);
        this.long.setColor(Color.white);
        this.long.drawString("Loading image...", 10, 20);
        this.repaint();
        this.case = 1;
        this.byte = new MediaTracker(this);
        this.try = this.getImage(this.getDocumentBase(), this.getParameter("FontImage"));
        this.byte.addImage(this.try, 0);
        this.byte.checkID(0, true);
        while (!this.byte.checkAll()) {
            try {
                Thread.sleep(50L);
            }
            catch (InterruptedException ex) {}
        }
        this.long.setColor(Color.black);
        this.long.fillRect(0, 0, this.i, this.do);
        this.long.setColor(Color.white);
        this.long.drawString("Precalculating...", 10, 20);
        this.repaint();
        this.a();
        this.case = 3;
        int n = this.i;
        while (this.n) {
            this.long.setColor(Color.black);
            this.long.fillRect(0, 0, this.i, this.do);
            int n2 = n;
            for (int i = 0; i < this.a; ++i) {
                if (n2 > -this.void && n2 < this.i && this.if[i] >= 0) {
                    this.a(this.if[i], n2, (this.do - this.void) / 2 + (int)(20.0 * this.g * Math.sin(0.03 * this.f * n2 + 0.3 * this.e * i) + 20.0 * this.m * Math.sin(0.03 * this.j * n2 + 0.3 * this.h * i)), this.long);
                }
                if (this.if[i] >= 0) {
                    n2 += this.int[this.if[i]] - this.new[this.if[i]] + this.goto;
                }
                else {
                    n2 += 20 + this.goto;
                }
            }
            if (n2 <= -this.void) {
                n = this.i;
            }
            this.long.setColor(Color.black);
            this.long.drawString("www.eigelb.at", 8, this.do - 6);
            this.long.drawString("www.eigelb.at", 8, this.do - 8);
            this.long.drawString("www.eigelb.at", 6, this.do - 6);
            this.long.drawString("www.eigelb.at", 6, this.do - 8);
            this.long.setColor(Color.white);
            this.long.drawString("www.eigelb.at", 7, this.do - 7);
            n -= this.k;
            this.repaint();
            try {
                Thread.sleep(this.o);
            }
            catch (InterruptedException ex2) {}
        }
    }
    
    public void a() {
        final ColorModel rgBdefault = ColorModel.getRGBdefault();
        final int n = this.r * this.char;
        final int[] array = new int[n];
        final boolean[][] array2 = new boolean[this.r][this.char];
        final PixelGrabber pixelGrabber = new PixelGrabber(this.try, 0, 0, this.r, this.char, array, 0, this.r);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {
            System.err.println("interrupted waiting for pixels!");
            return;
        }
        for (int i = 0; i < n; ++i) {
            if (rgBdefault.getRed(array[i]) + rgBdefault.getGreen(array[i]) + rgBdefault.getBlue(array[i]) > 78) {
                array2[i % this.r][i / this.r] = true;
            }
            else {
                array2[i % this.r][i / this.r] = false;
            }
        }
        int n2 = 0;
        for (int j = 0; j < this.d; ++j) {
            for (int k = 0; k < this.null[j].length(); ++k) {
                this.c[n2 + k] = j * this.void;
                this.b[n2 + k] = this.c[n2 + k] + this.void - 1;
                this.new[n2 + k] = k * this.void;
                this.int[n2 + k] = this.new[n2 + k] + this.void - 1;
                int n3 = 0;
                for (int l = 0; l < this.void; ++l) {
                    boolean b = true;
                    for (int n4 = this.c[n2 + k]; n4 <= this.b[n2 + k]; ++n4) {
                        if (array2[this.new[n2 + k] + l][n4]) {
                            b = false;
                            break;
                        }
                        if (this.for[n2 + k] == 'I') {}
                    }
                    if (!b) {
                        break;
                    }
                    ++n3;
                }
                final int[] new1 = this.new;
                final int n5 = n2 + k;
                new1[n5] += n3;
                int n6 = 0;
                for (int n7 = 0; n7 < this.void; ++n7) {
                    boolean b2 = true;
                    for (int n8 = this.c[n2 + k]; n8 <= this.b[n2 + k]; ++n8) {
                        if (array2[this.int[n2 + k] - n7][n8]) {
                            b2 = false;
                            break;
                        }
                    }
                    if (!b2) {
                        break;
                    }
                    ++n6;
                }
                final int[] int1 = this.int;
                final int n9 = n2 + k;
                int1[n9] -= n6;
            }
            n2 += this.null[j].length();
        }
    }
    
    public void a(final int n, final int n2, final int n3, final Graphics graphics) {
        graphics.drawImage(this.try, n2, n3, n2 + this.int[n] - this.new[n], n3 + this.void - 1, this.new[n], this.c[n], this.int[n], this.b[n], null);
    }
    
    class b extends MouseMotionAdapter
    {
        public void mouseMoved(final MouseEvent mouseEvent) {
            if (mouseEvent.getY() > a.this.do - 24 && mouseEvent.getX() < 90) {
                if (!a.this.p) {
                    a.this.setCursor(new Cursor(12));
                    a.this.p = true;
                }
            }
            else if (a.this.p) {
                a.this.setCursor(new Cursor(0));
                a.this.p = false;
            }
        }
    }
    
    class a extends MouseAdapter
    {
        public void mousePressed(final MouseEvent mouseEvent) {
            if (mouseEvent.getY() > a.this.do - 24 && mouseEvent.getX() < 90) {
                try {
                    a.this.getAppletContext().showDocument(new URL("http://www.eigelb.at"), "_blank");
                }
                catch (MalformedURLException ex) {}
            }
        }
    }
}
