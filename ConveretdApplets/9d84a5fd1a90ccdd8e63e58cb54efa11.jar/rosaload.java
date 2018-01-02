import java.awt.Dimension;
import java.awt.Graphics;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class rosaload extends Applet
{
    public void paint(final Graphics g) {
        final Dimension appletDim = this.getSize();
        final int nX = (appletDim.width - g.getFontMetrics().stringWidth("loading map")) / 2;
        g.drawString("loading map", nX, appletDim.height / 2);
    }
}
