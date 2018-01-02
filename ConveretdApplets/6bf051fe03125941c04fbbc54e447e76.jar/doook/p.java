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

public class p implements ImageObserver
{
    protected boolean a;
    protected boolean c;
    protected int g;
    protected int h;
    protected int i;
    protected String d;
    protected String e;
    protected String f;
    protected Object a;
    u b;
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if ((n & 0x20) != 0x0) {
            this.b.c(this);
            return false;
        }
        return true;
    }
    
    public final void a(final String f, final String e) {
        this.e = e;
        this.f = f;
    }
    
    public String a(final boolean b) {
        return b ? this.e : this.f;
    }
    
    public final int a() {
        return this.h;
    }
    
    public final int b() {
        return this.g;
    }
    
    public final void a(int n) {
        if (n < 15) {
            n = 15;
        }
        this.h = n;
        this.g = n;
        if (this.b != null) {
            this.b.repaint();
            this.b.i();
        }
    }
    
    public final void b(final int g) {
        this.g = g;
        if (this.b != null) {
            this.b.repaint();
        }
    }
    
    public final void c(final int h) {
        this.h = h;
        if (this.b != null) {
            this.b.i();
        }
    }
    
    public final boolean c() {
        return this.a;
    }
    
    public final boolean d() {
        return this.c;
    }
    
    public final void b(final boolean c) {
        this.c = c;
    }
    
    public final String b() {
        return this.d;
    }
    
    public void a(final Graphics graphics, final int n, final int n2, final int n3, final boolean b, final boolean b2) {
        if (this.a instanceof Image) {
            final Image image = (Image)this.a;
            final int height = image.getHeight(this.b);
            final int width = image.getWidth(this.b);
            if (height <= 0 || height <= 0) {
                graphics.drawImage(image, -1, -1, 1, 1, this.b);
            }
            else {
                graphics.drawImage(image, n + (n2 - width) / 2, (n3 - height) / 2, this.b);
            }
        }
        else if (this.a != null) {
            graphics.setColor(b ? Color.white : this.b.getForeground());
            u.a(graphics, this.a.toString(), n + 7, 0, n2 - 12, n3, 0, false);
        }
    }
    
    public boolean a(final Event event, final az az) {
        return false;
    }
    
    void a(final Graphics graphics, final m m, final Object o, final int n, final int n2, final int n3, final int n4, final boolean b) {
        if (o instanceof Boolean) {
            this.a(graphics, m, (boolean)o, n, n2, n3, n4, b);
        }
        else if (o instanceof Image) {
            this.a(graphics, m, (Image)o, n, n2, n3, n4, b);
        }
        else if (o != null) {
            final int n5 = n + 5;
            final int n6 = n2 + n4 / 2;
            String string = o.toString();
            if (this.b.c != null) {
                graphics.setFont(new Font(graphics.getFont().getFamily(), 0, 13));
                if (m.b > 0) {
                    graphics.drawImage(aX.a[m.b - 1], n5 - 11, n2 + (n4 - 2) - aX.a[m.b - 1].getHeight(this), this);
                }
                if (m.a != null && m.a.length() > 0 && aX.d.containsKey(m.a)) {
                    final Image image = aX.d.get(m.a);
                    graphics.drawImage(image, (m.c == null || m.c.length() == 0) ? n5 : (n5 + 14), n6 - image.getHeight(this) / 2, this);
                    string = "";
                }
            }
            this.a(graphics, m, string, n, n2, n3, n4, b);
        }
    }
    
    void a(final Graphics graphics, final m m, final boolean b, final int n, final int n2, final int n3, final int n4, final boolean b2) {
        if (b) {
            final int n5 = n2 + n4 / 2 + 3;
            final int n6 = n + n3 / 2 - 1;
            if (m.d) {
                graphics.setColor(Color.red);
            }
            else {
                graphics.setColor(u.a());
            }
            graphics.drawLine(n6, n5, n6 - 4, n5 - 4);
            graphics.drawLine(n6, n5 - 1, n6 - 3, n5 - 4);
            graphics.drawLine(n6, n5, n6 + 6, n5 - 6);
            graphics.drawLine(n6, n5 - 1, n6 + 6, n5 - 7);
        }
    }
    
    void a(final Graphics graphics, final m m, final String s, final int n, final int n2, final int n3, final int n4, final boolean b) {
        if (m.a == null) {
            graphics.setFont(this.b.getFont());
        }
        else {
            graphics.setFont(m.a);
        }
        Font font;
        if (m.f && !m.g) {
            font = new Font(graphics.getFont().getFamily(), 1, graphics.getFont().getSize());
        }
        else if (m.f && m.g) {
            font = new Font(graphics.getFont().getFamily(), 3, graphics.getFont().getSize());
        }
        else if (m.g) {
            font = new Font(graphics.getFont().getFamily(), 2, graphics.getFont().getSize());
        }
        else {
            font = new Font(graphics.getFont().getFamily(), 0, graphics.getFont().getSize());
        }
        graphics.setFont(font);
        if (b) {
            graphics.setColor(m.d ? m.b : Color.lightGray);
        }
        else {
            graphics.setColor(m.d ? m.a : Color.gray);
        }
        graphics.getFontMetrics().stringWidth(s);
        u.a(graphics, s, n + 5, n2, n3 - 8, n4, this.i, m.e, m.c);
    }
    
    void a(final Graphics graphics, final m m, final Image image, final int n, final int n2, final int n3, final int n4, final boolean b) {
        final int height = image.getHeight(this);
        final int width = image.getWidth(this);
        if (height == 0 || height == 0) {
            graphics.drawImage(image, -1, -1, 1, 1, this);
        }
        else {
            graphics.drawImage(image, n + (n3 - width) / 2, n2 + (n4 - height) / 2 - 1, this);
        }
    }
    
    public p(final Object a, final String d) {
        this.a = false;
        this.c = false;
        this.g = 50;
        this.h = 50;
        this.d = d;
        this.a = a;
    }
}
