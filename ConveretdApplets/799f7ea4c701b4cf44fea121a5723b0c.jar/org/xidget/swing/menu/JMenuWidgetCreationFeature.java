// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.menu;

import javax.swing.Icon;
import javax.swing.JComponent;
import org.xidget.IXidget;
import javax.swing.JMenu;
import org.xidget.ifeature.ILabelFeature;
import org.xidget.ifeature.IIconFeature;
import org.xidget.swing.feature.SwingWidgetCreationFeature;

public class JMenuWidgetCreationFeature extends SwingWidgetCreationFeature implements IIconFeature, ILabelFeature
{
    private JMenu jMenu;
    
    public JMenuWidgetCreationFeature(final IXidget xidget) {
        super(xidget);
    }
    
    @Override
    protected JComponent createSwingWidget() {
        return this.jMenu = new JMenu();
    }
    
    @Override
    public Object[] getLastWidgets() {
        return new Object[] { this.jMenu };
    }
    
    public JMenu getJMenu() {
        return this.jMenu;
    }
    
    @Override
    public void setIcon(final Object o) {
        this.jMenu.setIcon((Icon)o);
    }
    
    @Override
    public int getLabelWidth() {
        return 0;
    }
    
    @Override
    public void setLabelWidth(final int n) {
    }
    
    @Override
    public void setLabelText(final String text) {
        this.jMenu.setText(text);
    }
}
