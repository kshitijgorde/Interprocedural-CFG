// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.text.html;

import javax.swing.DefaultButtonModel;
import java.net.MalformedURLException;
import java.util.Stack;
import java.util.Vector;
import javax.swing.undo.UndoableEdit;
import java.util.BitSet;
import javax.swing.JToggleButton;
import javax.swing.text.PlainDocument;
import javax.swing.ComboBoxModel;
import java.io.Reader;
import java.io.StringReader;
import java.io.IOException;
import javax.swing.ButtonModel;
import javax.swing.text.BadLocationException;
import java.util.Enumeration;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.text.Document;
import javax.swing.text.ElementIterator;
import javax.swing.text.Element;
import java.net.URLEncoder;
import java.util.Hashtable;
import javax.swing.text.StyleContext;
import javax.swing.text.AbstractDocument;
import javax.swing.text.GapContent;
import javax.swing.text.StyleConstants;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.AttributeSet;
import java.net.URL;
import javax.swing.ButtonGroup;
import javax.swing.text.DefaultStyledDocument;

public class HTMLDocument extends DefaultStyledDocument
{
    private boolean frameDocument;
    private boolean preservesUnknownTags;
    private ButtonGroup radioButtonGroup;
    static final String TokenThreshold = "token threshold";
    public static final String AdditionalComments = "AdditionalComments";
    static final String StyleType = "StyleType";
    URL base;
    private static AttributeSet contentAttributeSet;
    static String MAP_PROPERTY;
    static String PARSER_PROPERTY;
    static HTML.UnknownTag EndOfLineTag;
    private static char[] NEWLINE;
    
    static {
        HTMLDocument.MAP_PROPERTY = "__MAP__";
        HTMLDocument.PARSER_PROPERTY = "__PARSER__";
        HTMLDocument.contentAttributeSet = new SimpleAttributeSet();
        ((MutableAttributeSet)HTMLDocument.contentAttributeSet).addAttribute(StyleConstants.NameAttribute, HTML.Tag.CONTENT);
        (HTMLDocument.NEWLINE = new char[1])[0] = '\n';
        HTMLDocument.EndOfLineTag = new HTML.UnknownTag("__EndOfLineTag__");
    }
    
    public HTMLDocument() {
        this(new GapContent(4096), new StyleSheet());
    }
    
    public HTMLDocument(final Content content, final StyleSheet styleSheet) {
        super(content, styleSheet);
        this.frameDocument = false;
        this.preservesUnknownTags = true;
    }
    
    public HTMLDocument(final StyleSheet styleSheet) {
        this(new GapContent(4096), styleSheet);
    }
    
    static /* synthetic */ void access$2(final HTMLDocument htmlDocument, final ButtonGroup radioButtonGroup) {
        htmlDocument.radioButtonGroup = radioButtonGroup;
    }
    
    void addMap(final Map map) {
        final String name = map.getName();
        if (name != null) {
            Object property = this.getProperty(HTMLDocument.MAP_PROPERTY);
            if (property == null) {
                property = new Hashtable<String, Map>(11);
                this.putProperty(HTMLDocument.MAP_PROPERTY, property);
            }
            if (property instanceof Hashtable) {
                ((Hashtable<String, Map>)property).put("#" + name, map);
            }
        }
    }
    
    private void ampersand(final StringBuffer sb) {
        if (sb.length() > 0) {
            sb.append('&');
        }
    }
    
    private void appendBuffer(final StringBuffer sb, final String s, final String s2) {
        this.ampersand(sb);
        sb.append(URLEncoder.encode(s));
        sb.append('=');
        sb.append(URLEncoder.encode(s2));
    }
    
    protected void create(final ElementSpec[] array) {
        super.create(array);
    }
    
    protected Element createBranchElement(final Element element, final AttributeSet set) {
        return new BlockElement(element, set);
    }
    
    protected AbstractElement createDefaultRoot() {
        this.writeLock();
        final SimpleAttributeSet set = new SimpleAttributeSet();
        set.addAttribute(StyleConstants.NameAttribute, HTML.Tag.HTML);
        final BlockElement blockElement = new BlockElement(null, set.copyAttributes());
        set.removeAttributes(set);
        set.addAttribute(StyleConstants.NameAttribute, HTML.Tag.BODY);
        final BlockElement blockElement2 = new BlockElement(blockElement, set.copyAttributes());
        set.removeAttributes(set);
        set.addAttribute(StyleConstants.NameAttribute, HTML.Tag.P);
        final BlockElement blockElement3 = new BlockElement(blockElement2, set.copyAttributes());
        set.removeAttributes(set);
        set.addAttribute(StyleConstants.NameAttribute, HTML.Tag.CONTENT);
        final Element[] array = { new RunElement(blockElement3, set, 0, 1) };
        ((BranchElement)blockElement3).replace(0, 0, array);
        array[0] = blockElement3;
        ((BranchElement)blockElement2).replace(0, 0, array);
        array[0] = blockElement2;
        ((BranchElement)blockElement).replace(0, 0, array);
        this.writeUnlock();
        return blockElement;
    }
    
    protected Element createLeafElement(final Element element, final AttributeSet set, final int n, final int n2) {
        return new RunElement(element, set, n, n2);
    }
    
    private Element findFrame(final String s) {
        Element next;
        while ((next = new ElementIterator(this).next()) != null) {
            final AttributeSet attributes = next.getAttributes();
            if (this.matchNameAttribute(attributes, HTML.Tag.FRAME) && ((String)attributes.getAttribute(HTML.Attribute.NAME)).equals(s)) {
                break;
            }
        }
        return next;
    }
    
    protected void fireChangedUpdate(final DocumentEvent documentEvent) {
        super.fireChangedUpdate(documentEvent);
    }
    
    protected void fireUndoableEditUpdate(final UndoableEditEvent undoableEditEvent) {
        super.fireUndoableEditUpdate(undoableEditEvent);
    }
    
    private boolean formMatchesSubmissionRequest(final AttributeSet set, final AttributeSet set2) {
        final AttributeSet formAttributes = this.getFormAttributes(set);
        return formAttributes != null && set2.isEqual(formAttributes);
    }
    
    public URL getBase() {
        return this.base;
    }
    
    String getDefaultStyleSheetType() {
        final String s = (String)this.getProperty("StyleType");
        if (s == null) {
            return "text/css";
        }
        return s;
    }
    
    Element getElementByID(final String s) {
        if (s == null) {
            return null;
        }
        return this.getElementWithAttribute(this.getDefaultRootElement(), HTML.Attribute.ID, s);
    }
    
    Element getElementWithAttribute(final Element element, final Object o, final Object o2) {
        final AttributeSet attributes = element.getAttributes();
        if (attributes != null && attributes.isDefined(o) && o2.equals(attributes.getAttribute(o))) {
            return element;
        }
        if (!element.isLeaf()) {
            for (int i = 0; i < element.getElementCount(); ++i) {
                final Element elementWithAttribute = this.getElementWithAttribute(element.getElement(i), o, o2);
                if (elementWithAttribute != null) {
                    return elementWithAttribute;
                }
            }
        }
        return null;
    }
    
    AttributeSet getFormAttributes(final AttributeSet set) {
        final Enumeration attributeNames = set.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            final HTML.Tag nextElement = attributeNames.nextElement();
            if (nextElement instanceof HTML.Tag) {
                final HTML.Tag tag = nextElement;
                if (tag != HTML.Tag.FORM) {
                    continue;
                }
                final Object attribute = set.getAttribute(tag);
                if (attribute != null && attribute instanceof AttributeSet) {
                    return (AttributeSet)attribute;
                }
                continue;
            }
        }
        return null;
    }
    
    void getFormData(final StringBuffer sb, final Element element) {
        final AttributeSet formAttributes = this.getFormAttributes(element.getAttributes());
        final ElementIterator elementIterator = new ElementIterator(this.getDefaultRootElement());
        if (formAttributes == null) {
            return;
        }
        boolean b = false;
        Element next;
        while ((next = elementIterator.next()) != null) {
            final AttributeSet attributes = next.getAttributes();
            if (this.formMatchesSubmissionRequest(attributes, formAttributes)) {
                b = true;
                final String s = (String)attributes.getAttribute(HTML.Attribute.TYPE);
                if ((s != null && s.equals("submit") && next != element) || (s != null && s.equals("image"))) {
                    continue;
                }
                this.loadElementDataIntoBuffer(next, sb);
            }
            else {
                if (b && next.isLeaf()) {
                    break;
                }
                continue;
            }
        }
    }
    
    private String getInputElementData(final AttributeSet set) {
        final Object attribute = set.getAttribute(StyleConstants.ModelAttribute);
        final String s = (String)set.getAttribute(HTML.Attribute.TYPE);
        String text = null;
        if (s.equals("text") || s.equals("password")) {
            final Document document = (Document)attribute;
            try {
                text = document.getText(0, document.getLength());
            }
            catch (BadLocationException ex) {
                text = null;
            }
        }
        else if (s.equals("submit") || s.equals("hidden")) {
            text = (String)set.getAttribute(HTML.Attribute.VALUE);
            if (text == null) {
                text = "";
            }
        }
        else if ((s.equals("radio") || s.equals("checkbox")) && ((ButtonModel)attribute).isSelected()) {
            text = (String)set.getAttribute(HTML.Attribute.VALUE);
            if (text == null) {
                text = "on";
            }
        }
        return text;
    }
    
    public Iterator getIterator(final HTML.Tag tag) {
        if (tag.isBlock()) {
            return null;
        }
        return (Iterator)new LeafIterator(tag, this);
    }
    
    Map getMap(final String s) {
        if (s != null) {
            final Object property = this.getProperty(HTMLDocument.MAP_PROPERTY);
            if (property != null && property instanceof Hashtable) {
                return ((Hashtable<K, Map>)property).get(s);
            }
        }
        return null;
    }
    
    Enumeration getMaps() {
        final Object property = this.getProperty(HTMLDocument.MAP_PROPERTY);
        if (property instanceof Hashtable) {
            return ((Hashtable)property).elements();
        }
        return null;
    }
    
    HTMLEditorKit.Parser getParser() {
        final Object property = this.getProperty(HTMLDocument.PARSER_PROPERTY);
        if (property instanceof HTMLEditorKit.Parser) {
            return (HTMLEditorKit.Parser)property;
        }
        return null;
    }
    
    public boolean getPreservesUnknownTags() {
        return this.preservesUnknownTags;
    }
    
    public HTMLEditorKit.ParserCallback getReader(final int n) {
        final Object property = this.getProperty("stream");
        if (property instanceof URL) {
            this.base = (URL)property;
        }
        return new HTMLReader(n);
    }
    
    public HTMLEditorKit.ParserCallback getReader(final int n, final int n2, final int n3, final HTML.Tag tag) {
        return this.getReader(n, n2, n3, tag, true);
    }
    
    HTMLEditorKit.ParserCallback getReader(final int n, final int n2, final int n3, final HTML.Tag tag, final boolean b) {
        final Object property = this.getProperty("stream");
        if (property instanceof URL) {
            this.base = (URL)property;
            this.getStyleSheet().setBase(this.base);
        }
        return new HTMLReader(n, n2, n3, tag, b);
    }
    
    public StyleSheet getStyleSheet() {
        return (StyleSheet)this.getAttributeContext();
    }
    
    private String getTextAreaData(final AttributeSet set) {
        final Document document = (Document)set.getAttribute(StyleConstants.ModelAttribute);
        try {
            return document.getText(0, document.getLength());
        }
        catch (BadLocationException ex) {
            return null;
        }
    }
    
    public int getTokenThreshold() {
        final Integer n = (Integer)this.getProperty("token threshold");
        if (n != null) {
            return n;
        }
        return Integer.MAX_VALUE;
    }
    
    protected void insert(final int n, final ElementSpec[] array) throws BadLocationException {
        super.insert(n, array);
    }
    
    void insertAfterEnd(final Element element, final String s) throws BadLocationException, IOException {
        if (element != null) {
            final Element parentElement = element.getParentElement();
            if (parentElement != null) {
                this.insertHTML(parentElement, element.getEndOffset(), s, null);
            }
        }
    }
    
    void insertAfterStart(final Element element, final String s) throws BadLocationException, IOException {
        this.insertHTML(element, element.getStartOffset(), s, null);
    }
    
    void insertBeforeEnd(final Element element, final String s) throws BadLocationException, IOException {
        this.insertHTML(element, element.getEndOffset(), s, null);
    }
    
    void insertBeforeStart(final Element element, final String s) throws BadLocationException, IOException {
        if (element != null) {
            final Element parentElement = element.getParentElement();
            if (parentElement != null) {
                this.insertHTML(parentElement, element.getStartOffset(), s, null);
            }
        }
    }
    
    void insertHTML(final Element element, final int n, final String s, final HTML.Tag tag) throws BadLocationException, IOException {
        if (element != null && s != null) {
            final Object o = (tag != null) ? tag : element.getAttributes().getAttribute(StyleConstants.NameAttribute);
            final HTMLEditorKit.Parser parser = this.getParser();
            if (parser != null && o != null && o instanceof HTML.Tag) {
                final int max = Math.max(0, n - 1);
                Element element2 = this.getCharacterElement(max);
                Element parentElement = element;
                int n2 = 0;
                int n3 = 0;
                if (element.getStartOffset() > max) {
                    while (parentElement != null && parentElement.getStartOffset() > max) {
                        parentElement = parentElement.getParentElement();
                        ++n3;
                    }
                    if (parentElement == null) {
                        throw new BadLocationException("No common parent", n);
                    }
                }
                while (element2 != null && element2 != parentElement) {
                    ++n2;
                    element2 = element2.getParentElement();
                }
                if (element2 != null) {
                    final HTMLEditorKit.ParserCallback reader = this.getReader(n, n2 - 1, n3, (HTML.Tag)o, tag != null);
                    parser.parse(new StringReader(s), reader, true);
                    reader.flush();
                }
            }
        }
    }
    
    protected void insertUpdate(final DefaultDocumentEvent defaultDocumentEvent, AttributeSet contentAttributeSet) {
        if (contentAttributeSet == null) {
            contentAttributeSet = HTMLDocument.contentAttributeSet;
        }
        else if (contentAttributeSet.isDefined(StyleConstants.ComposedTextAttribute)) {
            ((MutableAttributeSet)contentAttributeSet).addAttributes(HTMLDocument.contentAttributeSet);
        }
        super.insertUpdate(defaultDocumentEvent, contentAttributeSet);
    }
    
    boolean isFrameDocument() {
        return this.frameDocument;
    }
    
    boolean isLastTextOrPasswordField(final Element element) {
        final ElementIterator elementIterator = new ElementIterator(this.getDefaultRootElement());
        int n = 0;
        final AttributeSet formAttributes = this.getFormAttributes(element.getAttributes());
        Element next;
        while ((next = elementIterator.next()) != null) {
            final AttributeSet attributes = next.getAttributes();
            if (this.formMatchesSubmissionRequest(attributes, formAttributes)) {
                if (n != 0 && this.matchNameAttribute(attributes, HTML.Tag.INPUT)) {
                    final String s = (String)attributes.getAttribute(HTML.Attribute.TYPE);
                    if (s.equals("text") || s.equals("password")) {
                        return false;
                    }
                }
                if (next != element) {
                    continue;
                }
                n = 1;
            }
            else {
                if (n != 0 && next.isLeaf()) {
                    break;
                }
                continue;
            }
        }
        return true;
    }
    
    private void loadElementDataIntoBuffer(final Element element, final StringBuffer sb) {
        final AttributeSet attributes = element.getAttributes();
        final String s = (String)attributes.getAttribute(HTML.Attribute.NAME);
        if (s == null) {
            return;
        }
        String s2 = null;
        final HTML.Tag tag = (HTML.Tag)element.getAttributes().getAttribute(StyleConstants.NameAttribute);
        if (tag == HTML.Tag.INPUT) {
            s2 = this.getInputElementData(attributes);
        }
        else if (tag == HTML.Tag.TEXTAREA) {
            s2 = this.getTextAreaData(attributes);
        }
        else if (tag == HTML.Tag.SELECT) {
            this.loadSelectData(attributes, sb);
        }
        if (s != null && s2 != null) {
            this.appendBuffer(sb, s, s2);
        }
    }
    
    private void loadSelectData(final AttributeSet set, final StringBuffer sb) {
        final String s = (String)set.getAttribute(HTML.Attribute.NAME);
        if (s == null) {
            return;
        }
        final Object attribute = set.getAttribute(StyleConstants.ModelAttribute);
        if (attribute instanceof OptionListModel) {
            final OptionListModel optionListModel = (OptionListModel)attribute;
            for (int i = 0; i < optionListModel.getSize(); ++i) {
                if (optionListModel.isSelectedIndex(i)) {
                    this.appendBuffer(sb, s, ((Option)optionListModel.getElementAt(i)).getValue());
                }
            }
        }
        else if (attribute instanceof ComboBoxModel) {
            final Option option = (Option)((ComboBoxModel)attribute).getSelectedItem();
            if (option != null) {
                this.appendBuffer(sb, s, option.getValue());
            }
        }
    }
    
    boolean matchNameAttribute(final AttributeSet set, final HTML.Tag tag) {
        final Object attribute = set.getAttribute(StyleConstants.NameAttribute);
        return attribute instanceof HTML.Tag && attribute == tag;
    }
    
    void obtainLock() {
        this.writeLock();
    }
    
    public void processHTMLFrameHyperlinkEvent(final HTMLFrameHyperlinkEvent htmlFrameHyperlinkEvent) {
        final String target = htmlFrameHyperlinkEvent.getTarget();
        final Element sourceElement = htmlFrameHyperlinkEvent.getSourceElement();
        final String string = htmlFrameHyperlinkEvent.getURL().toString();
        if (target.equals("_self")) {
            this.updateFrame(sourceElement, string);
        }
        else if (target.equals("_parent")) {
            this.updateFrameSet(sourceElement.getParentElement(), string);
        }
        else {
            final Element frame = this.findFrame(target);
            if (frame != null) {
                this.updateFrame(frame, string);
            }
        }
    }
    
    void releaseLock() {
        this.writeUnlock();
    }
    
    void removeMap(final Map map) {
        final String name = map.getName();
        if (name != null) {
            final Object property = this.getProperty(HTMLDocument.MAP_PROPERTY);
            if (property instanceof Hashtable) {
                ((Hashtable)property).remove("#" + name);
            }
        }
    }
    
    void resetForm(final Element element) {
        final ElementIterator elementIterator = new ElementIterator(this.getDefaultRootElement());
        boolean b = false;
        final AttributeSet formAttributes = this.getFormAttributes(element.getAttributes());
        Element next;
        while ((next = elementIterator.next()) != null) {
            final AttributeSet attributes = next.getAttributes();
            if (this.formMatchesSubmissionRequest(attributes, formAttributes)) {
                final Object attribute = attributes.getAttribute(StyleConstants.ModelAttribute);
                if (attribute instanceof TextAreaDocument) {
                    ((TextAreaDocument)attribute).reset();
                }
                else if (attribute instanceof PlainDocument) {
                    try {
                        final TextAreaDocument textAreaDocument = (TextAreaDocument)attribute;
                        textAreaDocument.remove(0, textAreaDocument.getLength());
                        if (this.matchNameAttribute(attributes, HTML.Tag.INPUT)) {
                            final String s = (String)attributes.getAttribute(HTML.Attribute.VALUE);
                            if (s != null) {
                                textAreaDocument.insertString(0, s, null);
                            }
                        }
                    }
                    catch (BadLocationException ex) {}
                }
                else if (attribute instanceof OptionListModel) {
                    final OptionListModel optionListModel = (OptionListModel)attribute;
                    for (int size = optionListModel.getSize(), i = 0; i < size; ++i) {
                        optionListModel.removeIndexInterval(i, i);
                    }
                    final BitSet initialSelection = optionListModel.getInitialSelection();
                    for (int j = 0; j < initialSelection.size(); ++j) {
                        if (initialSelection.get(j)) {
                            optionListModel.addSelectionInterval(j, j);
                        }
                    }
                }
                else if (attribute instanceof OptionComboBoxModel) {
                    final OptionComboBoxModel optionComboBoxModel = (OptionComboBoxModel)attribute;
                    final Option initialSelection2 = optionComboBoxModel.getInitialSelection();
                    if (initialSelection2 != null) {
                        optionComboBoxModel.setSelectedItem(initialSelection2);
                    }
                }
                else if (attribute instanceof JToggleButton.ToggleButtonModel) {
                    ((JToggleButton.ToggleButtonModel)attribute).setSelected(attributes.getAttribute(HTML.Attribute.CHECKED) != null);
                }
                b = true;
            }
            else {
                if (b && next.isLeaf()) {
                    break;
                }
                continue;
            }
        }
    }
    
    public void setBase(final URL base) {
        this.base = base;
    }
    
    void setDefaultStyleSheetType(final String s) {
        this.putProperty("StyleType", s);
    }
    
    void setFrameDocumentState(final boolean frameDocument) {
        this.frameDocument = frameDocument;
    }
    
    void setInnerHTML(final Element element, final String s) throws BadLocationException, IOException {
        if (element != null && s != null) {
            final int elementCount = element.getElementCount();
            this.insertHTML(element, element.getEndOffset(), s, null);
            if (element.getElementCount() > elementCount) {
                final int startOffset = element.getStartOffset();
                this.remove(startOffset, element.getElement(elementCount).getStartOffset() - startOffset);
            }
        }
    }
    
    void setOuterHTML(final Element element, final String s) throws BadLocationException, IOException {
        if (element != null && element.getParentElement() != null && s != null) {
            this.insertHTML(element.getParentElement(), element.getStartOffset(), s, null);
            this.remove(element.getStartOffset(), element.getEndOffset() - element.getStartOffset());
        }
    }
    
    public void setParagraphAttributes(int startOffset, int max, final AttributeSet set, final boolean b) {
        try {
            this.writeLock();
            final int min = Math.min(startOffset + max, this.getLength());
            startOffset = this.getParagraphElement(startOffset).getStartOffset();
            max = Math.max(0, this.getParagraphElement(min).getEndOffset() - startOffset);
            this.getClass();
            final DefaultDocumentEvent defaultDocumentEvent = new DefaultDocumentEvent(this, startOffset, max, DocumentEvent.EventType.CHANGE);
            final AttributeSet copyAttributes = set.copyAttributes();
            for (int endOffset = Integer.MAX_VALUE, i = startOffset; i <= min; i = endOffset) {
                final Element paragraphElement = this.getParagraphElement(i);
                if (endOffset == paragraphElement.getEndOffset()) {
                    ++endOffset;
                }
                else {
                    endOffset = paragraphElement.getEndOffset();
                }
                final MutableAttributeSet set2 = (MutableAttributeSet)paragraphElement.getAttributes();
                defaultDocumentEvent.addEdit(new AttributeUndoableEdit(paragraphElement, copyAttributes, b));
                if (b) {
                    set2.removeAttributes(set2);
                }
                set2.addAttributes(set);
            }
            defaultDocumentEvent.end();
            this.fireChangedUpdate(defaultDocumentEvent);
            this.fireUndoableEditUpdate(new UndoableEditEvent(this, defaultDocumentEvent));
        }
        finally {
            this.writeUnlock();
        }
    }
    
    public void setPreservesUnknownTags(final boolean preservesUnknownTags) {
        this.preservesUnknownTags = preservesUnknownTags;
    }
    
    public void setTokenThreshold(final int n) {
        this.putProperty("token threshold", new Integer(n));
    }
    
    private void updateFrame(final Element element, final String s) {
        try {
            this.writeLock();
            this.getClass();
            final DefaultDocumentEvent defaultDocumentEvent = new DefaultDocumentEvent(this, element.getStartOffset(), 1, DocumentEvent.EventType.CHANGE);
            final AttributeSet copyAttributes = element.getAttributes().copyAttributes();
            final MutableAttributeSet set = (MutableAttributeSet)element.getAttributes();
            defaultDocumentEvent.addEdit(new AttributeUndoableEdit(element, copyAttributes, false));
            set.removeAttribute(HTML.Attribute.SRC);
            set.addAttribute(HTML.Attribute.SRC, s);
            defaultDocumentEvent.end();
            this.fireChangedUpdate(defaultDocumentEvent);
            this.fireUndoableEditUpdate(new UndoableEditEvent(this, defaultDocumentEvent));
        }
        finally {
            this.writeUnlock();
        }
    }
    
    private void updateFrameSet(final Element element, final String s) {
        try {
            final int startOffset = element.getStartOffset();
            this.remove(startOffset, element.getEndOffset() - startOffset);
            final SimpleAttributeSet set = new SimpleAttributeSet();
            set.addAttribute(HTML.Attribute.SRC, s);
            set.addAttribute(StyleConstants.NameAttribute, HTML.Tag.FRAME);
            this.insertString(startOffset, " ", set);
        }
        catch (BadLocationException ex) {}
    }
    
    public class HTMLReader extends ParserCallback
    {
        int threshold;
        int offset;
        boolean inParagraph;
        boolean impliedP;
        boolean inPre;
        boolean inTextArea;
        TextAreaDocument textAreaDocument;
        boolean inTitle;
        boolean lastWasNewline;
        boolean emptyAnchor;
        boolean midInsert;
        boolean inBody;
        HTML.Tag insertTag;
        boolean insertInsertTag;
        boolean foundInsertTag;
        int popDepth;
        int pushDepth;
        Map lastMap;
        boolean inStyle;
        String defaultStyle;
        Vector styles;
        boolean inHead;
        boolean isStyleCSS;
        boolean emptyDocument;
        AttributeSet styleAttributes;
        Option option;
        protected Vector parseBuffer;
        protected MutableAttributeSet charAttr;
        Stack charAttrStack;
        Hashtable tagMap;
        int inBlock;
        
        public HTMLReader(final HTMLDocument htmlDocument, final int n) {
            this(htmlDocument, n, 0, 0, null);
        }
        
        public HTMLReader(final HTMLDocument htmlDocument, final int n, final int n2, final int n3, final HTML.Tag tag) {
            this(htmlDocument, n, n2, n3, tag, true);
        }
        
        HTMLReader(final int offset, final int popDepth, final int pushDepth, final HTML.Tag insertTag, final boolean insertInsertTag) {
            this.inParagraph = false;
            this.impliedP = false;
            this.inPre = false;
            this.inTextArea = false;
            this.textAreaDocument = null;
            this.inTitle = false;
            this.lastWasNewline = true;
            this.inStyle = false;
            this.inHead = false;
            this.parseBuffer = new Vector();
            this.charAttr = new SimpleAttributeSet();
            this.charAttrStack = new Stack();
            this.inBlock = 0;
            this.emptyDocument = (HTMLDocument.this.getLength() == 0);
            this.isStyleCSS = "text/css".equals(HTMLDocument.this.getDefaultStyleSheetType());
            this.offset = offset;
            this.threshold = HTMLDocument.this.getTokenThreshold();
            this.tagMap = new Hashtable(57);
            final TagAction tagAction = new TagAction();
            final BlockAction blockAction = new BlockAction();
            final ParagraphAction paragraphAction = new ParagraphAction();
            final CharacterAction characterAction = new CharacterAction();
            final SpecialAction specialAction = new SpecialAction();
            final FormAction formAction = new FormAction();
            final HiddenAction hiddenAction = new HiddenAction();
            final ConvertAction convertAction = new ConvertAction();
            this.tagMap.put(HTML.Tag.A, new AnchorAction());
            this.tagMap.put(HTML.Tag.ADDRESS, characterAction);
            this.tagMap.put(HTML.Tag.APPLET, hiddenAction);
            this.tagMap.put(HTML.Tag.AREA, new AreaAction());
            this.tagMap.put(HTML.Tag.B, convertAction);
            this.tagMap.put(HTML.Tag.BASE, new BaseAction());
            this.tagMap.put(HTML.Tag.BASEFONT, characterAction);
            this.tagMap.put(HTML.Tag.BIG, characterAction);
            this.tagMap.put(HTML.Tag.BLOCKQUOTE, blockAction);
            this.tagMap.put(HTML.Tag.BODY, blockAction);
            this.tagMap.put(HTML.Tag.BR, specialAction);
            this.tagMap.put(HTML.Tag.CAPTION, blockAction);
            this.tagMap.put(HTML.Tag.CENTER, blockAction);
            this.tagMap.put(HTML.Tag.CITE, characterAction);
            this.tagMap.put(HTML.Tag.CODE, characterAction);
            this.tagMap.put(HTML.Tag.DD, blockAction);
            this.tagMap.put(HTML.Tag.DFN, characterAction);
            this.tagMap.put(HTML.Tag.DIR, blockAction);
            this.tagMap.put(HTML.Tag.DIV, blockAction);
            this.tagMap.put(HTML.Tag.DL, blockAction);
            this.tagMap.put(HTML.Tag.DT, paragraphAction);
            this.tagMap.put(HTML.Tag.EM, characterAction);
            this.tagMap.put(HTML.Tag.FONT, convertAction);
            this.tagMap.put(HTML.Tag.FORM, characterAction);
            this.tagMap.put(HTML.Tag.FRAME, specialAction);
            this.tagMap.put(HTML.Tag.FRAMESET, blockAction);
            this.tagMap.put(HTML.Tag.H1, paragraphAction);
            this.tagMap.put(HTML.Tag.H2, paragraphAction);
            this.tagMap.put(HTML.Tag.H3, paragraphAction);
            this.tagMap.put(HTML.Tag.H4, paragraphAction);
            this.tagMap.put(HTML.Tag.H5, paragraphAction);
            this.tagMap.put(HTML.Tag.H6, paragraphAction);
            this.tagMap.put(HTML.Tag.HEAD, new HeadAction());
            this.tagMap.put(HTML.Tag.HR, specialAction);
            this.tagMap.put(HTML.Tag.HTML, blockAction);
            this.tagMap.put(HTML.Tag.I, convertAction);
            this.tagMap.put(HTML.Tag.IMG, specialAction);
            this.tagMap.put(HTML.Tag.INPUT, formAction);
            this.tagMap.put(HTML.Tag.ISINDEX, new IsindexAction());
            this.tagMap.put(HTML.Tag.KBD, characterAction);
            this.tagMap.put(HTML.Tag.LI, blockAction);
            this.tagMap.put(HTML.Tag.LINK, new LinkAction());
            this.tagMap.put(HTML.Tag.MAP, new MapAction());
            this.tagMap.put(HTML.Tag.MENU, blockAction);
            this.tagMap.put(HTML.Tag.META, new MetaAction());
            this.tagMap.put(HTML.Tag.NOFRAMES, blockAction);
            this.tagMap.put(HTML.Tag.OBJECT, specialAction);
            this.tagMap.put(HTML.Tag.OL, blockAction);
            this.tagMap.put(HTML.Tag.OPTION, formAction);
            this.tagMap.put(HTML.Tag.P, paragraphAction);
            this.tagMap.put(HTML.Tag.PARAM, new ObjectAction());
            this.tagMap.put(HTML.Tag.PRE, new PreAction());
            this.tagMap.put(HTML.Tag.SAMP, characterAction);
            this.tagMap.put(HTML.Tag.SCRIPT, hiddenAction);
            this.tagMap.put(HTML.Tag.SELECT, formAction);
            this.tagMap.put(HTML.Tag.SMALL, characterAction);
            this.tagMap.put(HTML.Tag.STRIKE, convertAction);
            this.tagMap.put(HTML.Tag.S, characterAction);
            this.tagMap.put(HTML.Tag.STRONG, characterAction);
            this.tagMap.put(HTML.Tag.STYLE, new StyleAction());
            this.tagMap.put(HTML.Tag.SUB, convertAction);
            this.tagMap.put(HTML.Tag.SUP, convertAction);
            this.tagMap.put(HTML.Tag.TABLE, blockAction);
            this.tagMap.put(HTML.Tag.TD, blockAction);
            this.tagMap.put(HTML.Tag.TEXTAREA, formAction);
            this.tagMap.put(HTML.Tag.TH, blockAction);
            this.tagMap.put(HTML.Tag.TITLE, new TitleAction());
            this.tagMap.put(HTML.Tag.TR, blockAction);
            this.tagMap.put(HTML.Tag.TT, characterAction);
            this.tagMap.put(HTML.Tag.U, convertAction);
            this.tagMap.put(HTML.Tag.UL, blockAction);
            this.tagMap.put(HTML.Tag.VAR, characterAction);
            this.tagMap.put(HTMLDocument.EndOfLineTag, new EndOfLineAction());
            HTMLDocument.this.putProperty("AdditionalComments", null);
            if (insertTag != null) {
                this.insertTag = insertTag;
                this.popDepth = popDepth;
                this.pushDepth = pushDepth;
                this.insertInsertTag = insertInsertTag;
                this.foundInsertTag = false;
            }
            else {
                this.foundInsertTag = true;
            }
            this.midInsert = (!this.emptyDocument && insertTag == null);
            if (this.midInsert) {
                this.generateEndsSpecsForMidInsert();
            }
        }
        
        void addCSSRules(final String s) {
            HTMLDocument.this.getStyleSheet().addRule(s);
        }
        
        protected void addContent(final char[] array, final int n, final int n2) {
            this.addContent(array, n, n2, true);
        }
        
        protected void addContent(final char[] array, final int n, final int n2, final boolean b) {
            if (!this.foundInsertTag) {
                return;
            }
            if (b && !this.inParagraph && !this.inPre) {
                this.blockOpen(HTML.Tag.IMPLIED, new SimpleAttributeSet());
                this.inParagraph = true;
                this.impliedP = true;
            }
            this.emptyAnchor = false;
            this.charAttr.addAttribute(StyleConstants.NameAttribute, HTML.Tag.CONTENT);
            this.parseBuffer.addElement(new ElementSpec(this.charAttr.copyAttributes(), (short)3, array, n, n2));
            if (this.parseBuffer.size() > this.threshold) {
                try {
                    this.flushBuffer();
                }
                catch (BadLocationException ex) {}
            }
            if (n2 > 0) {
                this.lastWasNewline = (array[n + n2 - 1] == '\n');
            }
        }
        
        protected void addSpecialElement(final HTML.Tag tag, final MutableAttributeSet set) {
            if (tag != HTML.Tag.FRAME && !this.inParagraph && !this.inPre) {
                this.blockOpen(HTML.Tag.IMPLIED, new SimpleAttributeSet());
                this.inParagraph = true;
                this.impliedP = true;
            }
            if (!this.foundInsertTag) {
                if (!this.isInsertTag(tag)) {
                    return;
                }
                this.foundInsertTag();
                if (!this.insertInsertTag) {
                    return;
                }
            }
            this.emptyAnchor = false;
            set.addAttributes(this.charAttr);
            set.addAttribute(StyleConstants.NameAttribute, tag);
            this.parseBuffer.addElement(new ElementSpec(set.copyAttributes(), (short)3, new char[] { ' ' }, 0, 1));
            if (tag == HTML.Tag.FRAME) {
                this.lastWasNewline = true;
            }
        }
        
        private void adjustEndElement() {
            final int length = HTMLDocument.this.getLength();
            if (length == 0) {
                return;
            }
            HTMLDocument.this.obtainLock();
            try {
                final Element[] pathTo = this.getPathTo(length - 1);
                if (pathTo.length > 2 && pathTo[1].getAttributes().getAttribute(StyleConstants.NameAttribute) == HTML.Tag.BODY && pathTo[1].getEndOffset() == length) {
                    final String text = HTMLDocument.this.getText(length - 1, 1);
                    final Element[] array = new Element[0];
                    final Element[] array2 = { null };
                    final int elementIndex = pathTo[0].getElementIndex(length);
                    array2[0] = pathTo[0].getElement(elementIndex);
                    ((BranchElement)pathTo[0]).replace(elementIndex, 1, array);
                    final ElementEdit elementEdit = new ElementEdit(pathTo[0], elementIndex, array2, array);
                    DefaultDocumentEvent defaultDocumentEvent;
                    if (pathTo.length == 3 && pathTo[2].getAttributes().getAttribute(StyleConstants.NameAttribute) == HTML.Tag.P && !text.equals("\n")) {
                        final int elementIndex2 = pathTo[2].getElementIndex(length - 1);
                        final AttributeSet attributes = pathTo[2].getElement(elementIndex2).getAttributes();
                        if (attributes.getAttributeCount() == 1 && attributes.getAttribute(StyleConstants.NameAttribute) == HTML.Tag.CONTENT) {
                            final Element[] array3 = { null };
                            final Element[] array4 = { pathTo[2].getElement(elementIndex2) };
                            final int startOffset = array4[0].getStartOffset();
                            array3[0] = HTMLDocument.this.createLeafElement(pathTo[2], attributes, startOffset, length + 1);
                            ((BranchElement)pathTo[2]).replace(elementIndex2, 1, array3);
                            final HTMLDocument this$0 = HTMLDocument.this;
                            this$0.getClass();
                            defaultDocumentEvent = new DefaultDocumentEvent(this$0, startOffset, length - startOffset + 1, DocumentEvent.EventType.CHANGE);
                            defaultDocumentEvent.addEdit(new ElementEdit(pathTo[2], elementIndex2, array4, array3));
                        }
                        else {
                            final SimpleAttributeSet set = new SimpleAttributeSet();
                            set.addAttribute(StyleConstants.NameAttribute, HTML.Tag.CONTENT);
                            final Element[] array5 = { HTMLDocument.this.createLeafElement(pathTo[2], set, length, length + 1) };
                            ((BranchElement)pathTo[2]).replace(elementIndex2 + 1, 0, array5);
                            final HTMLDocument this$2 = HTMLDocument.this;
                            this$2.getClass();
                            defaultDocumentEvent = new DefaultDocumentEvent(this$2, length, 1, DocumentEvent.EventType.CHANGE);
                            defaultDocumentEvent.addEdit(new ElementEdit(pathTo[2], elementIndex2 + 1, new Element[0], array5));
                        }
                    }
                    else {
                        final SimpleAttributeSet set2 = new SimpleAttributeSet();
                        set2.addAttribute(StyleConstants.NameAttribute, HTML.Tag.P);
                        final BranchElement branchElement = (BranchElement)HTMLDocument.this.createBranchElement(pathTo[1], set2);
                        final Element[] array6 = { branchElement };
                        final Element[] array7 = new Element[0];
                        final int n = pathTo[1].getElementIndex(length - 1) + 1;
                        ((BranchElement)pathTo[1]).replace(n, 0, array6);
                        final HTMLDocument this$3 = HTMLDocument.this;
                        this$3.getClass();
                        defaultDocumentEvent = new DefaultDocumentEvent(this$3, length, 1, DocumentEvent.EventType.CHANGE);
                        defaultDocumentEvent.addEdit(new ElementEdit(pathTo[1], n, array7, array6));
                        final Element[] array8 = { null };
                        final SimpleAttributeSet set3 = new SimpleAttributeSet();
                        set3.addAttribute(StyleConstants.NameAttribute, HTML.Tag.CONTENT);
                        array8[0] = HTMLDocument.this.createLeafElement(branchElement, set3, length, length + 1);
                        branchElement.replace(0, 0, array8);
                    }
                    defaultDocumentEvent.addEdit(elementEdit);
                    defaultDocumentEvent.end();
                    HTMLDocument.this.fireChangedUpdate(defaultDocumentEvent);
                    HTMLDocument.this.fireUndoableEditUpdate(new UndoableEditEvent(this, defaultDocumentEvent));
                }
            }
            catch (BadLocationException ex) {}
            finally {
                HTMLDocument.this.releaseLock();
            }
        }
        
        protected void blockClose(final HTML.Tag tag) {
            if (!this.foundInsertTag) {
                return;
            }
            if (!this.lastWasNewline) {
                this.addContent(HTMLDocument.NEWLINE, 0, 1, this.insertTag != null);
                this.lastWasNewline = true;
            }
            if (this.impliedP) {
                this.impliedP = false;
                this.inParagraph = false;
                this.blockClose(HTML.Tag.IMPLIED);
            }
            --this.inBlock;
            final ElementSpec elementSpec = (this.parseBuffer.size() > 0) ? this.parseBuffer.lastElement() : null;
            if (elementSpec != null && elementSpec.getType() == 1) {
                this.addContent(new char[] { ' ' }, 0, 1);
            }
            this.parseBuffer.addElement(new ElementSpec(null, (short)2));
        }
        
        protected void blockOpen(final HTML.Tag tag, final MutableAttributeSet set) {
            if (this.impliedP) {
                this.impliedP = false;
                this.inParagraph = false;
                this.blockClose(HTML.Tag.IMPLIED);
            }
            ++this.inBlock;
            if (!this.foundInsertTag) {
                if (!this.isInsertTag(tag)) {
                    return;
                }
                this.foundInsertTag();
                if (!this.insertInsertTag) {
                    return;
                }
            }
            this.lastWasNewline = false;
            set.addAttribute(StyleConstants.NameAttribute, tag);
            this.parseBuffer.addElement(new ElementSpec(set.copyAttributes(), (short)1));
        }
        
        private int depthTo(final int n) {
            Element element = HTMLDocument.this.getDefaultRootElement();
            int n2 = 0;
            while (!element.isLeaf()) {
                ++n2;
                element = element.getElement(element.getElementIndex(n));
            }
            return n2;
        }
        
        public void flush() throws BadLocationException {
            this.flushBuffer();
            if (this.emptyDocument) {
                this.adjustEndElement();
            }
        }
        
        void flushBuffer() throws BadLocationException {
            final int length = HTMLDocument.this.getLength();
            final ElementSpec[] array = new ElementSpec[this.parseBuffer.size()];
            this.parseBuffer.copyInto(array);
            if (length == 0 && this.insertTag == null) {
                HTMLDocument.this.create(array);
            }
            else {
                HTMLDocument.this.insert(this.offset, array);
            }
            this.parseBuffer.removeAllElements();
            this.offset += HTMLDocument.this.getLength() - length;
        }
        
        private void foundInsertTag() {
            this.foundInsertTag = true;
            Label_0227: {
                if (this.popDepth <= 0) {
                    if (this.pushDepth <= 0) {
                        break Label_0227;
                    }
                }
                try {
                    if (this.offset == 0 || !HTMLDocument.this.getText(this.offset - 1, 1).equals("\n")) {
                        SimpleAttributeSet set = null;
                        boolean b = true;
                        if (this.offset != 0) {
                            final AttributeSet attributes = HTMLDocument.this.getCharacterElement(this.offset - 1).getAttributes();
                            if (attributes.isDefined(StyleConstants.ComposedTextAttribute)) {
                                b = false;
                            }
                            else {
                                final Object attribute = attributes.getAttribute(StyleConstants.NameAttribute);
                                if (attribute instanceof HTML.Tag) {
                                    final HTML.Tag tag = (HTML.Tag)attribute;
                                    if (tag == HTML.Tag.IMG || tag == HTML.Tag.HR || tag == HTML.Tag.COMMENT || tag instanceof HTML.UnknownTag) {
                                        b = false;
                                    }
                                }
                            }
                        }
                        if (!b) {
                            set = new SimpleAttributeSet();
                            set.addAttribute(StyleConstants.NameAttribute, HTML.Tag.CONTENT);
                        }
                        final ElementSpec elementSpec = new ElementSpec(set, (short)3, HTMLDocument.NEWLINE, 0, HTMLDocument.NEWLINE.length);
                        if (b) {
                            elementSpec.setDirection((short)4);
                        }
                        this.parseBuffer.addElement(elementSpec);
                    }
                }
                catch (BadLocationException ex) {}
            }
            for (int i = 0; i < this.popDepth; ++i) {
                this.parseBuffer.addElement(new ElementSpec(null, (short)2));
            }
            for (int j = 0; j < this.pushDepth; ++j) {
                final ElementSpec elementSpec2 = new ElementSpec(null, (short)1);
                elementSpec2.setDirection((short)5);
                this.parseBuffer.addElement(elementSpec2);
            }
        }
        
        private void generateEndsSpecsForMidInsert() {
            int n = this.heightToElementWithName(HTML.Tag.BODY, Math.max(0, this.offset - 1));
            boolean b = false;
            if (n == -1 && this.offset > 0) {
                n = this.heightToElementWithName(HTML.Tag.BODY, this.offset);
                if (n != -1) {
                    n = this.depthTo(this.offset - 1) - 1;
                    b = true;
                }
            }
            if (n == -1) {
                throw new RuntimeException("Must insert new content into body element-");
            }
            if (n != -1) {
                try {
                    if (!b && this.offset > 0 && !HTMLDocument.this.getText(this.offset - 1, 1).equals("\n")) {
                        final SimpleAttributeSet set = new SimpleAttributeSet();
                        set.addAttribute(StyleConstants.NameAttribute, HTML.Tag.CONTENT);
                        this.parseBuffer.addElement(new ElementSpec(set, (short)3, HTMLDocument.NEWLINE, 0, 1));
                    }
                }
                catch (BadLocationException ex) {}
                while (n-- > 0) {
                    this.parseBuffer.addElement(new ElementSpec(null, (short)2));
                }
                if (b) {
                    final ElementSpec elementSpec = new ElementSpec(null, (short)1);
                    elementSpec.setDirection((short)5);
                    this.parseBuffer.addElement(elementSpec);
                }
            }
        }
        
        private Element[] getPathTo(final int n) {
            final Stack stack = new Stack<Element>();
            for (Element element = HTMLDocument.this.getDefaultRootElement(); !element.isLeaf(); element = element.getElement(element.getElementIndex(n))) {
                stack.push(element);
            }
            final Element[] array = new Element[stack.size()];
            stack.copyInto(array);
            return array;
        }
        
        public void handleComment(final char[] array, final int n) {
            if (this.inStyle) {
                if (this.styles != null) {
                    this.styles.addElement(new String(array));
                }
            }
            else if (HTMLDocument.this.getPreservesUnknownTags()) {
                if (this.inBlock == 0) {
                    Object property = HTMLDocument.this.getProperty("AdditionalComments");
                    if (property != null && !(property instanceof Vector)) {
                        return;
                    }
                    if (property == null) {
                        property = new Vector<Object>();
                        HTMLDocument.this.putProperty("AdditionalComments", property);
                    }
                    ((Vector<Object>)property).addElement(new String(array));
                }
                else {
                    final SimpleAttributeSet set = new SimpleAttributeSet();
                    set.addAttribute(HTML.Attribute.COMMENT, new String(array));
                    this.addSpecialElement(HTML.Tag.COMMENT, set);
                }
            }
        }
        
        public void handleEndTag(final HTML.Tag tag, final int n) {
            if (this.midInsert && !this.inBody) {
                return;
            }
            if (tag == HTML.Tag.BODY) {
                this.inBody = false;
                if (this.midInsert) {
                    --this.inBlock;
                }
            }
            final TagAction tagAction = this.tagMap.get(tag);
            if (tagAction != null) {
                tagAction.end(tag);
            }
        }
        
        public void handleSimpleTag(final HTML.Tag tag, final MutableAttributeSet set, final int n) {
            if (this.midInsert && !this.inBody) {
                return;
            }
            if (this.isStyleCSS && set.isDefined(HTML.Attribute.STYLE)) {
                final String s = (String)set.getAttribute(HTML.Attribute.STYLE);
                set.removeAttribute(HTML.Attribute.STYLE);
                set.addAttributes(this.styleAttributes = HTMLDocument.this.getStyleSheet().getDeclaration(s));
            }
            else {
                this.styleAttributes = null;
            }
            final TagAction tagAction = this.tagMap.get(tag);
            if (tagAction != null) {
                tagAction.start(tag, set);
                tagAction.end(tag);
            }
            else if (HTMLDocument.this.getPreservesUnknownTags()) {
                this.addSpecialElement(tag, set);
            }
        }
        
        public void handleStartTag(final HTML.Tag tag, final MutableAttributeSet set, final int n) {
            if (this.midInsert && !this.inBody) {
                if (tag == HTML.Tag.BODY) {
                    this.inBody = true;
                    ++this.inBlock;
                }
                return;
            }
            if (!this.inBody && tag == HTML.Tag.BODY) {
                this.inBody = true;
            }
            if (this.isStyleCSS && set.isDefined(HTML.Attribute.STYLE)) {
                final String s = (String)set.getAttribute(HTML.Attribute.STYLE);
                set.removeAttribute(HTML.Attribute.STYLE);
                set.addAttributes(this.styleAttributes = HTMLDocument.this.getStyleSheet().getDeclaration(s));
            }
            else {
                this.styleAttributes = null;
            }
            final TagAction tagAction = this.tagMap.get(tag);
            if (tagAction != null) {
                tagAction.start(tag, set);
            }
        }
        
        public void handleText(final char[] array, final int n) {
            if (this.midInsert && !this.inBody) {
                return;
            }
            if (this.inTextArea) {
                this.textAreaContent(array);
            }
            else if (this.inPre) {
                this.preContent(array);
            }
            else if (this.inTitle) {
                HTMLDocument.this.putProperty("title", new String(array));
            }
            else if (this.option != null) {
                this.option.setLabel(new String(array));
            }
            else if (this.inStyle) {
                if (this.styles != null) {
                    this.styles.addElement(new String(array));
                }
            }
            else if (this.inBlock > 0 && array.length >= 1) {
                this.addContent(array, 0, array.length);
            }
        }
        
        private int heightToElementWithName(final Object o, final int n) {
            Element element = HTMLDocument.this.getCharacterElement(n).getParentElement();
            int n2 = 0;
            while (element != null && element.getAttributes().getAttribute(StyleConstants.NameAttribute) != o) {
                ++n2;
                element = element.getParentElement();
            }
            return (element == null) ? -1 : n2;
        }
        
        private boolean isInsertTag(final HTML.Tag tag) {
            return this.insertTag == tag || (tag == HTML.Tag.IMPLIED && tag == HTML.Tag.P);
        }
        
        void linkCSSStyleSheet(final String s) {
            URL url;
            try {
                url = new URL(HTMLDocument.this.base, s);
            }
            catch (MalformedURLException ex) {
                try {
                    url = new URL(s);
                }
                catch (MalformedURLException ex2) {
                    url = null;
                }
            }
            if (url != null) {
                HTMLDocument.this.getStyleSheet().importStyleSheet(url);
            }
        }
        
        protected void popCharacterStyle() {
            if (!this.charAttrStack.empty()) {
                this.charAttr = this.charAttrStack.peek();
                this.charAttrStack.pop();
            }
        }
        
        protected void preContent(final char[] array) {
            int n = 0;
            for (int i = 0; i < array.length; ++i) {
                if (array[i] == '\n') {
                    this.addContent(array, n, i - n + 1);
                    this.blockClose(HTML.Tag.IMPLIED);
                    final SimpleAttributeSet set = new SimpleAttributeSet();
                    set.addAttribute(CSS.Attribute.WHITE_SPACE, "pre");
                    this.blockOpen(HTML.Tag.IMPLIED, set);
                    n = i + 1;
                }
            }
            if (n < array.length) {
                this.addContent(array, n, array.length - n);
            }
        }
        
        protected void pushCharacterStyle() {
            this.charAttrStack.push(this.charAttr.copyAttributes());
        }
        
        protected void registerTag(final HTML.Tag tag, final TagAction tagAction) {
            this.tagMap.put(tag, tagAction);
        }
        
        protected void textAreaContent(final char[] array) {
            try {
                this.textAreaDocument.insertString(this.textAreaDocument.getLength(), new String(array), null);
            }
            catch (BadLocationException ex) {}
        }
        
        public class TagAction
        {
            public void end(final HTML.Tag tag) {
            }
            
            public void start(final HTML.Tag tag, final MutableAttributeSet set) {
            }
        }
        
        public class BlockAction extends TagAction
        {
            public void end(final HTML.Tag tag) {
                HTMLReader.this.blockClose(tag);
            }
            
            public void start(final HTML.Tag tag, final MutableAttributeSet set) {
                HTMLReader.this.blockOpen(tag, set);
            }
        }
        
        public class ParagraphAction extends BlockAction
        {
            public void end(final HTML.Tag tag) {
                super.end(tag);
                HTMLReader.this.inParagraph = false;
            }
            
            public void start(final HTML.Tag tag, final MutableAttributeSet set) {
                super.start(tag, set);
                HTMLReader.this.inParagraph = true;
            }
        }
        
        public class SpecialAction extends TagAction
        {
            public void start(final HTML.Tag tag, final MutableAttributeSet set) {
                HTMLReader.this.addSpecialElement(tag, set);
            }
        }
        
        public class IsindexAction extends TagAction
        {
            public void start(final HTML.Tag tag, final MutableAttributeSet set) {
                HTMLReader.this.blockOpen(HTML.Tag.IMPLIED, new SimpleAttributeSet());
                HTMLReader.this.addSpecialElement(tag, set);
                HTMLReader.this.blockClose(HTML.Tag.IMPLIED);
            }
        }
        
        public class HiddenAction extends TagAction
        {
            public void end(final HTML.Tag tag) {
                if (!this.isEmpty(tag)) {
                    final SimpleAttributeSet set = new SimpleAttributeSet();
                    set.addAttribute(HTML.Attribute.ENDTAG, "true");
                    HTMLReader.this.addSpecialElement(tag, set);
                }
            }
            
            boolean isEmpty(final HTML.Tag tag) {
                return tag != HTML.Tag.APPLET && tag != HTML.Tag.SCRIPT;
            }
            
            public void start(final HTML.Tag tag, final MutableAttributeSet set) {
                HTMLReader.this.addSpecialElement(tag, set);
            }
        }
        
        class MetaAction extends HiddenAction
        {
            boolean isEmpty(final HTML.Tag tag) {
                return true;
            }
            
            public void start(final HTML.Tag tag, final MutableAttributeSet set) {
                final Object attribute = set.getAttribute(HTML.Attribute.HTTPEQUIV);
                if (attribute != null) {
                    final String lowerCase = ((String)attribute).toLowerCase();
                    if (lowerCase.equals("content-style-type")) {
                        HTMLDocument.this.setDefaultStyleSheetType((String)set.getAttribute(HTML.Attribute.CONTENT));
                        HTMLReader.this.isStyleCSS = "text/css".equals(HTMLDocument.this.getDefaultStyleSheetType());
                    }
                    else if (lowerCase.equals("default-style")) {
                        HTMLReader.this.defaultStyle = (String)set.getAttribute(HTML.Attribute.CONTENT);
                    }
                }
                super.start(tag, set);
            }
        }
        
        class HeadAction extends HiddenAction
        {
            public void end(final HTML.Tag tag) {
                final HTMLReader this$1 = HTMLReader.this;
                final HTMLReader this$2 = HTMLReader.this;
                final boolean b = false;
                this$2.inStyle = b;
                this$1.inHead = b;
                if (HTMLReader.this.styles != null) {
                    final boolean isStyleCSS = HTMLReader.this.isStyleCSS;
                    for (int i = 0, size = HTMLReader.this.styles.size(); i < size; ++i) {
                        if (HTMLReader.this.styles.elementAt(i) == HTML.Tag.LINK) {
                            this.handleLink((AttributeSet)HTMLReader.this.styles.elementAt(++i));
                        }
                        else {
                            final String s = HTMLReader.this.styles.elementAt(++i);
                            final boolean b2 = (s == null) ? isStyleCSS : s.equals("text/css");
                            while (++i < size && HTMLReader.this.styles.elementAt(i) instanceof String) {
                                if (b2) {
                                    HTMLReader.this.addCSSRules((String)HTMLReader.this.styles.elementAt(i));
                                }
                            }
                        }
                    }
                }
                if (HTMLReader.this.insertTag == null || HTMLReader.this.insertTag == HTML.Tag.HEAD) {
                    super.end(tag);
                }
            }
            
            private void handleLink(final AttributeSet set) {
                String defaultStyleSheetType = (String)set.getAttribute(HTML.Attribute.TYPE);
                if (defaultStyleSheetType == null) {
                    defaultStyleSheetType = HTMLDocument.this.getDefaultStyleSheetType();
                }
                if (defaultStyleSheetType.equals("text/css")) {
                    final String s = (String)set.getAttribute(HTML.Attribute.REL);
                    final String s2 = (String)set.getAttribute(HTML.Attribute.TITLE);
                    final String s3 = (String)set.getAttribute(HTML.Attribute.MEDIA);
                    String lowerCase;
                    if (s3 == null) {
                        lowerCase = "all";
                    }
                    else {
                        lowerCase = s3.toLowerCase();
                    }
                    if (s != null) {
                        final String lowerCase2 = s.toLowerCase();
                        if ((lowerCase.indexOf("all") != -1 || lowerCase.indexOf("screen") != -1) && (lowerCase2.equals("stylesheet") || (lowerCase2.equals("alternate stylesheet") && s2.equals(HTMLReader.this.defaultStyle)))) {
                            HTMLReader.this.linkCSSStyleSheet((String)set.getAttribute(HTML.Attribute.HREF));
                        }
                    }
                }
            }
            
            boolean isEmpty(final HTML.Tag tag) {
                return false;
            }
            
            public void start(final HTML.Tag tag, final MutableAttributeSet set) {
                HTMLReader.this.inHead = true;
                if (HTMLReader.this.insertTag == null || HTMLReader.this.insertTag == HTML.Tag.HEAD) {
                    super.start(tag, set);
                }
            }
        }
        
        class LinkAction extends HiddenAction
        {
            public void start(final HTML.Tag tag, final MutableAttributeSet set) {
                final String s = (String)set.getAttribute(HTML.Attribute.REL);
                if (s != null) {
                    final String lowerCase = s.toLowerCase();
                    if (lowerCase.equals("stylesheet") || lowerCase.equals("alternate stylesheet")) {
                        if (HTMLReader.this.styles == null) {
                            HTMLReader.this.styles = new Vector(3);
                        }
                        HTMLReader.this.styles.addElement(tag);
                        HTMLReader.this.styles.addElement(set.copyAttributes());
                    }
                }
                super.start(tag, set);
            }
        }
        
        class MapAction extends TagAction
        {
            public void end(final HTML.Tag tag) {
            }
            
            public void start(final HTML.Tag tag, final MutableAttributeSet set) {
                HTMLReader.this.lastMap = new Map((String)set.getAttribute(HTML.Attribute.NAME));
                HTMLDocument.this.addMap(HTMLReader.this.lastMap);
            }
        }
        
        class AreaAction extends TagAction
        {
            public void end(final HTML.Tag tag) {
            }
            
            public void start(final HTML.Tag tag, final MutableAttributeSet set) {
                if (HTMLReader.this.lastMap != null) {
                    HTMLReader.this.lastMap.addArea(set.copyAttributes());
                }
            }
        }
        
        class StyleAction extends TagAction
        {
            public void end(final HTML.Tag tag) {
                HTMLReader.this.inStyle = false;
            }
            
            boolean isEmpty(final HTML.Tag tag) {
                return false;
            }
            
            public void start(final HTML.Tag tag, final MutableAttributeSet set) {
                if (HTMLReader.this.inHead) {
                    if (HTMLReader.this.styles == null) {
                        HTMLReader.this.styles = new Vector(3);
                    }
                    HTMLReader.this.styles.addElement(tag);
                    HTMLReader.this.styles.addElement(set.getAttribute(HTML.Attribute.TYPE));
                    HTMLReader.this.inStyle = true;
                }
            }
        }
        
        public class PreAction extends BlockAction
        {
            public void end(final HTML.Tag tag) {
                HTMLReader.this.blockClose(HTML.Tag.IMPLIED);
                HTMLReader.this.inPre = false;
                HTMLReader.this.blockClose(tag);
            }
            
            public void start(final HTML.Tag tag, final MutableAttributeSet set) {
                HTMLReader.this.inPre = true;
                HTMLReader.this.blockOpen(tag, set);
                set.addAttribute(CSS.Attribute.WHITE_SPACE, "pre");
                HTMLReader.this.blockOpen(HTML.Tag.IMPLIED, set);
            }
        }
        
        public class CharacterAction extends TagAction
        {
            public void end(final HTML.Tag tag) {
                HTMLReader.this.popCharacterStyle();
                if (tag == HTML.Tag.FORM) {
                    HTMLDocument.access$2(HTMLDocument.this, null);
                }
            }
            
            public void start(final HTML.Tag tag, final MutableAttributeSet set) {
                HTMLReader.this.pushCharacterStyle();
                HTMLReader.this.charAttr.addAttribute(tag, set.copyAttributes());
                if (HTMLReader.this.styleAttributes != null) {
                    HTMLReader.this.charAttr.addAttributes(HTMLReader.this.styleAttributes);
                }
                if (tag == HTML.Tag.FORM) {
                    HTMLDocument.access$2(HTMLDocument.this, new ButtonGroup());
                }
            }
        }
        
        class ConvertAction extends TagAction
        {
            public void end(final HTML.Tag tag) {
                HTMLReader.this.popCharacterStyle();
            }
            
            public void start(final HTML.Tag tag, final MutableAttributeSet set) {
                HTMLReader.this.pushCharacterStyle();
                if (HTMLReader.this.styleAttributes != null) {
                    HTMLReader.this.charAttr.addAttributes(HTMLReader.this.styleAttributes);
                }
                final StyleSheet styleSheet = HTMLDocument.this.getStyleSheet();
                if (tag == HTML.Tag.B) {
                    styleSheet.addCSSAttribute(HTMLReader.this.charAttr, CSS.Attribute.FONT_WEIGHT, "bold");
                }
                else if (tag == HTML.Tag.I) {
                    styleSheet.addCSSAttribute(HTMLReader.this.charAttr, CSS.Attribute.FONT_STYLE, "italic");
                }
                else if (tag == HTML.Tag.U) {
                    final Object attribute = HTMLReader.this.charAttr.getAttribute(CSS.Attribute.TEXT_DECORATION);
                    final String s = "underline";
                    styleSheet.addCSSAttribute(HTMLReader.this.charAttr, CSS.Attribute.TEXT_DECORATION, (attribute != null) ? (String.valueOf(s) + "," + attribute.toString()) : s);
                }
                else if (tag == HTML.Tag.STRIKE) {
                    final Object attribute2 = HTMLReader.this.charAttr.getAttribute(CSS.Attribute.TEXT_DECORATION);
                    final String s2 = "line-through";
                    styleSheet.addCSSAttribute(HTMLReader.this.charAttr, CSS.Attribute.TEXT_DECORATION, (attribute2 != null) ? (String.valueOf(s2) + "," + attribute2.toString()) : s2);
                }
                else if (tag == HTML.Tag.SUP) {
                    final Object attribute3 = HTMLReader.this.charAttr.getAttribute(CSS.Attribute.VERTICAL_ALIGN);
                    final String s3 = "sup";
                    styleSheet.addCSSAttribute(HTMLReader.this.charAttr, CSS.Attribute.VERTICAL_ALIGN, (attribute3 != null) ? (String.valueOf(s3) + "," + attribute3.toString()) : s3);
                }
                else if (tag == HTML.Tag.SUB) {
                    final Object attribute4 = HTMLReader.this.charAttr.getAttribute(CSS.Attribute.VERTICAL_ALIGN);
                    final String s4 = "sub";
                    styleSheet.addCSSAttribute(HTMLReader.this.charAttr, CSS.Attribute.VERTICAL_ALIGN, (attribute4 != null) ? (String.valueOf(s4) + "," + attribute4.toString()) : s4);
                }
                else if (tag == HTML.Tag.FONT) {
                    final String s5 = (String)set.getAttribute(HTML.Attribute.COLOR);
                    if (s5 != null) {
                        styleSheet.addCSSAttribute(HTMLReader.this.charAttr, CSS.Attribute.COLOR, s5);
                    }
                    final String s6 = (String)set.getAttribute(HTML.Attribute.FACE);
                    if (s6 != null) {
                        styleSheet.addCSSAttribute(HTMLReader.this.charAttr, CSS.Attribute.FONT_FAMILY, s6);
                    }
                    final String s7 = (String)set.getAttribute(HTML.Attribute.SIZE);
                    if (s7 != null) {
                        styleSheet.addCSSAttributeFromHTML(HTMLReader.this.charAttr, CSS.Attribute.FONT_SIZE, s7);
                    }
                }
            }
        }
        
        class AnchorAction extends CharacterAction
        {
            public void end(final HTML.Tag tag) {
                if (HTMLReader.this.emptyAnchor) {
                    HTMLReader.this.addContent(new char[] { ' ' }, 0, 1);
                }
                super.end(tag);
            }
            
            public void start(final HTML.Tag tag, final MutableAttributeSet set) {
                HTMLReader.this.emptyAnchor = true;
                super.start(tag, set);
            }
        }
        
        class TitleAction extends HiddenAction
        {
            public void end(final HTML.Tag tag) {
                HTMLReader.this.inTitle = false;
                super.end(tag);
            }
            
            boolean isEmpty(final HTML.Tag tag) {
                return false;
            }
            
            public void start(final HTML.Tag tag, final MutableAttributeSet set) {
                HTMLReader.this.inTitle = true;
                super.start(tag, set);
            }
        }
        
        class BaseAction extends TagAction
        {
            public void start(final HTML.Tag tag, final MutableAttributeSet set) {
                final String s = (String)set.getAttribute(HTML.Attribute.HREF);
                if (s != null) {
                    try {
                        HTMLDocument.this.base = new URL(HTMLDocument.this.base, s);
                        HTMLDocument.this.getStyleSheet().setBase(HTMLDocument.this.base);
                    }
                    catch (MalformedURLException ex) {}
                }
            }
        }
        
        class ObjectAction extends SpecialAction
        {
            void addParameter(final AttributeSet set) {
                final String s = (String)set.getAttribute(HTML.Attribute.NAME);
                final String s2 = (String)set.getAttribute(HTML.Attribute.VALUE);
                if (s != null && s2 != null) {
                    ((MutableAttributeSet)((ElementSpec)HTMLReader.this.parseBuffer.lastElement()).getAttributes()).addAttribute(s, s2);
                }
            }
            
            public void end(final HTML.Tag tag) {
                if (tag != HTML.Tag.PARAM) {
                    super.end(tag);
                }
            }
            
            public void start(final HTML.Tag tag, final MutableAttributeSet set) {
                if (tag == HTML.Tag.PARAM) {
                    this.addParameter(set);
                }
                else {
                    super.start(tag, set);
                }
            }
        }
        
        public class FormAction extends SpecialAction
        {
            Object selectModel;
            int optionCount;
            
            public void end(final HTML.Tag tag) {
                if (tag == HTML.Tag.OPTION) {
                    HTMLReader.this.option = null;
                }
                else {
                    if (tag == HTML.Tag.SELECT) {
                        this.selectModel = null;
                        this.optionCount = 0;
                    }
                    else if (tag == HTML.Tag.TEXTAREA) {
                        HTMLReader.this.inTextArea = false;
                        HTMLReader.this.textAreaDocument.storeInitialText();
                    }
                    super.end(tag);
                }
            }
            
            void setModel(final String s, final MutableAttributeSet set) {
                if (s.equals("submit") || s.equals("reset") || s.equals("image")) {
                    set.addAttribute(StyleConstants.ModelAttribute, new DefaultButtonModel());
                }
                else if (s.equals("text") || s.equals("password")) {
                    set.addAttribute(StyleConstants.ModelAttribute, new PlainDocument());
                }
                else if (s.equals("checkbox") || s.equals("radio")) {
                    final JToggleButton.ToggleButtonModel toggleButtonModel = new JToggleButton.ToggleButtonModel();
                    if (s.equals("radio")) {
                        toggleButtonModel.setGroup(HTMLDocument.this.radioButtonGroup);
                    }
                    set.addAttribute(StyleConstants.ModelAttribute, toggleButtonModel);
                }
            }
            
            public void start(final HTML.Tag tag, final MutableAttributeSet set) {
                if (tag == HTML.Tag.INPUT) {
                    String s = (String)set.getAttribute(HTML.Attribute.TYPE);
                    if (s == null) {
                        s = "text";
                        set.addAttribute(HTML.Attribute.TYPE, "text");
                    }
                    this.setModel(s, set);
                }
                else if (tag == HTML.Tag.TEXTAREA) {
                    HTMLReader.this.inTextArea = true;
                    HTMLReader.this.textAreaDocument = new TextAreaDocument();
                    set.addAttribute(StyleConstants.ModelAttribute, HTMLReader.this.textAreaDocument);
                }
                else if (tag == HTML.Tag.SELECT) {
                    final int integerAttributeValue = HTML.getIntegerAttributeValue(set, HTML.Attribute.SIZE, 1);
                    final boolean b = set.getAttribute(HTML.Attribute.MULTIPLE) != null;
                    if (integerAttributeValue > 1 || b) {
                        final OptionListModel selectModel = new OptionListModel();
                        if (b) {
                            selectModel.setSelectionMode(2);
                        }
                        this.selectModel = selectModel;
                    }
                    else {
                        this.selectModel = new OptionComboBoxModel();
                    }
                    set.addAttribute(StyleConstants.ModelAttribute, this.selectModel);
                }
                if (tag == HTML.Tag.OPTION) {
                    HTMLReader.this.option = new Option(set);
                    if (this.selectModel instanceof OptionListModel) {
                        final OptionListModel optionListModel = (OptionListModel)this.selectModel;
                        optionListModel.addElement(HTMLReader.this.option);
                        if (HTMLReader.this.option.isSelected()) {
                            optionListModel.addSelectionInterval(this.optionCount, this.optionCount);
                            optionListModel.setInitialSelection(this.optionCount);
                        }
                    }
                    else if (this.selectModel instanceof OptionComboBoxModel) {
                        final OptionComboBoxModel optionComboBoxModel = (OptionComboBoxModel)this.selectModel;
                        optionComboBoxModel.addElement(HTMLReader.this.option);
                        if (HTMLReader.this.option.isSelected()) {
                            optionComboBoxModel.setSelectedItem(HTMLReader.this.option);
                            optionComboBoxModel.setInitialSelection(HTMLReader.this.option);
                        }
                    }
                    ++this.optionCount;
                }
                else {
                    super.start(tag, set);
                }
            }
        }
        
        class EndOfLineAction extends TagAction
        {
            public void end(final HTML.Tag tag) {
            }
            
            public void start(final HTML.Tag tag, final MutableAttributeSet set) {
                if (HTMLReader.this.emptyDocument && set != null) {
                    final Object attribute = set.getAttribute("__EndOfLineString__");
                    if (attribute != null && attribute instanceof String) {
                        HTMLDocument.this.putProperty("__EndOfLine__", attribute);
                    }
                }
            }
        }
    }
    
    public abstract static class Iterator
    {
        public abstract AttributeSet getAttributes();
        
        public abstract int getEndOffset();
        
        public abstract int getStartOffset();
        
        public abstract HTML.Tag getTag();
        
        public abstract boolean isValid();
        
        public abstract void next();
    }
    
    static class LeafIterator extends Iterator
    {
        private int endOffset;
        private HTML.Tag tag;
        private ElementIterator pos;
        
        LeafIterator(final HTML.Tag tag, final Document document) {
            this.tag = tag;
            this.pos = new ElementIterator(document);
            this.endOffset = 0;
            this.next();
        }
        
        public AttributeSet getAttributes() {
            final Element current = this.pos.current();
            if (current != null) {
                return (AttributeSet)current.getAttributes().getAttribute(this.tag);
            }
            return null;
        }
        
        public int getEndOffset() {
            return this.endOffset;
        }
        
        public int getStartOffset() {
            final Element current = this.pos.current();
            if (current != null) {
                return current.getStartOffset();
            }
            return -1;
        }
        
        public HTML.Tag getTag() {
            return this.tag;
        }
        
        public boolean isValid() {
            return this.pos.current() != null;
        }
        
        public void next() {
            this.nextLeaf(this.pos);
            while (this.isValid()) {
                if (this.pos.current().getStartOffset() >= this.endOffset && this.pos.current().getAttributes().isDefined(this.tag)) {
                    this.setEndOffset();
                    break;
                }
                this.nextLeaf(this.pos);
            }
        }
        
        void nextLeaf(final ElementIterator elementIterator) {
            elementIterator.next();
            while (elementIterator.current() != null && !elementIterator.current().isLeaf()) {
                elementIterator.next();
            }
        }
        
        void setEndOffset() {
            final AttributeSet attributes = this.getAttributes();
            this.endOffset = this.pos.current().getEndOffset();
            final ElementIterator elementIterator = (ElementIterator)this.pos.clone();
            this.nextLeaf(elementIterator);
            while (elementIterator.current() != null) {
                final Element current = elementIterator.current();
                final AttributeSet set = (AttributeSet)current.getAttributes().getAttribute(this.tag);
                if (set == null) {
                    break;
                }
                if (!set.equals(attributes)) {
                    break;
                }
                this.endOffset = current.getEndOffset();
                this.nextLeaf(elementIterator);
            }
        }
    }
    
    public class RunElement extends LeafElement
    {
        public RunElement(final Element element, final AttributeSet set, final int n, final int n2) {
            super(element, set, n, n2);
        }
        
        public String getName() {
            final Object attribute = ((AbstractElement)this).getAttribute(StyleConstants.NameAttribute);
            if (attribute != null) {
                return attribute.toString();
            }
            return super.getName();
        }
        
        public AttributeSet getResolveParent() {
            return null;
        }
    }
    
    public class BlockElement extends BranchElement
    {
        public BlockElement(final Element element, final AttributeSet set) {
            super(element, set);
        }
        
        public String getName() {
            final Object attribute = ((AbstractElement)this).getAttribute(StyleConstants.NameAttribute);
            if (attribute != null) {
                return attribute.toString();
            }
            return super.getName();
        }
        
        public AttributeSet getResolveParent() {
            return null;
        }
    }
}
