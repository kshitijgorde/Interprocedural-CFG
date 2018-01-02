// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;

class ar extends Panel
{
    int ao;
    Image p;
    Graphics d;
    
    public ar(final int ao) {
        this.ao = ao;
        this.setSize(new Dimension(ao, ao));
    }
    
    public void paint(final Graphics graphics) {
        if (this.isShowing()) {
            if (this.p == null) {
                this.p = this.createImage(this.size().width, this.size().height);
                this.d = this.p.getGraphics();
            }
            final Dimension size = this.size();
            final int n = size.width - 1;
            final int n2 = size.height - 1;
            final Color background = this.getBackground();
            background.brighter();
            background.darker().darker();
            this.d.setColor(this.getParent().getBackground());
            this.d.drawRect(0, 0, n, n2);
            this.d.drawRect(1, 1, n - 2, n2 - 2);
            this.d.setColor(this.getBackground());
            this.d.fillRect(2, 2, n - 4, n2 - 4);
            final int n3 = n - 1;
            final int n4 = n2 - 1;
            this.d.setColor(Color.gray);
            this.d.drawLine(3, 0, n3 - 2, 0);
            this.d.drawLine(n3 - 1, 1, n3, 2);
            this.d.drawLine(n3, 3, n3, n4 - 2);
            this.d.drawLine(n3 - 1, n4 - 1, n3 - 2, n4);
            this.d.drawLine(n3 - 3, n4, 2, n4);
            this.d.drawLine(1, n4 - 1, 0, n4 - 2);
            this.d.drawLine(0, n4 - 3, 0, 2);
            this.d.drawLine(1, 1, 2, 0);
            this.d.setColor(background);
            this.d.drawLine(1, n4 - 2, 1, 2);
            this.d.drawLine(2, 1, n3 - 2, 1);
            this.d.drawLine(2, n4 - 3, 2, 2);
            this.d.drawLine(3, 2, n3 - 2, 2);
            this.d.drawLine(2, n4 - 1, n3 - 2, n4 - 1);
            this.d.drawLine(n3 - 1, n4 - 2, n3 - 1, 2);
            this.d.drawLine(2, n4 - 2, n3 - 2, n4 - 2);
            this.d.drawLine(n3 - 2, n4 - 3, n3 - 2, 3);
            graphics.drawImage(this.p, 0, 0, this);
        }
    }
    
    public Dimension minimumSize() {
        return new Dimension(25, 25);
    }
    
    public Dimension preferredSize() {
        return this.minimumSize();
    }
    
    public void update(final Graphics graphics) {
    }
}
