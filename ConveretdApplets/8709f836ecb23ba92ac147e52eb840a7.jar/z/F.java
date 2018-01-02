// 
// Decompiled by Procyon v0.5.30
// 

package z;

import java.util.Iterator;
import java.util.List;
import java.io.File;
import java.net.URL;
import foo.Bubba;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.util.Hashtable;
import java.util.Observer;
import java.util.Observable;

public class F extends Observable implements Observer
{
    private final int a;
    private int b;
    private final Hashtable c;
    private final Image d;
    private final Image e;
    private ImageIcon f;
    private aE g;
    private boolean h;
    private Bubba i;
    private static /* synthetic */ boolean j;
    
    public F(final int b, final int a, final Image image, final URL url, final URL url2, final boolean h) {
        this.c = new Hashtable();
        if (!F.j && b <= 0) {
            throw new AssertionError();
        }
        if (!F.j && a <= 0) {
            throw new AssertionError();
        }
        if (a > b) {
            throw new RuntimeException("Thumbsize = " + a + " exceeds maximum");
        }
        this.b = b;
        this.a = a;
        (this.g = new aE()).addObserver(this);
        this.f = new ImageIcon(image);
        this.d = V.a(url).getImage();
        this.e = V.a(url2).getImage();
        this.h = h;
    }
    
    private bf c(final String s) {
        if (!F.j && (s == null || s.length() <= 0)) {
            throw new AssertionError();
        }
        if (this.g == null) {
            return null;
        }
        synchronized (this.c) {
            if (this.c.containsKey(s)) {
                return this.c.get(s);
            }
            final bf bf = new bf(this);
            this.c.put(s, bf);
            this.g.a(s);
            final ImageIcon fetch;
            if (this.i != null && (fetch = this.i.fetch(au.a(s))) != null) {
                bf.b(fetch.getImage());
            }
            return bf;
        }
    }
    
    public final ImageIcon a(final String s) {
        final bf c;
        if ((c = this.c(s)) == null) {
            return null;
        }
        return c.a();
    }
    
    public final void a() {
        this.g.a();
        this.g = null;
        System.out.println("disposing icons...");
        synchronized (this.c) {
            this.c.clear();
        }
        final F f;
        f.f.getImage().flush();
        f.f = null;
    }
    
    public void update(Observable observable, Object o2) {
        try {
            if (o instanceof j) {
                if (this.c == null) {
                    return;
                }
                observable = (Observable)o;
                o2 = 0;
                synchronized (this.c) {
                    if (this.c.containsKey(((j)observable).a)) {
                        final bf bf;
                        if ((bf = this.c.get(((j)observable).a)) == null) {
                            return;
                        }
                        try {
                            bf.a(((j)observable).b.a);
                        }
                        catch (ah ah) {
                            this.a(((j)observable).a, ah);
                            return;
                        }
                        if (!bf.e()) {
                            this.g.a(((j)observable).a);
                        }
                        o2 = 1;
                    }
                }
                if (o2 != 0) {
                    final Observable observable2;
                    observable2.setChanged();
                    observable2.notifyObservers(observable);
                }
            }
            else {
                if (!(o instanceof bc)) {
                    throw new RuntimeException("Unexpected event type: " + o);
                }
                final bc bc = (bc)o;
                this.a(bc.a, bc.b);
            }
        }
        catch (NullPointerException ex) {}
    }
    
    private void a(final String s, final Exception ex) {
        synchronized (this.c) {
            if (this.c.containsKey(s)) {
                if (ex instanceof am) {
                    this.c.get(s).d();
                }
                else {
                    this.c.get(s).c();
                }
            }
        }
        final Observable observable;
        final bc bc = new bc(observable, s, ex);
        observable.setChanged();
        observable.notifyObservers(bc);
    }
    
    public final void b(String string) {
        this.g.b(string);
        this.i = null;
        string = string + File.separatorChar + "thumbs.db";
        if (new File(string).exists()) {
            try {
                this.i = new Bubba(string);
            }
            catch (Exception ex) {
                System.out.println("Failed to load " + string + ": " + ex.toString());
            }
        }
    }
    
    public final Hashtable a(final List list) {
        final Hashtable<String, Image> hashtable = new Hashtable<String, Image>();
        for (final String s : list) {
            final bf bf;
            final Image b;
            if (this.c.containsKey(s) && !(bf = this.c.get(s)).f() && (b = bf.b()) != null) {
                hashtable.put(s, b);
            }
        }
        return hashtable;
    }
    
    public final void a(final String s, final boolean b) {
        this.g.a(s, b);
    }
    
    static {
        F.j = !F.class.desiredAssertionStatus();
    }
}
