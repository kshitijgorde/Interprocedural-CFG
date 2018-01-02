// 
// Decompiled by Procyon v0.5.30
// 

package jclass.bwt;

import jclass.util.JCVector;

public interface JCMultiColumnInterface
{
    void setColumnLabels(final JCVector p0);
    
    void setColumnButtons(final JCVector p0);
    
    int[] getColumnWidths();
    
    void setColumnWidths(final int[] p0);
    
    int getColumnWidth(final int p0);
    
    int getColumnPosition(final int p0);
    
    void setColumnWidth(final int p0, final int p1);
    
    int getNumColumns();
    
    void setNumColumns(final int p0);
    
    int getColumnLeftMargin(final int p0);
    
    void setColumnLeftMargin(final int p0, final int p1);
    
    int getColumnRightMargin(final int p0);
    
    void setColumnRightMargin(final int p0, final int p1);
    
    int[] getColumnAlignments();
    
    int getColumnAlignment(final int p0);
    
    void setColumnAlignment(final int p0, final int p1);
    
    void setColumnAlignments(final int[] p0);
    
    int calcWidth(final int p0);
    
    JCMultiColumnData getMultiColumnData();
    
    int getColumnDisplayWidth(final int p0);
    
    void setColumnDisplayWidth(final int p0, final int p1);
    
    int[] getColumnDisplayWidths();
    
    void setColumnDisplayWidths(final int[] p0);
    
    int getColumnResizePolicy();
    
    void setColumnResizePolicy(final int p0);
}
