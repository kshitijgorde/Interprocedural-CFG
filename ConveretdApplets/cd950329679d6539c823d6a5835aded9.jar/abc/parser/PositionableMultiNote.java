// 
// Decompiled by Procyon v0.5.30
// 

package abc.parser;

import java.util.Vector;
import scanner.CharStreamPosition;
import scanner.PositionableInCharStream;
import abc.notation.MultiNote;

public class PositionableMultiNote extends MultiNote implements PositionableInCharStream
{
    private CharStreamPosition m_position;
    private int m_length;
    
    public PositionableMultiNote(final Vector notes) {
        super(notes);
        this.m_position = null;
        this.m_length = -1;
        final PositionableNote firstNote = notes.elementAt(0);
        final PositionableNote lastNote = notes.elementAt(notes.size() - 1);
        this.m_position = firstNote.getPosition();
        this.m_length = lastNote.getPosition().getCharactersOffset() - firstNote.getPosition().getCharactersOffset() + lastNote.getLength();
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
