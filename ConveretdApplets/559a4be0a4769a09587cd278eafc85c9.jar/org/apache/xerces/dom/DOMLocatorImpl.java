// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.w3c.dom.Node;
import org.apache.xerces.dom3.DOMLocator;

public class DOMLocatorImpl implements DOMLocator
{
    public int fColumnNumber;
    public Node fErrorNode;
    public int fLineNumber;
    public int fOffset;
    public String fUri;
    
    public DOMLocatorImpl() {
        this.fColumnNumber = -1;
        this.fErrorNode = null;
        this.fLineNumber = -1;
        this.fOffset = -1;
        this.fUri = null;
    }
    
    public DOMLocatorImpl(final int lineNumber, final int columnNumber, final String uri) {
        this.fColumnNumber = -1;
        this.fErrorNode = null;
        this.fLineNumber = -1;
        this.fOffset = -1;
        this.fUri = null;
        this.fLineNumber = lineNumber;
        this.fColumnNumber = columnNumber;
        this.fUri = uri;
    }
    
    public DOMLocatorImpl(final int lineNumber, final int columnNumber, final int offset, final Node errorNode, final String uri) {
        this.fColumnNumber = -1;
        this.fErrorNode = null;
        this.fLineNumber = -1;
        this.fOffset = -1;
        this.fUri = null;
        this.fLineNumber = lineNumber;
        this.fColumnNumber = columnNumber;
        this.fOffset = offset;
        this.fErrorNode = errorNode;
        this.fUri = uri;
    }
    
    public int getLineNumber() {
        return this.fLineNumber;
    }
    
    public int getColumnNumber() {
        return this.fColumnNumber;
    }
    
    public int getOffset() {
        return this.fOffset;
    }
    
    public Node getErrorNode() {
        return this.fErrorNode;
    }
    
    public String getUri() {
        return this.fUri;
    }
}
