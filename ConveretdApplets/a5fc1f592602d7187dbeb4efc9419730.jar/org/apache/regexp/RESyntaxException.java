// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.regexp;

public class RESyntaxException extends Exception
{
    public RESyntaxException(final String s) {
        super("Syntax error: " + s);
    }
}
