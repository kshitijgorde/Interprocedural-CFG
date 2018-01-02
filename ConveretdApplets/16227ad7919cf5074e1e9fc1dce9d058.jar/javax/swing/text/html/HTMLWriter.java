// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.text.html;

import javax.swing.text.Style;
import javax.swing.text.ElementIterator;
import javax.swing.text.StyleConstants;
import java.util.Enumeration;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import java.io.IOException;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Document;
import javax.swing.text.MutableAttributeSet;
import java.io.Writer;
import javax.swing.text.Segment;
import java.util.Vector;
import java.util.Stack;
import javax.swing.text.AbstractWriter;

public class HTMLWriter extends AbstractWriter
{
    private Stack blockElementStack;
    private boolean inContent;
    private boolean inPre;
    private int preEndOffset;
    private boolean inTextArea;
    private boolean newlineOutputed;
    private boolean completeDoc;
    private Vector tags;
    private Vector tagValues;
    private char[] tempChars;
    private char[] indentChars;
    private Segment segment;
    private Vector tagsToRemove;
    private boolean wroteHead;
    private Writer out;
    private int maxLineLength;
    private int currLength;
    private int indentLevel;
    private int indentSpace;
    private int offsetIndent;
    private String newline;
    private boolean isLineEmpty;
    private int startOffset;
    private int endOffset;
    private boolean indentNext;
    private boolean writeCSS;
    private MutableAttributeSet convAttr;
    private MutableAttributeSet oConvAttr;
    
    public HTMLWriter(final Writer writer, final HTMLDocument htmlDocument) {
        this(writer, htmlDocument, 0, htmlDocument.getLength());
    }
    
    public HTMLWriter(final Writer out, final HTMLDocument htmlDocument, final int startOffset, final int n) {
        super(out, htmlDocument, startOffset, n);
        this.blockElementStack = new Stack();
        this.inContent = false;
        this.inPre = false;
        this.inTextArea = false;
        this.newlineOutputed = false;
        this.tags = new Vector(10);
        this.tagValues = new Vector(10);
        this.tagsToRemove = new Vector(10);
        this.maxLineLength = 80;
        this.indentLevel = 0;
        this.indentSpace = 2;
        this.offsetIndent = 0;
        this.indentNext = false;
        this.writeCSS = false;
        this.convAttr = new SimpleAttributeSet();
        this.oConvAttr = new SimpleAttributeSet();
        this.completeDoc = (startOffset == 0 && n == htmlDocument.getLength());
        this.maxLineLength = 80;
        this.out = out;
        final Object property = htmlDocument.getProperty("__EndOfLine__");
        if (property instanceof String) {
            this.newline = (String)property;
        }
        else {
            try {
                this.newline = System.getProperty("line.separator");
            }
            catch (SecurityException ex) {}
            if (this.newline == null) {
                this.newline = "\n";
            }
        }
        this.startOffset = startOffset;
        this.endOffset = startOffset + n;
    }
    
    protected void closeOutUnwantedEmbeddedTags(AttributeSet convertToHTML) throws IOException {
        this.tagsToRemove.removeAllElements();
        convertToHTML = this.convertToHTML(convertToHTML, null);
        int n = -1;
        final int size = this.tags.size();
        for (int i = size - 1; i >= 0; --i) {
            final HTML.Tag tag = this.tags.elementAt(i);
            final Object element = this.tagValues.elementAt(i);
            if (convertToHTML == null || this.noMatchForTagInAttributes(convertToHTML, tag, element)) {
                n = i;
                this.tagsToRemove.addElement(tag);
            }
        }
        if (n != -1) {
            final boolean b = size - n == this.tagsToRemove.size();
            for (int j = size - 1; j >= n; --j) {
                final HTML.Tag tag2 = this.tags.elementAt(j);
                if (b || this.tagsToRemove.contains(tag2)) {
                    this.tags.removeElementAt(j);
                    this.tagValues.removeElementAt(j);
                }
                this.write('<');
                this.write('/');
                this.write(tag2.toString());
                this.write('>');
            }
            for (int size2 = this.tags.size(), k = n; k < size2; ++k) {
                final HTML.Tag tag3 = this.tags.elementAt(k);
                this.write('<');
                this.write(tag3.toString());
                final Object element2 = this.tagValues.elementAt(k);
                if (element2 != null && element2 instanceof AttributeSet) {
                    this.writeAttributes((AttributeSet)element2);
                }
                this.write('>');
            }
        }
    }
    
    protected void comment(final Element element) throws BadLocationException, IOException {
        final AttributeSet attributes = element.getAttributes();
        if (this.matchNameAttribute(attributes, HTML.Tag.COMMENT)) {
            final Object attribute = attributes.getAttribute(HTML.Attribute.COMMENT);
            if (attribute instanceof String) {
                this.writeComment((String)attribute);
            }
            else {
                this.writeComment(null);
            }
        }
    }
    
    AttributeSet convertToHTML(final AttributeSet set, MutableAttributeSet convAttr) {
        if (convAttr == null) {
            convAttr = this.convAttr;
        }
        convAttr.removeAttributes(convAttr);
        if (this.writeCSS) {
            convertToHTML40(set, convAttr);
        }
        else {
            convertToHTML32(set, convAttr);
        }
        return convAttr;
    }
    
    private static void convertToHTML32(final AttributeSet set, final MutableAttributeSet set2) {
        if (set == null) {
            return;
        }
        final Enumeration attributeNames = set.getAttributeNames();
        String s = "";
        while (attributeNames.hasMoreElements()) {
            final CSS.Attribute nextElement = attributeNames.nextElement();
            if (nextElement instanceof CSS.Attribute) {
                if (nextElement == CSS.Attribute.FONT_FAMILY || nextElement == CSS.Attribute.FONT_SIZE || nextElement == CSS.Attribute.COLOR) {
                    createFontAttribute(nextElement, set, set2);
                }
                else if (nextElement == CSS.Attribute.FONT_WEIGHT) {
                    final CSS.FontWeight fontWeight = (CSS.FontWeight)set.getAttribute(CSS.Attribute.FONT_WEIGHT);
                    if (fontWeight == null || fontWeight.getValue() <= 400) {
                        continue;
                    }
                    set2.addAttribute(HTML.Tag.B, SimpleAttributeSet.EMPTY);
                }
                else if (nextElement == CSS.Attribute.FONT_STYLE) {
                    if (set.getAttribute(nextElement).toString().indexOf("italic") < 0) {
                        continue;
                    }
                    set2.addAttribute(HTML.Tag.I, SimpleAttributeSet.EMPTY);
                }
                else if (nextElement == CSS.Attribute.TEXT_DECORATION) {
                    final String string = set.getAttribute(nextElement).toString();
                    if (string.indexOf("underline") >= 0) {
                        set2.addAttribute(HTML.Tag.U, SimpleAttributeSet.EMPTY);
                    }
                    if (string.indexOf("line-through") < 0) {
                        continue;
                    }
                    set2.addAttribute(HTML.Tag.STRIKE, SimpleAttributeSet.EMPTY);
                }
                else if (nextElement == CSS.Attribute.VERTICAL_ALIGN) {
                    final String string2 = set.getAttribute(nextElement).toString();
                    if (string2.indexOf("sup") >= 0) {
                        set2.addAttribute(HTML.Tag.SUP, SimpleAttributeSet.EMPTY);
                    }
                    if (string2.indexOf("sub") < 0) {
                        continue;
                    }
                    set2.addAttribute(HTML.Tag.SUB, SimpleAttributeSet.EMPTY);
                }
                else if (nextElement == CSS.Attribute.TEXT_ALIGN) {
                    set2.addAttribute(HTML.Attribute.ALIGN, set.getAttribute(nextElement).toString());
                }
                else {
                    if (s.length() > 0) {
                        s = String.valueOf(s) + "; ";
                    }
                    s = String.valueOf(s) + nextElement + ": " + set.getAttribute(nextElement);
                }
            }
            else {
                set2.addAttribute(nextElement, set.getAttribute(nextElement));
            }
        }
        if (s.length() > 0) {
            set2.addAttribute(HTML.Attribute.STYLE, s);
        }
    }
    
    private static void convertToHTML40(final AttributeSet set, final MutableAttributeSet set2) {
        final Enumeration attributeNames = set.getAttributeNames();
        String string = "";
        while (attributeNames.hasMoreElements()) {
            final Object nextElement = attributeNames.nextElement();
            if (nextElement instanceof CSS.Attribute) {
                string = String.valueOf(string) + " " + nextElement + "=" + set.getAttribute(nextElement) + ";";
            }
            else {
                set2.addAttribute(nextElement, set.getAttribute(nextElement));
            }
        }
        if (string.length() > 0) {
            set2.addAttribute(HTML.Attribute.STYLE, string);
        }
    }
    
    private static void createFontAttribute(final CSS.Attribute attribute, final AttributeSet set, final MutableAttributeSet set2) {
        MutableAttributeSet set3 = (MutableAttributeSet)set2.getAttribute(HTML.Tag.FONT);
        if (set3 == null) {
            set3 = new SimpleAttributeSet();
            set2.addAttribute(HTML.Tag.FONT, set3);
        }
        final String string = set.getAttribute(attribute).toString();
        if (attribute == CSS.Attribute.FONT_FAMILY) {
            set3.addAttribute(HTML.Attribute.FACE, string);
        }
        else if (attribute == CSS.Attribute.FONT_SIZE) {
            set3.addAttribute(HTML.Attribute.SIZE, string);
        }
        else if (attribute == CSS.Attribute.COLOR) {
            set3.addAttribute(HTML.Attribute.COLOR, string);
        }
    }
    
    protected void decrIndent() {
        if (this.offsetIndent > 0) {
            --this.offsetIndent;
        }
        else {
            --this.indentLevel;
        }
    }
    
    protected void emptyTag(final Element element) throws BadLocationException, IOException {
        if (!this.inContent && !this.inPre) {
            this.indent();
        }
        final AttributeSet attributes = element.getAttributes();
        this.closeOutUnwantedEmbeddedTags(attributes);
        this.writeEmbeddedTags(attributes);
        if (this.matchNameAttribute(attributes, HTML.Tag.CONTENT)) {
            this.inContent = true;
            this.text(element);
        }
        else if (this.matchNameAttribute(attributes, HTML.Tag.COMMENT)) {
            this.comment(element);
        }
        else {
            final boolean blockTag = this.isBlockTag(element.getAttributes());
            if (this.inContent && blockTag) {
                this.writeNewline();
                this.indent();
            }
            final Object o = (attributes != null) ? attributes.getAttribute(StyleConstants.NameAttribute) : null;
            final Object o2 = (attributes != null) ? attributes.getAttribute(HTML.Attribute.ENDTAG) : null;
            boolean b = false;
            if (o != null && o2 != null && o2 instanceof String && ((String)o2).equals("true")) {
                b = true;
            }
            if (this.completeDoc && this.matchNameAttribute(attributes, HTML.Tag.HEAD)) {
                if (b) {
                    this.writeStyles(((HTMLDocument)this.getDocument()).getStyleSheet());
                }
                this.wroteHead = true;
            }
            this.write('<');
            if (b) {
                this.write('/');
            }
            this.write(element.getName());
            this.writeAttributes(attributes);
            this.write('>');
            if (this.matchNameAttribute(attributes, HTML.Tag.TITLE) && !b) {
                this.write((String)element.getDocument().getProperty("title"));
            }
            else if (!this.inContent || blockTag) {
                this.writeNewline();
                if (blockTag && this.inContent) {
                    this.indent();
                }
            }
        }
    }
    
    protected void endTag(final Element element) throws IOException {
        if (this.synthesizedElement(element)) {
            return;
        }
        if (this.matchNameAttribute(element.getAttributes(), HTML.Tag.PRE)) {
            this.inPre = false;
        }
        this.closeOutUnwantedEmbeddedTags(element.getAttributes());
        if (this.inContent) {
            if (!this.newlineOutputed) {
                this.writeNewline();
            }
            this.newlineOutputed = false;
            this.inContent = false;
        }
        this.indent();
        this.write('<');
        this.write('/');
        this.write(element.getName());
        this.write('>');
        this.writeNewline();
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
        final int n = this.indentLevel * this.indentSpace;
        if (this.indentChars == null || n > this.indentChars.length) {
            this.indentChars = new char[n];
            for (int i = 0; i < n; ++i) {
                this.indentChars[i] = ' ';
            }
        }
        this.out.write(this.indentChars, 0, n);
        this.currLength += n;
    }
    
    private boolean indentNeedsIncrementing(final Element element, final Element element2) {
        if (element2.getParentElement() == element && !this.inPre) {
            if (this.indentNext) {
                this.indentNext = false;
                return true;
            }
            if (this.synthesizedElement(element2)) {
                this.indentNext = true;
            }
            else if (!this.synthesizedElement(element)) {
                return true;
            }
        }
        return false;
    }
    
    private int indexOf(final char[] array, final char c, int i, final int n) {
        while (i < n) {
            if (array[i] == c) {
                return i;
            }
            ++i;
        }
        return -1;
    }
    
    protected boolean isBlockTag(final AttributeSet set) {
        final Object attribute = set.getAttribute(StyleConstants.NameAttribute);
        return attribute instanceof HTML.Tag && ((HTML.Tag)attribute).isBlock();
    }
    
    private boolean isFormElementWithContent(final AttributeSet set) {
        return this.matchNameAttribute(set, HTML.Tag.TEXTAREA) || this.matchNameAttribute(set, HTML.Tag.SELECT);
    }
    
    protected boolean matchNameAttribute(final AttributeSet set, final HTML.Tag tag) {
        final Object attribute = set.getAttribute(StyleConstants.NameAttribute);
        return attribute instanceof HTML.Tag && attribute == tag;
    }
    
    private boolean noMatchForTagInAttributes(final AttributeSet set, final HTML.Tag tag, final Object o) {
        if (set != null && set.isDefined(tag)) {
            final Object attribute = set.getAttribute(tag);
            boolean b;
            if (o == null) {
                if (attribute == null) {
                    return false;
                }
                b = false;
            }
            else {
                if (attribute != null && o.equals(attribute)) {
                    return false;
                }
                b = false;
            }
            if (!b) {
                return true;
            }
            return false;
        }
        return true;
    }
    
    protected void selectContent(final AttributeSet set) throws IOException {
        final Object attribute = set.getAttribute(StyleConstants.ModelAttribute);
        this.incrIndent();
        if (attribute instanceof OptionListModel) {
            final OptionListModel optionListModel = (OptionListModel)attribute;
            for (int size = optionListModel.getSize(), i = 0; i < size; ++i) {
                this.writeOption((Option)optionListModel.getElementAt(i));
            }
        }
        else if (attribute instanceof OptionComboBoxModel) {
            final OptionComboBoxModel optionComboBoxModel = (OptionComboBoxModel)attribute;
            for (int size2 = optionComboBoxModel.getSize(), j = 0; j < size2; ++j) {
                this.writeOption((Option)optionComboBoxModel.getElementAt(j));
            }
        }
        this.decrIndent();
    }
    
    protected void setIndentSpace(final int indentSpace) {
        this.indentSpace = indentSpace;
    }
    
    protected void setLineLength(final int maxLineLength) {
        this.maxLineLength = maxLineLength;
    }
    
    protected void startTag(final Element element) throws IOException, BadLocationException {
        if (this.synthesizedElement(element)) {
            return;
        }
        final AttributeSet attributes = element.getAttributes();
        final Object attribute = attributes.getAttribute(StyleConstants.NameAttribute);
        HTML.Tag tag;
        if (attribute instanceof HTML.Tag) {
            tag = (HTML.Tag)attribute;
        }
        else {
            tag = null;
        }
        if (tag == HTML.Tag.PRE) {
            this.inPre = true;
            this.preEndOffset = element.getEndOffset();
        }
        this.closeOutUnwantedEmbeddedTags(attributes);
        if (this.inContent) {
            this.writeNewline();
            this.inContent = false;
            this.newlineOutputed = false;
        }
        if (this.completeDoc && tag == HTML.Tag.BODY && !this.wroteHead) {
            this.wroteHead = true;
            this.indent();
            this.write("<head>");
            this.writeNewline();
            this.incrIndent();
            this.writeStyles(((HTMLDocument)this.getDocument()).getStyleSheet());
            this.decrIndent();
            this.writeNewline();
            this.indent();
            this.write("</head>");
            this.writeNewline();
        }
        this.indent();
        this.write('<');
        this.write(element.getName());
        this.writeAttributes(attributes);
        this.write('>');
        if (tag != HTML.Tag.PRE) {
            this.writeNewline();
        }
        if (tag == HTML.Tag.TEXTAREA) {
            this.textAreaContent(element.getAttributes());
        }
        else if (tag == HTML.Tag.SELECT) {
            this.selectContent(element.getAttributes());
        }
        else if (this.completeDoc && tag == HTML.Tag.BODY) {
            this.writeMaps(((HTMLDocument)this.getDocument()).getMaps());
        }
        else if (tag == HTML.Tag.HEAD) {
            this.wroteHead = true;
        }
    }
    
    protected boolean synthesizedElement(final Element element) {
        return this.matchNameAttribute(element.getAttributes(), HTML.Tag.IMPLIED);
    }
    
    protected void text(final Element element) throws BadLocationException, IOException {
        final int max = Math.max(this.startOffset, element.getStartOffset());
        final int min = Math.min(this.endOffset, element.getEndOffset());
        if (max < min) {
            if (this.segment == null) {
                this.segment = new Segment();
            }
            this.getDocument().getText(max, min - max, this.segment);
            this.newlineOutputed = false;
            if (this.segment.count > 0) {
                if (this.segment.array[this.segment.offset + this.segment.count - 1] == '\n') {
                    this.newlineOutputed = true;
                }
                if (this.inPre && min == this.preEndOffset) {
                    if (this.segment.count <= 1) {
                        return;
                    }
                    final Segment segment = this.segment;
                    --segment.count;
                }
                this.write(this.segment.array, this.segment.offset, this.segment.count, this.inPre ^ true, true);
            }
        }
    }
    
    protected void textAreaContent(final AttributeSet set) throws BadLocationException, IOException {
        final Document document = (Document)set.getAttribute(StyleConstants.ModelAttribute);
        if (document != null && document.getLength() > 0) {
            if (this.segment == null) {
                this.segment = new Segment();
            }
            document.getText(0, document.getLength(), this.segment);
            if (this.segment.count > 0) {
                this.inTextArea = true;
                this.incrIndent();
                this.indent();
                this.write(this.segment.array, this.segment.offset, this.segment.count, true, true);
                this.writeNewline();
                this.inTextArea = false;
                this.decrIndent();
            }
        }
    }
    
    public void write() throws IOException, BadLocationException {
        final ElementIterator elementIterator = this.getElementIterator();
        Element element = null;
        this.wroteHead = false;
        this.tempChars = null;
        this.currLength = 0;
        this.isLineEmpty = true;
        if (this.segment == null) {
            this.segment = new Segment();
        }
        this.inPre = false;
        Element next;
        while ((next = elementIterator.next()) != null) {
            if (this.inRange(next)) {
                if (element != null) {
                    if (this.indentNeedsIncrementing(element, next)) {
                        this.incrIndent();
                    }
                    else if (element.getParentElement() != next.getParentElement()) {
                        for (Element element2 = this.blockElementStack.peek(); element2 != next.getParentElement(); element2 = this.blockElementStack.peek()) {
                            this.blockElementStack.pop();
                            if (!this.synthesizedElement(element2)) {
                                if (!this.matchNameAttribute(element2.getAttributes(), HTML.Tag.PRE)) {
                                    this.decrIndent();
                                }
                                this.endTag(element2);
                            }
                        }
                    }
                    else if (element.getParentElement() == next.getParentElement()) {
                        final Element element3 = this.blockElementStack.peek();
                        if (element3 == element) {
                            this.blockElementStack.pop();
                            this.endTag(element3);
                        }
                    }
                }
                if (!next.isLeaf() || this.isFormElementWithContent(next.getAttributes())) {
                    this.blockElementStack.push(next);
                    this.startTag(next);
                }
                else {
                    this.emptyTag(next);
                }
                element = next;
            }
        }
        this.closeOutUnwantedEmbeddedTags(null);
        while (!this.blockElementStack.empty()) {
            final Element element4 = this.blockElementStack.pop();
            if (!this.synthesizedElement(element4)) {
                if (!this.matchNameAttribute(element4.getAttributes(), HTML.Tag.PRE)) {
                    this.decrIndent();
                }
                this.endTag(element4);
            }
        }
        if (this.completeDoc) {
            this.writeAdditionalComments();
        }
        this.segment.array = null;
    }
    
    protected void write(final char c) throws IOException {
        this.write(c, false);
    }
    
    private void write(final char c, final boolean b) throws IOException {
        if (c == '\n') {
            this.currLength = 0;
            this.out.write(this.newline);
            this.isLineEmpty = true;
        }
        else {
            this.isLineEmpty = false;
            this.out.write(c);
            ++this.currLength;
            if (b && this.currLength >= this.maxLineLength) {
                this.writeNewline();
            }
        }
    }
    
    protected void write(final String s) throws IOException {
        this.write(s, false);
    }
    
    private void write(final String s, final boolean b) throws IOException {
        final int length = s.length();
        if (this.tempChars == null || this.tempChars.length < length) {
            this.tempChars = new char[length];
        }
        s.getChars(0, length, this.tempChars, 0);
        this.write(this.tempChars, 0, length, b, b);
    }
    
    private void write(final char[] array, final int n, int n2) throws IOException {
        int n3 = n;
        this.isLineEmpty = false;
        n2 += n;
        int i = n;
    Label_0329_Outer:
        while (i < n2) {
            while (true) {
                switch (array[i]) {
                    default: {
                        if (array[i] < ' ' || array[i] > '\u007f') {
                            if (i > n3) {
                                this.out.write(array, n3, i - n3);
                            }
                            n3 = i + 1;
                            this.out.write("&#");
                            this.out.write(String.valueOf((int)array[i]));
                            this.out.write(59);
                        }
                        break Label_0329;
                    }
                    case '\t':
                    case '\n':
                    case '\r': {
                        ++i;
                        continue Label_0329_Outer;
                    }
                    case '<': {
                        if (i > n3) {
                            this.out.write(array, n3, i - n3);
                        }
                        n3 = i + 1;
                        this.out.write("&lt;");
                        continue;
                    }
                    case '>': {
                        if (i > n3) {
                            this.out.write(array, n3, i - n3);
                        }
                        n3 = i + 1;
                        this.out.write("&gt;");
                        continue;
                    }
                    case '&': {
                        if (i > n3) {
                            this.out.write(array, n3, i - n3);
                        }
                        n3 = i + 1;
                        this.out.write("&amp;");
                        continue;
                    }
                    case '\"': {
                        if (i > n3) {
                            this.out.write(array, n3, i - n3);
                        }
                        n3 = i + 1;
                        this.out.write("&quot;");
                        continue;
                    }
                }
                break;
            }
        }
        if (n3 < n2) {
            this.out.write(array, n3, n2 - n3);
        }
    }
    
    private void write(final char[] array, final int n, final int n2, final boolean b, final boolean b2) throws IOException {
        if (!b) {
            int n3 = n;
            final int n4 = n + n2;
            for (int i = this.indexOf(array, '\n', n, n4); i != -1; i = this.indexOf(array, '\n', n3, n4)) {
                if (i > n3) {
                    if (b2) {
                        this.write(array, n3, i - n3);
                    }
                    else {
                        this.out.write(array, n3, i - n3);
                    }
                }
                this.writeNewline();
                n3 = i + 1;
            }
            if (n3 < n4) {
                this.currLength += n4 - n3;
                if (b2) {
                    this.write(array, n3, n4 - n3);
                }
                else {
                    this.out.write(array, n3, n4 - n3);
                }
            }
        }
        else {
            int j = n;
            final int n5 = n + n2;
            if (this.currLength >= this.maxLineLength && !this.isLineEmpty) {
                this.writeNewline();
            }
            while (j < n5) {
                final int index = this.indexOf(array, '\n', j, n5);
                boolean b3 = false;
                if (index != -1 && this.currLength + (index - j) < this.maxLineLength) {
                    if (index > j) {
                        this.write(array, j, index - j);
                    }
                    j = index + 1;
                    b3 = true;
                }
                else if (index == -1 && this.currLength + (n5 - j) < this.maxLineLength) {
                    if (n5 > j) {
                        this.write(array, j, n5 - j);
                    }
                    this.currLength += n5 - j;
                    j = n5;
                }
                else {
                    int n6 = -1;
                    final int min = Math.min(n5 - j, this.maxLineLength - this.currLength - 1);
                    for (int k = 0; k < min; ++k) {
                        if (Character.isWhitespace(array[k + j])) {
                            n6 = k;
                        }
                    }
                    if (n6 != -1) {
                        final int n7 = n6 + (j + 1);
                        this.write(array, j, n7 - j);
                        j = n7;
                    }
                    else if (this.isLineEmpty) {
                        for (int l = Math.max(0, min); l < n5 - j; ++l) {
                            if (Character.isWhitespace(array[l + j])) {
                                n6 = l;
                                break;
                            }
                        }
                        int n8;
                        if (n6 == -1) {
                            this.write(array, j, n5 - j);
                            n8 = n5;
                        }
                        else {
                            n8 = n6 + j;
                            if (array[n8] == '\n') {
                                this.write(array, j, n8++ - j);
                            }
                            else {
                                this.write(array, j, ++n8 - j);
                            }
                        }
                        j = n8;
                    }
                    b3 = true;
                }
                if (b3 || j < n5) {
                    this.writeNewline();
                    if (j >= n5) {
                        continue;
                    }
                    this.indent();
                }
            }
        }
    }
    
    void writeAdditionalComments() throws IOException {
        final Object property = this.getDocument().getProperty("AdditionalComments");
        if (property instanceof Vector) {
            final Vector<Object> vector = (Vector<Object>)property;
            for (int i = 0; i < vector.size(); ++i) {
                this.writeComment(vector.elementAt(i).toString());
            }
        }
    }
    
    protected void writeAttributes(final AttributeSet set) throws IOException {
        this.convAttr.removeAttributes(this.convAttr);
        convertToHTML32(set, this.convAttr);
        final Enumeration attributeNames = this.convAttr.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            final HTML.Attribute nextElement = attributeNames.nextElement();
            if (!(nextElement instanceof HTML.Tag) && !(nextElement instanceof StyleConstants)) {
                if (nextElement == HTML.Attribute.ENDTAG) {
                    continue;
                }
                this.write(" " + nextElement + "=\"" + this.convAttr.getAttribute(nextElement) + "\"");
            }
        }
    }
    
    void writeComment(final String s) throws IOException {
        this.write("<!--");
        if (s != null) {
            this.write(s);
        }
        this.write("-->");
        this.writeNewline();
    }
    
    protected void writeEmbeddedTags(AttributeSet convertToHTML) throws IOException {
        convertToHTML = this.convertToHTML(convertToHTML, this.oConvAttr);
        final Enumeration attributeNames = convertToHTML.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            final HTML.Tag nextElement = attributeNames.nextElement();
            if (nextElement instanceof HTML.Tag) {
                final HTML.Tag tag = nextElement;
                if (tag == HTML.Tag.FORM) {
                    continue;
                }
                if (this.tags.contains(tag)) {
                    continue;
                }
                this.write('<');
                this.write(tag.toString());
                final Object attribute = convertToHTML.getAttribute(tag);
                if (attribute != null && attribute instanceof AttributeSet) {
                    this.writeAttributes((AttributeSet)attribute);
                }
                this.write('>');
                this.tags.addElement(tag);
                this.tagValues.addElement(attribute);
            }
        }
    }
    
    void writeMaps(final Enumeration enumeration) throws IOException {
        if (enumeration != null) {
            while (enumeration.hasMoreElements()) {
                final Map map = enumeration.nextElement();
                final String name = map.getName();
                this.incrIndent();
                this.indent();
                this.write("<map");
                if (name != null) {
                    this.write(" name=\"");
                    this.write(name);
                    this.write("\">");
                }
                else {
                    this.write('>');
                }
                this.writeNewline();
                this.incrIndent();
                final AttributeSet[] areas = map.getAreas();
                if (areas != null) {
                    for (int i = 0; i < areas.length; ++i) {
                        this.indent();
                        this.write("<area");
                        this.writeAttributes(areas[i]);
                        this.write("></area>");
                        this.writeNewline();
                    }
                }
                this.decrIndent();
                this.indent();
                this.write("</map>");
                this.writeNewline();
                this.decrIndent();
            }
        }
    }
    
    private void writeNewline() throws IOException {
        this.out.write(this.newline);
        this.isLineEmpty = true;
        this.currLength = 0;
    }
    
    protected void writeOption(final Option option) throws IOException {
        this.indent();
        this.write('<');
        this.write("option ");
        if (option.getValue() != null) {
            this.write("value=" + option.getValue());
        }
        if (option.isSelected()) {
            this.write(" selected");
        }
        this.write('>');
        if (option.getLabel() != null) {
            this.write(option.getLabel());
        }
        this.writeNewline();
    }
    
    boolean writeStyle(final String s, final Style style, boolean b) throws IOException {
        boolean b2 = false;
        final Enumeration attributeNames = style.getAttributeNames();
        if (attributeNames != null) {
            while (attributeNames.hasMoreElements()) {
                final Object nextElement = attributeNames.nextElement();
                if (nextElement instanceof CSS.Attribute) {
                    final String string = style.getAttribute(nextElement).toString();
                    if (string == null) {
                        continue;
                    }
                    if (!b) {
                        this.writeStyleStartTag();
                        b = true;
                    }
                    if (!b2) {
                        b2 = true;
                        this.indent();
                        this.write(s);
                        this.write(" {");
                    }
                    else {
                        this.write(";");
                    }
                    this.write(' ');
                    this.write(nextElement.toString());
                    this.write(": ");
                    this.write(string);
                }
            }
        }
        if (b2) {
            this.write(" }");
            this.writeNewline();
        }
        return b2;
    }
    
    void writeStyleEndTag() throws IOException {
        this.decrIndent();
        this.indent();
        this.write("-->");
        this.writeNewline();
        this.decrIndent();
        this.indent();
        this.write("</style>");
        this.writeNewline();
        this.indent();
    }
    
    void writeStyleStartTag() throws IOException {
        this.indent();
        this.write("<style type=\"text/css\">");
        this.incrIndent();
        this.writeNewline();
        this.indent();
        this.write("<!--");
        this.incrIndent();
        this.writeNewline();
    }
    
    void writeStyles(final StyleSheet styleSheet) throws IOException {
        if (styleSheet != null) {
            final Enumeration styleNames = styleSheet.getStyleNames();
            if (styleNames != null) {
                boolean b = false;
                while (styleNames.hasMoreElements()) {
                    final String s = styleNames.nextElement();
                    if (!"default".equals(s) && this.writeStyle(s, styleSheet.getStyle(s), b)) {
                        b = true;
                    }
                }
                if (b) {
                    this.writeStyleEndTag();
                }
            }
        }
    }
}
