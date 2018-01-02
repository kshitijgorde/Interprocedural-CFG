// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.button;

import javax.swing.ButtonModel;
import javax.swing.AbstractButton;
import org.xidget.IXidget;
import org.xidget.ifeature.model.ISingleValueWidgetFeature;

public class AbstractButtonSingleValueWidgetFeature implements ISingleValueWidgetFeature
{
    private IXidget xidget;
    
    public AbstractButtonSingleValueWidgetFeature(final IXidget xidget) {
        this.xidget = xidget;
    }
    
    @Override
    public Object getValue() {
        return this.xidget.getFeature(AbstractButton.class).getModel().isSelected();
    }
    
    @Override
    public void setValue(final Object o) {
        final ButtonModel model = this.xidget.getFeature(AbstractButton.class).getModel();
        if (o instanceof Boolean) {
            model.setSelected((boolean)o);
        }
        else if (o instanceof Number) {
            model.setSelected(((Number)o).doubleValue() != 0.0);
        }
        else {
            model.setSelected(((o != null) ? o.toString() : "").equals("true"));
        }
    }
}
