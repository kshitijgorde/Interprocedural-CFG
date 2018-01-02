// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.jcm.data;

public class ParseError extends RuntimeException
{
    public ParserContext context;
    
    public ParseError(final String s, final ParserContext context) {
        super(s);
        this.context = context;
    }
}
