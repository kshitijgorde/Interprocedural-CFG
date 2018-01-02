import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class dd implements ActionListener
{
    private cl a;
    private int b;
    private ce c;
    private p d;
    
    public dd(final cl a, final int b, final ce c) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = p.a();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (this.b == 5) {
            this.d.j("FORCEJOIN " + this.c.a() + " " + this.a.g());
        }
        else if (this.b == 3) {
            this.d.j("WALLOPS :" + this.c.a());
        }
        else if (this.b == 4) {
            this.d.j("MODE " + this.a.g() + " +b");
        }
        else if (this.b == 2) {
            this.d.j("PRIVMSG ChanServ :SET " + this.a.g() + " TOPIC " + this.c.a());
        }
    }
}
