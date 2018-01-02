// 
// Decompiled by Procyon v0.5.30
// 

package symantec.itools.awt.shape;

import java.beans.PropertyVetoException;
import java.awt.Rectangle;

public class HorizontalLine extends Rect
{
    public HorizontalLine() {
        super.height = 2;
    }
    
    public void setBevelStyle(final int s) throws PropertyVetoException {
        if (super.style != s) {
            super.setBevelStyle(s);
            final Rectangle r = this.getBounds();
            this.reshape(r.x, r.y, r.width, (r.height == 1) ? 1 : 2);
            this.validate();
        }
    }
    
    public void reshape(final int x, final int y, final int width, final int height) {
        super.width = width;
        if (height == 1 && (super.style == 2 || super.style == 3)) {
            super.height = 1;
        }
        else {
            super.height = 2;
        }
        super.reshape(x, y, width, super.height);
    }
}
