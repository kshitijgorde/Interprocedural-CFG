// 
// Decompiled by Procyon v0.5.30
// 

package org.xml.sax;

public class SAXParseException extends SAXException
{
    private String publicId;
    private String systemId;
    private int lineNumber;
    private int columnNumber;
    
    public SAXParseException(final String s, final Locator locator) {
        super(s);
        this.publicId = locator.getPublicId();
        this.systemId = locator.getSystemId();
        this.lineNumber = locator.getLineNumber();
        this.columnNumber = locator.getColumnNumber();
    }
    
    public SAXParseException(final String s, final Locator locator, final Exception ex) {
        super(s, ex);
        this.publicId = locator.getPublicId();
        this.systemId = locator.getSystemId();
        this.lineNumber = locator.getLineNumber();
        this.columnNumber = locator.getColumnNumber();
    }
    
    public SAXParseException(final String s, final String publicId, final String systemId, final int lineNumber, final int columnNumber) {
        super(s);
        this.publicId = publicId;
        this.systemId = systemId;
        this.lineNumber = lineNumber;
        this.columnNumber = columnNumber;
    }
    
    public SAXParseException(final String s, final String publicId, final String systemId, final int lineNumber, final int columnNumber, final Exception ex) {
        super(s, ex);
        this.publicId = publicId;
        this.systemId = systemId;
        this.lineNumber = lineNumber;
        this.columnNumber = columnNumber;
    }
    
    public String getPublicId() {
        return this.publicId;
    }
    
    public String getSystemId() {
        return this.systemId;
    }
    
    public int getLineNumber() {
        return this.lineNumber;
    }
    
    public int getColumnNumber() {
        return this.columnNumber;
    }
}
