// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.processor;

import org.apache.xalan.templates.ElemTemplateElement;
import org.w3c.dom.Node;
import javax.xml.transform.TransformerException;
import javax.xml.transform.SourceLocator;
import org.apache.xalan.templates.ElemAttributeSet;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

class ProcessorAttributeSet extends XSLTElementProcessor
{
    public void endElement(final StylesheetHandler handler, final String uri, final String localName, final String rawName) throws SAXException {
        handler.popElemTemplateElement();
    }
    
    public void startElement(final StylesheetHandler handler, final String uri, final String localName, final String rawName, final Attributes attributes) throws SAXException {
        final ElemAttributeSet eat = new ElemAttributeSet();
        eat.setLocaterInfo(handler.getLocator());
        try {
            eat.setPrefixes(handler.getNamespaceSupport());
        }
        catch (TransformerException te) {
            throw new SAXException(te);
        }
        eat.setDOMBackPointer(handler.getOriginatingNode());
        this.setPropertiesFromAttributes(handler, rawName, attributes, eat);
        handler.getStylesheet().setAttributeSet(eat);
        final ElemTemplateElement parent = handler.getElemTemplateElement();
        parent.appendChild(eat);
        handler.pushElemTemplateElement(eat);
    }
}
