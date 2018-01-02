// 
// Decompiled by Procyon v0.5.30
// 

package com.alexi;

import java.awt.Rectangle;
import java.awt.Image;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Vector;

public class AScrollTextDisplayer
{
    protected int m_previousLineY;
    protected int m_currentLineHeight;
    protected int m_currentLineX;
    protected int m_width;
    protected int m_currentLineAlignment;
    protected Vector graphicalObjectsVector;
    protected Vector currentLineObjectsVector;
    protected Vector linkObjectsVector;
    public Color backColor;
    protected Graphics m_dummyGraphics;
    protected int m_deltaBetweenLines;
    protected Vector paragraphLines;
    
    public AScrollTextDisplayer(final int width) {
        this.m_previousLineY = 0;
        this.m_currentLineHeight = 0;
        this.m_currentLineX = 0;
        this.m_width = 0;
        this.m_currentLineAlignment = 0;
        this.graphicalObjectsVector = null;
        this.currentLineObjectsVector = null;
        this.linkObjectsVector = null;
        this.backColor = null;
        this.m_dummyGraphics = null;
        this.m_deltaBetweenLines = 4;
        this.paragraphLines = null;
        this.graphicalObjectsVector = new Vector(10);
        this.currentLineObjectsVector = new Vector(10);
        this.linkObjectsVector = new Vector(10);
        this.paragraphLines = new Vector(5);
        this.m_width = width;
    }
    
    public AScrollTextDisplayer(final Graphics dummyGraphics, final int width) {
        this.m_previousLineY = 0;
        this.m_currentLineHeight = 0;
        this.m_currentLineX = 0;
        this.m_width = 0;
        this.m_currentLineAlignment = 0;
        this.graphicalObjectsVector = null;
        this.currentLineObjectsVector = null;
        this.linkObjectsVector = null;
        this.backColor = null;
        this.m_dummyGraphics = null;
        this.m_deltaBetweenLines = 4;
        this.paragraphLines = null;
        this.graphicalObjectsVector = new Vector(10);
        this.currentLineObjectsVector = new Vector(10);
        this.linkObjectsVector = new Vector(10);
        this.paragraphLines = new Vector(5);
        this.m_width = width;
        this.m_dummyGraphics = dummyGraphics;
    }
    
    protected void addFormattedExactText(final String s, final Font font, final Color color, final int currentLineHeight, final String s2, final String s3, final int currentLineAlignment) {
        this.m_currentLineAlignment = currentLineAlignment;
        final ATextWithFont aTextWithFont = new ATextWithFont(s, font, color, s2, s3);
        final int stringWidth = this.m_dummyGraphics.getFontMetrics(font).stringWidth(s);
        aTextWithFont.boundingRectangle.x = this.m_currentLineX;
        if (currentLineHeight > this.m_currentLineHeight) {
            this.m_currentLineHeight = currentLineHeight;
            this.updateCurrentLine();
        }
        aTextWithFont.boundingRectangle.y = this.m_previousLineY;
        aTextWithFont.boundingRectangle.width = stringWidth;
        aTextWithFont.boundingRectangle.height = this.m_currentLineHeight;
        this.graphicalObjectsVector.addElement(aTextWithFont);
        this.currentLineObjectsVector.addElement(aTextWithFont);
        if (this.m_currentLineAlignment == 1) {
            this.updateCurrentLineAlignment();
        }
        if (s2 != null) {
            this.linkObjectsVector.addElement(aTextWithFont);
        }
    }
    
    public void addFormattedText(String substring, final Font font, final Color color, final String s, final String s2, final int n) {
        final FontMetrics fontMetrics = this.m_dummyGraphics.getFontMetrics(font);
        final int height = fontMetrics.getHeight();
        int stringWidth;
        while (true) {
            stringWidth = fontMetrics.stringWidth(substring);
            if (stringWidth + this.m_currentLineX <= this.m_width) {
                break;
            }
            int n2;
            int n3;
            for (n2 = this.m_width - this.m_currentLineX, n3 = 1; n3 <= substring.length() && fontMetrics.stringWidth(substring.substring(0, n3)) <= n2; ++n3) {}
            if (--n3 > 0) {
                int n4;
                for (n4 = n3; n4 >= 0 && substring.charAt(n4) != ' '; --n4) {}
                if (n4 > 0) {
                    final int n5 = n4 + 1;
                    this.addFormattedExactText(substring.substring(0, n5), font, color, height, s, s2, n);
                    substring = substring.substring(n5, substring.length());
                }
            }
            this.goToNextLine();
        }
        this.addFormattedExactText(substring, font, color, height, s, s2, n);
        this.m_currentLineX += stringWidth;
    }
    
    public void addImage(final Image image, final int width, final int n, final String s, final String s2, final int currentLineAlignment) {
        this.m_currentLineAlignment = currentLineAlignment;
        final AImage aImage = new AImage(image, width, n, s, s2);
        aImage.boundingRectangle.x = this.m_currentLineX;
        if (n > this.m_currentLineHeight) {
            this.m_currentLineHeight = n;
            this.updateCurrentLine();
        }
        aImage.boundingRectangle.y = this.m_previousLineY;
        aImage.boundingRectangle.width = width;
        aImage.boundingRectangle.height = n;
        this.graphicalObjectsVector.addElement(aImage);
        this.currentLineObjectsVector.addElement(aImage);
        this.m_currentLineX += width;
        if (this.m_currentLineAlignment == 1) {
            this.updateCurrentLineAlignment();
        }
        if (this.m_currentLineX > this.m_width) {
            this.goToNextLine();
        }
        if (s != null) {
            this.linkObjectsVector.addElement(aImage);
        }
    }
    
    public void addParagraphMark() {
        this.paragraphLines.addElement(new Integer(this.m_previousLineY));
    }
    
    public void drawAll(final Graphics graphics, final int n, final int n2) {
        if (this.backColor != null) {
            final Color color = graphics.getColor();
            graphics.setColor(this.backColor);
            graphics.fillRect(0, 0, n, n2);
            graphics.setColor(color);
        }
        for (int size = this.graphicalObjectsVector.size(), i = 0; i < size; ++i) {
            ((IGraphicObject)this.graphicalObjectsVector.elementAt(i)).drawGraphicObject(graphics);
        }
    }
    
    public String getClickedLink(final int n, final int n2) {
        for (int size = this.linkObjectsVector.size(), i = 0; i < size; ++i) {
            final IGraphicObject graphicObject = this.linkObjectsVector.elementAt(i);
            if (graphicObject.boundingRectangle.contains(n, n2)) {
                return ((ATextWithFont)graphicObject).getURL();
            }
        }
        return null;
    }
    
    public int getHeight() {
        return this.m_previousLineY + this.m_currentLineHeight;
    }
    
    public String getURL(final int n) {
        if (n >= 0 && n < this.linkObjectsVector.size()) {
            return this.linkObjectsVector.elementAt(n).getURL();
        }
        return null;
    }
    
    public String getURLTarget(final int n) {
        if (n >= 0 && n < this.linkObjectsVector.size()) {
            return this.linkObjectsVector.elementAt(n).linkTarget;
        }
        return null;
    }
    
    protected void goToNextLine() {
        if (this.m_currentLineHeight >= 0) {
            if (this.m_currentLineHeight == 0) {
                this.m_currentLineHeight = 10;
            }
            this.m_previousLineY += this.m_currentLineHeight + this.m_deltaBetweenLines;
            this.m_currentLineHeight = 0;
            this.m_currentLineX = 0;
            this.currentLineObjectsVector.removeAllElements();
        }
    }
    
    public boolean isParagraphStarted(final int n) {
        boolean b = false;
        for (int i = 0; i < this.paragraphLines.size(); ++i) {
            if ((int)this.paragraphLines.elementAt(i) == n) {
                b = true;
                break;
            }
        }
        return b;
    }
    
    protected void updateCurrentLine() {
        for (int size = this.currentLineObjectsVector.size(), i = 0; i < size; ++i) {
            ((IGraphicObject)this.currentLineObjectsVector.elementAt(i)).boundingRectangle.height = this.m_currentLineHeight;
        }
    }
    
    protected void updateCurrentLineAlignment() {
        if (this.m_currentLineAlignment == 0) {
            return;
        }
        final int size = this.currentLineObjectsVector.size();
        if (size == 0) {
            return;
        }
        final int x = this.currentLineObjectsVector.elementAt(0).boundingRectangle.x;
        final IGraphicObject graphicObject = this.currentLineObjectsVector.elementAt(size - 1);
        final int n = this.m_width / 2 - (graphicObject.boundingRectangle.x + graphicObject.boundingRectangle.width + x) / 2;
        for (int i = 0; i < size; ++i) {
            final Rectangle boundingRectangle = this.currentLineObjectsVector.elementAt(i).boundingRectangle;
            boundingRectangle.x += n;
        }
        this.m_currentLineX += n;
    }
    
    public int updateLinks(final Graphics graphics, final int n, final int n2, final int n3) {
        final int size = this.linkObjectsVector.size();
        int n4 = -1;
        IGraphicObject graphicObject = null;
        for (int i = 0; i < size; ++i) {
            graphicObject = (IGraphicObject)this.linkObjectsVector.elementAt(i);
            if (graphicObject.boundingRectangle.contains(n, n2)) {
                n4 = i;
                break;
            }
        }
        if (n3 != n4) {
            if (n4 != -1) {
                graphicObject.drawGraphicObject(graphics, n, n2);
            }
            if (n3 >= 0 && n3 < size) {
                this.linkObjectsVector.elementAt(n3).drawGraphicObject(graphics, n, n2);
            }
        }
        return n4;
    }
}
