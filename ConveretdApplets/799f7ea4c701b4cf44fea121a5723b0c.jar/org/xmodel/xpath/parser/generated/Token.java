// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.parser.generated;

import java.io.Serializable;

public class Token implements Serializable
{
    public int kind;
    public int beginLine;
    public int beginColumn;
    public int endLine;
    public int endColumn;
    public String image;
    public Token next;
    
    public Token() {
    }
    
    public Token(final int kind, final String image) {
        this.kind = kind;
        this.image = image;
    }
    
    @Override
    public String toString() {
        return this.image;
    }
    
    public static Token newToken(final int n, final String s) {
        return new Token(n, s);
    }
}
