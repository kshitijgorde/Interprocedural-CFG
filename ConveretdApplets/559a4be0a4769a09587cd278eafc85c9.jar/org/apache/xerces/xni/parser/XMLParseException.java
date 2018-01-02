// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.xni.parser;

import org.apache.xerces.xni.XMLLocator;
import org.apache.xerces.xni.XNIException;

public class XMLParseException extends XNIException
{
    protected String fPublicId;
    protected String fLiteralSystemId;
    protected String fExpandedSystemId;
    protected String fBaseSystemId;
    protected int fLineNumber;
    protected int fColumnNumber;
    
    public XMLParseException(final XMLLocator locator, final String message) {
        super(message);
        this.fLineNumber = -1;
        this.fColumnNumber = -1;
        if (locator != null) {
            this.fPublicId = locator.getPublicId();
            this.fLiteralSystemId = locator.getLiteralSystemId();
            this.fExpandedSystemId = locator.getExpandedSystemId();
            this.fBaseSystemId = locator.getBaseSystemId();
            this.fLineNumber = locator.getLineNumber();
            this.fColumnNumber = locator.getColumnNumber();
        }
    }
    
    public XMLParseException(final XMLLocator locator, final String message, final Exception exception) {
        super(message, exception);
        this.fLineNumber = -1;
        this.fColumnNumber = -1;
        this.fPublicId = locator.getPublicId();
        this.fLiteralSystemId = locator.getLiteralSystemId();
        this.fExpandedSystemId = locator.getExpandedSystemId();
        this.fBaseSystemId = locator.getBaseSystemId();
        this.fLineNumber = locator.getLineNumber();
        this.fColumnNumber = locator.getColumnNumber();
    }
    
    public String getPublicId() {
        return this.fPublicId;
    }
    
    public String getExpandedSystemId() {
        return this.fExpandedSystemId;
    }
    
    public String getLiteralSystemId() {
        return this.fLiteralSystemId;
    }
    
    public String getBaseSystemId() {
        return this.fBaseSystemId;
    }
    
    public int getLineNumber() {
        return this.fLineNumber;
    }
    
    public int getColumnNumber() {
        return this.fColumnNumber;
    }
    
    public String toString() {
        final StringBuffer str = new StringBuffer();
        if (this.fPublicId != null) {
            str.append(this.fPublicId);
        }
        str.append(':');
        if (this.fPublicId != null) {
            str.append(this.fPublicId);
        }
        str.append(':');
        if (this.fLiteralSystemId != null) {
            str.append(this.fLiteralSystemId);
        }
        str.append(':');
        if (this.fExpandedSystemId != null) {
            str.append(this.fExpandedSystemId);
        }
        str.append(':');
        if (this.fBaseSystemId != null) {
            str.append(this.fBaseSystemId);
        }
        str.append(':');
        str.append(this.fLineNumber);
        str.append(':');
        str.append(this.fColumnNumber);
        str.append(':');
        String message = this.getMessage();
        if (message == null) {
            final Exception exception = this.getException();
            if (exception != null) {
                message = exception.getMessage();
            }
        }
        if (message != null) {
            str.append(message);
        }
        return str.toString();
    }
}
