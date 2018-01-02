// 
// Decompiled by Procyon v0.5.30
// 

package abc.ui.swing;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import abc.notation.Note;

class JChordNote extends JNotePartOfGroup
{
    public JChordNote(final Note noteValue, final Point2D base, final ScoreMetrics c) {
        super(noteValue, base, c);
    }
    
    protected void valuateNoteChars() {
        if (this.note.getStrictDuration() == 96 || this.note.getStrictDuration() == 192) {
            this.noteChars = ScoreMetrics.NOTE_LONGER;
        }
        else {
            this.noteChars = ScoreMetrics.NOTE;
        }
    }
    
    public double render(final Graphics2D context) {
        context.drawChars(this.noteChars, 0, 1, (int)this.displayPosition.getX(), (int)this.displayPosition.getY());
        this.renderExtendedStaffLines(context, this.m_metrics, this.m_base);
        this.renderAccidentals(context);
        this.renderDots(context);
        return this.m_width;
    }
}
