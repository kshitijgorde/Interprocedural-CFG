// 
// Decompiled by Procyon v0.5.30
// 

package jlog.$T5.$D7.$XDC;

import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import jlog.awt.$V_;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Polygon;
import java.awt.Component;

public class $SRC extends Component
{
    public String name;
    public boolean $NSC;
    public boolean $OSC;
    public boolean $CEC;
    public boolean $MLD;
    private Polygon p;
    private Dimension $NLD;
    private Polygon p2;
    private float $OLD;
    private float $PLD;
    public boolean $QLD;
    public boolean $BSC;
    public static Color $F_C;
    public Color $JSC;
    
    public void $DSC(final boolean $qld) {
        if ($qld != this.$QLD) {
            this.$QLD = $qld;
            this.repaint();
        }
    }
    
    public void $JCB(final Polygon p) {
        if (p == null) {
            this.p = p;
            return;
        }
        this.p = new $V_(p);
        final Rectangle bounds = this.p.getBounds();
        this.p.translate(-bounds.x, -bounds.y);
        this.$NLD = new Dimension(bounds.width, bounds.height);
        this.p2 = null;
        this.$OLD = -1.0f;
        this.$PLD = -1.0f;
    }
    
    public void $JCB(final int[] array) {
        final int n = array.length / 2;
        final int[] array2 = new int[n];
        final int[] array3 = new int[n];
        int n2 = n;
        int n3 = 2 * n;
        while (n2-- != 0) {
            n3 -= 2;
            array2[n2] = array[n3];
            array3[n2] = array[n3 + 1];
        }
        this.$JCB(new Polygon(array2, array3, n));
    }
    
    public Polygon $KCB() {
        final Dimension size = this.getSize();
        return (this.p == null) ? null : new $V_(this.$KCB(size.width, size.height));
    }
    
    public Polygon $KCB(final int n, final int n2) {
        if (this.p == null) {
            return null;
        }
        final float $old = n / this.$NLD.width;
        final float $old2 = n2 / this.$NLD.height;
        if ($old == 1.0f && $old2 == 1.0f) {
            return this.p;
        }
        if ($old == this.$OLD && $old2 == this.$PLD) {
            return this.p2;
        }
        int npoints = this.p.npoints;
        final int[] xpoints = this.p.xpoints;
        final int[] ypoints = this.p.ypoints;
        final int[] array = (this.p2 == null) ? new int[npoints] : this.p2.xpoints;
        final int[] array2 = (this.p2 == null) ? new int[npoints] : this.p2.ypoints;
        while (npoints-- > 0) {
            array[npoints] = (int)($old * xpoints[npoints]);
            array2[npoints] = (int)($old2 * ypoints[npoints]);
        }
        this.p2 = new Polygon(array, array2, this.p.npoints);
        this.$OLD = $old;
        this.$OLD = $old2;
        return this.p2;
    }
    
    void $N0(final Graphics graphics) {
        final Dimension size = this.getSize();
        if (this.$BSC) {
            if (this.$JSC == null) {
                this.$JSC = $SRC.$F_C;
            }
            graphics.setXORMode(this.$JSC);
        }
        final Color background = this.getBackground();
        final Color foreground = this.getForeground();
        graphics.setColor(background);
        if (this.$CEC) {
            $VLD(graphics, foreground, background, size, this.$NSC, this.$OSC);
        }
        else if (this.p != null) {
            this.$WLD(graphics, foreground, background, size, this.$NSC, this.$OSC);
        }
        else if (this.$MLD) {
            $XLD(graphics, foreground, background, size, this.$NSC, this.$OSC);
        }
        if (this.name != null && this.name.length() != 0) {
            final Font font = this.getFont();
            if (font != null) {
                graphics.setFont(font);
            }
            final Rectangle $uld = $ULD(graphics, size, this.name);
            graphics.setColor(foreground);
            graphics.drawString(this.name, $uld.x, $uld.y);
        }
    }
    
    public static Rectangle $ULD(final Graphics graphics, final Dimension dimension, final String s) {
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        if (fontMetrics == null || dimension == null || s == null) {
            return new Rectangle(-1, -1, 0, 0);
        }
        final int maxAscent = fontMetrics.getMaxAscent();
        final int n = maxAscent + fontMetrics.getMaxDescent();
        final int stringWidth = fontMetrics.stringWidth(s);
        return new Rectangle((dimension.width - stringWidth) / 2, (dimension.height - n) / 2 + maxAscent, stringWidth, n);
    }
    
    public static final boolean $V8(final String s) {
        return s != null && !s.equals("");
    }
    
    static void $VLD(final Graphics graphics, final Color color, final Color color2, final Dimension dimension, final boolean b, final boolean b2) {
        if (b) {
            graphics.setColor(color2);
            if (b2) {
                graphics.fillOval(0, 0, dimension.width - 1, dimension.height - 1);
            }
            else {
                graphics.fillOval(0, 0, dimension.width, dimension.height);
            }
        }
        if (b2) {
            graphics.setColor(color);
            graphics.drawOval(0, 0, dimension.width - 1, dimension.height - 1);
        }
    }
    
    void $WLD(final Graphics graphics, final Color color, final Color color2, final Dimension dimension, final boolean b, final boolean b2) {
        Polygon polygon = null;
        if (b) {
            if (b2) {
                polygon = this.$KCB(dimension.width - 1, dimension.height - 1);
            }
            else {
                polygon = this.$KCB(dimension.width, dimension.height);
            }
            graphics.setColor(color2);
            graphics.fillPolygon(polygon);
        }
        if (this.$OSC) {
            if (polygon == null) {
                polygon = this.$KCB(dimension.width - 1, dimension.height - 1);
            }
            graphics.setColor(color);
            graphics.drawPolygon(polygon);
        }
    }
    
    static void $XLD(final Graphics graphics, final Color color, final Color color2, final Dimension dimension, final boolean b, final boolean b2) {
        if (b) {
            graphics.setColor(color2);
            if (b2) {
                graphics.fillRect(0, 0, dimension.width - 1, dimension.height - 1);
            }
            else {
                graphics.fillRect(0, 0, dimension.width, dimension.height);
            }
        }
        if (b2) {
            graphics.setColor(color);
            graphics.drawRect(0, 0, dimension.width - 1, dimension.height - 1);
        }
    }
    
    static {
        $SRC.$F_C = new Color(16704250);
    }
    
    public $SRC(final String name) {
        this.name = null;
        this.$NSC = true;
        this.$OSC = true;
        this.$CEC = false;
        this.$MLD = false;
        this.p = null;
        this.$NLD = null;
        this.p2 = null;
        this.$OLD = -1.0f;
        this.$PLD = -1.0f;
        this.$QLD = false;
        this.$BSC = false;
        this.$JSC = null;
        this.name = name;
        this.setLocation(0, 0);
        this.$JSC = $SRC.$F_C;
    }
    
    public $SRC(final String s, final boolean $cec) {
        this(s);
        this.$CEC = $cec;
        this.$MLD = ($cec ^ true);
    }
    
    public boolean contains(int n, int n2) {
        final Dimension size = this.getSize();
        if (n > size.width || n2 > size.height || n < 0 || n2 < 0) {
            return false;
        }
        if (this.p != null) {
            return this.$KCB(size.width, size.height).contains(n, n2);
        }
        if (!this.$CEC) {
            return true;
        }
        final Dimension size2 = this.getSize();
        final int n3 = size2.width / 2;
        final int n4 = size2.height / 2;
        n -= n3;
        n2 -= n4;
        return Math.abs(n) <= Math.sqrt((1.0f - n2 * n2 / (n4 * n4)) * n3 * n3);
    }
    
    public Component getComponentAt(final int n, final int n2) {
        if (this.contains(n, n2)) {
            return this;
        }
        return null;
    }
    
    public Dimension getMinimumSize() {
        return this.getPreferredSize();
    }
    
    public Dimension getPreferredSize() {
        final Graphics graphics = this.getGraphics();
        try {
            return this.getPreferredSize(graphics);
        }
        finally {
            if (graphics != null) {
                graphics.dispose();
            }
        }
    }
    
    public Dimension getPreferredSize(final Graphics graphics) {
        Dimension dimension;
        Dimension $nld = dimension = new Dimension(21, 21);
        if (this.p != null) {
            $nld = this.$NLD;
        }
        if (this.name != null && graphics != null) {
            final Font font = graphics.getFont();
            final Font font2 = this.getFont();
            if (font2 != null) {
                graphics.setFont(font2);
            }
            final Rectangle $uld = $ULD(graphics, $nld, this.name);
            graphics.setFont(font);
            dimension = new Dimension($uld.width + 6, $uld.height + 4);
            if (this.$CEC) {
                dimension.width = Math.max($nld.width, dimension.width);
                dimension.height = Math.max($nld.height, dimension.height);
                dimension.width = Math.max(dimension.height, dimension.width);
                dimension.height = dimension.width;
                return dimension;
            }
        }
        return new Dimension(Math.max($nld.width, dimension.width), Math.max($nld.height, dimension.height));
    }
    
    public void paint(Graphics create) {
        if (this.$QLD) {
            return;
        }
        create = create.create();
        try {
            this.$N0(create);
        }
        finally {
            create.dispose();
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
