import java.awt.event.ActionListener;
import au.com.rocketdog.project.awc.applet.Main;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Dimension;
import au.com.rocketdog.project.awc.applet.images.ImageRes;
import java.awt.PopupMenu;

// 
// Decompiled by Procyon v0.5.30
// 

public class a0 extends au implements Runnable
{
    private Thread a;
    private boolean b;
    private static al c;
    private PopupMenu d;
    
    public void a(final ba ba) {
        if (this.b) {
            if (ba.f()) {
                ba.a(ImageRes.a3);
            }
            else if (!ba.b() && !ba.n()) {
                ba.a(ImageRes.a2);
            }
        }
        super.a(ba);
    }
    
    public int a() {
        return 15;
    }
    
    public void run() {
        try {
            final m a = m.a(n.b(), 0, false);
            try {
                while (!this.a.isInterrupted() || this.b) {
                    int a2 = this.a();
                    final av[] f = this.f();
                    for (int i = 0; i < f.length; ++i) {
                        final ba ba = (ba)f[i];
                        if (ba.f()) {
                            ba.a(ImageRes.a3);
                        }
                        else if (!ba.b() && !ba.n()) {
                            ba.a(ImageRes.a2);
                        }
                        if (!ba.n()) {
                            a2 += ba.g().height;
                        }
                    }
                    this.a(new Dimension(179, a2));
                    this.c();
                    for (int j = 0; j < f.length; ++j) {
                        final ba ba2 = (ba)f[j];
                        if (ba2 != null && !ba2.n()) {
                            if (!ba2.f()) {
                                final b5 a3 = a.a(ba2);
                                if (this.a == null || this.a.isInterrupted() || !this.b) {
                                    return;
                                }
                                ba2.a(a3);
                                ba2.p();
                            }
                            else {
                                ba2.a(ImageRes.a3);
                            }
                        }
                    }
                    synchronized (this.a) {
                        this.a.wait(60000L);
                    }
                }
            }
            catch (InterruptedException ex2) {}
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public boolean t() {
        return this.b;
    }
    
    public synchronized void u() {
        this.b = true;
        (this.a = new Thread(this)).start();
        if (!this.e()) {
            this.a(true);
        }
    }
    
    public synchronized void v() {
        this.b = false;
        if (this.a != null) {
            this.a.interrupt();
        }
        this.a = null;
        final av[] f = this.f();
        for (int i = 0; i < f.length; ++i) {
            ((ba)f[i]).d();
        }
    }
    
    public a0(final z z, final String s, final boolean b) {
        super(z, s, b);
        this.b = false;
    }
    
    public void a(final Graphics graphics, final Component component, final int n, final int n2) {
        if (this.t()) {
            graphics.drawImage(ImageRes.am, n + 150, n2 + 2, component);
        }
        else {
            graphics.drawImage(ImageRes.an, n + 150, n2 + 2, component);
        }
    }
    
    public PopupMenu b() {
        if (this.d == null) {
            this.d = new PopupMenu();
        }
        return this.d;
    }
    
    public void a(final int n, final int n2) {
        if (n > this.k() + 148 & n <= this.k() + 165 & (n2 > this.l() & n2 <= this.l() + 15)) {
            if (this.t()) {
                this.v();
            }
            else if (n.b().t()) {
                this.u();
            }
            else {
                a0.c.setVisible(true);
            }
            this.p();
        }
    }
    
    static {
        a0.c = new al(Main.h(), Main.p.a("dialog.goldsilverupgrade"), 400, 100);
        a0.c.a.addActionListener(new am());
    }
}
