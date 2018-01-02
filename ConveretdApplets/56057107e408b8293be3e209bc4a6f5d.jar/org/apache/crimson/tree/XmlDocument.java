// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.crimson.tree;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.EntityReference;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Comment;
import org.w3c.dom.Attr;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Text;
import org.apache.crimson.util.XmlNames;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.DOMException;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.io.OutputStream;
import org.w3c.dom.Node;
import org.xml.sax.XMLReader;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.helpers.XMLReaderFactory;
import java.io.InputStream;
import org.xml.sax.SAXException;
import java.io.IOException;
import org.xml.sax.InputSource;
import java.util.Locale;
import org.apache.crimson.util.MessageCatalog;

public class XmlDocument extends ParentNode implements DocumentEx
{
    static String eol;
    static final MessageCatalog catalog;
    private Locale locale;
    private String systemId;
    private ElementFactory factory;
    int mutationCount;
    boolean replaceRootElement;
    
    public XmlDocument() {
        this.locale = Locale.getDefault();
    }
    
    public static XmlDocument createXmlDocument(final String documentURI, final boolean doValidate) throws IOException, SAXException {
        return createXmlDocument(new InputSource(documentURI), doValidate);
    }
    
    public static XmlDocument createXmlDocument(final String documentURI) throws IOException, SAXException {
        return createXmlDocument(new InputSource(documentURI), false);
    }
    
    public static XmlDocument createXmlDocument(final InputStream in, final boolean doValidate) throws IOException, SAXException {
        return createXmlDocument(new InputSource(in), doValidate);
    }
    
    public static XmlDocument createXmlDocument(final InputSource in, final boolean doValidate) throws IOException, SAXException {
        final String defaultReader = "org.apache.crimson.parser.XMLReaderImpl";
        String prop;
        try {
            prop = System.getProperty("org.xml.sax.driver", defaultReader);
        }
        catch (SecurityException se) {
            prop = defaultReader;
        }
        final XMLReader xmlReader = XMLReaderFactory.createXMLReader(prop);
        final String namespaces = "http://xml.org/sax/features/namespaces";
        xmlReader.setFeature(namespaces, true);
        final String nsPrefixes = "http://xml.org/sax/features/namespace-prefixes";
        xmlReader.setFeature(nsPrefixes, true);
        final XmlDocumentBuilder builder = new XmlDocumentBuilder();
        xmlReader.setContentHandler(builder);
        final String lexHandler = "http://xml.org/sax/properties/lexical-handler";
        xmlReader.setProperty(lexHandler, builder);
        final String declHandler = "http://xml.org/sax/properties/declaration-handler";
        xmlReader.setProperty(declHandler, builder);
        xmlReader.setDTDHandler(builder);
        final String validation = "http://xml.org/sax/features/validation";
        xmlReader.setFeature(validation, doValidate);
        if (doValidate) {
            xmlReader.setErrorHandler(new DefaultHandler() {
                public void error(final SAXParseException e) throws SAXException {
                    throw e;
                }
            });
        }
        builder.setDisableNamespaces(true);
        xmlReader.parse(in);
        return builder.getDocument();
    }
    
    ElementNode getDocument() {
        int i = 0;
        while (true) {
            final Node n = this.item(i);
            if (n == null) {
                return null;
            }
            if (n instanceof ElementNode) {
                return (ElementNode)n;
            }
            ++i;
        }
    }
    
    public Locale getLocale() {
        return this.locale;
    }
    
    public void setLocale(Locale locale) {
        if (locale == null) {
            locale = Locale.getDefault();
        }
        this.locale = locale;
    }
    
    public Locale chooseLocale(final String[] languages) {
        final Locale l = XmlDocument.catalog.chooseLocale(languages);
        if (l != null) {
            this.setLocale(l);
        }
        return l;
    }
    
    public void write(final OutputStream out) throws IOException {
        final Writer writer = new OutputStreamWriter(out, "UTF8");
        this.write(writer, "UTF-8");
    }
    
    public void write(final Writer out) throws IOException {
        String encoding = null;
        if (out instanceof OutputStreamWriter) {
            encoding = java2std(((OutputStreamWriter)out).getEncoding());
        }
        this.write(out, encoding);
    }
    
    static String java2std(final String encodingName) {
        if (encodingName == null) {
            return null;
        }
        if (encodingName.startsWith("ISO8859_")) {
            return "ISO-8859-" + encodingName.substring(8);
        }
        if (encodingName.startsWith("8859_")) {
            return "ISO-8859-" + encodingName.substring(5);
        }
        if ("ASCII7".equalsIgnoreCase(encodingName) || "ASCII".equalsIgnoreCase(encodingName)) {
            return "US-ASCII";
        }
        if ("UTF8".equalsIgnoreCase(encodingName)) {
            return "UTF-8";
        }
        if (encodingName.startsWith("Unicode")) {
            return "UTF-16";
        }
        if ("SJIS".equalsIgnoreCase(encodingName)) {
            return "Shift_JIS";
        }
        if ("JIS".equalsIgnoreCase(encodingName)) {
            return "ISO-2022-JP";
        }
        if ("EUCJIS".equalsIgnoreCase(encodingName)) {
            return "EUC-JP";
        }
        return encodingName;
    }
    
    public void write(final Writer out, final String encoding) throws IOException {
        out.write("<?xml version=\"1.0\"");
        if (encoding != null) {
            out.write(" encoding=\"");
            out.write(encoding);
            out.write(34);
        }
        out.write("?>");
        out.write(XmlDocument.eol);
        out.write(XmlDocument.eol);
        this.writeChildrenXml(this.createWriteContext(out, 0));
        out.write(XmlDocument.eol);
        out.flush();
    }
    
    public XmlWriteContext createWriteContext(final Writer out) {
        return new ExtWriteContext(out);
    }
    
    public XmlWriteContext createWriteContext(final Writer out, final int level) {
        return new ExtWriteContext(out, level);
    }
    
    public void writeXml(final XmlWriteContext context) throws IOException {
        final Writer out = context.getWriter();
        String encoding = null;
        if (out instanceof OutputStreamWriter) {
            encoding = java2std(((OutputStreamWriter)out).getEncoding());
        }
        out.write("<?xml version=\"1.0\"");
        if (encoding != null) {
            out.write(" encoding=\"");
            out.write(encoding);
            out.write(34);
        }
        out.write("?>");
        out.write(XmlDocument.eol);
        out.write(XmlDocument.eol);
        this.writeChildrenXml(context);
    }
    
    public void writeChildrenXml(final XmlWriteContext context) throws IOException {
        final int length = this.getLength();
        final Writer out = context.getWriter();
        if (length == 0) {
            return;
        }
        for (int i = 0; i < length; ++i) {
            ((NodeBase)this.item(i)).writeXml(context);
            out.write(XmlDocument.eol);
        }
    }
    
    void checkChildType(final int type) throws DOMException {
        switch (type) {
            case 1:
            case 7:
            case 8:
            case 10: {}
            default: {
                throw new DomEx((short)3);
            }
        }
    }
    
    public final void setSystemId(final String uri) {
        this.systemId = uri;
    }
    
    public final String getSystemId() {
        return this.systemId;
    }
    
    public Node appendChild(final Node n) throws DOMException {
        if (n instanceof Element && this.getDocument() != null) {
            throw new DomEx((short)3);
        }
        if (n instanceof DocumentType && this.getDoctype() != null) {
            throw new DomEx((short)3);
        }
        return super.appendChild(n);
    }
    
    public Node insertBefore(final Node n, final Node refNode) throws DOMException {
        if (!this.replaceRootElement && n instanceof Element && this.getDocument() != null) {
            throw new DomEx((short)3);
        }
        if (!this.replaceRootElement && n instanceof DocumentType && this.getDoctype() != null) {
            throw new DomEx((short)3);
        }
        return super.insertBefore(n, refNode);
    }
    
    public Node replaceChild(final Node newChild, final Node refChild) throws DOMException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1         /* newChild */
        //     1: instanceof      Lorg/w3c/dom/DocumentFragment;
        //     4: ifeq            95
        //     7: iconst_0       
        //     8: istore_3        /* elemCount */
        //     9: iconst_0       
        //    10: istore          docCount
        //    12: aload_0         /* this */
        //    13: iconst_0       
        //    14: putfield        org/apache/crimson/tree/XmlDocument.replaceRootElement:Z
        //    17: aload_1         /* newChild */
        //    18: checkcast       Lorg/apache/crimson/tree/ParentNode;
        //    21: astore          frag
        //    23: iconst_0       
        //    24: istore          i
        //    26: goto            57
        //    29: aload           6
        //    31: instanceof      Lorg/w3c/dom/Element;
        //    34: ifeq            43
        //    37: iinc            elemCount, 1
        //    40: goto            54
        //    43: aload           6
        //    45: instanceof      Lorg/w3c/dom/DocumentType;
        //    48: ifeq            54
        //    51: iinc            docCount, 1
        //    54: iinc            i, 1
        //    57: aload           frag
        //    59: iload           i
        //    61: invokevirtual   org/apache/crimson/tree/ParentNode.item:(I)Lorg/w3c/dom/Node;
        //    64: dup            
        //    65: astore          temp
        //    67: ifnonnull       29
        //    70: iload_3         /* elemCount */
        //    71: iconst_1       
        //    72: if_icmpgt       81
        //    75: iload           docCount
        //    77: iconst_1       
        //    78: if_icmple       90
        //    81: new             Lorg/apache/crimson/tree/DomEx;
        //    84: dup            
        //    85: iconst_3       
        //    86: invokespecial   org/apache/crimson/tree/DomEx.<init>:(S)V
        //    89: athrow         
        //    90: aload_0         /* this */
        //    91: iconst_1       
        //    92: putfield        org/apache/crimson/tree/XmlDocument.replaceRootElement:Z
        //    95: aload_0         /* this */
        //    96: aload_1         /* newChild */
        //    97: aload_2         /* refChild */
        //    98: invokespecial   org/apache/crimson/tree/ParentNode.replaceChild:(Lorg/w3c/dom/Node;Lorg/w3c/dom/Node;)Lorg/w3c/dom/Node;
        //   101: areturn        
        //    Exceptions:
        //  throws org.w3c.dom.DOMException
        //    LocalVariableTable:
        //  Start  Length  Slot  Name       Signature
        //  -----  ------  ----  ---------  -------------------------------------
        //  0      102     0     this       Lorg/apache/crimson/tree/XmlDocument;
        //  0      102     1     newChild   Lorg/w3c/dom/Node;
        //  0      102     2     refChild   Lorg/w3c/dom/Node;
        //  9      86      3     elemCount  I
        //  12     83      4     docCount   I
        //  23     72      5     frag       Lorg/apache/crimson/tree/ParentNode;
        //  67     28      6     temp       Lorg/w3c/dom/Node;
        //  26     69      7     i          I
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:2985)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public final short getNodeType() {
        return 9;
    }
    
    public final DocumentType getDoctype() {
        int i = 0;
        while (true) {
            final Node n = this.item(i);
            if (n == null) {
                return null;
            }
            if (n instanceof DocumentType) {
                return (DocumentType)n;
            }
            ++i;
        }
    }
    
    public DocumentType setDoctype(final String dtdPublicId, final String dtdSystemId, final String internalSubset) {
        Doctype retval = (Doctype)this.getDoctype();
        if (retval != null) {
            retval.setPrintInfo(dtdPublicId, dtdSystemId, internalSubset);
        }
        else {
            retval = new Doctype(dtdPublicId, dtdSystemId, internalSubset);
            retval.setOwnerDocument(this);
            this.insertBefore(retval, this.getFirstChild());
        }
        return retval;
    }
    
    public Element getDocumentElement() {
        return this.getDocument();
    }
    
    public final void setElementFactory(final ElementFactory factory) {
        this.factory = factory;
    }
    
    public final ElementFactory getElementFactory() {
        return this.factory;
    }
    
    public final Element createElement(final String tagName) throws DOMException {
        return this.createElementEx(tagName);
    }
    
    public Element createElementNS(final String namespaceURI, final String qualifiedName) throws DOMException {
        final ElementNode retval = new ElementNode(namespaceURI, qualifiedName);
        retval.setOwnerDocument(this);
        return retval;
    }
    
    public final ElementEx createElementEx(final String tagName) throws DOMException {
        if (!XmlNames.isName(tagName)) {
            throw new DomEx((short)5);
        }
        ElementNode retval;
        if (this.factory != null) {
            retval = (ElementNode)this.factory.createElementEx(tagName);
            retval.setTag(tagName);
        }
        else {
            retval = new ElementNode(tagName);
        }
        retval.setOwnerDocument(this);
        return retval;
    }
    
    public final ElementEx createElementEx(final String uri, final String tagName) throws DOMException {
        if (!XmlNames.isName(tagName)) {
            throw new DomEx((short)5);
        }
        ElementNode retval;
        if (this.factory != null) {
            retval = (ElementNode)this.factory.createElementEx(uri, tagName);
            retval.setTag(tagName);
        }
        else {
            retval = new ElementNode(tagName);
        }
        retval.setOwnerDocument(this);
        return retval;
    }
    
    public Text createTextNode(final String text) {
        final TextNode retval = new TextNode();
        retval.setOwnerDocument(this);
        if (text != null) {
            retval.setText(text.toCharArray());
        }
        return retval;
    }
    
    public CDATASection createCDATASection(final String text) {
        final CDataNode retval = new CDataNode();
        if (text != null) {
            retval.setText(text.toCharArray());
        }
        retval.setOwnerDocument(this);
        return retval;
    }
    
    TextNode newText(final char[] buf, final int offset, final int len) throws SAXException {
        final TextNode retval = (TextNode)this.createTextNode(null);
        final char[] data = new char[len];
        System.arraycopy(buf, offset, data, 0, len);
        retval.setText(data);
        return retval;
    }
    
    public ProcessingInstruction createProcessingInstruction(final String target, final String instructions) throws DOMException {
        if (!XmlNames.isName(target)) {
            throw new DomEx((short)5);
        }
        final PINode retval = new PINode(target, instructions);
        retval.setOwnerDocument(this);
        return retval;
    }
    
    public Attr createAttribute(final String name) throws DOMException {
        final AttributeNode retval = new AttributeNode(name, null, true, null);
        retval.setOwnerDocument(this);
        return retval;
    }
    
    public Attr createAttributeNS(final String namespaceURI, final String qualifiedName) throws DOMException {
        AttributeNode.checkArguments(namespaceURI, qualifiedName);
        final AttributeNode retval = new AttributeNode(namespaceURI, qualifiedName, null, true, null);
        retval.setOwnerDocument(this);
        return retval;
    }
    
    public Comment createComment(final String data) {
        final CommentNode retval = new CommentNode(data);
        retval.setOwnerDocument(this);
        return retval;
    }
    
    public Document getOwnerDoc() {
        return null;
    }
    
    public DOMImplementation getImplementation() {
        return DOMImplementationImpl.getDOMImplementation();
    }
    
    public DocumentFragment createDocumentFragment() {
        final DocFragNode retval = new DocFragNode();
        retval.setOwnerDocument(this);
        return retval;
    }
    
    public EntityReference createEntityReference(final String name) throws DOMException {
        if (!XmlNames.isName(name)) {
            throw new DomEx((short)5);
        }
        final EntityRefNode retval = new EntityRefNode(name);
        retval.setOwnerDocument(this);
        return retval;
    }
    
    public final String getNodeName() {
        return "#document";
    }
    
    public Node cloneNode(final boolean deep) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Lorg/apache/crimson/tree/XmlDocument;
        //     3: dup            
        //     4: invokespecial   org/apache/crimson/tree/XmlDocument.<init>:()V
        //     7: astore_2        /* retval */
        //     8: aload_2         /* retval */
        //     9: aload_0         /* this */
        //    10: getfield        org/apache/crimson/tree/XmlDocument.systemId:Ljava/lang/String;
        //    13: putfield        org/apache/crimson/tree/XmlDocument.systemId:Ljava/lang/String;
        //    16: iload_1         /* deep */
        //    17: ifeq            69
        //    20: iconst_0       
        //    21: istore          i
        //    23: goto            58
        //    26: aload_3        
        //    27: instanceof      Lorg/w3c/dom/DocumentType;
        //    30: ifeq            36
        //    33: goto            55
        //    36: aload_3        
        //    37: iconst_1       
        //    38: invokeinterface org/w3c/dom/Node.cloneNode:(Z)Lorg/w3c/dom/Node;
        //    43: astore_3        /* node */
        //    44: aload_2         /* retval */
        //    45: aload_3         /* node */
        //    46: invokevirtual   org/apache/crimson/tree/XmlDocument.changeNodeOwner:(Lorg/w3c/dom/Node;)V
        //    49: aload_2         /* retval */
        //    50: aload_3         /* node */
        //    51: invokevirtual   org/apache/crimson/tree/XmlDocument.appendChild:(Lorg/w3c/dom/Node;)Lorg/w3c/dom/Node;
        //    54: pop            
        //    55: iinc            i, 1
        //    58: aload_0         /* this */
        //    59: iload           i
        //    61: invokevirtual   org/apache/crimson/tree/ParentNode.item:(I)Lorg/w3c/dom/Node;
        //    64: dup            
        //    65: astore_3        /* node */
        //    66: ifnonnull       26
        //    69: aload_2         /* retval */
        //    70: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  -------------------------------------
        //  0      71      0     this    Lorg/apache/crimson/tree/XmlDocument;
        //  0      71      1     deep    Z
        //  8      63      2     retval  Lorg/apache/crimson/tree/XmlDocument;
        //  44     25      3     node    Lorg/w3c/dom/Node;
        //  23     46      4     i       I
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:2985)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public final void changeNodeOwner(final Node node) throws DOMException {
        if (node.getOwnerDocument() == this) {
            return;
        }
        if (!(node instanceof NodeBase)) {
            throw new DomEx((short)4);
        }
        switch (node.getNodeType()) {
            case 6:
            case 9:
            case 10:
            case 12: {
                throw new DomEx((short)3);
            }
            default: {
                if (node instanceof AttributeNode) {
                    final AttributeNode attr = (AttributeNode)node;
                    final Element scope = attr.getOwnerElement();
                    if (scope != null && scope.getOwnerDocument() != this) {
                        throw new DomEx((short)3);
                    }
                }
                NodeBase n = (NodeBase)node.getParentNode();
                if (n != null) {
                    n.removeChild(node);
                }
                TreeWalker walker;
                NamedNodeMap list;
                int length;
                int i;
                for (walker = new TreeWalker(node), n = (NodeBase)walker.getCurrent(); n != null; n = (NodeBase)walker.getNext()) {
                    n.setOwnerDocument(this);
                    if (n instanceof ElementNode) {
                        list = n.getAttributes();
                        for (length = list.getLength(), i = 0; i < length; ++i) {
                            this.changeNodeOwner(list.item(i));
                        }
                    }
                }
            }
        }
    }
    
    public Element getElementById(final String elementId) {
        return this.getElementExById(elementId);
    }
    
    public ElementEx getElementExById(final String id) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1         /* id */
        //     1: ifnonnull       18
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: aload_0         /* this */
        //     9: ldc             "XD-000"
        //    11: invokevirtual   org/apache/crimson/tree/NodeBase.getMessage:(Ljava/lang/String;)Ljava/lang/String;
        //    14: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    17: athrow         
        //    18: new             Lorg/apache/crimson/tree/TreeWalker;
        //    21: dup            
        //    22: aload_0         /* this */
        //    23: invokespecial   org/apache/crimson/tree/TreeWalker.<init>:(Lorg/w3c/dom/Node;)V
        //    26: astore_2        /* w */
        //    27: goto            67
        //    30: aload_3        
        //    31: invokeinterface org/apache/crimson/tree/ElementEx.getIdAttributeName:()Ljava/lang/String;
        //    36: astore          idAttr
        //    38: aload           idAttr
        //    40: ifnonnull       46
        //    43: goto            67
        //    46: aload_3        
        //    47: aload           idAttr
        //    49: invokeinterface org/w3c/dom/Element.getAttribute:(Ljava/lang/String;)Ljava/lang/String;
        //    54: astore          value
        //    56: aload           value
        //    58: aload_1         /* id */
        //    59: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    62: ifeq            67
        //    65: aload_3        
        //    66: areturn        
        //    67: aload_2         /* w */
        //    68: aconst_null    
        //    69: invokevirtual   org/apache/crimson/tree/TreeWalker.getNextElement:(Ljava/lang/String;)Lorg/w3c/dom/Element;
        //    72: checkcast       Lorg/apache/crimson/tree/ElementEx;
        //    75: dup            
        //    76: astore_3        /* element */
        //    77: ifnonnull       30
        //    80: aconst_null    
        //    81: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name     Signature
        //  -----  ------  ----  -------  -------------------------------------
        //  0      82      0     this     Lorg/apache/crimson/tree/XmlDocument;
        //  0      82      1     id       Ljava/lang/String;
        //  27     55      2     w        Lorg/apache/crimson/tree/TreeWalker;
        //  77     5       3     element  Lorg/apache/crimson/tree/ElementEx;
        //  38     29      4     idAttr   Ljava/lang/String;
        //  56     11      5     value    Ljava/lang/String;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:2985)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public Node importNode(final Node importedNode, final boolean deep) throws DOMException {
        Node node = null;
        switch (importedNode.getNodeType()) {
            case 2: {
                node = importedNode.cloneNode(true);
                break;
            }
            case 11: {
                if (deep) {
                    node = importedNode.cloneNode(true);
                    break;
                }
                node = new DocFragNode();
                break;
            }
            case 9:
            case 10: {
                throw new DomEx((short)9);
            }
            case 1: {
                node = importedNode.cloneNode(deep);
                break;
            }
            case 6: {
                node = importedNode.cloneNode(deep);
                break;
            }
            case 5: {
                node = importedNode.cloneNode(false);
                break;
            }
            default: {
                node = importedNode.cloneNode(false);
                break;
            }
        }
        final TreeWalker walker = new TreeWalker(node);
        for (NodeBase n = (NodeBase)walker.getCurrent(); n != null; n = (NodeBase)walker.getNext()) {
            n.setOwnerDocument(this);
            if (n instanceof ElementNode) {
                final NamedNodeMap list = n.getAttributes();
                for (int length = list.getLength(), i = 0; i < length; ++i) {
                    this.changeNodeOwner(list.item(i));
                }
            }
        }
        return node;
    }
    
    static {
        String temp;
        try {
            temp = System.getProperty("line.separator", "\n");
        }
        catch (SecurityException e) {
            temp = "\n";
        }
        XmlDocument.eol = temp;
        catalog = new Catalog();
    }
    
    static final class DocFragNode extends ParentNode implements DocumentFragment
    {
        void checkChildType(final int type) throws DOMException {
            switch (type) {
                case 1:
                case 3:
                case 4:
                case 5:
                case 7:
                case 8: {}
                default: {
                    throw new DomEx((short)3);
                }
            }
        }
        
        public void writeXml(final XmlWriteContext context) throws IOException {
            this.writeChildrenXml(context);
        }
        
        public Node getParentNode() {
            return null;
        }
        
        public void setParentNode(final Node p) {
            if (p != null) {
                throw new IllegalArgumentException();
            }
        }
        
        public short getNodeType() {
            return 11;
        }
        
        public String getNodeName() {
            return "#document-fragment";
        }
        
        public Node cloneNode(final boolean deep) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: new             Lorg/apache/crimson/tree/XmlDocument$DocFragNode;
            //     3: dup            
            //     4: invokespecial   org/apache/crimson/tree/XmlDocument$DocFragNode.<init>:()V
            //     7: astore_2        /* retval */
            //     8: aload_2         /* retval */
            //     9: aload_0         /* this */
            //    10: invokevirtual   org/apache/crimson/tree/NodeBase.getOwnerDocument:()Lorg/w3c/dom/Document;
            //    13: checkcast       Lorg/apache/crimson/tree/XmlDocument;
            //    16: invokevirtual   org/apache/crimson/tree/NodeBase.setOwnerDocument:(Lorg/apache/crimson/tree/XmlDocument;)V
            //    19: iload_1         /* deep */
            //    20: ifeq            57
            //    23: iconst_0       
            //    24: istore          i
            //    26: goto            46
            //    29: aload_3        
            //    30: iconst_1       
            //    31: invokeinterface org/w3c/dom/Node.cloneNode:(Z)Lorg/w3c/dom/Node;
            //    36: astore_3        /* node */
            //    37: aload_2         /* retval */
            //    38: aload_3         /* node */
            //    39: invokevirtual   org/apache/crimson/tree/ParentNode.appendChild:(Lorg/w3c/dom/Node;)Lorg/w3c/dom/Node;
            //    42: pop            
            //    43: iinc            i, 1
            //    46: aload_0         /* this */
            //    47: iload           i
            //    49: invokevirtual   org/apache/crimson/tree/ParentNode.item:(I)Lorg/w3c/dom/Node;
            //    52: dup            
            //    53: astore_3        /* node */
            //    54: ifnonnull       29
            //    57: aload_2         /* retval */
            //    58: areturn        
            //    LocalVariableTable:
            //  Start  Length  Slot  Name    Signature
            //  -----  ------  ----  ------  -------------------------------------------------
            //  0      59      0     this    Lorg/apache/crimson/tree/XmlDocument$DocFragNode;
            //  0      59      1     deep    Z
            //  8      51      2     retval  Lorg/apache/crimson/tree/XmlDocument$DocFragNode;
            //  37     20      3     node    Lorg/w3c/dom/Node;
            //  26     31      4     i       I
            // 
            // The error that occurred was:
            // 
            // java.lang.NullPointerException
            //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:2985)
            //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
            //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
            //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
            //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
            //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
    }
    
    static final class EntityRefNode extends ParentNode implements EntityReference
    {
        private String entity;
        
        EntityRefNode(final String name) {
            if (name == null) {
                throw new IllegalArgumentException(this.getMessage("XD-002"));
            }
            this.entity = name;
        }
        
        void checkChildType(final int type) throws DOMException {
            switch (type) {
                case 1:
                case 3:
                case 4:
                case 5:
                case 7:
                case 8: {}
                default: {
                    throw new DomEx((short)3);
                }
            }
        }
        
        public void writeXml(final XmlWriteContext context) throws IOException {
            if (!context.isEntityDeclared(this.entity)) {
                throw new IOException(this.getMessage("XD-003", new Object[] { this.entity }));
            }
            final Writer out = context.getWriter();
            out.write(38);
            out.write(this.entity);
            out.write(59);
        }
        
        public short getNodeType() {
            return 5;
        }
        
        public String getNodeName() {
            return this.entity;
        }
        
        public Node cloneNode(final boolean deep) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: new             Lorg/apache/crimson/tree/XmlDocument$EntityRefNode;
            //     3: dup            
            //     4: aload_0         /* this */
            //     5: getfield        org/apache/crimson/tree/XmlDocument$EntityRefNode.entity:Ljava/lang/String;
            //     8: invokespecial   org/apache/crimson/tree/XmlDocument$EntityRefNode.<init>:(Ljava/lang/String;)V
            //    11: astore_2        /* retval */
            //    12: aload_2         /* retval */
            //    13: aload_0         /* this */
            //    14: invokevirtual   org/apache/crimson/tree/NodeBase.getOwnerDocument:()Lorg/w3c/dom/Document;
            //    17: checkcast       Lorg/apache/crimson/tree/XmlDocument;
            //    20: invokevirtual   org/apache/crimson/tree/NodeBase.setOwnerDocument:(Lorg/apache/crimson/tree/XmlDocument;)V
            //    23: iload_1         /* deep */
            //    24: ifeq            61
            //    27: iconst_0       
            //    28: istore          i
            //    30: goto            50
            //    33: aload_3        
            //    34: iconst_1       
            //    35: invokeinterface org/w3c/dom/Node.cloneNode:(Z)Lorg/w3c/dom/Node;
            //    40: astore_3        /* node */
            //    41: aload_2         /* retval */
            //    42: aload_3         /* node */
            //    43: invokevirtual   org/apache/crimson/tree/ParentNode.appendChild:(Lorg/w3c/dom/Node;)Lorg/w3c/dom/Node;
            //    46: pop            
            //    47: iinc            i, 1
            //    50: aload_0         /* this */
            //    51: iload           i
            //    53: invokevirtual   org/apache/crimson/tree/ParentNode.item:(I)Lorg/w3c/dom/Node;
            //    56: dup            
            //    57: astore_3        /* node */
            //    58: ifnonnull       33
            //    61: aload_2         /* retval */
            //    62: areturn        
            //    LocalVariableTable:
            //  Start  Length  Slot  Name    Signature
            //  -----  ------  ----  ------  ---------------------------------------------------
            //  0      63      0     this    Lorg/apache/crimson/tree/XmlDocument$EntityRefNode;
            //  0      63      1     deep    Z
            //  12     51      2     retval  Lorg/apache/crimson/tree/XmlDocument$EntityRefNode;
            //  41     20      3     node    Lorg/w3c/dom/Node;
            //  30     31      4     i       I
            // 
            // The error that occurred was:
            // 
            // java.lang.NullPointerException
            //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:2985)
            //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
            //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
            //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
            //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
            //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
    }
    
    static class Catalog extends MessageCatalog
    {
        static /* synthetic */ Class class$org$apache$crimson$tree$XmlDocument$Catalog;
        
        Catalog() {
            super((Catalog.class$org$apache$crimson$tree$XmlDocument$Catalog == null) ? (Catalog.class$org$apache$crimson$tree$XmlDocument$Catalog = class$("org.apache.crimson.tree.XmlDocument$Catalog")) : Catalog.class$org$apache$crimson$tree$XmlDocument$Catalog);
        }
        
        static /* synthetic */ Class class$(final String x0) {
            try {
                return Class.forName(x0);
            }
            catch (ClassNotFoundException x) {
                throw new NoClassDefFoundError(x.getMessage());
            }
        }
    }
    
    class ExtWriteContext extends XmlWriteContext
    {
        ExtWriteContext(final Writer out) {
            super(out);
        }
        
        ExtWriteContext(final Writer out, final int level) {
            super(out, level);
        }
        
        public boolean isEntityDeclared(final String name) {
            if (super.isEntityDeclared(name)) {
                return true;
            }
            final DocumentType doctype = XmlDocument.this.getDoctype();
            return doctype != null && doctype.getEntities().getNamedItem(name) != null;
        }
    }
}
