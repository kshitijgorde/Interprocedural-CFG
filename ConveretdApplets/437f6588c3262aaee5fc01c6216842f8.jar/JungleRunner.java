import java.awt.Component;
import java.awt.LayoutManager;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class JungleRunner extends Applet
{
    JungleCanvas canvas1;
    
    public void init() {
        this.setLayout(null);
        this.setSize(600, 400);
        (this.canvas1 = new JungleCanvas(this)).setBounds(0, 0, 600, 400);
        this.add(this.canvas1);
        new Thread(this.canvas1).start();
    }
    
    public void stop() {
        this.canvas1.dieoff = true;
    }
}
