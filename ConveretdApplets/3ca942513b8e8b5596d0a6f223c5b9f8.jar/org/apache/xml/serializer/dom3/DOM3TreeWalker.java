// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.serializer.dom3;

import java.util.Enumeration;
import org.w3c.dom.Entity;
import org.w3c.dom.NodeList;
import org.apache.xml.serializer.utils.XML11Char;
import org.apache.xml.serializer.utils.XMLChar;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.DOMError;
import org.apache.xml.serializer.utils.Utils;
import org.w3c.dom.Attr;
import java.io.Writer;
import java.io.IOException;
import org.w3c.dom.EntityReference;
import org.w3c.dom.CDATASection;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Element;
import org.w3c.dom.Comment;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import java.io.File;
import org.xml.sax.Locator;
import java.util.Hashtable;
import org.apache.xml.serializer.NamespaceMappings;
import java.util.Properties;
import org.xml.sax.ext.LexicalHandler;
import org.w3c.dom.ls.LSSerializerFilter;
import org.w3c.dom.DOMErrorHandler;
import org.xml.sax.helpers.LocatorImpl;
import org.apache.xml.serializer.utils.DOM2Helper;
import org.apache.xml.serializer.SerializationHandler;

final class DOM3TreeWalker
{
    private SerializationHandler fSerializer;
    protected DOM2Helper fDOM2Helper;
    private LocatorImpl fLocator;
    private DOMErrorHandler fErrorHandler;
    private LSSerializerFilter fFilter;
    private LexicalHandler fLexicalHandler;
    private int fWhatToShowFilter;
    private char[] fNewLine;
    private Properties fDOMConfigProperties;
    private boolean fInEntityRef;
    private String fXMLVersion;
    private boolean fIsXMLVersion11;
    private boolean fIsLevel3DOM;
    private int fFeatures;
    private static final String XMLNS_URI = "http://www.w3.org/2000/xmlns/";
    private static final String XMLNS_PREFIX = "xmlns";
    private static final String XML_URI = "http://www.w3.org/XML/1998/namespace";
    private static final String XML_PREFIX = "xml";
    private NamespaceMappings fNSBinder;
    private int fElementDepth;
    private static final int CANONICAL = 1;
    private static final int CDATA = 2;
    private static final int CHARNORMALIZE = 4;
    private static final int COMMENTS = 8;
    private static final int DTNORMALIZE = 16;
    private static final int ELEM_CONTENT_WHITESPACE = 32;
    private static final int ENTITIES = 64;
    private static final int INFOSET = 128;
    private static final int NAMESPACES = 256;
    private static final int NAMESPACEDECLS = 512;
    private static final int NORMALIZECHARS = 1024;
    private static final int SPLITCDATA = 2048;
    private static final int VALIDATE = 4096;
    private static final int SCHEMAVALIDATE = 8192;
    private static final int WELLFORMED = 16384;
    private static final int DISCARDDEFAULT = 32768;
    private static final int PRETTY_PRINT = 65536;
    private static final int IGNORE_CHAR_DENORMALIZE = 131072;
    private static final int XMLDECL = 262144;
    boolean nextIsRaw;
    private static final Hashtable s_propKeys;
    
    public DOM3TreeWalker(final SerializationHandler serialHandler, final DOM2Helper dh, final String systemId) {
        this.fSerializer = null;
        this.fDOM2Helper = null;
        this.fLocator = new LocatorImpl();
        this.fErrorHandler = null;
        this.fFilter = null;
        this.fLexicalHandler = null;
        this.fNewLine = null;
        this.fDOMConfigProperties = null;
        this.fInEntityRef = false;
        this.fXMLVersion = null;
        this.fIsXMLVersion11 = false;
        this.fIsLevel3DOM = false;
        this.fFeatures = 0;
        this.fElementDepth = 0;
        this.nextIsRaw = false;
        this.fSerializer = serialHandler;
        this.fNSBinder = this.fSerializer.getNamespaceMappings();
        this.fDOMConfigProperties = this.fSerializer.getOutputFormat();
        this.fSerializer.setDocumentLocator(this.fLocator);
        this.initProperties(this.fDOMConfigProperties);
        if (systemId != null) {
            this.fLocator.setSystemId(systemId);
        }
        else {
            try {
                this.fLocator.setSystemId(System.getProperty("user.dir") + File.separator + "dummy.xsl");
            }
            catch (SecurityException ex) {}
        }
        this.fDOM2Helper = dh;
    }
    
    public DOM3TreeWalker(final SerializationHandler serialHandler, final DOM2Helper dh) {
        this.fSerializer = null;
        this.fDOM2Helper = null;
        this.fLocator = new LocatorImpl();
        this.fErrorHandler = null;
        this.fFilter = null;
        this.fLexicalHandler = null;
        this.fNewLine = null;
        this.fDOMConfigProperties = null;
        this.fInEntityRef = false;
        this.fXMLVersion = null;
        this.fIsXMLVersion11 = false;
        this.fIsLevel3DOM = false;
        this.fFeatures = 0;
        this.fElementDepth = 0;
        this.nextIsRaw = false;
        this.fSerializer = serialHandler;
        this.fNSBinder = this.fSerializer.getNamespaceMappings();
        this.fDOMConfigProperties = this.fSerializer.getOutputFormat();
        this.fSerializer.setDocumentLocator(this.fLocator);
        this.initProperties(this.fDOMConfigProperties);
        try {
            this.fLocator.setSystemId(System.getProperty("user.dir") + File.separator + "dummy.xsl");
        }
        catch (SecurityException ex) {}
        this.fDOM2Helper = dh;
    }
    
    public DOM3TreeWalker(final SerializationHandler serialHandler) {
        this.fSerializer = null;
        this.fDOM2Helper = null;
        this.fLocator = new LocatorImpl();
        this.fErrorHandler = null;
        this.fFilter = null;
        this.fLexicalHandler = null;
        this.fNewLine = null;
        this.fDOMConfigProperties = null;
        this.fInEntityRef = false;
        this.fXMLVersion = null;
        this.fIsXMLVersion11 = false;
        this.fIsLevel3DOM = false;
        this.fFeatures = 0;
        this.fElementDepth = 0;
        this.nextIsRaw = false;
        this.fSerializer = serialHandler;
        this.fDOMConfigProperties = this.fSerializer.getOutputFormat();
        this.fSerializer.setDocumentLocator(this.fLocator);
        this.initProperties(this.fDOMConfigProperties);
        try {
            this.fLocator.setSystemId(System.getProperty("user.dir") + File.separator + "dummy.xsl");
        }
        catch (SecurityException ex) {}
        this.fDOM2Helper = new DOM2Helper();
    }
    
    public DOM3TreeWalker(final SerializationHandler serialHandler, final DOMErrorHandler errHandler, final LSSerializerFilter filter, final char[] newLine) {
        this.fSerializer = null;
        this.fDOM2Helper = null;
        this.fLocator = new LocatorImpl();
        this.fErrorHandler = null;
        this.fFilter = null;
        this.fLexicalHandler = null;
        this.fNewLine = null;
        this.fDOMConfigProperties = null;
        this.fInEntityRef = false;
        this.fXMLVersion = null;
        this.fIsXMLVersion11 = false;
        this.fIsLevel3DOM = false;
        this.fFeatures = 0;
        this.fElementDepth = 0;
        this.nextIsRaw = false;
        this.fSerializer = serialHandler;
        this.fErrorHandler = errHandler;
        this.fFilter = filter;
        this.fNewLine = newLine;
        this.fNSBinder = this.fSerializer.getNamespaceMappings();
        this.fDOMConfigProperties = this.fSerializer.getOutputFormat();
        this.fSerializer.setDocumentLocator(this.fLocator);
        this.initProperties(this.fDOMConfigProperties);
        try {
            this.fLocator.setSystemId(System.getProperty("user.dir") + File.separator + "dummy.xsl");
        }
        catch (SecurityException ex) {}
        this.fDOM2Helper = new DOM2Helper();
    }
    
    public void traverse(Node pos) throws SAXException {
        this.fSerializer.startDocument();
        if (pos.getNodeType() != 9) {
            final Document ownerDoc = pos.getOwnerDocument();
            if (ownerDoc != null && ownerDoc.getImplementation().hasFeature("Core", "3.0")) {
                this.fIsLevel3DOM = true;
            }
        }
        else if (((Document)pos).getImplementation().hasFeature("Core", "3.0")) {
            this.fIsLevel3DOM = true;
        }
        if (this.fSerializer instanceof LexicalHandler) {
            this.fLexicalHandler = this.fSerializer;
        }
        if (this.fFilter != null) {
            this.fWhatToShowFilter = this.fFilter.getWhatToShow();
        }
        final Node top = pos;
        while (null != pos) {
            this.startNode(pos);
            Node nextNode = null;
            nextNode = pos.getFirstChild();
            while (null == nextNode) {
                this.endNode(pos);
                if (top.equals(pos)) {
                    break;
                }
                nextNode = pos.getNextSibling();
                if (null != nextNode) {
                    continue;
                }
                pos = pos.getParentNode();
                if (null == pos || top.equals(pos)) {
                    if (null != pos) {
                        this.endNode(pos);
                    }
                    nextNode = null;
                    break;
                }
            }
            pos = nextNode;
        }
        this.fSerializer.endDocument();
    }
    
    public void traverse(Node pos, final Node top) throws SAXException {
        this.fSerializer.startDocument();
        if (pos.getNodeType() != 9) {
            final Document ownerDoc = pos.getOwnerDocument();
            if (ownerDoc != null && ownerDoc.getImplementation().hasFeature("Core", "3.0")) {
                this.fIsLevel3DOM = true;
            }
        }
        else if (((Document)pos).getImplementation().hasFeature("Core", "3.0")) {
            this.fIsLevel3DOM = true;
        }
        if (this.fSerializer instanceof LexicalHandler) {
            this.fLexicalHandler = this.fSerializer;
        }
        if (this.fFilter != null) {
            this.fWhatToShowFilter = this.fFilter.getWhatToShow();
        }
        while (null != pos) {
            this.startNode(pos);
            Node nextNode = null;
            nextNode = pos.getFirstChild();
            while (null == nextNode) {
                this.endNode(pos);
                if (null != top && top.equals(pos)) {
                    break;
                }
                nextNode = pos.getNextSibling();
                if (null != nextNode) {
                    continue;
                }
                pos = pos.getParentNode();
                if (null == pos || (null != top && top.equals(pos))) {
                    nextNode = null;
                    break;
                }
            }
            pos = nextNode;
        }
        this.fSerializer.endDocument();
    }
    
    private final void dispatachChars(final Node node) throws SAXException {
        if (this.fSerializer != null) {
            this.fSerializer.characters(node);
        }
        else {
            final String data = ((Text)node).getData();
            this.fSerializer.characters(data.toCharArray(), 0, data.length());
        }
    }
    
    protected void startNode(final Node node) throws SAXException {
        if (node instanceof Locator) {
            final Locator loc = (Locator)node;
            this.fLocator.setColumnNumber(loc.getColumnNumber());
            this.fLocator.setLineNumber(loc.getLineNumber());
            this.fLocator.setPublicId(loc.getPublicId());
            this.fLocator.setSystemId(loc.getSystemId());
        }
        else {
            this.fLocator.setColumnNumber(0);
            this.fLocator.setLineNumber(0);
        }
        switch (node.getNodeType()) {
            case 10: {
                this.serializeDocType((DocumentType)node, true);
                break;
            }
            case 8: {
                this.serializeComment((Comment)node);
            }
            case 11: {}
            case 1: {
                this.serializeElement((Element)node, true);
                break;
            }
            case 7: {
                this.serializePI((ProcessingInstruction)node);
                break;
            }
            case 4: {
                this.serializeCDATASection((CDATASection)node);
                break;
            }
            case 3: {
                this.serializeText((Text)node);
                break;
            }
            case 5: {
                this.serializeEntityReference((EntityReference)node, true);
                break;
            }
        }
    }
    
    protected void endNode(final Node node) throws SAXException {
        switch (node.getNodeType()) {
            case 10: {
                this.serializeDocType((DocumentType)node, false);
                break;
            }
            case 1: {
                this.serializeElement((Element)node, false);
            }
            case 5: {
                this.serializeEntityReference((EntityReference)node, false);
                break;
            }
        }
    }
    
    protected boolean applyFilter(final Node node, final int nodeType) {
        if (this.fFilter != null && (this.fWhatToShowFilter & nodeType) != 0x0) {
            final short code = this.fFilter.acceptNode(node);
            switch (code) {
                case 2:
                case 3: {
                    return false;
                }
            }
        }
        return true;
    }
    
    protected void serializeDocType(final DocumentType node, final boolean bStart) throws SAXException {
        if (bStart) {
            if (this.fLexicalHandler != null) {
                if (!this.applyFilter(node, 512)) {
                    return;
                }
                this.fLexicalHandler.startDTD(node.getNodeName(), node.getPublicId(), node.getSystemId());
            }
        }
        else if (this.fLexicalHandler != null) {
            if (!this.applyFilter(node, 256)) {
                return;
            }
            this.fLexicalHandler.endDTD();
            try {
                final Writer writer = this.fSerializer.getWriter();
                final String internalSubset = node.getInternalSubset();
                if (internalSubset != null || !"".equals(internalSubset)) {
                    writer.write("[ ");
                    writer.write(new String(this.fNewLine) + internalSubset + "]");
                }
                writer.write(">" + new String(this.fNewLine));
            }
            catch (IOException e) {
                throw new SAXException("Error writing internal DTD subset", e);
            }
        }
    }
    
    protected void serializeComment(final Comment node) throws SAXException {
        if ((this.fFeatures & 0x8) != 0x0) {
            final String data = node.getData();
            if ((this.fFeatures & 0x4000) != 0x0) {
                this.isCommentWellFormed(data);
            }
            if (this.fLexicalHandler != null) {
                if (!this.applyFilter(node, 128)) {
                    return;
                }
                this.fLexicalHandler.comment(data.toCharArray(), 0, data.length());
            }
        }
    }
    
    protected void serializeElement(final Element node, final boolean bStart) throws SAXException {
        if (bStart) {
            ++this.fElementDepth;
            String namespace = node.getNamespaceURI();
            if (null == namespace) {
                namespace = "";
            }
            if ((this.fFeatures & 0x4000) != 0x0) {
                this.isElementWellFormed(node);
            }
            if (!this.applyFilter(node, 1)) {
                return;
            }
            if ((this.fFeatures & 0x100) != 0x0 && (this.fFeatures & 0x200) == 0x0) {
                namespace = null;
            }
            this.fSerializer.startElement(namespace, node.getLocalName(), node.getNodeName());
            if ((this.fFeatures & 0x100) != 0x0) {
                this.recordLocalNSDecl(node);
                this.fixupElementNS(node);
            }
            this.serializeAttList(node);
        }
        else {
            --this.fElementDepth;
            String ns = node.getNamespaceURI();
            if (null == ns) {
                ns = "";
            }
            if (!this.applyFilter(node, 1)) {
                return;
            }
            if ((this.fFeatures & 0x100) != 0x0 && (this.fFeatures & 0x200) == 0x0) {
                ns = null;
            }
            this.fSerializer.endElement(ns, node.getLocalName(), node.getNodeName());
        }
    }
    
    protected void serializeAttList(final Element node) throws SAXException {
        final NamedNodeMap atts = node.getAttributes();
        for (int nAttrs = atts.getLength(), i = 0; i < nAttrs; ++i) {
            final Node attr = atts.item(i);
            final String localName = attr.getLocalName();
            String attrName = attr.getNodeName();
            String attrPrefix = attr.getPrefix();
            final String attrValue = attr.getNodeValue();
            String type = null;
            if (this.fIsLevel3DOM) {
                type = ((Attr)attr).getSchemaTypeInfo().getTypeName();
            }
            type = ((type == null) ? "CDATA" : type);
            final String attrNS = attr.getNamespaceURI();
            final boolean isSpecified = ((Attr)attr).getSpecified();
            boolean addAttr = true;
            boolean applyFilter = false;
            final boolean xmlnsAttr = attrName.equals("xmlns") || attrName.startsWith("xmlns:");
            if ((this.fFeatures & 0x4000) != 0x0) {
                this.isAttributeWellFormed(attr);
            }
            if ((this.fFeatures & 0x100) != 0x0 && !xmlnsAttr) {
                if (attrNS != null) {
                    attrPrefix = ((attrPrefix == null) ? "" : attrPrefix);
                    final String declAttrPrefix = this.fNSBinder.lookupPrefix(attrNS);
                    final String declAttrNS = this.fNSBinder.lookupNamespace(attrPrefix);
                    if ("".equals(attrPrefix) || "".equals(declAttrPrefix) || !attrNS.equals(declAttrNS)) {
                        if (declAttrPrefix != null && !"".equals(declAttrPrefix)) {
                            if ((attrPrefix = declAttrPrefix) != null) {
                                attrName = declAttrPrefix + ":" + localName;
                            }
                            else {
                                attrName = localName;
                            }
                        }
                        else if (attrPrefix != null && !"".equals(attrPrefix) && declAttrNS == null) {
                            if ((this.fFeatures & 0x200) != 0x0) {
                                this.fSerializer.addAttribute("http://www.w3.org/2000/xmlns/", attrPrefix, "xmlns:" + attrPrefix, "CDATA", attrNS);
                            }
                        }
                        else {
                            int counter;
                            for (counter = 1, attrPrefix = "NS" + counter++; this.fNSBinder.lookupNamespace(attrPrefix) != null; attrPrefix = "NS" + counter++) {}
                            attrName = attrPrefix + ":" + localName;
                            if ((this.fFeatures & 0x200) != 0x0) {
                                this.fSerializer.addAttribute("http://www.w3.org/2000/xmlns/", attrPrefix, "xmlns:" + attrPrefix, "CDATA", attrNS);
                            }
                        }
                    }
                }
                else if (localName == null) {
                    final String msg = Utils.messages.createMessage("ER_NULL_LOCAL_ELEMENT_NAME", new Object[] { attrName });
                    if (this.fErrorHandler != null) {
                        this.fErrorHandler.handleError(new DOMErrorImpl((short)2, msg, "ER_NULL_LOCAL_ELEMENT_NAME", null, null, null));
                    }
                }
            }
            if (((this.fFeatures & 0x8000) != 0x0 && isSpecified) || (this.fFeatures & 0x8000) == 0x0) {
                applyFilter = true;
            }
            else {
                addAttr = false;
            }
            if (applyFilter && this.fFilter != null && (this.fFilter.getWhatToShow() & 0x2) != 0x0 && !xmlnsAttr) {
                final short code = this.fFilter.acceptNode(attr);
                switch (code) {
                    case 2:
                    case 3: {
                        addAttr = false;
                        break;
                    }
                }
            }
            if (addAttr && xmlnsAttr) {
                if ((this.fFeatures & 0x200) != 0x0 && localName != null && !"".equals(localName)) {
                    this.fSerializer.addAttribute(attrNS, localName, attrName, type, attrValue);
                }
            }
            else if (addAttr && !xmlnsAttr) {
                if ((this.fFeatures & 0x200) != 0x0 && attrNS != null) {
                    this.fSerializer.addAttribute(attrNS, localName, attrName, type, attrValue);
                }
                else {
                    this.fSerializer.addAttribute("", localName, attrName, type, attrValue);
                }
            }
            if (xmlnsAttr && (this.fFeatures & 0x200) != 0x0) {
                final int index;
                final String prefix = ((index = attrName.indexOf(":")) < 0) ? "" : attrName.substring(index + 1);
                if (!"".equals(prefix)) {
                    this.fSerializer.namespaceAfterStartElement(prefix, attrValue);
                }
            }
        }
    }
    
    protected void serializePI(final ProcessingInstruction node) throws SAXException {
        final String name = node.getNodeName();
        if ((this.fFeatures & 0x4000) != 0x0) {
            this.isPIWellFormed(node);
        }
        if (!this.applyFilter(node, 64)) {
            return;
        }
        if (name.equals("xslt-next-is-raw")) {
            this.nextIsRaw = true;
        }
        else {
            this.fSerializer.processingInstruction(name, node.getData());
        }
    }
    
    protected void serializeCDATASection(final CDATASection node) throws SAXException {
        if ((this.fFeatures & 0x4000) != 0x0) {
            this.isCDATASectionWellFormed(node);
        }
        if ((this.fFeatures & 0x2) != 0x0) {
            final boolean isLexH = this.fSerializer instanceof LexicalHandler;
            if (!this.applyFilter(node, 8)) {
                return;
            }
            final String nodeValue = node.getNodeValue();
            final int endIndex = nodeValue.indexOf("]]>");
            if ((this.fFeatures & 0x800) != 0x0) {
                if (endIndex >= 0) {
                    final String relatedData = nodeValue.substring(0, endIndex + 2);
                    final String msg = Utils.messages.createMessage("cdata-sections-splitted", null);
                    if (this.fErrorHandler != null) {
                        this.fErrorHandler.handleError(new DOMErrorImpl((short)1, msg, "cdata-sections-splitted", null, relatedData, null));
                    }
                }
            }
            else if (endIndex >= 0) {
                final String relatedData = nodeValue.substring(0, endIndex + 2);
                final String msg = Utils.messages.createMessage("cdata-sections-splitted", null);
                if (this.fErrorHandler != null) {
                    this.fErrorHandler.handleError(new DOMErrorImpl((short)2, msg, "cdata-sections-splitted"));
                }
                return;
            }
            if (isLexH) {
                this.fLexicalHandler.startCDATA();
            }
            this.dispatachChars(node);
            if (isLexH) {
                this.fLexicalHandler.endCDATA();
            }
        }
        else {
            this.dispatachChars(node);
        }
    }
    
    protected void serializeText(final Text node) throws SAXException {
        if (this.nextIsRaw) {
            this.nextIsRaw = false;
            this.fSerializer.processingInstruction("javax.xml.transform.disable-output-escaping", "");
            this.dispatachChars(node);
            this.fSerializer.processingInstruction("javax.xml.transform.enable-output-escaping", "");
        }
        else {
            boolean bDispatch = false;
            if ((this.fFeatures & 0x4000) != 0x0) {
                this.isTextWellFormed(node);
            }
            boolean isElementContentWhitespace = false;
            if (this.fIsLevel3DOM) {
                isElementContentWhitespace = node.isElementContentWhitespace();
            }
            if (isElementContentWhitespace) {
                if ((this.fFeatures & 0x20) != 0x0) {
                    bDispatch = true;
                }
            }
            else {
                bDispatch = true;
            }
            if (!this.applyFilter(node, 4)) {
                return;
            }
            if (bDispatch) {
                this.dispatachChars(node);
            }
        }
    }
    
    protected void serializeEntityReference(final EntityReference node, final boolean bStart) throws SAXException {
        if (bStart) {
            if ((this.fFeatures & 0x40) != 0x0) {
                if ((this.fFeatures & 0x4000) != 0x0) {
                    this.isEntityReferneceWellFormed(node);
                }
                if ((this.fFeatures & 0x100) != 0x0) {
                    this.checkUnboundPrefixInEntRef(node);
                }
                if (this.fSerializer instanceof LexicalHandler) {
                    this.fSerializer.startEntity(node.getNodeName());
                    this.fSerializer.entityReference(node.getNodeName());
                }
            }
        }
        else if ((this.fFeatures & 0x40) != 0x0 && this.fSerializer instanceof LexicalHandler) {
            final LexicalHandler lh = this.fSerializer;
            lh.endEntity(node.getNodeName());
        }
    }
    
    protected boolean isXMLName(final String s, final boolean xml11Version) {
        if (s == null) {
            return false;
        }
        if (!xml11Version) {
            return XMLChar.isValidName(s);
        }
        return XML11Char.isXML11ValidName(s);
    }
    
    protected boolean isValidQName(final String prefix, final String local, final boolean xml11Version) {
        if (local == null) {
            return false;
        }
        boolean validNCName = false;
        if (!xml11Version) {
            validNCName = ((prefix == null || XMLChar.isValidNCName(prefix)) && XMLChar.isValidNCName(local));
        }
        else {
            validNCName = ((prefix == null || XML11Char.isXML11ValidNCName(prefix)) && XML11Char.isXML11ValidNCName(local));
        }
        return validNCName;
    }
    
    protected boolean isWFXMLChar(final String chardata, Character refInvalidChar) {
        if (chardata == null || chardata.length() == 0) {
            return true;
        }
        final char[] dataarray = chardata.toCharArray();
        final int datalength = dataarray.length;
        if (this.fIsXMLVersion11) {
            int i = 0;
            while (i < datalength) {
                if (XML11Char.isXML11Invalid(dataarray[i++])) {
                    final char ch = dataarray[i - 1];
                    if (XMLChar.isHighSurrogate(ch) && i < datalength) {
                        final char ch2 = dataarray[i++];
                        if (XMLChar.isLowSurrogate(ch2) && XMLChar.isSupplemental(XMLChar.supplemental(ch, ch2))) {
                            continue;
                        }
                    }
                    refInvalidChar = new Character(ch);
                    return false;
                }
            }
        }
        else {
            int i = 0;
            while (i < datalength) {
                if (XMLChar.isInvalid(dataarray[i++])) {
                    final char ch = dataarray[i - 1];
                    if (XMLChar.isHighSurrogate(ch) && i < datalength) {
                        final char ch2 = dataarray[i++];
                        if (XMLChar.isLowSurrogate(ch2) && XMLChar.isSupplemental(XMLChar.supplemental(ch, ch2))) {
                            continue;
                        }
                    }
                    refInvalidChar = new Character(ch);
                    return false;
                }
            }
        }
        return true;
    }
    
    protected Character isWFXMLChar(final String chardata) {
        if (chardata == null || chardata.length() == 0) {
            return null;
        }
        final char[] dataarray = chardata.toCharArray();
        final int datalength = dataarray.length;
        if (this.fIsXMLVersion11) {
            int i = 0;
            while (i < datalength) {
                if (XML11Char.isXML11Invalid(dataarray[i++])) {
                    final char ch = dataarray[i - 1];
                    if (XMLChar.isHighSurrogate(ch) && i < datalength) {
                        final char ch2 = dataarray[i++];
                        if (XMLChar.isLowSurrogate(ch2) && XMLChar.isSupplemental(XMLChar.supplemental(ch, ch2))) {
                            continue;
                        }
                    }
                    final Character refInvalidChar = new Character(ch);
                    return refInvalidChar;
                }
            }
        }
        else {
            int i = 0;
            while (i < datalength) {
                if (XMLChar.isInvalid(dataarray[i++])) {
                    final char ch = dataarray[i - 1];
                    if (XMLChar.isHighSurrogate(ch) && i < datalength) {
                        final char ch2 = dataarray[i++];
                        if (XMLChar.isLowSurrogate(ch2) && XMLChar.isSupplemental(XMLChar.supplemental(ch, ch2))) {
                            continue;
                        }
                    }
                    final Character refInvalidChar = new Character(ch);
                    return refInvalidChar;
                }
            }
        }
        return null;
    }
    
    protected void isCommentWellFormed(final String data) {
        if (data == null || data.length() == 0) {
            return;
        }
        final char[] dataarray = data.toCharArray();
        final int datalength = dataarray.length;
        if (this.fIsXMLVersion11) {
            int i = 0;
            while (i < datalength) {
                final char c = dataarray[i++];
                if (XML11Char.isXML11Invalid(c)) {
                    if (XMLChar.isHighSurrogate(c) && i < datalength) {
                        final char c2 = dataarray[i++];
                        if (XMLChar.isLowSurrogate(c2) && XMLChar.isSupplemental(XMLChar.supplemental(c, c2))) {
                            continue;
                        }
                    }
                    final String msg = Utils.messages.createMessage("ER_WF_INVALID_CHARACTER_IN_COMMENT", new Object[] { new Character(c) });
                    if (this.fErrorHandler == null) {
                        continue;
                    }
                    this.fErrorHandler.handleError(new DOMErrorImpl((short)3, msg, "wf-invalid-character", null, null, null));
                }
                else {
                    if (c != '-' || i >= datalength || dataarray[i] != '-') {
                        continue;
                    }
                    final String msg = Utils.messages.createMessage("ER_WF_DASH_IN_COMMENT", null);
                    if (this.fErrorHandler == null) {
                        continue;
                    }
                    this.fErrorHandler.handleError(new DOMErrorImpl((short)3, msg, "wf-invalid-character", null, null, null));
                }
            }
        }
        else {
            int i = 0;
            while (i < datalength) {
                final char c = dataarray[i++];
                if (XMLChar.isInvalid(c)) {
                    if (XMLChar.isHighSurrogate(c) && i < datalength) {
                        final char c2 = dataarray[i++];
                        if (XMLChar.isLowSurrogate(c2) && XMLChar.isSupplemental(XMLChar.supplemental(c, c2))) {
                            continue;
                        }
                    }
                    final String msg = Utils.messages.createMessage("ER_WF_INVALID_CHARACTER_IN_COMMENT", new Object[] { new Character(c) });
                    if (this.fErrorHandler == null) {
                        continue;
                    }
                    this.fErrorHandler.handleError(new DOMErrorImpl((short)3, msg, "wf-invalid-character", null, null, null));
                }
                else {
                    if (c != '-' || i >= datalength || dataarray[i] != '-') {
                        continue;
                    }
                    final String msg = Utils.messages.createMessage("ER_WF_DASH_IN_COMMENT", null);
                    if (this.fErrorHandler == null) {
                        continue;
                    }
                    this.fErrorHandler.handleError(new DOMErrorImpl((short)3, msg, "wf-invalid-character", null, null, null));
                }
            }
        }
    }
    
    protected void isElementWellFormed(final Node node) {
        boolean isNameWF = false;
        if ((this.fFeatures & 0x100) != 0x0) {
            isNameWF = this.isValidQName(node.getPrefix(), node.getLocalName(), this.fIsXMLVersion11);
        }
        else {
            isNameWF = this.isXMLName(node.getNodeName(), this.fIsXMLVersion11);
        }
        if (!isNameWF) {
            final String msg = Utils.messages.createMessage("wf-invalid-character-in-node-name", new Object[] { "Element", node.getNodeName() });
            if (this.fErrorHandler != null) {
                this.fErrorHandler.handleError(new DOMErrorImpl((short)3, msg, "wf-invalid-character-in-node-name", null, null, null));
            }
        }
    }
    
    protected void isAttributeWellFormed(final Node node) {
        boolean isNameWF = false;
        if ((this.fFeatures & 0x100) != 0x0) {
            isNameWF = this.isValidQName(node.getPrefix(), node.getLocalName(), this.fIsXMLVersion11);
        }
        else {
            isNameWF = this.isXMLName(node.getNodeName(), this.fIsXMLVersion11);
        }
        if (!isNameWF) {
            final String msg = Utils.messages.createMessage("wf-invalid-character-in-node-name", new Object[] { "Attr", node.getNodeName() });
            if (this.fErrorHandler != null) {
                this.fErrorHandler.handleError(new DOMErrorImpl((short)3, msg, "wf-invalid-character-in-node-name", null, null, null));
            }
        }
        final String value = node.getNodeValue();
        if (value.indexOf(60) >= 0) {
            final String msg2 = Utils.messages.createMessage("ER_WF_LT_IN_ATTVAL", new Object[] { ((Attr)node).getOwnerElement().getNodeName(), node.getNodeName() });
            if (this.fErrorHandler != null) {
                this.fErrorHandler.handleError(new DOMErrorImpl((short)3, msg2, "ER_WF_LT_IN_ATTVAL", null, null, null));
            }
        }
        final NodeList children = node.getChildNodes();
        for (int i = 0; i < children.getLength(); ++i) {
            final Node child = children.item(i);
            switch (child.getNodeType()) {
                case 3: {
                    this.isTextWellFormed((Text)child);
                    break;
                }
                case 5: {
                    this.isEntityReferneceWellFormed((EntityReference)child);
                    break;
                }
            }
        }
    }
    
    protected void isPIWellFormed(final ProcessingInstruction node) {
        if (!this.isXMLName(node.getNodeName(), this.fIsXMLVersion11)) {
            final String msg = Utils.messages.createMessage("wf-invalid-character-in-node-name", new Object[] { "ProcessingInstruction", node.getTarget() });
            if (this.fErrorHandler != null) {
                this.fErrorHandler.handleError(new DOMErrorImpl((short)3, msg, "wf-invalid-character-in-node-name", null, null, null));
            }
        }
        final Character invalidChar = this.isWFXMLChar(node.getData());
        if (invalidChar != null) {
            final String msg2 = Utils.messages.createMessage("ER_WF_INVALID_CHARACTER_IN_PI", new Object[] { Integer.toHexString(Character.getNumericValue(invalidChar)) });
            if (this.fErrorHandler != null) {
                this.fErrorHandler.handleError(new DOMErrorImpl((short)3, msg2, "wf-invalid-character", null, null, null));
            }
        }
    }
    
    protected void isCDATASectionWellFormed(final CDATASection node) {
        final Character invalidChar = this.isWFXMLChar(node.getData());
        if (invalidChar != null) {
            final String msg = Utils.messages.createMessage("ER_WF_INVALID_CHARACTER_IN_CDATA", new Object[] { Integer.toHexString(Character.getNumericValue(invalidChar)) });
            if (this.fErrorHandler != null) {
                this.fErrorHandler.handleError(new DOMErrorImpl((short)3, msg, "wf-invalid-character", null, null, null));
            }
        }
    }
    
    protected void isTextWellFormed(final Text node) {
        final Character invalidChar = this.isWFXMLChar(node.getData());
        if (invalidChar != null) {
            final String msg = Utils.messages.createMessage("ER_WF_INVALID_CHARACTER_IN_TEXT", new Object[] { Integer.toHexString(Character.getNumericValue(invalidChar)) });
            if (this.fErrorHandler != null) {
                this.fErrorHandler.handleError(new DOMErrorImpl((short)3, msg, "wf-invalid-character", null, null, null));
            }
        }
    }
    
    protected void isEntityReferneceWellFormed(final EntityReference node) {
        if (!this.isXMLName(node.getNodeName(), this.fIsXMLVersion11)) {
            final String msg = Utils.messages.createMessage("wf-invalid-character-in-node-name", new Object[] { "EntityReference", node.getNodeName() });
            if (this.fErrorHandler != null) {
                this.fErrorHandler.handleError(new DOMErrorImpl((short)3, msg, "wf-invalid-character-in-node-name", null, null, null));
            }
        }
        final Node parent = node.getParentNode();
        final DocumentType docType = node.getOwnerDocument().getDoctype();
        if (docType != null) {
            final NamedNodeMap entities = docType.getEntities();
            for (int i = 0; i < entities.getLength(); ++i) {
                final Entity ent = (Entity)entities.item(i);
                final String nodeName = (node.getNodeName() == null) ? "" : node.getNodeName();
                final String nodeNamespaceURI = (node.getNamespaceURI() == null) ? "" : node.getNamespaceURI();
                final String entName = (ent.getNodeName() == null) ? "" : ent.getNodeName();
                final String entNamespaceURI = (ent.getNamespaceURI() == null) ? "" : ent.getNamespaceURI();
                if (parent.getNodeType() == 1 && entNamespaceURI.equals(nodeNamespaceURI) && entName.equals(nodeName) && ent.getNotationName() != null) {
                    final String msg2 = Utils.messages.createMessage("ER_WF_REF_TO_UNPARSED_ENT", new Object[] { node.getNodeName() });
                    if (this.fErrorHandler != null) {
                        this.fErrorHandler.handleError(new DOMErrorImpl((short)3, msg2, "ER_WF_REF_TO_UNPARSED_ENT", null, null, null));
                    }
                }
                if (parent.getNodeType() == 2 && entNamespaceURI.equals(nodeNamespaceURI) && entName.equals(nodeName) && (ent.getPublicId() != null || ent.getSystemId() != null || ent.getNotationName() != null)) {
                    final String msg2 = Utils.messages.createMessage("ER_WF_REF_TO_EXTERNAL_ENT", new Object[] { node.getNodeName() });
                    if (this.fErrorHandler != null) {
                        this.fErrorHandler.handleError(new DOMErrorImpl((short)3, msg2, "ER_WF_REF_TO_EXTERNAL_ENT", null, null, null));
                    }
                }
            }
        }
    }
    
    protected void checkUnboundPrefixInEntRef(final Node node) {
        Node next;
        for (Node child = node.getFirstChild(); child != null; child = next) {
            next = child.getNextSibling();
            if (child.getNodeType() == 1) {
                final String prefix = child.getPrefix();
                if (prefix != null && this.fNSBinder.lookupNamespace(prefix) == null) {
                    final String msg = Utils.messages.createMessage("unbound-prefix-in-entity-reference", new Object[] { node.getNodeName(), child.getNodeName(), prefix });
                    if (this.fErrorHandler != null) {
                        this.fErrorHandler.handleError(new DOMErrorImpl((short)3, msg, "unbound-prefix-in-entity-reference", null, null, null));
                    }
                }
                final NamedNodeMap attrs = child.getAttributes();
                for (int i = 0; i < attrs.getLength(); ++i) {
                    final String attrPrefix = attrs.item(i).getPrefix();
                    if (attrPrefix != null && this.fNSBinder.lookupNamespace(attrPrefix) == null) {
                        final String msg2 = Utils.messages.createMessage("unbound-prefix-in-entity-reference", new Object[] { node.getNodeName(), child.getNodeName(), attrs.item(i) });
                        if (this.fErrorHandler != null) {
                            this.fErrorHandler.handleError(new DOMErrorImpl((short)3, msg2, "unbound-prefix-in-entity-reference", null, null, null));
                        }
                    }
                }
            }
            if (child.hasChildNodes()) {
                this.checkUnboundPrefixInEntRef(child);
            }
        }
    }
    
    protected void recordLocalNSDecl(final Node node) {
        final NamedNodeMap atts = ((Element)node).getAttributes();
        for (int length = atts.getLength(), i = 0; i < length; ++i) {
            final Node attr = atts.item(i);
            String localName = attr.getLocalName();
            String attrPrefix = attr.getPrefix();
            String attrValue = attr.getNodeValue();
            String attrNS = attr.getNamespaceURI();
            localName = ((localName == null || "xmlns".equals(localName)) ? "" : localName);
            attrPrefix = ((attrPrefix == null) ? "" : attrPrefix);
            attrValue = ((attrValue == null) ? "" : attrValue);
            attrNS = ((attrNS == null) ? "" : attrNS);
            if (attrNS != null && attrNS.equals("http://www.w3.org/2000/xmlns/")) {
                if (attrValue.equals("http://www.w3.org/2000/xmlns/")) {
                    final String msg = Utils.messages.createMessage("ER_NS_PREFIX_CANNOT_BE_BOUND", new Object[] { attrPrefix, "http://www.w3.org/2000/xmlns/" });
                    if (this.fErrorHandler != null) {
                        this.fErrorHandler.handleError(new DOMErrorImpl((short)2, msg, "ER_NS_PREFIX_CANNOT_BE_BOUND", null, null, null));
                    }
                }
                else {
                    this.fNSBinder.pushNamespace(localName, attrValue, this.fElementDepth);
                }
            }
        }
    }
    
    protected void fixupElementNS(final Node node) throws SAXException {
        final String namespaceURI = ((Element)node).getNamespaceURI();
        String prefix = ((Element)node).getPrefix();
        final String localName = ((Element)node).getLocalName();
        if (namespaceURI != null) {
            prefix = ((prefix == null) ? "" : prefix);
            final String inScopeNamespaceURI = this.fNSBinder.lookupNamespace(prefix);
            if (inScopeNamespaceURI == null || !inScopeNamespaceURI.equals(namespaceURI)) {
                if ((this.fFeatures & 0x200) != 0x0) {
                    if ("".equals(prefix)) {
                        this.fSerializer.addAttribute("xmlns", namespaceURI);
                        ((Element)node).setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns", namespaceURI);
                    }
                    else {
                        this.fSerializer.addAttribute("http://www.w3.org/2000/xmlns/", prefix, "xmlns:" + prefix, "CDATA", namespaceURI);
                        ((Element)node).setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:" + prefix, namespaceURI);
                    }
                }
            }
        }
        else if (localName == null || "".equals(localName)) {
            final String msg = Utils.messages.createMessage("ER_NULL_LOCAL_ELEMENT_NAME", new Object[] { node.getNodeName() });
            if (this.fErrorHandler != null) {
                this.fErrorHandler.handleError(new DOMErrorImpl((short)2, msg, "ER_NULL_LOCAL_ELEMENT_NAME", null, null, null));
            }
        }
        else {
            final String uri = this.fNSBinder.lookupNamespace("");
            if (uri != null && uri.length() > 0 && (this.fFeatures & 0x200) != 0x0) {
                this.fSerializer.namespaceAfterStartElement("", "");
            }
        }
    }
    
    protected void fixupAttrNS(String attrName, final String attrLocalName, String attrPrefix, final String attrNS, final String attrValue, final String attrType) throws SAXException {
        if (attrNS != null) {
            attrPrefix = ((attrPrefix == null) ? "" : attrPrefix);
            final String declAttrPrefix = this.fNSBinder.lookupPrefix(attrNS);
            final String declAttrNS = this.fNSBinder.lookupNamespace(attrPrefix);
            if ("".equals(attrPrefix) || !attrNS.equals(declAttrNS)) {
                if (declAttrPrefix != null && !"".equals(declAttrPrefix)) {
                    attrPrefix = declAttrPrefix;
                }
                else if (attrPrefix == null || "".equals(attrPrefix) || declAttrNS != null) {
                    int counter;
                    for (counter = 1, attrPrefix = "NS" + counter++; this.fNSBinder.lookupNamespace(attrPrefix) != null; attrPrefix = "NS" + counter++) {}
                    attrName = attrPrefix + ":" + attrLocalName;
                    this.fSerializer.addAttribute("http://www.w3.org/2000/xmlns/", attrPrefix, "xmlns:" + attrPrefix, "CDATA", attrNS);
                }
            }
        }
        else if (attrLocalName == null) {
            final String msg = Utils.messages.createMessage("ER_NULL_LOCAL_ELEMENT_NAME", new Object[] { attrName });
            if (this.fErrorHandler != null) {
                this.fErrorHandler.handleError(new DOMErrorImpl((short)2, msg, "ER_NULL_LOCAL_ELEMENT_NAME", null, null, null));
            }
        }
    }
    
    protected void initProperties(final Properties properties) {
        final Enumeration keys = properties.keys();
        while (keys.hasMoreElements()) {
            final String key = keys.nextElement();
            final Object iobj = DOM3TreeWalker.s_propKeys.get(key);
            if (iobj != null) {
                if (iobj instanceof Integer) {
                    final int BITFLAG = (int)iobj;
                    if (properties.getProperty(key).endsWith("yes")) {
                        this.fFeatures |= BITFLAG;
                    }
                    else {
                        this.fFeatures &= ~BITFLAG;
                    }
                }
                else if ("{http://www.w3.org/TR/DOM-Level-3-LS}format-pretty-print".equals(key)) {
                    if (properties.getProperty(key).endsWith("yes")) {
                        this.fSerializer.setIndent(true);
                        this.fSerializer.setIndentAmount(3);
                    }
                    else {
                        this.fSerializer.setIndent(false);
                    }
                }
                else if ("omit-xml-declaration".equals(key)) {
                    if (properties.getProperty(key).endsWith("yes")) {
                        this.fSerializer.setOmitXMLDeclaration(true);
                    }
                    else {
                        this.fSerializer.setOmitXMLDeclaration(false);
                    }
                }
                else if ("{http://xml.apache.org/xerces-2j}xml-version".equals(key)) {
                    final String version = properties.getProperty(key);
                    if ("1.1".equals(version)) {
                        this.fIsXMLVersion11 = true;
                        this.fSerializer.setVersion(version);
                    }
                    else {
                        this.fSerializer.setVersion("1.0");
                    }
                }
                else {
                    if (!"encoding".equals(key)) {
                        continue;
                    }
                    final String encoding = properties.getProperty(key);
                    if (encoding == null) {
                        continue;
                    }
                    this.fSerializer.setEncoding(encoding);
                }
            }
        }
        if (this.fNewLine != null) {
            this.fSerializer.setNewLine(this.fNewLine);
        }
    }
    
    static {
        s_propKeys = new Hashtable();
        final int i = 2;
        Integer val = new Integer(i);
        DOM3TreeWalker.s_propKeys.put("{http://www.w3.org/TR/DOM-Level-3-LS}cdata-sections", val);
        final int i2 = 8;
        val = new Integer(i2);
        DOM3TreeWalker.s_propKeys.put("{http://www.w3.org/TR/DOM-Level-3-LS}comments", val);
        final int i3 = 32;
        val = new Integer(i3);
        DOM3TreeWalker.s_propKeys.put("{http://www.w3.org/TR/DOM-Level-3-LS}element-content-whitespace", val);
        final int i4 = 64;
        val = new Integer(i4);
        DOM3TreeWalker.s_propKeys.put("{http://www.w3.org/TR/DOM-Level-3-LS}entities", val);
        final int i5 = 256;
        val = new Integer(i5);
        DOM3TreeWalker.s_propKeys.put("{http://www.w3.org/TR/DOM-Level-3-LS}namespaces", val);
        final int i6 = 512;
        val = new Integer(i6);
        DOM3TreeWalker.s_propKeys.put("{http://www.w3.org/TR/DOM-Level-3-LS}namespace-declarations", val);
        final int i7 = 2048;
        val = new Integer(i7);
        DOM3TreeWalker.s_propKeys.put("{http://www.w3.org/TR/DOM-Level-3-LS}split-cdata-sections", val);
        final int i8 = 16384;
        val = new Integer(i8);
        DOM3TreeWalker.s_propKeys.put("{http://www.w3.org/TR/DOM-Level-3-LS}well-formed", val);
        final int i9 = 32768;
        val = new Integer(i9);
        DOM3TreeWalker.s_propKeys.put("{http://www.w3.org/TR/DOM-Level-3-LS}discard-default-content", val);
        DOM3TreeWalker.s_propKeys.put("{http://www.w3.org/TR/DOM-Level-3-LS}format-pretty-print", "");
        DOM3TreeWalker.s_propKeys.put("omit-xml-declaration", "");
        DOM3TreeWalker.s_propKeys.put("{http://xml.apache.org/xerces-2j}xml-version", "");
        DOM3TreeWalker.s_propKeys.put("encoding", "");
    }
}
