// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.w3c.dom.Node;
import org.w3c.dom.DOMException;
import org.w3c.dom.NodeList;

public abstract class CharacterDataImpl extends ChildNode
{
    static final long serialVersionUID = 7931170150428474230L;
    protected String data;
    private static transient NodeList singletonNodeList;
    
    public CharacterDataImpl() {
    }
    
    protected CharacterDataImpl(final CoreDocumentImpl ownerDocument, final String data) {
        super(ownerDocument);
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
    
    protected void setNodeValueInternal(final String value) {
        if (this.isReadOnly()) {
            final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NO_MODIFICATION_ALLOWED_ERR", null);
            throw new DOMException((short)7, msg);
        }
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        final String oldvalue = this.data;
        final CoreDocumentImpl ownerDocument = this.ownerDocument();
        ownerDocument.modifyingCharacterData(this);
        ownerDocument.modifiedCharacterData(this, oldvalue, this.data = value);
    }
    
    public void setNodeValue(final String value) {
        this.setNodeValueInternal(value);
        this.ownerDocument().replacedText(this);
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
    
    public void appendData(final String data) {
        if (this.isReadOnly()) {
            final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NO_MODIFICATION_ALLOWED_ERR", null);
            throw new DOMException((short)7, msg);
        }
        if (data == null) {
            return;
        }
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        this.setNodeValue(this.data + data);
    }
    
    public void deleteData(final int offset, final int count) throws DOMException {
        if (this.isReadOnly()) {
            final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NO_MODIFICATION_ALLOWED_ERR", null);
            throw new DOMException((short)7, msg);
        }
        if (count < 0) {
            final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "INDEX_SIZE_ERR", null);
            throw new DOMException((short)1, msg);
        }
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        final int tailLength = Math.max(this.data.length() - count - offset, 0);
        try {
            final String value = this.data.substring(0, offset) + ((tailLength > 0) ? this.data.substring(offset + count, offset + count + tailLength) : "");
            this.setNodeValueInternal(value);
            this.ownerDocument().deletedText(this, offset, count);
        }
        catch (StringIndexOutOfBoundsException e) {
            final String msg2 = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "INDEX_SIZE_ERR", null);
            throw new DOMException((short)1, msg2);
        }
    }
    
    public void insertData(final int offset, final String data) throws DOMException {
        if (this.isReadOnly()) {
            final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NO_MODIFICATION_ALLOWED_ERR", null);
            throw new DOMException((short)7, msg);
        }
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        try {
            final String value = new StringBuffer(this.data).insert(offset, data).toString();
            this.setNodeValueInternal(value);
            this.ownerDocument().insertedText(this, offset, data.length());
        }
        catch (StringIndexOutOfBoundsException e) {
            final String msg2 = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "INDEX_SIZE_ERR", null);
            throw new DOMException((short)1, msg2);
        }
    }
    
    public void replaceData(final int offset, final int count, final String data) throws DOMException {
        this.deleteData(offset, count);
        this.insertData(offset, data);
    }
    
    public void setData(final String value) throws DOMException {
        this.setNodeValue(value);
    }
    
    public String substringData(final int offset, final int count) throws DOMException {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        final int length = this.data.length();
        if (count < 0 || offset < 0 || offset > length - 1) {
            final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "INDEX_SIZE_ERR", null);
            throw new DOMException((short)1, msg);
        }
        final int tailIndex = Math.min(offset + count, length);
        return this.data.substring(offset, tailIndex);
    }
    
    static {
        CharacterDataImpl.singletonNodeList = new NodeList() {
            public Node item(final int index) {
                return null;
            }
            
            public int getLength() {
                return 0;
            }
        };
    }
}
