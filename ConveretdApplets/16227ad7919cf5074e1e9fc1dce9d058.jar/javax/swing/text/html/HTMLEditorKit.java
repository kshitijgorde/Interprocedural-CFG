// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.text.html;

import java.awt.event.ActionEvent;
import javax.swing.text.EditorKit;
import javax.swing.SizeRequirements;
import javax.swing.text.View;
import java.awt.Rectangle;
import java.io.Serializable;
import java.awt.event.MouseAdapter;
import java.awt.Point;
import java.awt.event.MouseEvent;
import javax.swing.event.HyperlinkEvent;
import java.net.MalformedURLException;
import javax.swing.text.StyledDocument;
import java.io.Writer;
import java.io.StringReader;
import javax.swing.text.BadLocationException;
import java.io.IOException;
import javax.swing.text.ViewFactory;
import java.net.URL;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import javax.swing.text.Style;
import javax.swing.text.TextAction;
import javax.swing.JEditorPane;
import javax.swing.text.StyleConstants;
import javax.swing.text.AttributeSet;
import javax.swing.text.Element;
import javax.swing.text.Document;
import javax.swing.Action;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.StyledEditorKit;

public class HTMLEditorKit extends StyledEditorKit
{
    public static final String DEFAULT_CSS = "default.css";
    MutableAttributeSet input;
    private static StyleSheet defaultStyles;
    private MouseListener linkHandler;
    private static Parser defaultParser;
    private MouseMotionListener tmpHandler;
    public static final String BOLD_ACTION = "html-bold-action";
    public static final String ITALIC_ACTION = "html-italic-action";
    public static final String PARA_INDENT_LEFT = "html-para-indent-left";
    public static final String PARA_INDENT_RIGHT = "html-para-indent-right";
    public static final String FONT_CHANGE_BIGGER = "html-font-bigger";
    public static final String FONT_CHANGE_SMALLER = "html-font-smaller";
    public static final String COLOR_ACTION = "html-color-action";
    public static final String LOGICAL_STYLE_ACTION = "html-logical-style-action";
    public static final String IMG_ALIGN_TOP = "html-image-align-top";
    public static final String IMG_ALIGN_MIDDLE = "html-image-align-middle";
    public static final String IMG_ALIGN_BOTTOM = "html-image-align-bottom";
    public static final String IMG_BORDER = "html-image-border";
    private static final String INSERT_TABLE_HTML = "<table border=1><tr><td></td></tr></table>";
    private static final String INSERT_UL_HTML = "<ul><li></li></ul>";
    private static final String INSERT_OL_HTML = "<ol><li></li></ol>";
    private static final String INSERT_HR_HTML = "<hr>";
    private static final String INSERT_PRE_HTML = "<pre></pre>";
    private static final Action[] defaultActions;
    static /* synthetic */ Class class$javax$swing$text$html$HTMLEditorKit;
    static /* synthetic */ Class class$java$lang$String;
    
    static {
        HTMLEditorKit.defaultStyles = null;
        HTMLEditorKit.defaultParser = null;
        defaultActions = new Action[] { new InsertHTMLTextAction("InsertTable", "<table border=1><tr><td></td></tr></table>", HTML.Tag.BODY, HTML.Tag.TABLE), new InsertHTMLTextAction("InsertTableRow", "<table border=1><tr><td></td></tr></table>", HTML.Tag.TABLE, HTML.Tag.TR, HTML.Tag.BODY, HTML.Tag.TABLE), new InsertHTMLTextAction("InsertTableDataCell", "<table border=1><tr><td></td></tr></table>", HTML.Tag.TR, HTML.Tag.TD, HTML.Tag.BODY, HTML.Tag.TABLE), new InsertHTMLTextAction("InsertUnorderedList", "<ul><li></li></ul>", HTML.Tag.BODY, HTML.Tag.UL), new InsertHTMLTextAction("InsertUnorderedListItem", "<ul><li></li></ul>", HTML.Tag.UL, HTML.Tag.LI, HTML.Tag.BODY, HTML.Tag.UL), new InsertHTMLTextAction("InsertOrderedList", "<ol><li></li></ol>", HTML.Tag.BODY, HTML.Tag.OL), new InsertHTMLTextAction("InsertOrderedListItem", "<ol><li></li></ol>", HTML.Tag.OL, HTML.Tag.LI, HTML.Tag.BODY, HTML.Tag.OL), new InsertHRAction(), new InsertHTMLTextAction("InsertPre", "<pre></pre>", HTML.Tag.BODY, HTML.Tag.PRE) };
    }
    
    public HTMLEditorKit() {
        this.linkHandler = new LinkController();
        this.tmpHandler = new TemporaryHandler();
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public Object clone() {
        return new HTMLEditorKit();
    }
    
    public Document createDefaultDocument() {
        final StyleSheet styleSheet = this.getStyleSheet();
        final StyleSheet styleSheet2 = new StyleSheet();
        styleSheet2.addStyleSheet(styleSheet);
        final HTMLDocument htmlDocument = new HTMLDocument(styleSheet2);
        htmlDocument.putProperty(HTMLDocument.PARSER_PROPERTY, this.getParser());
        htmlDocument.setAsynchronousLoadPriority(4);
        htmlDocument.setTokenThreshold(100);
        return htmlDocument;
    }
    
    protected void createInputAttributes(final Element element, final MutableAttributeSet set) {
        set.removeAttributes(set);
        set.addAttributes(element.getAttributes());
        set.removeAttribute(StyleConstants.ComposedTextAttribute);
        final Object attribute = set.getAttribute(StyleConstants.NameAttribute);
        if (attribute instanceof HTML.Tag) {
            final HTML.Tag tag = (HTML.Tag)attribute;
            if (tag == HTML.Tag.IMG) {
                set.removeAttribute(HTML.Attribute.SRC);
                set.removeAttribute(HTML.Attribute.HEIGHT);
                set.removeAttribute(HTML.Attribute.WIDTH);
                set.addAttribute(StyleConstants.NameAttribute, HTML.Tag.CONTENT);
            }
            else if (tag == HTML.Tag.HR) {
                set.addAttribute(StyleConstants.NameAttribute, HTML.Tag.CONTENT);
            }
            else if (tag == HTML.Tag.COMMENT) {
                set.addAttribute(StyleConstants.NameAttribute, HTML.Tag.CONTENT);
                set.removeAttribute(HTML.Attribute.COMMENT);
            }
            else if (tag instanceof HTML.UnknownTag) {
                set.addAttribute(StyleConstants.NameAttribute, HTML.Tag.CONTENT);
                set.removeAttribute(HTML.Attribute.ENDTAG);
            }
        }
    }
    
    public void deinstall(final JEditorPane editorPane) {
        editorPane.removeMouseListener(this.linkHandler);
        editorPane.removeMouseMotionListener(this.tmpHandler);
        super.deinstall(editorPane);
    }
    
    public Action[] getActions() {
        return TextAction.augmentList(super.getActions(), HTMLEditorKit.defaultActions);
    }
    
    public String getContentType() {
        return "text/html";
    }
    
    public MutableAttributeSet getInputAttributes() {
        if (this.input == null) {
            this.input = this.getStyleSheet().addStyle(null, null);
        }
        return this.input;
    }
    
    protected Parser getParser() {
        if (HTMLEditorKit.defaultParser == null) {
            try {
                HTMLEditorKit.defaultParser = (Parser)Class.forName("javax.swing.text.html.parser.ParserDelegator").newInstance();
            }
            catch (Throwable t) {}
        }
        return HTMLEditorKit.defaultParser;
    }
    
    static InputStream getResourceAsStream(final String s) {
        try {
            final ClassLoader classLoader = ((HTMLEditorKit.class$javax$swing$text$html$HTMLEditorKit != null) ? HTMLEditorKit.class$javax$swing$text$html$HTMLEditorKit : (HTMLEditorKit.class$javax$swing$text$html$HTMLEditorKit = class$("javax.swing.text.html.HTMLEditorKit"))).getClassLoader();
            Class<?> clazz;
            if (classLoader != null) {
                clazz = classLoader.loadClass("javax.swing.text.html.ResourceLoader");
            }
            else {
                clazz = Class.forName("javax.swing.text.html.ResourceLoader");
            }
            return (InputStream)clazz.getMethod("getResourceAsStream", (HTMLEditorKit.class$java$lang$String != null) ? HTMLEditorKit.class$java$lang$String : (HTMLEditorKit.class$java$lang$String = class$("java.lang.String"))).invoke(null, s);
        }
        catch (Throwable t) {
            return ((HTMLEditorKit.class$javax$swing$text$html$HTMLEditorKit != null) ? HTMLEditorKit.class$javax$swing$text$html$HTMLEditorKit : (HTMLEditorKit.class$javax$swing$text$html$HTMLEditorKit = class$("javax.swing.text.html.HTMLEditorKit"))).getResourceAsStream(s);
        }
    }
    
    public StyleSheet getStyleSheet() {
        if (HTMLEditorKit.defaultStyles == null) {
            HTMLEditorKit.defaultStyles = new StyleSheet();
            try {
                final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getResourceAsStream("default.css")));
                HTMLEditorKit.defaultStyles.loadRules(bufferedReader, null);
                bufferedReader.close();
            }
            catch (Throwable t) {}
        }
        return HTMLEditorKit.defaultStyles;
    }
    
    public ViewFactory getViewFactory() {
        return new HTMLFactory();
    }
    
    public void insertHTML(final HTMLDocument htmlDocument, final int n, final String s, final int n2, final int n3, final HTML.Tag tag) throws BadLocationException, IOException {
        final Parser parser = this.getParser();
        if (parser == null) {
            throw new IOException("Can't load parser");
        }
        if (n > htmlDocument.getLength()) {
            throw new BadLocationException("Invalid location", n);
        }
        final ParserCallback reader = htmlDocument.getReader(n, n2, n3, tag);
        final Boolean b = (Boolean)htmlDocument.getProperty("IgnoreCharsetDirective");
        parser.parse(new StringReader(s), reader, b != null && b);
        reader.flush();
    }
    
    public void install(final JEditorPane editorPane) {
        editorPane.addMouseListener(this.linkHandler);
        editorPane.addMouseMotionListener(this.tmpHandler);
        super.install(editorPane);
    }
    
    public void read(final Reader reader, final Document document, final int n) throws IOException, BadLocationException {
        if (document instanceof HTMLDocument) {
            final HTMLDocument htmlDocument = (HTMLDocument)document;
            final Parser parser = this.getParser();
            if (parser == null) {
                throw new IOException("Can't load parser");
            }
            if (n > document.getLength()) {
                throw new BadLocationException("Invalid location", n);
            }
            final ParserCallback reader2 = htmlDocument.getReader(n);
            final Boolean b = (Boolean)document.getProperty("IgnoreCharsetDirective");
            parser.parse(reader, reader2, b != null && b);
            reader2.flush();
        }
        else {
            super.read(reader, document, n);
        }
    }
    
    public void setStyleSheet(final StyleSheet defaultStyles) {
        HTMLEditorKit.defaultStyles = defaultStyles;
    }
    
    public void write(final Writer writer, final Document document, final int n, final int n2) throws IOException, BadLocationException {
        if (document instanceof HTMLDocument) {
            new HTMLWriter(writer, (HTMLDocument)document, n, n2).write();
        }
        else if (document instanceof StyledDocument) {
            new MinimalHTMLWriter(writer, (StyledDocument)document, n, n2).write();
        }
        else {
            super.write(writer, document, n, n2);
        }
    }
    
    static class TemporaryHandler implements MouseMotionListener
    {
        private Element curElem;
        private String href;
        
        TemporaryHandler() {
            this.curElem = null;
            this.href = null;
        }
        
        void fireEvents(final JEditorPane editorPane, final HTMLDocument htmlDocument, final String s) {
            if (this.href != null) {
                URL url;
                try {
                    url = new URL(htmlDocument.getBase(), this.href);
                }
                catch (MalformedURLException ex) {
                    url = null;
                }
                editorPane.fireHyperlinkUpdate(new HyperlinkEvent(editorPane, HyperlinkEvent.EventType.EXITED, url, this.href));
            }
            if (s != null) {
                URL url2;
                try {
                    url2 = new URL(htmlDocument.getBase(), s);
                }
                catch (MalformedURLException ex2) {
                    url2 = null;
                }
                editorPane.fireHyperlinkUpdate(new HyperlinkEvent(editorPane, HyperlinkEvent.EventType.ENTERED, url2, s));
            }
        }
        
        public void mouseDragged(final MouseEvent mouseEvent) {
        }
        
        public void mouseMoved(final MouseEvent mouseEvent) {
            final JEditorPane editorPane = (JEditorPane)mouseEvent.getSource();
            if (!editorPane.isEditable()) {
                final int viewToModel = editorPane.viewToModel(new Point(mouseEvent.getX(), mouseEvent.getY()));
                if (viewToModel >= 0) {
                    final Document document = editorPane.getDocument();
                    if (document instanceof HTMLDocument) {
                        final HTMLDocument htmlDocument = (HTMLDocument)document;
                        final Element characterElement = htmlDocument.getCharacterElement(viewToModel);
                        if (this.curElem != characterElement) {
                            this.curElem = characterElement;
                            final AttributeSet set = (AttributeSet)characterElement.getAttributes().getAttribute(HTML.Tag.A);
                            final String href = (set != null) ? ((String)set.getAttribute(HTML.Attribute.HREF)) : null;
                            if (href != this.href) {
                                this.fireEvents(editorPane, htmlDocument, href);
                                this.href = href;
                            }
                        }
                    }
                }
            }
        }
    }
    
    public static class LinkController extends MouseAdapter implements Serializable
    {
        protected void activateLink(final int n, final JEditorPane editorPane) {
            this.activateLink(n, editorPane, -1, -1);
        }
        
        void activateLink(final int n, final JEditorPane editorPane, final int n2, final int n3) {
            final Document document = editorPane.getDocument();
            if (document instanceof HTMLDocument) {
                final HTMLDocument htmlDocument = (HTMLDocument)document;
                final AttributeSet attributes = htmlDocument.getCharacterElement(n).getAttributes();
                final AttributeSet set = (AttributeSet)attributes.getAttribute(HTML.Tag.A);
                final String s = (set != null) ? ((String)set.getAttribute(HTML.Attribute.HREF)) : null;
                HyperlinkEvent hyperlinkEvent = null;
                if (s != null) {
                    hyperlinkEvent = this.createHyperlinkEvent(editorPane, htmlDocument, s, set);
                }
                else if (n2 >= 0 && n3 >= 0) {
                    final Object attribute = attributes.getAttribute(HTML.Attribute.USEMAP);
                    if (attribute != null && attribute instanceof String) {
                        final Map map = htmlDocument.getMap((String)attribute);
                        if (map != null) {
                            Rectangle modelToView;
                            try {
                                modelToView = editorPane.modelToView(n);
                                final Rectangle modelToView2 = editorPane.modelToView(n + 1);
                                if (modelToView != null && modelToView2 != null) {
                                    modelToView.union(modelToView2);
                                }
                            }
                            catch (BadLocationException ex) {
                                modelToView = null;
                            }
                            if (modelToView != null) {
                                final AttributeSet area = map.getArea(n2 - modelToView.x, n3 - modelToView.y, modelToView.width, modelToView.height);
                                if (area != null) {
                                    final String s2 = (String)area.getAttribute(HTML.Attribute.HREF);
                                    if (s2 != null) {
                                        hyperlinkEvent = this.createHyperlinkEvent(editorPane, htmlDocument, s2, set);
                                    }
                                }
                            }
                        }
                    }
                }
                if (hyperlinkEvent != null) {
                    editorPane.fireHyperlinkUpdate(hyperlinkEvent);
                }
            }
        }
        
        HyperlinkEvent createHyperlinkEvent(final JEditorPane editorPane, final HTMLDocument htmlDocument, final String s, final AttributeSet set) {
            URL url;
            try {
                final URL base = htmlDocument.getBase();
                url = new URL(base, s);
                if (s != null && "file".equals(url.getProtocol()) && s.startsWith("#")) {
                    final String file = base.getFile();
                    final String file2 = url.getFile();
                    if (file != null && file2 != null && !file2.startsWith(file)) {
                        url = new URL(base, String.valueOf(file) + s);
                    }
                }
            }
            catch (MalformedURLException ex) {
                url = null;
            }
            HyperlinkEvent hyperlinkEvent;
            if (!htmlDocument.isFrameDocument()) {
                hyperlinkEvent = new HyperlinkEvent(editorPane, HyperlinkEvent.EventType.ACTIVATED, url, s);
            }
            else {
                String s2 = (set != null) ? ((String)set.getAttribute(HTML.Attribute.TARGET)) : null;
                if (s2 == null || s2.equals("")) {
                    s2 = "_self";
                }
                hyperlinkEvent = new HTMLFrameHyperlinkEvent(editorPane, HyperlinkEvent.EventType.ACTIVATED, url, s, s2);
            }
            return hyperlinkEvent;
        }
        
        public void mouseClicked(final MouseEvent mouseEvent) {
            final JEditorPane editorPane = (JEditorPane)mouseEvent.getSource();
            if (!editorPane.isEditable()) {
                final int viewToModel = editorPane.viewToModel(new Point(mouseEvent.getX(), mouseEvent.getY()));
                if (viewToModel >= 0) {
                    this.activateLink(viewToModel, editorPane, mouseEvent.getX(), mouseEvent.getY());
                }
            }
        }
    }
    
    public abstract static class Parser
    {
        public abstract void parse(final Reader p0, final ParserCallback p1, final boolean p2) throws IOException;
    }
    
    public static class ParserCallback
    {
        public void flush() throws BadLocationException {
        }
        
        public void handleComment(final char[] array, final int n) {
        }
        
        public void handleEndTag(final HTML.Tag tag, final int n) {
        }
        
        public void handleError(final String s, final int n) {
        }
        
        public void handleSimpleTag(final HTML.Tag tag, final MutableAttributeSet set, final int n) {
        }
        
        public void handleStartTag(final HTML.Tag tag, final MutableAttributeSet set, final int n) {
        }
        
        public void handleText(final char[] array, final int n) {
        }
    }
    
    public static class HTMLFactory implements ViewFactory
    {
        public View create(final Element element) {
            final Object attribute = element.getAttributes().getAttribute(StyleConstants.NameAttribute);
            if (!(attribute instanceof HTML.Tag)) {
                throw new Error("Can't build a " + element);
            }
            final HTML.Tag tag = (HTML.Tag)attribute;
            if (tag == HTML.Tag.CONTENT) {
                return new InlineView(element);
            }
            if (tag == HTML.Tag.IMPLIED) {
                final String s = (String)element.getAttributes().getAttribute(CSS.Attribute.WHITE_SPACE);
                if (s != null && s.equals("pre")) {
                    return new LineView(element);
                }
                return new ParagraphView(element);
            }
            else {
                if (tag == HTML.Tag.P || tag == HTML.Tag.H1 || tag == HTML.Tag.H2 || tag == HTML.Tag.H3 || tag == HTML.Tag.H4 || tag == HTML.Tag.H5 || tag == HTML.Tag.H6 || tag == HTML.Tag.DT) {
                    return new ParagraphView(element);
                }
                if (tag == HTML.Tag.MENU || tag == HTML.Tag.DIR || tag == HTML.Tag.UL || tag == HTML.Tag.OL) {
                    return new ListView(element);
                }
                if (tag == HTML.Tag.BODY) {
                    return new BlockView(1) {
                        protected SizeRequirements calculateMajorAxisRequirements(final int n, SizeRequirements calculateMajorAxisRequirements) {
                            calculateMajorAxisRequirements = super.calculateMajorAxisRequirements(n, calculateMajorAxisRequirements);
                            calculateMajorAxisRequirements.maximum = Integer.MAX_VALUE;
                            return calculateMajorAxisRequirements;
                        }
                    };
                }
                if (tag == HTML.Tag.LI || tag == HTML.Tag.CENTER || tag == HTML.Tag.DL || tag == HTML.Tag.DD || tag == HTML.Tag.HTML || tag == HTML.Tag.DIV || tag == HTML.Tag.BLOCKQUOTE || tag == HTML.Tag.PRE) {
                    return new BlockView(element, 1);
                }
                if (tag == HTML.Tag.NOFRAMES) {
                    return new NoFramesView(element, 1);
                }
                if (tag == HTML.Tag.TH || tag == HTML.Tag.TD) {
                    return new TableView.CellView(element);
                }
                if (tag == HTML.Tag.IMG) {
                    return new ImageView(element);
                }
                if (tag == HTML.Tag.ISINDEX) {
                    return new IsindexView(element);
                }
                if (tag == HTML.Tag.HR) {
                    return new HRuleView(element);
                }
                if (tag == HTML.Tag.BR) {
                    return new BRView(element);
                }
                if (tag == HTML.Tag.TABLE) {
                    return new TableView(element);
                }
                if (tag == HTML.Tag.INPUT || tag == HTML.Tag.SELECT || tag == HTML.Tag.TEXTAREA) {
                    return new FormView(element);
                }
                if (tag == HTML.Tag.OBJECT) {
                    return new ObjectView(element);
                }
                if (tag == HTML.Tag.FRAMESET) {
                    if (element.getAttributes().isDefined(HTML.Attribute.ROWS)) {
                        return new FrameSetView(element, 1);
                    }
                    if (element.getAttributes().isDefined(HTML.Attribute.COLS)) {
                        return new FrameSetView(element, 0);
                    }
                    throw new Error("Can't build a" + tag + ", " + element + ":" + "no ROWS or COLS defined.");
                }
                else {
                    if (tag == HTML.Tag.FRAME) {
                        return new FrameView(element);
                    }
                    if (tag instanceof HTML.UnknownTag) {
                        return new HiddenTagView(element);
                    }
                    if (tag == HTML.Tag.COMMENT) {
                        return new CommentView(element);
                    }
                    if (tag == HTML.Tag.HEAD || tag == HTML.Tag.TITLE || tag == HTML.Tag.META || tag == HTML.Tag.LINK || tag == HTML.Tag.STYLE || tag == HTML.Tag.SCRIPT || tag == HTML.Tag.AREA || tag == HTML.Tag.MAP || tag == HTML.Tag.PARAM || tag == HTML.Tag.APPLET) {
                        return new HiddenTagView(element);
                    }
                    throw new Error("Can't build a " + tag + ", " + element);
                }
            }
        }
    }
    
    public abstract static class HTMLTextAction extends StyledTextAction
    {
        public HTMLTextAction(final String s) {
            super(s);
        }
        
        protected int elementCountToTag(final HTMLDocument htmlDocument, final int n, final HTML.Tag tag) {
            int n2;
            Element element;
            for (n2 = -1, element = htmlDocument.getCharacterElement(n); element != null && element.getAttributes().getAttribute(StyleConstants.NameAttribute) != tag; element = element.getParentElement(), ++n2) {}
            if (element == null) {
                return -1;
            }
            return n2;
        }
        
        protected Element findElementMatchingTag(final HTMLDocument htmlDocument, final int n, final HTML.Tag tag) {
            Element element = htmlDocument.getDefaultRootElement();
            Element element2 = null;
            while (element != null) {
                if (element.getAttributes().getAttribute(StyleConstants.NameAttribute) == tag) {
                    element2 = element;
                }
                element = element.getElement(element.getElementIndex(n));
            }
            return element2;
        }
        
        private Element[] getElementsAt(final Element element, final int n, final int n2) {
            if (element.isLeaf()) {
                final Element[] array = new Element[n2 + 1];
                array[n2] = element;
                return array;
            }
            final Element[] elements = this.getElementsAt(element.getElement(element.getElementIndex(n)), n, n2 + 1);
            elements[n2] = element;
            return elements;
        }
        
        protected Element[] getElementsAt(final HTMLDocument htmlDocument, final int n) {
            return this.getElementsAt(htmlDocument.getDefaultRootElement(), n, 0);
        }
        
        protected HTMLDocument getHTMLDocument(final JEditorPane editorPane) {
            final Document document = editorPane.getDocument();
            if (document instanceof HTMLDocument) {
                return (HTMLDocument)document;
            }
            throw new IllegalArgumentException("document must be HTMLDocument");
        }
        
        protected HTMLEditorKit getHTMLEditorKit(final JEditorPane editorPane) {
            final EditorKit editorKit = editorPane.getEditorKit();
            if (editorKit instanceof HTMLEditorKit) {
                return (HTMLEditorKit)editorKit;
            }
            throw new IllegalArgumentException("EditorKit must be HTMLEditorKit");
        }
    }
    
    public static class InsertHTMLTextAction extends HTMLTextAction
    {
        protected String html;
        protected HTML.Tag parentTag;
        protected HTML.Tag addTag;
        protected HTML.Tag alternateParentTag;
        protected HTML.Tag alternateAddTag;
        boolean adjustSelection;
        
        public InsertHTMLTextAction(final String s, final String s2, final HTML.Tag tag, final HTML.Tag tag2) {
            this(s, s2, tag, tag2, null, null);
        }
        
        public InsertHTMLTextAction(final String s, final String s2, final HTML.Tag tag, final HTML.Tag tag2, final HTML.Tag tag3, final HTML.Tag tag4) {
            this(s, s2, tag, tag2, tag3, tag4, true);
        }
        
        InsertHTMLTextAction(final String s, final String html, final HTML.Tag parentTag, final HTML.Tag addTag, final HTML.Tag alternateParentTag, final HTML.Tag alternateAddTag, final boolean adjustSelection) {
            super(s);
            this.html = html;
            this.parentTag = parentTag;
            this.addTag = addTag;
            this.alternateParentTag = alternateParentTag;
            this.alternateAddTag = alternateAddTag;
            this.adjustSelection = adjustSelection;
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            final JEditorPane editor = ((StyledTextAction)this).getEditor(actionEvent);
            if (editor != null) {
                final HTMLDocument htmlDocument = ((HTMLTextAction)this).getHTMLDocument(editor);
                final int selectionStart = editor.getSelectionStart();
                final int length = htmlDocument.getLength();
                final boolean b = this.insertIntoTag(editor, htmlDocument, selectionStart, this.parentTag, this.addTag) || this.alternateParentTag == null || this.insertIntoTag(editor, htmlDocument, selectionStart, this.alternateParentTag, this.alternateAddTag);
                if (this.adjustSelection && b) {
                    this.adjustSelection(editor, htmlDocument, selectionStart, length);
                }
            }
        }
        
        void adjustSelection(final JEditorPane editorPane, final HTMLDocument htmlDocument, final int n, final int n2) {
            final int length = htmlDocument.getLength();
            if (length != n2 && n < length) {
                if (n > 0) {
                    String text;
                    try {
                        text = htmlDocument.getText(n - 1, 1);
                    }
                    catch (BadLocationException ex) {
                        text = null;
                    }
                    if (text != null && text.length() > 0 && text.charAt(0) == '\n') {
                        editorPane.select(n, n);
                    }
                    else {
                        editorPane.select(n + 1, n + 1);
                    }
                }
                else {
                    editorPane.select(1, 1);
                }
            }
        }
        
        protected void insertAtBoundry(final JEditorPane editorPane, final HTMLDocument htmlDocument, int n, final Element element, final String s, final HTML.Tag tag, final HTML.Tag tag2) {
            final boolean b = n == 0;
            Element element3;
            if (n > 0 || element == null) {
                Element element2;
                for (element2 = htmlDocument.getDefaultRootElement(); element2 != null && element2.getStartOffset() != n && !element2.isLeaf(); element2 = element2.getElement(element2.getElementIndex(n))) {}
                element3 = ((element2 != null) ? element2.getParentElement() : null);
            }
            else {
                element3 = element;
            }
            if (element3 != null) {
                int n2 = 0;
                int n3 = 0;
                if (b && element != null) {
                    for (Element element4 = element3; element4 != null; element4 = element4.getElement(element4.getElementIndex(n)), ++n2) {
                        if (element4.isLeaf()) {
                            break;
                        }
                    }
                }
                else {
                    Element element5 = element3;
                    --n;
                    while (element5 != null && !element5.isLeaf()) {
                        element5 = element5.getElement(element5.getElementIndex(n));
                        ++n2;
                    }
                    Element element6 = element3;
                    ++n;
                    while (element6 != null && element6 != element) {
                        element6 = element6.getElement(element6.getElementIndex(n));
                        ++n3;
                    }
                }
                this.insertHTML(editorPane, htmlDocument, n, s, Math.max(0, n2 - 1), n3, tag2);
            }
        }
        
        protected void insertHTML(final JEditorPane editorPane, final HTMLDocument htmlDocument, final int n, final String s, final int n2, final int n3, final HTML.Tag tag) {
            try {
                ((HTMLTextAction)this).getHTMLEditorKit(editorPane).insertHTML(htmlDocument, n, s, n2, n3, tag);
            }
            catch (IOException ex) {
                throw new RuntimeException("Unable to insert: " + ex);
            }
            catch (BadLocationException ex2) {
                throw new RuntimeException("Unable to insert: " + ex2);
            }
        }
        
        boolean insertIntoTag(final JEditorPane editorPane, final HTMLDocument htmlDocument, final int n, final HTML.Tag tag, final HTML.Tag tag2) {
            final Element elementMatchingTag = ((HTMLTextAction)this).findElementMatchingTag(htmlDocument, n, tag);
            if (elementMatchingTag != null && elementMatchingTag.getStartOffset() == n) {
                this.insertAtBoundry(editorPane, htmlDocument, n, elementMatchingTag, this.html, tag, tag2);
                return true;
            }
            if (n > 0) {
                final int elementCountToTag = ((HTMLTextAction)this).elementCountToTag(htmlDocument, n - 1, tag);
                if (elementCountToTag != -1) {
                    this.insertHTML(editorPane, htmlDocument, n, this.html, elementCountToTag, 0, tag2);
                    return true;
                }
            }
            return false;
        }
    }
    
    static class InsertHRAction extends InsertHTMLTextAction
    {
        InsertHRAction() {
            super("InsertHR", "<hr>", null, HTML.Tag.IMPLIED, null, null, false);
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            final JEditorPane editor = ((StyledTextAction)this).getEditor(actionEvent);
            if (editor != null) {
                final Element paragraphElement = ((HTMLTextAction)this).getHTMLDocument(editor).getParagraphElement(editor.getSelectionStart());
                if (paragraphElement.getParentElement() != null) {
                    super.parentTag = (HTML.Tag)paragraphElement.getParentElement().getAttributes().getAttribute(StyleConstants.NameAttribute);
                    super.actionPerformed(actionEvent);
                }
            }
        }
    }
}
