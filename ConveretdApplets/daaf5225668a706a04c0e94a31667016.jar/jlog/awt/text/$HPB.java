// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.text;

import java.awt.Rectangle;
import java.awt.FontMetrics;
import java.awt.Font;
import java.util.Dictionary;
import java.util.Vector;
import java.awt.Graphics;
import java.util.Hashtable;
import java.awt.Dimension;
import jlog.awt.$DPB.$IPB;

public class $HPB extends $IPB
{
    public static int LEFT;
    public static int RIGHT;
    public static int CENTER;
    private int $TO;
    private String[] $JPB;
    private String text;
    private int $MLB;
    private Dimension $KPB;
    private int $LPB;
    private int $MPB;
    private int $NPB;
    public Hashtable $RPB;
    
    private void $OPB() {
        this.$KPB = null;
        this.$LPB = -4711;
        this.repaint();
    }
    
    public void $VPB(final Graphics graphics, final String s, final Vector vector, final int n, final int n2) {
        graphics.drawString(s, n, n2);
    }
    
    public int $Y$() {
        return this.$TO;
    }
    
    static {
        $HPB.LEFT = 0;
        $HPB.RIGHT = 1;
        $HPB.CENTER = 2;
    }
    
    public $HPB(final String text, final int $mlb) {
        this.$TO = $HPB.LEFT;
        this.text = "";
        this.$KPB = null;
        this.$MPB = 6;
        this.$NPB = 4;
        this.$RPB = null;
        if ($mlb < 0) {
            throw new IllegalArgumentException("preferredWidth < 1 :" + $mlb);
        }
        this.$MLB = $mlb;
        this.setText(text);
    }
    
    public Dimension getMinimumSize() {
        return this.getPreferredSize();
    }
    
    public Dimension getPreferredSize() {
        final Font font = this.getFont();
        if (font == null) {
            return new Dimension(this.$MLB, this.$MLB);
        }
        final FontMetrics fontMetrics = this.getFontMetrics(font);
        if (fontMetrics == null) {
            return new Dimension(this.$MLB, this.$MLB);
        }
        if (this.$KPB == null) {
            this.$JPB = $PPB.$QPB(this.text, this.$MLB - 2 * this.$MPB, fontMetrics, new Hashtable());
            this.$KPB = new Dimension(this.$MLB, this.$JPB.length * fontMetrics.getHeight() + 2 * this.$NPB);
            this.$LPB = this.$MLB;
        }
        return this.$KPB;
    }
    
    public String getText() {
        return this.text;
    }
    
    public void paint(final Graphics graphics) {
        super.paint(graphics);
        final Font font;
        if (graphics == null || (font = graphics.getFont()) == null) {
            return;
        }
        final FontMetrics fontMetrics = this.getFontMetrics(font);
        final Dimension size = this.getSize();
        if (fontMetrics == null || size == null) {
            return;
        }
        if (this.$LPB != size.width || this.$JPB == null || this.$RPB == null) {
            if (this.$RPB != null) {
                this.$RPB.clear();
            }
            else {
                this.$RPB = new Hashtable();
            }
            this.$JPB = $PPB.$QPB(this.text, size.width - 2 * this.$MPB, fontMetrics, this.$RPB);
            this.$LPB = size.width;
        }
        final int ascent = fontMetrics.getAscent();
        final int height = fontMetrics.getHeight();
        final Rectangle clipBounds = graphics.getClipBounds();
        final int max = Math.max((clipBounds.y - ascent) / height, 0);
        final int min = Math.min((clipBounds.y + clipBounds.height - ascent) / height + 1, this.$JPB.length - 1);
        final int n = ascent + this.$NPB;
        for (int i = max; i <= min; ++i) {
            final String s = this.$JPB[i];
            Vector vector = this.$RPB.get(new Integer(i));
            if (vector == null) {
                vector = new Vector();
            }
            if (this.$TO == $HPB.LEFT) {
                this.$VPB(graphics, s, vector, this.$MPB, n + i * height);
            }
            else if (this.$TO == $HPB.RIGHT) {
                this.$VPB(graphics, s, vector, clipBounds.width - fontMetrics.stringWidth(this.$JPB[i]) - this.$MPB, n + i * height);
            }
            else {
                this.$VPB(graphics, s, vector, (clipBounds.width - fontMetrics.stringWidth(this.$JPB[i]) - this.$MPB) / 2, n + i * height);
            }
        }
    }
    
    public void setAlignment(final int $to) {
        if ($to != this.$TO) {
            this.$TO = $to;
            this.repaint();
        }
    }
    
    public void setPad(final int $mpb, final int $npb) {
        this.$MPB = $mpb;
        this.$NPB = $npb;
        this.$OPB();
    }
    
    public void setText(String text) {
        if (text == null) {
            text = "";
        }
        if (!text.equals(this.text)) {
            this.text = text;
            this.$OPB();
        }
    }
}
