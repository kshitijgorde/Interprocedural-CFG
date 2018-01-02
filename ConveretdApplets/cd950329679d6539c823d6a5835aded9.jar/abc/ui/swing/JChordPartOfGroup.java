// 
// Decompiled by Procyon v0.5.30
// 

package abc.ui.swing;

import abc.notation.Note;
import java.awt.geom.Point2D;
import abc.notation.MultiNote;

class JChordPartOfGroup extends JChord implements JGroupableNote
{
    public JChordPartOfGroup(final MultiNote multiNote, final ScoreMetrics metrics, final Point2D base) {
        super(multiNote, metrics, base);
    }
    
    protected JNote createAnchorNote(final Note note, final Point2D base, final ScoreMetrics metrics) {
        return new JNotePartOfGroup(note, new Point2D.Double(), this.m_metrics);
    }
    
    protected JChord createNormalizedChord(final MultiNote mNote, final ScoreMetrics mtrx, final Point2D base) {
        if (this.multiNote.getStrictDurations()[0] == mNote.getStrictDurations()[0]) {
            return new JChordPartOfGroup(mNote, mtrx, base);
        }
        return new JChord(mNote, mtrx, base);
    }
    
    public void setStemYEnd(final int value) {
        if (this.m_normalizedChords != null) {
            ((JChordPartOfGroup)this.m_normalizedChords[0]).setStemYEnd(value);
        }
        else {
            ((JNotePartOfGroup)this.anchor).setStemYEnd(value);
        }
    }
    
    public int getStemYEnd() {
        if (this.m_normalizedChords != null) {
            return ((JChordPartOfGroup)this.m_normalizedChords[0]).getStemYEnd();
        }
        return ((JNotePartOfGroup)this.anchor).getStemYEnd();
    }
    
    public Point2D getStemBegin() {
        if (this.m_normalizedChords != null) {
            return ((JChordPartOfGroup)this.m_normalizedChords[0]).getStemBegin();
        }
        return ((JNotePartOfGroup)this.anchor).getStemBegin();
    }
    
    public JNotePartOfGroup getReferenceNoteForGroup() {
        if (this.m_normalizedChords != null) {
            return ((JChordPartOfGroup)this.m_normalizedChords[0]).getReferenceNoteForGroup();
        }
        return (JNotePartOfGroup)this.anchor;
    }
}
