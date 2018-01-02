// 
// Decompiled by Procyon v0.5.30
// 

package ji.awt;

import java.awt.Component;
import java.awt.event.ItemListener;
import java.awt.Rectangle;
import java.awt.Dimension;

public interface fm
{
    void repaint();
    
    Dimension getPreferredSize();
    
    Rectangle getBounds();
    
    void setBounds(final int p0, final int p1, final int p2, final int p3);
    
    void makeVisible(final int p0);
    
    int getRows();
    
    void clear();
    
    void setVisible(final boolean p0);
    
    void addItemListener(final ItemListener p0);
    
    boolean isVisible();
    
    void add(final String p0);
    
    int getSelectedIndex();
    
    void select(final int p0);
    
    void select(final int p0, final boolean p1);
    
    void removeItemListener(final ItemListener p0);
    
    void releaseResources();
    
    int getTotalRowHeight();
    
    boolean isSwing();
    
    Component getComponent();
    
    void beginBulkAdd();
    
    void finishBulkAdd();
    
    boolean isAdjustingValue();
    
    Rectangle getItemBounds(final int p0);
    
    ItemListener[] getItemListeners();
}
