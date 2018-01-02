// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.actionbar;

import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Component;

public class ScrollButton extends Component
{
    int size;
    boolean isRight;
    Color color;
    Dimension dim;
    int[] x;
    int[] y;
    MenuPanel parent;
    
    public ScrollButton(final int size, final boolean isRight, final MenuPanel parent) {
        this.size = 0;
        this.isRight = false;
        this.color = null;
        this.dim = null;
        this.x = new int[] { 0, 0, 0 };
        this.y = new int[] { 0, 0, 0 };
        this.parent = null;
        this.size = size;
        this.isRight = isRight;
        this.dim = new Dimension(size, size);
        this.parent = parent;
        this.enableEvents(16L);
    }
    
    public Dimension minimumsize() {
        return this.dim;
    }
    
    public Dimension preferredSize() {
        return this.dim;
    }
    
    public void paint(final Graphics graphics) {
        final Color foreground = this.getForeground();
        final Color color = graphics.getColor();
        graphics.setColor(foreground);
        if (this.isRight) {
            this.x[0] = 0;
            this.y[0] = 0;
            this.x[1] = 0;
            this.y[1] = this.size;
            this.x[2] = this.size;
            this.y[2] = this.size / 2;
        }
        else {
            this.x[0] = 0;
            this.y[0] = this.size / 2;
            this.x[1] = this.size;
            this.y[1] = 0;
            this.x[2] = this.size;
            this.y[2] = this.size;
        }
        graphics.fillPolygon(this.x, this.y, 3);
        if (ButtonPanel.nearBlack(foreground) || ButtonPanel.nearWhite(foreground)) {
            graphics.setColor(Color.lightGray);
        }
        else {
            graphics.setColor(foreground.brighter());
        }
        graphics.drawLine(this.x[0], this.y[0], this.x[1], this.y[1]);
        graphics.setColor(color);
    }
    
    public void processMouseEvent(final MouseEvent mouseEvent) {
        if (mouseEvent.getID() == 501) {
            this.parent.handleMouseEvent(mouseEvent);
        }
    }
}
