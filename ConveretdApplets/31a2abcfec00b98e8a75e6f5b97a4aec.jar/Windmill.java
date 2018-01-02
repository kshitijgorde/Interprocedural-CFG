import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Windmill extends Applet
{
    WindmillPanel windmillPanel;
    
    public Windmill() {
        this.windmillPanel = new WindmillPanel();
    }
    
    public void init() {
        this.setLayout(new GridLayout(1, 1));
        this.add(this.windmillPanel);
    }
    
    public void stop() {
    }
    
    public void start() {
    }
}
