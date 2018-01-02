// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.serialize;

import org.w3c.dom.traversal.NodeFilter;
import org.xml.sax.AttributeList;
import org.xml.sax.Attributes;
import org.apache.xerces.dom.DOMLocatorImpl;
import org.apache.xerces.dom3.DOMError;
import java.lang.reflect.Method;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.DocumentType;
import org.xml.sax.Locator;
import org.apache.xerces.util.XMLChar;
import org.xml.sax.SAXException;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Element;
import org.apache.xerces.dom.DOMMessageFormatter;
import java.io.IOException;
import org.w3c.dom.Node;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Vector;
import org.w3c.dom.ls.DOMWriterFilter;
import org.apache.xerces.dom.DOMErrorImpl;
import org.apache.xerces.dom3.DOMErrorHandler;
import java.util.Hashtable;
import org.xml.sax.ext.DeclHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.DocumentHandler;
import org.xml.sax.ContentHandler;

public abstract class BaseMarkupSerializer implements ContentHandler, DocumentHandler, LexicalHandler, DTDHandler, DeclHandler, DOMSerializer, Serializer
{
    protected Hashtable fFeatures;
    protected DOMErrorHandler fDOMErrorHandler;
    protected final DOMErrorImpl fDOMError;
    protected DOMWriterFilter fDOMFilter;
    protected EncodingInfo _encodingInfo;
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
    protected final StringBuffer fStrBuffer;
    private Writer _writer;
    private OutputStream _output;
    private Node fCurrentNode;
    static /* synthetic */ Class class$java$lang$String;
    
    protected BaseMarkupSerializer(final OutputFormat format) {
        this.fDOMError = new DOMErrorImpl();
        this.fStrBuffer = new StringBuffer(40);
        this.fCurrentNode = null;
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
            final String msg = DOMMessageFormatter.formatMessage("http://apache.org/xml/serializer", "ArgumentIsNull", new Object[] { "output" });
            throw new NullPointerException(msg);
        }
        this._output = output;
        this._writer = null;
        this.reset();
    }
    
    public void setOutputCharStream(final Writer writer) {
        if (writer == null) {
            final String msg = DOMMessageFormatter.formatMessage("http://apache.org/xml/serializer", "ArgumentIsNull", new Object[] { "writer" });
            throw new NullPointerException(msg);
        }
        this._writer = writer;
        this._output = null;
        this.reset();
    }
    
    public void setOutputFormat(final OutputFormat format) {
        if (format == null) {
            final String msg = DOMMessageFormatter.formatMessage("http://apache.org/xml/serializer", "ArgumentIsNull", new Object[] { "format" });
            throw new NullPointerException(msg);
        }
        this._format = format;
        this.reset();
    }
    
    public boolean reset() {
        if (this._elementStateCount > 1) {
            final String msg = DOMMessageFormatter.formatMessage("http://apache.org/xml/serializer", "ResetInMiddle", null);
            throw new IllegalStateException(msg);
        }
        this._prepared = false;
        this.fCurrentNode = null;
        this.fStrBuffer.setLength(0);
        return true;
    }
    
    protected void prepare() throws IOException {
        if (this._prepared) {
            return;
        }
        if (this._writer == null && this._output == null) {
            final String msg = DOMMessageFormatter.formatMessage("http://apache.org/xml/serializer", "NoWriterSupplied", null);
            throw new IOException(msg);
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
        final ElementState state = this._elementStates[0];
        state.namespaceURI = null;
        state.localName = null;
        state.rawName = null;
        state.preserveSpace = this._format.getPreserveSpace();
        state.empty = true;
        state.afterElement = false;
        state.afterComment = false;
        final ElementState elementState = state;
        final ElementState elementState2 = state;
        final boolean b = false;
        elementState2.inCData = b;
        elementState.doCData = b;
        state.prefixes = null;
        this._docTypePublicId = this._format.getDoctypePublic();
        this._docTypeSystemId = this._format.getDoctypeSystem();
        this._started = false;
        this._prepared = true;
    }
    
    public void serialize(final Element elem) throws IOException {
        this.reset();
        this.prepare();
        this.serializeNode(elem);
        this._printer.flush();
        if (this._printer.getException() != null) {
            throw this._printer.getException();
        }
    }
    
    public void serialize(final DocumentFragment frag) throws IOException {
        this.reset();
        this.prepare();
        this.serializeNode(frag);
        this._printer.flush();
        if (this._printer.getException() != null) {
            throw this._printer.getException();
        }
    }
    
    public void serialize(final Document doc) throws IOException {
        this.reset();
        this.prepare();
        this.serializeNode(doc);
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
        catch (IOException except) {
            throw new SAXException(except.toString());
        }
    }
    
    public void characters(final char[] chars, final int start, final int length) throws SAXException {
        try {
            final ElementState state = this.content();
            if (state.inCData || state.doCData) {
                if (!state.inCData) {
                    this._printer.printText("<![CDATA[");
                    state.inCData = true;
                }
                final int saveIndent = this._printer.getNextIndent();
                this._printer.setNextIndent(0);
                for (int index = 0; index < length; ++index) {
                    final char ch = chars[index];
                    if (ch == ']' && index + 2 < length && chars[index + 1] == ']' && chars[index + 2] == '>') {
                        this._printer.printText("]]]]><![CDATA[>");
                        index += 2;
                    }
                    else if (!XMLChar.isValid(ch)) {
                        if (++index < length) {
                            this.surrogates(ch, chars[index]);
                        }
                        else {
                            this.fatalError("The character '" + ch + "' is an invalid XML character");
                        }
                    }
                    else if ((ch >= ' ' && this._encodingInfo.isPrintable(ch) && ch != '\u00f7') || ch == '\n' || ch == '\r' || ch == '\t') {
                        this._printer.printText(ch);
                    }
                    else {
                        this._printer.printText("]]>&#x");
                        this._printer.printText(Integer.toHexString(ch));
                        this._printer.printText(";<![CDATA[");
                    }
                }
                this._printer.setNextIndent(saveIndent);
            }
            else if (state.preserveSpace) {
                final int saveIndent = this._printer.getNextIndent();
                this._printer.setNextIndent(0);
                this.printText(chars, start, length, true, state.unescaped);
                this._printer.setNextIndent(saveIndent);
            }
            else {
                this.printText(chars, start, length, false, state.unescaped);
            }
        }
        catch (IOException except) {
            throw new SAXException(except);
        }
    }
    
    public void ignorableWhitespace(final char[] chars, final int start, int length) throws SAXException {
        try {
            this.content();
            if (this._indenting) {
                this._printer.setThisIndent(0);
                int i = start;
                while (length-- > 0) {
                    this._printer.printText(chars[i]);
                    ++i;
                }
            }
        }
        catch (IOException except) {
            throw new SAXException(except);
        }
    }
    
    public final void processingInstruction(final String target, final String code) throws SAXException {
        try {
            this.processingInstructionIO(target, code);
        }
        catch (IOException except) {
            throw new SAXException(except);
        }
    }
    
    public void processingInstructionIO(final String target, final String code) throws IOException {
        final ElementState state = this.content();
        int index = target.indexOf("?>");
        if (index >= 0) {
            this.fStrBuffer.append("<?").append(target.substring(0, index));
        }
        else {
            this.fStrBuffer.append("<?").append(target);
        }
        if (code != null) {
            this.fStrBuffer.append(' ');
            index = code.indexOf("?>");
            if (index >= 0) {
                this.fStrBuffer.append(code.substring(0, index));
            }
            else {
                this.fStrBuffer.append(code);
            }
        }
        this.fStrBuffer.append("?>");
        if (this.isDocumentState()) {
            if (this._preRoot == null) {
                this._preRoot = new Vector();
            }
            this._preRoot.addElement(this.fStrBuffer.toString());
        }
        else {
            this._printer.indent();
            this.printText(this.fStrBuffer.toString(), true, true);
            this._printer.unindent();
            if (this._indenting) {
                state.afterElement = true;
            }
        }
        this.fStrBuffer.setLength(0);
    }
    
    public void comment(final char[] chars, final int start, final int length) throws SAXException {
        try {
            this.comment(new String(chars, start, length));
        }
        catch (IOException except) {
            throw new SAXException(except);
        }
    }
    
    public void comment(final String text) throws IOException {
        if (this._format.getOmitComments()) {
            return;
        }
        final ElementState state = this.content();
        final int index = text.indexOf("-->");
        if (index >= 0) {
            this.fStrBuffer.append("<!--").append(text.substring(0, index)).append("-->");
        }
        else {
            this.fStrBuffer.append("<!--").append(text).append("-->");
        }
        if (this.isDocumentState()) {
            if (this._preRoot == null) {
                this._preRoot = new Vector();
            }
            this._preRoot.addElement(this.fStrBuffer.toString());
        }
        else {
            if (this._indenting && !state.preserveSpace) {
                this._printer.breakLine();
            }
            this._printer.indent();
            this.printText(this.fStrBuffer.toString(), true, true);
            this._printer.unindent();
            if (this._indenting) {
                state.afterElement = true;
            }
        }
        this.fStrBuffer.setLength(0);
        state.afterComment = true;
        state.afterElement = false;
    }
    
    public void startCDATA() {
        final ElementState state = this.getElementState();
        state.doCData = true;
    }
    
    public void endCDATA() {
        final ElementState state = this.getElementState();
        state.doCData = false;
    }
    
    public void startNonEscaping() {
        final ElementState state = this.getElementState();
        state.unescaped = true;
    }
    
    public void endNonEscaping() {
        final ElementState state = this.getElementState();
        state.unescaped = false;
    }
    
    public void startPreserving() {
        final ElementState state = this.getElementState();
        state.preserveSpace = true;
    }
    
    public void endPreserving() {
        final ElementState state = this.getElementState();
        state.preserveSpace = false;
    }
    
    public void endDocument() throws SAXException {
        try {
            this.serializePreRoot();
            this._printer.flush();
        }
        catch (IOException except) {
            throw new SAXException(except);
        }
    }
    
    public void startEntity(final String name) {
    }
    
    public void endEntity(final String name) {
    }
    
    public void setDocumentLocator(final Locator locator) {
    }
    
    public void skippedEntity(final String name) throws SAXException {
        try {
            this.endCDATA();
            this.content();
            this._printer.printText('&');
            this._printer.printText(name);
            this._printer.printText(';');
        }
        catch (IOException except) {
            throw new SAXException(except);
        }
    }
    
    public void startPrefixMapping(final String prefix, final String uri) throws SAXException {
        if (this._prefixes == null) {
            this._prefixes = new Hashtable();
        }
        this._prefixes.put(uri, (prefix == null) ? "" : prefix);
    }
    
    public void endPrefixMapping(final String prefix) throws SAXException {
    }
    
    public final void startDTD(final String name, final String publicId, final String systemId) throws SAXException {
        try {
            this._printer.enterDTD();
            this._docTypePublicId = publicId;
            this._docTypeSystemId = systemId;
        }
        catch (IOException except) {
            throw new SAXException(except);
        }
    }
    
    public void endDTD() {
    }
    
    public void elementDecl(final String name, final String model) throws SAXException {
        try {
            this._printer.enterDTD();
            this._printer.printText("<!ELEMENT ");
            this._printer.printText(name);
            this._printer.printText(' ');
            this._printer.printText(model);
            this._printer.printText('>');
            if (this._indenting) {
                this._printer.breakLine();
            }
        }
        catch (IOException except) {
            throw new SAXException(except);
        }
    }
    
    public void attributeDecl(final String eName, final String aName, final String type, final String valueDefault, final String value) throws SAXException {
        try {
            this._printer.enterDTD();
            this._printer.printText("<!ATTLIST ");
            this._printer.printText(eName);
            this._printer.printText(' ');
            this._printer.printText(aName);
            this._printer.printText(' ');
            this._printer.printText(type);
            if (valueDefault != null) {
                this._printer.printText(' ');
                this._printer.printText(valueDefault);
            }
            if (value != null) {
                this._printer.printText(" \"");
                this.printEscaped(value);
                this._printer.printText('\"');
            }
            this._printer.printText('>');
            if (this._indenting) {
                this._printer.breakLine();
            }
        }
        catch (IOException except) {
            throw new SAXException(except);
        }
    }
    
    public void internalEntityDecl(final String name, final String value) throws SAXException {
        try {
            this._printer.enterDTD();
            this._printer.printText("<!ENTITY ");
            this._printer.printText(name);
            this._printer.printText(" \"");
            this.printEscaped(value);
            this._printer.printText("\">");
            if (this._indenting) {
                this._printer.breakLine();
            }
        }
        catch (IOException except) {
            throw new SAXException(except);
        }
    }
    
    public void externalEntityDecl(final String name, final String publicId, final String systemId) throws SAXException {
        try {
            this._printer.enterDTD();
            this.unparsedEntityDecl(name, publicId, systemId, null);
        }
        catch (IOException except) {
            throw new SAXException(except);
        }
    }
    
    public void unparsedEntityDecl(final String name, final String publicId, final String systemId, final String notationName) throws SAXException {
        try {
            this._printer.enterDTD();
            if (publicId == null) {
                this._printer.printText("<!ENTITY ");
                this._printer.printText(name);
                this._printer.printText(" SYSTEM ");
                this.printDoctypeURL(systemId);
            }
            else {
                this._printer.printText("<!ENTITY ");
                this._printer.printText(name);
                this._printer.printText(" PUBLIC ");
                this.printDoctypeURL(publicId);
                this._printer.printText(' ');
                this.printDoctypeURL(systemId);
            }
            if (notationName != null) {
                this._printer.printText(" NDATA ");
                this._printer.printText(notationName);
            }
            this._printer.printText('>');
            if (this._indenting) {
                this._printer.breakLine();
            }
        }
        catch (IOException except) {
            throw new SAXException(except);
        }
    }
    
    public void notationDecl(final String name, final String publicId, final String systemId) throws SAXException {
        try {
            this._printer.enterDTD();
            if (publicId != null) {
                this._printer.printText("<!NOTATION ");
                this._printer.printText(name);
                this._printer.printText(" PUBLIC ");
                this.printDoctypeURL(publicId);
                if (systemId != null) {
                    this._printer.printText(' ');
                    this.printDoctypeURL(systemId);
                }
            }
            else {
                this._printer.printText("<!NOTATION ");
                this._printer.printText(name);
                this._printer.printText(" SYSTEM ");
                this.printDoctypeURL(systemId);
            }
            this._printer.printText('>');
            if (this._indenting) {
                this._printer.breakLine();
            }
        }
        catch (IOException except) {
            throw new SAXException(except);
        }
    }
    
    protected void serializeNode(final Node node) throws IOException {
        this.fCurrentNode = node;
        switch (node.getNodeType()) {
            case 3: {
                final String text = node.getNodeValue();
                if (text == null) {
                    break;
                }
                if (this.fDOMFilter != null && (this.fDOMFilter.getWhatToShow() & 0x4) != 0x0) {
                    final short code = ((NodeFilter)this.fDOMFilter).acceptNode(node);
                    switch (code) {
                        case 2:
                        case 3: {
                            break;
                        }
                        default: {
                            this.characters(text);
                            break;
                        }
                    }
                    break;
                }
                if (!this._indenting || this.getElementState().preserveSpace || text.replace('\n', ' ').trim().length() != 0) {
                    this.characters(text);
                    break;
                }
                break;
            }
            case 4: {
                final String text = node.getNodeValue();
                if (text != null) {
                    if (this.fDOMFilter != null && (this.fDOMFilter.getWhatToShow() & 0x8) != 0x0) {
                        final short code = ((NodeFilter)this.fDOMFilter).acceptNode(node);
                        switch (code) {
                            case 2:
                            case 3: {
                                return;
                            }
                        }
                    }
                    this.startCDATA();
                    this.characters(text);
                    this.endCDATA();
                    break;
                }
                break;
            }
            case 8: {
                if (this._format.getOmitComments()) {
                    break;
                }
                final String text = node.getNodeValue();
                if (text != null) {
                    if (this.fDOMFilter != null && (this.fDOMFilter.getWhatToShow() & 0x80) != 0x0) {
                        final short code = ((NodeFilter)this.fDOMFilter).acceptNode(node);
                        switch (code) {
                            case 2:
                            case 3: {
                                return;
                            }
                        }
                    }
                    this.comment(text);
                    break;
                }
                break;
            }
            case 5: {
                this.endCDATA();
                this.content();
                if (this.fDOMFilter != null && (this.fDOMFilter.getWhatToShow() & 0x10) != 0x0) {
                    final short code = ((NodeFilter)this.fDOMFilter).acceptNode(node);
                    switch (code) {
                        case 2: {
                            return;
                        }
                        case 3: {
                            for (Node child = node.getFirstChild(); child != null; child = child.getNextSibling()) {
                                this.serializeNode(child);
                            }
                            return;
                        }
                        default: {
                            final Node child = node.getFirstChild();
                            if (child != null) {
                                this._printer.printText("&");
                                this._printer.printText(node.getNodeName());
                                this._printer.printText(";");
                            }
                            return;
                        }
                    }
                }
                else {
                    Node child = node.getFirstChild();
                    if (child == null || (this.fFeatures != null && this.getFeature("entities"))) {
                        this._printer.printText("&");
                        this._printer.printText(node.getNodeName());
                        this._printer.printText(";");
                        break;
                    }
                    while (child != null) {
                        this.serializeNode(child);
                        child = child.getNextSibling();
                    }
                    break;
                }
                break;
            }
            case 7: {
                if (this.fDOMFilter != null && (this.fDOMFilter.getWhatToShow() & 0x40) != 0x0) {
                    final short code2 = ((NodeFilter)this.fDOMFilter).acceptNode(node);
                    switch (code2) {
                        case 2:
                        case 3: {
                            return;
                        }
                    }
                }
                this.processingInstructionIO(node.getNodeName(), node.getNodeValue());
                break;
            }
            case 1: {
                if (this.fDOMFilter != null && (this.fDOMFilter.getWhatToShow() & 0x40) != 0x0) {
                    final short code2 = ((NodeFilter)this.fDOMFilter).acceptNode(node);
                    switch (code2) {
                        case 2: {
                            return;
                        }
                        case 3: {
                            for (Node child2 = node.getFirstChild(); child2 != null; child2 = child2.getNextSibling()) {
                                this.serializeNode(child2);
                            }
                            return;
                        }
                    }
                }
                this.serializeElement((Element)node);
                break;
            }
            case 9: {
                final DocumentType docType = ((Document)node).getDoctype();
                if (docType != null) {
                    final DOMImplementation domImpl = ((Document)node).getImplementation();
                    try {
                        this._printer.enterDTD();
                        this._docTypePublicId = docType.getPublicId();
                        this._docTypeSystemId = docType.getSystemId();
                        final String internal = docType.getInternalSubset();
                        if (internal != null && internal.length() > 0) {
                            this._printer.printText(internal);
                        }
                        this.endDTD();
                    }
                    catch (NoSuchMethodError nsme) {
                        final Class docTypeClass = docType.getClass();
                        String docTypePublicId = null;
                        String docTypeSystemId = null;
                        try {
                            final Method getPublicId = docTypeClass.getMethod("getPublicId", (Class[])null);
                            if (getPublicId.getReturnType().equals((BaseMarkupSerializer.class$java$lang$String == null) ? (BaseMarkupSerializer.class$java$lang$String = class$("java.lang.String")) : BaseMarkupSerializer.class$java$lang$String)) {
                                docTypePublicId = (String)getPublicId.invoke(docType, (Object[])null);
                            }
                        }
                        catch (Exception ex) {}
                        try {
                            final Method getSystemId = docTypeClass.getMethod("getSystemId", (Class[])null);
                            if (getSystemId.getReturnType().equals((BaseMarkupSerializer.class$java$lang$String == null) ? (BaseMarkupSerializer.class$java$lang$String = class$("java.lang.String")) : BaseMarkupSerializer.class$java$lang$String)) {
                                docTypeSystemId = (String)getSystemId.invoke(docType, (Object[])null);
                            }
                        }
                        catch (Exception ex2) {}
                        this._printer.enterDTD();
                        this._docTypePublicId = docTypePublicId;
                        this._docTypeSystemId = docTypeSystemId;
                        this.endDTD();
                    }
                }
            }
            case 11: {
                for (Node child = node.getFirstChild(); child != null; child = child.getNextSibling()) {
                    this.serializeNode(child);
                }
                break;
            }
        }
    }
    
    protected ElementState content() throws IOException {
        final ElementState state = this.getElementState();
        if (!this.isDocumentState()) {
            if (state.inCData && !state.doCData) {
                this._printer.printText("]]>");
                state.inCData = false;
            }
            if (state.empty) {
                this._printer.printText('>');
                state.empty = false;
            }
            state.afterElement = false;
            state.afterComment = false;
        }
        return state;
    }
    
    protected void characters(final String text) throws IOException {
        final ElementState state = this.content();
        if (state.inCData || state.doCData) {
            if (!state.inCData) {
                this._printer.printText("<![CDATA[");
                state.inCData = true;
            }
            final int saveIndent = this._printer.getNextIndent();
            this._printer.setNextIndent(0);
            this.printCDATAText(text);
            this._printer.setNextIndent(saveIndent);
        }
        else if (state.preserveSpace) {
            final int saveIndent2 = this._printer.getNextIndent();
            this._printer.setNextIndent(0);
            this.printText(text, true, state.unescaped);
            this._printer.setNextIndent(saveIndent2);
        }
        else {
            this.printText(text, false, state.unescaped);
        }
    }
    
    protected abstract String getEntityRef(final int p0);
    
    protected abstract void serializeElement(final Element p0) throws IOException;
    
    protected void serializePreRoot() throws IOException {
        if (this._preRoot != null) {
            for (int i = 0; i < this._preRoot.size(); ++i) {
                this.printText(this._preRoot.elementAt(i), true, true);
                if (this._indenting) {
                    this._printer.breakLine();
                }
            }
            this._preRoot.removeAllElements();
        }
    }
    
    protected final void printCDATAText(final String text) throws IOException {
        for (int length = text.length(), index = 0; index < length; ++index) {
            final char ch = text.charAt(index);
            if (ch == ']' && index + 2 < length && text.charAt(index + 1) == ']' && text.charAt(index + 2) == '>') {
                if (this.fFeatures != null && this.fDOMErrorHandler != null) {
                    if (!this.getFeature("split-cdata-sections")) {
                        final String msg = DOMMessageFormatter.formatMessage("http://apache.org/xml/serializer", "EndingCDATA", null);
                        this.modifyDOMError(msg, (short)2, this.fCurrentNode);
                        final boolean continueProcess = this.fDOMErrorHandler.handleError(this.fDOMError);
                        if (!continueProcess) {
                            throw new IOException();
                        }
                    }
                    else {
                        final String msg = DOMMessageFormatter.formatMessage("http://apache.org/xml/serializer", "SplittingCDATA", null);
                        this.modifyDOMError(msg, (short)0, this.fCurrentNode);
                        this.fDOMErrorHandler.handleError(this.fDOMError);
                    }
                }
                this._printer.printText("]]]]><![CDATA[>");
                index += 2;
            }
            else if (!XMLChar.isValid(ch)) {
                if (++index < length) {
                    this.surrogates(ch, text.charAt(index));
                }
                else {
                    this.fatalError("The character '" + ch + "' is an invalid XML character");
                }
            }
            else if ((ch >= ' ' && this._encodingInfo.isPrintable(ch) && ch != '\u00f7') || ch == '\n' || ch == '\r' || ch == '\t') {
                this._printer.printText(ch);
            }
            else {
                this._printer.printText("]]>&#x");
                this._printer.printText(Integer.toHexString(ch));
                this._printer.printText(";<![CDATA[");
            }
        }
    }
    
    protected final void surrogates(final int high, final int low) throws IOException {
        if (XMLChar.isHighSurrogate(high)) {
            if (!XMLChar.isLowSurrogate(low)) {
                this.fatalError("The character '" + (char)low + "' is an invalid XML character");
            }
            else {
                final int supplemental = XMLChar.supplemental((char)high, (char)low);
                if (!XMLChar.isValid(supplemental)) {
                    this.fatalError("The character '" + (char)supplemental + "' is an invalid XML character");
                }
                else if (this.content().inCData) {
                    this._printer.printText("]]>&#x");
                    this._printer.printText(Integer.toHexString(supplemental));
                    this._printer.printText(";<![CDATA[");
                }
                else {
                    this._printer.printText("&#x");
                    this._printer.printText(Integer.toHexString(supplemental));
                    this._printer.printText(";");
                }
            }
        }
        else {
            this.fatalError("The character '" + (char)high + "' is an invalid XML character");
        }
    }
    
    protected void printText(final char[] chars, int start, int length, final boolean preserveSpace, final boolean unescaped) throws IOException {
        if (preserveSpace) {
            while (length-- > 0) {
                final char ch = chars[start];
                ++start;
                if (ch == '\n' || ch == '\r' || unescaped) {
                    this._printer.printText(ch);
                }
                else {
                    this.printEscaped(ch);
                }
            }
        }
        else {
            while (length-- > 0) {
                final char ch = chars[start];
                ++start;
                if (ch == ' ' || ch == '\f' || ch == '\t' || ch == '\n' || ch == '\r') {
                    this._printer.printSpace();
                }
                else if (unescaped) {
                    this._printer.printText(ch);
                }
                else {
                    this.printEscaped(ch);
                }
            }
        }
    }
    
    protected void printText(final String text, final boolean preserveSpace, final boolean unescaped) throws IOException {
        if (preserveSpace) {
            for (int index = 0; index < text.length(); ++index) {
                final char ch = text.charAt(index);
                if (ch == '\n' || ch == '\r' || unescaped) {
                    this._printer.printText(ch);
                }
                else {
                    this.printEscaped(ch);
                }
            }
        }
        else {
            for (int index = 0; index < text.length(); ++index) {
                final char ch = text.charAt(index);
                if (ch == ' ' || ch == '\f' || ch == '\t' || ch == '\n' || ch == '\r') {
                    this._printer.printSpace();
                }
                else if (unescaped) {
                    this._printer.printText(ch);
                }
                else {
                    this.printEscaped(ch);
                }
            }
        }
    }
    
    protected void printDoctypeURL(final String url) throws IOException {
        this._printer.printText('\"');
        for (int i = 0; i < url.length(); ++i) {
            if (url.charAt(i) == '\"' || url.charAt(i) < ' ' || url.charAt(i) > '\u007f') {
                this._printer.printText('%');
                this._printer.printText(Integer.toHexString(url.charAt(i)));
            }
            else {
                this._printer.printText(url.charAt(i));
            }
        }
        this._printer.printText('\"');
    }
    
    protected void printEscaped(final int ch) throws IOException {
        final String charRef = this.getEntityRef(ch);
        if (charRef != null) {
            this._printer.printText('&');
            this._printer.printText(charRef);
            this._printer.printText(';');
        }
        else if ((ch >= 32 && this._encodingInfo.isPrintable(ch) && ch != 247) || ch == 10 || ch == 13 || ch == 9) {
            if (ch < 65536) {
                this._printer.printText((char)ch);
            }
            else {
                this._printer.printText((char)((ch - 65536 >> 10) + 55296));
                this._printer.printText((char)((ch - 65536 & 0x3FF) + 56320));
            }
        }
        else {
            this._printer.printText("&#x");
            this._printer.printText(Integer.toHexString(ch));
            this._printer.printText(';');
        }
    }
    
    protected void printEscaped(final String source) throws IOException {
        for (int i = 0; i < source.length(); ++i) {
            int ch = source.charAt(i);
            if ((ch & 0xFC00) == 0xD800 && i + 1 < source.length()) {
                final int lowch = source.charAt(i + 1);
                if ((lowch & 0xFC00) == 0xDC00) {
                    ch = 65536 + (ch - 55296 << 10) + lowch - 56320;
                    ++i;
                }
            }
            this.printEscaped(ch);
        }
    }
    
    protected ElementState getElementState() {
        return this._elementStates[this._elementStateCount];
    }
    
    protected ElementState enterElementState(final String namespaceURI, final String localName, final String rawName, final boolean preserveSpace) {
        if (this._elementStateCount + 1 == this._elementStates.length) {
            final ElementState[] newStates = new ElementState[this._elementStates.length + 10];
            for (int i = 0; i < this._elementStates.length; ++i) {
                newStates[i] = this._elementStates[i];
            }
            for (int j = this._elementStates.length; j < newStates.length; ++j) {
                newStates[j] = new ElementState();
            }
            this._elementStates = newStates;
        }
        ++this._elementStateCount;
        final ElementState state = this._elementStates[this._elementStateCount];
        state.namespaceURI = namespaceURI;
        state.localName = localName;
        state.rawName = rawName;
        state.preserveSpace = preserveSpace;
        state.empty = true;
        state.afterElement = false;
        state.afterComment = false;
        final ElementState elementState = state;
        final ElementState elementState2 = state;
        final boolean b = false;
        elementState2.inCData = b;
        elementState.doCData = b;
        state.unescaped = false;
        state.prefixes = this._prefixes;
        this._prefixes = null;
        return state;
    }
    
    protected ElementState leaveElementState() {
        if (this._elementStateCount > 0) {
            this._prefixes = null;
            --this._elementStateCount;
            return this._elementStates[this._elementStateCount];
        }
        final String msg = DOMMessageFormatter.formatMessage("http://apache.org/xml/serializer", "Internal", null);
        throw new IllegalStateException(msg);
    }
    
    protected boolean isDocumentState() {
        return this._elementStateCount == 0;
    }
    
    protected String getPrefix(final String namespaceURI) {
        if (this._prefixes != null) {
            final String prefix = this._prefixes.get(namespaceURI);
            if (prefix != null) {
                return prefix;
            }
        }
        if (this._elementStateCount == 0) {
            return null;
        }
        for (int i = this._elementStateCount; i > 0; --i) {
            if (this._elementStates[i].prefixes != null) {
                final String prefix = this._elementStates[i].prefixes.get(namespaceURI);
                if (prefix != null) {
                    return prefix;
                }
            }
        }
        return null;
    }
    
    protected DOMError modifyDOMError(final String message, final short severity, final Node node) {
        this.fDOMError.reset();
        this.fDOMError.fMessage = message;
        this.fDOMError.fSeverity = severity;
        this.fDOMError.fLocator = new DOMLocatorImpl(-1, -1, -1, node, null);
        return this.fDOMError;
    }
    
    private boolean getFeature(final String feature) {
        return this.fFeatures.get(feature);
    }
    
    protected void fatalError(final String message) throws IOException {
        if (this.fDOMErrorHandler != null) {
            this.modifyDOMError(message, (short)2, this.fCurrentNode);
            this.fDOMErrorHandler.handleError(this.fDOMError);
            return;
        }
        throw new IOException(message);
    }
    
    public abstract void endElement(final String p0, final String p1, final String p2) throws SAXException;
    
    public abstract void startElement(final String p0, final String p1, final String p2, final Attributes p3) throws SAXException;
    
    public abstract void endElement(final String p0) throws SAXException;
    
    public abstract void startElement(final String p0, final AttributeList p1) throws SAXException;
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError(x.getMessage());
        }
    }
}
