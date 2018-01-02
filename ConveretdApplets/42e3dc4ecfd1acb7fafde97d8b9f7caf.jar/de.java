import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import au.com.rocketdog.project.awc.applet.Main;
import java.awt.Component;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class de implements ActionListener, Runnable
{
    private cz a;
    private String b;
    private p c;
    private Component d;
    
    public de(final cz a, final String b, final Component d) {
        this.a = a;
        this.d = d;
        this.b = b;
        try {
            this.c = p.a();
        }
        catch (Exception ex) {
            b.a(ex, 3);
        }
    }
    
    public void run() {
        Main.a(this.a.q(), this.a.j());
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final MenuItem menuItem = (MenuItem)actionEvent.getSource();
        if (menuItem.getName().equalsIgnoreCase("pm") && !this.a.d()) {
            (this.c = p.a()).a(this.a);
        }
        if (menuItem.getName().equalsIgnoreCase("pro")) {
            new Thread(this).start();
        }
        if (menuItem.getName().equalsIgnoreCase("ignore")) {
            Main.f(this.a.r());
            this.a.a(-1);
        }
        if (menuItem.getName().equalsIgnoreCase("unignore")) {
            Main.h(this.a.r());
            this.a.a(0);
        }
        if (menuItem.getName().equalsIgnoreCase("kick")) {
            final c4 c4 = new c4(Main.h(), Main.p.a("chat.popupmenu.kickreason"), p.l);
            c4.a.addActionListener(new df(this, c4));
            c4.setVisible(true);
        }
        if (menuItem.getName().equalsIgnoreCase("ban")) {
            this.c.b(this.b, this.a.r());
            final c4 c5 = new c4(Main.h(), Main.p.a("chat.popupmenu.banreason"), p.l);
            c5.a.addActionListener(new df(this, c5));
            c5.setVisible(true);
        }
        if (menuItem.getName().equalsIgnoreCase("mute")) {
            if (this.a.f() >= 1) {
                this.c.i(this.b, this.a.r());
            }
            this.c.j("MODE " + this.a.z() + " +v " + this.a.r());
        }
        if (menuItem.getName().equalsIgnoreCase("unmute")) {
            this.c.j("MODE " + this.a.z() + " -v " + this.a.r());
        }
        if (menuItem.getName().equalsIgnoreCase("bud")) {
            l.c(this.a.q());
        }
        if (menuItem.getName().equalsIgnoreCase("opp")) {
            if (this.a.f() < 1) {
                this.c.d(this.b, this.a.r());
            }
            else {
                this.c.e(this.b, this.a.r());
            }
        }
        if (menuItem.getName().equalsIgnoreCase("tempopp")) {
            if (this.a.f() < 1) {
                this.c.h(this.b, this.a.r());
            }
            else {
                this.c.i(this.b, this.a.r());
            }
        }
        if (menuItem.getName().equalsIgnoreCase("view")) {
            this.a.a();
        }
        if (menuItem.getName().equalsIgnoreCase("hi")) {
            final dg dg = new dg(Main.h(), this.a);
            dg.setLocation(this.d.getLocationOnScreen().x + this.a.g().width, this.d.getLocationOnScreen().y + this.a.l());
            dg.show();
        }
        if (menuItem.getName().equalsIgnoreCase("unhi")) {
            p.a((av)this.a);
        }
    }
    
    public void a(final String s) {
        this.c.e(this.b, this.a.r(), s);
    }
}
