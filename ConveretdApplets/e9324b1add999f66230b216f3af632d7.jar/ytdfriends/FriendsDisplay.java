// 
// Decompiled by Procyon v0.5.30
// 

package ytdfriends;

import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;
import edu.berkeley.guir.prefuse.util.FontLib;
import edu.berkeley.guir.prefuse.util.ColorLib;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import ytdfriends.ui.Legend;
import java.awt.geom.AffineTransform;
import edu.berkeley.guir.prefuse.Display;

public class FriendsDisplay extends Display
{
    private FriendsPanel fPanel;
    private AffineTransform id;
    private Legend legend;
    
    public FriendsDisplay(final FriendsPanel fPanel) {
        super(fPanel.getItemRegistry());
        this.id = new AffineTransform();
        this.legend = null;
        this.fPanel = fPanel;
    }
    
    public void prePaint(final Graphics2D g) {
        final Object o = g.getRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        final AffineTransform at = g.getTransform();
        g.setTransform(this.id);
        final Color c = ColorLib.getColor(200, 200, 200, 255);
        final Font f = FontLib.getFont("SansSerif", 3, 48);
        final FontMetrics fm = g.getFontMetrics(f);
        int x = 8;
        final int y = fm.getAscent();
        g.setColor(c);
        g.setFont(f);
        if (this.legend != null) {
            x = this.getWidth() - this.legend.getWidth();
            this.legend.paint(g, (Component)this, x, 150);
        }
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, o);
        g.setTransform(at);
    }
    
    public void setLegend(final Legend l) {
        this.legend = l;
    }
}
