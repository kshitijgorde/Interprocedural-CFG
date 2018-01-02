// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.w3c.dom.events.Event;
import org.apache.xerces.dom.events.MutationEventImpl;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.w3c.dom.Element;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.w3c.dom.Attr;

public class AttrImpl extends NodeImpl implements Attr
{
    static final long serialVersionUID = 7277707688218972102L;
    protected Object value;
    protected String name;
    protected static TextImpl textNode;
    
    protected AttrImpl(final DocumentImpl documentImpl, final String name) {
        super(documentImpl);
        this.value = null;
        this.name = name;
        this.isSpecified(true);
        this.hasStringValue(true);
    }
    
    protected AttrImpl() {
        this.value = null;
    }
    
    protected void makeChildNode() {
        if (this.hasStringValue()) {
            if (this.value != null) {
                final TextImpl textImpl = (TextImpl)this.ownerDocument().createTextNode((String)this.value);
                ((NodeImpl)(this.value = textImpl)).isFirstChild(true);
                textImpl.previousSibling = textImpl;
                textImpl.ownerNode = this;
                textImpl.isOwned(true);
            }
            this.hasStringValue(false);
        }
    }
    
    void setOwnerDocument(final DocumentImpl documentImpl) {
        if (this.needsSyncChildren()) {
            this.synchronizeChildren();
        }
        super.setOwnerDocument(documentImpl);
        if (!this.hasStringValue()) {
            for (ChildNode nextSibling = (ChildNode)this.value; nextSibling != null; nextSibling = nextSibling.nextSibling) {
                nextSibling.setOwnerDocument(documentImpl);
            }
        }
    }
    
    public Node cloneNode(final boolean b) {
        if (this.needsSyncChildren()) {
            this.synchronizeChildren();
        }
        final AttrImpl attrImpl = (AttrImpl)super.cloneNode(b);
        if (!attrImpl.hasStringValue()) {
            attrImpl.value = null;
            if (b) {
                for (Node nextSibling = (Node)this.value; nextSibling != null; nextSibling = nextSibling.getNextSibling()) {
                    attrImpl.appendChild(nextSibling.cloneNode(true));
                }
            }
        }
        attrImpl.isSpecified(true);
        return attrImpl;
    }
    
    public short getNodeType() {
        return 2;
    }
    
    public String getNodeName() {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        return this.name;
    }
    
    public void setNodeValue(final String value) throws DOMException {
        this.setValue(value);
    }
    
    public String getNodeValue() {
        return this.getValue();
    }
    
    public String getName() {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        return this.name;
    }
    
    public void setValue(final String value) {
        if (this.isReadOnly()) {
            throw new DOMException((short)7, "DOM001 Modification not allowed");
        }
        String value2 = "";
        final DocumentImpl ownerDocument = this.ownerDocument();
        if (ownerDocument.mutationEvents) {
            final LCount lookup = LCount.lookup("DOMAttrModified");
            if (lookup.captures + lookup.bubbles + lookup.defaults > 0 && super.ownerNode != null) {
                value2 = this.getValue();
            }
        }
        if (ownerDocument.mutationEvents) {
            if (this.needsSyncChildren()) {
                this.synchronizeChildren();
            }
            if (this.value != null) {
                if (this.hasStringValue()) {
                    if (AttrImpl.textNode == null) {
                        AttrImpl.textNode = (TextImpl)ownerDocument.createTextNode((String)this.value);
                    }
                    else {
                        AttrImpl.textNode.data = (String)this.value;
                    }
                    this.value = AttrImpl.textNode;
                    AttrImpl.textNode.isFirstChild(true);
                    AttrImpl.textNode.previousSibling = AttrImpl.textNode;
                    AttrImpl.textNode.ownerNode = this;
                    AttrImpl.textNode.isOwned(true);
                    this.hasStringValue(false);
                    this.internalRemoveChild(AttrImpl.textNode, 1);
                }
                else {
                    while (this.value != null) {
                        this.internalRemoveChild((Node)this.value, 1);
                    }
                }
            }
        }
        else {
            if (!this.hasStringValue() && this.value != null) {
                final ChildNode childNode = (ChildNode)this.value;
                childNode.previousSibling = null;
                childNode.isFirstChild(false);
            }
            this.value = null;
            this.needsSyncChildren(false);
        }
        this.isSpecified(true);
        if (value != null) {
            if (ownerDocument.mutationEvents) {
                this.internalInsertBefore(ownerDocument.createTextNode(value), null, 1);
                this.hasStringValue(false);
            }
            else {
                this.value = value;
                this.hasStringValue(true);
            }
        }
        this.changed();
        if (ownerDocument.mutationEvents) {
            this.dispatchAggregateEvents(this, value2, (short)1);
        }
    }
    
    public String getValue() {
        if (this.needsSyncChildren()) {
            this.synchronizeChildren();
        }
        if (this.value == null) {
            return "";
        }
        if (this.hasStringValue()) {
            return (String)this.value;
        }
        final ChildNode childNode = (ChildNode)this.value;
        ChildNode childNode2 = childNode.nextSibling;
        if (childNode2 == null) {
            return childNode.getNodeValue();
        }
        final StringBuffer sb = new StringBuffer(childNode.getNodeValue());
        while (childNode2 != null) {
            sb.append(childNode2.getNodeValue());
            childNode2 = childNode2.nextSibling;
        }
        return sb.toString();
    }
    
    public boolean getSpecified() {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        return this.isSpecified();
    }
    
    public Element getElement() {
        return (Element)(this.isOwned() ? super.ownerNode : null);
    }
    
    public Element getOwnerElement() {
        return (Element)(this.isOwned() ? super.ownerNode : null);
    }
    
    public void normalize() {
        if (this.isNormalized() || this.hasStringValue()) {
            return;
        }
        Node nextSibling;
        for (Object o = this.value; o != null; o = nextSibling) {
            nextSibling = ((Node)o).getNextSibling();
            if (((Node)o).getNodeType() == 3) {
                if (nextSibling != null && nextSibling.getNodeType() == 3) {
                    ((Text)o).appendData(nextSibling.getNodeValue());
                    this.removeChild(nextSibling);
                    nextSibling = (Node)o;
                }
                else if (((Node)o).getNodeValue().length() == 0) {
                    this.removeChild((Node)o);
                }
            }
        }
        this.isNormalized(true);
    }
    
    public void setSpecified(final boolean b) {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        this.isSpecified(b);
    }
    
    public String toString() {
        return this.getName() + "=" + "\"" + this.getValue() + "\"";
    }
    
    public boolean hasChildNodes() {
        if (this.needsSyncChildren()) {
            this.synchronizeChildren();
        }
        return this.value != null;
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
        this.makeChildNode();
        return (Node)this.value;
    }
    
    public Node getLastChild() {
        if (this.needsSyncChildren()) {
            this.synchronizeChildren();
        }
        return this.lastChild();
    }
    
    final ChildNode lastChild() {
        this.makeChildNode();
        return (this.value != null) ? ((ChildNode)this.value).previousSibling : null;
    }
    
    final void lastChild(final ChildNode previousSibling) {
        if (this.value != null) {
            ((ChildNode)this.value).previousSibling = previousSibling;
        }
    }
    
    public Node insertBefore(final Node node, final Node node2) throws DOMException {
        return this.internalInsertBefore(node, node2, 65535);
    }
    
    Node internalInsertBefore(final Node node, Node nextSibling, final int n) throws DOMException {
        final DocumentImpl ownerDocument = this.ownerDocument();
        final boolean errorChecking = ownerDocument.errorChecking;
        if (node.getNodeType() == 11) {
            if (errorChecking) {
                for (Node node2 = node.getFirstChild(); node2 != null; node2 = node2.getNextSibling()) {
                    if (!ownerDocument.isKidOK(this, node2)) {
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
            if (node.getOwnerDocument() != ownerDocument) {
                throw new DOMException((short)4, "DOM005 Wrong document");
            }
            if (!ownerDocument.isKidOK(this, node)) {
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
        this.makeChildNode();
        EnclosingAttr enclosingAttr = null;
        if (ownerDocument.mutationEvents && (n & 0x2) != 0x0) {
            final LCount lookup = LCount.lookup("DOMAttrModified");
            if (lookup.captures + lookup.bubbles + lookup.defaults > 0) {
                enclosingAttr = this.getEnclosingAttr();
            }
        }
        final ChildNode childNode = (ChildNode)node;
        final NodeImpl parentNode2 = childNode.parentNode();
        if (parentNode2 != null) {
            parentNode2.removeChild(childNode);
        }
        final ChildNode nextSibling2 = (ChildNode)nextSibling;
        childNode.ownerNode = this;
        childNode.isOwned(true);
        final ChildNode nextSibling3 = (ChildNode)this.value;
        if (nextSibling3 == null) {
            ((NodeImpl)(this.value = childNode)).isFirstChild(true);
            childNode.previousSibling = childNode;
        }
        else if (nextSibling2 == null) {
            final ChildNode previousSibling = nextSibling3.previousSibling;
            previousSibling.nextSibling = childNode;
            childNode.previousSibling = previousSibling;
            nextSibling3.previousSibling = childNode;
        }
        else if (nextSibling == nextSibling3) {
            nextSibling3.isFirstChild(false);
            childNode.nextSibling = nextSibling3;
            childNode.previousSibling = nextSibling3.previousSibling;
            nextSibling3.previousSibling = childNode;
            ((NodeImpl)(this.value = childNode)).isFirstChild(true);
        }
        else {
            final ChildNode previousSibling2 = nextSibling2.previousSibling;
            childNode.nextSibling = nextSibling2;
            previousSibling2.nextSibling = childNode;
            nextSibling2.previousSibling = childNode;
            childNode.previousSibling = previousSibling2;
        }
        this.changed();
        if (ownerDocument.mutationEvents) {
            if ((n & 0x1) != 0x0) {
                final LCount lookup2 = LCount.lookup("DOMNodeInserted");
                if (lookup2.captures + lookup2.bubbles + lookup2.defaults > 0) {
                    final MutationEventImpl mutationEventImpl = new MutationEventImpl();
                    mutationEventImpl.initMutationEvent("DOMNodeInserted", true, false, this, null, null, null, (short)0);
                    childNode.dispatchEvent(mutationEventImpl);
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
                            this.dispatchEventToSubtree(childNode, mutationEventImpl2);
                        }
                    }
                }
            }
            if ((n & 0x2) != 0x0) {
                this.dispatchAggregateEvents(enclosingAttr);
            }
        }
        this.checkNormalizationAfterInsert(childNode);
        return node;
    }
    
    public Node removeChild(final Node node) throws DOMException {
        if (this.hasStringValue()) {
            throw new DOMException((short)8, "DOM008 Not found");
        }
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
        if (childNode == this.value) {
            childNode.isFirstChild(false);
            this.value = childNode.nextSibling;
            final ChildNode childNode2 = (ChildNode)this.value;
            if (childNode2 != null) {
                childNode2.isFirstChild(true);
                childNode2.previousSibling = childNode.previousSibling;
            }
        }
        else {
            final ChildNode previousSibling = childNode.previousSibling;
            final ChildNode nextSibling = childNode.nextSibling;
            if ((previousSibling.nextSibling = nextSibling) == null) {
                ((ChildNode)this.value).previousSibling = previousSibling;
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
        this.makeChildNode();
        EnclosingAttr enclosingAttr = null;
        final DocumentImpl ownerDocument = this.ownerDocument();
        if (ownerDocument.mutationEvents) {
            final LCount lookup = LCount.lookup("DOMAttrModified");
            if (lookup.captures + lookup.bubbles + lookup.defaults > 0) {
                enclosingAttr = this.getEnclosingAttr();
            }
        }
        this.internalInsertBefore(node, node2, 1);
        if (node != node2) {
            this.internalRemoveChild(node2, 1);
        }
        if (ownerDocument.mutationEvents) {
            this.dispatchAggregateEvents(enclosingAttr);
        }
        return node2;
    }
    
    public int getLength() {
        if (this.hasStringValue()) {
            return 1;
        }
        ChildNode nextSibling = (ChildNode)this.value;
        int n = 0;
        while (nextSibling != null) {
            ++n;
            nextSibling = nextSibling.nextSibling;
        }
        return n;
    }
    
    public Node item(final int n) {
        if (!this.hasStringValue()) {
            ChildNode nextSibling = (ChildNode)this.value;
            for (int n2 = 0; n2 < n && nextSibling != null; nextSibling = nextSibling.nextSibling, ++n2) {}
            return nextSibling;
        }
        if (n != 0 || this.value == null) {
            return null;
        }
        this.makeChildNode();
        return (Node)this.value;
    }
    
    public void setReadOnly(final boolean b, final boolean b2) {
        super.setReadOnly(b, b2);
        if (b2) {
            if (this.needsSyncChildren()) {
                this.synchronizeChildren();
            }
            if (this.hasStringValue()) {
                return;
            }
            for (ChildNode nextSibling = (ChildNode)this.value; nextSibling != null; nextSibling = nextSibling.nextSibling) {
                if (nextSibling.getNodeType() != 5) {
                    nextSibling.setReadOnly(b, true);
                }
            }
        }
    }
    
    protected void synchronizeChildren() {
        this.needsSyncChildren(false);
    }
    
    protected final void synchronizeChildren(final int n) {
        final DeferredDocumentImpl deferredDocumentImpl = (DeferredDocumentImpl)this.ownerDocument();
        final boolean mutationEvents = deferredDocumentImpl.mutationEvents;
        this.needsSyncChildren(deferredDocumentImpl.mutationEvents = false);
        final int lastChild = deferredDocumentImpl.getLastChild(n);
        if (deferredDocumentImpl.getPrevSibling(lastChild) == -1) {
            this.value = deferredDocumentImpl.getNodeValueString(lastChild);
            this.hasStringValue(true);
        }
        else {
            ChildNode childNode = null;
            ChildNode childNode2 = null;
            for (int i = lastChild; i != -1; i = deferredDocumentImpl.getPrevSibling(i)) {
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
                ((NodeImpl)(this.value = childNode)).isFirstChild(true);
                this.lastChild(childNode2);
            }
            this.hasStringValue(false);
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
    }
    
    static {
        AttrImpl.textNode = null;
    }
}
