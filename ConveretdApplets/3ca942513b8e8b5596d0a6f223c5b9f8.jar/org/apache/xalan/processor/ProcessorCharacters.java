// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.processor;

import org.apache.xalan.templates.ElemTemplateElement;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;
import javax.xml.transform.SourceLocator;
import org.apache.xalan.templates.ElemTextLiteral;
import org.apache.xml.utils.XMLCharacterRecognizer;
import org.apache.xalan.templates.ElemText;
import org.w3c.dom.Node;

public class ProcessorCharacters extends XSLTElementProcessor
{
    static final long serialVersionUID = 8632900007814162650L;
    protected Node m_firstBackPointer;
    private StringBuffer m_accumulator;
    private ElemText m_xslTextElement;
    
    public ProcessorCharacters() {
        this.m_firstBackPointer = null;
        this.m_accumulator = new StringBuffer();
    }
    
    public void startNonText(final StylesheetHandler handler) throws SAXException {
        if (this == handler.getCurrentProcessor()) {
            handler.popProcessor();
        }
        final int nChars = this.m_accumulator.length();
        if ((nChars > 0 && (null != this.m_xslTextElement || !XMLCharacterRecognizer.isWhiteSpace(this.m_accumulator))) || handler.isSpacePreserve()) {
            final ElemTextLiteral elem = new ElemTextLiteral();
            elem.setDOMBackPointer(this.m_firstBackPointer);
            elem.setLocaterInfo(handler.getLocator());
            try {
                elem.setPrefixes(handler.getNamespaceSupport());
            }
            catch (TransformerException te) {
                throw new SAXException(te);
            }
            final boolean doe = null != this.m_xslTextElement && this.m_xslTextElement.getDisableOutputEscaping();
            elem.setDisableOutputEscaping(doe);
            elem.setPreserveSpace(true);
            final char[] chars = new char[nChars];
            this.m_accumulator.getChars(0, nChars, chars, 0);
            elem.setChars(chars);
            final ElemTemplateElement parent = handler.getElemTemplateElement();
            parent.appendChild(elem);
        }
        this.m_accumulator.setLength(0);
        this.m_firstBackPointer = null;
    }
    
    public void characters(final StylesheetHandler handler, final char[] ch, final int start, final int length) throws SAXException {
        this.m_accumulator.append(ch, start, length);
        if (null == this.m_firstBackPointer) {
            this.m_firstBackPointer = handler.getOriginatingNode();
        }
        if (this != handler.getCurrentProcessor()) {
            handler.pushProcessor(this);
        }
    }
    
    public void endElement(final StylesheetHandler handler, final String uri, final String localName, final String rawName) throws SAXException {
        this.startNonText(handler);
        handler.getCurrentProcessor().endElement(handler, uri, localName, rawName);
        handler.popProcessor();
    }
    
    void setXslTextElement(final ElemText xslTextElement) {
        this.m_xslTextElement = xslTextElement;
    }
}
