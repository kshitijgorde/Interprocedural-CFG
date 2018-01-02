// 
// Decompiled by Procyon v0.5.30
// 

package org.jdom.output;

import org.jdom.Attribute;
import org.jdom.Namespace;
import java.io.StringWriter;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.BufferedOutputStream;
import java.io.UnsupportedEncodingException;
import org.jdom.EntityRef;
import org.jdom.ProcessingInstruction;
import org.jdom.Comment;
import org.jdom.Text;
import org.jdom.CDATA;
import com.sun.java.util.collections.List;
import org.jdom.Element;
import org.jdom.DocType;
import java.io.IOException;
import java.io.Writer;
import java.io.OutputStream;
import org.jdom.Document;

public class XMLOutputter implements Cloneable
{
    private static final String CVS_ID = "@(#) $RCSfile: XMLOutputter.java,v $ $Revision: 1.76 $ $Date: 2002/03/15 05:36:48 $ $Name: jdom_1_0_b8 $";
    private boolean omitDeclaration;
    private String encoding;
    private boolean omitEncoding;
    private static final String STANDARD_INDENT = "  ";
    private static final String STANDARD_LINE_SEPARATOR = "\r\n";
    Format noFormatting;
    Format defaultFormat;
    Format currentFormat;
    
    public XMLOutputter() {
        this.omitDeclaration = false;
        this.encoding = "UTF-8";
        this.omitEncoding = false;
        this.noFormatting = new Format();
        this.defaultFormat = new Format();
        this.currentFormat = this.defaultFormat;
    }
    
    public XMLOutputter(final String indent) {
        this.omitDeclaration = false;
        this.encoding = "UTF-8";
        this.omitEncoding = false;
        this.noFormatting = new Format();
        this.defaultFormat = new Format();
        this.currentFormat = this.defaultFormat;
        this.setIndent(indent);
    }
    
    public XMLOutputter(final String indent, final boolean newlines) {
        this.omitDeclaration = false;
        this.encoding = "UTF-8";
        this.omitEncoding = false;
        this.noFormatting = new Format();
        this.defaultFormat = new Format();
        this.currentFormat = this.defaultFormat;
        this.setIndent(indent);
        this.setNewlines(newlines);
    }
    
    public XMLOutputter(final String indent, final boolean newlines, final String encoding) {
        this.omitDeclaration = false;
        this.encoding = "UTF-8";
        this.omitEncoding = false;
        this.noFormatting = new Format();
        this.defaultFormat = new Format();
        this.currentFormat = this.defaultFormat;
        this.setEncoding(encoding);
        this.setIndent(indent);
        this.setNewlines(newlines);
    }
    
    public XMLOutputter(final XMLOutputter that) {
        this.omitDeclaration = false;
        this.encoding = "UTF-8";
        this.omitEncoding = false;
        this.noFormatting = new Format();
        this.defaultFormat = new Format();
        this.currentFormat = this.defaultFormat;
        this.encoding = that.encoding;
        this.omitDeclaration = that.omitDeclaration;
        this.omitEncoding = that.omitEncoding;
        this.defaultFormat = (Format)that.defaultFormat.clone();
    }
    
    public void setLineSeparator(final String separator) {
        this.defaultFormat.lineSeparator = separator;
    }
    
    public void setNewlines(final boolean newlines) {
        this.defaultFormat.newlines = newlines;
    }
    
    public void setEncoding(final String encoding) {
        this.encoding = encoding;
    }
    
    public void setOmitEncoding(final boolean omitEncoding) {
        this.omitEncoding = omitEncoding;
    }
    
    public void setOmitDeclaration(final boolean omitDeclaration) {
        this.omitDeclaration = omitDeclaration;
    }
    
    public void setExpandEmptyElements(final boolean expandEmptyElements) {
        this.defaultFormat.expandEmptyElements = expandEmptyElements;
    }
    
    public void setTrimAllWhite(final boolean trimAllWhite) {
        this.defaultFormat.trimAllWhite = trimAllWhite;
    }
    
    public void setTextTrim(final boolean textTrim) {
        this.defaultFormat.textTrim = textTrim;
    }
    
    public void setTextNormalize(final boolean textNormalize) {
        this.defaultFormat.textNormalize = textNormalize;
    }
    
    public void setIndent(String indent) {
        if ("".equals(indent)) {
            indent = null;
        }
        this.defaultFormat.indent = indent;
    }
    
    public void setIndent(final boolean doIndent) {
        if (doIndent) {
            this.defaultFormat.indent = "  ";
        }
        else {
            this.defaultFormat.indent = null;
        }
    }
    
    public void setIndent(final int size) {
        this.setIndentSize(size);
    }
    
    public void setIndentSize(final int indentSize) {
        final StringBuffer indentBuffer = new StringBuffer();
        for (int i = 0; i < indentSize; ++i) {
            indentBuffer.append(" ");
        }
        this.defaultFormat.indent = indentBuffer.toString();
    }
    
    public void output(final Document doc, final OutputStream out) throws IOException {
        final Writer writer = this.makeWriter(out);
        this.output(doc, writer);
    }
    
    public void output(final DocType doctype, final OutputStream out) throws IOException {
        final Writer writer = this.makeWriter(out);
        this.output(doctype, writer);
    }
    
    public void output(final Element element, final OutputStream out) throws IOException {
        final Writer writer = this.makeWriter(out);
        this.output(element, writer);
    }
    
    public void outputElementContent(final Element element, final OutputStream out) throws IOException {
        final Writer writer = this.makeWriter(out);
        this.outputElementContent(element, writer);
    }
    
    public void output(final List list, final OutputStream out) throws IOException {
        final Writer writer = this.makeWriter(out);
        this.output(list, writer);
    }
    
    public void output(final CDATA cdata, final OutputStream out) throws IOException {
        final Writer writer = this.makeWriter(out);
        this.output(cdata, writer);
    }
    
    public void output(final Text text, final OutputStream out) throws IOException {
        final Writer writer = this.makeWriter(out);
        this.output(text, writer);
    }
    
    public void output(final String string, final OutputStream out) throws IOException {
        final Writer writer = this.makeWriter(out);
        this.output(string, writer);
    }
    
    public void output(final Comment comment, final OutputStream out) throws IOException {
        final Writer writer = this.makeWriter(out);
        this.output(comment, writer);
    }
    
    public void output(final ProcessingInstruction pi, final OutputStream out) throws IOException {
        final Writer writer = this.makeWriter(out);
        this.output(pi, writer);
    }
    
    public void output(final EntityRef entity, final OutputStream out) throws IOException {
        final Writer writer = this.makeWriter(out);
        this.output(entity, writer);
    }
    
    protected Writer makeWriter(final OutputStream out) throws UnsupportedEncodingException {
        return this.makeWriter(out, this.encoding);
    }
    
    protected Writer makeWriter(final OutputStream out, String enc) throws UnsupportedEncodingException {
        if ("UTF-8".equals(enc)) {
            enc = "UTF8";
        }
        final Writer writer = new BufferedWriter(new OutputStreamWriter(new BufferedOutputStream(out), enc));
        return writer;
    }
    
    public void output(final Document doc, final Writer out) throws IOException {
        this.printDeclaration(doc, out, this.encoding);
        if (doc.getDocType() != null) {
            this.printDocType(doc.getDocType(), out);
        }
        final List content = doc.getContent();
        for (int i = 0; i < content.size(); ++i) {
            final Object obj = content.get(i);
            if (obj instanceof Element) {
                this.printElement(doc.getRootElement(), out, 0, this.createNamespaceStack());
            }
            else if (obj instanceof Comment) {
                this.printComment((Comment)obj, out);
            }
            else if (obj instanceof ProcessingInstruction) {
                this.printProcessingInstruction((ProcessingInstruction)obj, out);
            }
            this.newline(out);
            this.indent(out, 0);
        }
        out.write(this.currentFormat.lineSeparator);
        out.flush();
    }
    
    public void output(final DocType doctype, final Writer out) throws IOException {
        this.printDocType(doctype, out);
        out.flush();
    }
    
    public void output(final Element element, final Writer out) throws IOException {
        this.printElement(element, out, 0, this.createNamespaceStack());
        out.flush();
    }
    
    public void outputElementContent(final Element element, final Writer out) throws IOException {
        this.printContent(element.getContent(), out, 0, this.createNamespaceStack());
        out.flush();
    }
    
    public void output(final List list, final Writer out) throws IOException {
        this.printContent(list, out, 0, this.createNamespaceStack());
        out.flush();
    }
    
    public void output(final CDATA cdata, final Writer out) throws IOException {
        this.printCDATA(cdata, out);
        out.flush();
    }
    
    public void output(final Text text, final Writer out) throws IOException {
        this.printText(text, out);
        out.flush();
    }
    
    public void output(final String string, final Writer out) throws IOException {
        this.printString(string, out);
        out.flush();
    }
    
    public void output(final Comment comment, final Writer out) throws IOException {
        this.printComment(comment, out);
        out.flush();
    }
    
    public void output(final ProcessingInstruction pi, final Writer out) throws IOException {
        this.printProcessingInstruction(pi, out);
        out.flush();
    }
    
    public void output(final EntityRef entity, final Writer out) throws IOException {
        this.printEntityRef(entity, out);
        out.flush();
    }
    
    public String outputString(final Document doc) {
        final StringWriter out = new StringWriter();
        try {
            this.output(doc, out);
        }
        catch (IOException ex) {}
        return out.toString();
    }
    
    public String outputString(final DocType doctype) {
        final StringWriter out = new StringWriter();
        try {
            this.output(doctype, out);
        }
        catch (IOException ex) {}
        return out.toString();
    }
    
    public String outputString(final Element element) {
        final StringWriter out = new StringWriter();
        try {
            this.output(element, out);
        }
        catch (IOException ex) {}
        return out.toString();
    }
    
    public String outputString(final List list) {
        final StringWriter out = new StringWriter();
        try {
            this.output(list, out);
        }
        catch (IOException ex) {}
        return out.toString();
    }
    
    public String outputString(final CDATA cdata) {
        final StringWriter out = new StringWriter();
        try {
            this.output(cdata, out);
        }
        catch (IOException ex) {}
        return out.toString();
    }
    
    public String outputString(final Text text) {
        final StringWriter out = new StringWriter();
        try {
            this.output(text, out);
        }
        catch (IOException ex) {}
        return out.toString();
    }
    
    public String outputString(final String str) {
        final StringWriter out = new StringWriter();
        try {
            this.output(str, out);
        }
        catch (IOException ex) {}
        return out.toString();
    }
    
    public String outputString(final Comment comment) {
        final StringWriter out = new StringWriter();
        try {
            this.output(comment, out);
        }
        catch (IOException ex) {}
        return out.toString();
    }
    
    public String outputString(final ProcessingInstruction pi) {
        final StringWriter out = new StringWriter();
        try {
            this.output(pi, out);
        }
        catch (IOException ex) {}
        return out.toString();
    }
    
    public String outputString(final EntityRef entity) {
        final StringWriter out = new StringWriter();
        try {
            this.output(entity, out);
        }
        catch (IOException ex) {}
        return out.toString();
    }
    
    protected void printDeclaration(final Document doc, final Writer out, final String encoding) throws IOException {
        if (!this.omitDeclaration) {
            out.write("<?xml version=\"1.0\"");
            if (!this.omitEncoding) {
                out.write(" encoding=\"" + encoding + "\"");
            }
            out.write("?>");
            out.write(this.currentFormat.lineSeparator);
        }
    }
    
    protected void printDocType(final DocType docType, final Writer out) throws IOException {
        final String publicID = docType.getPublicID();
        final String systemID = docType.getSystemID();
        final String internalSubset = docType.getInternalSubset();
        boolean hasPublic = false;
        out.write("<!DOCTYPE ");
        out.write(docType.getElementName());
        if (publicID != null) {
            out.write(" PUBLIC \"");
            out.write(publicID);
            out.write("\"");
            hasPublic = true;
        }
        if (systemID != null) {
            if (!hasPublic) {
                out.write(" SYSTEM");
            }
            out.write(" \"");
            out.write(systemID);
            out.write("\"");
        }
        if (internalSubset != null && !internalSubset.equals("")) {
            out.write(" [\n");
            out.write(docType.getInternalSubset());
            out.write("]");
        }
        out.write(">");
        out.write(this.currentFormat.lineSeparator);
    }
    
    protected void printComment(final Comment comment, final Writer out) throws IOException {
        out.write("<!--");
        out.write(comment.getText());
        out.write("-->");
    }
    
    protected void printProcessingInstruction(final ProcessingInstruction pi, final Writer out) throws IOException {
        final String target = pi.getTarget();
        final String rawData = pi.getData();
        if (!"".equals(rawData)) {
            out.write("<?");
            out.write(target);
            out.write(" ");
            out.write(rawData);
            out.write("?>");
        }
        else {
            out.write("<?");
            out.write(target);
            out.write("?>");
        }
    }
    
    protected void printEntityRef(final EntityRef entity, final Writer out) throws IOException {
        out.write("&");
        out.write(entity.getName());
        out.write(";");
    }
    
    protected void printCDATA(final CDATA cdata, final Writer out) throws IOException {
        final String str = this.currentFormat.textNormalize ? cdata.getTextNormalize() : (this.currentFormat.textTrim ? cdata.getText().trim() : cdata.getText());
        out.write("<![CDATA[");
        out.write(str);
        out.write("]]>");
    }
    
    protected void printText(final Text text, final Writer out) throws IOException {
        final String str = this.currentFormat.textNormalize ? text.getTextNormalize() : (this.currentFormat.textTrim ? text.getText().trim() : text.getText());
        out.write(this.escapeElementEntities(str));
    }
    
    protected void printString(String str, final Writer out) throws IOException {
        if (this.currentFormat.textNormalize) {
            str = Text.normalizeString(str);
        }
        else if (this.currentFormat.textTrim) {
            str = str.trim();
        }
        out.write(this.escapeElementEntities(str));
    }
    
    protected void printElement(final Element element, final Writer out, final int level, final NamespaceStack namespaces) throws IOException {
        final List attributes = element.getAttributes();
        final List content = element.getContent();
        String space = null;
        if (attributes != null) {
            space = element.getAttributeValue("space", Namespace.XML_NAMESPACE);
        }
        final Format previousFormat = this.currentFormat;
        if ("default".equals(space)) {
            this.currentFormat = this.defaultFormat;
        }
        else if ("preserve".equals(space)) {
            this.currentFormat = this.noFormatting;
        }
        out.write("<");
        out.write(element.getQualifiedName());
        final int previouslyDeclaredNamespaces = namespaces.size();
        this.printElementNamespace(element, out, namespaces);
        this.printAdditionalNamespaces(element, out, namespaces);
        if (attributes != null) {
            this.printAttributes(attributes, element, out, namespaces);
        }
        final int start = this.skipLeadingWhite(content, 0);
        if (start >= content.size()) {
            if (this.currentFormat.expandEmptyElements) {
                out.write("></");
                out.write(element.getQualifiedName());
                out.write(">");
            }
            else {
                out.write(" />");
            }
        }
        else {
            out.write(">");
            if (this.nextNonText(content, start) < content.size()) {
                this.newline(out);
                this.printContentRange(content, start, content.size(), out, level + 1, namespaces);
                this.newline(out);
                this.indent(out, level);
            }
            else {
                this.printTextRange(content, start, content.size(), out);
            }
            out.write("</");
            out.write(element.getQualifiedName());
            out.write(">");
        }
        while (namespaces.size() > previouslyDeclaredNamespaces) {
            namespaces.pop();
        }
        this.currentFormat = previousFormat;
    }
    
    protected void printElementContent(final Element element, final Writer out, final int level, final NamespaceStack namespaces) throws IOException {
        this.printContent(element.getContent(), out, level, namespaces);
    }
    
    protected void printContent(final List content, final Writer out, final int level, final NamespaceStack namespaces) throws IOException {
        this.printContentRange(content, 0, content.size(), out, level, namespaces);
    }
    
    protected void printContentRange(final List content, final int start, final int end, final Writer out, final int level, final NamespaceStack namespaces) throws IOException {
        int index = start;
        while (index < end) {
            final boolean firstNode = index == start;
            final Object next = content.get(index);
            if (next instanceof CDATA || next instanceof Text) {
                final int first = this.skipLeadingWhite(content, index);
                index = this.nextNonText(content, first);
                if (first >= index) {
                    continue;
                }
                if (!firstNode) {
                    this.newline(out);
                }
                this.indent(out, level);
                this.printTextRange(content, first, index, out);
            }
            else {
                if (!firstNode) {
                    this.newline(out);
                }
                this.indent(out, level);
                if (next instanceof Comment) {
                    this.printComment((Comment)next, out);
                }
                else if (next instanceof Element) {
                    this.printElement((Element)next, out, level, namespaces);
                }
                else if (next instanceof EntityRef) {
                    this.printEntityRef((EntityRef)next, out);
                }
                else if (next instanceof ProcessingInstruction) {
                    this.printProcessingInstruction((ProcessingInstruction)next, out);
                }
                ++index;
            }
        }
    }
    
    protected void printTextRange(final List content, int start, int end, final Writer out) throws IOException {
        String previous = null;
        start = this.skipLeadingWhite(content, start);
        if (start < content.size()) {
            end = this.skipTrialingWhite(content, end);
            for (int i = start; i < end; ++i) {
                final Object node = content.get(i);
                String next;
                if (node instanceof CDATA) {
                    next = ((CDATA)node).getText();
                }
                else {
                    next = ((Text)node).getText();
                }
                if (next != null) {
                    if (!"".equals(next)) {
                        if (previous != null && (this.currentFormat.textNormalize || this.currentFormat.textTrim) && (this.endsWithWhite(previous) || this.startsWithWhite(next))) {
                            out.write(" ");
                        }
                        if (node instanceof CDATA) {
                            this.printCDATA((CDATA)node, out);
                        }
                        else {
                            this.printString(next, out);
                        }
                        previous = next;
                    }
                }
            }
        }
    }
    
    private void printNamespace(final Namespace ns, final Writer out, final NamespaceStack namespaces) throws IOException {
        final String prefix = ns.getPrefix();
        final String uri = ns.getURI();
        if (uri.equals(namespaces.getURI(prefix))) {
            return;
        }
        out.write(" xmlns");
        if (!prefix.equals("")) {
            out.write(":");
            out.write(prefix);
        }
        out.write("=\"");
        out.write(uri);
        out.write("\"");
        namespaces.push(ns);
    }
    
    protected void printAttributes(final List attributes, final Element parent, final Writer out, final NamespaceStack namespaces) throws IOException {
        for (int i = 0; i < attributes.size(); ++i) {
            final Attribute attribute = (Attribute)attributes.get(i);
            final Namespace ns = attribute.getNamespace();
            if (ns != Namespace.NO_NAMESPACE && ns != Namespace.XML_NAMESPACE) {
                this.printNamespace(ns, out, namespaces);
            }
            out.write(" ");
            out.write(attribute.getQualifiedName());
            out.write("=");
            out.write("\"");
            out.write(this.escapeAttributeEntities(attribute.getValue()));
            out.write("\"");
        }
    }
    
    private void printElementNamespace(final Element element, final Writer out, final NamespaceStack namespaces) throws IOException {
        final Namespace ns = element.getNamespace();
        if (ns == Namespace.XML_NAMESPACE) {
            return;
        }
        if (ns != Namespace.NO_NAMESPACE || namespaces.getURI("") != null) {
            this.printNamespace(ns, out, namespaces);
        }
    }
    
    private void printAdditionalNamespaces(final Element element, final Writer out, final NamespaceStack namespaces) throws IOException {
        final List list = element.getAdditionalNamespaces();
        if (list != null) {
            for (int i = 0; i < list.size(); ++i) {
                final Namespace additional = (Namespace)list.get(i);
                this.printNamespace(additional, out, namespaces);
            }
        }
    }
    
    protected void newline(final Writer out) throws IOException {
        if (this.currentFormat.newlines) {
            out.write(this.currentFormat.lineSeparator);
        }
    }
    
    protected void indent(final Writer out) throws IOException {
        this.indent(out, 0);
    }
    
    protected void indent(final Writer out, final int level) throws IOException {
        if (this.currentFormat.newlines) {
            if (this.currentFormat.indent == null || this.currentFormat.indent.equals("")) {
                return;
            }
            for (int i = 0; i < level; ++i) {
                out.write(this.currentFormat.indent);
            }
        }
    }
    
    private int skipLeadingWhite(final List content, int start) {
        if (start < 0) {
            start = 0;
        }
        int index = start;
        if (!this.currentFormat.trimAllWhite && !this.currentFormat.textNormalize && !this.currentFormat.textTrim) {
            if (!this.currentFormat.newlines) {
                return index;
            }
        }
        while (index < content.size()) {
            if (!this.isAllWhitespace(content.get(index))) {
                break;
            }
            ++index;
        }
        return index;
    }
    
    private int skipTrialingWhite(final List content, int start) {
        if (start > content.size()) {
            start = content.size();
        }
        int index = start;
        if (!this.currentFormat.trimAllWhite && !this.currentFormat.textNormalize && !this.currentFormat.textTrim) {
            if (!this.currentFormat.newlines) {
                return index;
            }
        }
        while (index >= 0) {
            if (!this.isAllWhitespace(content.get(index - 1))) {
                break;
            }
            --index;
        }
        return index;
    }
    
    private int nextNonText(final List content, int start) {
        if (start < 0) {
            start = 0;
        }
        int index;
        for (index = start; index < content.size() && (content.get(index) instanceof CDATA || content.get(index) instanceof Text); ++index) {}
        return index;
    }
    
    private boolean isAllWhitespace(final Object obj) {
        String str = null;
        if (obj instanceof String) {
            str = (String)obj;
        }
        else if (obj instanceof CDATA) {
            str = ((CDATA)obj).getText();
        }
        else {
            if (!(obj instanceof Text)) {
                return false;
            }
            str = ((Text)obj).getText();
        }
        for (int i = 0; i < str.length(); ++i) {
            if (!this.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    
    private boolean startsWithWhite(final String str) {
        return str != null && str.length() > 0 && this.isWhitespace(str.charAt(0));
    }
    
    private boolean endsWithWhite(final String str) {
        return str != null && str.length() > 0 && this.isWhitespace(str.charAt(str.length() - 1));
    }
    
    private boolean isWhitespace(final char ch) {
        return " \t\n\r".indexOf(ch) >= 0;
    }
    
    public String escapeAttributeEntities(final String str) {
        StringBuffer buffer = null;
        for (int i = 0; i < str.length(); ++i) {
            final char ch = str.charAt(i);
            String entity = null;
            switch (ch) {
                case '<': {
                    entity = "&lt;";
                    break;
                }
                case '>': {
                    entity = "&gt;";
                    break;
                }
                case '\"': {
                    entity = "&quot;";
                    break;
                }
                case '&': {
                    entity = "&amp;";
                    break;
                }
                default: {
                    entity = null;
                    break;
                }
            }
            if (buffer == null) {
                if (entity != null) {
                    buffer = new StringBuffer(str.length() + 20);
                    buffer.append(str.substring(0, i));
                    buffer.append(entity);
                }
            }
            else if (entity == null) {
                buffer.append(ch);
            }
            else {
                buffer.append(entity);
            }
        }
        return (buffer == null) ? str : buffer.toString();
    }
    
    public String escapeElementEntities(final String str) {
        StringBuffer buffer = null;
        for (int i = 0; i < str.length(); ++i) {
            final char ch = str.charAt(i);
            String entity = null;
            switch (ch) {
                case '<': {
                    entity = "&lt;";
                    break;
                }
                case '>': {
                    entity = "&gt;";
                    break;
                }
                case '&': {
                    entity = "&amp;";
                    break;
                }
                default: {
                    entity = null;
                    break;
                }
            }
            if (buffer == null) {
                if (entity != null) {
                    buffer = new StringBuffer(str.length() + 20);
                    buffer.append(str.substring(0, i));
                    buffer.append(entity);
                }
            }
            else if (entity == null) {
                buffer.append(ch);
            }
            else {
                buffer.append(entity);
            }
        }
        return (buffer == null) ? str : buffer.toString();
    }
    
    public int parseArgs(final String[] args, int i) {
        while (i < args.length) {
            if (args[i].equals("-omitDeclaration")) {
                this.setOmitDeclaration(true);
            }
            else if (args[i].equals("-omitEncoding")) {
                this.setOmitEncoding(true);
            }
            else if (args[i].equals("-indent")) {
                this.setIndent(args[++i]);
            }
            else if (args[i].equals("-indentSize")) {
                this.setIndentSize(Integer.parseInt(args[++i]));
            }
            else if (args[i].startsWith("-expandEmpty")) {
                this.setExpandEmptyElements(true);
            }
            else if (args[i].equals("-encoding")) {
                this.setEncoding(args[++i]);
            }
            else if (args[i].equals("-newlines")) {
                this.setNewlines(true);
            }
            else if (args[i].equals("-lineSeparator")) {
                this.setLineSeparator(args[++i]);
            }
            else if (args[i].equals("-trimAllWhite")) {
                this.setTrimAllWhite(true);
            }
            else if (args[i].equals("-textTrim")) {
                this.setTextTrim(true);
            }
            else {
                if (!args[i].equals("-textNormalize")) {
                    return i;
                }
                this.setTextNormalize(true);
            }
            ++i;
        }
        return i;
    }
    
    public Object clone() {
        try {
            return super.clone();
        }
        catch (CloneNotSupportedException e) {
            throw new RuntimeException(e.toString());
        }
    }
    
    public String toString() {
        final StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < this.defaultFormat.lineSeparator.length(); ++i) {
            final char ch = this.defaultFormat.lineSeparator.charAt(i);
            switch (ch) {
                case '\r': {
                    buffer.append("\\r");
                    break;
                }
                case '\n': {
                    buffer.append("\\n");
                    break;
                }
                case '\t': {
                    buffer.append("\\t");
                    break;
                }
                default: {
                    buffer.append("[" + (int)ch + "]");
                    break;
                }
            }
        }
        return "XMLOutputter[omitDeclaration = " + this.omitDeclaration + ", " + "encoding = " + this.encoding + ", " + "omitEncoding = " + this.omitEncoding + ", " + "indent = '" + this.defaultFormat.indent + "'" + ", " + "expandEmptyElements = " + this.defaultFormat.expandEmptyElements + ", " + "newlines = " + this.defaultFormat.newlines + ", " + "lineSeparator = '" + buffer.toString() + "', " + "trimAllWhite = " + this.defaultFormat.trimAllWhite + "textTrim = " + this.defaultFormat.textTrim + "textNormalize = " + this.defaultFormat.textNormalize + "]";
    }
    
    protected NamespaceStack createNamespaceStack() {
        return new NamespaceStack();
    }
    
    public void setPadText(final boolean padText) {
    }
    
    public void setIndentLevel(final int level) {
    }
    
    public void setSuppressDeclaration(final boolean suppressDeclaration) {
        this.omitDeclaration = suppressDeclaration;
    }
    
    class Format implements Cloneable
    {
        String indent;
        boolean expandEmptyElements;
        String lineSeparator;
        boolean trimAllWhite;
        boolean textTrim;
        boolean textNormalize;
        boolean newlines;
        
        Format() {
            this.indent = null;
            this.expandEmptyElements = false;
            this.lineSeparator = "\r\n";
            this.trimAllWhite = false;
            this.textTrim = false;
            this.textNormalize = false;
            this.newlines = false;
        }
        
        protected Object clone() {
            Format format = null;
            try {
                format = (Format)super.clone();
            }
            catch (CloneNotSupportedException ex) {}
            return format;
        }
    }
    
    protected class NamespaceStack extends org.jdom.output.NamespaceStack
    {
    }
}
