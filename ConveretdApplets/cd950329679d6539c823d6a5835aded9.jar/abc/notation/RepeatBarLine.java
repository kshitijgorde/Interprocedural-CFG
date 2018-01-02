// 
// Decompiled by Procyon v0.5.30
// 

package abc.notation;

public class RepeatBarLine extends BarLine implements MusicElement
{
    private byte m_repeatNumber;
    
    public RepeatBarLine(final byte repeatsNumber) {
        super((byte)((repeatsNumber == 1) ? 0 : 2));
        this.m_repeatNumber = 0;
        this.m_repeatNumber = repeatsNumber;
    }
    
    public byte getRepeatNumber() {
        return this.m_repeatNumber;
    }
    
    public String toString() {
        if (this.m_repeatNumber == 1) {
            return "|1";
        }
        if (this.m_repeatNumber == 2) {
            return ":|2";
        }
        return "?";
    }
}
