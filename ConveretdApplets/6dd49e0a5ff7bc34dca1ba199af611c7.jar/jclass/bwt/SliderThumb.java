// 
// Decompiled by Procyon v0.5.30
// 

package jclass.bwt;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Component;

class SliderThumb extends ScrollbarThumb
{
    JCSlider slider;
    
    SliderThumb(final JCSlider slider) {
        super(slider);
        super.highlight = 0;
        this.slider = slider;
    }
    
    public void move(int n, int n2) {
        if (this.slider.dir == 0) {
            n += this.slider.trough_offset;
        }
        else {
            n2 += this.slider.trough_offset;
        }
        super.move(n, n2);
    }
    
    protected void drawShadow(final Graphics graphics) {
        final int width = this.size().width;
        final int height = this.size().height;
        graphics.setColor(BWTUtil.brighter(this.getBackground()));
        if (this.slider.dir == 0) {
            graphics.drawLine(0, 0, width, 0);
            graphics.drawLine(0, 0, 0, height - 6);
            graphics.drawLine(0, height - 6, width / 2, height - 1);
            graphics.setColor(BWTUtil.darker(this.getBackground()));
            graphics.drawLine(width - 2, 1, width - 2, height - 6);
            graphics.drawLine(width - 2, height - 6, width / 2, height - 2);
            graphics.setColor(Color.black);
            graphics.drawLine(width - 1, 0, width - 1, height - 6);
            graphics.drawLine(width - 1, height - 6, width / 2 - 1, height);
        }
        else {
            graphics.drawLine(0, 0, 0, height);
            graphics.drawLine(0, 0, width - 6, 0);
            graphics.drawLine(width - 6, 0, width - 1, height / 2);
            graphics.setColor(BWTUtil.darker(this.getBackground()));
            graphics.drawLine(1, height - 2, width - 6, height - 2);
            graphics.drawLine(width - 6, height - 2, width - 2, height / 2);
            graphics.setColor(Color.black);
            graphics.drawLine(0, height - 1, width - 6, height - 1);
            graphics.drawLine(width - 6, height - 1, width, height / 2 - 1);
        }
    }
}
