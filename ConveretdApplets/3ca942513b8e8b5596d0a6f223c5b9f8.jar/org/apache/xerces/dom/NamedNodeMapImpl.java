// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import java.util.Vector;
import java.io.Serializable;
import org.w3c.dom.NamedNodeMap;

public class NamedNodeMapImpl implements NamedNodeMap, Serializable
{
    static final long serialVersionUID = -7039242451046758020L;
    protected short flags;
    protected static final short READONLY = 1;
    protected static final short CHANGED = 2;
    protected static final short HASDEFAULTS = 4;
    protected Vector nodes;
    protected NodeImpl ownerNode;
    
    protected NamedNodeMapImpl(final NodeImpl ownerNode) {
        this.ownerNode = ownerNode;
    }
    
    public int getLength() {
        return (this.nodes != null) ? this.nodes.size() : 0;
    }
    
    public Node item(final int n) {
        return (this.nodes != null && n < this.nodes.size()) ? this.nodes.elementAt(n) : null;
    }
    
    public Node getNamedItem(final String s) {
        final int namePoint = this.findNamePoint(s, 0);
        return (namePoint < 0) ? null : ((Node)this.nodes.elementAt(namePoint));
    }
    
    public Node getNamedItemNS(final String s, final String s2) {
        final int namePoint = this.findNamePoint(s, s2);
        return (namePoint < 0) ? null : ((Node)this.nodes.elementAt(namePoint));
    }
    
    public Node setNamedItem(final Node node) throws DOMException {
        final CoreDocumentImpl ownerDocument = this.ownerNode.ownerDocument();
        if (ownerDocument.errorChecking) {
            if (this.isReadOnly()) {
                throw new DOMException((short)7, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NO_MODIFICATION_ALLOWED_ERR", null));
            }
            if (node.getOwnerDocument() != ownerDocument) {
                throw new DOMException((short)4, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "WRONG_DOCUMENT_ERR", null));
            }
        }
        final int namePoint = this.findNamePoint(node.getNodeName(), 0);
        Node node2 = null;
        if (namePoint >= 0) {
            node2 = this.nodes.elementAt(namePoint);
            this.nodes.setElementAt(node, namePoint);
        }
        else {
            final int n = -1 - namePoint;
            if (null == this.nodes) {
                this.nodes = new Vector(5, 10);
            }
            this.nodes.insertElementAt(node, n);
        }
        return node2;
    }
    
    public Node setNamedItemNS(final Node node) throws DOMException {
        final CoreDocumentImpl ownerDocument = this.ownerNode.ownerDocument();
        if (ownerDocument.errorChecking) {
            if (this.isReadOnly()) {
                throw new DOMException((short)7, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NO_MODIFICATION_ALLOWED_ERR", null));
            }
            if (node.getOwnerDocument() != ownerDocument) {
                throw new DOMException((short)4, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "WRONG_DOCUMENT_ERR", null));
            }
        }
        final int namePoint = this.findNamePoint(node.getNamespaceURI(), node.getLocalName());
        Node node2 = null;
        if (namePoint >= 0) {
            node2 = this.nodes.elementAt(namePoint);
            this.nodes.setElementAt(node, namePoint);
        }
        else {
            final int namePoint2 = this.findNamePoint(node.getNodeName(), 0);
            if (namePoint2 >= 0) {
                node2 = (NodeImpl)this.nodes.elementAt(namePoint2);
                this.nodes.insertElementAt(node, namePoint2);
            }
            else {
                final int n = -1 - namePoint2;
                if (null == this.nodes) {
                    this.nodes = new Vector(5, 10);
                }
                this.nodes.insertElementAt(node, n);
            }
        }
        return node2;
    }
    
    public Node removeNamedItem(final String s) throws DOMException {
        if (this.isReadOnly()) {
            throw new DOMException((short)7, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NO_MODIFICATION_ALLOWED_ERR", null));
        }
        final int namePoint = this.findNamePoint(s, 0);
        if (namePoint < 0) {
            throw new DOMException((short)8, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_FOUND_ERR", null));
        }
        final NodeImpl nodeImpl = this.nodes.elementAt(namePoint);
        this.nodes.removeElementAt(namePoint);
        return nodeImpl;
    }
    
    public Node removeNamedItemNS(final String s, final String s2) throws DOMException {
        if (this.isReadOnly()) {
            throw new DOMException((short)7, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NO_MODIFICATION_ALLOWED_ERR", null));
        }
        final int namePoint = this.findNamePoint(s, s2);
        if (namePoint < 0) {
            throw new DOMException((short)8, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_FOUND_ERR", null));
        }
        final NodeImpl nodeImpl = this.nodes.elementAt(namePoint);
        this.nodes.removeElementAt(namePoint);
        return nodeImpl;
    }
    
    public NamedNodeMapImpl cloneMap(final NodeImpl nodeImpl) {
        final NamedNodeMapImpl namedNodeMapImpl = new NamedNodeMapImpl(nodeImpl);
        namedNodeMapImpl.cloneContent(this);
        return namedNodeMapImpl;
    }
    
    protected void cloneContent(final NamedNodeMapImpl namedNodeMapImpl) {
        final Vector nodes = namedNodeMapImpl.nodes;
        if (nodes != null) {
            final int size = nodes.size();
            if (size != 0) {
                if (this.nodes == null) {
                    this.nodes = new Vector(size);
                }
                this.nodes.setSize(size);
                for (int i = 0; i < size; ++i) {
                    final NodeImpl nodeImpl = namedNodeMapImpl.nodes.elementAt(i);
                    final NodeImpl nodeImpl2 = (NodeImpl)nodeImpl.cloneNode(true);
                    nodeImpl2.isSpecified(nodeImpl.isSpecified());
                    this.nodes.setElementAt(nodeImpl2, i);
                }
            }
        }
    }
    
    void setReadOnly(final boolean b, final boolean b2) {
        this.isReadOnly(b);
        if (b2 && this.nodes != null) {
            for (int i = this.nodes.size() - 1; i >= 0; --i) {
                ((NodeImpl)this.nodes.elementAt(i)).setReadOnly(b, b2);
            }
        }
    }
    
    boolean getReadOnly() {
        return this.isReadOnly();
    }
    
    void setOwnerDocument(final CoreDocumentImpl ownerDocument) {
        if (this.nodes != null) {
            for (int i = 0; i < this.nodes.size(); ++i) {
                ((NodeImpl)this.item(i)).setOwnerDocument(ownerDocument);
            }
        }
    }
    
    final boolean isReadOnly() {
        return (this.flags & 0x1) != 0x0;
    }
    
    final void isReadOnly(final boolean b) {
        this.flags = (b ? ((short)(this.flags | 0x1)) : ((short)(this.flags & 0xFFFFFFFE)));
    }
    
    final boolean changed() {
        return (this.flags & 0x2) != 0x0;
    }
    
    final void changed(final boolean b) {
        this.flags = (b ? ((short)(this.flags | 0x2)) : ((short)(this.flags & 0xFFFFFFFD)));
    }
    
    final boolean hasDefaults() {
        return (this.flags & 0x4) != 0x0;
    }
    
    final void hasDefaults(final boolean b) {
        this.flags = (b ? ((short)(this.flags | 0x4)) : ((short)(this.flags & 0xFFFFFFFB)));
    }
    
    protected int findNamePoint(final String s, final int n) {
        int n2 = 0;
        if (this.nodes != null) {
            int i = n;
            int n3 = this.nodes.size() - 1;
            while (i <= n3) {
                n2 = (i + n3) / 2;
                final int compareTo = s.compareTo(this.nodes.elementAt(n2).getNodeName());
                if (compareTo == 0) {
                    return n2;
                }
                if (compareTo < 0) {
                    n3 = n2 - 1;
                }
                else {
                    i = n2 + 1;
                }
            }
            if (i > n2) {
                n2 = i;
            }
        }
        return -1 - n2;
    }
    
    protected int findNamePoint(final String s, final String s2) {
        if (this.nodes == null) {
            return -1;
        }
        if (s2 == null) {
            return -1;
        }
        for (int i = 0; i < this.nodes.size(); ++i) {
            final NodeImpl nodeImpl = this.nodes.elementAt(i);
            final String namespaceURI = nodeImpl.getNamespaceURI();
            final String localName = nodeImpl.getLocalName();
            if (s == null) {
                if (namespaceURI == null && (s2.equals(localName) || (localName == null && s2.equals(nodeImpl.getNodeName())))) {
                    return i;
                }
            }
            else if (s.equals(namespaceURI) && s2.equals(localName)) {
                return i;
            }
        }
        return -1;
    }
    
    protected boolean precedes(final Node node, final Node node2) {
        if (this.nodes != null) {
            for (int i = 0; i < this.nodes.size(); ++i) {
                final Node node3 = this.nodes.elementAt(i);
                if (node3 == node) {
                    return true;
                }
                if (node3 == node2) {
                    return false;
                }
            }
        }
        return false;
    }
    
    protected void removeItem(final int n) {
        if (this.nodes != null && n < this.nodes.size()) {
            this.nodes.removeElementAt(n);
        }
    }
    
    protected Object getItem(final int n) {
        if (this.nodes != null) {
            return this.nodes.elementAt(n);
        }
        return null;
    }
    
    protected int addItem(final Node node) {
        int n = this.findNamePoint(node.getNamespaceURI(), node.getLocalName());
        if (n >= 0) {
            final NodeImpl nodeImpl = this.nodes.elementAt(n);
            this.nodes.setElementAt(node, n);
        }
        else {
            n = this.findNamePoint(node.getNodeName(), 0);
            if (n >= 0) {
                final NodeImpl nodeImpl2 = this.nodes.elementAt(n);
                this.nodes.insertElementAt(node, n);
            }
            else {
                n = -1 - n;
                if (null == this.nodes) {
                    this.nodes = new Vector(5, 10);
                }
                this.nodes.insertElementAt(node, n);
            }
        }
        return n;
    }
    
    protected Vector cloneMap(Vector vector) {
        if (vector == null) {
            vector = new Vector<Object>(5, 10);
        }
        vector.setSize(0);
        if (this.nodes != null) {
            for (int i = 0; i < this.nodes.size(); ++i) {
                vector.insertElementAt(this.nodes.elementAt(i), i);
            }
        }
        return vector;
    }
    
    protected int getNamedItemIndex(final String s, final String s2) {
        return this.findNamePoint(s, s2);
    }
    
    public void removeAll() {
        if (this.nodes != null) {
            this.nodes.removeAllElements();
        }
    }
}
