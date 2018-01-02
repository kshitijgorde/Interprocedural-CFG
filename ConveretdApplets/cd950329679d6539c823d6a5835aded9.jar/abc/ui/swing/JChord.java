// 
// Decompiled by Procyon v0.5.30
// 

package abc.ui.swing;

import java.awt.Point;
import java.awt.BasicStroke;
import java.awt.Stroke;
import java.awt.Graphics2D;
import abc.notation.MusicElement;
import java.awt.geom.Point2D;
import abc.notation.Note;
import abc.notation.MultiNote;

class JChord extends JScoreElementAbstract
{
    protected MultiNote multiNote;
    protected Note[] m_notes;
    protected JNote[] m_sNoteInstances;
    protected JNote anchor;
    protected boolean isStemUp;
    protected JChord[] m_normalizedChords;
    
    public JChord(final MultiNote multiNote, final ScoreMetrics metrics, final Point2D base) {
        super(metrics);
        this.multiNote = null;
        this.m_notes = null;
        this.m_sNoteInstances = null;
        this.anchor = null;
        this.isStemUp = true;
        this.m_normalizedChords = null;
        this.multiNote = multiNote;
        this.m_notes = multiNote.toArray();
        if (multiNote.hasUniqueStrictDuration()) {
            this.m_sNoteInstances = new JNote[this.m_notes.length];
            for (int i = 0; i < this.m_notes.length; ++i) {
                this.m_sNoteInstances[i] = new JChordNote(this.m_notes[i], new Point2D.Double(), this.m_metrics);
                if (this.m_sNoteInstances[i].getWidth() > this.m_width) {
                    this.m_width = this.m_sNoteInstances[i].getWidth();
                }
            }
            this.setStemUp(this.isStemUp);
        }
        else {
            (this.m_sNoteInstances = new JNote[1])[0] = new JNote(multiNote.getHighestNote(), new Point2D.Double(), this.m_metrics);
            this.m_width = this.m_sNoteInstances[0].getWidth();
            final MultiNote[] h = multiNote.normalize();
            final short[] durations = multiNote.getStrictDurations();
            if (durations.length > 2) {
                System.err.println("abc4j - warning : chords with more than 2 differents strict duration aren't supported : only 2 smaller durations are taken into account");
            }
            this.m_normalizedChords = new JChord[2];
            final MultiNote fastest = h[0];
            JChord jChord = this.createNormalizedChord(fastest, metrics, base);
            (this.m_normalizedChords[0] = jChord).setStemUp(true);
            final MultiNote slowest = h[1];
            jChord = this.createNormalizedChord(slowest, metrics, base);
            (this.m_normalizedChords[1] = jChord).setStemUp(false);
        }
        this.setBase(base);
    }
    
    protected JNote createAnchorNote(final Note note, final Point2D base, final ScoreMetrics metrics) {
        final JNote jNote = new JNote(note, new Point2D.Double(), this.m_metrics);
        return jNote;
    }
    
    protected JChord createNormalizedChord(final MultiNote mNote, final ScoreMetrics mtrx, final Point2D base) {
        return new JChord(mNote, mtrx, base);
    }
    
    public MusicElement getMusicElement() {
        return this.multiNote;
    }
    
    public JNote[] getScoreElements() {
        return this.m_sNoteInstances;
    }
    
    public void setStaffLine(final JStaffLine staffLine) {
        for (int i = 0; i < this.m_sNoteInstances.length; ++i) {
            this.m_sNoteInstances[i].setStaffLine(staffLine);
        }
        super.setStaffLine(staffLine);
    }
    
    public void setBase(final Point2D base) {
        if (this.m_normalizedChords != null) {
            for (int i = 0; i < this.m_normalizedChords.length; ++i) {
                this.m_normalizedChords[i].setBase(base);
            }
        }
        super.setBase(base);
    }
    
    protected void onBaseChanged() {
        if (this.m_normalizedChords == null) {
            double biggestStemX = -1.0;
            for (int i = 0; i < this.m_sNoteInstances.length; ++i) {
                this.m_sNoteInstances[i].setBase(this.m_base);
                if (this.m_sNoteInstances[i].getStemBegin().getX() > biggestStemX) {
                    biggestStemX = this.m_sNoteInstances[i].getStemBegin().getX();
                }
            }
            for (int i = 0; i < this.m_sNoteInstances.length; ++i) {
                final Point2D stemBegin = this.m_sNoteInstances[i].getStemBegin();
                final Point2D newStemBegin = new Point2D.Double(biggestStemX, stemBegin.getY());
                this.m_sNoteInstances[i].setStemBegin(newStemBegin);
            }
        }
        else {
            for (int j = 0; j < this.m_normalizedChords.length; ++j) {
                this.m_normalizedChords[j].onBaseChanged();
            }
        }
    }
    
    public double render(final Graphics2D context) {
        if (this.m_normalizedChords == null) {
            for (int i = 0; i < this.m_sNoteInstances.length; ++i) {
                final JNote n = this.m_sNoteInstances[i];
                n.render(context);
            }
            final Stroke defaultStroke = context.getStroke();
            final BasicStroke notesLinkStroke = this.m_metrics.getStemStroke();
            context.setStroke(notesLinkStroke);
            if (this.multiNote.getLongestNote().getStrictDuration() < 192) {
                context.drawLine((int)this.m_sNoteInstances[0].getStemBegin().getX(), (int)this.m_sNoteInstances[0].getStemBegin().getY(), (int)this.m_sNoteInstances[this.m_sNoteInstances.length - 1].getStemBegin().getX(), (int)this.m_sNoteInstances[this.m_sNoteInstances.length - 1].getStemBegin().getY());
            }
            context.setStroke(defaultStroke);
        }
        else {
            for (int i = 0; i < this.m_normalizedChords.length; ++i) {
                this.m_normalizedChords[i].render(context);
            }
        }
        return this.m_width;
    }
    
    public void setStemUp(final boolean isUp) {
        this.isStemUp = isUp;
        if (isUp) {
            this.m_sNoteInstances[0] = new JChordNote(this.m_notes[0], this.m_sNoteInstances[0].getBase(), this.m_metrics);
            final JNote highestJNote = this.m_sNoteInstances[this.m_sNoteInstances.length - 1];
            final Note highestNote = (Note)highestJNote.getMusicElement();
            this.anchor = this.createAnchorNote(highestNote, highestJNote.getBase(), this.m_metrics);
            this.m_sNoteInstances[this.m_sNoteInstances.length - 1] = this.anchor;
            for (int i = 0; i < this.m_sNoteInstances.length; ++i) {
                this.m_sNoteInstances[i].setStemUp(true);
            }
        }
        else {
            this.m_sNoteInstances[this.m_sNoteInstances.length - 1] = new JChordNote(this.m_notes[this.m_notes.length - 1], this.m_sNoteInstances[this.m_sNoteInstances.length - 1].getBase(), this.m_metrics);
            final JNote lowestJNote = this.m_sNoteInstances[0];
            final Note lowestNote = (Note)lowestJNote.getMusicElement();
            this.anchor = this.createAnchorNote(lowestNote, lowestJNote.getBase(), this.m_metrics);
            this.m_sNoteInstances[0] = this.anchor;
            for (int i = 0; i < this.m_sNoteInstances.length; ++i) {
                this.m_sNoteInstances[i].setStemUp(false);
            }
        }
    }
    
    public JScoreElement getScoreElementAt(final Point point) {
        JScoreElement scoreEl = null;
        if (this.m_normalizedChords != null) {
            for (int i = 0; i < this.m_normalizedChords.length && scoreEl == null; scoreEl = this.m_normalizedChords[i].getScoreElementAt(point), ++i) {}
        }
        else {
            for (int i = 0; i < this.m_sNoteInstances.length; ++i) {
                scoreEl = this.m_sNoteInstances[i].getScoreElementAt(point);
                if (scoreEl != null) {
                    return scoreEl;
                }
            }
        }
        return scoreEl;
    }
}
