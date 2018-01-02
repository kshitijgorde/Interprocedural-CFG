import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import au.com.rocketdog.project.awc.applet.Main;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

public class cp extends MouseAdapter
{
    private cq a;
    private al b;
    private n c;
    private ck d;
    
    public cp(final ck d) {
        this.c = n.b();
        this.d = d;
        this.a = new cq(d);
        if (this.c.h() < 1) {
            this.b = new al(Main.h(), Main.p.a("dialog.goldsilverupgrade"), 400, 100);
            this.b.a.addActionListener(new am());
        }
    }
    
    public synchronized void mousePressed(final MouseEvent mouseEvent) {
        try {
            if (mouseEvent == null) {
                this.a.b = true;
                this.a.interrupt();
            }
            if (this.c.h() >= 1) {
                if (!this.a.isAlive()) {
                    this.a.b = false;
                    (this.a = new cq(this.d)).start();
                }
                else {
                    this.a.b = true;
                    this.a.interrupt();
                }
            }
            else {
                this.b.setVisible(true);
            }
        }
        catch (RuntimeException ex) {}
    }
}
