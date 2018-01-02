import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class fractal extends Applet implements MouseListener, MouseMotionListener
{
    private final int MAX = 256;
    private final double SX = -2.025;
    private final double SY = -1.125;
    private final double EX = 0.6;
    private final double EY = 1.125;
    private static int x1;
    private static int y1;
    private static int xs;
    private static int ys;
    private static int xe;
    private static int ye;
    private static double xstart;
    private static double ystart;
    private static double xende;
    private static double yende;
    private static double xzoom;
    private static double yzoom;
    private static boolean action;
    private static boolean rechteck;
    private static boolean fertig;
    private static float xy;
    private Image bild;
    private Graphics g1;
    private Cursor c1;
    private Cursor c2;
    
    public void init() {
        fractal.fertig = false;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.c1 = new Cursor(3);
        this.c2 = new Cursor(1);
        fractal.x1 = this.getSize().width;
        fractal.y1 = this.getSize().height;
        fractal.xy = fractal.x1 / fractal.y1;
        this.bild = this.createImage(fractal.x1, fractal.y1);
        this.g1 = this.bild.getGraphics();
        fractal.fertig = true;
    }
    
    public void destroy() {
        if (fractal.fertig) {
            this.removeMouseListener(this);
            this.removeMouseMotionListener(this);
            this.bild = null;
            this.g1 = null;
            this.c1 = null;
            this.c2 = null;
            System.gc();
        }
    }
    
    public void start() {
        fractal.action = false;
        fractal.rechteck = false;
        this.startwerte();
        fractal.xzoom = (fractal.xende - fractal.xstart) / fractal.x1;
        fractal.yzoom = (fractal.yende - fractal.ystart) / fractal.y1;
        this.mandelbrot();
    }
    
    public void stop() {
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public void update(final Graphics graphics) {
        graphics.drawImage(this.bild, 0, 0, this);
        if (fractal.rechteck) {
            graphics.setColor(Color.white);
            if (fractal.xs < fractal.xe) {
                if (fractal.ys < fractal.ye) {
                    graphics.drawRect(fractal.xs, fractal.ys, fractal.xe - fractal.xs, fractal.ye - fractal.ys);
                }
                else {
                    graphics.drawRect(fractal.xs, fractal.ye, fractal.xe - fractal.xs, fractal.ys - fractal.ye);
                }
            }
            else if (fractal.ys < fractal.ye) {
                graphics.drawRect(fractal.xe, fractal.ys, fractal.xs - fractal.xe, fractal.ye - fractal.ys);
            }
            else {
                graphics.drawRect(fractal.xe, fractal.ye, fractal.xs - fractal.xe, fractal.ys - fractal.ye);
            }
        }
    }
    
    private void mandelbrot() {
        float n = 0.0f;
        fractal.action = false;
        this.setCursor(this.c1);
        this.showStatus("Mandelbrot-Set will be produced - please wait...");
        for (int i = 0; i < fractal.x1; i += 2) {
            for (int j = 0; j < fractal.y1; ++j) {
                final float punktfarbe = this.punktfarbe(fractal.xstart + fractal.xzoom * i, fractal.ystart + fractal.yzoom * j);
                if (punktfarbe != n) {
                    this.g1.setColor(Color.getHSBColor(punktfarbe, 0.8f, 1.0f - punktfarbe * punktfarbe));
                    n = punktfarbe;
                }
                this.g1.drawLine(i, j, i + 1, j);
            }
        }
        this.showStatus("Mandelbrot-Set ready - please select zoom area with pressed mouse.");
        this.setCursor(this.c2);
        fractal.action = true;
    }
    
    private float punktfarbe(final double n, final double n2) {
        double n3;
        double n4;
        double n5;
        int n6;
        for (n3 = 0.0, n4 = 0.0, n5 = 0.0, n6 = 0; n6 < 256 && n5 < 4.0; ++n6, n5 = n3 * n3 - n4 * n4, n4 = 2.0 * n3 * n4 + n2, n3 = n5 + n) {}
        return n6 / 256.0f;
    }
    
    private void startwerte() {
        fractal.xstart = -2.025;
        fractal.ystart = -1.125;
        fractal.xende = 0.6;
        fractal.yende = 1.125;
        if ((float)((fractal.xende - fractal.xstart) / (fractal.yende - fractal.ystart)) != fractal.xy) {
            fractal.xstart = fractal.xende - (fractal.yende - fractal.ystart) * fractal.xy;
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        mouseEvent.consume();
        if (fractal.action) {
            fractal.xs = mouseEvent.getX();
            fractal.ys = mouseEvent.getY();
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        mouseEvent.consume();
        if (fractal.action) {
            fractal.xe = mouseEvent.getX();
            fractal.ye = mouseEvent.getY();
            if (fractal.xs > fractal.xe) {
                final int xs = fractal.xs;
                fractal.xs = fractal.xe;
                fractal.xe = xs;
            }
            if (fractal.ys > fractal.ye) {
                final int ys = fractal.ys;
                fractal.ys = fractal.ye;
                fractal.ye = ys;
            }
            final int n = fractal.xe - fractal.xs;
            final int n2 = fractal.ye - fractal.ys;
            if (n < 2 && n2 < 2) {
                this.startwerte();
            }
            else {
                if (n > n2 * fractal.xy) {
                    fractal.ye = (int)(fractal.ys + n / fractal.xy);
                }
                else {
                    fractal.xe = (int)(fractal.xs + n2 * fractal.xy);
                }
                fractal.xende = fractal.xstart + fractal.xzoom * fractal.xe;
                fractal.yende = fractal.ystart + fractal.yzoom * fractal.ye;
                fractal.xstart += fractal.xzoom * fractal.xs;
                fractal.ystart += fractal.yzoom * fractal.ys;
            }
            fractal.xzoom = (fractal.xende - fractal.xstart) / fractal.x1;
            fractal.yzoom = (fractal.yende - fractal.ystart) / fractal.y1;
            this.mandelbrot();
            fractal.rechteck = false;
            this.repaint();
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        mouseEvent.consume();
        if (fractal.action) {
            fractal.xe = mouseEvent.getX();
            fractal.ye = mouseEvent.getY();
            fractal.rechteck = true;
            this.repaint();
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    public String getAppletInfo() {
        return "fractal.class - Mandelbrot Set a Java Applet by Eckhard Roessel 2000-2001";
    }
}
