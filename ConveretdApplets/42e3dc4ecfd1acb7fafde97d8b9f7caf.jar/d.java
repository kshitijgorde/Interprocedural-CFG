import java.awt.Color;
import au.com.rocketdog.project.awc.applet.Main;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class d extends Thread
{
    private URL a;
    private long b;
    private g c;
    private boolean d;
    
    public d() {
        this.d = false;
    }
    
    public synchronized void d() {
        this.d = true;
        this.interrupt();
        this.b();
    }
    
    public void a(final g c) {
        this.c = c;
    }
    
    public long e() {
        return this.b;
    }
    
    public void a(final long b) {
        this.b = b;
    }
    
    public abstract void a();
    
    public abstract void b();
    
    public abstract void c();
    
    public URL f() {
        return this.a;
    }
    
    public void a(final URL a) {
        this.a = a;
    }
    
    public synchronized void run() {
        this.a();
        this.d = false;
        try {
            while (!this.d) {
                this.c.a(this.getName() + Main.p.a("directory.updater.1"));
                this.c.setBackground(Color.red);
                this.c();
                this.c.setBackground(Color.white);
                for (int n = 0; n < this.e(); ++n) {
                    this.wait(1000L);
                    this.c.b(n);
                    this.c.a(this.e() - n + Main.p.a("directory.updater.1") + this.getName() + Main.p.a("directory.updater.3"));
                }
                this.wait(1000L);
                this.c.b(0L);
            }
        }
        catch (InterruptedException ex) {
            b.a("Stopping " + this.getName(), 3);
        }
    }
}
