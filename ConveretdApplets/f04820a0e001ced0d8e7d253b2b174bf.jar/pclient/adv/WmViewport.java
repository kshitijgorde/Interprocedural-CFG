// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adv;

import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JViewport;

public class WmViewport extends JViewport
{
    private WmPainter bgPainter;
    private WmPainter fgPainter;
    
    public WmViewport(final WmPainter backgroundPainter, final WmPainter foregroundPainter) {
        this.setBackgroundPainter(backgroundPainter);
        this.setForegroundPainter(foregroundPainter);
        this.setBackground(Color.WHITE);
    }
    
    public void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        if (this.bgPainter != null) {
            this.bgPainter.paint(graphics);
        }
    }
    
    public void paintChildren(final Graphics graphics) {
        super.paintChildren(graphics);
        if (this.fgPainter != null) {
            this.fgPainter.paint(graphics);
        }
    }
    
    public void setBackgroundPainter(final WmPainter bgPainter) {
        if (this.bgPainter != null) {
            this.bgPainter.setComponent(null);
        }
        this.bgPainter = bgPainter;
        if (this.bgPainter != null) {
            this.bgPainter.setComponent(this);
        }
        this.repaint();
    }
    
    public void setForegroundPainter(final WmPainter fgPainter) {
        if (this.fgPainter != null) {
            this.fgPainter.setComponent(null);
        }
        this.fgPainter = fgPainter;
        if (this.fgPainter != null) {
            this.fgPainter.setComponent(this);
        }
        this.repaint();
    }
    
    public WmPainter getBackgroundPainter() {
        return this.bgPainter;
    }
    
    public WmPainter getForegroundPainter() {
        return this.fgPainter;
    }
}
