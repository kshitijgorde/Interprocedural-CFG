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
import org.apache.xerces.dom.DOMMessageFormatter;
import org.xml.sax.Attributes;
import java.io.OutputStream;
import java.io.Writer;

public class HTMLSerializer extends BaseMarkupSerializer
{
    private boolean _xhtml;
    public static final String XHTMLNamespace = "http://www.w3.org/1999/xhtml";
    private String fUserXHTMLNamespace;
    
    protected HTMLSerializer(final boolean xhtml, final OutputFormat outputFormat) {
        super(outputFormat);
        this.fUserXHTMLNamespace = null;
        this._xhtml = xhtml;
    }
    
    public HTMLSerializer() {
        this(false, new OutputFormat("html", "ISO-8859-1", false));
    }
    
    public HTMLSerializer(final OutputFormat outputFormat) {
        this(false, (outputFormat != null) ? outputFormat : new OutputFormat("html", "ISO-8859-1", false));
    }
    
    public HTMLSerializer(final Writer outputCharStream, final OutputFormat outputFormat) {
        this(false, (outputFormat != null) ? outputFormat : new OutputFormat("html", "ISO-8859-1", false));
        this.setOutputCharStream(outputCharStream);
    }
    
    public HTMLSerializer(final OutputStream outputByteStream, final OutputFormat outputFormat) {
        this(false, (outputFormat != null) ? outputFormat : new OutputFormat("html", "ISO-8859-1", false));
        this.setOutputByteStream(outputByteStream);
    }
    
    public void setOutputFormat(final OutputFormat outputFormat) {
        super.setOutputFormat((outputFormat != null) ? outputFormat : new OutputFormat("html", "ISO-8859-1", false));
    }
    
    public void setXHTMLNamespace(final String fUserXHTMLNamespace) {
        this.fUserXHTMLNamespace = fUserXHTMLNamespace;
    }
    
    public void startElement(final String s, final String s2, String string, final Attributes attributes) throws SAXException {
        boolean b = false;
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
                if (super._indenting && !elementState.preserveSpace && (elementState.empty || elementState.afterElement)) {
                    super._printer.breakLine();
                }
            }
            boolean preserveSpace = elementState.preserveSpace;
            final boolean b2 = s != null && s.length() != 0;
            if (string == null || string.length() == 0) {
                string = s2;
                if (b2) {
                    final String prefix = this.getPrefix(s);
                    if (prefix != null && prefix.length() != 0) {
                        string = prefix + ":" + s2;
                    }
                }
                b = true;
            }
            String s3;
            if (!b2) {
                s3 = string;
            }
            else if (s.equals("http://www.w3.org/1999/xhtml") || (this.fUserXHTMLNamespace != null && this.fUserXHTMLNamespace.equals(s))) {
                s3 = s2;
            }
            else {
                s3 = null;
            }
            super._printer.printText('<');
            if (this._xhtml) {
                super._printer.printText(string.toLowerCase(Locale.ENGLISH));
            }
            else {
                super._printer.printText(string);
            }
            super._printer.indent();
            if (attributes != null) {
                for (int i = 0; i < attributes.getLength(); ++i) {
                    super._printer.printSpace();
                    final String lowerCase = attributes.getQName(i).toLowerCase(Locale.ENGLISH);
                    String value = attributes.getValue(i);
                    if (this._xhtml || b2) {
                        if (value == null) {
                            super._printer.printText(lowerCase);
                            super._printer.printText("=\"\"");
                        }
                        else {
                            super._printer.printText(lowerCase);
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
                            super._printer.printText(lowerCase);
                        }
                        else if (HTMLdtd.isURI(string, lowerCase)) {
                            super._printer.printText(lowerCase);
                            super._printer.printText("=\"");
                            super._printer.printText(this.escapeURI(value));
                            super._printer.printText('\"');
                        }
                        else if (HTMLdtd.isBoolean(string, lowerCase)) {
                            super._printer.printText(lowerCase);
                        }
                        else {
                            super._printer.printText(lowerCase);
                            super._printer.printText("=\"");
                            this.printEscaped(value);
                            super._printer.printText('\"');
                        }
                    }
                }
            }
            if (s3 != null && HTMLdtd.isPreserveSpace(s3)) {
                preserveSpace = true;
            }
            if (b) {
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
            if (s3 != null && (s3.equalsIgnoreCase("A") || s3.equalsIgnoreCase("TD"))) {
                enterElementState.empty = false;
                super._printer.printText('>');
            }
            if (s3 != null && (string.equalsIgnoreCase("SCRIPT") || string.equalsIgnoreCase("STYLE"))) {
                if (this._xhtml) {
                    enterElementState.doCData = true;
                }
                else {
                    enterElementState.unescaped = true;
                }
            }
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
        String s4;
        if (elementState.namespaceURI == null || elementState.namespaceURI.length() == 0) {
            s4 = elementState.rawName;
        }
        else if (elementState.namespaceURI.equals("http://www.w3.org/1999/xhtml") || (this.fUserXHTMLNamespace != null && this.fUserXHTMLNamespace.equals(elementState.namespaceURI))) {
            s4 = elementState.localName;
        }
        else {
            s4 = null;
        }
        if (this._xhtml) {
            if (elementState.empty) {
                super._printer.printText(" />");
            }
            else {
                if (elementState.inCData) {
                    super._printer.printText("]]>");
                }
                super._printer.printText("</");
                super._printer.printText(elementState.rawName.toLowerCase(Locale.ENGLISH));
                super._printer.printText('>');
            }
        }
        else {
            if (elementState.empty) {
                super._printer.printText('>');
            }
            if (s4 == null || !HTMLdtd.isOnlyOpening(s4)) {
                if (super._indenting && !elementState.preserveSpace && elementState.afterElement) {
                    super._printer.breakLine();
                }
                if (elementState.inCData) {
                    super._printer.printText("]]>");
                }
                super._printer.printText("</");
                super._printer.printText(elementState.rawName);
                super._printer.printText('>');
            }
        }
        final ElementState leaveElementState = this.leaveElementState();
        if (s4 == null || (!s4.equalsIgnoreCase("A") && !s4.equalsIgnoreCase("TD"))) {
            leaveElementState.afterElement = true;
        }
        leaveElementState.empty = false;
        if (this.isDocumentState()) {
            super._printer.flush();
        }
    }
    
    public void characters(final char[] array, final int n, final int n2) throws SAXException {
        try {
            this.content().doCData = false;
            super.characters(array, n, n2);
        }
        catch (IOException ex) {
            throw new SAXException(ex);
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
                if (super._indenting && !elementState.preserveSpace && (elementState.empty || elementState.afterElement)) {
                    super._printer.breakLine();
                }
            }
            boolean preserveSpace = elementState.preserveSpace;
            super._printer.printText('<');
            if (this._xhtml) {
                super._printer.printText(s.toLowerCase(Locale.ENGLISH));
            }
            else {
                super._printer.printText(s);
            }
            super._printer.indent();
            if (list != null) {
                for (int i = 0; i < list.getLength(); ++i) {
                    super._printer.printSpace();
                    final String lowerCase = list.getName(i).toLowerCase(Locale.ENGLISH);
                    String value = list.getValue(i);
                    if (this._xhtml) {
                        if (value == null) {
                            super._printer.printText(lowerCase);
                            super._printer.printText("=\"\"");
                        }
                        else {
                            super._printer.printText(lowerCase);
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
                            super._printer.printText(lowerCase);
                        }
                        else if (HTMLdtd.isURI(s, lowerCase)) {
                            super._printer.printText(lowerCase);
                            super._printer.printText("=\"");
                            super._printer.printText(this.escapeURI(value));
                            super._printer.printText('\"');
                        }
                        else if (HTMLdtd.isBoolean(s, lowerCase)) {
                            super._printer.printText(lowerCase);
                        }
                        else {
                            super._printer.printText(lowerCase);
                            super._printer.printText("=\"");
                            this.printEscaped(value);
                            super._printer.printText('\"');
                        }
                    }
                }
            }
            if (HTMLdtd.isPreserveSpace(s)) {
                preserveSpace = true;
            }
            final ElementState enterElementState = this.enterElementState(null, null, s, preserveSpace);
            if (s.equalsIgnoreCase("A") || s.equalsIgnoreCase("TD")) {
                enterElementState.empty = false;
                super._printer.printText('>');
            }
            if (s.equalsIgnoreCase("SCRIPT") || s.equalsIgnoreCase("STYLE")) {
                if (this._xhtml) {
                    enterElementState.doCData = true;
                }
                else {
                    enterElementState.unescaped = true;
                }
            }
        }
        catch (IOException ex) {
            throw new SAXException(ex);
        }
    }
    
    public void endElement(final String s) throws SAXException {
        this.endElement(null, null, s);
    }
    
    protected void startDocument(final String s) throws IOException {
        super._printer.leaveDTD();
        if (!super._started) {
            if (super._docTypePublicId == null && super._docTypeSystemId == null) {
                if (this._xhtml) {
                    super._docTypePublicId = "-//W3C//DTD XHTML 1.0 Strict//EN";
                    super._docTypeSystemId = "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd";
                }
                else {
                    super._docTypePublicId = "-//W3C//DTD HTML 4.01//EN";
                    super._docTypeSystemId = "http://www.w3.org/TR/html4/strict.dtd";
                }
            }
            if (!super._format.getOmitDocumentType()) {
                if (super._docTypePublicId != null && (!this._xhtml || super._docTypeSystemId != null)) {
                    if (this._xhtml) {
                        super._printer.printText("<!DOCTYPE html PUBLIC ");
                    }
                    else {
                        super._printer.printText("<!DOCTYPE HTML PUBLIC ");
                    }
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
                    if (this._xhtml) {
                        super._printer.printText("<!DOCTYPE html SYSTEM ");
                    }
                    else {
                        super._printer.printText("<!DOCTYPE HTML SYSTEM ");
                    }
                    this.printDoctypeURL(super._docTypeSystemId);
                    super._printer.printText('>');
                    super._printer.breakLine();
                }
            }
        }
        super._started = true;
        this.serializePreRoot();
    }
    
    protected void serializeElement(final Element element) throws IOException {
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
            if (super._indenting && !elementState.preserveSpace && (elementState.empty || elementState.afterElement)) {
                super._printer.breakLine();
            }
        }
        boolean preserveSpace = elementState.preserveSpace;
        super._printer.printText('<');
        if (this._xhtml) {
            super._printer.printText(tagName.toLowerCase(Locale.ENGLISH));
        }
        else {
            super._printer.printText(tagName);
        }
        super._printer.indent();
        final NamedNodeMap attributes = element.getAttributes();
        if (attributes != null) {
            for (int i = 0; i < attributes.getLength(); ++i) {
                final Attr attr = (Attr)attributes.item(i);
                final String lowerCase = attr.getName().toLowerCase(Locale.ENGLISH);
                String value = attr.getValue();
                if (attr.getSpecified()) {
                    super._printer.printSpace();
                    if (this._xhtml) {
                        if (value == null) {
                            super._printer.printText(lowerCase);
                            super._printer.printText("=\"\"");
                        }
                        else {
                            super._printer.printText(lowerCase);
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
                            super._printer.printText(lowerCase);
                        }
                        else if (HTMLdtd.isURI(tagName, lowerCase)) {
                            super._printer.printText(lowerCase);
                            super._printer.printText("=\"");
                            super._printer.printText(this.escapeURI(value));
                            super._printer.printText('\"');
                        }
                        else if (HTMLdtd.isBoolean(tagName, lowerCase)) {
                            super._printer.printText(lowerCase);
                        }
                        else {
                            super._printer.printText(lowerCase);
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
        if (element.hasChildNodes() || !HTMLdtd.isEmptyTag(tagName)) {
            final ElementState enterElementState = this.enterElementState(null, null, tagName, preserveSpace);
            if (tagName.equalsIgnoreCase("A") || tagName.equalsIgnoreCase("TD")) {
                enterElementState.empty = false;
                super._printer.printText('>');
            }
            if (tagName.equalsIgnoreCase("SCRIPT") || tagName.equalsIgnoreCase("STYLE")) {
                if (this._xhtml) {
                    enterElementState.doCData = true;
                }
                else {
                    enterElementState.unescaped = true;
                }
            }
            for (Node node = element.getFirstChild(); node != null; node = node.getNextSibling()) {
                this.serializeNode(node);
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
            elementState.afterElement = true;
            elementState.empty = false;
            if (this.isDocumentState()) {
                super._printer.flush();
            }
        }
    }
    
    protected void characters(final String s) throws IOException {
        this.content();
        super.characters(s);
    }
    
    protected String getEntityRef(final int n) {
        return HTMLdtd.fromChar(n);
    }
    
    protected String escapeURI(final String s) {
        final int index = s.indexOf("\"");
        if (index >= 0) {
            return s.substring(0, index);
        }
        return s;
    }
}
