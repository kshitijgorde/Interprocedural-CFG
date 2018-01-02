// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient.gui;

import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Color;
import jagoclient.Global;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;

public class BigLabel extends Panel
{
    Image I;
    Graphics GI;
    FontMetrics FM;
    int Offset;
    int W;
    int H;
    Font F;
    
    public BigLabel(final Font font) {
        this.F = font;
        if (font != null) {
            this.setFont(font);
        }
        this.FM = this.getFontMetrics(font);
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.getSize();
        final int width = size.width;
        final int height = size.height;
        if (this.I == null || width != this.W || height != this.H) {
            this.W = width;
            this.H = height;
            this.I = this.createImage(this.W, this.H);
            if (this.I == null) {
                return;
            }
            this.GI = this.I.getGraphics();
            if (this.F != null) {
                this.GI.setFont(this.F);
            }
            this.FM = this.GI.getFontMetrics();
            this.Offset = this.FM.charWidth('m') / 2;
        }
        this.GI.setColor(Global.gray);
        this.GI.fillRect(0, 0, this.W, this.H);
        this.GI.setColor(Color.black);
        this.drawString(this.GI, this.Offset, (this.H + this.FM.getAscent() - this.FM.getDescent()) / 2, this.FM);
        graphics.drawImage(this.I, 0, 0, this.W, this.H, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void drawString(final Graphics graphics, final int n, final int n2, final FontMetrics fontMetrics) {
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(this.getSize().width, (this.FM.getAscent() + this.FM.getDescent()) * 3 / 2);
    }
    
    public Dimension getMinimumSize() {
        return this.getPreferredSize();
    }
}
