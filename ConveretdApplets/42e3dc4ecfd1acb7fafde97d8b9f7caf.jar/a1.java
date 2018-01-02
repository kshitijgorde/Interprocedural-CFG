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

public class a1 extends au implements Runnable
{
    private Thread a;
    private boolean b;
    private static al c;
    private PopupMenu d;
    
    public void a(final bh bh) {
        if (this.b && !bh.n()) {
            bh.a(ImageRes.a2);
        }
        super.a(bh);
    }
    
    public int a() {
        return 15;
    }
    
    public void run() {
        try {
            while (!this.a.isInterrupted() || this.b) {
                int a = this.a();
                final av[] f = this.f();
                for (int i = 0; i < f.length; ++i) {
                    final bh bh = (bh)f[i];
                    if (!bh.n()) {
                        bh.a(ImageRes.a2);
                    }
                    if (!bh.n()) {
                        a += bh.g().height;
                    }
                }
                this.a(new Dimension(179, a));
                this.c();
                for (int j = 0; j < f.length; ++j) {
                    final bh bh2 = (bh)f[j];
                    if (bh2 != null && !bh2.n()) {
                        if (this.a == null || this.a.isInterrupted() || !this.b) {
                            return;
                        }
                        bh2.f();
                        bh2.p();
                    }
                }
                synchronized (this.a) {
                    this.a.wait(120000L);
                }
            }
        }
        catch (InterruptedException ex) {}
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
            ((bh)f[i]).c();
        }
    }
    
    public a1(final z z, final String s, final boolean b) {
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
                a1.c.setVisible(true);
            }
            this.p();
        }
    }
    
    static {
        a1.c = new al(Main.h(), Main.p.a("dialog.goldsilverupgrade"), 400, 100);
        a1.c.a.addActionListener(new am());
    }
}
