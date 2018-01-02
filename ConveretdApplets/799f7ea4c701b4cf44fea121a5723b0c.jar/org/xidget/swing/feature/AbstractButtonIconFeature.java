// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.feature;

import java.awt.Insets;
import javax.swing.Icon;
import javax.swing.AbstractButton;
import org.xidget.IXidget;
import org.xidget.ifeature.IIconFeature;

public class AbstractButtonIconFeature implements IIconFeature
{
    private IXidget xidget;
    
    public AbstractButtonIconFeature(final IXidget xidget) {
        this.xidget = xidget;
    }
    
    @Override
    public void setIcon(final Object o) {
        final AbstractButton abstractButton = this.xidget.getFeature(AbstractButton.class);
        if (abstractButton != null) {
            abstractButton.setIcon((Icon)o);
        }
        final String text = abstractButton.getText();
        if (text == null || text.length() == 0) {
            abstractButton.setMargin(new Insets(0, 0, 0, 0));
        }
    }
}
