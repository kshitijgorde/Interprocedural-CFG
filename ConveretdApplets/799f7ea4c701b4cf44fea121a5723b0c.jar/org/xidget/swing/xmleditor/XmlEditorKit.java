// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.xmleditor;

import javax.swing.text.ViewFactory;
import javax.swing.text.StyledEditorKit;

public class XmlEditorKit extends StyledEditorKit
{
    private ViewFactory xmlViewFactory;
    
    public XmlEditorKit() {
        this.xmlViewFactory = new XmlViewFactory();
    }
    
    @Override
    public ViewFactory getViewFactory() {
        return this.xmlViewFactory;
    }
    
    @Override
    public String getContentType() {
        return "text/xml";
    }
}
