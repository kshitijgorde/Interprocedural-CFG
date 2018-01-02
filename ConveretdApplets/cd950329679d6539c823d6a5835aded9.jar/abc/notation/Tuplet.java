// 
// Decompiled by Procyon v0.5.30
// 

package abc.notation;

import java.util.Vector;

public class Tuplet
{
    private Vector m_notes;
    private int m_totalRelativeLength;
    private short m_totalDuration;
    
    public Tuplet(final Vector notes, final int totalRelativeLength, final short defaultNoteLength) {
        this.m_notes = null;
        this.m_totalRelativeLength = -1;
        this.m_totalDuration = -1;
        this.m_notes = notes;
        this.m_totalRelativeLength = totalRelativeLength;
        this.m_totalDuration = (short)(this.m_totalRelativeLength * defaultNoteLength);
        for (int i = 0; i < notes.size(); ++i) {
            notes.elementAt(i).setTuplet(this);
        }
    }
    
    public int getTotalRelativeLength() {
        return this.m_totalRelativeLength;
    }
    
    public short getTotalDuration() {
        return this.m_totalDuration;
    }
    
    public Vector getNotesAsVector() {
        return (Vector)this.m_notes.clone();
    }
}
