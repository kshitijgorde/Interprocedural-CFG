// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.serialize;

import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.AttributeList;
import org.xml.sax.Attributes;

public final class TextSerializer extends BaseMarkupSerializer
{
    public TextSerializer() {
        super(new OutputFormat("text", null, false));
    }
    
    public void setOutputFormat(final OutputFormat outputFormat) {
        super.setOutputFormat((outputFormat != null) ? outputFormat : new OutputFormat("text", null, false));
    }
    
    public void startElement(final String s, final String s2, final String s3, final Attributes attributes) {
        this.startElement((s3 == null) ? s2 : s3, null);
    }
    
    public void endElement(final String s, final String s2, final String s3) {
        this.endElement((s3 == null) ? s2 : s3);
    }
    
    public void startElement(final String s, final AttributeList list) {
        final ElementState elementState = this.getElementState();
        if (this.isDocumentState() && !super._started) {
            this.startDocument(s);
        }
        this.enterElementState(null, null, s, elementState.preserveSpace);
    }
    
    public void endElement(final String s) {
        this.getElementState();
        final ElementState leaveElementState = this.leaveElementState();
        leaveElementState.afterElement = true;
        leaveElementState.empty = false;
        if (this.isDocumentState()) {
            super._printer.flush();
        }
    }
    
    public void processingInstruction(final String s, final String s2) {
    }
    
    public void comment(final String s) {
    }
    
    public void comment(final char[] array, final int n, final int n2) {
    }
    
    public void characters(final char[] array, final int n, final int n2) {
        final ElementState content = this.content();
        final boolean b = false;
        content.inCData = b;
        content.doCData = b;
        this.printText(array, n, n2, true, true);
    }
    
    protected void characters(final String s, final boolean b) {
        final ElementState content = this.content();
        final boolean b2 = false;
        content.inCData = b2;
        content.doCData = b2;
        this.printText(s, true, true);
    }
    
    protected void startDocument(final String s) {
        super._printer.leaveDTD();
        super._started = true;
        this.serializePreRoot();
    }
    
    protected void serializeElement(final Element element) {
        final String tagName = element.getTagName();
        final ElementState elementState = this.getElementState();
        if (this.isDocumentState() && !super._started) {
            this.startDocument(tagName);
        }
        final boolean preserveSpace = elementState.preserveSpace;
        if (element.hasChildNodes()) {
            this.enterElementState(null, null, tagName, preserveSpace);
            for (Node node = element.getFirstChild(); node != null; node = node.getNextSibling()) {
                this.serializeNode(node);
            }
            this.endElement(tagName);
        }
        else if (!this.isDocumentState()) {
            elementState.afterElement = true;
            elementState.empty = false;
        }
    }
    
    protected void serializeNode(final Node node) {
        switch (node.getNodeType()) {
            case 3: {
                if (node.getNodeValue() != null) {
                    this.characters(node.getNodeValue(), true);
                    break;
                }
                break;
            }
            case 4: {
                if (node.getNodeValue() != null) {
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
                for (Node node2 = node.getFirstChild(); node2 != null; node2 = node2.getNextSibling()) {
                    this.serializeNode(node2);
                }
                break;
            }
        }
    }
    
    protected ElementState content() {
        final ElementState elementState = this.getElementState();
        if (!this.isDocumentState()) {
            if (elementState.empty) {
                elementState.empty = false;
            }
            elementState.afterElement = false;
        }
        return elementState;
    }
    
    protected String getEntityRef(final int n) {
        return null;
    }
}
