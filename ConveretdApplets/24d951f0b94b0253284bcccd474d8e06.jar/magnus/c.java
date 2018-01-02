// 
// Decompiled by Procyon v0.5.30
// 

package magnus;

import java.awt.Image;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Color;
import java.awt.Canvas;

class c extends Canvas
{
    Eyring a;
    int if;
    
    public c(final Eyring a) {
        this.a = a;
        this.if = (int)(Eyring.n / 2.0);
        this.setBackground(new Color(0, 0, 0));
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (n < 390 && n > 10) {
            this.if = n - 10;
            if (magnus.a.A == 0) {
                Eyring.n = this.if * 2.0;
            }
            else {
                Eyring.l = this.if * 0.08;
            }
            magnus.a.p = 0;
        }
        this.repaint();
        return false;
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        if (n < 390 && n > 10) {
            this.if = n - 10;
            if (magnus.a.A == 0) {
                Eyring.n = this.if * 2.0;
            }
            else {
                Eyring.l = this.if * 0.08;
            }
            magnus.a.p = 0;
        }
        this.repaint();
        return false;
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
        graphics.setColor(Color.cyan);
        graphics.drawRect(10, 2, 380, 12);
        graphics.setColor(Color.white);
        if (magnus.a.A == 0) {
            this.if = (int)(Eyring.n / 2.0);
        }
        else {
            this.if = (int)(Eyring.l / 0.08);
        }
        if (this.if > 0 && this.if < 380) {
            graphics.fillRect(11, 3, this.if, 11);
        }
        else if (this.if > 390) {
            graphics.fillRect(11, 3, 380, 11);
        }
        if (magnus.a.A == 0) {
            graphics.drawString("Temperature (K)", 399, 10);
            Eyring.k.setText("Min Energy:");
            Eyring.new.setText("Max Energy:");
        }
        else {
            graphics.drawString("Energy kJ/mol", 399, 10);
            Eyring.k.setText("Min Temp:");
            Eyring.new.setText("Max Temp:");
        }
        this.a.j.repaint();
    }
}
