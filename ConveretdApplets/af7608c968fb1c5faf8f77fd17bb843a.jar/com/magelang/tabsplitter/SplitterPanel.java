// 
// Decompiled by Procyon v0.5.30
// 

package com.magelang.tabsplitter;

import java.awt.Container;
import com.magelang.splitter.ReOrientingSplitterBar;
import java.awt.LayoutManager;
import com.magelang.splitter.SplitterBar;
import java.awt.Component;
import com.magelang.splitter.SplitterLayout;
import java.awt.Panel;

class SplitterPanel extends Panel implements TabNamedComponent
{
    private String tabName;
    
    int getOrientation() {
        return ((SplitterLayout)this.getLayout()).getOrientation();
    }
    
    void separate(final Component component) {
        final Component[] components = this.getComponents();
        for (int componentCount = this.getComponentCount(), i = 0; i < componentCount; ++i) {
            if (components[i] instanceof TabLabelPanel && ((TabLabelPanel)components[i]).getComponent() == component) {
                this.separateTabs((TabLabelPanel)components[i]);
                break;
            }
        }
    }
    
    void separateTabs(TabLabelPanel tabLabelPanel) {
        final int componentCount = this.getComponentCount();
        Component[] components;
        int n;
        for (components = this.getComponents(), n = 0; components[n] != tabLabelPanel; ++n) {}
        this.remove(tabLabelPanel);
        if (n > 0) {
            this.remove(n - 1);
        }
        else {
            this.remove(0);
        }
        final Component component = tabLabelPanel.getComponent();
        final String tabName = tabLabelPanel.getTabName();
        final String explicitText = tabLabelPanel.getExplicitText();
        Component component2 = null;
        String tabName2 = null;
        String explicitText2 = null;
        if (componentCount == 3) {
            tabLabelPanel = (TabLabelPanel)this.getComponent(0);
            component2 = tabLabelPanel.getComponent();
            tabName2 = tabLabelPanel.getTabName();
            explicitText2 = tabLabelPanel.getExplicitText();
        }
        ((TabSplitter)this.getParent()).separateTabs(tabName, component, explicitText, tabName2, component2, explicitText2, this);
        this.invalidate();
    }
    
    public void remove(final int n) {
        final Component component = this.getComponent(n);
        super.remove(n);
        if (!(component instanceof SplitterBar)) {
            this.doNames();
        }
    }
    
    SplitterPanel() {
        this.setLayout(new SplitterLayout(1));
    }
    
    protected void doNames() {
        String tabName = null;
        String string = null;
        final Component[] components = this.getComponents();
        for (int componentCount = this.getComponentCount(), i = 0; i < componentCount; ++i) {
            if (components[i] instanceof TabLabelPanel) {
                if (tabName == null) {
                    string = (tabName = ((TabLabelPanel)components[i]).getTabName());
                }
                else {
                    tabName = String.valueOf(tabName) + " / " + ((TabLabelPanel)components[i]).getTabName();
                    string = String.valueOf(string) + "/" + ((TabLabelPanel)components[i]).getTabName();
                }
            }
        }
        this.tabName = tabName;
        this.setName(string);
    }
    
    public void decrPositions(final int n) {
        final int componentCount = this.getComponentCount();
        final Component[] components = this.getComponents();
        for (int i = 0; i < componentCount; ++i) {
            if (components[i] instanceof TabLabelPanel) {
                ((TabLabelPanel)components[i]).decrPosition(n);
            }
        }
    }
    
    void add(final SplitterPanel splitterPanel) {
        final Component[] components = splitterPanel.getComponents();
        for (int componentCount = splitterPanel.getComponentCount(), i = 0; i < componentCount; ++i) {
            if (components[i] instanceof SplitterBar) {
                splitterPanel.remove(components[i]);
            }
            else {
                this.add(((TabLabelPanel)components[i]).getTabName(), components[i], ((TabLabelPanel)components[i]).getPosition(), ((TabLabelPanel)components[i]).getExplicitText());
            }
        }
    }
    
    boolean contains(final Component component) {
        final Component[] components = this.getComponents();
        for (int componentCount = this.getComponentCount(), i = 0; i < componentCount; ++i) {
            if (components[i] instanceof TabLabelPanel && ((TabLabelPanel)components[i]).getComponent() == component) {
                return true;
            }
        }
        return false;
    }
    
    int[] getVisibleComponentNum() {
        final int componentCount = this.getComponentCount();
        final Component[] components = this.getComponents();
        final int[] array = new int[(componentCount + 1) / 2];
        int i = 0;
        int n = 0;
        while (i < componentCount) {
            if (components[i] instanceof TabLabelPanel) {
                array[n++] = ((TabLabelPanel)components[i]).getPosition();
            }
            ++i;
        }
        return array;
    }
    
    Component add(final String s, Component component, final int n, final String s2) {
        component.setVisible(true);
        if (!(component instanceof TabLabelPanel)) {
            component = new TabLabelPanel(s, component, n, s2);
        }
        final int componentCount = this.getComponentCount();
        if (componentCount == 0) {
            super.add("1", component);
        }
        else {
            Component[] components;
            int n2;
            for (components = this.getComponents(), n2 = 0; n2 < componentCount && (components[n2] instanceof SplitterBar || ((TabLabelPanel)components[n2]).getPosition() < n); ++n2) {}
            if (n2 == componentCount) {
                super.add(new ReOrientingSplitterBar());
                super.add("1", component);
            }
            else {
                super.add(new ReOrientingSplitterBar(), n2);
                super.add(component, "1", n2);
            }
        }
        this.doNames();
        return component;
    }
    
    public String getTabName() {
        return this.tabName;
    }
    
    Object getVisibleComponent() {
        final int componentCount = this.getComponentCount();
        final Component[] components = this.getComponents();
        final Object[] array = new Object[(componentCount + 1) / 2];
        int i = 0;
        int n = 0;
        while (i < componentCount) {
            if (components[i] instanceof TabLabelPanel) {
                array[n++] = ((TabLabelPanel)components[i]).getComponent();
            }
            ++i;
        }
        return array;
    }
    
    boolean contains(final String s) {
        final Component[] components = this.getComponents();
        for (int componentCount = this.getComponentCount(), i = 0; i < componentCount; ++i) {
            if (components[i] instanceof TabLabelPanel && ((TabLabelPanel)components[i]).getTabName().equals(s)) {
                return true;
            }
        }
        return false;
    }
    
    void swapOrientation() {
        ((SplitterLayout)this.getLayout()).swapOrientation(this);
    }
}
