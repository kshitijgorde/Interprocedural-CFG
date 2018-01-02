// 
// Decompiled by Procyon v0.5.30
// 

package com.magelang.tabsplitter;

import java.awt.LayoutManager;
import java.awt.Panel;

public class TabNamePanel extends Panel implements TabNamedComponent
{
    String tabName;
    
    public TabNamePanel() {
    }
    
    public TabNamePanel(final LayoutManager layoutManager) {
        super(layoutManager);
    }
    
    public String getTabName() {
        return this.tabName;
    }
    
    public String toString() {
        return String.valueOf(this.getClass().getName()) + " [\"" + this.tabName + "\"]";
    }
    
    public void setTabName(final String tabName) {
        this.tabName = tabName;
    }
    
    public TabNamePanel(final String tabName) {
        this.setTabName(tabName);
    }
    
    public TabNamePanel(final String tabName, final LayoutManager layoutManager) {
        super(layoutManager);
        this.setTabName(tabName);
    }
}
