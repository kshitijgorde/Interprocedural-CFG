// 
// Decompiled by Procyon v0.5.30
// 

package ytdfriends.ui;

import edu.berkeley.guir.prefuse.render.Renderer;
import java.awt.FontMetrics;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Font;
import edu.berkeley.guir.prefuse.util.ColorMap;
import javax.swing.JPanel;

public class Legend extends JPanel
{
    private String[] labels;
    private ColorMap cmap;
    private Font font;
    private int height;
    private int width;
    private int size;
    
    public Legend(String[] labels, final ColorMap cmap) {
        this.font = new Font("SansSerif", 0, 11);
        this.height = -1;
        this.width = -1;
        this.size = 20;
        this.cmap = cmap;
        if (labels == null) {
            final double min = cmap.getMinValue();
            final double max = cmap.getMaxValue();
            labels = new String[6];
            for (int len = labels.length, i = 0; i < len; ++i) {
                final int val = (int)Math.round(i / (len - 1.0) * (max - min) + min);
                labels[i] = String.valueOf(val);
            }
        }
        this.labels = labels;
    }
    
    public void paint(final Graphics2D g, final Component c, final int x, int y) {
        final int height = this.labels.length;
        y += 5;
        final double min = this.cmap.getMinValue();
        final double max = this.cmap.getMaxValue();
        final double len = this.labels.length - 1.0;
        final FontMetrics fm = g.getFontMetrics(this.font);
        g.setFont(this.font);
        for (int i = 0; i < height; ++i) {
            final double v = (len - i) / len * (max - min) + min;
            g.setPaint(this.cmap.getColor(v));
            g.fillRect(x, y, this.size, this.size);
            g.drawString(this.labels[height - 1 - i], x + this.size + 5, y + fm.getAscent());
            y += this.size;
        }
    }
    
    public int getHeight() {
        if (this.height == -1) {
            this.height = this.size * this.labels.length + 10;
        }
        return this.height;
    }
    
    public int getWidth() {
        if (this.width == -1) {
            int mw = 0;
            final FontMetrics fm = Renderer.DEFAULT_GRAPHICS.getFontMetrics(this.font);
            for (int i = 0; i < this.labels.length; ++i) {
                final int w = fm.stringWidth(this.labels[i]);
                if (w > mw) {
                    mw = w;
                }
            }
            this.width = this.size + 15 + mw;
        }
        return this.width;
    }
}
