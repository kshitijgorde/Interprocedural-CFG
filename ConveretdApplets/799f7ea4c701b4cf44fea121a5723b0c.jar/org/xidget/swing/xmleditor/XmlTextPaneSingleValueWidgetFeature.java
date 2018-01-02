// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.xmleditor;

import org.xmodel.IModelObject;
import org.xmodel.xml.XmlException;
import org.xmodel.xml.XmlIO;
import org.xidget.IXidget;
import org.xidget.ifeature.model.ISingleValueWidgetFeature;

public class XmlTextPaneSingleValueWidgetFeature implements ISingleValueWidgetFeature
{
    private IXidget xidget;
    private XmlIO xmlIO;
    private boolean updating;
    
    public XmlTextPaneSingleValueWidgetFeature(final IXidget xidget) {
        this.xidget = xidget;
        this.xmlIO = new XmlIO();
    }
    
    public void ignoreUpdate(final boolean updating) {
        this.updating = updating;
    }
    
    @Override
    public Object getValue() {
        final String text = this.xidget.getFeature(XmlTextPane.class).getText();
        try {
            return this.xmlIO.read(text);
        }
        catch (XmlException ex) {
            return null;
        }
    }
    
    @Override
    public void setValue(final Object o) {
        if (this.updating) {
            return;
        }
        if (!(o instanceof IModelObject)) {
            return;
        }
        final String write = this.xmlIO.write((IModelObject)o);
        if (write != null) {
            final XmlTextPane xmlTextPane = this.xidget.getFeature(XmlTextPane.class);
            try {
                xmlTextPane.setText(write);
                xmlTextPane.setCaretPosition(0);
            }
            catch (NullPointerException ex) {}
        }
    }
}
