// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.xmleditor;

import org.xidget.ifeature.ITextWidgetFeature;
import org.xidget.IXidget;
import org.xidget.swing.feature.SwingTextWidgetFeature;

public class XmlTextPaneTextWidgetFeature extends SwingTextWidgetFeature
{
    public XmlTextPaneTextWidgetFeature(final IXidget xidget) {
        super(xidget);
    }
    
    @Override
    public void setEditable(final boolean editable) {
        this.xidget.getFeature(XmlTextPane.class).setEditable(editable);
    }
    
    @Override
    public void setHAlign(final ITextWidgetFeature.HAlign hAlign) {
    }
    
    @Override
    public void setVAlign(final ITextWidgetFeature.VAlign vAlign) {
    }
}
