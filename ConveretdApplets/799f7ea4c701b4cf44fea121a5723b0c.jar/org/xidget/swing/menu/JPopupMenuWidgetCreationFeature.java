// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.menu;

import java.awt.event.MouseListener;
import java.awt.Component;
import org.xidget.ifeature.IWidgetCreationFeature;
import javax.swing.JComponent;
import org.xidget.IXidget;
import javax.swing.JPopupMenu;
import org.xidget.swing.feature.SwingWidgetCreationFeature;

public class JPopupMenuWidgetCreationFeature extends SwingWidgetCreationFeature
{
    private JPopupMenu jPopupMenu;
    
    public JPopupMenuWidgetCreationFeature(final IXidget xidget) {
        super(xidget);
    }
    
    @Override
    protected JComponent createSwingWidget() {
        this.jPopupMenu = new JPopupMenu();
        Object[] lastWidgets;
        for (int length = (lastWidgets = this.xidget.getParent().getFeature(IWidgetCreationFeature.class).getLastWidgets()).length, i = 0; i < length; ++i) {
            final JComponent component = (JComponent)lastWidgets[i];
            component.add(this.jPopupMenu);
            component.addMouseListener(new PopupMouseListener(this.xidget));
        }
        return this.jPopupMenu;
    }
    
    @Override
    public Object[] getLastWidgets() {
        return new Object[] { this.jPopupMenu };
    }
    
    public JPopupMenu getJPopupMenu() {
        return this.jPopupMenu;
    }
}
