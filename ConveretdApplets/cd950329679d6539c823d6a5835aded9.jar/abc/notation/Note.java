// 
// Decompiled by Procyon v0.5.30
// 

package abc.notation;

public class Note extends NoteAbstract
{
    public static final byte C = 0;
    public static final byte D = 2;
    public static final byte E = 4;
    public static final byte F = 5;
    public static final byte G = 7;
    public static final byte A = 9;
    public static final byte B = 11;
    public static final byte c = 12;
    public static final byte d = 14;
    public static final byte e = 16;
    public static final byte f = 17;
    public static final byte g = 19;
    public static final byte a = 21;
    public static final byte b = 23;
    public static final byte REST = Byte.MIN_VALUE;
    private static final short LENGTH_RESOLUTION = 3;
    public static final short DOTTED_WHOLE = 288;
    public static final short WHOLE = 192;
    public static final short DOTTED_HALF = 144;
    public static final short HALF = 96;
    public static final short DOTTED_QUARTER = 72;
    public static final short QUARTER = 48;
    public static final short DOTTED_EIGHTH = 36;
    public static final short EIGHTH = 24;
    public static final short DOTTED_SIXTEENTH = 18;
    public static final short SIXTEENTH = 12;
    public static final short DOTTED_THIRTY_SECOND = 9;
    public static final short THIRTY_SECOND = 6;
    public static final short DOTTED_SIXTY_FOURTH = 4;
    public static final short SIXTY_FOURTH = 3;
    private byte strictHeight;
    private byte octaveTransposition;
    private byte accidental;
    private short m_duration;
    private short m_strictDuration;
    protected TieDefinition tieDefinition;
    
    public Note(final byte heightValue) {
        this.strictHeight = -128;
        this.octaveTransposition = 0;
        this.accidental = 10;
        this.m_duration = -1;
        this.m_strictDuration = 24;
        this.tieDefinition = null;
        this.setHeight(heightValue);
    }
    
    public Note(final byte heightValue, final byte accidentalValue) {
        this.strictHeight = -128;
        this.octaveTransposition = 0;
        this.accidental = 10;
        this.m_duration = -1;
        this.m_strictDuration = 24;
        this.tieDefinition = null;
        this.setHeight(heightValue);
        this.setAccidental(accidentalValue);
    }
    
    public Note(final byte heightValue, final byte accidentalValue, final byte octaveTranspositionValue) {
        this(heightValue, accidentalValue);
        this.setOctaveTransposition((byte)(this.octaveTransposition + octaveTranspositionValue));
    }
    
    public void setHeigth(final byte heigthValue) {
        this.setHeight(heigthValue);
    }
    
    public void setHeight(final byte heightValue) throws IllegalArgumentException {
        this.strictHeight = getStrictHeight(heightValue);
        if (this.strictHeight < 0 && this.strictHeight != -128) {
            throw new IllegalArgumentException("negative : " + this.strictHeight);
        }
        this.octaveTransposition = getOctaveTransposition(heightValue);
    }
    
    public byte getHeigth() {
        return this.getHeight();
    }
    
    public byte getHeight() {
        return (byte)(this.strictHeight + this.octaveTransposition * 12);
    }
    
    public boolean isHigherThan(final Note aNote) {
        return this.getMidiLikeHeight() > aNote.getMidiLikeHeight();
    }
    
    public boolean isLowerThan(final Note aNote) {
        return this.getMidiLikeHeight() < aNote.getMidiLikeHeight();
    }
    
    private int getMidiLikeHeight() {
        int midiLikeHeight = this.getHeight();
        if (this.accidental == 1) {
            ++midiLikeHeight;
        }
        else if (this.accidental == -1) {
            --midiLikeHeight;
        }
        return midiLikeHeight;
    }
    
    public byte getStrictHeight() {
        return getStrictHeight(this.strictHeight);
    }
    
    public static byte getStrictHeight(final byte height) {
        if (height == -128) {
            return -128;
        }
        final byte sh = (byte)((height + 24) % 12);
        if (sh != 0 && sh != 2 && sh != 4 && sh != 5 && sh != 7 && sh != 9 && sh != 11) {
            throw new IllegalArgumentException("The height " + height + " cannot be strictly mapped because of sharp or flat (sh=" + sh + ")");
        }
        return sh;
    }
    
    public static byte getOctaveTransposition(final byte height) {
        if (height == -128) {
            return 0;
        }
        return (byte)((height - getStrictHeight(height)) / 12);
    }
    
    public byte toRootOctaveHeigth() {
        return this.getStrictHeight();
    }
    
    public void setOctaveTransposition(final byte octaveTranspositionValue) {
        this.octaveTransposition = octaveTranspositionValue;
    }
    
    public byte getOctaveTransposition() {
        return this.octaveTransposition;
    }
    
    public void setLength(final short length) {
        this.m_duration = length;
    }
    
    public void setDuration(final short duration) {
        this.m_duration = duration;
        System.err.println("[warning]duration of " + this + " set in an absolute manner with " + duration + "(not recommanded but supported)");
    }
    
    public void setStrictDuration(final short strictDuration) throws IllegalArgumentException {
        if (isStrictDuration(strictDuration)) {
            this.m_strictDuration = strictDuration;
            this.m_duration = -1;
            return;
        }
        throw new IllegalArgumentException("The note duration " + strictDuration + " is not equals to " + "Note.WHOLE, Note.HALF, Note.QUARTER, Note.EIGHTH, Note.SIXTEENTH, " + "Note.THIRTY_SECOND or Note.SIXTY_FOURTH");
    }
    
    public short getStrictDuration() {
        return this.m_strictDuration;
    }
    
    public short getDuration() {
        if (this.m_duration != -1) {
            return this.m_duration;
        }
        return this.m_duration = this.computeDuration(this.m_strictDuration, this.countDots());
    }
    
    public void setAccidental(final byte accidentalValue) {
        this.accidental = accidentalValue;
    }
    
    public byte getAccidental() {
        return this.accidental;
    }
    
    public boolean hasAccidental() {
        return this.accidental == -1 || this.accidental == 1 || this.accidental == 0;
    }
    
    public void setTieDefinition(final TieDefinition tieDef) {
        this.tieDefinition = tieDef;
    }
    
    public TieDefinition getTieDefinition() {
        return this.tieDefinition;
    }
    
    public boolean isBeginningTie() {
        return this.tieDefinition != null && this.equals(this.tieDefinition.getStart());
    }
    
    public boolean isEndingTie() {
        return this.tieDefinition != null && this.equals(this.tieDefinition.getEnd());
    }
    
    public boolean isTied() {
        return this.tieDefinition != null;
    }
    
    public boolean isRest() {
        return this.strictHeight == -128;
    }
    
    public void setDotted(final byte dotsNb) {
        super.setDotted(dotsNb);
        this.m_duration = -1;
    }
    
    public static byte convertToNoteType(final String note) {
        if (note.equals("A")) {
            return 9;
        }
        if (note.equals("B")) {
            return 11;
        }
        if (note.equals("C")) {
            return 0;
        }
        if (note.equals("D")) {
            return 2;
        }
        if (note.equals("E")) {
            return 4;
        }
        if (note.equals("F")) {
            return 5;
        }
        if (note.equals("G")) {
            return 7;
        }
        if (note.equals("a")) {
            return 21;
        }
        if (note.equals("b")) {
            return 23;
        }
        if (note.equals("c")) {
            return 12;
        }
        if (note.equals("d")) {
            return 14;
        }
        if (note.equals("e")) {
            return 16;
        }
        if (note.equals("f")) {
            return 17;
        }
        if (note.equals("g")) {
            return 19;
        }
        if (note.equals("z")) {
            return -128;
        }
        return -1;
    }
    
    public static short convertToNoteLengthStrict(final int num, final int denom) throws IllegalArgumentException {
        if (num == 1 && denom == 1) {
            return 192;
        }
        if (num == 1 && denom == 2) {
            return 96;
        }
        if (num == 1 && denom == 4) {
            return 48;
        }
        if (num == 1 && denom == 8) {
            return 24;
        }
        if (num == 1 && denom == 16) {
            return 12;
        }
        if (num == 1 && denom == 32) {
            return 6;
        }
        if (num == 1 && denom == 64) {
            return 3;
        }
        throw new IllegalArgumentException(num + "/" + denom + " doesn't correspond to any strict note length");
    }
    
    public static byte convertToAccidentalType(final String accidental) throws IllegalArgumentException {
        if (accidental == null) {
            return 10;
        }
        if (accidental.equals("^")) {
            return 1;
        }
        if (accidental.equals("^^")) {
            return 1;
        }
        if (accidental.equals("_")) {
            return -1;
        }
        if (accidental.equals("__")) {
            return -1;
        }
        if (accidental.equals("=")) {
            return 0;
        }
        throw new IllegalArgumentException(accidental + " is not a valid accidental");
    }
    
    public String toString() {
        String string2Return = super.toString();
        if (this.strictHeight == -128) {
            string2Return = string2Return.concat("z");
        }
        else if (this.strictHeight == 0) {
            string2Return = string2Return.concat("C");
        }
        else if (this.strictHeight == 2) {
            string2Return = string2Return.concat("D");
        }
        else if (this.strictHeight == 4) {
            string2Return = string2Return.concat("E");
        }
        else if (this.strictHeight == 5) {
            string2Return = string2Return.concat("F");
        }
        else if (this.strictHeight == 7) {
            string2Return = string2Return.concat("G");
        }
        else if (this.strictHeight == 9) {
            string2Return = string2Return.concat("A");
        }
        else if (this.strictHeight == 11) {
            string2Return = string2Return.concat("B");
        }
        if (this.octaveTransposition == 1) {
            string2Return = string2Return.concat("'");
        }
        else if (this.octaveTransposition == -1) {
            string2Return = string2Return.concat(",");
        }
        if (this.accidental == -1) {
            string2Return = string2Return.concat("b");
        }
        if (this.accidental == 1) {
            string2Return = string2Return.concat("#");
        }
        return string2Return;
    }
    
    public static boolean isStrictDuration(final short noteDuration) {
        return noteDuration == 192 || noteDuration == 96 || noteDuration == 48 || noteDuration == 24 || noteDuration == 12 || noteDuration == 6 || noteDuration == 3;
    }
    
    private short computeDuration(final short strictDuration, final int dotsNumber) {
        short duration = strictDuration;
        if (this.isPartOfTuplet()) {
            final Tuplet tuplet = this.getTuplet();
            final int notesNb = tuplet.getNotesAsVector().size();
            final float totalTupletDuration = tuplet.getTotalDuration();
            duration = (short)(totalTupletDuration / notesNb);
        }
        else {
            for (int dotsNb = this.countDots(), i = 1; i <= dotsNb; ++i) {
                final short dottedDuration = (short)(strictDuration / (i + 1));
                duration += dottedDuration;
            }
        }
        return duration;
    }
    
    public static Note getHighestNote(final Note[] notes) {
        Note highestNote = notes[0];
        for (int i = 0; i < notes.length; ++i) {
            if (notes[i].getHeight() > highestNote.getHeight()) {
                highestNote = notes[i];
            }
        }
        return highestNote;
    }
    
    public static int getHighestNoteIndex(final Note[] notes) {
        Note highestNote = notes[0];
        int index = 0;
        for (int i = 0; i < notes.length; ++i) {
            if (notes[i].getHeight() > highestNote.getHeight()) {
                highestNote = notes[i];
                index = i;
            }
        }
        return index;
    }
    
    public static Note getLowestNote(final Note[] notes) {
        Note lowestNote = notes[0];
        for (int i = 0; i < notes.length; ++i) {
            if (notes[i].getHeight() < lowestNote.getHeight()) {
                lowestNote = notes[i];
            }
        }
        return lowestNote;
    }
}
