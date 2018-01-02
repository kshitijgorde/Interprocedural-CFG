// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.processor;

import org.xml.sax.SAXException;
import org.apache.xalan.templates.ElemTemplateElement;
import org.apache.xalan.templates.NamespaceAlias;
import org.xml.sax.Attributes;

class ProcessorNamespaceAlias extends XSLTElementProcessor
{
    public void startElement(final StylesheetHandler handler, final String uri, final String localName, final String rawName, final Attributes attributes) throws SAXException {
        final NamespaceAlias na = new NamespaceAlias(handler.nextUid());
        this.setPropertiesFromAttributes(handler, rawName, attributes, na);
        String prefix = na.getStylesheetPrefix();
        if (prefix.equals("#default")) {
            prefix = "";
            na.setStylesheetPrefix(prefix);
        }
        final String stylesheetNS = handler.getNamespaceForPrefix(prefix);
        na.setStylesheetNamespace(stylesheetNS);
        prefix = na.getResultPrefix();
        if (prefix.equals("#default")) {
            prefix = "";
            na.setResultPrefix(prefix);
        }
        final String resultNS = handler.getNamespaceForPrefix(prefix);
        na.setResultNamespace(resultNS);
        handler.getStylesheet().setNamespaceAlias(na);
        handler.getStylesheet().appendChild(na);
    }
}
