// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.w3c.dom.events.Event;
import org.apache.xerces.dom.events.MutationEventImpl;
import org.w3c.dom.DOMException;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public abstract class ParentNode extends ChildNode
{
    static final long serialVersionUID = 2815829867152120872L;
    protected DocumentImpl ownerDocument;
    protected ChildNode firstChild;
    protected transient int fCachedLength;
    protected transient ChildNode fCachedChild;
    protected transient int fCachedChildIndex;
    
    protected ParentNode(final DocumentImpl ownerDocument) {
        super(ownerDocument);
        this.firstChild = null;
        this.fCachedLength = -1;
        this.fCachedChildIndex = -1;
        this.ownerDocument = ownerDocument;
    }
    
    public ParentNode() {
        this.firstChild = null;
        this.fCachedLength = -1;
        this.fCachedChildIndex = -1;
    }
    
    public Node cloneNode(final boolean b) {
        if (this.needsSyncChildren()) {
            this.synchronizeChildren();
        }
        final ParentNode parentNode = (ParentNode)super.cloneNode(b);
        parentNode.ownerDocument = this.ownerDocument;
        parentNode.firstChild = null;
        parentNode.fCachedChildIndex = -1;
        parentNode.fCachedLength = -1;
        if (b) {
            for (ChildNode childNode = this.firstChild; childNode != null; childNode = childNode.nextSibling) {
                parentNode.appendChild(childNode.cloneNode(true));
            }
        }
        return parentNode;
    }
    
    public Document getOwnerDocument() {
        return this.ownerDocument;
    }
    
    DocumentImpl ownerDocument() {
        return this.ownerDocument;
    }
    
    void setOwnerDocument(final DocumentImpl ownerDocument) {
        if (this.needsSyncChildren()) {
            this.synchronizeChildren();
        }
        super.setOwnerDocument(ownerDocument);
        this.ownerDocument = ownerDocument;
        for (ChildNode childNode = this.firstChild; childNode != null; childNode = childNode.nextSibling) {
            childNode.setOwnerDocument(ownerDocument);
        }
    }
    
    public boolean hasChildNodes() {
        if (this.needsSyncChildren()) {
            this.synchronizeChildren();
        }
        return this.firstChild != null;
    }
    
    public NodeList getChildNodes() {
        if (this.needsSyncChildren()) {
            this.synchronizeChildren();
        }
        return this;
    }
    
    public Node getFirstChild() {
        if (this.needsSyncChildren()) {
            this.synchronizeChildren();
        }
        return this.firstChild;
    }
    
    public Node getLastChild() {
        if (this.needsSyncChildren()) {
            this.synchronizeChildren();
        }
        return this.lastChild();
    }
    
    final ChildNode lastChild() {
        return (this.firstChild != null) ? this.firstChild.previousSibling : null;
    }
    
    final void lastChild(final ChildNode previousSibling) {
        if (this.firstChild != null) {
            this.firstChild.previousSibling = previousSibling;
        }
    }
    
    public Node insertBefore(final Node node, final Node node2) throws DOMException {
        return this.internalInsertBefore(node, node2, 65535);
    }
    
    Node internalInsertBefore(final Node node, Node nextSibling, final int n) throws DOMException {
        final boolean errorChecking = this.ownerDocument.errorChecking;
        if (node.getNodeType() == 11) {
            if (errorChecking) {
                for (Node node2 = node.getFirstChild(); node2 != null; node2 = node2.getNextSibling()) {
                    if (!this.ownerDocument.isKidOK(this, node2)) {
                        throw new DOMException((short)3, "DOM006 Hierarchy request error");
                    }
                }
            }
            while (node.hasChildNodes()) {
                this.insertBefore(node.getFirstChild(), nextSibling);
            }
            return node;
        }
        if (node == nextSibling) {
            nextSibling = nextSibling.getNextSibling();
            this.removeChild(node);
            this.insertBefore(node, nextSibling);
            return node;
        }
        if (this.needsSyncChildren()) {
            this.synchronizeChildren();
        }
        if (errorChecking) {
            if (this.isReadOnly()) {
                throw new DOMException((short)7, "DOM001 Modification not allowed");
            }
            if (node.getOwnerDocument() != this.ownerDocument) {
                throw new DOMException((short)4, "DOM005 Wrong document");
            }
            if (!this.ownerDocument.isKidOK(this, node)) {
                throw new DOMException((short)3, "DOM006 Hierarchy request error");
            }
            if (nextSibling != null && nextSibling.getParentNode() != this) {
                throw new DOMException((short)8, "DOM008 Not found");
            }
            boolean b = true;
            for (NodeImpl parentNode = this; b && parentNode != null; b = (node != parentNode), parentNode = parentNode.parentNode()) {}
            if (!b) {
                throw new DOMException((short)3, "DOM006 Hierarchy request error");
            }
        }
        EnclosingAttr enclosingAttr = null;
        if (this.ownerDocument.mutationEvents && (n & 0x2) != 0x0) {
            final LCount lookup = LCount.lookup("DOMAttrModified");
            if (lookup.captures + lookup.bubbles + lookup.defaults > 0) {
                enclosingAttr = this.getEnclosingAttr();
            }
        }
        final ChildNode fCachedChild = (ChildNode)node;
        final NodeImpl parentNode2 = fCachedChild.parentNode();
        if (parentNode2 != null) {
            parentNode2.removeChild(fCachedChild);
        }
        final ChildNode nextSibling2 = (ChildNode)nextSibling;
        fCachedChild.ownerNode = this;
        fCachedChild.isOwned(true);
        if (this.firstChild == null) {
            (this.firstChild = fCachedChild).isFirstChild(true);
            fCachedChild.previousSibling = fCachedChild;
        }
        else if (nextSibling2 == null) {
            final ChildNode previousSibling = this.firstChild.previousSibling;
            previousSibling.nextSibling = fCachedChild;
            fCachedChild.previousSibling = previousSibling;
            this.firstChild.previousSibling = fCachedChild;
        }
        else if (nextSibling == this.firstChild) {
            this.firstChild.isFirstChild(false);
            fCachedChild.nextSibling = this.firstChild;
            fCachedChild.previousSibling = this.firstChild.previousSibling;
            this.firstChild.previousSibling = fCachedChild;
            (this.firstChild = fCachedChild).isFirstChild(true);
        }
        else {
            final ChildNode previousSibling2 = nextSibling2.previousSibling;
            fCachedChild.nextSibling = nextSibling2;
            previousSibling2.nextSibling = fCachedChild;
            nextSibling2.previousSibling = fCachedChild;
            fCachedChild.previousSibling = previousSibling2;
        }
        this.changed();
        if (this.fCachedLength != -1) {
            ++this.fCachedLength;
        }
        if (this.fCachedChildIndex != -1) {
            if (this.fCachedChild == nextSibling2) {
                this.fCachedChild = fCachedChild;
            }
            else {
                this.fCachedChildIndex = -1;
            }
        }
        if (this.ownerDocument.mutationEvents) {
            if ((n & 0x1) != 0x0) {
                final LCount lookup2 = LCount.lookup("DOMNodeInserted");
                if (lookup2.captures + lookup2.bubbles + lookup2.defaults > 0) {
                    final MutationEventImpl mutationEventImpl = new MutationEventImpl();
                    mutationEventImpl.initMutationEvent("DOMNodeInserted", true, false, this, null, null, null, (short)0);
                    fCachedChild.dispatchEvent(mutationEventImpl);
                }
                final LCount lookup3 = LCount.lookup("DOMNodeInsertedIntoDocument");
                if (lookup3.captures + lookup3.bubbles + lookup3.defaults > 0) {
                    NodeImpl nodeImpl = this;
                    if (enclosingAttr != null) {
                        nodeImpl = (NodeImpl)enclosingAttr.node.getOwnerElement();
                    }
                    if (nodeImpl != null) {
                        NodeImpl parentNode3 = nodeImpl;
                        while (parentNode3 != null) {
                            nodeImpl = parentNode3;
                            if (parentNode3.getNodeType() == 2) {
                                parentNode3 = (ElementImpl)((AttrImpl)parentNode3).getOwnerElement();
                            }
                            else {
                                parentNode3 = parentNode3.parentNode();
                            }
                        }
                        if (nodeImpl.getNodeType() == 9) {
                            final MutationEventImpl mutationEventImpl2 = new MutationEventImpl();
                            mutationEventImpl2.initMutationEvent("DOMNodeInsertedIntoDocument", false, false, null, null, null, null, (short)0);
                            this.dispatchEventToSubtree(fCachedChild, mutationEventImpl2);
                        }
                    }
                }
            }
            if ((n & 0x2) != 0x0) {
                this.dispatchAggregateEvents(enclosingAttr);
            }
        }
        this.checkNormalizationAfterInsert(fCachedChild);
        return node;
    }
    
    public Node removeChild(final Node node) throws DOMException {
        return this.internalRemoveChild(node, 65535);
    }
    
    Node internalRemoveChild(final Node node, final int n) throws DOMException {
        final DocumentImpl ownerDocument = this.ownerDocument();
        if (ownerDocument.errorChecking) {
            if (this.isReadOnly()) {
                throw new DOMException((short)7, "DOM001 Modification not allowed");
            }
            if (node != null && node.getParentNode() != this) {
                throw new DOMException((short)8, "DOM008 Not found");
            }
        }
        ownerDocument.removedChildNode(node);
        final ChildNode childNode = (ChildNode)node;
        EnclosingAttr enclosingAttr = null;
        if (ownerDocument.mutationEvents) {
            final LCount lookup = LCount.lookup("DOMAttrModified");
            if (lookup.captures + lookup.bubbles + lookup.defaults > 0) {
                enclosingAttr = this.getEnclosingAttr();
            }
            if ((n & 0x1) != 0x0) {
                final LCount lookup2 = LCount.lookup("DOMNodeRemoved");
                if (lookup2.captures + lookup2.bubbles + lookup2.defaults > 0) {
                    final MutationEventImpl mutationEventImpl = new MutationEventImpl();
                    mutationEventImpl.initMutationEvent("DOMNodeRemoved", true, false, this, null, null, null, (short)0);
                    childNode.dispatchEvent(mutationEventImpl);
                }
                final LCount lookup3 = LCount.lookup("DOMNodeRemovedFromDocument");
                if (lookup3.captures + lookup3.bubbles + lookup3.defaults > 0) {
                    NodeImpl nodeImpl = this;
                    if (enclosingAttr != null) {
                        nodeImpl = (NodeImpl)enclosingAttr.node.getOwnerElement();
                    }
                    if (nodeImpl != null) {
                        for (NodeImpl nodeImpl2 = nodeImpl.parentNode(); nodeImpl2 != null; nodeImpl2 = nodeImpl2.parentNode()) {
                            nodeImpl = nodeImpl2;
                        }
                        if (nodeImpl.getNodeType() == 9) {
                            final MutationEventImpl mutationEventImpl2 = new MutationEventImpl();
                            mutationEventImpl2.initMutationEvent("DOMNodeRemovedFromDocument", false, false, null, null, null, null, (short)0);
                            this.dispatchEventToSubtree(childNode, mutationEventImpl2);
                        }
                    }
                }
            }
        }
        if (this.fCachedLength != -1) {
            --this.fCachedLength;
        }
        if (this.fCachedChildIndex != -1) {
            if (this.fCachedChild == childNode) {
                --this.fCachedChildIndex;
                this.fCachedChild = childNode.previousSibling();
            }
            else {
                this.fCachedChildIndex = -1;
            }
        }
        if (childNode == this.firstChild) {
            childNode.isFirstChild(false);
            this.firstChild = childNode.nextSibling;
            if (this.firstChild != null) {
                this.firstChild.isFirstChild(true);
                this.firstChild.previousSibling = childNode.previousSibling;
            }
        }
        else {
            final ChildNode previousSibling = childNode.previousSibling;
            final ChildNode nextSibling = childNode.nextSibling;
            if ((previousSibling.nextSibling = nextSibling) == null) {
                this.firstChild.previousSibling = previousSibling;
            }
            else {
                nextSibling.previousSibling = previousSibling;
            }
        }
        final ChildNode previousSibling2 = childNode.previousSibling();
        childNode.ownerNode = ownerDocument;
        childNode.isOwned(false);
        childNode.nextSibling = null;
        childNode.previousSibling = null;
        this.changed();
        if (ownerDocument.mutationEvents && (n & 0x2) != 0x0) {
            this.dispatchAggregateEvents(enclosingAttr);
        }
        this.checkNormalizationAfterRemove(previousSibling2);
        return childNode;
    }
    
    public Node replaceChild(final Node node, final Node node2) throws DOMException {
        EnclosingAttr enclosingAttr = null;
        if (this.ownerDocument.mutationEvents) {
            final LCount lookup = LCount.lookup("DOMAttrModified");
            if (lookup.captures + lookup.bubbles + lookup.defaults > 0) {
                enclosingAttr = this.getEnclosingAttr();
            }
        }
        this.internalInsertBefore(node, node2, 1);
        if (node != node2) {
            this.internalRemoveChild(node2, 1);
        }
        if (this.ownerDocument.mutationEvents) {
            this.dispatchAggregateEvents(enclosingAttr);
        }
        return node2;
    }
    
    private int nodeListGetLength() {
        if (this.fCachedLength == -1) {
            ChildNode childNode;
            if (this.fCachedChildIndex != -1 && this.fCachedChild != null) {
                this.fCachedLength = this.fCachedChildIndex;
                childNode = this.fCachedChild;
            }
            else {
                childNode = this.firstChild;
                this.fCachedLength = 0;
            }
            while (childNode != null) {
                ++this.fCachedLength;
                childNode = childNode.nextSibling;
            }
        }
        return this.fCachedLength;
    }
    
    public int getLength() {
        return this.nodeListGetLength();
    }
    
    private Node nodeListItem(final int n) {
        if (this.fCachedChildIndex != -1 && this.fCachedChild != null) {
            if (this.fCachedChildIndex < n) {
                while (this.fCachedChildIndex < n) {
                    if (this.fCachedChild == null) {
                        break;
                    }
                    ++this.fCachedChildIndex;
                    this.fCachedChild = this.fCachedChild.nextSibling;
                }
            }
            else if (this.fCachedChildIndex > n) {
                while (this.fCachedChildIndex > n && this.fCachedChild != null) {
                    --this.fCachedChildIndex;
                    this.fCachedChild = this.fCachedChild.previousSibling();
                }
            }
            return this.fCachedChild;
        }
        this.fCachedChild = this.firstChild;
        this.fCachedChildIndex = 0;
        while (this.fCachedChildIndex < n && this.fCachedChild != null) {
            this.fCachedChild = this.fCachedChild.nextSibling;
            ++this.fCachedChildIndex;
        }
        return this.fCachedChild;
    }
    
    public Node item(final int n) {
        return this.nodeListItem(n);
    }
    
    protected final NodeList getChildNodesUnoptimized() {
        if (this.needsSyncChildren()) {
            this.synchronizeChildren();
        }
        return new NodeList() {
            public int getLength() {
                return ParentNode.this.nodeListGetLength();
            }
            
            public Node item(final int n) {
                return ParentNode.this.nodeListItem(n);
            }
        };
    }
    
    public void normalize() {
        if (this.isNormalized()) {
            return;
        }
        if (this.needsSyncChildren()) {
            this.synchronizeChildren();
        }
        for (ChildNode childNode = this.firstChild; childNode != null; childNode = childNode.nextSibling) {
            childNode.normalize();
        }
        this.isNormalized(true);
    }
    
    public void setReadOnly(final boolean b, final boolean b2) {
        super.setReadOnly(b, b2);
        if (b2) {
            if (this.needsSyncChildren()) {
                this.synchronizeChildren();
            }
            for (ChildNode childNode = this.firstChild; childNode != null; childNode = childNode.nextSibling) {
                if (childNode.getNodeType() != 5) {
                    childNode.setReadOnly(b, true);
                }
            }
        }
    }
    
    protected void synchronizeChildren() {
        this.needsSyncChildren(false);
    }
    
    protected final void synchronizeChildren(final int n) {
        final boolean mutationEvents = this.ownerDocument.mutationEvents;
        this.needsSyncChildren(this.ownerDocument.mutationEvents = false);
        final DeferredDocumentImpl deferredDocumentImpl = (DeferredDocumentImpl)this.ownerDocument;
        ChildNode childNode = null;
        ChildNode childNode2 = null;
        for (int i = deferredDocumentImpl.getLastChild(n); i != -1; i = deferredDocumentImpl.getPrevSibling(i)) {
            final ChildNode previousSibling = (ChildNode)deferredDocumentImpl.getNodeObject(i);
            if (childNode2 == null) {
                childNode2 = previousSibling;
            }
            else {
                childNode.previousSibling = previousSibling;
            }
            previousSibling.ownerNode = this;
            previousSibling.isOwned(true);
            previousSibling.nextSibling = childNode;
            childNode = previousSibling;
        }
        if (childNode2 != null) {
            (this.firstChild = childNode).isFirstChild(true);
            this.lastChild(childNode2);
        }
        deferredDocumentImpl.mutationEvents = mutationEvents;
    }
    
    void checkNormalizationAfterInsert(final ChildNode childNode) {
        if (childNode.getNodeType() == 3) {
            final ChildNode previousSibling = childNode.previousSibling();
            final ChildNode nextSibling = childNode.nextSibling;
            if ((previousSibling != null && previousSibling.getNodeType() == 3) || (nextSibling != null && nextSibling.getNodeType() == 3)) {
                this.isNormalized(false);
            }
        }
        else if (!childNode.isNormalized()) {
            this.isNormalized(false);
        }
    }
    
    void checkNormalizationAfterRemove(final ChildNode childNode) {
        if (childNode != null && childNode.getNodeType() == 3) {
            final ChildNode nextSibling = childNode.nextSibling;
            if (nextSibling != null && nextSibling.getNodeType() == 3) {
                this.isNormalized(false);
            }
        }
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        if (this.needsSyncChildren()) {
            this.synchronizeChildren();
        }
        objectOutputStream.defaultWriteObject();
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        this.needsSyncChildren(false);
        this.fCachedLength = -1;
        this.fCachedChildIndex = -1;
    }
}
