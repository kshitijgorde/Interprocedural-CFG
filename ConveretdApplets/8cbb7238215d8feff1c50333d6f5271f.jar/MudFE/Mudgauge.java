// 
// Decompiled by Procyon v0.5.30
// 

package MudFE;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Canvas;

public class Mudgauge extends Canvas
{
    private int percentFill;
    
    public void setFillPercent(int percentage) {
        if (percentage > 100) {
            percentage = 100;
        }
        if (percentage < 0) {
            percentage = 0;
        }
        this.percentFill = percentage;
    }
    
    public Mudgauge() {
        this.percentFill = 0;
    }
    
    public void repaint() {
        this.paint(this.getGraphics());
    }
    
    public void paint(final Graphics g) {
        if (this.isVisible()) {
            final Dimension d = this.size();
            int fillw = d.width;
            int fillh = d.height;
            final int fill = this.percentFill;
            if (d.width > d.height) {
                fillw = fillw * fill / 100;
            }
            else {
                fillh = fillh * this.percentFill / 100;
            }
            if (d.width > d.height) {
                g.clearRect(fillw, 0, d.width, d.height);
            }
            else {
                g.clearRect(0, 0, d.width, d.height - fillh);
            }
            if (fill <= 5) {
                g.setColor(Color.red.brighter());
            }
            else if (fill <= 15) {
                g.setColor(Color.red);
            }
            else if (fill <= 36) {
                g.setColor(Color.yellow.darker());
            }
            else if (fill <= 75) {
                g.setColor(Color.yellow);
            }
            else if (fill <= 99) {
                g.setColor(Color.green.darker());
            }
            else {
                g.setColor(Color.green.brighter());
            }
            if (d.width > d.height) {
                g.fill3DRect(0, 0, fillw, d.height, true);
            }
            else {
                g.fill3DRect(0, d.height - fillh, d.width, fillh, true);
            }
            g.finalize();
        }
    }
    
    public Dimension preferredSize() {
        return new Dimension(40, 20);
    }
}
