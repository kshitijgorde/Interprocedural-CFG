import java.awt.Graphics;
import java.awt.Color;
import java.util.Date;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class deot212 extends Applet
{
    String versStr;
    Date dat;
    public compute comp;
    
    public void init() {
        this.dat = new Date();
        this.setBackground(new Color(2, 86, 2));
        this.comp = new compute(this.dat, this.versStr);
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(Color.black);
        this.comp.update(graphics);
    }
    
    public deot212() {
        this.versStr = "2.12  © J.Giesen www.GeoAstro.de";
    }
}
