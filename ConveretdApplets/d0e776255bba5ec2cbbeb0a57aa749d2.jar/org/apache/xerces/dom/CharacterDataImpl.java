// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.w3c.dom.events.Event;
import org.apache.xerces.dom.events.MutationEventImpl;
import org.w3c.dom.Node;
import org.w3c.dom.DOMException;
import org.w3c.dom.NodeList;

public abstract class CharacterDataImpl extends ChildNode
{
    static final long serialVersionUID = 7931170150428474230L;
    protected String data;
    private static transient NodeList singletonNodeList;
    
    protected CharacterDataImpl(final DocumentImpl documentImpl, final String data) {
        super(documentImpl);
        this.data = data;
    }
    
    public NodeList getChildNodes() {
        return CharacterDataImpl.singletonNodeList;
    }
    
    public String getNodeValue() {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        return this.data;
    }
    
    void setNodeValueInternal(final String nodeValue) {
        this.setValueCalled(true);
        this.setNodeValue(nodeValue);
        this.setValueCalled(false);
    }
    
    public void setNodeValue(final String data) {
        if (this.isReadOnly()) {
            throw new DOMException((short)7, "DOM001 Modification not allowed");
        }
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        final String data2 = this.data;
        EnclosingAttr enclosingAttr = null;
        final LCount lookup = LCount.lookup("DOMAttrModified");
        if (lookup.captures + lookup.bubbles + lookup.defaults > 0) {
            enclosingAttr = this.getEnclosingAttr();
        }
        this.data = data;
        if (!this.setValueCalled()) {
            this.ownerDocument().replacedText(this);
        }
        final LCount lookup2 = LCount.lookup("DOMCharacterDataModified");
        if (lookup2.captures + lookup2.bubbles + lookup2.defaults > 0) {
            final MutationEventImpl mutationEventImpl = new MutationEventImpl();
            mutationEventImpl.initMutationEvent("DOMCharacterDataModified", true, false, null, data2, data, null, (short)0);
            this.dispatchEvent(mutationEventImpl);
        }
        this.dispatchAggregateEvents(enclosingAttr);
    }
    
    public String getData() {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        return this.data;
    }
    
    public int getLength() {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        return this.data.length();
    }
    
    public void appendData(final String s) {
        if (this.isReadOnly()) {
            throw new DOMException((short)7, "DOM001 Modification not allowed");
        }
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        this.setNodeValue(this.data + s);
    }
    
    public void deleteData(final int n, final int n2) throws DOMException {
        if (this.isReadOnly()) {
            throw new DOMException((short)7, "DOM001 Modification not allowed");
        }
        if (n2 < 0) {
            throw new DOMException((short)1, "DOM004 Index out of bounds");
        }
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        final int max = Math.max(this.data.length() - n2 - n, 0);
        try {
            this.setNodeValueInternal(this.data.substring(0, n) + ((max > 0) ? this.data.substring(n + n2, n + n2 + max) : ""));
            this.ownerDocument().deletedText(this, n, n2);
        }
        catch (StringIndexOutOfBoundsException ex) {
            throw new DOMException((short)1, "DOM004 Index out of bounds");
        }
    }
    
    public void insertData(final int n, final String s) throws DOMException {
        if (this.isReadOnly()) {
            throw new DOMException((short)7, "DOM001 Modification not allowed");
        }
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        try {
            this.setNodeValueInternal(new StringBuffer(this.data).insert(n, s).toString());
            this.ownerDocument().insertedText(this, n, s.length());
        }
        catch (StringIndexOutOfBoundsException ex) {
            throw new DOMException((short)1, "DOM004 Index out of bounds");
        }
    }
    
    public void replaceData(final int n, final int n2, final String s) throws DOMException {
        this.deleteData(n, n2);
        this.insertData(n, s);
    }
    
    public void setData(final String nodeValue) throws DOMException {
        this.setNodeValue(nodeValue);
    }
    
    public String substringData(final int n, final int n2) throws DOMException {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        final int length = this.data.length();
        if (n2 < 0 || n < 0 || n > length - 1) {
            throw new DOMException((short)1, "DOM004 Index out of bounds");
        }
        return this.data.substring(n, Math.min(n + n2, length));
    }
    
    static {
        CharacterDataImpl.singletonNodeList = new NodeList() {
            public Node item(final int n) {
                return null;
            }
            
            public int getLength() {
                return 0;
            }
        };
    }
}
