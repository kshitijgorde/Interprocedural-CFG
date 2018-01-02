import java.awt.Container;
import java.awt.Frame;
import java.net.MalformedURLException;
import java.awt.Event;
import java.awt.FontMetrics;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Image;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public final class o extends Panel
{
    public d a;
    public Image b;
    public boolean c;
    public Image d;
    public String[] e;
    public String f;
    public Rectangle g;
    public String h;
    public Rectangle i;
    public static final Font j;
    public static final Color k;
    public static final Color l;
    
    public o(final d a) {
        this.e = new String[] { "" };
        this.f = null;
        this.h = "";
        this.a = a;
        if (this.a.q) {
            this.setBackground(this.a.i().c);
            this.a.d();
            this.setForeground(f.a(this.getBackground()));
            this.setFont(this.a.i().o);
            if ((this.d = this.a.e().a) != null) {
                final MediaTracker mediaTracker = new MediaTracker(this);
                mediaTracker.addImage(this.d, 0);
                try {
                    mediaTracker.waitForAll(0L);
                }
                catch (InterruptedException ex) {}
                if (mediaTracker.isErrorAny()) {
                    this.d = null;
                }
            }
        }
        this.resize(this.a.s.GetSize());
        this.a("Loading Heatmaps®...");
    }
    
    public final void reshape(final int n, final int n2, final int n3, final int n4) {
        synchronized (this) {
            final Dimension size = this.size();
            if (size.width != n3 || size.height != n4) {
                this.c = true;
            }
            super.reshape(n, n2, n3, n4);
        }
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final void paint(final Graphics graphics) {
        if (this.a == null) {
            super.paint(graphics);
            return;
        }
        synchronized (this) {
            final Dimension size = this.size();
            if ((this.b == null || this.b.getWidth(this) < size.width || this.b.getHeight(this) < size.height) && size.width > 0 && size.height > 0) {
                if (this.b != null) {
                    this.b.flush();
                    this.b = null;
                    this.a.d();
                    f.b();
                }
                this.b = this.createImage(size.width, size.height);
                this.c = true;
            }
            if (this.c && size.width > 0 && size.height > 0) {
                final Graphics graphics2 = this.b.getGraphics();
                final Rectangle clipRect = graphics.getClipRect();
                graphics2.clipRect(clipRect.x, clipRect.y, clipRect.width, clipRect.height);
                this.a(graphics2);
                graphics2.dispose();
                this.c = false;
            }
            graphics.drawImage(this.b, 0, 0, this);
        }
    }
    
    public final void a(final Graphics graphics) {
        final int n = 1;
        final int n2 = 1;
        final int x = 1;
        final int n3 = 1;
        int n4 = 0;
        final Dimension size = this.size();
        graphics.setColor(this.getBackground());
        graphics.fillRect(0, 0, size.width, size.height);
        final FontMetrics fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(this.getFont());
        if (this.i == null) {
            this.i = new Rectangle();
        }
        this.i.width = Math.min(size.width, x + fontMetrics.stringWidth(this.h) + n3);
        this.i.x = Math.max(0, size.width - this.i.width) / 2;
        this.i.height = Math.min(size.height, n + fontMetrics.getAscent() + fontMetrics.getMaxDecent() + n2);
        this.i.y = Math.max(0, size.height - this.i.height);
        final FontMetrics fontMetrics2 = Toolkit.getDefaultToolkit().getFontMetrics(o.j);
        if (this.g == null || this.e[0].length() == 0) {
            this.g = new Rectangle();
        }
        if (this.e[0].length() > 0) {
            for (int i = 0; i < this.e.length; ++i) {
                n4 = Math.max(n4, fontMetrics2.stringWidth(this.e[i]));
            }
            n4 = Math.max(Math.min((int)(n4 * 1.5), size.width - (x + n3)), n4);
            final int n5 = (fontMetrics2.getAscent() + fontMetrics2.getMaxDecent()) * this.e.length;
            this.g.width = Math.min(size.width, x + n4 + n3);
            this.g.x = Math.max(0, size.width - this.g.width) / 2;
            this.g.height = Math.min(size.height, n + n5 + n2);
            this.g.y = Math.max(0, (size.height - (this.g.height + this.i.height)) / 2);
            if (this.g.height < n + n5 + n2 || this.g.width < x + n4 + n3) {
                this.g.height = 0;
            }
        }
        if (this.d != null) {
            final Rectangle rectangle = new Rectangle();
            rectangle.width = Math.min(size.width, this.d.getWidth(this));
            rectangle.x = Math.max(0, size.width - rectangle.width) / 2;
            rectangle.height = Math.min(size.height, this.d.getHeight(this));
            if (rectangle.height + this.g.height + this.i.height > size.height) {
                rectangle.height = Math.max(0, size.height - (this.i.height + this.g.height));
            }
            final int n6 = rectangle.height + this.g.height + this.i.height;
            if (this.g.height > 0) {
                final int max = Math.max(0, (size.height - n6) / 4);
                this.g.y = Math.max(0, rectangle.height + max * 3);
                rectangle.y = max * 2;
            }
            else {
                rectangle.y = Math.max(0, (size.height - n6) / 2);
            }
            graphics.drawImage(this.d, Math.min(size.width, rectangle.x), Math.min(size.height, rectangle.y), rectangle.width, rectangle.height, this);
        }
        if (this.e[0].length() > 0 && this.g.height > 0) {
            graphics.setColor(o.l);
            graphics.fillRect(this.g.x, this.g.y, this.g.width, this.g.height);
            graphics.setFont(o.j);
            graphics.setColor(o.k);
            for (int j = 0; j < this.e.length; ++j) {
                graphics.drawString(this.e[j], Math.min(size.width, this.g.x + (n4 - fontMetrics2.stringWidth(this.e[j])) / 2 + x), Math.min(size.height, this.g.y + (fontMetrics2.getAscent() + fontMetrics2.getMaxDecent()) * j + n + fontMetrics2.getAscent()));
            }
        }
        if (this.i.height >= n + fontMetrics.getAscent() + fontMetrics.getMaxDescent() + n2 && this.i.width >= x + fontMetrics.stringWidth(this.h) + n3) {
            graphics.setFont(this.getFont());
            graphics.setColor(this.getForeground());
            graphics.drawString(this.h, Math.min(size.width, this.i.x + x), Math.min(size.height, this.i.y + n + fontMetrics.getAscent()));
        }
        this.i.x = x;
        this.i.width = size.width - n3;
    }
    
    public final void a(String[] e, final String f) {
        synchronized (this) {
            if (e == null) {
                e = new String[] { "" };
            }
            if (!this.e.equals(e) || !this.e[0].equals(e[0])) {
                this.c = true;
                this.e = e;
                this.f = f;
                if (this.g == null) {
                    this.repaint(1L);
                }
                else {
                    this.repaint(1L, this.g.x, this.g.y, this.g.width, this.g.height);
                }
            }
        }
    }
    
    public final void a(String h) {
        synchronized (this) {
            if (h == null) {
                h = "";
            }
            if (!this.h.equals(h)) {
                this.c = true;
                this.h = h;
                if (this.i == null) {
                    this.repaint(1L);
                }
                else {
                    this.repaint(1L, this.i.x, this.i.y, this.i.width, this.i.height);
                }
            }
        }
    }
    
    public final void a() {
        this.b = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.a = null;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.f != null && this.g != null && this.g.inside(n, n2)) {
            try {
                this.a.a(this.a.d().a(this.f), "_blank");
            }
            catch (MalformedURLException ex) {}
        }
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        if (this.f != null && this.g != null) {
            int cursor;
            if (this.g.inside(n, n2)) {
                cursor = 12;
            }
            else {
                cursor = 0;
            }
            Container parent = this;
            while ((parent = parent.getParent()) != null && !(parent instanceof Frame)) {}
            if (parent != null && cursor != ((Frame)parent).getCursorType()) {
                ((Frame)parent).setCursor(cursor);
            }
        }
        return true;
    }
    
    static {
        j = new Font("SansSerif", 1, 16);
        k = Color.red;
        l = Color.lightGray;
    }
}
