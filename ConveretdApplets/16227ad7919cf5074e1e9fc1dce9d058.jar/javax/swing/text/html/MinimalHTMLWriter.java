// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.text.html;

import javax.swing.text.Style;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleContext;
import java.awt.Color;
import javax.swing.text.ElementIterator;
import javax.swing.text.AbstractDocument;
import java.util.Enumeration;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyleConstants;
import javax.swing.text.Element;
import java.io.IOException;
import javax.swing.text.Document;
import javax.swing.text.StyledDocument;
import java.io.Writer;
import javax.swing.text.AttributeSet;
import javax.swing.text.AbstractWriter;

public class MinimalHTMLWriter extends AbstractWriter
{
    private static final int BOLD = 1;
    private static final int ITALIC = 2;
    private static final int UNDERLINE = 4;
    private static final CSS css;
    private int fontMask;
    int startOffset;
    int endOffset;
    private AttributeSet fontAttributes;
    
    static {
        css = new CSS();
    }
    
    public MinimalHTMLWriter(final Writer writer, final StyledDocument styledDocument) {
        super(writer, styledDocument);
        this.fontMask = 0;
        this.startOffset = 0;
        this.endOffset = 0;
    }
    
    public MinimalHTMLWriter(final Writer writer, final StyledDocument styledDocument, final int n, final int n2) {
        super(writer, styledDocument, n, n2);
        this.fontMask = 0;
        this.startOffset = 0;
        this.endOffset = 0;
    }
    
    protected void endFontTag() throws IOException {
        this.write('\n');
        this.writeEndTag("</font>");
        this.fontAttributes = null;
    }
    
    protected boolean inFontTag() {
        return this.fontAttributes != null;
    }
    
    protected boolean isText(final Element element) {
        return element.getName() == "content";
    }
    
    private void setFontMask(final AttributeSet set) {
        if (StyleConstants.isBold(set)) {
            this.fontMask |= 0x1;
        }
        if (StyleConstants.isItalic(set)) {
            this.fontMask |= 0x2;
        }
        if (StyleConstants.isUnderline(set)) {
            this.fontMask |= 0x4;
        }
    }
    
    protected void startFontTag(final String s) throws IOException {
        boolean b = false;
        if (this.inFontTag()) {
            this.endFontTag();
            b = true;
        }
        this.writeStartTag("<font style=\"" + s + "\">");
        if (b) {
            this.indent();
        }
    }
    
    protected void text(final Element element) throws IOException, BadLocationException {
        String s = this.getText(element);
        if (s.length() > 0 && s.charAt(s.length() - 1) == '\n') {
            s = s.substring(0, s.length() - 1);
        }
        if (s.length() > 0) {
            this.write(s);
        }
    }
    
    public void write() throws IOException, BadLocationException {
        this.writeStartTag("<html>");
        this.writeHeader();
        this.writeBody();
        this.writeEndTag("</html>");
    }
    
    protected void writeAttributes(final AttributeSet set) throws IOException {
        final Enumeration attributeNames = set.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            final StyleConstants nextElement = attributeNames.nextElement();
            if (nextElement instanceof StyleConstants.ParagraphConstants || nextElement instanceof StyleConstants.CharacterConstants || nextElement instanceof StyleConstants.FontConstants || nextElement instanceof StyleConstants.ColorConstants) {
                this.indent();
                this.write(nextElement.toString());
                this.write(':');
                this.write(MinimalHTMLWriter.css.styleConstantsValueToCSSValue(nextElement, set.getAttribute(nextElement)).toString());
                this.write(';');
                this.write('\n');
            }
        }
    }
    
    protected void writeBody() throws IOException, BadLocationException {
        final ElementIterator elementIterator = this.getElementIterator();
        elementIterator.current();
        this.writeStartTag("<body>");
        int n = 0;
        Element next;
        while ((next = elementIterator.next()) != null) {
            if (this.inRange(next)) {
                if (next instanceof AbstractDocument.BranchElement) {
                    if (n != 0) {
                        this.writeEndParagraph();
                        n = 0;
                        this.fontMask = 0;
                    }
                    this.writeStartParagraph(next);
                }
                else if (this.isText(next)) {
                    this.writeContent(next, (n ^ 0x1) != 0x0);
                    n = 1;
                }
                else {
                    this.writeLeaf(next);
                    n = 1;
                }
            }
        }
        if (n != 0) {
            this.writeEndParagraph();
        }
        this.writeEndTag("</body>");
    }
    
    protected void writeComponent(final Element element) throws IOException {
    }
    
    protected void writeContent(final Element element, final boolean b) throws IOException, BadLocationException {
        final AttributeSet attributes = element.getAttributes();
        this.writeNonHTMLAttributes(attributes);
        if (b) {
            this.indent();
        }
        this.writeHTMLTags(attributes);
        this.text(element);
    }
    
    private void writeEndMask(final int n) throws IOException {
        if (n != 0) {
            if ((n & 0x1) != 0x0) {
                this.write("</b>");
            }
            if ((n & 0x2) != 0x0) {
                this.write("</i>");
            }
            if ((n & 0x4) != 0x0) {
                this.write("</u>");
            }
        }
    }
    
    protected void writeEndParagraph() throws IOException {
        this.writeEndMask(this.fontMask);
        if (this.inFontTag()) {
            this.endFontTag();
        }
        else {
            this.write('\n');
        }
        this.writeEndTag("</p>");
    }
    
    protected void writeEndTag(final String s) throws IOException {
        this.decrIndent();
        this.indent();
        this.write(s);
        this.write('\n');
    }
    
    protected void writeHTMLTags(final AttributeSet fontMask) throws IOException {
        final int fontMask2 = this.fontMask;
        this.setFontMask(fontMask);
        int n = 0;
        int n2 = 0;
        if ((fontMask2 & 0x1) != 0x0) {
            if ((this.fontMask & 0x1) == 0x0) {
                n |= 0x1;
            }
        }
        else if ((this.fontMask & 0x1) != 0x0) {
            n2 |= 0x1;
        }
        if ((fontMask2 & 0x2) != 0x0) {
            if ((this.fontMask & 0x2) == 0x0) {
                n |= 0x2;
            }
        }
        else if ((this.fontMask & 0x2) != 0x0) {
            n2 |= 0x2;
        }
        if ((fontMask2 & 0x4) != 0x0) {
            if ((this.fontMask & 0x4) == 0x0) {
                n |= 0x4;
            }
        }
        else if ((this.fontMask & 0x4) != 0x0) {
            n2 |= 0x4;
        }
        this.writeEndMask(n);
        this.writeStartMask(n2);
    }
    
    protected void writeHeader() throws IOException {
        this.writeStartTag("<head>");
        this.writeStartTag("<style>");
        this.writeStartTag("<!--");
        this.writeStyles();
        this.writeEndTag("-->");
        this.writeEndTag("</style>");
        this.writeEndTag("</head>");
    }
    
    protected void writeImage(final Element element) throws IOException {
    }
    
    protected void writeLeaf(final Element element) throws IOException {
        this.indent();
        if (element.getName() == "icon") {
            this.writeImage(element);
        }
        else if (element.getName() == "component") {
            this.writeComponent(element);
        }
    }
    
    protected void writeNonHTMLAttributes(final AttributeSet fontAttributes) throws IOException {
        String s = "";
        final String s2 = "; ";
        if (this.inFontTag() && this.fontAttributes.isEqual(fontAttributes)) {
            return;
        }
        final Color color = (Color)fontAttributes.getAttribute(StyleConstants.Foreground);
        if (color != null) {
            s = String.valueOf(s) + "color: " + MinimalHTMLWriter.css.styleConstantsValueToCSSValue((StyleConstants)StyleConstants.Foreground, color) + s2;
        }
        final Integer n = (Integer)fontAttributes.getAttribute(StyleConstants.FontSize);
        if (n != null) {
            s = String.valueOf(s) + "font-size: " + (int)n + s2;
        }
        final String s3 = (String)fontAttributes.getAttribute(StyleConstants.FontFamily);
        if (s3 != null) {
            s = String.valueOf(s) + "font-family: " + s3 + s2;
        }
        if (s.length() > 0) {
            this.startFontTag(s);
            this.fontAttributes = fontAttributes;
        }
    }
    
    private void writeStartMask(final int n) throws IOException {
        if (n != 0) {
            if ((n & 0x4) != 0x0) {
                this.write("<u>");
            }
            if ((n & 0x2) != 0x0) {
                this.write("<i>");
            }
            if ((n & 0x1) != 0x0) {
                this.write("<b>");
            }
        }
    }
    
    protected void writeStartParagraph(final Element element) throws IOException {
        final Object attribute = element.getAttributes().getAttribute(StyleConstants.ResolveAttribute);
        if (attribute instanceof StyleContext.NamedStyle) {
            this.writeStartTag("<p class=" + ((StyleContext.NamedStyle)attribute).getName() + ">");
        }
        else {
            this.writeStartTag("<p>");
        }
    }
    
    protected void writeStartTag(final String s) throws IOException {
        this.indent();
        this.write(s);
        this.write('\n');
        this.incrIndent();
    }
    
    protected void writeStyles() throws IOException {
        final DefaultStyledDocument defaultStyledDocument = (DefaultStyledDocument)this.getDocument();
        final Enumeration styleNames = defaultStyledDocument.getStyleNames();
        while (styleNames.hasMoreElements()) {
            final Style style = defaultStyledDocument.getStyle(styleNames.nextElement());
            if (style.getAttributeCount() != 1 || !style.isDefined(StyleConstants.NameAttribute)) {
                this.indent();
                this.write("p." + style.getName());
                this.write(" {\n");
                this.incrIndent();
                this.writeAttributes(style);
                this.decrIndent();
                this.indent();
                this.write("}\n");
            }
        }
    }
}
