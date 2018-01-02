// 
// Decompiled by Procyon v0.5.30
// 

package scanner;

import java.util.EventObject;

public class InvalidCharacterEvent extends EventObject implements PositionableInCharStream
{
    private char m_character;
    private CharStreamPosition m_position;
    
    public InvalidCharacterEvent(final Object source, final char character, final CharStreamPosition position) {
        super(source);
        this.m_position = null;
        this.m_position = position;
        this.m_character = character;
    }
    
    public char getCharacter() {
        return this.m_character;
    }
    
    public int getLength() {
        return 1;
    }
    
    public CharStreamPosition getPosition() {
        return this.m_position;
    }
    
    public String toString() {
        return "'" + this.m_character + "'" + this.m_position;
    }
}
