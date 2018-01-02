// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.processor;

import org.apache.xalan.templates.ElemParam;
import org.xml.sax.SAXException;
import org.apache.xalan.templates.ElemTemplateElement;

class ProcessorGlobalParamDecl extends ProcessorTemplateElem
{
    static final long serialVersionUID = 1900450872353587350L;
    
    protected void appendAndPush(final StylesheetHandler handler, final ElemTemplateElement elem) throws SAXException {
        handler.pushElemTemplateElement(elem);
    }
    
    public void endElement(final StylesheetHandler handler, final String uri, final String localName, final String rawName) throws SAXException {
        final ElemParam v = (ElemParam)handler.getElemTemplateElement();
        handler.getStylesheet().appendChild(v);
        handler.getStylesheet().setParam(v);
        super.endElement(handler, uri, localName, rawName);
    }
}
