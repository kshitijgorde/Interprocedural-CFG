import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class bk extends Thread
{
    protected IpixViewer a;
    protected Vector b;
    protected Vector c;
    protected y d;
    protected long e;
    private long f;
    private long g;
    private int h;
    private long i;
    private boolean j;
    protected boolean k;
    protected Object l;
    private bh m;
    
    bk(final IpixViewer a) {
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
    
    protected y a() {
        return new be(this);
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
        if (IpixViewer.p() > 2.1f) {
            this.b.addElement(new bb(this));
        }
        try {
            if ((startsWith && startsWith2) || b || IpixViewer.p() <= 2.0f) {
                this.b.addElement(new bd(this));
            }
            else {
                this.b.addElement(this.a());
            }
        }
        catch (Throwable t2) {
            this.b.addElement(new bd(this));
        }
        try {
            this.m = new bh();
            while (!this.j) {
                for (y y = this.d; y != null; y = this.d) {
                    if (this.j) {
                        return;
                    }
                    final float[] j = this.a.j();
                    final float[] a = bj.a(j);
                    if (y.a(a)) {
                        final float[] a2 = bj.a(j, a);
                        final boolean a3 = this.a.a(a, false);
                        if (!a3 && y.a(2)) {
                            this.b(y);
                        }
                        this.m.a(a2);
                        this.a(this.m);
                        if (a3) {
                            final long currentTimeMillis = System.currentTimeMillis();
                            this.a.k();
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
                this.a.a(this.a.j(), true);
                this.a(new bh(new float[5]));
                this.a.k();
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
                ((y)this.b.elementAt(i)).a();
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
    
    synchronized boolean a(final y d) {
        if (this.d != null && !this.d.a((d != null) ? 1 : 0)) {
            return false;
        }
        this.b(this.d);
        if (d != null) {
            this.d = d;
            if (d instanceof bf) {
                this.e += 25L;
            }
            if (d instanceof z || d instanceof ba) {
                final IpixViewer a = this.a;
                a.bl |= IpixViewer.bm;
            }
        }
        return true;
    }
    
    synchronized void b(final y y) {
        if (y == this.d) {
            this.d = null;
            if (y instanceof bf) {
                this.e -= 25L;
            }
            if (y instanceof z || y instanceof ba) {
                final IpixViewer a = this.a;
                a.bl &= ~IpixViewer.bm;
            }
        }
    }
    
    boolean c(final y y) {
        if (y == null) {
            return this.d != null;
        }
        return y == this.d;
    }
    
    y c() {
        return this.d;
    }
    
    float d() {
        return 1000000.0f / this.f;
    }
    
    void a(final bi bi) {
        this.c.addElement(bi);
    }
    
    void b(final bi bi) {
        this.c.removeElement(bi);
    }
    
    protected void a(final bh bh) {
        for (int i = 0; i < this.c.size(); ++i) {
            ((bi)this.c.elementAt(i)).a(bh);
        }
    }
}
