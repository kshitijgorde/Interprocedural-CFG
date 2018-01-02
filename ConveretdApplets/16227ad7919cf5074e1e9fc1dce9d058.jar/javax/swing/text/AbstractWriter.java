// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.text;

import java.util.Enumeration;
import java.io.IOException;
import java.io.Writer;

public abstract class AbstractWriter
{
    private ElementIterator it;
    private Writer out;
    private int indentLevel;
    private int indentSpace;
    private Document doc;
    private int maxLineLength;
    private int currLength;
    private int startOffset;
    private int endOffset;
    private int offsetIndent;
    protected static final char NEWLINE = '\n';
    
    protected AbstractWriter(final Writer writer, final Document document) {
        this(writer, document, 0, document.getLength());
    }
    
    protected AbstractWriter(final Writer out, final Document doc, final int startOffset, final int n) {
        this.indentLevel = 0;
        this.indentSpace = 2;
        this.doc = null;
        this.maxLineLength = 100;
        this.currLength = 0;
        this.startOffset = 0;
        this.endOffset = 0;
        this.offsetIndent = 0;
        this.doc = doc;
        this.it = new ElementIterator(doc.getDefaultRootElement());
        this.out = out;
        this.startOffset = startOffset;
        this.endOffset = startOffset + n;
    }
    
    protected AbstractWriter(final Writer writer, final Element element) {
        this(writer, element, 0, element.getEndOffset());
    }
    
    protected AbstractWriter(final Writer out, final Element element, final int startOffset, final int n) {
        this.indentLevel = 0;
        this.indentSpace = 2;
        this.doc = null;
        this.maxLineLength = 100;
        this.currLength = 0;
        this.startOffset = 0;
        this.endOffset = 0;
        this.offsetIndent = 0;
        this.doc = element.getDocument();
        this.it = new ElementIterator(element);
        this.out = out;
        this.startOffset = startOffset;
        this.endOffset = startOffset + n;
    }
    
    protected void decrIndent() {
        if (this.offsetIndent > 0) {
            --this.offsetIndent;
        }
        else {
            --this.indentLevel;
        }
    }
    
    protected Document getDocument() {
        return this.doc;
    }
    
    protected ElementIterator getElementIterator() {
        return this.it;
    }
    
    protected String getText(final Element element) throws BadLocationException {
        return this.doc.getText(element.getStartOffset(), element.getEndOffset() - element.getStartOffset());
    }
    
    protected boolean inRange(final Element element) {
        return (element.getStartOffset() >= this.startOffset && element.getStartOffset() < this.endOffset) || (this.startOffset >= element.getStartOffset() && this.startOffset < element.getEndOffset());
    }
    
    protected void incrIndent() {
        if (this.offsetIndent > 0) {
            ++this.offsetIndent;
        }
        else if (++this.indentLevel * this.indentSpace >= this.maxLineLength) {
            ++this.offsetIndent;
            --this.indentLevel;
        }
    }
    
    protected void indent() throws IOException {
        for (int n = this.indentLevel * this.indentSpace, i = 0; i < n; ++i) {
            this.write(' ');
        }
    }
    
    protected void setIndentSpace(final int indentSpace) {
        this.indentSpace = indentSpace;
    }
    
    protected void setLineLength(final int maxLineLength) {
        this.maxLineLength = maxLineLength;
    }
    
    protected void text(final Element element) throws BadLocationException, IOException {
        final String text = this.getText(element);
        if (text.length() > 0) {
            this.write(text);
        }
    }
    
    protected abstract void write() throws IOException, BadLocationException;
    
    protected void write(final char c) throws IOException {
        this.out.write(c);
        if (c == '\n') {
            this.currLength = 0;
        }
        else {
            ++this.currLength;
            if (this.currLength == this.maxLineLength) {
                this.out.write(10);
                this.currLength = 0;
                this.indent();
            }
        }
    }
    
    protected void write(final String s) throws IOException {
        final int n = this.indentLevel * this.indentSpace;
        final int index = s.indexOf(10);
        if (this.currLength + s.length() <= this.maxLineLength) {
            this.out.write(s);
            this.currLength += s.length();
            if (index >= 0) {
                this.currLength -= index - 1;
            }
        }
        else if (n + s.length() <= this.maxLineLength) {
            this.out.write(10);
            this.currLength = 0;
            this.indent();
            this.out.write(s);
            this.currLength = n + s.length();
            if (index >= 0) {
                this.currLength -= index - 1;
            }
        }
        else {
            final int n2 = this.maxLineLength - n;
            this.write(s.substring(0, n2));
            this.write(s.substring(n2, s.length()));
        }
    }
    
    protected void writeAttributes(final AttributeSet set) throws IOException {
        final Enumeration attributeNames = set.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            final Object nextElement = attributeNames.nextElement();
            this.write(" " + nextElement + "=" + set.getAttribute(nextElement));
        }
    }
}
