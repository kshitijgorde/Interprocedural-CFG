// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.serialize;

import org.xml.sax.AttributeList;
import org.xml.sax.Attributes;
import java.lang.reflect.Method;
import org.w3c.dom.DocumentType;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Hashtable;
import java.util.Vector;
import org.xml.sax.ext.DeclHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.DocumentHandler;
import org.xml.sax.ContentHandler;

public abstract class BaseMarkupSerializer implements ContentHandler, DocumentHandler, LexicalHandler, DTDHandler, DeclHandler, DOMSerializer, Serializer
{
    private EncodingInfo _encodingInfo;
    private ElementState[] _elementStates;
    private int _elementStateCount;
    private Vector _preRoot;
    protected boolean _started;
    private boolean _prepared;
    protected Hashtable _prefixes;
    protected String _docTypePublicId;
    protected String _docTypeSystemId;
    protected OutputFormat _format;
    protected Printer _printer;
    protected boolean _indenting;
    private Writer _writer;
    private OutputStream _output;
    static /* synthetic */ Class class$java$lang$String;
    
    protected BaseMarkupSerializer(final OutputFormat format) {
        this._elementStates = new ElementState[10];
        for (int i = 0; i < this._elementStates.length; ++i) {
            this._elementStates[i] = new ElementState();
        }
        this._format = format;
    }
    
    public DocumentHandler asDocumentHandler() throws IOException {
        this.prepare();
        return this;
    }
    
    public ContentHandler asContentHandler() throws IOException {
        this.prepare();
        return this;
    }
    
    public DOMSerializer asDOMSerializer() throws IOException {
        this.prepare();
        return this;
    }
    
    public void setOutputByteStream(final OutputStream output) {
        if (output == null) {
            throw new NullPointerException("SER001 Argument 'output' is null.");
        }
        this._output = output;
        this._writer = null;
        this.reset();
    }
    
    public void setOutputCharStream(final Writer writer) {
        if (writer == null) {
            throw new NullPointerException("SER001 Argument 'writer' is null.");
        }
        this._writer = writer;
        this._output = null;
        this.reset();
    }
    
    public void setOutputFormat(final OutputFormat format) {
        if (format == null) {
            throw new NullPointerException("SER001 Argument 'format' is null.");
        }
        this._format = format;
        this.reset();
    }
    
    public boolean reset() {
        if (this._elementStateCount > 1) {
            throw new IllegalStateException("Serializer reset in the middle of serialization");
        }
        this._prepared = false;
        return true;
    }
    
    protected void prepare() throws IOException {
        if (this._prepared) {
            return;
        }
        if (this._writer == null && this._output == null) {
            throw new IOException("SER002 No writer supplied for serializer");
        }
        this._encodingInfo = this._format.getEncodingInfo();
        if (this._output != null) {
            this._writer = this._encodingInfo.getWriter(this._output);
        }
        if (this._format.getIndenting()) {
            this._indenting = true;
            this._printer = new IndentPrinter(this._writer, this._format);
        }
        else {
            this._indenting = false;
            this._printer = new Printer(this._writer, this._format);
        }
        this._elementStateCount = 0;
        final ElementState elementState = this._elementStates[0];
        elementState.namespaceURI = null;
        elementState.localName = null;
        elementState.rawName = null;
        elementState.preserveSpace = this._format.getPreserveSpace();
        elementState.empty = true;
        elementState.afterElement = false;
        final ElementState elementState2 = elementState;
        final ElementState elementState3 = elementState;
        final boolean b = false;
        elementState3.inCData = b;
        elementState2.doCData = b;
        elementState.prefixes = null;
        this._docTypePublicId = this._format.getDoctypePublic();
        this._docTypeSystemId = this._format.getDoctypeSystem();
        this._started = false;
        this._prepared = true;
    }
    
    public void serialize(final Element element) throws IOException {
        this.prepare();
        this.serializeNode(element);
        this._printer.flush();
        if (this._printer.getException() != null) {
            throw this._printer.getException();
        }
    }
    
    public void serialize(final DocumentFragment documentFragment) throws IOException {
        this.prepare();
        this.serializeNode(documentFragment);
        this._printer.flush();
        if (this._printer.getException() != null) {
            throw this._printer.getException();
        }
    }
    
    public void serialize(final Document document) throws IOException {
        this.prepare();
        this.serializeNode(document);
        this.serializePreRoot();
        this._printer.flush();
        if (this._printer.getException() != null) {
            throw this._printer.getException();
        }
    }
    
    public void startDocument() throws SAXException {
        try {
            this.prepare();
        }
        catch (IOException ex) {
            throw new SAXException(ex.toString());
        }
    }
    
    public void characters(final char[] array, int n, int n2) {
        final ElementState content = this.content();
        if (content.inCData || content.doCData) {
            if (!content.inCData) {
                this._printer.printText("<![CDATA[");
                content.inCData = true;
            }
            final int nextIndent = this._printer.getNextIndent();
            this._printer.setNextIndent(0);
            for (int i = 0; i < n2; ++i) {
                if (i + 2 < n2 && array[i] == ']' && array[i + 1] == ']' && array[i + 2] == '>') {
                    this.printText(array, n, i + 2, true, true);
                    this._printer.printText("]]><![CDATA[");
                    n += i + 2;
                    n2 -= i + 2;
                    i = 0;
                }
            }
            if (n2 > 0) {
                this.printText(array, n, n2, true, true);
            }
            this._printer.setNextIndent(nextIndent);
        }
        else if (content.preserveSpace) {
            final int nextIndent2 = this._printer.getNextIndent();
            this._printer.setNextIndent(0);
            this.printText(array, n, n2, true, content.unescaped);
            this._printer.setNextIndent(nextIndent2);
        }
        else {
            this.printText(array, n, n2, false, content.unescaped);
        }
    }
    
    public void ignorableWhitespace(final char[] array, final int n, int n2) {
        this.content();
        if (this._indenting) {
            this._printer.setThisIndent(0);
            int n3 = n;
            while (n2-- > 0) {
                this._printer.printText(array[n3]);
                ++n3;
            }
        }
    }
    
    public void processingInstruction(final String s, final String s2) {
        this.content();
        final StringBuffer sb = new StringBuffer(40);
        final int index = s.indexOf("?>");
        if (index >= 0) {
            sb.append("<?").append(s.substring(0, index));
        }
        else {
            sb.append("<?").append(s);
        }
        if (s2 != null) {
            sb.append(' ');
            final int index2 = s2.indexOf("?>");
            if (index2 >= 0) {
                sb.append(s2.substring(0, index2));
            }
            else {
                sb.append(s2);
            }
        }
        sb.append("?>");
        if (this.isDocumentState()) {
            if (this._preRoot == null) {
                this._preRoot = new Vector();
            }
            this._preRoot.addElement(sb.toString());
        }
        else {
            this._printer.indent();
            this.printText(sb.toString(), true, true);
            this._printer.unindent();
        }
    }
    
    public void comment(final char[] array, final int n, final int n2) {
        this.comment(new String(array, n, n2));
    }
    
    public void comment(final String s) {
        if (this._format.getOmitComments()) {
            return;
        }
        this.content();
        final StringBuffer sb = new StringBuffer(40);
        final int index = s.indexOf("-->");
        if (index >= 0) {
            sb.append("<!--").append(s.substring(0, index)).append("-->");
        }
        else {
            sb.append("<!--").append(s).append("-->");
        }
        if (this.isDocumentState()) {
            if (this._preRoot == null) {
                this._preRoot = new Vector();
            }
            this._preRoot.addElement(sb.toString());
        }
        else {
            this._printer.indent();
            this.printText(sb.toString(), false, true);
            this._printer.unindent();
        }
    }
    
    public void startCDATA() {
        this.getElementState().doCData = true;
    }
    
    public void endCDATA() {
        this.getElementState().doCData = false;
    }
    
    public void startNonEscaping() {
        this.getElementState().unescaped = true;
    }
    
    public void endNonEscaping() {
        this.getElementState().unescaped = false;
    }
    
    public void startPreserving() {
        this.getElementState().preserveSpace = true;
    }
    
    public void endPreserving() {
        this.getElementState().preserveSpace = false;
    }
    
    public void endDocument() throws SAXException {
        this.serializePreRoot();
        this._printer.flush();
        if (this._printer.getException() != null) {
            throw new SAXException(this._printer.getException());
        }
    }
    
    public void startEntity(final String s) {
    }
    
    public void endEntity(final String s) {
    }
    
    public void setDocumentLocator(final Locator locator) {
    }
    
    public void skippedEntity(final String s) throws SAXException {
        this.endCDATA();
        this.content();
        this._printer.printText('&');
        this._printer.printText(s);
        this._printer.printText(';');
    }
    
    public void startPrefixMapping(final String s, final String s2) throws SAXException {
        if (this._prefixes == null) {
            this._prefixes = new Hashtable();
        }
        this._prefixes.put(s2, (s == null) ? "" : s);
    }
    
    public void endPrefixMapping(final String s) throws SAXException {
    }
    
    public void startDTD(final String s, final String docTypePublicId, final String docTypeSystemId) {
        this._printer.enterDTD();
        this._docTypePublicId = docTypePublicId;
        this._docTypeSystemId = docTypeSystemId;
    }
    
    public void endDTD() {
    }
    
    public void elementDecl(final String s, final String s2) {
        this._printer.enterDTD();
        this._printer.printText("<!ELEMENT ");
        this._printer.printText(s);
        this._printer.printText(' ');
        this._printer.printText(s2);
        this._printer.printText('>');
        if (this._indenting) {
            this._printer.breakLine();
        }
    }
    
    public void attributeDecl(final String s, final String s2, final String s3, final String s4, final String s5) {
        this._printer.enterDTD();
        this._printer.printText("<!ATTLIST ");
        this._printer.printText(s);
        this._printer.printText(' ');
        this._printer.printText(s2);
        this._printer.printText(' ');
        this._printer.printText(s3);
        if (s4 != null) {
            this._printer.printText(' ');
            this._printer.printText(s4);
        }
        if (s5 != null) {
            this._printer.printText(" \"");
            this.printEscaped(s5);
            this._printer.printText('\"');
        }
        this._printer.printText('>');
        if (this._indenting) {
            this._printer.breakLine();
        }
    }
    
    public void internalEntityDecl(final String s, final String s2) {
        this._printer.enterDTD();
        this._printer.printText("<!ENTITY ");
        this._printer.printText(s);
        this._printer.printText(" \"");
        this.printEscaped(s2);
        this._printer.printText("\">");
        if (this._indenting) {
            this._printer.breakLine();
        }
    }
    
    public void externalEntityDecl(final String s, final String s2, final String s3) {
        this._printer.enterDTD();
        this.unparsedEntityDecl(s, s2, s3, null);
    }
    
    public void unparsedEntityDecl(final String s, final String s2, final String s3, final String s4) {
        this._printer.enterDTD();
        if (s2 == null) {
            this._printer.printText("<!ENTITY ");
            this._printer.printText(s);
            this._printer.printText(" SYSTEM ");
            this.printDoctypeURL(s3);
        }
        else {
            this._printer.printText("<!ENTITY ");
            this._printer.printText(s);
            this._printer.printText(" PUBLIC ");
            this.printDoctypeURL(s2);
            this._printer.printText(' ');
            this.printDoctypeURL(s3);
        }
        if (s4 != null) {
            this._printer.printText(" NDATA ");
            this._printer.printText(s4);
        }
        this._printer.printText('>');
        if (this._indenting) {
            this._printer.breakLine();
        }
    }
    
    public void notationDecl(final String s, final String s2, final String s3) {
        this._printer.enterDTD();
        if (s2 != null) {
            this._printer.printText("<!NOTATION ");
            this._printer.printText(s);
            this._printer.printText(" PUBLIC ");
            this.printDoctypeURL(s2);
            if (s3 != null) {
                this._printer.printText(' ');
                this.printDoctypeURL(s3);
            }
        }
        else {
            this._printer.printText("<!NOTATION ");
            this._printer.printText(s);
            this._printer.printText(" SYSTEM ");
            this.printDoctypeURL(s3);
        }
        this._printer.printText('>');
        if (this._indenting) {
            this._printer.breakLine();
        }
    }
    
    protected void serializeNode(final Node node) {
        switch (node.getNodeType()) {
            case 3: {
                if (node.getNodeValue() != null) {
                    this.characters(node.getNodeValue());
                    break;
                }
                break;
            }
            case 4: {
                if (node.getNodeValue() != null) {
                    this.startCDATA();
                    this.characters(node.getNodeValue());
                    this.endCDATA();
                    break;
                }
                break;
            }
            case 8: {
                if (!this._format.getOmitComments() && node.getNodeValue() != null) {
                    this.comment(node.getNodeValue());
                    break;
                }
                break;
            }
            case 5: {
                this.endCDATA();
                this.content();
                for (Node node2 = node.getFirstChild(); node2 != null; node2 = node2.getNextSibling()) {
                    this.serializeNode(node2);
                }
                break;
            }
            case 7: {
                this.processingInstruction(node.getNodeName(), node.getNodeValue());
                break;
            }
            case 1: {
                this.serializeElement((Element)node);
                break;
            }
            case 9: {
                final DocumentType doctype = ((Document)node).getDoctype();
                if (doctype != null) {
                    ((Document)node).getImplementation();
                    try {
                        this.startDTD(doctype.getName(), doctype.getPublicId(), doctype.getSystemId());
                        final String internalSubset = doctype.getInternalSubset();
                        if (internalSubset != null && internalSubset.length() > 0) {
                            this._printer.printText(internalSubset);
                        }
                        this.endDTD();
                    }
                    catch (NoSuchMethodError noSuchMethodError) {
                        final Class<? extends DocumentType> class1 = doctype.getClass();
                        String s = null;
                        String s2 = null;
                        try {
                            final Method method = class1.getMethod("getPublicId", (Class[])null);
                            if (method.getReturnType().equals((BaseMarkupSerializer.class$java$lang$String == null) ? (BaseMarkupSerializer.class$java$lang$String = class$("java.lang.String")) : BaseMarkupSerializer.class$java$lang$String)) {
                                s = (String)method.invoke(doctype, (Object[])null);
                            }
                        }
                        catch (Exception ex) {}
                        try {
                            final Method method2 = class1.getMethod("getSystemId", (Class[])null);
                            if (method2.getReturnType().equals((BaseMarkupSerializer.class$java$lang$String == null) ? (BaseMarkupSerializer.class$java$lang$String = class$("java.lang.String")) : BaseMarkupSerializer.class$java$lang$String)) {
                                s2 = (String)method2.invoke(doctype, (Object[])null);
                            }
                        }
                        catch (Exception ex2) {}
                        this.startDTD(doctype.getName(), s, s2);
                        this.endDTD();
                    }
                }
            }
            case 11: {
                for (Node node3 = node.getFirstChild(); node3 != null; node3 = node3.getNextSibling()) {
                    this.serializeNode(node3);
                }
                break;
            }
        }
    }
    
    protected ElementState content() {
        final ElementState elementState = this.getElementState();
        if (!this.isDocumentState()) {
            if (elementState.inCData && !elementState.doCData) {
                this._printer.printText("]]>");
                elementState.inCData = false;
            }
            if (elementState.empty) {
                this._printer.printText('>');
                elementState.empty = false;
            }
            elementState.afterElement = false;
        }
        return elementState;
    }
    
    protected void characters(String substring) {
        final ElementState content = this.content();
        if (content.inCData || content.doCData) {
            final StringBuffer sb = new StringBuffer(substring.length());
            if (!content.inCData) {
                sb.append("<![CDATA[");
                content.inCData = true;
            }
            for (int i = substring.indexOf("]]>"); i >= 0; i = substring.indexOf("]]>")) {
                sb.append(substring.substring(0, i + 2)).append("]]><![CDATA[");
                substring = substring.substring(i + 2);
            }
            sb.append(substring);
            final int nextIndent = this._printer.getNextIndent();
            this._printer.setNextIndent(0);
            this.printText(sb.toString(), true, true);
            this._printer.setNextIndent(nextIndent);
        }
        else if (content.preserveSpace) {
            final int nextIndent2 = this._printer.getNextIndent();
            this._printer.setNextIndent(0);
            this.printText(substring, true, content.unescaped);
            this._printer.setNextIndent(nextIndent2);
        }
        else {
            this.printText(substring, false, content.unescaped);
        }
    }
    
    protected abstract String getEntityRef(final int p0);
    
    protected abstract void serializeElement(final Element p0);
    
    protected void serializePreRoot() {
        if (this._preRoot != null) {
            for (int i = 0; i < this._preRoot.size(); ++i) {
                this.printText((String)this._preRoot.elementAt(i), true, true);
                this._printer.breakLine();
            }
            this._preRoot.removeAllElements();
        }
    }
    
    protected final void printText(final char[] array, int n, int n2, final boolean b, final boolean b2) {
        if (b) {
            while (n2-- > 0) {
                final char c = array[n];
                ++n;
                if (c == '\n' || c == '\r' || b2) {
                    this._printer.printText(c);
                }
                else {
                    this.printEscaped(c);
                }
            }
        }
        else {
            while (n2-- > 0) {
                final char c2 = array[n];
                ++n;
                if (c2 == ' ' || c2 == '\f' || c2 == '\t' || c2 == '\n' || c2 == '\r') {
                    this._printer.printSpace();
                }
                else if (b2) {
                    this._printer.printText(c2);
                }
                else {
                    this.printEscaped(c2);
                }
            }
        }
    }
    
    protected final void printText(final String s, final boolean b, final boolean b2) {
        if (b) {
            for (int i = 0; i < s.length(); ++i) {
                final char char1 = s.charAt(i);
                if (char1 == '\n' || char1 == '\r' || b2) {
                    this._printer.printText(char1);
                }
                else {
                    this.printEscaped(char1);
                }
            }
        }
        else {
            for (int j = 0; j < s.length(); ++j) {
                final char char2 = s.charAt(j);
                if (char2 == ' ' || char2 == '\f' || char2 == '\t' || char2 == '\n' || char2 == '\r') {
                    this._printer.printSpace();
                }
                else if (b2) {
                    this._printer.printText(char2);
                }
                else {
                    this.printEscaped(char2);
                }
            }
        }
    }
    
    protected void printDoctypeURL(final String s) {
        this._printer.printText('\"');
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '\"' || s.charAt(i) < ' ' || s.charAt(i) > '\u007f') {
                this._printer.printText('%');
                this._printer.printText(Integer.toHexString(s.charAt(i)));
            }
            else {
                this._printer.printText(s.charAt(i));
            }
        }
        this._printer.printText('\"');
    }
    
    protected void printEscaped(final int n) {
        final String entityRef = this.getEntityRef(n);
        if (entityRef != null) {
            this._printer.printText('&');
            this._printer.printText(entityRef);
            this._printer.printText(';');
        }
        else if ((n >= 32 && this._encodingInfo.isPrintable(n) && n != 247) || n == 10 || n == 13 || n == 9) {
            if (n < 65536) {
                this._printer.printText((char)n);
            }
            else {
                this._printer.printText((char)((n - 65536 >> 10) + 55296));
                this._printer.printText((char)((n - 65536 & 0x3FF) + 56320));
            }
        }
        else {
            this._printer.printText("&#x");
            this._printer.printText(Integer.toHexString(n));
            this._printer.printText(';');
        }
    }
    
    protected void printEscaped(final String s) {
        for (int i = 0; i < s.length(); ++i) {
            int char1 = s.charAt(i);
            if ((char1 & 0xFC00) == 0xD800 && i + 1 < s.length()) {
                final char char2 = s.charAt(i + 1);
                if ((char2 & '\ufc00') == '\udc00') {
                    char1 = 65536 + (char1 - 55296 << 10) + char2 - 56320;
                    ++i;
                }
            }
            this.printEscaped(char1);
        }
    }
    
    protected ElementState getElementState() {
        return this._elementStates[this._elementStateCount];
    }
    
    protected ElementState enterElementState(final String namespaceURI, final String localName, final String rawName, final boolean preserveSpace) {
        if (this._elementStateCount + 1 == this._elementStates.length) {
            final ElementState[] elementStates = new ElementState[this._elementStates.length + 10];
            for (int i = 0; i < this._elementStates.length; ++i) {
                elementStates[i] = this._elementStates[i];
            }
            for (int j = this._elementStates.length; j < elementStates.length; ++j) {
                elementStates[j] = new ElementState();
            }
            this._elementStates = elementStates;
        }
        ++this._elementStateCount;
        final ElementState elementState = this._elementStates[this._elementStateCount];
        elementState.namespaceURI = namespaceURI;
        elementState.localName = localName;
        elementState.rawName = rawName;
        elementState.preserveSpace = preserveSpace;
        elementState.empty = true;
        elementState.afterElement = false;
        final ElementState elementState2 = elementState;
        final ElementState elementState3 = elementState;
        final boolean b = false;
        elementState3.inCData = b;
        elementState2.doCData = b;
        elementState.unescaped = false;
        elementState.prefixes = this._prefixes;
        this._prefixes = null;
        return elementState;
    }
    
    protected ElementState leaveElementState() {
        if (this._elementStateCount > 0) {
            this._prefixes = this._elementStates[this._elementStateCount].prefixes;
            --this._elementStateCount;
            return this._elementStates[this._elementStateCount];
        }
        throw new IllegalStateException("Internal error: element state is zero");
    }
    
    protected boolean isDocumentState() {
        return this._elementStateCount == 0;
    }
    
    protected String getPrefix(final String s) {
        if (this._prefixes != null) {
            final String s2 = this._prefixes.get(s);
            if (s2 != null) {
                return s2;
            }
        }
        if (this._elementStateCount == 0) {
            return null;
        }
        for (int i = this._elementStateCount; i > 0; --i) {
            if (this._elementStates[i].prefixes != null) {
                final String s3 = this._elementStates[i].prefixes.get(s);
                if (s3 != null) {
                    return s3;
                }
            }
        }
        return null;
    }
    
    public abstract void endElement(final String p0, final String p1, final String p2) throws SAXException;
    
    public abstract void startElement(final String p0, final String p1, final String p2, final Attributes p3) throws SAXException;
    
    public abstract void endElement(final String p0) throws SAXException;
    
    public abstract void startElement(final String p0, final AttributeList p1) throws SAXException;
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
