// 
// Decompiled by Procyon v0.5.30
// 

package magnus;

import java.awt.Event;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Canvas;

class g extends Canvas
{
    private h a;
    
    public g(final h a) {
        this.a = a;
    }
    
    public void a(final h a) {
        this.a = a;
    }
    
    public void update(final Graphics graphics) {
        final Dimension size = this.size();
        final Image image = this.createImage(size.width, size.height);
        final Graphics graphics2 = image.getGraphics();
        graphics2.setColor(this.getBackground());
        graphics2.fillRect(0, 0, size.width, size.height);
        this.paint(graphics2);
        graphics.drawImage(image, 0, 0, this);
    }
    
    public void paint(final Graphics graphics) {
        this.a.if(graphics);
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (n > 30) {
            final double case1 = (n - 30) * (this.a.do() - this.a.if()) / 450.0 + this.a.if();
            double byte1;
            if (Nomo.if) {
                byte1 = Nomo.if(case1, 4.4 + Math.log(Nomo.else + 273.15));
            }
            else {
                byte1 = Nomo.if(case1, 10.2233);
            }
            Nomo.a.setText("" + ((h.int == 0) ? ((float)byte1) : ((float)(byte1 * 7.5005998611450195))));
            Nomo.c.setText("" + (float)(case1 - 273.0));
            h.do = 1;
            h.case = case1;
            h.byte = byte1;
            this.repaint();
        }
        return false;
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        if (n > 30) {
            final double case1 = (n - 30) * (this.a.do() - this.a.if()) / 450.0 + this.a.if();
            double byte1;
            if (Nomo.if) {
                byte1 = Nomo.if(case1, 4.4 + Math.log(Nomo.else + 273.15));
            }
            else {
                byte1 = Nomo.if(case1, 10.2233);
            }
            Nomo.a.setText("" + ((h.int == 0) ? ((float)byte1) : ((float)(byte1 * 7.5006))));
            Nomo.c.setText("" + (float)(case1 - 273.0));
            h.do = 1;
            h.case = case1;
            h.byte = byte1;
            this.repaint();
        }
        return false;
    }
}
