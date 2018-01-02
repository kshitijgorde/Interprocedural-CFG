// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import java.io.IOException;
import java.io.ObjectOutputStream;
import org.w3c.dom.EntityReference;
import org.apache.xerces.dom.events.EventImpl;
import org.apache.xerces.domx.events.EventListener;
import java.util.Enumeration;
import org.apache.xerces.dom.traversal.NodeIteratorImpl;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Document;
import org.w3c.dom.DOMException;
import org.apache.xerces.domx.events.Event;
import org.apache.xerces.dom.events.MutationEventImpl;
import java.util.Vector;
import java.io.Serializable;
import org.apache.xerces.domx.events.EventTarget;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

public abstract class NodeImpl implements Node, NodeList, EventTarget, Cloneable, Serializable
{
    static final long serialVersionUID = 2815829867052120872L;
    public static final short ELEMENT_DEFINITION_NODE = -1;
    protected DocumentImpl ownerDocument;
    protected NodeImpl parentNode;
    protected NodeImpl previousSibling;
    protected NodeImpl nextSibling;
    protected String name;
    protected String value;
    protected boolean readOnly;
    protected Object userData;
    protected NodeImpl firstChild;
    protected NodeImpl lastChild;
    protected transient boolean syncChildren;
    protected transient boolean syncData;
    int changes;
    protected static int[] kidOK;
    protected static final boolean MUTATIONEVENTS = true;
    protected static final int MUTATION_NONE = 0;
    protected static final int MUTATION_LOCAL = 1;
    protected static final int MUTATION_AGGREGATE = 2;
    protected static final int MUTATION_ALL = 65535;
    Vector nodeListeners;
    
    protected NodeImpl(final DocumentImpl ownerDocument, final String name, final String value) {
        this.ownerDocument = ownerDocument;
        this.name = name;
        this.value = value;
    }
    
    public NodeImpl() {
    }
    
    public abstract short getNodeType();
    
    public String getNodeName() {
        if (this.syncData) {
            this.synchronizeData();
        }
        return this.name;
    }
    
    public void setNodeValue(final String value) {
        if (this.readOnly) {
            throw new DOMExceptionImpl((short)7, "NO_MODIFICATION_ALLOWED_ERR");
        }
        if (this.syncData) {
            this.synchronizeData();
        }
        final String value2 = this.value;
        EnclosingAttr enclosingAttr = null;
        final LCount lookup = LCount.lookup("DOMAttrModified");
        if (lookup.captures + lookup.bubbles + lookup.defaults > 0) {
            enclosingAttr = this.getEnclosingAttr();
        }
        this.value = value;
        final LCount lookup2 = LCount.lookup("DOMCharacterDataModified");
        if (lookup2.captures + lookup2.bubbles + lookup2.defaults > 0) {
            final MutationEventImpl mutationEventImpl = new MutationEventImpl();
            mutationEventImpl.initMutationEvent("DOMCharacterDataModified", true, false, null, value2, value, null);
            this.dispatchEvent(mutationEventImpl);
        }
        this.dispatchAggregateEvents(enclosingAttr);
    }
    
    public String getNodeValue() {
        if (this.syncData) {
            this.synchronizeData();
        }
        return this.value;
    }
    
    public Node appendChild(final Node node) throws DOMException {
        return this.insertBefore(node, null);
    }
    
    public Node cloneNode(final boolean b) {
        if (this.syncData) {
            this.synchronizeData();
        }
        if (this.syncChildren) {
            this.synchronizeChildren();
        }
        NodeImpl nodeImpl;
        try {
            nodeImpl = (NodeImpl)this.clone();
        }
        catch (CloneNotSupportedException ex) {
            return null;
        }
        nodeImpl.readOnly = false;
        nodeImpl.parentNode = null;
        nodeImpl.previousSibling = null;
        nodeImpl.nextSibling = null;
        nodeImpl.firstChild = null;
        nodeImpl.lastChild = null;
        if (b) {
            for (NodeImpl nodeImpl2 = (NodeImpl)this.getFirstChild(); nodeImpl2 != null; nodeImpl2 = (NodeImpl)nodeImpl2.getNextSibling()) {
                nodeImpl.appendChild(nodeImpl2.cloneNode(true));
            }
        }
        return nodeImpl;
    }
    
    public Document getOwnerDocument() {
        return this.ownerDocument;
    }
    
    public Node getParentNode() {
        return this.parentNode;
    }
    
    public Node getNextSibling() {
        return this.nextSibling;
    }
    
    public Node getPreviousSibling() {
        return this.previousSibling;
    }
    
    public NamedNodeMap getAttributes() {
        return null;
    }
    
    public boolean hasChildNodes() {
        if (this.syncChildren) {
            this.synchronizeChildren();
        }
        return this.firstChild != null;
    }
    
    public NodeList getChildNodes() {
        if (this.syncChildren) {
            this.synchronizeChildren();
        }
        return this;
    }
    
    public Node getFirstChild() {
        if (this.syncChildren) {
            this.synchronizeChildren();
        }
        return this.firstChild;
    }
    
    public Node getLastChild() {
        if (this.syncChildren) {
            this.synchronizeChildren();
        }
        return this.lastChild;
    }
    
    public Node insertBefore(final Node node, final Node node2) throws DOMException {
        return this.internalInsertBefore(node, node2, 65535);
    }
    
    Node internalInsertBefore(final Node node, final Node node2, final int n) throws DOMException {
        if (this.readOnly) {
            throw new DOMExceptionImpl((short)7, "NO_MODIFICATION_ALLOWED_ERR");
        }
        if (!(node instanceof NodeImpl) || (node.getOwnerDocument() != this.ownerDocument && (this.getNodeType() != 9 || node.getOwnerDocument() != (Document)this))) {
            throw new DOMExceptionImpl((short)4, "WRONG_DOCUMENT_ERR");
        }
        if (this.syncChildren) {
            this.synchronizeChildren();
        }
        final NodeImpl nodeImpl = (NodeImpl)node;
        boolean b = true;
        for (NodeImpl nodeImpl2 = this.parentNode; b && nodeImpl2 != null; b = (nodeImpl != nodeImpl2), nodeImpl2 = nodeImpl2.parentNode) {}
        if (!b) {
            throw new DOMExceptionImpl((short)3, "HIERARCHY_REQUEST_ERR");
        }
        if (node2 != null && node2.getParentNode() != this) {
            throw new DOMExceptionImpl((short)8, "NOT_FOUND_ERR");
        }
        if (nodeImpl.getNodeType() == 11) {
            for (Node node3 = nodeImpl.getFirstChild(); node3 != null; node3 = node3.getNextSibling()) {
                if (!this.ownerDocument.isKidOK(this, node3)) {
                    throw new DOMExceptionImpl((short)3, "HIERARCHY_REQUEST_ERR");
                }
            }
            while (nodeImpl.hasChildNodes()) {
                this.insertBefore(nodeImpl.getFirstChild(), node2);
            }
        }
        else {
            if (!this.ownerDocument.isKidOK(this, nodeImpl)) {
                throw new DOMExceptionImpl((short)3, "HIERARCHY_REQUEST_ERR");
            }
            EnclosingAttr enclosingAttr = null;
            if ((n & 0x2) != 0x0) {
                final LCount lookup = LCount.lookup("DOMAttrModified");
                if (lookup.captures + lookup.bubbles + lookup.defaults > 0) {
                    enclosingAttr = this.getEnclosingAttr();
                }
            }
            final Node parentNode = nodeImpl.getParentNode();
            if (parentNode != null) {
                parentNode.removeChild(nodeImpl);
            }
            final NodeImpl previousSibling = (node2 == null) ? this.lastChild : ((NodeImpl)node2).previousSibling;
            nodeImpl.parentNode = this;
            if ((nodeImpl.previousSibling = previousSibling) == null) {
                this.firstChild = nodeImpl;
            }
            else {
                previousSibling.nextSibling = nodeImpl;
            }
            nodeImpl.nextSibling = (NodeImpl)node2;
            if (node2 == null) {
                this.lastChild = nodeImpl;
            }
            else {
                ((NodeImpl)node2).previousSibling = nodeImpl;
            }
            this.changed();
            if ((n & 0x1) != 0x0) {
                final LCount lookup2 = LCount.lookup("DOMNodeInserted");
                if (lookup2.captures + lookup2.bubbles + lookup2.defaults > 0) {
                    final MutationEventImpl mutationEventImpl = new MutationEventImpl();
                    mutationEventImpl.initMutationEvent("DOMNodeInserted", true, false, this, null, null, null);
                    nodeImpl.dispatchEvent(mutationEventImpl);
                }
                final LCount lookup3 = LCount.lookup("DOMNodeInsertedIntoDocument");
                if (lookup3.captures + lookup3.bubbles + lookup3.defaults > 0) {
                    NodeImpl nodeImpl3 = this;
                    if (enclosingAttr != null) {
                        nodeImpl3 = (NodeImpl)enclosingAttr.node.getOwnerElement();
                    }
                    if (nodeImpl3 != null) {
                        NodeImpl parentNode2 = nodeImpl3;
                        while (parentNode2 != null) {
                            nodeImpl3 = parentNode2;
                            if (parentNode2.getNodeType() == 2) {
                                parentNode2 = (ElementImpl)((AttrImpl)parentNode2).getOwnerElement();
                            }
                            else {
                                parentNode2 = parentNode2.parentNode;
                            }
                        }
                        if (nodeImpl3.getNodeType() == 9) {
                            final MutationEventImpl mutationEventImpl2 = new MutationEventImpl();
                            mutationEventImpl2.initMutationEvent("DOMNodeInsertedIntoDocument", false, false, null, null, null, null);
                            this.dispatchEventToSubtree(nodeImpl, mutationEventImpl2);
                        }
                    }
                }
            }
            if ((n & 0x2) != 0x0) {
                this.dispatchAggregateEvents(enclosingAttr);
            }
        }
        return nodeImpl;
    }
    
    public Node removeChild(final Node node) throws DOMException {
        return this.internalRemoveChild(node, 65535);
    }
    
    Node internalRemoveChild(final Node node, final int n) throws DOMException {
        if (this.readOnly) {
            throw new DOMExceptionImpl((short)7, "NO_MODIFICATION_ALLOWED_ERR");
        }
        if (node != null && node.getParentNode() != this) {
            throw new DOMExceptionImpl((short)8, "NOT_FOUND_ERR");
        }
        final Enumeration nodeIterators = this.ownerDocument.getNodeIterators();
        if (nodeIterators != null) {
            while (nodeIterators.hasMoreElements()) {
                nodeIterators.nextElement().removeNode(node);
            }
        }
        final NodeImpl nodeImpl = (NodeImpl)node;
        EnclosingAttr enclosingAttr = null;
        final LCount lookup = LCount.lookup("DOMAttrModified");
        if (lookup.captures + lookup.bubbles + lookup.defaults > 0) {
            enclosingAttr = this.getEnclosingAttr();
        }
        if ((n & 0x1) != 0x0) {
            final LCount lookup2 = LCount.lookup("DOMNodeRemoved");
            if (lookup2.captures + lookup2.bubbles + lookup2.defaults > 0) {
                final MutationEventImpl mutationEventImpl = new MutationEventImpl();
                mutationEventImpl.initMutationEvent("DOMNodeRemoved", true, false, this, null, null, null);
                nodeImpl.dispatchEvent(mutationEventImpl);
            }
            final LCount lookup3 = LCount.lookup("DOMNodeRemovedFromDocument");
            if (lookup3.captures + lookup3.bubbles + lookup3.defaults > 0) {
                NodeImpl nodeImpl2 = this;
                if (enclosingAttr != null) {
                    nodeImpl2 = (NodeImpl)enclosingAttr.node.getOwnerElement();
                }
                if (nodeImpl2 != null) {
                    for (NodeImpl nodeImpl3 = nodeImpl2.parentNode; nodeImpl3 != null; nodeImpl3 = nodeImpl3.parentNode) {
                        nodeImpl2 = nodeImpl3;
                    }
                    if (nodeImpl2.getNodeType() == 9) {
                        final MutationEventImpl mutationEventImpl2 = new MutationEventImpl();
                        mutationEventImpl2.initMutationEvent("DOMNodeRemovedFromDocument", false, false, null, null, null, null);
                        this.dispatchEventToSubtree(nodeImpl, mutationEventImpl2);
                    }
                }
            }
        }
        final NodeImpl previousSibling = nodeImpl.previousSibling;
        final NodeImpl nextSibling = nodeImpl.nextSibling;
        if (previousSibling != null) {
            previousSibling.nextSibling = nextSibling;
        }
        else {
            this.firstChild = nextSibling;
        }
        if (nextSibling != null) {
            nextSibling.previousSibling = previousSibling;
        }
        else {
            this.lastChild = previousSibling;
        }
        nodeImpl.parentNode = null;
        nodeImpl.nextSibling = null;
        nodeImpl.previousSibling = null;
        this.changed();
        if ((n & 0x2) != 0x0) {
            this.dispatchAggregateEvents(enclosingAttr);
        }
        return nodeImpl;
    }
    
    public Node replaceChild(final Node node, final Node node2) throws DOMException {
        EnclosingAttr enclosingAttr = null;
        final LCount lookup = LCount.lookup("DOMAttrModified");
        if (lookup.captures + lookup.bubbles + lookup.defaults > 0) {
            enclosingAttr = this.getEnclosingAttr();
        }
        this.internalInsertBefore(node, node2, 1);
        this.internalRemoveChild(node2, 1);
        this.dispatchAggregateEvents(enclosingAttr);
        return node2;
    }
    
    public int getLength() {
        int n = 0;
        for (NodeImpl nodeImpl = this.firstChild; nodeImpl != null; nodeImpl = nodeImpl.nextSibling) {
            ++n;
        }
        return n;
    }
    
    public Node item(final int n) {
        NodeImpl nodeImpl = this.firstChild;
        for (int n2 = 0; n2 < n && nodeImpl != null; nodeImpl = nodeImpl.nextSibling, ++n2) {}
        return nodeImpl;
    }
    
    public boolean supports(final String s, final String s2) {
        return this.ownerDocument.getImplementation().hasFeature(s, s2);
    }
    
    public String getNamespaceURI() {
        return null;
    }
    
    public String getPrefix() {
        return null;
    }
    
    public void setPrefix(final String s) throws DOMException {
    }
    
    public String getLocalName() {
        if (this.syncData) {
            this.synchronizeData();
        }
        return this.name;
    }
    
    public void addEventListener(final String s, final EventListener eventListener, final boolean b) {
        if (s == null || s.equals("") || eventListener == null) {
            return;
        }
        this.removeEventListener(s, eventListener, b);
        if (this.nodeListeners == null) {
            this.nodeListeners = new Vector();
        }
        this.nodeListeners.addElement(new LEntry(s, eventListener, b));
        final LCount lookup = LCount.lookup(s);
        if (b) {
            final LCount lCount = lookup;
            ++lCount.captures;
            return;
        }
        final LCount lCount2 = lookup;
        ++lCount2.bubbles;
    }
    
    public void removeEventListener(final String s, final EventListener eventListener, final boolean b) {
        if (this.nodeListeners == null || s == null || s.equals("") || eventListener == null) {
            return;
        }
        int i = this.nodeListeners.size() - 1;
        while (i >= 0) {
            final LEntry lEntry = this.nodeListeners.elementAt(i);
            if (lEntry.useCapture == b && lEntry.listener == eventListener && lEntry.type.equals(s)) {
                this.nodeListeners.removeElementAt(i);
                if (this.nodeListeners.size() == 0) {
                    this.nodeListeners = null;
                }
                final LCount lookup = LCount.lookup(s);
                if (b) {
                    final LCount lCount = lookup;
                    --lCount.captures;
                    return;
                }
                final LCount lCount2 = lookup;
                --lCount2.bubbles;
            }
            else {
                --i;
            }
        }
    }
    
    protected void finalize() throws Throwable {
        super.finalize();
        if (this.nodeListeners != null) {
            for (int i = this.nodeListeners.size() - 1; i >= 0; --i) {
                final LEntry lEntry = this.nodeListeners.elementAt(i);
                final LCount lookup = LCount.lookup(lEntry.type);
                if (lEntry.useCapture) {
                    final LCount lCount = lookup;
                    --lCount.captures;
                }
                else {
                    final LCount lCount2 = lookup;
                    --lCount2.bubbles;
                }
            }
        }
    }
    
    public boolean dispatchEvent(final Event event) {
        if (event == null) {
            return false;
        }
        final EventImpl eventImpl = (EventImpl)event;
        if (!eventImpl.initialized || eventImpl.type == null || eventImpl.type.equals("")) {
            throw new DOMExceptionImpl((short)100, "");
        }
        final LCount lookup = LCount.lookup(eventImpl.getType());
        if (lookup.captures + lookup.bubbles + lookup.defaults == 0) {
            return eventImpl.preventDefault;
        }
        eventImpl.target = this;
        eventImpl.stopPropagation = false;
        eventImpl.preventDefault = false;
        final Vector<NodeImpl> vector = new Vector<NodeImpl>(10, 10);
        for (Node node = super.getParentNode(); node != null; node = node.getParentNode()) {
            vector.addElement((NodeImpl)node);
        }
        if (lookup.captures > 0) {
            eventImpl.eventPhase = 2;
            for (int n = vector.size() - 1; n >= 0 && !eventImpl.stopPropagation; --n) {
                final NodeImpl currentNode = vector.elementAt(n);
                eventImpl.currentNode = currentNode;
                if (currentNode.nodeListeners != null) {
                    final Vector vector2 = (Vector)currentNode.nodeListeners.clone();
                    for (int i = vector2.size() - 1; i >= 0; --i) {
                        final LEntry lEntry = vector2.elementAt(i);
                        if (lEntry.useCapture && lEntry.type.equals(eventImpl.type)) {
                            try {
                                lEntry.listener.handleEvent(eventImpl);
                            }
                            catch (Exception ex) {}
                        }
                    }
                }
            }
        }
        if (lookup.bubbles > 0) {
            eventImpl.eventPhase = 3;
            eventImpl.currentNode = this;
            if (!eventImpl.stopPropagation && this.nodeListeners != null) {
                final Vector vector3 = (Vector)this.nodeListeners.clone();
                for (int j = vector3.size() - 1; j >= 0; --j) {
                    final LEntry lEntry2 = vector3.elementAt(j);
                    if (lEntry2 != null && !lEntry2.useCapture && lEntry2.type.equals(eventImpl.type)) {
                        try {
                            lEntry2.listener.handleEvent(eventImpl);
                        }
                        catch (Exception ex2) {}
                    }
                }
            }
            if (eventImpl.bubbles) {
                eventImpl.eventPhase = 1;
                for (int n2 = 0; n2 < vector.size() && !eventImpl.stopPropagation; ++n2) {
                    final NodeImpl currentNode2 = vector.elementAt(n2);
                    eventImpl.currentNode = currentNode2;
                    if (currentNode2.nodeListeners != null) {
                        final Vector vector4 = (Vector)currentNode2.nodeListeners.clone();
                        for (int k = vector4.size() - 1; k >= 0; --k) {
                            final LEntry lEntry3 = vector4.elementAt(k);
                            if (!lEntry3.useCapture && lEntry3.type.equals(eventImpl.type)) {
                                try {
                                    lEntry3.listener.handleEvent(eventImpl);
                                }
                                catch (Exception ex3) {}
                            }
                        }
                    }
                }
            }
        }
        if (lookup.defaults <= 0 || (eventImpl.cancelable && eventImpl.preventDefault)) {}
        return eventImpl.preventDefault;
    }
    
    void dispatchEventToSubtree(final NodeImpl nodeImpl, final Event event) {
        if (this.nodeListeners == null || nodeImpl == null) {
            return;
        }
        nodeImpl.dispatchEvent(event);
        if (nodeImpl.getNodeType() == 1) {
            final NamedNodeMap attributes = nodeImpl.getAttributes();
            for (int i = attributes.getLength() - 1; i >= 0; --i) {
                this.dispatchEventToSubtree((NodeImpl)attributes.item(i), event);
            }
        }
        this.dispatchEventToSubtree(nodeImpl.firstChild, event);
        this.dispatchEventToSubtree(nodeImpl.nextSibling, event);
    }
    
    EnclosingAttr getEnclosingAttr() {
        for (NodeImpl parentNode = this; parentNode != null; parentNode = parentNode.parentNode) {
            final short nodeType = parentNode.getNodeType();
            if (nodeType == 2) {
                final EnclosingAttr enclosingAttr = new EnclosingAttr();
                enclosingAttr.node = (AttrImpl)parentNode;
                enclosingAttr.oldvalue = enclosingAttr.node.getNodeValue();
                return enclosingAttr;
            }
            if (nodeType != 5) {
                return null;
            }
        }
        return null;
    }
    
    void dispatchAggregateEvents(final EnclosingAttr enclosingAttr) {
        if (enclosingAttr != null) {
            this.dispatchAggregateEvents(enclosingAttr.node, enclosingAttr.oldvalue);
            return;
        }
        this.dispatchAggregateEvents(null, null);
    }
    
    void dispatchAggregateEvents(final AttrImpl attrImpl, final String s) {
        if (this.nodeListeners == null) {
            return;
        }
        NodeImpl nodeImpl = null;
        if (attrImpl != null) {
            final LCount lookup = LCount.lookup("DOMAttrModified");
            if (lookup.captures + lookup.bubbles + lookup.defaults > 0) {
                nodeImpl = (NodeImpl)attrImpl.getOwnerElement();
                if (nodeImpl != null) {
                    final MutationEventImpl mutationEventImpl = new MutationEventImpl();
                    mutationEventImpl.initMutationEvent("DOMAttrModified", true, false, null, s, attrImpl.getNodeValue(), attrImpl.getNodeName());
                    nodeImpl.dispatchEvent(mutationEventImpl);
                }
            }
        }
        final LCount lookup2 = LCount.lookup("DOMSubtreeModified");
        if (lookup2.captures + lookup2.bubbles + lookup2.defaults > 0) {
            final MutationEventImpl mutationEventImpl2 = new MutationEventImpl();
            mutationEventImpl2.initMutationEvent("DOMSubtreeModified", true, false, null, null, null, null);
            if (attrImpl != null) {
                attrImpl.dispatchEvent(mutationEventImpl2);
                if (nodeImpl != null) {
                    nodeImpl.dispatchEvent(mutationEventImpl2);
                }
            }
            else {
                this.dispatchEvent(mutationEventImpl2);
            }
        }
    }
    
    public void setReadOnly(final boolean readOnly, final boolean b) {
        if (this.syncData) {
            this.synchronizeData();
        }
        this.readOnly = readOnly;
        if (b) {
            if (this.syncChildren) {
                this.synchronizeChildren();
            }
            for (NodeImpl nodeImpl = this.firstChild; nodeImpl != null; nodeImpl = nodeImpl.nextSibling) {
                if (!(nodeImpl instanceof EntityReference)) {
                    nodeImpl.setReadOnly(readOnly, true);
                }
            }
        }
    }
    
    public boolean getReadOnly() {
        if (this.syncData) {
            this.synchronizeData();
        }
        return this.readOnly;
    }
    
    public void setUserData(final Object userData) {
        this.userData = userData;
    }
    
    public Object getUserData() {
        return this.userData;
    }
    
    protected void synchronizeChildren() {
    }
    
    protected void synchronizeData() {
    }
    
    protected void changed() {
        ++this.changes;
        if (this.parentNode != null) {
            this.parentNode.changed();
        }
    }
    
    public String toString() {
        return "[" + this.getNodeName() + ": " + this.getNodeValue() + "]";
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        if (this.syncData) {
            this.synchronizeData();
        }
        if (this.syncChildren) {
            this.synchronizeChildren();
        }
        objectOutputStream.defaultWriteObject();
    }
    
    static {
        (NodeImpl.kidOK = new int[13])[9] = 1410;
        final int[] kidOK = NodeImpl.kidOK;
        final int n = 11;
        final int[] kidOK2 = NodeImpl.kidOK;
        final int n2 = 6;
        final int[] kidOK3 = NodeImpl.kidOK;
        final int n3 = 5;
        final int[] kidOK4 = NodeImpl.kidOK;
        final int n4 = 1;
        final int n5 = 446;
        kidOK3[n3] = (kidOK4[n4] = n5);
        kidOK[n] = (kidOK2[n2] = n5);
        NodeImpl.kidOK[2] = 40;
        final int[] kidOK5 = NodeImpl.kidOK;
        final int n6 = 10;
        final int[] kidOK6 = NodeImpl.kidOK;
        final int n7 = 7;
        final int[] kidOK7 = NodeImpl.kidOK;
        final int n8 = 8;
        final int[] kidOK8 = NodeImpl.kidOK;
        final int n9 = 3;
        final int[] kidOK9 = NodeImpl.kidOK;
        final int n10 = 4;
        final int[] kidOK10 = NodeImpl.kidOK;
        final int n11 = 12;
        final boolean b = false;
        kidOK9[n10] = (kidOK10[n11] = (b ? 1 : 0));
        kidOK7[n8] = (kidOK8[n9] = (b ? 1 : 0));
        kidOK5[n6] = (kidOK6[n7] = (b ? 1 : 0));
    }
    
    class LEntry
    {
        String type;
        EventListener listener;
        boolean useCapture;
        
        LEntry(final String type, final EventListener listener, final boolean useCapture) {
            this.type = type;
            this.listener = listener;
            this.useCapture = useCapture;
        }
    }
    
    class EnclosingAttr
    {
        AttrImpl node;
        String oldvalue;
    }
}
