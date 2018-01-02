// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.widgets;

import org.eclipse.swt.graphics.Point;

public abstract class Layout
{
    protected abstract Point computeSize(final Composite p0, final int p1, final int p2, final boolean p3);
    
    protected boolean flushCache(final Control control) {
        return false;
    }
    
    protected abstract void layout(final Composite p0, final boolean p1);
}
