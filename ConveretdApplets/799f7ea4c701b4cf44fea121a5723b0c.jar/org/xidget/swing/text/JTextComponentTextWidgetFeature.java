// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.text;

import javax.swing.JTextField;
import org.xidget.ifeature.ITextWidgetFeature;
import javax.swing.text.JTextComponent;
import org.xidget.IXidget;
import org.xidget.swing.feature.SwingTextWidgetFeature;

public class JTextComponentTextWidgetFeature extends SwingTextWidgetFeature
{
    public JTextComponentTextWidgetFeature(final IXidget xidget) {
        super(xidget);
    }
    
    @Override
    public void setEditable(final boolean editable) {
        this.xidget.getFeature(JTextComponent.class).setEditable(editable);
    }
    
    @Override
    public void setHAlign(final ITextWidgetFeature.HAlign hAlign) {
        final JTextComponent textComponent = this.xidget.getFeature(JTextComponent.class);
        if (textComponent instanceof JTextField) {
            int horizontalAlignment = 0;
            switch (hAlign) {
                case left: {
                    horizontalAlignment = 2;
                    break;
                }
                case center: {
                    horizontalAlignment = 0;
                    break;
                }
                case right: {
                    horizontalAlignment = 4;
                    break;
                }
            }
            ((JTextField)textComponent).setHorizontalAlignment(horizontalAlignment);
        }
    }
    
    @Override
    public void setVAlign(final ITextWidgetFeature.VAlign vAlign) {
    }
}
