// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.theme;

import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Point;

public class RangeDrawData extends DrawData
{
    public int selection;
    public int minimum;
    public int maximum;
    
    int getSelection(final Point point, final Rectangle rectangle) {
        return 0;
    }
}
