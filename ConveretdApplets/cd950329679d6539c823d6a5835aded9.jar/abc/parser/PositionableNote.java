// 
// Decompiled by Procyon v0.5.30
// 

package abc.parser;

import scanner.CharStreamPosition;
import scanner.PositionableInCharStream;
import abc.notation.Note;

public class PositionableNote extends Note implements PositionableInCharStream
{
    private CharStreamPosition m_position;
    private int m_length;
    
    public PositionableNote(final byte heigthValue, final byte accidentalValue) {
        super(heigthValue, accidentalValue);
        this.m_position = null;
        this.m_length = -1;
    }
    
    public PositionableNote(final byte heigthValue, final byte accidentalValue, final byte octaveTranspositionValue) {
        super(heigthValue, accidentalValue, octaveTranspositionValue);
        this.m_position = null;
        this.m_length = -1;
    }
    
    public CharStreamPosition getPosition() {
        return this.m_position;
    }
    
    public void setBeginPosition(final CharStreamPosition position) {
        this.m_position = position;
    }
    
    public void setLength(final int length) {
        this.m_length = length;
    }
    
    public int getLength() {
        return this.m_length;
    }
    
    public String toString() {
        return super.toString() + " " + this.m_position + "(l=" + this.m_length + ")";
    }
}
