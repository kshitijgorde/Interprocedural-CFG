// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.accessibility;

import org.eclipse.swt.internal.SWTEventListener;

public interface AccessibleTableCellListener extends SWTEventListener
{
    void getColumnSpan(final AccessibleTableCellEvent p0);
    
    void getColumnHeaders(final AccessibleTableCellEvent p0);
    
    void getColumnIndex(final AccessibleTableCellEvent p0);
    
    void getRowSpan(final AccessibleTableCellEvent p0);
    
    void getRowHeaders(final AccessibleTableCellEvent p0);
    
    void getRowIndex(final AccessibleTableCellEvent p0);
    
    void getTable(final AccessibleTableCellEvent p0);
    
    void isSelected(final AccessibleTableCellEvent p0);
}
