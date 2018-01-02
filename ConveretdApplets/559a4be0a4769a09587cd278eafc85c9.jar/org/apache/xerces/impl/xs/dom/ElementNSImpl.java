// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.dom;

import org.w3c.dom.DOMException;
import org.apache.xerces.dom.CoreDocumentImpl;

public class ElementNSImpl extends org.apache.xerces.dom.ElementNSImpl
{
    protected int lineNum;
    protected int columnNum;
    
    public ElementNSImpl() {
    }
    
    protected ElementNSImpl(final CoreDocumentImpl ownerDocument, final String namespaceURI, final String qualifiedName, final String localpart, final int lineNum, final int columnNum) throws DOMException {
        super(ownerDocument, namespaceURI, qualifiedName, localpart);
        this.lineNum = lineNum;
        this.columnNum = columnNum;
    }
    
    protected void setValues(final CoreDocumentImpl ownerDocument, final String namespaceURI, final String qualifiedName, final String localpart, final int lineNum, final int columnNum) {
        super.setValues(ownerDocument, namespaceURI, qualifiedName, localpart);
        this.lineNum = lineNum;
        this.columnNum = columnNum;
    }
    
    public int getLineNumber() {
        return this.lineNum;
    }
    
    public int getColumnNumber() {
        return this.columnNum;
    }
}
