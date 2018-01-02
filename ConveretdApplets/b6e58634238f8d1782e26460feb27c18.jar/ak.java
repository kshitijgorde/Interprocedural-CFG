import java.awt.Graphics;
import java.io.IOException;
import java.awt.image.ImageObserver;
import java.awt.Dimension;
import java.awt.Image;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

public final class ak extends al implements ad
{
    public URL a;
    public final boolean b;
    public Image c;
    public Dimension d;
    public w e;
    
    public ak(final d d, final boolean b, final URL a, final boolean b2) {
        super(d, b);
        this.a = a;
        this.b = b2;
    }
    
    public final void a(final Image c) {
        this.c = c;
    }
    
    public final Image a() {
        Image image;
        if ((image = this.c) == null) {
            try {
                image = super.a.e().a(this, this.a, this.b);
                this.a(image);
            }
            catch (IOException ex) {}
        }
        return image;
    }
    
    public final void a(final Dimension d) {
        this.d = d;
    }
    
    public final Dimension b() {
        Dimension d;
        final Image a;
        if ((d = this.d) == null && (a = this.a()) != null) {
            d = new Dimension(Math.max(0, a.getWidth(this)), Math.max(0, a.getHeight(this)));
            if (d.width > 0 && d.height > 0) {
                this.a(d);
            }
        }
        if (d == null) {
            d = new Dimension();
        }
        return d;
    }
    
    public final Dimension c() {
        final Image a = this.a();
        final Dimension dimension = new Dimension();
        if (a != null) {
            dimension.width = Math.max(0, a.getWidth(this));
            dimension.height = Math.max(0, a.getHeight(this));
        }
        if (dimension.width > 0) {
            final Dimension dimension2 = dimension;
            dimension2.width += 6;
        }
        if (dimension.height > 0) {
            final Dimension dimension3 = dimension;
            dimension3.height += 2;
        }
        return dimension;
    }
    
    public final void paint(final Graphics graphics) {
        final Dimension size = this.getSize();
        graphics.setColor(this.getBackground());
        graphics.fillRect(0, 0, size.width, size.height);
        final Image a;
        if ((a = this.a()) != null && this.b().width <= size.width + 6 && this.b().height <= size.height + 2) {
            graphics.drawImage(a, 3, 1, this.getBackground(), this);
        }
    }
    
    private void a(final int width) {
        Dimension b;
        if ((b = this.b()) == null) {
            b = new Dimension();
            this.a(b);
        }
        b.width = width;
    }
    
    private void b(final int height) {
        Dimension b;
        if ((b = this.b()) == null) {
            b = new Dimension();
            this.a(b);
        }
        b.height = height;
    }
    
    public final boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        boolean b = image != this.c && this.e != null;
        if (!b) {
            if ((n & 0x1) != 0x0) {
                this.a(Math.max(0, n4));
            }
            if ((n & 0x2) != 0x0) {
                this.b(Math.max(0, n5));
            }
            if ((n & 0x3) != 0x0 && this.e != null) {
                this.e.f();
                this.e.g().repaint();
            }
            if ((n & 0x28) != 0x0 && this.e != null) {
                this.e.g().repaint(this.getBounds().x + n2, this.getBounds().y + n3, n4, n5);
            }
            b = ((n & 0xE0) == 0x0);
        }
        return b;
    }
    
    public final void a(final Image image, final Image image2) {
        this.a(image2);
        if (this.e != null) {
            this.e.g().repaint(this.getBounds().x + 3, this.getBounds().y + 1, this.b().width, this.b().height);
        }
    }
    
    public final void a(final w e) {
        this.e = e;
    }
}
