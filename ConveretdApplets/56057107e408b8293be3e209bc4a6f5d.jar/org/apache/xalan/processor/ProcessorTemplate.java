// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.processor;

import org.xml.sax.SAXException;
import org.apache.xalan.templates.ElemTemplate;
import org.apache.xalan.templates.ElemTemplateElement;

class ProcessorTemplate extends ProcessorTemplateElem
{
    protected void appendAndPush(final StylesheetHandler handler, final ElemTemplateElement elem) throws SAXException {
        super.appendAndPush(handler, elem);
        elem.setDOMBackPointer(handler.getOriginatingNode());
        handler.getStylesheet().setTemplate((ElemTemplate)elem);
    }
}
