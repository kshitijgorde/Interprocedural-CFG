// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.w3c.dom.DOMException;
import org.w3c.dom.Text;
import org.w3c.dom.NodeList;
import org.apache.xerces.util.URI;
import org.w3c.dom.Attr;
import org.w3c.dom.Node;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Element;

public class ElementImpl extends ParentNode implements Element
{
    static final long serialVersionUID = 3717253516652722278L;
    protected String name;
    protected AttributeMap attributes;
    
    public ElementImpl(final CoreDocumentImpl ownerDoc, final String name) {
        super(ownerDoc);
        this.name = name;
        this.needsSyncData(true);
    }
    
    protected ElementImpl() {
    }
    
    void rename(final String name) {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        this.name = name;
        this.reconcileDefaultAttributes();
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
    
    public Node cloneNode(final boolean deep) {
        final ElementImpl newnode = (ElementImpl)super.cloneNode(deep);
        if (this.attributes != null) {
            newnode.attributes = (AttributeMap)this.attributes.cloneMap(newnode);
        }
        return newnode;
    }
    
    public String getBaseURI() {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        final String baseURI = super.ownerNode.getBaseURI();
        if (this.attributes != null) {
            final Attr attrNode = (Attr)this.attributes.getNamedItem("xml:base");
            if (attrNode != null) {
                String uri = attrNode.getNodeValue();
                if (uri.length() != 0) {
                    try {
                        uri = new URI(baseURI, uri).toString();
                    }
                    catch (URI.MalformedURIException e) {
                        return null;
                    }
                    return uri;
                }
            }
        }
        return baseURI;
    }
    
    void setOwnerDocument(final CoreDocumentImpl doc) {
        super.setOwnerDocument(doc);
        if (this.attributes != null) {
            this.attributes.setOwnerDocument(doc);
        }
    }
    
    public String getAttribute(final String name) {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        if (this.attributes == null) {
            return "";
        }
        final Attr attr = (Attr)this.attributes.getNamedItem(name);
        return (attr == null) ? "" : attr.getValue();
    }
    
    public Attr getAttributeNode(final String name) {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        if (this.attributes == null) {
            return null;
        }
        return (Attr)this.attributes.getNamedItem(name);
    }
    
    public NodeList getElementsByTagName(final String tagname) {
        return new DeepNodeListImpl(this, tagname);
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
        ChildNode next;
        for (ChildNode kid = super.firstChild; kid != null; kid = next) {
            next = kid.nextSibling;
            if (kid.getNodeType() == 3) {
                if (next != null && next.getNodeType() == 3) {
                    ((Text)kid).appendData(next.getNodeValue());
                    this.removeChild(next);
                    next = kid;
                }
                else if (kid.getNodeValue().length() == 0) {
                    this.removeChild(kid);
                }
            }
            else if (kid.getNodeType() == 1) {
                kid.normalize();
            }
        }
        if (this.attributes != null) {
            for (int i = 0; i < this.attributes.getLength(); ++i) {
                final Node attr = this.attributes.item(i);
                attr.normalize();
            }
        }
        this.isNormalized(true);
    }
    
    public void removeAttribute(final String name) {
        if (super.ownerDocument.errorChecking && this.isReadOnly()) {
            final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NO_MODIFICATION_ALLOWED_ERR", null);
            throw new DOMException((short)7, msg);
        }
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        if (this.attributes == null) {
            return;
        }
        this.attributes.safeRemoveNamedItem(name);
    }
    
    public Attr removeAttributeNode(final Attr oldAttr) throws DOMException {
        if (super.ownerDocument.errorChecking && this.isReadOnly()) {
            final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NO_MODIFICATION_ALLOWED_ERR", null);
            throw new DOMException((short)7, msg);
        }
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        if (this.attributes == null) {
            final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_FOUND_ERR", null);
            throw new DOMException((short)8, msg);
        }
        return (Attr)this.attributes.removeItem(oldAttr, true);
    }
    
    public void setAttribute(final String name, final String value) {
        if (super.ownerDocument.errorChecking && this.isReadOnly()) {
            final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NO_MODIFICATION_ALLOWED_ERR", null);
            throw new DOMException((short)7, msg);
        }
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        Attr newAttr = this.getAttributeNode(name);
        if (newAttr == null) {
            newAttr = this.getOwnerDocument().createAttribute(name);
            if (this.attributes == null) {
                this.attributes = new AttributeMap(this, null);
            }
            newAttr.setNodeValue(value);
            this.attributes.setNamedItem(newAttr);
        }
        else {
            newAttr.setNodeValue(value);
        }
    }
    
    public Attr setAttributeNode(final Attr newAttr) throws DOMException {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        if (super.ownerDocument.errorChecking) {
            if (this.isReadOnly()) {
                final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NO_MODIFICATION_ALLOWED_ERR", null);
                throw new DOMException((short)7, msg);
            }
            if (newAttr.getOwnerDocument() != super.ownerDocument) {
                final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "WRONG_DOCUMENT_ERR", null);
                throw new DOMException((short)4, msg);
            }
        }
        if (this.attributes == null) {
            this.attributes = new AttributeMap(this, null);
        }
        return (Attr)this.attributes.setNamedItem(newAttr);
    }
    
    public String getAttributeNS(final String namespaceURI, final String localName) {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        if (this.attributes == null) {
            return "";
        }
        final Attr attr = (Attr)this.attributes.getNamedItemNS(namespaceURI, localName);
        return (attr == null) ? "" : attr.getValue();
    }
    
    public void setAttributeNS(final String namespaceURI, final String qualifiedName, final String value) {
        if (super.ownerDocument.errorChecking && this.isReadOnly()) {
            final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NO_MODIFICATION_ALLOWED_ERR", null);
            throw new DOMException((short)7, msg);
        }
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        final int index = qualifiedName.indexOf(58);
        String prefix;
        String localName;
        if (index < 0) {
            prefix = null;
            localName = qualifiedName;
        }
        else {
            prefix = qualifiedName.substring(0, index);
            localName = qualifiedName.substring(index + 1);
        }
        Attr newAttr = this.getAttributeNodeNS(namespaceURI, localName);
        if (newAttr == null) {
            newAttr = this.getOwnerDocument().createAttributeNS(namespaceURI, qualifiedName);
            if (this.attributes == null) {
                this.attributes = new AttributeMap(this, null);
            }
            newAttr.setNodeValue(value);
            this.attributes.setNamedItemNS(newAttr);
        }
        else {
            ((AttrNSImpl)newAttr).name = prefix + ":" + localName;
            newAttr.setNodeValue(value);
        }
    }
    
    public void removeAttributeNS(final String namespaceURI, final String localName) {
        if (super.ownerDocument.errorChecking && this.isReadOnly()) {
            final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NO_MODIFICATION_ALLOWED_ERR", null);
            throw new DOMException((short)7, msg);
        }
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        if (this.attributes == null) {
            return;
        }
        this.attributes.safeRemoveNamedItemNS(namespaceURI, localName);
    }
    
    public Attr getAttributeNodeNS(final String namespaceURI, final String localName) {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        if (this.attributes == null) {
            return null;
        }
        return (Attr)this.attributes.getNamedItemNS(namespaceURI, localName);
    }
    
    public Attr setAttributeNodeNS(final Attr newAttr) throws DOMException {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        if (super.ownerDocument.errorChecking) {
            if (this.isReadOnly()) {
                final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NO_MODIFICATION_ALLOWED_ERR", null);
                throw new DOMException((short)7, msg);
            }
            if (newAttr.getOwnerDocument() != super.ownerDocument) {
                final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "WRONG_DOCUMENT_ERR", null);
                throw new DOMException((short)4, msg);
            }
        }
        if (this.attributes == null) {
            this.attributes = new AttributeMap(this, null);
        }
        return (Attr)this.attributes.setNamedItemNS(newAttr);
    }
    
    protected int setXercesAttributeNode(final Attr attr) {
        if (this.attributes == null) {
            this.attributes = new AttributeMap(this, null);
        }
        return this.attributes.addItem(attr);
    }
    
    public boolean hasAttributes() {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        return this.attributes != null && this.attributes.getLength() != 0;
    }
    
    public boolean hasAttribute(final String name) {
        return this.getAttributeNode(name) != null;
    }
    
    public boolean hasAttributeNS(final String namespaceURI, final String localName) {
        return this.getAttributeNodeNS(namespaceURI, localName) != null;
    }
    
    public NodeList getElementsByTagNameNS(final String namespaceURI, final String localName) {
        return new DeepNodeListImpl(this, namespaceURI, localName);
    }
    
    public boolean isEqualNode(final Node arg) {
        if (!super.isEqualNode(arg)) {
            return false;
        }
        final boolean hasAttrs = this.hasAttributes();
        if (hasAttrs != ((Element)arg).hasAttributes()) {
            return false;
        }
        if (hasAttrs) {
            final NamedNodeMap map1 = this.getAttributes();
            final NamedNodeMap map2 = ((Element)arg).getAttributes();
            final int len = map1.getLength();
            if (len != map2.getLength()) {
                return false;
            }
            for (int i = 0; i < len; ++i) {
                final Node n1 = map1.item(i);
                if (n1.getLocalName() == null) {
                    final Node n2 = map2.getNamedItem(n1.getNodeName());
                    if (n2 == null || !((NodeImpl)n1).isEqualNode(n2)) {
                        return false;
                    }
                }
                else {
                    final Node n2 = map2.getNamedItemNS(n1.getNamespaceURI(), n1.getLocalName());
                    if (n2 == null || !((NodeImpl)n1).isEqualNode(n2)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    public void setReadOnly(final boolean readOnly, final boolean deep) {
        super.setReadOnly(readOnly, deep);
        if (this.attributes != null) {
            this.attributes.setReadOnly(readOnly, true);
        }
    }
    
    public void setIdAttributeNode(final Attr at) {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        if (super.ownerDocument.errorChecking) {
            if (this.isReadOnly()) {
                final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NO_MODIFICATION_ALLOWED_ERR", null);
                throw new DOMException((short)7, msg);
            }
            if (at.getOwnerElement() != this) {
                final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_FOUND_ERR", null);
                throw new DOMException((short)8, msg);
            }
        }
        ((AttrImpl)at).isIdAttribute(true);
        super.ownerDocument.putIdentifier(at.getValue(), this);
    }
    
    protected void synchronizeData() {
        this.needsSyncData(false);
        final boolean orig = super.ownerDocument.getMutationEvents();
        super.ownerDocument.setMutationEvents(false);
        this.setupDefaultAttributes();
        super.ownerDocument.setMutationEvents(orig);
    }
    
    void moveSpecifiedAttributes(final ElementImpl el) {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        if (el.hasAttributes()) {
            if (this.attributes == null) {
                this.attributes = new AttributeMap(this, null);
            }
            this.attributes.moveSpecifiedAttributes(el.attributes);
        }
    }
    
    protected void setupDefaultAttributes() {
        final NamedNodeMapImpl defaults = this.getDefaultAttributes();
        if (defaults != null) {
            this.attributes = new AttributeMap(this, defaults);
        }
    }
    
    protected void reconcileDefaultAttributes() {
        if (this.attributes != null) {
            final NamedNodeMapImpl defaults = this.getDefaultAttributes();
            this.attributes.reconcileDefaults(defaults);
        }
    }
    
    protected NamedNodeMapImpl getDefaultAttributes() {
        final DocumentTypeImpl doctype = (DocumentTypeImpl)super.ownerDocument.getDoctype();
        if (doctype == null) {
            return null;
        }
        final ElementDefinitionImpl eldef = (ElementDefinitionImpl)doctype.getElements().getNamedItem(this.getNodeName());
        if (eldef == null) {
            return null;
        }
        return (NamedNodeMapImpl)eldef.getAttributes();
    }
}
