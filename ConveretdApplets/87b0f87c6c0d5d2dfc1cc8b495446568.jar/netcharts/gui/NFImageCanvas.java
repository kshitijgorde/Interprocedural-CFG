// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.gui;

import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import netcharts.util.NFDebug;
import java.net.URL;
import java.awt.Component;
import netcharts.util.NFImageCache;
import java.applet.Applet;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.Canvas;

public class NFImageCanvas extends Canvas implements ImageObserver
{
    private Image a;
    private String b;
    private String c;
    private Applet d;
    private int e;
    private int f;
    private NFGuiObserver g;
    private NFImageCache h;
    
    public NFImageCanvas(final Applet d) {
        this.a = null;
        this.b = null;
        this.c = "Center";
        this.d = null;
        this.e = 0;
        this.f = 0;
        this.g = null;
        this.h = null;
        this.d = d;
        (this.h = new NFImageCache(this, d)).addObserver(this);
    }
    
    public NFImageCanvas(final Applet applet, final URL url) {
        this(applet);
        this.getImage(url);
    }
    
    public NFImageCanvas(final Applet applet, final String s) {
        this(applet);
        this.getImage(s);
    }
    
    public NFImageCanvas(final Applet applet, final byte[] array) {
        this(applet);
        this.getImage(array);
    }
    
    public void setJustify(final String c) {
        this.c = c;
    }
    
    public void clear() {
        if (this.a != null) {
            this.a = null;
            this.repaint();
        }
    }
    
    public void setImage(final Image a) {
        this.a = a;
        this.repaint();
    }
    
    public Image getImage() {
        return this.a;
    }
    
    public Image getImage(final URL url) {
        this.b = url.toString();
        this.a = this.h.getImage(url);
        if (this.a == null) {
            NFDebug.print("Unable to load " + url);
        }
        return this.a;
    }
    
    public Image getImage(final String b) {
        this.b = b;
        this.a = this.h.getImage(b);
        if (this.a == null) {
            NFDebug.print(65536L, "Unable to load " + b);
        }
        return this.a;
    }
    
    public Image getImage(final byte[] array) {
        this.b = null;
        this.a = this.h.getImage(array);
        if (this.a == null) {
            NFDebug.print(65536L, "Unable to load byte array image");
        }
        return this.a;
    }
    
    public boolean waitForImage(final int n, final int n2) {
        return this.h.waitForImage(this.a, n, n2);
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.size();
        if (this.a == null) {
            return;
        }
        if ((this.checkImage(this.a, null) & 0x20) == 0x0) {
            final String s = "Loading ";
            String s2;
            if (this.b != null) {
                s2 = s + this.b + " ...";
            }
            else {
                s2 = s + "image ...";
            }
            graphics.drawString(s2, 10, size.height / 2);
            return;
        }
        int n = this.a.getWidth(null);
        int n2 = this.a.getHeight(null);
        if (this.c.equals("Tile")) {
            this.a(graphics, this.a, 0, 0, size.width, size.height, n, n2);
            return;
        }
        int n3;
        int n4;
        if (this.c.equals("Left")) {
            n3 = 0;
            n4 = (size.height - n2) / 2;
        }
        else if (this.c.equals("Right")) {
            n3 = size.width - n;
            n4 = (size.height - n2) / 2;
        }
        else if (this.c.equals("Top")) {
            n3 = (size.width - n) / 2;
            n4 = 0;
        }
        else if (this.c.equals("Bottom")) {
            n3 = (size.width - n) / 2;
            n4 = size.height - n2;
        }
        else if (this.c.equals("Fill") || this.c.startsWith("Size")) {
            n3 = 0;
            n4 = 0;
            n = size.width;
            n2 = size.height;
        }
        else if (this.c.equals("MaxSize")) {
            final int width = this.a.getWidth(null);
            final int height = this.a.getHeight(null);
            if (width > height) {
                final float n5 = height / width;
                n = size.width;
                n2 = (int)(n * n5);
                if (n2 > size.height) {
                    final float n6 = width / height;
                    n2 = size.height;
                    n = (int)(n2 * n6);
                }
            }
            else {
                final float n7 = width / height;
                n2 = size.height;
                n = (int)(n2 * n7);
                if (n > size.width) {
                    final float n8 = height / width;
                    n = size.width;
                    n2 = (int)(n * n8);
                }
            }
            n3 = size.width / 2 - n / 2;
            n4 = size.height / 2 - n2 / 2;
        }
        else {
            n3 = (size.width - n) / 2;
            n4 = (size.height - n2) / 2;
        }
        graphics.drawImage(this.a, n3, n4, n, n2, null);
    }
    
    private void a(final Graphics graphics, final Image image, final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        final Graphics create = graphics.create(n, n2, n3, n4);
        final int n7 = n3 / n5 + 1;
        for (int n8 = n4 / n6 + 1, i = 0; i < n8; ++i) {
            for (int j = 0; j < n7; ++j) {
                create.drawImage(image, j * n5, i * n6, null);
            }
        }
        create.dispose();
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if ((n & 0x20) > 0) {
            for (Container container = this.getParent(); container != null; container = container.getParent()) {
                if (container instanceof Container) {
                    container.layout();
                }
            }
            this.repaint();
            if (this.g != null) {
                this.g.valueChanged(this);
            }
            return false;
        }
        return true;
    }
    
    public void setFixedSize(final int e, final int f) {
        this.e = e;
        this.f = f;
    }
    
    public Dimension preferredSize() {
        Dimension size;
        if (this.e > 0 && this.f > 0) {
            size = new Dimension(this.e, this.f);
        }
        else if (this.a != null && this.a.getWidth(null) > 0) {
            size = new Dimension(this.a.getWidth(null), this.a.getHeight(null));
        }
        else {
            size = this.size();
            if (size.width <= 0) {
                size = new Dimension(200, 200);
            }
        }
        return size;
    }
    
    public void addObserver(final NFGuiObserver g) {
        this.g = g;
    }
    
    public void close() {
        if (this.a != null) {
            this.a = null;
        }
    }
    
    public static void main(final String[] array) {
        final Frame frame = new Frame("Image Canvas Test");
        final NFImageCanvas nfImageCanvas = new NFImageCanvas(null, "e:\\nf\\nc\\classes\\netcharts\\images\\netchar1.gif");
        nfImageCanvas.setJustify(array[0]);
        frame.setLayout(new BorderLayout());
        frame.add("North", new Label("Image Canvas Test"));
        frame.add("Center", nfImageCanvas);
        frame.resize(500, 500);
        frame.show();
    }
}
