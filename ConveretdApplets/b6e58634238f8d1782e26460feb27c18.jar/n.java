import java.awt.event.WindowEvent;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Component;
import java.util.Properties;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Enumeration;
import java.util.Vector;
import java.util.Hashtable;
import java.awt.event.WindowListener;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public final class n extends Frame implements b, WindowListener
{
    public Hashtable a;
    public Vector b;
    public d c;
    public final String d;
    public Integer e;
    public Integer f;
    public Integer g;
    public Integer h;
    public boolean i;
    public Frame j;
    public q k;
    
    public n(final d c) {
        this.a = new Hashtable();
        this.b = new Vector();
        this.c = c;
        this.d = "";
        this.a(this.c.k());
    }
    
    public final void a(final n n) {
        synchronized (this.a) {
            if (n.d != null) {
                this.a.put(n.d, n);
            }
            else {
                this.b.addElement(n);
            }
        }
    }
    
    public final n a(final String s) {
        final n n;
        synchronized (this.a) {
            n = this.a.get(s);
        }
        return n;
    }
    
    public final void b(final n n) {
        synchronized (this.a) {
            if (n.d != null) {
                this.a.remove(n.d);
            }
            else {
                this.b.removeElement(n);
            }
        }
    }
    
    public final void a() {
        synchronized (this.a) {
            final Enumeration<String> keys = this.a.keys();
            while (keys.hasMoreElements()) {
                final n n = this.a.get(keys.nextElement());
                n.Destroy();
                n.b();
            }
            while (this.b.size() > 0) {
                final n n2 = this.b.elementAt(0);
                n2.Destroy();
                n2.b();
            }
        }
    }
    
    public n(final d c, final Frame j, final String d, final boolean i) {
        this.a = new Hashtable();
        this.b = new Vector();
        this.c = c;
        final Image b;
        if ((b = this.c.e().b) != null) {
            this.setIconImage(b);
        }
        this.j = j;
        this.i = i;
        this.d = d;
        this.addWindowListener(this);
        this.setTitle("Heatmaps®");
        if (i) {
            this.a(this.c.k());
        }
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final void a(final Properties properties) {
        final String property;
        if ((property = properties.getProperty("TearOffWidth")) != null) {
            try {
                final Integer c = this.c.d().c(property);
                if (c >= 0) {
                    this.e = c;
                }
                else {
                    this.c.c().a((Component)this.c.s, 4, new Object[] { property, "TearOffWidth", "negative" });
                }
            }
            catch (Exception ex) {
                this.c.c().a((Component)this.c.s, 4, new Object[] { property, "TearOffWidth", ex.getMessage() });
            }
        }
        final String property2;
        if ((property2 = properties.getProperty("TearOffHeight")) != null) {
            try {
                final Integer c2 = this.c.d().c(property2);
                if (c2 >= 0) {
                    this.f = c2;
                }
                else {
                    this.c.c().a((Component)this.c.s, 4, new Object[] { property2, "TearOffHeight", "negative" });
                }
            }
            catch (Exception ex2) {
                this.c.c().a((Component)this.c.s, 4, new Object[] { property2, "TearOffHeight", ex2.getMessage() });
            }
        }
        final String property3;
        if ((property3 = properties.getProperty("TearOffX")) != null) {
            try {
                this.g = this.c.d().c(property3);
            }
            catch (Exception ex3) {
                this.c.c().a((Component)this.c.s, 4, new Object[] { property3, "TearOffX", ex3.getMessage() });
            }
        }
        final String property4;
        if ((property4 = properties.getProperty("TearOffY")) != null) {
            try {
                this.h = this.c.d().c(property4);
            }
            catch (Exception ex4) {
                this.c.c().a((Component)this.c.s, 4, new Object[] { property4, "TearOffY", ex4.getMessage() });
            }
        }
        if (this.e == null || this.f == null) {
            final Integer n = null;
            this.h = n;
            this.g = n;
            this.f = n;
            this.e = n;
        }
        else if (this.g == null || this.h == null) {
            final Integer n2 = null;
            this.h = n2;
            this.g = n2;
        }
    }
    
    public final Dimension GetSize() {
        final Dimension size = this.getSize();
        if (this.e != null && this.f != null) {
            size.width = this.e;
            size.height = this.f;
        }
        return size;
    }
    
    public final void Reset(final String s, final String s2, final String s3, final t t) {
        this.Destroy();
        this.setSize(this.c.s.GetSize());
        try {
            (this.k = new q(this.c, this.c.k(), this, s, s2, s3, this.i)).a(t);
        }
        catch (Throwable t2) {
            t2.printStackTrace();
            this.c.c().a(this, 12, new Object[] { t2.getMessage() });
            this.k = null;
        }
        this.c();
    }
    
    public final void Reset(final q q, final String s, final t t) {
        try {
            this.setSize(this.c.s.GetSize());
            final q k = new q(this.c, this.c.k(), this, q, s);
            k.a(t);
            this.Destroy();
            this.k = k;
        }
        catch (Throwable t2) {
            t2.printStackTrace();
            this.c.c().a(this, 12, new Object[] { t2.getMessage() });
            this.k = null;
        }
        this.c();
    }
    
    private void c() {
        this.c.d();
        final Dimension size = f.c().getSize();
        if (!this.isVisible()) {
            if (this.g != null && this.h != null) {
                this.setLocation(this.g, this.h);
            }
            else if (this.e != null && this.f != null) {
                final Dimension size2 = this.getSize();
                this.setBounds((size.width - size2.width) / 2, (size.height - size2.height) / 2, Math.min(size.width, size2.width), Math.min(size.height, size2.height));
            }
            else {
                this.c.d();
                f.a(this.j, this, false);
            }
            final Dimension size3 = this.getSize();
            if (this.getBounds().x + this.getBounds().width > size.width) {
                size3.width = size.width - this.getBounds().x;
            }
            if (this.getBounds().y + this.getBounds().height > size.height) {
                size3.height = size.height - this.getBounds().y;
            }
            this.setSize(size3.width, size3.height);
            this.show();
        }
    }
    
    public final void Destroy() {
        if (this.c.q) {
            try {
                if (this.k != null) {
                    this.k.e();
                    this.k = null;
                }
            }
            catch (Throwable t) {
                t.printStackTrace();
                this.c.c().a(this, 1, new Object[] { t.getMessage() });
                this.k = null;
            }
        }
    }
    
    public void b() {
        if (this.c != null) {
            this.c.g().b(this);
        }
        this.setVisible(false);
        this.dispose();
        if (this.k != null) {
            this.k.e();
            this.k = null;
        }
        this.j = null;
        this.c = null;
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.Destroy();
        this.b();
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
}
