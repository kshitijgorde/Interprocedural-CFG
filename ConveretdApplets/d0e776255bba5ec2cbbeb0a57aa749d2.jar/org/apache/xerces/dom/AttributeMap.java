// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.w3c.dom.events.Event;
import org.apache.xerces.dom.events.MutationEventImpl;
import java.util.Vector;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;

public class AttributeMap extends NamedNodeMapImpl
{
    static final boolean DEBUG = false;
    
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
        if (this.isReadOnly()) {
            throw new DOMException((short)7, "DOM001 Modification not allowed");
        }
        if (node.getOwnerDocument() != super.ownerNode.ownerDocument()) {
            throw new DOMException((short)4, "DOM005 Wrong document");
        }
        if (node.getNodeType() != 2) {
            throw new DOMException((short)3, "DOM006 Hierarchy request error");
        }
        final NodeImpl nodeImpl = (NodeImpl)node;
        if (nodeImpl.isOwned()) {
            throw new DOMException((short)10, "DOM009 Attribute already in use");
        }
        nodeImpl.ownerNode = super.ownerNode;
        nodeImpl.isOwned(true);
        final int namePoint = this.findNamePoint(node.getNodeName(), 0);
        NodeImpl nodeImpl2 = null;
        if (namePoint >= 0) {
            nodeImpl2 = super.nodes.elementAt(namePoint);
            super.nodes.setElementAt(node, namePoint);
            nodeImpl2.ownerNode = super.ownerNode.ownerDocument();
            nodeImpl2.isOwned(false);
            nodeImpl2.isSpecified(true);
        }
        else {
            final int n = -1 - namePoint;
            if (null == super.nodes) {
                super.nodes = new Vector(5, 10);
            }
            super.nodes.insertElementAt(node, n);
        }
        if (super.ownerNode.ownerDocument().mutationEvents) {
            super.ownerNode.dispatchAggregateEvents((AttrImpl)node, (nodeImpl2 == null) ? null : nodeImpl2.getNodeValue(), (short)((nodeImpl2 == null) ? 2 : 1));
        }
        if (!nodeImpl.isNormalized()) {
            super.ownerNode.isNormalized(false);
        }
        return nodeImpl2;
    }
    
    public Node setNamedItemNS(final Node node) throws DOMException {
        if (this.isReadOnly()) {
            throw new DOMException((short)7, "DOM001 Modification not allowed");
        }
        if (node.getOwnerDocument() != super.ownerNode.ownerDocument()) {
            throw new DOMException((short)4, "DOM005 Wrong document");
        }
        if (node.getNodeType() != 2) {
            throw new DOMException((short)3, "DOM006 Hierarchy request error");
        }
        final NodeImpl nodeImpl = (NodeImpl)node;
        if (nodeImpl.isOwned()) {
            throw new DOMException((short)10, "DOM009 Attribute already in use");
        }
        nodeImpl.ownerNode = super.ownerNode;
        nodeImpl.isOwned(true);
        final int namePoint = this.findNamePoint(nodeImpl.getNamespaceURI(), nodeImpl.getLocalName());
        NodeImpl nodeImpl2 = null;
        if (namePoint >= 0) {
            nodeImpl2 = super.nodes.elementAt(namePoint);
            super.nodes.setElementAt(node, namePoint);
            nodeImpl2.ownerNode = super.ownerNode.ownerDocument();
            nodeImpl2.isOwned(false);
            nodeImpl2.isSpecified(true);
        }
        else {
            final int namePoint2 = this.findNamePoint(node.getNodeName(), 0);
            if (namePoint2 >= 0) {
                nodeImpl2 = (NodeImpl)super.nodes.elementAt(namePoint2);
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
        if (super.ownerNode.ownerDocument().mutationEvents) {
            super.ownerNode.dispatchAggregateEvents((AttrImpl)node, (nodeImpl2 == null) ? null : nodeImpl2.getNodeValue(), (short)((nodeImpl2 == null) ? 2 : 1));
        }
        if (!nodeImpl.isNormalized()) {
            super.ownerNode.isNormalized(false);
        }
        return nodeImpl2;
    }
    
    public Node removeNamedItem(final String s) throws DOMException {
        return this.internalRemoveNamedItem(s, true);
    }
    
    Node safeRemoveNamedItem(final String s) {
        return this.internalRemoveNamedItem(s, false);
    }
    
    protected final Node internalRemoveNamedItem(final String s, final boolean b) {
        if (this.isReadOnly()) {
            throw new DOMException((short)7, "DOM001 Modification not allowed");
        }
        final int namePoint = this.findNamePoint(s, 0);
        if (namePoint >= 0) {
            LCount lookup = null;
            if (super.ownerNode.ownerDocument().mutationEvents) {
                lookup = LCount.lookup("DOMAttrModified");
                if (lookup.captures + lookup.bubbles + lookup.defaults > 0) {
                    ((AttrImpl)super.nodes.elementAt(namePoint)).getNodeValue();
                }
            }
            final NodeImpl nodeImpl = super.nodes.elementAt(namePoint);
            if (this.hasDefaults()) {
                final NamedNodeMapImpl defaultAttributes = ((ElementImpl)super.ownerNode).getDefaultAttributes();
                final Node namedItem;
                if (defaultAttributes != null && (namedItem = defaultAttributes.getNamedItem(s)) != null && this.findNamePoint(s, namePoint + 1) < 0) {
                    final NodeImpl nodeImpl2 = (NodeImpl)namedItem.cloneNode(true);
                    nodeImpl2.ownerNode = super.ownerNode;
                    nodeImpl2.isOwned(true);
                    nodeImpl2.isSpecified(false);
                    super.nodes.setElementAt(nodeImpl2, namePoint);
                }
                else {
                    super.nodes.removeElementAt(namePoint);
                }
            }
            else {
                super.nodes.removeElementAt(namePoint);
            }
            nodeImpl.ownerNode = super.ownerNode.ownerDocument();
            nodeImpl.isOwned(false);
            nodeImpl.isSpecified(true);
            if (super.ownerNode.ownerDocument().mutationEvents) {
                if (lookup.captures + lookup.bubbles + lookup.defaults > 0) {
                    final MutationEventImpl mutationEventImpl = new MutationEventImpl();
                    mutationEventImpl.initMutationEvent("DOMAttrModified", true, false, null, nodeImpl.getNodeValue(), null, s, (short)3);
                    super.ownerNode.dispatchEvent(mutationEventImpl);
                }
                super.ownerNode.dispatchAggregateEvents(null, null, (short)0);
            }
            return nodeImpl;
        }
        if (b) {
            throw new DOMException((short)8, "DOM008 Not found");
        }
        return null;
    }
    
    public Node removeNamedItemNS(final String s, final String s2) throws DOMException {
        return this.internalRemoveNamedItemNS(s, s2, true);
    }
    
    Node safeRemoveNamedItemNS(final String s, final String s2) {
        return this.internalRemoveNamedItemNS(s, s2, false);
    }
    
    protected final Node internalRemoveNamedItemNS(final String s, final String s2, final boolean b) {
        if (this.isReadOnly()) {
            throw new DOMException((short)7, "DOM001 Modification not allowed");
        }
        final int namePoint = this.findNamePoint(s, s2);
        if (namePoint >= 0) {
            LCount lookup = null;
            if (super.ownerNode.ownerDocument().mutationEvents) {
                lookup = LCount.lookup("DOMAttrModified");
                if (lookup.captures + lookup.bubbles + lookup.defaults > 0) {
                    ((AttrImpl)super.nodes.elementAt(namePoint)).getNodeValue();
                }
            }
            final NodeImpl nodeImpl = super.nodes.elementAt(namePoint);
            final String nodeName = nodeImpl.getNodeName();
            if (this.hasDefaults()) {
                final NamedNodeMapImpl defaultAttributes = ((ElementImpl)super.ownerNode).getDefaultAttributes();
                final Node namedItem;
                if (defaultAttributes != null && (namedItem = defaultAttributes.getNamedItem(nodeName)) != null) {
                    final int namePoint2 = this.findNamePoint(nodeName, 0);
                    if (namePoint2 >= 0 && this.findNamePoint(nodeName, namePoint2 + 1) < 0) {
                        final NodeImpl nodeImpl2 = (NodeImpl)namedItem.cloneNode(true);
                        nodeImpl2.ownerNode = super.ownerNode;
                        nodeImpl2.isOwned(true);
                        nodeImpl2.isSpecified(false);
                        super.nodes.setElementAt(nodeImpl2, namePoint);
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
            nodeImpl.ownerNode = super.ownerNode.ownerDocument();
            nodeImpl.isOwned(false);
            nodeImpl.isSpecified(true);
            if (super.ownerNode.ownerDocument().mutationEvents) {
                if (lookup.captures + lookup.bubbles + lookup.defaults > 0) {
                    final MutationEventImpl mutationEventImpl = new MutationEventImpl();
                    mutationEventImpl.initMutationEvent("DOMAttrModified", true, false, null, nodeImpl.getNodeValue(), null, s2, (short)3);
                    super.ownerNode.dispatchEvent(mutationEventImpl);
                }
                super.ownerNode.dispatchAggregateEvents(null, null, (short)0);
            }
            return nodeImpl;
        }
        if (b) {
            throw new DOMException((short)8, "DOM008 Not found");
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
        if (namedNodeMapImpl.nodes != null) {
            if (super.nodes == null) {
                super.nodes = new Vector(namedNodeMapImpl.nodes.size());
            }
            else {
                super.nodes.setSize(namedNodeMapImpl.nodes.size());
            }
            for (int i = 0; i < namedNodeMapImpl.nodes.size(); ++i) {
                final NodeImpl nodeImpl = namedNodeMapImpl.nodes.elementAt(i);
                final NodeImpl nodeImpl2 = (NodeImpl)nodeImpl.cloneNode(true);
                nodeImpl2.isSpecified(nodeImpl.isSpecified());
                super.nodes.insertElementAt(nodeImpl2, i);
                nodeImpl2.ownerNode = super.ownerNode;
                nodeImpl2.isOwned(true);
            }
        }
    }
    
    protected void reconcileDefaults(final NamedNodeMapImpl namedNodeMapImpl) {
        for (int i = ((super.nodes != null) ? super.nodes.size() : 0) - 1; i >= 0; --i) {
            final AttrImpl attrImpl = super.nodes.elementAt(i);
            if (!attrImpl.isSpecified()) {
                attrImpl.ownerNode = super.ownerNode.ownerDocument();
                attrImpl.isOwned(false);
                attrImpl.isSpecified(true);
                super.nodes.removeElementAt(i);
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
                    final NodeImpl nodeImpl = (NodeImpl)attrImpl2.cloneNode(true);
                    nodeImpl.ownerNode = super.ownerNode;
                    nodeImpl.isOwned(true);
                    nodeImpl.isSpecified(false);
                    super.nodes.setElementAt(nodeImpl, namePoint);
                }
            }
        }
    }
}
