// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.$XRB;

import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Component;

public class $HSB extends Component
{
    Rectangle $DSB;
    int $ESB;
    int $FSB;
    boolean $KSB;
    private int $MLB;
    private int $NLB;
    
    public Rectangle $GSB(final int n, final int n2) {
        final int $esb = this.$ESB;
        final int $fsb = this.$FSB;
        final Rectangle rectangle = new Rectangle(this.$DSB.x, this.$DSB.y, this.$DSB.width, this.$DSB.height);
        if (n < 1 || n2 < 1 || $esb < 1 || $fsb < 1 || rectangle.width < 1 || rectangle.height < 1) {
            return new Rectangle(0, 0, 0, 0);
        }
        final float n3 = n / $esb;
        final float n4 = n2 / $fsb;
        return new Rectangle((int)(rectangle.x * n3), (int)(rectangle.y * n4), (int)(rectangle.width * n3), (int)(rectangle.height * n4));
    }
    
    public void $RSB(final Rectangle $dsb, final int $esb, final int $fsb) {
        if (this.$DSB.equals($dsb) && this.$FSB == $fsb && this.$ESB == $esb) {
            return;
        }
        if ($dsb == null) {
            this.$DSB = new Rectangle(0, 0, 0, 0);
        }
        else {
            this.$DSB = $dsb;
        }
        this.$FSB = $fsb;
        this.$ESB = $esb;
        this.$KSB = ($dsb.width > 0 && $dsb.height > 0 && $esb > 0 && $fsb > 0);
        this.repaint();
    }
    
    public $HSB() {
        this.$DSB = new Rectangle(0, 0, 0, 0);
        this.$ESB = 0;
        this.$FSB = 0;
        this.$KSB = false;
        this.$MLB = 120;
        this.$NLB = 120;
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(this.$MLB, this.$NLB);
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.getSize();
        final Rectangle $gsb = this.$GSB(size.width, size.height);
        if (this.$KSB) {
            --$gsb.width;
            --$gsb.height;
            graphics.setColor(this.getBackground());
            graphics.draw3DRect($gsb.x, $gsb.y, $gsb.width, $gsb.height, true);
            $gsb.grow(-1, -1);
            graphics.drawRect($gsb.x, $gsb.y, $gsb.width, $gsb.height);
            $gsb.grow(-1, -1);
            graphics.draw3DRect($gsb.x, $gsb.y, $gsb.width, $gsb.height, false);
        }
    }
    
    public Dimension preferredSize() {
        return this.getPreferredSize();
    }
    
    public void setPreferredSize(final int $mlb, final int $nlb) {
        if (this.$MLB != $mlb || this.$NLB != $nlb) {
            this.$MLB = $mlb;
            this.$NLB = $nlb;
            this.invalidate();
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
