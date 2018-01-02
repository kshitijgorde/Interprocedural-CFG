// 
// Decompiled by Procyon v0.5.30
// 

package com.alexi;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Font;

public class ATextWithFont extends IGraphicObject
{
    protected String displayText;
    protected Font textFont;
    protected Color textColor;
    
    public ATextWithFont(final String displayText, final Font font, final Color textColor, final String theUrl, final String linkTarget) {
        this.displayText = null;
        this.textFont = null;
        this.textColor = null;
        this.displayText = displayText;
        this.textFont = new Font(font.getName(), font.getStyle(), font.getSize());
        this.textColor = textColor;
        super.theUrl = theUrl;
        super.boundingRectangle = new Rectangle();
        super.linkTarget = linkTarget;
    }
    
    public void drawGraphicObject(final Graphics graphics) {
        Color color = null;
        if (this.textColor != null) {
            color = graphics.getColor();
            graphics.setColor(this.textColor);
        }
        graphics.setFont(this.textFont);
        final int n = super.boundingRectangle.y + super.boundingRectangle.height;
        graphics.drawString(this.displayText, super.boundingRectangle.x, n);
        if (super.theUrl != null) {
            graphics.drawLine(super.boundingRectangle.x, n, super.boundingRectangle.x + super.boundingRectangle.width, n);
        }
        if (color != null) {
            graphics.setColor(color);
        }
    }
    
    public void drawGraphicObject(final Graphics graphics, final int n, final int n2) {
        final Color color = graphics.getColor();
        if (super.boundingRectangle.contains(n, n2)) {
            graphics.setColor(new Color(255, 0, 0));
        }
        else if (this.textColor != null) {
            graphics.setColor(this.textColor);
        }
        graphics.setFont(this.textFont);
        final int n3 = super.boundingRectangle.y + super.boundingRectangle.height;
        graphics.drawString(this.displayText, super.boundingRectangle.x, n3);
        if (super.theUrl != null) {
            graphics.drawLine(super.boundingRectangle.x, n3, super.boundingRectangle.x + super.boundingRectangle.width, n3);
        }
        if (color != null) {
            graphics.setColor(color);
        }
    }
    
    public String getDisplayText() {
        return this.displayText;
    }
    
    public void setDisplayText(final String displayText) {
        this.displayText = displayText;
    }
}
