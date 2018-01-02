// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.$Z7;

import java.awt.Container;
import java.awt.AWTEventMulticaster;
import java.awt.event.ActionEvent;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.Component;

public abstract class $QBB extends Component implements $WAB
{
    String $RBB;
    String $MP;
    int $VBB;
    int $UBB;
    ActionListener actionListener;
    boolean $ADB;
    boolean $XBB;
    boolean $WBB;
    Color $ACB;
    Color $BCB;
    Color $CCB;
    Color $DCB;
    $NBB $ECB;
    Color $YBB;
    static final int HORIZONTAL = 1;
    static final int VERTICAL = 4;
    public static final int $EDB = 1;
    public static final int $HCB = 3;
    public static final int $FDB = 4;
    public static final int $GDB = 6;
    public static final int $HDB = 0;
    int $GCB;
    int $FCB;
    boolean state;
    public static final int $PCB = 0;
    public static final int $MDB = 1;
    public static final int $OCB = 2;
    int $NCB;
    
    public void $CDB(final boolean b) {
        this.$XBB = this.$XBB;
    }
    
    public Dimension $JDB(final Graphics graphics) {
        final String $mp = this.$MP;
        if ($mp == null || $mp.length() == 0 || graphics == null || this.$GCB == 0) {
            return new Dimension(0, 0);
        }
        final Font font = this.getFont();
        if (font != null) {
            graphics.setFont(font);
        }
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        return new Dimension(fontMetrics.stringWidth($mp) + 1, fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent() + 1);
    }
    
    public void $KDB(final int n) {
        if (this.actionListener != null) {
            this.actionListener.actionPerformed(new ActionEvent(this, 1001, this.$RBB, n));
        }
    }
    
    public void $LDB(final Graphics graphics, final int n, final int n2) {
        final String $mp = this.$MP;
        if ($mp == null || $mp.length() == 0 || graphics == null) {
            return;
        }
        final Font font = this.getFont();
        if (font != null) {
            graphics.setFont(font);
        }
        if (!this.isEnabled()) {
            graphics.setColor(this.getBackground().brighter());
            graphics.drawString($mp, n + 1, n2 + 1);
            graphics.setColor(this.getBackground().darker());
            graphics.drawString($mp, n, n2);
        }
        else {
            if (this.getState() && this.$DCB != null) {
                graphics.setColor(this.$DCB);
            }
            else if (this.$NCB != 0 && this.$BCB != null) {
                graphics.setColor(this.$BCB);
            }
            else {
                graphics.setColor(this.getForeground());
            }
            graphics.drawString($mp, n, n2);
        }
    }
    
    void $N0(final Graphics graphics) {
        final Dimension size = this.getSize();
        final int n = this.$VBB + this.$UBB;
        final Dimension dimension = size;
        dimension.width -= 2 * n;
        final Dimension dimension2 = size;
        dimension2.height -= 2 * n;
        final Dimension $sbb = this.$SBB(graphics);
        final Dimension $jdb = this.$JDB(graphics);
        if ($jdb.width > 0) {
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            if (fontMetrics != null) {
                int n2 = (size.width - $jdb.width) / 2;
                int maxAscent = (size.height - $jdb.height) / 2 + fontMetrics.getMaxAscent();
                if ($sbb.width >= 1) {
                    if (this.$GCB == 1) {
                        n2 = 0;
                    }
                    else if (this.$GCB == 3) {
                        n2 = $sbb.width + this.$FCB;
                    }
                    else if (this.$GCB == 4) {
                        maxAscent = fontMetrics.getMaxAscent();
                    }
                    else if (this.$GCB == 6) {
                        maxAscent = size.height - $jdb.height + fontMetrics.getMaxAscent();
                    }
                }
                this.$LDB(graphics, n2 + n, maxAscent + n);
            }
        }
        if ($sbb.width > 0 && $sbb.height > 0) {
            int n3 = (size.width - $sbb.width) / 2;
            int max = (size.height - $sbb.height) / 2;
            if ($jdb.width >= 1) {
                if (this.$GCB == 1) {
                    n3 = $jdb.width + this.$FCB;
                }
                else if (this.$GCB == 3) {
                    n3 = 0;
                }
                else if (this.$GCB == 4) {
                    max = $jdb.height + this.$FCB + Math.max(0, (size.height - $sbb.height - $jdb.height - this.$FCB) / 2);
                }
                else if (this.$GCB == 6) {
                    max = Math.max(0, (size.height - $sbb.height - this.$FCB - $jdb.height) / 2);
                }
            }
            this.$TBB(graphics, n3 + n, max + n);
        }
    }
    
    public int $NDB() {
        if (this.$ECB != null && this.state) {
            return 2;
        }
        if (this.$NCB == 0 && !this.$WBB) {
            return 1;
        }
        return this.$NCB;
    }
    
    void $ODB(final Graphics graphics) {
        final Dimension size = this.getSize();
        Color color;
        if (this.getState() && this.$CCB != null) {
            color = this.$CCB;
        }
        else if (this.$NCB != 0 && this.$ACB != null) {
            color = this.$ACB;
        }
        else {
            color = this.$YBB;
        }
        if (color != null) {
            graphics.setColor(color);
            graphics.fillRect(0, 0, size.width, size.height);
        }
    }
    
    public void $PDB(final Graphics graphics) {
        final Dimension size = this.getSize();
        final int $ndb = this.$NDB();
        final int $vbb = this.$VBB;
        if (size == null || size.width < 1 || size.height < 1 || ($ndb == 0 && this.$WBB) || $vbb < 1 || !this.isEnabled()) {
            return;
        }
        final boolean b = this.$NDB() != 2;
        final int min = Math.min($vbb, Math.min(size.width / 2, size.height / 2));
        final Dimension dimension = size;
        --dimension.width;
        final Dimension dimension2 = size;
        --dimension2.height;
        graphics.setColor(this.getBackground());
        for (int i = 0; i < min; ++i) {
            graphics.draw3DRect(i, i, size.width - i * 2, size.height - i * 2, b);
        }
    }
    
    protected abstract Dimension $SBB(final Graphics p0);
    
    protected abstract void $TBB(final Graphics p0, final int p1, final int p2);
    
    public $QBB(final String s, final String s2) {
        this(s, s2, new $YAB());
    }
    
    public $QBB(final String $mp, final String $rbb, final $YAB $yab) {
        this.actionListener = null;
        this.$ADB = false;
        this.$ECB = null;
        this.$YBB = null;
        this.$GCB = 6;
        this.$FCB = 2;
        this.state = false;
        this.$NCB = 0;
        this.$MP = $mp;
        this.$RBB = $rbb;
        this.setBackground($yab.$YBB);
        this.setForeground($yab.$ZBB);
        this.$ACB = $yab.$ACB;
        this.$CCB = $yab.$CCB;
        this.$BCB = $yab.$BCB;
        this.$DCB = $yab.$DCB;
        this.setFont($yab.$FF);
        this.$UBB = $yab.$UBB;
        this.$VBB = $yab.$VBB;
        this.$FCB = $yab.$FCB;
        this.$GCB = $yab.$GCB;
        this.$WBB = $yab.$WBB;
        this.$XBB = $yab.$XBB;
        this.$ECB = $yab.$ECB;
        new $BDB(this);
    }
    
    public void addActionListener(final ActionListener actionListener) {
        this.actionListener = AWTEventMulticaster.add(this.actionListener, actionListener);
    }
    
    public String getActionCommand() {
        return this.$RBB;
    }
    
    public String getLabel() {
        return this.$MP;
    }
    
    public Dimension getPreferredSize() {
        final Graphics graphics = this.getGraphics();
        try {
            final Dimension $sbb = this.$SBB(graphics);
            final Dimension $jdb = this.$JDB(graphics);
            if (this.$GCB != 0 && $jdb.width > 0 && $jdb.height > 0) {
                final int n = ($sbb.width > 0 && $sbb.height > 0) ? this.$FCB : 0;
                if ((this.$GCB & 0x4) == 0x4) {
                    $sbb.height = $sbb.height + n + $jdb.height;
                    $sbb.width = Math.max($jdb.width, $sbb.width);
                }
                else {
                    $sbb.width = $sbb.width + n + $jdb.width;
                    $sbb.height = Math.max($jdb.height, $sbb.height);
                }
            }
            final int n2 = (this.$VBB + this.$UBB) * 2;
            final Dimension dimension = $sbb;
            dimension.width += n2;
            final Dimension dimension2 = $sbb;
            dimension2.height += n2;
            return $sbb;
        }
        finally {
            graphics.dispose();
        }
    }
    
    public boolean getState() {
        return this.state || this.$NCB == 2;
    }
    
    public boolean isFocusTraversable() {
        return this.$XBB && (this.$ECB == null || !this.state);
    }
    
    public void paint(Graphics create) {
        create = create.create();
        try {
            this.$ODB(create);
            final int n = (this.$NDB() == 2) ? 1 : 0;
            create.translate(n, n);
            this.$N0(create);
            create.translate(-n, -n);
            this.$PDB(create);
        }
        finally {
            create.dispose();
        }
    }
    
    public void removeActionListener(final ActionListener actionListener) {
        this.actionListener = AWTEventMulticaster.remove(this.actionListener, actionListener);
    }
    
    public void setActionCommand(final String $rbb) {
        this.$RBB = $rbb;
    }
    
    public void setBackground(final Color $ybb) {
        super.setBackground(this.$YBB = $ybb);
    }
    
    public void setEnabled(final boolean enabled) {
        super.setEnabled(enabled);
        this.repaint();
    }
    
    public void setLabel(String s) {
        if (s == null) {
            s = "";
        }
        if (!s.equals(this.$MP)) {
            this.$MP = s;
            if (this.$RBB == null || this.$RBB.equals("")) {
                this.$RBB = s;
            }
            this.invalidate();
            Container container = this.getParent();
            if (container != null) {
                while (container.getParent() != null) {
                    container = container.getParent();
                }
                if (container != null) {
                    container.validate();
                    this.repaint();
                }
            }
        }
    }
    
    public void setState(final boolean state) {
        if (state != this.state) {
            this.state = state;
            if (this.$ECB != null) {
                if (state) {
                    this.$ECB.$OBB(this);
                    this.$NCB = 2;
                }
                else {
                    this.$NCB = 0;
                }
            }
            this.repaint();
        }
    }
}
