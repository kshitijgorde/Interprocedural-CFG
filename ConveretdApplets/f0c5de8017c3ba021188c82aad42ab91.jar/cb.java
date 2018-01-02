import java.util.Vector;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class cb extends Thread
{
    private l a;
    private static l b;
    private Hashtable c;
    private final long d;
    private Long e;
    private j f;
    
    public cb(final long d, final int n, final int n2) {
        this.a = new l(1);
        this.c = new Hashtable();
        if (d <= 0L) {
            throw new IllegalArgumentException("Scheduler: resolution is " + d + ".");
        }
        this.d = d;
        this.e = new Long(System.currentTimeMillis() / d);
        this.f = new j(n, n2, "SCHED");
        this.start();
    }
    
    private Long a(final long n) {
        final long n2 = (n + this.d) / this.d;
        if (n2 < this.e) {
            return this.e;
        }
        return new Long(n2);
    }
    
    public final Long a(final e e, final long n) {
        synchronized (this.c) {
            final Long a = this.a(n);
            Vector<e> vector = this.c.get(a);
            if (vector == null) {
                vector = new Vector<e>();
                this.c.put(a, vector);
            }
            vector.addElement(e);
            return a;
        }
    }
    
    private void a() {
        while (this.a.b() != 2 && this.e <= System.currentTimeMillis() / this.d) {
            Vector<e> vector;
            synchronized (this.c) {
                vector = this.c.remove(this.e);
                this.e = new Long(this.e + 1L);
            }
            if (vector != null) {
                for (int i = 0; i < vector.size(); ++i) {
                    final e e = vector.elementAt(i);
                    try {
                        final int a = this.f.a(e, "Scheduler width " + e);
                        if (n.d()) {
                            n.d("ret of create is " + a);
                        }
                    }
                    catch (Exception ex) {
                        if (n.a()) {
                            n.a("Job " + e + " threw Exception. ", ex);
                        }
                    }
                }
                vector = null;
            }
        }
    }
    
    public void run() {
        synchronized (this.a) {
            if (this.a.b() != 1) {
                if (n.d()) {
                    n.d("Scheduler already destroyed - skipping run.");
                }
                return;
            }
        }
        synchronized (cb.b) {
            Thread.currentThread().setName("Scheduler:" + cb.b.b());
            cb.b.a();
        }
        int n = 1;
        final int n2 = 100;
        while (true) {
            synchronized (this.a) {
                if (n % n2 == 0) {
                    if (n.c()) {
                        n.c("scheduler alive after " + n + " scans with " + this.c.size() + " slices " + this.hashCode());
                    }
                    n = 1;
                }
                else {
                    ++n;
                }
                if (this.a.b() == 1) {
                    this.a();
                    final long d = this.d;
                    try {
                        this.a.wait(d);
                    }
                    catch (Exception ex) {}
                }
                if (this.a.b() != 2) {
                    continue;
                }
                if (n.d()) {
                    n.d("Scheduler: breaking run loop because of STATUS_DESTROY");
                }
            }
            break;
        }
    }
    
    public void destroy() {
        synchronized (this.a) {
            this.a.a(2);
            this.a.notify();
            if (n.c()) {
                n.c("Scheduler.destroy with " + this.a.hashCode());
            }
        }
        if (this.f != null) {
            this.f.a();
        }
    }
    
    static {
        cb.b = new l(0);
    }
}
