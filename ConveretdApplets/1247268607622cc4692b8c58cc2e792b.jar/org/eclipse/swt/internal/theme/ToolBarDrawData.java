// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.theme;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.GC;

public class ToolBarDrawData extends DrawData
{
    public ToolBarDrawData() {
        this.state = new int[1];
    }
    
    void draw(final Theme theme, final GC gc, final Rectangle rectangle) {
    }
    
    char[] getClassId() {
        return ToolBarDrawData.TOOLBAR;
    }
    
    int hit(final Theme theme, final Point point, final Rectangle rectangle) {
        if (!rectangle.contains(point)) {
            return -1;
        }
        return 0;
    }
}
