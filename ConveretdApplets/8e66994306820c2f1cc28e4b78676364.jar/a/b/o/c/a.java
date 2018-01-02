// 
// Decompiled by Procyon v0.5.30
// 

package a.b.o.c;

import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Graphics;
import a.b.b.b;
import java.util.Vector;
import java.awt.Cursor;
import a.b.o.a.b.c;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import java.awt.Component;

public class a extends Component implements h, ImageObserver, MouseListener
{
    private c a;
    private Cursor b;
    private Vector c;
    private Vector d;
    private Vector e;
    private Vector f;
    private int g;
    
    public a() {
        this(new c());
    }
    
    public a(final c c) {
        this.c = new Vector();
        this.d = new Vector();
        this.e = new Vector();
        this.f = new Vector();
        this.a(c);
        this.a((Cursor)null);
        this.a(0);
        this.addMouseListener(this);
    }
    
    public void a(final c c) {
        this.a(c, true);
    }
    
    protected void a(final c a, final boolean b) {
        if (a != null) {
            this.a = a;
        }
        else {
            this.a = new c();
        }
        if (b) {
            this.b();
        }
    }
    
    public void a(final a.b.b.c c) {
        if (c != null) {
            this.c.addElement(c);
        }
    }
    
    public void b(final a.b.b.c c) {
        if (c != null) {
            this.c.removeElement(c);
        }
    }
    
    public void c(final a.b.b.c c) {
        if (c != null) {
            this.d.addElement(c);
        }
    }
    
    public void d(final a.b.b.c c) {
        if (c != null) {
            this.d.removeElement(c);
        }
    }
    
    public void e(final a.b.b.c c) {
        if (c != null) {
            this.e.addElement(c);
        }
    }
    
    public void f(final a.b.b.c c) {
        if (c != null) {
            this.e.removeElement(c);
        }
    }
    
    public void a(final b b) {
        this.f.addElement(b);
    }
    
    public void a(final int g) {
        this.g = g;
    }
    
    public int a() {
        return this.g;
    }
    
    public int getX() {
        return this.getBounds().x;
    }
    
    public int getY() {
        return this.getBounds().y;
    }
    
    public int getWidth() {
        return this.getSize().width;
    }
    
    public int getHeight() {
        return this.getSize().height;
    }
    
    public void paint(final Graphics graphics) {
        if (graphics != null && this.a != null) {
            final Image a = this.a.a();
            if (a != null) {
                graphics.drawImage(a, 0, 0, null);
            }
        }
    }
    
    public void a(final Cursor b) {
        this.b = b;
    }
    
    protected void a() {
        this.setSize(this.b(this.a));
    }
    
    protected Dimension b(final c c) {
        if (c == null) {
            return new Dimension(0, 0);
        }
        final Image a = c.a();
        if (a != null) {
            return new Dimension(a.getWidth(null), a.getHeight(null));
        }
        return new Dimension(0, 0);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        if (this.b != null) {
            super.setCursor(this.b);
        }
        for (int i = 0; i < this.f.size(); ++i) {
            final b b = this.f.elementAt(i);
            for (int j = 0; j < this.d.size(); ++j) {
                try {
                    b.a((a.b.b.c)this.d.elementAt(j));
                }
                catch (NullPointerException ex) {
                    this.f.removeElementAt(i);
                    --i;
                }
            }
        }
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        if (this.b != null) {
            super.setCursor(new Cursor(0));
        }
        for (int i = 0; i < this.f.size(); ++i) {
            final b b = this.f.elementAt(i);
            for (int j = 0; j < this.e.size(); ++j) {
                try {
                    b.a((a.b.b.c)this.e.elementAt(j));
                }
                catch (NullPointerException ex) {
                    this.f.removeElementAt(i);
                    --i;
                }
            }
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        for (int i = 0; i < this.f.size(); ++i) {
            final b b = this.f.elementAt(i);
            for (int j = 0; j < this.c.size(); ++j) {
                try {
                    b.a((a.b.b.c)this.c.elementAt(j));
                }
                catch (NullPointerException ex) {
                    this.f.removeElementAt(i);
                    --i;
                }
            }
        }
    }
    
    private void b() {
        if (this.a != null && this.a.a() != null) {
            this.prepareImage(this.a.a(), -1, -1, this);
            this.a();
        }
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if ((n & 0x80) != 0x0) {
            return false;
        }
        if ((n & 0x20) != 0x0) {
            this.a();
            return false;
        }
        return true;
    }
}
