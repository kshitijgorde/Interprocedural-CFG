// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.label;

import javax.swing.JLabel;
import org.xidget.ifeature.ITextWidgetFeature;
import org.xidget.IXidget;
import org.xidget.swing.feature.SwingTextWidgetFeature;

public class JLabelTextWidgetFeature extends SwingTextWidgetFeature
{
    public JLabelTextWidgetFeature(final IXidget xidget) {
        super(xidget);
    }
    
    @Override
    public void setEditable(final boolean b) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void setHAlign(final ITextWidgetFeature.HAlign hAlign) {
        final JLabel label = this.xidget.getFeature(JLabel.class);
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
        label.setHorizontalAlignment(horizontalAlignment);
    }
    
    @Override
    public void setVAlign(final ITextWidgetFeature.VAlign vAlign) {
        final JLabel label = this.xidget.getFeature(JLabel.class);
        int verticalAlignment = 0;
        switch (vAlign) {
            case top: {
                verticalAlignment = 1;
                break;
            }
            case center: {
                verticalAlignment = 0;
                break;
            }
            case bottom: {
                verticalAlignment = 3;
                break;
            }
        }
        label.setVerticalAlignment(verticalAlignment);
    }
}
