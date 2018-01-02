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
import org.w3c.dom.TypeInfo;
import org.w3c.dom.Element;

public class ElementImpl extends ParentNode implements Element, TypeInfo
{
    static final long serialVersionUID = 3717253516652722278L;
    protected String name;
    protected AttributeMap attributes;
    
    public ElementImpl(final CoreDocumentImpl coreDocumentImpl, final String name) {
        super(coreDocumentImpl);
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
    
    public Node cloneNode(final boolean b) {
        final ElementImpl elementImpl = (ElementImpl)super.cloneNode(b);
        if (this.attributes != null) {
            elementImpl.attributes = (AttributeMap)this.attributes.cloneMap(elementImpl);
        }
        return elementImpl;
    }
    
    public String getBaseURI() {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        if (this.attributes != null) {
            final Attr attr = (Attr)this.attributes.getNamedItem("xml:base");
            if (attr != null) {
                String s = attr.getNodeValue();
                if (s.length() != 0) {
                    try {
                        s = new URI(s).toString();
                    }
                    catch (URI.MalformedURIException ex) {
                        final String s2 = (super.ownerNode != null) ? super.ownerNode.getBaseURI() : null;
                        if (s2 != null) {
                            String string;
                            try {
                                string = new URI(new URI(s2), s).toString();
                            }
                            catch (URI.MalformedURIException ex2) {
                                return null;
                            }
                            return string;
                        }
                        return null;
                    }
                    return s;
                }
            }
        }
        final String s3 = (super.ownerNode != null) ? super.ownerNode.getBaseURI() : null;
        if (s3 != null) {
            try {
                return new URI(s3).toString();
            }
            catch (URI.MalformedURIException ex3) {
                return null;
            }
        }
        return null;
    }
    
    void setOwnerDocument(final CoreDocumentImpl coreDocumentImpl) {
        super.setOwnerDocument(coreDocumentImpl);
        if (this.attributes != null) {
            this.attributes.setOwnerDocument(coreDocumentImpl);
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
                else if (((NodeImpl)firstChild).getNodeValue() == null || ((NodeImpl)firstChild).getNodeValue().length() == 0) {
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
            throw new DOMException((short)7, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NO_MODIFICATION_ALLOWED_ERR", null));
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
            throw new DOMException((short)7, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NO_MODIFICATION_ALLOWED_ERR", null));
        }
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        if (this.attributes == null) {
            throw new DOMException((short)8, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_FOUND_ERR", null));
        }
        return (Attr)this.attributes.removeItem(attr, true);
    }
    
    public void setAttribute(final String s, final String s2) {
        if (super.ownerDocument.errorChecking && this.isReadOnly()) {
            throw new DOMException((short)7, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NO_MODIFICATION_ALLOWED_ERR", null));
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
                throw new DOMException((short)7, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NO_MODIFICATION_ALLOWED_ERR", null));
            }
            if (namedItem.getOwnerDocument() != super.ownerDocument) {
                throw new DOMException((short)4, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "WRONG_DOCUMENT_ERR", null));
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
            throw new DOMException((short)7, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NO_MODIFICATION_ALLOWED_ERR", null));
        }
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        final int index = s2.indexOf(58);
        String substring;
        String substring2;
        if (index < 0) {
            substring = null;
            substring2 = s2;
        }
        else {
            substring = s2.substring(0, index);
            substring2 = s2.substring(index + 1);
        }
        Attr attributeNodeNS = this.getAttributeNodeNS(s, substring2);
        if (attributeNodeNS == null) {
            final Attr attributeNS = this.getOwnerDocument().createAttributeNS(s, s2);
            if (this.attributes == null) {
                this.attributes = new AttributeMap(this, null);
            }
            attributeNS.setNodeValue(s3);
            this.attributes.setNamedItemNS(attributeNS);
        }
        else {
            if (attributeNodeNS instanceof AttrNSImpl) {
                ((AttrNSImpl)attributeNodeNS).name = ((substring != null) ? (substring + ":" + substring2) : substring2);
            }
            else {
                attributeNodeNS = new AttrNSImpl((CoreDocumentImpl)this.getOwnerDocument(), s, s2, substring2);
                this.attributes.setNamedItemNS(attributeNodeNS);
            }
            attributeNodeNS.setNodeValue(s3);
        }
    }
    
    public void removeAttributeNS(final String s, final String s2) {
        if (super.ownerDocument.errorChecking && this.isReadOnly()) {
            throw new DOMException((short)7, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NO_MODIFICATION_ALLOWED_ERR", null));
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
                throw new DOMException((short)7, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NO_MODIFICATION_ALLOWED_ERR", null));
            }
            if (namedItemNS.getOwnerDocument() != super.ownerDocument) {
                throw new DOMException((short)4, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "WRONG_DOCUMENT_ERR", null));
            }
        }
        if (this.attributes == null) {
            this.attributes = new AttributeMap(this, null);
        }
        return (Attr)this.attributes.setNamedItemNS(namedItemNS);
    }
    
    protected int setXercesAttributeNode(final Attr attr) {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        if (this.attributes == null) {
            this.attributes = new AttributeMap(this, null);
        }
        return this.attributes.addItem(attr);
    }
    
    protected int getXercesAttribute(final String s, final String s2) {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        if (this.attributes == null) {
            return -1;
        }
        return this.attributes.getNamedItemIndex(s, s2);
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
    
    public boolean isEqualNode(final Node node) {
        if (!super.isEqualNode(node)) {
            return false;
        }
        final boolean hasAttributes = this.hasAttributes();
        if (hasAttributes != ((Element)node).hasAttributes()) {
            return false;
        }
        if (hasAttributes) {
            final NamedNodeMap attributes = this.getAttributes();
            final NamedNodeMap attributes2 = ((Element)node).getAttributes();
            final int length = attributes.getLength();
            if (length != attributes2.getLength()) {
                return false;
            }
            for (int i = 0; i < length; ++i) {
                final Node item = attributes.item(i);
                if (item.getLocalName() == null) {
                    final Node namedItem = attributes2.getNamedItem(item.getNodeName());
                    if (namedItem == null || !((NodeImpl)item).isEqualNode(namedItem)) {
                        return false;
                    }
                }
                else {
                    final Node namedItemNS = attributes2.getNamedItemNS(item.getNamespaceURI(), item.getLocalName());
                    if (namedItemNS == null || !((NodeImpl)item).isEqualNode(namedItemNS)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    public void setIdAttributeNode(final Attr attr, final boolean b) {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        if (super.ownerDocument.errorChecking) {
            if (this.isReadOnly()) {
                throw new DOMException((short)7, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NO_MODIFICATION_ALLOWED_ERR", null));
            }
            if (attr.getOwnerElement() != this) {
                throw new DOMException((short)8, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_FOUND_ERR", null));
            }
        }
        ((AttrImpl)attr).isIdAttribute(b);
        if (!b) {
            super.ownerDocument.removeIdentifier(attr.getValue());
        }
        else {
            super.ownerDocument.putIdentifier(attr.getValue(), this);
        }
    }
    
    public void setIdAttribute(final String s, final boolean b) {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        final Attr attributeNode = this.getAttributeNode(s);
        if (attributeNode == null) {
            throw new DOMException((short)8, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_FOUND_ERR", null));
        }
        if (super.ownerDocument.errorChecking) {
            if (this.isReadOnly()) {
                throw new DOMException((short)7, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NO_MODIFICATION_ALLOWED_ERR", null));
            }
            if (attributeNode.getOwnerElement() != this) {
                throw new DOMException((short)8, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_FOUND_ERR", null));
            }
        }
        ((AttrImpl)attributeNode).isIdAttribute(b);
        if (!b) {
            super.ownerDocument.removeIdentifier(attributeNode.getValue());
        }
        else {
            super.ownerDocument.putIdentifier(attributeNode.getValue(), this);
        }
    }
    
    public void setIdAttributeNS(final String s, final String s2, final boolean b) {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        final Attr attributeNodeNS = this.getAttributeNodeNS(s, s2);
        if (attributeNodeNS == null) {
            throw new DOMException((short)8, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_FOUND_ERR", null));
        }
        if (super.ownerDocument.errorChecking) {
            if (this.isReadOnly()) {
                throw new DOMException((short)7, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NO_MODIFICATION_ALLOWED_ERR", null));
            }
            if (attributeNodeNS.getOwnerElement() != this) {
                throw new DOMException((short)8, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_FOUND_ERR", null));
            }
        }
        ((AttrImpl)attributeNodeNS).isIdAttribute(b);
        if (!b) {
            super.ownerDocument.removeIdentifier(attributeNodeNS.getValue());
        }
        else {
            super.ownerDocument.putIdentifier(attributeNodeNS.getValue(), this);
        }
    }
    
    public String getTypeName() {
        return null;
    }
    
    public String getTypeNamespace() {
        return null;
    }
    
    public boolean isDerivedFrom(final String s, final String s2, final int n) {
        return false;
    }
    
    public TypeInfo getSchemaTypeInfo() {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        return this;
    }
    
    public void setReadOnly(final boolean b, final boolean b2) {
        super.setReadOnly(b, b2);
        if (this.attributes != null) {
            this.attributes.setReadOnly(b, true);
        }
    }
    
    protected void synchronizeData() {
        this.needsSyncData(false);
        final boolean mutationEvents = super.ownerDocument.getMutationEvents();
        super.ownerDocument.setMutationEvents(false);
        this.setupDefaultAttributes();
        super.ownerDocument.setMutationEvents(mutationEvents);
    }
    
    void moveSpecifiedAttributes(final ElementImpl elementImpl) {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        if (elementImpl.hasAttributes()) {
            if (this.attributes == null) {
                this.attributes = new AttributeMap(this, null);
            }
            this.attributes.moveSpecifiedAttributes(elementImpl.attributes);
        }
    }
    
    protected void setupDefaultAttributes() {
        final NamedNodeMapImpl defaultAttributes = this.getDefaultAttributes();
        if (defaultAttributes != null) {
            this.attributes = new AttributeMap(this, defaultAttributes);
        }
    }
    
    protected void reconcileDefaultAttributes() {
        if (this.attributes != null) {
            this.attributes.reconcileDefaults(this.getDefaultAttributes());
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
