// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.$Z7;

import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Polygon;

public class $ICB extends $QBB
{
    Polygon $IBB;
    boolean $JBB;
    Color $KBB;
    Color $LBB;
    Color $MBB;
    
    public void $JCB(final Polygon $ibb, final boolean $jbb) {
        this.$IBB = $ibb;
        this.$JBB = $jbb;
    }
    
    public Polygon $KCB() {
        return this.$IBB;
    }
    
    public boolean $LCB() {
        return this.$JBB;
    }
    
    protected Dimension $SBB(final Graphics graphics) {
        final Polygon $ibb = this.$IBB;
        if ($ibb == null) {
            return new Dimension(0, 0);
        }
        final Rectangle rectangle = new Rectangle($ibb.getBounds());
        if (!this.$JBB) {
            final Rectangle rectangle2 = rectangle;
            ++rectangle2.width;
            final Rectangle rectangle3 = rectangle;
            ++rectangle3.height;
        }
        return new Dimension(rectangle.x + rectangle.width, rectangle.y + rectangle.height);
    }
    
    protected void $TBB(final Graphics graphics, final int n, final int n2) {
        if (this.$SBB(graphics).height > 0) {
            graphics.translate(n, n2);
            if (this.isEnabled()) {
                if (super.$NCB == 2 && this.$MBB != null) {
                    graphics.setColor(this.$MBB);
                }
                else if (super.$NCB == 2 && super.$DCB != null) {
                    graphics.setColor(super.$DCB);
                }
                else if (super.$NCB != 0 && this.$LBB != null) {
                    graphics.setColor(this.$LBB);
                }
                else if (this.$KBB != null) {
                    graphics.setColor(this.$KBB);
                }
                else {
                    graphics.setColor(this.getForeground());
                }
                if (this.$JBB) {
                    graphics.fillPolygon(this.$IBB);
                }
                else {
                    graphics.drawPolygon(this.$IBB);
                }
            }
            else {
                graphics.translate(1, 1);
                graphics.setColor(this.getBackground().brighter());
                if (this.$JBB) {
                    graphics.fillPolygon(this.$IBB);
                }
                else {
                    graphics.drawPolygon(this.$IBB);
                }
                graphics.translate(-1, -1);
                graphics.setColor(this.getBackground().darker());
                if (this.$JBB) {
                    graphics.fillPolygon(this.$IBB);
                }
                else {
                    graphics.drawPolygon(this.$IBB);
                }
            }
            graphics.translate(-n, -n2);
        }
    }
    
    public $ICB(final Polygon polygon, final String s) {
        this(polygon, null, s);
    }
    
    public $ICB(final Polygon $ibb, final String s, final String s2) {
        super(s, s2);
        this.$IBB = null;
        this.$JBB = false;
        this.$KBB = null;
        this.$LBB = null;
        this.$MBB = null;
        this.$IBB = $ibb;
    }
    
    public $ICB(final String s, final String s2, final $HBB $hbb) {
        super(s, s2, $hbb);
        this.$IBB = null;
        this.$JBB = false;
        this.$KBB = null;
        this.$LBB = null;
        this.$MBB = null;
        this.$IBB = $hbb.$IBB;
        this.$JBB = $hbb.$JBB;
        this.$KBB = $hbb.$KBB;
        this.$LBB = $hbb.$LBB;
        this.$MBB = $hbb.$MBB;
    }
}
