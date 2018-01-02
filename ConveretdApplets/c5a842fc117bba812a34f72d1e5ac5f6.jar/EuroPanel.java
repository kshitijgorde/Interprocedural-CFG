import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.net.MalformedURLException;
import java.awt.Color;
import java.applet.Applet;
import java.net.URL;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public final class EuroPanel extends Canvas
{
    Dimension size;
    Image imageFlag;
    URL url;
    
    public EuroPanel(final Applet applet) {
        applet.getCodeBase().getFile();
        this.resize(this.size = new Dimension(40, 28));
        applet.validate();
        this.setBackground(Color.blue);
        try {
            this.url = new URL(String.valueOf(EuroCalc.URLBASE) + "flags/");
        }
        catch (MalformedURLException ex) {}
        this.imageFlag = applet.getImage(this.url, "85000133.gif");
        this.repaint();
    }
    
    public Dimension getPreferredSize() {
        return this.size;
    }
    
    public Dimension getMinimumSize() {
        return this.size;
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.imageFlag, 0, 0, this);
    }
}
