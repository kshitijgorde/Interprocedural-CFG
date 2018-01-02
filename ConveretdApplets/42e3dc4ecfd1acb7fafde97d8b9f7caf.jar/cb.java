import au.com.rocketdog.project.awc.applet.Main;
import au.com.rocketdog.project.awc.applet.images.ImageRes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class cb implements ActionListener
{
    private n a;
    private int b;
    private be c;
    
    public cb(final be c, final int b) {
        this.a = n.b();
        this.b = 0;
        this.c = c;
        this.b = b;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (this.b == 0) {
            this.c.g();
        }
        if (this.b == 1) {
            this.c.c.a(ImageRes.b);
            this.c.c.a(4, this.c.c.getSize().height - 10, dj.x, this.c.getFont(), Main.p.a("cam.request"));
            this.c.c("+" + this.a.x() + this.a.h() + this.a.j());
        }
    }
}
