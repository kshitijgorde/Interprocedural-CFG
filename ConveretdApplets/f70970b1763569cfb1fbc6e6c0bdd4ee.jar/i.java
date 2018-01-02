import java.io.IOException;

// 
// Decompiled by Procyon v0.5.30
// 

public final class i extends Thread
{
    private e a;
    private boolean b;
    private m c;
    private int d;
    private g e;
    
    public i() {
        this.c = new m();
        this.a = null;
        this.b = true;
        this.d = 0;
        this.e = null;
        this.start();
    }
    
    public final void a(final e a) {
        if (this.a != a) {
            this.a = a;
            this.d();
        }
    }
    
    public final void a(final int d, final g e) {
        this.e = e;
        this.d = d;
        this.f();
    }
    
    public final void a() {
        this.b = false;
        this.f();
    }
    
    public final void a(final g g) {
        if (this.a == null) {
            return;
        }
        try {
            synchronized (this.c) {
                this.c.a(g);
            }
            this.f();
        }
        catch (Exception ex) {
            System.out.println("ServiceMessageWriter.append(): " + ex);
            ex.printStackTrace();
        }
    }
    
    private void b() {
        if (this.a != null) {
            synchronized (this.c) {
                if (this.c.a == 0) {
                    this.c.a(this.e);
                }
            }
        }
    }
    
    private g c() {
        g g = null;
        if (this.a != null) {
            synchronized (this.c) {
                if (this.c.a > 0) {
                    g = (g)this.c.b();
                }
            }
        }
        return g;
    }
    
    private void d() {
        try {
            synchronized (this.c) {
                this.c.c();
            }
            this.f();
        }
        catch (Exception ex) {
            System.out.println("ServiceMessageWriter.clear(): " + ex);
            ex.printStackTrace();
        }
    }
    
    public final void run() {
        int n = 0;
        while (this.b) {
            try {
                if (this.a == null) {
                    this.e();
                }
                while (this.b && this.a != null) {
                    g c;
                    while ((c = this.c()) != null) {
                        this.a.a(c.a());
                        ++n;
                    }
                    if (n > 0) {
                        n = 0;
                        this.a.a();
                    }
                    if (c == null) {
                        this.e();
                        this.b();
                    }
                }
            }
            catch (IOException ex2) {
                try {
                    if (this.a != null) {
                        this.a.b();
                    }
                }
                catch (Exception ex3) {}
                this.a = null;
            }
            catch (Exception ex) {
                System.out.println("ServiceMessageWriter.run(): " + ex);
                ex.printStackTrace();
            }
        }
    }
    
    private synchronized void e() {
        if ((this.c.a <= 0 || this.a == null) && this.b) {
            try {
                this.wait(this.d);
            }
            catch (Exception ex) {
                System.out.println("ServiceMessageWriter.doWait(): " + ex);
            }
        }
    }
    
    private synchronized void f() {
        try {
            this.notify();
        }
        catch (Exception ex) {
            System.out.println("ServiceMessageWriter.doNotify(): " + ex);
        }
    }
}
