import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Rectangle;
import java.awt.font.TextLayout;
import java.util.Vector;
import java.awt.font.FontRenderContext;
import java.awt.Font;
import java.awt.image.BufferedImage;

// 
// Decompiled by Procyon v0.5.30
// 

class Bubble extends StretchyBox
{
    boolean pointLeft;
    BufferedImage leftPointer;
    BufferedImage rightPointer;
    int fontSize;
    Font font;
    FontRenderContext renderContext;
    int wrapWidth;
    String contents;
    String[] lines;
    int[] xOffsets;
    
    Bubble() {
        this.pointLeft = false;
        this.fontSize = 13;
        this.font = new Font("Arial Unicode MS", 1, this.fontSize);
        this.wrapWidth = 135;
        this.renderContext = Skin.bubbleFrame.createGraphics().getFontRenderContext();
        this.setFrameImage(Skin.bubbleFrame);
        this.beThinkBubble(false);
    }
    
    void beThinkBubble(final boolean b) {
        if (b) {
            this.leftPointer = Skin.thinkPointerL;
            this.rightPointer = Skin.thinkPointerR;
        }
        else {
            this.leftPointer = Skin.talkPointerL;
            this.rightPointer = Skin.talkPointerR;
        }
    }
    
    void beAskBubble() {
        this.renderContext = Skin.askBubbleFrame.createGraphics().getFontRenderContext();
        this.setFrameImage(Skin.askBubbleFrame);
        this.leftPointer = Skin.askPointerL;
        this.rightPointer = Skin.askPointerR;
    }
    
    void setContents(final String contents) {
        this.contents = contents;
        final Vector vector = new Vector<String>();
        int lineEnd;
        for (int i = 0; i < contents.length(); i = lineEnd) {
            lineEnd = this.findLineEnd(contents, i);
            vector.addElement(contents.substring(i, lineEnd));
        }
        this.lines = new String[vector.size()];
        this.w = 65;
        for (int j = 0; j < this.lines.length; ++j) {
            this.lines[j] = vector.get(j);
            this.w = Math.max(this.w, this.widthOf(this.lines[j]) + 15);
        }
        this.xOffsets = new int[this.lines.length];
        for (int k = 0; k < this.lines.length; ++k) {
            this.xOffsets[k] = (this.w - this.widthOf(this.lines[k])) / 2;
        }
        this.h = this.lines.length * (this.fontSize + 2) + 19;
    }
    
    int findLineEnd(final String s, final int n) {
        int i;
        for (i = n + 1; i < s.length() && this.widthOf(s.substring(n, i + 1)) < this.wrapWidth; ++i) {}
        if (i == s.length()) {
            return i;
        }
        if (this.widthOf(s.substring(n, i + 1)) < this.wrapWidth) {
            return i + 1;
        }
        final int n2 = i + 1;
        while (i > n + 1) {
            if (i < s.length() && s.charAt(i) == ' ') {
                return i + 1;
            }
            --i;
        }
        return n2;
    }
    
    int widthOf(final String s) {
        if (s.length() == 0) {
            return 0;
        }
        return (int)new TextLayout(s, this.font, this.renderContext).getAdvance();
    }
    
    public Rectangle rect() {
        return new Rectangle(this.x, this.y, this.w, this.h + this.leftPointer.getHeight(null));
    }
    
    public void paint(final Graphics graphics) {
        super.paint(graphics);
        if (this.pointLeft) {
            graphics.drawImage(this.leftPointer, this.x + 7, this.y + this.h - 3, null);
        }
        else {
            graphics.drawImage(this.rightPointer, this.x - 9 + this.w - this.rightPointer.getWidth(null), this.y + this.h - 3, null);
        }
        graphics.setColor(new Color(0, 0, 0));
        graphics.setFont(this.font);
        int n = this.y + this.fontSize + 8;
        for (int i = 0; i < this.lines.length; ++i) {
            graphics.drawString(this.lines[i], this.x + this.xOffsets[i], n);
            n += this.fontSize + 2;
        }
    }
}
