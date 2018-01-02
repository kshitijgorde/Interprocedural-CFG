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
import org.xml.sax.Attributes;
import java.io.OutputStream;
import java.io.Writer;

public class XMLSerializer extends BaseMarkupSerializer
{
    public XMLSerializer() {
        super(new OutputFormat("xml", null, false));
    }
    
    public XMLSerializer(final OutputFormat outputFormat) {
        super((outputFormat != null) ? outputFormat : new OutputFormat("xml", null, false));
        super._format.setMethod("xml");
    }
    
    public XMLSerializer(final Writer outputCharStream, final OutputFormat outputFormat) {
        super((outputFormat != null) ? outputFormat : new OutputFormat("xml", null, false));
        super._format.setMethod("xml");
        this.setOutputCharStream(outputCharStream);
    }
    
    public XMLSerializer(final OutputStream outputByteStream, final OutputFormat outputFormat) {
        super((outputFormat != null) ? outputFormat : new OutputFormat("xml", null, false));
        super._format.setMethod("xml");
        this.setOutputByteStream(outputByteStream);
    }
    
    public void setOutputFormat(final OutputFormat outputFormat) {
        super.setOutputFormat((outputFormat != null) ? outputFormat : new OutputFormat("xml", null, false));
    }
    
    public void startElement(final String s, final String s2, String string, final Attributes attributes) {
        boolean b = false;
        if (super._printer == null) {
            throw new IllegalStateException("SER002 No writer supplied for serializer");
        }
        final ElementState elementState = this.getElementState();
        if (this.isDocumentState()) {
            if (!super._started) {
                this.startDocument((s2 == null) ? string : s2);
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
            if (super._indenting && !elementState.preserveSpace && (elementState.empty || elementState.afterElement)) {
                super._printer.breakLine();
            }
        }
        boolean preserveSpace = elementState.preserveSpace;
        if (string == null) {
            string = s2;
            if (s != null) {
                final String prefix = this.getPrefix(s);
                if (prefix.length() > 0) {
                    string = prefix + ":" + s2;
                }
            }
            b = true;
        }
        super._printer.printText('<');
        super._printer.printText(string);
        super._printer.indent();
        if (attributes != null) {
            for (int i = 0; i < attributes.getLength(); ++i) {
                super._printer.printSpace();
                String s3 = attributes.getQName(i);
                if (s3 == null) {
                    s3 = attributes.getLocalName(i);
                    final String uri = attributes.getURI(i);
                    if (uri != null && (s == null || !uri.equals(s))) {
                        final String prefix2 = this.getPrefix(uri);
                        if (prefix2 != null && prefix2.length() > 0) {
                            s3 = prefix2 + ":" + s3;
                        }
                    }
                }
                String value = attributes.getValue(i);
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
        enterElementState.doCData = super._format.isCDataElement((s == null) ? string : (s + "^" + s2));
        enterElementState.unescaped = super._format.isNonEscapingElement((s == null) ? string : (s + "^" + s2));
    }
    
    public void endElement(final String s, final String s2, final String s3) {
        super._printer.unindent();
        final ElementState elementState = this.getElementState();
        if (elementState.empty) {
            super._printer.printText("/>");
        }
        else {
            if (elementState.inCData) {
                super._printer.printText("]]>");
            }
            if (super._indenting && !elementState.preserveSpace && elementState.afterElement) {
                super._printer.breakLine();
            }
            super._printer.printText("</");
            super._printer.printText(elementState.rawName);
            super._printer.printText('>');
        }
        final ElementState leaveElementState = this.leaveElementState();
        leaveElementState.afterElement = true;
        leaveElementState.empty = false;
        if (this.isDocumentState()) {
            super._printer.flush();
        }
    }
    
    public void startElement(final String s, final AttributeList list) {
        if (super._printer == null) {
            throw new IllegalStateException("SER002 No writer supplied for serializer");
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
            if (super._indenting && !elementState.preserveSpace && (elementState.empty || elementState.afterElement)) {
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
    
    public void endElement(final String s) {
        this.endElement(null, null, s);
    }
    
    protected void startDocument(final String s) {
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
                if (super._format.getEncoding() != null) {
                    sb.append(" encoding=\"");
                    sb.append(super._format.getEncoding());
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
    
    protected void serializeElement(final Element element) {
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
            if (super._indenting && !elementState.preserveSpace && (elementState.empty || elementState.afterElement)) {
                super._printer.breakLine();
            }
        }
        boolean preserveSpace = elementState.preserveSpace;
        super._printer.printText('<');
        super._printer.printText(tagName);
        super._printer.indent();
        final NamedNodeMap attributes = element.getAttributes();
        if (attributes != null) {
            for (int i = 0; i < attributes.getLength(); ++i) {
                final Attr attr = (Attr)attributes.item(i);
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
                    preserveSpace = (value.equals("preserve") || super._format.getPreserveSpace());
                }
            }
        }
        if (element.hasChildNodes()) {
            final ElementState enterElementState = this.enterElementState(null, null, tagName, preserveSpace);
            enterElementState.doCData = super._format.isCDataElement(tagName);
            enterElementState.unescaped = super._format.isNonEscapingElement(tagName);
            for (Node node = element.getFirstChild(); node != null; node = node.getNextSibling()) {
                this.serializeNode(node);
            }
            this.endElement(tagName);
        }
        else {
            super._printer.unindent();
            super._printer.printText("/>");
            elementState.afterElement = true;
            elementState.empty = false;
            if (this.isDocumentState()) {
                super._printer.flush();
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
}
