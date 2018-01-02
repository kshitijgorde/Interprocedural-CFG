// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.processor;

import org.xml.sax.SAXException;
import org.w3c.dom.Node;
import org.apache.xalan.templates.ElemText;
import org.apache.xalan.templates.ElemTemplateElement;

public class ProcessorText extends ProcessorTemplateElem
{
    protected void appendAndPush(final StylesheetHandler handler, final ElemTemplateElement elem) throws SAXException {
        final ProcessorCharacters charProcessor = (ProcessorCharacters)handler.getProcessorFor(null, "text()", "text");
        charProcessor.setXslTextElement((ElemText)elem);
        final ElemTemplateElement parent = handler.getElemTemplateElement();
        parent.appendChild(elem);
        elem.setDOMBackPointer(handler.getOriginatingNode());
    }
    
    public void endElement(final StylesheetHandler handler, final String uri, final String localName, final String rawName) throws SAXException {
        final ProcessorCharacters charProcessor = (ProcessorCharacters)handler.getProcessorFor(null, "text()", "text");
        charProcessor.setXslTextElement(null);
    }
}
