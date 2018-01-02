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
    Thread j;
    Image h;
    Image null;
    Image for;
    Image[] goto;
    Graphics case;
    Graphics void;
    MediaTracker int;
    int else;
    int e;
    int[][] long;
    int[][] do;
    int[][] char;
    int byte;
    boolean[][] new;
    boolean i;
    boolean c;
    double d;
    double if;
    int b;
    int a;
    int g;
    boolean f;
    int try;
    
    public a() {
        this.else = 15;
        this.e = 0;
        this.byte = 100;
        this.d = 0.5;
        this.f = true;
        this.try = 0;
    }
    
    public void init() {
        this.b = this.size().width;
        this.a = this.size().height;
        this.g = Integer.parseInt(this.getParameter("delay"));
        this.else = Integer.parseInt(this.getParameter("frames"));
        this.d = 0.007 * Integer.parseInt(this.getParameter("size"));
        this.if = 3.0E-4 * Integer.parseInt(this.getParameter("number"));
        if (this.getParameter("animatebrightness").charAt(0) == 'y') {
            this.c = true;
        }
        else {
            this.c = false;
        }
        this.goto = new Image[this.else];
        this.long = new int[this.b][this.a];
        this.do = new int[this.b][this.a];
        this.new = new boolean[this.b][this.a];
        this.char = new int[this.byte][this.byte];
        this.h = this.createImage(this.b, this.a);
        this.case = this.h.getGraphics();
        this.addMouseListener(new a());
        this.addMouseMotionListener(new b());
    }
    
    public void start() {
        if (this.j == null) {
            (this.j = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.j != null) {
            this.j.stop();
            this.j = null;
            this.f = false;
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.try == 3) {
            graphics.drawImage(this.goto[this.e], 0, 0, this);
        }
        else if (this.try != 0) {
            graphics.drawImage(this.h, 0, 0, this);
        }
    }
    
    public void run() {
        final int n = this.byte / 2;
        this.case.setColor(Color.black);
        this.case.fillRect(0, 0, this.b, this.a);
        this.case.setColor(Color.white);
        this.case.drawString("Loading images...", 10, 20);
        this.repaint();
        this.try = 1;
        this.int = new MediaTracker(this);
        this.null = this.getImage(this.getDocumentBase(), this.getParameter("picName"));
        this.int.addImage(this.null, 0);
        this.int.checkID(0, true);
        this.for = this.getImage(this.getDocumentBase(), this.getParameter("starPicName"));
        this.int.addImage(this.for, 1);
        this.int.checkID(1, true);
        while (!this.int.checkAll()) {
            try {
                Thread.sleep(50L);
            }
            catch (InterruptedException ex) {}
        }
        this.case.setColor(Color.black);
        this.case.fillRect(0, 0, this.b, this.a);
        this.case.setColor(Color.white);
        this.case.drawString("Precalculating...", 10, 20);
        this.repaint();
        this.a();
        for (int i = 0; i < this.a; ++i) {
            for (int j = 0; j < this.b; ++j) {
                if (Math.random() < this.long[j][i] / 255.0 * this.if) {
                    this.new[j][i] = true;
                    this.long[j][i] = (int)(35.0 + 220.0 * Math.random());
                }
                else {
                    this.new[j][i] = false;
                }
            }
        }
        this.try = 2;
        this.case.drawRect(10, 30, this.b - 20, 10);
        for (int k = 0; k < this.else; ++k) {
            this.goto[k] = this.createImage(this.b, this.a);
            this.void = this.goto[k].getGraphics();
            for (int l = 0; l < this.a; ++l) {
                for (int n2 = 0; n2 < this.b; ++n2) {
                    this.do[n2][l] = 0;
                }
            }
            this.case.setColor(Color.black);
            this.case.fillRect(10, 50, this.b - 20, 10);
            this.case.setColor(Color.white);
            this.case.drawRect(10, 50, this.b - 20, 10);
            for (int n3 = 0; n3 < this.a; ++n3) {
                for (int n4 = 0; n4 < this.b; ++n4) {
                    if (this.new[n4][n3]) {
                        final double n5 = this.d * this.long[n4][n3] / 255.0;
                        final double n6 = 1.0 / n5;
                        final double n7 = this.byte * n5 * 1.414 / 2.0;
                        final double n8 = n4 + n3 + ((n4 + n3) % 2 * 2 - 1) * k * 3.141592653589793 / (2.0 * this.else);
                        double n9;
                        if (this.c) {
                            n9 = (Math.sin(n4 + n3 + 6.283185307179586 * k / this.else) * 0.5 + 0.5) * 0.7 + 0.3;
                        }
                        else {
                            n9 = 1.0;
                        }
                        final double cos = Math.cos(n8);
                        final double sin = Math.sin(n8);
                        final int n10 = (int)(n4 - n7);
                        final int n11 = (int)(n4 + n7);
                        final int n12 = (int)(n3 - n7);
                        final int n13 = (int)(n3 + n7);
                        int n14 = 0;
                        for (int n15 = n12; n15 < n13; ++n15) {
                            int n16 = 0;
                            for (int n17 = n10; n17 < n11; ++n17) {
                                if (n17 >= 0 && n15 >= 0 && n17 < this.b && n15 < this.a) {
                                    final double n18 = n16 - n7;
                                    final double n19 = n14 - n7;
                                    final int n20 = (int)((n18 * cos - n19 * sin) * n6) + n;
                                    final int n21 = (int)((n19 * cos + n18 * sin) * n6) + n;
                                    if (n20 >= 0 && n21 >= 0 && n20 < this.byte && n21 < this.byte && this.char[n20][n21] > 5) {
                                        final int n22 = (this.do[n17][n15] & 0xFF0000) >> 16;
                                        final int n23 = (this.do[n17][n15] & 0xFF00) >> 8;
                                        final int n24 = this.do[n17][n15] & 0xFF;
                                        final int n25 = (int)(n9 * this.char[n20][n21]);
                                        int n26 = n22 + (n25 << 1) / 2;
                                        int n27 = n23 + (n25 << 1) / 3;
                                        int n28 = n24 + (n25 >> 1);
                                        if (n26 > 255) {
                                            n26 = 255;
                                            if (n27 > 255) {
                                                n27 = 255;
                                                if (n28 > 255) {
                                                    n28 = 255;
                                                }
                                            }
                                        }
                                        this.do[n17][n15] = (n26 << 16 | n27 << 8 | n28);
                                    }
                                }
                                ++n16;
                            }
                            ++n14;
                        }
                    }
                }
                if (n3 % 10 == 0) {
                    this.case.fillRect(10, 50, (int)(1.0 * n3 / this.a * (this.b - 20)) + 1, 10);
                    this.repaint();
                }
            }
            this.void.setColor(Color.black);
            this.void.fillRect(0, 0, this.b, this.a);
            for (int n29 = 0; n29 < this.a; ++n29) {
                for (int n30 = 0; n30 < this.b; ++n30) {
                    if (this.do[n30][n29] > 0) {
                        this.void.setColor(new Color(this.do[n30][n29]));
                        this.void.fillRect(n30, n29, 1, 1);
                    }
                }
                this.void.setColor(Color.black);
                this.void.drawString("www.eigelb.at", 8, this.a - 6);
                this.void.drawString("www.eigelb.at", 8, this.a - 8);
                this.void.drawString("www.eigelb.at", 6, this.a - 6);
                this.void.drawString("www.eigelb.at", 6, this.a - 8);
                this.void.setColor(Color.white);
                this.void.drawString("www.eigelb.at", 7, this.a - 7);
            }
            this.case.fillRect(10, 30, (int)((1.0 + k) / this.else * (this.b - 20)), 10);
            this.repaint();
        }
        this.try = 3;
        this.e = 0;
        while (this.f) {
            this.e = (this.e + 1) % this.else;
            this.repaint();
            try {
                Thread.sleep(this.g);
            }
            catch (InterruptedException ex2) {}
        }
    }
    
    public void a() {
        final ColorModel rgBdefault = ColorModel.getRGBdefault();
        final int n = this.b * this.a;
        final int[] array = new int[n];
        final PixelGrabber pixelGrabber = new PixelGrabber(this.null, 0, 0, this.b, this.a, array, 0, this.b);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {
            System.err.println("interrupted waiting for pixels!");
            return;
        }
        for (int i = 0; i < n; ++i) {
            this.long[i % this.b][i / this.b] = rgBdefault.getGreen(array[i]);
        }
        final int n2 = this.byte * this.byte;
        final int[] array2 = new int[n2];
        final PixelGrabber pixelGrabber2 = new PixelGrabber(this.for, 0, 0, this.byte, this.byte, array2, 0, this.byte);
        try {
            pixelGrabber2.grabPixels();
        }
        catch (InterruptedException ex2) {
            System.err.println("interrupted waiting for pixels!");
            return;
        }
        for (int j = 0; j < n2; ++j) {
            this.char[j % this.byte][j / this.byte] = rgBdefault.getGreen(array2[j]);
        }
    }
    
    class b extends MouseMotionAdapter
    {
        public void mouseMoved(final MouseEvent mouseEvent) {
            if (mouseEvent.getY() > a.this.a - 24 && mouseEvent.getX() < 90) {
                if (!a.this.i) {
                    a.this.setCursor(new Cursor(12));
                    a.this.i = true;
                }
            }
            else if (a.this.i) {
                a.this.setCursor(new Cursor(0));
                a.this.i = false;
            }
        }
    }
    
    class a extends MouseAdapter
    {
        public void mousePressed(final MouseEvent mouseEvent) {
            if (mouseEvent.getY() > a.this.a - 24 && mouseEvent.getX() < 90) {
                try {
                    a.this.getAppletContext().showDocument(new URL("http://www.eigelb.at"), "_blank");
                }
                catch (MalformedURLException ex) {}
            }
        }
    }
}
