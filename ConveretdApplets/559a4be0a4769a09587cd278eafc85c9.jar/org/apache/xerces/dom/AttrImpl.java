// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
    
    protected AttrImpl(final CoreDocumentImpl ownerDocument, final String name) {
        super(ownerDocument);
        this.value = null;
        this.name = name;
        this.isSpecified(true);
        this.hasStringValue(true);
    }
    
    protected AttrImpl() {
        this.value = null;
    }
    
    void rename(final String name) {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        this.name = name;
    }
    
    protected void makeChildNode() {
        if (this.hasStringValue()) {
            if (this.value != null) {
                final TextImpl text = (TextImpl)this.ownerDocument().createTextNode((String)this.value);
                ((NodeImpl)(this.value = text)).isFirstChild(true);
                text.previousSibling = text;
                text.ownerNode = this;
                text.isOwned(true);
            }
            this.hasStringValue(false);
        }
    }
    
    void setOwnerDocument(final CoreDocumentImpl doc) {
        if (this.needsSyncChildren()) {
            this.synchronizeChildren();
        }
        super.setOwnerDocument(doc);
        if (!this.hasStringValue()) {
            for (ChildNode child = (ChildNode)this.value; child != null; child = child.nextSibling) {
                child.setOwnerDocument(doc);
            }
        }
    }
    
    public void setIdAttribute(final boolean id) {
        this.isIdAttribute(id);
    }
    
    public Node cloneNode(final boolean deep) {
        if (this.needsSyncChildren()) {
            this.synchronizeChildren();
        }
        final AttrImpl clone = (AttrImpl)super.cloneNode(deep);
        if (!clone.hasStringValue()) {
            clone.value = null;
            for (Node child = (Node)this.value; child != null; child = child.getNextSibling()) {
                clone.appendChild(child.cloneNode(true));
            }
        }
        clone.isSpecified(true);
        return clone;
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
    
    public void setValue(final String newvalue) {
        if (this.isReadOnly()) {
            final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NO_MODIFICATION_ALLOWED_ERR", null);
            throw new DOMException((short)7, msg);
        }
        final CoreDocumentImpl ownerDocument = this.ownerDocument();
        final Element ownerElement = this.getOwnerElement();
        String oldvalue = "";
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        if (this.needsSyncChildren()) {
            this.synchronizeChildren();
        }
        if (this.value != null) {
            if (ownerDocument.getMutationEvents()) {
                if (this.hasStringValue()) {
                    oldvalue = (String)this.value;
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
                    this.internalRemoveChild(AttrImpl.textNode, true);
                }
                else {
                    oldvalue = this.getValue();
                    while (this.value != null) {
                        this.internalRemoveChild((Node)this.value, true);
                    }
                }
            }
            else {
                if (this.hasStringValue()) {
                    oldvalue = (String)this.value;
                }
                else {
                    oldvalue = this.getValue();
                    final ChildNode firstChild = (ChildNode)this.value;
                    firstChild.previousSibling = null;
                    firstChild.isFirstChild(false);
                    firstChild.ownerNode = ownerDocument;
                }
                this.value = null;
                this.needsSyncChildren(false);
            }
            if (this.isIdAttribute() && ownerElement != null) {
                ownerDocument.removeIdentifier(oldvalue);
            }
        }
        this.isSpecified(true);
        if (ownerDocument.getMutationEvents()) {
            this.internalInsertBefore(ownerDocument.createTextNode(newvalue), null, true);
            this.hasStringValue(false);
            ownerDocument.modifiedAttrValue(this, oldvalue);
        }
        else {
            this.value = newvalue;
            this.hasStringValue(true);
            this.changed();
        }
        if (this.isIdAttribute() && ownerElement != null) {
            ownerDocument.putIdentifier(newvalue, ownerElement);
        }
    }
    
    public String getValue() {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        if (this.needsSyncChildren()) {
            this.synchronizeChildren();
        }
        if (this.value == null) {
            return "";
        }
        if (this.hasStringValue()) {
            return (String)this.value;
        }
        final ChildNode firstChild = (ChildNode)this.value;
        ChildNode node = firstChild.nextSibling;
        if (node == null) {
            return firstChild.getNodeValue();
        }
        final StringBuffer value = new StringBuffer(firstChild.getNodeValue());
        while (node != null) {
            value.append(node.getNodeValue());
            node = node.nextSibling;
        }
        return value.toString();
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
        Node kid;
        Node next;
        for (ChildNode firstChild = (ChildNode)(kid = (ChildNode)this.value); kid != null; kid = next) {
            next = kid.getNextSibling();
            if (kid.getNodeType() == 3) {
                if (next != null && next.getNodeType() == 3) {
                    ((Text)kid).appendData(next.getNodeValue());
                    this.removeChild(next);
                    next = kid;
                }
                else if (kid.getNodeValue().length() == 0) {
                    this.removeChild(kid);
                }
            }
        }
        this.isNormalized(true);
    }
    
    public void setSpecified(final boolean arg) {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        this.isSpecified(arg);
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
    
    final void lastChild(final ChildNode node) {
        if (this.value != null) {
            ((ChildNode)this.value).previousSibling = node;
        }
    }
    
    public Node insertBefore(final Node newChild, final Node refChild) throws DOMException {
        return this.internalInsertBefore(newChild, refChild, false);
    }
    
    Node internalInsertBefore(final Node newChild, Node refChild, final boolean replace) throws DOMException {
        final CoreDocumentImpl ownerDocument = this.ownerDocument();
        final boolean errorChecking = ownerDocument.errorChecking;
        if (newChild.getNodeType() == 11) {
            if (errorChecking) {
                for (Node kid = newChild.getFirstChild(); kid != null; kid = kid.getNextSibling()) {
                    if (!ownerDocument.isKidOK(this, kid)) {
                        final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "HIERARCHY_REQUEST_ERR", null);
                        throw new DOMException((short)3, msg);
                    }
                }
            }
            while (newChild.hasChildNodes()) {
                this.insertBefore(newChild.getFirstChild(), refChild);
            }
            return newChild;
        }
        if (newChild == refChild) {
            refChild = refChild.getNextSibling();
            this.removeChild(newChild);
            this.insertBefore(newChild, refChild);
            return newChild;
        }
        if (this.needsSyncChildren()) {
            this.synchronizeChildren();
        }
        if (errorChecking) {
            if (this.isReadOnly()) {
                final String msg2 = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NO_MODIFICATION_ALLOWED_ERR", null);
                throw new DOMException((short)7, msg2);
            }
            if (newChild.getOwnerDocument() != ownerDocument) {
                final String msg2 = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "WRONG_DOCUMENT_ERR", null);
                throw new DOMException((short)4, msg2);
            }
            if (!ownerDocument.isKidOK(this, newChild)) {
                final String msg2 = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "HIERARCHY_REQUEST_ERR", null);
                throw new DOMException((short)3, msg2);
            }
            if (refChild != null && refChild.getParentNode() != this) {
                final String msg2 = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_FOUND_ERR", null);
                throw new DOMException((short)8, msg2);
            }
            boolean treeSafe = true;
            for (NodeImpl a = this; treeSafe && a != null; treeSafe = (newChild != a), a = a.parentNode()) {}
            if (!treeSafe) {
                final String msg3 = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "HIERARCHY_REQUEST_ERR", null);
                throw new DOMException((short)3, msg3);
            }
        }
        this.makeChildNode();
        ownerDocument.insertingNode(this, replace);
        final ChildNode newInternal = (ChildNode)newChild;
        final Node oldparent = newInternal.parentNode();
        if (oldparent != null) {
            oldparent.removeChild(newInternal);
        }
        final ChildNode refInternal = (ChildNode)refChild;
        newInternal.ownerNode = this;
        newInternal.isOwned(true);
        final ChildNode firstChild = (ChildNode)this.value;
        if (firstChild == null) {
            ((NodeImpl)(this.value = newInternal)).isFirstChild(true);
            newInternal.previousSibling = newInternal;
        }
        else if (refInternal == null) {
            final ChildNode lastChild = firstChild.previousSibling;
            lastChild.nextSibling = newInternal;
            newInternal.previousSibling = lastChild;
            firstChild.previousSibling = newInternal;
        }
        else if (refChild == firstChild) {
            firstChild.isFirstChild(false);
            newInternal.nextSibling = firstChild;
            newInternal.previousSibling = firstChild.previousSibling;
            firstChild.previousSibling = newInternal;
            ((NodeImpl)(this.value = newInternal)).isFirstChild(true);
        }
        else {
            final ChildNode prev = refInternal.previousSibling;
            newInternal.nextSibling = refInternal;
            prev.nextSibling = newInternal;
            refInternal.previousSibling = newInternal;
            newInternal.previousSibling = prev;
        }
        this.changed();
        ownerDocument.insertedNode(this, newInternal, replace);
        this.checkNormalizationAfterInsert(newInternal);
        return newChild;
    }
    
    public Node removeChild(final Node oldChild) throws DOMException {
        if (this.hasStringValue()) {
            throw new DOMException((short)8, "DOM008 Not found");
        }
        return this.internalRemoveChild(oldChild, false);
    }
    
    Node internalRemoveChild(final Node oldChild, final boolean replace) throws DOMException {
        final CoreDocumentImpl ownerDocument = this.ownerDocument();
        if (ownerDocument.errorChecking) {
            if (this.isReadOnly()) {
                final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NO_MODIFICATION_ALLOWED_ERR", null);
                throw new DOMException((short)7, msg);
            }
            if (oldChild != null && oldChild.getParentNode() != this) {
                final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_FOUND_ERR", null);
                throw new DOMException((short)8, msg);
            }
        }
        final ChildNode oldInternal = (ChildNode)oldChild;
        ownerDocument.removingNode(this, oldInternal, replace);
        if (oldInternal == this.value) {
            oldInternal.isFirstChild(false);
            this.value = oldInternal.nextSibling;
            final ChildNode firstChild = (ChildNode)this.value;
            if (firstChild != null) {
                firstChild.isFirstChild(true);
                firstChild.previousSibling = oldInternal.previousSibling;
            }
        }
        else {
            final ChildNode prev = oldInternal.previousSibling;
            final ChildNode next = oldInternal.nextSibling;
            if ((prev.nextSibling = next) == null) {
                final ChildNode firstChild2 = (ChildNode)this.value;
                firstChild2.previousSibling = prev;
            }
            else {
                next.previousSibling = prev;
            }
        }
        final ChildNode oldPreviousSibling = oldInternal.previousSibling();
        oldInternal.ownerNode = ownerDocument;
        oldInternal.isOwned(false);
        oldInternal.nextSibling = null;
        oldInternal.previousSibling = null;
        this.changed();
        ownerDocument.removedNode(this, replace);
        this.checkNormalizationAfterRemove(oldPreviousSibling);
        return oldInternal;
    }
    
    public Node replaceChild(final Node newChild, final Node oldChild) throws DOMException {
        this.makeChildNode();
        final CoreDocumentImpl ownerDocument = this.ownerDocument();
        ownerDocument.replacingNode(this);
        this.internalInsertBefore(newChild, oldChild, true);
        if (newChild != oldChild) {
            this.internalRemoveChild(oldChild, true);
        }
        ownerDocument.replacedNode(this);
        return oldChild;
    }
    
    public int getLength() {
        if (this.hasStringValue()) {
            return 1;
        }
        ChildNode node = (ChildNode)this.value;
        int length = 0;
        while (node != null) {
            ++length;
            node = node.nextSibling;
        }
        return length;
    }
    
    public Node item(final int index) {
        if (!this.hasStringValue()) {
            ChildNode node = (ChildNode)this.value;
            for (int i = 0; i < index && node != null; node = node.nextSibling, ++i) {}
            return node;
        }
        if (index != 0 || this.value == null) {
            return null;
        }
        this.makeChildNode();
        return (Node)this.value;
    }
    
    public boolean isEqualNode(final Node arg) {
        return super.isEqualNode(arg);
    }
    
    public void setReadOnly(final boolean readOnly, final boolean deep) {
        super.setReadOnly(readOnly, deep);
        if (deep) {
            if (this.needsSyncChildren()) {
                this.synchronizeChildren();
            }
            if (this.hasStringValue()) {
                return;
            }
            for (ChildNode mykid = (ChildNode)this.value; mykid != null; mykid = mykid.nextSibling) {
                if (mykid.getNodeType() != 5) {
                    mykid.setReadOnly(readOnly, true);
                }
            }
        }
    }
    
    protected void synchronizeChildren() {
        this.needsSyncChildren(false);
    }
    
    void checkNormalizationAfterInsert(final ChildNode insertedChild) {
        if (insertedChild.getNodeType() == 3) {
            final ChildNode prev = insertedChild.previousSibling();
            final ChildNode next = insertedChild.nextSibling;
            if ((prev != null && prev.getNodeType() == 3) || (next != null && next.getNodeType() == 3)) {
                this.isNormalized(false);
            }
        }
        else if (!insertedChild.isNormalized()) {
            this.isNormalized(false);
        }
    }
    
    void checkNormalizationAfterRemove(final ChildNode previousSibling) {
        if (previousSibling != null && previousSibling.getNodeType() == 3) {
            final ChildNode next = previousSibling.nextSibling;
            if (next != null && next.getNodeType() == 3) {
                this.isNormalized(false);
            }
        }
    }
    
    private void writeObject(final ObjectOutputStream out) throws IOException {
        if (this.needsSyncChildren()) {
            this.synchronizeChildren();
        }
        out.defaultWriteObject();
    }
    
    private void readObject(final ObjectInputStream ois) throws ClassNotFoundException, IOException {
        ois.defaultReadObject();
        this.needsSyncChildren(false);
    }
    
    static {
        AttrImpl.textNode = null;
    }
}
