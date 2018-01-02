// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

import java.net.URL;
import java.util.Hashtable;
import java.util.Vector;

public class d implements ResourceListener
{
    private Vector a;
    private Vector b;
    private Hashtable c;
    private URL d;
    private boolean e;
    
    public void a(final Shout3DViewer shout3DViewer, final c c) {
        if (this.b(c.e()) == null) {
            this.a(c.e(), c.a());
        }
        if (this.e) {
            new k().a(shout3DViewer, c, this);
            return;
        }
        try {
            c.d();
            this.a(c, c.a());
        }
        catch (Exception ex) {
            System.out.println("Resource loading ERROR: " + ex);
            ex.printStackTrace();
        }
    }
    
    public void removeResourceObserver(final ResourceObserver resourceObserver) {
        if (this.a != null) {
            final int index = this.a.indexOf(resourceObserver);
            this.a.removeElement(resourceObserver);
            this.b.removeElementAt(index);
            if (this.a.size() == 0) {
                this.a = null;
                this.b = null;
            }
        }
    }
    
    public void a(final URL url, final Object o) {
        this.c.put(url, o);
    }
    
    public void a(final boolean e) {
        this.e = e;
    }
    
    public d() {
        this.a = null;
        this.b = null;
        this.c = new Hashtable();
        this.e = true;
    }
    
    public boolean a() {
        return this.e;
    }
    
    public void a(final c c, final boolean b) {
        this.b(c, b);
    }
    
    public void addResourceObserver(final ResourceObserver resourceObserver, final Object o) {
        if (this.a == null) {
            this.a = new Vector(1);
            this.b = new Vector(1);
        }
        this.a.addElement(resourceObserver);
        this.b.addElement(o);
    }
    
    public URL b() {
        return this.d;
    }
    
    public void a(final URL d) {
        this.d = d;
        System.out.println(d);
    }
    
    public void c() {
        this.c = new Hashtable();
    }
    
    void a(final c c, final Object o) {
        if (o == null) {
            this.a(c, false);
            c.a(null);
            return;
        }
        this.a(c, true);
        c.a(o);
        this.a(c.e(), o);
    }
    
    public Object b(final URL url) {
        if (url != null) {
            return this.c.get(url);
        }
        return null;
    }
    
    void b(final c c, final boolean b) {
        if (this.a != null) {
            for (int i = 0; i < this.a.size(); ++i) {
                ((ResourceObserver)this.a.elementAt(i)).onLoadDone(c.e().toString(), b, this.b.elementAt(i));
            }
        }
    }
    
    public void a(final Shout3DViewer shout3DViewer, final c c, final boolean b) {
        final boolean a = this.a();
        this.a(b);
        this.a(shout3DViewer, c);
        this.a(a);
    }
}
