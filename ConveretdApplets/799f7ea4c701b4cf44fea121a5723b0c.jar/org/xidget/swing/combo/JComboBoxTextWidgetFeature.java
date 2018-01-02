// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.combo;

import org.xidget.ifeature.ITextWidgetFeature;
import javax.swing.JComboBox;
import org.xidget.IXidget;
import org.xidget.swing.feature.SwingTextWidgetFeature;

public class JComboBoxTextWidgetFeature extends SwingTextWidgetFeature
{
    public JComboBoxTextWidgetFeature(final IXidget xidget) {
        super(xidget);
    }
    
    @Override
    public void setEditable(final boolean editable) {
        this.xidget.getFeature(JComboBox.class).setEditable(editable);
    }
    
    @Override
    public void setHAlign(final ITextWidgetFeature.HAlign hAlign) {
    }
    
    @Override
    public void setVAlign(final ITextWidgetFeature.VAlign vAlign) {
    }
}
