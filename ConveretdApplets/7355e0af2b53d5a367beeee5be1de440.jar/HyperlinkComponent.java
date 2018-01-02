import java.awt.Color;
import java.awt.Graphics;
import java.awt.Event;
import java.net.MalformedURLException;
import java.net.URL;
import java.applet.Applet;
import java.awt.Font;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class HyperlinkComponent extends Canvas
{
    String \u0136;
    String \u0137;
    Font \u0138;
    int \u0139;
    int \u013a;
    Applet \u013b;
    int \u013c;
    
    public HyperlinkComponent(final String \u0137, final String \u01372, final Applet \u013c, final int \u013c2) {
        this.\u0136 = \u0137;
        this.\u0137 = \u01372;
        this.\u013b = \u013c;
        this.\u013c = \u013c2;
    }
    
    public void activateHyperlink() {
        final String s = "_blank";
        final String \u0137 = this.\u0137;
        try {
            this.\u013b.getAppletContext().showDocument(new URL(\u0137), s);
        }
        catch (MalformedURLException ex) {}
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        return super.mouseMove(event, n, n2);
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        this.activateHyperlink();
        return false;
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(Color.blue);
        this.\u0139 = this.size().height;
        this.\u013a = this.size().width;
        graphics.setFont(this.\u0138 = new Font("roman", 0, this.\u013c));
        final int stringWidth = graphics.getFontMetrics(this.\u0138).stringWidth(this.\u0136);
        graphics.drawString(this.\u0136, (this.\u013a - stringWidth) / 2, this.\u0139 - 3);
        graphics.drawLine((this.\u013a - stringWidth) / 2, this.\u0139 - 1, (this.\u013a + stringWidth) / 2, this.\u0139 - 1);
    }
}
