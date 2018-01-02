import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Event;
import java.awt.Color;
import java.awt.Graphics;
import java.applet.Applet;
import java.awt.Font;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class HyperlinkComponent extends Canvas
{
    String \u011c;
    String \u00fb;
    Font \u011d;
    int \u00e7;
    int \u00e6;
    Applet \u00fc;
    int \u011e;
    
    public HyperlinkComponent(final String \u011d, final String \u00fb, final Applet \u00fc, final int \u011f) {
        this.\u011c = \u011d;
        this.\u00fb = \u00fb;
        this.\u00fc = \u00fc;
        this.\u011e = \u011f;
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(Color.blue);
        this.\u00e7 = this.size().height;
        this.\u00e6 = this.size().width;
        graphics.setFont(this.\u011d = new Font("roman", 0, this.\u011e));
        final int stringWidth = graphics.getFontMetrics(this.\u011d).stringWidth(this.\u011c);
        graphics.drawString(this.\u011c, (this.\u00e6 - stringWidth) / 2, this.\u00e7 - 3);
        graphics.drawLine((this.\u00e6 - stringWidth) / 2, this.\u00e7 - 1, (this.\u00e6 + stringWidth) / 2, this.\u00e7 - 1);
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        this.activateHyperlink();
        return false;
    }
    
    public void activateHyperlink() {
        final String s = "_blank";
        final String \u00fb = this.\u00fb;
        try {
            this.\u00fc.getAppletContext().showDocument(new URL(\u00fb), s);
        }
        catch (MalformedURLException ex) {}
    }
}
