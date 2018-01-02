// 
// Decompiled by Procyon v0.5.30
// 

package testvm2;

import java.awt.RenderingHints;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Graphics;
import javax.swing.JPanel;

public class BannerTextPanel extends JPanel
{
    private final String headline;
    private String subtitle;
    private final int FONTSIZE;
    
    public BannerTextPanel(final String h, final String s, final int size) {
        this.headline = h;
        this.subtitle = s;
        this.FONTSIZE = size;
    }
    
    protected void setSubtitle(final String s) {
        this.subtitle = s;
    }
    
    public void setSize(final int width, final int height) {
        super.setSize(355, 100);
    }
    
    public void paint(final Graphics g) {
        super.paint(g);
        final Graphics2D g2d = (Graphics2D)g;
        final Font f = new Font("AERIAL", 1, this.FONTSIZE);
        g2d.setColor(Color.decode("#53819F"));
        g2d.setFont(f);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_DEFAULT);
        g2d.drawString(this.headline, 0, 50);
        g2d.setColor(Color.decode("#E56F0E"));
        g2d.drawString(this.subtitle, 0, 80);
    }
}
