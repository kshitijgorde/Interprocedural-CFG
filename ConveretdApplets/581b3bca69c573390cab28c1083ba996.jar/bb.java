import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class bb extends Thread
{
    protected IpixViewer a;
    protected Vector b;
    protected Vector c;
    protected r d;
    protected long e;
    private long f;
    private long g;
    private int h;
    private long i;
    private boolean j;
    protected boolean k;
    protected Object l;
    private y m;
    
    bb(final IpixViewer a) {
        this.b = new Vector();
        this.c = new Vector();
        this.e = 1L;
        this.f = 15000L;
        this.g = 0L;
        this.h = 1;
        this.i = 0L;
        this.j = false;
        this.k = false;
        this.l = new Object();
        this.m = null;
        this.a = a;
    }
    
    protected r a() {
        return new v(this);
    }
    
    public void run() {
        if (this.j) {
            return;
        }
        this.setPriority(1);
        final boolean startsWith = System.getProperty("os.name").startsWith("Mac");
        final boolean startsWith2 = System.getProperty("java.vendor").startsWith("Netscape");
        final boolean b = System.getProperty("java.version").compareTo("1.1") < 0;
        if (startsWith && startsWith2) {
            this.e = 50L;
        }
        if (IpixViewer.k() > 2.1f) {
            this.b.addElement(new s(this));
        }
        try {
            if ((startsWith && startsWith2) || b || IpixViewer.k() <= 2.0f) {
                this.b.addElement(new u(this));
            }
            else {
                this.b.addElement(this.a());
            }
        }
        catch (Throwable t2) {
            this.b.addElement(new u(this));
        }
        try {
            this.m = new y();
            while (!this.j) {
                for (r r = this.d; r != null; r = this.d) {
                    if (this.j) {
                        return;
                    }
                    final float[] f = this.a.f();
                    final float[] a = ba.a(f);
                    if (r.a(a)) {
                        final float[] a2 = ba.a(f, a);
                        final boolean a3 = this.a.a(a, false);
                        if (!a3 && r.a(2)) {
                            this.b(r);
                        }
                        this.m.a(a2);
                        this.a(this.m);
                        if (a3) {
                            final long currentTimeMillis = System.currentTimeMillis();
                            this.a.g();
                            final long currentTimeMillis2 = System.currentTimeMillis();
                            if (currentTimeMillis2 == currentTimeMillis || currentTimeMillis2 - currentTimeMillis > 33L) {
                                Thread.sleep(1L);
                            }
                            else {
                                Thread.sleep(33L - (currentTimeMillis2 - currentTimeMillis));
                            }
                            final long currentTimeMillis3 = System.currentTimeMillis();
                            long g = (currentTimeMillis3 - currentTimeMillis + (this.e - 1L)) * 1000L;
                            if (g == 0L) {
                                if (this.h > 1) {
                                    g = (currentTimeMillis3 - this.i) * 1000L;
                                }
                                this.i = currentTimeMillis3;
                                ++this.h;
                            }
                            if (g != 0L) {
                                if (g < this.f * 2L || g < 2L * this.g) {
                                    this.f = (g + (50 - this.h) * this.f) / 50L;
                                }
                                this.g = g;
                                this.h = 1;
                            }
                        }
                    }
                    Thread.sleep(this.e);
                }
                if (this.j) {
                    return;
                }
                this.a.a(this.a.f(), true);
                this.a(new y(new float[5]));
                this.a.g();
                while (this.d == null && !this.j) {
                    Thread.sleep(50L);
                }
            }
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
        finally {
            for (int i = 0; i < this.b.size(); ++i) {
                ((r)this.b.elementAt(i)).a();
            }
            this.b.removeAllElements();
            this.d = null;
            synchronized (this.l) {
                this.k = true;
                this.j = false;
            }
        }
    }
    
    void a(final int n) throws InterruptedException, IllegalThreadStateException {
        if (Thread.currentThread() == this) {
            throw new IllegalThreadStateException();
        }
        synchronized (this.l) {
            if (this.k) {
                return;
            }
            this.j = true;
        }
        final long currentTimeMillis = System.currentTimeMillis();
        while (this.j && System.currentTimeMillis() - currentTimeMillis < n) {
            Thread.sleep(10L);
        }
    }
    
    IpixViewer b() {
        return this.a;
    }
    
    synchronized boolean a(final r d) {
        if (this.d != null && !this.d.a((d != null) ? 1 : 0)) {
            return false;
        }
        this.b(this.d);
        if (d != null) {
            this.d = d;
            if (d instanceof w) {
                this.e += 25L;
            }
        }
        return true;
    }
    
    synchronized void b(final r r) {
        if (r == this.d) {
            this.d = null;
            if (r instanceof w) {
                this.e -= 25L;
            }
        }
    }
    
    boolean c(final r r) {
        if (r == null) {
            return this.d != null;
        }
        return r == this.d;
    }
    
    r c() {
        return this.d;
    }
    
    float d() {
        return 1000000.0f / this.f;
    }
    
    void a(final z z) {
        this.c.addElement(z);
    }
    
    void b(final z z) {
        this.c.removeElement(z);
    }
    
    protected void a(final y y) {
        for (int i = 0; i < this.c.size(); ++i) {
            ((z)this.c.elementAt(i)).a(y);
        }
    }
}
