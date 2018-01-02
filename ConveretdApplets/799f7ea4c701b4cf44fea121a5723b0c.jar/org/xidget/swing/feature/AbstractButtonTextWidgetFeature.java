// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.feature;

import javax.swing.AbstractButton;
import org.xidget.ifeature.ITextWidgetFeature;
import org.xidget.IXidget;

public class AbstractButtonTextWidgetFeature extends SwingTextWidgetFeature
{
    public AbstractButtonTextWidgetFeature(final IXidget xidget) {
        super(xidget);
    }
    
    @Override
    public void setEditable(final boolean b) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void setHAlign(final ITextWidgetFeature.HAlign hAlign) {
        final AbstractButton abstractButton = this.xidget.getFeature(AbstractButton.class);
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
        abstractButton.setHorizontalAlignment(horizontalAlignment);
    }
    
    @Override
    public void setVAlign(final ITextWidgetFeature.VAlign vAlign) {
        final AbstractButton abstractButton = this.xidget.getFeature(AbstractButton.class);
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
        abstractButton.setVerticalAlignment(verticalAlignment);
    }
}
