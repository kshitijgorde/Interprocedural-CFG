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
    protected AttributeMap(final ElementImpl ownerNode, final NamedNodeMapImpl defaults) {
        super(ownerNode);
        if (defaults != null) {
            this.cloneContent(defaults);
            if (super.nodes != null) {
                this.hasDefaults(true);
            }
        }
    }
    
    public Node setNamedItem(final Node arg) throws DOMException {
        if (this.isReadOnly()) {
            final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NO_MODIFICATION_ALLOWED_ERR", null);
            throw new DOMException((short)7, msg);
        }
        if (arg.getOwnerDocument() != super.ownerNode.ownerDocument()) {
            final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "WRONG_DOCUMENT_ERR", null);
            throw new DOMException((short)4, msg);
        }
        if (arg.getNodeType() != 2) {
            final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "HIERARCHY_REQUEST_ERR", null);
            throw new DOMException((short)3, msg);
        }
        final AttrImpl argn = (AttrImpl)arg;
        if (!argn.isOwned()) {
            argn.ownerNode = super.ownerNode;
            argn.isOwned(true);
            int i = this.findNamePoint(arg.getNodeName(), 0);
            AttrImpl previous = null;
            if (i >= 0) {
                previous = super.nodes.elementAt(i);
                super.nodes.setElementAt(arg, i);
                previous.ownerNode = super.ownerNode.ownerDocument();
                previous.isOwned(false);
                previous.isSpecified(true);
            }
            else {
                i = -1 - i;
                if (null == super.nodes) {
                    super.nodes = new Vector(5, 10);
                }
                super.nodes.insertElementAt(arg, i);
            }
            super.ownerNode.ownerDocument().setAttrNode(argn, previous);
            if (!argn.isNormalized()) {
                super.ownerNode.isNormalized(false);
            }
            return previous;
        }
        if (argn.getOwnerElement() != super.ownerNode) {
            final String msg2 = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "INUSE_ATTRIBUTE_ERR", null);
            throw new DOMException((short)10, msg2);
        }
        return arg;
    }
    
    public Node setNamedItemNS(final Node arg) throws DOMException {
        if (this.isReadOnly()) {
            final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NO_MODIFICATION_ALLOWED_ERR", null);
            throw new DOMException((short)7, msg);
        }
        if (arg.getOwnerDocument() != super.ownerNode.ownerDocument()) {
            final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "WRONG_DOCUMENT_ERR", null);
            throw new DOMException((short)4, msg);
        }
        if (arg.getNodeType() != 2) {
            final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "HIERARCHY_REQUEST_ERR", null);
            throw new DOMException((short)3, msg);
        }
        final AttrImpl argn = (AttrImpl)arg;
        if (!argn.isOwned()) {
            argn.ownerNode = super.ownerNode;
            argn.isOwned(true);
            int i = this.findNamePoint(argn.getNamespaceURI(), argn.getLocalName());
            AttrImpl previous = null;
            if (i >= 0) {
                previous = super.nodes.elementAt(i);
                super.nodes.setElementAt(arg, i);
                previous.ownerNode = super.ownerNode.ownerDocument();
                previous.isOwned(false);
                previous.isSpecified(true);
            }
            else {
                i = this.findNamePoint(arg.getNodeName(), 0);
                if (i >= 0) {
                    previous = super.nodes.elementAt(i);
                    super.nodes.insertElementAt(arg, i);
                }
                else {
                    i = -1 - i;
                    if (null == super.nodes) {
                        super.nodes = new Vector(5, 10);
                    }
                    super.nodes.insertElementAt(arg, i);
                }
            }
            super.ownerNode.ownerDocument().setAttrNode(argn, previous);
            if (!argn.isNormalized()) {
                super.ownerNode.isNormalized(false);
            }
            return previous;
        }
        if (argn.getOwnerElement() != super.ownerNode) {
            final String msg2 = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "INUSE_ATTRIBUTE_ERR", null);
            throw new DOMException((short)10, msg2);
        }
        return arg;
    }
    
    public Node removeNamedItem(final String name) throws DOMException {
        return this.internalRemoveNamedItem(name, true);
    }
    
    Node safeRemoveNamedItem(final String name) {
        return this.internalRemoveNamedItem(name, false);
    }
    
    protected Node removeItem(final Node item, final boolean addDefault) throws DOMException {
        int index = -1;
        if (super.nodes != null) {
            for (int i = 0; i < super.nodes.size(); ++i) {
                if (super.nodes.elementAt(i) == item) {
                    index = i;
                    break;
                }
            }
        }
        if (index < 0) {
            final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_FOUND_ERR", null);
            throw new DOMException((short)8, msg);
        }
        return this.remove((AttrImpl)item, index, addDefault);
    }
    
    protected final Node internalRemoveNamedItem(final String name, final boolean raiseEx) {
        if (this.isReadOnly()) {
            final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NO_MODIFICATION_ALLOWED_ERR", null);
            throw new DOMException((short)7, msg);
        }
        final int i = this.findNamePoint(name, 0);
        if (i >= 0) {
            return this.remove(super.nodes.elementAt(i), i, true);
        }
        if (raiseEx) {
            final String msg2 = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_FOUND_ERR", null);
            throw new DOMException((short)8, msg2);
        }
        return null;
    }
    
    private final Node remove(final AttrImpl attr, final int index, final boolean addDefault) {
        final CoreDocumentImpl ownerDocument = super.ownerNode.ownerDocument();
        final String name = attr.getNodeName();
        if (attr.isIdAttribute()) {
            ownerDocument.removeIdentifier(attr.getValue());
        }
        if (this.hasDefaults() && addDefault) {
            final NamedNodeMapImpl defaults = ((ElementImpl)super.ownerNode).getDefaultAttributes();
            final Node d;
            if (defaults != null && (d = defaults.getNamedItem(name)) != null && this.findNamePoint(name, index + 1) < 0) {
                final NodeImpl clone = (NodeImpl)d.cloneNode(true);
                clone.ownerNode = super.ownerNode;
                clone.isOwned(true);
                clone.isSpecified(false);
                super.nodes.setElementAt(clone, index);
                if (attr.isIdAttribute()) {
                    ownerDocument.putIdentifier(clone.getNodeValue(), (Element)super.ownerNode);
                }
            }
            else {
                super.nodes.removeElementAt(index);
            }
        }
        else {
            super.nodes.removeElementAt(index);
        }
        attr.ownerNode = ownerDocument;
        attr.isOwned(false);
        attr.isSpecified(true);
        attr.isIdAttribute(false);
        ownerDocument.removedAttrNode(attr, super.ownerNode, name);
        return attr;
    }
    
    public Node removeNamedItemNS(final String namespaceURI, final String name) throws DOMException {
        return this.internalRemoveNamedItemNS(namespaceURI, name, true);
    }
    
    Node safeRemoveNamedItemNS(final String namespaceURI, final String name) {
        return this.internalRemoveNamedItemNS(namespaceURI, name, false);
    }
    
    protected final Node internalRemoveNamedItemNS(final String namespaceURI, final String name, final boolean raiseEx) {
        if (this.isReadOnly()) {
            final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NO_MODIFICATION_ALLOWED_ERR", null);
            throw new DOMException((short)7, msg);
        }
        final int i = this.findNamePoint(namespaceURI, name);
        if (i >= 0) {
            final AttrImpl n = super.nodes.elementAt(i);
            final CoreDocumentImpl ownerDocument = super.ownerNode.ownerDocument();
            if (n.isIdAttribute()) {
                ownerDocument.removeIdentifier(n.getValue());
            }
            final String nodeName = n.getNodeName();
            if (this.hasDefaults()) {
                final NamedNodeMapImpl defaults = ((ElementImpl)super.ownerNode).getDefaultAttributes();
                final Node d;
                if (defaults != null && (d = defaults.getNamedItem(nodeName)) != null) {
                    final int j = this.findNamePoint(nodeName, 0);
                    if (j >= 0 && this.findNamePoint(nodeName, j + 1) < 0) {
                        final NodeImpl clone = (NodeImpl)d.cloneNode(true);
                        clone.ownerNode = super.ownerNode;
                        if (clone instanceof AttrNSImpl) {
                            ((AttrNSImpl)clone).namespaceURI = namespaceURI;
                        }
                        clone.isOwned(true);
                        clone.isSpecified(false);
                        super.nodes.setElementAt(clone, i);
                        if (clone.isIdAttribute()) {
                            ownerDocument.putIdentifier(clone.getNodeValue(), (Element)super.ownerNode);
                        }
                    }
                    else {
                        super.nodes.removeElementAt(i);
                    }
                }
                else {
                    super.nodes.removeElementAt(i);
                }
            }
            else {
                super.nodes.removeElementAt(i);
            }
            n.ownerNode = ownerDocument;
            n.isOwned(false);
            n.isSpecified(true);
            n.isIdAttribute(false);
            ownerDocument.removedAttrNode(n, super.ownerNode, name);
            return n;
        }
        if (raiseEx) {
            final String msg2 = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_FOUND_ERR", null);
            throw new DOMException((short)8, msg2);
        }
        return null;
    }
    
    public NamedNodeMapImpl cloneMap(final NodeImpl ownerNode) {
        final AttributeMap newmap = new AttributeMap((ElementImpl)ownerNode, null);
        newmap.hasDefaults(this.hasDefaults());
        newmap.cloneContent(this);
        return newmap;
    }
    
    protected void cloneContent(final NamedNodeMapImpl srcmap) {
        final Vector srcnodes = srcmap.nodes;
        if (srcnodes != null) {
            final int size = srcnodes.size();
            if (size != 0) {
                if (super.nodes == null) {
                    super.nodes = new Vector(size);
                }
                super.nodes.setSize(size);
                for (int i = 0; i < size; ++i) {
                    final NodeImpl n = srcnodes.elementAt(i);
                    final NodeImpl clone = (NodeImpl)n.cloneNode(true);
                    clone.isSpecified(n.isSpecified());
                    super.nodes.setElementAt(clone, i);
                    clone.ownerNode = super.ownerNode;
                    clone.isOwned(true);
                }
            }
        }
    }
    
    void moveSpecifiedAttributes(final AttributeMap srcmap) {
        final int nsize = (srcmap.nodes != null) ? srcmap.nodes.size() : 0;
        for (int i = nsize - 1; i >= 0; --i) {
            final AttrImpl attr = srcmap.nodes.elementAt(i);
            if (attr.isSpecified()) {
                srcmap.remove(attr, i, false);
                if (attr.getLocalName() != null) {
                    this.setNamedItem(attr);
                }
                else {
                    this.setNamedItemNS(attr);
                }
            }
        }
    }
    
    protected void reconcileDefaults(final NamedNodeMapImpl defaults) {
        final int nsize = (super.nodes != null) ? super.nodes.size() : 0;
        for (int i = nsize - 1; i >= 0; --i) {
            final AttrImpl attr = super.nodes.elementAt(i);
            if (!attr.isSpecified()) {
                this.remove(attr, i, false);
            }
        }
        if (defaults == null) {
            return;
        }
        if (super.nodes == null || super.nodes.size() == 0) {
            this.cloneContent(defaults);
        }
        else {
            for (int dsize = defaults.nodes.size(), n = 0; n < dsize; ++n) {
                final AttrImpl d = defaults.nodes.elementAt(n);
                final int j = this.findNamePoint(d.getNodeName(), 0);
                if (j < 0) {
                    final NodeImpl clone = (NodeImpl)d.cloneNode(true);
                    clone.ownerNode = super.ownerNode;
                    clone.isOwned(true);
                    clone.isSpecified(false);
                    super.nodes.setElementAt(clone, j);
                }
            }
        }
    }
}
