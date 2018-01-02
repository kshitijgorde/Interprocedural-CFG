// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import java.util.Enumeration;
import org.apache.xerces.domx.events.Event;
import org.apache.xerces.dom.events.MutationEventImpl;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.w3c.dom.Document;
import java.util.Vector;
import java.io.Serializable;
import org.w3c.dom.NamedNodeMap;

public class NamedNodeMapImpl implements NamedNodeMap, Serializable
{
    static final long serialVersionUID = -7039242451046758020L;
    protected Vector nodes;
    protected Document ownerDocument;
    protected ElementImpl element;
    protected NamedNodeMapImpl defaults;
    protected int changes;
    protected int lastDefaultsChanges;
    protected boolean readOnly;
    
    protected NamedNodeMapImpl(final Document ownerDocument, final NamedNodeMapImpl defaults) {
        this.nodes = new Vector();
        this.lastDefaultsChanges = -1;
        this.readOnly = false;
        this.ownerDocument = ownerDocument;
        this.defaults = defaults;
    }
    
    protected NamedNodeMapImpl(final ElementImpl element, final NamedNodeMapImpl namedNodeMapImpl) {
        this(element.getOwnerDocument(), namedNodeMapImpl);
        this.element = element;
    }
    
    public int getLength() {
        this.reconcileDefaults();
        if (this.nodes != null) {
            return this.nodes.size();
        }
        return 0;
    }
    
    public Node item(final int n) {
        this.reconcileDefaults();
        if (this.nodes != null && n < this.nodes.size()) {
            return this.nodes.elementAt(n);
        }
        return null;
    }
    
    public Node getNamedItem(final String s) {
        final int namePoint = this.findNamePoint(s);
        if (namePoint < 0) {
            return null;
        }
        return (Node)this.nodes.elementAt(namePoint);
    }
    
    public Node getNamedItemNS(final String s, final String s2) {
        final int namePoint = this.findNamePoint(s, s2);
        if (namePoint < 0) {
            return null;
        }
        return (Node)this.nodes.elementAt(namePoint);
    }
    
    public Node setNamedItem(final Node node) throws DOMException {
        if (node.getOwnerDocument() != this.ownerDocument) {
            throw new DOMExceptionImpl((short)4, "WRONG_DOCUMENT_ERR");
        }
        if (node instanceof AttrImpl && ((AttrImpl)node).owned) {
            throw new DOMExceptionImpl((short)10, "INUSE_ATTRIBUTE_ERR");
        }
        final NodeImpl nodeImpl = (NodeImpl)node;
        final int namePoint = this.findNamePoint(node.getNodeName());
        Node node2 = null;
        if (namePoint >= 0) {
            node2 = this.nodes.elementAt(namePoint);
            if (this.element != null) {
                ((NodeImpl)node).parentNode = this.element;
            }
            this.nodes.setElementAt(node, namePoint);
        }
        else {
            final int n = -1 - namePoint;
            if (this.nodes == null) {
                this.nodes = new Vector();
            }
            if (this.element != null) {
                ((NodeImpl)node).parentNode = this.element;
            }
            this.nodes.insertElementAt(node, n);
        }
        if (this.element != null) {
            ((NodeImpl)node).parentNode = this.element;
        }
        ++this.changes;
        if (this.element != null) {
            this.element.dispatchAggregateEvents((AttrImpl)node, (node2 == null) ? null : node2.getNodeValue());
        }
        return node2;
    }
    
    public Node removeNamedItem(final String s) {
        return this.removeNamedItemNS(null, s);
    }
    
    public Node removeNamedItemNS(final String s, final String s2) throws DOMException {
        final int namePoint = this.findNamePoint(s, s2);
        if (namePoint < 0) {
            throw new DOMExceptionImpl((short)8, "NOT_FOUND_ERR");
        }
        LCount lookup = null;
        if (this.element != null) {
            lookup = LCount.lookup("DOMAttrModified");
            if (lookup.captures + lookup.bubbles + lookup.defaults > 0) {
                ((AttrImpl)this.nodes.elementAt(namePoint)).getNodeValue();
            }
        }
        final Node node = this.nodes.elementAt(namePoint);
        final Node namedItemNS;
        if (this.defaults != null && (namedItemNS = this.defaults.getNamedItemNS(s, s2)) != null) {
            final NodeImpl nodeImpl = (NodeImpl)namedItemNS.cloneNode(true);
            nodeImpl.parentNode = this.element;
            this.nodes.setElementAt(nodeImpl, namePoint);
        }
        else {
            this.nodes.removeElementAt(namePoint);
        }
        if (this.element != null) {
            final AttrImpl attrImpl = (AttrImpl)node;
            attrImpl.parentNode = null;
            attrImpl.owned = false;
        }
        ++this.changes;
        if (this.element != null) {
            if (lookup.captures + lookup.bubbles + lookup.defaults > 0) {
                final MutationEventImpl mutationEventImpl = new MutationEventImpl();
                mutationEventImpl.initMutationEvent("DOMAttrModified", true, false, null, node.getNodeValue(), this.element.getAttribute(s2), s2);
                this.element.dispatchEvent(mutationEventImpl);
            }
            this.element.dispatchAggregateEvents(null, null);
        }
        return node;
    }
    
    public NamedNodeMapImpl cloneMap() {
        final boolean b = true;
        final NamedNodeMapImpl namedNodeMapImpl = new NamedNodeMapImpl(this.ownerDocument, this.defaults);
        if (this.nodes != null) {
            namedNodeMapImpl.nodes = new Vector(this.nodes.size());
            for (int i = 0; i < this.nodes.size(); ++i) {
                final NodeImpl nodeImpl = (NodeImpl)this.nodes.elementAt(i).cloneNode(b);
                nodeImpl.parentNode = this.element;
                namedNodeMapImpl.nodes.addElement(nodeImpl);
            }
        }
        namedNodeMapImpl.defaults = this.defaults;
        namedNodeMapImpl.lastDefaultsChanges = this.lastDefaultsChanges;
        return namedNodeMapImpl;
    }
    
    void setReadOnly(final boolean readOnly, final boolean b) {
        this.readOnly = readOnly;
        if (b && this.nodes != null) {
            final Enumeration<NodeImpl> elements = this.nodes.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().setReadOnly(readOnly, b);
            }
        }
    }
    
    protected void reconcileDefaults() {
        if (this.defaults != null && this.lastDefaultsChanges != this.defaults.changes) {
            int i = 0;
            int j = 0;
            final int size = this.nodes.size();
            final int size2 = this.defaults.nodes.size();
            final AttrImpl attrImpl = (size == 0) ? null : this.nodes.elementAt(0);
            final AttrImpl attrImpl2 = (size2 == 0) ? null : this.defaults.nodes.elementAt(0);
            while (i < size) {
                if (j >= size2) {
                    break;
                }
                final AttrImpl attrImpl3 = this.nodes.elementAt(i);
                final AttrImpl attrImpl4 = this.defaults.nodes.elementAt(j);
                final String namespaceURI = attrImpl3.getNamespaceURI();
                final String namespaceURI2 = attrImpl4.getNamespaceURI();
                int compareTo;
                if (namespaceURI == null) {
                    if (namespaceURI2 == null) {
                        compareTo = 0;
                    }
                    else {
                        compareTo = 1;
                    }
                }
                else if (namespaceURI2 == null) {
                    compareTo = -1;
                }
                else {
                    compareTo = namespaceURI.compareTo(namespaceURI2);
                }
                System.out.println("n,d" + attrImpl3.getLocalName() + "," + attrImpl4.getLocalName());
                final int compareTo2 = attrImpl3.getLocalName().compareTo(attrImpl4.getLocalName());
                final int n = (compareTo != 0) ? compareTo : compareTo2;
                if (n == 0 && !attrImpl3.getSpecified()) {
                    final NodeImpl nodeImpl = (NodeImpl)attrImpl4.cloneNode(true);
                    nodeImpl.parentNode = this.element;
                    this.nodes.setElementAt(nodeImpl, i);
                    ++i;
                    ++j;
                }
                else if (n > 0) {
                    final NodeImpl nodeImpl2 = (NodeImpl)attrImpl4.cloneNode(true);
                    nodeImpl2.parentNode = this.element;
                    this.nodes.insertElementAt(nodeImpl2, i);
                    ++i;
                    ++j;
                }
                else if (!attrImpl3.getSpecified()) {
                    this.nodes.removeElementAt(i);
                }
                else {
                    ++i;
                }
            }
            while (j < size2) {
                final NodeImpl nodeImpl3 = (NodeImpl)this.defaults.nodes.elementAt(j++).cloneNode(true);
                nodeImpl3.parentNode = this.element;
                this.nodes.addElement(nodeImpl3);
            }
            this.lastDefaultsChanges = this.defaults.changes;
        }
    }
    
    private int findNamePoint(final String s) {
        return this.findNamePoint(null, s);
    }
    
    private int findNamePoint(final String s, final String s2) {
        this.reconcileDefaults();
        int n = 0;
        if (this.nodes != null) {
            int i = 0;
            int n2 = this.nodes.size() - 1;
            while (i <= n2) {
                n = (i + n2) / 2;
                final String namespaceURI = this.nodes.elementAt(n).getNamespaceURI();
                int compareTo;
                if (s == null) {
                    if (namespaceURI == null) {
                        compareTo = 0;
                    }
                    else {
                        compareTo = 1;
                    }
                }
                else if (namespaceURI == null) {
                    compareTo = -1;
                }
                else {
                    compareTo = s.compareTo(namespaceURI);
                }
                if (((NodeImpl)this.nodes.elementAt(n)).getLocalName() == null) {
                    final int compareTo2 = -1;
                }
                else {
                    final int compareTo2 = s2.compareTo(this.nodes.elementAt(n).getLocalName());
                }
                int compareTo2;
                final int n3 = (compareTo != 0) ? compareTo : compareTo2;
                if (n3 == 0) {
                    return n;
                }
                if (n3 < 0) {
                    n2 = n - 1;
                }
                else {
                    i = n + 1;
                }
            }
            if (i > n) {
                n = i;
            }
        }
        return -1 - n;
    }
}
