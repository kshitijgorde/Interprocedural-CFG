// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.serializer.dom3;

import org.w3c.dom.Node;
import org.w3c.dom.DOMLocator;

final class DOMLocatorImpl implements DOMLocator
{
    private final int fColumnNumber;
    private final int fLineNumber;
    private final Node fRelatedNode;
    private final String fUri;
    private final int fByteOffset;
    private final int fUtf16Offset;
    
    DOMLocatorImpl() {
        this.fColumnNumber = -1;
        this.fLineNumber = -1;
        this.fRelatedNode = null;
        this.fUri = null;
        this.fByteOffset = -1;
        this.fUtf16Offset = -1;
    }
    
    public DOMLocatorImpl(final int lineNumber, final int columnNumber, final String uri) {
        this.fLineNumber = lineNumber;
        this.fColumnNumber = columnNumber;
        this.fUri = uri;
        this.fRelatedNode = null;
        this.fByteOffset = -1;
        this.fUtf16Offset = -1;
    }
    
    public DOMLocatorImpl(final int lineNumber, final int columnNumber, final int utf16Offset, final String uri) {
        this.fLineNumber = lineNumber;
        this.fColumnNumber = columnNumber;
        this.fUri = uri;
        this.fUtf16Offset = utf16Offset;
        this.fRelatedNode = null;
        this.fByteOffset = -1;
    }
    
    public DOMLocatorImpl(final int lineNumber, final int columnNumber, final int byteoffset, final Node relatedData, final String uri) {
        this.fLineNumber = lineNumber;
        this.fColumnNumber = columnNumber;
        this.fByteOffset = byteoffset;
        this.fRelatedNode = relatedData;
        this.fUri = uri;
        this.fUtf16Offset = -1;
    }
    
    public DOMLocatorImpl(final int lineNumber, final int columnNumber, final int byteoffset, final Node relatedData, final String uri, final int utf16Offset) {
        this.fLineNumber = lineNumber;
        this.fColumnNumber = columnNumber;
        this.fByteOffset = byteoffset;
        this.fRelatedNode = relatedData;
        this.fUri = uri;
        this.fUtf16Offset = utf16Offset;
    }
    
    public int getLineNumber() {
        return this.fLineNumber;
    }
    
    public int getColumnNumber() {
        return this.fColumnNumber;
    }
    
    public String getUri() {
        return this.fUri;
    }
    
    public Node getRelatedNode() {
        return this.fRelatedNode;
    }
    
    public int getByteOffset() {
        return this.fByteOffset;
    }
    
    public int getUtf16Offset() {
        return this.fUtf16Offset;
    }
}
