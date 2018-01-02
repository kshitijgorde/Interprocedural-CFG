// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.graphics;

import java.awt.Event;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Frame;
import java.awt.Window;

class ActiveLabelWindow extends Window
{
    NFActiveLabel a;
    NFLabel b;
    Frame c;
    Window d;
    
    ActiveLabelWindow(final Frame c) {
        super(c);
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.c = c;
        this.disable();
    }
    
    public void show(final NFActiveLabel a, final NFLabel b, final Window d) {
        this.a = a;
        this.b = b;
        this.d = d;
        this.show();
    }
    
    public void paint(final Graphics graphics) {
        final Rectangle parentBounds = this.b.getParentBounds();
        this.b.setParentBounds(null);
        if (this.a.label.equals("OUTLINE")) {
            this.a.drawOutline(graphics, this.b);
        }
        else {
            final String label = this.b.getLabel();
            if (label != null && label.equals("OUTLINE")) {
                this.a.drawOutline(graphics, this.b);
            }
            else {
                this.a.draw(graphics, this.b);
            }
        }
        this.b.setParentBounds(parentBounds);
    }
    
    public boolean gotFocus(final Event event, final Object o) {
        this.d.requestFocus();
        this.toFront();
        return false;
    }
}
