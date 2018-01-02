import au.com.rocketdog.project.awc.applet.Main;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class ch implements ActionListener
{
    private bj a;
    private static al b;
    
    public ch(final bj a) {
        this.a = a;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final MenuItem menuItem = (MenuItem)actionEvent.getSource();
        if (menuItem.getName().equalsIgnoreCase("addfav")) {
            if (n.b().h() >= 1) {
                Main.l(this.a.s());
                h.f().b(this.a);
            }
            else {
                ch.b.setVisible(true);
            }
        }
        if (menuItem.getName().equalsIgnoreCase("remfav")) {
            Main.m(this.a.s());
            h.f().b(this.a);
        }
    }
    
    static {
        ch.b = new al(Main.h(), Main.p.a("dialog.goldsilverupgrade"), 400, 100);
        ch.b.a.addActionListener(new am());
    }
}
