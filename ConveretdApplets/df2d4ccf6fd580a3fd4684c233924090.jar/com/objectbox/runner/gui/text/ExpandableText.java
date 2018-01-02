// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.gui.text;

import java.awt.Insets;

public class ExpandableText extends MultiText
{
    public int m_nLookAhead;
    protected int m_nFixedWidth;
    
    public ExpandableText() {
        this("");
    }
    
    public ExpandableText(final String s) {
        super(s);
        this.m_nLookAhead = 15;
        this.m_nFixedWidth = 100;
        this.m_nFixedWidth = 100;
        this.setAutoWrap(true);
        this.setAllowEnter(false);
        this.setInsets(new Insets(0, 0, 0, 0));
    }
    
    protected void onVerticalScroll() {
        int n = 0;
        int n2 = 0;
        for (int i = 0; i <= super.cursorPoint.y; ++i) {
            n += ((Paragraph)super.m_vParagraphs.elementAt(i)).getYSpan();
            if (n > super.y_offset) {
                n2 = n - super.y_offset;
            }
        }
        final Paragraph paragraph = super.m_vParagraphs.elementAt(super.cursorPoint.y);
        final int n3 = n2 - paragraph.getYSpan() + paragraph.getCursorPoint().y;
        int n4 = 2;
        if (super.m_nBorderStyle == 0) {
            n4 = 0;
        }
        else if (super.m_nBorderStyle == 3) {
            n4 = 1;
        }
        if (n3 + super.m_inTextInsets.top + super.m_inTextInsets.bottom + 2 * n4 > this.getSize().height) {
            super.y_offset += paragraph.getFontMetrics().getHeight();
        }
        else if (n3 < 0 && super.y_offset > 0) {
            super.y_offset -= paragraph.getFontMetrics().getHeight();
        }
    }
    
    public void setFixedWidth(final int nFixedWidth) {
        this.m_nFixedWidth = nFixedWidth;
    }
    
    public void sizeToFit() {
        final int stringWidth = this.getFontMetrics((this.getFont() != null) ? this.getFont() : super.defaultfont).stringWidth(this.getText());
        int nFixedWidth;
        if (this.m_nFixedWidth > 0 && stringWidth + this.m_nLookAhead > this.m_nFixedWidth) {
            nFixedWidth = this.m_nFixedWidth;
            this.setAutoWrap(true);
        }
        else {
            this.setAutoWrap(false);
            nFixedWidth = stringWidth + this.m_nLookAhead;
        }
        if (this.getBounds().width != nFixedWidth) {
            this.setBounds(this.getBounds().x, this.getBounds().y, nFixedWidth, this.getBounds().height);
        }
    }
    
    public void update() {
        this.sizeToFit();
        super.update();
    }
}
