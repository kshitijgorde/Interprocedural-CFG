// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.processor;

import org.apache.xalan.templates.ElemTemplateElement;
import org.xml.sax.SAXException;
import org.apache.xalan.templates.ElemExsltFuncResult;
import org.apache.xalan.templates.ElemParam;
import org.apache.xalan.templates.ElemVariable;
import org.apache.xalan.templates.ElemExsltFunction;
import org.xml.sax.Attributes;

public class ProcessorExsltFuncResult extends ProcessorTemplateElem
{
    public void startElement(final StylesheetHandler handler, final String uri, final String localName, final String rawName, final Attributes attributes) throws SAXException {
        String msg = "";
        super.startElement(handler, uri, localName, rawName, attributes);
        ElemTemplateElement ancestor;
        for (ancestor = handler.getElemTemplateElement().getParentElem(); ancestor != null && !(ancestor instanceof ElemExsltFunction); ancestor = ancestor.getParentElem()) {
            if (ancestor instanceof ElemVariable || ancestor instanceof ElemParam || ancestor instanceof ElemExsltFuncResult) {
                msg = "func:result cannot appear within a variable, parameter, or another func:result.";
                handler.error(msg, new SAXException(msg));
            }
        }
        if (ancestor == null) {
            msg = "func:result must appear in a func:function element";
            handler.error(msg, new SAXException(msg));
        }
    }
}
