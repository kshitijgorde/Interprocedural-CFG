// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.ifeature.tree;

public interface IColumnWidthFeature
{
    void insertColumn(final int p0);
    
    void removeColumn(final int p0);
    
    void setAbsoluteWidth(final int p0, final int p1, final int p2, final boolean p3);
    
    void setFreeWidth(final int p0, final int p1, final int p2, final int p3);
    
    void setRelativeWidth(final int p0, final int p1, final int p2, final double p3, final int p4, final boolean p5);
    
    void setAutoWidth(final int p0, final int p1, final int p2, final int p3, final boolean p4);
    
    int getWidth(final int p0);
    
    boolean isResizeable(final int p0);
    
    void setTotalWidth(final int p0);
    
    void setColumnTitle(final int p0, final String p1);
    
    void setColumnText(final int p0, final int p1, final String p2);
    
    void insertRow(final int p0);
    
    void removeRow(final int p0);
}
