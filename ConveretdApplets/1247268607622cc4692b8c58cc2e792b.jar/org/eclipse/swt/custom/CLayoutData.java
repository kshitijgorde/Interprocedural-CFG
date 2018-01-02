// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.custom;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Control;

class CLayoutData
{
    int defaultWidth;
    int defaultHeight;
    int currentWhint;
    int currentHhint;
    int currentWidth;
    int currentHeight;
    
    CLayoutData() {
        this.defaultWidth = -1;
        this.defaultHeight = -1;
        this.currentWidth = -1;
        this.currentHeight = -1;
    }
    
    Point computeSize(final Control control, final int currentWhint, final int currentHhint, final boolean b) {
        if (b) {
            this.flushCache();
        }
        if (currentWhint == -1 && currentHhint == -1) {
            if (this.defaultWidth == -1 || this.defaultHeight == -1) {
                final Point computeSize = control.computeSize(currentWhint, currentHhint, b);
                this.defaultWidth = computeSize.x;
                this.defaultHeight = computeSize.y;
            }
            return new Point(this.defaultWidth, this.defaultHeight);
        }
        if (this.currentWidth == -1 || this.currentHeight == -1 || currentWhint != this.currentWhint || currentHhint != this.currentHhint) {
            final Point computeSize2 = control.computeSize(currentWhint, currentHhint, b);
            this.currentWhint = currentWhint;
            this.currentHhint = currentHhint;
            this.currentWidth = computeSize2.x;
            this.currentHeight = computeSize2.y;
        }
        return new Point(this.currentWidth, this.currentHeight);
    }
    
    void flushCache() {
        final int n = -1;
        this.defaultHeight = n;
        this.defaultWidth = n;
        final int n2 = -1;
        this.currentHeight = n2;
        this.currentWidth = n2;
    }
}
