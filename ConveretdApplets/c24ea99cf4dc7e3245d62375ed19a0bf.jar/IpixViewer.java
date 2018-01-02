import java.awt.Component;
import java.awt.Color;
import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class IpixViewer extends Applet implements Runnable
{
    private ipixa a;
    private Thread b;
    private Thread c;
    private int d;
    
    public final void stop() {
        if (this.a.h != null) {
            this.a.h.a();
        }
        if (this.b != null) {
            this.b.stop();
            this.b = null;
        }
        if (this.c != null) {
            this.c.stop();
            this.c = null;
        }
    }
    
    public final void setViewpoint(final float n, final float n2, final float n3, final float n4) {
        this.a.a(new float[] { n * 0.017453292f, n2 * 0.017453292f, n4 });
    }
    
    public IpixViewer() {
        this.d = 0;
    }
    
    public final void spin(final int n) {
        final boolean a = ipixa.a;
        synchronized (this.a) {
            if (this.a.h == null) {
                // monitorexit(this.a)
                return;
            }
            Label_0103: {
                switch (n) {
                    case 2: {
                        if (this.a.bf != 0) {
                            break;
                        }
                        this.a.a(3);
                        if (a) {
                            break Label_0103;
                        }
                        break;
                    }
                    case 1: {
                        if (this.a.bf != 3) {
                            break;
                        }
                        this.a.a(0);
                        if (a) {
                            break Label_0103;
                        }
                        break;
                    }
                    case 0: {
                        if (this.a.bf == 3) {
                            this.a.a(0);
                            if (!a) {
                                break;
                            }
                        }
                        if (this.a.bf == 0) {
                            this.a.a(3);
                            break;
                        }
                        break;
                    }
                }
            }
        }
        // monitorexit(this.a)
    }
    
    public final void start() {
        if (this.b == null) {
            (this.b = new Thread(this.a)).start();
        }
        if (this.d != 0 && this.c == null) {
            (this.c = new Thread(this)).start();
        }
        this.setURL(this.getParameter("URL"));
    }
    
    public final void setURL(final String s) {
        try {
            final URL url = new URL(this.getDocumentBase(), s);
            try {
                if (url.sameFile(this.a.h.getURL())) {
                    return;
                }
            }
            catch (NullPointerException ex) {}
            System.out.println("Loading: " + url);
            if (this.d != 0) {
                url.openConnection().setUseCaches(false);
            }
            this.a.a(new ipixc(this.a, url, url.openStream()));
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            this.a.g();
            t.printStackTrace();
        }
    }
    
    public final void run() {
        while (true) {
            try {
            Label_0083_Outer:
                while (true) {
                    Thread.sleep(this.d * 1000);
                    final URL url = this.a.h.getURL();
                    System.out.println("Refreshing: " + url);
                    url.openConnection().setUseCaches(false);
                    final ipixc ipixc = new ipixc(this.a, url, url.openStream());
                    ipixc.e();
                    while (true) {
                        Label_0089: {
                            if (!ipixa.a) {
                                break Label_0089;
                            }
                            Thread.sleep(100L);
                        }
                        if (!ipixc.b()) {
                            continue Label_0083_Outer;
                        }
                        continue;
                    }
                }
            }
            catch (ThreadDeath threadDeath) {
                throw threadDeath;
            }
            catch (Throwable t) {
                continue;
            }
            break;
        }
    }
    
    public final void init() {
        this.setBackground(Color.white);
        this.getGraphics().clearRect(0, 0, this.size().width, this.size().height);
        (this.a = new ipixa(this, "2.4")).p();
        this.add(this.a);
        this.validate();
        final String parameter = this.getParameter("Refresh");
        if (parameter != null) {
            this.d = Integer.parseInt(parameter);
        }
    }
}
