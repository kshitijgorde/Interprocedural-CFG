// 
// Decompiled by Procyon v0.5.30
// 

package abc.ui.swing;

import java.awt.Stroke;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.BasicStroke;
import java.awt.geom.Point2D;
import abc.notation.Note;

class JNotePartOfGroup extends JNote implements JGroupableNote
{
    protected int stemYEnd;
    
    public JNotePartOfGroup(final Note noteValue, final Point2D base, final ScoreMetrics c) {
        super(noteValue, base, c);
        this.stemYEnd = -1;
    }
    
    protected void valuateNoteChars() {
        this.noteChars = ScoreMetrics.NOTE;
    }
    
    protected void onBaseChanged() {
        super.onBaseChanged();
        final int noteY = (int)(this.m_base.getY() - getOffset(this.note) * this.m_metrics.getNoteHeigth());
        this.displayPosition.setLocation(this.displayPosition.getX(), noteY);
        final double noteX = this.displayPosition.getX();
        final BasicStroke stemStroke = this.m_metrics.getNotesLinkStroke();
        final int stemYBegin = (int)(noteY - this.m_metrics.getNoteHeigth() / 6.0);
        this.stemUpBeginPosition = new Point2D.Double(noteX + this.m_metrics.getNoteWidth() - stemStroke.getLineWidth() / 10.0f, stemYBegin);
        this.stemDownBeginPosition = new Point2D.Double(noteX, stemYBegin);
        this.notePosition = new Point2D.Double(this.displayPosition.getX(), this.displayPosition.getY() + this.m_metrics.getNoteHeigth() * 0.5);
        this.onNotePositionChanged();
    }
    
    public void setStemYEnd(final int value) {
        this.stemYEnd = value;
    }
    
    public int getStemYEnd() {
        return this.stemYEnd;
    }
    
    public Rectangle2D getBoundingBox() {
        final Rectangle2D bb = new Rectangle2D.Double((int)this.m_base.getX(), this.stemYEnd, this.m_width, this.stemBeginPosition.getY() - this.stemYEnd + this.m_metrics.getNoteHeigth() / 2.0);
        return bb;
    }
    
    public Point2D getEndOfStemPosition() {
        if (this.stemYEnd != -1) {
            return new Point2D.Double(this.stemBeginPosition.getX(), this.stemYEnd);
        }
        throw new IllegalStateException();
    }
    
    public static double getOffset(final Note note) {
        double positionOffset = 0.0;
        final byte noteHeight = note.getStrictHeight();
        switch (noteHeight) {
            case 0: {
                positionOffset = -1.0;
                break;
            }
            case 2: {
                positionOffset = -0.5;
                break;
            }
            case 4: {
                positionOffset = 0.0;
                break;
            }
            case 5: {
                positionOffset = 0.5;
                break;
            }
            case 7: {
                positionOffset = 1.0;
                break;
            }
            case 9: {
                positionOffset = 1.5;
                break;
            }
            case 11: {
                positionOffset = 2.0;
                break;
            }
        }
        positionOffset += note.getOctaveTransposition() * 3.5;
        return positionOffset;
    }
    
    public double render(final Graphics2D context) {
        super.render(context);
        context.drawChars(this.noteChars, 0, 1, (int)this.displayPosition.getX(), (int)this.displayPosition.getY());
        final Stroke defaultS = context.getStroke();
        context.setStroke(this.m_metrics.getStemStroke());
        context.drawLine((int)this.stemBeginPosition.getX(), (int)this.stemBeginPosition.getY(), (int)this.stemBeginPosition.getX(), this.stemYEnd);
        context.setStroke(defaultS);
        return this.m_width;
    }
}
