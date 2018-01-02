// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.w3c.dom.DOMException;
import org.w3c.dom.Text;
import org.w3c.dom.NodeList;
import org.w3c.dom.Attr;
import org.w3c.dom.Node;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Element;

public class ElementImpl extends ParentNode implements Element
{
    static final long serialVersionUID = 3717253516652722278L;
    protected String name;
    protected AttributeMap attributes;
    
    public ElementImpl(final DocumentImpl documentImpl, final String name) {
        super(documentImpl);
        this.name = name;
        this.needsSyncData(true);
    }
    
    protected ElementImpl() {
    }
    
    public short getNodeType() {
        return 1;
    }
    
    public String getNodeName() {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        return this.name;
    }
    
    public NamedNodeMap getAttributes() {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        if (this.attributes == null) {
            this.attributes = new AttributeMap(this, null);
        }
        return this.attributes;
    }
    
    public Node cloneNode(final boolean b) {
        final ElementImpl elementImpl = (ElementImpl)super.cloneNode(b);
        if (this.attributes != null) {
            elementImpl.attributes = (AttributeMap)this.attributes.cloneMap(elementImpl);
        }
        return elementImpl;
    }
    
    void setOwnerDocument(final DocumentImpl documentImpl) {
        super.setOwnerDocument(documentImpl);
        if (this.attributes != null) {
            this.attributes.setOwnerDocument(documentImpl);
        }
    }
    
    public String getAttribute(final String s) {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        if (this.attributes == null) {
            return "";
        }
        final Attr attr = (Attr)this.attributes.getNamedItem(s);
        return (attr == null) ? "" : attr.getValue();
    }
    
    public Attr getAttributeNode(final String s) {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        if (this.attributes == null) {
            return null;
        }
        return (Attr)this.attributes.getNamedItem(s);
    }
    
    public NodeList getElementsByTagName(final String s) {
        return new DeepNodeListImpl(this, s);
    }
    
    public String getTagName() {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        return this.name;
    }
    
    public void normalize() {
        if (this.isNormalized()) {
            return;
        }
        if (this.needsSyncChildren()) {
            this.synchronizeChildren();
        }
        Object nextSibling;
        for (Object firstChild = super.firstChild; firstChild != null; firstChild = nextSibling) {
            nextSibling = ((ChildNode)firstChild).nextSibling;
            if (((NodeImpl)firstChild).getNodeType() == 3) {
                if (nextSibling != null && ((NodeImpl)nextSibling).getNodeType() == 3) {
                    ((Text)firstChild).appendData(((NodeImpl)nextSibling).getNodeValue());
                    this.removeChild((Node)nextSibling);
                    nextSibling = firstChild;
                }
                else if (((NodeImpl)firstChild).getNodeValue().length() == 0) {
                    this.removeChild((Node)firstChild);
                }
            }
            else if (((NodeImpl)firstChild).getNodeType() == 1) {
                ((NodeImpl)firstChild).normalize();
            }
        }
        if (this.attributes != null) {
            for (int i = 0; i < this.attributes.getLength(); ++i) {
                this.attributes.item(i).normalize();
            }
        }
        this.isNormalized(true);
    }
    
    public void removeAttribute(final String s) {
        if (super.ownerDocument.errorChecking && this.isReadOnly()) {
            throw new DOMException((short)7, "DOM001 Modification not allowed");
        }
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        if (this.attributes == null) {
            return;
        }
        this.attributes.safeRemoveNamedItem(s);
    }
    
    public Attr removeAttributeNode(final Attr attr) throws DOMException {
        if (super.ownerDocument.errorChecking && this.isReadOnly()) {
            throw new DOMException((short)7, "DOM001 Modification not allowed");
        }
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        if (this.attributes == null) {
            throw new DOMException((short)8, "DOM008 Not found");
        }
        return (Attr)this.attributes.removeNamedItem(attr.getName());
    }
    
    public void setAttribute(final String s, final String s2) {
        if (super.ownerDocument.errorChecking && this.isReadOnly()) {
            throw new DOMException((short)7, "DOM001 Modification not allowed");
        }
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        final Attr attributeNode = this.getAttributeNode(s);
        if (attributeNode == null) {
            final Attr attribute = this.getOwnerDocument().createAttribute(s);
            if (this.attributes == null) {
                this.attributes = new AttributeMap(this, null);
            }
            attribute.setNodeValue(s2);
            this.attributes.setNamedItem(attribute);
        }
        else {
            attributeNode.setNodeValue(s2);
        }
    }
    
    public Attr setAttributeNode(final Attr namedItem) throws DOMException {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        if (super.ownerDocument.errorChecking) {
            if (this.isReadOnly()) {
                throw new DOMException((short)7, "DOM001 Modification not allowed");
            }
            if (namedItem.getOwnerDocument() != super.ownerDocument) {
                throw new DOMException((short)4, "DOM005 Wrong document");
            }
        }
        if (this.attributes == null) {
            this.attributes = new AttributeMap(this, null);
        }
        return (Attr)this.attributes.setNamedItem(namedItem);
    }
    
    public String getAttributeNS(final String s, final String s2) {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        if (this.attributes == null) {
            return "";
        }
        final Attr attr = (Attr)this.attributes.getNamedItemNS(s, s2);
        return (attr == null) ? "" : attr.getValue();
    }
    
    public void setAttributeNS(final String s, final String s2, final String s3) {
        if (super.ownerDocument.errorChecking && this.isReadOnly()) {
            throw new DOMException((short)7, "DOM001 Modification not allowed");
        }
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        final Attr attributeNodeNS = this.getAttributeNodeNS(s, s2);
        if (attributeNodeNS == null) {
            final Attr attributeNS = this.getOwnerDocument().createAttributeNS(s, s2);
            if (this.attributes == null) {
                this.attributes = new AttributeMap(this, null);
            }
            attributeNS.setNodeValue(s3);
            this.attributes.setNamedItemNS(attributeNS);
        }
        else {
            attributeNodeNS.setNodeValue(s3);
        }
    }
    
    public void removeAttributeNS(final String s, final String s2) {
        if (super.ownerDocument.errorChecking && this.isReadOnly()) {
            throw new DOMException((short)7, "DOM001 Modification not allowed");
        }
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        if (this.attributes == null) {
            return;
        }
        this.attributes.safeRemoveNamedItemNS(s, s2);
    }
    
    public Attr getAttributeNodeNS(final String s, final String s2) {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        if (this.attributes == null) {
            return null;
        }
        return (Attr)this.attributes.getNamedItemNS(s, s2);
    }
    
    public Attr setAttributeNodeNS(final Attr namedItemNS) throws DOMException {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        if (super.ownerDocument.errorChecking) {
            if (this.isReadOnly()) {
                throw new DOMException((short)7, "DOM001 Modification not allowed");
            }
            if (namedItemNS.getOwnerDocument() != super.ownerDocument) {
                throw new DOMException((short)4, "DOM005 Wrong document");
            }
        }
        if (this.attributes == null) {
            this.attributes = new AttributeMap(this, null);
        }
        return (Attr)this.attributes.setNamedItemNS(namedItemNS);
    }
    
    public boolean hasAttributes() {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        return this.attributes != null && this.attributes.getLength() != 0;
    }
    
    public boolean hasAttribute(final String s) {
        return this.getAttributeNode(s) != null;
    }
    
    public boolean hasAttributeNS(final String s, final String s2) {
        return this.getAttributeNodeNS(s, s2) != null;
    }
    
    public NodeList getElementsByTagNameNS(final String s, final String s2) {
        return new DeepNodeListImpl(this, s, s2);
    }
    
    public void setReadOnly(final boolean b, final boolean b2) {
        super.setReadOnly(b, b2);
        if (this.attributes != null) {
            this.attributes.setReadOnly(b, true);
        }
    }
    
    protected void synchronizeData() {
        this.needsSyncData(false);
        final boolean mutationEvents = super.ownerDocument.mutationEvents;
        super.ownerDocument.mutationEvents = false;
        this.setupDefaultAttributes();
        super.ownerDocument.mutationEvents = mutationEvents;
    }
    
    protected void setupDefaultAttributes() {
        final NamedNodeMapImpl defaultAttributes = this.getDefaultAttributes();
        if (defaultAttributes != null) {
            this.attributes = new AttributeMap(this, defaultAttributes);
        }
    }
    
    protected void reconcileDefaultAttributes() {
        final NamedNodeMapImpl defaultAttributes = this.getDefaultAttributes();
        if (defaultAttributes != null) {
            this.attributes.reconcileDefaults(defaultAttributes);
        }
    }
    
    protected NamedNodeMapImpl getDefaultAttributes() {
        final DocumentTypeImpl documentTypeImpl = (DocumentTypeImpl)super.ownerDocument.getDoctype();
        if (documentTypeImpl == null) {
            return null;
        }
        final ElementDefinitionImpl elementDefinitionImpl = (ElementDefinitionImpl)documentTypeImpl.getElements().getNamedItem(this.getNodeName());
        if (elementDefinitionImpl == null) {
            return null;
        }
        return (NamedNodeMapImpl)elementDefinitionImpl.getAttributes();
    }
}
