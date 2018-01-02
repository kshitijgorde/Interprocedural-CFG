// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Font;
import java.awt.Event;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

public class j implements ImageObserver
{
    protected boolean a;
    protected boolean m;
    protected int i;
    protected int a;
    protected int b;
    protected String f;
    protected String g;
    protected String h;
    protected Object a;
    l c;
    
    public void a(final int b) {
        this.b = b;
        if (this.c != null) {
            this.c.c(this);
        }
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if ((n & 0x20) != 0x0) {
            this.c.c(this);
            return false;
        }
        return true;
    }
    
    public final void a(final String h, final String g) {
        this.g = g;
        this.h = h;
    }
    
    public String a(final boolean b) {
        return b ? this.g : this.h;
    }
    
    public final int a() {
        return this.a;
    }
    
    public final int b() {
        return this.i;
    }
    
    public final void b(int n) {
        if (n < 15) {
            n = 15;
        }
        this.a = n;
        this.i = n;
        if (this.c != null) {
            this.c.repaint();
            this.c.g();
        }
    }
    
    public final void c(final int i) {
        this.i = i;
        if (this.c != null) {
            this.c.repaint();
        }
    }
    
    public final void d(final int a) {
        this.a = a;
        if (this.c != null) {
            this.c.g();
        }
    }
    
    public final boolean b() {
        return this.a;
    }
    
    public final void b(final boolean a) {
        this.a = a;
    }
    
    public final boolean c() {
        return this.m;
    }
    
    public final void c(final boolean m) {
        this.m = m;
    }
    
    public final String b() {
        return this.f;
    }
    
    public final Object a() {
        return this.a;
    }
    
    public void a(final Graphics graphics, final int n, final int n2, final int n3, final boolean b, final boolean b2) {
        if (this.a instanceof Image) {
            final Image image = (Image)this.a;
            final int height = image.getHeight(this.c);
            final int width = image.getWidth(this.c);
            if (height <= 0 || height <= 0) {
                graphics.drawImage(image, -1, -1, 1, 1, this.c);
            }
            else {
                graphics.drawImage(image, n + (n2 - width) / 2, (n3 - height) / 2, this.c);
            }
        }
        else if (this.a != null) {
            graphics.setColor(b ? Color.white : this.c.getForeground());
            l.a(graphics, this.a.toString(), n + 7, 0, n2 - 12, n3, 0, false);
        }
    }
    
    public boolean a(final Event event, final aU au) {
        return false;
    }
    
    void a(final Graphics graphics, final d d, final Object o, final int n, final int n2, final int n3, final int n4, final boolean b) {
        if (o instanceof Boolean) {
            this.a(graphics, d, (boolean)o, n, n2, n3, n4, b);
        }
        else if (o instanceof Image) {
            this.a(graphics, d, (Image)o, n, n2, n3, n4, b);
        }
        else if (o != null) {
            final int n5 = n + 5;
            final int n6 = n2 + n4 / 2;
            String string = o.toString();
            if (this.c.a != null) {
                graphics.setFont(new Font(graphics.getFont().getFamily(), 0, 13));
                if (this.c.a.av && d.f > 0) {
                    graphics.setColor(Color.red);
                    graphics.fillOval(0, n2, 15, 15);
                    graphics.setColor(Color.white);
                    graphics.drawString(String.valueOf(d.f), (d.f > 9) ? 0 : 5, n2 + 13);
                }
                if (d.c > 0) {
                    graphics.drawImage(y.a[d.c - 1], n5 - 11, n2 + (n4 - 2) - y.a[d.c - 1].getHeight(this), this);
                }
                if (d.c != null && d.c.length() > 0 && y.a.containsKey(d.c)) {
                    final Image image = y.a.get(d.c);
                    graphics.drawImage(image, (d.b == null || d.b.length() == 0) ? n5 : (n5 + 14), n6 - image.getHeight(this) / 2, this);
                    string = "";
                }
            }
            this.a(graphics, d, string, n, n2, n3, n4, b);
        }
    }
    
    void a(final Graphics graphics, final d d, final boolean b, final int n, final int n2, final int n3, final int n4, final boolean b2) {
        if (b) {
            final int n5 = n2 + n4 / 2 + 3;
            final int n6 = n + n3 / 2 - 1;
            if (d.d) {
                graphics.setColor(Color.red);
            }
            else {
                graphics.setColor(l.a());
            }
            graphics.drawLine(n6, n5, n6 - 4, n5 - 4);
            graphics.drawLine(n6, n5 - 1, n6 - 3, n5 - 4);
            graphics.drawLine(n6, n5, n6 + 6, n5 - 6);
            graphics.drawLine(n6, n5 - 1, n6 + 6, n5 - 7);
        }
    }
    
    void a(final Graphics graphics, final d d, final String s, final int n, final int n2, final int n3, final int n4, final boolean b) {
        if (d.a == null) {
            graphics.setFont(this.c.getFont());
        }
        else {
            graphics.setFont(d.a);
        }
        Font font;
        if (d.f && !d.g) {
            font = new Font(graphics.getFont().getFamily(), 1, graphics.getFont().getSize());
        }
        else if (d.f && d.g) {
            font = new Font(graphics.getFont().getFamily(), 3, graphics.getFont().getSize());
        }
        else if (d.g) {
            font = new Font(graphics.getFont().getFamily(), 2, graphics.getFont().getSize());
        }
        else {
            font = new Font(graphics.getFont().getFamily(), 0, graphics.getFont().getSize());
        }
        graphics.setFont(font);
        if (b) {
            graphics.setColor(d.d ? d.b : Color.lightGray);
        }
        else {
            graphics.setColor(d.d ? d.a : Color.gray);
        }
        graphics.getFontMetrics().stringWidth(s);
        l.a(graphics, s, n + 5, n2, n3 - 8, n4, this.b, d.e, d.b);
    }
    
    void a(final Graphics graphics, final d d, final Image image, final int n, final int n2, final int n3, final int n4, final boolean b) {
        final int height = image.getHeight(this);
        final int width = image.getWidth(this);
        if (height == 0 || height == 0) {
            graphics.drawImage(image, -1, -1, 1, 1, this);
        }
        else {
            graphics.drawImage(image, n + (n3 - width) / 2, n2 + (n4 - height) / 2 - 1, this);
        }
    }
    
    public j(final String s) {
        this(s, s);
    }
    
    public j(final Object a, final String f) {
        this.a = false;
        this.m = false;
        this.i = 50;
        this.a = 50;
        this.f = f;
        this.a = a;
    }
}
