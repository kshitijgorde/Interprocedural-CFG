// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.label;

import javax.swing.JLabel;
import org.xidget.IXidget;
import org.xidget.ifeature.model.ISingleValueWidgetFeature;

public class JLabelSingleValueWidgetFeature implements ISingleValueWidgetFeature
{
    private IXidget xidget;
    
    public JLabelSingleValueWidgetFeature(final IXidget xidget) {
        this.xidget = xidget;
    }
    
    @Override
    public void setValue(final Object o) {
        final String text = (o != null) ? o.toString() : "";
        final JLabel label = this.xidget.getFeature(JLabel.class);
        if (!label.getText().equals(text)) {
            label.setText(text);
        }
    }
    
    @Override
    public Object getValue() {
        return this.xidget.getFeature(JLabel.class).getText();
    }
}
