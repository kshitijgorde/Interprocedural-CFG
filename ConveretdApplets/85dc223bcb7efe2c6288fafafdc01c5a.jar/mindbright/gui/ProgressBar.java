// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.gui;

import java.awt.image.ImageObserver;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Canvas;

public class ProgressBar extends Canvas
{
    int max;
    int current;
    int width;
    int height;
    Color barColor;
    FontMetrics fm;
    Image img;
    Graphics memG;
    
    public synchronized void setBarColor(final Color c) {
        this.barColor = c;
    }
    
    public synchronized void setValue(final int v) {
        this.setValue(v, false);
    }
    
    public synchronized void setValue(final int v, final boolean repaintNow) {
        this.current = ((v > this.max) ? this.max : v);
        if (repaintNow) {
            this.update(this.getGraphics());
        }
        else {
            this.repaint();
        }
    }
    
    public synchronized void setMax(final int max, final boolean reset) {
        this.max = max;
        if (reset) {
            this.current = 0;
        }
        this.setValue(this.current, true);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(this.width, this.height);
    }
    
    public ProgressBar(final int max, final int width, final int height) {
        this.max = 0;
        this.current = 0;
        this.width = 0;
        this.height = 0;
        this.max = max;
        this.width = width;
        this.height = height;
        this.barColor = Color.black;
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public synchronized void paint(final Graphics g) {
        final int d = (this.max != 0) ? this.max : 1;
        final double perc = this.current * 100.0 / d;
        final String p = (int)perc + "%";
        final int w = this.current * (this.width - 2) / d;
        if (this.fm == null) {
            this.fm = g.getFontMetrics(g.getFont());
        }
        if (this.img == null) {
            this.setBackground(Color.white);
            this.img = this.createImage(this.width, this.height);
            this.memG = this.img.getGraphics();
        }
        this.memG.setPaintMode();
        this.memG.setColor(Color.white);
        this.memG.fillRect(0, 0, this.width, this.height);
        this.memG.setColor(Color.black);
        this.memG.drawRect(0, 0, this.width - 1, this.height - 1);
        this.memG.drawString(p, this.width / 2 - this.fm.stringWidth(p) / 2 + 1, this.height / 2 + this.fm.getMaxAscent() + this.fm.getLeading() - this.fm.getHeight() / 2);
        this.memG.setColor(this.barColor);
        this.memG.setXORMode(Color.white);
        this.memG.fillRect(1, 1, w, this.height - 2);
        g.drawImage(this.img, 0, 0, this);
    }
}
