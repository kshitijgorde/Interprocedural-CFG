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
    
    public Node item(final int index) {
        return (this.nodes != null && index < this.nodes.size()) ? this.nodes.elementAt(index) : null;
    }
    
    public Node getNamedItem(final String name) {
        final int i = this.findNamePoint(name, 0);
        return (i < 0) ? null : this.nodes.elementAt(i);
    }
    
    public Node getNamedItemNS(final String namespaceURI, final String localName) {
        final int i = this.findNamePoint(namespaceURI, localName);
        return (i < 0) ? null : this.nodes.elementAt(i);
    }
    
    public Node setNamedItem(final Node arg) throws DOMException {
        if (this.isReadOnly()) {
            final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NO_MODIFICATION_ALLOWED_ERR", null);
            throw new DOMException((short)7, msg);
        }
        if (arg.getOwnerDocument() != this.ownerNode.ownerDocument()) {
            final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "WRONG_DOCUMENT_ERR", null);
            throw new DOMException((short)4, msg);
        }
        int i = this.findNamePoint(arg.getNodeName(), 0);
        NodeImpl previous = null;
        if (i >= 0) {
            previous = this.nodes.elementAt(i);
            this.nodes.setElementAt(arg, i);
        }
        else {
            i = -1 - i;
            if (null == this.nodes) {
                this.nodes = new Vector(5, 10);
            }
            this.nodes.insertElementAt(arg, i);
        }
        return previous;
    }
    
    public Node setNamedItemNS(final Node arg) throws DOMException {
        if (this.isReadOnly()) {
            final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NO_MODIFICATION_ALLOWED_ERR", null);
            throw new DOMException((short)7, msg);
        }
        if (arg.getOwnerDocument() != this.ownerNode.ownerDocument()) {
            final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "WRONG_DOCUMENT_ERR", null);
            throw new DOMException((short)4, msg);
        }
        int i = this.findNamePoint(arg.getNamespaceURI(), arg.getLocalName());
        NodeImpl previous = null;
        if (i >= 0) {
            previous = this.nodes.elementAt(i);
            this.nodes.setElementAt(arg, i);
        }
        else {
            i = this.findNamePoint(arg.getNodeName(), 0);
            if (i >= 0) {
                previous = this.nodes.elementAt(i);
                this.nodes.insertElementAt(arg, i);
            }
            else {
                i = -1 - i;
                if (null == this.nodes) {
                    this.nodes = new Vector(5, 10);
                }
                this.nodes.insertElementAt(arg, i);
            }
        }
        return previous;
    }
    
    public Node removeNamedItem(final String name) throws DOMException {
        if (this.isReadOnly()) {
            final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NO_MODIFICATION_ALLOWED_ERR", null);
            throw new DOMException((short)7, msg);
        }
        final int i = this.findNamePoint(name, 0);
        if (i < 0) {
            final String msg2 = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_FOUND_ERR", null);
            throw new DOMException((short)8, msg2);
        }
        final NodeImpl n = this.nodes.elementAt(i);
        this.nodes.removeElementAt(i);
        return n;
    }
    
    public Node removeNamedItemNS(final String namespaceURI, final String name) throws DOMException {
        if (this.isReadOnly()) {
            final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NO_MODIFICATION_ALLOWED_ERR", null);
            throw new DOMException((short)7, msg);
        }
        final int i = this.findNamePoint(namespaceURI, name);
        if (i < 0) {
            final String msg2 = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_FOUND_ERR", null);
            throw new DOMException((short)8, msg2);
        }
        final NodeImpl n = this.nodes.elementAt(i);
        this.nodes.removeElementAt(i);
        return n;
    }
    
    public NamedNodeMapImpl cloneMap(final NodeImpl ownerNode) {
        final NamedNodeMapImpl newmap = new NamedNodeMapImpl(ownerNode);
        newmap.cloneContent(this);
        return newmap;
    }
    
    protected void cloneContent(final NamedNodeMapImpl srcmap) {
        final Vector srcnodes = srcmap.nodes;
        if (srcnodes != null) {
            final int size = srcnodes.size();
            if (size != 0) {
                if (this.nodes == null) {
                    this.nodes = new Vector(size);
                }
                this.nodes.setSize(size);
                for (int i = 0; i < size; ++i) {
                    final NodeImpl n = srcmap.nodes.elementAt(i);
                    final NodeImpl clone = (NodeImpl)n.cloneNode(true);
                    clone.isSpecified(n.isSpecified());
                    this.nodes.setElementAt(clone, i);
                }
            }
        }
    }
    
    void setReadOnly(final boolean readOnly, final boolean deep) {
        this.isReadOnly(readOnly);
        if (deep && this.nodes != null) {
            for (int i = this.nodes.size() - 1; i >= 0; --i) {
                this.nodes.elementAt(i).setReadOnly(readOnly, deep);
            }
        }
    }
    
    boolean getReadOnly() {
        return this.isReadOnly();
    }
    
    void setOwnerDocument(final CoreDocumentImpl doc) {
        if (this.nodes != null) {
            for (int i = 0; i < this.nodes.size(); ++i) {
                ((NodeImpl)this.item(i)).setOwnerDocument(doc);
            }
        }
    }
    
    final boolean isReadOnly() {
        return (this.flags & 0x1) != 0x0;
    }
    
    final void isReadOnly(final boolean value) {
        this.flags = (value ? ((short)(this.flags | 0x1)) : ((short)(this.flags & 0xFFFFFFFE)));
    }
    
    final boolean changed() {
        return (this.flags & 0x2) != 0x0;
    }
    
    final void changed(final boolean value) {
        this.flags = (value ? ((short)(this.flags | 0x2)) : ((short)(this.flags & 0xFFFFFFFD)));
    }
    
    final boolean hasDefaults() {
        return (this.flags & 0x4) != 0x0;
    }
    
    final void hasDefaults(final boolean value) {
        this.flags = (value ? ((short)(this.flags | 0x4)) : ((short)(this.flags & 0xFFFFFFFB)));
    }
    
    protected int findNamePoint(final String name, final int start) {
        int i = 0;
        if (this.nodes != null) {
            int first = start;
            int last = this.nodes.size() - 1;
            while (first <= last) {
                i = (first + last) / 2;
                final int test = name.compareTo(this.nodes.elementAt(i).getNodeName());
                if (test == 0) {
                    return i;
                }
                if (test < 0) {
                    last = i - 1;
                }
                else {
                    first = i + 1;
                }
            }
            if (first > i) {
                i = first;
            }
        }
        return -1 - i;
    }
    
    protected int findNamePoint(final String namespaceURI, final String name) {
        if (this.nodes == null) {
            return -1;
        }
        if (name == null) {
            return -1;
        }
        for (int i = 0; i < this.nodes.size(); ++i) {
            final NodeImpl a = this.nodes.elementAt(i);
            final String aNamespaceURI = a.getNamespaceURI();
            final String aLocalName = a.getLocalName();
            if (namespaceURI == null) {
                if (aNamespaceURI == null && (name.equals(aLocalName) || (aLocalName == null && name.equals(a.getNodeName())))) {
                    return i;
                }
            }
            else if (namespaceURI.equals(aNamespaceURI) && name.equals(aLocalName)) {
                return i;
            }
        }
        return -1;
    }
    
    protected void removeItem(final int index) {
        if (this.nodes != null && index < this.nodes.size()) {
            this.nodes.removeElementAt(index);
        }
    }
    
    protected Object getItem(final int index) {
        if (this.nodes != null) {
            return this.nodes.elementAt(index);
        }
        return null;
    }
    
    protected int addItem(final Node arg) {
        int i = this.findNamePoint(arg.getNamespaceURI(), arg.getLocalName());
        NodeImpl previous = null;
        if (i >= 0) {
            previous = this.nodes.elementAt(i);
            this.nodes.setElementAt(arg, i);
        }
        else {
            i = this.findNamePoint(arg.getNodeName(), 0);
            if (i >= 0) {
                previous = this.nodes.elementAt(i);
                this.nodes.insertElementAt(arg, i);
            }
            else {
                i = -1 - i;
                if (null == this.nodes) {
                    this.nodes = new Vector(5, 10);
                }
                this.nodes.insertElementAt(arg, i);
            }
        }
        return i;
    }
    
    protected Vector cloneMap(Vector list) {
        if (list == null) {
            list = new Vector<Object>(5, 10);
        }
        list.setSize(0);
        if (this.nodes != null) {
            for (int i = 0; i < this.nodes.size(); ++i) {
                list.insertElementAt(this.nodes.elementAt(i), i);
            }
        }
        return list;
    }
    
    public void removeAll() {
        if (this.nodes != null) {
            this.nodes.removeAllElements();
        }
    }
}
