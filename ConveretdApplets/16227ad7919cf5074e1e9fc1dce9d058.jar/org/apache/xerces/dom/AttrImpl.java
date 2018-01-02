// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.DOMException;
import org.w3c.dom.Attr;

public class AttrImpl extends NodeImpl implements Attr
{
    static final long serialVersionUID = -4421396439224009670L;
    protected boolean owned;
    protected boolean specified;
    protected String namespaceURI;
    protected String prefix;
    protected String localName;
    
    protected AttrImpl(final DocumentImpl documentImpl, final String localName) {
        super(documentImpl, localName, null);
        this.specified = true;
        this.localName = localName;
        super.syncData = true;
    }
    
    protected AttrImpl(final DocumentImpl ownerDocument, final String namespaceURI, final String s) {
        this.specified = true;
        super.ownerDocument = ownerDocument;
        super.name = s;
        this.namespaceURI = namespaceURI;
        final int index = s.indexOf(58);
        if (index < 0) {
            this.prefix = null;
            this.localName = s;
        }
        else {
            this.prefix = s.substring(0, index);
            this.localName = s.substring(index + 1);
        }
        super.syncData = true;
    }
    
    public short getNodeType() {
        return 2;
    }
    
    public void setNodeValue(final String value) throws DOMException {
        this.setValue(value);
    }
    
    public String getNodeValue() {
        return this.getValue();
    }
    
    public Node getParentNode() {
        return null;
    }
    
    public String getName() {
        if (super.syncData) {
            this.synchronizeData();
        }
        return super.name;
    }
    
    public void setValue(final String s) {
        if (super.readOnly) {
            throw new DOMExceptionImpl((short)7, "NO_MODIFICATION_ALLOWED_ERR");
        }
        String value = "";
        final LCount lookup = LCount.lookup("DOMAttrModified");
        if (lookup.captures + lookup.bubbles + lookup.defaults > 0 && super.parentNode != null) {
            value = this.getValue();
        }
        while (super.firstChild != null) {
            this.internalRemoveChild(super.firstChild, 1);
        }
        this.specified = true;
        if (s != null) {
            this.internalInsertBefore(super.ownerDocument.createTextNode(s), null, 1);
        }
        this.changed();
        this.dispatchAggregateEvents(this, value);
    }
    
    public String getValue() {
        final StringBuffer sb = new StringBuffer();
        for (NodeImpl nextSibling = (NodeImpl)this.getFirstChild(); nextSibling != null; nextSibling = nextSibling.nextSibling) {
            sb.append(nextSibling.getNodeValue());
        }
        return sb.toString();
    }
    
    public boolean getSpecified() {
        if (super.syncData) {
            this.synchronizeData();
        }
        return this.specified;
    }
    
    public Element getElement() {
        return (Element)super.parentNode;
    }
    
    public Element getOwnerElement() {
        return (Element)super.parentNode;
    }
    
    public String getNamespaceURI() {
        if (super.syncData) {
            this.synchronizeData();
        }
        return this.namespaceURI;
    }
    
    public String getPrefix() {
        if (super.syncData) {
            this.synchronizeData();
        }
        return this.prefix;
    }
    
    public void setPrefix(final String prefix) throws DOMException {
        if (super.syncData) {
            this.synchronizeData();
        }
        if (!DocumentImpl.isXMLName(prefix)) {
            throw new DOMExceptionImpl((short)5, "INVALID_CHARACTER_ERR");
        }
        this.prefix = prefix;
        super.name = String.valueOf(prefix) + ":" + this.localName;
    }
    
    public String getLocalName() {
        if (super.syncData) {
            this.synchronizeData();
        }
        return this.localName;
    }
    
    public void setSpecified(final boolean specified) {
        if (super.syncData) {
            this.synchronizeData();
        }
        this.specified = specified;
    }
    
    public String toString() {
        return String.valueOf(this.getName()) + "=" + "\"" + this.getValue() + "\"";
    }
}
