// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.html.dom;

import org.apache.xerces.util.ObjectFactory;
import org.w3c.dom.html.HTMLCollection;
import org.w3c.dom.Attr;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.w3c.dom.DOMException;
import java.util.Locale;
import org.apache.xerces.dom.NodeImpl;
import org.w3c.dom.html.HTMLFrameSetElement;
import org.w3c.dom.html.HTMLBodyElement;
import org.w3c.dom.NodeList;
import org.w3c.dom.html.HTMLTitleElement;
import org.w3c.dom.html.HTMLHeadElement;
import org.w3c.dom.Node;
import org.w3c.dom.html.HTMLElement;
import org.w3c.dom.html.HTMLHtmlElement;
import org.w3c.dom.Element;
import java.util.Hashtable;
import java.io.StringWriter;
import org.w3c.dom.html.HTMLDocument;
import org.apache.xerces.dom.DocumentImpl;

public class HTMLDocumentImpl extends DocumentImpl implements HTMLDocument
{
    private HTMLCollectionImpl _anchors;
    private HTMLCollectionImpl _forms;
    private HTMLCollectionImpl _images;
    private HTMLCollectionImpl _links;
    private HTMLCollectionImpl _applets;
    private StringWriter _writer;
    private static Hashtable _elementTypesHTML;
    private static final Class[] _elemClassSigHTML;
    static /* synthetic */ Class class$org$apache$html$dom$HTMLDocumentImpl;
    static /* synthetic */ Class class$java$lang$String;
    
    public HTMLDocumentImpl() {
        populateElementTypes();
    }
    
    public synchronized Element getDocumentElement() {
        for (Node html = this.getFirstChild(); html != null; html = html.getNextSibling()) {
            if (html instanceof HTMLHtmlElement) {
                synchronized (html) {
                    Node next;
                    for (Node child = this.getFirstChild(); child != null && child != html; child = next) {
                        next = child.getNextSibling();
                        html.appendChild(child);
                    }
                }
                return (HTMLElement)html;
            }
        }
        Node html = new HTMLHtmlElementImpl(this, "HTML");
        Node next;
        for (Node child = this.getFirstChild(); child != null; child = next) {
            next = child.getNextSibling();
            html.appendChild(child);
        }
        this.appendChild(html);
        return (HTMLElement)html;
    }
    
    public synchronized HTMLElement getHead() {
        final Node html = this.getDocumentElement();
        Node head;
        synchronized (html) {
            for (head = html.getFirstChild(); head != null && !(head instanceof HTMLHeadElement); head = head.getNextSibling()) {}
            if (head != null) {
                synchronized (head) {
                    Node next;
                    for (Node child = html.getFirstChild(); child != null && child != head; child = next) {
                        next = child.getNextSibling();
                        head.insertBefore(child, head.getFirstChild());
                    }
                }
                return (HTMLElement)head;
            }
            head = new HTMLHeadElementImpl(this, "HEAD");
            html.insertBefore(head, html.getFirstChild());
        }
        return (HTMLElement)head;
    }
    
    public synchronized String getTitle() {
        final HTMLElement head = this.getHead();
        Node title = head.getElementsByTagName("TITLE").item(0);
        final NodeList list = head.getElementsByTagName("TITLE");
        if (list.getLength() > 0) {
            title = list.item(0);
            return ((HTMLTitleElement)title).getText();
        }
        return "";
    }
    
    public synchronized void setTitle(final String newTitle) {
        final HTMLElement head = this.getHead();
        final NodeList list = head.getElementsByTagName("TITLE");
        if (list.getLength() > 0) {
            final Node title = list.item(0);
            if (title.getParentNode() != head) {
                head.appendChild(title);
            }
            ((HTMLTitleElement)title).setText(newTitle);
        }
        else {
            final Node title = new HTMLTitleElementImpl(this, "TITLE");
            ((HTMLTitleElement)title).setText(newTitle);
            head.appendChild(title);
        }
    }
    
    public synchronized HTMLElement getBody() {
        final Node html = this.getDocumentElement();
        final Node head = this.getHead();
        Node body;
        synchronized (html) {
            for (body = head.getNextSibling(); body != null && !(body instanceof HTMLBodyElement) && !(body instanceof HTMLFrameSetElement); body = body.getNextSibling()) {}
            if (body != null) {
                synchronized (body) {
                    Node next;
                    for (Node child = head.getNextSibling(); child != null && child != body; child = next) {
                        next = child.getNextSibling();
                        body.insertBefore(child, body.getFirstChild());
                    }
                }
                return (HTMLElement)body;
            }
            body = new HTMLBodyElementImpl(this, "BODY");
            html.appendChild(body);
        }
        return (HTMLElement)body;
    }
    
    public synchronized void setBody(final HTMLElement newBody) {
        synchronized (newBody) {
            final Node html = this.getDocumentElement();
            final Node head = this.getHead();
            synchronized (html) {
                final NodeList list = this.getElementsByTagName("BODY");
                if (list.getLength() > 0) {
                    final Node body = list.item(0);
                    synchronized (body) {
                        for (Node child = head; child != null; child = child.getNextSibling()) {
                            if (child instanceof Element) {
                                if (child != body) {
                                    html.insertBefore(newBody, child);
                                }
                                else {
                                    html.replaceChild(newBody, body);
                                }
                                return;
                            }
                        }
                        html.appendChild(newBody);
                    }
                    return;
                }
                html.appendChild(newBody);
            }
        }
    }
    
    public synchronized Element getElementById(final String elementId) {
        return this.getElementById(elementId, this);
    }
    
    public NodeList getElementsByName(final String elementName) {
        return new NameNodeListImpl(this, elementName);
    }
    
    public final NodeList getElementsByTagName(final String tagName) {
        return super.getElementsByTagName(tagName.toUpperCase(Locale.ENGLISH));
    }
    
    public final NodeList getElementsByTagNameNS(final String namespaceURI, final String localName) {
        if (namespaceURI != null && namespaceURI.length() > 0) {
            return super.getElementsByTagNameNS(namespaceURI, localName.toUpperCase(Locale.ENGLISH));
        }
        return super.getElementsByTagName(localName.toUpperCase(Locale.ENGLISH));
    }
    
    public Element createElementNS(final String namespaceURI, final String qualifiedName, final String localpart) throws DOMException {
        return this.createElementNS(namespaceURI, qualifiedName);
    }
    
    public Element createElementNS(final String namespaceURI, final String qualifiedName) {
        if (namespaceURI == null || namespaceURI.length() == 0) {
            return this.createElement(qualifiedName);
        }
        return super.createElementNS(namespaceURI, qualifiedName);
    }
    
    public Element createElement(String tagName) throws DOMException {
        tagName = tagName.toUpperCase(Locale.ENGLISH);
        final Class elemClass = HTMLDocumentImpl._elementTypesHTML.get(tagName);
        if (elemClass != null) {
            try {
                final Constructor cnst = elemClass.getConstructor((Class[])HTMLDocumentImpl._elemClassSigHTML);
                return cnst.newInstance(this, tagName);
            }
            catch (Exception except) {
                if (except instanceof InvocationTargetException) {
                    final Throwable thrw = ((InvocationTargetException)except).getTargetException();
                }
                else {
                    final Throwable thrw = except;
                }
                throw new IllegalStateException("HTM15 Tag '" + tagName + "' associated with an Element class that failed to construct.\n" + tagName);
            }
        }
        return new HTMLElementImpl(this, tagName);
    }
    
    public Attr createAttribute(final String name) throws DOMException {
        return super.createAttribute(name.toLowerCase(Locale.ENGLISH));
    }
    
    public String getReferrer() {
        return null;
    }
    
    public String getDomain() {
        return null;
    }
    
    public String getURL() {
        return null;
    }
    
    public String getCookie() {
        return null;
    }
    
    public void setCookie(final String cookie) {
    }
    
    public HTMLCollection getImages() {
        if (this._images == null) {
            this._images = new HTMLCollectionImpl(this.getBody(), (short)3);
        }
        return this._images;
    }
    
    public HTMLCollection getApplets() {
        if (this._applets == null) {
            this._applets = new HTMLCollectionImpl(this.getBody(), (short)4);
        }
        return this._applets;
    }
    
    public HTMLCollection getLinks() {
        if (this._links == null) {
            this._links = new HTMLCollectionImpl(this.getBody(), (short)5);
        }
        return this._links;
    }
    
    public HTMLCollection getForms() {
        if (this._forms == null) {
            this._forms = new HTMLCollectionImpl(this.getBody(), (short)2);
        }
        return this._forms;
    }
    
    public HTMLCollection getAnchors() {
        if (this._anchors == null) {
            this._anchors = new HTMLCollectionImpl(this.getBody(), (short)1);
        }
        return this._anchors;
    }
    
    public void open() {
        if (this._writer == null) {
            this._writer = new StringWriter();
        }
    }
    
    public void close() {
        if (this._writer != null) {
            this._writer = null;
        }
    }
    
    public void write(final String text) {
        if (this._writer != null) {
            this._writer.write(text);
        }
    }
    
    public void writeln(final String text) {
        if (this._writer != null) {
            this._writer.write(text + "\n");
        }
    }
    
    public Node cloneNode(final boolean deep) {
        final HTMLDocumentImpl clone = new HTMLDocumentImpl();
        if (deep) {
            for (NodeImpl node = (NodeImpl)this.getFirstChild(); node != null; node = (NodeImpl)node.getNextSibling()) {
                clone.appendChild(clone.importNode(node, true));
            }
        }
        return clone;
    }
    
    private Element getElementById(final String elementId, final Node node) {
        for (Node child = node.getFirstChild(); child != null; child = child.getNextSibling()) {
            if (child instanceof Element) {
                if (elementId.equals(((Element)child).getAttribute("id"))) {
                    return (Element)child;
                }
                final Element result = this.getElementById(elementId, child);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }
    
    private static synchronized void populateElementTypes() {
        if (HTMLDocumentImpl._elementTypesHTML != null) {
            return;
        }
        HTMLDocumentImpl._elementTypesHTML = new Hashtable(63);
        populateElementType("A", "HTMLAnchorElementImpl");
        populateElementType("APPLET", "HTMLAppletElementImpl");
        populateElementType("AREA", "HTMLAreaElementImpl");
        populateElementType("BASE", "HTMLBaseElementImpl");
        populateElementType("BASEFONT", "HTMLBaseFontElementImpl");
        populateElementType("BLOCKQUOTE", "HTMLQuoteElementImpl");
        populateElementType("BODY", "HTMLBodyElementImpl");
        populateElementType("BR", "HTMLBRElementImpl");
        populateElementType("BUTTON", "HTMLButtonElementImpl");
        populateElementType("DEL", "HTMLModElementImpl");
        populateElementType("DIR", "HTMLDirectoryElementImpl");
        populateElementType("DIV", "HTMLDivElementImpl");
        populateElementType("DL", "HTMLDListElementImpl");
        populateElementType("FIELDSET", "HTMLFieldSetElementImpl");
        populateElementType("FONT", "HTMLFontElementImpl");
        populateElementType("FORM", "HTMLFormElementImpl");
        populateElementType("FRAME", "HTMLFrameElementImpl");
        populateElementType("FRAMESET", "HTMLFrameSetElementImpl");
        populateElementType("HEAD", "HTMLHeadElementImpl");
        populateElementType("H1", "HTMLHeadingElementImpl");
        populateElementType("H2", "HTMLHeadingElementImpl");
        populateElementType("H3", "HTMLHeadingElementImpl");
        populateElementType("H4", "HTMLHeadingElementImpl");
        populateElementType("H5", "HTMLHeadingElementImpl");
        populateElementType("H6", "HTMLHeadingElementImpl");
        populateElementType("HR", "HTMLHRElementImpl");
        populateElementType("HTML", "HTMLHtmlElementImpl");
        populateElementType("IFRAME", "HTMLIFrameElementImpl");
        populateElementType("IMG", "HTMLImageElementImpl");
        populateElementType("INPUT", "HTMLInputElementImpl");
        populateElementType("INS", "HTMLModElementImpl");
        populateElementType("ISINDEX", "HTMLIsIndexElementImpl");
        populateElementType("LABEL", "HTMLLabelElementImpl");
        populateElementType("LEGEND", "HTMLLegendElementImpl");
        populateElementType("LI", "HTMLLIElementImpl");
        populateElementType("LINK", "HTMLLinkElementImpl");
        populateElementType("MAP", "HTMLMapElementImpl");
        populateElementType("MENU", "HTMLMenuElementImpl");
        populateElementType("META", "HTMLMetaElementImpl");
        populateElementType("OBJECT", "HTMLObjectElementImpl");
        populateElementType("OL", "HTMLOListElementImpl");
        populateElementType("OPTGROUP", "HTMLOptGroupElementImpl");
        populateElementType("OPTION", "HTMLOptionElementImpl");
        populateElementType("P", "HTMLParagraphElementImpl");
        populateElementType("PARAM", "HTMLParamElementImpl");
        populateElementType("PRE", "HTMLPreElementImpl");
        populateElementType("Q", "HTMLQuoteElementImpl");
        populateElementType("SCRIPT", "HTMLScriptElementImpl");
        populateElementType("SELECT", "HTMLSelectElementImpl");
        populateElementType("STYLE", "HTMLStyleElementImpl");
        populateElementType("TABLE", "HTMLTableElementImpl");
        populateElementType("CAPTION", "HTMLTableCaptionElementImpl");
        populateElementType("TD", "HTMLTableCellElementImpl");
        populateElementType("TH", "HTMLTableCellElementImpl");
        populateElementType("COL", "HTMLTableColElementImpl");
        populateElementType("COLGROUP", "HTMLTableColElementImpl");
        populateElementType("TR", "HTMLTableRowElementImpl");
        populateElementType("TBODY", "HTMLTableSectionElementImpl");
        populateElementType("THEAD", "HTMLTableSectionElementImpl");
        populateElementType("TFOOT", "HTMLTableSectionElementImpl");
        populateElementType("TEXTAREA", "HTMLTextAreaElementImpl");
        populateElementType("TITLE", "HTMLTitleElementImpl");
        populateElementType("UL", "HTMLUListElementImpl");
    }
    
    private static void populateElementType(final String tagName, final String className) {
        try {
            HTMLDocumentImpl._elementTypesHTML.put(tagName, ObjectFactory.findClassLoader().loadClass("org.apache.html.dom." + className));
        }
        catch (Exception except) {
            final RuntimeException ex = new RuntimeException("HTM019 OpenXML Error: Could not find or execute class " + className + " implementing HTML element " + tagName + "\n" + className + "\t" + tagName);
        }
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError(x.getMessage());
        }
    }
    
    static {
        _elemClassSigHTML = new Class[] { (HTMLDocumentImpl.class$org$apache$html$dom$HTMLDocumentImpl == null) ? (HTMLDocumentImpl.class$org$apache$html$dom$HTMLDocumentImpl = class$("org.apache.html.dom.HTMLDocumentImpl")) : HTMLDocumentImpl.class$org$apache$html$dom$HTMLDocumentImpl, (HTMLDocumentImpl.class$java$lang$String == null) ? (HTMLDocumentImpl.class$java$lang$String = class$("java.lang.String")) : HTMLDocumentImpl.class$java$lang$String };
    }
}
