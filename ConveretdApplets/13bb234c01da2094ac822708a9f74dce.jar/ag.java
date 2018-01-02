import java.awt.Component;
import javax.swing.JComponent;

// 
// Decompiled by Procyon v0.5.30
// 

class ag implements Runnable
{
    final /* synthetic */ JComponent a;
    final /* synthetic */ TTApplet b;
    
    ag(final TTApplet b, final JComponent a) {
        this.b = b;
        this.a = a;
    }
    
    public void run() {
        this.b.getContentPane().add(this.a, "Center");
        this.b.getContentPane().validate();
        this.b.getContentPane().repaint();
    }
}
