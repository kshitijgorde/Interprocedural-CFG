// 
// Decompiled by Procyon v0.5.30
// 

package abc.notation;

public class TimeSignature extends Fraction implements MusicElement
{
    public static final TimeSignature SIGNATURE_4_4;
    public static final TimeSignature SIGNATURE_3_4;
    public static final TimeSignature SIGNATURE_6_8;
    
    public TimeSignature(final int num, final int den) {
        super(num, den);
    }
    
    public short getDefaultNoteLength() {
        short currentNoteLength;
        if (this.floatValue() < 0.75) {
            currentNoteLength = 12;
        }
        else {
            currentNoteLength = 24;
        }
        return currentNoteLength;
    }
    
    public boolean isCoumpound() {
        return this.getNumerator() % 3 == 0;
    }
    
    public int getNumberOfDefaultNotesPerBeat(final short defaultLength) {
        final short meterDefLength = Note.convertToNoteLengthStrict(1, this.getDenominator());
        return meterDefLength / defaultLength;
    }
    
    public boolean equals(final Object o) {
        if (o instanceof TimeSignature) {
            return ((TimeSignature)o).getDenominator() == this.getDenominator() && ((TimeSignature)o).getNumerator() == this.getNumerator();
        }
        return super.equals(o);
    }
    
    static {
        SIGNATURE_4_4 = new TimeSignature(4, 4);
        SIGNATURE_3_4 = new TimeSignature(3, 4);
        SIGNATURE_6_8 = new TimeSignature(6, 8);
    }
}
