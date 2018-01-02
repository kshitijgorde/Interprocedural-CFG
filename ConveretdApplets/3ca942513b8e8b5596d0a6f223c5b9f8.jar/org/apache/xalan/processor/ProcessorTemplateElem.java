// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.processor;

import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;
import javax.xml.transform.SourceLocator;
import org.apache.xalan.templates.ElemTemplateElement;
import org.xml.sax.Attributes;

public class ProcessorTemplateElem extends XSLTElementProcessor
{
    static final long serialVersionUID = 8344994001943407235L;
    
    public void startElement(final StylesheetHandler handler, final String uri, final String localName, final String rawName, final Attributes attributes) throws SAXException {
        super.startElement(handler, uri, localName, rawName, attributes);
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
                handler.error("ER_FAILED_CREATING_ELEMTMPL", null, ie);
            }
            catch (IllegalAccessException iae) {
                handler.error("ER_FAILED_CREATING_ELEMTMPL", null, iae);
            }
            this.setPropertiesFromAttributes(handler, rawName, attributes, elem);
            this.appendAndPush(handler, elem);
        }
        catch (TransformerException te) {
            throw new SAXException(te);
        }
    }
    
    protected void appendAndPush(final StylesheetHandler handler, final ElemTemplateElement elem) throws SAXException {
        final ElemTemplateElement parent = handler.getElemTemplateElement();
        if (null != parent) {
            parent.appendChild(elem);
            handler.pushElemTemplateElement(elem);
        }
    }
    
    public void endElement(final StylesheetHandler handler, final String uri, final String localName, final String rawName) throws SAXException {
        super.endElement(handler, uri, localName, rawName);
        handler.popElemTemplateElement().setEndLocaterInfo(handler.getLocator());
    }
}
