// 
// Decompiled by Procyon v0.5.30
// 

package abc.notation;

public class NoteAbstract implements MusicElement
{
    public static final byte NONE = -1;
    public static final byte DOWN = 1;
    public static final byte UP = 2;
    private String m_chordName;
    private Note[] m_gracingNotes;
    private boolean generalGracing;
    private boolean staccato;
    private byte bow;
    private byte m_dotted;
    protected SlurDefinition slurDefinition;
    protected boolean m_isPartOfSlur;
    private Tuplet m_tuplet;
    
    public NoteAbstract() {
        this.m_chordName = null;
        this.m_gracingNotes = null;
        this.generalGracing = false;
        this.staccato = false;
        this.bow = -1;
        this.m_dotted = 0;
        this.slurDefinition = null;
        this.m_isPartOfSlur = false;
        this.m_tuplet = null;
    }
    
    public void setChordName(final String chordName) {
        this.m_chordName = chordName;
    }
    
    public String getChordName() {
        return this.m_chordName;
    }
    
    public byte getBow() {
        return this.bow;
    }
    
    public void setBow(final byte bowValue) {
        this.bow = bowValue;
    }
    
    public Note[] getGracingNotes() {
        return this.m_gracingNotes;
    }
    
    public void setGracingNotes(final Note[] notes) {
        this.m_gracingNotes = notes;
    }
    
    public boolean hasGracingNotes() {
        return this.m_gracingNotes != null;
    }
    
    public void setDotted(final byte dotsNumber) {
        this.m_dotted = dotsNumber;
    }
    
    public byte getDotted() {
        return this.m_dotted;
    }
    
    public byte countDots() {
        return this.m_dotted;
    }
    
    public boolean hasGeneralGracing() {
        return this.generalGracing;
    }
    
    public void setGeneralGracing(final boolean hasGeneralGracing) {
        this.generalGracing = hasGeneralGracing;
    }
    
    public boolean hasStaccato() {
        return this.staccato;
    }
    
    public void setStaccato(final boolean staccatoValue) {
        this.staccato = staccatoValue;
    }
    
    public boolean isPartOfSlur() {
        return this.m_isPartOfSlur;
    }
    
    public boolean isPartOfTuplet() {
        return this.m_tuplet != null;
    }
    
    public Tuplet getTuplet() {
        return this.m_tuplet;
    }
    
    public void setPartOfSlur(final boolean isPartOfSlur) {
        this.m_isPartOfSlur = isPartOfSlur;
    }
    
    public int getGracingNotesLength(final short defaultNoteLength) {
        final int totalLength = 0;
        return totalLength;
    }
    
    void setTuplet(final Tuplet tuplet) {
        this.m_tuplet = tuplet;
    }
    
    public SlurDefinition getSlurDefinition() {
        return this.slurDefinition;
    }
    
    public boolean isBeginingSlur() {
        return this.slurDefinition != null && this.slurDefinition.getStart() != null && this.slurDefinition.getStart().equals(this);
    }
    
    public boolean isEndingSlur() {
        return this.slurDefinition != null && this.slurDefinition.getEnd().equals(this);
    }
    
    public void setSlurDefinition(final SlurDefinition slurDefinition) {
        this.slurDefinition = slurDefinition;
    }
    
    public String toString() {
        String string2Return = "";
        if (this.m_chordName != null) {
            string2Return = string2Return.concat(this.m_chordName);
        }
        if (this.generalGracing) {
            string2Return = string2Return.concat("~");
        }
        if (this.m_gracingNotes != null) {
            string2Return = string2Return.concat("{" + this.m_gracingNotes.toString() + "}");
        }
        if (this.staccato) {
            string2Return = string2Return.concat(".");
        }
        if (this.bow == 2) {
            string2Return = string2Return.concat("u");
        }
        if (this.bow == 1) {
            string2Return = string2Return.concat("d");
        }
        return string2Return;
    }
}
