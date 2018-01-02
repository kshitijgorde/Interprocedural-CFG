// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.ifeature;

import org.xidget.layout.Margins;
import org.xmodel.IModelObject;
import org.xidget.layout.Bounds;

public interface IWidgetFeature
{
    void setDefaultBounds(final float p0, final float p1, final float p2, final float p3, final boolean p4);
    
    Bounds getDefaultBounds();
    
    void setComputedBounds(final float p0, final float p1, final float p2, final float p3);
    
    Bounds getComputedBounds();
    
    void setBoundsNode(final IModelObject p0);
    
    IModelObject getBoundsNode();
    
    void setInsideMargins(final Margins p0);
    
    Margins getInsideMargins();
    
    void setOutsideMargins(final Margins p0);
    
    Margins getOutsideMargins();
    
    void setVisible(final boolean p0);
    
    boolean getVisible();
    
    void setEnabled(final boolean p0);
    
    void setTooltip(final String p0);
    
    void setForeground(final int p0);
    
    void setBackground(final int p0);
}
