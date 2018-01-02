// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.processor;

import org.xml.sax.SAXException;
import org.apache.xalan.templates.Stylesheet;
import org.apache.xalan.templates.WhiteSpaceInfo;
import org.apache.xpath.XPath;
import org.xml.sax.Attributes;
import java.util.Vector;

class ProcessorPreserveSpace extends XSLTElementProcessor
{
    private Vector m_elements;
    
    Vector getElements() {
        return this.m_elements;
    }
    
    public void setElements(final Vector elems) {
        this.m_elements = elems;
    }
    
    public void startElement(final StylesheetHandler handler, final String uri, final String localName, final String rawName, final Attributes attributes) throws SAXException {
        this.setPropertiesFromAttributes(handler, rawName, attributes, this);
        final Stylesheet thisSheet = handler.getStylesheet();
        final Vector xpaths = this.getElements();
        for (int i = 0; i < xpaths.size(); ++i) {
            final WhiteSpaceInfo wsi = new WhiteSpaceInfo(xpaths.elementAt(i), false, thisSheet);
            wsi.setUid(handler.nextUid());
            thisSheet.setPreserveSpaces(wsi);
        }
    }
}
