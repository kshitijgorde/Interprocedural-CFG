// 
// Decompiled by Procyon v0.5.30
// 

package scanner;

import java.util.EventObject;

public class TokenEvent extends EventObject implements PositionableInCharStream
{
    protected Token m_token;
    
    public TokenEvent(final Object source, final Token token) {
        super(source);
        this.m_token = null;
        this.m_token = token;
    }
    
    public CharStreamPosition getPosition() {
        return this.m_token.getPosition();
    }
    
    public int getLength() {
        return this.m_token.getLength();
    }
    
    public Token getToken() {
        return this.m_token;
    }
    
    public String toString() {
        return this.m_token + "(" + this.m_token.getType() + ")";
    }
}
