import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.Graphics;
import java.applet.Applet;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.Image;
import java.awt.Toolkit;

// 
// Decompiled by Procyon v0.5.30
// 

public class c
{
    public int[] a;
    public int b;
    public int c;
    public d d;
    public Toolkit e;
    
    public Image a() {
        final Image image = this.e.createImage(this.d);
        this.e.prepareImage(image, -1, -1, null);
        return image;
    }
    
    private int[] a(final Image image) {
        final int width = image.getWidth(null);
        final int height = image.getHeight(null);
        if (width < 0 || height < 0 || image == null) {
            System.err.println("Could not grab pixels: " + "Image is not valid");
            return null;
        }
        final int[] array = new int[width * height];
        final g g = new g(image.getSource(), 0, 0, width, height, array, 0, width);
        try {
            if (!g.a(0L)) {
                g.a(0L);
            }
            if ((g.a() & 0x80) != 0x0) {
                System.err.println("Could not grab pixels: " + "Grabbing aborted");
                return null;
            }
        }
        catch (Exception ex) {
            System.err.println("Could not grab pixels: " + ex);
            return null;
        }
        return array;
    }
    
    public c(final Image image, final Applet applet) {
        this.b = -1;
        this.c = -1;
        final int width = image.getWidth(null);
        final int height = image.getHeight(null);
        final Image image2 = applet.createImage(width, height);
        final Graphics graphics = image2.getGraphics();
        graphics.drawImage(image, 0, 0, null);
        graphics.dispose();
        this.a = this.a(image2);
        image2.flush();
        if (this.a != null) {
            this.b = width;
            this.c = height;
        }
        this.c();
    }
    
    public c(final int b, final int c) {
        this.b = -1;
        this.c = -1;
        this.a = new int[b * c];
        this.b = b;
        this.c = c;
        this.c();
    }
    
    public int[] b() {
        return this.a;
    }
    
    private void c() {
        this.d = new d(this.b, this.c, new DirectColorModel(32, 16711680, 65280, 255), this.a, 0, this.b);
        this.e = Toolkit.getDefaultToolkit();
    }
}
