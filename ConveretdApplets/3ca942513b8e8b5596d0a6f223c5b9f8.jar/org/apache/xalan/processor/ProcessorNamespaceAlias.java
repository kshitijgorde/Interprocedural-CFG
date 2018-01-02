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
    static final long serialVersionUID = -6309867839007018964L;
    
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
        String resultNS;
        if (prefix.equals("#default")) {
            prefix = "";
            na.setResultPrefix(prefix);
            resultNS = handler.getNamespaceForPrefix(prefix);
            if (null == resultNS) {
                handler.error("ER_INVALID_NAMESPACE_URI_VALUE_FOR_RESULT_PREFIX_FOR_DEFAULT", null, null);
            }
        }
        else {
            resultNS = handler.getNamespaceForPrefix(prefix);
            if (null == resultNS) {
                handler.error("ER_INVALID_SET_NAMESPACE_URI_VALUE_FOR_RESULT_PREFIX", new Object[] { prefix }, null);
            }
        }
        na.setResultNamespace(resultNS);
        handler.getStylesheet().setNamespaceAlias(na);
        handler.getStylesheet().appendChild(na);
    }
}
