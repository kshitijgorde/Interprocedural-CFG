import au.com.rocketdog.project.awc.applet.images.ImageRes;

// 
// Decompiled by Procyon v0.5.30
// 

public class cq extends Thread
{
    public ck a;
    public boolean b;
    
    public cq(final ck a) {
        this.b = false;
        this.a = a;
    }
    
    public synchronized void run() {
        try {
            this.a.r.c(ImageRes.bf);
            this.a.r.a(ImageRes.bf);
            final m a = m.a(n.b(), 0, false);
            try {
                while (!this.isInterrupted() || !this.b) {
                    this.a.r.b(ImageRes.ao);
                    this.a.r.disable();
                    final av[] b = this.a.f.b();
                    for (int i = 0; i < b.length; ++i) {
                        final cz cz = (cz)b[i];
                        if (this.isInterrupted() || this.b) {
                            this.a();
                            return;
                        }
                        if (cz != null && !cz.e() && !cz.y() && cz.v()) {
                            cz.a(a.a(cz));
                        }
                    }
                    this.a.f.a();
                    this.a.r.enable();
                    this.wait(60000L);
                }
            }
            catch (InterruptedException ex) {}
            this.a();
        }
        catch (Exception ex2) {}
    }
    
    private void a() {
        final av[] b = this.a.f.b();
        for (int i = 0; i < b.length; ++i) {
            final cz cz = (cz)b[i];
            if (cz != null && !cz.e() && cz.v()) {
                cz.b();
            }
        }
        this.a.r.enable();
        this.a.f.a();
        this.a.r.c(ImageRes.aj);
        this.a.r.a(ImageRes.aj);
    }
}
