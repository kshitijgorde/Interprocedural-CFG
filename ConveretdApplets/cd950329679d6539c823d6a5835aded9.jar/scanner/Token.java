// 
// Decompiled by Procyon v0.5.30
// 

package scanner;

public class Token implements PositionableInCharStream
{
    private String m_value;
    private TokenType m_type;
    private CharStreamPosition m_position;
    
    public Token(final String val, final TokenType type, final CharStreamPosition position) {
        this.m_value = val;
        this.m_type = type;
        this.m_position = position;
    }
    
    public TokenType getType() {
        return this.m_type;
    }
    
    public String getValue() {
        return this.m_value;
    }
    
    public int getLength() {
        return this.m_value.length();
    }
    
    public CharStreamPosition getPosition() {
        return this.m_position;
    }
    
    public String toString() {
        return "[\"" + this.m_value + "\", " + this.m_type + "]" + this.m_position;
    }
}
