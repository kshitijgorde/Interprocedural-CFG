// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.gui;

import javax.swing.JMenuItem;
import javax.swing.JMenu;
import com.itt.J2KViewer.controller.ViewCentral;
import com.itt.J2KViewer.actions.ToolbarAndMenuActionCreator;
import javax.swing.JMenuBar;

public class MenuBar extends JMenuBar
{
    private static final long serialVersionUID = 1L;
    private ToolbarAndMenuActionCreator actionCreator;
    
    public MenuBar(final ViewCentral viewCentral) {
        this.actionCreator = viewCentral.getActionManager();
        this.initMenuBar();
    }
    
    private void initMenuBar() {
        final JMenu menu = new JMenu("File");
        if (System.getProperty("os.name").toLowerCase().indexOf("win") != -1) {
            menu.add(this.createMenuItem("Copy Viewport"));
        }
        menu.add(this.createMenuItem("Save View As..."));
        menu.add(this.createMenuItem("Close Image"));
        menu.add(this.createMenuItem("Exit"));
        this.add(menu);
        final JMenu menu2 = new JMenu("Mode");
        menu2.add(this.createMenuItem("Zoom In"));
        menu2.add(this.createMenuItem("Zoom Out"));
        menu2.add(this.createMenuItem("Pan"));
        menu2.add(this.createMenuItem("Manual Rotation"));
        menu2.add(this.createMenuItem("Magnifying Glass"));
        menu2.add(this.createMenuItem("Fly"));
        menu2.add(this.createMenuItem("Create Waypoints"));
        this.add(menu2);
        final JMenu menu3 = new JMenu("Tools");
        menu3.add(this.createMenuItem("Bands..."));
        menu3.add(this.createMenuItem("Jump To..."));
        menu3.add(this.createMenuItem("DRA..."));
        menu3.add(this.createMenuItem("Quality Layer..."));
        menu3.add(this.createMenuItem("Cache Directory..."));
        menu3.add(this.createMenuItem("User Preferences..."));
        this.add(menu3);
        final JMenu menu4 = new JMenu("Properties");
        menu4.add(this.createMenuItem("Code Stream..."));
        menu4.add(this.createMenuItem("XML MetaData..."));
        this.add(menu4);
        final JMenu menu5 = new JMenu("Help");
        menu5.add(this.createMenuItem("About..."));
        this.add(menu5);
    }
    
    private JMenuItem createMenuItem(final String s) {
        return (JMenuItem)this.actionCreator.getAbstractButtonForAction(s, 2);
    }
}
