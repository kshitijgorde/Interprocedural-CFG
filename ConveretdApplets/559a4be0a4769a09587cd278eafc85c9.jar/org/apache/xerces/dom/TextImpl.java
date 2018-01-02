// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.w3c.dom.Text;
import org.w3c.dom.CharacterData;

public class TextImpl extends CharacterDataImpl implements CharacterData, Text
{
    static final long serialVersionUID = -5294980852957403469L;
    
    public TextImpl() {
    }
    
    public TextImpl(final CoreDocumentImpl ownerDoc, final String data) {
        super(ownerDoc, data);
    }
    
    public void setValues(final CoreDocumentImpl ownerDoc, final String data) {
        super.flags = 0;
        super.nextSibling = null;
        super.previousSibling = null;
        this.setOwnerDocument(ownerDoc);
        super.data = data;
    }
    
    public short getNodeType() {
        return 3;
    }
    
    public String getNodeName() {
        return "#text";
    }
    
    public void setIgnorableWhitespace(final boolean ignore) {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        this.isIgnorableWhitespace(ignore);
    }
    
    public boolean getIsWhitespaceInElementContent() {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        return this.internalIsIgnorableWhitespace();
    }
    
    public String getWholeText() {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        if (super.nextSibling == null) {
            return super.data;
        }
        final StringBuffer buffer = new StringBuffer();
        if (super.data != null && super.data.length() != 0) {
            buffer.append(super.data);
        }
        this.getWholeText(super.nextSibling, buffer);
        return buffer.toString();
    }
    
    private boolean getWholeText(Node node, final StringBuffer buffer) {
        while (node != null) {
            final short type = node.getNodeType();
            if (type == 5) {
                if (this.getWholeText(node.getFirstChild(), buffer)) {
                    return true;
                }
            }
            else {
                if (type != 3 && type != 4) {
                    return true;
                }
                ((NodeImpl)node).getTextContent(buffer);
            }
            node = node.getNextSibling();
        }
        return false;
    }
    
    public Text replaceWholeText(final String content) throws DOMException {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        if (!this.canModify(super.nextSibling)) {
            throw new DOMException((short)7, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NO_MODIFICATION_ALLOWED_ERR", null));
        }
        final Node parent = this.getParentNode();
        if ((content == null || content.length() == 0) && parent != null) {
            parent.removeChild(this);
            return null;
        }
        Text currentNode = null;
        if (this.isReadOnly()) {
            final Text newNode = this.ownerDocument().createTextNode(content);
            if (parent == null) {
                return newNode;
            }
            parent.insertBefore(newNode, this);
            parent.removeChild(this);
            currentNode = newNode;
        }
        else {
            this.setData(content);
            currentNode = this;
        }
        for (Node sibling = currentNode.getNextSibling(); sibling != null; sibling = currentNode.getNextSibling()) {
            parent.removeChild(sibling);
        }
        return currentNode;
    }
    
    private boolean canModify(Node node) {
        while (node != null) {
            final short type = node.getNodeType();
            if (type == 5) {
                if (!this.canModify(node.getFirstChild())) {
                    return false;
                }
            }
            else if (type != 3 && type != 4) {
                return false;
            }
            node = node.getNextSibling();
        }
        return true;
    }
    
    public boolean isIgnorableWhitespace() {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        return this.internalIsIgnorableWhitespace();
    }
    
    public Text splitText(final int offset) throws DOMException {
        if (this.isReadOnly()) {
            throw new DOMException((short)7, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NO_MODIFICATION_ALLOWED_ERR", null));
        }
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        if (offset < 0 || offset > super.data.length()) {
            throw new DOMException((short)1, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "INDEX_SIZE_ERR", null));
        }
        final Text newText = this.getOwnerDocument().createTextNode(super.data.substring(offset));
        this.setNodeValue(super.data.substring(0, offset));
        final Node parentNode = this.getParentNode();
        if (parentNode != null) {
            parentNode.insertBefore(newText, super.nextSibling);
        }
        return newText;
    }
    
    public void replaceData(final String value) {
        super.data = value;
    }
    
    public String removeData() {
        final String olddata = super.data;
        super.data = "";
        return olddata;
    }
}
