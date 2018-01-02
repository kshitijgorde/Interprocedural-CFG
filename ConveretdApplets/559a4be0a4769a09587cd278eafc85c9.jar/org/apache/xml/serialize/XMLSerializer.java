// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.serialize;

import org.apache.xerces.util.XMLChar;
import org.xml.sax.helpers.AttributesImpl;
import org.w3c.dom.NamedNodeMap;
import org.apache.xerces.dom3.DOMError;
import org.w3c.dom.Node;
import org.apache.xerces.util.XMLSymbols;
import org.apache.xerces.xni.NamespaceContext;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.xml.sax.AttributeList;
import java.util.Enumeration;
import java.io.IOException;
import org.xml.sax.SAXException;
import org.apache.xerces.dom.DOMMessageFormatter;
import org.xml.sax.Attributes;
import java.io.OutputStream;
import java.io.Writer;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.util.NamespaceSupport;

public class XMLSerializer extends BaseMarkupSerializer
{
    protected static final boolean DEBUG = false;
    protected NamespaceSupport fNSBinder;
    protected NamespaceSupport fLocalNSBinder;
    protected SymbolTable fSymbolTable;
    protected boolean fDOML1;
    protected int fNamespaceCounter;
    protected static final String PREFIX = "NS";
    protected boolean fNamespaces;
    private boolean fPreserveSpace;
    
    public XMLSerializer() {
        super(new OutputFormat("xml", null, false));
        this.fDOML1 = false;
        this.fNamespaceCounter = 1;
        this.fNamespaces = false;
    }
    
    public XMLSerializer(final OutputFormat format) {
        super((format != null) ? format : new OutputFormat("xml", null, false));
        this.fDOML1 = false;
        this.fNamespaceCounter = 1;
        this.fNamespaces = false;
        super._format.setMethod("xml");
    }
    
    public XMLSerializer(final Writer writer, final OutputFormat format) {
        super((format != null) ? format : new OutputFormat("xml", null, false));
        this.fDOML1 = false;
        this.fNamespaceCounter = 1;
        this.fNamespaces = false;
        super._format.setMethod("xml");
        this.setOutputCharStream(writer);
    }
    
    public XMLSerializer(final OutputStream output, final OutputFormat format) {
        super((format != null) ? format : new OutputFormat("xml", null, false));
        this.fDOML1 = false;
        this.fNamespaceCounter = 1;
        this.fNamespaces = false;
        super._format.setMethod("xml");
        this.setOutputByteStream(output);
    }
    
    public void setOutputFormat(final OutputFormat format) {
        super.setOutputFormat((format != null) ? format : new OutputFormat("xml", null, false));
    }
    
    public void setNamespaces(final boolean namespaces) {
        this.fNamespaces = namespaces;
        this.fNSBinder = new NamespaceSupport();
        this.fLocalNSBinder = new NamespaceSupport();
        this.fSymbolTable = new SymbolTable();
    }
    
    public void startElement(final String namespaceURI, final String localName, String rawName, Attributes attrs) throws SAXException {
        boolean addNSAttr = false;
        try {
            if (super._printer == null) {
                final String msg = DOMMessageFormatter.formatMessage("http://apache.org/xml/serializer", "NoWriterSupplied", null);
                throw new IllegalStateException(msg);
            }
            ElementState state = this.getElementState();
            if (this.isDocumentState()) {
                if (!super._started) {
                    this.startDocument((localName == null || localName.length() == 0) ? rawName : localName);
                }
            }
            else {
                if (state.empty) {
                    super._printer.printText('>');
                }
                if (state.inCData) {
                    super._printer.printText("]]>");
                    state.inCData = false;
                }
                if (super._indenting && !state.preserveSpace && (state.empty || state.afterElement || state.afterComment)) {
                    super._printer.breakLine();
                }
            }
            boolean preserveSpace = state.preserveSpace;
            attrs = this.extractNamespaces(attrs);
            if (rawName == null || rawName.length() == 0) {
                if (localName == null) {
                    final String msg = DOMMessageFormatter.formatMessage("http://apache.org/xml/serializer", "NoName", null);
                    throw new SAXException(msg);
                }
                if (namespaceURI != null && !namespaceURI.equals("")) {
                    final String prefix = this.getPrefix(namespaceURI);
                    if (prefix != null && prefix.length() > 0) {
                        rawName = prefix + ":" + localName;
                    }
                    else {
                        rawName = localName;
                    }
                }
                else {
                    rawName = localName;
                }
                addNSAttr = true;
            }
            super._printer.printText('<');
            super._printer.printText(rawName);
            super._printer.indent();
            if (attrs != null) {
                for (int i = 0; i < attrs.getLength(); ++i) {
                    super._printer.printSpace();
                    String name = attrs.getQName(i);
                    if (name != null && name.length() == 0) {
                        name = attrs.getLocalName(i);
                        final String attrURI = attrs.getURI(i);
                        if (attrURI != null && attrURI.length() != 0 && (namespaceURI == null || namespaceURI.length() == 0 || !attrURI.equals(namespaceURI))) {
                            final String prefix = this.getPrefix(attrURI);
                            if (prefix != null && prefix.length() > 0) {
                                name = prefix + ":" + name;
                            }
                        }
                    }
                    String value = attrs.getValue(i);
                    if (value == null) {
                        value = "";
                    }
                    super._printer.printText(name);
                    super._printer.printText("=\"");
                    this.printEscaped(value);
                    super._printer.printText('\"');
                    if (name.equals("xml:space")) {
                        preserveSpace = (value.equals("preserve") || super._format.getPreserveSpace());
                    }
                }
            }
            if (super._prefixes != null) {
                final Enumeration enum1 = super._prefixes.keys();
                while (enum1.hasMoreElements()) {
                    super._printer.printSpace();
                    final String value = enum1.nextElement();
                    final String name = super._prefixes.get(value);
                    if (name.length() == 0) {
                        super._printer.printText("xmlns=\"");
                        this.printEscaped(value);
                        super._printer.printText('\"');
                    }
                    else {
                        super._printer.printText("xmlns:");
                        super._printer.printText(name);
                        super._printer.printText("=\"");
                        this.printEscaped(value);
                        super._printer.printText('\"');
                    }
                }
            }
            state = this.enterElementState(namespaceURI, localName, rawName, preserveSpace);
            String name = (localName == null || localName.length() == 0) ? rawName : (namespaceURI + "^" + localName);
            state.doCData = super._format.isCDataElement(name);
            state.unescaped = super._format.isNonEscapingElement(name);
        }
        catch (IOException except) {
            throw new SAXException(except);
        }
    }
    
    public void endElement(final String namespaceURI, final String localName, final String rawName) throws SAXException {
        try {
            this.endElementIO(namespaceURI, localName, rawName);
        }
        catch (IOException except) {
            throw new SAXException(except);
        }
    }
    
    public void endElementIO(final String namespaceURI, final String localName, final String rawName) throws IOException {
        super._printer.unindent();
        ElementState state = this.getElementState();
        if (state.empty) {
            super._printer.printText("/>");
        }
        else {
            if (state.inCData) {
                super._printer.printText("]]>");
            }
            if (super._indenting && !state.preserveSpace && (state.afterElement || state.afterComment)) {
                super._printer.breakLine();
            }
            super._printer.printText("</");
            super._printer.printText(state.rawName);
            super._printer.printText('>');
        }
        state = this.leaveElementState();
        state.afterElement = true;
        state.afterComment = false;
        state.empty = false;
        if (this.isDocumentState()) {
            super._printer.flush();
        }
    }
    
    public void startElement(final String tagName, final AttributeList attrs) throws SAXException {
        try {
            if (super._printer == null) {
                final String msg = DOMMessageFormatter.formatMessage("http://apache.org/xml/serializer", "NoWriterSupplied", null);
                throw new IllegalStateException(msg);
            }
            ElementState state = this.getElementState();
            if (this.isDocumentState()) {
                if (!super._started) {
                    this.startDocument(tagName);
                }
            }
            else {
                if (state.empty) {
                    super._printer.printText('>');
                }
                if (state.inCData) {
                    super._printer.printText("]]>");
                    state.inCData = false;
                }
                if (super._indenting && !state.preserveSpace && (state.empty || state.afterElement || state.afterComment)) {
                    super._printer.breakLine();
                }
            }
            boolean preserveSpace = state.preserveSpace;
            super._printer.printText('<');
            super._printer.printText(tagName);
            super._printer.indent();
            if (attrs != null) {
                for (int i = 0; i < attrs.getLength(); ++i) {
                    super._printer.printSpace();
                    final String name = attrs.getName(i);
                    final String value = attrs.getValue(i);
                    if (value != null) {
                        super._printer.printText(name);
                        super._printer.printText("=\"");
                        this.printEscaped(value);
                        super._printer.printText('\"');
                    }
                    if (name.equals("xml:space")) {
                        preserveSpace = (value.equals("preserve") || super._format.getPreserveSpace());
                    }
                }
            }
            state = this.enterElementState(null, null, tagName, preserveSpace);
            state.doCData = super._format.isCDataElement(tagName);
            state.unescaped = super._format.isNonEscapingElement(tagName);
        }
        catch (IOException except) {
            throw new SAXException(except);
        }
    }
    
    public void endElement(final String tagName) throws SAXException {
        this.endElement(null, null, tagName);
    }
    
    protected void startDocument(final String rootTagName) throws IOException {
        final String dtd = super._printer.leaveDTD();
        if (!super._started) {
            if (!super._format.getOmitXMLDeclaration()) {
                final StringBuffer buffer = new StringBuffer("<?xml version=\"");
                if (super._format.getVersion() != null) {
                    buffer.append(super._format.getVersion());
                }
                else {
                    buffer.append("1.0");
                }
                buffer.append('\"');
                final String format_encoding = super._format.getEncoding();
                if (format_encoding != null) {
                    buffer.append(" encoding=\"");
                    buffer.append(format_encoding);
                    buffer.append('\"');
                }
                if (super._format.getStandalone() && super._docTypeSystemId == null && super._docTypePublicId == null) {
                    buffer.append(" standalone=\"yes\"");
                }
                buffer.append("?>");
                super._printer.printText(buffer);
                super._printer.breakLine();
            }
            if (!super._format.getOmitDocumentType()) {
                if (super._docTypeSystemId != null) {
                    super._printer.printText("<!DOCTYPE ");
                    super._printer.printText(rootTagName);
                    if (super._docTypePublicId != null) {
                        super._printer.printText(" PUBLIC ");
                        this.printDoctypeURL(super._docTypePublicId);
                        if (super._indenting) {
                            super._printer.breakLine();
                            for (int i = 0; i < 18 + rootTagName.length(); ++i) {
                                super._printer.printText(" ");
                            }
                        }
                        else {
                            super._printer.printText(" ");
                        }
                        this.printDoctypeURL(super._docTypeSystemId);
                    }
                    else {
                        super._printer.printText(" SYSTEM ");
                        this.printDoctypeURL(super._docTypeSystemId);
                    }
                    if (dtd != null && dtd.length() > 0) {
                        super._printer.printText(" [");
                        this.printText(dtd, true, true);
                        super._printer.printText(']');
                    }
                    super._printer.printText(">");
                    super._printer.breakLine();
                }
                else if (dtd != null && dtd.length() > 0) {
                    super._printer.printText("<!DOCTYPE ");
                    super._printer.printText(rootTagName);
                    super._printer.printText(" [");
                    this.printText(dtd, true, true);
                    super._printer.printText("]>");
                    super._printer.breakLine();
                }
            }
        }
        super._started = true;
        this.serializePreRoot();
    }
    
    protected void serializeElement(final Element elem) throws IOException {
        if (this.fNamespaces) {
            this.fLocalNSBinder.reset();
            this.fLocalNSBinder.pushContext();
            this.fNSBinder.pushContext();
        }
        final String tagName = elem.getTagName();
        ElementState state = this.getElementState();
        if (this.isDocumentState()) {
            this.fDOML1 = (elem.getLocalName() == null);
            if (!super._started) {
                this.startDocument(tagName);
            }
        }
        else {
            if (state.empty) {
                super._printer.printText('>');
            }
            if (state.inCData) {
                super._printer.printText("]]>");
                state.inCData = false;
            }
            if (super._indenting && !state.preserveSpace && (state.empty || state.afterElement || state.afterComment)) {
                super._printer.breakLine();
            }
        }
        this.fPreserveSpace = state.preserveSpace;
        int length = 0;
        NamedNodeMap attrMap = null;
        if (elem.hasAttributes()) {
            attrMap = elem.getAttributes();
            length = attrMap.getLength();
        }
        if (!this.fNamespaces) {
            super._printer.printText('<');
            super._printer.printText(tagName);
            super._printer.indent();
            for (int i = 0; i < length; ++i) {
                final Attr attr = (Attr)attrMap.item(i);
                final String name = attr.getName();
                String value = attr.getValue();
                if (value == null) {
                    value = "";
                }
                if (attr.getSpecified()) {
                    super._printer.printSpace();
                    super._printer.printText(name);
                    super._printer.printText("=\"");
                    this.printEscaped(value);
                    super._printer.printText('\"');
                }
                if (name.equals("xml:space")) {
                    if (value.equals("preserve")) {
                        this.fPreserveSpace = true;
                    }
                    else {
                        this.fPreserveSpace = super._format.getPreserveSpace();
                    }
                }
            }
        }
        else {
            for (int i = 0; i < length; ++i) {
                final Attr attr = (Attr)attrMap.item(i);
                final String uri = attr.getNamespaceURI();
                if (uri != null && uri.equals(NamespaceContext.XMLNS_URI)) {
                    String value = attr.getNodeValue();
                    if (value == null) {
                        value = XMLSymbols.EMPTY_STRING;
                    }
                    if (value.equals(NamespaceContext.XMLNS_URI)) {
                        if (super.fDOMErrorHandler != null) {
                            this.modifyDOMError("No prefix other than 'xmlns' can be bound to 'http://www.w3.org/2000/xmlns/' namespace name", (short)1, attr);
                            final boolean continueProcess = super.fDOMErrorHandler.handleError(super.fDOMError);
                            if (!continueProcess) {
                                throw new RuntimeException("Stopped at user request");
                            }
                        }
                    }
                    else {
                        String prefix = attr.getPrefix();
                        prefix = ((prefix == null || prefix.length() == 0) ? XMLSymbols.EMPTY_STRING : this.fSymbolTable.addSymbol(prefix));
                        final String localpart = this.fSymbolTable.addSymbol(attr.getLocalName());
                        if (prefix == XMLSymbols.PREFIX_XMLNS) {
                            value = this.fSymbolTable.addSymbol(value);
                            if (value.length() != 0) {
                                this.fNSBinder.declarePrefix(localpart, value);
                            }
                        }
                        else {
                            value = this.fSymbolTable.addSymbol(value);
                            this.fNSBinder.declarePrefix(XMLSymbols.EMPTY_STRING, value);
                        }
                    }
                }
            }
            String uri = elem.getNamespaceURI();
            String prefix = elem.getPrefix();
            if (uri != null && prefix != null && uri.length() == 0 && prefix.length() != 0) {
                prefix = null;
                super._printer.printText('<');
                super._printer.printText(elem.getLocalName());
                super._printer.indent();
            }
            else {
                super._printer.printText('<');
                super._printer.printText(tagName);
                super._printer.indent();
            }
            if (uri != null) {
                uri = this.fSymbolTable.addSymbol(uri);
                prefix = ((prefix == null || prefix.length() == 0) ? XMLSymbols.EMPTY_STRING : this.fSymbolTable.addSymbol(prefix));
                if (this.fNSBinder.getURI(prefix) != uri) {
                    this.printNamespaceAttr(prefix, uri);
                    this.fLocalNSBinder.declarePrefix(prefix, uri);
                }
            }
            else {
                final int colon = tagName.indexOf(58);
                if (colon > -1) {
                    if (super.fDOMErrorHandler != null) {
                        final String msg = DOMMessageFormatter.formatMessage("http://apache.org/xml/serializer", "ElementQName", new Object[] { tagName });
                        this.modifyDOMError(msg, (short)1, elem);
                        final boolean continueProcess2 = super.fDOMErrorHandler.handleError(super.fDOMError);
                        if (!continueProcess2) {
                            throw new RuntimeException("Process stoped at user request");
                        }
                    }
                }
                else {
                    uri = this.fNSBinder.getURI(XMLSymbols.EMPTY_STRING);
                    if (uri != null && uri.length() > 0) {
                        this.printNamespaceAttr(XMLSymbols.EMPTY_STRING, XMLSymbols.EMPTY_STRING);
                        this.fLocalNSBinder.declarePrefix(XMLSymbols.EMPTY_STRING, XMLSymbols.EMPTY_STRING);
                    }
                }
            }
            for (int i = 0; i < length; ++i) {
                final Attr attr = (Attr)attrMap.item(i);
                String value = attr.getValue();
                String name = attr.getNodeName();
                uri = attr.getNamespaceURI();
                if (uri != null && uri.length() == 0) {
                    uri = null;
                    name = attr.getLocalName();
                }
                if (value == null) {
                    value = XMLSymbols.EMPTY_STRING;
                }
                if (uri != null) {
                    prefix = attr.getPrefix();
                    prefix = ((prefix == null) ? XMLSymbols.EMPTY_STRING : this.fSymbolTable.addSymbol(prefix));
                    String localpart = this.fSymbolTable.addSymbol(attr.getLocalName());
                    if (uri != null && uri.equals(NamespaceContext.XMLNS_URI)) {
                        prefix = attr.getPrefix();
                        prefix = ((prefix == null || prefix.length() == 0) ? XMLSymbols.EMPTY_STRING : this.fSymbolTable.addSymbol(prefix));
                        localpart = this.fSymbolTable.addSymbol(attr.getLocalName());
                        if (prefix == XMLSymbols.PREFIX_XMLNS) {
                            final String localUri = this.fLocalNSBinder.getURI(localpart);
                            value = this.fSymbolTable.addSymbol(value);
                            if (value.length() != 0) {
                                if (localUri != null) {
                                    this.fNSBinder.declarePrefix(localpart, localUri);
                                }
                                else {
                                    this.printNamespaceAttr(localpart, value);
                                }
                            }
                        }
                        else {
                            uri = this.fNSBinder.getURI(XMLSymbols.EMPTY_STRING);
                            final String localUri = this.fLocalNSBinder.getURI(XMLSymbols.EMPTY_STRING);
                            value = this.fSymbolTable.addSymbol(value);
                            if (localUri != null) {
                                this.fNSBinder.declarePrefix(XMLSymbols.EMPTY_STRING, value);
                            }
                            else {
                                this.printNamespaceAttr(XMLSymbols.EMPTY_STRING, value);
                            }
                        }
                    }
                    else {
                        uri = this.fSymbolTable.addSymbol(uri);
                        final String declaredURI = this.fNSBinder.getURI(prefix);
                        if (prefix == XMLSymbols.EMPTY_STRING || declaredURI != uri) {
                            name = attr.getNodeName();
                            String declaredPrefix = this.fLocalNSBinder.getPrefix(uri);
                            if (declaredPrefix == null) {
                                declaredPrefix = this.fNSBinder.getPrefix(uri);
                            }
                            if (declaredPrefix != null && declaredPrefix != XMLSymbols.EMPTY_STRING) {
                                prefix = declaredPrefix;
                                name = prefix + ":" + localpart;
                            }
                            else {
                                if (prefix == XMLSymbols.EMPTY_STRING || this.fLocalNSBinder.getURI(prefix) != null) {
                                    for (prefix = this.fSymbolTable.addSymbol("NS" + this.fNamespaceCounter++); this.fLocalNSBinder.getURI(prefix) != null; prefix = this.fSymbolTable.addSymbol("NS" + this.fNamespaceCounter++)) {}
                                    name = prefix + ":" + localpart;
                                }
                                this.printNamespaceAttr(prefix, uri);
                                value = this.fSymbolTable.addSymbol(value);
                                this.fLocalNSBinder.declarePrefix(prefix, value);
                                this.fNSBinder.declarePrefix(prefix, uri);
                            }
                        }
                        this.printAttribute(name, (value == null) ? XMLSymbols.EMPTY_STRING : value, attr.getSpecified());
                    }
                }
                else {
                    final int colon = name.indexOf(58);
                    if (colon > -1) {
                        if (super.fDOMErrorHandler != null) {
                            this.modifyDOMError("DOM Level 1 Node: " + name, (short)1, attr);
                            final boolean continueProcess3 = super.fDOMErrorHandler.handleError(super.fDOMError);
                            if (!continueProcess3) {
                                throw new RuntimeException("Stopped at user request");
                            }
                        }
                        this.printAttribute(name, value, attr.getSpecified());
                    }
                    else {
                        this.printAttribute(name, value, attr.getSpecified());
                    }
                }
            }
        }
        if (elem.hasChildNodes()) {
            state = this.enterElementState(null, null, tagName, this.fPreserveSpace);
            state.doCData = super._format.isCDataElement(tagName);
            state.unescaped = super._format.isNonEscapingElement(tagName);
            for (Node child = elem.getFirstChild(); child != null; child = child.getNextSibling()) {
                this.serializeNode(child);
            }
            if (this.fNamespaces) {
                this.fNSBinder.popContext();
            }
            this.endElementIO(null, null, tagName);
        }
        else {
            if (this.fNamespaces) {
                this.fNSBinder.popContext();
            }
            super._printer.unindent();
            super._printer.printText("/>");
            state.afterElement = true;
            state.afterComment = false;
            state.empty = false;
            if (this.isDocumentState()) {
                super._printer.flush();
            }
        }
    }
    
    private void printNamespaceAttr(final String prefix, final String uri) throws IOException {
        super._printer.printSpace();
        if (prefix == XMLSymbols.EMPTY_STRING) {
            super._printer.printText(XMLSymbols.PREFIX_XMLNS);
        }
        else {
            super._printer.printText("xmlns:" + prefix);
        }
        super._printer.printText("=\"");
        this.printEscaped(uri);
        super._printer.printText('\"');
    }
    
    private void printAttribute(final String name, final String value, final boolean isSpecified) throws IOException {
        if (isSpecified || (super.fFeatures != null && !super.fFeatures.get("discard-default-content"))) {
            super._printer.printSpace();
            super._printer.printText(name);
            super._printer.printText("=\"");
            this.printEscaped(value);
            super._printer.printText('\"');
        }
        if (name.equals("xml:space")) {
            if (value.equals("preserve")) {
                this.fPreserveSpace = true;
            }
            else {
                this.fPreserveSpace = super._format.getPreserveSpace();
            }
        }
    }
    
    protected String getEntityRef(final int ch) {
        switch (ch) {
            case 60: {
                return "lt";
            }
            case 62: {
                return "gt";
            }
            case 34: {
                return "quot";
            }
            case 39: {
                return "apos";
            }
            case 38: {
                return "amp";
            }
            default: {
                return null;
            }
        }
    }
    
    private Attributes extractNamespaces(final Attributes attrs) throws SAXException {
        if (attrs == null) {
            return null;
        }
        final int length = attrs.getLength();
        final AttributesImpl attrsOnly = new AttributesImpl(attrs);
        for (int i = length - 1; i >= 0; --i) {
            final String rawName = attrsOnly.getQName(i);
            if (rawName.startsWith("xmlns")) {
                if (rawName.length() == 5) {
                    this.startPrefixMapping("", attrs.getValue(i));
                    attrsOnly.removeAttribute(i);
                }
                else if (rawName.charAt(5) == ':') {
                    this.startPrefixMapping(rawName.substring(6), attrs.getValue(i));
                    attrsOnly.removeAttribute(i);
                }
            }
        }
        return attrsOnly;
    }
    
    protected void printEscaped(final String source) throws IOException {
        for (int length = source.length(), i = 0; i < length; ++i) {
            final int ch = source.charAt(i);
            if (!XMLChar.isValid(ch)) {
                if (++i < length) {
                    this.surrogates(ch, source.charAt(i));
                }
                else {
                    this.fatalError("The character '" + (char)ch + "' is an invalid XML character");
                }
            }
            else {
                this.printXMLChar(ch);
            }
        }
    }
    
    protected final void printXMLChar(final int ch) throws IOException {
        if (ch == 60) {
            super._printer.printText("&lt;");
        }
        else if (ch == 38) {
            super._printer.printText("&amp;");
        }
        else if (ch == 34) {
            super._printer.printText("&quot;");
        }
        else if ((ch >= 32 && super._encodingInfo.isPrintable(ch)) || ch == 10 || ch == 13 || ch == 9) {
            super._printer.printText((char)ch);
        }
        else {
            super._printer.printText("&#x");
            super._printer.printText(Integer.toHexString(ch));
            super._printer.printText(';');
        }
    }
    
    protected void printText(final String text, final boolean preserveSpace, final boolean unescaped) throws IOException {
        final int length = text.length();
        if (preserveSpace) {
            for (int index = 0; index < length; ++index) {
                final char ch = text.charAt(index);
                if (!XMLChar.isValid(ch)) {
                    if (++index < length) {
                        this.surrogates(ch, text.charAt(index));
                    }
                    else {
                        this.fatalError("The character '" + ch + "' is an invalid XML character");
                    }
                }
                else if (unescaped) {
                    super._printer.printText(ch);
                }
                else {
                    this.printXMLChar(ch);
                }
            }
        }
        else {
            for (int index = 0; index < length; ++index) {
                final char ch = text.charAt(index);
                if (!XMLChar.isValid(ch)) {
                    if (++index < length) {
                        this.surrogates(ch, text.charAt(index));
                    }
                    else {
                        this.fatalError("The character '" + ch + "' is an invalid XML character");
                    }
                }
                else if (XMLChar.isSpace(ch)) {
                    super._printer.printSpace();
                }
                else if (unescaped) {
                    super._printer.printText(ch);
                }
                else {
                    this.printXMLChar(ch);
                }
            }
        }
    }
    
    protected void printText(final char[] chars, int start, int length, final boolean preserveSpace, final boolean unescaped) throws IOException {
        if (preserveSpace) {
            while (length-- > 0) {
                final char ch = chars[start];
                ++start;
                if (!XMLChar.isValid(ch)) {
                    if (++start < length) {
                        this.surrogates(ch, chars[start]);
                    }
                    else {
                        this.fatalError("The character '" + ch + "' is an invalid XML character");
                    }
                }
                else if (unescaped) {
                    super._printer.printText(ch);
                }
                else {
                    this.printXMLChar(ch);
                }
            }
        }
        else {
            while (length-- > 0) {
                final char ch = chars[start];
                ++start;
                if (!XMLChar.isValid(ch)) {
                    if (++start < length) {
                        this.surrogates(ch, chars[start]);
                    }
                    else {
                        this.fatalError("The character '" + ch + "' is an invalid XML character");
                    }
                }
                else if (XMLChar.isSpace(ch)) {
                    super._printer.printSpace();
                }
                else if (unescaped) {
                    super._printer.printText(ch);
                }
                else {
                    this.printXMLChar(ch);
                }
            }
        }
    }
    
    public boolean reset() {
        super.reset();
        return true;
    }
}
