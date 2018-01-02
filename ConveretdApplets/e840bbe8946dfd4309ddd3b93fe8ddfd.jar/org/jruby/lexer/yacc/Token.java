// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.lexer.yacc;

public class Token implements ISourcePositionHolder
{
    ISourcePosition position;
    Object value;
    int type;
    
    public Token(final Object value, final ISourcePosition position) {
        this.position = null;
        this.type = 0;
        this.value = value;
        this.position = position;
    }
    
    public Token(final Object value, final int type, final ISourcePosition position) {
        this.position = null;
        this.type = 0;
        this.value = value;
        this.position = position;
        this.type = type;
    }
    
    public void setValue(final Object value) {
        this.value = value;
    }
    
    public Object getValue() {
        return this.value;
    }
    
    public int getType() {
        return this.type;
    }
    
    public ISourcePosition getPosition() {
        return this.position;
    }
    
    public void setPosition(final ISourcePosition position) {
        this.position = position;
    }
    
    public String toString() {
        return "Token { Value=" + this.value + ", Position=" + this.position + "}";
    }
}
