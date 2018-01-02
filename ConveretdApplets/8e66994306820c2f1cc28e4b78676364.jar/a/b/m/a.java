// 
// Decompiled by Procyon v0.5.30
// 

package a.b.m;

import org.a.d.g;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import a.b.o.c.h;
import java.awt.Point;
import a.b.h.c.d;
import java.awt.Image;
import java.util.Vector;
import java.awt.Component;
import java.awt.Container;

public class a extends Container implements b
{
    private static final Component a;
    private boolean b;
    private a.b.h.c.a c;
    private Vector d;
    private Image e;
    private String f;
    private Component g;
    private boolean h;
    private static String z;
    
    public a(final String s, final a.b.h.c.a a) {
        this(s, a, null);
    }
    
    public a(final String s, final a.b.h.c.a a, final Image image) {
        this.b = false;
        this.d = new Vector();
        this.a(image);
        this.a(s);
        this.a(a);
        this.a(a.b.m.a.a);
        this.h = false;
    }
    
    public void a(final Component g) {
        if (g != null) {
            this.g = g;
        }
        else {
            this.g = a.b.m.a.a;
        }
    }
    
    public void a(final String f) {
        this.f = f;
    }
    
    public void a(final Image e) {
        this.e = e;
    }
    
    public void a(final a.b.h.c.a c) {
        if (c != null) {
            this.c = c;
        }
        else {
            this.c = new a.b.h.c.a(this.f);
        }
    }
    
    public void a(final d d) {
        this.c.a((Object)d);
    }
    
    public int getX() {
        return this.getBounds().x;
    }
    
    public int getY() {
        return this.getBounds().y;
    }
    
    public int getHeight() {
        return this.getBounds().height;
    }
    
    public int getWidth() {
        return this.getBounds().width;
    }
    
    private a.b.m.c a(final d d) {
        if (d == null) {
            return null;
        }
        final h a = d.a();
        a.b.a.a.c c = null;
        try {
            c = (a.b.a.a.c)d.b();
        }
        catch (ClassCastException ex) {}
        if (a != null && c != null) {
            final a.b.m.c c2 = new a.b.m.c(c, a, c.a());
            int n = 0;
            switch (a.a()) {
                case 1: {
                    n = 0;
                    break;
                }
                case 2: {
                    n = this.getHeight() - a.getHeight();
                    break;
                }
                default: {
                    n = (this.getHeight() - a.getHeight()) / 2;
                    break;
                }
            }
            c2.a(new Point((this.getWidth() - a.getWidth()) / 2, n));
            return c2;
        }
        return null;
    }
    
    public boolean a() {
        boolean b = false;
        if (this.h) {
            this.b();
        }
        else if (!this.b) {
            int i = 0;
            while (i < this.d.size()) {
                final a.b.m.c c = this.d.elementAt(i);
                c.b(System.currentTimeMillis());
                if (c.b()) {
                    this.remove((Component)c.a());
                    this.d.removeElementAt(i);
                    b = true;
                }
                else {
                    ++i;
                }
            }
            if (this.d.size() == 0) {
                if (this.c.a() > 0) {
                    this.c();
                    b = true;
                }
                else {
                    this.b();
                    b = true;
                }
            }
        }
        if (b) {
            this.getParent().repaint(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        }
        return b;
    }
    
    public void paint(final Graphics graphics) {
        if (this.e != null) {
            graphics.drawImage(this.e, 0, 0, this.getWidth(), this.getHeight(), null);
        }
        super.paint(graphics);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void b() {
        if (this.c.a() > 0) {
            if (this.h && this.g != null) {
                this.remove(this.g);
                this.getParent().repaint(this.getX(), this.getY(), this.getWidth(), this.getHeight());
                this.h = false;
            }
        }
        else if (!this.h && this.g != null) {
            this.g.setBounds((this.getWidth() - this.g.getBounds().width) / 2, (this.getHeight() - this.g.getBounds().height) / 2, this.g.getBounds().width, this.g.getBounds().height);
            this.add(this.g);
            this.getParent().repaint(this.getX(), this.getY(), this.getWidth(), this.getHeight());
            this.h = true;
        }
    }
    
    private synchronized void c() {
        a.b.m.c a = null;
        for (int i = 1; i != 0; i = ((a == null && this.c.a() > 0) ? 1 : 0)) {
            try {
                a = this.a((d)this.c.b());
            }
            catch (org.a.d.g g) {}
        }
        if (a != null) {
            this.add((Component)a.a());
            a.a(System.currentTimeMillis());
            this.d.addElement(a);
        }
    }
    
    static {
        final char[] charArray = "Om=\t)mepM0og=\u001e%#u=\u00044".toCharArray();
        final int i = charArray.length;
        for (int n = 0; i > n; ++n) {
            final int n2 = n;
            final char c = charArray[n2];
            char c2 = '\0';
            switch (n % 5) {
                case 0: {
                    c2 = '\u0003';
                    break;
                }
                case 1: {
                    c2 = '\u0002';
                    break;
                }
                case 2: {
                    c2 = '\\';
                    break;
                }
                case 3: {
                    c2 = 'm';
                    break;
                }
                default: {
                    c2 = '@';
                    break;
                }
            }
            charArray[n2] = (char)(c ^ c2);
        }
        a.b.m.a.z = new String(charArray).intern();
        a = new a.b.o.c.d(a.b.m.a.z);
    }
}
