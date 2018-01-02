// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.text;

import java.awt.Rectangle;
import java.text.BreakIterator;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class Utilities
{
    static boolean is1dot2;
    static /* synthetic */ Class class$java$awt$Toolkit;
    
    static {
        Utilities.is1dot2 = false;
        try {
            Utilities.is1dot2 = (((Utilities.class$java$awt$Toolkit != null) ? Utilities.class$java$awt$Toolkit : (Utilities.class$java$awt$Toolkit = class$("java.awt.Toolkit"))).getMethod("getMaximumCursorColors", (Class[])null) != null);
        }
        catch (NoSuchMethodException ex) {
            Utilities.is1dot2 = false;
        }
        if (Utilities.is1dot2) {
            System.err.println("warning: running 1.1 version of Utilities");
        }
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static int drawComposedText(final AttributeSet set, final Graphics graphics, final int n, final int n2, final int n3, final int n4) throws BadLocationException {
        return n;
    }
    
    public static final int drawTabbedText(final Segment segment, int n, final int n2, final Graphics graphics, final TabExpander tabExpander, final int n3) {
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        int n4 = n;
        final char[] array = segment.array;
        int n5 = 0;
        int offset = segment.offset;
        for (int n6 = segment.offset + segment.count, i = segment.offset; i < n6; ++i) {
            if (array[i] == '\t') {
                if (n5 > 0) {
                    graphics.drawChars(array, offset, n5, n, n2);
                    n5 = 0;
                }
                offset = i + 1;
                if (tabExpander != null) {
                    n4 = (int)tabExpander.nextTabStop(n4, n3 + i - segment.offset);
                }
                else {
                    n4 += fontMetrics.charWidth(' ');
                }
                n = n4;
            }
            else if (array[i] == '\n' || array[i] == '\r') {
                if (n5 > 0) {
                    graphics.drawChars(array, offset, n5, n, n2);
                    n5 = 0;
                }
                offset = i + 1;
                n = n4;
            }
            else {
                ++n5;
                n4 += fontMetrics.charWidth(array[i]);
            }
        }
        if (n5 > 0) {
            graphics.drawChars(array, offset, n5, n, n2);
        }
        return n4;
    }
    
    public static final int getBreakLocation(final Segment segment, final FontMetrics fontMetrics, final int n, final int n2, final TabExpander tabExpander, final int n3) {
        int tabbedTextOffset = getTabbedTextOffset(segment, fontMetrics, n, n2, tabExpander, n3, false);
        for (int i = segment.offset + Math.min(tabbedTextOffset, segment.count - 1); i >= segment.offset; --i) {
            if (Character.isWhitespace(segment.array[i])) {
                tabbedTextOffset = i - segment.offset + 1;
                break;
            }
        }
        return tabbedTextOffset;
    }
    
    public static final int getNextWord(final JTextComponent textComponent, int endOffset) throws BadLocationException {
        Element element;
        int i;
        for (element = getParagraphElement(textComponent, endOffset), i = getNextWordInParagraph(element, endOffset, false); i == -1; i = getNextWordInParagraph(element, endOffset, true)) {
            endOffset = element.getEndOffset();
            element = getParagraphElement(textComponent, endOffset);
        }
        return i;
    }
    
    static int getNextWordInParagraph(final Element element, int n, final boolean b) throws BadLocationException {
        if (element == null) {
            throw new BadLocationException("No more words", n);
        }
        final Document document = element.getDocument();
        final int startOffset = element.getStartOffset();
        final int min = Math.min(element.getEndOffset(), document.getLength());
        if (n >= min || n < startOffset) {
            throw new BadLocationException("No more words", n);
        }
        final String text = document.getText(startOffset, min - startOffset);
        final BreakIterator wordInstance = BreakIterator.getWordInstance();
        wordInstance.setText(text);
        if (b && wordInstance.first() == n - startOffset && !Character.isWhitespace(text.charAt(wordInstance.first()))) {
            return n;
        }
        final int following = wordInstance.following(n - startOffset);
        if (following == -1 || following >= text.length()) {
            return -1;
        }
        if (!Character.isWhitespace(text.charAt(following))) {
            return startOffset + following;
        }
        final int next = wordInstance.next();
        if (next != -1) {
            n = startOffset + next;
            if (n != min) {
                return n;
            }
        }
        return -1;
    }
    
    public static final Element getParagraphElement(final JTextComponent textComponent, final int n) {
        final Document document = textComponent.getDocument();
        if (document instanceof StyledDocument) {
            return ((StyledDocument)document).getParagraphElement(n);
        }
        final Element defaultRootElement = document.getDefaultRootElement();
        final Element element = defaultRootElement.getElement(defaultRootElement.getElementIndex(n));
        if (n >= element.getStartOffset() && n < element.getEndOffset()) {
            return element;
        }
        return null;
    }
    
    public static final int getPositionAbove(final JTextComponent textComponent, int n, final int n2) throws BadLocationException {
        int n3 = getRowStart(textComponent, n) - 1;
        int n4 = 32767;
        int y = 0;
        Rectangle modelToView = null;
        if (n3 >= 0) {
            modelToView = textComponent.modelToView(n3);
            y = modelToView.y;
        }
        while (modelToView != null && y == modelToView.y) {
            final int abs = Math.abs(modelToView.x - n2);
            if (abs < n4) {
                n = n3;
                n4 = abs;
            }
            modelToView = ((--n3 >= 0) ? textComponent.modelToView(n3) : null);
        }
        return n;
    }
    
    public static final int getPositionBelow(final JTextComponent textComponent, int n, final int n2) throws BadLocationException {
        int n3 = getRowEnd(textComponent, n) + 1;
        int n4 = 32767;
        final int length = textComponent.getDocument().getLength();
        int y = 0;
        Rectangle modelToView = null;
        if (n3 <= length) {
            modelToView = textComponent.modelToView(n3);
            y = modelToView.y;
        }
        while (modelToView != null && y == modelToView.y) {
            final int abs = Math.abs(n2 - modelToView.x);
            if (abs < n4) {
                n = n3;
                n4 = abs;
            }
            modelToView = ((++n3 <= length) ? textComponent.modelToView(n3) : null);
        }
        return n;
    }
    
    static int getPrevWordInParagraph(final Element element, final int n) throws BadLocationException {
        if (element == null) {
            throw new BadLocationException("No more words", n);
        }
        final Document document = element.getDocument();
        final int startOffset = element.getStartOffset();
        final int endOffset = element.getEndOffset();
        if (n > endOffset || n < startOffset) {
            throw new BadLocationException("No more words", n);
        }
        final String text = document.getText(startOffset, endOffset - startOffset);
        final BreakIterator wordInstance = BreakIterator.getWordInstance();
        wordInstance.setText(text);
        if (wordInstance.following(n - startOffset) == -1) {
            wordInstance.last();
        }
        int n2 = wordInstance.previous();
        if (n2 == n - startOffset) {
            n2 = wordInstance.previous();
        }
        if (n2 == -1) {
            return -1;
        }
        if (!Character.isWhitespace(text.charAt(n2))) {
            return startOffset + n2;
        }
        final int previous = wordInstance.previous();
        if (previous != -1) {
            return startOffset + previous;
        }
        return -1;
    }
    
    public static final int getPreviousWord(final JTextComponent textComponent, int n) throws BadLocationException {
        Element element;
        int i;
        for (element = getParagraphElement(textComponent, n), i = getPrevWordInParagraph(element, n); i == -1; i = getPrevWordInParagraph(element, n)) {
            n = element.getStartOffset() - 1;
            element = getParagraphElement(textComponent, n);
        }
        return i;
    }
    
    public static final int getRowEnd(final JTextComponent textComponent, int n) throws BadLocationException {
        Rectangle modelToView = textComponent.modelToView(n);
        for (int length = textComponent.getDocument().getLength(), n2 = n, y = modelToView.y; modelToView != null && y == modelToView.y; modelToView = ((++n2 <= length) ? textComponent.modelToView(n2) : null)) {
            n = n2;
        }
        return n;
    }
    
    public static final int getRowStart(final JTextComponent textComponent, int n) throws BadLocationException {
        Rectangle modelToView = textComponent.modelToView(n);
        for (int n2 = n, y = modelToView.y; modelToView != null && y == modelToView.y; modelToView = ((--n2 >= 0) ? textComponent.modelToView(n2) : null)) {
            n = n2;
        }
        return n;
    }
    
    public static final int getTabbedTextOffset(final Segment segment, final FontMetrics fontMetrics, final int n, final int n2, final TabExpander tabExpander, final int n3) {
        return getTabbedTextOffset(segment, fontMetrics, n, n2, tabExpander, n3, true);
    }
    
    public static final int getTabbedTextOffset(final Segment segment, final FontMetrics fontMetrics, final int n, final int n2, final TabExpander tabExpander, final int n3, final boolean b) {
        int n5;
        int n4 = n5 = n;
        final char[] array = segment.array;
        final int n6 = segment.offset + segment.count;
        int i = segment.offset;
        while (i < n6) {
            if (array[i] == '\t') {
                if (tabExpander != null) {
                    n5 = (int)tabExpander.nextTabStop(n5, n3 + i - segment.offset);
                }
                else {
                    n5 += fontMetrics.charWidth(' ');
                }
            }
            else {
                n5 += fontMetrics.charWidth(array[i]);
            }
            if (n2 >= n4 && n2 < n5) {
                if (!b || n2 - n4 < n5 - n2) {
                    return i - segment.offset;
                }
                return i + 1 - segment.offset;
            }
            else {
                n4 = n5;
                ++i;
            }
        }
        return segment.count;
    }
    
    public static final int getTabbedTextWidth(final Segment segment, final FontMetrics fontMetrics, final int n, final TabExpander tabExpander, final int n2) {
        int n3 = n;
        final char[] array = segment.array;
        for (int n4 = segment.offset + segment.count, i = segment.offset; i < n4; ++i) {
            if (array[i] == '\t') {
                if (tabExpander != null) {
                    n3 = (int)tabExpander.nextTabStop(n3, n2 + i - segment.offset);
                }
                else {
                    n3 += fontMetrics.charWidth(' ');
                }
            }
            else if (array[i] != '\n') {
                n3 += fontMetrics.charWidth(array[i]);
            }
        }
        return n3 - n;
    }
    
    public static final int getWordEnd(final JTextComponent textComponent, int n) throws BadLocationException {
        final Document document = textComponent.getDocument();
        final Element paragraphElement = getParagraphElement(textComponent, n);
        final int startOffset = paragraphElement.getStartOffset();
        final String text = document.getText(startOffset, Math.min(paragraphElement.getEndOffset(), document.getLength()) - startOffset);
        if (text != null && text.length() > 0) {
            final BreakIterator wordInstance = BreakIterator.getWordInstance();
            wordInstance.setText(text);
            int n2 = n - startOffset;
            if (n2 >= wordInstance.last()) {
                n2 = wordInstance.last() - 1;
            }
            n = startOffset + wordInstance.following(n2);
        }
        return n;
    }
    
    public static final int getWordStart(final JTextComponent textComponent, int n) throws BadLocationException {
        final Document document = textComponent.getDocument();
        final Element paragraphElement = getParagraphElement(textComponent, n);
        final int startOffset = paragraphElement.getStartOffset();
        final String text = document.getText(startOffset, Math.min(paragraphElement.getEndOffset(), document.getLength()) - startOffset);
        if (text != null && text.length() > 0) {
            final BreakIterator wordInstance = BreakIterator.getWordInstance();
            wordInstance.setText(text);
            int n2 = n - startOffset;
            if (n2 >= wordInstance.last()) {
                n2 = wordInstance.last() - 1;
            }
            wordInstance.following(n2);
            n = startOffset + wordInstance.previous();
        }
        return n;
    }
    
    static boolean isComposedTextAttributeDefined(final AttributeSet set) {
        return set != null && set.isDefined(StyleConstants.ComposedTextAttribute);
    }
    
    static boolean isComposedTextElement(final Element element) {
        return isComposedTextAttributeDefined(element.getAttributes());
    }
}
