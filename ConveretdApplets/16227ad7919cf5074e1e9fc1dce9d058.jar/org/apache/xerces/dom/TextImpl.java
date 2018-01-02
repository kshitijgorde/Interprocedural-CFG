// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

public class TextImpl extends CharacterDataImpl implements Text
{
    static final long serialVersionUID = -5294980852957403469L;
    protected boolean ignorableWhitespace;
    
    public TextImpl(final DocumentImpl documentImpl, final String s) {
        super(documentImpl, s);
    }
    
    public short getNodeType() {
        return 3;
    }
    
    public String getNodeName() {
        return "#text";
    }
    
    public Node cloneNode(final boolean b) {
        return super.ownerDocument.createTextNode(this.getNodeValue());
    }
    
    public void setIgnorableWhitespace(final boolean ignorableWhitespace) {
        if (super.syncData) {
            this.synchronizeData();
        }
        this.ignorableWhitespace = ignorableWhitespace;
    }
    
    public boolean isIgnorableWhitespace() {
        if (super.syncData) {
            this.synchronizeData();
        }
        return this.ignorableWhitespace;
    }
    
    public Text splitText(final int n) throws DOMException {
        if (super.readOnly) {
            throw new DOMExceptionImpl((short)7, "NO_MODIFICATION_ALLOWED_ERR");
        }
        if (super.syncData) {
            this.synchronizeData();
        }
        if (n < 0 || n > super.value.length() - 1) {
            throw new DOMExceptionImpl((short)1, "INDEX_SIZE_ERR");
        }
        final Text textNode = super.ownerDocument.createTextNode(super.value.substring(n));
        this.setNodeValue(super.value.substring(0, n));
        if (super.parentNode != null) {
            super.parentNode.insertBefore(textNode, super.nextSibling);
        }
        return textNode;
    }
}
