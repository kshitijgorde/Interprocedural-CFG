// 
// Decompiled by Procyon v0.5.30
// 

package wordle.a;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Component;
import javax.swing.Icon;

final class g implements Icon
{
    private boolean a;
    private int b;
    private int c;
    
    public g(final boolean a, final int b, final int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    public final void paintIcon(final Component component, final Graphics graphics, final int n, int n2) {
        final Color color = (component == null) ? Color.GRAY : component.getBackground();
        final int n3 = (int)(this.b / 2.0 * Math.pow(0.8, this.c));
        final int n4 = this.a ? n3 : (-n3);
        n2 = n2 + 5 * this.b / 6 + (this.a ? (-n4) : false);
        final int n5 = this.a ? 1 : -1;
        graphics.translate(n, n2);
        graphics.setColor(color.darker());
        graphics.drawLine(n3 / 2, n4, 0, 0);
        graphics.drawLine(n3 / 2, n4 + n5, 0, n5);
        graphics.setColor(color.brighter());
        graphics.drawLine(n3 / 2, n4, n3, 0);
        graphics.drawLine(n3 / 2, n4 + n5, n3, n5);
        if (this.a) {
            graphics.setColor(color.darker().darker());
        }
        else {
            graphics.setColor(color.brighter().brighter());
        }
        graphics.drawLine(n3, 0, 0, 0);
        graphics.setColor(color);
        graphics.translate(-n, -n2);
    }
    
    public final int getIconWidth() {
        return this.b;
    }
    
    public final int getIconHeight() {
        return this.b;
    }
}
