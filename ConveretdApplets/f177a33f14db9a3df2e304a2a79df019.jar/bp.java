import java.io.IOException;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

class bp extends Thread
{
    protected URL a;
    protected q b;
    protected e c;
    private final IpixViewer d;
    
    public q a() {
        return this.b;
    }
    
    public bp(final IpixViewer d, final URL a, final e c) {
        this.d = d;
        this.a = a;
        this.c = c;
    }
    
    public void run() {
        final IpixViewer d = this.d;
        d.bj |= IpixViewer.bo;
        this.d.j = new Object();
        try {
            this.b = this.d.a(this.d.a(this.a), this.a, this.c);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        catch (InterruptedException ex2) {
            ex2.printStackTrace();
        }
        this.d.j = null;
        synchronized (this.d.i) {
            this.d.i.notify();
        }
        final IpixViewer d2 = this.d;
        d2.bj &= ~IpixViewer.bo;
    }
}
