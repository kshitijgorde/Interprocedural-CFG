// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.text;

import javax.swing.text.JTextComponent;
import org.xidget.IXidget;
import org.xidget.ifeature.model.ISingleValueWidgetFeature;

public class JTextComponentSingleValueWidgetFeature implements ISingleValueWidgetFeature
{
    private IXidget xidget;
    
    public JTextComponentSingleValueWidgetFeature(final IXidget xidget) {
        this.xidget = xidget;
    }
    
    @Override
    public void setValue(final Object o) {
        final String text = (o != null) ? o.toString() : "";
        final JTextComponent textComponent = this.xidget.getFeature(JTextComponent.class);
        if (!textComponent.getText().equals(text)) {
            textComponent.setText(text);
        }
    }
    
    @Override
    public Object getValue() {
        return this.xidget.getFeature(JTextComponent.class).getText();
    }
}
