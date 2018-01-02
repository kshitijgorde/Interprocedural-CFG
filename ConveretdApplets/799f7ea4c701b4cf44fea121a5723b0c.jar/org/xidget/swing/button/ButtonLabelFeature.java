// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.button;

import javax.swing.AbstractButton;
import org.xidget.IXidget;
import org.xidget.ifeature.ILabelFeature;

public class ButtonLabelFeature implements ILabelFeature
{
    private IXidget xidget;
    
    public ButtonLabelFeature(final IXidget xidget) {
        this.xidget = xidget;
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
        final AbstractButton abstractButton = this.xidget.getFeature(AbstractButton.class);
        if (abstractButton != null) {
            abstractButton.setText(text);
        }
    }
}
