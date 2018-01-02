// 
// Decompiled by Procyon v0.5.30
// 

package abc.ui.swing;

import java.awt.Point;
import java.awt.BasicStroke;
import java.awt.Stroke;
import java.awt.Graphics2D;
import abc.notation.MusicElement;
import abc.notation.MultiNote;
import abc.notation.NoteAbstract;
import java.awt.geom.Point2D;
import abc.notation.Note;

class JGroupOfNotes extends JScoreElementAbstract
{
    public static final char[] DIGITS;
    protected Note[] m_notes;
    protected JGroupableNote[] m_jNotes;
    protected int m_stemYend;
    protected int nUpletSize;
    
    public JGroupOfNotes(final ScoreMetrics metrics, final Point2D base, final NoteAbstract[] notes) {
        super(metrics);
        this.m_notes = null;
        this.m_jNotes = null;
        this.m_stemYend = -1;
        this.nUpletSize = -1;
        if (notes.length <= 1) {
            throw new IllegalArgumentException(this.m_notes + "is not a group of notes, length = " + this.m_notes.length);
        }
        this.m_notes = new Note[notes.length];
        this.m_jNotes = new JGroupableNote[this.m_notes.length];
        for (int i = 0; i < notes.length; ++i) {
            if (notes[i] instanceof Note) {
                this.m_notes[i] = (Note)notes[i];
                this.m_jNotes[i] = new JNotePartOfGroup(this.m_notes[i], new Point2D.Double(), this.m_metrics);
            }
            else {
                this.m_jNotes[i] = new JChordPartOfGroup((MultiNote)notes[i], this.m_metrics, new Point2D.Double());
                this.m_notes[i] = (Note)((JChordPartOfGroup)this.m_jNotes[i]).getReferenceNoteForGroup().getMusicElement();
            }
        }
        if (notes[0].getTuplet() != null) {
            this.nUpletSize = notes[0].getTuplet().getNotesAsVector().size();
        }
        this.setBase(base);
    }
    
    public MusicElement getMusicElement() {
        return null;
    }
    
    public void setStaffLine(final JStaffLine staffLine) {
        for (int i = 0; i < this.m_jNotes.length; ++i) {
            ((JScoreElementAbstract)this.m_jNotes[i]).setStaffLine(staffLine);
        }
        super.setStaffLine(staffLine);
    }
    
    Note[] getMusicElements() {
        return this.m_notes;
    }
    
    JScoreElementAbstract[] getRenditionElements() {
        final JScoreElementAbstract[] array = new JScoreElementAbstract[this.m_jNotes.length];
        System.arraycopy(this.m_jNotes, 0, array, 0, this.m_jNotes.length);
        return array;
    }
    
    protected void onBaseChanged() {
        final Point2D currentBase = (Point2D)this.m_base.clone();
        final JGroupableNote highestNote = this.m_jNotes[Note.getHighestNoteIndex(this.m_notes)];
        Point2D updatedBase = highestNote.getBase();
        updatedBase.setLocation(currentBase);
        ((JScoreElementAbstract)highestNote).setBase(updatedBase);
        this.m_stemYend = (int)highestNote.getStemBegin().getY() - this.m_metrics.getStemLength();
        final JGroupableNote firstNote = this.m_jNotes[0];
        JGroupableNote lastNote = this.m_jNotes[this.m_jNotes.length - 1];
        for (int i = 0; i < this.m_jNotes.length; ++i) {
            updatedBase = this.m_jNotes[i].getBase();
            updatedBase.setLocation(currentBase);
            ((JScoreElementAbstract)this.m_jNotes[i]).setBase(updatedBase);
            this.m_jNotes[i].setStemYEnd(this.m_stemYend);
            currentBase.setLocation(currentBase.getX() + this.m_jNotes[i].getWidth() + this.m_metrics.getNotesSpacing(), this.m_base.getY());
        }
        if (lastNote == null) {
            lastNote = firstNote;
        }
        final double firstNoteAccidentalWidth = firstNote.getWidth() - this.m_metrics.getNoteWidth();
        this.m_width = (int)(lastNote.getStemBegin().getX() - firstNote.getBase().getX() + firstNoteAccidentalWidth);
    }
    
    public double render(final Graphics2D context) {
        final Stroke defaultStroke = context.getStroke();
        for (int i = 0; i < this.m_jNotes.length; ++i) {
            final JGroupableNote n = this.m_jNotes[i];
            ((JScoreElementAbstract)n).render(context);
            final BasicStroke notesLinkStroke = this.m_metrics.getNotesLinkStroke();
            context.setStroke(notesLinkStroke);
            short[] longerRhythms = null;
            final short noteStrictDuration = this.m_notes[i].getStrictDuration();
            switch (noteStrictDuration) {
                case 24: {
                    longerRhythms = new short[] { 24 };
                    break;
                }
                case 12: {
                    longerRhythms = new short[] { 24, 12 };
                    break;
                }
                case 6: {
                    longerRhythms = new short[] { 24, 12, 6 };
                    break;
                }
            }
            for (int j = 0; j < longerRhythms.length; ++j) {
                int noteLinkY = -1;
                if (longerRhythms[j] == 24) {
                    noteLinkY = (int)(this.m_stemYend + notesLinkStroke.getLineWidth() / 2.5);
                }
                else if (longerRhythms[j] == 12) {
                    noteLinkY = (int)(this.m_stemYend + notesLinkStroke.getLineWidth() * 2.0f);
                }
                else if (longerRhythms[j] == 6) {
                    noteLinkY = (int)(this.m_stemYend + notesLinkStroke.getLineWidth() * 3.5);
                }
                int noteLinkEnd = -1;
                boolean nextNoteIsShorterOrEquals = false;
                boolean previousNoteIsShorterOrEquals = false;
                final boolean hasNext = i < this.m_jNotes.length - 1;
                final boolean hasPrevious = i > 0;
                if (hasNext) {
                    nextNoteIsShorterOrEquals = (this.m_notes[i + 1].getStrictDuration() <= longerRhythms[j]);
                }
                if (hasPrevious) {
                    previousNoteIsShorterOrEquals = (this.m_notes[i - 1].getStrictDuration() <= longerRhythms[j]);
                }
                if (hasPrevious) {
                    if (previousNoteIsShorterOrEquals) {
                        noteLinkEnd = (int)this.m_jNotes[i - 1].getStemBegin().getX();
                    }
                    else if (!hasNext || !nextNoteIsShorterOrEquals) {
                        noteLinkEnd = (int)(this.m_jNotes[i].getStemBegin().getX() - this.m_metrics.getNoteWidth() / 2.0);
                    }
                }
                else if (!nextNoteIsShorterOrEquals) {
                    noteLinkEnd = (int)(this.m_jNotes[i].getStemBegin().getX() + this.m_metrics.getNoteWidth() / 2.0);
                }
                if (noteLinkEnd != -1) {
                    context.drawLine((int)this.m_jNotes[i].getStemBegin().getX(), noteLinkY, noteLinkEnd, noteLinkY);
                }
            }
            context.setStroke(defaultStroke);
        }
        if (this.nUpletSize != -1) {
            final char[] chars = { JGroupOfNotes.DIGITS[this.nUpletSize - 1] };
            context.drawChars(chars, 0, 1, (int)(this.m_jNotes[0].getBase().getX() + this.m_width / 2.0), (int)(this.m_stemYend - this.m_metrics.getNoteHeigth() / 4.0));
        }
        return this.m_width;
    }
    
    public JScoreElement getScoreElementAt(final Point point) {
        JScoreElement scoreEl = null;
        for (int i = 0; i < this.m_jNotes.length; ++i) {
            scoreEl = this.m_jNotes[i].getScoreElementAt(point);
            if (scoreEl != null) {
                return scoreEl;
            }
        }
        return scoreEl;
    }
    
    static {
        DIGITS = new char[] { '\uf0c1', '\uf0aa', '\uf0a3', '\uf0a2', '\uf0b0', '\uf0a4', '\uf0a6', '\uf0a5', '\uf0bb' };
    }
}
