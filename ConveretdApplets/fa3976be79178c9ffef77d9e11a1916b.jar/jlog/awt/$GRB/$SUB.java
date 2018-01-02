// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.$GRB;

import java.awt.event.AdjustmentEvent;
import java.awt.Graphics;
import java.awt.AWTEventMulticaster;
import java.awt.Dimension;
import java.awt.event.MouseMotionAdapter;
import java.awt.Toolkit;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.Point;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseMotionListener;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Adjustable;
import java.awt.Component;

public class $SUB extends Component implements Adjustable, Runnable
{
    public static Color $VVB;
    public static int $WVB;
    public static int $XVB;
    int $YVB;
    int $ZVB;
    Color $SVB;
    int $RVB;
    int $MVB;
    int $NVB;
    int $OVB;
    int $PVB;
    int orientation;
    int value;
    private Rectangle $AWB;
    private Adjustable source;
    private String text;
    private int $GZ;
    private int $HZ;
    private int stringWidth;
    MouseMotionListener $LNB;
    AdjustmentListener $CWB;
    Thread $OR;
    Object $I6;
    private Point $IWB;
    private Point $KU;
    private boolean $MU;
    
    private MouseListener $A1() {
        return new MouseAdapter() {
            public void mousePressed(final MouseEvent mouseEvent) {
                final int max = Math.max($SUB.this.$OVB, $SUB.this.$RVB);
                final int x = mouseEvent.getX();
                final int y = mouseEvent.getY();
                if ($SUB.this.$AWB.contains(x, y)) {
                    $SUB.this.$KU.x = x;
                    $SUB.this.$KU.y = y;
                    $SUB.this.$IWB.x = $SUB.this.$AWB.x;
                    $SUB.this.$IWB.y = $SUB.this.$AWB.y;
                    $SUB.this.addMouseMotionListener($SUB.this.$LNB);
                    $SUB.access$4($SUB.this, true);
                }
                else if ($SUB.this.orientation == 0) {
                    if (x < $SUB.this.$AWB.x) {
                        $SUB.this.$UVB($SUB.this.value - max);
                    }
                    else {
                        $SUB.this.$UVB($SUB.this.value + max);
                    }
                }
                else if (y < $SUB.this.$AWB.y) {
                    $SUB.this.$UVB($SUB.this.value - max);
                }
                else {
                    $SUB.this.$UVB($SUB.this.value + max);
                }
            }
            
            public void mouseReleased(final MouseEvent mouseEvent) {
                $SUB.access$4($SUB.this, false);
                $SUB.this.removeMouseMotionListener($SUB.this.$LNB);
            }
        };
    }
    
    private void $FWB() {
        if (this.text == null || this.text.equals("")) {
            return;
        }
        final Font font = this.getFont();
        if (font == null) {
            return;
        }
        final FontMetrics fontMetrics = this.getFontMetrics(font);
        if (fontMetrics == null) {
            return;
        }
        this.stringWidth = fontMetrics.stringWidth(this.text);
        this.$GZ = fontMetrics.getMaxAscent();
        this.$HZ = fontMetrics.getMaxDescent();
        final int max = Math.max(this.$ZVB, this.$GZ + this.$HZ + 4);
        final int max2 = Math.max(this.$YVB, this.stringWidth + 6);
        if (this.$ZVB != max || this.$YVB != max2) {
            this.$YVB = max2;
            this.$ZVB = max;
            this.invalidate();
            this.repaint();
        }
    }
    
    public void $NRB(final Color $svb) {
        this.$SVB = $svb;
        this.repaint();
    }
    
    public void $TVB(final int n, final int n2) {
        try {
            final int screenResolution = Toolkit.getDefaultToolkit().getScreenResolution();
            this.$YVB = (int)(n * screenResolution / 96.0f);
            this.$ZVB = (int)(n2 * screenResolution / 96.0f);
        }
        catch (Exception ex) {
            System.err.print("\n" + ex.getMessage());
        }
    }
    
    synchronized void $UVB(final int n) {
        if (this.setValues(n, this.$RVB, this.$MVB, this.$NVB) && this.$CWB != null) {
            synchronized (this.$I6) {
                if (this.$OR == null || !this.$OR.isAlive()) {
                    (this.$OR = new Thread(this)).start();
                }
            }
            // monitorexit(this.$I6)
        }
    }
    
    private MouseMotionListener $Z_() {
        return new MouseMotionAdapter() {
            public void mouseDragged(final MouseEvent mouseEvent) {
                if (!$SUB.this.$MU) {
                    return;
                }
                final Dimension size = $SUB.this.getSize();
                final int x = mouseEvent.getX();
                final int y = mouseEvent.getY();
                if ($SUB.this.orientation == 0) {
                    final int n = x - $SUB.this.$KU.x;
                    final float n2 = size.width - $SUB.this.$AWB.width - 2;
                    if (n2 > 0.0f) {
                        $SUB.this.$UVB($SUB.this.$MVB + (int)(($SUB.this.$NVB - $SUB.this.$MVB - $SUB.this.$RVB) * (($SUB.this.$IWB.x + n) / n2)));
                    }
                }
                else {
                    final int n3 = y - $SUB.this.$KU.y;
                    final float n4 = size.height - $SUB.this.$AWB.height - 2;
                    if (n4 > 0.0f) {
                        $SUB.this.$UVB($SUB.this.$MVB + (int)(($SUB.this.$NVB - $SUB.this.$MVB - $SUB.this.$RVB) * (($SUB.this.$IWB.y + n3) / n4)));
                    }
                }
            }
        };
    }
    
    static {
        $SUB.$VVB = Color.orange;
        $SUB.$WVB = 24;
        $SUB.$XVB = 18;
    }
    
    public $SUB(final int orientation) {
        this.$YVB = $SUB.$WVB;
        this.$ZVB = $SUB.$XVB;
        this.$SVB = $SUB.$VVB;
        this.$RVB = 0;
        this.$MVB = 0;
        this.$NVB = 0;
        this.$OVB = 0;
        this.$PVB = 0;
        this.orientation = 0;
        this.value = 0;
        this.$AWB = new Rectangle();
        this.source = null;
        this.text = null;
        this.$GZ = 0;
        this.$HZ = 0;
        this.stringWidth = 0;
        this.$CWB = null;
        this.$OR = null;
        this.$I6 = new Object();
        this.$IWB = new Point(0, 0);
        this.$KU = new Point(0, 0);
        this.$MU = false;
        this.source = this;
        this.orientation = orientation;
        if (orientation == 0) {
            this.$TVB($SUB.$WVB, $SUB.$XVB);
        }
        else {
            this.$TVB($SUB.$XVB, $SUB.$WVB);
        }
        this.$LNB = this.$Z_();
        this.addMouseListener(this.$A1());
    }
    
    static /* synthetic */ void access$4(final $SUB $sub, final boolean $mu) {
        $sub.$MU = $mu;
    }
    
    public void addAdjustmentListener(final AdjustmentListener adjustmentListener) {
        this.$CWB = AWTEventMulticaster.add(this.$CWB, adjustmentListener);
    }
    
    public int getBlockIncrement() {
        return this.$OVB;
    }
    
    public int getMaximum() {
        return this.$NVB;
    }
    
    public int getMinimum() {
        return this.$MVB;
    }
    
    public Dimension getMinimumSize() {
        if (this.orientation == 0) {
            return new Dimension(this.$YVB * 2, this.$ZVB);
        }
        return new Dimension(this.$YVB, this.$ZVB * 2);
    }
    
    public int getOrientation() {
        return this.orientation;
    }
    
    public Dimension getPreferredSize() {
        if (this.orientation == 0) {
            return new Dimension(160, this.$ZVB);
        }
        return new Dimension(this.$YVB, 160);
    }
    
    public String getText() {
        return this.text;
    }
    
    public int getUnitIncrement() {
        return this.$PVB;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public int getVisibleAmount() {
        return this.$RVB;
    }
    
    public void paint(final Graphics graphics) {
        final int $mvb;
        final int $nvb;
        final int $rvb;
        final int value;
        synchronized (this) {
            $mvb = this.$MVB;
            $nvb = this.$NVB;
            $rvb = this.$RVB;
            value = this.value;
        }
        final int n = $nvb - $mvb + 1;
        final int max = Math.max(0, $nvb - $mvb - $rvb);
        final int n2 = value - $mvb;
        if (max < 1) {
            return;
        }
        this.$FWB();
        final Dimension size = this.getSize();
        if (size == null || size.width < 1 || size.height < 1) {
            return;
        }
        Color color = this.getBackground();
        if (color == null) {
            color = Color.lightGray;
        }
        graphics.setColor(color);
        graphics.fill3DRect(0, 0, size.width, size.height, false);
        final Dimension dimension = size;
        dimension.width -= 2;
        final Dimension dimension2 = size;
        dimension2.height -= 2;
        final Color $svb = this.$SVB;
        if ($svb != null) {
            color = $svb;
            graphics.setColor(color);
        }
        if (this.orientation == 0) {
            final int max2 = Math.max($rvb / n * size.width, this.$YVB);
            this.$AWB.setBounds(1 + n2 / max * (size.width - max2), 1, max2, size.height);
        }
        else {
            final int max3 = Math.max($rvb / n * size.height, this.$ZVB);
            this.$AWB.setBounds(1, 1 + n2 / max * (size.height - max3), size.width, max3);
        }
        graphics.fill3DRect(this.$AWB.x, this.$AWB.y, this.$AWB.width, this.$AWB.height, true);
        if (this.text == null || this.text.equals("")) {
            if (this.orientation == 0) {
                final int n3 = this.$AWB.width / 2 - 3;
                for (int i = 0; i < 6; i += 2) {
                    graphics.fill3DRect(this.$AWB.x + n3 + i, this.$AWB.y + 2, 2, this.$AWB.height - 4, true);
                }
            }
            else {
                final int n4 = this.$AWB.height / 2 - 3;
                for (int j = 0; j < 6; j += 2) {
                    graphics.fill3DRect(this.$AWB.x + 1, this.$AWB.y + n4 + j, this.$AWB.width - 2, 2, true);
                }
            }
        }
        else {
            graphics.setFont(this.getFont());
            final int n5 = this.$AWB.x + (this.$AWB.width - this.stringWidth) / 2;
            final int n6 = this.$AWB.y + (this.$AWB.height - this.$GZ - this.$HZ) / 2 + this.$GZ;
            graphics.setColor(color.brighter());
            graphics.drawString(this.text, n5, n6);
            graphics.setColor(color.darker());
            graphics.drawString(this.text, n5 - 1, n6 - 1);
        }
    }
    
    public void removeAdjustmentListener(final AdjustmentListener adjustmentListener) {
        this.$CWB = AWTEventMulticaster.remove(this.$CWB, adjustmentListener);
    }
    
    public void run() {
        int value = -1;
        while (this.$OR == Thread.currentThread()) {
            synchronized (this.$I6) {
                if (this.value == value) {
                    this.$OR = null;
                    // monitorexit(this.$I6)
                    return;
                }
            }
            // monitorexit(this.$I6)
            value = this.value;
            if (this.$CWB != null) {
                this.$CWB.adjustmentValueChanged(new AdjustmentEvent(this.source, 601, 5, value));
            }
        }
    }
    
    public void setBlockIncrement(final int $ovb) {
        this.$OVB = $ovb;
    }
    
    public void setMaximum(final int $nvb) {
        this.$NVB = $nvb;
    }
    
    public void setMinimum(final int $mvb) {
        this.$MVB = $mvb;
    }
    
    void setSource(final Adjustable source) {
        this.source = source;
    }
    
    public void setText(final String text) {
        if (text == this.text || (text != null && text.equals(this.text))) {
            return;
        }
        this.text = text;
        this.repaint();
    }
    
    public void setUnitIncrement(final int $pvb) {
        this.$PVB = $pvb;
    }
    
    public void setValue(final int n) {
        this.setValues(n, this.$RVB, this.$MVB, this.$NVB);
    }
    
    public synchronized boolean setValues(int max, final int $rvb, int $mvb, int $nvb) {
        if ($mvb > $nvb) {
            final int n = $mvb;
            $mvb = $nvb;
            $nvb = n;
        }
        if (max > $nvb - $rvb) {
            max = Math.max($mvb, $nvb - $rvb);
        }
        else if (max < $mvb) {
            max = $mvb;
        }
        if (max != this.value || $rvb != this.$RVB || $mvb != this.$MVB || $nvb != this.$NVB) {
            this.$MVB = $mvb;
            this.$NVB = $nvb;
            this.$RVB = $rvb;
            this.value = max;
            this.repaint();
            return true;
        }
        return false;
    }
    
    public void setVisibleAmount(final int $rvb) {
        this.$RVB = $rvb;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
