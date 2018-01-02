import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import java.awt.MediaTracker;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.image.ImageProducer;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class else
{
    int[] d;
    boolean e;
    boolean f;
    Component g;
    ImageProducer h;
    Dimension i;
    Image[] j;
    Image[] k;
    Image[] l;
    Image[] m;
    Image[] n;
    Image[] o;
    Image[] p;
    Image[] q;
    Image[] r;
    Image[] s;
    Image[] t;
    
    public else(final Image image, final boolean e, final boolean f, final Dimension i, final Component g) {
        this.d = new int[] { 4352, 5376, 532610, 533634, 598162, 598674, 696490, 697514, 699050 };
        this.e = e;
        this.f = f;
        this.g = g;
        this.i = i;
        this.h = image.getSource();
        this._();
    }
    
    protected abstract void _();
    
    protected final Image[] b(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        final Image[] array = new Image[n5 * n6];
        final MediaTracker mediaTracker = new MediaTracker(this.g);
        int i = 0;
        int n7 = 0;
        while (i < n5) {
            for (int j = 0; j < n6; ++j, ++n7) {
                mediaTracker.addImage(array[n7] = this.g.createImage(new FilteredImageSource(this.h, new CropImageFilter(n + i * n3, n2 + j * n4, n3, n4))), n7);
                try {
                    mediaTracker.waitForID(n7);
                }
                catch (InterruptedException ex) {}
            }
            ++i;
        }
        return array;
    }
    
    public final Image[][] _() {
        final Image[][] array = new Image[4][];
        array[0] = this.a(this.l, this.o, this.q[0]);
        array[1] = this.a(this.k, this.o, this.q[1]);
        array[2] = this.a(this.m, this.n, this.q[2]);
        array[3] = this.a(this.j, this.n, this.q[3]);
        return array;
    }
    
    protected final Image[] a(final Image[] array, final Image[] array2, final Image image) {
        final Image[] array3 = new Image[13];
        for (int i = 0; i < 13; ++i) {
            array3[i] = this._(i, array, array2, image);
        }
        return array3;
    }
    
    protected Image _(final int n, final Image[] array, final Image[] array2, final Image image) {
        final Image image2 = this.g.createImage(this.i.width, this.i.height);
        final Graphics graphics = image2.getGraphics();
        graphics.drawImage(this.t[0], 0, 0, this.g);
        graphics.setColor(Color.white);
        graphics.fillRect(2, 2, this.i.width - 4, this.i.height - 4);
        graphics.drawImage(array2[n], 2, 2, this.g);
        final int width = array2[n].getWidth(this.g);
        final int height = array2[n].getHeight(this.g);
        graphics.drawImage(array2[n], this.i.width - width - 2, this.i.height - height - 2, this.g);
        final int width2 = array[2].getWidth(this.g);
        final int height2 = array[2].getHeight(this.g);
        graphics.drawImage(array[2], 1, 3 + height, this.g);
        graphics.drawImage(array[3], this.i.width - width2 - 1, this.i.height - height - height2 - 3, this.g);
        if (n == 0) {
            graphics.drawImage(image, (this.i.width - image.getWidth(this.g)) / 2, (this.i.height - image.getHeight(this.g)) / 2, this.g);
        }
        else if (n < 10) {
            if ((this.d[n - 1] & 0x2) != 0x0) {
                graphics.drawImage(array[0], 11, 8, this.g);
            }
            if ((this.d[n - 1] & 0x4) != 0x0) {
                graphics.drawImage(array[0], 11, 18, this.g);
            }
            if ((this.d[n - 1] & 0x8) != 0x0) {
                graphics.drawImage(array[0], 11, 21, this.g);
            }
            if ((this.d[n - 1] & 0x10) != 0x0) {
                graphics.drawImage(array[0], 11, 28, this.g);
            }
            if ((this.d[n - 1] & 0x20) != 0x0) {
                graphics.drawImage(array[1], 11, 35, this.g);
            }
            if ((this.d[n - 1] & 0x40) != 0x0) {
                graphics.drawImage(array[1], 11, 38, this.g);
            }
            if ((this.d[n - 1] & 0x80) != 0x0) {
                graphics.drawImage(array[1], 11, 48, this.g);
            }
            if ((this.d[n - 1] & 0x100) != 0x0) {
                graphics.drawImage(array[0], 21, 8, this.g);
            }
            if ((this.d[n - 1] & 0x200) != 0x0) {
                graphics.drawImage(array[0], 21, 18, this.g);
            }
            if ((this.d[n - 1] & 0x400) != 0x0) {
                graphics.drawImage(array[0], 21, 28, this.g);
            }
            if ((this.d[n - 1] & 0x800) != 0x0) {
                graphics.drawImage(array[1], 21, 38, this.g);
            }
            if ((this.d[n - 1] & 0x1000) != 0x0) {
                graphics.drawImage(array[1], 21, 48, this.g);
            }
            if ((this.d[n - 1] & 0x2000) != 0x0) {
                graphics.drawImage(array[0], 31, 8, this.g);
            }
            if ((this.d[n - 1] & 0x4000) != 0x0) {
                graphics.drawImage(array[0], 31, 18, this.g);
            }
            if ((this.d[n - 1] & 0x8000) != 0x0) {
                graphics.drawImage(array[0], 31, 21, this.g);
            }
            if ((this.d[n - 1] & 0x10000) != 0x0) {
                graphics.drawImage(array[0], 31, 28, this.g);
            }
            if ((this.d[n - 1] & 0x20000) != 0x0) {
                graphics.drawImage(array[1], 31, 35, this.g);
            }
            if ((this.d[n - 1] & 0x40000) != 0x0) {
                graphics.drawImage(array[1], 31, 38, this.g);
            }
            if ((this.d[n - 1] & 0x80000) != 0x0) {
                graphics.drawImage(array[1], 31, 48, this.g);
            }
        }
        else {
            if (n == 10) {
                graphics.drawImage(this.p[0], 10, 10, this.g);
            }
            if (n == 11) {
                graphics.drawImage(this.p[1], 10, 10, this.g);
            }
            if (n == 12) {
                graphics.drawImage(this.p[2], 10, 10, this.g);
            }
        }
        return image2;
    }
    
    public Image _() {
        return this.s[0];
    }
    
    public Image a() {
        return this.r[0];
    }
    
    public void a() {
    }
}
