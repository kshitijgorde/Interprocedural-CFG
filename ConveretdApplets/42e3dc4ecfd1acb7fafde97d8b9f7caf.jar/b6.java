import java.awt.Dimension;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import au.com.rocketdog.project.awc.applet.Main;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class b6 implements ActionListener, Runnable
{
    private ba a;
    private static al b;
    private static al c;
    
    public b6(final ba a) {
        this.a = a;
    }
    
    public void run() {
        Main.a(this.a.q(), this.a.j());
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final MenuItem menuItem = (MenuItem)actionEvent.getSource();
        if (menuItem.getName().equalsIgnoreCase("view")) {
            this.a.x();
        }
        if (menuItem.getName().equalsIgnoreCase("pro")) {
            new Thread(this).start();
        }
        if (menuItem.getName().equalsIgnoreCase("history")) {
            Main.b("http://" + Main.b + "/awc/servlet/dispatch?CMD=cmd.cam.viewaudit&uid=" + this.a.q(), "frameContent");
        }
        if (menuItem.getName().equalsIgnoreCase("bud")) {
            l.c(this.a.q());
        }
        if (menuItem.getName().equalsIgnoreCase("chat")) {
            v.a().a(this.a.r());
        }
        if (menuItem.getName().equalsIgnoreCase("addfav")) {
            if (n.b().h() >= 1) {
                Main.j(this.a.s());
                h.f().a(this.a.s(), this.a.w(), this.a.f(), this.a.v());
            }
            else {
                b6.b.setVisible(true);
            }
        }
        if (menuItem.getName().equalsIgnoreCase("remfav")) {
            Main.k(this.a.s());
            h.f().a(this.a.s(), this.a.w(), this.a.f(), this.a.v());
        }
        if (menuItem.getName().equalsIgnoreCase("kick")) {
            final Dimension screenSize = Main.h().getToolkit().getScreenSize();
            final ce ce = new ce(Main.h(), Main.p.a("cams.popupmenu.kick") + " " + this.a.r());
            ce.setSize(190, 80);
            ce.setLocation(screenSize.width / 2 - ce.getSize().width / 2, screenSize.height / 2 - ce.getSize().height / 2);
            ce.a.addActionListener(new c0(this.a, ce, "kick"));
            ce.setVisible(true);
        }
        if (menuItem.getName().equalsIgnoreCase("ban")) {
            final Dimension screenSize2 = Main.h().getToolkit().getScreenSize();
            final c1 c1 = new c1(Main.h(), Main.p.a("cams.popupmenu.ban") + " " + this.a.r(), Main.u);
            c1.setSize(190, 100);
            c1.setLocation(screenSize2.width / 2 - c1.getSize().width / 2, screenSize2.height / 2 - c1.getSize().height / 2);
            c1.a.addActionListener(new c3(this.a, c1, "ban"));
            c1.setVisible(true);
        }
        if (menuItem.getName().equalsIgnoreCase("cg")) {
            final Dimension screenSize3 = Main.h().getToolkit().getScreenSize();
            final c4 c2 = new c4(Main.h(), Main.p.a("cams.popupmenu.changegender") + " " + this.a.r(), Main.t);
            c2.setSize(190, 80);
            c2.setLocation(screenSize3.width / 2 - c2.getSize().width / 2, screenSize3.height / 2 - c2.getSize().height / 2);
            c2.a.addActionListener(new c6(this.a, c2, "cg"));
            c2.setVisible(true);
        }
        if (menuItem.getName().equalsIgnoreCase("message")) {
            final Dimension screenSize4 = Main.h().getToolkit().getScreenSize();
            final ce ce2 = new ce(Main.h(), Main.p.a("cams.popupmenu.message") + " " + this.a.r());
            ce2.setSize(190, 80);
            ce2.setLocation(screenSize4.width / 2 - ce2.getSize().width / 2, screenSize4.height / 2 - ce2.getSize().height / 2);
            ce2.a.addActionListener(new c0(this.a, ce2, "message"));
            ce2.setVisible(true);
        }
        if (menuItem.getName().equalsIgnoreCase("debug")) {
            new be(this.a.q(), this.a.r(), false, 0, this.a.j(), true).show();
        }
    }
    
    static {
        b6.b = new al(Main.h(), Main.p.a("dialog.goldsilverupgrade"), 400, 100);
        b6.c = new al(Main.h(), Main.p.a("dialog.goldupgrade"), 400, 100);
        b6.b.a.addActionListener(new am());
        b6.c.a.addActionListener(new am());
    }
}
