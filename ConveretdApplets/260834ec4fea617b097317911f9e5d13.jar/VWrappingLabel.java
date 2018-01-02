import java.util.Enumeration;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.util.Vector;
import java.awt.FontMetrics;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class VWrappingLabel extends Canvas
{
    protected String text;
    protected float m_nHAlign;
    protected float m_nVAlign;
    protected int baseline;
    protected int m_nMargin;
    
    public String getText() {
        return this.text;
    }
    
    public void setHAlignStyle(final float nhAlign) {
        this.m_nHAlign = nhAlign;
        this.invalidate();
    }
    
    public void setVAlignStyle(final float nvAlign) {
        this.m_nVAlign = nvAlign;
        this.invalidate();
    }
    
    public void setText(final String text) {
        this.text = text;
    }
    
    protected Vector breakIntoLines(String s, final FontMetrics fontMetrics, final int n) {
        int i = 0;
        final Vector<String> vector = new Vector<String>();
        while (i != -1) {
            while (i < this.text.length() && this.text.charAt(i) == ' ' && ++i < this.text.length()) {}
            int j = i;
            int n2 = -1;
            String s2 = null;
            while (j >= i) {
                final int index = this.text.indexOf(10, j);
                final int index2 = this.text.indexOf(32, j);
                boolean b;
                if (index != -1 && (index2 == -1 || (index2 != -1 && index < index2))) {
                    j = index;
                    b = true;
                }
                else {
                    j = index2;
                    b = false;
                }
                if (j == -1) {
                    s = this.text.substring(i);
                }
                else {
                    s = this.text.substring(i, j);
                }
                if (fontMetrics.stringWidth(s) >= n) {
                    break;
                }
                s2 = s;
                n2 = j;
                if (b) {
                    ++n2;
                }
                if (j == -1) {
                    break;
                }
                if (b) {
                    break;
                }
                ++j;
            }
            if (s2 == null) {
                int n3 = 0;
                int k;
                for (k = i; k < this.text.length(); ++k) {
                    final int charWidth = fontMetrics.charWidth(this.text.charAt(k));
                    if (n3 + charWidth >= n) {
                        break;
                    }
                    n3 += charWidth;
                }
                vector.addElement(this.text.substring(i, k));
                i = k;
            }
            else {
                vector.addElement(s2);
                i = n2;
            }
        }
        return vector;
    }
    
    protected void drawAlignedString(final Graphics graphics, final String s, final FontMetrics fontMetrics, final int n, final int n2, final int n3) {
        int n4 = n;
        final int n5 = n2 + this.baseline;
        if (this.m_nHAlign != 0.0f) {
            final int stringWidth = fontMetrics.stringWidth(s);
            if (this.m_nHAlign == 0.5f) {
                n4 += (n3 - stringWidth) / 2;
            }
            else if (this.m_nHAlign == 1.0f) {
                n4 = n4 + n3 - stringWidth;
            }
        }
        graphics.drawString(s, n4, n5);
    }
    
    public VWrappingLabel() {
        this("");
    }
    
    public VWrappingLabel(final String s) {
        this(s, 0.0f, 0.5f);
    }
    
    public VWrappingLabel(final String text, final float hAlignStyle, final float vAlignStyle) {
        this.m_nMargin = 4;
        this.setText(text);
        this.setHAlignStyle(hAlignStyle);
        this.setVAlignStyle(vAlignStyle);
    }
    
    public void processMouseEvent(final MouseEvent mouseEvent) {
        if (mouseEvent.getID() == 500) {
            this.setVisible(false);
            this.transferFocus();
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.text != null) {
            final String s = new String(this.text);
            int n = 0;
            final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
            this.baseline = fontMetrics.getMaxAscent();
            final Dimension size = this.getSize();
            final Vector breakIntoLines = this.breakIntoLines(s, fontMetrics, size.width - this.m_nMargin * 2);
            if (this.m_nVAlign == 0.5f) {
                n = size.height / 2 - breakIntoLines.size() * fontMetrics.getHeight() / 2;
            }
            else if (this.m_nVAlign == 1.0f) {
                n = size.height - breakIntoLines.size() * fontMetrics.getHeight();
            }
            graphics.setColor(this.getBackground());
            graphics.fillRect(0, 0, size.width, size.height);
            graphics.setColor(this.getForeground());
            final Enumeration<String> elements = breakIntoLines.elements();
            while (elements.hasMoreElements()) {
                this.drawAlignedString(graphics, elements.nextElement(), fontMetrics, this.m_nMargin, n, size.width);
                n += fontMetrics.getHeight();
            }
        }
    }
    
    public int computeHeight() {
        int n = 0;
        if (this.text != null) {
            final String s = new String(this.text);
            final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
            this.baseline = fontMetrics.getMaxAscent();
            final Vector breakIntoLines = this.breakIntoLines(s, fontMetrics, this.getSize().width - this.m_nMargin * 2);
            int n2 = fontMetrics.getHeight() / 2;
            int n3 = 0;
            final Enumeration elements = breakIntoLines.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement();
                ++n3;
                n2 += fontMetrics.getHeight();
            }
            n = n2;
        }
        return n;
    }
    
    public String paramString() {
        return "";
    }
    
    public float getHAlignStyle() {
        return this.m_nHAlign;
    }
    
    public float getVAlignStyle() {
        return this.m_nVAlign;
    }
}
