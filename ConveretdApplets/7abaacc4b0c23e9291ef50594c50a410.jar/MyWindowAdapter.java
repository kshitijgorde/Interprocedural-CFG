import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

class MyWindowAdapter extends WindowAdapter
{
    Geo geo;
    
    public MyWindowAdapter(final Geo geo) {
        this.geo = geo;
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.geo.setVisible(false);
        System.exit(0);
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
}
