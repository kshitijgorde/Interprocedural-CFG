// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.util.Enumeration;
import javax.swing.SwingUtilities;
import java.net.URLEncoder;
import java.util.Vector;
import java.net.URL;

public class aD
{
    private URL c;
    private String d;
    protected o a;
    protected cZ b;
    private n e;
    private final Vector f;
    private H g;
    
    public aD(final URL c, final String s) {
        this.c = c;
        if (s != null) {
            this.d = URLEncoder.encode(s);
        }
        this.f = new Vector();
    }
    
    public final String a() {
        return this.d;
    }
    
    protected final n b() {
        if (this.e == null) {
            this.e = new n();
        }
        return this.e;
    }
    
    public final void c() {
        this.g = new H();
        this.a = new D(this.c, this.g, this);
        this.b = new cr(this.c, this.d, this);
    }
    
    final void a(final String s) {
        this.d = URLEncoder.encode(s);
        this.a.a(this.d);
        SwingUtilities.invokeLater(new aa(this));
        if (this.c.toExternalForm().startsWith("http://")) {
            new bV(this, this.c);
        }
    }
    
    protected final void a(final Object o) {
        if (o.equals(this.e.a())) {
            return;
        }
        synchronized (this.f) {
            final Enumeration<ai> elements = this.f.elements();
            while (elements.hasMoreElements()) {
                SwingUtilities.invokeLater(new bl(this, elements.nextElement(), o));
            }
        }
    }
    
    protected final void d() {
        SwingUtilities.invokeLater(new Y(this));
    }
    
    public final void a(final ai ai) {
        synchronized (this.f) {
            this.f.addElement(ai);
        }
    }
    
    public final void b(final Object o) {
        this.g.a(o);
    }
    
    public final void e() {
        synchronized (this.f) {
            this.f.removeAllElements();
        }
        if (this.b != null) {
            this.b.a();
        }
        this.b = null;
        if (this.a != null) {
            this.a.a();
        }
        this.a = null;
    }
    
    public final void f() {
        this.a.b();
        this.b.b();
    }
    
    static Vector a(final aD ad) {
        return ad.f;
    }
    
    public aD() {
    }
}
