import java.awt.event.ActionEvent;
import au.com.rocketdog.project.awc.applet.Main;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class c3 implements ActionListener, Runnable
{
    private bc a;
    private c1 b;
    private String c;
    private p d;
    
    public c3(final bc a, final c1 b, final String c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    public void run() {
        if (this.c.equalsIgnoreCase("ban")) {
            (this.d = p.a()).j("PRIVMSG Anywebcam :[RELAY] ^" + this.a.r() + " popup >" + Main.p.a("cams.action.banned") + " : " + this.b.b());
            this.d.j("PRIVMSG Anywebcam :[RELAY] ^" + this.a.r() + " kick");
            l.a(this.a.q(), this.b.b(), 0, this.b.a());
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        new Thread(this).start();
    }
}
