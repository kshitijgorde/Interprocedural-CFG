// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xpath.regex;

public class ParseException extends RuntimeException
{
    int location;
    
    public ParseException(final String mes, final int location) {
        super(mes);
        this.location = location;
    }
    
    public int getLocation() {
        return this.location;
    }
}
