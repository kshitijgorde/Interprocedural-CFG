// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.processor;

import javax.xml.transform.TransformerException;
import javax.xml.transform.SourceLocator;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.w3c.dom.Node;
import org.apache.xalan.templates.ElemTemplateElement;

public class ProcessorTemplateElem extends XSLTElementProcessor
{
    protected void appendAndPush(final StylesheetHandler handler, final ElemTemplateElement elem) throws SAXException {
        final ElemTemplateElement parent = handler.getElemTemplateElement();
        parent.appendChild(elem);
        handler.pushElemTemplateElement(elem);
    }
    
    public void endElement(final StylesheetHandler handler, final String uri, final String localName, final String rawName) throws SAXException {
        handler.popElemTemplateElement();
    }
    
    public void startElement(final StylesheetHandler handler, final String uri, final String localName, final String rawName, final Attributes attributes) throws SAXException {
        try {
            final XSLTElementDef def = this.getElemDef();
            final Class classObject = def.getClassObject();
            ElemTemplateElement elem = null;
            try {
                elem = classObject.newInstance();
                elem.setDOMBackPointer(handler.getOriginatingNode());
                elem.setLocaterInfo(handler.getLocator());
                elem.setPrefixes(handler.getNamespaceSupport());
            }
            catch (InstantiationException ie) {
                handler.error("Failed creating ElemTemplateElement instance!", ie);
            }
            catch (IllegalAccessException iae) {
                handler.error("Failed creating ElemTemplateElement instance!", iae);
            }
            this.setPropertiesFromAttributes(handler, rawName, attributes, elem);
            this.appendAndPush(handler, elem);
        }
        catch (TransformerException e) {
            throw new SAXException(e);
        }
    }
}
