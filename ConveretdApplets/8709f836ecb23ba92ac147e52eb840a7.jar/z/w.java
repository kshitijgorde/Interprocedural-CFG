// 
// Decompiled by Procyon v0.5.30
// 

package z;

import java.util.List;
import java.util.Collections;
import java.util.Vector;
import java.net.URL;
import java.awt.Dimension;
import java.util.Iterator;
import java.util.Enumeration;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Observer;
import java.util.Observable;

public class w extends Observable implements Observer
{
    private Hashtable a;
    private ArrayList b;
    private final ax c;
    private final float d;
    private final int e;
    private final int f;
    private boolean g;
    private long h;
    private boolean i;
    private final Object j;
    private int k;
    private F l;
    private Image m;
    private static /* synthetic */ boolean n;
    
    public w(final float d, final int e, final int f, final ax c) {
        this.a = new Hashtable();
        this.b = new ArrayList();
        this.g = false;
        this.j = new Object();
        this.k = 0;
        if (d <= 0.0f || d > 1.0f) {
            throw new RuntimeException("Invalid parameter: jpegQFactor");
        }
        this.d = d;
        this.e = e;
        this.f = f;
        (this.c = c).addObserver(this);
    }
    
    private void b(final String s, final boolean b) {
        if (!w.n && (s == null || s.length() <= 0)) {
            throw new AssertionError();
        }
        this.setChanged();
        this.notifyObservers(new aN(this, s, b));
    }
    
    public final synchronized void a(final String s, final ImageIcon imageIcon, final boolean b) {
        if (!w.n && (s == null || s.length() <= 0)) {
            throw new AssertionError();
        }
        if (!w.n && imageIcon == null) {
            throw new AssertionError();
        }
        if (!w.n && this.a.containsKey(s)) {
            throw new AssertionError();
        }
        final aw aw = new aw(s);
        this.a.put(s, aw);
        this.b.add(s);
        aw.a(new ImageIcon(imageIcon.getImage()));
        this.b(s, b);
    }
    
    private synchronized aw m(final String s) {
        if (!w.n && s == null) {
            throw new AssertionError();
        }
        final aw aw;
        if ((aw = this.a.get(s)) == null) {
            throw new RuntimeException("Photo not found in table: " + s);
        }
        return aw;
    }
    
    public final ImageIcon a(final String s) {
        if (!w.n && (s == null || s.length() <= 0)) {
            throw new AssertionError();
        }
        return this.m(s).h();
    }
    
    public final synchronized void a() {
        final ArrayList<aw> list = new ArrayList<aw>();
        final Enumeration<aw> elements = this.a.elements();
        while (elements.hasMoreElements()) {
            list.add(elements.nextElement());
        }
        for (final aw aw : list) {
            System.out.println("PhotoManager: removing photo " + aw.c());
            this.f(aw.c());
        }
    }
    
    public static ImageIcon a(final String s, final Dimension dimension) {
        if (!w.n && (s == null || s.length() <= 0)) {
            throw new AssertionError();
        }
        if (!w.n && (dimension.width <= 0 || dimension.height <= 0)) {
            throw new AssertionError();
        }
        System.out.println("PhotoManager.getImage: " + s);
        return V.a(new ImageIcon(new ImageIcon(V.b(s)).getImage()), dimension, Z.b);
    }
    
    public final int b(final String s) {
        if (!w.n && (s == null || s.length() <= 0)) {
            throw new AssertionError();
        }
        final long f;
        if ((f = this.m(s).f()) > 2147483647L) {
            throw new RuntimeException("File too big for uploader: " + s);
        }
        return (int)f;
    }
    
    public final String c(final String s) {
        if (!w.n && (s == null || s.length() <= 0)) {
            throw new AssertionError();
        }
        return this.m(s).d();
    }
    
    public final int d(final String s) {
        if (!w.n && (s == null || s.length() <= 0)) {
            throw new AssertionError();
        }
        final int g = this.m(s).g();
        if (!w.n && (0 > g || g > 100)) {
            throw new AssertionError();
        }
        return g;
    }
    
    public final synchronized void a(final URL url, final URL url2, final URL url3, final boolean g, final String s, final String s2, final String s3, final String s4) {
        if (this.a.size() == 0) {
            return;
        }
        this.g = g;
        final ArrayList<aD> list = new ArrayList<aD>();
        final Iterator<String> iterator = this.h().iterator();
        while (iterator.hasNext()) {
            final aw m = this.m(iterator.next());
            final aD ad;
            (ad = new aD()).a = m.c();
            ad.b = m.f();
            ad.c = m.i();
            ad.f = this;
            list.add(ad);
        }
        final ArrayList<aD> list2 = list;
        this.h = System.currentTimeMillis();
        this.i = false;
        this.setChanged();
        this.notifyObservers(new D(this, Y.b));
        new N(this, url, url2, url3, list2, s, s2, s3, s4).start();
    }
    
    private Vector h() {
        final Vector<Comparable<? super T>> vector = new Vector<Comparable<? super T>>(this.a.size());
        final Enumeration<T> keys = this.a.keys();
        while (keys.hasMoreElements()) {
            vector.add(keys.nextElement());
        }
        Collections.sort((List<Comparable>)vector);
        return vector;
    }
    
    protected final void b() {
        this.c.c();
        this.setChanged();
        this.notifyObservers(new aU(this));
    }
    
    public void update(final Observable observable, final Object o) {
        if (!(o instanceof n)) {
            if (o instanceof p) {
                this.a(o);
                synchronized (this.j) {
                    ++this.k;
                }
                final w w;
                if (w.k >= 3) {
                    w.b();
                }
            }
            else {
                if (o instanceof P) {
                    this.m(((P)o).a).j();
                }
                else {
                    if (o instanceof bl) {
                        if (((bl)o).a) {
                            this.setChanged();
                            this.notifyObservers(new D(this, Y.b));
                        }
                        else if (!this.i) {
                            System.out.println("TIME (batch init) " + (System.currentTimeMillis() - this.h) / 1000L + " sec");
                            this.h = System.currentTimeMillis();
                            this.i = true;
                        }
                        this.a(o);
                        return;
                    }
                    if (o instanceof ad) {
                        if (this.l != null) {
                            this.l.a();
                        }
                        System.out.println("TIME (photo data upload) " + (System.currentTimeMillis() - this.h) / 1000L + " sec");
                        return;
                    }
                    if (!(o instanceof bs) && !(o instanceof C) && !(o instanceof j) && !(o instanceof bc)) {
                        return;
                    }
                }
                this.a(o);
            }
            return;
        }
        final n n = (n)o;
        if (!w.n && (n.a == null || n.a.length() <= 0)) {
            throw new AssertionError();
        }
        if (!w.n && n.b <= 0) {
            throw new AssertionError();
        }
        this.m(n.a).a(n.b);
        final int g = this.m(n.a).g();
        this.setChanged();
        this.notifyObservers(new ba(this, n.a, g));
    }
    
    private void a(final Object o) {
        this.setChanged();
        this.notifyObservers(o);
    }
    
    public final void c() {
        this.c.b();
    }
    
    public final String e(final String s) {
        return this.m(s).e();
    }
    
    public final void f(String s) {
        this.a.remove(s);
        final w w = this;
        s = s;
        this = w;
        if (!z.w.n && (s == null || s.length() <= 0)) {
            throw new AssertionError();
        }
        this.setChanged();
        this.notifyObservers(new an(this, s));
    }
    
    public final void d() {
        this.c.c();
    }
    
    public final int e() {
        return this.a.size();
    }
    
    public final ag g(final String s) {
        if (!w.n && (s == null || s.length() <= 0)) {
            throw new AssertionError();
        }
        try {
            return this.m(s).a(this.d, this.g, this.e, this.f);
        }
        catch (ah c) {
            System.out.println("getPhotoJpegStream: trapped memory exc;  retrying with full resolution stream");
            final ag a;
            (a = this.m(s).a(this.d, false, -1, -1)).c = c;
            return a;
        }
        catch (ae c2) {
            System.out.println("getPhotoJpegStream: sending full resolution stream");
            final ag a2;
            (a2 = this.m(s).a(this.d, false, -1, -1)).c = c2;
            return a2;
        }
    }
    
    public final int h(final String s) {
        if (!w.n && (s == null || s.length() <= 0)) {
            throw new AssertionError();
        }
        return this.m(s).a();
    }
    
    public final int i(final String s) {
        if (!w.n && (s == null || s.length() <= 0)) {
            throw new AssertionError();
        }
        return this.m(s).b();
    }
    
    public final void a(final int n, final int n2, final URL url, final URL url2, final URL url3, final boolean b) {
        if (!w.n && this.l != null) {
            throw new AssertionError();
        }
        this.m = new ImageIcon(V.a(url).getImage().getScaledInstance(n2, n2, 4)).getImage();
        (this.l = new F(120, n2, this.m, url2, url3, b)).addObserver(this);
    }
    
    public final void f() {
        this.setChanged();
        this.notifyObservers(new D(this, Y.a));
    }
    
    public final void j(final String s) {
        this.l.b(s);
    }
    
    public final ImageIcon k(final String s) {
        return this.l.a(s);
    }
    
    public final Hashtable a(final List list) {
        return this.l.a(list);
    }
    
    public final void a(final String s, final boolean b) {
        this.l.a(s, b);
    }
    
    public final void g() {
        this.l.a();
        this.l = null;
    }
    
    public final void l(final String s) {
        this.c.a(s);
    }
    
    static {
        w.n = !w.class.desiredAssertionStatus();
    }
}
