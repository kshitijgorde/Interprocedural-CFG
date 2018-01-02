import au.com.rocketdog.project.awc.applet.Main;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

public class ao extends MouseAdapter
{
    private int a;
    private h b;
    
    public ao(final h b, final int a) {
        this.a = 0;
        this.a = a;
        this.b = b;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        switch (this.a) {
            case 0: {
                this.b.h();
            }
            case 1: {
                this.b.i();
            }
            case 2: {
                this.b.j();
            }
            case 3: {
                Main.g();
            }
            default: {}
        }
    }
}
