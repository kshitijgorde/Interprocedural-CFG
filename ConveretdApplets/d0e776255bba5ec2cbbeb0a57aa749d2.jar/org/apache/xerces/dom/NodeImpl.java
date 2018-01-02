// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import java.io.IOException;
import java.io.ObjectOutputStream;
import org.apache.xerces.dom.events.MutationEventImpl;
import org.w3c.dom.events.EventException;
import org.apache.xerces.dom.events.EventImpl;
import org.w3c.dom.events.Event;
import java.util.Vector;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Document;
import org.w3c.dom.DOMException;
import java.io.Serializable;
import org.w3c.dom.events.EventTarget;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

public abstract class NodeImpl implements Node, NodeList, EventTarget, Cloneable, Serializable
{
    static final long serialVersionUID = -6316591992167219696L;
    public static final short ELEMENT_DEFINITION_NODE = -1;
    protected NodeImpl ownerNode;
    protected short flags;
    protected static final short READONLY = 1;
    protected static final short SYNCDATA = 2;
    protected static final short SYNCCHILDREN = 4;
    protected static final short OWNED = 8;
    protected static final short FIRSTCHILD = 16;
    protected static final short SPECIFIED = 32;
    protected static final short IGNORABLEWS = 64;
    protected static final short SETVALUE = 128;
    protected static final short HASSTRING = 256;
    protected static final short UNNORMALIZED = 512;
    protected static final boolean MUTATIONEVENTS = true;
    protected static final int MUTATION_NONE = 0;
    protected static final int MUTATION_LOCAL = 1;
    protected static final int MUTATION_AGGREGATE = 2;
    protected static final int MUTATION_ALL = 65535;
    
    protected NodeImpl(final DocumentImpl ownerNode) {
        this.ownerNode = ownerNode;
    }
    
    public NodeImpl() {
    }
    
    public abstract short getNodeType();
    
    public abstract String getNodeName();
    
    public String getNodeValue() {
        return null;
    }
    
    public void setNodeValue(final String s) throws DOMException {
    }
    
    public Node appendChild(final Node node) throws DOMException {
        return this.insertBefore(node, null);
    }
    
    public Node cloneNode(final boolean b) {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        NodeImpl nodeImpl;
        try {
            nodeImpl = (NodeImpl)this.clone();
        }
        catch (CloneNotSupportedException ex) {
            return null;
        }
        nodeImpl.ownerNode = this.ownerDocument();
        nodeImpl.isOwned(false);
        nodeImpl.isReadOnly(false);
        return nodeImpl;
    }
    
    public Document getOwnerDocument() {
        if (this.isOwned()) {
            return this.ownerNode.ownerDocument();
        }
        return (Document)this.ownerNode;
    }
    
    DocumentImpl ownerDocument() {
        if (this.isOwned()) {
            return this.ownerNode.ownerDocument();
        }
        return (DocumentImpl)this.ownerNode;
    }
    
    void setOwnerDocument(final DocumentImpl ownerNode) {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        if (!this.isOwned()) {
            this.ownerNode = ownerNode;
        }
    }
    
    public Node getParentNode() {
        return null;
    }
    
    NodeImpl parentNode() {
        return null;
    }
    
    public Node getNextSibling() {
        return null;
    }
    
    public Node getPreviousSibling() {
        return null;
    }
    
    ChildNode previousSibling() {
        return null;
    }
    
    public NamedNodeMap getAttributes() {
        return null;
    }
    
    public boolean hasAttributes() {
        return false;
    }
    
    public boolean hasChildNodes() {
        return false;
    }
    
    public NodeList getChildNodes() {
        return this;
    }
    
    public Node getFirstChild() {
        return null;
    }
    
    public Node getLastChild() {
        return null;
    }
    
    public Node insertBefore(final Node node, final Node node2) throws DOMException {
        throw new DOMException((short)3, "DOM006 Hierarchy request error");
    }
    
    public Node removeChild(final Node node) throws DOMException {
        throw new DOMException((short)8, "DOM008 Not found");
    }
    
    public Node replaceChild(final Node node, final Node node2) throws DOMException {
        throw new DOMException((short)3, "DOM006 Hierarchy request error");
    }
    
    public int getLength() {
        return 0;
    }
    
    public Node item(final int n) {
        return null;
    }
    
    public void normalize() {
    }
    
    public boolean isSupported(final String s, final String s2) {
        return this.ownerDocument().getImplementation().hasFeature(s, s2);
    }
    
    public String getNamespaceURI() {
        return null;
    }
    
    public String getPrefix() {
        return null;
    }
    
    public void setPrefix(final String s) throws DOMException {
        throw new DOMException((short)14, "DOM003 Namespace error");
    }
    
    public String getLocalName() {
        return null;
    }
    
    public void addEventListener(final String s, final EventListener eventListener, final boolean b) {
        if (s == null || s.equals("") || eventListener == null) {
            return;
        }
        this.removeEventListener(s, eventListener, b);
        Vector<LEntry> eventListeners = (Vector<LEntry>)this.ownerDocument().getEventListeners(this);
        if (eventListeners == null) {
            eventListeners = new Vector<LEntry>();
            this.ownerDocument().setEventListeners(this, eventListeners);
        }
        eventListeners.addElement(new LEntry(s, eventListener, b));
        final LCount lookup = LCount.lookup(s);
        if (b) {
            final LCount lCount = lookup;
            ++lCount.captures;
        }
        else {
            final LCount lCount2 = lookup;
            ++lCount2.bubbles;
        }
    }
    
    public void removeEventListener(final String s, final EventListener eventListener, final boolean b) {
        final Vector eventListeners = this.ownerDocument().getEventListeners(this);
        if (eventListeners == null || s == null || s.equals("") || eventListener == null) {
            return;
        }
        int i = eventListeners.size() - 1;
        while (i >= 0) {
            final LEntry lEntry = eventListeners.elementAt(i);
            if (lEntry.useCapture == b && lEntry.listener == eventListener && lEntry.type.equals(s)) {
                eventListeners.removeElementAt(i);
                if (eventListeners.size() == 0) {
                    this.ownerDocument().setEventListeners(this, null);
                }
                final LCount lookup = LCount.lookup(s);
                if (b) {
                    final LCount lCount = lookup;
                    --lCount.captures;
                    break;
                }
                final LCount lCount2 = lookup;
                --lCount2.bubbles;
                break;
            }
            else {
                --i;
            }
        }
    }
    
    public boolean dispatchEvent(final Event event) {
        if (event == null) {
            return false;
        }
        final EventImpl eventImpl = (EventImpl)event;
        if (!eventImpl.initialized || eventImpl.type == null || eventImpl.type.equals("")) {
            throw new EventException((short)0, "DOM010 Unspecified event type");
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
            eventImpl.eventPhase = 1;
            for (int i = vector.size() - 1; i >= 0; --i) {
                if (eventImpl.stopPropagation) {
                    break;
                }
                final NodeImpl currentTarget = vector.elementAt(i);
                eventImpl.currentTarget = currentTarget;
                final Vector eventListeners = this.ownerDocument().getEventListeners(currentTarget);
                if (eventListeners != null) {
                    final Vector vector2 = (Vector)eventListeners.clone();
                    for (int j = vector2.size() - 1; j >= 0; --j) {
                        final LEntry lEntry = vector2.elementAt(j);
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
            eventImpl.eventPhase = 2;
            eventImpl.currentTarget = this;
            final Vector eventListeners2 = this.ownerDocument().getEventListeners(this);
            if (!eventImpl.stopPropagation && eventListeners2 != null) {
                final Vector vector3 = (Vector)eventListeners2.clone();
                for (int k = vector3.size() - 1; k >= 0; --k) {
                    final LEntry lEntry2 = vector3.elementAt(k);
                    if (lEntry2 != null && !lEntry2.useCapture && lEntry2.type.equals(eventImpl.type)) {
                        try {
                            lEntry2.listener.handleEvent(eventImpl);
                        }
                        catch (Exception ex2) {}
                    }
                }
            }
            if (eventImpl.bubbles) {
                eventImpl.eventPhase = 3;
                for (int l = 0; l < vector.size(); ++l) {
                    if (eventImpl.stopPropagation) {
                        break;
                    }
                    final NodeImpl currentTarget2 = vector.elementAt(l);
                    eventImpl.currentTarget = currentTarget2;
                    final Vector eventListeners3 = this.ownerDocument().getEventListeners(currentTarget2);
                    if (eventListeners3 != null) {
                        final Vector vector4 = (Vector)eventListeners3.clone();
                        for (int n = vector4.size() - 1; n >= 0; --n) {
                            final LEntry lEntry3 = vector4.elementAt(n);
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
        if (lookup.defaults <= 0 || !eventImpl.cancelable || !eventImpl.preventDefault) {}
        return eventImpl.preventDefault;
    }
    
    void dispatchEventToSubtree(final Node node, final Event event) {
        if (this.ownerDocument().mutationEvents) {
            if (this.ownerDocument().getEventListeners(this) == null || node == null) {
                return;
            }
            ((NodeImpl)node).dispatchEvent(event);
            if (node.getNodeType() == 1) {
                final NamedNodeMap attributes = node.getAttributes();
                for (int i = attributes.getLength() - 1; i >= 0; --i) {
                    this.dispatchEventToSubtree(attributes.item(i), event);
                }
            }
            this.dispatchEventToSubtree(node.getFirstChild(), event);
            this.dispatchEventToSubtree(node.getNextSibling(), event);
        }
    }
    
    EnclosingAttr getEnclosingAttr() {
        if (this.ownerDocument().mutationEvents) {
            for (NodeImpl parentNode = this; parentNode != null; parentNode = parentNode.parentNode()) {
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
        return null;
    }
    
    void dispatchAggregateEvents(final EnclosingAttr enclosingAttr) {
        if (enclosingAttr != null) {
            this.dispatchAggregateEvents(enclosingAttr.node, enclosingAttr.oldvalue, (short)1);
        }
        else {
            this.dispatchAggregateEvents(null, null, (short)0);
        }
    }
    
    void dispatchAggregateEvents(final AttrImpl attrImpl, final String s, final short attrChange) {
        if (this.ownerDocument().mutationEvents) {
            NodeImpl nodeImpl = null;
            if (attrImpl != null) {
                final LCount lookup = LCount.lookup("DOMAttrModified");
                if (lookup.captures + lookup.bubbles + lookup.defaults > 0) {
                    nodeImpl = (NodeImpl)attrImpl.getOwnerElement();
                    if (nodeImpl != null) {
                        final MutationEventImpl mutationEventImpl = new MutationEventImpl();
                        mutationEventImpl.initMutationEvent("DOMAttrModified", true, false, null, s, attrImpl.getNodeValue(), attrImpl.getNodeName(), (short)0);
                        mutationEventImpl.attrChange = attrChange;
                        nodeImpl.dispatchEvent(mutationEventImpl);
                    }
                }
            }
            final LCount lookup2 = LCount.lookup("DOMSubtreeModified");
            if (lookup2.captures + lookup2.bubbles + lookup2.defaults > 0) {
                final MutationEventImpl mutationEventImpl2 = new MutationEventImpl();
                mutationEventImpl2.initMutationEvent("DOMSubtreeModified", true, false, null, null, null, null, (short)0);
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
    }
    
    public void setReadOnly(final boolean b, final boolean b2) {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        this.isReadOnly(b);
    }
    
    public boolean getReadOnly() {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        return this.isReadOnly();
    }
    
    public void setUserData(final Object o) {
        this.ownerDocument().setUserData(this, o);
    }
    
    public Object getUserData() {
        return this.ownerDocument().getUserData(this);
    }
    
    protected void changed() {
        this.ownerDocument().changed();
    }
    
    protected int changes() {
        return this.ownerDocument().changes();
    }
    
    protected void synchronizeData() {
        this.needsSyncData(false);
    }
    
    final boolean isReadOnly() {
        return (this.flags & 0x1) != 0x0;
    }
    
    final void isReadOnly(final boolean b) {
        this.flags = (b ? ((short)(this.flags | 0x1)) : ((short)(this.flags & 0xFFFFFFFE)));
    }
    
    final boolean needsSyncData() {
        return (this.flags & 0x2) != 0x0;
    }
    
    final void needsSyncData(final boolean b) {
        this.flags = (b ? ((short)(this.flags | 0x2)) : ((short)(this.flags & 0xFFFFFFFD)));
    }
    
    final boolean needsSyncChildren() {
        return (this.flags & 0x4) != 0x0;
    }
    
    final void needsSyncChildren(final boolean b) {
        this.flags = (b ? ((short)(this.flags | 0x4)) : ((short)(this.flags & 0xFFFFFFFB)));
    }
    
    final boolean isOwned() {
        return (this.flags & 0x8) != 0x0;
    }
    
    final void isOwned(final boolean b) {
        this.flags = (b ? ((short)(this.flags | 0x8)) : ((short)(this.flags & 0xFFFFFFF7)));
    }
    
    final boolean isFirstChild() {
        return (this.flags & 0x10) != 0x0;
    }
    
    final void isFirstChild(final boolean b) {
        this.flags = (b ? ((short)(this.flags | 0x10)) : ((short)(this.flags & 0xFFFFFFEF)));
    }
    
    final boolean isSpecified() {
        return (this.flags & 0x20) != 0x0;
    }
    
    final void isSpecified(final boolean b) {
        this.flags = (b ? ((short)(this.flags | 0x20)) : ((short)(this.flags & 0xFFFFFFDF)));
    }
    
    final boolean internalIsIgnorableWhitespace() {
        return (this.flags & 0x40) != 0x0;
    }
    
    final void isIgnorableWhitespace(final boolean b) {
        this.flags = (b ? ((short)(this.flags | 0x40)) : ((short)(this.flags & 0xFFFFFFBF)));
    }
    
    final boolean setValueCalled() {
        return (this.flags & 0x80) != 0x0;
    }
    
    final void setValueCalled(final boolean b) {
        this.flags = (b ? ((short)(this.flags | 0x80)) : ((short)(this.flags & 0xFFFFFF7F)));
    }
    
    final boolean hasStringValue() {
        return (this.flags & 0x100) != 0x0;
    }
    
    final void hasStringValue(final boolean b) {
        this.flags = (b ? ((short)(this.flags | 0x100)) : ((short)(this.flags & 0xFFFFFEFF)));
    }
    
    final boolean isNormalized() {
        return (this.flags & 0x200) == 0x0;
    }
    
    final void isNormalized(final boolean b) {
        if (!b && this.isNormalized() && this.ownerNode != null) {
            this.ownerNode.isNormalized(false);
        }
        this.flags = (b ? ((short)(this.flags & 0xFFFFFDFF)) : ((short)(this.flags | 0x200)));
    }
    
    public String toString() {
        return "[" + this.getNodeName() + ": " + this.getNodeValue() + "]";
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        objectOutputStream.defaultWriteObject();
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
