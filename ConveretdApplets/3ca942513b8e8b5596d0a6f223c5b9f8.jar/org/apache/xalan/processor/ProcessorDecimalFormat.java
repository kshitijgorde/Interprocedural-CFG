// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.processor;

import org.xml.sax.SAXException;
import org.apache.xalan.templates.ElemTemplateElement;
import javax.xml.transform.SourceLocator;
import org.apache.xalan.templates.DecimalFormatProperties;
import org.xml.sax.Attributes;

class ProcessorDecimalFormat extends XSLTElementProcessor
{
    static final long serialVersionUID = -5052904382662921627L;
    
    public void startElement(final StylesheetHandler handler, final String uri, final String localName, final String rawName, final Attributes attributes) throws SAXException {
        final DecimalFormatProperties dfp = new DecimalFormatProperties(handler.nextUid());
        dfp.setDOMBackPointer(handler.getOriginatingNode());
        dfp.setLocaterInfo(handler.getLocator());
        this.setPropertiesFromAttributes(handler, rawName, attributes, dfp);
        handler.getStylesheet().setDecimalFormat(dfp);
        handler.getStylesheet().appendChild(dfp);
    }
}
