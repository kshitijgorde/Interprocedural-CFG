// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.serialize;

import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.IOException;
import org.xml.sax.SAXException;
import org.xml.sax.AttributeList;
import org.xml.sax.Attributes;

public class TextSerializer extends BaseMarkupSerializer
{
    public TextSerializer() {
        super(new OutputFormat("text", null, false));
    }
    
    public void setOutputFormat(final OutputFormat format) {
        super.setOutputFormat((format != null) ? format : new OutputFormat("text", null, false));
    }
    
    public void startElement(final String namespaceURI, final String localName, final String rawName, final Attributes attrs) throws SAXException {
        this.startElement((rawName == null) ? localName : rawName, null);
    }
    
    public void endElement(final String namespaceURI, final String localName, final String rawName) throws SAXException {
        this.endElement((rawName == null) ? localName : rawName);
    }
    
    public void startElement(final String tagName, final AttributeList attrs) throws SAXException {
        try {
            ElementState state = this.getElementState();
            if (this.isDocumentState() && !super._started) {
                this.startDocument(tagName);
            }
            final boolean preserveSpace = state.preserveSpace;
            state = this.enterElementState(null, null, tagName, preserveSpace);
        }
        catch (IOException except) {
            throw new SAXException(except);
        }
    }
    
    public void endElement(final String tagName) throws SAXException {
        try {
            this.endElementIO(tagName);
        }
        catch (IOException except) {
            throw new SAXException(except);
        }
    }
    
    public void endElementIO(final String tagName) throws IOException {
        ElementState state = this.getElementState();
        state = this.leaveElementState();
        state.afterElement = true;
        state.empty = false;
        if (this.isDocumentState()) {
            super._printer.flush();
        }
    }
    
    public void processingInstructionIO(final String target, final String code) throws IOException {
    }
    
    public void comment(final String text) {
    }
    
    public void comment(final char[] chars, final int start, final int length) {
    }
    
    public void characters(final char[] chars, final int start, final int length) throws SAXException {
        try {
            final ElementState content = this.content();
            final boolean b = false;
            content.inCData = b;
            content.doCData = b;
            this.printText(chars, start, length, true, true);
        }
        catch (IOException except) {
            throw new SAXException(except);
        }
    }
    
    protected void characters(final String text, final boolean unescaped) throws IOException {
        final ElementState content = this.content();
        final boolean b = false;
        content.inCData = b;
        content.doCData = b;
        this.printText(text, true, true);
    }
    
    protected void startDocument(final String rootTagName) throws IOException {
        super._printer.leaveDTD();
        super._started = true;
        this.serializePreRoot();
    }
    
    protected void serializeElement(final Element elem) throws IOException {
        final String tagName = elem.getTagName();
        ElementState state = this.getElementState();
        if (this.isDocumentState() && !super._started) {
            this.startDocument(tagName);
        }
        final boolean preserveSpace = state.preserveSpace;
        if (elem.hasChildNodes()) {
            state = this.enterElementState(null, null, tagName, preserveSpace);
            for (Node child = elem.getFirstChild(); child != null; child = child.getNextSibling()) {
                this.serializeNode(child);
            }
            this.endElementIO(tagName);
        }
        else if (!this.isDocumentState()) {
            state.afterElement = true;
            state.empty = false;
        }
    }
    
    protected void serializeNode(final Node node) throws IOException {
        switch (node.getNodeType()) {
            case 3: {
                final String text = node.getNodeValue();
                if (text != null) {
                    this.characters(node.getNodeValue(), true);
                    break;
                }
                break;
            }
            case 4: {
                final String text = node.getNodeValue();
                if (text != null) {
                    this.characters(node.getNodeValue(), true);
                    break;
                }
                break;
            }
            case 8: {}
            case 5: {}
            case 1: {
                this.serializeElement((Element)node);
                break;
            }
            case 9:
            case 11: {
                for (Node child = node.getFirstChild(); child != null; child = child.getNextSibling()) {
                    this.serializeNode(child);
                }
                break;
            }
        }
    }
    
    protected ElementState content() {
        final ElementState state = this.getElementState();
        if (!this.isDocumentState()) {
            if (state.empty) {
                state.empty = false;
            }
            state.afterElement = false;
        }
        return state;
    }
    
    protected String getEntityRef(final int ch) {
        return null;
    }
}
