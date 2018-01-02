import java.awt.Dimension;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import au.com.rocketdog.project.awc.applet.Main;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class cc implements ActionListener, Runnable
{
    private be a;
    private static al b;
    private n c;
    
    public cc(final be a) {
        this.c = n.b();
        this.a = a;
    }
    
    public void run() {
        Main.a(this.a.q(), this.a.j());
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final MenuItem menuItem = (MenuItem)actionEvent.getSource();
        if (menuItem.getName().equalsIgnoreCase("addfav")) {
            if (this.c.h() >= 1) {
                Main.j((this.a.r() + "_" + this.a.j()).toLowerCase());
                h.f().a((this.a.r() + "_" + this.a.j()).toLowerCase(), this.a.w(), this.a.d(), this.a.b());
            }
            else {
                cc.b.setVisible(true);
            }
        }
        if (menuItem.getName().equalsIgnoreCase("remfav")) {
            Main.k((this.a.r() + "_" + this.a.j()).toLowerCase());
            h.f().a((this.a.r() + "_" + this.a.j()).toLowerCase(), this.a.w(), this.a.d(), this.a.b());
        }
        if (menuItem.getName().equalsIgnoreCase("pro")) {
            new Thread(this).start();
        }
        if (menuItem.getName().equalsIgnoreCase("+")) {
            this.a.a(true);
        }
        if (menuItem.getName().equalsIgnoreCase("-")) {
            this.a.a(false);
        }
        if (menuItem.getName().equalsIgnoreCase("bud")) {
            l.c(this.a.q());
        }
        if (menuItem.getName().equalsIgnoreCase("chat")) {
            v.a().a(this.a.getTitle());
        }
        if (menuItem.getName().equalsIgnoreCase("snap")) {
            if (this.a.ag == 1 || this.c.h() >= 3) {
                if (this.c.v()) {
                    this.a.o = true;
                }
                else {
                    cc.b.setVisible(true);
                }
            }
            else {
                new bl(Main.h(), Main.p.a("dialog.notallowcapture")).setVisible(true);
            }
        }
        if (menuItem.getName().equalsIgnoreCase("history")) {
            Main.b("http://" + Main.b + "/awc/servlet/dispatch?CMD=cmd.cam.viewaudit&uid=" + this.a.q(), "frameContent");
        }
        if (menuItem.getName().equalsIgnoreCase("min")) {
            this.a.setSize(this.a.getSize().width, 0);
        }
        if (menuItem.getName().equalsIgnoreCase("cap")) {
            if (this.a.ag == 1 || this.c.h() >= 3) {
                if (this.c.v()) {
                    if (this.a.n) {
                        this.a.n = false;
                    }
                    else {
                        this.a.n = true;
                    }
                }
                else {
                    cc.b.setVisible(true);
                }
            }
            else {
                new bl(Main.h(), Main.p.a("dialog.notallowcapture")).setVisible(true);
            }
        }
        if (menuItem.getName().equalsIgnoreCase("kick")) {
            final Dimension screenSize = Main.h().getToolkit().getScreenSize();
            final ce ce = new ce(Main.h(), Main.p.a("cams.popupmenu.kick") + " " + this.a.getTitle());
            ce.setSize(190, 80);
            ce.setLocation(screenSize.width / 2 - ce.getSize().width / 2, screenSize.height / 2 - ce.getSize().height / 2);
            ce.a.addActionListener(new c0(this.a, ce, "kick"));
            ce.setVisible(true);
        }
        if (menuItem.getName().equalsIgnoreCase("ban")) {
            final Dimension screenSize2 = Main.h().getToolkit().getScreenSize();
            final c1 c1 = new c1(Main.h(), Main.p.a("cams.popupmenu.ban") + " " + this.a.getName(), Main.u);
            c1.setSize(190, 100);
            c1.setLocation(screenSize2.width / 2 - c1.getSize().width / 2, screenSize2.height / 2 - c1.getSize().height / 2);
            c1.a.addActionListener(new c3(this.a, c1, "ban"));
            c1.setVisible(true);
        }
        if (menuItem.getName().equalsIgnoreCase("cg")) {
            final Dimension screenSize3 = Main.h().getToolkit().getScreenSize();
            final c4 c2 = new c4(Main.h(), Main.p.a("cams.popupmenu.changegender") + " " + this.a.getName(), Main.t);
            c2.setSize(190, 80);
            c2.setLocation(screenSize3.width / 2 - c2.getSize().width / 2, screenSize3.height / 2 - c2.getSize().height / 2);
            c2.a.addActionListener(new c6(this.a, c2, "cg"));
            c2.setVisible(true);
        }
        if (menuItem.getName().equalsIgnoreCase("message")) {
            final Dimension screenSize4 = Main.h().getToolkit().getScreenSize();
            final ce ce2 = new ce(Main.h(), Main.p.a("cams.popupmenu.message") + " " + this.a.getName());
            ce2.setSize(190, 80);
            ce2.setLocation(screenSize4.width / 2 - ce2.getSize().width / 2, screenSize4.height / 2 - ce2.getSize().height / 2);
            ce2.a.addActionListener(new c0(this.a, ce2, "message"));
            ce2.setVisible(true);
        }
    }
    
    static {
        cc.b = new al(Main.h(), Main.p.a("dialog.goldupgrade"), 400, 100);
        cc.b.a.addActionListener(new am());
    }
}
