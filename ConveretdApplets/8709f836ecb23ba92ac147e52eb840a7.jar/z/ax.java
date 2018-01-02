// 
// Decompiled by Procyon v0.5.30
// 

package z;

import java.util.Iterator;
import java.util.TimerTask;
import java.util.ArrayList;
import java.net.URL;
import com.photochannel.easyUploader.AppletParams;
import java.util.Timer;
import java.util.Hashtable;
import java.util.Observer;
import java.util.Observable;

public class ax extends Observable implements Observer
{
    private Hashtable a;
    private aZ b;
    private aG c;
    private long d;
    private final Object e;
    private long f;
    private Hashtable g;
    private long h;
    private int i;
    private int j;
    private Timer k;
    private int l;
    private static /* synthetic */ boolean m;
    
    public ax(final AppletParams appletParams) {
        this.a = new Hashtable();
        this.d = -1L;
        this.e = new Object();
        this.g = new Hashtable();
        this.l = 1;
        if (!ax.m && appletParams.getUploadMaxThreads() <= 0) {
            throw new AssertionError();
        }
        this.b = new aZ(appletParams.getUploadMaxThreads());
    }
    
    public final void a(final URL url, final URL url2, final URL url3, final ArrayList list, final String s, final String s2, final String s3, final String s4, final boolean b) {
        this.c = new aG(s, s2, url2, url3);
        if (!this.a.isEmpty()) {
            System.out.println("Uploader: discarding start upload request -- upload already in progress.");
            return;
        }
        final bl bl;
        (bl = new bl(this)).a = true;
        this.setChanged();
        this.notifyObservers(bl);
        (this.k = new Timer(true)).scheduleAtFixedRate(new k(this, this), 1950L, 1950L);
        if (!this.a(url, list, s3, s4, b)) {
            throw new u();
        }
        this.k.cancel();
        this.k = null;
        synchronized (this.e) {
            for (final aD ad : list) {
                this.g.put(ad.a, ad.c);
            }
            this.h = 0L;
            this.i = list.size();
            this.j = 0;
            this.d();
        }
        final ax ax;
        System.out.println("Starting upload of " + ax.i + " photos....");
        ax.d = System.currentTimeMillis();
        for (final aD ad2 : list) {
            final b b2;
            (b2 = new b(ad2, ax.c, null)).addObserver(ax);
            ax.a.put(ad2.a, b2);
            ax.b.a(b2);
        }
    }
    
    protected final void a() {
        if (this.l < 100) {
            ++this.l;
        }
        final bl bl;
        (bl = new bl(this)).a = true;
        bl.b = this.l;
        this.setChanged();
        this.notifyObservers(bl);
    }
    
    private boolean a(final URL url, final ArrayList list, final String s, final String s2, final boolean b) {
        int i = 0;
        while (i < 2) {
            try {
                this.c.a(url, list, s, s2, b);
                return true;
            }
            catch (u u) {
                u.printStackTrace();
                ++i;
                continue;
            }
            break;
        }
        System.out.println("Remote BatchInit call failed after 2 tries");
        return false;
    }
    
    public final void b() {
        this.b.c();
    }
    
    private long a(final Hashtable hashtable) {
        long n = 0L;
        synchronized (this.e) {
            final Iterator<Long> iterator = hashtable.values().iterator();
            while (iterator.hasNext()) {
                n += iterator.next();
            }
        }
        return n;
    }
    
    private void d() {
        final bl bl = new bl(this);
        synchronized (this.e) {
            bl.c = this.j;
            bl.d = this.i;
            final long n;
            final int e;
            bl.e = (((n = this.f - this.h) < 0L) ? 0 : (((e = this.e()) == 0) ? -1 : ((int)(n / e / 1000L))));
            bl.f = this.e();
            if (this.j == this.i) {
                bl.b = 100;
                bl.e = -1;
            }
            else {
                final double n2 = this.h / this.f * 100.0;
                final double n3 = 0.01 * this.l;
                bl.b = (int)(n2 * (1.0 - n3) + n3 * 100.0);
                if (bl.b > 100) {
                    bl.b = 100;
                }
            }
        }
        final Observable observable;
        observable.setChanged();
        observable.notifyObservers(bl);
    }
    
    private int e() {
        if (this.d < 0L) {
            return 0;
        }
        final long n;
        if ((n = System.currentTimeMillis() - this.d) <= 0L) {
            return 0;
        }
        return (int)(this.h / n);
    }
    
    public final void c() {
        this.b.b();
        final Iterator<b> iterator = this.a.values().iterator();
        while (iterator.hasNext()) {
            iterator.next().a();
        }
    }
    
    public void update(final Observable observable, final Object o) {
        if (o instanceof bk) {
            synchronized (this.e) {
                final bk bk = (bk)o;
                synchronized (this.e) {
                    this.g.put(bk.a, (long)bk.b);
                    this.f = this.a(this.g);
                }
                final ax ax;
                ax.d();
                return;
            }
        }
        if (o instanceof n) {
            this.a(o);
            synchronized (this.e) {
                this.h += ((n)o).b;
            }
            final ax ax2;
            ax2.d();
            return;
        }
        if (o instanceof bs) {
            this.a(o);
            synchronized (this.e) {
                ++this.j;
                if (!ax.m && this.j > this.i) {
                    throw new AssertionError();
                }
            }
            final ax ax3;
            ax3.d();
            if (ax3.j == ax3.i) {
                ax3.a.clear();
                final ax ax4 = ax3;
                final String c = ax3.c.c();
                final String d = ax3.c.d();
                final String s = c;
                final ax ax5 = ax4;
                System.out.println("UploadController: total batch time = " + (System.currentTimeMillis() - ax5.d) / 1000L + " sec");
                final ad ad = new ad(ax5, s, d);
                ax5.setChanged();
                ax5.notifyObservers(ad);
            }
        }
        else {
            if (o instanceof p) {
                this.a(o);
                final p p2;
                if (!(p2 = (p)o).b) {
                    this.update(this, new bs(this, p2.a, false));
                }
                return;
            }
            if (o instanceof C || o instanceof P) {
                this.a(o);
                return;
            }
            throw new RuntimeException("Unexpected event type: " + o);
        }
    }
    
    private void a(final Object o) {
        if (!ax.m && o == null) {
            throw new AssertionError();
        }
        this.setChanged();
        this.notifyObservers(o);
    }
    
    public final void a(final String s) {
        if (this.c != null) {
            this.c.a(s);
        }
    }
    
    static {
        ax.m = !ax.class.desiredAssertionStatus();
    }
}
