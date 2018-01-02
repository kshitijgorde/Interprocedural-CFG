// 
// Decompiled by Procyon v0.5.30
// 

package abc.ui.swing;

import java.awt.Shape;
import java.awt.geom.QuadCurve2D;
import java.awt.geom.GeneralPath;
import abc.notation.TwoNotesLink;
import java.awt.RenderingHints;
import java.awt.Color;
import java.awt.Graphics2D;
import abc.notation.Tuplet;
import abc.notation.NotesSeparator;
import abc.notation.EndOfStaffLine;
import abc.notation.BarLine;
import abc.notation.RepeatBarLine;
import abc.notation.NoteAbstract;
import abc.notation.MultiNote;
import abc.notation.Note;
import java.util.ArrayList;
import java.awt.Point;
import abc.notation.MusicElement;
import java.awt.geom.Point2D;
import abc.notation.TimeSignature;
import abc.notation.KeySignature;
import java.util.Vector;
import java.util.Hashtable;
import abc.notation.Tune;

class JTune extends JScoreElementAbstract
{
    protected Tune m_tune;
    protected Hashtable m_scoreElements;
    protected Vector m_beginningNotesLinkElements;
    private int m_staffLinesSpacing;
    private int XOffset;
    protected Vector m_staffLines;
    protected boolean m_isJustified;
    protected double m_height;
    private boolean currentStaffLineInitialized;
    private JStaffLine currentStaffLine;
    private KeySignature currentKey;
    private TimeSignature currentTime;
    private Point2D cursor;
    
    public JTune(final Tune tune, final Point2D base, final ScoreMetrics c) {
        this(tune, base, c, false);
    }
    
    public JTune(final Tune tune, final Point2D base, final ScoreMetrics c, final boolean isJustified) {
        super(c);
        this.m_tune = null;
        this.m_scoreElements = null;
        this.m_beginningNotesLinkElements = null;
        this.m_staffLinesSpacing = -1;
        this.XOffset = 0;
        this.m_staffLines = null;
        this.m_isJustified = false;
        this.m_height = -1.0;
        this.currentStaffLineInitialized = false;
        this.currentStaffLine = null;
        this.currentKey = null;
        this.currentTime = null;
        this.cursor = null;
        this.m_staffLines = new Vector();
        this.m_isJustified = isJustified;
        this.m_scoreElements = new Hashtable();
        this.m_beginningNotesLinkElements = new Vector();
        this.setTune(tune);
        this.setBase(base);
    }
    
    public void onBaseChanged() {
    }
    
    public MusicElement getMusicElement() {
        return null;
    }
    
    public double getHeight() {
        return this.m_height;
    }
    
    public void setStaffLinesSpacing(final int staffLinesSpacing) {
        this.m_staffLinesSpacing = staffLinesSpacing;
        this.setTune(this.m_tune);
    }
    
    public int getStaffLinesSpacing() {
        return this.m_staffLinesSpacing;
    }
    
    public Tune getTune() {
        return this.m_tune;
    }
    
    public Hashtable getRenditionObjectsMapping() {
        return this.m_scoreElements;
    }
    
    public JScoreElement getScoreElementAt(final Point location) {
        JScoreElement scoreEl = null;
        for (int i = 0; i < this.m_staffLines.size(); ++i) {
            scoreEl = this.m_staffLines.elementAt(i).getScoreElementAt(location);
            if (scoreEl != null) {
                return scoreEl;
            }
            scoreEl = null;
        }
        return scoreEl;
    }
    
    public void setTune(final Tune tune) {
        this.m_tune = tune;
        this.m_scoreElements.clear();
        this.m_staffLines.removeAllElements();
        final Tune.Music score = tune.getMusic();
        this.cursor = new Point(this.XOffset, 0);
        double componentWidth = 0.0;
        double componentHeight = 0.0;
        final ArrayList lessThanQuarter = new ArrayList();
        Tuplet tupletContainer = null;
        int staffLineNb = 0;
        this.currentKey = tune.getKey();
        this.currentTime = null;
        this.currentStaffLineInitialized = false;
        this.currentStaffLine = null;
        for (int i = 0; i < score.size(); ++i) {
            final MusicElement s = score.elementAt(i);
            if (((!(s instanceof Note) && !(s instanceof MultiNote)) || (s instanceof Note && ((Note)s).isRest()) || (s instanceof NoteAbstract && tupletContainer != null && !tupletContainer.equals(((NoteAbstract)s).getTuplet())) || (s instanceof NoteAbstract && tupletContainer == null && ((NoteAbstract)s).isPartOfTuplet()) || (s instanceof Note && ((Note)s).getStrictDuration() >= 48) || (s instanceof MultiNote && (!((MultiNote)s).hasUniqueStrictDuration() || ((MultiNote)s).getLongestNote().getStrictDuration() >= 48))) && lessThanQuarter.size() != 0) {
                this.appendToScore(lessThanQuarter);
                lessThanQuarter.clear();
            }
            if (s instanceof KeySignature) {
                this.currentKey = (KeySignature)s;
            }
            else if (s instanceof TimeSignature) {
                this.currentTime = (TimeSignature)s;
            }
            else if (s instanceof MultiNote) {
                tupletContainer = ((MultiNote)s).getTuplet();
                final Note[] tiesStart = ((MultiNote)s).getNotesBeginningTie();
                if (tiesStart != null) {
                    for (int j = 0; j < tiesStart.length; ++j) {
                        this.m_beginningNotesLinkElements.addElement(tiesStart[j]);
                    }
                }
                if (((MultiNote)s).getStrictDurations()[0] < 48) {
                    lessThanQuarter.add(s);
                }
                else {
                    this.appendToScore(new JChord((MultiNote)s, this.m_metrics, this.cursor));
                }
            }
            else if (s instanceof Note) {
                final Note note = (Note)s;
                if (note.isBeginingSlur() || note.isBeginningTie()) {
                    this.m_beginningNotesLinkElements.addElement(note);
                }
                final short strictDur = note.getStrictDuration();
                tupletContainer = note.getTuplet();
                if (strictDur < 48 && !note.isRest()) {
                    lessThanQuarter.add(note);
                }
                else {
                    final JNote noteR = new JNote(note, this.cursor, this.m_metrics);
                    if (note.getHeight() > 12) {
                        noteR.setStemUp(false);
                    }
                    this.appendToScore(noteR);
                }
            }
            else if (s instanceof RepeatBarLine) {
                this.appendToScore(new JRepeatBar((RepeatBarLine)s, this.cursor, this.m_metrics));
            }
            else if (s instanceof BarLine) {
                this.appendToScore(new JBar((BarLine)s, this.cursor, this.m_metrics));
            }
            else if (s instanceof EndOfStaffLine) {
                ++staffLineNb;
                if (this.cursor.getX() > componentWidth) {
                    componentWidth = (int)this.cursor.getX();
                }
                this.currentStaffLineInitialized = false;
            }
            else if (s instanceof NotesSeparator) {
                this.appendToScore(lessThanQuarter);
                lessThanQuarter.clear();
            }
        }
        if (lessThanQuarter.size() != 0) {
            this.appendToScore(lessThanQuarter);
            lessThanQuarter.clear();
        }
        if (this.cursor.getX() > componentWidth) {
            componentWidth = (int)this.cursor.getX();
        }
        componentHeight = (int)this.cursor.getY();
        this.m_width = componentWidth + this.m_metrics.getStaffCharBounds().getWidth();
        this.m_height = componentHeight + this.m_metrics.getStaffCharBounds().getHeight();
        if (this.m_isJustified) {
            this.justify();
        }
    }
    
    protected void appendToScore(final JScoreElementAbstract element) {
        if (!this.currentStaffLineInitialized) {
            this.currentStaffLine = this.initNewStaffLine();
            this.m_staffLines.addElement(this.currentStaffLine);
            this.currentStaffLineInitialized = true;
            element.setBase(this.cursor);
        }
        this.currentStaffLine.addElement(element);
        final double width = element.getWidth();
        final int cursorNewLocationX = (int)(this.cursor.getX() + width + this.m_metrics.getNotesSpacing());
        this.cursor.setLocation(cursorNewLocationX, this.cursor.getY());
        if (element instanceof JNote) {
            this.m_scoreElements.put(element.getMusicElement(), element);
        }
        else if (element instanceof JGroupOfNotes) {
            final JGroupOfNotes g = (JGroupOfNotes)element;
            for (int j = 0; j < g.getRenditionElements().length; ++j) {
                if (g.getRenditionElements()[j] instanceof JNote) {
                    this.m_scoreElements.put(g.getMusicElements()[j], g.getRenditionElements()[j]);
                }
                else if (g.getRenditionElements()[j] instanceof JChord) {
                    final JNote[] jnotes = ((JChord)g.getRenditionElements()[j]).getScoreElements();
                    final Vector notes = ((MultiNote)((JChord)g.getRenditionElements()[j]).getMusicElement()).getNotesAsVector();
                    for (int i = 0; i < jnotes.length; ++i) {
                        this.m_scoreElements.put(notes.elementAt(i), jnotes[i]);
                    }
                    this.m_scoreElements.put(g.getRenditionElements()[j].getMusicElement(), g.getRenditionElements()[j]);
                }
            }
        }
        else if (element instanceof JChord) {
            final JNote[] jnotes2 = ((JChord)element).getScoreElements();
            final Vector notes2 = ((MultiNote)((JChord)element).getMusicElement()).getNotesAsVector();
            for (int k = 0; k < jnotes2.length; ++k) {
                this.m_scoreElements.put(notes2.elementAt(k), jnotes2[k]);
            }
            this.m_scoreElements.put(element.getMusicElement(), element);
        }
    }
    
    private void appendToScore(final ArrayList lessThanQuarterGroup) {
        if (lessThanQuarterGroup.size() > 0) {
            JScoreElementAbstract renditionResult = null;
            JScoreElementAbstract[] renditionResultRootsElmts = new JScoreElementAbstract[lessThanQuarterGroup.size()];
            final NoteAbstract[] notes = lessThanQuarterGroup.toArray(new NoteAbstract[lessThanQuarterGroup.size()]);
            if (notes.length == 1) {
                if (notes[0] instanceof Note) {
                    renditionResult = new JNote((Note)notes[0], this.cursor, this.m_metrics);
                    renditionResultRootsElmts[0] = renditionResult;
                }
                else {
                    renditionResult = new JChord((MultiNote)notes[0], this.m_metrics, this.cursor);
                    renditionResultRootsElmts[0] = renditionResult;
                }
            }
            else {
                renditionResult = new JGroupOfNotes(this.m_metrics, this.cursor, notes);
                renditionResultRootsElmts = ((JGroupOfNotes)renditionResult).getRenditionElements();
            }
            this.appendToScore(renditionResult);
        }
    }
    
    public double render(final Graphics2D g2) {
        g2.setFont(this.m_metrics.getFont());
        g2.setColor(Color.BLACK);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        final int staffCharNb = (int)(this.m_width / this.m_metrics.getStaffCharBounds().getWidth());
        final char[] staffS = new char[staffCharNb + 1];
        for (int i = 0; i < staffS.length; ++i) {
            staffS[i] = '\uf03d';
        }
        JStaffLine currentStaffLine = null;
        for (int j = 0; j < this.m_staffLines.size(); ++j) {
            currentStaffLine = this.m_staffLines.elementAt(j);
            currentStaffLine.render(g2);
            g2.drawChars(staffS, 0, staffS.length, 0, (int)currentStaffLine.getBase().getY());
        }
        this.renderSlursAndTies(g2);
        return this.m_width;
    }
    
    protected void justify() {
        if (this.m_staffLines.size() > 0) {
            double maxWidth = this.m_staffLines.elementAt(0).getWidth();
            for (int i = 1; i < this.m_staffLines.size(); ++i) {
                final JStaffLine currentStaffLine = this.m_staffLines.elementAt(i);
                if (currentStaffLine.getWidth() > maxWidth) {
                    maxWidth = currentStaffLine.getWidth();
                }
            }
            for (int i = 0; i < this.m_staffLines.size(); ++i) {
                final JStaffLine currentStaffLine = this.m_staffLines.elementAt(i);
                if (currentStaffLine.getWidth() > maxWidth / 2.0) {
                    currentStaffLine.scaleToWidth(maxWidth);
                }
            }
        }
    }
    
    protected void renderSlursAndTies(final Graphics2D g2) {
        for (int j = 0; j < this.m_beginningNotesLinkElements.size(); ++j) {
            final NoteAbstract n = this.m_beginningNotesLinkElements.elementAt(j);
            TwoNotesLink link = n.getSlurDefinition();
            if (link == null) {
                link = ((Note)n).getTieDefinition();
            }
            if (link.getEnd() != null) {
                this.drawLinkDown(g2, link);
            }
        }
    }
    
    protected void drawLinkDown(final Graphics2D g2, final TwoNotesLink slurDef) {
        final JNote elmtStart = this.m_scoreElements.get(slurDef.getStart());
        if (slurDef.getEnd() != null) {
            final JNote elmtEnd = this.m_scoreElements.get(slurDef.getEnd());
            if (elmtStart.getStaffLine().equals(elmtEnd.getStaffLine())) {
                Point2D controlPoint = null;
                final Note lowestNote = this.m_tune.getMusic().getLowestNoteBewteen(slurDef.getStart(), slurDef.getEnd());
                if (lowestNote.equals(slurDef.getStart())) {
                    controlPoint = new Point2D.Double(elmtStart.getSlurDownAnchor().getX() + (elmtEnd.getSlurDownAnchor().getX() - elmtStart.getSlurDownAnchor().getX()) / 2.0, elmtStart.getSlurDownAnchor().getY() + this.m_metrics.getNoteWidth() / 4.0);
                }
                else if (lowestNote.equals(slurDef.getEnd())) {
                    controlPoint = new Point2D.Double(elmtStart.getSlurDownAnchor().getX() + (elmtEnd.getSlurDownAnchor().getX() - elmtStart.getSlurDownAnchor().getX()) / 2.0, elmtEnd.getSlurDownAnchor().getY() + this.m_metrics.getNoteWidth() / 4.0);
                }
                else {
                    final JNote lowestNoteGlyph = this.m_scoreElements.get(lowestNote);
                    controlPoint = new Point2D.Double(lowestNoteGlyph.getSlurDownAnchor().getX(), lowestNoteGlyph.getSlurDownAnchor().getY() + this.m_metrics.getNoteWidth() / 2.0);
                }
                final GeneralPath path = new GeneralPath();
                path.moveTo((int)elmtStart.getSlurDownAnchor().getX(), (int)elmtStart.getSlurDownAnchor().getY());
                QuadCurve2D q = new QuadCurve2D.Float();
                q.setCurve(elmtStart.getSlurDownAnchor(), this.newControl(elmtStart.getSlurDownAnchor(), controlPoint, elmtEnd.getSlurDownAnchor()), elmtEnd.getSlurDownAnchor());
                path.append(q, true);
                q = new QuadCurve2D.Float();
                controlPoint.setLocation(controlPoint.getX(), controlPoint.getY() + this.m_metrics.getNoteWidth() / 8.0);
                q.setCurve(elmtEnd.getSlurDownAnchor(), this.newControl(elmtStart.getSlurDownAnchor(), controlPoint, elmtEnd.getSlurDownAnchor()), elmtStart.getSlurDownAnchor());
                path.append(q, true);
                g2.fill(path);
                g2.draw(path);
            }
            else {
                System.err.println("Warning - ab4j limitation : Slurs / ties cannot be drawn accross several lines for now.");
            }
        }
    }
    
    private JStaffLine initNewStaffLine() {
        final JStaffLine sl = new JStaffLine(this.cursor, this.m_metrics);
        this.cursor.setLocation(0.0, this.cursor.getY() + this.m_staffLinesSpacing);
        final JClef clef = new JClef(this.cursor, this.m_metrics);
        sl.addElement(clef);
        double width = clef.getWidth();
        this.cursor.setLocation(this.cursor.getX() + width, this.cursor.getY());
        if (this.currentKey != null) {
            final JKeySignature sk = new JKeySignature(this.currentKey, this.cursor, this.m_metrics);
            sl.addElement(sk);
            width = sk.getWidth();
            final int cursorNewLocationX = (int)(this.cursor.getX() + width + this.m_metrics.getNotesSpacing());
            this.cursor.setLocation(cursorNewLocationX, this.cursor.getY());
        }
        if (this.currentTime != null && this.m_staffLines.size() == 0) {
            try {
                final JTimeSignature sig = new JTimeSignature(this.currentTime, this.cursor, this.m_metrics);
                sl.addElement(sig);
                width = (int)sig.getWidth();
                final int cursorNewLocationX = (int)(this.cursor.getX() + width + this.m_metrics.getNotesSpacing());
                this.cursor.setLocation(cursorNewLocationX, this.cursor.getY());
            }
            catch (Exception ex) {}
        }
        return sl;
    }
    
    private Point2D newControl(final Point2D start, final Point2D ctrl, final Point2D end) {
        final Point2D.Double newCtrl = new Point2D.Double();
        newCtrl.x = 2.0 * ctrl.getX() - (start.getX() + end.getX()) / 2.0;
        newCtrl.y = 2.0 * ctrl.getY() - (start.getY() + end.getY()) / 2.0;
        return newCtrl;
    }
}
