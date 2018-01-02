// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.w3c.dom.Text;
import org.w3c.dom.NodeList;
import org.w3c.dom.Attr;
import org.w3c.dom.Node;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;

public class ElementImpl extends NodeImpl implements Element
{
    static final long serialVersionUID = -7202454486126245907L;
    protected NamedNodeMapImpl attributes;
    protected String namespaceURI;
    protected String prefix;
    protected String localName;
    protected boolean enableNamespace;
    
    public ElementImpl(final DocumentImpl documentImpl, final String localName) {
        super(documentImpl, localName, null);
        this.enableNamespace = false;
        this.localName = localName;
        super.syncData = true;
    }
    
    protected ElementImpl(final DocumentImpl ownerDocument, final String namespaceURI, final String s) {
        this.enableNamespace = false;
        super.ownerDocument = ownerDocument;
        this.namespaceURI = namespaceURI;
        super.name = s;
        final int index = s.indexOf(58);
        if (index < 0) {
            this.prefix = null;
            this.localName = s;
        }
        else {
            this.prefix = s.substring(0, index);
            this.localName = s.substring(index + 1);
        }
        this.enableNamespace = true;
        super.syncData = true;
    }
    
    public short getNodeType() {
        return 1;
    }
    
    public String getNodeValue() {
        return null;
    }
    
    public void setNodeValue(final String s) throws DOMException {
        throw new DOMExceptionImpl((short)7, "NO_MODIFICATION_ALLOWED_ERR");
    }
    
    public NamedNodeMap getAttributes() {
        if (super.syncData) {
            this.synchronizeData();
        }
        return this.attributes;
    }
    
    public Node cloneNode(final boolean b) {
        if (super.syncData) {
            this.synchronizeData();
        }
        final ElementImpl elementImpl = (ElementImpl)super.cloneNode(b);
        elementImpl.attributes = this.attributes.cloneMap();
        return elementImpl;
    }
    
    public String getValue() {
        return null;
    }
    
    public String getAttribute(final String s) {
        if (super.syncData) {
            this.synchronizeData();
        }
        final Attr attr = (Attr)this.attributes.getNamedItem(s);
        if (attr == null) {
            return "";
        }
        return attr.getValue();
    }
    
    public Attr getAttributeNode(final String s) {
        if (super.syncData) {
            this.synchronizeData();
        }
        return (Attr)this.attributes.getNamedItem(s);
    }
    
    public NodeList getElementsByTagName(final String s) {
        return new DeepNodeListImpl(this, s);
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
    
    public String getTagName() {
        if (super.syncData) {
            this.synchronizeData();
        }
        return super.name;
    }
    
    public void normalize() {
        Node nextSibling;
        for (Node firstChild = this.getFirstChild(); firstChild != null; firstChild = nextSibling) {
            nextSibling = firstChild.getNextSibling();
            if (nextSibling != null && firstChild.getNodeType() == 3 && nextSibling.getNodeType() == 3) {
                ((Text)firstChild).appendData(nextSibling.getNodeValue());
                this.removeChild(nextSibling);
                nextSibling = firstChild;
            }
            else if (firstChild.getNodeType() == 1) {
                ((Element)firstChild).normalize();
            }
        }
    }
    
    public void removeAttribute(final String s) {
        if (super.readOnly) {
            throw new DOMExceptionImpl((short)7, "NO_MODIFICATION_ALLOWED_ERR");
        }
        if (super.syncData) {
            this.synchronizeData();
        }
        final AttrImpl attrImpl = (AttrImpl)this.attributes.getNamedItem(s);
        if (attrImpl != null) {
            attrImpl.owned = false;
            this.attributes.removeNamedItem(s);
        }
    }
    
    public Attr removeAttributeNode(final Attr attr) throws DOMException {
        if (super.readOnly) {
            throw new DOMExceptionImpl((short)7, "NO_MODIFICATION_ALLOWED_ERR");
        }
        if (super.syncData) {
            this.synchronizeData();
        }
        final AttrImpl attrImpl = (AttrImpl)this.attributes.getNamedItem(attr.getName());
        if (attrImpl == attr) {
            this.attributes.removeNamedItem(attr.getName());
            attrImpl.owned = false;
            return attrImpl;
        }
        throw new DOMExceptionImpl((short)8, "NOT_FOUND_ERR");
    }
    
    public void setAttribute(final String s, final String nodeValue) {
        if (super.readOnly) {
            throw new DOMExceptionImpl((short)7, "NO_MODIFICATION_ALLOWED_ERR");
        }
        if (super.syncData) {
            this.synchronizeData();
        }
        final AttrImpl namedItem = (AttrImpl)this.getOwnerDocument().createAttribute(s);
        namedItem.setNodeValue(nodeValue);
        this.attributes.setNamedItem(namedItem);
        namedItem.owned = true;
    }
    
    public Attr setAttributeNode(final Attr attr) throws DOMException {
        if (super.readOnly) {
            throw new DOMExceptionImpl((short)7, "NO_MODIFICATION_ALLOWED_ERR");
        }
        if (super.syncData) {
            this.synchronizeData();
        }
        if (!(attr instanceof AttrImpl)) {
            throw new DOMExceptionImpl((short)4, "WRONG_DOCUMENT_ERR");
        }
        final AttrImpl namedItem = (AttrImpl)attr;
        final AttrImpl attrImpl = (AttrImpl)this.attributes.getNamedItem(attr.getName());
        this.attributes.setNamedItem(namedItem);
        namedItem.owned = true;
        return attrImpl;
    }
    
    public String getAttributeNS(final String s, final String s2) {
        if (super.syncData) {
            this.synchronizeData();
        }
        final Attr attr = (Attr)this.attributes.getNamedItemNS(s, s2);
        if (attr == null) {
            return "";
        }
        return attr.getValue();
    }
    
    public void setAttributeNS(final String s, final String s2, final String nodeValue) {
        if (super.readOnly) {
            throw new DOMExceptionImpl((short)7, "NO_MODIFICATION_ALLOWED_ERR");
        }
        if (super.syncData) {
            this.synchronizeData();
        }
        final AttrImpl namedItem = (AttrImpl)((DocumentImpl)this.getOwnerDocument()).createAttributeNS(s, s2);
        namedItem.setNodeValue(nodeValue);
        this.attributes.setNamedItem(namedItem);
        namedItem.owned = true;
    }
    
    public void removeAttributeNS(final String s, final String s2) {
        if (super.readOnly) {
            throw new DOMExceptionImpl((short)7, "NO_MODIFICATION_ALLOWED_ERR");
        }
        if (super.syncData) {
            this.synchronizeData();
        }
        final AttrImpl attrImpl = (AttrImpl)this.attributes.getNamedItemNS(s, s2);
        if (attrImpl != null) {
            attrImpl.owned = false;
            this.attributes.removeNamedItemNS(s, s2);
        }
    }
    
    public Attr getAttributeNodeNS(final String s, final String s2) {
        if (super.syncData) {
            this.synchronizeData();
        }
        return (Attr)this.attributes.getNamedItemNS(s, s2);
    }
    
    public Attr setAttributeNodeNS(final Attr attr) throws DOMException {
        if (super.readOnly) {
            throw new DOMExceptionImpl((short)7, "NO_MODIFICATION_ALLOWED_ERR");
        }
        if (super.syncData) {
            this.synchronizeData();
        }
        if (!(attr instanceof AttrImpl)) {
            throw new DOMExceptionImpl((short)4, "WRONG_DOCUMENT_ERR");
        }
        final AttrImpl namedItem = (AttrImpl)attr;
        final AttrImpl attrImpl = (AttrImpl)this.attributes.getNamedItemNS(namedItem.getNamespaceURI(), namedItem.getLocalName());
        this.attributes.setNamedItem(namedItem);
        namedItem.owned = true;
        return attrImpl;
    }
    
    public NodeList getElementsByTagNameNS(final String s, final String s2) {
        return new DeepNodeListImpl(this, s, s2);
    }
    
    public void setReadOnly(final boolean b, final boolean b2) {
        super.setReadOnly(b, b2);
        if (super.syncChildren) {
            this.synchronizeChildren();
        }
        this.attributes.setReadOnly(b, true);
    }
    
    protected void synchronizeData() {
        super.syncData = false;
        this.setupDefaultAttributes();
    }
    
    protected void setupDefaultAttributes() {
        NamedNodeMapImpl namedNodeMapImpl = null;
        final DocumentTypeImpl documentTypeImpl = (DocumentTypeImpl)super.ownerDocument.getDoctype();
        if (documentTypeImpl != null) {
            final ElementDefinitionImpl elementDefinitionImpl = (ElementDefinitionImpl)documentTypeImpl.getElements().getNamedItem(this.getNodeName());
            if (elementDefinitionImpl != null) {
                namedNodeMapImpl = (NamedNodeMapImpl)elementDefinitionImpl.getAttributes();
            }
        }
        this.attributes = new NamedNodeMapImpl(this, namedNodeMapImpl);
    }
}
