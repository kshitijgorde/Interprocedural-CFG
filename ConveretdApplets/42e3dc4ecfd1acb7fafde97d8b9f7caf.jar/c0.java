import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class c0 implements ActionListener, Runnable
{
    private ce a;
    private String b;
    private p c;
    private bc d;
    
    public c0(final bc d, final ce a, final String b) {
        this.d = d;
        this.a = a;
        this.b = b;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        new Thread(this).start();
    }
    
    public void run() {
        this.c = p.a();
        if (this.b.equalsIgnoreCase("message")) {
            this.c.j("PRIVMSG Anywebcam :[RELAY] ^" + this.d.r() + " popup type=\"2\">" + this.a.a());
            l.a(this.d.q(), this.a.a(), 2, 0);
        }
        if (this.b.equalsIgnoreCase("kick")) {
            this.c.j("PRIVMSG Anywebcam :[RELAY] ^" + this.d.r() + " kick " + this.a.a());
            l.a(this.d.q(), this.a.a(), 3, 0);
        }
    }
}
