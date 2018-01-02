// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.gui.text;

import java.awt.SystemColor;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.StringTokenizer;
import java.awt.Image;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Color;
import java.util.Vector;
import java.awt.Panel;

public class OBLabel extends Panel implements IScrollable
{
    public static final int STANDARD = 1;
    public static final int LEFT = 1;
    public static final int CENTER = 3;
    public static final int RIGHT = 2;
    public static final int TOP = 1;
    public static final int BOTTOM = 2;
    public static final int NONE = 0;
    public static final int INSET = 1;
    public static final int RAISED = 2;
    public static final int NORMAL = 3;
    protected Vector m_vParagraphs;
    protected Color borderColor;
    protected Insets m_inTextInsets;
    Font defaultfont;
    int m_nHAlign;
    int m_nVAlign;
    int m_nBorderStyle;
    int y_offset;
    int prevYOffset;
    int m_nWidthOffset;
    boolean m_bUnderline;
    boolean m_bStrikeOut;
    boolean m_bAutoWrap;
    transient Image offscreen;
    protected boolean m_bDoubleBuffering;
    protected boolean m_bLockUpdate;
    
    public OBLabel() {
        this("");
    }
    
    public OBLabel(final String s) {
        this.borderColor = Color.black;
        this.defaultfont = new Font("Dialog", 0, 12);
        this.m_nHAlign = 1;
        this.m_nVAlign = 1;
        this.m_nBorderStyle = 0;
        this.y_offset = 0;
        this.prevYOffset = 0;
        this.m_nWidthOffset = 0;
        this.m_bUnderline = false;
        this.m_bStrikeOut = false;
        this.m_bAutoWrap = true;
        this.m_bDoubleBuffering = true;
        this.m_bLockUpdate = false;
        this.m_vParagraphs = new Vector();
        super.setFont(this.defaultfont);
        super.setBackground(Color.white);
        super.setForeground(Color.black);
        this.m_inTextInsets = new Insets(3, 3, 3, 3);
        if (s.equals("")) {
            final Paragraph paragraph = new Paragraph(this, s);
            paragraph.setFont(this.getFont());
            paragraph.setFontMetrics(this.getFontMetrics(this.getFont()));
            paragraph.setBackground(this.getBackground());
            paragraph.setForeground(this.getForeground());
            paragraph.setWidth(this.getSize().width);
            paragraph.setInsets(this.m_inTextInsets);
            paragraph.setAutoWrap(this.isAutoWrap());
            this.m_vParagraphs.addElement(paragraph);
        }
        else {
            final StringTokenizer stringTokenizer = new StringTokenizer(s, "\n", false);
            while (stringTokenizer.hasMoreTokens()) {
                final Paragraph paragraph2 = new Paragraph(this, stringTokenizer.nextToken());
                paragraph2.setFont(this.getFont());
                paragraph2.setFontMetrics(this.getFontMetrics(this.getFont()));
                paragraph2.setBackground(this.getBackground());
                paragraph2.setForeground(this.getForeground());
                paragraph2.setWidth(this.getSize().width);
                paragraph2.setInsets(this.m_inTextInsets);
                paragraph2.setAutoWrap(this.isAutoWrap());
                this.m_vParagraphs.addElement(paragraph2);
            }
        }
    }
    
    public void addNotify() {
        super.addNotify();
        synchronized (this) {
            for (int i = 0; i < this.m_vParagraphs.size(); ++i) {
                final Paragraph paragraph = this.m_vParagraphs.elementAt(i);
                paragraph.setWidth(this.getSize().width - (this.m_inTextInsets.left + this.m_inTextInsets.right) - this.m_nWidthOffset);
                paragraph.addNotify();
            }
        }
    }
    
    public void draw(final Graphics graphics) {
        if (graphics == null || this.isLockUpdate()) {
            return;
        }
        final Dimension size = this.getSize();
        this.printBorder(graphics);
        int n = 2;
        if (this.m_nBorderStyle == 0) {
            n = 0;
        }
        else if (this.m_nBorderStyle == 3) {
            n = 1;
        }
        graphics.clipRect(this.m_inTextInsets.left + n, this.m_inTextInsets.top + n, size.width - this.m_inTextInsets.left - this.m_inTextInsets.right - 2 * n + 1, size.height - this.m_inTextInsets.top - this.m_inTextInsets.bottom - 2 * n + 1);
        int top = this.m_inTextInsets.top;
        if (this.y_offset == 0 && this.m_nVAlign != 1) {
            final int height = size.height;
            int n2 = 0;
            for (int i = 0; i < this.m_vParagraphs.size(); ++i) {
                n2 += ((Paragraph)this.m_vParagraphs.elementAt(i)).getYSpan();
            }
            if (this.m_nVAlign == 3) {
                top = height / 2 - n2 / 2;
            }
            else if (this.m_nVAlign == 2) {
                top = height - n2 - this.m_inTextInsets.bottom;
            }
        }
        int n3 = top - this.y_offset;
        final int left = this.m_inTextInsets.left;
        graphics.setColor(this.getBackground());
        graphics.fillRect(2, 2, size.width - 4, size.height - 4);
        graphics.setColor(this.getForeground());
        for (int j = 0; j < this.m_vParagraphs.size(); ++j) {
            final Paragraph paragraph = this.m_vParagraphs.elementAt(j);
            paragraph.render(graphics, left, n3, this.m_bUnderline, this.m_bStrikeOut);
            n3 += paragraph.getYSpan();
        }
    }
    
    public boolean getAutoWrap() {
        return this.m_bAutoWrap;
    }
    
    public boolean getBorder() {
        return this.m_nBorderStyle != 0;
    }
    
    public Color getBorderColor() {
        return this.borderColor;
    }
    
    public int getBorderStyle() {
        return this.m_nBorderStyle;
    }
    
    public int getHAlign() {
        return this.m_nHAlign;
    }
    
    public Insets getInsets() {
        return this.m_inTextInsets;
    }
    
    public Dimension getMinimumSize() {
        return new Dimension(80, 20);
    }
    
    public Dimension getPreferredSize() {
        final Dimension dimension = new Dimension(80, 0);
        int n = 0;
        for (int i = 0; i < this.m_vParagraphs.size(); ++i) {
            n += ((Paragraph)this.m_vParagraphs.elementAt(i)).getYSpan();
        }
        int n2 = 2;
        if (this.m_nBorderStyle == 0) {
            n2 = 0;
        }
        else if (this.m_nBorderStyle == 3) {
            n2 = 1;
        }
        dimension.height = this.m_inTextInsets.top + this.m_inTextInsets.bottom + n + 2 * n2;
        return dimension;
    }
    
    public boolean getRaised() {
        return this.m_nBorderStyle == 2;
    }
    
    public String getText() {
        if (this.m_vParagraphs.size() == 0) {
            return null;
        }
        String string = new String();
        for (int i = 0; i < this.m_vParagraphs.size(); ++i) {
            string = String.valueOf(string) + ((Paragraph)this.m_vParagraphs.elementAt(i)).getText() + "\n";
        }
        return string.substring(0, string.length() - 1);
    }
    
    public int getTextHIndent() {
        return this.m_inTextInsets.left;
    }
    
    public int getTextVIndent() {
        return this.m_inTextInsets.top;
    }
    
    public int getVAlign() {
        return this.m_nVAlign;
    }
    
    protected int getWidthOffset() {
        return this.m_nWidthOffset;
    }
    
    protected boolean isAutoWrap() {
        return this.m_bAutoWrap;
    }
    
    public boolean isLockUpdate() {
        return this.m_bLockUpdate;
    }
    
    public boolean isStrikeOut() {
        return this.m_bStrikeOut;
    }
    
    public boolean isUnderLine() {
        return this.m_bUnderline;
    }
    
    public void paint(final Graphics graphics) {
        if (!this.isShowing()) {
            return;
        }
        if (this.m_bDoubleBuffering && this.validateImage()) {
            final Dimension size = this.getSize();
            final Rectangle clipBounds = graphics.getClipBounds();
            final Graphics graphics2 = this.offscreen.getGraphics();
            if (clipBounds != null) {
                graphics2.clipRect(clipBounds.x, clipBounds.y, clipBounds.width, clipBounds.height);
            }
            graphics2.setFont(graphics.getFont());
            graphics2.setColor(this.getBackground());
            graphics2.fillRect(0, 0, size.width, size.height);
            graphics2.setColor(graphics.getColor());
            this.draw(graphics2);
            graphics.drawImage(this.offscreen, 0, 0, this);
            graphics2.dispose();
        }
        else if (graphics != null && graphics.getClipBounds() != null) {
            this.draw(graphics);
        }
    }
    
    private void paintBorder(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final boolean b) {
        graphics.setColor(SystemColor.controlLtHighlight);
        if (b) {
            graphics.drawLine(n, n2, n + n3, n2);
            graphics.drawLine(n, n2, n, n2 + n4);
        }
        else {
            graphics.drawLine(n + 1, n2 + n4 - 1, n + n3 - 1, n2 + n4 - 1);
            graphics.drawLine(n + n3 - 1, n2 + 1, n + n3 - 1, n2 + n4 - 1);
        }
        graphics.setColor(SystemColor.controlHighlight);
        if (!b) {
            graphics.drawLine(n + 1, n2 + n4 - 2, n + n3 - 2, n2 + n4 - 2);
            graphics.drawLine(n + n3 - 2, n2 + 1, n + n3 - 2, n2 + n4 - 2);
        }
        graphics.setColor(SystemColor.controlShadow);
        if (b) {
            graphics.drawLine(n + 1, n2 + n4 - 1, n + n3 - 1, n2 + n4 - 1);
            graphics.drawLine(n + n3 - 1, n2 + 1, n + n3 - 1, n2 + n4 - 1);
        }
        else {
            graphics.drawLine(n, n2, n + n3 - 1, n2);
            graphics.drawLine(n, n2, n, n2 + n4 - 1);
        }
        graphics.setColor(SystemColor.controlDkShadow);
        if (b) {
            graphics.drawLine(n, n2 + n4, n + n3, n2 + n4);
            graphics.drawLine(n + n3, n2, n + n3, n2 + n4);
        }
        else {
            graphics.drawLine(n + 1, n2 + 1, n + n3 - 2, n2 + 1);
            graphics.drawLine(n + 1, n2 + 1, n + 1, n2 + n4 - 2);
        }
    }
    
    protected void printBorder(final Graphics graphics) {
        final Dimension size = super.getSize();
        if (this.m_nBorderStyle == 0) {
            graphics.setColor(this.getBackground());
            graphics.drawRect(0, 0, size.width - 1, size.height - 1);
        }
        else if (this.m_nBorderStyle == 3) {
            graphics.setColor(this.borderColor);
            graphics.drawRect(0, 0, size.width - 1, size.height - 1);
        }
        else if (this.m_nBorderStyle == 2 || this.m_nBorderStyle == 1) {
            this.paintBorder(graphics, 0, 0, size.width, size.height, this.m_nBorderStyle == 2);
        }
    }
    
    public void repaint() {
        final Dimension size = this.getSize();
        final int height = size.height;
        final int width = size.width;
        final Graphics graphics = this.getGraphics();
        if (graphics != null) {
            if (!this.m_bDoubleBuffering) {
                graphics.setColor(this.getBackground());
                graphics.fillRect(0, 0, size.width, size.height);
            }
            this.update(graphics, 0, 0, width, height);
            graphics.dispose();
        }
    }
    
    public void setAutoWrap(final boolean b) {
        this.m_bAutoWrap = b;
        for (int i = 0; i < this.m_vParagraphs.size(); ++i) {
            ((Paragraph)this.m_vParagraphs.elementAt(i)).setAutoWrap(b);
        }
    }
    
    public void setBackground(final Color color) {
        super.setBackground(color);
        for (int i = 0; i < this.m_vParagraphs.size(); ++i) {
            ((Paragraph)this.m_vParagraphs.elementAt(i)).setBackground(color);
        }
        this.repaint();
    }
    
    public void setBorder(final boolean b) {
        if (!b) {
            this.m_nBorderStyle = 0;
            this.repaint();
        }
    }
    
    public void setBorderColor(final Color borderColor) {
        this.borderColor = borderColor;
        this.repaint();
    }
    
    public void setBorderStyle(final int nBorderStyle) {
        if (nBorderStyle != 0 && nBorderStyle != 1 && nBorderStyle != 2 && nBorderStyle != 3) {
            return;
        }
        this.m_nBorderStyle = nBorderStyle;
        this.repaint();
    }
    
    public void setBounds(final int n, final int n2, final int n3, final int n4) {
        super.setBounds(n, n2, n3, n4);
        for (int i = 0; i < this.m_vParagraphs.size(); ++i) {
            ((Paragraph)this.m_vParagraphs.elementAt(i)).setWidth(n3 - (this.m_inTextInsets.left + this.m_inTextInsets.right) - this.m_nWidthOffset);
        }
        this.repaint();
    }
    
    public void setFont(final int n, final Font font) {
        final Paragraph paragraph = this.m_vParagraphs.elementAt(n);
        if (paragraph != null) {
            paragraph.setFont(font);
            paragraph.setFontMetrics(this.getFontMetrics(font));
        }
        this.repaint();
    }
    
    public void setFont(final Font font) {
        super.setFont(font);
        for (int i = 0; i < this.m_vParagraphs.size(); ++i) {
            final Paragraph paragraph = this.m_vParagraphs.elementAt(i);
            paragraph.setFont(font);
            paragraph.setFontMetrics(this.getFontMetrics(font));
        }
        this.repaint();
    }
    
    public void setForeground(final Color color) {
        super.setForeground(color);
        for (int i = 0; i < this.m_vParagraphs.size(); ++i) {
            ((Paragraph)this.m_vParagraphs.elementAt(i)).setForeground(color);
        }
        this.repaint();
    }
    
    public void setHAlign(final int n) {
        if (n != 1 && n != 3 && n != 2) {
            return;
        }
        this.m_nHAlign = n;
        for (int i = 0; i < this.m_vParagraphs.size(); ++i) {
            ((Paragraph)this.m_vParagraphs.elementAt(i)).setHAlign(n);
        }
        this.repaint();
    }
    
    public void setInsets(final Insets insets) {
        this.m_inTextInsets = insets;
        for (int i = 0; i < this.m_vParagraphs.size(); ++i) {
            ((Paragraph)this.m_vParagraphs.elementAt(i)).setInsets(insets);
        }
        this.repaint();
    }
    
    public void setLockUpdate(final boolean bLockUpdate) {
        this.m_bLockUpdate = bLockUpdate;
    }
    
    public void setRaised(final boolean b) {
        if (b) {
            this.m_nBorderStyle = 2;
            this.repaint();
        }
    }
    
    public void setSize(final int n, final int n2) {
        super.setSize(n, n2);
        for (int i = 0; i < this.m_vParagraphs.size(); ++i) {
            ((Paragraph)this.m_vParagraphs.elementAt(i)).setWidth(n - (this.m_inTextInsets.left + this.m_inTextInsets.right) - this.m_nWidthOffset);
        }
        this.repaint();
    }
    
    public void setStrikeOut(final boolean bStrikeOut) {
        this.m_bStrikeOut = bStrikeOut;
        this.repaint();
    }
    
    public void setText(final String s) {
        this.setText(s, true);
    }
    
    public void setText(String s, final boolean b) {
        if (s == null) {
            s = "";
        }
        if (this.m_vParagraphs.size() > 0) {
            this.m_vParagraphs.removeAllElements();
        }
        if (s.equals("")) {
            final Paragraph paragraph = new Paragraph(this, s);
            paragraph.setFont(this.getFont());
            paragraph.setFontMetrics(this.getFontMetrics(this.getFont()));
            paragraph.setBackground(this.getBackground());
            paragraph.setForeground(this.getForeground());
            paragraph.setWidth(this.getSize().width - this.m_nWidthOffset);
            paragraph.setInsets(this.m_inTextInsets);
            paragraph.setHAlign(this.getHAlign());
            paragraph.setAutoWrap(this.isAutoWrap());
            this.m_vParagraphs.addElement(paragraph);
        }
        else {
            final StringTokenizer stringTokenizer = new StringTokenizer(s, "\n", false);
            while (stringTokenizer.hasMoreTokens()) {
                final Paragraph paragraph2 = new Paragraph(this, stringTokenizer.nextToken());
                paragraph2.setFont(this.getFont());
                paragraph2.setFontMetrics(this.getFontMetrics(this.getFont()));
                paragraph2.setBackground(this.getBackground());
                paragraph2.setForeground(this.getForeground());
                paragraph2.setWidth(this.getSize().width - this.m_nWidthOffset);
                paragraph2.setInsets(this.m_inTextInsets);
                paragraph2.setHAlign(this.getHAlign());
                paragraph2.setAutoWrap(this.isAutoWrap());
                this.m_vParagraphs.addElement(paragraph2);
            }
        }
        if (b) {
            this.repaint();
        }
    }
    
    public void setTextHIndent(final int left) {
        this.m_inTextInsets.left = left;
        this.repaint();
    }
    
    public void setTextVIndent(final int top) {
        this.m_inTextInsets.top = top;
    }
    
    public void setUnderLine(final boolean bUnderline) {
        this.m_bUnderline = bUnderline;
    }
    
    public void setVAlign(final int nvAlign) {
        if (nvAlign != 1 && nvAlign != 3 && nvAlign != 2) {
            return;
        }
        this.m_nVAlign = nvAlign;
        this.repaint();
    }
    
    protected void setWidthOffset(final int nWidthOffset) {
        this.m_nWidthOffset = nWidthOffset;
        final int n = this.getSize().width - this.m_inTextInsets.left - this.m_inTextInsets.right;
        for (int i = 0; i < this.m_vParagraphs.size(); ++i) {
            ((Paragraph)this.m_vParagraphs.elementAt(i)).setWidth(n - nWidthOffset);
        }
    }
    
    public void setXOffset(final int n) {
    }
    
    public void update() {
        final Dimension size = this.getSize();
        final int height = size.height;
        final int width = size.width;
        final Graphics graphics = this.getGraphics();
        if (graphics != null) {
            this.update(graphics, 0, 0, width, height);
            graphics.dispose();
        }
    }
    
    public void update(final Graphics graphics) {
        if (this.m_bDoubleBuffering) {
            this.paint(graphics);
        }
        else {
            super.update(graphics);
        }
    }
    
    public void update(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        graphics.clipRect(n, n2, n3, n4);
        this.paint(graphics);
    }
    
    protected boolean validateImage() {
        try {
            final Dimension size = this.getSize();
            if (this.offscreen == null || this.offscreen.getWidth(this) != size.width || this.offscreen.getHeight(this) != size.height) {
                if (size.width <= 0 || size.height <= 0) {
                    return false;
                }
                this.offscreen = this.createImage(size.width, size.height);
            }
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }
}
