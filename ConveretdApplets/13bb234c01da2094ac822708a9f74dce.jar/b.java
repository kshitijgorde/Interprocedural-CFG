import com.daysofwonder.applet.z;
import java.awt.Graphics;
import com.daysofwonder.b.a;
import com.daysofwonder.tt.o;
import java.awt.image.ColorModel;
import java.awt.image.IndexColorModel;
import java.awt.Rectangle;
import com.daysofwonder.tt.e;
import java.awt.Toolkit;
import java.awt.image.PixelGrabber;
import com.daysofwonder.util.t;
import java.awt.image.ImageObserver;
import java.util.Vector;
import java.awt.image.DirectColorModel;
import java.awt.image.ImageConsumer;
import java.awt.Image;
import java.awt.image.ImageProducer;

// 
// Decompiled by Procyon v0.5.30
// 

public class b extends R implements ImageProducer
{
    protected Image a;
    protected ImageConsumer b;
    protected DirectColorModel c;
    
    public b(final int n, final int n2, final Image image, final Image image2, final Image image3, final Image image4, final Image image5, final Image image6, final Image image7, final Image image8, final Image image9, final int n3, final int n4, final Vector vector, final int n5, final int n6, final int n7, final double n8, final int n9) {
        super(n, n2, n3, n4, vector, n5, n6, n7, n8, n9);
        this.E = image4.getWidth(null);
        this.F = image4.getHeight(null);
        this.G = this.E >> 1;
        this.H = this.F >> 1;
        this.I = image7.getWidth(null);
        this.J = image7.getHeight(null);
        this.K = this.I >> 1;
        this.L = this.J >> 1;
        com.daysofwonder.util.t.a("beginning grabbing and slicing");
        com.daysofwonder.util.t.a("before grab mem: " + Runtime.getRuntime().totalMemory() + " " + Runtime.getRuntime().freeMemory());
        try {
            final long currentTimeMillis = System.currentTimeMillis();
            final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, image.getWidth(null), image.getHeight(null), true);
            pixelGrabber.grabPixels();
            this.i = this.b((int[])pixelGrabber.getPixels(), n3, n4, image.getWidth(null), image.getHeight(null));
            image.flush();
            final PixelGrabber pixelGrabber2 = new PixelGrabber(image2, 0, 0, image2.getWidth(null), image2.getHeight(null), true);
            pixelGrabber2.grabPixels();
            this.k = this.a((int[])pixelGrabber2.getPixels(), n3, n4, image2.getWidth(null), image2.getHeight(null));
            image2.flush();
            final PixelGrabber pixelGrabber3 = new PixelGrabber(image3, 0, 0, image3.getWidth(null), image3.getHeight(null), true);
            pixelGrabber3.grabPixels();
            this.j = this.a((int[])pixelGrabber3.getPixels(), n3, n4, image3.getWidth(null), image3.getHeight(null));
            image3.flush();
            this.s = new int[this.E * this.F];
            new PixelGrabber(image4, 0, 0, this.E, this.F, this.s, 0, this.E).grabPixels();
            this.u = new int[this.E * this.F];
            new PixelGrabber(image5, 0, 0, this.E, this.F, this.u, 0, this.E).grabPixels();
            final PixelGrabber pixelGrabber4 = new PixelGrabber(image6, 0, 0, this.E, this.F, true);
            pixelGrabber4.grabPixels();
            this.t = this.a((int[])pixelGrabber4.getPixels());
            this.v = new int[this.I * this.J];
            new PixelGrabber(image7, 0, 0, this.I, this.J, this.v, 0, this.I).grabPixels();
            this.a(this.v, this.I, this.J);
            final int[] array = new int[this.I * this.J];
            new PixelGrabber(image8, 0, 0, this.I, this.J, array, 0, this.I).grabPixels();
            this.w = this.b(array, this.I, this.J);
            final int[] array2 = new int[this.I * this.J];
            new PixelGrabber(image9, 0, 0, this.I, this.J, array2, 0, this.I).grabPixels();
            this.x = this.b(array2, this.I, this.J);
            com.daysofwonder.util.t.a("Grabbing images: " + (System.currentTimeMillis() - currentTimeMillis) + " ms");
        }
        catch (Exception ex) {
            com.daysofwonder.util.t.a(ex);
        }
        com.daysofwonder.util.t.a("end of grabbing and slicing");
        com.daysofwonder.util.t.a("after grab mem: " + Runtime.getRuntime().totalMemory() + " " + Runtime.getRuntime().freeMemory());
        this.c = new DirectColorModel(32, 16711680, 65280, 255, 0);
        this.a = Toolkit.getDefaultToolkit().createImage(this);
        com.daysofwonder.util.t.a("after create: " + Runtime.getRuntime().totalMemory() + " " + Runtime.getRuntime().freeMemory());
        System.gc();
        com.daysofwonder.util.t.a("after gc: " + Runtime.getRuntime().totalMemory() + " " + Runtime.getRuntime().freeMemory());
    }
    
    public void a(final Image image) {
        com.daysofwonder.util.t.a("before board grab mem: " + Runtime.getRuntime().totalMemory() + " " + Runtime.getRuntime().freeMemory());
        try {
            this.d = image.getWidth(null);
            this.e = image.getHeight(null);
            com.daysofwonder.util.t.a("setBoard: " + this.d + "," + this.e);
            this.m = new int[this.d * this.e];
            new PixelGrabber(image, 0, 0, this.d, this.e, this.m, 0, this.d).grabPixels();
            image.flush();
        }
        catch (InterruptedException ex) {}
        com.daysofwonder.util.t.a("after board grab mem: " + Runtime.getRuntime().totalMemory() + " " + Runtime.getRuntime().freeMemory());
    }
    
    public void a(final e n, final Image image, final int n2, final int n3, final int n4, final int n5) {
        if (this.n == null) {
            this.n = n;
            final Rectangle a = this.n.a(this.Q);
            this.d = a.width;
            this.e = a.height;
            com.daysofwonder.util.t.a("addToBoard: " + this.d + "," + this.e);
        }
        com.daysofwonder.util.t.a("addToBoard: " + n2 + "," + n3 + " mem: " + Runtime.getRuntime().totalMemory() + " " + Runtime.getRuntime().freeMemory());
        try {
            if (this.m == null) {
                System.gc();
                this.m = new int[this.d * this.e];
            }
            new PixelGrabber(image, 0, 0, n4, n5, this.m, n2 + n3 * this.d, this.d).grabPixels();
        }
        catch (InterruptedException ex) {}
        com.daysofwonder.util.t.a("after board grab mem: " + Runtime.getRuntime().totalMemory() + " " + Runtime.getRuntime().freeMemory());
    }
    
    public void a(final e n, final Image image) {
        this.n = n;
        try {
            final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, this.d, this.e, false);
            pixelGrabber.grabPixels();
            final ColorModel colorModel = pixelGrabber.getColorModel();
            com.daysofwonder.util.t.a("colormodel: " + colorModel);
            this.M = ((IndexColorModel)colorModel).getMapSize() - 1;
            com.daysofwonder.util.t.a("max color index: " + this.M);
            final int size = this.n.j().size();
            final byte[] array = (byte[])pixelGrabber.getPixels();
            for (int i = 0; i < this.e; ++i) {
                final int n2 = i * this.d;
                for (int j = 0; j < this.d; ++j) {
                    final int m = array[j + n2] & 0xFF;
                    this.m[j + n2] = (m << 24 & 0xFF000000) + (this.m[j + n2] & 0xFFFFFF);
                    if (m < size) {
                        final o b = this.n.b(m);
                        if (b != null) {
                            Rectangle b2 = b.b(this.Q);
                            if (b2 == null) {
                                b2 = new Rectangle(j, i, 0, 0);
                            }
                            else {
                                b2.add(j, i);
                            }
                            b.a(this.Q, b2);
                        }
                    }
                    else if (m >= size) {
                        this.M = m;
                    }
                }
            }
            image.flush();
        }
        catch (InterruptedException ex) {}
        this.P = true;
        this.e();
    }
    
    public synchronized void a() {
        if (this.a != null) {
            this.a.flush();
            this.a = null;
        }
        this.m = null;
        this.S = null;
    }
    
    public synchronized void addConsumer(final ImageConsumer b) {
        (this.b = b).setDimensions(this.C, this.D);
        this.b.setHints(30);
        this.b.setColorModel(this.c);
        this.b();
    }
    
    public synchronized boolean isConsumer(final ImageConsumer imageConsumer) {
        return true;
    }
    
    public synchronized void removeConsumer(final ImageConsumer imageConsumer) {
        if (this.b == imageConsumer) {
            this.b = null;
        }
    }
    
    public void startProduction(final ImageConsumer imageConsumer) {
        this.addConsumer(imageConsumer);
    }
    
    public void requestTopDownLeftRightResend(final ImageConsumer imageConsumer) {
    }
    
    public synchronized void a(final a a, final int n, final int n2, final int n3, final int n4) {
        if (!this.P) {
            return;
        }
        if (this.b != null && this.l) {
            this.b.setPixels(0, 0, this.C, this.D, this.c, this.S, 0, this.C);
            this.b.imageComplete(2);
            this.l = false;
        }
        if (this.a != null) {
            ((Graphics)a.h()).drawImage(this.a, n3, n4, null);
        }
    }
    
    public synchronized void b() {
        if (this.b != null && this.l) {
            this.b.setPixels(0, 0, this.C, this.D, this.c, this.S, 0, this.C);
            this.b.imageComplete(2);
            this.l = false;
        }
    }
    
    public synchronized void a(final z z, final int n, final int n2, final int n3, final int n4) {
        if (!this.P) {
            return;
        }
        if (this.b != null && this.l) {
            this.b.setPixels(0, 0, this.C, this.D, this.c, this.S, 0, this.C);
            this.b.imageComplete(2);
            this.l = false;
        }
        if (z != null && this.a != null) {
            ((Graphics)z.d().h()).drawImage(this.a, n3, n4, null);
        }
    }
}
