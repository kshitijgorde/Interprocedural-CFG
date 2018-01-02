// 
// Decompiled by Procyon v0.5.30
// 

package BsscXML;

import java.io.IOException;
import java.io.Reader;
import java.util.Stack;

public class BsscXMLParser implements IBsscXMLParser, Runnable
{
    private Stack m_processingElements;
    private IBsscXMLDocumentBuilder m_document;
    private boolean m_bReady;
    private Reader m_is;
    
    public static String xmlToText(final String s) {
        final StringBuffer sb = new StringBuffer();
        for (int length = s.length(), i = 0; i < length; ++i) {
            if (s.charAt(i) == '&') {
                if (i + 3 < length && s.charAt(i + 1) == 'l' && s.charAt(i + 2) == 't' && s.charAt(i + 3) == ';') {
                    sb.append('<');
                    i += 3;
                }
                else if (i + 3 < length && s.charAt(i + 1) == 'g' && s.charAt(i + 2) == 't' && s.charAt(i + 3) == ';') {
                    sb.append('>');
                    i += 3;
                }
                else if (i + 5 < length && s.charAt(i + 1) == 'q' && s.charAt(i + 2) == 'u' && s.charAt(i + 3) == 'o' && s.charAt(i + 4) == 't' && s.charAt(i + 5) == ';') {
                    sb.append('\"');
                    i += 5;
                }
                else if (i + 4 < length && s.charAt(i + 1) == 'a' && s.charAt(i + 2) == 'm' && s.charAt(i + 3) == 'p' && s.charAt(i + 4) == ';') {
                    sb.append('&');
                    i += 4;
                }
                else {
                    sb.append(s.charAt(i));
                }
            }
            else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
    
    public void parse() {
        if (this.m_is != null) {
            this.parse(this.m_is);
        }
    }
    
    public void parse(final Reader reader) {
        try {
            new BsscXMLTokenizer(this).tokenize(reader);
            reader.close();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void beginElement(final String s) throws BsscXMLException {
        final IBsscXMLElementBuilder element = BsscXML.createElement(s);
        if (!this.m_processingElements.empty()) {
            final IBsscXMLElementBuilder peek = this.m_processingElements.peek();
            if (!(peek instanceof IBsscXMLElementBuilder)) {
                throw new BsscXMLException("Type Mismatch!");
            }
            peek.addChild(element);
            element.setParent(peek);
        }
        else {
            if (this.m_document == null) {
                throw new BsscXMLException("Internal Error!");
            }
            this.m_document.setRoot(element);
        }
        this.m_processingElements.push(element);
    }
    
    public void findValue(final String s) throws BsscXMLException {
        final IBsscXMLElementBuilder peek = this.m_processingElements.peek();
        if (peek instanceof IBsscXMLElementBuilder) {
            peek.setValue(xmlToText(s));
            return;
        }
        throw new BsscXMLException("Type Mismatch!");
    }
    
    public void beginDocument() {
    }
    
    public void endElement(final String s) throws BsscXMLException {
        final IBsscXMLElementBuilder peek = this.m_processingElements.peek();
        if (!(peek instanceof IBsscXMLElementBuilder)) {
            throw new BsscXMLException("Type Mismatch!");
        }
        if (peek.checkName(s)) {
            this.m_processingElements.pop();
            return;
        }
        throw new BsscXMLException("Bad XML Format!");
    }
    
    public BsscXMLParser(final IBsscXMLConsumer bsscXMLConsumer) {
        this.m_processingElements = null;
        this.m_document = null;
        this.m_is = null;
        this.m_document = new BsscXMLDocument();
        ((BsscXMLDocument)this.m_document).addConsumer(bsscXMLConsumer);
        this.m_processingElements = new Stack();
    }
    
    public void endDocument() throws BsscXMLException {
        if (!this.m_processingElements.empty()) {
            throw new BsscXMLException("Wrong XML Format!");
        }
        this.m_document.setReady(true);
    }
    
    public void findAttr(final String s, final String s2) throws BsscXMLException {
        final IBsscXMLElementBuilder peek = this.m_processingElements.peek();
        if (peek instanceof IBsscXMLElementBuilder) {
            peek.setAttribute(s, xmlToText(s2));
            return;
        }
        throw new BsscXMLException("Type Mismatch!");
    }
    
    public void run() {
        this.parse();
    }
    
    public void setSource(final Reader is) {
        this.m_is = is;
    }
}
