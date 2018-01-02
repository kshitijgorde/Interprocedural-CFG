// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.label;

import javax.swing.JComponent;
import javax.swing.Icon;
import org.xidget.IXidget;
import javax.swing.JLabel;
import org.xidget.ifeature.IIconFeature;
import org.xidget.swing.feature.SwingWidgetCreationFeature;

public class JLabelWidgetCreationFeature extends SwingWidgetCreationFeature implements IIconFeature
{
    private JLabel jLabel;
    
    public JLabelWidgetCreationFeature(final IXidget xidget) {
        super(xidget);
    }
    
    @Override
    public void setIcon(final Object o) {
        this.jLabel.setIcon((Icon)o);
    }
    
    @Override
    protected JComponent createSwingWidget() {
        return this.jLabel = new JLabel();
    }
    
    @Override
    public Object[] getLastWidgets() {
        return new Object[] { this.jLabel };
    }
    
    public JLabel getJLabel() {
        return this.jLabel;
    }
}
