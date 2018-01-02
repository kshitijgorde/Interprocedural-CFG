// 
// Decompiled by Procyon v0.5.30
// 

package abc.ui.swing;

import java.awt.Stroke;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import abc.notation.MusicElement;
import java.awt.geom.Point2D;
import abc.notation.Note;

class JNote extends JScoreElementAbstract
{
    private static final double SPACE_RATIO_FOR_ACCIDENTALS = 1.3;
    protected Note note;
    protected Point2D notePosition;
    protected Point2D displayPosition;
    protected Point2D accidentalsPosition;
    protected Point2D slurDownAnchor;
    protected Point2D slurUpAnchor;
    protected Point2D dotsPosition;
    protected char[] noteChars;
    protected char[] accidentalsChars;
    protected boolean isStemUp;
    protected Point2D stemBeginPosition;
    protected Point2D stemUpBeginPosition;
    protected Point2D stemDownBeginPosition;
    public static final char[] WHOLE_NOTE;
    public static final char[] HALF_NOTE;
    public static final char[] QUARTER_NOTE;
    public static final char[] EIGHTH_NOTE;
    public static final char[] SIXTEENTH_NOTE;
    public static final char[] THIRTY_SECOND_NOTE;
    public static final char[] WHOLE_NOTE_STEM_DOWN;
    public static final char[] HALF_NOTE_STEM_DOWN;
    public static final char[] QUARTER_NOTE_STEM_DOWN;
    public static final char[] EIGHTH_NOTE_STEM_DOWN;
    public static final char[] SIXTEENTH_NOTE_STEM_DOWN;
    public static final char[] THIRTY_SECOND_NOTE_STEM_DOWN;
    public static final char[] DOUBLE_REST;
    public static final char[] WHOLE_REST;
    public static final char[] HALF_REST;
    public static final char[] QUARTER_REST;
    public static final char[] EIGHTH_REST;
    public static final char[] SIXTEENTH_REST;
    public static final char[] THIRTY_SECOND_REST;
    public static final char[] SIXTY_FOUR_REST;
    public static final char[] UNKNWON;
    
    public JNote(final Note noteValue, final Point2D base, final ScoreMetrics c) {
        super(c);
        this.note = null;
        this.notePosition = null;
        this.displayPosition = null;
        this.accidentalsPosition = null;
        this.slurUpAnchor = null;
        this.dotsPosition = null;
        this.noteChars = null;
        this.accidentalsChars = null;
        this.isStemUp = true;
        this.stemBeginPosition = null;
        this.stemUpBeginPosition = null;
        this.stemDownBeginPosition = null;
        this.note = noteValue;
        this.valuateNoteChars();
        this.setBase(base);
        if (!this.note.isRest()) {
            this.setStemUp(this.isStemUp);
        }
    }
    
    public MusicElement getMusicElement() {
        return this.note;
    }
    
    protected void valuateNoteChars() {
        final short noteDuration = this.note.getStrictDuration();
        if (this.note.isRest()) {
            switch (noteDuration) {
                case 6: {
                    this.noteChars = JNote.THIRTY_SECOND_REST;
                    break;
                }
                case 12: {
                    this.noteChars = JNote.SIXTEENTH_REST;
                    break;
                }
                case 24: {
                    this.noteChars = JNote.EIGHTH_REST;
                    break;
                }
                case 48: {
                    this.noteChars = JNote.QUARTER_REST;
                    break;
                }
                case 96: {
                    this.noteChars = JNote.HALF_REST;
                    break;
                }
                case 192: {
                    this.noteChars = JNote.WHOLE_REST;
                    break;
                }
                default: {
                    this.noteChars = JNote.UNKNWON;
                    break;
                }
            }
        }
        else if (this.isStemUp) {
            switch (noteDuration) {
                case 6: {
                    this.noteChars = JNote.THIRTY_SECOND_NOTE;
                    break;
                }
                case 12: {
                    this.noteChars = JNote.SIXTEENTH_NOTE;
                    break;
                }
                case 24: {
                    this.noteChars = JNote.EIGHTH_NOTE;
                    break;
                }
                case 48: {
                    this.noteChars = JNote.QUARTER_NOTE;
                    break;
                }
                case 96: {
                    this.noteChars = JNote.HALF_NOTE;
                    break;
                }
                case 192: {
                    this.noteChars = JNote.WHOLE_NOTE;
                    break;
                }
                default: {
                    this.noteChars = JNote.UNKNWON;
                    break;
                }
            }
        }
        else {
            switch (noteDuration) {
                case 6: {
                    this.noteChars = JNote.THIRTY_SECOND_NOTE_STEM_DOWN;
                    break;
                }
                case 12: {
                    this.noteChars = JNote.SIXTEENTH_NOTE_STEM_DOWN;
                    break;
                }
                case 24: {
                    this.noteChars = JNote.EIGHTH_NOTE_STEM_DOWN;
                    break;
                }
                case 48: {
                    this.noteChars = JNote.QUARTER_NOTE_STEM_DOWN;
                    break;
                }
                case 96: {
                    this.noteChars = JNote.HALF_NOTE_STEM_DOWN;
                    break;
                }
                case 192: {
                    this.noteChars = JNote.WHOLE_NOTE_STEM_DOWN;
                    break;
                }
                default: {
                    this.noteChars = JNote.UNKNWON;
                    break;
                }
            }
        }
    }
    
    public void setStemUp(final boolean isUp) {
        this.isStemUp = isUp;
        this.valuateNoteChars();
        if (isUp) {
            this.stemBeginPosition = this.stemUpBeginPosition;
        }
        else {
            this.stemBeginPosition = this.stemDownBeginPosition;
        }
    }
    
    public Point2D getStemBegin() {
        return this.stemBeginPosition;
    }
    
    public void setStemBegin(final Point2D newStemBeginPos) {
        final double xDelta = newStemBeginPos.getX() - this.getStemBegin().getX();
        final double yDelta = newStemBeginPos.getY() - this.getStemBegin().getY();
        final Point2D newBase = new Point2D.Double(this.m_base.getX() + xDelta, this.m_base.getY() + yDelta);
        this.setBase(newBase);
    }
    
    protected void onBaseChanged() {
        final ScoreMetrics c = this.m_metrics;
        final Point2D base = this.m_base;
        int noteY = 0;
        if (this.note.isRest()) {
            noteY = (int)(base.getY() - 2.0 * c.getNoteHeigth());
        }
        else {
            noteY = (int)(base.getY() - getCorrectedOffset(this.note) * c.getNoteHeigth());
        }
        double accidentalsWidth = 0.0;
        if (this.note.getAccidental() != 10) {
            switch (this.note.getAccidental()) {
                case -1: {
                    this.accidentalsChars = ScoreMetrics.FLAT;
                    accidentalsWidth = c.getFlatBounds().getWidth();
                    break;
                }
                case 1: {
                    this.accidentalsChars = ScoreMetrics.SHARP;
                    accidentalsWidth = c.getSharpBounds().getWidth();
                    break;
                }
                case 0: {
                    this.accidentalsChars = ScoreMetrics.NATURAL;
                    accidentalsWidth = c.getNaturalBounds().getWidth();
                    break;
                }
                default: {
                    throw new IllegalArgumentException("Incorrect accidental for " + this.note + " : " + this.note.getAccidental());
                }
            }
        }
        final double noteX = base.getX() + accidentalsWidth * 1.3;
        this.displayPosition = new Point2D.Double(noteX, noteY);
        if (this.note.getStrictDuration() == 192 || this.note.getStrictDuration() == 12 || this.note.getStrictDuration() == 6) {
            this.notePosition = new Point2D.Double(this.displayPosition.getX(), this.displayPosition.getY() + this.m_metrics.getNoteHeigth() * 0.5);
        }
        else {
            this.notePosition = (Point2D)this.displayPosition.clone();
        }
        this.stemUpBeginPosition = new Point2D.Double(this.notePosition.getX() + this.m_metrics.getNoteWidth() * 0.93, this.notePosition.getY() - this.m_metrics.getNoteHeigth() / 2.0);
        this.stemDownBeginPosition = new Point2D.Double(this.notePosition.getX(), this.notePosition.getY() - this.m_metrics.getNoteHeigth() / 2.0);
        this.setStemUp(this.isStemUp);
        if (this.note.hasAccidental()) {
            this.accidentalsPosition = new Point2D.Double(base.getX(), this.notePosition.getY() - this.m_metrics.getNoteHeigth() / 2.0);
        }
        this.m_width = (int)(accidentalsWidth + c.getNoteWidth());
        this.onNotePositionChanged();
    }
    
    protected void onNotePositionChanged() {
        if (this.note.countDots() != 0) {
            this.dotsPosition = new Point2D.Double(this.notePosition.getX() + this.m_metrics.getNoteWidth() * 1.2, this.notePosition.getY() - this.m_metrics.getNoteHeigth() * 0.05);
        }
        this.slurDownAnchor = new Point2D.Double(this.notePosition.getX() + this.m_metrics.getNoteWidth() / 2.0, this.notePosition.getY() + this.m_metrics.getNoteWidth() / 4.0);
        this.slurUpAnchor = new Point2D.Double(this.displayPosition.getX() + this.m_metrics.getNoteWidth() / 2.0, this.notePosition.getY() - this.m_metrics.getNoteWidth() / 4.0);
    }
    
    public static double getCorrectedOffset(final Note note) {
        double positionOffset = getOffset(note);
        final short noteDuration = note.getStrictDuration();
        switch (noteDuration) {
            case 6:
            case 12:
            case 192: {
                positionOffset += 0.5;
                break;
            }
        }
        return positionOffset;
    }
    
    public static double getOffset(final Note note) {
        double positionOffset = 0.0;
        final byte noteHeight = note.getStrictHeight();
        switch (noteHeight) {
            case 0: {
                positionOffset = -1.5;
                break;
            }
            case 2: {
                positionOffset = -1.0;
                break;
            }
            case 4: {
                positionOffset = -0.5;
                break;
            }
            case 5: {
                positionOffset = 0.0;
                break;
            }
            case 7: {
                positionOffset = 0.5;
                break;
            }
            case 9: {
                positionOffset = 1.0;
                break;
            }
            case 11: {
                positionOffset = 1.5;
                break;
            }
        }
        positionOffset += note.getOctaveTransposition() * 3.5;
        return positionOffset;
    }
    
    public Point2D getNotePosition() {
        return this.notePosition;
    }
    
    protected Point2D getDisplayPosition() {
        return this.displayPosition;
    }
    
    public Point2D getSlurUpAnchor() {
        return this.slurUpAnchor;
    }
    
    public Point2D getSlurDownAnchor() {
        return this.slurDownAnchor;
    }
    
    public Rectangle2D getBoundingBox() {
        final double noteGlyphHeight = this.m_metrics.getNoteHeigth() * 4.0;
        final Rectangle2D bb = new Rectangle2D.Double((int)this.displayPosition.getX(), (int)(this.displayPosition.getY() - noteGlyphHeight), this.m_width, noteGlyphHeight);
        return bb;
    }
    
    public double render(final Graphics2D g) {
        super.render(g);
        this.renderExtendedStaffLines(g, this.m_metrics, this.m_base);
        this.renderAccidentals(g);
        this.renderDots(g);
        this.renderNoteChars(g);
        return this.m_width;
    }
    
    protected void renderNoteChars(final Graphics2D gfx) {
        gfx.drawChars(this.noteChars, 0, 1, (int)this.displayPosition.getX(), (int)this.displayPosition.getY());
    }
    
    protected void renderAccidentals(final Graphics2D gfx) {
        if (this.accidentalsPosition != null) {
            gfx.drawChars(this.accidentalsChars, 0, 1, (int)this.accidentalsPosition.getX(), (int)this.accidentalsPosition.getY());
        }
    }
    
    protected void renderExtendedStaffLines(final Graphics2D context, final ScoreMetrics metrics, final Point2D base) {
        final int extSize = (int)metrics.getNoteWidth() / 3;
        if (this.note.getHeight() <= 0) {
            double currentOffset = getCorrectedOffset(new Note((byte)0, (byte)10));
            int currentPosition = (int)(base.getY() - currentOffset * metrics.getNoteHeigth() / 1.5);
            final double offset = getOffset(this.note);
            final Stroke dfs = context.getStroke();
            context.setStroke(metrics.getStemStroke());
            while (currentOffset >= offset) {
                context.drawLine((int)(this.displayPosition.getX() - extSize), currentPosition, (int)(this.displayPosition.getX() + metrics.getNoteWidth() + extSize), currentPosition);
                --currentOffset;
                currentPosition += (int)metrics.getNoteHeigth();
            }
            context.setStroke(dfs);
        }
        else if (this.note.getHeight() >= 21) {
            double currentOffset = getCorrectedOffset(new Note((byte)21, (byte)10));
            int currentPosition = (int)(base.getY() - currentOffset * metrics.getNoteHeigth() - metrics.getNoteHeigth() / 2.0);
            final double offset = getOffset(this.note);
            final Stroke dfs = context.getStroke();
            context.setStroke(metrics.getStemStroke());
            while (currentOffset <= offset) {
                context.drawLine((int)(this.displayPosition.getX() - extSize), currentPosition, (int)(this.displayPosition.getX() + metrics.getNoteWidth() + extSize), currentPosition);
                ++currentOffset;
                currentPosition -= (int)metrics.getNoteHeigth();
            }
            context.setStroke(dfs);
        }
    }
    
    protected void renderDots(final Graphics2D context) {
        if (this.dotsPosition != null) {
            context.drawChars(ScoreMetrics.DOT, 0, 1, (int)this.dotsPosition.getX(), (int)this.dotsPosition.getY());
        }
    }
    
    static {
        WHOLE_NOTE = new char[] { '\uf092' };
        HALF_NOTE = new char[] { '\uf068' };
        QUARTER_NOTE = new char[] { '\uf071' };
        EIGHTH_NOTE = new char[] { '\uf065' };
        SIXTEENTH_NOTE = new char[] { '\uf072' };
        THIRTY_SECOND_NOTE = new char[] { '\uf078' };
        WHOLE_NOTE_STEM_DOWN = new char[] { '\uf092' };
        HALF_NOTE_STEM_DOWN = new char[] { '\uf048' };
        QUARTER_NOTE_STEM_DOWN = new char[] { '\uf051' };
        EIGHTH_NOTE_STEM_DOWN = new char[] { '\uf045' };
        SIXTEENTH_NOTE_STEM_DOWN = new char[] { '\uf052' };
        THIRTY_SECOND_NOTE_STEM_DOWN = new char[] { '\uf058' };
        DOUBLE_REST = new char[] { '\uf0e3' };
        WHOLE_REST = new char[] { '\uf0b7' };
        HALF_REST = new char[] { '\uf0ee' };
        QUARTER_REST = new char[] { '\uf0ce' };
        EIGHTH_REST = new char[] { '\uf0e4' };
        SIXTEENTH_REST = new char[] { '\uf0c5' };
        THIRTY_SECOND_REST = new char[] { '\uf0a8' };
        SIXTY_FOUR_REST = new char[] { '\uf0f4' };
        UNKNWON = new char[] { '\uf0ad' };
    }
}
