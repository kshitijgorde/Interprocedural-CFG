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

public class aB implements ImageObserver
{
    protected boolean h;
    protected boolean j;
    protected int a;
    protected int b;
    protected int c;
    protected String m;
    protected String n;
    protected String o;
    protected Object a;
    am c;
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if ((n & 0x20) != 0x0) {
            this.c.c(this);
            return false;
        }
        return true;
    }
    
    public final void a(final String o, final String n) {
        this.n = n;
        this.o = o;
    }
    
    public String a(final boolean b) {
        return b ? this.n : this.o;
    }
    
    public final int i() {
        return this.b;
    }
    
    public final int a() {
        return this.a;
    }
    
    public final void b(int n) {
        if (n < 15) {
            n = 15;
        }
        this.b = n;
        this.a = n;
        if (this.c != null) {
            this.c.repaint();
            this.c.i();
        }
    }
    
    public final void c(final int a) {
        this.a = a;
        if (this.c != null) {
            this.c.repaint();
        }
    }
    
    public final void e(final int b) {
        this.b = b;
        if (this.c != null) {
            this.c.i();
        }
    }
    
    public final boolean d() {
        return this.h;
    }
    
    public final boolean b() {
        return this.j;
    }
    
    public final void b(final boolean j) {
        this.j = j;
    }
    
    public final String c() {
        return this.m;
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
            am.a(graphics, this.a.toString(), n + 7, 0, n2 - 12, n3, 0, false);
        }
    }
    
    public boolean a(final Event event, final bk bk) {
        return false;
    }
    
    void a(final Graphics graphics, final bf bf, final Object o, final int n, final int n2, final int n3, final int n4, final boolean b) {
        if (o instanceof Boolean) {
            this.a(graphics, bf, (boolean)o, n, n2, n3, n4, b);
        }
        else if (o instanceof Image) {
            this.a(graphics, bf, (Image)o, n, n2, n3, n4, b);
        }
        else if (o != null) {
            final int n5 = n + 5;
            final int n6 = n2 + n4 / 2;
            String string = o.toString();
            if (this.c.g != null) {
                graphics.setFont(new Font(graphics.getFont().getFamily(), 0, 13));
                if (bf.u > 0) {
                    graphics.drawImage(bq.a[bf.u - 1], n5 - 11, n2 + (n4 - 2) - bq.a[bf.u - 1].getHeight(this), this);
                }
                if (bf.g != null && bf.g.length() > 0 && bq.g.containsKey(bf.g)) {
                    final Image image = bq.g.get(bf.g);
                    graphics.drawImage(image, (bf.d == null || bf.d.length() == 0) ? n5 : (n5 + 14), n6 - image.getHeight(this) / 2, this);
                    string = "";
                }
            }
            this.a(graphics, bf, string, n, n2, n3, n4, b);
        }
    }
    
    void a(final Graphics graphics, final bf bf, final boolean b, final int n, final int n2, final int n3, final int n4, final boolean b2) {
        if (b) {
            final int n5 = n2 + n4 / 2 + 3;
            final int n6 = n + n3 / 2 - 1;
            if (bf.b) {
                graphics.setColor(Color.red);
            }
            else {
                graphics.setColor(am.e());
            }
            graphics.drawLine(n6, n5, n6 - 4, n5 - 4);
            graphics.drawLine(n6, n5 - 1, n6 - 3, n5 - 4);
            graphics.drawLine(n6, n5, n6 + 6, n5 - 6);
            graphics.drawLine(n6, n5 - 1, n6 + 6, n5 - 7);
        }
    }
    
    void a(final Graphics graphics, final bf bf, final String s, final int n, final int n2, final int n3, final int n4, final boolean b) {
        if (bf.f == null) {
            graphics.setFont(this.c.getFont());
        }
        else {
            graphics.setFont(bf.f);
        }
        Font font;
        if (bf.d && !bf.i) {
            font = new Font(graphics.getFont().getFamily(), 1, graphics.getFont().getSize());
        }
        else if (bf.d && bf.i) {
            font = new Font(graphics.getFont().getFamily(), 3, graphics.getFont().getSize());
        }
        else if (bf.i) {
            font = new Font(graphics.getFont().getFamily(), 2, graphics.getFont().getSize());
        }
        else {
            font = new Font(graphics.getFont().getFamily(), 0, graphics.getFont().getSize());
        }
        graphics.setFont(font);
        if (b) {
            graphics.setColor(bf.b ? bf.d : Color.lightGray);
        }
        else {
            graphics.setColor(bf.b ? bf.c : Color.gray);
        }
        graphics.getFontMetrics().stringWidth(s);
        am.a(graphics, s, n + 5, n2, n3 - 8, n4, this.c, bf.c, bf.d);
    }
    
    void a(final Graphics graphics, final bf bf, final Image image, final int n, final int n2, final int n3, final int n4, final boolean b) {
        final int height = image.getHeight(this);
        final int width = image.getWidth(this);
        if (height == 0 || height == 0) {
            graphics.drawImage(image, -1, -1, 1, 1, this);
        }
        else {
            graphics.drawImage(image, n + (n3 - width) / 2, n2 + (n4 - height) / 2 - 1, this);
        }
    }
    
    public aB(final Object a, final String m) {
        this.h = false;
        this.j = false;
        this.a = 50;
        this.b = 50;
        this.m = m;
        this.a = a;
    }
}
