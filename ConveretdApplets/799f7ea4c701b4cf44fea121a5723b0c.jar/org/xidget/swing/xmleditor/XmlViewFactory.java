// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.xmleditor;

import javax.swing.text.View;
import javax.swing.text.Element;
import javax.swing.text.ViewFactory;

public class XmlViewFactory implements ViewFactory
{
    @Override
    public View create(final Element element) {
        return new XmlView(element);
    }
}
