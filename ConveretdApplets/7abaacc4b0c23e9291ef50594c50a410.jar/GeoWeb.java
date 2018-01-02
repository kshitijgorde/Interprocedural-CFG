import java.awt.Graphics;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class GeoWeb extends Applet
{
    Geo f;
    
    public void init() {
        this.setBackground(Color.white);
        this.f = new Geo();
    }
    
    public void start() {
        final String[] array = { null };
        try {
            Geo.main(array);
        }
        catch (Exception ex) {}
        this.f.setVisible(true);
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawString("Loading Angle Finder...", 50, 50);
    }
}
