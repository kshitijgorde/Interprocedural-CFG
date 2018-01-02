// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.processor;

import org.apache.xalan.templates.ElemVariable;
import org.xml.sax.SAXException;
import org.apache.xalan.templates.ElemTemplateElement;

class ProcessorGlobalVariableDecl extends ProcessorTemplateElem
{
    static final long serialVersionUID = -5954332402269819582L;
    
    protected void appendAndPush(final StylesheetHandler handler, final ElemTemplateElement elem) throws SAXException {
        handler.pushElemTemplateElement(elem);
    }
    
    public void endElement(final StylesheetHandler handler, final String uri, final String localName, final String rawName) throws SAXException {
        final ElemVariable v = (ElemVariable)handler.getElemTemplateElement();
        handler.getStylesheet().appendChild(v);
        handler.getStylesheet().setVariable(v);
        super.endElement(handler, uri, localName, rawName);
    }
}
