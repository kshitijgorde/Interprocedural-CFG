// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.tabs;

import java.awt.Container;
import org.xidget.ifeature.ILayoutFeature;
import javax.swing.Icon;
import java.awt.Component;
import org.xidget.ifeature.IWidgetCreationFeature;
import org.xmodel.Xlate;
import javax.swing.JTabbedPane;
import org.xidget.IXidget;
import org.xidget.ifeature.IWidgetContainerFeature;

public class JTabbedPaneContainerFeature implements IWidgetContainerFeature
{
    private IXidget xidget;
    private int spacing;
    
    public JTabbedPaneContainerFeature(final IXidget xidget) {
        this.xidget = xidget;
    }
    
    @Override
    public void addWidget(final IXidget xidget) {
        this.addWidget(-1, xidget);
    }
    
    @Override
    public void addWidget(final int n, final IXidget xidget) {
        final JTabbedPane tabbedPane = this.xidget.getFeature(JTabbedPane.class);
        if (tabbedPane != null) {
            final boolean closeButton = Xlate.get(this.xidget.getConfig(), "removable", false) || Xlate.get(xidget.getConfig(), "removable", false);
            final Object[] lastWidgets = xidget.getFeature(IWidgetCreationFeature.class).getLastWidgets();
            if (lastWidgets.length > 0) {
                final CustomTab customTab = new CustomTab(xidget);
                customTab.setCloseButton(closeButton);
                final Component selectedComponent = (Component)lastWidgets[0];
                if (n < 0) {
                    tabbedPane.addTab("", selectedComponent);
                    tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, customTab);
                }
                else {
                    tabbedPane.insertTab("", null, selectedComponent, null, n);
                    tabbedPane.setTabComponentAt(n, customTab);
                }
                tabbedPane.setSelectedComponent(selectedComponent);
            }
        }
    }
    
    @Override
    public void removeWidget(final IXidget xidget) {
        final JTabbedPane tabbedPane = this.xidget.getFeature(JTabbedPane.class);
        if (tabbedPane != null) {
            final Object[] lastWidgets = xidget.getFeature(IWidgetCreationFeature.class).getLastWidgets();
            if (lastWidgets.length > 0) {
                final int tabIndex = this.getTabIndex(tabbedPane, (Component)lastWidgets[0]);
                if (tabIndex >= 0) {
                    tabbedPane.removeTabAt(tabIndex);
                }
            }
        }
    }
    
    @Override
    public void relayout() {
        final ILayoutFeature layoutFeature = this.xidget.getFeature(ILayoutFeature.class);
        if (layoutFeature != null) {
            layoutFeature.invalidate();
        }
        final Container container = this.xidget.getFeature(Container.class);
        if (container != null && container.isShowing()) {
            container.validate();
        }
    }
    
    @Override
    public void setSpacing(final int spacing) {
        this.spacing = spacing;
    }
    
    @Override
    public int getSpacing() {
        return this.spacing;
    }
    
    private int getTabIndex(final JTabbedPane tabbedPane, final Component component) {
        for (int i = 0; i < tabbedPane.getTabCount(); ++i) {
            if (tabbedPane.getComponentAt(i) == component) {
                return i;
            }
        }
        return -1;
    }
}
