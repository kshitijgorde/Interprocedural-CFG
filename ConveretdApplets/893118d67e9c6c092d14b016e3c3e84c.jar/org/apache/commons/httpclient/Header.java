// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.httpclient;

public class Header extends NameValuePair
{
    public Header() {
        this(null, null);
    }
    
    public Header(final String name, final String value) {
        super(name, value);
    }
    
    public HeaderElement[] getElements() {
        return HeaderElement.parseElements(this.getValue());
    }
    
    public HeaderElement[] getValues() throws HttpException {
        return HeaderElement.parse(this.getValue());
    }
    
    public String toExternalForm() {
        return String.valueOf((this.getName() == null) ? "" : this.getName()) + ": " + ((this.getValue() == null) ? "" : this.getValue()) + "\r\n";
    }
    
    public String toString() {
        return this.toExternalForm();
    }
}
