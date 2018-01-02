import java.awt.Checkbox;
import java.awt.Event;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Tower extends Applet
{
    TowerPanel panel;
    TowerControl control;
    
    public void init() {
        this.resize(500, 450);
        this.setLayout(new BorderLayout());
        this.add("Center", this.panel = new TowerPanel());
        this.add("South", this.control = new TowerControl(this.panel));
    }
    
    public void start() {
        this.panel.start();
    }
    
    public void stop() {
        this.panel.stop();
    }
    
    public boolean action(final Event event, final Object o) {
        if (o instanceof Boolean) {
            if (((Checkbox)event.target).getLabel().equals("Reset")) {
                this.panel.reset = (boolean)o;
            }
            else {
                this.panel.reset = (boolean)o;
            }
            return true;
        }
        return false;
    }
}
