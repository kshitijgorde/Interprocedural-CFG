import java.awt.Component;
import javax.swing.JComponent;

// 
// Decompiled by Procyon v0.5.30
// 

class af implements Runnable
{
    final /* synthetic */ JComponent a;
    final /* synthetic */ TTApplet b;
    
    af(final TTApplet b, final JComponent a) {
        this.b = b;
        this.a = a;
    }
    
    public void run() {
        this.b.getContentPane().remove(this.a);
    }
}
