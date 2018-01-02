// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.feature;

import org.xidget.layout.Bounds;
import org.xidget.IXidget;

public class SwingContainerWidgetFeature extends SwingWidgetFeature
{
    public SwingContainerWidgetFeature(final IXidget xidget) {
        super(xidget);
    }
    
    @Override
    public Bounds getDefaultBounds() {
        return this.defaultBounds;
    }
}
