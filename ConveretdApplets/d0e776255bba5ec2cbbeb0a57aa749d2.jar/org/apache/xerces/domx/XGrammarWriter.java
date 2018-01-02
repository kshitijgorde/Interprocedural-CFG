// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.domx;

import java.util.StringTokenizer;
import java.util.Vector;
import java.io.StringWriter;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Attr;
import org.w3c.dom.Text;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.apache.xerces.validators.schema.XUtil;
import org.apache.xerces.readers.MIME2Java;
import org.w3c.dom.DocumentType;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.io.OutputStream;
import org.apache.xerces.parsers.DOMParser;
import java.io.PrintWriter;

public class XGrammarWriter
{
    protected static final OutputFormat DEFAULT_OUTPUT_FORMAT;
    protected static final String[] CONTENT_MODEL_ELEMENT_NAMES;
    protected PrintWriter out;
    protected int indent;
    protected OutputFormat format;
    protected boolean verbose;
    protected String encoding;
    protected boolean canonical;
    
    public static void main(final String[] array) {
        final DOMParser domParser = new DOMParser();
        try {
            domParser.setFeature("http://apache.org/xml/features/domx/grammar-access", true);
        }
        catch (Exception ex2) {
            System.err.println("warning: unable to set feature.");
        }
        final XGrammarWriter xGrammarWriter = new XGrammarWriter();
        if (array.length == 0) {
            printUsage();
        }
        else {
            for (int i = 0; i < array.length; ++i) {
                String s = array[i];
                if (s.startsWith("-")) {
                    if (s.equals("-d") || s.equals("--dtd")) {
                        xGrammarWriter.setOutputFormat(OutputFormat.DTD);
                        continue;
                    }
                    if (s.equals("-x") || s.equals("--schema")) {
                        xGrammarWriter.setOutputFormat(OutputFormat.XML_SCHEMA);
                        continue;
                    }
                    if (s.equals("-v") || s.equals("--verbose")) {
                        xGrammarWriter.setVerbose(true);
                        continue;
                    }
                    if (s.equals("-q") || s.equals("--quiet")) {
                        xGrammarWriter.setVerbose(false);
                        continue;
                    }
                    if (s.equals("-h") || s.equals("--help")) {
                        printUsage();
                        break;
                    }
                    if (s.equals("--")) {
                        if (i < array.length - 1) {
                            System.err.println("error: Missing argument to -- option.");
                            break;
                        }
                        s = array[++i];
                    }
                    else {
                        System.err.println("error: Unknown option (" + s + ").");
                    }
                }
                try {
                    domParser.parse(s);
                    xGrammarWriter.printGrammar(s, domParser.getDocument().getDoctype());
                }
                catch (Exception ex) {
                    System.err.println("error: Error parsing document (" + s + ").");
                    ex.printStackTrace(System.err);
                }
            }
        }
    }
    
    private static void printUsage() {
        System.err.println("usage: java org.apache.xerces.domx.XGrammarWriter (options) uri ...");
        System.err.println();
        System.err.println("options:");
        System.err.println("  -d | --dtd      Output document grammar in DTD format.");
        System.err.println("  -x | --schema   Output document grammar in XML Schema format. (default)");
        System.err.println("  -v | --verbose  Verbose output prints default attributes.");
        System.err.println("  -q | --quiet    Quiet output prints specified attributes. (default)");
        System.err.println("  -h | --help     This help screen.");
        System.err.println();
        System.err.println("  -- filename     Specify input URI that starts with a hyphen (-).");
    }
    
    public XGrammarWriter() {
        this(System.out);
    }
    
    public XGrammarWriter(final PrintWriter out) {
        this.init();
        this.out = out;
    }
    
    public XGrammarWriter(final OutputStream outputStream) {
        this.init();
        try {
            this.out = new PrintWriter(new OutputStreamWriter(outputStream, this.encoding));
        }
        catch (UnsupportedEncodingException ex) {
            this.encoding = null;
            this.out = new PrintWriter(outputStream);
        }
    }
    
    public XGrammarWriter(final Writer writer, final String encoding) {
        this(new PrintWriter(writer));
        this.encoding = encoding;
    }
    
    public void setOutputFormat(final OutputFormat format) {
        this.format = format;
    }
    
    public OutputFormat getOutputFormat() {
        return this.format;
    }
    
    public void setVerbose(final boolean verbose) {
        this.verbose = verbose;
    }
    
    public boolean isVerbose() {
        return this.verbose;
    }
    
    public void setCanonical(final boolean canonical) {
        this.canonical = canonical;
    }
    
    public boolean isCanonical() {
        return this.canonical;
    }
    
    public void printGrammar(final String s, final DocumentType documentType) {
        this.out.print("<?xml ");
        if (this.format.equals(OutputFormat.XML_SCHEMA)) {
            this.out.print("version=\"1.0\" ");
        }
        String reverse = MIME2Java.reverse(this.encoding);
        if (reverse == null) {
            reverse = "US-ASCII";
        }
        this.out.print("encoding=\"");
        this.out.print(reverse);
        this.out.print('\"');
        this.out.print("?>");
        this.out.flush();
        this.out.println();
        this.out.print("<!-- Grammar referenced in document: \"");
        this.out.print(s);
        this.out.print("\" -->");
        this.out.flush();
        if (documentType == null) {
            return;
        }
        final Element firstChildElement = XUtil.getFirstChildElement(documentType, "schema");
        if (this.format.equals(OutputFormat.DTD)) {
            this.out.println();
            for (Element element = XUtil.getFirstChildElement(firstChildElement); element != null; element = XUtil.getNextSiblingElement(element)) {
                final String nodeName = element.getNodeName();
                if (nodeName.equals("element")) {
                    this.printElementDecl(element);
                }
                else if (nodeName.equals("textEntity")) {
                    this.printEntityDecl(element);
                }
                else if (nodeName.equals("externalEntity")) {
                    this.printEntityDecl(element);
                }
                else if (nodeName.equals("unparsedEntity")) {
                    this.printEntityDecl(element);
                }
                else if (nodeName.equals("notation")) {
                    this.printNotationDecl(element);
                }
                else if (nodeName.equals("comment")) {
                    this.printComment(element);
                }
            }
            return;
        }
        if (this.format.equals(OutputFormat.XML_SCHEMA)) {
            this.out.println();
            this.out.print("<!DOCTYPE schema PUBLIC \"-//W3C//DTD XML Schema Version 1.0//EN\" \"http://www.w3.org/XML/Group/1999/09/23-xmlschema/structures/structures.dtd\">");
            this.printElement(firstChildElement);
            this.out.println();
            this.out.flush();
            return;
        }
        throw new IllegalArgumentException("unknown output format (" + this.format + ")");
    }
    
    public void printComment(final Element element) {
        Node node = element.getFirstChild();
        if (node != null) {
            this.out.println();
            this.printIndent(this.indent);
            while (node != null) {
                if (node.getNodeType() == 3) {
                    this.out.print(node.getNodeValue());
                }
                node = node.getNextSibling();
            }
            this.out.flush();
        }
    }
    
    public void printElement(final Element element) {
        if (this.isEmpty(element)) {
            this.out.println();
            this.printIndent(this.indent);
            this.printEmptyElement(element);
        }
        else {
            this.out.println();
            this.printIndent(this.indent);
            this.printOpenElement(element);
            Node node = element.getFirstChild();
            int nodeType = -1;
            while (node != null) {
                nodeType = node.getNodeType();
                if (nodeType == 1) {
                    ++this.indent;
                    this.printElement((Element)node);
                    --this.indent;
                }
                else if (nodeType == 3) {
                    this.printText((Text)node);
                }
                node = node.getNextSibling();
            }
            if (nodeType != 3) {
                this.out.println();
                this.printIndent(this.indent);
            }
            this.printCloseElement(element);
        }
        this.out.flush();
    }
    
    public void printIndent(final int n) {
        for (int i = 0; i < n; ++i) {
            this.out.print("  ");
        }
        this.out.flush();
    }
    
    public void printOpenElement(final Element element) {
        this.printOpenElement(element, false);
    }
    
    public void printEmptyElement(final Element element) {
        this.printOpenElement(element, true);
    }
    
    public void printCloseElement(final Element element) {
        this.out.print("</");
        this.out.print(element.getNodeName());
        this.out.print('>');
        this.out.flush();
    }
    
    public void printAttribute(final Attr attr) {
        final String nodeName = attr.getNodeName();
        final String nodeValue = attr.getNodeValue();
        this.out.print(nodeName);
        this.out.print('=');
        this.out.print('\"');
        this.out.print(this.normalize(nodeValue));
        this.out.print('\"');
    }
    
    public void printText(final Text text) {
        this.out.print(this.normalize(text.getNodeValue()));
    }
    
    public void printElementDecl(final Element element) {
        final String attribute = element.getAttribute("name");
        final Element firstChildElement = XUtil.getFirstChildElement(element, "archetype");
        this.out.print("<!ELEMENT ");
        this.out.print(attribute);
        this.out.print(' ');
        this.printElementDeclContentModel(firstChildElement);
        this.out.print('>');
        this.out.println();
        this.out.flush();
        final Element firstChildElement2 = XUtil.getFirstChildElement(element, "archetype");
        if (firstChildElement2 != null) {
            for (Element element2 = XUtil.getFirstChildElement(firstChildElement2, "attribute"); element2 != null; element2 = XUtil.getNextSiblingElement(element2, "attribute")) {
                this.printAttributeDecl(attribute, element2);
            }
        }
    }
    
    public void printElementDeclContentModel(final Element element) {
        final String attribute = element.getAttribute("content");
        if (attribute.equals("empty") || attribute.equals("any")) {
            this.out.print(attribute.toUpperCase());
        }
        else if (attribute.equals("elemOnly")) {
            this.printElementDeclContentModelChildren(element);
        }
        else if (attribute.equals("mixed") || attribute.equals("textOnly")) {
            this.printElementDeclContentModelMixed(element);
        }
        this.out.flush();
    }
    
    public void printElementDeclContentModelMixed(final Element element) {
        Element element2 = XUtil.getFirstChildElement(element, "element");
        final boolean b = element2 == null;
        this.out.print("(#PCDATA");
        if (!b) {
            while (element2 != null) {
                final String attribute = element2.getAttribute("ref");
                this.out.print('|');
                this.out.print(attribute);
                element2 = XUtil.getNextSiblingElement(element2, "element");
            }
        }
        this.out.print(')');
        if (!b) {
            this.out.print('*');
        }
    }
    
    public void printElementDeclContentModelChildren(final Element element) {
        final boolean b = !this.containsMoreThanOneChildOfType(element, new String[] { "element", "group" }) && XUtil.getFirstChildElement(element, "element") != null;
        if (b) {
            this.out.print('(');
        }
        Element element2 = XUtil.getFirstChildElement(element, XGrammarWriter.CONTENT_MODEL_ELEMENT_NAMES);
        while (element2 != null) {
            this.printElementDeclContentModelChildren0(element2);
            element2 = XUtil.getNextSiblingElement(element2, XGrammarWriter.CONTENT_MODEL_ELEMENT_NAMES);
            if (element2 != null) {
                this.out.print(',');
            }
        }
        if (b) {
            this.out.print(')');
        }
    }
    
    public void printAttributeDecl(final String s, final Element element) {
        final String attribute = element.getAttribute("name");
        final String attribute2 = element.getAttribute("type");
        final Attr attributeNode = element.getAttributeNode("default");
        this.out.print("<!ATTLIST ");
        this.out.print(s);
        this.out.print(' ');
        this.out.print(attribute);
        this.out.print(' ');
        if (this.isBasicAttributeType(attribute2)) {
            final Element firstChildElement = XUtil.getFirstChildElement(element, "enumeration");
            if (attribute2.equals("NMTOKEN") && firstChildElement != null) {
                this.out.print('(');
                Element element2 = XUtil.getFirstChildElement(firstChildElement, "literal");
                while (element2 != null) {
                    element2.normalize();
                    final Node firstChildOfType = this.getFirstChildOfType(element2, (short)3);
                    this.out.print((firstChildOfType != null) ? firstChildOfType.getNodeValue() : "");
                    element2 = XUtil.getNextSiblingElement(element2, "literal");
                    if (element2 != null) {
                        this.out.print('|');
                    }
                }
                this.out.print(')');
            }
            else {
                this.out.print(attribute2);
            }
        }
        else {
            this.out.print("CDATA");
        }
        if (element.getAttribute("minOccurs").equals("1")) {
            this.out.print(" #REQUIRED");
        }
        else if (element.getAttribute("fixed").length() > 0) {
            final String attribute3 = element.getAttribute("fixed");
            this.out.print(" #FIXED ");
            this.out.print('\"');
            this.out.print(this.normalize(attribute3));
            this.out.print('\"');
        }
        else if (attributeNode == null) {
            this.out.print(" #IMPLIED");
        }
        if (attributeNode != null) {
            final String nodeValue = attributeNode.getNodeValue();
            this.out.print(' ');
            this.out.print('\"');
            this.out.print(this.normalize(nodeValue));
            this.out.print('\"');
        }
        this.out.print('>');
        this.out.println();
        this.out.flush();
    }
    
    public void printEntityDecl(final Element element) {
        final String nodeName = element.getNodeName();
        final String attribute = element.getAttribute("name");
        this.out.print("<!ENTITY ");
        this.out.print(attribute);
        this.out.print(' ');
        if (nodeName.equals("textEntity")) {
            element.normalize();
            final Node firstChildOfType = this.getFirstChildOfType(element, (short)3);
            final String s = (firstChildOfType != null) ? firstChildOfType.getNodeValue() : "";
            this.out.print('\"');
            this.out.print(this.normalize(s));
            this.out.print('\"');
        }
        else {
            final String attribute2 = element.getAttribute("public");
            final String attribute3 = element.getAttribute("system");
            if (attribute2.length() > 0) {
                this.out.print("PUBLIC ");
                this.out.print('\"');
                this.out.print(attribute2);
                this.out.print('\"');
                this.out.print(' ');
                this.out.print('\"');
                this.out.print(attribute3);
                this.out.print('\"');
            }
            else if (attribute3.length() > 0) {
                this.out.print("SYSTEM ");
                this.out.print('\"');
                this.out.print(attribute3);
                this.out.print('\"');
            }
            if (nodeName.equals("unparsedEntity")) {
                final String attribute4 = element.getAttribute("notation");
                this.out.print(" NDATA ");
                this.out.print(attribute4);
            }
        }
        this.out.print('>');
        this.out.println();
        this.out.flush();
    }
    
    public void printNotationDecl(final Element element) {
        final String attribute = element.getAttribute("name");
        final String attribute2 = element.getAttribute("public");
        final String attribute3 = element.getAttribute("system");
        this.out.print("<!NOTATION ");
        this.out.print(attribute);
        this.out.print(' ');
        if (attribute2.length() > 0) {
            this.out.print("PUBLIC ");
            this.out.print('\"');
            this.out.print(attribute2);
            this.out.print('\"');
            if (attribute3.length() > 0) {
                this.out.print(' ');
                this.out.print('\"');
                this.out.print(attribute3);
                this.out.print('\"');
            }
        }
        else if (attribute3.length() > 0) {
            this.out.print("SYSTEM ");
            this.out.print('\"');
            this.out.print(attribute3);
            this.out.print('\"');
        }
        this.out.print('>');
        this.out.println();
        this.out.flush();
    }
    
    protected void printOpenElement(final Element element, final boolean b) {
        this.out.print('<');
        this.out.print(element.getNodeName());
        final NamedNodeMap attributes = element.getAttributes();
        for (int length = attributes.getLength(), i = 0; i < length; ++i) {
            final Attr attr = (Attr)attributes.item(i);
            if (this.verbose || attr.getSpecified()) {
                this.out.print(' ');
                this.printAttribute(attr);
            }
        }
        if (b) {
            this.out.print('/');
        }
        this.out.print('>');
        this.out.flush();
    }
    
    protected boolean isEmpty(final Element element) {
        if (!element.hasChildNodes()) {
            return true;
        }
        for (Node node = element.getFirstChild(); node != null; node = node.getNextSibling()) {
            final short nodeType = node.getNodeType();
            if (nodeType == 1 || nodeType == 3) {
                return false;
            }
        }
        return true;
    }
    
    protected boolean isBasicAttributeType(final String s) {
        return s.equals("ENTITY") || s.equals("ENTITIES") || s.equals("ID") || s.equals("IDREF") || s.equals("IDREFS") || s.equals("NMTOKEN") || s.equals("NMTOKENS");
    }
    
    protected boolean isBasicOccurrenceCount(final String s, final String s2) {
        final int int1 = this.parseInt(s, 1);
        final int int2 = this.parseInt(s2, 1);
        return (int1 == 0 && int2 == 1) || (int1 == 1 && int2 == 1) || (int1 == 0 && int2 == -1) || (int1 == 1 && int2 == -1);
    }
    
    protected int parseInt(final String s, final int n) {
        if (s == null || s.length() == 0) {
            return n;
        }
        try {
            return Integer.parseInt(s);
        }
        catch (NumberFormatException ex) {
            return -1;
        }
    }
    
    protected boolean containsMoreThanOneChildOfType(final Element element, final String[] array) {
        int n = 0;
        for (Element element2 = XUtil.getFirstChildElement(element, array); element2 != null; element2 = XUtil.getNextSiblingElement(element2, array)) {
            ++n;
        }
        return n > 1;
    }
    
    protected Node getFirstChildOfType(final Node node, final short n) {
        if (node != null) {
            for (Node node2 = node.getFirstChild(); node2 != null; node2 = node2.getNextSibling()) {
                if (node2.getNodeType() == n) {
                    return node2;
                }
            }
        }
        return null;
    }
    
    protected Node getNextSiblingOfType(final Node node, final short n) {
        if (node != null) {
            for (Node node2 = node.getNextSibling(); node2 != null; node2 = node2.getNextSibling()) {
                if (node2.getNodeType() == n) {
                    return node2;
                }
            }
        }
        return null;
    }
    
    protected String normalize(final String s) {
        final StringBuffer sb = new StringBuffer();
        for (int n = (s != null) ? s.length() : 0, i = 0; i < n; ++i) {
            final char char1 = s.charAt(i);
            switch (char1) {
                case 60: {
                    sb.append("&lt;");
                    break;
                }
                case 62: {
                    sb.append("&gt;");
                    break;
                }
                case 38: {
                    sb.append("&amp;");
                    break;
                }
                case 34: {
                    sb.append("&quot;");
                    break;
                }
                default: {
                    sb.append(char1);
                    break;
                }
            }
        }
        return sb.toString();
    }
    
    private void init() {
        this.indent = 0;
        this.verbose = false;
        this.format = OutputFormat.XML_SCHEMA;
        this.encoding = "UTF8";
        this.canonical = false;
    }
    
    private void printElementDeclContentModelChildren0(final Element element) {
        if (element.getNodeName().equals("element")) {
            this.out.print(this.buildOccurrenceCountString(element.getAttribute("ref"), element.getAttribute("minOccurs"), element.getAttribute("maxOccurs")));
        }
        else {
            char c = ',';
            final String attribute = element.getAttribute("order");
            if (attribute.equals("choice")) {
                c = '|';
            }
            else if (attribute.equals("all")) {
                c = '&';
            }
            final StringWriter stringWriter = new StringWriter();
            final PrintWriter out = new PrintWriter(stringWriter);
            final PrintWriter out2 = this.out;
            (this.out = out).print('(');
            Element element2 = XUtil.getFirstChildElement(element, XGrammarWriter.CONTENT_MODEL_ELEMENT_NAMES);
            while (element2 != null) {
                this.printElementDeclContentModelChildren0(element2);
                element2 = XUtil.getNextSiblingElement(element2, XGrammarWriter.CONTENT_MODEL_ELEMENT_NAMES);
                if (element2 != null) {
                    this.out.print(c);
                }
            }
            this.out.print(')');
            String s = stringWriter.toString();
            if (c == '&') {
                if (s.startsWith("(") && s.endsWith(")")) {
                    s = s.substring(1, s.length() - 1);
                }
                s = this.expandAllModel(s);
            }
            (this.out = out2).print(this.buildOccurrenceCountString(s, element.getAttribute("minOccurs"), element.getAttribute("maxOccurs")));
        }
    }
    
    private String expandAllModel(final String s) {
        final Vector vector = new Vector<String>();
        final StringTokenizer stringTokenizer = new StringTokenizer(s, "&");
        while (stringTokenizer.hasMoreTokens()) {
            vector.addElement(stringTokenizer.nextToken());
        }
        final int size = vector.size();
        if (size > 1) {
            final String[] array = new String[size];
            for (int i = 0; i < array.length; ++i) {
                array[i] = vector.elementAt(i);
            }
            return "(" + this.buildAllModel(array, 0) + ')';
        }
        return s;
    }
    
    private String buildAllModel(final String[] array, final int n) {
        if (array.length - n == 2) {
            final StringBuffer sb = new StringBuffer();
            sb.append(this.createSeq(array));
            this.swap(array, n, n + 1);
            sb.append('|');
            sb.append(this.createSeq(array));
            this.swap(array, n, n + 1);
            return sb.toString();
        }
        final String[] array2 = new String[array.length];
        final StringBuffer sb2 = new StringBuffer();
        for (int i = n; i < array.length; ++i) {
            System.arraycopy(array, 0, array2, 0, array.length);
            this.shift(array2, n, i);
            sb2.append(this.buildAllModel(array2, n + 1));
            if (i < array.length - 1) {
                sb2.append('|');
            }
        }
        return sb2.toString();
    }
    
    private String createSeq(final String[] array) {
        final StringBuffer sb = new StringBuffer();
        sb.append('(');
        for (int i = 0; i < array.length; ++i) {
            sb.append(array[i]);
            if (i < array.length - 1) {
                sb.append(',');
            }
        }
        sb.append(')');
        return sb.toString();
    }
    
    private void shift(final String[] array, final int n, final int n2) {
        final String s = array[n2];
        for (int i = n2; i > n; --i) {
            array[i] = array[i - 1];
        }
        array[n] = s;
    }
    
    private void swap(final String[] array, final int n, final int n2) {
        final String s = array[n];
        array[n] = array[n2];
        array[n2] = s;
    }
    
    private String buildOccurrenceCountString(final String s, final String s2, final String s3) {
        final int int1 = this.parseInt(s2, 0);
        int int2 = this.parseInt(s3, 1);
        boolean b = true;
        if (int2 == -1) {
            int2 = int1;
            b = false;
        }
        final StringBuffer sb = new StringBuffer();
        if (int1 == 0 && int2 == 1 && b) {
            sb.append(s);
            sb.append('?');
        }
        else if (int1 == 0 && int2 == 0 && !b) {
            sb.append(s);
            sb.append('*');
        }
        else if (int1 == 1 && int2 == 1 && !b) {
            sb.append(s);
            sb.append('+');
        }
        else if (int1 == 1 && int2 == 1 && b) {
            sb.append(s);
        }
        else {
            sb.append('(');
            for (int i = 0; i < int1; ++i) {
                sb.append(s);
                if (i < int1 - 1) {
                    sb.append(',');
                }
            }
            if (int2 > int1) {
                for (int j = int1; j < int2; ++j) {
                    sb.append(',');
                    sb.append(s);
                    sb.append('?');
                }
            }
            if (!b) {
                sb.append(',');
                sb.append(s);
                sb.append('*');
            }
            sb.append(')');
        }
        return sb.toString();
    }
    
    static {
        DEFAULT_OUTPUT_FORMAT = OutputFormat.XML_SCHEMA;
        CONTENT_MODEL_ELEMENT_NAMES = new String[] { "element", "group" };
    }
    
    public static final class OutputFormat
    {
        public static final OutputFormat DTD;
        public static final OutputFormat XML_SCHEMA;
        private int value;
        
        private OutputFormat(final int value) {
            this.value = value;
        }
        
        public int getValue() {
            return this.value;
        }
        
        public int hashCode() {
            return this.value;
        }
        
        public boolean equals(final Object o) {
            return o != null && this.getClass() == o.getClass() && this.value == ((OutputFormat)o).getValue();
        }
        
        public String toString() {
            if (this == OutputFormat.DTD) {
                return "DTD";
            }
            if (this == OutputFormat.XML_SCHEMA) {
                return "XML SCHEMA";
            }
            return "???";
        }
        
        static {
            DTD = new OutputFormat(0);
            XML_SCHEMA = new OutputFormat(1);
        }
    }
}
