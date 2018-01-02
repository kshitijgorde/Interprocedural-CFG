// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;

final class bs extends Panel
{
    private Image a;
    private Graphics a;
    boolean a;
    
    public bs() {
        this.setSize(new Dimension(25, 25));
    }
    
    public final void paint(final Graphics graphics) {
        if (this.isShowing()) {
            if (this.a == null) {
                this.a = this.createImage(this.size().width, this.size().height);
                this.a = this.a.getGraphics();
            }
            final Dimension size;
            int n = (size = this.size()).width - 1;
            int n2 = size.height - 1;
            final Color background;
            (background = this.getBackground()).brighter();
            background.darker().darker();
            this.a.setColor(this.getParent().getBackground());
            this.a.drawRect(0, 0, n, n2);
            this.a.drawRect(1, 1, n - 2, n2 - 2);
            this.a.setColor(this.getBackground());
            this.a.fillRect(2, 2, n - 4, n2 - 4);
            --n;
            --n2;
            this.a.setColor(Color.gray);
            this.a.drawLine(3, 0, n - 2, 0);
            this.a.drawLine(n - 1, 1, n, 2);
            this.a.drawLine(n, 3, n, n2 - 2);
            this.a.drawLine(n - 1, n2 - 1, n - 2, n2);
            this.a.drawLine(n - 3, n2, 2, n2);
            this.a.drawLine(1, n2 - 1, 0, n2 - 2);
            this.a.drawLine(0, n2 - 3, 0, 2);
            this.a.drawLine(1, 1, 2, 0);
            this.a.setColor(background);
            this.a.drawLine(1, n2 - 2, 1, 2);
            this.a.drawLine(2, 1, n - 2, 1);
            this.a.drawLine(2, n2 - 3, 2, 2);
            this.a.drawLine(3, 2, n - 2, 2);
            this.a.drawLine(2, n2 - 1, n - 2, n2 - 1);
            this.a.drawLine(n - 1, n2 - 2, n - 1, 2);
            this.a.drawLine(2, n2 - 2, n - 2, n2 - 2);
            this.a.drawLine(n - 2, n2 - 3, n - 2, 3);
            if (this.a && background.equals(Color.white)) {
                this.a.setColor(Color.red);
                this.a.drawLine(1, this.size().height, this.size().width, 1);
                this.a.drawLine(1, this.size().height - 1, this.size().width, 0);
            }
            graphics.drawImage(this.a, 0, 0, this);
        }
    }
    
    public final Dimension minimumSize() {
        return new Dimension(25, 25);
    }
    
    public final Dimension preferredSize() {
        return this.minimumSize();
    }
    
    public final void update(final Graphics graphics) {
    }
}
