// 
// Decompiled by Procyon v0.5.30
// 

package abc.ui.swing;

import java.awt.Graphics2D;
import abc.notation.Note;
import abc.notation.MusicElement;
import java.awt.geom.Point2D;
import abc.notation.KeySignature;

class JKeySignature extends JScoreElementAbstract
{
    KeySignature key;
    Point2D FPosition;
    char[] Fchar;
    Point2D CPosition;
    char[] Cchar;
    Point2D GPosition;
    char[] Gchar;
    Point2D DPosition;
    char[] Dchar;
    Point2D APosition;
    char[] Achar;
    Point2D EPosition;
    char[] Echar;
    Point2D BPosition;
    char[] Bchar;
    
    public JKeySignature(final KeySignature keyV, final Point2D base, final ScoreMetrics c) {
        super(c);
        this.key = null;
        this.FPosition = null;
        this.Fchar = null;
        this.CPosition = null;
        this.Cchar = null;
        this.GPosition = null;
        this.Gchar = null;
        this.DPosition = null;
        this.Dchar = null;
        this.APosition = null;
        this.Achar = null;
        this.EPosition = null;
        this.Echar = null;
        this.BPosition = null;
        this.Bchar = null;
        this.key = keyV;
        this.setBase(base);
    }
    
    public MusicElement getMusicElement() {
        return this.key;
    }
    
    protected void onBaseChanged() {
        final ScoreMetrics c = this.m_metrics;
        if (this.key.hasOnlySharps()) {
            final double FPositionX = this.m_base.getX();
            final double FPositionY = this.m_base.getY() - JNotePartOfGroup.getOffset(new Note((byte)17, (byte)10)) * c.getNoteHeigth();
            this.FPosition = new Point2D.Double(FPositionX, FPositionY);
            final double CPositionX = this.m_base.getX() + c.getSharpBounds().getWidth();
            final double CPositionY = this.m_base.getY() - JNotePartOfGroup.getOffset(new Note((byte)12, (byte)10)) * c.getNoteHeigth();
            this.CPosition = new Point2D.Double(CPositionX, CPositionY);
            final double GPositionX = this.m_base.getX() + 2.0 * c.getSharpBounds().getWidth();
            final double GPositionY = this.m_base.getY() - JNotePartOfGroup.getOffset(new Note((byte)19, (byte)10)) * c.getNoteHeigth();
            this.GPosition = new Point2D.Double(GPositionX, GPositionY);
            final double DPositionX = this.m_base.getX() + 3.0 * c.getSharpBounds().getWidth();
            final double DPositionY = this.m_base.getY() - JNotePartOfGroup.getOffset(new Note((byte)14, (byte)10)) * c.getNoteHeigth();
            this.DPosition = new Point2D.Double(DPositionX, DPositionY);
            final double APositionX = this.m_base.getX() + 4.0 * c.getSharpBounds().getWidth();
            final double APositionY = this.m_base.getY() - JNotePartOfGroup.getOffset(new Note((byte)9, (byte)10)) * c.getNoteHeigth();
            this.APosition = new Point2D.Double(APositionX, APositionY);
            final double EPositionX = this.m_base.getX() + 5.0 * c.getSharpBounds().getWidth();
            final double EPositionY = this.m_base.getY() - JNotePartOfGroup.getOffset(new Note((byte)16, (byte)10)) * c.getNoteHeigth();
            this.EPosition = new Point2D.Double(EPositionX, EPositionY);
            final double BPositionX = this.m_base.getX() + 6.0 * c.getSharpBounds().getWidth();
            final double BPositionY = this.m_base.getY() - JNotePartOfGroup.getOffset(new Note((byte)11, (byte)10)) * c.getNoteHeigth();
            this.BPosition = new Point2D.Double(BPositionX, BPositionY);
        }
        else if (this.key.hasOnlyFlats()) {
            final double BPositionX2 = this.m_base.getX();
            final double BPositionY2 = this.m_base.getY() - JNotePartOfGroup.getOffset(new Note((byte)11, (byte)10)) * c.getNoteHeigth();
            this.BPosition = new Point2D.Double(BPositionX2, BPositionY2);
            final double EPositionX2 = this.m_base.getX() + c.getSharpBounds().getWidth();
            final double EPositionY2 = this.m_base.getY() - JNotePartOfGroup.getOffset(new Note((byte)16, (byte)10)) * c.getNoteHeigth();
            this.EPosition = new Point2D.Double(EPositionX2, EPositionY2);
            final double APositionX2 = this.m_base.getX() + 2.0 * c.getSharpBounds().getWidth();
            final double APositionY2 = this.m_base.getY() - JNotePartOfGroup.getOffset(new Note((byte)9, (byte)10)) * c.getNoteHeigth();
            this.APosition = new Point2D.Double(APositionX2, APositionY2);
            final double DPositionX = this.m_base.getX() + 3.0 * c.getSharpBounds().getWidth();
            final double DPositionY = this.m_base.getY() - JNotePartOfGroup.getOffset(new Note((byte)14, (byte)10)) * c.getNoteHeigth();
            this.DPosition = new Point2D.Double(DPositionX, DPositionY);
            final double GPositionX2 = this.m_base.getX() + 4.0 * c.getSharpBounds().getWidth();
            final double GPositionY2 = this.m_base.getY() - JNotePartOfGroup.getOffset(new Note((byte)7, (byte)10)) * c.getNoteHeigth();
            this.GPosition = new Point2D.Double(GPositionX2, GPositionY2);
            final double CPositionX2 = this.m_base.getX() + 5.0 * c.getSharpBounds().getWidth();
            final double CPositionY2 = this.m_base.getY() - JNotePartOfGroup.getOffset(new Note((byte)12, (byte)10)) * c.getNoteHeigth();
            this.CPosition = new Point2D.Double(CPositionX2, CPositionY2);
            final double FPositionX2 = this.m_base.getX() + 6.0 * c.getSharpBounds().getWidth();
            final double FPositionY2 = this.m_base.getY() - JNotePartOfGroup.getOffset(new Note((byte)5, (byte)10)) * c.getNoteHeigth();
            this.FPosition = new Point2D.Double(FPositionX2, FPositionY2);
        }
        final byte[] accidentals = this.key.getAccidentals();
        for (int i = 0; i < accidentals.length; ++i) {
            char[] chars = null;
            if (accidentals[i] == 1) {
                chars = ScoreMetrics.SHARP;
                this.m_width += c.getSharpBounds().getWidth();
            }
            else if (accidentals[i] == -1) {
                chars = ScoreMetrics.FLAT;
                this.m_width += c.getFlatBounds().getWidth();
            }
            switch (i) {
                case 0: {
                    this.Cchar = chars;
                    break;
                }
                case 1: {
                    this.Dchar = chars;
                    break;
                }
                case 2: {
                    this.Echar = chars;
                    break;
                }
                case 3: {
                    this.Fchar = chars;
                    break;
                }
                case 4: {
                    this.Gchar = chars;
                    break;
                }
                case 5: {
                    this.Achar = chars;
                    break;
                }
                case 6: {
                    this.Bchar = chars;
                    break;
                }
            }
        }
    }
    
    public double render(final Graphics2D context) {
        super.render(context);
        if (this.Fchar != null) {
            context.drawChars(this.Fchar, 0, 1, (int)this.FPosition.getX(), (int)this.FPosition.getY());
        }
        if (this.Cchar != null) {
            context.drawChars(this.Cchar, 0, 1, (int)this.CPosition.getX(), (int)this.CPosition.getY());
        }
        if (this.Gchar != null) {
            context.drawChars(this.Gchar, 0, 1, (int)this.GPosition.getX(), (int)this.GPosition.getY());
        }
        if (this.Dchar != null) {
            context.drawChars(this.Dchar, 0, 1, (int)this.DPosition.getX(), (int)this.DPosition.getY());
        }
        if (this.Achar != null) {
            context.drawChars(this.Achar, 0, 1, (int)this.APosition.getX(), (int)this.APosition.getY());
        }
        if (this.Echar != null) {
            context.drawChars(this.Echar, 0, 1, (int)this.EPosition.getX(), (int)this.EPosition.getY());
        }
        if (this.Bchar != null) {
            context.drawChars(this.Bchar, 0, 1, (int)this.BPosition.getX(), (int)this.BPosition.getY());
        }
        return this.m_width;
    }
}
