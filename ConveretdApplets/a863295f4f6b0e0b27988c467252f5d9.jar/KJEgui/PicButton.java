// 
// Decompiled by Procyon v0.5.30
// 

package KJEgui;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.awt.Panel;

public class PicButton extends Panel
{
    private boolean bYesNo;
    private Color colorLine;
    private Color colorFill;
    private Color colorFillClicked;
    private Color colorText;
    private Font font;
    private Font fontExtra;
    private String sText;
    private String sTextExtra;
    private boolean bDown;
    private int iLineHeight;
    private boolean bEnhanced;
    private Dimension _dSize;
    private int iStringTotalWidth;
    private int iStringWidth;
    private int iStringWidthExtra;
    private int iFontHeight;
    private int iFontHeightExtra;
    private int iFontDescent;
    private int iFontDescentExtra;
    
    public PicButton() {
        this.bYesNo = false;
        this.colorLine = Color.black;
        this.colorFill = Color.red;
        this.colorFillClicked = Color.blue;
        this.colorText = Color.black;
        this.font = new Font("Helvetica", 1, 9);
        this.fontExtra = new Font("Helvetica", 0, 9);
        this.sText = "";
        this.sTextExtra = "";
        this.bDown = false;
        this.iLineHeight = 0;
        this.bEnhanced = false;
        this._dSize = new Dimension(0, 0);
        this.iStringTotalWidth = 0;
        this.iStringWidth = 0;
        this.iStringWidthExtra = 0;
        this.iFontHeight = 0;
        this.iFontHeightExtra = 0;
        this.iFontDescent = 0;
        this.iFontDescentExtra = 0;
        this.setPicButtonSize();
    }
    
    public PicButton(final String sText) {
        this.bYesNo = false;
        this.colorLine = Color.black;
        this.colorFill = Color.red;
        this.colorFillClicked = Color.blue;
        this.colorText = Color.black;
        this.font = new Font("Helvetica", 1, 9);
        this.fontExtra = new Font("Helvetica", 0, 9);
        this.sText = "";
        this.sTextExtra = "";
        this.bDown = false;
        this.iLineHeight = 0;
        this.bEnhanced = false;
        this._dSize = new Dimension(0, 0);
        this.iStringTotalWidth = 0;
        this.iStringWidth = 0;
        this.iStringWidthExtra = 0;
        this.iFontHeight = 0;
        this.iFontHeightExtra = 0;
        this.iFontDescent = 0;
        this.iFontDescentExtra = 0;
        this.sText = sText;
        this.setPicButtonSize();
    }
    
    public Dimension getMinimumSize() {
        return this._dSize;
    }
    
    public Dimension getPreferredSize() {
        return this._dSize;
    }
    
    public String getText() {
        return this.sText;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.bDown = true;
        this.repaint(0, 0, this.size().width, this.size().height + 1);
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (!this.bYesNo) {
            this.bDown = false;
        }
        this.deliverEvent(new Event(this, 1001, this));
        this.repaint(0, 0, this.size().width, this.size().height + 1);
        return true;
    }
    
    public void paint(final Graphics graphics) {
        final int width = this.size().width;
        final int height = this.size().height;
        graphics.setFont(this.font);
        if (this.bDown) {
            graphics.setColor(this.colorLine);
            graphics.drawRoundRect(0, 0, width - 1, height - 1, 15, 15);
            graphics.setColor(this.colorFillClicked);
            graphics.fillRoundRect(1, 1, width - 1, height - 1, 15, 15);
            graphics.setColor(this.colorText);
            graphics.drawString(this.sText, width / 2 - this.iStringTotalWidth / 2 + 1, height / 2 + this.iFontHeight - this.iFontHeight / 2 - this.iFontDescent + 1);
            graphics.setFont(this.fontExtra);
            graphics.drawString(this.sTextExtra, this.iStringWidth + width / 2 - this.iStringTotalWidth / 2 + 1, height / 2 + this.iFontHeight - this.iFontHeight / 2 - this.iFontDescent + 1);
        }
        else {
            graphics.setColor(this.colorLine);
            graphics.drawRoundRect(0, 0, width - 1, height - 1, 15, 15);
            graphics.setColor(this.colorFillClicked);
            graphics.drawRoundRect(0, 0, width - 2, height - 2, 15, 15);
            graphics.setColor(this.colorFill);
            graphics.fillRoundRect(1, 1, width - 2, height - 2, 15, 15);
            graphics.setColor(this.colorText);
            graphics.drawString(this.sText, width / 2 - this.iStringTotalWidth / 2, height / 2 + this.iFontHeight - this.iFontHeight / 2 - this.iFontDescent);
            graphics.setFont(this.fontExtra);
            graphics.drawString(this.sTextExtra, this.iStringWidth + width / 2 - this.iStringTotalWidth / 2, height / 2 + this.iFontHeight - this.iFontHeight / 2 - this.iFontDescent);
        }
    }
    
    public void setAttributes(final String sText, final Color colorLine, final Color colorFill, final Color colorFillClicked, final Color colorText, final Font font) {
        this.colorLine = colorLine;
        this.colorFill = colorFill;
        this.colorFillClicked = colorFillClicked;
        this.colorText = colorText;
        this.sText = sText;
        this.font = font;
        this.bEnhanced = false;
        this.setPicButtonSize();
    }
    
    public void setAttributes(final String sText, final Color colorLine, final Color colorFill, final Color colorFillClicked, final Color colorText, final Font font, final String sTextExtra, final Font fontExtra) {
        this.colorLine = colorLine;
        this.colorFill = colorFill;
        this.colorFillClicked = colorFillClicked;
        this.colorText = colorText;
        this.sText = sText;
        this.font = font;
        this.fontExtra = fontExtra;
        this.sTextExtra = sTextExtra;
        this.bEnhanced = true;
        this.setPicButtonSize();
    }
    
    public void setDefaultFont(final Font font) {
        this.iLineHeight = this.getFontMetrics(font).getHeight() - 3;
        this.font = font;
    }
    
    private void setPicButtonSize() {
        final FontMetrics fontMetrics = this.getFontMetrics(this.font);
        this.iStringWidth = fontMetrics.stringWidth(String.valueOf(this.sText) + (this.bEnhanced ? " " : ""));
        this.iFontHeight = fontMetrics.getHeight();
        this.iFontDescent = fontMetrics.getDescent();
        if (this.bEnhanced) {
            final FontMetrics fontMetrics2 = this.getFontMetrics(this.fontExtra);
            this.iStringWidthExtra = fontMetrics2.stringWidth(this.sTextExtra);
            this.iFontHeightExtra = fontMetrics2.getHeight();
            this.iFontDescentExtra = fontMetrics2.getDescent();
        }
        else {
            this.iStringWidthExtra = 0;
            this.iFontHeightExtra = 0;
            this.iFontDescentExtra = 0;
        }
        final int n = (this.iFontHeightExtra > this.iFontHeight) ? this.iFontHeightExtra : this.iFontHeight;
        this.iStringTotalWidth = this.iStringWidthExtra + this.iStringWidth;
        this._dSize = new Dimension(n + this.iStringTotalWidth, (int)(n * 1.5));
        this.invalidate();
    }
    
    public void setYesNo(final boolean b, final boolean bDown) {
        final boolean bYesNo = this.bYesNo;
        this.bDown = bDown;
        this.repaint(0, 0, this.size().width, this.size().height + 1);
    }
}
