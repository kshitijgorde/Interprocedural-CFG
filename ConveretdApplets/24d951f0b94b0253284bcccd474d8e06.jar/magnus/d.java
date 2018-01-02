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

class d extends Canvas
{
    Boltzmann a;
    int if;
    
    public d(final Boltzmann a) {
        this.a = a;
        this.if = 134;
        this.setBackground(new Color(0, 0, 0));
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (n < 390 && n > 10) {
            this.if = n - 10;
            Boltzmann.char.setText("" + this.if * 2.0f);
            Boltzmann.byte.setText("");
            Boltzmann.do.setText("");
            e.if = 0;
        }
        this.repaint();
        return false;
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        if (n < 390 && n > 10) {
            this.if = n - 10;
            Boltzmann.char.setText("" + this.if * 2.0f);
            e.if = 0;
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
        graphics.setColor(Color.blue);
        graphics.drawRect(10, 2, 380, 12);
        graphics.setColor(Color.white);
        if (this.if > 0 && this.if < 380) {
            graphics.fillRect(11, 3, this.if, 11);
        }
        else if (this.if > 390) {
            graphics.fillRect(11, 3, 380, 11);
        }
        final double n = this.if * 2.0;
        graphics.drawString("Temperature (K)", 399, 13);
        this.a.else.do(n);
        this.a.new.repaint();
    }
}
