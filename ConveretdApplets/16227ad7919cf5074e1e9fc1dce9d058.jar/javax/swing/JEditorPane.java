// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import javax.swing.text.WrappedPlainView;
import javax.swing.text.View;
import javax.swing.text.ViewFactory;
import javax.swing.text.DefaultEditorKit;
import javax.accessibility.AccessibleText;
import javax.accessibility.AccessibleState;
import javax.accessibility.AccessibleStateSet;
import java.util.Vector;
import java.net.MalformedURLException;
import javax.accessibility.AccessibleHyperlink;
import javax.swing.text.Element;
import javax.swing.text.ElementIterator;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.accessibility.AccessibleHypertext;
import java.io.ObjectOutputStream;
import java.io.StringReader;
import java.util.Enumeration;
import javax.swing.text.AbstractDocument;
import java.awt.Rectangle;
import javax.swing.text.html.HTML;
import javax.swing.text.Caret;
import javax.swing.text.AttributeSet;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.ChangedCharSetException;
import javax.swing.text.BadLocationException;
import java.io.Reader;
import java.io.InputStreamReader;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLDocument;
import java.awt.event.KeyEvent;
import java.io.Writer;
import java.io.StringWriter;
import java.net.URLConnection;
import java.net.HttpURLConnection;
import java.io.InputStream;
import javax.swing.plaf.TextUI;
import java.awt.Dimension;
import javax.swing.text.html.HTMLEditorKit;
import javax.accessibility.AccessibleContext;
import javax.swing.event.HyperlinkEvent;
import java.util.EventListener;
import javax.swing.event.HyperlinkListener;
import java.net.URL;
import java.io.IOException;
import java.util.Hashtable;
import javax.swing.text.EditorKit;
import javax.swing.text.JTextComponent;

public class JEditorPane extends JTextComponent
{
    private EditorKit kit;
    private Hashtable pageProperties;
    private Hashtable typeHandlers;
    private String charSetName;
    private static final Object kitRegistryKey;
    private static final Object kitTypeRegistryKey;
    private static final Object kitLoaderRegistryKey;
    private static final String uiClassID = "EditorPaneUI";
    static /* synthetic */ Class class$javax$swing$event$HyperlinkListener;
    
    static {
        kitRegistryKey = new StringBuffer("JEditorPane.kitRegistry");
        kitTypeRegistryKey = new StringBuffer("JEditorPane.kitTypeRegistry");
        kitLoaderRegistryKey = new StringBuffer("JEditorPane.kitLoaderRegistry");
        registerEditorKitForContentType("text/plain", "javax.swing.JEditorPane$PlainEditorKit");
        registerEditorKitForContentType("text/html", "javax.swing.text.html.HTMLEditorKit");
        registerEditorKitForContentType("text/rtf", "javax.swing.text.rtf.RTFEditorKit");
        registerEditorKitForContentType("application/rtf", "javax.swing.text.rtf.RTFEditorKit");
    }
    
    public JEditorPane() {
        this.charSetName = "8859_1";
    }
    
    public JEditorPane(final String page) throws IOException {
        this();
        this.setPage(page);
    }
    
    public JEditorPane(final String contentType, final String text) {
        this();
        this.setContentType(contentType);
        this.setText(text);
    }
    
    public JEditorPane(final URL page) throws IOException {
        this();
        this.setPage(page);
    }
    
    public synchronized void addHyperlinkListener(final HyperlinkListener hyperlinkListener) {
        super.listenerList.add((JEditorPane.class$javax$swing$event$HyperlinkListener != null) ? JEditorPane.class$javax$swing$event$HyperlinkListener : (JEditorPane.class$javax$swing$event$HyperlinkListener = class$("javax.swing.event.HyperlinkListener")), hyperlinkListener);
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    protected EditorKit createDefaultEditorKit() {
        return new PlainEditorKit();
    }
    
    public static EditorKit createEditorKitForContentType(final String s) {
        EditorKit editorKit = null;
        Hashtable<Object, EditorKit> hashtable = (Hashtable<Object, EditorKit>)SwingUtilities.appContextGet(JEditorPane.kitRegistryKey);
        if (hashtable == null) {
            hashtable = (Hashtable<Object, EditorKit>)new Hashtable<String, EditorKit>();
            SwingUtilities.appContextPut(JEditorPane.kitRegistryKey, hashtable);
        }
        else {
            editorKit = hashtable.get(s);
        }
        if (editorKit == null) {
            final String s2 = getKitTypeRegistry().get(s);
            final ClassLoader classLoader = getKitLoaderRegistry().get(s);
            try {
                Class<?> clazz;
                if (classLoader != null) {
                    clazz = classLoader.loadClass(s2);
                }
                else {
                    clazz = Class.forName(s2);
                }
                editorKit = (EditorKit)clazz.newInstance();
                hashtable.put(s, editorKit);
            }
            catch (Throwable t) {
                editorKit = null;
            }
        }
        if (editorKit != null) {
            return (EditorKit)editorKit.clone();
        }
        return null;
    }
    
    public void fireHyperlinkUpdate(final HyperlinkEvent hyperlinkEvent) {
        final Object[] listenerList = super.listenerList.getListenerList();
        for (int i = listenerList.length - 2; i >= 0; i -= 2) {
            if (listenerList[i] == ((JEditorPane.class$javax$swing$event$HyperlinkListener != null) ? JEditorPane.class$javax$swing$event$HyperlinkListener : (JEditorPane.class$javax$swing$event$HyperlinkListener = class$("javax.swing.event.HyperlinkListener")))) {
                ((HyperlinkListener)listenerList[i + 1]).hyperlinkUpdate(hyperlinkEvent);
            }
        }
    }
    
    public AccessibleContext getAccessibleContext() {
        if (super.accessibleContext == null) {
            if (this.getEditorKit() instanceof HTMLEditorKit) {
                super.accessibleContext = new AccessibleJEditorPaneHTML();
            }
            else {
                super.accessibleContext = new AccessibleJEditorPane();
            }
        }
        return super.accessibleContext;
    }
    
    public final String getContentType() {
        return (this.kit != null) ? this.kit.getContentType() : null;
    }
    
    public final EditorKit getEditorKit() {
        if (this.kit == null) {
            this.kit = this.createDefaultEditorKit();
        }
        return this.kit;
    }
    
    public EditorKit getEditorKitForContentType(final String s) {
        if (this.typeHandlers == null) {
            this.typeHandlers = new Hashtable(3);
        }
        EditorKit editorKit = this.typeHandlers.get(s);
        if (editorKit == null) {
            editorKit = createEditorKitForContentType(s);
            if (editorKit != null) {
                this.setEditorKitForContentType(s, editorKit);
            }
        }
        if (editorKit == null) {
            editorKit = this.createDefaultEditorKit();
        }
        return editorKit;
    }
    
    private static Hashtable getKitLoaderRegistry() {
        Hashtable hashtable = (Hashtable)SwingUtilities.appContextGet(JEditorPane.kitLoaderRegistryKey);
        if (hashtable == null) {
            hashtable = new Hashtable();
            SwingUtilities.appContextPut(JEditorPane.kitLoaderRegistryKey, hashtable);
        }
        return hashtable;
    }
    
    private static Hashtable getKitTypeRegistry() {
        Hashtable hashtable = (Hashtable)SwingUtilities.appContextGet(JEditorPane.kitTypeRegistryKey);
        if (hashtable == null) {
            hashtable = new Hashtable();
            SwingUtilities.appContextPut(JEditorPane.kitTypeRegistryKey, hashtable);
        }
        return hashtable;
    }
    
    public URL getPage() {
        return (URL)this.getDocument().getProperty("stream");
    }
    
    public Dimension getPreferredSize() {
        final Dimension preferredSize = super.getPreferredSize();
        if (this.getParent() instanceof JViewport) {
            final JViewport viewport = (JViewport)this.getParent();
            final TextUI ui = this.getUI();
            if (!this.getScrollableTracksViewportWidth()) {
                final int width = viewport.getWidth();
                final Dimension minimumSize = ui.getMinimumSize(this);
                ui.getMaximumSize(this);
                if (width < minimumSize.width) {
                    preferredSize.width = minimumSize.width;
                }
            }
            if (!this.getScrollableTracksViewportHeight()) {
                final int height = viewport.getHeight();
                final Dimension minimumSize2 = ui.getMinimumSize(this);
                ui.getMaximumSize(this);
                if (height < minimumSize2.height) {
                    preferredSize.height = minimumSize2.height;
                }
            }
        }
        return preferredSize;
    }
    
    public boolean getScrollableTracksViewportHeight() {
        if (this.getParent() instanceof JViewport) {
            final JViewport viewport = (JViewport)this.getParent();
            final TextUI ui = this.getUI();
            final int height = viewport.getHeight();
            final Dimension minimumSize = ui.getMinimumSize(this);
            final Dimension maximumSize = ui.getMaximumSize(this);
            if (height >= minimumSize.height && height <= maximumSize.height) {
                return true;
            }
        }
        return false;
    }
    
    public boolean getScrollableTracksViewportWidth() {
        if (this.getParent() instanceof JViewport) {
            final JViewport viewport = (JViewport)this.getParent();
            final TextUI ui = this.getUI();
            final int width = viewport.getWidth();
            final Dimension minimumSize = ui.getMinimumSize(this);
            final Dimension maximumSize = ui.getMaximumSize(this);
            if (width >= minimumSize.width && width <= maximumSize.width) {
                return true;
            }
        }
        return false;
    }
    
    protected InputStream getStream(URL url) throws IOException {
        final URLConnection openConnection = url.openConnection();
        if (openConnection instanceof HttpURLConnection) {
            final HttpURLConnection httpURLConnection = (HttpURLConnection)openConnection;
            HttpURLConnection.setFollowRedirects(false);
            final int responseCode = httpURLConnection.getResponseCode();
            if (responseCode >= 300 && responseCode <= 399) {
                final String headerField = openConnection.getHeaderField("Location");
                if (headerField.startsWith("http", 0)) {
                    url = new URL(headerField);
                }
                else {
                    url = new URL(url, headerField);
                }
                return this.getStream(url);
            }
        }
        if (this.pageProperties == null) {
            this.pageProperties = new Hashtable();
        }
        final String contentType = openConnection.getContentType();
        if (contentType != null) {
            this.setContentType(contentType);
            this.pageProperties.put("content-type", contentType);
        }
        this.pageProperties.put("stream", url);
        final String contentEncoding = openConnection.getContentEncoding();
        if (contentEncoding != null) {
            this.pageProperties.put("content-encoding", contentEncoding);
        }
        return openConnection.getInputStream();
    }
    
    public String getText() {
        String string;
        try {
            final StringWriter stringWriter = new StringWriter();
            this.write(stringWriter);
            string = stringWriter.toString();
        }
        catch (IOException ex) {
            string = null;
        }
        return string;
    }
    
    public String getUIClassID() {
        return "EditorPaneUI";
    }
    
    public boolean isManagingFocus() {
        return true;
    }
    
    protected String paramString() {
        return String.valueOf(super.paramString()) + ",charSetName=" + ((this.charSetName != null) ? this.charSetName : "") + ",kit=" + ((this.kit != null) ? this.kit.toString() : "") + ",typeHandlers=" + ((this.typeHandlers != null) ? this.typeHandlers.toString() : "");
    }
    
    protected void processComponentKeyEvent(final KeyEvent keyEvent) {
        super.processComponentKeyEvent(keyEvent);
        if (this.isManagingFocus() && (keyEvent.getKeyCode() == 9 || keyEvent.getKeyChar() == '\t')) {
            keyEvent.consume();
        }
    }
    
    public void read(final InputStream inputStream, final Object o) throws IOException {
        if (o instanceof HTMLDocument && this.kit instanceof HTMLEditorKit) {
            final HTMLDocument document = (HTMLDocument)o;
            this.setDocument(document);
            this.read(inputStream, document);
        }
        else {
            super.read(new InputStreamReader(inputStream, this.charSetName), o);
        }
    }
    
    void read(InputStream inputStream, final Document document) throws IOException {
        try {
            this.kit.read(new InputStreamReader(inputStream, this.charSetName), document, 0);
        }
        catch (BadLocationException ex) {
            throw new IOException(ex.getMessage());
        }
        catch (ChangedCharSetException ex2) {
            final String charSetSpec = ex2.getCharSetSpec();
            if (ex2.keyEqualsCharSet()) {
                this.charSetName = charSetSpec;
            }
            else {
                this.setCharsetFromContentTypeParameters(charSetSpec);
            }
            inputStream.close();
            inputStream = ((URL)document.getProperty("stream")).openConnection().getInputStream();
            try {
                document.remove(0, document.getLength());
            }
            catch (BadLocationException ex3) {}
            document.putProperty("IgnoreCharsetDirective", new Boolean(true));
            this.read(inputStream, document);
        }
    }
    
    public static void registerEditorKitForContentType(final String s, final String s2) {
        getKitLoaderRegistry().remove(s);
        getKitTypeRegistry().put(s, s2);
    }
    
    public static void registerEditorKitForContentType(final String s, final String s2, final ClassLoader classLoader) {
        getKitTypeRegistry().put(s, s2);
        getKitLoaderRegistry().put(s, classLoader);
    }
    
    public synchronized void removeHyperlinkListener(final HyperlinkListener hyperlinkListener) {
        super.listenerList.remove((JEditorPane.class$javax$swing$event$HyperlinkListener != null) ? JEditorPane.class$javax$swing$event$HyperlinkListener : (JEditorPane.class$javax$swing$event$HyperlinkListener = class$("javax.swing.event.HyperlinkListener")), hyperlinkListener);
    }
    
    public void replaceSelection(final String s) {
        if (!this.isEditable()) {
            this.getToolkit().beep();
            return;
        }
        final EditorKit editorKit = this.getEditorKit();
        if (editorKit instanceof StyledEditorKit) {
            try {
                final Document document = this.getDocument();
                final Caret caret = this.getCaret();
                final int min = Math.min(caret.getDot(), caret.getMark());
                final int max = Math.max(caret.getDot(), caret.getMark());
                if (min != max) {
                    document.remove(min, max - min);
                }
                if (s != null && s.length() > 0) {
                    document.insertString(min, s, ((StyledEditorKit)editorKit).getInputAttributes());
                }
            }
            catch (BadLocationException ex) {
                this.getToolkit().beep();
            }
        }
        else {
            super.replaceSelection(s);
        }
    }
    
    protected void scrollToReference(final String s) {
        final Document document = this.getDocument();
        if (document instanceof HTMLDocument) {
            final HTMLDocument.Iterator iterator = ((HTMLDocument)document).getIterator(HTML.Tag.A);
            while (iterator.isValid()) {
                final String s2 = (String)iterator.getAttributes().getAttribute(HTML.Attribute.NAME);
                if (s2 != null && s2.equals(s)) {
                    try {
                        final Rectangle modelToView = this.modelToView(iterator.getStartOffset());
                        if (modelToView != null) {
                            final Rectangle visibleRect = this.getVisibleRect();
                            final Rectangle rectangle = modelToView;
                            rectangle.y -= visibleRect.height / 2;
                            modelToView.height = visibleRect.height;
                            this.scrollRectToVisible(modelToView);
                        }
                    }
                    catch (BadLocationException ex) {
                        this.getToolkit().beep();
                    }
                }
                iterator.next();
            }
        }
    }
    
    private void setCharsetFromContentTypeParameters(String substring) {
        try {
            final int index = substring.indexOf(59);
            if (index > -1 && index < substring.length() - 1) {
                substring = substring.substring(index + 1);
            }
            if (substring.length() > 0) {
                this.charSetName = new HeaderParser(substring).findValue("charset");
            }
        }
        catch (IndexOutOfBoundsException ex2) {}
        catch (NullPointerException ex3) {}
        catch (Exception ex) {
            System.err.println("JEditorPane.getCharsetFromContentTypeParameters failed on: " + substring);
            ex.printStackTrace();
        }
    }
    
    public final void setContentType(String trim) {
        final int index = trim.indexOf(";");
        if (index > -1) {
            final String substring = trim.substring(index);
            trim = trim.substring(0, index).trim();
            if (trim.toLowerCase().startsWith("text/")) {
                this.setCharsetFromContentTypeParameters(substring);
            }
        }
        if (this.kit == null || !trim.equals(this.kit.getContentType())) {
            final EditorKit editorKitForContentType = this.getEditorKitForContentType(trim);
            if (editorKitForContentType != null) {
                this.setEditorKit(editorKitForContentType);
            }
        }
    }
    
    public void setEditorKit(final EditorKit kit) {
        final EditorKit kit2 = this.kit;
        if (kit2 != null) {
            kit2.deinstall(this);
        }
        this.kit = kit;
        if (this.kit != null) {
            this.kit.install(this);
            this.setDocument(this.kit.createDefaultDocument());
        }
        this.firePropertyChange("editorKit", kit2, kit);
    }
    
    public void setEditorKitForContentType(final String s, final EditorKit editorKit) {
        if (this.typeHandlers == null) {
            this.typeHandlers = new Hashtable(3);
        }
        this.typeHandlers.put(s, editorKit);
    }
    
    public void setPage(final String s) throws IOException {
        if (s == null) {
            throw new IOException("invalid url");
        }
        this.setPage(new URL(s));
    }
    
    public void setPage(final URL url) throws IOException {
        if (url == null) {
            throw new IOException("invalid url");
        }
        final URL page = this.getPage();
        this.scrollRectToVisible(new Rectangle(0, 0, 1, 1));
        boolean b = false;
        if (page == null || !page.sameFile(url)) {
            final InputStream stream = this.getStream(url);
            if (this.kit != null) {
                final Document defaultDocument = this.kit.createDefaultDocument();
                if (this.pageProperties != null) {
                    final Enumeration<Object> keys = (Enumeration<Object>)this.pageProperties.keys();
                    while (keys.hasMoreElements()) {
                        final Object nextElement = keys.nextElement();
                        defaultDocument.putProperty(nextElement, this.pageProperties.get(nextElement));
                    }
                    this.pageProperties.clear();
                }
                if (defaultDocument.getProperty("stream") == null) {
                    defaultDocument.putProperty("stream", url);
                }
                if (defaultDocument instanceof AbstractDocument) {
                    final int asynchronousLoadPriority = ((AbstractDocument)defaultDocument).getAsynchronousLoadPriority();
                    if (asynchronousLoadPriority >= 0) {
                        this.setDocument(defaultDocument);
                        new PageLoader(stream, asynchronousLoadPriority, page, url).start();
                        return;
                    }
                }
                this.read(stream, defaultDocument);
                this.setDocument(defaultDocument);
                b = true;
            }
        }
        final String ref = url.getRef();
        if (ref != null) {
            if (!b) {
                this.scrollToReference(ref);
            }
            else {
                SwingUtilities.invokeLater(new Runnable() {
                    private final /* synthetic */ String val$reference = val$reference;
                    
                    public void run() {
                        JEditorPane.this.scrollToReference(this.val$reference);
                    }
                });
            }
        }
        this.firePropertyChange("page", page, url);
    }
    
    public void setText(final String s) {
        try {
            final Document document = this.getDocument();
            document.remove(0, document.getLength());
            this.getEditorKit().read(new StringReader(s), document, 0);
        }
        catch (IOException ex) {
            this.getToolkit().beep();
        }
        catch (BadLocationException ex2) {
            this.getToolkit().beep();
        }
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        if (super.ui != null && this.getUIClassID().equals("EditorPaneUI")) {
            super.ui.installUI(this);
        }
    }
    
    class PageLoader extends Thread
    {
        private final /* synthetic */ JEditorPane this$0;
        InputStream in;
        URL old;
        URL page;
        
        PageLoader(final InputStream in, final int priority, final URL old, final URL page) {
            this.setPriority(priority);
            this.in = in;
            this.old = old;
            this.page = page;
        }
        
        public void run() {
            final Document document = JEditorPane.this.getDocument();
            try {
                JEditorPane.this.read(this.in, document);
                if (((URL)document.getProperty("stream")).getRef() != null) {
                    SwingUtilities.invokeLater(new Runnable() {
                        private final /* synthetic */ PageLoader this$1 = this$1;
                        
                        public void run() {
                            this.this$1.this$0.scrollToReference(((URL)this.this$1.this$0.getDocument().getProperty("stream")).getRef());
                        }
                    });
                }
            }
            catch (IOException ex) {
                JEditorPane.this.getToolkit().beep();
            }
            finally {
                JEditorPane.this.firePropertyChange("page", this.old, this.page);
            }
        }
    }
    
    protected class JEditorPaneAccessibleHypertextSupport extends AccessibleJEditorPane implements AccessibleHypertext
    {
        LinkVector hyperlinks;
        boolean linksValid;
        
        public JEditorPaneAccessibleHypertextSupport() {
            this.linksValid = false;
            this.hyperlinks = new LinkVector();
            final Document document = JEditorPane.this.getDocument();
            if (document != null) {
                document.addDocumentListener(new DocumentListener() {
                    private final /* synthetic */ JEditorPaneAccessibleHypertextSupport this$1 = this$1;
                    
                    public void changedUpdate(final DocumentEvent documentEvent) {
                        this.this$1.linksValid = false;
                    }
                    
                    public void insertUpdate(final DocumentEvent documentEvent) {
                        this.this$1.linksValid = false;
                    }
                    
                    public void removeUpdate(final DocumentEvent documentEvent) {
                        this.this$1.linksValid = false;
                    }
                });
            }
        }
        
        private void buildLinkTable() {
            this.hyperlinks.removeAllElements();
            final Document document = JEditorPane.this.getDocument();
            if (document != null) {
                Element next;
                while ((next = new ElementIterator(document).next()) != null) {
                    if (next.isLeaf()) {
                        final AttributeSet set = (AttributeSet)next.getAttributes().getAttribute(HTML.Tag.A);
                        if (((set != null) ? ((String)set.getAttribute(HTML.Attribute.HREF)) : null) == null) {
                            continue;
                        }
                        this.hyperlinks.addElement(new HTMLLink(next));
                    }
                }
            }
            this.linksValid = true;
        }
        
        public AccessibleHyperlink getLink(final int n) {
            if (!this.linksValid) {
                this.buildLinkTable();
            }
            if (n >= 0 && n < this.hyperlinks.size()) {
                return this.hyperlinks.elementAt(n);
            }
            return null;
        }
        
        public int getLinkCount() {
            if (!this.linksValid) {
                this.buildLinkTable();
            }
            return this.hyperlinks.size();
        }
        
        public int getLinkIndex(final int n) {
            if (!this.linksValid) {
                this.buildLinkTable();
            }
            Element element = null;
            final Document document = JEditorPane.this.getDocument();
            if (document != null) {
                for (element = document.getDefaultRootElement(); !element.isLeaf(); element = element.getElement(element.getElementIndex(n))) {}
            }
            return this.hyperlinks.baseElementIndex(element);
        }
        
        public String getLinkText(final int n) {
            if (!this.linksValid) {
                this.buildLinkTable();
            }
            final Element element = this.hyperlinks.elementAt(n);
            if (element != null) {
                final Document document = JEditorPane.this.getDocument();
                if (document != null) {
                    try {
                        return document.getText(element.getStartOffset(), element.getEndOffset() - element.getStartOffset());
                    }
                    catch (BadLocationException ex) {
                        return null;
                    }
                }
            }
            return null;
        }
        
        public class HTMLLink extends AccessibleHyperlink
        {
            Element element;
            
            public HTMLLink(final Element element) {
                this.element = element;
            }
            
            public boolean doAccessibleAction(final int n) {
                if (n == 0 && this.isValid()) {
                    final URL url = (URL)this.getAccessibleActionObject(n);
                    if (url != null) {
                        JEditorPane.this.fireHyperlinkUpdate(new HyperlinkEvent(JEditorPane.this, HyperlinkEvent.EventType.ACTIVATED, url));
                        return true;
                    }
                }
                return false;
            }
            
            public Object getAccessibleActionAnchor(final int n) {
                return this.getAccessibleActionDescription(n);
            }
            
            public int getAccessibleActionCount() {
                return 1;
            }
            
            public String getAccessibleActionDescription(final int n) {
                if (n == 0 && this.isValid()) {
                    final Document document = JEditorPane.this.getDocument();
                    if (document != null) {
                        try {
                            return document.getText(this.getStartIndex(), this.getEndIndex() - this.getStartIndex());
                        }
                        catch (BadLocationException ex) {
                            return null;
                        }
                    }
                }
                return null;
            }
            
            public Object getAccessibleActionObject(final int n) {
                if (n == 0 && this.isValid()) {
                    final AttributeSet set = (AttributeSet)this.element.getAttributes().getAttribute(HTML.Tag.A);
                    final String s = (set != null) ? ((String)set.getAttribute(HTML.Attribute.HREF)) : null;
                    if (s != null) {
                        Object o;
                        try {
                            o = new URL(JEditorPane.this.getPage(), s);
                        }
                        catch (MalformedURLException ex) {
                            o = null;
                        }
                        return o;
                    }
                }
                return null;
            }
            
            public int getEndIndex() {
                return this.element.getEndOffset();
            }
            
            public int getStartIndex() {
                return this.element.getStartOffset();
            }
            
            public boolean isValid() {
                return JEditorPaneAccessibleHypertextSupport.this.linksValid;
            }
        }
        
        private class LinkVector extends Vector
        {
            public int baseElementIndex(final Element element) {
                for (int i = 0; i < super.elementCount; ++i) {
                    if (((HTMLLink)this.elementAt(i)).element == element) {
                        return i;
                    }
                }
                return -1;
            }
        }
    }
    
    protected class AccessibleJEditorPane extends AccessibleJTextComponent
    {
        public String getAccessibleDescription() {
            if (super.accessibleDescription != null) {
                return super.accessibleDescription;
            }
            return JEditorPane.this.getContentType();
        }
        
        public AccessibleStateSet getAccessibleStateSet() {
            final AccessibleStateSet accessibleStateSet = super.getAccessibleStateSet();
            accessibleStateSet.add(AccessibleState.MULTI_LINE);
            return accessibleStateSet;
        }
    }
    
    protected class AccessibleJEditorPaneHTML extends AccessibleJEditorPane
    {
        public AccessibleText getAccessibleText() {
            return new JEditorPaneAccessibleHypertextSupport();
        }
    }
    
    static class PlainEditorKit extends DefaultEditorKit implements ViewFactory
    {
        public Object clone() {
            return new PlainEditorKit();
        }
        
        public View create(final Element element) {
            return new WrappedPlainView(element);
        }
        
        public ViewFactory getViewFactory() {
            return this;
        }
    }
}
