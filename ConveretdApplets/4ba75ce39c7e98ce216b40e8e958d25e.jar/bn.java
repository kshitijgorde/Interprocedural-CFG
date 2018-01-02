import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class bn extends Thread
{
    protected IpixViewer a;
    protected Vector b;
    protected ba c;
    protected Vector d;
    protected long e;
    private long f;
    private long g;
    private int h;
    private long i;
    
    void a(final bl bl) {
        this.d.addElement(bl);
    }
    
    float a() {
        return 1000000.0f / this.f;
    }
    
    bn(final IpixViewer a) {
        this.b = new Vector();
        this.d = new Vector();
        this.e = 1L;
        this.f = 15000L;
        this.g = 0L;
        this.h = 1;
        this.i = 0L;
        this.a = a;
    }
    
    synchronized boolean a(final ba c) {
        if (this.c != null && !this.c.a((c != null) ? 1 : 0)) {
            return false;
        }
        if (c == null) {
            this.b(this.c);
        }
        else {
            this.c = c;
        }
        return true;
    }
    
    synchronized void b(final ba ba) {
        if (ba == this.c) {
            this.c = null;
        }
    }
    
    IpixViewer b() {
        return this.a;
    }
    
    protected void a(final bk bk) {
        for (int i = 0; i < this.d.size(); ++i) {
            ((bl)this.d.elementAt(i)).a(bk);
        }
    }
    
    protected ba c() {
        return new bf(this);
    }
    
    void b(final bl bl) {
        this.d.removeElement(bl);
    }
    
    public void run() {
        this.setPriority(1);
        final boolean startsWith = System.getProperty("os.name").startsWith("Mac");
        final boolean startsWith2 = System.getProperty("java.vendor").startsWith("Netscape");
        final boolean b = System.getProperty("java.version").compareTo("1.1") < 0;
        if (startsWith && startsWith2) {
            this.e = 50L;
        }
        if (IpixViewer.h() > 2.0f) {
            this.b.addElement(new bb(this));
        }
        Label_0157: {
            try {
                if ((startsWith && startsWith2) || b || IpixViewer.h() <= 2.0f) {
                    this.b.addElement(new be(this));
                    break Label_0157;
                }
                this.b.addElement(this.c());
                break Label_0157;
            }
            catch (Throwable t2) {
                this.b.addElement(new be(this));
            }
            try {
                while (true) {
                    for (ba ba = this.c; ba != null; ba = this.c) {
                        final float[] a = this.a.a();
                        final float[] a2 = bm.a(a);
                        if (ba.a(a2)) {
                            final float[] b2 = bm.b(a, a2);
                            final boolean a3 = this.a.a(a2, false);
                            if (!a3 && ba.a(2)) {
                                this.b(ba);
                            }
                            this.a(new bk(b2));
                            if (a3) {
                                final long currentTimeMillis = System.currentTimeMillis();
                                this.a.f();
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
                    this.a.a(this.a.a(), true);
                    this.a(new bk(new float[5]));
                    this.a.f();
                    while (this.c == null) {
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
                    ((ba)this.b.elementAt(i)).a();
                }
                this.b.removeAllElements();
                this.c = null;
            }
        }
    }
    
    ba d() {
        return this.c;
    }
    
    boolean c(final ba ba) {
        if (ba == null) {
            return this.c != null;
        }
        return ba == this.c;
    }
}
