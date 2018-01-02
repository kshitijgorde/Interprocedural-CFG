// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.w3c.dom.Node;
import org.w3c.dom.DOMException;
import org.w3c.dom.Text;
import org.w3c.dom.CharacterData;

public class TextImpl extends CharacterDataImpl implements CharacterData, Text
{
    static final long serialVersionUID = -5294980852957403469L;
    
    public TextImpl(final DocumentImpl documentImpl, final String s) {
        super(documentImpl, s);
    }
    
    public short getNodeType() {
        return 3;
    }
    
    public String getNodeName() {
        return "#text";
    }
    
    public void setIgnorableWhitespace(final boolean b) {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        this.isIgnorableWhitespace(b);
    }
    
    public boolean isIgnorableWhitespace() {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        return this.internalIsIgnorableWhitespace();
    }
    
    public Text splitText(final int n) throws DOMException {
        if (this.isReadOnly()) {
            throw new DOMException((short)7, "DOM001 Modification not allowed");
        }
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        if (n < 0 || n > super.data.length()) {
            throw new DOMException((short)1, "DOM004 Index out of bounds");
        }
        final Text textNode = this.getOwnerDocument().createTextNode(super.data.substring(n));
        this.setNodeValue(super.data.substring(0, n));
        final Node parentNode = this.getParentNode();
        if (parentNode != null) {
            parentNode.insertBefore(textNode, super.nextSibling);
        }
        return textNode;
    }
}
