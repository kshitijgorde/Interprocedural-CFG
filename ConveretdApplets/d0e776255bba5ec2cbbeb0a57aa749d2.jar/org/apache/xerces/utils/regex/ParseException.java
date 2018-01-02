// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.utils.regex;

public class ParseException extends RuntimeException
{
    int location;
    
    public ParseException(final String s, final int location) {
        super(s);
        this.location = location;
    }
    
    public int getLocation() {
        return this.location;
    }
}
