// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.$B8;

import java.awt.Container;
import java.awt.image.ImageObserver;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Image;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Canvas;

public class $GJB extends Canvas
{
    public static final Dimension $TJB;
    protected static Dimension $UJB;
    protected static int $VJB;
    protected Color $WJB;
    protected String $MP;
    protected String $NJB;
    protected Dimension size;
    protected boolean $XJB;
    Image image;
    int $SAB;
    int $TAB;
    int $YJB;
    Component $OU;
    int $ZJB;
    $BKB $AKB;
    
    Dimension $CKB(final Graphics graphics) {
        final FontMetrics fontMetrics;
        if (this.$MP != null && graphics != null && (fontMetrics = graphics.getFontMetrics()) != null) {
            Dimension preferredSize;
            if (this.$OU != null) {
                preferredSize = this.$OU.getPreferredSize();
            }
            else {
                preferredSize = new Dimension(0, 0);
            }
            return new Dimension(fontMetrics.stringWidth(this.$MP) + ($GJB.$UJB.width << 1) + this.$SAB + this.$YJB + preferredSize.width + this.$ZJB, Math.max(Math.max(fontMetrics.getAscent() + fontMetrics.getDescent() + 2 * $GJB.$UJB.height + $GJB.$VJB, this.$TAB + 2 + 2 * $GJB.$UJB.height + $GJB.$VJB), preferredSize.height + 2 * $GJB.$UJB.height + $GJB.$VJB));
        }
        return new Dimension(-1, -1);
    }
    
    Color $FKB(final Color color) {
        final int red = color.getRed();
        final int green = color.getGreen();
        final int blue = color.getBlue();
        final int n = 14;
        int n2;
        int n3;
        int n4;
        if (red < n && green < n && blue < n) {
            n2 = Math.min(255, red + n);
            n3 = Math.min(255, green + n);
            n4 = Math.min(255, blue + n);
        }
        else {
            n2 = Math.max(0, red - n);
            n3 = Math.max(0, green - n);
            n4 = Math.max(0, blue - n);
        }
        return new Color(n2, n3, n4);
    }
    
    public void $JKB(final Color $wjb) {
        this.$WJB = $wjb;
    }
    
    public String $QJB() {
        return this.$NJB;
    }
    
    static {
        $TJB = new Dimension(16, 16);
        $GJB.$UJB = new Dimension(6, 4);
        $GJB.$VJB = 2;
    }
    
    public $GJB(final String $njb, final String $mp) {
        this.$WJB = null;
        this.$MP = null;
        this.$NJB = null;
        this.size = null;
        this.image = null;
        this.$SAB = 0;
        this.$TAB = 0;
        this.$YJB = 0;
        this.$OU = null;
        this.$ZJB = 0;
        this.$MP = $mp;
        this.$NJB = $njb;
        this.addMouseListener(this.$AKB = new $BKB(this));
    }
    
    public $GJB(final String s, final String s2, final Color $wjb) {
        this(s, s2);
        this.$WJB = $wjb;
    }
    
    public void addActionListener(final ActionListener actionListener) {
        this.$AKB.addActionListener(actionListener);
    }
    
    public String getLabel() {
        return this.$MP;
    }
    
    public Dimension getMinimumSize() {
        final Graphics graphics = this.getGraphics();
        try {
            return this.$CKB(graphics);
        }
        finally {
            graphics.dispose();
        }
    }
    
    public Dimension getPreferredSize() {
        final Graphics graphics = this.getGraphics();
        try {
            return this.$CKB(graphics);
        }
        finally {
            graphics.dispose();
        }
    }
    
    public boolean getState() {
        return this.$XJB;
    }
    
    public boolean isFocusTraversable() {
        return false;
    }
    
    public void paint(final Graphics graphics) {
        if (this.size == null) {
            this.setSize(this.size = this.$CKB(graphics));
            final Container parent = this.getParent();
            if (parent != null && parent.getParent() != null) {
                parent.getParent().validate();
            }
        }
        final Dimension size = this.getSize();
        final int n = this.$XJB ? 0 : $GJB.$VJB;
        final int[] array = { 0, 0, 2, size.width - 3, size.width - 1, size.width - 1 };
        final int[] array2 = { size.height - 1, n + 2, n, n, n + 2, size.height - 1 };
        Color color = this.getBackground();
        if (this.$WJB != null) {
            color = this.$WJB;
        }
        if (!this.$XJB) {
            color = this.$FKB(color);
        }
        graphics.setColor(color);
        graphics.fillRect(0, 0, size.width, size.height);
        graphics.setColor(Color.black);
        for (int i = 3; i < 5; ++i) {
            graphics.drawLine(array[i], array2[i], array[i + 1], array2[i + 1]);
        }
        graphics.setColor(Color.white);
        for (int j = 0; j < 3; ++j) {
            graphics.drawLine(array[j], array2[j], array[j + 1], array2[j + 1]);
        }
        graphics.setColor(color.darker());
        graphics.drawLine(array[4] - 1, array2[4], array[4] - 1, array2[5]);
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        if (fontMetrics == null) {
            return;
        }
        Dimension preferredSize;
        if (this.$OU != null) {
            preferredSize = this.$OU.getPreferredSize();
        }
        else {
            preferredSize = new Dimension(0, 0);
        }
        if (this.$MP == null) {
            this.$MP = "";
        }
        final int n2 = fontMetrics.stringWidth(this.$MP) + this.$SAB + this.$YJB + preferredSize.width + this.$ZJB;
        int n3 = 4;
        if (this.image != null) {
            graphics.drawImage(this.image, n3, (size.height - this.$TAB) / 2 + n, this.$SAB, this.$TAB, color, this);
            n3 = n3 + this.$SAB + this.$YJB;
        }
        if (this.$OU != null) {
            this.$OU.setSize(preferredSize);
            final int n4 = (size.height - preferredSize.height) / 2 + n;
            graphics.translate(n3, n4);
            try {
                this.$OU.paint(graphics);
            }
            finally {
                graphics.translate(-n3, -n4);
            }
            n3 = n3 + preferredSize.width + this.$ZJB;
        }
        if (this.$MP.length() != 0) {
            final int n5 = (size.height + fontMetrics.getAscent() - fontMetrics.getDescent() >> 1) + n;
            graphics.setColor(this.getForeground());
            graphics.drawString(this.$MP, n3, n5);
        }
    }
    
    public void removeActionListener(final ActionListener actionListener) {
        this.$AKB.removeActionListener(actionListener);
    }
    
    public void setComponent(final Component $ou) {
        if ($ou == this.$OU) {
            return;
        }
        if ((this.$OU = $ou) != null) {
            this.$ZJB = 4;
            $ou.setSize($ou.getPreferredSize());
        }
        else {
            this.$ZJB = 0;
        }
    }
    
    public void setImage(final Image image, final int n, final int n2) {
        if (image == this.image && n == this.$SAB && n2 == this.$TAB) {
            return;
        }
        if (n != this.$SAB || n2 != this.$TAB) {
            this.size = null;
        }
        if ((this.image = image) != null) {
            this.$SAB = Math.max(0, n);
            this.$TAB = Math.max(0, n2);
            this.$YJB = 4;
        }
        else {
            this.$SAB = 0;
            this.$TAB = 0;
            this.$YJB = 0;
        }
    }
    
    public void setLabel(final String $mp) {
        if (!$mp.equals(this.$MP)) {
            this.$MP = $mp;
            this.size = null;
        }
    }
    
    public void setState(final boolean $xjb) {
        this.$XJB = $xjb;
    }
}
