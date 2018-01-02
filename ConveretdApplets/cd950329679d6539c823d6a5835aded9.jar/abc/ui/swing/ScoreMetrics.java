// 
// Decompiled by Procyon v0.5.30
// 

package abc.ui.swing;

import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.geom.Rectangle2D;
import java.awt.Font;

class ScoreMetrics
{
    public static final char STAFF_SIX_LINES = '\uf03d';
    public static final char[] BAR_LINE;
    public static final char[] DOT;
    public static final char[] STROKE;
    public static final char[] NOTE;
    public static final char[] NOTE_LONGER;
    public static final char STEM_COMBINE_UP_SINGLE = '\uf021';
    public static final char STEM_COMBINE_UP_DOUBLE = '\uf040';
    public static final char[] SHARP;
    public static final char[] FLAT;
    public static final char[] NATURAL;
    public static final float DEFAULT_SIZE = 45.0f;
    private double noteHeigth;
    private double noteWidth;
    private Font myFont;
    private Rectangle2D staffCharBounds;
    private Rectangle2D sharpBounds;
    private Rectangle2D naturalBounds;
    private Rectangle2D flatBounds;
    private Rectangle2D quarterNoteBounds;
    private BasicStroke notesLinkStroke;
    private BasicStroke stemStroke;
    private int noteStrokeLength;
    private int stemLength;
    private int notesSpacing;
    protected double biggestAccidentalWidth;
    
    public ScoreMetrics(final Graphics2D g2) {
        this(g2, 45.0f);
    }
    
    public ScoreMetrics(final Graphics2D g2, final float size) {
        this.noteHeigth = -1.0;
        this.noteWidth = -1.0;
        this.myFont = null;
        this.staffCharBounds = null;
        this.sharpBounds = null;
        this.naturalBounds = null;
        this.flatBounds = null;
        this.quarterNoteBounds = null;
        this.notesLinkStroke = null;
        this.stemStroke = null;
        this.noteStrokeLength = -1;
        this.stemLength = -1;
        this.notesSpacing = -1;
        this.biggestAccidentalWidth = -1.0;
        try {
            final FontRenderContext frc = g2.getFontRenderContext();
            this.myFont = Font.createFont(0, this.getClass().getResourceAsStream("SONORA.TTF"));
            this.myFont = this.myFont.deriveFont(size);
            this.staffCharBounds = new TextLayout(new Character('\uf03d').toString(), this.myFont, frc).getBounds();
            this.noteHeigth = this.staffCharBounds.getHeight() / 4.1;
            this.sharpBounds = new TextLayout(new Character(ScoreMetrics.SHARP[0]).toString(), this.myFont, frc).getBounds();
            this.flatBounds = new TextLayout(new Character(ScoreMetrics.FLAT[0]).toString(), this.myFont, frc).getBounds();
            this.naturalBounds = new TextLayout(new Character(ScoreMetrics.NATURAL[0]).toString(), this.myFont, frc).getBounds();
            this.quarterNoteBounds = new TextLayout(new Character(JNote.QUARTER_NOTE[0]).toString(), this.myFont, frc).getBounds();
            this.noteWidth = new TextLayout(new Character(ScoreMetrics.NOTE[0]).toString(), this.myFont, frc).getBounds().getWidth();
            this.notesLinkStroke = new BasicStroke((float)(this.noteWidth / 3.0), 0, 0);
            this.stemStroke = new BasicStroke((float)(this.noteWidth / 12.0));
            this.stemLength = (int)(this.noteHeigth * 3.0);
            this.noteStrokeLength = 2;
            this.notesSpacing = (int)(1.5 * this.noteWidth);
            this.biggestAccidentalWidth = ((this.getFlatBounds().getWidth() > this.getNaturalBounds().getWidth()) ? this.getFlatBounds().getWidth() : this.getNaturalBounds().getWidth());
            this.biggestAccidentalWidth = ((this.getSharpBounds().getWidth() > this.biggestAccidentalWidth) ? this.getSharpBounds().getWidth() : this.biggestAccidentalWidth);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public double getNoteHeigth() {
        return this.noteHeigth;
    }
    
    public double getNoteStrokeLength() {
        return this.noteStrokeLength;
    }
    
    public double getNoteWidth() {
        return this.noteWidth;
    }
    
    public double getNotesSpacing() {
        return this.notesSpacing;
    }
    
    public BasicStroke getNotesLinkStroke() {
        return this.notesLinkStroke;
    }
    
    public Rectangle2D getStaffCharBounds() {
        return this.staffCharBounds;
    }
    
    public BasicStroke getStemStroke() {
        return this.stemStroke;
    }
    
    public int getStemLength() {
        return this.stemLength;
    }
    
    public Rectangle2D getSharpBounds() {
        return this.sharpBounds;
    }
    
    public Rectangle2D getNaturalBounds() {
        return this.naturalBounds;
    }
    
    public Rectangle2D getFlatBounds() {
        return this.flatBounds;
    }
    
    public double getBiggestAccidentalWidth() {
        return this.biggestAccidentalWidth;
    }
    
    public Rectangle2D getQuarterNoteBounds() {
        return this.quarterNoteBounds;
    }
    
    public Font getFont() {
        return this.myFont;
    }
    
    static {
        BAR_LINE = new char[] { '\uf05c' };
        DOT = new char[] { '\uf06b' };
        STROKE = new char[] { '\uf05f' };
        NOTE = new char[] { '\uf0cf' };
        NOTE_LONGER = new char[] { '\uf092' };
        SHARP = new char[] { '\uf023' };
        FLAT = new char[] { '\uf062' };
        NATURAL = new char[] { '\uf06e' };
    }
}
