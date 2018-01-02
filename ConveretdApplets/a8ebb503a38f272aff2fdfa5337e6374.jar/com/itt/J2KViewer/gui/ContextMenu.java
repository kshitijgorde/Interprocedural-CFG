// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.gui;

import java.awt.Color;
import javax.swing.JComponent;
import java.awt.Component;
import com.itt.J2KViewer.actions.ToolbarAndMenuActionCreator;
import javax.swing.JMenuItem;
import com.itt.J2KViewer.util.ViewerConst;
import javax.swing.JPopupMenu;

public class ContextMenu extends JPopupMenu implements ViewerConst
{
    private static final long serialVersionUID = 1L;
    private JMenuItem zoomInItem;
    private JMenuItem zoomOutItem;
    private ToolbarAndMenuActionCreator actionMgr;
    
    public ContextMenu(final ToolbarAndMenuActionCreator actionMgr) {
        this.zoomInItem = null;
        this.zoomOutItem = null;
        this.actionMgr = null;
        this.actionMgr = actionMgr;
        this.createDefaultItems();
        this.setLightWeightPopupEnabled(false);
    }
    
    private void createDefaultItems() {
        this.add(this.actionMgr.getAbstractButtonForAction("Set View 1:1", 2));
        this.add(this.zoomInItem = (JMenuItem)this.actionMgr.getAbstractButtonForAction("Zoom In", 2));
        this.add(this.zoomOutItem = (JMenuItem)this.actionMgr.getAbstractButtonForAction("Zoom Out", 2));
        this.add(this.actionMgr.getAbstractButtonForAction("Pan", 2));
        this.add(this.actionMgr.getAbstractButtonForAction("Manual Rotation", 2));
        this.add(this.actionMgr.getAbstractButtonForAction("Magnifying Glass", 2));
        this.add(this.actionMgr.getAbstractButtonForAction("Magnifying Glass Properties...", 2));
        this.addSeparator();
        this.add(this.actionMgr.getAbstractButtonForAction("Create Waypoints", 2));
        this.add(this.actionMgr.getAbstractButtonForAction("Add Waypoint", 2));
        this.add(this.actionMgr.getAbstractButtonForAction("Remove Waypoint", 2));
        this.add(this.actionMgr.getAbstractButtonForAction("Remove All Waypoints", 2));
        this.add(this.actionMgr.getAbstractButtonForAction("Waypoint Following", 2));
        this.addSeparator();
        this.add(this.actionMgr.getAbstractButtonForAction("Quality Layer...", 2));
    }
    
    public void setTransparency() {
        final Component[] components = this.getComponents();
        for (int i = 0; i < components.length; ++i) {
            final JComponent component = (JComponent)components[i];
            component.setOpaque(true);
            component.setForeground(new Color(255, 255, 255));
            component.setBackground(new Color(58, 58, 64, 150));
        }
    }
}
