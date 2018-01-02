// 
// Decompiled by Procyon v0.5.30
// 

package pclient.anim;

import java.awt.Toolkit;
import java.awt.FontMetrics;
import java.awt.Image;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Component;

public abstract class MotionSingleText extends MotionTextParent
{
    protected Component theCom;
    protected Graphics theGC;
    protected String theString;
    protected Color theFg;
    protected Color theBg;
    protected Font theFont;
    protected int fontBase;
    protected Image bufferImage;
    protected Graphics bufferGC;
    protected boolean isShadow;
    protected boolean isTextColor;
    protected int windowHeight;
    protected int windowWidth;
    protected int positionX;
    protected int positionY;
    protected int stringX;
    protected int stringY;
    protected int timeout;
    protected static final boolean IsDebug = false;
    
    public MotionSingleText(final Component component, final Graphics graphics, final String theString) {
        this.theCom = null;
        this.theGC = null;
        this.positionX = 0;
        this.positionY = 0;
        this.stringX = 0;
        this.stringY = 0;
        this.timeout = 70;
        this.initSetup(component, graphics);
        this.theString = theString;
    }
    
    public abstract void prepare(final Thread p0);
    
    public abstract long runOneStep(final Graphics p0);
    
    public void setPosition(final int positionX, final int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }
    
    public void setArea(final int windowWidth, final int windowHeight) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
    }
    
    public void setForeground(final Color theFg) {
        this.theFg = theFg;
        this.isTextColor = true;
    }
    
    public void setBackground(final Color theBg) {
        this.theBg = theBg;
    }
    
    public void setFont(final Font theFont) {
        this.theFont = theFont;
    }
    
    public void setFontCode(final int n) {
        this.theFont = new Font("Helvetica", 1, n);
    }
    
    public void setShadow() {
        this.isShadow = true;
    }
    
    protected int[] computeCenter(final Graphics graphics, final String s, final int n) {
        final int n2 = 1;
        final int length = s.length();
        final int[] array = new int[length];
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        int n3 = 0;
        array[0] = n3;
        for (int i = 1; i < length; ++i) {
            n3 = n3 + fontMetrics.charWidth(s.charAt(i - 1)) + n2;
            array[i] = n3;
        }
        int n4 = (n - (array[length - 1] + fontMetrics.charWidth(s.charAt(length - 1)))) / 2;
        if (n4 < 0) {
            n4 = 0;
        }
        for (int j = 0; j < length; ++j) {
            array[j] += n4;
        }
        return array;
    }
    
    public static int[] centerStringPosition(final int n, final int n2, final Font font, final String s) {
        final FontMetrics fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(font);
        final int height = fontMetrics.getHeight();
        final int stringWidth = fontMetrics.stringWidth(s);
        final int n3 = (n2 - height) / 2 + fontMetrics.getAscent();
        int n4 = (n - stringWidth) / 2;
        if (n4 < 0) {
            n4 = 0;
        }
        return new int[] { n4, n3 };
    }
    
    protected void setStringPosition(final int n, final int n2, final Font font, final String s) {
        final FontMetrics fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(font);
        final int height = fontMetrics.getHeight();
        final int stringWidth = fontMetrics.stringWidth(s);
        this.stringY = (n2 - height) / 2 + fontMetrics.getAscent();
        this.stringX = (n - stringWidth) / 2;
        if (this.stringX < 0) {
            this.stringX = 0;
        }
    }
    
    protected int getMaxCharWidth(final FontMetrics fontMetrics) {
        final int[] widths = fontMetrics.getWidths();
        int n = 0;
        for (int i = 0; i < widths.length; ++i) {
            if (n < widths[i]) {
                n = widths[i];
            }
        }
        return n;
    }
    
    protected Color randomColor() {
        return new Color((int)(Math.random() * 255.0), (int)(Math.random() * 255.0), (int)(Math.random() * 255.0));
    }
    
    protected Color averageColor(final Color color, final int n, final Color color2) {
        final int n2 = 100 - n;
        return new Color(Math.min((color.getRed() * n + color2.getRed() * n2) / 100, 255), Math.min((color.getGreen() * n + color2.getGreen() * n2) / 100, 255), Math.min((color.getBlue() * n + color2.getBlue() * n2) / 100, 255));
    }
    
    private void initSetup(final Component theCom, final Graphics theGC) {
        this.theCom = theCom;
        this.theGC = theGC;
        this.theBg = theCom.getBackground();
        this.theFg = theCom.getForeground();
        this.windowHeight = 0;
        this.windowWidth = 0;
        this.theFont = new Font("Helvetica", 1, 16);
        if (this.theFont == null) {
            this.theFont = this.theGC.getFont();
        }
        this.theGC.setFont(this.theFont);
        this.bufferImage = null;
        this.bufferGC = null;
        this.isShadow = false;
        this.isTextColor = false;
    }
}
