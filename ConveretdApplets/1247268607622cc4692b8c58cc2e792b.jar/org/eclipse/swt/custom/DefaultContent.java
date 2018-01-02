// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.custom;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.TypedListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.internal.SWTEventListener;
import org.eclipse.swt.internal.Compatibility;
import java.util.Vector;

class DefaultContent implements StyledTextContent
{
    private static final String LineDelimiter;
    Vector textListeners;
    char[] textStore;
    int gapStart;
    int gapEnd;
    int gapLine;
    int highWatermark;
    int lowWatermark;
    int[][] lines;
    int lineCount;
    int expandExp;
    int replaceExpandExp;
    
    static {
        LineDelimiter = System.getProperty("line.separator");
    }
    
    DefaultContent() {
        this.textListeners = new Vector();
        this.textStore = new char[0];
        this.gapStart = -1;
        this.gapEnd = -1;
        this.gapLine = -1;
        this.highWatermark = 300;
        this.lowWatermark = 50;
        this.lines = new int[50][2];
        this.lineCount = 0;
        this.expandExp = 1;
        this.replaceExpandExp = 1;
        this.setText("");
    }
    
    void addLineIndex(final int n, final int n2) {
        final int length = this.lines.length;
        if (this.lineCount == length) {
            final int[][] lines = new int[length + Compatibility.pow2(this.expandExp)][2];
            System.arraycopy(this.lines, 0, lines, 0, length);
            this.lines = lines;
            ++this.expandExp;
        }
        this.lines[this.lineCount] = new int[] { n, n2 };
        ++this.lineCount;
    }
    
    int[][] addLineIndex(final int n, final int n2, final int[][] array, final int n3) {
        final int length = array.length;
        int[][] array2 = array;
        if (n3 == length) {
            array2 = new int[length + Compatibility.pow2(this.replaceExpandExp)][2];
            ++this.replaceExpandExp;
            System.arraycopy(array, 0, array2, 0, length);
        }
        array2[n3] = new int[] { n, n2 };
        return array2;
    }
    
    public void addTextChangeListener(final TextChangeListener textChangeListener) {
        if (textChangeListener == null) {
            this.error(4);
        }
        this.textListeners.addElement(new StyledTextListener(textChangeListener));
    }
    
    void adjustGap(final int n, final int n2, final int n3) {
        if (n == this.gapStart) {
            final int n4 = this.gapEnd - this.gapStart - n2;
            if (this.lowWatermark <= n4 && n4 <= this.highWatermark) {
                return;
            }
        }
        else if (n + n2 == this.gapStart && n2 < 0) {
            final int n5 = this.gapEnd - this.gapStart - n2;
            if (this.lowWatermark <= n5 && n5 <= this.highWatermark) {
                return;
            }
        }
        this.moveAndResizeGap(n, n2, n3);
    }
    
    void indexLines() {
        int n = 0;
        this.lineCount = 0;
        int length;
        int i;
        for (length = this.textStore.length, i = n; i < length; ++i) {
            final char c = this.textStore[i];
            if (c == '\r') {
                if (i + 1 < length && this.textStore[i + 1] == '\n') {
                    ++i;
                }
                this.addLineIndex(n, i - n + 1);
                n = i + 1;
            }
            else if (c == '\n') {
                this.addLineIndex(n, i - n + 1);
                n = i + 1;
            }
        }
        this.addLineIndex(n, i - n);
    }
    
    boolean isDelimiter(final char c) {
        return c == '\r' || c == '\n';
    }
    
    protected boolean isValidReplace(final int n, final int n2, final String s) {
        if (n2 == 0) {
            if (n == 0) {
                return true;
            }
            if (n == this.getCharCount()) {
                return true;
            }
            if (this.getTextRange(n - 1, 1).charAt(0) == '\r' && this.getTextRange(n, 1).charAt(0) == '\n') {
                return false;
            }
        }
        else {
            if (this.getTextRange(n, 1).charAt(0) == '\n' && n != 0 && this.getTextRange(n - 1, 1).charAt(0) == '\r') {
                return false;
            }
            if (this.getTextRange(n + n2 - 1, 1).charAt(0) == '\r' && n + n2 != this.getCharCount() && this.getTextRange(n + n2, 1).charAt(0) == '\n') {
                return false;
            }
        }
        return true;
    }
    
    int[][] indexLines(final int n, final int n2, final int n3) {
        int[][] array = new int[n3][2];
        int n4 = 0;
        int n5 = 0;
        this.replaceExpandExp = 1;
        int i;
        for (i = n4; i < n2; ++i) {
            final int n6 = i + n;
            if (n6 < this.gapStart || n6 >= this.gapEnd) {
                final char c = this.textStore[n6];
                if (c == '\r') {
                    if (n6 + 1 < this.textStore.length && this.textStore[n6 + 1] == '\n') {
                        ++i;
                    }
                    array = this.addLineIndex(n4, i - n4 + 1, array, n5);
                    ++n5;
                    n4 = i + 1;
                }
                else if (c == '\n') {
                    array = this.addLineIndex(n4, i - n4 + 1, array, n5);
                    ++n5;
                    n4 = i + 1;
                }
            }
        }
        final int[][] array2 = new int[n5 + 1][2];
        System.arraycopy(array, 0, array2, 0, n5);
        array2[n5] = new int[] { n4, i - n4 };
        return array2;
    }
    
    void insert(final int n, final String s) {
        if (s.length() == 0) {
            return;
        }
        final int lineAtOffset = this.getLineAtOffset(n);
        final int length = s.length();
        final boolean b = n == this.getCharCount();
        this.adjustGap(n, length, lineAtOffset);
        final int offsetAtLine = this.getOffsetAtLine(lineAtOffset);
        final int length2 = this.getPhysicalLine(lineAtOffset).length();
        if (length > 0) {
            this.gapStart += length;
            for (int i = 0; i < s.length(); ++i) {
                this.textStore[n + i] = s.charAt(i);
            }
        }
        final int[][] indexLines = this.indexLines(offsetAtLine, length2, 10);
        int n2 = indexLines.length - 1;
        if (indexLines[n2][1] == 0) {
            if (b) {
                ++n2;
            }
            else {
                --n2;
            }
        }
        this.expandLinesBy(n2);
        for (int j = this.lineCount - 1; j > lineAtOffset; --j) {
            this.lines[j + n2] = this.lines[j];
        }
        for (int k = 0; k < n2; ++k) {
            final int[] array = indexLines[k];
            final int n3 = 0;
            array[n3] += offsetAtLine;
            this.lines[lineAtOffset + k] = indexLines[k];
        }
        if (n2 < indexLines.length) {
            final int[] array2 = indexLines[n2];
            final int n4 = 0;
            array2[n4] += offsetAtLine;
            this.lines[lineAtOffset + n2] = indexLines[n2];
        }
        this.lineCount += n2;
        this.gapLine = this.getLineAtPhysicalOffset(this.gapStart);
    }
    
    void moveAndResizeGap(final int gapStart, final int n, final int gapLine) {
        final char[] array = null;
        final int n2 = this.gapEnd - this.gapStart;
        int n3;
        if (n > 0) {
            n3 = this.highWatermark + n;
        }
        else {
            n3 = this.lowWatermark - n;
        }
        if (this.gapExists()) {
            this.lines[this.gapLine][1] -= n2;
            for (int i = this.gapLine + 1; i < this.lineCount; ++i) {
                this.lines[i][0] -= n2;
            }
        }
        if (n3 < 0) {
            if (n2 > 0) {
                final char[] textStore = new char[this.textStore.length - n2];
                System.arraycopy(this.textStore, 0, textStore, 0, this.gapStart);
                System.arraycopy(this.textStore, this.gapEnd, textStore, this.gapStart, textStore.length - this.gapStart);
                this.textStore = textStore;
            }
            this.gapEnd = gapStart;
            this.gapStart = gapStart;
            return;
        }
        final char[] textStore2 = new char[this.textStore.length + (n3 - n2)];
        final int gapEnd = gapStart + n3;
        if (n2 == 0) {
            System.arraycopy(this.textStore, 0, textStore2, 0, gapStart);
            System.arraycopy(this.textStore, gapStart, textStore2, gapEnd, textStore2.length - gapEnd);
        }
        else if (gapStart < this.gapStart) {
            final int n4 = this.gapStart - gapStart;
            System.arraycopy(this.textStore, 0, textStore2, 0, gapStart);
            System.arraycopy(this.textStore, gapStart, textStore2, gapEnd, n4);
            System.arraycopy(this.textStore, this.gapEnd, textStore2, gapEnd + n4, this.textStore.length - this.gapEnd);
        }
        else {
            final int n5 = gapStart - this.gapStart;
            System.arraycopy(this.textStore, 0, textStore2, 0, this.gapStart);
            System.arraycopy(this.textStore, this.gapEnd, textStore2, this.gapStart, n5);
            System.arraycopy(this.textStore, this.gapEnd + n5, textStore2, gapEnd, textStore2.length - gapEnd);
        }
        this.textStore = textStore2;
        this.gapStart = gapStart;
        this.gapEnd = gapEnd;
        if (this.gapExists()) {
            this.gapLine = gapLine;
            final int n6 = this.gapEnd - this.gapStart;
            this.lines[this.gapLine][1] += n6;
            for (int j = this.gapLine + 1; j < this.lineCount; ++j) {
                this.lines[j][0] += n6;
            }
        }
    }
    
    int lineCount(final int n, final int n2) {
        if (n2 == 0) {
            return 0;
        }
        int n3 = 0;
        int i = 0;
        int n4 = n;
        if (n4 >= this.gapStart) {
            n4 += this.gapEnd - this.gapStart;
        }
        while (i < n2) {
            if (n4 < this.gapStart || n4 >= this.gapEnd) {
                final char c = this.textStore[n4];
                if (c == '\r') {
                    if (n4 + 1 < this.textStore.length && this.textStore[n4 + 1] == '\n') {
                        ++n4;
                        ++i;
                    }
                    ++n3;
                }
                else if (c == '\n') {
                    ++n3;
                }
                ++i;
            }
            ++n4;
        }
        return n3;
    }
    
    int lineCount(final String s) {
        int n = 0;
        for (int length = s.length(), i = 0; i < length; ++i) {
            final char char1 = s.charAt(i);
            if (char1 == '\r') {
                if (i + 1 < length && s.charAt(i + 1) == '\n') {
                    ++i;
                }
                ++n;
            }
            else if (char1 == '\n') {
                ++n;
            }
        }
        return n;
    }
    
    public int getCharCount() {
        return this.textStore.length - (this.gapEnd - this.gapStart);
    }
    
    public String getLine(final int n) {
        if (n >= this.lineCount || n < 0) {
            this.error(5);
        }
        final int n2 = this.lines[n][0];
        int n3 = this.lines[n][1];
        final int n4 = n2 + n3 - 1;
        if (this.gapExists() && n4 >= this.gapStart) {
            if (n2 < this.gapEnd) {
                final StringBuffer sb = new StringBuffer();
                final int n5 = this.gapEnd - this.gapStart;
                sb.append(this.textStore, n2, this.gapStart - n2);
                sb.append(this.textStore, this.gapEnd, n3 - n5 - (this.gapStart - n2));
                int length;
                for (length = sb.length(); length - 1 >= 0 && this.isDelimiter(sb.charAt(length - 1)); --length) {}
                return sb.toString().substring(0, length);
            }
        }
        while (n3 - 1 >= 0 && this.isDelimiter(this.textStore[n2 + n3 - 1])) {
            --n3;
        }
        return new String(this.textStore, n2, n3);
    }
    
    public String getLineDelimiter() {
        return DefaultContent.LineDelimiter;
    }
    
    String getFullLine(final int n) {
        final int n2 = this.lines[n][0];
        final int n3 = this.lines[n][1];
        final int n4 = n2 + n3 - 1;
        if (!this.gapExists() || n4 < this.gapStart || n2 >= this.gapEnd) {
            return new String(this.textStore, n2, n3);
        }
        final StringBuffer sb = new StringBuffer();
        final int n5 = this.gapEnd - this.gapStart;
        sb.append(this.textStore, n2, this.gapStart - n2);
        sb.append(this.textStore, this.gapEnd, n3 - n5 - (this.gapStart - n2));
        return sb.toString();
    }
    
    String getPhysicalLine(final int n) {
        return this.getPhysicalText(this.lines[n][0], this.lines[n][1]);
    }
    
    public int getLineCount() {
        return this.lineCount;
    }
    
    public int getLineAtOffset(final int n) {
        if (n > this.getCharCount() || n < 0) {
            this.error(5);
        }
        int n2;
        if (n < this.gapStart) {
            n2 = n;
        }
        else {
            n2 = n + (this.gapEnd - this.gapStart);
        }
        if (this.lineCount > 0) {
            final int n3 = this.lineCount - 1;
            if (n2 == this.lines[n3][0] + this.lines[n3][1]) {
                return n3;
            }
        }
        int lineCount = this.lineCount;
        int n4 = -1;
        final int lineCount2 = this.lineCount;
        while (lineCount - n4 > 1) {
            final int n5 = (lineCount + n4) / 2;
            final int n6 = this.lines[n5][0];
            final int n7 = n6 + this.lines[n5][1] - 1;
            if (n2 <= n6) {
                lineCount = n5;
            }
            else {
                if (n2 <= n7) {
                    lineCount = n5;
                    break;
                }
                n4 = n5;
            }
        }
        return lineCount;
    }
    
    int getLineAtPhysicalOffset(final int n) {
        int lineCount = this.lineCount;
        int n2 = -1;
        final int lineCount2 = this.lineCount;
        while (lineCount - n2 > 1) {
            final int n3 = (lineCount + n2) / 2;
            final int n4 = this.lines[n3][0];
            final int n5 = n4 + this.lines[n3][1] - 1;
            if (n <= n4) {
                lineCount = n3;
            }
            else {
                if (n <= n5) {
                    lineCount = n3;
                    break;
                }
                n2 = n3;
            }
        }
        return lineCount;
    }
    
    public int getOffsetAtLine(final int n) {
        if (n == 0) {
            return 0;
        }
        if (n >= this.lineCount || n < 0) {
            this.error(5);
        }
        final int n2 = this.lines[n][0];
        if (n2 > this.gapEnd) {
            return n2 - (this.gapEnd - this.gapStart);
        }
        return n2;
    }
    
    void expandLinesBy(final int n) {
        final int length = this.lines.length;
        if (length - this.lineCount >= n) {
            return;
        }
        final int[][] lines = new int[length + Math.max(10, n)][2];
        System.arraycopy(this.lines, 0, lines, 0, length);
        this.lines = lines;
    }
    
    void error(final int n) {
        SWT.error(n);
    }
    
    boolean gapExists() {
        return this.gapStart != this.gapEnd;
    }
    
    String getPhysicalText(final int n, final int n2) {
        return new String(this.textStore, n, n2);
    }
    
    public String getTextRange(final int n, final int n2) {
        if (this.textStore == null) {
            return "";
        }
        if (n2 == 0) {
            return "";
        }
        final int n3 = n + n2;
        if (!this.gapExists() || n3 < this.gapStart) {
            return new String(this.textStore, n, n2);
        }
        if (this.gapStart < n) {
            return new String(this.textStore, n + (this.gapEnd - this.gapStart), n2);
        }
        final StringBuffer sb = new StringBuffer();
        sb.append(this.textStore, n, this.gapStart - n);
        sb.append(this.textStore, this.gapEnd, n3 - this.gapStart);
        return sb.toString();
    }
    
    public void removeTextChangeListener(final TextChangeListener textChangeListener) {
        if (textChangeListener == null) {
            this.error(4);
        }
        for (int i = 0; i < this.textListeners.size(); ++i) {
            if (((TypedListener)this.textListeners.elementAt(i)).getEventListener() == textChangeListener) {
                this.textListeners.removeElementAt(i);
                break;
            }
        }
    }
    
    public void replaceTextRange(final int start, final int replaceCharCount, final String text) {
        if (!this.isValidReplace(start, replaceCharCount, text)) {
            SWT.error(5);
        }
        final StyledTextEvent styledTextEvent = new StyledTextEvent(this);
        styledTextEvent.type = 3003;
        styledTextEvent.start = start;
        styledTextEvent.replaceLineCount = this.lineCount(start, replaceCharCount);
        styledTextEvent.text = text;
        styledTextEvent.newLineCount = this.lineCount(text);
        styledTextEvent.replaceCharCount = replaceCharCount;
        styledTextEvent.newCharCount = text.length();
        this.sendTextEvent(styledTextEvent);
        this.delete(start, replaceCharCount, styledTextEvent.replaceLineCount + 1);
        this.insert(start, text);
        final StyledTextEvent styledTextEvent2 = new StyledTextEvent(this);
        styledTextEvent2.type = 3006;
        this.sendTextEvent(styledTextEvent2);
    }
    
    void sendTextEvent(final StyledTextEvent styledTextEvent) {
        for (int i = 0; i < this.textListeners.size(); ++i) {
            ((StyledTextListener)this.textListeners.elementAt(i)).handleEvent(styledTextEvent);
        }
    }
    
    public void setText(final String s) {
        this.textStore = s.toCharArray();
        this.gapStart = -1;
        this.gapEnd = -1;
        this.expandExp = 1;
        this.indexLines();
        final StyledTextEvent styledTextEvent = new StyledTextEvent(this);
        styledTextEvent.type = 3004;
        styledTextEvent.text = "";
        this.sendTextEvent(styledTextEvent);
    }
    
    void delete(final int n, final int n2, final int n3) {
        if (n2 == 0) {
            return;
        }
        final int lineAtOffset = this.getLineAtOffset(n);
        final int offsetAtLine = this.getOffsetAtLine(lineAtOffset);
        final int lineAtOffset2 = this.getLineAtOffset(n + n2);
        boolean b = false;
        if (n + n2 < this.getCharCount()) {
            final String textRange = this.getTextRange(n + n2 - 1, 2);
            if (textRange.charAt(0) == '\r' && textRange.charAt(1) == '\n') {
                b = true;
            }
        }
        this.adjustGap(n + n2, -n2, lineAtOffset);
        final int[][] indexLines = this.indexLines(n, n2 + (this.gapEnd - this.gapStart), n3);
        if (n + n2 == this.gapStart) {
            this.gapStart -= n2;
        }
        else {
            this.gapEnd += n2;
        }
        int n4 = n;
        for (boolean b2 = false; n4 < this.textStore.length && !b2; ++n4) {
            if (n4 < this.gapStart || n4 >= this.gapEnd) {
                final char c = this.textStore[n4];
                if (this.isDelimiter(c)) {
                    if (n4 + 1 < this.textStore.length && c == '\r' && this.textStore[n4 + 1] == '\n') {
                        ++n4;
                    }
                    b2 = true;
                }
            }
        }
        this.lines[lineAtOffset][1] = n - offsetAtLine + (n4 - n);
        int n5 = indexLines.length - 1;
        if (b) {
            --n5;
        }
        for (int i = lineAtOffset2 + 1; i < this.lineCount; ++i) {
            this.lines[i - n5] = this.lines[i];
        }
        this.lineCount -= n5;
        this.gapLine = this.getLineAtPhysicalOffset(this.gapStart);
    }
}
