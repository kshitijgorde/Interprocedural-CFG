// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.w3c.dom.DOMException;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public abstract class ParentNode extends ChildNode
{
    static final long serialVersionUID = 2815829867152120872L;
    protected CoreDocumentImpl ownerDocument;
    protected ChildNode firstChild;
    protected transient NodeListCache fNodeListCache;
    
    protected ParentNode(final CoreDocumentImpl ownerDocument) {
        super(ownerDocument);
        this.firstChild = null;
        this.fNodeListCache = null;
        this.ownerDocument = ownerDocument;
    }
    
    public ParentNode() {
        this.firstChild = null;
        this.fNodeListCache = null;
    }
    
    public Node cloneNode(final boolean deep) {
        if (this.needsSyncChildren()) {
            this.synchronizeChildren();
        }
        final ParentNode newnode = (ParentNode)super.cloneNode(deep);
        newnode.ownerDocument = this.ownerDocument;
        newnode.firstChild = null;
        newnode.fNodeListCache = null;
        if (deep) {
            for (ChildNode child = this.firstChild; child != null; child = child.nextSibling) {
                newnode.appendChild(child.cloneNode(true));
            }
        }
        return newnode;
    }
    
    public Document getOwnerDocument() {
        return this.ownerDocument;
    }
    
    CoreDocumentImpl ownerDocument() {
        return this.ownerDocument;
    }
    
    void setOwnerDocument(final CoreDocumentImpl doc) {
        if (this.needsSyncChildren()) {
            this.synchronizeChildren();
        }
        super.setOwnerDocument(doc);
        this.ownerDocument = doc;
        for (ChildNode child = this.firstChild; child != null; child = child.nextSibling) {
            child.setOwnerDocument(doc);
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
    
    final void lastChild(final ChildNode node) {
        if (this.firstChild != null) {
            this.firstChild.previousSibling = node;
        }
    }
    
    public Node insertBefore(final Node newChild, final Node refChild) throws DOMException {
        return this.internalInsertBefore(newChild, refChild, false);
    }
    
    Node internalInsertBefore(final Node newChild, Node refChild, final boolean replace) throws DOMException {
        final boolean errorChecking = this.ownerDocument.errorChecking;
        if (newChild.getNodeType() == 11) {
            if (errorChecking) {
                for (Node kid = newChild.getFirstChild(); kid != null; kid = kid.getNextSibling()) {
                    if (!this.ownerDocument.isKidOK(this, kid)) {
                        throw new DOMException((short)3, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "HIERARCHY_REQUEST_ERR", null));
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
                throw new DOMException((short)7, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NO_MODIFICATION_ALLOWED_ERR", null));
            }
            if (newChild.getOwnerDocument() != this.ownerDocument) {
                throw new DOMException((short)4, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "WRONG_DOCUMENT_ERR", null));
            }
            if (!this.ownerDocument.isKidOK(this, newChild)) {
                throw new DOMException((short)3, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "HIERARCHY_REQUEST_ERR", null));
            }
            if (refChild != null && refChild.getParentNode() != this) {
                throw new DOMException((short)8, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_FOUND_ERR", null));
            }
            boolean treeSafe = true;
            for (NodeImpl a = this; treeSafe && a != null; treeSafe = (newChild != a), a = a.parentNode()) {}
            if (!treeSafe) {
                throw new DOMException((short)3, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "HIERARCHY_REQUEST_ERR", null));
            }
        }
        this.ownerDocument.insertingNode(this, replace);
        final ChildNode newInternal = (ChildNode)newChild;
        final Node oldparent = newInternal.parentNode();
        if (oldparent != null) {
            oldparent.removeChild(newInternal);
        }
        final ChildNode refInternal = (ChildNode)refChild;
        newInternal.ownerNode = this;
        newInternal.isOwned(true);
        if (this.firstChild == null) {
            (this.firstChild = newInternal).isFirstChild(true);
            newInternal.previousSibling = newInternal;
        }
        else if (refInternal == null) {
            final ChildNode lastChild = this.firstChild.previousSibling;
            lastChild.nextSibling = newInternal;
            newInternal.previousSibling = lastChild;
            this.firstChild.previousSibling = newInternal;
        }
        else if (refChild == this.firstChild) {
            this.firstChild.isFirstChild(false);
            newInternal.nextSibling = this.firstChild;
            newInternal.previousSibling = this.firstChild.previousSibling;
            this.firstChild.previousSibling = newInternal;
            (this.firstChild = newInternal).isFirstChild(true);
        }
        else {
            final ChildNode prev = refInternal.previousSibling;
            newInternal.nextSibling = refInternal;
            prev.nextSibling = newInternal;
            refInternal.previousSibling = newInternal;
            newInternal.previousSibling = prev;
        }
        this.changed();
        if (this.fNodeListCache != null) {
            if (this.fNodeListCache.fLength != -1) {
                final NodeListCache fNodeListCache = this.fNodeListCache;
                ++fNodeListCache.fLength;
            }
            if (this.fNodeListCache.fChildIndex != -1) {
                if (this.fNodeListCache.fChild == refInternal) {
                    this.fNodeListCache.fChild = newInternal;
                }
                else {
                    this.fNodeListCache.fChildIndex = -1;
                }
            }
        }
        this.ownerDocument.insertedNode(this, newInternal, replace);
        this.checkNormalizationAfterInsert(newInternal);
        return newChild;
    }
    
    public Node removeChild(final Node oldChild) throws DOMException {
        return this.internalRemoveChild(oldChild, false);
    }
    
    Node internalRemoveChild(final Node oldChild, final boolean replace) throws DOMException {
        final CoreDocumentImpl ownerDocument = this.ownerDocument();
        if (ownerDocument.errorChecking) {
            if (this.isReadOnly()) {
                throw new DOMException((short)7, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NO_MODIFICATION_ALLOWED_ERR", null));
            }
            if (oldChild != null && oldChild.getParentNode() != this) {
                throw new DOMException((short)8, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_FOUND_ERR", null));
            }
        }
        final ChildNode oldInternal = (ChildNode)oldChild;
        ownerDocument.removingNode(this, oldInternal, replace);
        if (this.fNodeListCache != null) {
            if (this.fNodeListCache.fLength != -1) {
                final NodeListCache fNodeListCache = this.fNodeListCache;
                --fNodeListCache.fLength;
            }
            if (this.fNodeListCache.fChildIndex != -1) {
                if (this.fNodeListCache.fChild == oldInternal) {
                    final NodeListCache fNodeListCache2 = this.fNodeListCache;
                    --fNodeListCache2.fChildIndex;
                    this.fNodeListCache.fChild = oldInternal.previousSibling();
                }
                else {
                    this.fNodeListCache.fChildIndex = -1;
                }
            }
        }
        if (oldInternal == this.firstChild) {
            oldInternal.isFirstChild(false);
            this.firstChild = oldInternal.nextSibling;
            if (this.firstChild != null) {
                this.firstChild.isFirstChild(true);
                this.firstChild.previousSibling = oldInternal.previousSibling;
            }
        }
        else {
            final ChildNode prev = oldInternal.previousSibling;
            final ChildNode next = oldInternal.nextSibling;
            if ((prev.nextSibling = next) == null) {
                this.firstChild.previousSibling = prev;
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
        this.ownerDocument.replacingNode(this);
        this.internalInsertBefore(newChild, oldChild, true);
        if (newChild != oldChild) {
            this.internalRemoveChild(oldChild, true);
        }
        this.ownerDocument.replacedNode(this);
        return oldChild;
    }
    
    public String getTextContent() throws DOMException {
        final Node child = this.getFirstChild();
        if (child == null) {
            return "";
        }
        final Node next = child.getNextSibling();
        if (next == null) {
            return this.hasTextContent(child) ? child.getNodeValue() : "";
        }
        final StringBuffer buf = new StringBuffer();
        this.getTextContent(buf);
        return buf.toString();
    }
    
    void getTextContent(final StringBuffer buf) throws DOMException {
        for (Node child = this.getFirstChild(); child != null; child = child.getNextSibling()) {
            if (this.hasTextContent(child)) {
                ((NodeImpl)child).getTextContent(buf);
            }
        }
    }
    
    final boolean hasTextContent(final Node child) {
        return child.getNodeType() != 8 && child.getNodeType() != 7 && (child.getNodeType() != 3 || !((TextImpl)child).isIgnorableWhitespace());
    }
    
    public void setTextContent(final String textContent) throws DOMException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: goto            9
        //     3: aload_0         /* this */
        //     4: aload_2        
        //     5: invokevirtual   org/apache/xerces/dom/ParentNode.removeChild:(Lorg/w3c/dom/Node;)Lorg/w3c/dom/Node;
        //     8: pop            
        //     9: aload_0         /* this */
        //    10: invokevirtual   org/apache/xerces/dom/ParentNode.getFirstChild:()Lorg/w3c/dom/Node;
        //    13: dup            
        //    14: astore_2        /* child */
        //    15: ifnonnull       3
        //    18: aload_0         /* this */
        //    19: aload_0         /* this */
        //    20: invokevirtual   org/apache/xerces/dom/ParentNode.ownerDocument:()Lorg/apache/xerces/dom/CoreDocumentImpl;
        //    23: aload_1         /* textContent */
        //    24: invokevirtual   org/apache/xerces/dom/CoreDocumentImpl.createTextNode:(Ljava/lang/String;)Lorg/w3c/dom/Text;
        //    27: invokevirtual   org/apache/xerces/dom/NodeImpl.appendChild:(Lorg/w3c/dom/Node;)Lorg/w3c/dom/Node;
        //    30: pop            
        //    31: return         
        //    Exceptions:
        //  throws org.w3c.dom.DOMException
        //    LocalVariableTable:
        //  Start  Length  Slot  Name         Signature
        //  -----  ------  ----  -----------  ----------------------------------
        //  0      32      0     this         Lorg/apache/xerces/dom/ParentNode;
        //  0      32      1     textContent  Ljava/lang/String;
        //  15     16      2     child        Lorg/w3c/dom/Node;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:2985)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private int nodeListGetLength() {
        if (this.fNodeListCache == null) {
            if (this.firstChild == null) {
                return 0;
            }
            if (this.firstChild == this.lastChild()) {
                return 1;
            }
            this.fNodeListCache = this.ownerDocument.getNodeListCache(this);
        }
        if (this.fNodeListCache.fLength == -1) {
            int l;
            ChildNode n;
            if (this.fNodeListCache.fChildIndex != -1 && this.fNodeListCache.fChild != null) {
                l = this.fNodeListCache.fChildIndex;
                n = this.fNodeListCache.fChild;
            }
            else {
                n = this.firstChild;
                l = 0;
            }
            while (n != null) {
                ++l;
                n = n.nextSibling;
            }
            this.fNodeListCache.fLength = l;
        }
        return this.fNodeListCache.fLength;
    }
    
    public int getLength() {
        return this.nodeListGetLength();
    }
    
    private Node nodeListItem(final int index) {
        if (this.fNodeListCache == null) {
            if (this.firstChild == this.lastChild()) {
                return (index == 0) ? this.firstChild : null;
            }
            this.fNodeListCache = this.ownerDocument.getNodeListCache(this);
        }
        int i = this.fNodeListCache.fChildIndex;
        ChildNode n = this.fNodeListCache.fChild;
        boolean firstAccess = true;
        if (i != -1 && n != null) {
            firstAccess = false;
            if (i < index) {
                while (i < index) {
                    if (n == null) {
                        break;
                    }
                    ++i;
                    n = n.nextSibling;
                }
            }
            else if (i > index) {
                while (i > index) {
                    if (n == null) {
                        break;
                    }
                    --i;
                    n = n.previousSibling();
                }
            }
        }
        else {
            for (n = this.firstChild, i = 0; i < index && n != null; n = n.nextSibling, ++i) {}
        }
        if (!firstAccess && (n == this.firstChild || n == this.lastChild())) {
            this.fNodeListCache.fChildIndex = -1;
            this.fNodeListCache.fChild = null;
            this.ownerDocument.freeNodeListCache(this.fNodeListCache);
        }
        else {
            this.fNodeListCache.fChildIndex = i;
            this.fNodeListCache.fChild = n;
        }
        return n;
    }
    
    public Node item(final int index) {
        return this.nodeListItem(index);
    }
    
    protected final NodeList getChildNodesUnoptimized() {
        if (this.needsSyncChildren()) {
            this.synchronizeChildren();
        }
        return new NodeList() {
            public int getLength() {
                return ParentNode.this.nodeListGetLength();
            }
            
            public Node item(final int index) {
                return ParentNode.this.nodeListItem(index);
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
        for (ChildNode kid = this.firstChild; kid != null; kid = kid.nextSibling) {
            kid.normalize();
        }
        this.isNormalized(true);
    }
    
    public boolean isEqualNode(final Node arg) {
        if (!super.isEqualNode(arg)) {
            return false;
        }
        Node child1;
        Node child2;
        for (child1 = this.getFirstChild(), child2 = arg.getFirstChild(); child1 != null && child2 != null; child1 = child1.getNextSibling(), child2 = child2.getNextSibling()) {
            if (!((NodeImpl)child1).isEqualNode(child2)) {
                return false;
            }
        }
        return child1 == child2;
    }
    
    public void setReadOnly(final boolean readOnly, final boolean deep) {
        super.setReadOnly(readOnly, deep);
        if (deep) {
            if (this.needsSyncChildren()) {
                this.synchronizeChildren();
            }
            for (ChildNode mykid = this.firstChild; mykid != null; mykid = mykid.nextSibling) {
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
}
