// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import java.io.Serializable;
import org.w3c.dom.Element;
import org.w3c.dom.Attr;
import org.w3c.dom.events.MutationEvent;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.events.EventException;
import org.w3c.dom.events.EventListener;
import org.apache.xerces.dom.events.MutationEventImpl;
import org.apache.xerces.dom.events.EventImpl;
import org.w3c.dom.events.Event;
import org.w3c.dom.ranges.Range;
import org.w3c.dom.DOMException;
import org.w3c.dom.traversal.TreeWalker;
import org.w3c.dom.traversal.NodeIterator;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Node;
import org.w3c.dom.DocumentType;
import java.util.Hashtable;
import java.util.Vector;
import org.w3c.dom.ranges.DocumentRange;
import org.w3c.dom.events.DocumentEvent;
import org.w3c.dom.traversal.DocumentTraversal;

public class DocumentImpl extends CoreDocumentImpl implements DocumentTraversal, DocumentEvent, DocumentRange
{
    static final long serialVersionUID = 515687835542616694L;
    protected Vector iterators;
    protected Vector ranges;
    protected Hashtable eventListeners;
    protected boolean mutationEvents;
    EnclosingAttr savedEnclosingAttr;
    
    public DocumentImpl() {
        this.mutationEvents = false;
    }
    
    public DocumentImpl(final boolean grammarAccess) {
        super(grammarAccess);
        this.mutationEvents = false;
    }
    
    public DocumentImpl(final DocumentType doctype) {
        super(doctype);
        this.mutationEvents = false;
    }
    
    public DocumentImpl(final DocumentType doctype, final boolean grammarAccess) {
        super(doctype, grammarAccess);
        this.mutationEvents = false;
    }
    
    public Node cloneNode(final boolean deep) {
        final DocumentImpl newdoc = new DocumentImpl();
        this.callUserDataHandlers(this, newdoc, (short)1);
        this.cloneNode(newdoc, deep);
        newdoc.mutationEvents = this.mutationEvents;
        return newdoc;
    }
    
    public DOMImplementation getImplementation() {
        return DOMImplementationImpl.getDOMImplementation();
    }
    
    public NodeIterator createNodeIterator(final Node root, final short whatToShow, final NodeFilter filter) {
        return this.createNodeIterator(root, whatToShow, filter, true);
    }
    
    public NodeIterator createNodeIterator(final Node root, final int whatToShow, final NodeFilter filter, final boolean entityReferenceExpansion) {
        final NodeIterator iterator = new NodeIteratorImpl(this, root, whatToShow, filter, entityReferenceExpansion);
        if (this.iterators == null) {
            this.iterators = new Vector();
        }
        this.iterators.addElement(iterator);
        return iterator;
    }
    
    public TreeWalker createTreeWalker(final Node root, final short whatToShow, final NodeFilter filter) {
        return this.createTreeWalker(root, whatToShow, filter, true);
    }
    
    public TreeWalker createTreeWalker(final Node root, final int whatToShow, final NodeFilter filter, final boolean entityReferenceExpansion) {
        if (root == null) {
            final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_SUPPORTED_ERR", null);
            throw new DOMException((short)9, msg);
        }
        return new TreeWalkerImpl(root, whatToShow, filter, entityReferenceExpansion);
    }
    
    void removeNodeIterator(final NodeIterator nodeIterator) {
        if (nodeIterator == null) {
            return;
        }
        if (this.iterators == null) {
            return;
        }
        this.iterators.removeElement(nodeIterator);
    }
    
    public Range createRange() {
        if (this.ranges == null) {
            this.ranges = new Vector();
        }
        final Range range = new RangeImpl(this);
        this.ranges.addElement(range);
        return range;
    }
    
    void removeRange(final Range range) {
        if (range == null) {
            return;
        }
        if (this.ranges == null) {
            return;
        }
        this.ranges.removeElement(range);
    }
    
    void replacedText(final NodeImpl node) {
        if (this.ranges != null) {
            for (int size = this.ranges.size(), i = 0; i != size; ++i) {
                this.ranges.elementAt(i).receiveReplacedText(node);
            }
        }
    }
    
    void deletedText(final NodeImpl node, final int offset, final int count) {
        if (this.ranges != null) {
            for (int size = this.ranges.size(), i = 0; i != size; ++i) {
                this.ranges.elementAt(i).receiveDeletedText(node, offset, count);
            }
        }
    }
    
    void insertedText(final NodeImpl node, final int offset, final int count) {
        if (this.ranges != null) {
            for (int size = this.ranges.size(), i = 0; i != size; ++i) {
                this.ranges.elementAt(i).receiveInsertedText(node, offset, count);
            }
        }
    }
    
    void splitData(final Node node, final Node newNode, final int offset) {
        if (this.ranges != null) {
            for (int size = this.ranges.size(), i = 0; i != size; ++i) {
                this.ranges.elementAt(i).receiveSplitData(node, newNode, offset);
            }
        }
    }
    
    public Event createEvent(final String type) throws DOMException {
        if (type.equalsIgnoreCase("Events") || "Event".equals(type)) {
            return new EventImpl();
        }
        if (type.equalsIgnoreCase("MutationEvents") || "MutationEvent".equals(type)) {
            return new MutationEventImpl();
        }
        final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_SUPPORTED_ERR", null);
        throw new DOMException((short)9, msg);
    }
    
    void setMutationEvents(final boolean set) {
        this.mutationEvents = set;
    }
    
    boolean getMutationEvents() {
        return this.mutationEvents;
    }
    
    protected void setEventListeners(final NodeImpl n, final Vector listeners) {
        if (this.eventListeners == null) {
            this.eventListeners = new Hashtable();
        }
        if (listeners == null) {
            this.eventListeners.remove(n);
            if (this.eventListeners.isEmpty()) {
                this.mutationEvents = false;
            }
        }
        else {
            this.eventListeners.put(n, listeners);
            this.mutationEvents = true;
        }
    }
    
    protected Vector getEventListeners(final NodeImpl n) {
        if (this.eventListeners == null) {
            return null;
        }
        return this.eventListeners.get(n);
    }
    
    protected void addEventListener(final NodeImpl node, final String type, final EventListener listener, final boolean useCapture) {
        if (type == null || type.equals("") || listener == null) {
            return;
        }
        this.removeEventListener(node, type, listener, useCapture);
        Vector nodeListeners = this.getEventListeners(node);
        if (nodeListeners == null) {
            nodeListeners = new Vector();
            this.setEventListeners(node, nodeListeners);
        }
        nodeListeners.addElement(new LEntry(type, listener, useCapture));
        final LCount lc = LCount.lookup(type);
        if (useCapture) {
            final LCount lCount = lc;
            ++lCount.captures;
        }
        else {
            final LCount lCount2 = lc;
            ++lCount2.bubbles;
        }
    }
    
    protected void removeEventListener(final NodeImpl node, final String type, final EventListener listener, final boolean useCapture) {
        if (type == null || type.equals("") || listener == null) {
            return;
        }
        final Vector nodeListeners = this.getEventListeners(node);
        if (nodeListeners == null) {
            return;
        }
        int i = nodeListeners.size() - 1;
        while (i >= 0) {
            final LEntry le = nodeListeners.elementAt(i);
            if (le.useCapture == useCapture && le.listener == listener && le.type.equals(type)) {
                nodeListeners.removeElementAt(i);
                if (nodeListeners.size() == 0) {
                    this.setEventListeners(node, null);
                }
                final LCount lc = LCount.lookup(type);
                if (useCapture) {
                    final LCount lCount = lc;
                    --lCount.captures;
                    break;
                }
                final LCount lCount2 = lc;
                --lCount2.bubbles;
                break;
            }
            else {
                --i;
            }
        }
    }
    
    protected void copyEventListeners(final NodeImpl src, final NodeImpl tgt) {
        final Vector nodeListeners = this.getEventListeners(src);
        if (nodeListeners == null) {
            return;
        }
        this.setEventListeners(tgt, (Vector)nodeListeners.clone());
    }
    
    protected boolean dispatchEvent(final NodeImpl node, final Event event) {
        if (event == null) {
            return false;
        }
        final EventImpl evt = (EventImpl)event;
        if (!evt.initialized || evt.type == null || evt.type.equals("")) {
            final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "UNSPECIFIED_EVENT_TYPE_ERR", null);
            throw new EventException((short)0, msg);
        }
        final LCount lc = LCount.lookup(evt.getType());
        if (lc.captures + lc.bubbles + lc.defaults == 0) {
            return evt.preventDefault;
        }
        evt.target = node;
        evt.stopPropagation = false;
        evt.preventDefault = false;
        final Vector pv = new Vector(10, 10);
        for (Node n = node.getParentNode(); n != null; n = n.getParentNode()) {
            pv.addElement(n);
            final Node p = n;
        }
        if (lc.captures > 0) {
            evt.eventPhase = 1;
            for (int j = pv.size() - 1; j >= 0; --j) {
                if (evt.stopPropagation) {
                    break;
                }
                final NodeImpl nn = pv.elementAt(j);
                evt.currentTarget = nn;
                final Vector nodeListeners = this.getEventListeners(nn);
                if (nodeListeners != null) {
                    final Vector nl = (Vector)nodeListeners.clone();
                    for (int i = nl.size() - 1; i >= 0; --i) {
                        final LEntry le = nl.elementAt(i);
                        if (le.useCapture && le.type.equals(evt.type) && nodeListeners.contains(le)) {
                            try {
                                le.listener.handleEvent(evt);
                            }
                            catch (Exception ex) {}
                        }
                    }
                }
            }
        }
        if (lc.bubbles > 0) {
            evt.eventPhase = 2;
            evt.currentTarget = node;
            Vector nodeListeners2 = this.getEventListeners(node);
            if (!evt.stopPropagation && nodeListeners2 != null) {
                final Vector nl2 = (Vector)nodeListeners2.clone();
                for (int k = nl2.size() - 1; k >= 0; --k) {
                    final LEntry le2 = nl2.elementAt(k);
                    if (!le2.useCapture && le2.type.equals(evt.type) && nodeListeners2.contains(le2)) {
                        try {
                            le2.listener.handleEvent(evt);
                        }
                        catch (Exception ex2) {}
                    }
                }
            }
            if (evt.bubbles) {
                evt.eventPhase = 3;
                for (int l = 0; l < pv.size(); ++l) {
                    if (evt.stopPropagation) {
                        break;
                    }
                    final NodeImpl nn2 = pv.elementAt(l);
                    evt.currentTarget = nn2;
                    nodeListeners2 = this.getEventListeners(nn2);
                    if (nodeListeners2 != null) {
                        final Vector nl = (Vector)nodeListeners2.clone();
                        for (int i = nl.size() - 1; i >= 0; --i) {
                            final LEntry le = nl.elementAt(i);
                            if (!le.useCapture && le.type.equals(evt.type) && nodeListeners2.contains(le)) {
                                try {
                                    le.listener.handleEvent(evt);
                                }
                                catch (Exception ex3) {}
                            }
                        }
                    }
                }
            }
        }
        if (lc.defaults <= 0 || !evt.cancelable || !evt.preventDefault) {}
        return evt.preventDefault;
    }
    
    protected void dispatchEventToSubtree(final NodeImpl node, final Node n, final Event e) {
        final Vector nodeListeners = this.getEventListeners(node);
        if (nodeListeners == null || n == null) {
            return;
        }
        ((NodeImpl)n).dispatchEvent(e);
        if (n.getNodeType() == 1) {
            final NamedNodeMap a = n.getAttributes();
            for (int i = a.getLength() - 1; i >= 0; --i) {
                this.dispatchEventToSubtree(node, a.item(i), e);
            }
        }
        this.dispatchEventToSubtree(node, n.getFirstChild(), e);
        this.dispatchEventToSubtree(node, n.getNextSibling(), e);
    }
    
    protected void dispatchAggregateEvents(final NodeImpl node, final EnclosingAttr ea) {
        if (ea != null) {
            this.dispatchAggregateEvents(node, ea.node, ea.oldvalue, (short)1);
        }
        else {
            this.dispatchAggregateEvents(node, null, null, (short)0);
        }
    }
    
    protected void dispatchAggregateEvents(final NodeImpl node, final AttrImpl enclosingAttr, final String oldvalue, final short change) {
        NodeImpl owner = null;
        if (enclosingAttr != null) {
            final LCount lc = LCount.lookup("DOMAttrModified");
            owner = (NodeImpl)enclosingAttr.getOwnerElement();
            if (lc.captures + lc.bubbles + lc.defaults > 0 && owner != null) {
                final MutationEventImpl me = new MutationEventImpl();
                me.initMutationEvent("DOMAttrModified", true, false, enclosingAttr, oldvalue, enclosingAttr.getNodeValue(), enclosingAttr.getNodeName(), change);
                owner.dispatchEvent(me);
            }
        }
        final LCount lc = LCount.lookup("DOMSubtreeModified");
        if (lc.captures + lc.bubbles + lc.defaults > 0) {
            final MutationEvent me2 = new MutationEventImpl();
            me2.initMutationEvent("DOMSubtreeModified", true, false, null, null, null, null, (short)0);
            if (enclosingAttr != null) {
                this.dispatchEvent(enclosingAttr, me2);
                if (owner != null) {
                    this.dispatchEvent(owner, me2);
                }
            }
            else {
                this.dispatchEvent(node, me2);
            }
        }
    }
    
    protected void saveEnclosingAttr(final NodeImpl node) {
        this.savedEnclosingAttr = null;
        final LCount lc = LCount.lookup("DOMAttrModified");
        if (lc.captures + lc.bubbles + lc.defaults > 0) {
            for (NodeImpl eventAncestor = node; eventAncestor != null; eventAncestor = eventAncestor.parentNode()) {
                final int type = eventAncestor.getNodeType();
                if (type == 2) {
                    final EnclosingAttr retval = new EnclosingAttr();
                    retval.node = (AttrImpl)eventAncestor;
                    retval.oldvalue = retval.node.getNodeValue();
                    this.savedEnclosingAttr = retval;
                    return;
                }
                if (type != 5) {
                    return;
                }
            }
        }
    }
    
    void modifyingCharacterData(final NodeImpl node) {
        if (this.mutationEvents) {
            this.saveEnclosingAttr(node);
        }
    }
    
    void modifiedCharacterData(final NodeImpl node, final String oldvalue, final String value) {
        if (this.mutationEvents) {
            final LCount lc = LCount.lookup("DOMCharacterDataModified");
            if (lc.captures + lc.bubbles + lc.defaults > 0) {
                final MutationEvent me = new MutationEventImpl();
                me.initMutationEvent("DOMCharacterDataModified", true, false, null, oldvalue, value, null, (short)0);
                this.dispatchEvent(node, me);
            }
            this.dispatchAggregateEvents(node, this.savedEnclosingAttr);
        }
    }
    
    void insertingNode(final NodeImpl node, final boolean replace) {
        if (this.mutationEvents && !replace) {
            this.saveEnclosingAttr(node);
        }
    }
    
    void insertedNode(final NodeImpl node, final NodeImpl newInternal, final boolean replace) {
        if (this.mutationEvents) {
            LCount lc = LCount.lookup("DOMNodeInserted");
            if (lc.captures + lc.bubbles + lc.defaults > 0) {
                final MutationEventImpl me = new MutationEventImpl();
                me.initMutationEvent("DOMNodeInserted", true, false, node, null, null, null, (short)0);
                this.dispatchEvent(newInternal, me);
            }
            lc = LCount.lookup("DOMNodeInsertedIntoDocument");
            if (lc.captures + lc.bubbles + lc.defaults > 0) {
                NodeImpl eventAncestor = node;
                if (this.savedEnclosingAttr != null) {
                    eventAncestor = (NodeImpl)this.savedEnclosingAttr.node.getOwnerElement();
                }
                if (eventAncestor != null) {
                    NodeImpl p = eventAncestor;
                    while (p != null) {
                        eventAncestor = p;
                        if (p.getNodeType() == 2) {
                            p = (NodeImpl)((AttrImpl)p).getOwnerElement();
                        }
                        else {
                            p = p.parentNode();
                        }
                    }
                    if (eventAncestor.getNodeType() == 9) {
                        final MutationEventImpl me2 = new MutationEventImpl();
                        me2.initMutationEvent("DOMNodeInsertedIntoDocument", false, false, null, null, null, null, (short)0);
                        this.dispatchEventToSubtree(node, newInternal, me2);
                    }
                }
            }
            if (!replace) {
                this.dispatchAggregateEvents(node, this.savedEnclosingAttr);
            }
        }
    }
    
    void removingNode(final NodeImpl node, final NodeImpl oldChild, final boolean replace) {
        if (this.iterators != null) {
            for (int size = this.iterators.size(), i = 0; i != size; ++i) {
                this.iterators.elementAt(i).removeNode(oldChild);
            }
        }
        if (this.ranges != null) {
            for (int size = this.ranges.size(), i = 0; i != size; ++i) {
                this.ranges.elementAt(i).removeNode(oldChild);
            }
        }
        if (this.mutationEvents) {
            if (!replace) {
                this.saveEnclosingAttr(node);
            }
            LCount lc = LCount.lookup("DOMNodeRemoved");
            if (lc.captures + lc.bubbles + lc.defaults > 0) {
                final MutationEventImpl me = new MutationEventImpl();
                me.initMutationEvent("DOMNodeRemoved", true, false, node, null, null, null, (short)0);
                this.dispatchEvent(oldChild, me);
            }
            lc = LCount.lookup("DOMNodeRemovedFromDocument");
            if (lc.captures + lc.bubbles + lc.defaults > 0) {
                NodeImpl eventAncestor = this;
                if (this.savedEnclosingAttr != null) {
                    eventAncestor = (NodeImpl)this.savedEnclosingAttr.node.getOwnerElement();
                }
                if (eventAncestor != null) {
                    for (NodeImpl p = eventAncestor.parentNode(); p != null; p = p.parentNode()) {
                        eventAncestor = p;
                    }
                    if (eventAncestor.getNodeType() == 9) {
                        final MutationEventImpl me2 = new MutationEventImpl();
                        me2.initMutationEvent("DOMNodeRemovedFromDocument", false, false, null, null, null, null, (short)0);
                        this.dispatchEventToSubtree(node, oldChild, me2);
                    }
                }
            }
        }
    }
    
    void removedNode(final NodeImpl node, final boolean replace) {
        if (this.mutationEvents && !replace) {
            this.dispatchAggregateEvents(node, this.savedEnclosingAttr);
        }
    }
    
    void replacingNode(final NodeImpl node) {
        if (this.mutationEvents) {
            this.saveEnclosingAttr(node);
        }
    }
    
    void replacedNode(final NodeImpl node) {
        if (this.mutationEvents) {
            this.dispatchAggregateEvents(node, this.savedEnclosingAttr);
        }
    }
    
    void modifiedAttrValue(final AttrImpl attr, final String oldvalue) {
        if (this.mutationEvents) {
            this.dispatchAggregateEvents(attr, attr, oldvalue, (short)1);
        }
    }
    
    void setAttrNode(final AttrImpl attr, final AttrImpl previous) {
        if (this.mutationEvents) {
            if (previous == null) {
                this.dispatchAggregateEvents(attr.ownerNode, attr, null, (short)2);
            }
            else {
                this.dispatchAggregateEvents(attr.ownerNode, attr, previous.getNodeValue(), (short)1);
            }
        }
    }
    
    void removedAttrNode(final AttrImpl attr, final NodeImpl oldOwner, final String name) {
        if (this.mutationEvents) {
            final LCount lc = LCount.lookup("DOMAttrModified");
            if (lc.captures + lc.bubbles + lc.defaults > 0) {
                final MutationEventImpl me = new MutationEventImpl();
                me.initMutationEvent("DOMAttrModified", true, false, attr, attr.getNodeValue(), null, name, (short)3);
                this.dispatchEvent(oldOwner, me);
            }
            this.dispatchAggregateEvents(oldOwner, null, null, (short)0);
        }
    }
    
    void renamedAttrNode(final Attr oldAt, final Attr newAt) {
    }
    
    void renamedElement(final Element oldEl, final Element newEl) {
    }
    
    class LEntry implements Serializable
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
    
    class EnclosingAttr implements Serializable
    {
        AttrImpl node;
        String oldvalue;
    }
}
