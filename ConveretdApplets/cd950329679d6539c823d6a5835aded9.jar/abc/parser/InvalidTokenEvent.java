// 
// Decompiled by Procyon v0.5.30
// 

package abc.parser;

import scanner.Token;
import scanner.CharStreamPosition;
import scanner.TokenType;
import scanner.TokenEvent;

public class InvalidTokenEvent extends TokenEvent
{
    private TokenType m_expectedTokenType;
    private CharStreamPosition m_position;
    
    public InvalidTokenEvent(final Object source, final Token token, final TokenType expectedTokenType) {
        super(source, token);
        this.m_expectedTokenType = TokenType.UNKNOWN;
        this.m_position = null;
        this.m_expectedTokenType = expectedTokenType;
        this.m_position = token.getPosition();
    }
    
    public InvalidTokenEvent(final Object source, final CharStreamPosition position, final TokenType expectedTokenType) {
        super(source, null);
        this.m_expectedTokenType = TokenType.UNKNOWN;
        this.m_position = null;
        this.m_expectedTokenType = expectedTokenType;
        this.m_position = position;
    }
    
    public TokenType getExpectedTokenType() {
        return this.m_expectedTokenType;
    }
    
    public CharStreamPosition getPosition() {
        return this.m_position;
    }
    
    public int getLength() {
        if (this.m_token == null) {
            return 0;
        }
        return this.m_token.getLength();
    }
    
    public String toString() {
        if (this.getToken() != null) {
            return "Expecting " + this.m_expectedTokenType.toString() + " instead of " + this.getToken().getType().toString() + "(" + this.getToken() + ")" + this.getPosition();
        }
        return "Expecting " + this.m_expectedTokenType.toString() + " " + this.getPosition();
    }
}
