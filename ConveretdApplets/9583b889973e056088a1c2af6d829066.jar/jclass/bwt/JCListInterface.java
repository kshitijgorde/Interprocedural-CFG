// 
// Decompiled by Procyon v0.5.30
// 

package jclass.bwt;

import java.awt.Color;
import jclass.util.JCVector;

public interface JCListInterface extends JCItemSelectable
{
    int countItems();
    
    JCVector getItems();
    
    void setItems(final JCVector p0);
    
    Object getItem(final int p0);
    
    void addItem(final Object p0);
    
    void addItem(final Object p0, final int p1);
    
    void replaceItem(final Object p0, final int p1);
    
    void clear();
    
    void deleteItem(final int p0);
    
    void deleteItems(final int p0, final int p1);
    
    int getSelectedIndex();
    
    int[] getSelectedIndexes();
    
    Object getSelectedItem();
    
    Object[] getSelectedObjects();
    
    void select(final int p0);
    
    boolean getAutoSelect();
    
    void setAutoSelect(final boolean p0);
    
    void deselect(final int p0);
    
    boolean isSelected(final int p0);
    
    Color getSelectedBackground();
    
    void setSelectedBackground(final Color p0);
    
    Color getSelectedForeground();
    
    void setSelectedForeground(final Color p0);
    
    int getRows();
    
    void setVisibleRows(final int p0);
    
    boolean allowsMultipleSelections();
    
    void setAllowMultipleSelections(final boolean p0);
    
    int getVisibleIndex();
    
    void makeVisible(final int p0);
    
    int getTopRow();
    
    void setTopRow(final int p0);
    
    void setRowHeight(final int p0);
    
    int getSpacing();
    
    void setSpacing(final int p0);
}
