// 
// Decompiled by Procyon v0.5.30
// 

package abc.notation;

public class KeySignature implements MusicElement, Cloneable
{
    private final byte[][] accidentalsRules;
    private final byte[][] accidentalRulesFlat;
    private final byte[][] accidentalRulesSharp;
    public static final byte AEOLIAN = 0;
    public static final byte DORIAN = 1;
    public static final byte IONIAN = 2;
    public static final byte LOCRIAN = 3;
    public static final byte LYDIAN = 4;
    public static final byte MAJOR = 5;
    public static final byte MINOR = 6;
    public static final byte MIXOLYDIAN = 7;
    public static final byte PHRYGIAN = 8;
    public static final byte OTHER = -1;
    private byte key;
    private byte m_keyAccidental;
    private byte mode;
    private byte[] accidentals;
    private int keyIndex;
    
    public KeySignature(final byte keyNoteType, final byte modeType) {
        this(keyNoteType, (byte)10, modeType);
    }
    
    public KeySignature(final byte keyNoteType, final byte keyAccidental, final byte modeType) {
        this.accidentalsRules = new byte[][] { { 0, 0, 0, 0, 0, 0, 0 }, { 0, -1, -1, 0, -1, -1, -1 }, { 1, 0, 0, 1, 0, 0, 0 }, { 0, 0, -1, 0, 0, -1, -1 }, { 1, 1, 0, 1, 1, 0, 0 }, { 0, 0, 0, 0, 0, 0, -1 }, new byte[0], { 0, 0, 0, 1, 0, 0, 0 }, { 0, -1, -1, 0, 0, -1, -1 }, { 1, 0, 0, 1, 1, 0, 0 }, { 0, 0, -1, 0, 0, 0, -1 }, { 1, 1, 0, 1, 1, 1, 0 } };
        this.accidentalRulesFlat = new byte[][] { new byte[0], new byte[0], new byte[0], new byte[0], new byte[0], new byte[0], { -1, -1, -1, 0, -1, -1, -1 }, new byte[0], new byte[0], new byte[0], new byte[0], { -1, -1, -1, -1, -1, -1, -1 } };
        this.accidentalRulesSharp = new byte[][] { new byte[0], { 1, 1, 1, 1, 1, 1, 1 }, new byte[0], new byte[0], new byte[0], new byte[0], { 1, 1, 1, 1, 1, 1, 0 }, new byte[0], new byte[0], new byte[0], new byte[0], new byte[0] };
        this.key = 0;
        this.m_keyAccidental = 0;
        this.mode = -1;
        this.accidentals = this.accidentalsRules[0];
        this.keyIndex = 0;
        if (keyAccidental != -1 && keyAccidental != 0 && keyAccidental != 1 && keyAccidental != 10) {
            throw new IllegalArgumentException("Key accidental type should be AccidentalType.FLAT, AccidentalType.NATURAL, AccidentalType.SHARP or AccidentalType.NONE");
        }
        if (modeType != 0 && modeType != 1 && modeType != 2 && modeType != 3 && modeType != 4 && modeType != 5 && modeType != 6 && modeType != 7 && modeType != 8 && modeType != -1) {
            throw new IllegalArgumentException("Mode type must be choose among AEOLIAN, DORIAN, IONIAN, LOCRIAN, LYDIAN, MAJOR, MINOR, MIXOLYDIAN, PHRYGIAN or OTHER");
        }
        this.key = keyNoteType;
        this.mode = modeType;
        this.m_keyAccidental = keyAccidental;
        this.keyIndex = 0;
        if (this.key == 2) {
            this.keyIndex += 2;
        }
        else if (this.key == 4) {
            this.keyIndex += 4;
        }
        else if (this.key == 5) {
            this.keyIndex += 5;
        }
        else if (this.key == 7) {
            this.keyIndex += 7;
        }
        else if (this.key == 9) {
            this.keyIndex += 9;
        }
        else if (this.key == 11) {
            this.keyIndex += 11;
        }
        if (this.m_keyAccidental == 1) {
            ++this.keyIndex;
        }
        else if (this.m_keyAccidental == -1) {
            --this.keyIndex;
        }
        if (modeType == 6) {
            this.keyIndex += 3;
        }
        else if (modeType == 4) {
            this.keyIndex += 7;
        }
        else if (modeType == 7) {
            this.keyIndex += 5;
        }
        else if (modeType == 1) {
            this.keyIndex += 10;
        }
        else if (modeType == 0) {
            this.keyIndex += 3;
        }
        else if (modeType == 8) {
            this.keyIndex += 8;
        }
        else if (modeType == 3) {
            ++this.keyIndex;
        }
        this.keyIndex = (this.keyIndex + 12) % 12;
        if (this.keyIndex == 6 && keyAccidental == -1) {
            this.accidentals = this.accidentalRulesFlat[this.keyIndex];
        }
        else if (this.keyIndex == 6 && keyAccidental == 1) {
            this.accidentals = this.accidentalRulesSharp[this.keyIndex];
        }
        else {
            if (this.keyIndex == 6 && keyAccidental == 0) {
                throw new RuntimeException("Cannot map " + keyNoteType + "/" + keyAccidental + "/" + modeType + " to a key signature");
            }
            if (this.keyIndex == 11 && keyAccidental == -1) {
                this.accidentals = this.accidentalRulesFlat[this.keyIndex];
            }
            else if (this.keyIndex == 1 && keyAccidental == 1) {
                this.accidentals = this.accidentalRulesSharp[this.keyIndex];
            }
            else {
                this.accidentals = this.accidentalsRules[this.keyIndex];
            }
        }
    }
    
    public KeySignature(final byte[] accidentalsDefinition) {
        this.accidentalsRules = new byte[][] { { 0, 0, 0, 0, 0, 0, 0 }, { 0, -1, -1, 0, -1, -1, -1 }, { 1, 0, 0, 1, 0, 0, 0 }, { 0, 0, -1, 0, 0, -1, -1 }, { 1, 1, 0, 1, 1, 0, 0 }, { 0, 0, 0, 0, 0, 0, -1 }, new byte[0], { 0, 0, 0, 1, 0, 0, 0 }, { 0, -1, -1, 0, 0, -1, -1 }, { 1, 0, 0, 1, 1, 0, 0 }, { 0, 0, -1, 0, 0, 0, -1 }, { 1, 1, 0, 1, 1, 1, 0 } };
        this.accidentalRulesFlat = new byte[][] { new byte[0], new byte[0], new byte[0], new byte[0], new byte[0], new byte[0], { -1, -1, -1, 0, -1, -1, -1 }, new byte[0], new byte[0], new byte[0], new byte[0], { -1, -1, -1, -1, -1, -1, -1 } };
        this.accidentalRulesSharp = new byte[][] { new byte[0], { 1, 1, 1, 1, 1, 1, 1 }, new byte[0], new byte[0], new byte[0], new byte[0], { 1, 1, 1, 1, 1, 1, 0 }, new byte[0], new byte[0], new byte[0], new byte[0], new byte[0] };
        this.key = 0;
        this.m_keyAccidental = 0;
        this.mode = -1;
        this.accidentals = this.accidentalsRules[0];
        this.keyIndex = 0;
        final byte[] newArray = new byte[7];
        System.arraycopy(accidentalsDefinition, 0, newArray, 0, 7);
        this.accidentals = newArray;
    }
    
    public byte getNote() {
        return this.key;
    }
    
    public byte getMode() {
        return this.mode;
    }
    
    public byte getAccidental() {
        return this.m_keyAccidental;
    }
    
    public byte[] getAccidentals() {
        return this.accidentals;
    }
    
    public void setAccidental(byte noteHeigth, final byte accidental) throws IllegalArgumentException {
        int index = 0;
        noteHeigth %= 12;
        if (noteHeigth == 0) {
            index = 0;
        }
        else if (noteHeigth == 2) {
            index = 1;
        }
        else if (noteHeigth == 4) {
            index = 2;
        }
        else if (noteHeigth == 5) {
            index = 3;
        }
        else if (noteHeigth == 7) {
            index = 4;
        }
        else if (noteHeigth == 9) {
            index = 5;
        }
        else {
            if (noteHeigth != 11) {
                throw new IllegalArgumentException("Invalid note heigth : " + noteHeigth);
            }
            index = 6;
        }
        if (accidental == 0 || accidental == 1 || accidental == -1) {
            this.accidentals[index] = accidental;
            return;
        }
        throw new IllegalArgumentException("Accidental type should be SHARP, FLAT or NATURAL");
    }
    
    public byte getAccidentalFor(final byte noteHeigth) {
        int index = 0;
        if (noteHeigth == 0) {
            index = 0;
        }
        else if (noteHeigth == 2) {
            index = 1;
        }
        else if (noteHeigth == 4) {
            index = 2;
        }
        else if (noteHeigth == 5) {
            index = 3;
        }
        else if (noteHeigth == 7) {
            index = 4;
        }
        else if (noteHeigth == 9) {
            index = 5;
        }
        else {
            if (noteHeigth != 11) {
                throw new IllegalArgumentException("Invalid note heigth : " + noteHeigth);
            }
            index = 6;
        }
        return this.accidentals[index];
    }
    
    public boolean hasOnlySharps() {
        return (this.keyIndex == 1 && this.m_keyAccidental == 1) || this.keyIndex == 2 || this.keyIndex == 4 || (this.keyIndex == 6 && this.m_keyAccidental == 1) || this.keyIndex == 7 || this.keyIndex == 9 || (this.keyIndex == 11 && this.m_keyAccidental == 0);
    }
    
    public boolean hasOnlyFlats() {
        return (this.keyIndex == 1 && this.m_keyAccidental == -1) || this.keyIndex == 3 || this.keyIndex == 5 || (this.keyIndex == 6 && this.m_keyAccidental == -1) || this.keyIndex == 8 || this.keyIndex == 10 || (this.keyIndex == 11 && this.m_keyAccidental == -1);
    }
    
    public String toLitteralNotation() {
        String notation = "";
        if (this.key == 9) {
            notation = notation.concat("A");
        }
        else if (this.key == 11) {
            notation = notation.concat("B");
        }
        else if (this.key == 0) {
            notation = notation.concat("C");
        }
        else if (this.key == 2) {
            notation = notation.concat("D");
        }
        else if (this.key == 4) {
            notation = notation.concat("E");
        }
        else if (this.key == 5) {
            notation = notation.concat("F");
        }
        else if (this.key == 7) {
            notation = notation.concat("G");
        }
        if (this.m_keyAccidental == -1) {
            notation = notation.concat("b");
        }
        else if (this.m_keyAccidental == 1) {
            notation = notation.concat("#");
        }
        if (this.mode == 0) {
            notation = notation.concat("aeo");
        }
        else if (this.mode == 1) {
            notation = notation.concat("dor");
        }
        else if (this.mode == 2) {
            notation = notation.concat("ion");
        }
        else if (this.mode == 3) {
            notation = notation.concat("loc");
        }
        else if (this.mode == 4) {
            notation = notation.concat("lyd");
        }
        else if (this.mode == 5) {
            notation = notation.concat("maj");
        }
        else if (this.mode == 6) {
            notation = notation.concat("min");
        }
        else if (this.mode == 7) {
            notation = notation.concat("mix");
        }
        else if (this.mode == 8) {
            notation = notation.concat("phr");
        }
        return notation;
    }
    
    public static byte convertToModeType(final String mode) {
        if ("AEO".equalsIgnoreCase(mode)) {
            return 0;
        }
        if ("DOR".equalsIgnoreCase(mode)) {
            return 1;
        }
        if ("ION".equalsIgnoreCase(mode)) {
            return 2;
        }
        if ("LOC".equalsIgnoreCase(mode)) {
            return 3;
        }
        if ("LYD".equalsIgnoreCase(mode)) {
            return 4;
        }
        if ("MAJ".equalsIgnoreCase(mode)) {
            return 5;
        }
        if ("MIN".equalsIgnoreCase(mode) || "M".equalsIgnoreCase(mode)) {
            return 6;
        }
        if ("MIX".equalsIgnoreCase(mode)) {
            return 7;
        }
        if ("PHR".equalsIgnoreCase(mode)) {
            return 8;
        }
        return -1;
    }
    
    public static byte convertToAccidentalType(final String accidental) throws IllegalArgumentException {
        if (accidental == null) {
            return 0;
        }
        if (accidental.equals("#")) {
            return 1;
        }
        if (accidental.equals("b")) {
            return -1;
        }
        throw new IllegalArgumentException(accidental + " is not a valid accidental");
    }
    
    public String toString() {
        String string2Return = "{";
        for (int i = 0; i < 7; ++i) {
            switch (this.accidentals[i]) {
                case 0: {
                    string2Return = string2Return.concat("AccidentalType.NATURAL");
                    break;
                }
                case -1: {
                    string2Return = string2Return.concat("AccidentalType.FLAT");
                    break;
                }
                case 1: {
                    string2Return = string2Return.concat("AccidentalType.SHARP");
                    break;
                }
            }
            if (i != 6) {
                string2Return = string2Return.concat(", ");
            }
        }
        string2Return = string2Return.concat("}");
        return string2Return;
    }
    
    public Object clone() {
        return new KeySignature(this.getNote(), this.getAccidental(), this.getMode());
    }
}
