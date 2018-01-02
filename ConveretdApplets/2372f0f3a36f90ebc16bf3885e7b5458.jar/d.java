import java.awt.Event;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.awt.Font;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class d extends Canvas
{
    public Font a;
    public Image b;
    public boolean c;
    public boolean d;
    public boolean e;
    public boolean f;
    public boolean g;
    public int h;
    public int i;
    public String j;
    public static Color k;
    public static Color l;
    public Image m;
    public Image n;
    public boolean o;
    public boolean p;
    public String q;
    
    public void a(final boolean g) {
        this.g = g;
    }
    
    public void a() {
        this.d = true;
    }
    
    public void b() {
        this.d = false;
    }
    
    public void b(final boolean c) {
        this.c = c;
        this.repaint();
    }
    
    public d(final Image m, final Image n, final Image b) {
        this.a = null;
        this.d = false;
        this.e = true;
        this.f = false;
        this.g = false;
        this.j = null;
        this.m = null;
        this.n = null;
        this.o = false;
        this.p = false;
        this.m = m;
        this.n = n;
        this.b = b;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public d(final Image image) {
        this.a = null;
        this.d = false;
        this.e = true;
        this.f = false;
        this.g = false;
        this.j = null;
        this.m = null;
        this.n = null;
        this.o = false;
        this.p = false;
        this.m = image;
        this.n = this.m;
        this.b = image;
    }
    
    public d(final Image image, final Image n) {
        this.a = null;
        this.d = false;
        this.e = true;
        this.f = false;
        this.g = false;
        this.j = null;
        this.m = null;
        this.n = null;
        this.o = false;
        this.p = false;
        this.m = image;
        this.n = n;
        this.b = image;
    }
    
    public d() {
        this.a = null;
        this.d = false;
        this.e = true;
        this.f = false;
        this.g = false;
        this.j = null;
        this.m = null;
        this.n = null;
        this.o = false;
        this.p = false;
    }
    
    public void paint(final Graphics graphics) {
        final Rectangle bounds = this.bounds();
        if (bounds == null) {
            return;
        }
        final Image image = this.createImage(bounds.width, bounds.height);
        final Graphics graphics2 = image.getGraphics();
        if (this.f && this.j != null) {
            if (this.a == null) {
                this.a = new Font(zkmToString("Rt}\u0010\u0001nxr\u0007"), 0, 9);
            }
            graphics2.setFont(this.a);
            this.setBackground(d.l);
            final Color color = graphics2.getColor();
            if (bounds != null) {
                graphics2.clearRect(0, 0, bounds.width - 1, bounds.height - 1);
                graphics2.drawRect(0, 0, bounds.width - 1, bounds.height - 1);
                graphics2.setColor(d.k);
                final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
                if (fontMetrics != null) {
                    graphics2.drawString(this.j, 2, fontMetrics.getAscent());
                }
            }
            graphics2.setColor(color);
        }
        else {
            if (this.m == null) {
                return;
            }
            if (bounds != null) {
                graphics2.clearRect(0, 0, bounds.width - 1, bounds.height - 1);
            }
            if (this.d) {
                if (this.c) {
                    graphics2.drawImage(this.b, 0, 0, null);
                }
                else {
                    graphics2.drawImage(this.m, 0, 0, null);
                }
            }
            else if (!this.e) {
                graphics2.drawImage(this.n, 0, 0, null);
            }
            else if (this.p) {
                graphics2.drawImage(this.b, 0, 0, null);
            }
            else {
                graphics2.drawImage(this.m, 0, 0, null);
            }
        }
        graphics.drawImage(image, 0, 0, null);
        graphics2.dispose();
        image.flush();
    }
    
    public void setName(final String q) {
        this.q = q;
    }
    
    public String getName() {
        return this.q;
    }
    
    public void enable() {
        this.e = true;
        this.repaint();
    }
    
    public void disable() {
        this.e = false;
        this.repaint();
    }
    
    public Dimension size() {
        return new Dimension(this.m.getWidth(this), this.m.getHeight(this));
    }
    
    public synchronized boolean mouseDown(final Event event, final int h, final int i) {
        this.h = h;
        this.i = i;
        this.o = true;
        this.repaint();
        return true;
    }
    
    public synchronized boolean mouseUp(final Event event, final int h, final int i) {
        this.h = h;
        this.i = i;
        if (event.metaDown()) {
            this.postEvent(new Event(this, 0L, 1001, h, i, 0, 4));
        }
        else {
            this.postEvent(new Event(this, 1001, this.q));
        }
        this.o = false;
        this.repaint();
        return true;
    }
    
    public synchronized boolean mouseEnter(final Event event, final int h, final int i) {
        this.h = h;
        this.i = i;
        this.p = true;
        if (this.g) {
            this.postEvent(new Event(this, 1001, zkmToString("w~d\u0015\u0001_\u007fe\u0003\u0016")));
        }
        this.repaint();
        return true;
    }
    
    public synchronized boolean mouseExit(final Event event, final int h, final int i) {
        this.h = h;
        this.i = i;
        this.p = false;
        if (this.g) {
            this.postEvent(new Event(this, 1001, zkmToString("w~d\u0015\u0001_ix\u0012")));
        }
        this.repaint();
        return true;
    }
    
    static {
        d.k = Color.black;
        d.l = Color.lightGray;
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
                    c2 = '\u001a';
                    break;
                }
                case 1: {
                    c2 = '\u0011';
                    break;
                }
                case 2: {
                    c2 = '\u0011';
                    break;
                }
                case 3: {
                    c2 = 'f';
                    break;
                }
                default: {
                    c2 = 'd';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
