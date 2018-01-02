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

class b extends Canvas
{
    private e a;
    
    public b(final e a) {
        this.a = a;
    }
    
    public void a(final e a) {
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
            final double try1 = (n - 30) * (this.a.do() - this.a.if()) / 300.0;
            final double exp = Math.exp(-1000.0 * try1 / (8.314 * this.a.int()));
            Boltzmann.do.setText("" + (float)try1);
            Boltzmann.byte.setText("" + (float)exp);
            e.if = 1;
            e.try = try1;
            e.new = exp;
            this.repaint();
        }
        return false;
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        if (n > 30) {
            final double try1 = (n - 30) * (this.a.do() - this.a.if()) / 300.0;
            final double exp = Math.exp(-1000.0 * try1 / (8.314 * this.a.int()));
            Boltzmann.do.setText("" + (float)try1);
            Boltzmann.byte.setText("" + (float)exp);
            e.if = 1;
            e.try = try1;
            e.new = exp;
            this.repaint();
        }
        return false;
    }
}
