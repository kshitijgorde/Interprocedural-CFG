// 
// Decompiled by Procyon v0.5.30
// 

package abc.notation;

public class Tempo implements MusicElement
{
    private short m_referenceLength;
    private int m_value;
    
    public Tempo(final int notesNbPerMinute) {
        this.m_referenceLength = 48;
        this.m_value = notesNbPerMinute;
    }
    
    public Tempo(final short referenceLength, final int value) {
        this.m_referenceLength = 48;
        this.m_referenceLength = referenceLength;
        this.m_value = value;
    }
    
    public short getReference() {
        return this.m_referenceLength;
    }
    
    public int getNotesNumberPerMinute() {
        return this.m_value;
    }
    
    public int getNotesNumberPerMinute(final short refLength) {
        return this.m_value * (this.m_referenceLength / refLength);
    }
}
