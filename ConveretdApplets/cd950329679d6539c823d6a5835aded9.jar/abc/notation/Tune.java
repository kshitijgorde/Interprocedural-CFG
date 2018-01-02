// 
// Decompiled by Procyon v0.5.30
// 

package abc.notation;

import scanner.PositionableInCharStream;
import java.util.Hashtable;
import java.util.Vector;

public class Tune implements Cloneable
{
    private String m_area;
    private String m_book;
    private String m_composer;
    private String m_discography;
    private String m_group;
    private String m_history;
    private String m_information;
    private KeySignature m_key;
    private String m_notes;
    private String m_origin;
    private String m_rhythm;
    private String m_source;
    private int m_referenceNumber;
    private String m_transcriptionNotes;
    private int m_elemskip;
    private Vector m_titles;
    private Part m_defaultPart;
    private MultiPartsDefinition m_multiPartsDef;
    private Hashtable m_parts;
    
    public Tune() {
        this.m_area = null;
        this.m_book = null;
        this.m_composer = null;
        this.m_discography = null;
        this.m_group = null;
        this.m_history = null;
        this.m_information = null;
        this.m_key = null;
        this.m_notes = null;
        this.m_origin = null;
        this.m_rhythm = null;
        this.m_source = null;
        this.m_referenceNumber = -1;
        this.m_transcriptionNotes = null;
        this.m_elemskip = 0;
        this.m_defaultPart = null;
        this.m_multiPartsDef = null;
        this.m_parts = null;
        this.m_titles = new Vector();
        this.m_defaultPart = new Part(this, ' ');
    }
    
    public Tune(final Tune tune) {
        this.m_area = null;
        this.m_book = null;
        this.m_composer = null;
        this.m_discography = null;
        this.m_group = null;
        this.m_history = null;
        this.m_information = null;
        this.m_key = null;
        this.m_notes = null;
        this.m_origin = null;
        this.m_rhythm = null;
        this.m_source = null;
        this.m_referenceNumber = -1;
        this.m_transcriptionNotes = null;
        this.m_elemskip = 0;
        this.m_defaultPart = null;
        this.m_multiPartsDef = null;
        this.m_parts = null;
        this.m_area = tune.m_area;
        this.m_book = tune.m_book;
        this.m_composer = tune.m_composer;
        this.m_discography = tune.m_discography;
        this.m_group = tune.m_group;
        this.m_history = tune.m_history;
        this.m_information = tune.m_information;
        this.m_key = (KeySignature)tune.m_key.clone();
        this.m_notes = tune.m_notes;
        this.m_origin = tune.m_origin;
        this.m_rhythm = tune.m_rhythm;
        this.m_source = tune.m_source;
        this.m_referenceNumber = tune.m_referenceNumber;
        this.m_transcriptionNotes = tune.m_transcriptionNotes;
        this.m_elemskip = tune.m_elemskip;
        this.m_titles = (Vector)tune.m_titles.clone();
        this.m_parts = (Hashtable)tune.m_parts.clone();
        this.m_defaultPart = (Part)tune.m_defaultPart.clone();
        this.m_multiPartsDef = (MultiPartsDefinition)tune.m_multiPartsDef.clone();
    }
    
    public void setArea(final String area) {
        this.m_area = area;
    }
    
    public String getArea() {
        return this.m_area;
    }
    
    public void setBook(final String book) {
        this.m_book = book;
    }
    
    public String getBook() {
        return this.m_book;
    }
    
    public void setComposer(final String composer) {
        this.m_composer = composer;
    }
    
    public String getComposer() {
        return this.m_composer;
    }
    
    public void setDiscography(final String discography) {
        this.m_discography = discography;
    }
    
    public String getDiscography() {
        return this.m_discography;
    }
    
    public void setElemskip(final int value) {
        this.m_elemskip = value;
    }
    
    public int getElemskip() {
        return this.m_elemskip;
    }
    
    public void setGroup(final String value) {
        this.m_group = value;
    }
    
    public String getGroup() {
        return this.m_group;
    }
    
    public void addHistory(final String history) {
        if (this.m_history == null) {
            this.m_history = new String(history);
        }
        else {
            this.m_history = this.m_history.concat(history + "\n");
        }
    }
    
    public String getHistory() {
        return this.m_history;
    }
    
    void setKey(final KeySignature key) {
        this.m_key = key;
    }
    
    public KeySignature getKey() {
        return this.m_key;
    }
    
    public void setInformation(final String information) {
        this.m_information = information;
    }
    
    public String getInformation() {
        return this.m_information;
    }
    
    public void setNotes(final String notes) {
        this.m_notes = notes;
    }
    
    public String getNotes() {
        return this.m_notes;
    }
    
    public void setOrigin(final String origin) {
        this.m_origin = origin;
    }
    
    public String getOrigin() {
        return this.m_origin;
    }
    
    public Part getPart(final char partLabel) {
        if (this.m_parts != null) {
            final Part p = this.m_parts.get(new Character(partLabel));
            return p;
        }
        return null;
    }
    
    public Part createPart(final char partLabel) {
        final Part part = new Part(this, partLabel);
        if (this.m_parts == null) {
            this.m_parts = new Hashtable();
        }
        this.m_parts.put(new Character(partLabel), part);
        return part;
    }
    
    public void setMultiPartsDefinition(final MultiPartsDefinition multiPartsDef) {
        this.m_multiPartsDef = multiPartsDef;
    }
    
    public MultiPartsDefinition getMultiPartsDefinition() {
        return this.m_multiPartsDef;
    }
    
    public void setRhythm(final String rhythm) {
        this.m_rhythm = rhythm;
    }
    
    public String getRhythm() {
        return this.m_rhythm;
    }
    
    public void setSource(final String source) {
        this.m_source = source;
    }
    
    public String getSource() {
        return this.m_source;
    }
    
    public void addTitle(final String title) {
        this.m_titles.addElement(title);
    }
    
    public void removeTitle(final String title) {
        this.m_titles.removeElement(title);
    }
    
    public String[] getTitles() {
        String[] titles = null;
        if (this.m_titles.size() != 0) {
            titles = new String[this.m_titles.size()];
            for (int i = 0; i < this.m_titles.size(); ++i) {
                titles[i] = this.m_titles.elementAt(i);
            }
        }
        return titles;
    }
    
    public void setReferenceNumber(final int id) {
        this.m_referenceNumber = id;
    }
    
    public int getReferenceNumber() {
        return this.m_referenceNumber;
    }
    
    public void addTranscriptionNotes(final String transciptionNotes) {
        if (this.m_transcriptionNotes == null) {
            this.m_transcriptionNotes = new String(transciptionNotes);
        }
        else {
            this.m_transcriptionNotes = this.m_transcriptionNotes.concat(transciptionNotes + "\n");
        }
    }
    
    public String getTranscriptionNotes() {
        return this.m_transcriptionNotes;
    }
    
    public Music getMusic() {
        if (this.m_multiPartsDef == null) {
            return this.m_defaultPart.getMusic();
        }
        final Music globalScore = new Music();
        final Music defaultScore = this.m_defaultPart.getMusic();
        for (int i = 0; i < defaultScore.size(); ++i) {
            globalScore.addElement(defaultScore.elementAt(i));
        }
        final Part[] parts = this.m_multiPartsDef.toPartsArray();
        for (int j = 0; j < parts.length; ++j) {
            final Music score = parts[j].getMusic();
            for (int k = 0; k < score.size(); ++k) {
                globalScore.addElement(score.elementAt(k));
            }
        }
        return globalScore;
    }
    
    public String toString() {
        String string2return = "";
        if (this.m_titles.size() != 0) {
            string2return = this.m_titles + "(" + this.m_referenceNumber + ")@" + this.hashCode();
        }
        else {
            string2return = "(" + this.m_referenceNumber + ")@" + this.hashCode();
        }
        return string2return;
    }
    
    public Object clone() {
        return new Tune(this);
    }
    
    Music createMusic() {
        return new Music();
    }
    
    public class Music extends Vector
    {
        protected NoteAbstract lastNote;
        
        public Music() {
            this.lastNote = null;
        }
        
        public void addElement(final KeySignature key) {
            if (Tune.this.getKey() == null) {
                Tune.this.setKey(key);
            }
            super.addElement(key);
        }
        
        public void addElement(final NoteAbstract note) {
            super.addElement(this.lastNote = note);
        }
        
        public NoteAbstract getLastNote() {
            return this.lastNote;
        }
        
        public MusicElement getElementAt(final int offset) {
            MusicElement foundElement = null;
            MusicElement current = null;
            for (int i = 0; i < this.size(); ++i) {
                current = this.elementAt(i);
                if (current instanceof PositionableInCharStream) {
                    final PositionableInCharStream pos = (PositionableInCharStream)current;
                    if (pos.getPosition().getCharactersOffset() <= offset && pos.getPosition().getCharactersOffset() + pos.getLength() >= offset) {
                        foundElement = current;
                    }
                }
            }
            return foundElement;
        }
        
        public int indexOf(final MusicElement elmnt) {
            Object elmntIt = null;
            final boolean isLooking4Note = elmnt instanceof Note;
            for (int i = 0; i < this.size(); ++i) {
                elmntIt = this.elementAt(i);
                if (this.elementAt(i) instanceof MultiNote && isLooking4Note) {
                    if (((MultiNote)elmntIt).contains((Note)elmnt)) {
                        return i;
                    }
                }
                else if (this.elementAt(i).equals(elmnt)) {
                    return i;
                }
            }
            return -1;
        }
        
        public Note getHighestNoteBewteen(final MusicElement elmtBegin, final MusicElement elmtEnd) throws IllegalArgumentException {
            Note highestNote = null;
            if (elmtBegin instanceof Note) {
                highestNote = (Note)elmtBegin;
            }
            else if (elmtBegin instanceof MultiNote) {
                highestNote = ((MultiNote)elmtBegin).getHighestNote();
            }
            final int idxBegin = this.indexOf(elmtBegin);
            final int idxEnd = this.indexOf(elmtEnd);
            if (idxBegin == -1) {
                throw new IllegalArgumentException("Note " + elmtBegin + " hasn't been found in tune");
            }
            if (idxEnd == -1) {
                throw new IllegalArgumentException("Note " + elmtEnd + " hasn't been found in tune");
            }
            if (idxBegin > idxEnd) {
                throw new IllegalArgumentException("Note " + elmtBegin + " is located after " + elmtEnd + " in the score");
            }
            for (int i = idxBegin + 1; i <= idxEnd; ++i) {
                final MusicElement currentScoreEl = this.elementAt(i);
                if (currentScoreEl instanceof Note && ((Note)currentScoreEl).isHigherThan(highestNote)) {
                    highestNote = (Note)currentScoreEl;
                }
            }
            return highestNote;
        }
        
        public Note getLowestNoteBewteen(final MusicElement noteBegin, final MusicElement noteEnd) throws IllegalArgumentException {
            Note lowestNote = null;
            if (noteBegin instanceof Note) {
                lowestNote = (Note)noteBegin;
            }
            else if (noteBegin instanceof MultiNote) {
                lowestNote = ((MultiNote)noteBegin).getLowestNote();
            }
            final int idxBegin = this.indexOf(noteBegin);
            final int idxEnd = this.indexOf(noteEnd);
            if (idxBegin == -1) {
                throw new IllegalArgumentException("Note " + noteBegin + " hasn't been found in tune");
            }
            if (idxEnd == -1) {
                throw new IllegalArgumentException("Note " + noteEnd + " hasn't been found in tune");
            }
            if (idxBegin > idxEnd) {
                throw new IllegalArgumentException("Note " + noteBegin + " is located after " + noteEnd + " in the score");
            }
            for (int i = idxBegin + 1; i <= idxEnd; ++i) {
                final MusicElement currentScoreEl = this.elementAt(i);
                if (currentScoreEl instanceof Note && ((Note)currentScoreEl).isLowerThan(lowestNote)) {
                    lowestNote = (Note)currentScoreEl;
                }
            }
            return lowestNote;
        }
    }
}
