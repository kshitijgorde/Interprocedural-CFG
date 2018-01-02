// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.jdisplay;

import java.awt.Font;
import com.mindprod.jtokens.NL;
import java.util.Arrays;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Graphics;
import com.mindprod.jtokens.Token;
import javax.swing.JPanel;

public class ColorTokenPanel extends JPanel
{
    private static final boolean DEBUGGING = false;
    private Token[] tokens;
    private boolean accelerated;
    private boolean hasLineNumbers;
    private int[] baselines;
    private int[] firstLineNumbersInBand;
    private int[] firstTokensInBand;
    private int bandCount;
    private int lineNumberWidth;
    private int startAtBaseline;
    private int startAtLineNumber;
    private int totalLines;
    
    public ColorTokenPanel() {
        this.accelerated = false;
        this.setOpaque(true);
    }
    
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        this.render(g2d);
    }
    
    public void set(final int width, final int height, final boolean hasLineNumbers, final int lineNumberWidth) {
        final Dimension dimension = new Dimension(width, height);
        this.setMinimumSize(dimension);
        this.setPreferredSize(dimension);
        this.setMaximumSize(dimension);
        this.setSize(dimension);
        this.hasLineNumbers = hasLineNumbers;
        this.lineNumberWidth = lineNumberWidth;
        this.accelerated = false;
        this.invalidate();
    }
    
    public void setTokens(final Token[] tokens, final int totalLines) {
        this.tokens = tokens;
        this.totalLines = totalLines;
        this.accelerated = false;
    }
    
    private int firstTokenNeedToRender(final Rectangle r) {
        final int topOfBand = r.y;
        final int firstBaseline = topOfBand - 23;
        int band = Arrays.binarySearch(this.baselines, firstBaseline);
        if (band < 0) {
            final int insert = -band - 1;
            band = insert - 1;
            band = Math.min(Math.max(0, band), this.bandCount - 1);
        }
        this.startAtBaseline = this.baselines[band];
        this.startAtLineNumber = this.firstLineNumbersInBand[band];
        return this.firstTokensInBand[band];
    }
    
    private int lastTokenNeedToRender(final Rectangle r) {
        final int bottomOfBand = r.y + r.height;
        final int lastBaseline = bottomOfBand + 23;
        int band = Arrays.binarySearch(this.baselines, lastBaseline);
        if (band < 0) {
            band = Math.min(Math.max(0, -band - 1), this.bandCount - 1);
        }
        if (band == this.bandCount - 1) {
            return this.tokens.length - 1;
        }
        return this.firstTokensInBand[band + 1] - 1;
    }
    
    private void lineRenderedAt(final int baseline, final int lineNumber, final int tokenIndex) {
        this.baselines[this.bandCount] = baseline;
        this.firstTokensInBand[this.bandCount] = tokenIndex;
        this.firstLineNumbersInBand[this.bandCount] = lineNumber;
        ++this.bandCount;
    }
    
    private void prepareAccelerator1() {
        if (this.tokens == null || this.tokens.length == 0) {
            return;
        }
        this.bandCount = 0;
        this.baselines = new int[this.totalLines];
        this.firstTokensInBand = new int[this.totalLines];
        this.firstLineNumbersInBand = new int[this.totalLines];
        for (int i = 0; i < this.totalLines; ++i) {
            this.baselines[i] = -10;
            this.firstTokensInBand[i] = -20;
            this.firstLineNumbersInBand[i] = -30;
        }
    }
    
    private void prepareAccelerator2() {
        int[] old = this.baselines;
        System.arraycopy(old, 0, this.baselines = new int[this.bandCount], 0, this.bandCount);
        old = this.firstTokensInBand;
        System.arraycopy(old, 0, this.firstTokensInBand = new int[this.bandCount], 0, this.bandCount);
        old = this.firstLineNumbersInBand;
        System.arraycopy(old, 0, this.firstLineNumbersInBand = new int[this.bandCount], 0, this.bandCount);
    }
    
    private void render(final Graphics2D g) {
        final Rectangle r = g.getClipBounds();
        if (this.tokens == null || this.tokens.length == 0) {
            return;
        }
        int firstTokenToRender;
        int lastTokenToRender;
        int x;
        int y;
        int lineNumber;
        boolean firstTokenOnLine;
        if (this.accelerated) {
            firstTokenToRender = this.firstTokenNeedToRender(r);
            lastTokenToRender = this.lastTokenNeedToRender(r);
            x = 5;
            y = this.startAtBaseline;
            lineNumber = this.startAtLineNumber;
            firstTokenOnLine = true;
        }
        else {
            this.prepareAccelerator1();
            firstTokenToRender = 0;
            lastTokenToRender = this.tokens.length - 1;
            x = 5;
            y = 23;
            lineNumber = 1;
            firstTokenOnLine = true;
        }
        for (int i = firstTokenToRender; i <= lastTokenToRender; ++i) {
            final Token t = this.tokens[i];
            if (!this.accelerated && firstTokenOnLine) {
                this.lineRenderedAt(y, lineNumber, i);
            }
            if (t instanceof NL) {
                final int lines = ((NL)t).getCount();
                switch (lines) {
                    case 1: {
                        y += 23;
                        break;
                    }
                    case 2: {
                        y += 33;
                        break;
                    }
                    default: {
                        y += 43;
                        break;
                    }
                }
                lineNumber += lines;
                x = 5;
                firstTokenOnLine = true;
            }
            else {
                if (this.hasLineNumbers && firstTokenOnLine) {
                    g.setColor(Token.getLineNumberForeground());
                    g.setFont(Token.getLineNumberFont());
                    final String digits = Integer.toString(lineNumber);
                    final int width = g.getFontMetrics().stringWidth(digits);
                    g.drawString(digits, x + this.lineNumberWidth - width, y);
                    x += this.lineNumberWidth + 3;
                }
                g.setColor(t.getForeground());
                final Font font = t.getFont();
                g.setFont(font);
                final String text = t.getText();
                g.drawString(text, x, y);
                x += g.getFontMetrics().stringWidth(text);
                firstTokenOnLine = false;
            }
        }
        if (!this.accelerated) {
            this.prepareAccelerator2();
            this.accelerated = true;
        }
    }
}
