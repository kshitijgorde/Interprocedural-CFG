// 
// Decompiled by Procyon v0.5.30
// 

package buddysoft.a;

import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;

public class d extends b
{
    private Image int;
    
    public d(final String a) {
        super.a = a;
    }
    
    public d(final Image int1) {
        this.int = int1;
    }
    
    public void paint(final Graphics graphics) {
        if (super.do == null) {
            super.do = this.getParent();
            while (!(super.do instanceof Frame)) {
                super.do = ((Component)super.do).getParent();
            }
        }
        final int n = this.size().width - 1;
        final int n2 = this.size().height - 1;
        graphics.setColor(this.getParent().getBackground());
        graphics.fillRect(0, 0, n + 1, n2 + 1);
        graphics.setColor(this.getBackground());
        graphics.fillRect(1, 1, n - 2, n2 - 2);
        graphics.setColor(super.for ? this.getBackground().darker() : this.getBackground().brighter());
        graphics.drawLine(2, 1, n - 2, 1);
        graphics.drawLine(2, 2, n - 3, 2);
        graphics.drawLine(1, 2, 1, n2 - 2);
        graphics.drawLine(2, 3, 2, n2 - 3);
        graphics.fillRect(3, 3, 1, 1);
        graphics.setColor(super.for ? this.getBackground().brighter() : this.getBackground().darker());
        graphics.drawLine(3, n2 - 2, n - 3, n2 - 2);
        graphics.drawLine(2, n2 - 1, n - 2, n2 - 1);
        graphics.drawLine(n - 2, 3, n - 2, n2 - 1);
        graphics.drawLine(n - 1, 2, n - 1, n2 - 2);
        graphics.fillRect(n - 3, n2 - 3, 1, 1);
        graphics.fillRect(n - 3, 3, 1, 1);
        graphics.fillRect(3, n2 - 3, 1, 1);
        graphics.fillRect(n - 2, n2 - 2, 1, 1);
        graphics.setColor(Color.black);
        graphics.drawRoundRect(0, 0, n, n2, 7, 7);
        if (this.int != null) {
            graphics.drawImage(this.int, 4, 4, n - 7, n2 - 7, this);
            return;
        }
        final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
        graphics.setColor(this.getForeground());
        if (!super.for) {
            graphics.drawString(super.a, n / 2 - fontMetrics.stringWidth(super.a) / 2, n2 / 2 + fontMetrics.getHeight() / 2 - fontMetrics.getMaxDescent());
            return;
        }
        graphics.drawString(super.a, n / 2 - fontMetrics.stringWidth(super.a) / 2 + 1, n2 / 2 + fontMetrics.getHeight() / 2 - fontMetrics.getMaxDescent() + 1);
    }
    
    public Dimension minimumSize() {
        if (super.a != null) {
            final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
            return new Dimension(fontMetrics.stringWidth(super.a) * 2, (int)(fontMetrics.getHeight() * 1.8));
        }
        return new Dimension(50, 25);
    }
    
    public Dimension preferredSize() {
        return this.minimumSize();
    }
}
