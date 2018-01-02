// 
// Decompiled by Procyon v0.5.30
// 

package risco_table.graphics;

import java.awt.FontMetrics;
import java.awt.Graphics;

public class StringWrapFormatter extends StringFormatter
{
    protected char[] normalWrapCharacters;
    protected char[] persistentWrapCharacters;
    protected boolean wrap;
    protected static final int MAX_CLEAR_COUNTER = 1024;
    protected int clearCounter;
    protected int[] startIndexes;
    protected int[] pastIndexes;
    protected char[] charBuf;
    protected int lineCount;
    protected boolean normalWrap;
    
    public StringWrapFormatter() {
        this.normalWrapCharacters = new char[] { ' ', '\n', '_' };
        this.persistentWrapCharacters = new char[] { '-', '/' };
        this.wrap = true;
        this.clearCounter = 0;
    }
    
    public StringWrapFormatter(final int hAlignment, final int vAlignment) {
        super(hAlignment, vAlignment);
        this.normalWrapCharacters = new char[] { ' ', '\n', '_' };
        this.persistentWrapCharacters = new char[] { '-', '/' };
        this.wrap = true;
        this.clearCounter = 0;
    }
    
    public StringWrapFormatter(final int hAlignment, final int vAlignment, final int[] tabstops, final int decTabstop) {
        super(hAlignment, vAlignment, tabstops, decTabstop);
        this.normalWrapCharacters = new char[] { ' ', '\n', '_' };
        this.persistentWrapCharacters = new char[] { '-', '/' };
        this.wrap = true;
        this.clearCounter = 0;
    }
    
    public void formatString(final Graphics g, final FontMetrics m, final String s, final int x, final int y, final int width, final int height) {
        this.setUpBuffers(s);
        s.getChars(0, s.length(), this.charBuf, 0);
        this.wrap(m, this.charBuf, 0, s.length(), width);
        final int fontHeight = m.getHeight();
        final int blockHeight = this.lineCount * fontHeight;
        int maxWidth = 0;
        int curBaseline;
        if (super.vAlignment == 4) {
            curBaseline = (height + blockHeight) / 2 - blockHeight + fontHeight;
        }
        else if (super.vAlignment == 8) {
            curBaseline = height - blockHeight + fontHeight;
        }
        else {
            curBaseline = fontHeight;
        }
        curBaseline = y + curBaseline - m.getDescent();
        for (int i = 0; i < this.lineCount; ++i) {
            this.drawChars(g, m, this.charBuf, this.startIndexes[i], this.pastIndexes[i] - this.startIndexes[i], x, curBaseline, width);
            curBaseline += fontHeight;
            if (super.preferredWidth > maxWidth) {
                maxWidth = super.preferredWidth;
            }
        }
        super.preferredHeight = blockHeight;
        super.preferredWidth = maxWidth;
    }
    
    public char[] getNormalWrapCharacters() {
        return this.normalWrapCharacters;
    }
    
    public char[] getPersistentWrapCharacters() {
        return this.persistentWrapCharacters;
    }
    
    public boolean getWrap() {
        return this.wrap;
    }
    
    private boolean isNormalWrapCharacter(final char c) {
        for (int i = 0; i < this.normalWrapCharacters.length; ++i) {
            if (this.normalWrapCharacters[i] == c) {
                return true;
            }
        }
        return false;
    }
    
    private boolean isPersistentWrapCharacter(final char c) {
        for (int i = 0; i < this.persistentWrapCharacters.length; ++i) {
            if (this.persistentWrapCharacters[i] == c) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isWrappingFormatter() {
        return true;
    }
    
    private int nextStartIndex(final char[] chars, int start, final int past) {
        if (start < past && chars[start] == '\n') {
            return start + 1;
        }
        while (start < past) {
            if (!this.isNormalWrapCharacter(chars[start])) {
                break;
            }
            ++start;
        }
        while (start < past && this.isPersistentWrapCharacter(chars[start])) {
            ++start;
        }
        return start;
    }
    
    private int nextWrapIndex(final char[] chars, int start, final int past) {
        if (this.wrap) {
            while (start < past) {
                if (this.isPersistentWrapCharacter(chars[start])) {
                    this.normalWrap = false;
                    return start + 1;
                }
                if (this.isNormalWrapCharacter(chars[start])) {
                    this.normalWrap = true;
                    return start;
                }
                ++start;
            }
        }
        else {
            while (start < past) {
                if (chars[start] == '\n') {
                    return start;
                }
                ++start;
            }
        }
        return past;
    }
    
    private void setLineIndexes(final int line, final int start, final int past) {
        if (line >= this.startIndexes.length) {
            final int[] newStartIndexes = new int[2 * this.startIndexes.length];
            final int[] newPastIndexes = new int[2 * this.pastIndexes.length];
            System.arraycopy(this.startIndexes, 0, newStartIndexes, 0, this.startIndexes.length);
            System.arraycopy(this.pastIndexes, 0, newPastIndexes, 0, this.pastIndexes.length);
            this.startIndexes = newStartIndexes;
            this.pastIndexes = newPastIndexes;
        }
        this.startIndexes[line] = start;
        this.pastIndexes[line] = past;
    }
    
    public void setNormalWrapCharacters(final char[] chars) {
        this.normalWrapCharacters = chars;
    }
    
    public void setPersistentWrapCharacters(final char[] chars) {
        this.persistentWrapCharacters = chars;
    }
    
    protected final void setUpBuffers(final String s) {
        if (this.clearCounter++ % 1024 == 0) {
            this.startIndexes = new int[8];
            this.pastIndexes = new int[8];
            this.charBuf = new char[s.length()];
        }
        if (this.charBuf.length < s.length()) {
            this.charBuf = new char[s.length()];
        }
    }
    
    public void setWrapEnabled(final boolean wrap) {
        this.wrap = wrap;
    }
    
    protected final void wrap(final FontMetrics m, final char[] chars, final int start, final int past, final int width) {
        this.lineCount = 0;
        int curLineStart;
        int wordStart = curLineStart = this.nextStartIndex(chars, start, past);
        int curLinePast;
        int wordPast = curLinePast = this.nextWrapIndex(chars, wordStart, past);
        int curLineWidth = 0;
        int wordWidth = m.charsWidth(chars, wordStart, wordPast - wordStart);
        boolean forceBreak = false;
        while (true) {
            if (wordPast >= past || curLineWidth + wordWidth > width || forceBreak) {
                if (forceBreak || curLineWidth + wordWidth > width) {
                    forceBreak = false;
                    if (curLineWidth == 0) {
                        this.setLineIndexes(this.lineCount++, wordStart, wordPast);
                        if (wordPast >= past) {
                            return;
                        }
                        wordStart = (curLineStart = this.nextStartIndex(chars, wordPast, past));
                        wordPast = (curLinePast = this.nextWrapIndex(chars, wordStart, past));
                    }
                    else {
                        this.setLineIndexes(this.lineCount++, curLineStart, curLinePast);
                        curLineStart = wordStart;
                        curLinePast = wordPast;
                    }
                    curLineWidth = 0;
                }
                if (wordPast >= past) {
                    this.setLineIndexes(this.lineCount++, curLineStart, past);
                    return;
                }
                continue;
            }
            else {
                curLineWidth += wordWidth;
                if (this.normalWrap) {
                    curLineWidth += m.charWidth(chars[wordPast]);
                }
                curLinePast = wordPast;
                if (chars[wordPast] == '\n') {
                    forceBreak = true;
                }
                wordStart = this.nextStartIndex(chars, wordPast, past);
                wordPast = this.nextWrapIndex(chars, wordStart, past);
                wordWidth = m.charsWidth(chars, wordStart, wordPast - wordStart);
            }
        }
    }
}
