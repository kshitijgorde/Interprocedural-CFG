import java.awt.Event;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class d extends Canvas
{
    public Image a;
    public boolean b;
    public boolean c;
    public boolean d;
    public boolean e;
    public boolean f;
    public int g;
    public int h;
    public String i;
    public static Color j;
    public static Color k;
    public Image l;
    public Image m;
    public boolean n;
    public boolean o;
    public String p;
    
    public void a(final boolean f) {
        this.f = f;
    }
    
    public void a() {
        this.c = true;
    }
    
    public void b() {
        this.c = false;
    }
    
    public void b(final boolean b) {
        this.b = b;
        this.repaint();
    }
    
    public d(final Image l, final Image m, final Image a) {
        this.c = false;
        this.d = true;
        this.e = false;
        this.f = false;
        this.i = null;
        this.l = null;
        this.m = null;
        this.n = false;
        this.o = false;
        this.l = l;
        this.m = m;
        this.a = a;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public d(final Image image) {
        this.c = false;
        this.d = true;
        this.e = false;
        this.f = false;
        this.i = null;
        this.l = null;
        this.m = null;
        this.n = false;
        this.o = false;
        this.l = image;
        this.m = this.l;
        this.a = image;
    }
    
    public d(final Image image, final Image m) {
        this.c = false;
        this.d = true;
        this.e = false;
        this.f = false;
        this.i = null;
        this.l = null;
        this.m = null;
        this.n = false;
        this.o = false;
        this.l = image;
        this.m = m;
        this.a = image;
    }
    
    public d() {
        this.c = false;
        this.d = true;
        this.e = false;
        this.f = false;
        this.i = null;
        this.l = null;
        this.m = null;
        this.n = false;
        this.o = false;
    }
    
    public void paint(final Graphics graphics) {
        if (this.e && this.i != null) {
            this.setBackground(d.k);
            final Color color = graphics.getColor();
            final Rectangle bounds = this.bounds();
            if (bounds != null) {
                graphics.clearRect(0, 0, bounds.width - 1, bounds.height - 1);
                graphics.drawRect(0, 0, bounds.width - 1, bounds.height - 1);
                graphics.setColor(d.j);
                final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
                if (fontMetrics != null) {
                    graphics.drawString(this.i, 2, 1 + fontMetrics.getMaxAscent());
                }
            }
            graphics.setColor(color);
        }
        else {
            if (this.l == null) {
                return;
            }
            final Rectangle bounds2 = this.bounds();
            if (bounds2 != null) {
                graphics.clearRect(0, 0, bounds2.width - 1, bounds2.height - 1);
            }
            if (this.c) {
                if (this.b) {
                    graphics.drawImage(this.a, 0, 0, null);
                }
                else {
                    graphics.drawImage(this.l, 0, 0, null);
                }
                return;
            }
            if (!this.d) {
                graphics.drawImage(this.m, 0, 0, null);
            }
            else if (this.o) {
                graphics.drawImage(this.a, 0, 0, null);
            }
            else {
                graphics.drawImage(this.l, 0, 0, null);
            }
        }
    }
    
    public void setName(final String p) {
        this.p = p;
    }
    
    public String getName() {
        return this.p;
    }
    
    public void enable() {
        this.d = true;
        this.repaint();
    }
    
    public void disable() {
        this.d = false;
        this.repaint();
    }
    
    public Dimension size() {
        return new Dimension(this.l.getWidth(this), this.l.getHeight(this));
    }
    
    public synchronized boolean mouseDown(final Event event, final int g, final int h) {
        this.g = g;
        this.h = h;
        this.n = true;
        this.repaint();
        return true;
    }
    
    public synchronized boolean mouseUp(final Event event, final int g, final int h) {
        this.g = g;
        this.h = h;
        if (event.metaDown()) {
            this.postEvent(new Event(this, 0L, 1001, g, h, 0, 4));
        }
        else {
            this.postEvent(new Event(this, 1001, this.p));
        }
        this.n = false;
        this.repaint();
        return true;
    }
    
    public synchronized boolean mouseEnter(final Event event, final int g, final int h) {
        this.g = g;
        this.h = h;
        this.o = true;
        if (this.f) {
            this.postEvent(new Event(this, 1001, zkmToString("C]PB\u0012k\\QT\u0005")));
        }
        this.repaint();
        return true;
    }
    
    public synchronized boolean mouseExit(final Event event, final int g, final int h) {
        this.g = g;
        this.h = h;
        this.o = false;
        if (this.f) {
            this.postEvent(new Event(this, 1001, zkmToString("C]PB\u0012kJLE")));
        }
        this.repaint();
        return true;
    }
    
    static {
        d.j = Color.black;
        d.k = Color.lightGray;
    }
    
    private static String zkmToString(final String s) {
        final char[] charArray = s.toCharArray();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = '.';
                    break;
                }
                case 1: {
                    c2 = '2';
                    break;
                }
                case 2: {
                    c2 = '%';
                    break;
                }
                case 3: {
                    c2 = '1';
                    break;
                }
                default: {
                    c2 = 'w';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
