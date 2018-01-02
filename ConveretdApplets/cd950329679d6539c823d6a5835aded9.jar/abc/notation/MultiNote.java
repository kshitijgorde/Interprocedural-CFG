// 
// Decompiled by Procyon v0.5.30
// 

package abc.notation;

import java.util.Hashtable;
import java.util.Vector;

public class MultiNote extends NoteAbstract
{
    private Vector m_notes;
    
    public MultiNote(final Vector notes) {
        this.m_notes = fromLowestToHighest(notes);
    }
    
    public boolean contains(final Note aNote) {
        return this.m_notes.indexOf(aNote) != -1;
    }
    
    public Note getLongestNote() {
        return getLongestNote(this.toArray());
    }
    
    public static Note getLongestNote(final Note[] notes) {
        final float length = 0.0f;
        float currentNoteLength = 0.0f;
        Note maxNote = null;
        for (int i = 0; i < notes.length && maxNote == null; ++i) {
            currentNoteLength = notes[i].getDuration();
            if (currentNoteLength > length) {
                maxNote = notes[i];
            }
        }
        return maxNote;
    }
    
    public static Note[] getNotesShorterThan(final Note[] notes, final int aStrictDuration) {
        final Vector shorterNotes = new Vector();
        Note[] notesArray = null;
        for (int i = 0; i < notes.length; ++i) {
            if (notes[i].getStrictDuration() < aStrictDuration) {
                shorterNotes.addElement(notes[i]);
            }
        }
        if (shorterNotes.size() > 0) {
            notesArray = new Note[shorterNotes.size()];
            shorterNotes.toArray(notesArray);
        }
        return notesArray;
    }
    
    public static Note getLowestNote(final Note[] notes) {
        Note lowestNote = notes[0];
        for (int i = 1; i < notes.length; ++i) {
            if (notes[i].isLowerThan(lowestNote)) {
                lowestNote = notes[i];
            }
        }
        return lowestNote;
    }
    
    public static Note getHighestNote(final Note[] notes) {
        Note highestNote = notes[0];
        for (int i = 1; i < notes.length; ++i) {
            if (notes[i].isHigherThan(highestNote)) {
                highestNote = notes[i];
            }
        }
        return highestNote;
    }
    
    public Note getShortestNote() {
        Note shortNote = this.m_notes.elementAt(0);
        final float length = shortNote.getDuration();
        float currentNoteLength = 0.0f;
        for (int i = 1; i < this.m_notes.size(); ++i) {
            currentNoteLength = this.m_notes.elementAt(i).getDuration();
            if (currentNoteLength < length) {
                shortNote = this.m_notes.elementAt(i);
            }
        }
        return shortNote;
    }
    
    public Note getHighestNote() {
        Note highestNote = this.m_notes.elementAt(0);
        for (int i = 1; i < this.m_notes.size(); ++i) {
            if (this.m_notes.elementAt(i).isHigherThan(highestNote)) {
                highestNote = this.m_notes.elementAt(i);
            }
        }
        return highestNote;
    }
    
    public Note[] getNotesBeginningTie() {
        final Vector notesBT = new Vector();
        for (int i = 0; i < this.m_notes.size(); ++i) {
            if (this.m_notes.elementAt(i).isBeginningTie()) {
                notesBT.addElement(this.m_notes.elementAt(i));
            }
        }
        if (notesBT.size() > 0) {
            final Note[] notesBTasArray = new Note[notesBT.size()];
            notesBT.toArray(notesBTasArray);
            return notesBTasArray;
        }
        return null;
    }
    
    public static Note[] excludeTiesEndings(final Note[] notes) {
        final Vector withoutTiesEnding = new Vector();
        Note[] withoutTiesEndingArray = null;
        for (int i = 0; i < notes.length; ++i) {
            if (!notes[i].isEndingTie()) {
                withoutTiesEnding.addElement(notes[i]);
            }
        }
        if (withoutTiesEnding.size() > 0) {
            withoutTiesEndingArray = new Note[withoutTiesEnding.size()];
            withoutTiesEnding.toArray(withoutTiesEndingArray);
        }
        return withoutTiesEndingArray;
    }
    
    public Note getLowestNote() {
        return getLowestNote(this.toArray());
    }
    
    public boolean hasUniqueStrictDuration() {
        return this.getStrictDurations().length == 1;
    }
    
    public short[] getStrictDurations() {
        final Vector durations = new Vector();
        short currentDuration = 0;
        for (int i = 0; i < this.m_notes.size(); ++i) {
            currentDuration = this.m_notes.elementAt(i).getStrictDuration();
            if (durations.indexOf(new Short(currentDuration)) == -1) {
                durations.addElement(new Short(currentDuration));
            }
        }
        if (durations.size() == 0) {
            return null;
        }
        final Vector sortedDurations = new Vector();
        for (int j = 0; j < durations.size(); ++j) {
            int k;
            for (k = 0; k < sortedDurations.size() && sortedDurations.elementAt(k) < durations.elementAt(j); ++k) {}
            sortedDurations.insertElementAt(durations.elementAt(j), k);
        }
        final short[] durationsAsArray = new short[sortedDurations.size()];
        for (int l = 0; l < sortedDurations.size(); ++l) {
            durationsAsArray[l] = sortedDurations.elementAt(l);
        }
        return durationsAsArray;
    }
    
    public MultiNote[] normalize() {
        final Hashtable splitter = new Hashtable();
        for (int i = 0; i < this.m_notes.size(); ++i) {
            final Note note = this.m_notes.elementAt(i);
            final Short key = new Short(note.getStrictDuration());
            if (splitter.containsKey(key)) {
                splitter.get(key).addElement(note);
            }
            else {
                final Vector v = new Vector();
                v.addElement(note);
                splitter.put(key, v);
            }
        }
        final short[] strictDurations = this.getStrictDurations();
        final MultiNote[] normalizedChords = new MultiNote[strictDurations.length];
        for (int j = 0; j < strictDurations.length; ++j) {
            normalizedChords[j] = new MultiNote(splitter.get(new Short(strictDurations[j])));
        }
        return normalizedChords;
    }
    
    private static Vector fromLowestToHighest(final Vector original) {
        final Vector orderedNotes = new Vector();
        for (int i = 0; i < original.size(); ++i) {
            int j;
            for (j = 0; j < orderedNotes.size() && orderedNotes.elementAt(j).isLowerThan(original.elementAt(i)); ++j) {}
            orderedNotes.insertElementAt(original.elementAt(i), j);
        }
        return orderedNotes;
    }
    
    public boolean hasAccidental() {
        for (int i = 1; i < this.m_notes.size(); ++i) {
            if (this.m_notes.elementAt(i).hasAccidental()) {
                return true;
            }
        }
        return false;
    }
    
    public Vector getNotesAsVector() {
        return (Vector)this.m_notes.clone();
    }
    
    public Note[] toArray() {
        if (this.m_notes.size() > 0) {
            final Note[] notes = new Note[this.m_notes.size()];
            return this.m_notes.toArray(notes);
        }
        return null;
    }
}
