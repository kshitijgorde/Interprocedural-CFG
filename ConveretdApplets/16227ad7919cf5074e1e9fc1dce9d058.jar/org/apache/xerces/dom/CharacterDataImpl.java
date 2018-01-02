// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.CharacterData;

public abstract class CharacterDataImpl extends NodeImpl implements CharacterData
{
    static final long serialVersionUID = 7931170150428474230L;
    private static transient NodeList singletonNodeList;
    
    protected CharacterDataImpl(final DocumentImpl documentImpl, final String s) {
        super(documentImpl, null, s);
    }
    
    public abstract String getNodeName();
    
    public NodeList getChildNodes() {
        if (CharacterDataImpl.singletonNodeList == null) {
            CharacterDataImpl.singletonNodeList = new NodeList() {
                public Node item(final int n) {
                    return null;
                }
                
                public int getLength() {
                    return 0;
                }
            };
        }
        return CharacterDataImpl.singletonNodeList;
    }
    
    public String getData() {
        if (super.syncData) {
            this.synchronizeData();
        }
        return super.value;
    }
    
    public int getLength() {
        if (super.syncData) {
            this.synchronizeData();
        }
        return super.value.length();
    }
    
    public void appendData(final String s) {
        if (super.readOnly) {
            throw new DOMExceptionImpl((short)7, "NO_MODIFICATION_ALLOWED_ERR");
        }
        if (super.syncData) {
            this.synchronizeData();
        }
        this.setNodeValue(String.valueOf(super.value) + s);
    }
    
    public void deleteData(final int n, final int n2) throws DOMException {
        if (super.readOnly) {
            throw new DOMExceptionImpl((short)7, "NO_MODIFICATION_ALLOWED_ERR");
        }
        if (n2 < 0) {
            throw new DOMExceptionImpl((short)1, "INDEX_SIZE_ERR");
        }
        if (super.syncData) {
            this.synchronizeData();
        }
        final int max = Math.max(super.value.length() - n2 - n, 0);
        try {
            this.setNodeValue(String.valueOf(super.value.substring(0, n)) + ((max > 0) ? super.value.substring(n + n2, n + n2 + max) : ""));
        }
        catch (StringIndexOutOfBoundsException ex) {
            throw new DOMExceptionImpl((short)1, "INDEX_SIZE_ERR");
        }
    }
    
    public void insertData(final int n, final String s) throws DOMException {
        if (super.readOnly) {
            throw new DOMExceptionImpl((short)7, "NO_MODIFICATION_ALLOWED_ERR");
        }
        if (super.syncData) {
            this.synchronizeData();
        }
        try {
            this.setNodeValue(new StringBuffer(super.value).insert(n, s).toString());
        }
        catch (StringIndexOutOfBoundsException ex) {
            throw new DOMExceptionImpl((short)1, "INDEX_SIZE_ERR");
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
        if (super.syncData) {
            this.synchronizeData();
        }
        final int length = super.value.length();
        if (n2 < 0 || n < 0 || n > length - 1) {
            throw new DOMExceptionImpl((short)1, "INDEX_SIZE_ERR");
        }
        return super.value.substring(n, Math.min(n + n2, length));
    }
}
