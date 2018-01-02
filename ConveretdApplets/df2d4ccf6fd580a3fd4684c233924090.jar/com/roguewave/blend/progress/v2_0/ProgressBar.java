// 
// Decompiled by Procyon v0.5.30
// 

package com.roguewave.blend.progress.v2_0;

import com.roguewave.blend.border.v2_0.RoundedBorder;
import java.awt.Insets;
import java.awt.Image;
import java.awt.image.ImageObserver;
import com.roguewave.blend.border.v2_0.FlatBorder;
import java.awt.FontMetrics;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Graphics;
import java.beans.PropertyChangeListener;
import com.roguewave.blend.border.v2_0.ThreeDBorder;
import java.beans.PropertyChangeSupport;
import java.awt.Rectangle;
import java.awt.Font;
import com.roguewave.blend.border.v2_0.Border;
import java.awt.Color;
import java.awt.Canvas;

public class ProgressBar extends Canvas
{
    public static final int ALIGNMENT_LEFT = 1;
    public static final int ALIGNMENT_RIGHT = 2;
    public static final int ALIGNMENT_CENTER = 3;
    public static final int TEXT_FORMAT_PERCENTAGE = 1;
    public static final int TEXT_FORMAT_DECIMAL = 2;
    public static final int TEXT_FORMAT_NUMERIC = 3;
    public static final int TEXT_FORMAT_NONE = 4;
    public static final Color DEFAULT_BAR_COLOR;
    public static final Color DEFAULT_FOREGROUND;
    public static final int DEFAULT_ALIGNMENT = 3;
    public static final Border DEFAULT_BORDER;
    public static final int DEFAULT_MINIMUM = 0;
    public static final int DEFAULT_MAXIMUM = 100;
    public static final int DEFAULT_PROGRESS = 0;
    public static final String DEFAULT_PRE_TEXT = "";
    public static final int DEFAULT_TEXT_FORMAT = 1;
    public static final String DEFAULT_POST_TEXT = "";
    public static final Font DEFAULT_FONT;
    private int m_nProgress;
    private String m_strPreText;
    private String m_strPostText;
    private int m_nAlignment;
    private Border m_bdrBorder;
    private int m_nMinimum;
    private int m_nMaximum;
    private transient int m_nIncrements;
    private Color m_colBarColor;
    private String m_strText;
    private Rectangle m_rectTextRectangle;
    private boolean m_bInitialized;
    private int m_nTextFormat;
    private PropertyChangeSupport m_pcsChanges;
    static final Color FLAT_SHADOW_COLOR;
    static final Color FLAT_REFLECTION_COLOR;
    static final long serialVersionUID = -8687333054606268895L;
    
    static {
        DEFAULT_BAR_COLOR = Color.red;
        DEFAULT_FOREGROUND = Color.black;
        DEFAULT_BORDER = new ThreeDBorder();
        DEFAULT_FONT = new Font("Helvetica", 0, 12);
        FLAT_SHADOW_COLOR = Color.black;
        FLAT_REFLECTION_COLOR = Color.black;
    }
    
    public ProgressBar() {
        this(3);
    }
    
    public ProgressBar(final int n) {
        this(n, ProgressBar.DEFAULT_BORDER);
    }
    
    public ProgressBar(final int n, final int n2) {
        this(n, ProgressBar.DEFAULT_BORDER, n2);
    }
    
    public ProgressBar(final int n, final Border border) {
        this(n, border, 0);
    }
    
    public ProgressBar(final int n, final Border border, final int n2) {
        this(n, border, n2, ProgressBar.DEFAULT_BAR_COLOR);
    }
    
    public ProgressBar(final int n, final Border border, final int n2, final Color color) {
        this(n, border, n2, color, ProgressBar.DEFAULT_FOREGROUND);
    }
    
    public ProgressBar(final int alignment, final Border border, final int progress, final Color barColor, final Color foreground) {
        this.m_strText = "";
        this.m_rectTextRectangle = new Rectangle();
        this.m_bInitialized = false;
        this.m_pcsChanges = new PropertyChangeSupport(this);
        this.setAlignment(alignment);
        this.setBorder(border);
        this.setBarColor(barColor);
        this.setForeground(foreground);
        this.setProgress(progress);
        this.setMinimum(0);
        this.setMaximum(100);
        this.setPreText("");
        this.setTextFormat(1);
        this.setPostText("");
        this.setFont(ProgressBar.DEFAULT_FONT);
    }
    
    public void addPropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
        this.m_pcsChanges.addPropertyChangeListener(propertyChangeListener);
    }
    
    private void calculateText() {
        String s = "";
        if (this.m_nTextFormat == 1) {
            s = String.valueOf(String.valueOf((int)(Object)new Double(Math.round(new Integer(100 * (this.m_nProgress - this.m_nMinimum)) / new Integer(this.m_nMaximum - this.m_nMinimum))))) + "%";
        }
        else if (this.m_nTextFormat == 2) {
            s = String.valueOf(Math.round(100.0 * (new Integer(this.m_nProgress - this.m_nMinimum) / new Integer(this.m_nMaximum - this.m_nMinimum))) / 100.0);
        }
        else if (this.m_nTextFormat == 3) {
            s = String.valueOf(this.m_nProgress - this.m_nMinimum);
        }
        this.m_strText = String.valueOf(this.m_strPreText) + s + this.m_strPostText;
    }
    
    public void decrement() {
        this.decrementBy(1);
    }
    
    public void decrementBy(final int n) {
        this.incrementBy(-1 * n);
    }
    
    private void displayText(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        if (this.m_bInitialized) {
            graphics.setColor(this.getBackground());
            graphics.fillRect(this.m_rectTextRectangle.x, this.m_rectTextRectangle.y, this.m_rectTextRectangle.width, this.m_rectTextRectangle.height);
            final Color color = graphics.getColor();
            graphics.setColor(this.m_colBarColor);
            final int n5 = (this.m_nProgress - this.m_nMinimum) * n3 / this.m_nIncrements + n;
            if (n5 > this.m_rectTextRectangle.x + this.m_rectTextRectangle.width) {
                graphics.fillRect(this.m_rectTextRectangle.x, this.m_rectTextRectangle.y, this.m_rectTextRectangle.width, this.m_rectTextRectangle.height);
            }
            else if (n5 > this.m_rectTextRectangle.x) {
                graphics.fillRect(this.m_rectTextRectangle.x, this.m_rectTextRectangle.y, n5 - this.m_rectTextRectangle.x, this.m_rectTextRectangle.height);
            }
            graphics.setColor(color);
        }
        this.calculateText();
        final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
        final int stringWidth = fontMetrics.stringWidth(this.m_strText);
        final int maxAscent = fontMetrics.getMaxAscent();
        final int n6 = 2;
        new Dimension(stringWidth, maxAscent);
        final Point point = new Point();
        point.y = n2 + n4 / 2 + maxAscent / 2;
        switch (this.m_nAlignment) {
            case 1: {
                point.x = n + n6;
                break;
            }
            case 2: {
                point.x = n + n3 - stringWidth - n6;
                break;
            }
            case 3: {
                point.x = n + (n3 - stringWidth - n6) / 2;
                break;
            }
        }
        this.m_rectTextRectangle = new Rectangle(point.x, point.y - maxAscent, stringWidth + n6, maxAscent);
        final Color color2 = graphics.getColor();
        graphics.setColor(this.getForeground());
        graphics.drawString(this.m_strText, point.x, point.y);
        graphics.setColor(color2);
    }
    
    public int getAlignment() {
        return this.m_nAlignment;
    }
    
    public Color getBackground() {
        return this.m_bdrBorder.getBackground();
    }
    
    public Color getBarColor() {
        return this.m_colBarColor;
    }
    
    public Border getBorder() {
        return this.m_bdrBorder;
    }
    
    public Color getBorderColor() {
        if (this.m_bdrBorder instanceof FlatBorder) {
            return ((FlatBorder)this.m_bdrBorder).getBorderColor();
        }
        return Color.black;
    }
    
    public int getBorderThickness() {
        return this.getBorder().getThickness();
    }
    
    public int getMaximum() {
        return this.m_nMaximum;
    }
    
    public int getMinimum() {
        return this.m_nMinimum;
    }
    
    public Dimension getMinimumSize() {
        final int n = 10;
        final int n2 = 4;
        final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
        return new Dimension(Math.max(n, fontMetrics.stringWidth(this.m_strText)), Math.max(n2, fontMetrics.getMaxAscent()));
    }
    
    public String getPostText() {
        return this.m_strPostText;
    }
    
    public Dimension getPreferredSize() {
        final Dimension minimumSize = this.getMinimumSize();
        return new Dimension(minimumSize.width * 5, minimumSize.height * 3);
    }
    
    public String getPreText() {
        return this.m_strPreText;
    }
    
    public int getProgress() {
        return this.m_nProgress;
    }
    
    public Color getReflectionColor() {
        if (this.m_bdrBorder instanceof ThreeDBorder) {
            return ((ThreeDBorder)this.m_bdrBorder).getReflectionColor();
        }
        return ProgressBar.FLAT_REFLECTION_COLOR;
    }
    
    public Color getShadowColor() {
        if (this.m_bdrBorder instanceof ThreeDBorder) {
            return ((ThreeDBorder)this.m_bdrBorder).getShadowColor();
        }
        return ProgressBar.FLAT_SHADOW_COLOR;
    }
    
    public int getTextFormat() {
        return this.m_nTextFormat;
    }
    
    public void increment() {
        this.incrementBy(1);
    }
    
    public void incrementBy(final int n) {
        this.setProgress(this.m_nProgress + n);
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        this.m_bInitialized = true;
        final Dimension size = this.getSize();
        final Image image = this.createImage(size.width, size.height);
        final Graphics graphics2 = image.getGraphics();
        final Rectangle rectangle = new Rectangle(0, 0, size.width, size.height);
        this.m_bdrBorder.paint(graphics2, rectangle);
        final Insets insets = this.m_bdrBorder.getInsets(rectangle);
        this.m_nIncrements = this.m_nMaximum - this.m_nMinimum;
        final int n = size.width - insets.left - insets.right;
        final int n2 = size.height - insets.top - insets.bottom;
        final int left = insets.left;
        final int top = insets.top;
        graphics2.setColor(this.m_colBarColor);
        int n3 = this.m_nProgress;
        if (n3 > this.m_nMaximum) {
            n3 = this.m_nMaximum;
        }
        graphics2.fillRect(left, top, (n3 - this.m_nMinimum) * n / this.m_nIncrements, n2);
        graphics2.setClip(left, top, n, n2);
        this.displayText(graphics2, left, top, n, n2);
        graphics.drawImage(image, 0, 0, null);
        graphics2.dispose();
    }
    
    public void removePropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
        this.m_pcsChanges.removePropertyChangeListener(propertyChangeListener);
    }
    
    private void sanityCheck(final int n, final int n2, final int n3) {
        final String string = String.valueOf(new StringBuffer(String.valueOf(new StringBuffer(String.valueOf(new StringBuffer(String.valueOf(new StringBuffer(String.valueOf(new StringBuffer(String.valueOf(new StringBuffer(String.valueOf(new StringBuffer(String.valueOf(new StringBuffer(String.valueOf(new StringBuffer(String.valueOf(new StringBuffer(String.valueOf(new StringBuffer(String.valueOf(new String())).append("\n\n").toString())).append("Minimum: ").toString())).append(n).toString())).append("\n").toString())).append("Progress: ").toString())).append(n2).toString())).append("\n").toString())).append("Maximum: ").toString())).append(n3).toString())).append("\n\n").toString())).append("Values must obey the rule: minimum <= progress <= maximum").toString()) + "\n";
        if (n > n2 || n2 > n3) {
            throw new IllegalArgumentException(string);
        }
    }
    
    public void setAlignment(final int nAlignment) {
        final Integer n = new Integer(this.getAlignment());
        this.m_nAlignment = nAlignment;
        this.m_pcsChanges.firePropertyChange("alignment", n, new Integer(this.getAlignment()));
    }
    
    public void setBackground(final Color background) {
        final Color background2 = this.getBackground();
        this.m_bdrBorder.setBackground(background);
        this.m_pcsChanges.firePropertyChange("background", background2, this.getBackground());
    }
    
    public void setBarColor(final Color colBarColor) {
        final Color barColor = this.getBarColor();
        this.m_colBarColor = colBarColor;
        this.m_pcsChanges.firePropertyChange("barColor", barColor, this.getBarColor());
    }
    
    public void setBorder(final Border bdrBorder) {
        final Border border = this.getBorder();
        this.m_bdrBorder = bdrBorder;
        this.m_pcsChanges.firePropertyChange("border", border, this.getBorder());
        if (bdrBorder instanceof RoundedBorder) {
            ((RoundedBorder)this.m_bdrBorder).setPercentRounded(0);
        }
    }
    
    public void setBorderColor(final Color borderColor) {
        if (this.m_bdrBorder instanceof FlatBorder) {
            final Color borderColor2 = this.getBorderColor();
            ((FlatBorder)this.m_bdrBorder).setBorderColor(borderColor);
            this.m_pcsChanges.firePropertyChange("borderColor", borderColor2, this.getBorderColor());
        }
    }
    
    public void setBorderThickness(final int thickness) {
        final Integer n = new Integer(this.getBorderThickness());
        this.getBorder().setThickness(thickness);
        this.m_pcsChanges.firePropertyChange("borderThickness", n, new Integer(this.getBorderThickness()));
    }
    
    public void setEnabled(final boolean enabled) {
        final Boolean b = new Boolean(this.isEnabled());
        super.setEnabled(enabled);
        this.m_pcsChanges.firePropertyChange("enabled", b, new Boolean(this.isEnabled()));
    }
    
    public void setFont(final Font font) {
        final Font font2 = this.getFont();
        super.setFont(font);
        this.m_pcsChanges.firePropertyChange("font", font2, this.getFont());
    }
    
    public void setForeground(final Color foreground) {
        final Color foreground2 = this.getForeground();
        super.setForeground(foreground);
        this.m_pcsChanges.firePropertyChange("foreground", foreground2, this.getForeground());
    }
    
    public void setMaximum(final int nMaximum) {
        this.sanityCheck(this.getMinimum(), this.getProgress(), nMaximum);
        final Integer n = new Integer(this.getMaximum());
        this.m_nMaximum = nMaximum;
        this.m_pcsChanges.firePropertyChange("maximum", n, new Integer(this.getMaximum()));
    }
    
    public void setMinimum(final int nMinimum) {
        this.sanityCheck(nMinimum, this.getProgress(), this.getMaximum());
        final Integer n = new Integer(this.getMinimum());
        this.m_nMinimum = nMinimum;
        this.m_pcsChanges.firePropertyChange("minimum", n, new Integer(this.getMinimum()));
    }
    
    public void setPostText(final String strPostText) {
        final String postText = this.getPostText();
        this.m_strPostText = strPostText;
        this.m_pcsChanges.firePropertyChange("postText", postText, this.getPostText());
    }
    
    public void setPreText(final String strPreText) {
        final String preText = this.getPreText();
        this.m_strPreText = strPreText;
        this.m_pcsChanges.firePropertyChange("preText", preText, this.getPreText());
    }
    
    public void setProgress(final int nProgress) {
        this.sanityCheck(this.getMinimum(), nProgress, this.getMaximum());
        final Integer n = new Integer(this.getProgress());
        this.m_nProgress = nProgress;
        this.m_pcsChanges.firePropertyChange("progress", n, new Integer(this.getProgress()));
    }
    
    public void setReflectionColor(final Color reflectionColor) {
        if (this.m_bdrBorder instanceof ThreeDBorder) {
            final Color reflectionColor2 = this.getReflectionColor();
            ((ThreeDBorder)this.m_bdrBorder).setReflectionColor(reflectionColor);
            this.m_pcsChanges.firePropertyChange("reflectionColor", reflectionColor2, this.getReflectionColor());
        }
    }
    
    public void setShadowColor(final Color shadowColor) {
        if (this.m_bdrBorder instanceof ThreeDBorder) {
            final Color shadowColor2 = this.getShadowColor();
            ((ThreeDBorder)this.m_bdrBorder).setShadowColor(shadowColor);
            this.m_pcsChanges.firePropertyChange("shadowColor", shadowColor2, this.getShadowColor());
        }
    }
    
    public void setTextFormat(final int nTextFormat) {
        final Integer n = new Integer(this.getTextFormat());
        this.m_nTextFormat = nTextFormat;
        this.m_pcsChanges.firePropertyChange("textFormat", n, new Integer(this.getTextFormat()));
    }
    
    public void setVisible(final boolean visible) {
        final Boolean b = new Boolean(this.isVisible());
        super.setVisible(visible);
        this.m_pcsChanges.firePropertyChange("visible", b, new Boolean(this.isVisible()));
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
