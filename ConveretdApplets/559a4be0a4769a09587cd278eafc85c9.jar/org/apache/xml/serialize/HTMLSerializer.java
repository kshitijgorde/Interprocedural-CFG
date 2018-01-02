// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.serialize;

import org.w3c.dom.Node;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.xml.sax.AttributeList;
import java.util.Enumeration;
import java.io.IOException;
import org.xml.sax.SAXException;
import java.util.Locale;
import org.xml.sax.Attributes;
import java.io.OutputStream;
import java.io.Writer;

public class HTMLSerializer extends BaseMarkupSerializer
{
    private boolean _xhtml;
    public static final String XHTMLNamespace = "http://www.w3.org/1999/xhtml";
    private String fUserXHTMLNamespace;
    
    protected HTMLSerializer(final boolean xhtml, final OutputFormat format) {
        super(format);
        this.fUserXHTMLNamespace = null;
        this._xhtml = xhtml;
    }
    
    public HTMLSerializer() {
        this(false, new OutputFormat("html", "ISO-8859-1", false));
    }
    
    public HTMLSerializer(final OutputFormat format) {
        this(false, (format != null) ? format : new OutputFormat("html", "ISO-8859-1", false));
    }
    
    public HTMLSerializer(final Writer writer, final OutputFormat format) {
        this(false, (format != null) ? format : new OutputFormat("html", "ISO-8859-1", false));
        this.setOutputCharStream(writer);
    }
    
    public HTMLSerializer(final OutputStream output, final OutputFormat format) {
        this(false, (format != null) ? format : new OutputFormat("html", "ISO-8859-1", false));
        this.setOutputByteStream(output);
    }
    
    public void setOutputFormat(final OutputFormat format) {
        super.setOutputFormat((format != null) ? format : new OutputFormat("html", "ISO-8859-1", false));
    }
    
    public void setXHTMLNamespace(final String newNamespace) {
        this.fUserXHTMLNamespace = newNamespace;
    }
    
    public void startElement(final String namespaceURI, final String localName, String rawName, final Attributes attrs) throws SAXException {
        boolean addNSAttr = false;
        try {
            if (super._printer == null) {
                throw new IllegalStateException("SER002 No writer supplied for serializer");
            }
            ElementState state = this.getElementState();
            if (this.isDocumentState()) {
                if (!super._started) {
                    this.startDocument((localName == null) ? rawName : localName);
                }
            }
            else {
                if (state.empty) {
                    super._printer.printText('>');
                }
                if (super._indenting && !state.preserveSpace && (state.empty || state.afterElement)) {
                    super._printer.breakLine();
                }
            }
            boolean preserveSpace = state.preserveSpace;
            if (rawName == null) {
                rawName = localName;
                if (namespaceURI != null) {
                    final String prefix = this.getPrefix(namespaceURI);
                    if (prefix.length() > 0) {
                        rawName = prefix + ":" + localName;
                    }
                }
                addNSAttr = true;
            }
            String htmlName;
            if (namespaceURI == null) {
                htmlName = rawName;
            }
            else if (namespaceURI.equals("http://www.w3.org/1999/xhtml") || (this.fUserXHTMLNamespace != null && this.fUserXHTMLNamespace.equals(namespaceURI))) {
                htmlName = localName;
            }
            else {
                htmlName = null;
            }
            super._printer.printText('<');
            if (this._xhtml) {
                super._printer.printText(rawName.toLowerCase(Locale.ENGLISH));
            }
            else {
                super._printer.printText(rawName);
            }
            super._printer.indent();
            if (attrs != null) {
                for (int i = 0; i < attrs.getLength(); ++i) {
                    super._printer.printSpace();
                    final String name = attrs.getQName(i).toLowerCase(Locale.ENGLISH);
                    String value = attrs.getValue(i);
                    if (this._xhtml || namespaceURI != null) {
                        if (value == null) {
                            super._printer.printText(name);
                            super._printer.printText("=\"\"");
                        }
                        else {
                            super._printer.printText(name);
                            super._printer.printText("=\"");
                            this.printEscaped(value);
                            super._printer.printText('\"');
                        }
                    }
                    else {
                        if (value == null) {
                            value = "";
                        }
                        if (!super._format.getPreserveEmptyAttributes() && value.length() == 0) {
                            super._printer.printText(name);
                        }
                        else if (HTMLdtd.isURI(rawName, name)) {
                            super._printer.printText(name);
                            super._printer.printText("=\"");
                            super._printer.printText(this.escapeURI(value));
                            super._printer.printText('\"');
                        }
                        else if (HTMLdtd.isBoolean(rawName, name)) {
                            super._printer.printText(name);
                        }
                        else {
                            super._printer.printText(name);
                            super._printer.printText("=\"");
                            this.printEscaped(value);
                            super._printer.printText('\"');
                        }
                    }
                }
            }
            if (htmlName != null && HTMLdtd.isPreserveSpace(htmlName)) {
                preserveSpace = true;
            }
            if (addNSAttr) {
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
            if (htmlName != null && (htmlName.equalsIgnoreCase("A") || htmlName.equalsIgnoreCase("TD"))) {
                state.empty = false;
                super._printer.printText('>');
            }
            if (htmlName != null && (rawName.equalsIgnoreCase("SCRIPT") || rawName.equalsIgnoreCase("STYLE"))) {
                if (this._xhtml) {
                    state.doCData = true;
                }
                else {
                    state.unescaped = true;
                }
            }
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
        String htmlName;
        if (state.namespaceURI == null) {
            htmlName = state.rawName;
        }
        else if (state.namespaceURI.equals("http://www.w3.org/1999/xhtml") || (this.fUserXHTMLNamespace != null && this.fUserXHTMLNamespace.equals(state.namespaceURI))) {
            htmlName = state.localName;
        }
        else {
            htmlName = null;
        }
        if (this._xhtml) {
            if (state.empty) {
                super._printer.printText(" />");
            }
            else {
                if (state.inCData) {
                    super._printer.printText("]]>");
                }
                super._printer.printText("</");
                super._printer.printText(state.rawName.toLowerCase(Locale.ENGLISH));
                super._printer.printText('>');
            }
        }
        else {
            if (state.empty) {
                super._printer.printText('>');
            }
            if (htmlName == null || !HTMLdtd.isOnlyOpening(htmlName)) {
                if (super._indenting && !state.preserveSpace && state.afterElement) {
                    super._printer.breakLine();
                }
                if (state.inCData) {
                    super._printer.printText("]]>");
                }
                super._printer.printText("</");
                super._printer.printText(state.rawName);
                super._printer.printText('>');
            }
        }
        state = this.leaveElementState();
        if (htmlName == null || (!htmlName.equalsIgnoreCase("A") && !htmlName.equalsIgnoreCase("TD"))) {
            state.afterElement = true;
        }
        state.empty = false;
        if (this.isDocumentState()) {
            super._printer.flush();
        }
    }
    
    public void characters(final char[] chars, final int start, final int length) throws SAXException {
        try {
            final ElementState state = this.content();
            state.doCData = false;
            super.characters(chars, start, length);
        }
        catch (IOException except) {
            throw new SAXException(except);
        }
    }
    
    public void startElement(final String tagName, final AttributeList attrs) throws SAXException {
        try {
            if (super._printer == null) {
                throw new IllegalStateException("SER002 No writer supplied for serializer");
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
                if (super._indenting && !state.preserveSpace && (state.empty || state.afterElement)) {
                    super._printer.breakLine();
                }
            }
            boolean preserveSpace = state.preserveSpace;
            super._printer.printText('<');
            if (this._xhtml) {
                super._printer.printText(tagName.toLowerCase(Locale.ENGLISH));
            }
            else {
                super._printer.printText(tagName);
            }
            super._printer.indent();
            if (attrs != null) {
                for (int i = 0; i < attrs.getLength(); ++i) {
                    super._printer.printSpace();
                    final String name = attrs.getName(i).toLowerCase(Locale.ENGLISH);
                    String value = attrs.getValue(i);
                    if (this._xhtml) {
                        if (value == null) {
                            super._printer.printText(name);
                            super._printer.printText("=\"\"");
                        }
                        else {
                            super._printer.printText(name);
                            super._printer.printText("=\"");
                            this.printEscaped(value);
                            super._printer.printText('\"');
                        }
                    }
                    else {
                        if (value == null) {
                            value = "";
                        }
                        if (!super._format.getPreserveEmptyAttributes() && value.length() == 0) {
                            super._printer.printText(name);
                        }
                        else if (HTMLdtd.isURI(tagName, name)) {
                            super._printer.printText(name);
                            super._printer.printText("=\"");
                            super._printer.printText(this.escapeURI(value));
                            super._printer.printText('\"');
                        }
                        else if (HTMLdtd.isBoolean(tagName, name)) {
                            super._printer.printText(name);
                        }
                        else {
                            super._printer.printText(name);
                            super._printer.printText("=\"");
                            this.printEscaped(value);
                            super._printer.printText('\"');
                        }
                    }
                }
            }
            if (HTMLdtd.isPreserveSpace(tagName)) {
                preserveSpace = true;
            }
            state = this.enterElementState(null, null, tagName, preserveSpace);
            if (tagName.equalsIgnoreCase("A") || tagName.equalsIgnoreCase("TD")) {
                state.empty = false;
                super._printer.printText('>');
            }
            if (tagName.equalsIgnoreCase("SCRIPT") || tagName.equalsIgnoreCase("STYLE")) {
                if (this._xhtml) {
                    state.doCData = true;
                }
                else {
                    state.unescaped = true;
                }
            }
        }
        catch (IOException except) {
            throw new SAXException(except);
        }
    }
    
    public void endElement(final String tagName) throws SAXException {
        this.endElement(null, null, tagName);
    }
    
    protected void startDocument(final String rootTagName) throws IOException {
        super._printer.leaveDTD();
        if (!super._started) {
            if (super._docTypePublicId == null && super._docTypeSystemId == null) {
                if (this._xhtml) {
                    super._docTypePublicId = "-//W3C//DTD XHTML 1.0 Strict//EN";
                    super._docTypeSystemId = "http://www.w3.org/TR/WD-html-in-xml/DTD/xhtml1-strict.dtd";
                }
                else {
                    super._docTypePublicId = "-//W3C//DTD HTML 4.0//EN";
                    super._docTypeSystemId = "http://www.w3.org/TR/WD-html-in-xml/DTD/xhtml1-strict.dtd";
                }
            }
            if (!super._format.getOmitDocumentType()) {
                if (super._docTypePublicId != null && (!this._xhtml || super._docTypeSystemId != null)) {
                    super._printer.printText("<!DOCTYPE HTML PUBLIC ");
                    this.printDoctypeURL(super._docTypePublicId);
                    if (super._docTypeSystemId != null) {
                        if (super._indenting) {
                            super._printer.breakLine();
                            super._printer.printText("                      ");
                        }
                        else {
                            super._printer.printText(' ');
                        }
                        this.printDoctypeURL(super._docTypeSystemId);
                    }
                    super._printer.printText('>');
                    super._printer.breakLine();
                }
                else if (super._docTypeSystemId != null) {
                    super._printer.printText("<!DOCTYPE HTML SYSTEM ");
                    this.printDoctypeURL(super._docTypeSystemId);
                    super._printer.printText('>');
                    super._printer.breakLine();
                }
            }
        }
        super._started = true;
        this.serializePreRoot();
    }
    
    protected void serializeElement(final Element elem) throws IOException {
        final String tagName = elem.getTagName();
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
            if (super._indenting && !state.preserveSpace && (state.empty || state.afterElement)) {
                super._printer.breakLine();
            }
        }
        boolean preserveSpace = state.preserveSpace;
        super._printer.printText('<');
        if (this._xhtml) {
            super._printer.printText(tagName.toLowerCase(Locale.ENGLISH));
        }
        else {
            super._printer.printText(tagName);
        }
        super._printer.indent();
        final NamedNodeMap attrMap = elem.getAttributes();
        if (attrMap != null) {
            for (int i = 0; i < attrMap.getLength(); ++i) {
                final Attr attr = (Attr)attrMap.item(i);
                final String name = attr.getName().toLowerCase(Locale.ENGLISH);
                String value = attr.getValue();
                if (attr.getSpecified()) {
                    super._printer.printSpace();
                    if (this._xhtml) {
                        if (value == null) {
                            super._printer.printText(name);
                            super._printer.printText("=\"\"");
                        }
                        else {
                            super._printer.printText(name);
                            super._printer.printText("=\"");
                            this.printEscaped(value);
                            super._printer.printText('\"');
                        }
                    }
                    else {
                        if (value == null) {
                            value = "";
                        }
                        if (!super._format.getPreserveEmptyAttributes() && value.length() == 0) {
                            super._printer.printText(name);
                        }
                        else if (HTMLdtd.isURI(tagName, name)) {
                            super._printer.printText(name);
                            super._printer.printText("=\"");
                            super._printer.printText(this.escapeURI(value));
                            super._printer.printText('\"');
                        }
                        else if (HTMLdtd.isBoolean(tagName, name)) {
                            super._printer.printText(name);
                        }
                        else {
                            super._printer.printText(name);
                            super._printer.printText("=\"");
                            this.printEscaped(value);
                            super._printer.printText('\"');
                        }
                    }
                }
            }
        }
        if (HTMLdtd.isPreserveSpace(tagName)) {
            preserveSpace = true;
        }
        if (elem.hasChildNodes() || !HTMLdtd.isEmptyTag(tagName)) {
            state = this.enterElementState(null, null, tagName, preserveSpace);
            if (tagName.equalsIgnoreCase("A") || tagName.equalsIgnoreCase("TD")) {
                state.empty = false;
                super._printer.printText('>');
            }
            if (tagName.equalsIgnoreCase("SCRIPT") || tagName.equalsIgnoreCase("STYLE")) {
                if (this._xhtml) {
                    state.doCData = true;
                }
                else {
                    state.unescaped = true;
                }
            }
            for (Node child = elem.getFirstChild(); child != null; child = child.getNextSibling()) {
                this.serializeNode(child);
            }
            this.endElementIO(null, null, tagName);
        }
        else {
            super._printer.unindent();
            if (this._xhtml) {
                super._printer.printText(" />");
            }
            else {
                super._printer.printText('>');
            }
            state.afterElement = true;
            state.empty = false;
            if (this.isDocumentState()) {
                super._printer.flush();
            }
        }
    }
    
    protected void characters(final String text) throws IOException {
        final ElementState state = this.content();
        super.characters(text);
    }
    
    protected String getEntityRef(final int ch) {
        return HTMLdtd.fromChar(ch);
    }
    
    protected String escapeURI(final String uri) {
        final int index = uri.indexOf("\"");
        if (index >= 0) {
            return uri.substring(0, index);
        }
        return uri;
    }
}
