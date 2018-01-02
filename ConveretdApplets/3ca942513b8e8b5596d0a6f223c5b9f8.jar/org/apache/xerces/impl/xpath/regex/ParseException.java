// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xpath.regex;

public class ParseException extends RuntimeException
{
    static final long serialVersionUID = -7012400318097691370L;
    int location;
    
    public ParseException(final String s, final int location) {
        super(s);
        this.location = location;
    }
    
    public int getLocation() {
        return this.location;
    }
}
