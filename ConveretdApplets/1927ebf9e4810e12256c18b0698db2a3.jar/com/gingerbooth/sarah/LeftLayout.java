// 
// Decompiled by Procyon v0.5.30
// 

package com.gingerbooth.sarah;

import java.awt.Dimension;
import java.awt.Container;
import java.awt.Component;
import java.awt.LayoutManager2;

public class LeftLayout implements LayoutManager2
{
    UndoPanel undoPanel;
    Tableau shapeMenu;
    
    public LeftLayout(final UndoPanel undoPanel, final Tableau shapeMenu) {
        this.undoPanel = undoPanel;
        this.shapeMenu = shapeMenu;
    }
    
    public void addLayoutComponent(final Component component, final Object o) {
    }
    
    public void addLayoutComponent(final String s, final Component component) {
    }
    
    public float getLayoutAlignmentX(final Container container) {
        return 0.5f;
    }
    
    public float getLayoutAlignmentY(final Container container) {
        return 0.5f;
    }
    
    public void invalidateLayout(final Container container) {
    }
    
    public void layoutContainer(final Container container) {
        final Dimension dimension = new Dimension(container.getSize());
        this.shapeMenu.setBounds(0, 0, 66, dimension.height - 110);
        this.undoPanel.setBounds(0, dimension.height - 110, 66, 110);
    }
    
    public Dimension maximumLayoutSize(final Container container) {
        return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }
    
    public Dimension minimumLayoutSize(final Container container) {
        return new Dimension(container.getSize());
    }
    
    public Dimension preferredLayoutSize(final Container container) {
        return this.minimumLayoutSize(container);
    }
    
    public void removeLayoutComponent(final Component component) {
    }
}
