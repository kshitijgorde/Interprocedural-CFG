import java.awt.event.ActionEvent;
import au.com.rocketdog.project.awc.applet.Main;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class c6 implements ActionListener, Runnable
{
    private bc a;
    private c4 b;
    private String c;
    private p d;
    
    public c6(final bc a, final c4 b, final String c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    public void run() {
        if (this.c.equalsIgnoreCase("cg")) {
            (this.d = p.a()).j("PRIVMSG Anywebcam :[RELAY] ^" + this.a.r() + " gender " + this.b.b());
            l.a(this.a.q(), Main.p.a("cams.action.genderchanged") + " " + ba.a(this.a.w()) + " " + Main.p.a("cams.action.to") + " " + this.b.a(), 1, 0);
        }
        if (this.c.equalsIgnoreCase("admin")) {
            new be(this.a.q(), this.a.r(), false, 0, this.a.j(), true).show();
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        new Thread(this).start();
    }
}
