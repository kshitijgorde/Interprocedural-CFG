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
    String \u00e6;
    String \u00e7;
    Font \u00e8;
    int \u00e9;
    int \u00ea;
    Applet \u00eb;
    int \u00ec;
    
    public HyperlinkComponent(final String \u00e6, final String \u00e7, final Applet \u00eb, final int \u00ec) {
        this.\u00e6 = \u00e6;
        this.\u00e7 = \u00e7;
        this.\u00eb = \u00eb;
        this.\u00ec = \u00ec;
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(Color.blue);
        this.\u00e9 = this.size().height;
        this.\u00ea = this.size().width;
        graphics.setFont(this.\u00e8 = new Font("roman", 0, this.\u00ec));
        final int stringWidth = graphics.getFontMetrics(this.\u00e8).stringWidth(this.\u00e6);
        graphics.drawString(this.\u00e6, (this.\u00ea - stringWidth) / 2, this.\u00e9 - 3);
        graphics.drawLine((this.\u00ea - stringWidth) / 2, this.\u00e9 - 1, (this.\u00ea + stringWidth) / 2, this.\u00e9 - 1);
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        this.activateHyperlink();
        return false;
    }
    
    public void activateHyperlink() {
        final String s = "_blank";
        final String \u00e7 = this.\u00e7;
        try {
            this.\u00eb.getAppletContext().showDocument(new URL(\u00e7), s);
        }
        catch (MalformedURLException ex) {}
    }
}
