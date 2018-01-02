// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.custom;

import org.eclipse.swt.graphics.Drawable;
import org.eclipse.swt.widgets.IME;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GlyphMetrics;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.TextLayout;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Device;

class StyledTextRenderer
{
    Device device;
    StyledText styledText;
    StyledTextContent content;
    Font regularFont;
    Font boldFont;
    Font italicFont;
    Font boldItalicFont;
    int tabWidth;
    int ascent;
    int descent;
    int averageCharWidth;
    int topIndex;
    TextLayout[] layouts;
    int lineCount;
    int[] lineWidth;
    int[] lineHeight;
    LineInfo[] lines;
    int maxWidth;
    int maxWidthLineIndex;
    boolean idleRunning;
    Bullet[] bullets;
    int[] bulletsIndices;
    int[] redrawLines;
    int[] ranges;
    int styleCount;
    StyleRange[] styles;
    StyleRange[] stylesSet;
    int stylesSetCount;
    boolean hasLinks;
    boolean fixedPitch;
    static final int BULLET_MARGIN = 8;
    static final boolean COMPACT_STYLES = true;
    static final boolean MERGE_STYLES = true;
    static final int GROW = 32;
    static final int IDLE_TIME = 50;
    static final int CACHE_SIZE = 128;
    static final int BACKGROUND = 1;
    static final int ALIGNMENT = 2;
    static final int INDENT = 4;
    static final int JUSTIFY = 8;
    static final int SEGMENTS = 32;
    static final int TABSTOPS = 64;
    static final int WRAP_INDENT = 128;
    static final int SEGMENT_CHARS = 256;
    
    StyledTextRenderer(final Device device, final StyledText styledText) {
        this.topIndex = -1;
        this.stylesSetCount = 0;
        this.device = device;
        this.styledText = styledText;
    }
    
    int addMerge(final int[] array, final StyleRange[] array2, int n, final int n2, int n3) {
        final int n4 = this.styleCount << 1;
        StyleRange styleRange = null;
        int n5 = 0;
        int n6 = 0;
        if (n3 < n4) {
            styleRange = this.styles[n3 >> 1];
            n5 = this.ranges[n3];
            n6 = this.ranges[n3 + 1];
        }
        final int n7 = n - (n3 - n2);
        if (n4 + n7 >= this.ranges.length) {
            final int[] ranges = new int[this.ranges.length + n7 + 64];
            System.arraycopy(this.ranges, 0, ranges, 0, n2);
            final StyleRange[] styles = new StyleRange[this.styles.length + (n7 >> 1) + 32];
            System.arraycopy(this.styles, 0, styles, 0, n2 >> 1);
            if (n4 > n3) {
                System.arraycopy(this.ranges, n3, ranges, n2 + n, n4 - n3);
                System.arraycopy(this.styles, n3 >> 1, styles, n2 + n >> 1, this.styleCount - (n3 >> 1));
            }
            this.ranges = ranges;
            this.styles = styles;
        }
        else if (n4 > n3) {
            System.arraycopy(this.ranges, n3, this.ranges, n2 + n, n4 - n3);
            System.arraycopy(this.styles, n3 >> 1, this.styles, n2 + n >> 1, this.styleCount - (n3 >> 1));
        }
        int n8 = n2;
        for (int i = 0; i < n; i += 2) {
            if (n8 > 0 && this.ranges[n8 - 2] + this.ranges[n8 - 1] == array[i] && array2[i >> 1].similarTo(this.styles[n8 - 2 >> 1])) {
                final int[] ranges2 = this.ranges;
                final int n9 = n8 - 1;
                ranges2[n9] += array[i + 1];
            }
            else {
                this.styles[n8 >> 1] = array2[i >> 1];
                this.ranges[n8++] = array[i];
                this.ranges[n8++] = array[i + 1];
            }
        }
        if (styleRange != null && this.ranges[n8 - 2] + this.ranges[n8 - 1] == n5 && styleRange.similarTo(this.styles[n8 - 2 >> 1])) {
            final int[] ranges3 = this.ranges;
            final int n10 = n8 - 1;
            ranges3[n10] += n6;
            n3 += 2;
            n += 2;
        }
        if (n4 > n3) {
            System.arraycopy(this.ranges, n2 + n, this.ranges, n8, n4 - n3);
            System.arraycopy(this.styles, n2 + n >> 1, this.styles, n8 >> 1, this.styleCount - (n3 >> 1));
        }
        final int n11 = n8 - n2 - (n3 - n2);
        this.styleCount += n11 >> 1;
        return n11;
    }
    
    int addMerge(final StyleRange[] array, int n, final int n2, int n3) {
        final int n4 = n - (n3 - n2);
        StyleRange styleRange = null;
        if (n3 < this.styleCount) {
            styleRange = this.styles[n3];
        }
        if (this.styleCount + n4 >= this.styles.length) {
            final StyleRange[] styles = new StyleRange[this.styles.length + n4 + 32];
            System.arraycopy(this.styles, 0, styles, 0, n2);
            if (this.styleCount > n3) {
                System.arraycopy(this.styles, n3, styles, n2 + n, this.styleCount - n3);
            }
            this.styles = styles;
        }
        else if (this.styleCount > n3) {
            System.arraycopy(this.styles, n3, this.styles, n2 + n, this.styleCount - n3);
        }
        int n5 = n2;
        for (final StyleRange styleRange2 : array) {
            final StyleRange styleRange3;
            if (n5 > 0 && (styleRange3 = this.styles[n5 - 1]).start + styleRange3.length == styleRange2.start && styleRange2.similarTo(styleRange3)) {
                final StyleRange styleRange4 = styleRange3;
                styleRange4.length += styleRange2.length;
            }
            else {
                this.styles[n5++] = styleRange2;
            }
        }
        final StyleRange styleRange5 = this.styles[n5 - 1];
        if (styleRange != null && styleRange5.start + styleRange5.length == styleRange.start && styleRange.similarTo(styleRange5)) {
            final StyleRange styleRange6 = styleRange5;
            styleRange6.length += styleRange.length;
            ++n3;
            ++n;
        }
        if (this.styleCount > n3) {
            System.arraycopy(this.styles, n2 + n, this.styles, n5, this.styleCount - n3);
        }
        final int n6 = n5 - n2 - (n3 - n2);
        this.styleCount += n6;
        return n6;
    }
    
    void calculate(final int n, final int n2) {
        final int n3 = n + n2;
        if (n < 0 || n3 > this.lineWidth.length) {
            return;
        }
        final int n4 = this.styledText.leftMargin + this.styledText.rightMargin + this.styledText.getCaretWidth();
        for (int i = n; i < n3; ++i) {
            if (this.lineWidth[i] == -1 || this.lineHeight[i] == -1) {
                final TextLayout textLayout = this.getTextLayout(i);
                final Rectangle bounds = textLayout.getBounds();
                this.lineWidth[i] = bounds.width + n4;
                this.lineHeight[i] = bounds.height;
                this.disposeTextLayout(textLayout);
            }
            if (this.lineWidth[i] > this.maxWidth) {
                this.maxWidth = this.lineWidth[i];
                this.maxWidthLineIndex = i;
            }
        }
    }
    
    void calculateClientArea() {
        for (int topIndex = this.styledText.getTopIndex(), lineCount = this.content.getLineCount(), height = this.styledText.getClientArea().height, n = 0; height > n && lineCount > topIndex; n += this.lineHeight[topIndex++]) {
            this.calculate(topIndex, 1);
        }
    }
    
    void calculateIdle() {
        if (this.idleRunning) {
            return;
        }
        this.styledText.getDisplay().asyncExec(new Runnable() {
            public void run() {
                if (StyledTextRenderer.this.styledText == null) {
                    return;
                }
                final long currentTimeMillis = System.currentTimeMillis();
                int i;
                for (i = 0; i < StyledTextRenderer.this.lineCount; ++i) {
                    if (StyledTextRenderer.this.lineHeight[i] == -1 || StyledTextRenderer.this.lineWidth[i] == -1) {
                        StyledTextRenderer.this.calculate(i, 1);
                        if (System.currentTimeMillis() - currentTimeMillis > 50L) {
                            break;
                        }
                    }
                }
                if (i < StyledTextRenderer.this.lineCount) {
                    StyledTextRenderer.this.styledText.getDisplay().asyncExec(this);
                }
                else {
                    StyledTextRenderer.this.idleRunning = false;
                    StyledTextRenderer.this.styledText.setScrollBars(true);
                    final ScrollBar verticalBar = StyledTextRenderer.this.styledText.getVerticalBar();
                    if (verticalBar != null) {
                        verticalBar.setSelection(StyledTextRenderer.this.styledText.getVerticalScrollOffset());
                    }
                }
            }
        });
        this.idleRunning = true;
    }
    
    void clearLineBackground(final int n, final int n2) {
        if (this.lines == null) {
            return;
        }
        for (int i = n; i < n + n2; ++i) {
            final LineInfo lineInfo = this.lines[i];
            if (lineInfo != null) {
                final LineInfo lineInfo2 = lineInfo;
                lineInfo2.flags &= 0xFFFFFFFE;
                lineInfo.background = null;
                if (lineInfo.flags == 0) {
                    this.lines[i] = null;
                }
            }
        }
    }
    
    void clearLineStyle(final int n, final int n2) {
        if (this.lines == null) {
            return;
        }
        for (int i = n; i < n + n2; ++i) {
            final LineInfo lineInfo = this.lines[i];
            if (lineInfo != null) {
                final LineInfo lineInfo2 = lineInfo;
                lineInfo2.flags &= 0xFFFFFF31;
                if (lineInfo.flags == 0) {
                    this.lines[i] = null;
                }
            }
        }
    }
    
    void copyInto(final StyledTextRenderer styledTextRenderer) {
        if (this.ranges != null) {
            final int[] ranges = new int[this.styleCount << 1];
            styledTextRenderer.ranges = ranges;
            final int[] array = ranges;
            System.arraycopy(this.ranges, 0, array, 0, array.length);
        }
        if (this.styles != null) {
            final StyleRange[] styles = new StyleRange[this.styleCount];
            styledTextRenderer.styles = styles;
            final StyleRange[] array2 = styles;
            for (int i = 0; i < array2.length; ++i) {
                array2[i] = (StyleRange)this.styles[i].clone();
            }
            styledTextRenderer.styleCount = this.styleCount;
        }
        if (this.lines != null) {
            final LineInfo[] lines = new LineInfo[this.lineCount];
            styledTextRenderer.lines = lines;
            final LineInfo[] array3 = lines;
            for (int j = 0; j < array3.length; ++j) {
                array3[j] = new LineInfo(this.lines[j]);
            }
            styledTextRenderer.lineCount = this.lineCount;
        }
    }
    
    void dispose() {
        if (this.boldFont != null) {
            this.boldFont.dispose();
        }
        if (this.italicFont != null) {
            this.italicFont.dispose();
        }
        if (this.boldItalicFont != null) {
            this.boldItalicFont.dispose();
        }
        final Font boldFont = null;
        this.boldItalicFont = boldFont;
        this.italicFont = boldFont;
        this.boldFont = boldFont;
        this.reset();
        this.content = null;
        this.device = null;
        this.styledText = null;
    }
    
    void disposeTextLayout(final TextLayout textLayout) {
        if (this.layouts != null) {
            for (int i = 0; i < this.layouts.length; ++i) {
                if (this.layouts[i] == textLayout) {
                    return;
                }
            }
        }
        textLayout.dispose();
    }
    
    void drawBullet(final Bullet bullet, final GC gc, final int n, final int n2, final int n3, final int ascent, final int descent) {
        final StyleRange style = bullet.style;
        final GlyphMetrics metrics = style.metrics;
        Color color = style.foreground;
        if (color != null) {
            gc.setForeground(color);
        }
        if ((bullet.type & 0x1) != 0x0 && StyledText.IS_MOTIF) {
            int max = Math.max(4, (ascent + descent) / 4);
            if ((max & 0x1) == 0x0) {
                ++max;
            }
            if (color == null) {
                color = this.styledText.getDisplay().getSystemColor(2);
            }
            gc.setBackground(color);
            gc.fillArc(n + Math.max(0, metrics.width - max - 8), n2 + max, max + 1, max + 1, 0, 360);
            return;
        }
        final Font font = style.font;
        if (font != null) {
            gc.setFont(font);
        }
        String text = "";
        switch (bullet.type & 0xF) {
            case 1: {
                text = "\u2022";
                break;
            }
            case 2: {
                text = String.valueOf(n3 + 1);
                break;
            }
            case 4: {
                text = String.valueOf((char)(n3 % 26 + 97));
                break;
            }
            case 8: {
                text = String.valueOf((char)(n3 % 26 + 65));
                break;
            }
        }
        if ((bullet.type & 0x10) != 0x0) {
            text = String.valueOf(text) + bullet.text;
        }
        final TextLayout textLayout = new TextLayout(this.styledText.getDisplay());
        textLayout.setText(text);
        textLayout.setAscent(ascent);
        textLayout.setDescent(descent);
        final StyleRange styleRange = (StyleRange)style.clone();
        styleRange.metrics = null;
        if (styleRange.font == null) {
            styleRange.font = this.getFont(styleRange.fontStyle);
        }
        textLayout.setStyle(styleRange, 0, text.length());
        textLayout.draw(gc, n + Math.max(0, metrics.width - textLayout.getBounds().width - 8), n2);
        textLayout.dispose();
    }
    
    int drawLine(final int n, final int n2, final int n3, final GC gc, final Color background, final Color foreground) {
        final TextLayout textLayout = this.getTextLayout(n);
        final String line = this.content.getLine(n);
        final int offsetAtLine = this.content.getOffsetAtLine(n);
        final int length = line.length();
        final Point selection = this.styledText.getSelection();
        int n4 = selection.x - offsetAtLine;
        int n5 = selection.y - offsetAtLine;
        if (this.styledText.getBlockSelection()) {
            n5 = (n4 = 0);
        }
        final Rectangle clientArea = this.styledText.getClientArea();
        Color background2 = this.getLineBackground(n, null);
        final StyledTextEvent lineBackgroundData = this.styledText.getLineBackgroundData(offsetAtLine, line);
        if (lineBackgroundData != null && lineBackgroundData.lineBackground != null) {
            background2 = lineBackgroundData.lineBackground;
        }
        final int height = textLayout.getBounds().height;
        if (background2 != null) {
            gc.setBackground(background2);
            gc.fillRectangle(clientArea.x, n3, clientArea.width, height);
        }
        else {
            gc.setBackground(background);
            this.styledText.drawBackground(gc, clientArea.x, n3, clientArea.width, height);
        }
        gc.setForeground(foreground);
        if (n4 == n5 || (n5 <= 0 && n4 > length - 1)) {
            textLayout.draw(gc, n2, n3);
        }
        else {
            final int max = Math.max(0, n4);
            final int min = Math.min(length, n5);
            final Color selectionForeground = this.styledText.getSelectionForeground();
            final Color selectionBackground = this.styledText.getSelectionBackground();
            int n6;
            if ((this.styledText.getStyle() & 0x10000) != 0x0) {
                n6 = 65536;
            }
            else {
                n6 = 131072;
            }
            if (n4 <= length && length < n5) {
                n6 |= 0x100000;
            }
            textLayout.draw(gc, n2, n3, max, min - 1, selectionForeground, selectionBackground, n6);
        }
        Bullet bullet = null;
        int index = -1;
        if (this.bullets != null) {
            if (this.bulletsIndices != null) {
                final int n7 = n - this.topIndex;
                if (n7 >= 0 && n7 < 128) {
                    bullet = this.bullets[n7];
                    index = this.bulletsIndices[n7];
                }
            }
            else {
                for (int i = 0; i < this.bullets.length; ++i) {
                    bullet = this.bullets[i];
                    index = bullet.indexOf(n);
                    if (index != -1) {
                        break;
                    }
                }
            }
        }
        if (index != -1 && bullet != null) {
            final FontMetrics lineMetrics = textLayout.getLineMetrics(0);
            final int n8 = lineMetrics.getAscent() + lineMetrics.getLeading();
            if (bullet.type == 32) {
                bullet.style.start = offsetAtLine;
                this.styledText.paintObject(gc, n2, n3, n8, lineMetrics.getDescent(), bullet.style, bullet, index);
            }
            else {
                this.drawBullet(bullet, gc, n2, n3, index, n8, lineMetrics.getDescent());
            }
        }
        final TextStyle[] styles = textLayout.getStyles();
        int[] ranges = null;
        for (int j = 0; j < styles.length; ++j) {
            if (styles[j].metrics != null) {
                if (ranges == null) {
                    ranges = textLayout.getRanges();
                }
                final int n9 = ranges[j << 1];
                final int length2 = ranges[(j << 1) + 1] - n9 + 1;
                final Point location = textLayout.getLocation(n9, false);
                final FontMetrics lineMetrics2 = textLayout.getLineMetrics(textLayout.getLineIndex(n9));
                final StyleRange styleRange = (StyleRange)((StyleRange)styles[j]).clone();
                styleRange.start = n9 + offsetAtLine;
                styleRange.length = length2;
                this.styledText.paintObject(gc, location.x + n2, location.y + n3, lineMetrics2.getAscent() + lineMetrics2.getLeading(), lineMetrics2.getDescent(), styleRange, null, 0);
            }
        }
        this.disposeTextLayout(textLayout);
        return height;
    }
    
    int getBaseline() {
        return this.ascent;
    }
    
    Font getFont(final int n) {
        switch (n) {
            case 1: {
                if (this.boldFont != null) {
                    return this.boldFont;
                }
                return this.boldFont = new Font(this.device, this.getFontData(n));
            }
            case 2: {
                if (this.italicFont != null) {
                    return this.italicFont;
                }
                return this.italicFont = new Font(this.device, this.getFontData(n));
            }
            case 3: {
                if (this.boldItalicFont != null) {
                    return this.boldItalicFont;
                }
                return this.boldItalicFont = new Font(this.device, this.getFontData(n));
            }
            default: {
                return this.regularFont;
            }
        }
    }
    
    FontData[] getFontData(final int style) {
        final FontData[] fontData = this.regularFont.getFontData();
        for (int i = 0; i < fontData.length; ++i) {
            fontData[i].setStyle(style);
        }
        return fontData;
    }
    
    int getHeight() {
        final int lineHeight = this.getLineHeight();
        if (this.styledText.isFixedLineHeight()) {
            return this.lineCount * lineHeight + this.styledText.topMargin + this.styledText.bottomMargin;
        }
        int n = 0;
        final int wrapWidth = this.styledText.getWrapWidth();
        for (int i = 0; i < this.lineCount; ++i) {
            int n2 = this.lineHeight[i];
            if (n2 == -1) {
                if (wrapWidth > 0) {
                    n2 = (this.content.getLine(i).length() * this.averageCharWidth / wrapWidth + 1) * lineHeight;
                }
                else {
                    n2 = lineHeight;
                }
            }
            n += n2;
        }
        return n + this.styledText.topMargin + this.styledText.bottomMargin;
    }
    
    boolean hasLink(final int n) {
        if (n == -1) {
            return false;
        }
        final int lineAtOffset = this.content.getLineAtOffset(n);
        final StyledTextEvent lineStyleData = this.styledText.getLineStyleData(this.content.getOffsetAtLine(lineAtOffset), this.content.getLine(lineAtOffset));
        if (lineStyleData != null) {
            final StyleRange[] styles = lineStyleData.styles;
            if (styles != null) {
                final int[] ranges = lineStyleData.ranges;
                if (ranges != null) {
                    for (int i = 0; i < ranges.length; i += 2) {
                        if (ranges[i] <= n && n < ranges[i] + ranges[i + 1] && styles[i >> 1].underline && styles[i >> 1].underlineStyle == 4) {
                            return true;
                        }
                    }
                }
                else {
                    for (int j = 0; j < styles.length; ++j) {
                        final StyleRange styleRange = styles[j];
                        if (styleRange.start <= n && n < styleRange.start + styleRange.length && styleRange.underline && styleRange.underlineStyle == 4) {
                            return true;
                        }
                    }
                }
            }
        }
        else if (this.ranges != null) {
            final int n2 = this.styleCount << 1;
            final int rangeIndex = this.getRangeIndex(n, -1, n2);
            if (rangeIndex >= n2) {
                return false;
            }
            final int n3 = this.ranges[rangeIndex];
            final int n4 = this.ranges[rangeIndex + 1];
            final StyleRange styleRange2 = this.styles[rangeIndex >> 1];
            if (n3 <= n && n < n3 + n4 && styleRange2.underline && styleRange2.underlineStyle == 4) {
                return true;
            }
        }
        return false;
    }
    
    int getLineAlignment(final int n, final int n2) {
        if (this.lines == null) {
            return n2;
        }
        final LineInfo lineInfo = this.lines[n];
        if (lineInfo != null && (lineInfo.flags & 0x2) != 0x0) {
            return lineInfo.alignment;
        }
        return n2;
    }
    
    Color getLineBackground(final int n, final Color color) {
        if (this.lines == null) {
            return color;
        }
        final LineInfo lineInfo = this.lines[n];
        if (lineInfo != null && (lineInfo.flags & 0x1) != 0x0) {
            return lineInfo.background;
        }
        return color;
    }
    
    Bullet getLineBullet(final int n, final Bullet bullet) {
        if (this.bullets == null) {
            return bullet;
        }
        if (this.bulletsIndices != null) {
            return bullet;
        }
        for (int i = 0; i < this.bullets.length; ++i) {
            final Bullet bullet2 = this.bullets[i];
            if (bullet2.indexOf(n) != -1) {
                return bullet2;
            }
        }
        return bullet;
    }
    
    int getLineHeight() {
        return this.ascent + this.descent;
    }
    
    int getLineHeight(final int n) {
        if (this.lineHeight[n] == -1) {
            this.calculate(n, 1);
        }
        return this.lineHeight[n];
    }
    
    int getLineIndent(final int n, final int n2) {
        if (this.lines == null) {
            return n2;
        }
        final LineInfo lineInfo = this.lines[n];
        if (lineInfo != null && (lineInfo.flags & 0x4) != 0x0) {
            return lineInfo.indent;
        }
        return n2;
    }
    
    int getLineWrapIndent(final int n, final int n2) {
        if (this.lines == null) {
            return n2;
        }
        final LineInfo lineInfo = this.lines[n];
        if (lineInfo != null && (lineInfo.flags & 0x80) != 0x0) {
            return lineInfo.wrapIndent;
        }
        return n2;
    }
    
    boolean getLineJustify(final int n, final boolean b) {
        if (this.lines == null) {
            return b;
        }
        final LineInfo lineInfo = this.lines[n];
        if (lineInfo != null && (lineInfo.flags & 0x8) != 0x0) {
            return lineInfo.justify;
        }
        return b;
    }
    
    int[] getLineTabStops(final int n, final int[] array) {
        if (this.lines == null) {
            return array;
        }
        final LineInfo lineInfo = this.lines[n];
        if (lineInfo != null && (lineInfo.flags & 0x40) != 0x0) {
            return lineInfo.tabStops;
        }
        return array;
    }
    
    int getRangeIndex(final int n, int n2, int n3) {
        if (this.styleCount == 0) {
            return 0;
        }
        if (this.ranges != null) {
            while (n3 - n2 > 2) {
                final int n4 = (n3 + n2) / 2 / 2 * 2;
                if (this.ranges[n4] + this.ranges[n4 + 1] > n) {
                    n3 = n4;
                }
                else {
                    n2 = n4;
                }
            }
        }
        else {
            while (n3 - n2 > 1) {
                final int n5 = (n3 + n2) / 2;
                if (this.styles[n5].start + this.styles[n5].length > n) {
                    n3 = n5;
                }
                else {
                    n2 = n5;
                }
            }
        }
        return n3;
    }
    
    int[] getRanges(final int n, final int n2) {
        if (n2 == 0) {
            return null;
        }
        final int n3 = n + n2 - 1;
        int[] array;
        if (this.ranges != null) {
            final int n4 = this.styleCount << 1;
            final int rangeIndex = this.getRangeIndex(n, -1, n4);
            if (rangeIndex >= n4) {
                return null;
            }
            if (this.ranges[rangeIndex] > n3) {
                return null;
            }
            int n5 = Math.min(n4 - 2, this.getRangeIndex(n3, rangeIndex - 1, n4));
            if (this.ranges[n5] > n3) {
                n5 = Math.max(rangeIndex, n5 - 2);
            }
            array = new int[n5 - rangeIndex + 2];
            System.arraycopy(this.ranges, rangeIndex, array, 0, array.length);
        }
        else {
            final int rangeIndex2 = this.getRangeIndex(n, -1, this.styleCount);
            if (rangeIndex2 >= this.styleCount) {
                return null;
            }
            if (this.styles[rangeIndex2].start > n3) {
                return null;
            }
            int n6 = Math.min(this.styleCount - 1, this.getRangeIndex(n3, rangeIndex2 - 1, this.styleCount));
            if (this.styles[n6].start > n3) {
                n6 = Math.max(rangeIndex2, n6 - 1);
            }
            array = new int[n6 - rangeIndex2 + 1 << 1];
            for (int i = rangeIndex2, n7 = 0; i <= n6; ++i, n7 += 2) {
                final StyleRange styleRange = this.styles[i];
                array[n7] = styleRange.start;
                array[n7 + 1] = styleRange.length;
            }
        }
        if (n > array[0]) {
            array[1] = array[0] + array[1] - n;
            array[0] = n;
        }
        if (n3 < array[array.length - 2] + array[array.length - 1] - 1) {
            array[array.length - 1] = n3 - array[array.length - 2] + 1;
        }
        return array;
    }
    
    StyleRange[] getStyleRanges(final int start, final int n, final boolean b) {
        if (n == 0) {
            return null;
        }
        final int n2 = start + n - 1;
        StyleRange[] array;
        if (this.ranges != null) {
            final int n3 = this.styleCount << 1;
            final int rangeIndex = this.getRangeIndex(start, -1, n3);
            if (rangeIndex >= n3) {
                return null;
            }
            if (this.ranges[rangeIndex] > n2) {
                return null;
            }
            int n4 = Math.min(n3 - 2, this.getRangeIndex(n2, rangeIndex - 1, n3));
            if (this.ranges[n4] > n2) {
                n4 = Math.max(rangeIndex, n4 - 2);
            }
            array = new StyleRange[(n4 - rangeIndex >> 1) + 1];
            if (b) {
                for (int i = rangeIndex, n5 = 0; i <= n4; i += 2, ++n5) {
                    array[n5] = (StyleRange)this.styles[i >> 1].clone();
                    array[n5].start = this.ranges[i];
                    array[n5].length = this.ranges[i + 1];
                }
            }
            else {
                System.arraycopy(this.styles, rangeIndex >> 1, array, 0, array.length);
            }
        }
        else {
            final int rangeIndex2 = this.getRangeIndex(start, -1, this.styleCount);
            if (rangeIndex2 >= this.styleCount) {
                return null;
            }
            if (this.styles[rangeIndex2].start > n2) {
                return null;
            }
            int n6 = Math.min(this.styleCount - 1, this.getRangeIndex(n2, rangeIndex2 - 1, this.styleCount));
            if (this.styles[n6].start > n2) {
                n6 = Math.max(rangeIndex2, n6 - 1);
            }
            array = new StyleRange[n6 - rangeIndex2 + 1];
            System.arraycopy(this.styles, rangeIndex2, array, 0, array.length);
        }
        if (b || this.ranges == null) {
            final StyleRange styleRange = array[0];
            if (start > styleRange.start) {
                final StyleRange styleRange2 = array[0] = (StyleRange)styleRange.clone();
                styleRange2.length = styleRange2.start + styleRange2.length - start;
                styleRange2.start = start;
            }
            final StyleRange styleRange3 = array[array.length - 1];
            if (n2 < styleRange3.start + styleRange3.length - 1) {
                final StyleRange styleRange4 = array[array.length - 1] = (StyleRange)styleRange3.clone();
                styleRange4.length = n2 - styleRange4.start + 1;
            }
        }
        return array;
    }
    
    StyleRange getStyleRange(final StyleRange styleRange) {
        if (styleRange.underline && styleRange.underlineStyle == 4) {
            this.hasLinks = true;
        }
        if (styleRange.start == 0 && styleRange.length == 0 && styleRange.fontStyle == 0) {
            return styleRange;
        }
        final StyleRange styleRange4;
        final StyleRange styleRange3;
        final StyleRange styleRange2 = styleRange3 = (styleRange4 = (StyleRange)styleRange.clone());
        final boolean b = false;
        styleRange3.length = (b ? 1 : 0);
        styleRange4.start = (b ? 1 : 0);
        styleRange2.fontStyle = 0;
        if (styleRange2.font == null) {
            styleRange2.font = this.getFont(styleRange.fontStyle);
        }
        return styleRange2;
    }
    
    TextLayout getTextLayout(final int n) {
        return this.getTextLayout(n, this.styledText.getOrientation(), this.styledText.getWrapWidth(), this.styledText.lineSpacing);
    }
    
    TextLayout getTextLayout(final int n, final int orientation, final int width, final int spacing) {
        TextLayout textLayout = null;
        if (this.styledText != null) {
            final int topIndex = (this.styledText.topIndex > 0) ? (this.styledText.topIndex - 1) : 0;
            if (this.layouts == null || topIndex != this.topIndex) {
                final TextLayout[] layouts = new TextLayout[128];
                if (this.layouts != null) {
                    for (int i = 0; i < this.layouts.length; ++i) {
                        if (this.layouts[i] != null) {
                            final int n2 = i + this.topIndex - topIndex;
                            if (n2 >= 0 && n2 < layouts.length) {
                                layouts[n2] = this.layouts[i];
                            }
                            else {
                                this.layouts[i].dispose();
                            }
                        }
                    }
                }
                if (this.bullets != null && this.bulletsIndices != null && topIndex != this.topIndex) {
                    final int n3 = topIndex - this.topIndex;
                    if (n3 > 0) {
                        if (n3 < this.bullets.length) {
                            System.arraycopy(this.bullets, n3, this.bullets, 0, this.bullets.length - n3);
                            System.arraycopy(this.bulletsIndices, n3, this.bulletsIndices, 0, this.bulletsIndices.length - n3);
                        }
                        for (int j = Math.max(0, this.bullets.length - n3); j < this.bullets.length; ++j) {
                            this.bullets[j] = null;
                        }
                    }
                    else {
                        if (-n3 < this.bullets.length) {
                            System.arraycopy(this.bullets, 0, this.bullets, -n3, this.bullets.length + n3);
                            System.arraycopy(this.bulletsIndices, 0, this.bulletsIndices, -n3, this.bulletsIndices.length + n3);
                        }
                        for (int min = Math.min(this.bullets.length, -n3), k = 0; k < min; ++k) {
                            this.bullets[k] = null;
                        }
                    }
                }
                this.topIndex = topIndex;
                this.layouts = layouts;
            }
            if (this.layouts != null) {
                final int n4 = n - topIndex;
                if (n4 >= 0 && n4 < this.layouts.length) {
                    textLayout = this.layouts[n4];
                    if (textLayout != null) {
                        if (this.lineWidth[n] != -1) {
                            return textLayout;
                        }
                    }
                    else {
                        final TextLayout[] layouts2 = this.layouts;
                        final int n5 = n4;
                        final TextLayout textLayout2 = new TextLayout(this.device);
                        layouts2[n5] = textLayout2;
                        textLayout = textLayout2;
                    }
                }
            }
        }
        if (textLayout == null) {
            textLayout = new TextLayout(this.device);
        }
        final String line = this.content.getLine(n);
        final int offsetAtLine = this.content.getOffsetAtLine(n);
        int[] segments = null;
        char[] segmentsChars = null;
        int indent = 0;
        int wrapIndent = 0;
        int alignment = 16384;
        boolean justify = false;
        int[] tabs = { this.tabWidth };
        Bullet bullet = null;
        final int[] array = null;
        final StyleRange[] array2 = null;
        int n6 = 0;
        int n7 = 0;
        StyledTextEvent lineStyleData = null;
        if (this.styledText != null) {
            final StyledTextEvent bidiSegments = this.styledText.getBidiSegments(offsetAtLine, line);
            if (bidiSegments != null) {
                segments = bidiSegments.segments;
                segmentsChars = bidiSegments.segmentsChars;
            }
            lineStyleData = this.styledText.getLineStyleData(offsetAtLine, line);
            indent = this.styledText.indent;
            wrapIndent = this.styledText.wrapIndent;
            alignment = this.styledText.alignment;
            justify = this.styledText.justify;
            if (this.styledText.tabs != null) {
                tabs = this.styledText.tabs;
            }
        }
        int[] array3;
        StyleRange[] array4;
        if (lineStyleData != null) {
            indent = lineStyleData.indent;
            wrapIndent = lineStyleData.wrapIndent;
            alignment = lineStyleData.alignment;
            justify = lineStyleData.justify;
            bullet = lineStyleData.bullet;
            array3 = lineStyleData.ranges;
            array4 = lineStyleData.styles;
            if (lineStyleData.tabStops != null) {
                tabs = lineStyleData.tabStops;
            }
            if (array4 != null) {
                n7 = array4.length;
                if (this.styledText.isFixedLineHeight()) {
                    for (int l = 0; l < n7; ++l) {
                        if (array4[l].isVariableHeight()) {
                            this.styledText.verticalScrollOffset = -1;
                            this.styledText.setVariableLineHeight();
                            this.styledText.redraw();
                            break;
                        }
                    }
                }
            }
            if (this.bullets == null || this.bulletsIndices == null) {
                this.bullets = new Bullet[128];
                this.bulletsIndices = new int[128];
            }
            final int n8 = n - this.topIndex;
            if (n8 >= 0 && n8 < 128) {
                this.bullets[n8] = bullet;
                this.bulletsIndices[n8] = lineStyleData.bulletIndex;
            }
        }
        else {
            if (this.lines != null) {
                final LineInfo lineInfo = this.lines[n];
                if (lineInfo != null) {
                    if ((lineInfo.flags & 0x4) != 0x0) {
                        indent = lineInfo.indent;
                    }
                    if ((lineInfo.flags & 0x80) != 0x0) {
                        wrapIndent = lineInfo.wrapIndent;
                    }
                    if ((lineInfo.flags & 0x2) != 0x0) {
                        alignment = lineInfo.alignment;
                    }
                    if ((lineInfo.flags & 0x8) != 0x0) {
                        justify = lineInfo.justify;
                    }
                    if ((lineInfo.flags & 0x20) != 0x0) {
                        segments = lineInfo.segments;
                    }
                    if ((lineInfo.flags & 0x100) != 0x0) {
                        segmentsChars = lineInfo.segmentsChars;
                    }
                    if ((lineInfo.flags & 0x40) != 0x0) {
                        tabs = lineInfo.tabStops;
                    }
                }
            }
            if (this.bulletsIndices != null) {
                this.bullets = null;
                this.bulletsIndices = null;
            }
            if (this.bullets != null) {
                for (int n9 = 0; n9 < this.bullets.length; ++n9) {
                    if (this.bullets[n9].indexOf(n) != -1) {
                        bullet = this.bullets[n9];
                        break;
                    }
                }
            }
            array3 = this.ranges;
            array4 = this.styles;
            n7 = this.styleCount;
            if (array3 != null) {
                n6 = this.getRangeIndex(offsetAtLine, -1, n7 << 1);
            }
            else {
                n6 = this.getRangeIndex(offsetAtLine, -1, n7);
            }
        }
        if (bullet != null) {
            indent += bullet.style.metrics.width;
        }
        textLayout.setFont(this.regularFont);
        textLayout.setAscent(this.ascent);
        textLayout.setDescent(this.descent);
        textLayout.setText(line);
        textLayout.setOrientation(orientation);
        textLayout.setSegments(segments);
        textLayout.setSegmentsChars(segmentsChars);
        textLayout.setWidth(width);
        textLayout.setSpacing(spacing);
        textLayout.setTabs(tabs);
        textLayout.setIndent(indent);
        textLayout.setWrapIndent(wrapIndent);
        textLayout.setAlignment(alignment);
        textLayout.setJustify(justify);
        int n10 = 0;
        final int length = line.length();
        if (array4 != null) {
            if (array3 != null) {
                for (int n11 = n7 << 1, n12 = n6; n12 < n11; n12 += 2) {
                    int n13;
                    int n14;
                    if (offsetAtLine > array3[n12]) {
                        n13 = 0;
                        n14 = Math.min(length, array3[n12 + 1] - offsetAtLine + array3[n12]);
                    }
                    else {
                        n13 = array3[n12] - offsetAtLine;
                        n14 = Math.min(length, n13 + array3[n12 + 1]);
                    }
                    if (n13 >= length) {
                        break;
                    }
                    if (n10 < n13) {
                        textLayout.setStyle(null, n10, n13 - 1);
                    }
                    textLayout.setStyle(this.getStyleRange(array4[n12 >> 1]), n13, n14);
                    n10 = Math.max(n10, n14);
                }
            }
            else {
                for (int n15 = n6; n15 < n7; ++n15) {
                    int n16;
                    int n17;
                    if (offsetAtLine > array4[n15].start) {
                        n16 = 0;
                        n17 = Math.min(length, array4[n15].length - offsetAtLine + array4[n15].start);
                    }
                    else {
                        n16 = array4[n15].start - offsetAtLine;
                        n17 = Math.min(length, n16 + array4[n15].length);
                    }
                    if (n16 >= length) {
                        break;
                    }
                    if (n10 < n16) {
                        textLayout.setStyle(null, n10, n16 - 1);
                    }
                    textLayout.setStyle(this.getStyleRange(array4[n15]), n16, n17);
                    n10 = Math.max(n10, n17);
                }
            }
        }
        if (n10 < length) {
            textLayout.setStyle(null, n10, length);
        }
        if (this.styledText != null && this.styledText.ime != null) {
            final IME ime = this.styledText.ime;
            final int compositionOffset = ime.getCompositionOffset();
            if (compositionOffset != -1) {
                final int commitCount = ime.getCommitCount();
                final int length2 = ime.getText().length();
                if (length2 != commitCount && this.content.getLineAtOffset(compositionOffset) == n) {
                    final int[] ranges = ime.getRanges();
                    final TextStyle[] styles = ime.getStyles();
                    if (ranges.length > 0) {
                        for (int n18 = 0; n18 < styles.length; ++n18) {
                            final int n19 = ranges[n18 * 2] - offsetAtLine;
                            final int n20 = ranges[n18 * 2 + 1] - offsetAtLine;
                            final TextStyle textStyle = styles[n18];
                            for (int n21 = n19; n21 <= n20; ++n21) {
                                TextStyle textStyle2 = textLayout.getStyle(n21);
                                if (textStyle2 == null && n21 > 0) {
                                    textStyle2 = textLayout.getStyle(n21 - 1);
                                }
                                if (textStyle2 == null && n21 + 1 < length) {
                                    textStyle2 = textLayout.getStyle(n21 + 1);
                                }
                                if (textStyle2 == null) {
                                    textLayout.setStyle(textStyle, n21, n21);
                                }
                                else {
                                    final TextStyle textStyle3 = new TextStyle(textStyle);
                                    if (textStyle3.font == null) {
                                        textStyle3.font = textStyle2.font;
                                    }
                                    if (textStyle3.foreground == null) {
                                        textStyle3.foreground = textStyle2.foreground;
                                    }
                                    if (textStyle3.background == null) {
                                        textStyle3.background = textStyle2.background;
                                    }
                                    textLayout.setStyle(textStyle3, n21, n21);
                                }
                            }
                        }
                    }
                    else {
                        final int n22 = compositionOffset - offsetAtLine;
                        final int n23 = n22 + length2 - 1;
                        TextStyle textStyle4 = textLayout.getStyle(n22);
                        if (textStyle4 == null) {
                            if (n22 > 0) {
                                textStyle4 = textLayout.getStyle(n22 - 1);
                            }
                            if (textStyle4 == null && n23 + 1 < length) {
                                textStyle4 = textLayout.getStyle(n23 + 1);
                            }
                            if (textStyle4 != null) {
                                final TextStyle textStyle5 = new TextStyle();
                                textStyle5.font = textStyle4.font;
                                textStyle5.foreground = textStyle4.foreground;
                                textStyle5.background = textStyle4.background;
                                textLayout.setStyle(textStyle5, n22, n23);
                            }
                        }
                    }
                }
            }
        }
        if (this.styledText != null && this.styledText.isFixedLineHeight()) {
            int n24 = -1;
            final int lineCount = textLayout.getLineCount();
            int lineHeight = this.getLineHeight();
            for (int n25 = 0; n25 < lineCount; ++n25) {
                final int height = textLayout.getLineBounds(n25).height;
                if (height > lineHeight) {
                    lineHeight = height;
                    n24 = n25;
                }
            }
            if (n24 != -1) {
                final FontMetrics lineMetrics = textLayout.getLineMetrics(n24);
                this.ascent = lineMetrics.getAscent() + lineMetrics.getLeading();
                this.descent = lineMetrics.getDescent();
                if (this.layouts != null) {
                    for (int n26 = 0; n26 < this.layouts.length; ++n26) {
                        if (this.layouts[n26] != null && this.layouts[n26] != textLayout) {
                            this.layouts[n26].setAscent(this.ascent);
                            this.layouts[n26].setDescent(this.descent);
                        }
                    }
                }
                if (this.styledText.verticalScrollOffset != 0) {
                    final int topIndex2 = this.styledText.topIndex;
                    final int topIndexY = this.styledText.topIndexY;
                    final int lineHeight2 = this.getLineHeight();
                    if (topIndexY >= 0) {
                        this.styledText.verticalScrollOffset = (topIndex2 - 1) * lineHeight2 + lineHeight2 - topIndexY;
                    }
                    else {
                        this.styledText.verticalScrollOffset = topIndex2 * lineHeight2 - topIndexY;
                    }
                }
                this.styledText.calculateScrollBars();
                if (this.styledText.isBidiCaret()) {
                    this.styledText.createCaretBitmaps();
                }
                this.styledText.caretDirection = 0;
                this.styledText.setCaretLocation();
                this.styledText.redraw();
            }
        }
        return textLayout;
    }
    
    int getWidth() {
        return this.maxWidth;
    }
    
    void reset() {
        if (this.layouts != null) {
            for (int i = 0; i < this.layouts.length; ++i) {
                final TextLayout textLayout = this.layouts[i];
                if (textLayout != null) {
                    textLayout.dispose();
                }
            }
            this.layouts = null;
        }
        this.topIndex = -1;
        final boolean stylesSetCount = false;
        this.lineCount = (stylesSetCount ? 1 : 0);
        this.styleCount = (stylesSetCount ? 1 : 0);
        this.stylesSetCount = (stylesSetCount ? 1 : 0);
        this.ranges = null;
        this.styles = null;
        this.stylesSet = null;
        this.lines = null;
        this.lineWidth = null;
        this.lineHeight = null;
        this.bullets = null;
        this.bulletsIndices = null;
        this.redrawLines = null;
        this.hasLinks = false;
    }
    
    void reset(final int n, final int n2) {
        final int n3 = n + n2;
        if (n < 0 || n3 > this.lineWidth.length) {
            return;
        }
        for (int i = n; i < n3; ++i) {
            this.lineWidth[i] = -1;
            this.lineHeight[i] = -1;
        }
        if (n <= this.maxWidthLineIndex && this.maxWidthLineIndex < n3) {
            this.maxWidth = 0;
            this.maxWidthLineIndex = -1;
            if (n2 != this.lineCount) {
                for (int j = 0; j < this.lineCount; ++j) {
                    if (this.lineWidth[j] > this.maxWidth) {
                        this.maxWidth = this.lineWidth[j];
                        this.maxWidthLineIndex = j;
                    }
                }
            }
        }
    }
    
    void setContent(final StyledTextContent content) {
        this.reset();
        this.content = content;
        this.lineCount = content.getLineCount();
        this.lineWidth = new int[this.lineCount];
        this.lineHeight = new int[this.lineCount];
        this.reset(0, this.lineCount);
    }
    
    void setFont(final Font font, final int n) {
        final TextLayout textLayout = new TextLayout(this.device);
        textLayout.setFont(this.regularFont);
        if (font != null) {
            if (this.boldFont != null) {
                this.boldFont.dispose();
            }
            if (this.italicFont != null) {
                this.italicFont.dispose();
            }
            if (this.boldItalicFont != null) {
                this.boldItalicFont.dispose();
            }
            final Font boldFont = null;
            this.boldItalicFont = boldFont;
            this.italicFont = boldFont;
            this.boldFont = boldFont;
            this.regularFont = font;
            textLayout.setText("    ");
            textLayout.setFont(font);
            textLayout.setStyle(new TextStyle(this.getFont(0), null, null), 0, 0);
            textLayout.setStyle(new TextStyle(this.getFont(1), null, null), 1, 1);
            textLayout.setStyle(new TextStyle(this.getFont(2), null, null), 2, 2);
            textLayout.setStyle(new TextStyle(this.getFont(3), null, null), 3, 3);
            final FontMetrics lineMetrics = textLayout.getLineMetrics(0);
            this.ascent = lineMetrics.getAscent() + lineMetrics.getLeading();
            this.descent = lineMetrics.getDescent();
            this.boldFont.dispose();
            this.italicFont.dispose();
            this.boldItalicFont.dispose();
            final Font boldFont2 = null;
            this.boldItalicFont = boldFont2;
            this.italicFont = boldFont2;
            this.boldFont = boldFont2;
        }
        textLayout.dispose();
        final TextLayout textLayout2 = new TextLayout(this.device);
        textLayout2.setFont(this.regularFont);
        final StringBuffer sb = new StringBuffer(n);
        for (int i = 0; i < n; ++i) {
            sb.append(' ');
        }
        textLayout2.setText(sb.toString());
        this.tabWidth = textLayout2.getBounds().width;
        textLayout2.dispose();
        if (this.styledText != null) {
            final GC gc = new GC(this.styledText);
            this.averageCharWidth = gc.getFontMetrics().getAverageCharWidth();
            this.fixedPitch = (gc.stringExtent("l").x == gc.stringExtent("W").x);
            gc.dispose();
        }
    }
    
    void setLineAlignment(final int n, final int n2, final int alignment) {
        if (this.lines == null) {
            this.lines = new LineInfo[this.lineCount];
        }
        for (int i = n; i < n + n2; ++i) {
            if (this.lines[i] == null) {
                this.lines[i] = new LineInfo();
            }
            final LineInfo lineInfo = this.lines[i];
            lineInfo.flags |= 0x2;
            this.lines[i].alignment = alignment;
        }
    }
    
    void setLineBackground(final int n, final int n2, final Color background) {
        if (this.lines == null) {
            this.lines = new LineInfo[this.lineCount];
        }
        for (int i = n; i < n + n2; ++i) {
            if (this.lines[i] == null) {
                this.lines[i] = new LineInfo();
            }
            final LineInfo lineInfo = this.lines[i];
            lineInfo.flags |= 0x1;
            this.lines[i].background = background;
        }
    }
    
    void setLineBullet(final int n, final int n2, final Bullet bullet) {
        if (this.bulletsIndices != null) {
            this.bulletsIndices = null;
            this.bullets = null;
        }
        if (this.bullets == null) {
            if (bullet == null) {
                return;
            }
            (this.bullets = new Bullet[1])[0] = bullet;
        }
        int n3;
        for (n3 = 0; n3 < this.bullets.length && bullet != this.bullets[n3]; ++n3) {}
        if (bullet != null) {
            if (n3 == this.bullets.length) {
                final Bullet[] bullets = new Bullet[this.bullets.length + 1];
                System.arraycopy(this.bullets, 0, bullets, 0, this.bullets.length);
                bullets[n3] = bullet;
                this.bullets = bullets;
            }
            bullet.addIndices(n, n2);
        }
        else {
            this.updateBullets(n, n2, 0, false);
            this.styledText.redrawLinesBullet(this.redrawLines);
            this.redrawLines = null;
        }
    }
    
    void setLineIndent(final int n, final int n2, final int indent) {
        if (this.lines == null) {
            this.lines = new LineInfo[this.lineCount];
        }
        for (int i = n; i < n + n2; ++i) {
            if (this.lines[i] == null) {
                this.lines[i] = new LineInfo();
            }
            final LineInfo lineInfo = this.lines[i];
            lineInfo.flags |= 0x4;
            this.lines[i].indent = indent;
        }
    }
    
    void setLineWrapIndent(final int n, final int n2, final int wrapIndent) {
        if (this.lines == null) {
            this.lines = new LineInfo[this.lineCount];
        }
        for (int i = n; i < n + n2; ++i) {
            if (this.lines[i] == null) {
                this.lines[i] = new LineInfo();
            }
            final LineInfo lineInfo = this.lines[i];
            lineInfo.flags |= 0x80;
            this.lines[i].wrapIndent = wrapIndent;
        }
    }
    
    void setLineJustify(final int n, final int n2, final boolean justify) {
        if (this.lines == null) {
            this.lines = new LineInfo[this.lineCount];
        }
        for (int i = n; i < n + n2; ++i) {
            if (this.lines[i] == null) {
                this.lines[i] = new LineInfo();
            }
            final LineInfo lineInfo = this.lines[i];
            lineInfo.flags |= 0x8;
            this.lines[i].justify = justify;
        }
    }
    
    void setLineSegments(final int n, final int n2, final int[] segments) {
        if (this.lines == null) {
            this.lines = new LineInfo[this.lineCount];
        }
        for (int i = n; i < n + n2; ++i) {
            if (this.lines[i] == null) {
                this.lines[i] = new LineInfo();
            }
            final LineInfo lineInfo = this.lines[i];
            lineInfo.flags |= 0x20;
            this.lines[i].segments = segments;
        }
    }
    
    void setLineSegmentChars(final int n, final int n2, final char[] segmentsChars) {
        if (this.lines == null) {
            this.lines = new LineInfo[this.lineCount];
        }
        for (int i = n; i < n + n2; ++i) {
            if (this.lines[i] == null) {
                this.lines[i] = new LineInfo();
            }
            final LineInfo lineInfo = this.lines[i];
            lineInfo.flags |= 0x100;
            this.lines[i].segmentsChars = segmentsChars;
        }
    }
    
    void setLineTabStops(final int n, final int n2, final int[] tabStops) {
        if (this.lines == null) {
            this.lines = new LineInfo[this.lineCount];
        }
        for (int i = n; i < n + n2; ++i) {
            if (this.lines[i] == null) {
                this.lines[i] = new LineInfo();
            }
            final LineInfo lineInfo = this.lines[i];
            lineInfo.flags |= 0x40;
            this.lines[i].tabStops = tabStops;
        }
    }
    
    void setStyleRanges(int[] array, StyleRange[] array2) {
        if (array2 == null) {
            final boolean b = false;
            this.styleCount = (b ? 1 : 0);
            this.stylesSetCount = (b ? 1 : 0);
            this.ranges = null;
            this.styles = null;
            this.stylesSet = null;
            this.hasLinks = false;
            return;
        }
        if (array == null) {
            array = new int[array2.length << 1];
            final StyleRange[] array3 = new StyleRange[array2.length];
            if (this.stylesSet == null) {
                this.stylesSet = new StyleRange[4];
            }
            int i = 0;
            int n = 0;
            while (i < array2.length) {
                final StyleRange styleRange = array2[i];
                array[n++] = styleRange.start;
                array[n++] = styleRange.length;
                int n2;
                for (n2 = 0; n2 < this.stylesSetCount && !this.stylesSet[n2].similarTo(styleRange); ++n2) {}
                if (n2 == this.stylesSetCount) {
                    if (this.stylesSetCount == this.stylesSet.length) {
                        final StyleRange[] stylesSet = new StyleRange[this.stylesSetCount + 4];
                        System.arraycopy(this.stylesSet, 0, stylesSet, 0, this.stylesSetCount);
                        this.stylesSet = stylesSet;
                    }
                    this.stylesSet[this.stylesSetCount++] = styleRange;
                }
                array3[i] = this.stylesSet[n2];
                ++i;
            }
            array2 = array3;
        }
        if (this.styleCount == 0) {
            if (array != null) {
                System.arraycopy(array, 0, this.ranges = new int[array.length], 0, this.ranges.length);
            }
            System.arraycopy(array2, 0, this.styles = new StyleRange[array2.length], 0, this.styles.length);
            this.styleCount = array2.length;
            return;
        }
        if (array != null && this.ranges == null) {
            this.ranges = new int[this.styles.length << 1];
            int j = 0;
            int n3 = 0;
            while (j < this.styleCount) {
                this.ranges[n3++] = this.styles[j].start;
                this.ranges[n3++] = this.styles[j].length;
                ++j;
            }
        }
        if (array == null && this.ranges != null) {
            array = new int[array2.length << 1];
            int k = 0;
            int n4 = 0;
            while (k < array2.length) {
                array[n4++] = array2[k].start;
                array[n4++] = array2[k].length;
                ++k;
            }
        }
        if (this.ranges != null) {
            int n5 = this.styleCount << 1;
            int rangeIndex = this.getRangeIndex(array[0], -1, n5);
            boolean b2 = rangeIndex == n5;
            if (!b2) {
                final int n6 = array[array.length - 2] + array[array.length - 1];
                b2 = (rangeIndex == this.getRangeIndex(n6, rangeIndex - 1, n5) && this.ranges[rangeIndex] >= n6);
            }
            if (b2) {
                this.addMerge(array, array2, array.length, rangeIndex, rangeIndex);
                return;
            }
            int l = rangeIndex;
            final int[] array4 = new int[6];
            final StyleRange[] array5 = new StyleRange[3];
            for (int n7 = 0; n7 < array.length; n7 += 2) {
                final int n8 = array[n7];
                final int n9 = n8 + array[n7 + 1];
                if (n8 != n9) {
                    int n10 = 0;
                    int n11 = 0;
                    while (l < n5) {
                        if (n8 >= this.ranges[rangeIndex] + this.ranges[rangeIndex + 1]) {
                            rangeIndex += 2;
                        }
                        if (this.ranges[l] + this.ranges[l + 1] > n9) {
                            break;
                        }
                        l += 2;
                    }
                    if (this.ranges[rangeIndex] < n8 && n8 < this.ranges[rangeIndex] + this.ranges[rangeIndex + 1]) {
                        array5[n11 >> 1] = this.styles[rangeIndex >> 1];
                        array4[n11] = this.ranges[rangeIndex];
                        array4[n11 + 1] = n8 - this.ranges[rangeIndex];
                        n11 += 2;
                    }
                    array5[n11 >> 1] = array2[n7 >> 1];
                    array4[n11] = n8;
                    array4[n11 + 1] = array[n7 + 1];
                    n11 += 2;
                    if (l < n5 && this.ranges[l] < n9 && n9 < this.ranges[l] + this.ranges[l + 1]) {
                        array5[n11 >> 1] = this.styles[l >> 1];
                        array4[n11] = n9;
                        array4[n11 + 1] = this.ranges[l] + this.ranges[l + 1] - n9;
                        n11 += 2;
                        n10 = 2;
                    }
                    final int addMerge = this.addMerge(array4, array5, n11, rangeIndex, l + n10);
                    n5 += addMerge;
                    l = (rangeIndex = l + addMerge);
                }
            }
        }
        else {
            int rangeIndex2 = this.getRangeIndex(array2[0].start, -1, this.styleCount);
            boolean b3 = rangeIndex2 == this.styleCount;
            if (!b3) {
                final int n12 = array2[array2.length - 1].start + array2[array2.length - 1].length;
                b3 = (rangeIndex2 == this.getRangeIndex(n12, rangeIndex2 - 1, this.styleCount) && this.styles[rangeIndex2].start >= n12);
            }
            if (b3) {
                this.addMerge(array2, array2.length, rangeIndex2, rangeIndex2);
                return;
            }
            int n13 = rangeIndex2;
            final StyleRange[] array6 = new StyleRange[3];
            for (int n14 = 0; n14 < array2.length; ++n14) {
                final StyleRange styleRange2 = array2[n14];
                final int start = styleRange2.start;
                final int start2 = start + styleRange2.length;
                if (start != start2) {
                    int n15 = 0;
                    int n16 = 0;
                    while (n13 < this.styleCount) {
                        if (start >= this.styles[rangeIndex2].start + this.styles[rangeIndex2].length) {
                            ++rangeIndex2;
                        }
                        if (this.styles[n13].start + this.styles[n13].length > start2) {
                            break;
                        }
                        ++n13;
                    }
                    final StyleRange styleRange3 = this.styles[rangeIndex2];
                    if (styleRange3.start < start && start < styleRange3.start + styleRange3.length) {
                        final StyleRange[] array7 = array6;
                        final int n17 = n16++;
                        final StyleRange styleRange4 = (StyleRange)styleRange3.clone();
                        array7[n17] = styleRange4;
                        final StyleRange styleRange5 = styleRange4;
                        styleRange5.length = start - styleRange5.start;
                    }
                    array6[n16++] = styleRange2;
                    if (n13 < this.styleCount) {
                        final StyleRange styleRange6 = this.styles[n13];
                        if (styleRange6.start < start2 && start2 < styleRange6.start + styleRange6.length) {
                            final StyleRange[] array8 = array6;
                            final int n18 = n16++;
                            final StyleRange styleRange7 = (StyleRange)styleRange6.clone();
                            array8[n18] = styleRange7;
                            final StyleRange styleRange9;
                            final StyleRange styleRange8 = styleRange9 = styleRange7;
                            styleRange9.length += styleRange8.start - start2;
                            styleRange8.start = start2;
                            n15 = 1;
                        }
                    }
                    n13 = (rangeIndex2 = n13 + this.addMerge(array6, n16, rangeIndex2, n13 + n15));
                }
            }
        }
    }
    
    void textChanging(final TextChangingEvent textChangingEvent) {
        final int start = textChangingEvent.start;
        final int newCharCount = textChangingEvent.newCharCount;
        final int replaceCharCount = textChangingEvent.replaceCharCount;
        final int newLineCount = textChangingEvent.newLineCount;
        final int replaceLineCount = textChangingEvent.replaceLineCount;
        this.updateRanges(start, replaceCharCount, newCharCount);
        int lineAtOffset = this.content.getLineAtOffset(start);
        if (replaceCharCount == this.content.getCharCount()) {
            this.lines = null;
        }
        if (replaceLineCount == this.lineCount) {
            this.lineCount = newLineCount;
            this.lineWidth = new int[this.lineCount];
            this.lineHeight = new int[this.lineCount];
            this.reset(0, this.lineCount);
        }
        else {
            final int n = newLineCount - replaceLineCount;
            if (this.lineCount + n > this.lineWidth.length) {
                final int[] lineWidth = new int[this.lineCount + n + 32];
                System.arraycopy(this.lineWidth, 0, lineWidth, 0, this.lineCount);
                this.lineWidth = lineWidth;
                final int[] lineHeight = new int[this.lineCount + n + 32];
                System.arraycopy(this.lineHeight, 0, lineHeight, 0, this.lineCount);
                this.lineHeight = lineHeight;
            }
            if (this.lines != null && this.lineCount + n > this.lines.length) {
                final LineInfo[] lines = new LineInfo[this.lineCount + n + 32];
                System.arraycopy(this.lines, 0, lines, 0, this.lineCount);
                this.lines = lines;
            }
            final int n2 = lineAtOffset + replaceLineCount + 1;
            final int n3 = lineAtOffset + newLineCount + 1;
            System.arraycopy(this.lineWidth, n2, this.lineWidth, n3, this.lineCount - n2);
            System.arraycopy(this.lineHeight, n2, this.lineHeight, n3, this.lineCount - n2);
            for (int i = lineAtOffset; i < n3; ++i) {
                this.lineWidth[i] = (this.lineHeight[i] = -1);
            }
            for (int j = this.lineCount + n; j < this.lineCount; ++j) {
                this.lineWidth[j] = (this.lineHeight[j] = -1);
            }
            if (this.layouts != null) {
                final int n4 = lineAtOffset - this.topIndex;
                final int n5 = n4 + replaceLineCount + 1;
                for (int k = n4; k < n5; ++k) {
                    if (k >= 0 && k < this.layouts.length) {
                        if (this.layouts[k] != null) {
                            this.layouts[k].dispose();
                        }
                        this.layouts[k] = null;
                        if (this.bullets != null && this.bulletsIndices != null) {
                            this.bullets[k] = null;
                        }
                    }
                }
                if (n > 0) {
                    for (int l = this.layouts.length - 1; l >= n5; --l) {
                        if (l >= 0 && l < this.layouts.length) {
                            final int n6 = l + n;
                            if (n6 >= 0 && n6 < this.layouts.length) {
                                this.layouts[n6] = this.layouts[l];
                                this.layouts[l] = null;
                                if (this.bullets != null && this.bulletsIndices != null) {
                                    this.bullets[n6] = this.bullets[l];
                                    this.bulletsIndices[n6] = this.bulletsIndices[l];
                                    this.bullets[l] = null;
                                }
                            }
                            else {
                                if (this.layouts[l] != null) {
                                    this.layouts[l].dispose();
                                }
                                this.layouts[l] = null;
                                if (this.bullets != null && this.bulletsIndices != null) {
                                    this.bullets[l] = null;
                                }
                            }
                        }
                    }
                }
                else if (n < 0) {
                    for (int n7 = n5; n7 < this.layouts.length; ++n7) {
                        if (n7 >= 0 && n7 < this.layouts.length) {
                            final int n8 = n7 + n;
                            if (n8 >= 0 && n8 < this.layouts.length) {
                                this.layouts[n8] = this.layouts[n7];
                                this.layouts[n7] = null;
                                if (this.bullets != null && this.bulletsIndices != null) {
                                    this.bullets[n8] = this.bullets[n7];
                                    this.bulletsIndices[n8] = this.bulletsIndices[n7];
                                    this.bullets[n7] = null;
                                }
                            }
                            else {
                                if (this.layouts[n7] != null) {
                                    this.layouts[n7].dispose();
                                }
                                this.layouts[n7] = null;
                                if (this.bullets != null && this.bulletsIndices != null) {
                                    this.bullets[n7] = null;
                                }
                            }
                        }
                    }
                }
            }
            if (replaceLineCount != 0 || newLineCount != 0) {
                if (this.content.getOffsetAtLine(lineAtOffset) != start) {
                    ++lineAtOffset;
                }
                this.updateBullets(lineAtOffset, replaceLineCount, newLineCount, true);
                if (this.lines != null) {
                    final int n9 = lineAtOffset + replaceLineCount;
                    final int n10 = lineAtOffset + newLineCount;
                    System.arraycopy(this.lines, n9, this.lines, n10, this.lineCount - n9);
                    for (int n11 = lineAtOffset; n11 < n10; ++n11) {
                        this.lines[n11] = null;
                    }
                    for (int n12 = this.lineCount + n; n12 < this.lineCount; ++n12) {
                        this.lines[n12] = null;
                    }
                }
            }
            this.lineCount += n;
            if (this.maxWidthLineIndex != -1 && lineAtOffset <= this.maxWidthLineIndex && this.maxWidthLineIndex <= lineAtOffset + replaceLineCount) {
                this.maxWidth = 0;
                this.maxWidthLineIndex = -1;
                for (int maxWidthLineIndex = 0; maxWidthLineIndex < this.lineCount; ++maxWidthLineIndex) {
                    if (this.lineWidth[maxWidthLineIndex] > this.maxWidth) {
                        this.maxWidth = this.lineWidth[maxWidthLineIndex];
                        this.maxWidthLineIndex = maxWidthLineIndex;
                    }
                }
            }
        }
    }
    
    void updateBullets(final int n, final int n2, final int n3, final boolean b) {
        if (this.bullets == null) {
            return;
        }
        if (this.bulletsIndices != null) {
            return;
        }
        for (int i = 0; i < this.bullets.length; ++i) {
            final int[] removeIndices = this.bullets[i].removeIndices(n, n2, n3, b);
            if (removeIndices != null) {
                if (this.redrawLines == null) {
                    this.redrawLines = removeIndices;
                }
                else {
                    final int[] redrawLines = new int[this.redrawLines.length + removeIndices.length];
                    System.arraycopy(this.redrawLines, 0, redrawLines, 0, this.redrawLines.length);
                    System.arraycopy(removeIndices, 0, redrawLines, this.redrawLines.length, removeIndices.length);
                    this.redrawLines = redrawLines;
                }
            }
        }
        int n4 = 0;
        for (int j = 0; j < this.bullets.length; ++j) {
            if (this.bullets[j].size() == 0) {
                ++n4;
            }
        }
        if (n4 > 0) {
            if (n4 == this.bullets.length) {
                this.bullets = null;
            }
            else {
                final Bullet[] bullets = new Bullet[this.bullets.length - n4];
                int k = 0;
                int n5 = 0;
                while (k < this.bullets.length) {
                    final Bullet bullet = this.bullets[k];
                    if (bullet.size() > 0) {
                        bullets[n5++] = bullet;
                    }
                    ++k;
                }
                this.bullets = bullets;
            }
        }
    }
    
    void updateRanges(final int n, final int n2, final int n3) {
        if (this.styleCount == 0 || (n2 == 0 && n3 == 0)) {
            return;
        }
        if (this.ranges != null) {
            int n4 = this.styleCount << 1;
            int rangeIndex = this.getRangeIndex(n, -1, n4);
            if (rangeIndex == n4) {
                return;
            }
            final int n5 = n + n2;
            int rangeIndex2 = this.getRangeIndex(n5, rangeIndex - 1, n4);
            final int n6 = n3 - n2;
            if (rangeIndex == rangeIndex2 && this.ranges[rangeIndex] < n && n5 < this.ranges[rangeIndex2] + this.ranges[rangeIndex2 + 1]) {
                if (n3 == 0) {
                    final int[] ranges = this.ranges;
                    final int n7 = rangeIndex + 1;
                    ranges[n7] -= n2;
                    rangeIndex2 += 2;
                }
                else {
                    if (n4 + 2 > this.ranges.length) {
                        final int[] ranges2 = new int[this.ranges.length + 64];
                        System.arraycopy(this.ranges, 0, ranges2, 0, n4);
                        this.ranges = ranges2;
                        final StyleRange[] styles = new StyleRange[this.styles.length + 32];
                        System.arraycopy(this.styles, 0, styles, 0, this.styleCount);
                        this.styles = styles;
                    }
                    System.arraycopy(this.ranges, rangeIndex + 2, this.ranges, rangeIndex + 4, n4 - (rangeIndex + 2));
                    System.arraycopy(this.styles, rangeIndex + 2 >> 1, this.styles, rangeIndex + 4 >> 1, this.styleCount - (rangeIndex + 2 >> 1));
                    this.ranges[rangeIndex + 3] = this.ranges[rangeIndex] + this.ranges[rangeIndex + 1] - n5;
                    this.ranges[rangeIndex + 2] = n + n3;
                    this.ranges[rangeIndex + 1] = n - this.ranges[rangeIndex];
                    this.styles[(rangeIndex >> 1) + 1] = this.styles[rangeIndex >> 1];
                    n4 += 2;
                    ++this.styleCount;
                    rangeIndex2 += 4;
                }
                if (n6 != 0) {
                    for (int i = rangeIndex2; i < n4; i += 2) {
                        final int[] ranges3 = this.ranges;
                        final int n8 = i;
                        ranges3[n8] += n6;
                    }
                }
            }
            else {
                if (this.ranges[rangeIndex] < n && n < this.ranges[rangeIndex] + this.ranges[rangeIndex + 1]) {
                    this.ranges[rangeIndex + 1] = n - this.ranges[rangeIndex];
                    rangeIndex += 2;
                }
                if (rangeIndex2 < n4 && this.ranges[rangeIndex2] < n5 && n5 < this.ranges[rangeIndex2] + this.ranges[rangeIndex2 + 1]) {
                    this.ranges[rangeIndex2 + 1] = this.ranges[rangeIndex2] + this.ranges[rangeIndex2 + 1] - n5;
                    this.ranges[rangeIndex2] = n5;
                }
                if (n6 != 0) {
                    for (int j = rangeIndex2; j < n4; j += 2) {
                        final int[] ranges4 = this.ranges;
                        final int n9 = j;
                        ranges4[n9] += n6;
                    }
                }
                System.arraycopy(this.ranges, rangeIndex2, this.ranges, rangeIndex, n4 - rangeIndex2);
                System.arraycopy(this.styles, rangeIndex2 >> 1, this.styles, rangeIndex >> 1, this.styleCount - (rangeIndex2 >> 1));
                this.styleCount -= rangeIndex2 - rangeIndex >> 1;
            }
        }
        else {
            int rangeIndex3 = this.getRangeIndex(n, -1, this.styleCount);
            if (rangeIndex3 == this.styleCount) {
                return;
            }
            final int start = n + n2;
            int rangeIndex4 = this.getRangeIndex(start, rangeIndex3 - 1, this.styleCount);
            final int n10 = n3 - n2;
            if (rangeIndex3 == rangeIndex4 && this.styles[rangeIndex3].start < n && start < this.styles[rangeIndex4].start + this.styles[rangeIndex4].length) {
                if (n3 == 0) {
                    final StyleRange styleRange = this.styles[rangeIndex3];
                    styleRange.length -= n2;
                    ++rangeIndex4;
                }
                else {
                    if (this.styleCount + 1 > this.styles.length) {
                        final StyleRange[] styles2 = new StyleRange[this.styles.length + 32];
                        System.arraycopy(this.styles, 0, styles2, 0, this.styleCount);
                        this.styles = styles2;
                    }
                    System.arraycopy(this.styles, rangeIndex3 + 1, this.styles, rangeIndex3 + 2, this.styleCount - (rangeIndex3 + 1));
                    this.styles[rangeIndex3 + 1] = (StyleRange)this.styles[rangeIndex3].clone();
                    this.styles[rangeIndex3 + 1].length = this.styles[rangeIndex3].start + this.styles[rangeIndex3].length - start;
                    this.styles[rangeIndex3 + 1].start = n + n3;
                    this.styles[rangeIndex3].length = n - this.styles[rangeIndex3].start;
                    ++this.styleCount;
                    rangeIndex4 += 2;
                }
                if (n10 != 0) {
                    for (int k = rangeIndex4; k < this.styleCount; ++k) {
                        final StyleRange styleRange2 = this.styles[k];
                        styleRange2.start += n10;
                    }
                }
            }
            else {
                if (this.styles[rangeIndex3].start < n && n < this.styles[rangeIndex3].start + this.styles[rangeIndex3].length) {
                    this.styles[rangeIndex3].length = n - this.styles[rangeIndex3].start;
                    ++rangeIndex3;
                }
                if (rangeIndex4 < this.styleCount && this.styles[rangeIndex4].start < start && start < this.styles[rangeIndex4].start + this.styles[rangeIndex4].length) {
                    this.styles[rangeIndex4].length = this.styles[rangeIndex4].start + this.styles[rangeIndex4].length - start;
                    this.styles[rangeIndex4].start = start;
                }
                if (n10 != 0) {
                    for (int l = rangeIndex4; l < this.styleCount; ++l) {
                        final StyleRange styleRange3 = this.styles[l];
                        styleRange3.start += n10;
                    }
                }
                System.arraycopy(this.styles, rangeIndex4, this.styles, rangeIndex3, this.styleCount - rangeIndex4);
                this.styleCount -= rangeIndex4 - rangeIndex3;
            }
        }
    }
    
    static class LineInfo
    {
        int flags;
        Color background;
        int alignment;
        int indent;
        int wrapIndent;
        boolean justify;
        int[] segments;
        char[] segmentsChars;
        int[] tabStops;
        
        public LineInfo() {
        }
        
        public LineInfo(final LineInfo lineInfo) {
            if (lineInfo != null) {
                this.flags = lineInfo.flags;
                this.background = lineInfo.background;
                this.alignment = lineInfo.alignment;
                this.indent = lineInfo.indent;
                this.wrapIndent = lineInfo.wrapIndent;
                this.justify = lineInfo.justify;
                this.segments = lineInfo.segments;
                this.segmentsChars = lineInfo.segmentsChars;
                this.tabStops = lineInfo.tabStops;
            }
        }
    }
}
