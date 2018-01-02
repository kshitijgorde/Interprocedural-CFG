// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.processor;

import org.xml.sax.SAXException;
import org.w3c.dom.Node;
import org.apache.xalan.templates.DecimalFormatProperties;
import org.xml.sax.Attributes;

class ProcessorDecimalFormat extends XSLTElementProcessor
{
    public void startElement(final StylesheetHandler handler, final String uri, final String localName, final String rawName, final Attributes attributes) throws SAXException {
        final DecimalFormatProperties dfp = new DecimalFormatProperties(handler.nextUid());
        this.setPropertiesFromAttributes(handler, rawName, attributes, dfp);
        handler.getStylesheet().setDecimalFormat(dfp);
        handler.getStylesheet().appendChild(dfp);
    }
}
