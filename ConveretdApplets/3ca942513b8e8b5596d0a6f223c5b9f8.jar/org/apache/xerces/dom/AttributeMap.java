// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.w3c.dom.Element;
import java.util.Vector;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;

public class AttributeMap extends NamedNodeMapImpl
{
    static final long serialVersionUID = 8872606282138665383L;
    
    protected AttributeMap(final ElementImpl elementImpl, final NamedNodeMapImpl namedNodeMapImpl) {
        super(elementImpl);
        if (namedNodeMapImpl != null) {
            this.cloneContent(namedNodeMapImpl);
            if (super.nodes != null) {
                this.hasDefaults(true);
            }
        }
    }
    
    public Node setNamedItem(final Node node) throws DOMException {
        final boolean errorChecking = super.ownerNode.ownerDocument().errorChecking;
        if (errorChecking) {
            if (this.isReadOnly()) {
                throw new DOMException((short)7, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NO_MODIFICATION_ALLOWED_ERR", null));
            }
            if (node.getOwnerDocument() != super.ownerNode.ownerDocument()) {
                throw new DOMException((short)4, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "WRONG_DOCUMENT_ERR", null));
            }
            if (node.getNodeType() != 2) {
                throw new DOMException((short)3, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "HIERARCHY_REQUEST_ERR", null));
            }
        }
        final AttrImpl attrImpl = (AttrImpl)node;
        if (!attrImpl.isOwned()) {
            attrImpl.ownerNode = super.ownerNode;
            attrImpl.isOwned(true);
            final int namePoint = this.findNamePoint(node.getNodeName(), 0);
            AttrImpl attrImpl2 = null;
            if (namePoint >= 0) {
                attrImpl2 = super.nodes.elementAt(namePoint);
                super.nodes.setElementAt(node, namePoint);
                attrImpl2.ownerNode = super.ownerNode.ownerDocument();
                attrImpl2.isOwned(false);
                attrImpl2.isSpecified(true);
            }
            else {
                final int n = -1 - namePoint;
                if (null == super.nodes) {
                    super.nodes = new Vector(5, 10);
                }
                super.nodes.insertElementAt(node, n);
            }
            super.ownerNode.ownerDocument().setAttrNode(attrImpl, attrImpl2);
            if (!attrImpl.isNormalized()) {
                super.ownerNode.isNormalized(false);
            }
            return attrImpl2;
        }
        if (errorChecking && attrImpl.getOwnerElement() != super.ownerNode) {
            throw new DOMException((short)10, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "INUSE_ATTRIBUTE_ERR", null));
        }
        return node;
    }
    
    public Node setNamedItemNS(final Node node) throws DOMException {
        final boolean errorChecking = super.ownerNode.ownerDocument().errorChecking;
        if (errorChecking) {
            if (this.isReadOnly()) {
                throw new DOMException((short)7, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NO_MODIFICATION_ALLOWED_ERR", null));
            }
            if (node.getOwnerDocument() != super.ownerNode.ownerDocument()) {
                throw new DOMException((short)4, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "WRONG_DOCUMENT_ERR", null));
            }
            if (node.getNodeType() != 2) {
                throw new DOMException((short)3, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "HIERARCHY_REQUEST_ERR", null));
            }
        }
        final AttrImpl attrImpl = (AttrImpl)node;
        if (!attrImpl.isOwned()) {
            attrImpl.ownerNode = super.ownerNode;
            attrImpl.isOwned(true);
            final int namePoint = this.findNamePoint(attrImpl.getNamespaceURI(), attrImpl.getLocalName());
            AttrImpl attrImpl2 = null;
            if (namePoint >= 0) {
                attrImpl2 = super.nodes.elementAt(namePoint);
                super.nodes.setElementAt(node, namePoint);
                attrImpl2.ownerNode = super.ownerNode.ownerDocument();
                attrImpl2.isOwned(false);
                attrImpl2.isSpecified(true);
            }
            else {
                final int namePoint2 = this.findNamePoint(node.getNodeName(), 0);
                if (namePoint2 >= 0) {
                    attrImpl2 = (AttrImpl)super.nodes.elementAt(namePoint2);
                    super.nodes.insertElementAt(node, namePoint2);
                }
                else {
                    final int n = -1 - namePoint2;
                    if (null == super.nodes) {
                        super.nodes = new Vector(5, 10);
                    }
                    super.nodes.insertElementAt(node, n);
                }
            }
            super.ownerNode.ownerDocument().setAttrNode(attrImpl, attrImpl2);
            if (!attrImpl.isNormalized()) {
                super.ownerNode.isNormalized(false);
            }
            return attrImpl2;
        }
        if (errorChecking && attrImpl.getOwnerElement() != super.ownerNode) {
            throw new DOMException((short)10, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "INUSE_ATTRIBUTE_ERR", null));
        }
        return node;
    }
    
    public Node removeNamedItem(final String s) throws DOMException {
        return this.internalRemoveNamedItem(s, true);
    }
    
    Node safeRemoveNamedItem(final String s) {
        return this.internalRemoveNamedItem(s, false);
    }
    
    protected Node removeItem(final Node node, final boolean b) throws DOMException {
        int n = -1;
        if (super.nodes != null) {
            for (int i = 0; i < super.nodes.size(); ++i) {
                if (super.nodes.elementAt(i) == node) {
                    n = i;
                    break;
                }
            }
        }
        if (n < 0) {
            throw new DOMException((short)8, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_FOUND_ERR", null));
        }
        return this.remove((AttrImpl)node, n, b);
    }
    
    protected final Node internalRemoveNamedItem(final String s, final boolean b) {
        if (this.isReadOnly()) {
            throw new DOMException((short)7, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NO_MODIFICATION_ALLOWED_ERR", null));
        }
        final int namePoint = this.findNamePoint(s, 0);
        if (namePoint >= 0) {
            return this.remove((AttrImpl)super.nodes.elementAt(namePoint), namePoint, true);
        }
        if (b) {
            throw new DOMException((short)8, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_FOUND_ERR", null));
        }
        return null;
    }
    
    private final Node remove(final AttrImpl attrImpl, final int n, final boolean b) {
        final CoreDocumentImpl ownerDocument = super.ownerNode.ownerDocument();
        final String nodeName = attrImpl.getNodeName();
        if (attrImpl.isIdAttribute()) {
            ownerDocument.removeIdentifier(attrImpl.getValue());
        }
        if (this.hasDefaults() && b) {
            final NamedNodeMapImpl defaultAttributes = ((ElementImpl)super.ownerNode).getDefaultAttributes();
            final Node namedItem;
            if (defaultAttributes != null && (namedItem = defaultAttributes.getNamedItem(nodeName)) != null && this.findNamePoint(nodeName, n + 1) < 0) {
                final NodeImpl nodeImpl = (NodeImpl)namedItem.cloneNode(true);
                if (namedItem.getLocalName() != null) {
                    ((AttrNSImpl)nodeImpl).namespaceURI = attrImpl.getNamespaceURI();
                }
                nodeImpl.ownerNode = super.ownerNode;
                nodeImpl.isOwned(true);
                nodeImpl.isSpecified(false);
                super.nodes.setElementAt(nodeImpl, n);
                if (attrImpl.isIdAttribute()) {
                    ownerDocument.putIdentifier(nodeImpl.getNodeValue(), (Element)super.ownerNode);
                }
            }
            else {
                super.nodes.removeElementAt(n);
            }
        }
        else {
            super.nodes.removeElementAt(n);
        }
        attrImpl.ownerNode = ownerDocument;
        attrImpl.isOwned(false);
        attrImpl.isSpecified(true);
        attrImpl.isIdAttribute(false);
        ownerDocument.removedAttrNode(attrImpl, super.ownerNode, nodeName);
        return attrImpl;
    }
    
    public Node removeNamedItemNS(final String s, final String s2) throws DOMException {
        return this.internalRemoveNamedItemNS(s, s2, true);
    }
    
    Node safeRemoveNamedItemNS(final String s, final String s2) {
        return this.internalRemoveNamedItemNS(s, s2, false);
    }
    
    protected final Node internalRemoveNamedItemNS(final String namespaceURI, final String s, final boolean b) {
        final CoreDocumentImpl ownerDocument = super.ownerNode.ownerDocument();
        if (ownerDocument.errorChecking && this.isReadOnly()) {
            throw new DOMException((short)7, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NO_MODIFICATION_ALLOWED_ERR", null));
        }
        final int namePoint = this.findNamePoint(namespaceURI, s);
        if (namePoint >= 0) {
            final AttrImpl attrImpl = super.nodes.elementAt(namePoint);
            if (attrImpl.isIdAttribute()) {
                ownerDocument.removeIdentifier(attrImpl.getValue());
            }
            final String nodeName = attrImpl.getNodeName();
            if (this.hasDefaults()) {
                final NamedNodeMapImpl defaultAttributes = ((ElementImpl)super.ownerNode).getDefaultAttributes();
                final Node namedItem;
                if (defaultAttributes != null && (namedItem = defaultAttributes.getNamedItem(nodeName)) != null) {
                    final int namePoint2 = this.findNamePoint(nodeName, 0);
                    if (namePoint2 >= 0 && this.findNamePoint(nodeName, namePoint2 + 1) < 0) {
                        final NodeImpl nodeImpl = (NodeImpl)namedItem.cloneNode(true);
                        nodeImpl.ownerNode = super.ownerNode;
                        if (namedItem.getLocalName() != null) {
                            ((AttrNSImpl)nodeImpl).namespaceURI = namespaceURI;
                        }
                        nodeImpl.isOwned(true);
                        nodeImpl.isSpecified(false);
                        super.nodes.setElementAt(nodeImpl, namePoint);
                        if (nodeImpl.isIdAttribute()) {
                            ownerDocument.putIdentifier(nodeImpl.getNodeValue(), (Element)super.ownerNode);
                        }
                    }
                    else {
                        super.nodes.removeElementAt(namePoint);
                    }
                }
                else {
                    super.nodes.removeElementAt(namePoint);
                }
            }
            else {
                super.nodes.removeElementAt(namePoint);
            }
            attrImpl.ownerNode = ownerDocument;
            attrImpl.isOwned(false);
            attrImpl.isSpecified(true);
            attrImpl.isIdAttribute(false);
            ownerDocument.removedAttrNode(attrImpl, super.ownerNode, s);
            return attrImpl;
        }
        if (b) {
            throw new DOMException((short)8, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_FOUND_ERR", null));
        }
        return null;
    }
    
    public NamedNodeMapImpl cloneMap(final NodeImpl nodeImpl) {
        final AttributeMap attributeMap = new AttributeMap((ElementImpl)nodeImpl, null);
        attributeMap.hasDefaults(this.hasDefaults());
        attributeMap.cloneContent(this);
        return attributeMap;
    }
    
    protected void cloneContent(final NamedNodeMapImpl namedNodeMapImpl) {
        final Vector nodes = namedNodeMapImpl.nodes;
        if (nodes != null) {
            final int size = nodes.size();
            if (size != 0) {
                if (super.nodes == null) {
                    super.nodes = new Vector(size);
                }
                super.nodes.setSize(size);
                for (int i = 0; i < size; ++i) {
                    final NodeImpl nodeImpl = nodes.elementAt(i);
                    final NodeImpl nodeImpl2 = (NodeImpl)nodeImpl.cloneNode(true);
                    nodeImpl2.isSpecified(nodeImpl.isSpecified());
                    super.nodes.setElementAt(nodeImpl2, i);
                    nodeImpl2.ownerNode = super.ownerNode;
                    nodeImpl2.isOwned(true);
                }
            }
        }
    }
    
    void moveSpecifiedAttributes(final AttributeMap attributeMap) {
        for (int i = ((attributeMap.nodes != null) ? attributeMap.nodes.size() : 0) - 1; i >= 0; --i) {
            final AttrImpl attrImpl = attributeMap.nodes.elementAt(i);
            if (attrImpl.isSpecified()) {
                attributeMap.remove(attrImpl, i, false);
                if (attrImpl.getLocalName() != null) {
                    this.setNamedItem(attrImpl);
                }
                else {
                    this.setNamedItemNS(attrImpl);
                }
            }
        }
    }
    
    protected void reconcileDefaults(final NamedNodeMapImpl namedNodeMapImpl) {
        for (int i = ((super.nodes != null) ? super.nodes.size() : 0) - 1; i >= 0; --i) {
            final AttrImpl attrImpl = super.nodes.elementAt(i);
            if (!attrImpl.isSpecified()) {
                this.remove(attrImpl, i, false);
            }
        }
        if (namedNodeMapImpl == null) {
            return;
        }
        if (super.nodes == null || super.nodes.size() == 0) {
            this.cloneContent(namedNodeMapImpl);
        }
        else {
            for (int size = namedNodeMapImpl.nodes.size(), j = 0; j < size; ++j) {
                final AttrImpl attrImpl2 = namedNodeMapImpl.nodes.elementAt(j);
                final int namePoint = this.findNamePoint(attrImpl2.getNodeName(), 0);
                if (namePoint < 0) {
                    final int n = -1 - namePoint;
                    final NodeImpl nodeImpl = (NodeImpl)attrImpl2.cloneNode(true);
                    nodeImpl.ownerNode = super.ownerNode;
                    nodeImpl.isOwned(true);
                    nodeImpl.isSpecified(false);
                    super.nodes.insertElementAt(nodeImpl, n);
                }
            }
        }
    }
    
    protected final int addItem(final Node node) {
        final AttrImpl attrImpl = (AttrImpl)node;
        attrImpl.ownerNode = super.ownerNode;
        attrImpl.isOwned(true);
        int n = this.findNamePoint(node.getNamespaceURI(), node.getLocalName());
        if (n >= 0) {
            super.nodes.setElementAt(node, n);
        }
        else {
            n = this.findNamePoint(node.getNodeName(), 0);
            if (n >= 0) {
                super.nodes.insertElementAt(node, n);
            }
            else {
                n = -1 - n;
                if (null == super.nodes) {
                    super.nodes = new Vector(5, 10);
                }
                super.nodes.insertElementAt(node, n);
            }
        }
        super.ownerNode.ownerDocument().setAttrNode(attrImpl, null);
        return n;
    }
}
