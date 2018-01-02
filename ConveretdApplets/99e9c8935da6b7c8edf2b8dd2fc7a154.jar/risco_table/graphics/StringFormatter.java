// 
// Decompiled by Procyon v0.5.30
// 

package risco_table.graphics;

import java.awt.Graphics;
import java.awt.FontMetrics;

public class StringFormatter
{
    public static final int ALIGNMENT_LEFT = 1;
    public static final int ALIGNMENT_LEFT_WITH_TABS = 2;
    public static final int ALIGNMENT_CENTER = 4;
    public static final int ALIGNMENT_RIGHT = 8;
    public static final int ALIGNMENT_RIGHT_DECIMAL = 16;
    public static final int ALIGNMENT_TOP = 2;
    public static final int ALIGNMENT_VCENTER = 4;
    public static final int ALIGNMENT_BOTTOM = 8;
    public static final int DEFAULT_NUM_DECIMALS = 2;
    protected int hAlignment;
    protected int vAlignment;
    private int[] tabstops;
    private boolean clip;
    private int decTabstop;
    private static char decimalDelimiter;
    private static char[] dotsString;
    protected int lineCount;
    protected boolean wasClipped;
    protected int preferredHeight;
    protected int preferredWidth;
    
    static {
        StringFormatter.decimalDelimiter = '.';
        StringFormatter.dotsString = new char[] { '.', '.' };
    }
    
    public StringFormatter() {
        this(1, 2, null, 2);
    }
    
    public StringFormatter(final int hAlignment, final int vAlignment) {
        this(hAlignment, vAlignment, null, 2);
    }
    
    public StringFormatter(final int hAlignment, final int vAlignment, final int[] tabstops, final int decTabstop) {
        this.hAlignment = 1;
        this.vAlignment = 2;
        this.tabstops = null;
        this.clip = true;
        this.decTabstop = 3;
        this.hAlignment = hAlignment;
        this.vAlignment = vAlignment;
        this.tabstops = tabstops;
        this.decTabstop = decTabstop;
    }
    
    public int calcBaselineOffset(final FontMetrics m, final int height) {
        switch (this.vAlignment) {
            case 8: {
                return height - m.getDescent();
            }
            case 4: {
                return (height + m.getHeight()) / 2 - m.getDescent();
            }
            default: {
                return m.getHeight() - m.getDescent();
            }
        }
    }
    
    private final void drawAligned(final Graphics g, final FontMetrics m, final char[] chars, final int start, final int len, final int x, final int y, final int width, final int w, final int hAlign) {
        if (len > 0) {
            switch (hAlign) {
                case 1: {
                    g.drawChars(chars, start, len, x, y);
                    break;
                }
                case 8: {
                    g.drawChars(chars, start, len, x + width - w, y);
                    break;
                }
                case 4: {
                    g.drawChars(chars, start, len, x + (width - w) / 2, y);
                    break;
                }
            }
        }
    }
    
    public final void drawChars(final Graphics g, final FontMetrics m, final char[] chars, final int start, final int len, final int x, final int y, final int width) {
        this.preferredHeight = m.getHeight();
        this.preferredWidth = 0;
        this.lineCount = 1;
        this.wasClipped = false;
        if (len == 0) {
            return;
        }
        if (this.hAlignment == 16) {
            this.drawCharsDecimal(g, m, chars, start, len, x, y, width);
        }
        else if (this.hAlignment == 2) {
            this.drawCharsTabbed(g, m, chars, start, len, x, y, width);
        }
        else {
            final int w = m.charsWidth(chars, start, len);
            if ((this.preferredWidth = w) <= width) {
                if (g != null) {
                    this.drawAligned(g, m, chars, start, len, x, y, width, w, this.hAlignment);
                }
            }
            else if (!this.clip) {
                this.wasClipped = true;
                if (g != null) {
                    this.drawAligned(g, m, chars, start, len, x, y, width, w, this.hAlignment);
                }
            }
            else {
                this.wasClipped = true;
                if (g != null) {
                    this.drawClipped(g, m, chars, start, len, x, y, width, w, this.hAlignment);
                }
            }
        }
    }
    
    private final void drawCharsDecimal(final Graphics g, final FontMetrics m, final char[] chars, final int start, final int len, final int x, final int y, final int width) {
        final int zeroWidth = m.charWidth('0');
        if (width < zeroWidth) {
            return;
        }
        int decWidth = this.decTabstop * zeroWidth + m.charWidth(StringFormatter.decimalDelimiter);
        decWidth = Math.min(decWidth, width - zeroWidth);
        final int magWidth = Math.max(zeroWidth, width - decWidth);
        int i;
        for (i = start + len - 1; i >= start && chars[i] != StringFormatter.decimalDelimiter; --i) {}
        int w;
        if (i < start) {
            w = m.charsWidth(chars, start, len);
            if (w <= magWidth) {
                if (g != null) {
                    this.drawAligned(g, m, chars, start, len, x, y, magWidth, w, 8);
                }
            }
            else {
                if (g != null) {
                    this.drawClippedLeft(g, m, chars, start, len, x, y, magWidth, w);
                }
                this.wasClipped = true;
            }
        }
        else {
            int l = i - start;
            w = m.charsWidth(chars, start, l);
            if (w <= magWidth) {
                if (g != null) {
                    this.drawAligned(g, m, chars, start, l, x, y, magWidth, w, 8);
                }
            }
            else {
                if (g != null) {
                    this.drawClippedLeft(g, m, chars, start, l, x, y, magWidth, w);
                }
                this.wasClipped = true;
            }
            l = len - i;
            w = m.charsWidth(chars, i, l);
            if (w <= decWidth) {
                if (g != null) {
                    this.drawAligned(g, m, chars, i, l, x + magWidth, y, decWidth, w, 1);
                }
            }
            else if (l > 2 || (l == 2 && chars[i + 1] != '0')) {
                if (g != null) {
                    this.drawClipped(g, m, chars, i, l, x + magWidth, y, decWidth, w, 1);
                }
                this.wasClipped = true;
            }
        }
        this.preferredWidth = w + decWidth;
    }
    
    private final void drawCharsTabbed(final Graphics g, final FontMetrics m, final char[] chars, final int start, final int len, final int x, final int y, final int width) {
        if (this.tabstops == null) {
            final int w = m.charsWidth(chars, start, len);
            if ((this.preferredWidth = w) <= width) {
                if (g != null && len > 0) {
                    g.drawChars(chars, start, len, x, y);
                }
            }
            else {
                this.wasClipped = true;
                if (g != null) {
                    this.drawClipped(g, m, chars, start, len, x, y, width, w, 1);
                }
            }
            return;
        }
        final int zero = m.charWidth('0');
        int tab = 0;
        int curStart = start;
        int curX = x;
        final int maxX = x + width;
        final int past = start + len;
        int i = this.nextTabIndex(chars, start, len);
        int curLen = i - curStart;
        while (true) {
            int curWidth;
            if (tab >= this.tabstops.length || i == past) {
                curWidth = 32767;
            }
            else {
                curWidth = this.tabstops[tab] * zero;
            }
            if (curX + curWidth > maxX) {
                curWidth = maxX - curX;
            }
            final int w = m.charsWidth(chars, curStart, curLen);
            if (w <= curWidth) {
                if (g != null && curLen > 0) {
                    g.drawChars(chars, curStart, curLen, curX, y);
                }
                if (i >= past) {
                    this.preferredWidth = curX + w - x;
                    return;
                }
            }
            else {
                if (g != null) {
                    this.drawClipped(g, m, chars, curStart, curLen, curX, y, curWidth, w, 1);
                }
                if (i >= past) {
                    this.wasClipped = true;
                    this.preferredWidth = curX + w - x;
                    return;
                }
            }
            ++tab;
            curX += curWidth;
            curStart += curLen + 1;
            i = this.nextTabIndex(chars, curStart, len - curStart);
            curLen = i - curStart;
        }
    }
    
    private final void drawClipped(final Graphics g, final FontMetrics m, final char[] chars, final int start, final int len, int x, final int y, final int width, int w, final int hAlign) {
        final int dotsWidth = m.charsWidth(StringFormatter.dotsString, 0, 2);
        int i;
        for (i = start + len - 1, w += dotsWidth; i >= start && w > width; w -= m.charWidth(chars[i--])) {}
        final int count = i - start + 1;
        switch (hAlign) {
            case 1: {
                if (count > 0) {
                    g.drawChars(chars, start, count, x, y);
                }
                g.drawChars(StringFormatter.dotsString, 0, 2, x + w - dotsWidth, y);
                break;
            }
            case 8: {
                if (count > 0) {
                    g.drawChars(chars, start, count, x + width - w, y);
                }
                x = Math.max(x + width - dotsWidth, x);
                g.drawChars(StringFormatter.dotsString, 0, 2, x, y);
                break;
            }
            case 4: {
                int left = x + (width - w) / 2;
                if (count > 0) {
                    g.drawChars(chars, start, count, left, y);
                }
                left = Math.max(left + w - dotsWidth, x);
                g.drawChars(StringFormatter.dotsString, 0, 2, left, y);
                break;
            }
        }
    }
    
    private final void drawClippedLeft(final Graphics g, final FontMetrics m, final char[] chars, final int start, final int len, final int x, final int y, final int width, int w) {
        final int dotsWidth = m.charsWidth(StringFormatter.dotsString, 0, 2);
        int i;
        int end;
        for (i = start, w += dotsWidth, end = start + len - 1; i <= end && w > width; w -= m.charWidth(chars[i++])) {}
        final int count = end - i + 1;
        g.drawChars(StringFormatter.dotsString, 0, 2, Math.max(x, x + width - w), y);
        if (count > 0) {
            g.drawChars(chars, i, count, x + width - w + dotsWidth, y);
        }
    }
    
    public void formatString(final Graphics g, final FontMetrics m, final String s, final int x, final int y, final int width, final int height) {
        this.drawChars(g, m, s.toCharArray(), 0, s.length(), x, y + this.calcBaselineOffset(m, height), width);
    }
    
    public final boolean getClip() {
        return this.clip;
    }
    
    public static char getDecimalDelimiter() {
        return StringFormatter.decimalDelimiter;
    }
    
    public final int getDecTabstop() {
        return this.decTabstop;
    }
    
    public final int getHAlignment() {
        return this.hAlignment;
    }
    
    public final int getLineCount() {
        return this.lineCount;
    }
    
    public final int getPreferredHeight() {
        return this.preferredHeight;
    }
    
    public final int getPreferredWidth() {
        return this.preferredWidth;
    }
    
    public final int[] getTabstops() {
        return this.tabstops;
    }
    
    public final int getVAlignment() {
        return this.vAlignment;
    }
    
    public boolean isWrappingFormatter() {
        return false;
    }
    
    public void measureString(final FontMetrics m, final String s, final int x, final int y, final int width, final int height) {
        this.formatString(null, m, s, x, y, width, height);
    }
    
    private int nextTabIndex(final char[] chars, int start, final int len) {
        for (int past = start + len; start < past && chars[start] != '\t'; ++start) {}
        return start;
    }
    
    public void setClipEnabled(final boolean clip) {
        this.clip = clip;
    }
    
    public static void setDecimalDelimiter(final char c) {
        StringFormatter.decimalDelimiter = c;
    }
    
    public final void setDecTabstop(final int decTabstop) {
        this.decTabstop = decTabstop;
    }
    
    public final void setHAlignment(final int hAlignment) {
        this.hAlignment = hAlignment;
    }
    
    public final void setTabstops(final int[] tabs) {
        this.tabstops = tabs;
    }
    
    public final void setVAlignment(final int vAlignment) {
        this.vAlignment = vAlignment;
    }
    
    public final boolean wasClipped() {
        return this.wasClipped;
    }
}
