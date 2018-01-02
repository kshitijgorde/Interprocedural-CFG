// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.serialize;

import org.apache.xerces.util.XMLChar;
import org.xml.sax.helpers.AttributesImpl;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.DOMError;
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
    protected static final String PREFIX = "NS";
    protected boolean fNamespaces;
    protected boolean fNamespacePrefixes;
    private boolean fPreserveSpace;
    
    public XMLSerializer() {
        super(new OutputFormat("xml", null, false));
        this.fNamespaces = false;
        this.fNamespacePrefixes = true;
    }
    
    public XMLSerializer(final OutputFormat outputFormat) {
        super((outputFormat != null) ? outputFormat : new OutputFormat("xml", null, false));
        this.fNamespaces = false;
        this.fNamespacePrefixes = true;
        super._format.setMethod("xml");
    }
    
    public XMLSerializer(final Writer outputCharStream, final OutputFormat outputFormat) {
        super((outputFormat != null) ? outputFormat : new OutputFormat("xml", null, false));
        this.fNamespaces = false;
        this.fNamespacePrefixes = true;
        super._format.setMethod("xml");
        this.setOutputCharStream(outputCharStream);
    }
    
    public XMLSerializer(final OutputStream outputByteStream, final OutputFormat outputFormat) {
        super((outputFormat != null) ? outputFormat : new OutputFormat("xml", null, false));
        this.fNamespaces = false;
        this.fNamespacePrefixes = true;
        super._format.setMethod("xml");
        this.setOutputByteStream(outputByteStream);
    }
    
    public void setOutputFormat(final OutputFormat outputFormat) {
        super.setOutputFormat((outputFormat != null) ? outputFormat : new OutputFormat("xml", null, false));
    }
    
    public void setNamespaces(final boolean fNamespaces) {
        this.fNamespaces = fNamespaces;
        if (this.fNSBinder == null) {
            this.fNSBinder = new NamespaceSupport();
            this.fLocalNSBinder = new NamespaceSupport();
            this.fSymbolTable = new SymbolTable();
        }
    }
    
    public void startElement(final String s, final String s2, String string, Attributes namespaces) throws SAXException {
        try {
            if (super._printer == null) {
                throw new IllegalStateException(DOMMessageFormatter.formatMessage("http://apache.org/xml/serializer", "NoWriterSupplied", null));
            }
            final ElementState elementState = this.getElementState();
            if (this.isDocumentState()) {
                if (!super._started) {
                    this.startDocument((s2 == null || s2.length() == 0) ? string : s2);
                }
            }
            else {
                if (elementState.empty) {
                    super._printer.printText('>');
                }
                if (elementState.inCData) {
                    super._printer.printText("]]>");
                    elementState.inCData = false;
                }
                if (super._indenting && !elementState.preserveSpace && (elementState.empty || elementState.afterElement || elementState.afterComment)) {
                    super._printer.breakLine();
                }
            }
            boolean preserveSpace = elementState.preserveSpace;
            namespaces = this.extractNamespaces(namespaces);
            if (string == null || string.length() == 0) {
                if (s2 == null) {
                    throw new SAXException(DOMMessageFormatter.formatMessage("http://apache.org/xml/serializer", "NoName", null));
                }
                if (s != null && !s.equals("")) {
                    final String prefix = this.getPrefix(s);
                    if (prefix != null && prefix.length() > 0) {
                        string = prefix + ":" + s2;
                    }
                    else {
                        string = s2;
                    }
                }
                else {
                    string = s2;
                }
            }
            super._printer.printText('<');
            super._printer.printText(string);
            super._printer.indent();
            if (namespaces != null) {
                for (int i = 0; i < namespaces.getLength(); ++i) {
                    super._printer.printSpace();
                    String s3 = namespaces.getQName(i);
                    if (s3 != null && s3.length() == 0) {
                        s3 = namespaces.getLocalName(i);
                        final String uri = namespaces.getURI(i);
                        if (uri != null && uri.length() != 0 && (s == null || s.length() == 0 || !uri.equals(s))) {
                            final String prefix2 = this.getPrefix(uri);
                            if (prefix2 != null && prefix2.length() > 0) {
                                s3 = prefix2 + ":" + s3;
                            }
                        }
                    }
                    String value = namespaces.getValue(i);
                    if (value == null) {
                        value = "";
                    }
                    super._printer.printText(s3);
                    super._printer.printText("=\"");
                    this.printEscaped(value);
                    super._printer.printText('\"');
                    if (s3.equals("xml:space")) {
                        preserveSpace = (value.equals("preserve") || super._format.getPreserveSpace());
                    }
                }
            }
            if (super._prefixes != null) {
                final Enumeration<String> keys = super._prefixes.keys();
                while (keys.hasMoreElements()) {
                    super._printer.printSpace();
                    final String s4 = keys.nextElement();
                    final String s5 = super._prefixes.get(s4);
                    if (s5.length() == 0) {
                        super._printer.printText("xmlns=\"");
                        this.printEscaped(s4);
                        super._printer.printText('\"');
                    }
                    else {
                        super._printer.printText("xmlns:");
                        super._printer.printText(s5);
                        super._printer.printText("=\"");
                        this.printEscaped(s4);
                        super._printer.printText('\"');
                    }
                }
            }
            final ElementState enterElementState = this.enterElementState(s, s2, string, preserveSpace);
            final String s6 = (s2 == null || s2.length() == 0) ? string : (s + "^" + s2);
            enterElementState.doCData = super._format.isCDataElement(s6);
            enterElementState.unescaped = super._format.isNonEscapingElement(s6);
        }
        catch (IOException ex) {
            throw new SAXException(ex);
        }
    }
    
    public void endElement(final String s, final String s2, final String s3) throws SAXException {
        try {
            this.endElementIO(s, s2, s3);
        }
        catch (IOException ex) {
            throw new SAXException(ex);
        }
    }
    
    public void endElementIO(final String s, final String s2, final String s3) throws IOException {
        super._printer.unindent();
        final ElementState elementState = this.getElementState();
        if (elementState.empty) {
            super._printer.printText("/>");
        }
        else {
            if (elementState.inCData) {
                super._printer.printText("]]>");
            }
            if (super._indenting && !elementState.preserveSpace && (elementState.afterElement || elementState.afterComment)) {
                super._printer.breakLine();
            }
            super._printer.printText("</");
            super._printer.printText(elementState.rawName);
            super._printer.printText('>');
        }
        final ElementState leaveElementState = this.leaveElementState();
        leaveElementState.afterElement = true;
        leaveElementState.afterComment = false;
        leaveElementState.empty = false;
        if (this.isDocumentState()) {
            super._printer.flush();
        }
    }
    
    public void startElement(final String s, final AttributeList list) throws SAXException {
        try {
            if (super._printer == null) {
                throw new IllegalStateException(DOMMessageFormatter.formatMessage("http://apache.org/xml/serializer", "NoWriterSupplied", null));
            }
            final ElementState elementState = this.getElementState();
            if (this.isDocumentState()) {
                if (!super._started) {
                    this.startDocument(s);
                }
            }
            else {
                if (elementState.empty) {
                    super._printer.printText('>');
                }
                if (elementState.inCData) {
                    super._printer.printText("]]>");
                    elementState.inCData = false;
                }
                if (super._indenting && !elementState.preserveSpace && (elementState.empty || elementState.afterElement || elementState.afterComment)) {
                    super._printer.breakLine();
                }
            }
            boolean preserveSpace = elementState.preserveSpace;
            super._printer.printText('<');
            super._printer.printText(s);
            super._printer.indent();
            if (list != null) {
                for (int i = 0; i < list.getLength(); ++i) {
                    super._printer.printSpace();
                    final String name = list.getName(i);
                    final String value = list.getValue(i);
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
            final ElementState enterElementState = this.enterElementState(null, null, s, preserveSpace);
            enterElementState.doCData = super._format.isCDataElement(s);
            enterElementState.unescaped = super._format.isNonEscapingElement(s);
        }
        catch (IOException ex) {
            throw new SAXException(ex);
        }
    }
    
    public void endElement(final String s) throws SAXException {
        this.endElement(null, null, s);
    }
    
    protected void startDocument(final String s) throws IOException {
        final String leaveDTD = super._printer.leaveDTD();
        if (!super._started) {
            if (!super._format.getOmitXMLDeclaration()) {
                final StringBuffer sb = new StringBuffer("<?xml version=\"");
                if (super._format.getVersion() != null) {
                    sb.append(super._format.getVersion());
                }
                else {
                    sb.append("1.0");
                }
                sb.append('\"');
                final String encoding = super._format.getEncoding();
                if (encoding != null) {
                    sb.append(" encoding=\"");
                    sb.append(encoding);
                    sb.append('\"');
                }
                if (super._format.getStandalone() && super._docTypeSystemId == null && super._docTypePublicId == null) {
                    sb.append(" standalone=\"yes\"");
                }
                sb.append("?>");
                super._printer.printText(sb);
                super._printer.breakLine();
            }
            if (!super._format.getOmitDocumentType()) {
                if (super._docTypeSystemId != null) {
                    super._printer.printText("<!DOCTYPE ");
                    super._printer.printText(s);
                    if (super._docTypePublicId != null) {
                        super._printer.printText(" PUBLIC ");
                        this.printDoctypeURL(super._docTypePublicId);
                        if (super._indenting) {
                            super._printer.breakLine();
                            for (int i = 0; i < 18 + s.length(); ++i) {
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
                    if (leaveDTD != null && leaveDTD.length() > 0) {
                        super._printer.printText(" [");
                        this.printText(leaveDTD, true, true);
                        super._printer.printText(']');
                    }
                    super._printer.printText(">");
                    super._printer.breakLine();
                }
                else if (leaveDTD != null && leaveDTD.length() > 0) {
                    super._printer.printText("<!DOCTYPE ");
                    super._printer.printText(s);
                    super._printer.printText(" [");
                    this.printText(leaveDTD, true, true);
                    super._printer.printText("]>");
                    super._printer.breakLine();
                }
            }
        }
        super._started = true;
        this.serializePreRoot();
    }
    
    protected void serializeElement(final Element element) throws IOException {
        if (this.fNamespaces) {
            this.fLocalNSBinder.reset();
            this.fNSBinder.pushContext();
        }
        final String tagName = element.getTagName();
        final ElementState elementState = this.getElementState();
        if (this.isDocumentState()) {
            if (!super._started) {
                this.startDocument(tagName);
            }
        }
        else {
            if (elementState.empty) {
                super._printer.printText('>');
            }
            if (elementState.inCData) {
                super._printer.printText("]]>");
                elementState.inCData = false;
            }
            if (super._indenting && !elementState.preserveSpace && (elementState.empty || elementState.afterElement || elementState.afterComment)) {
                super._printer.breakLine();
            }
        }
        this.fPreserveSpace = elementState.preserveSpace;
        int length = 0;
        NamedNodeMap attributes = null;
        if (element.hasAttributes()) {
            attributes = element.getAttributes();
            length = attributes.getLength();
        }
        if (!this.fNamespaces) {
            super._printer.printText('<');
            super._printer.printText(tagName);
            super._printer.indent();
            for (int i = 0; i < length; ++i) {
                final Attr attr = (Attr)attributes.item(i);
                final String name = attr.getName();
                String value = attr.getValue();
                if (value == null) {
                    value = "";
                }
                this.printAttribute(name, value, attr.getSpecified(), attr);
            }
        }
        else {
            for (int j = 0; j < length; ++j) {
                final Attr attr2 = (Attr)attributes.item(j);
                final String namespaceURI = attr2.getNamespaceURI();
                if (namespaceURI != null && namespaceURI.equals(NamespaceContext.XMLNS_URI)) {
                    String s = attr2.getNodeValue();
                    if (s == null) {
                        s = XMLSymbols.EMPTY_STRING;
                    }
                    if (s.equals(NamespaceContext.XMLNS_URI)) {
                        if (super.fDOMErrorHandler != null) {
                            this.modifyDOMError(DOMMessageFormatter.formatMessage("http://www.w3.org/TR/1998/REC-xml-19980210", "CantBindXMLNS", null), (short)2, null, attr2);
                            if (!super.fDOMErrorHandler.handleError(super.fDOMError)) {
                                throw new RuntimeException(DOMMessageFormatter.formatMessage("http://apache.org/xml/serializer", "SerializationStopped", null));
                            }
                        }
                    }
                    else {
                        final String prefix = attr2.getPrefix();
                        final String s2 = (prefix == null || prefix.length() == 0) ? XMLSymbols.EMPTY_STRING : this.fSymbolTable.addSymbol(prefix);
                        final String addSymbol = this.fSymbolTable.addSymbol(attr2.getLocalName());
                        if (s2 == XMLSymbols.PREFIX_XMLNS) {
                            final String addSymbol2 = this.fSymbolTable.addSymbol(s);
                            if (addSymbol2.length() != 0) {
                                this.fNSBinder.declarePrefix(addSymbol, addSymbol2);
                            }
                        }
                        else {
                            this.fNSBinder.declarePrefix(XMLSymbols.EMPTY_STRING, this.fSymbolTable.addSymbol(s));
                        }
                    }
                }
            }
            final String namespaceURI2 = element.getNamespaceURI();
            String prefix2 = element.getPrefix();
            if (namespaceURI2 != null && prefix2 != null && namespaceURI2.length() == 0 && prefix2.length() != 0) {
                prefix2 = null;
                super._printer.printText('<');
                super._printer.printText(element.getLocalName());
                super._printer.indent();
            }
            else {
                super._printer.printText('<');
                super._printer.printText(tagName);
                super._printer.indent();
            }
            if (namespaceURI2 != null) {
                final String addSymbol3 = this.fSymbolTable.addSymbol(namespaceURI2);
                final String s3 = (prefix2 == null || prefix2.length() == 0) ? XMLSymbols.EMPTY_STRING : this.fSymbolTable.addSymbol(prefix2);
                if (this.fNSBinder.getURI(s3) != addSymbol3) {
                    if (this.fNamespacePrefixes) {
                        this.printNamespaceAttr(s3, addSymbol3);
                    }
                    this.fLocalNSBinder.declarePrefix(s3, addSymbol3);
                    this.fNSBinder.declarePrefix(s3, addSymbol3);
                }
            }
            else if (element.getLocalName() == null) {
                if (super.fDOMErrorHandler != null) {
                    this.modifyDOMError(DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NullLocalElementName", new Object[] { element.getNodeName() }), (short)2, null, element);
                    if (!super.fDOMErrorHandler.handleError(super.fDOMError)) {
                        throw new RuntimeException(DOMMessageFormatter.formatMessage("http://apache.org/xml/serializer", "SerializationStopped", null));
                    }
                }
            }
            else {
                final String uri = this.fNSBinder.getURI(XMLSymbols.EMPTY_STRING);
                if (uri != null && uri.length() > 0) {
                    if (this.fNamespacePrefixes) {
                        this.printNamespaceAttr(XMLSymbols.EMPTY_STRING, XMLSymbols.EMPTY_STRING);
                    }
                    this.fLocalNSBinder.declarePrefix(XMLSymbols.EMPTY_STRING, XMLSymbols.EMPTY_STRING);
                    this.fNSBinder.declarePrefix(XMLSymbols.EMPTY_STRING, XMLSymbols.EMPTY_STRING);
                }
            }
            for (int k = 0; k < length; ++k) {
                final Attr attr3 = (Attr)attributes.item(k);
                String s4 = attr3.getValue();
                String s5 = attr3.getNodeName();
                String namespaceURI3 = attr3.getNamespaceURI();
                if (namespaceURI3 != null && namespaceURI3.length() == 0) {
                    namespaceURI3 = null;
                    s5 = attr3.getLocalName();
                }
                if (s4 == null) {
                    s4 = XMLSymbols.EMPTY_STRING;
                }
                if (namespaceURI3 != null) {
                    final String prefix3 = attr3.getPrefix();
                    String s6 = (prefix3 == null) ? XMLSymbols.EMPTY_STRING : this.fSymbolTable.addSymbol(prefix3);
                    final String addSymbol4 = this.fSymbolTable.addSymbol(attr3.getLocalName());
                    if (namespaceURI3 != null && namespaceURI3.equals(NamespaceContext.XMLNS_URI)) {
                        final String prefix4 = attr3.getPrefix();
                        final String s7 = (prefix4 == null || prefix4.length() == 0) ? XMLSymbols.EMPTY_STRING : this.fSymbolTable.addSymbol(prefix4);
                        final String addSymbol5 = this.fSymbolTable.addSymbol(attr3.getLocalName());
                        if (s7 == XMLSymbols.PREFIX_XMLNS) {
                            final String uri2 = this.fLocalNSBinder.getURI(addSymbol5);
                            final String addSymbol6 = this.fSymbolTable.addSymbol(s4);
                            if (addSymbol6.length() != 0 && uri2 == null) {
                                if (this.fNamespacePrefixes) {
                                    this.printNamespaceAttr(addSymbol5, addSymbol6);
                                }
                                this.fLocalNSBinder.declarePrefix(addSymbol5, addSymbol6);
                            }
                        }
                        else {
                            this.fNSBinder.getURI(XMLSymbols.EMPTY_STRING);
                            final String uri3 = this.fLocalNSBinder.getURI(XMLSymbols.EMPTY_STRING);
                            final String addSymbol7 = this.fSymbolTable.addSymbol(s4);
                            if (uri3 == null && this.fNamespacePrefixes) {
                                this.printNamespaceAttr(XMLSymbols.EMPTY_STRING, addSymbol7);
                            }
                        }
                    }
                    else {
                        final String addSymbol8 = this.fSymbolTable.addSymbol(namespaceURI3);
                        final String uri4 = this.fNSBinder.getURI(s6);
                        if (s6 == XMLSymbols.EMPTY_STRING || uri4 != addSymbol8) {
                            s5 = attr3.getNodeName();
                            final String prefix5 = this.fNSBinder.getPrefix(addSymbol8);
                            if (prefix5 != null && prefix5 != XMLSymbols.EMPTY_STRING) {
                                s5 = prefix5 + ":" + addSymbol4;
                            }
                            else {
                                if (s6 == XMLSymbols.EMPTY_STRING || this.fLocalNSBinder.getURI(s6) != null) {
                                    int n;
                                    for (n = 1, s6 = this.fSymbolTable.addSymbol("NS" + n++); this.fLocalNSBinder.getURI(s6) != null; s6 = this.fSymbolTable.addSymbol("NS" + n++)) {}
                                    s5 = s6 + ":" + addSymbol4;
                                }
                                if (this.fNamespacePrefixes) {
                                    this.printNamespaceAttr(s6, addSymbol8);
                                }
                                s4 = this.fSymbolTable.addSymbol(s4);
                                this.fLocalNSBinder.declarePrefix(s6, s4);
                                this.fNSBinder.declarePrefix(s6, addSymbol8);
                            }
                        }
                        this.printAttribute(s5, (s4 == null) ? XMLSymbols.EMPTY_STRING : s4, attr3.getSpecified(), attr3);
                    }
                }
                else if (attr3.getLocalName() == null) {
                    if (super.fDOMErrorHandler != null) {
                        this.modifyDOMError(DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NullLocalAttrName", new Object[] { attr3.getNodeName() }), (short)2, null, attr3);
                        if (!super.fDOMErrorHandler.handleError(super.fDOMError)) {
                            throw new RuntimeException(DOMMessageFormatter.formatMessage("http://apache.org/xml/serializer", "SerializationStopped", null));
                        }
                    }
                    this.printAttribute(s5, s4, attr3.getSpecified(), attr3);
                }
                else {
                    this.printAttribute(s5, s4, attr3.getSpecified(), attr3);
                }
            }
        }
        if (element.hasChildNodes()) {
            final ElementState enterElementState = this.enterElementState(null, null, tagName, this.fPreserveSpace);
            enterElementState.doCData = super._format.isCDataElement(tagName);
            enterElementState.unescaped = super._format.isNonEscapingElement(tagName);
            for (Node node = element.getFirstChild(); node != null; node = node.getNextSibling()) {
                this.serializeNode(node);
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
            elementState.afterElement = true;
            elementState.afterComment = false;
            elementState.empty = false;
            if (this.isDocumentState()) {
                super._printer.flush();
            }
        }
    }
    
    private void printNamespaceAttr(final String s, final String s2) throws IOException {
        super._printer.printSpace();
        if (s == XMLSymbols.EMPTY_STRING) {
            super._printer.printText(XMLSymbols.PREFIX_XMLNS);
        }
        else {
            super._printer.printText("xmlns:" + s);
        }
        super._printer.printText("=\"");
        this.printEscaped(s2);
        super._printer.printText('\"');
    }
    
    private void printAttribute(final String s, final String s2, final boolean b, final Attr attr) throws IOException {
        if (b || (super.features & 0x40) == 0x0) {
            if (super.fDOMFilter != null && (super.fDOMFilter.getWhatToShow() & 0x2) != 0x0) {
                switch (super.fDOMFilter.acceptNode(attr)) {
                    case 2:
                    case 3: {
                        return;
                    }
                }
            }
            super._printer.printSpace();
            super._printer.printText(s);
            super._printer.printText("=\"");
            this.printEscaped(s2);
            super._printer.printText('\"');
        }
        if (s.equals("xml:space")) {
            if (s2.equals("preserve")) {
                this.fPreserveSpace = true;
            }
            else {
                this.fPreserveSpace = super._format.getPreserveSpace();
            }
        }
    }
    
    protected String getEntityRef(final int n) {
        switch (n) {
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
    
    private Attributes extractNamespaces(final Attributes attributes) throws SAXException {
        if (attributes == null) {
            return null;
        }
        final int length = attributes.getLength();
        final AttributesImpl attributesImpl = new AttributesImpl(attributes);
        for (int i = length - 1; i >= 0; --i) {
            final String qName = attributesImpl.getQName(i);
            if (qName.startsWith("xmlns")) {
                if (qName.length() == 5) {
                    this.startPrefixMapping("", attributes.getValue(i));
                    attributesImpl.removeAttribute(i);
                }
                else if (qName.charAt(5) == ':') {
                    this.startPrefixMapping(qName.substring(6), attributes.getValue(i));
                    attributesImpl.removeAttribute(i);
                }
            }
        }
        return attributesImpl;
    }
    
    protected void printEscaped(final String s) throws IOException {
        for (int length = s.length(), i = 0; i < length; ++i) {
            final char char1 = s.charAt(i);
            if (!XMLChar.isValid(char1)) {
                if (++i < length) {
                    this.surrogates(char1, s.charAt(i));
                }
                else {
                    this.fatalError("The character '" + char1 + "' is an invalid XML character");
                }
            }
            else if (char1 == '\n' || char1 == '\r' || char1 == '\t') {
                this.printHex(char1);
            }
            else if (char1 == '<') {
                super._printer.printText("&lt;");
            }
            else if (char1 == '&') {
                super._printer.printText("&amp;");
            }
            else if (char1 == '\"') {
                super._printer.printText("&quot;");
            }
            else if (char1 >= ' ' && super._encodingInfo.isPrintable(char1)) {
                super._printer.printText(char1);
            }
            else {
                this.printHex(char1);
            }
        }
    }
    
    protected void printXMLChar(final int n) throws IOException {
        if (n == 13) {
            this.printHex(n);
        }
        else if (n == 60) {
            super._printer.printText("&lt;");
        }
        else if (n == 38) {
            super._printer.printText("&amp;");
        }
        else if (n == 62) {
            super._printer.printText("&gt;");
        }
        else if (n == 10 || n == 9 || (n >= 32 && super._encodingInfo.isPrintable((char)n))) {
            super._printer.printText((char)n);
        }
        else {
            this.printHex(n);
        }
    }
    
    protected void printText(final String s, final boolean b, final boolean b2) throws IOException {
        final int length = s.length();
        if (b) {
            for (int i = 0; i < length; ++i) {
                final char char1 = s.charAt(i);
                if (!XMLChar.isValid(char1)) {
                    if (++i < length) {
                        this.surrogates(char1, s.charAt(i));
                    }
                    else {
                        this.fatalError("The character '" + char1 + "' is an invalid XML character");
                    }
                }
                else if (b2) {
                    super._printer.printText(char1);
                }
                else {
                    this.printXMLChar(char1);
                }
            }
        }
        else {
            for (int j = 0; j < length; ++j) {
                final char char2 = s.charAt(j);
                if (!XMLChar.isValid(char2)) {
                    if (++j < length) {
                        this.surrogates(char2, s.charAt(j));
                    }
                    else {
                        this.fatalError("The character '" + char2 + "' is an invalid XML character");
                    }
                }
                else if (b2) {
                    super._printer.printText(char2);
                }
                else {
                    this.printXMLChar(char2);
                }
            }
        }
    }
    
    protected void printText(final char[] array, int n, int n2, final boolean b, final boolean b2) throws IOException {
        if (b) {
            while (n2-- > 0) {
                final char c = array[n++];
                if (!XMLChar.isValid(c)) {
                    if (n2-- > 0) {
                        this.surrogates(c, array[n++]);
                    }
                    else {
                        this.fatalError("The character '" + c + "' is an invalid XML character");
                    }
                }
                else if (b2) {
                    super._printer.printText(c);
                }
                else {
                    this.printXMLChar(c);
                }
            }
        }
        else {
            while (n2-- > 0) {
                final char c2 = array[n++];
                if (!XMLChar.isValid(c2)) {
                    if (n2-- > 0) {
                        this.surrogates(c2, array[n++]);
                    }
                    else {
                        this.fatalError("The character '" + c2 + "' is an invalid XML character");
                    }
                }
                else if (b2) {
                    super._printer.printText(c2);
                }
                else {
                    this.printXMLChar(c2);
                }
            }
        }
    }
    
    protected void checkUnboundNamespacePrefixedNode(final Node node) throws IOException {
        if (this.fNamespaces) {
            Node nextSibling;
            for (Node firstChild = node.getFirstChild(); firstChild != null; firstChild = nextSibling) {
                nextSibling = firstChild.getNextSibling();
                final String prefix = firstChild.getPrefix();
                final String s = (prefix == null || prefix.length() == 0) ? XMLSymbols.EMPTY_STRING : this.fSymbolTable.addSymbol(prefix);
                if (this.fNSBinder.getURI(s) == null && s != null) {
                    this.fatalError("The replacement text of the entity node '" + node.getNodeName() + "' contains an element node '" + firstChild.getNodeName() + "' with an undeclared prefix '" + s + "'.");
                }
                if (firstChild.getNodeType() == 1) {
                    final NamedNodeMap attributes = firstChild.getAttributes();
                    for (int i = 0; i < attributes.getLength(); ++i) {
                        final String prefix2 = attributes.item(i).getPrefix();
                        final String s2 = (prefix2 == null || prefix2.length() == 0) ? XMLSymbols.EMPTY_STRING : this.fSymbolTable.addSymbol(prefix2);
                        if (this.fNSBinder.getURI(s2) == null && s2 != null) {
                            this.fatalError("The replacement text of the entity node '" + node.getNodeName() + "' contains an element node '" + firstChild.getNodeName() + "' with an attribute '" + attributes.item(i).getNodeName() + "' an undeclared prefix '" + s2 + "'.");
                        }
                    }
                }
                if (firstChild.hasChildNodes()) {
                    this.checkUnboundNamespacePrefixedNode(firstChild);
                }
            }
        }
    }
    
    public boolean reset() {
        super.reset();
        if (this.fNSBinder != null) {
            this.fNSBinder.reset();
            this.fNSBinder.declarePrefix(XMLSymbols.EMPTY_STRING, XMLSymbols.EMPTY_STRING);
        }
        return true;
    }
}
