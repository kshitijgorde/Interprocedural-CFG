// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.gui;

import java.awt.Component;
import java.awt.Dimension;

public interface NFSashPanelIntf
{
    Dimension allComponentsSize();
    
    int countManagedComponents();
    
    Component getManagedComponent(final int p0);
    
    void addLine(final NFSashLine p0);
    
    void removeLine(final NFSashLine p0);
    
    void layout();
}
