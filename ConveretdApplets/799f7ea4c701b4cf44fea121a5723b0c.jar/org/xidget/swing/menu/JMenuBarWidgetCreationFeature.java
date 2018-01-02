// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.menu;

import javax.swing.JComponent;
import org.xidget.IXidget;
import javax.swing.JMenuBar;
import org.xidget.swing.feature.SwingWidgetCreationFeature;

public class JMenuBarWidgetCreationFeature extends SwingWidgetCreationFeature
{
    private JMenuBar jMenuBar;
    
    public JMenuBarWidgetCreationFeature(final IXidget xidget) {
        super(xidget);
    }
    
    @Override
    protected JComponent createSwingWidget() {
        return this.jMenuBar = new JMenuBar();
    }
    
    @Override
    public Object[] getLastWidgets() {
        return new Object[] { this.jMenuBar };
    }
    
    public JMenuBar getJMenuBar() {
        return this.jMenuBar;
    }
}
