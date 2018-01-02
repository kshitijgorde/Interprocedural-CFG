// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.dom;

import org.apache.xml.dtm.ref.EmptyIterator;
import org.apache.xml.dtm.ref.ExpandedNameTable;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Entity;
import java.util.Enumeration;
import org.apache.xalan.xsltc.DOM;
import org.apache.xml.serializer.ToXMLSAXHandler;
import org.xml.sax.ContentHandler;
import org.apache.xml.serializer.SerializationHandler;
import org.apache.xalan.xsltc.runtime.BasisLibrary;
import org.apache.xml.dtm.Axis;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import javax.xml.transform.dom.DOMSource;
import org.apache.xml.dtm.DTMManager;
import org.apache.xml.utils.XMLStringFactory;
import org.apache.xml.dtm.DTMWSFilter;
import javax.xml.transform.Source;
import org.apache.xml.dtm.ref.DTMAxisIterNodeList;
import org.apache.xml.dtm.ref.DTMDefaultBaseIterators;
import org.apache.xml.dtm.DTM;
import org.apache.xml.dtm.ref.DTMNodeProxy;
import org.apache.xalan.xsltc.StripFilter;
import org.apache.xalan.xsltc.TransletException;
import org.apache.xml.utils.SystemIDResolver;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Document;
import org.apache.xalan.xsltc.runtime.Hashtable;
import org.apache.xml.dtm.DTMAxisIterator;
import org.apache.xalan.xsltc.DOMEnhancedForDTM;
import org.apache.xml.dtm.ref.sax2dtm.SAX2DTM2;

public final class SAXImpl extends SAX2DTM2 implements DOMEnhancedForDTM, DOMBuilder
{
    private int _uriCount;
    private int _prefixCount;
    private int[] _xmlSpaceStack;
    private int _idx;
    private boolean _preserve;
    private static final String XML_STRING = "xml:";
    private static final String XML_PREFIX = "xml";
    private static final String XMLSPACE_STRING = "xml:space";
    private static final String PRESERVE_STRING = "preserve";
    private static final String XMLNS_PREFIX = "xmlns";
    private static final String XML_URI = "http://www.w3.org/XML/1998/namespace";
    private boolean _escaping;
    private boolean _disableEscaping;
    private int _textNodeToProcess;
    private static final String EMPTYSTRING = "";
    private static final DTMAxisIterator EMPTYITERATOR;
    private int _namesSize;
    private Hashtable _nsIndex;
    private int _size;
    private BitArray _dontEscape;
    private String _documentURI;
    private static int _documentURIIndex;
    private Document _document;
    private Hashtable _node2Ids;
    private boolean _hasDOMSource;
    private XSLTCDTMManager _dtmManager;
    private Node[] _nodes;
    private NodeList[] _nodeLists;
    private static final String XML_LANG_ATTRIBUTE = "http://www.w3.org/XML/1998/namespace:@lang";
    
    public void setDocumentURI(final String uri) {
        if (uri != null) {
            this.setDocumentBaseURI(SystemIDResolver.getAbsoluteURI(uri));
        }
    }
    
    public String getDocumentURI() {
        final String baseURI = this.getDocumentBaseURI();
        return (baseURI != null) ? baseURI : ("rtf" + SAXImpl._documentURIIndex++);
    }
    
    public String getDocumentURI(final int node) {
        return this.getDocumentURI();
    }
    
    public void setupMapping(final String[] names, final String[] urisArray, final int[] typesArray, final String[] namespaces) {
    }
    
    public String lookupNamespace(final int node, final String prefix) throws TransletException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Lorg/apache/xml/dtm/ref/sax2dtm/SAX2DTM2$AncestorIterator;
        //     3: dup            
        //     4: aload_0         /* this */
        //     5: invokespecial   org/apache/xml/dtm/ref/sax2dtm/SAX2DTM2$AncestorIterator.<init>:(Lorg/apache/xml/dtm/ref/sax2dtm/SAX2DTM2;)V
        //     8: astore          ancestors
        //    10: aload_0         /* this */
        //    11: iload_1         /* node */
        //    12: invokevirtual   org/apache/xalan/xsltc/dom/SAXImpl.isElement:(I)Z
        //    15: ifeq            24
        //    18: aload           ancestors
        //    20: invokevirtual   org/apache/xml/dtm/ref/DTMAxisIteratorBase.includeSelf:()Lorg/apache/xml/dtm/DTMAxisIterator;
        //    23: pop            
        //    24: aload           ancestors
        //    26: iload_1         /* node */
        //    27: invokevirtual   org/apache/xml/dtm/ref/sax2dtm/SAX2DTM2$AncestorIterator.setStartNode:(I)Lorg/apache/xml/dtm/DTMAxisIterator;
        //    30: pop            
        //    31: goto            86
        //    34: new             Lorg/apache/xml/dtm/ref/DTMDefaultBaseIterators$NamespaceIterator;
        //    37: dup            
        //    38: aload_0         /* this */
        //    39: invokespecial   org/apache/xml/dtm/ref/DTMDefaultBaseIterators$NamespaceIterator.<init>:(Lorg/apache/xml/dtm/ref/DTMDefaultBaseIterators;)V
        //    42: astore          namespaces
        //    44: aload           namespaces
        //    46: iload_3        
        //    47: invokevirtual   org/apache/xml/dtm/ref/DTMDefaultBaseIterators$NamespaceIterator.setStartNode:(I)Lorg/apache/xml/dtm/DTMAxisIterator;
        //    50: pop            
        //    51: goto            74
        //    54: aload_0         /* this */
        //    55: iload           4
        //    57: invokevirtual   org/apache/xml/dtm/ref/sax2dtm/SAX2DTM2.getLocalName:(I)Ljava/lang/String;
        //    60: aload_2         /* prefix */
        //    61: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    64: ifeq            74
        //    67: aload_0         /* this */
        //    68: iload           4
        //    70: invokevirtual   org/apache/xml/dtm/ref/sax2dtm/SAX2DTM2.getNodeValue:(I)Ljava/lang/String;
        //    73: areturn        
        //    74: aload           namespaces
        //    76: invokevirtual   org/apache/xml/dtm/ref/DTMDefaultBaseIterators$NamespaceIterator.next:()I
        //    79: dup            
        //    80: istore          nsnode
        //    82: iconst_m1      
        //    83: if_icmpne       54
        //    86: aload           ancestors
        //    88: invokevirtual   org/apache/xml/dtm/ref/sax2dtm/SAX2DTM2$AncestorIterator.next:()I
        //    91: dup            
        //    92: istore_3        /* anode */
        //    93: iconst_m1      
        //    94: if_icmpne       34
        //    97: ldc             "NAMESPACE_PREFIX_ERR"
        //    99: aload_2         /* prefix */
        //   100: invokestatic    org/apache/xalan/xsltc/runtime/BasisLibrary.runTimeError:(Ljava/lang/String;Ljava/lang/Object;)V
        //   103: aconst_null    
        //   104: areturn        
        //    Exceptions:
        //  throws org.apache.xalan.xsltc.TransletException
        //    LocalVariableTable:
        //  Start  Length  Slot  Name        Signature
        //  -----  ------  ----  ----------  ------------------------------------------------------------------
        //  0      105     0     this        Lorg/apache/xalan/xsltc/dom/SAXImpl;
        //  0      105     1     node        I
        //  0      105     2     prefix      Ljava/lang/String;
        //  93     12      3     anode       I
        //  82     23      4     nsnode      I
        //  10     95      5     ancestors   Lorg/apache/xml/dtm/ref/sax2dtm/SAX2DTM2$AncestorIterator;
        //  44     42      6     namespaces  Lorg/apache/xml/dtm/ref/DTMDefaultBaseIterators$NamespaceIterator;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public boolean isElement(final int node) {
        return this.getNodeType(node) == 1;
    }
    
    public boolean isAttribute(final int node) {
        return this.getNodeType(node) == 2;
    }
    
    public int getSize() {
        return this.getNumberOfNodes();
    }
    
    public void setFilter(final StripFilter filter) {
    }
    
    public boolean lessThan(final int node1, final int node2) {
        return node1 != -1 && (node2 == -1 || node1 < node2);
    }
    
    public Node makeNode(final int index) {
        if (this._nodes == null) {
            this._nodes = new Node[this._namesSize];
        }
        final int nodeID = this.makeNodeIdentity(index);
        if (nodeID < 0) {
            return null;
        }
        if (nodeID < this._nodes.length) {
            return (this._nodes[nodeID] != null) ? this._nodes[nodeID] : (this._nodes[nodeID] = new DTMNodeProxy(this, index));
        }
        return new DTMNodeProxy(this, index);
    }
    
    public Node makeNode(final DTMAxisIterator iter) {
        return this.makeNode(iter.next());
    }
    
    public NodeList makeNodeList(final int index) {
        if (this._nodeLists == null) {
            this._nodeLists = new NodeList[this._namesSize];
        }
        final int nodeID = this.makeNodeIdentity(index);
        if (nodeID < 0) {
            return null;
        }
        if (nodeID < this._nodeLists.length) {
            return (this._nodeLists[nodeID] != null) ? this._nodeLists[nodeID] : (this._nodeLists[nodeID] = new DTMAxisIterNodeList(this, new SingletonIterator(this, index)));
        }
        return new DTMAxisIterNodeList(this, new SingletonIterator(this, index));
    }
    
    public NodeList makeNodeList(final DTMAxisIterator iter) {
        return new DTMAxisIterNodeList(this, iter);
    }
    
    public DTMAxisIterator getNodeValueIterator(final DTMAxisIterator iterator, final int type, final String value, final boolean op) {
        return new NodeValueIterator(iterator, type, value, op);
    }
    
    public DTMAxisIterator orderNodes(final DTMAxisIterator source, final int node) {
        return new DupFilterIterator(source);
    }
    
    public DTMAxisIterator getIterator() {
        return new SingletonIterator(this, this.getDocument());
    }
    
    public int getNSType(final int node) {
        final String s = this.getNamespaceURI(node);
        if (s == null) {
            return 0;
        }
        final int eType = this.getIdForNamespace(s);
        return (int)this._nsIndex.get(new Integer(eType));
    }
    
    public int getNamespaceType(final int node) {
        return super.getNamespaceType(node);
    }
    
    private int[] setupMapping(final String[] names, final String[] uris, final int[] types, final int nNames) {
        final int[] result = new int[super.m_expandedNameTable.getSize()];
        for (int i = 0; i < nNames; ++i) {
            final int type = super.m_expandedNameTable.getExpandedTypeID(uris[i], names[i], types[i], false);
            result[type] = type;
        }
        return result;
    }
    
    public int getGeneralizedType(final String name) {
        return this.getGeneralizedType(name, true);
    }
    
    public int getGeneralizedType(final String name, final boolean searchOnly) {
        String ns = null;
        int index = -1;
        if ((index = name.lastIndexOf(":")) > -1) {
            ns = name.substring(0, index);
        }
        int lNameStartIdx = index + 1;
        int code;
        if (name.charAt(lNameStartIdx) == '@') {
            code = 2;
            ++lNameStartIdx;
        }
        else {
            code = 1;
        }
        final String lName = (lNameStartIdx == 0) ? name : name.substring(lNameStartIdx);
        return super.m_expandedNameTable.getExpandedTypeID(ns, lName, code, searchOnly);
    }
    
    public short[] getMapping(final String[] names, final String[] uris, final int[] types) {
        if (this._namesSize < 0) {
            return this.getMapping2(names, uris, types);
        }
        final int namesLength = names.length;
        final int exLength = super.m_expandedNameTable.getSize();
        final short[] result = new short[exLength];
        for (int i = 0; i < 14; ++i) {
            result[i] = (short)i;
        }
        for (int i = 14; i < exLength; ++i) {
            result[i] = super.m_expandedNameTable.getType(i);
        }
        for (int i = 0; i < namesLength; ++i) {
            final int genType = super.m_expandedNameTable.getExpandedTypeID(uris[i], names[i], types[i], true);
            if (genType >= 0 && genType < exLength) {
                result[genType] = (short)(i + 14);
            }
        }
        return result;
    }
    
    public int[] getReverseMapping(final String[] names, final String[] uris, final int[] types) {
        final int[] result = new int[names.length + 14];
        for (int i = 0; i < 14; ++i) {
            result[i] = i;
        }
        for (int i = 0; i < names.length; ++i) {
            final int type = super.m_expandedNameTable.getExpandedTypeID(uris[i], names[i], types[i], true);
            result[i + 14] = type;
        }
        return result;
    }
    
    private short[] getMapping2(final String[] names, final String[] uris, final int[] types) {
        final int namesLength = names.length;
        final int exLength = super.m_expandedNameTable.getSize();
        int[] generalizedTypes = null;
        if (namesLength > 0) {
            generalizedTypes = new int[namesLength];
        }
        int resultLength = exLength;
        for (int i = 0; i < namesLength; ++i) {
            generalizedTypes[i] = super.m_expandedNameTable.getExpandedTypeID(uris[i], names[i], types[i], false);
            if (this._namesSize < 0 && generalizedTypes[i] >= resultLength) {
                resultLength = generalizedTypes[i] + 1;
            }
        }
        final short[] result = new short[resultLength];
        for (int i = 0; i < 14; ++i) {
            result[i] = (short)i;
        }
        for (int i = 14; i < exLength; ++i) {
            result[i] = super.m_expandedNameTable.getType(i);
        }
        for (int i = 0; i < namesLength; ++i) {
            final int genType = generalizedTypes[i];
            if (genType >= 0 && genType < resultLength) {
                result[genType] = (short)(i + 14);
            }
        }
        return result;
    }
    
    public short[] getNamespaceMapping(final String[] namespaces) {
        final int nsLength = namespaces.length;
        final int mappingLength = this._uriCount;
        final short[] result = new short[mappingLength];
        for (int i = 0; i < mappingLength; ++i) {
            result[i] = -1;
        }
        for (int i = 0; i < nsLength; ++i) {
            final int eType = this.getIdForNamespace(namespaces[i]);
            final Integer type = (Integer)this._nsIndex.get(new Integer(eType));
            if (type != null) {
                result[type] = (short)i;
            }
        }
        return result;
    }
    
    public short[] getReverseNamespaceMapping(final String[] namespaces) {
        final int length = namespaces.length;
        final short[] result = new short[length];
        for (int i = 0; i < length; ++i) {
            final int eType = this.getIdForNamespace(namespaces[i]);
            final Integer type = (Integer)this._nsIndex.get(new Integer(eType));
            result[i] = (short)((type == null) ? -1 : ((short)(Object)type));
        }
        return result;
    }
    
    public SAXImpl(final XSLTCDTMManager mgr, final Source source, final int dtmIdentity, final DTMWSFilter whiteSpaceFilter, final XMLStringFactory xstringfactory, final boolean doIndexing, final boolean buildIdIndex) {
        this(mgr, source, dtmIdentity, whiteSpaceFilter, xstringfactory, doIndexing, 512, buildIdIndex, false);
    }
    
    public SAXImpl(final XSLTCDTMManager mgr, final Source source, final int dtmIdentity, final DTMWSFilter whiteSpaceFilter, final XMLStringFactory xstringfactory, final boolean doIndexing, final int blocksize, final boolean buildIdIndex, final boolean newNameTable) {
        super(mgr, source, dtmIdentity, whiteSpaceFilter, xstringfactory, doIndexing, blocksize, false, buildIdIndex, newNameTable);
        this._uriCount = 0;
        this._prefixCount = 0;
        this._idx = 1;
        this._preserve = false;
        this._escaping = true;
        this._disableEscaping = false;
        this._textNodeToProcess = -1;
        this._namesSize = -1;
        this._nsIndex = new Hashtable();
        this._size = 0;
        this._dontEscape = null;
        this._documentURI = null;
        this._node2Ids = null;
        this._hasDOMSource = false;
        this._dtmManager = mgr;
        this._size = blocksize;
        (this._xmlSpaceStack = new int[(blocksize <= 64) ? 4 : 64])[0] = 0;
        if (source instanceof DOMSource) {
            this._hasDOMSource = true;
            final DOMSource domsrc = (DOMSource)source;
            final Node node = domsrc.getNode();
            if (node instanceof Document) {
                this._document = (Document)node;
            }
            else {
                this._document = node.getOwnerDocument();
            }
            this._node2Ids = new Hashtable();
        }
    }
    
    public void migrateTo(final DTMManager manager) {
        super.migrateTo(manager);
        if (manager instanceof XSLTCDTMManager) {
            this._dtmManager = (XSLTCDTMManager)manager;
        }
    }
    
    public int getElementById(final String idString) {
        final Node node = this._document.getElementById(idString);
        if (node != null) {
            final Integer id = (Integer)this._node2Ids.get(node);
            return (id != null) ? id : -1;
        }
        return -1;
    }
    
    public boolean hasDOMSource() {
        return this._hasDOMSource;
    }
    
    private void xmlSpaceDefine(final String val, final int node) {
        final boolean setting = val.equals("preserve");
        if (setting != this._preserve) {
            this._xmlSpaceStack[this._idx++] = node;
            this._preserve = setting;
        }
    }
    
    private void xmlSpaceRevert(final int node) {
        if (node == this._xmlSpaceStack[this._idx - 1]) {
            --this._idx;
            this._preserve = !this._preserve;
        }
    }
    
    protected boolean getShouldStripWhitespace() {
        return !this._preserve && super.getShouldStripWhitespace();
    }
    
    private void handleTextEscaping() {
        if (this._disableEscaping && this._textNodeToProcess != -1 && this._type(this._textNodeToProcess) == 3) {
            if (this._dontEscape == null) {
                this._dontEscape = new BitArray(this._size);
            }
            if (this._textNodeToProcess >= this._dontEscape.size()) {
                this._dontEscape.resize(this._dontEscape.size() * 2);
            }
            this._dontEscape.setBit(this._textNodeToProcess);
            this._disableEscaping = false;
        }
        this._textNodeToProcess = -1;
    }
    
    public void characters(final char[] ch, final int start, final int length) throws SAXException {
        super.characters(ch, start, length);
        this._disableEscaping = !this._escaping;
        this._textNodeToProcess = this.getNumberOfNodes();
    }
    
    public void startDocument() throws SAXException {
        super.startDocument();
        this._nsIndex.put(new Integer(0), new Integer(this._uriCount++));
        this.definePrefixAndUri("xml", "http://www.w3.org/XML/1998/namespace");
    }
    
    public void endDocument() throws SAXException {
        super.endDocument();
        this.handleTextEscaping();
        this._namesSize = super.m_expandedNameTable.getSize();
    }
    
    public void startElement(final String uri, final String localName, final String qname, final Attributes attributes, final Node node) throws SAXException {
        this.startElement(uri, localName, qname, attributes);
        if (super.m_buildIdIndex) {
            this._node2Ids.put(node, new Integer(super.m_parents.peek()));
        }
    }
    
    public void startElement(final String uri, final String localName, final String qname, final Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qname, attributes);
        this.handleTextEscaping();
        if (super.m_wsfilter != null) {
            final int index = attributes.getIndex("xml:space");
            if (index >= 0) {
                this.xmlSpaceDefine(attributes.getValue(index), super.m_parents.peek());
            }
        }
    }
    
    public void endElement(final String namespaceURI, final String localName, final String qname) throws SAXException {
        super.endElement(namespaceURI, localName, qname);
        this.handleTextEscaping();
        if (super.m_wsfilter != null) {
            this.xmlSpaceRevert(super.m_previous);
        }
    }
    
    public void processingInstruction(final String target, final String data) throws SAXException {
        super.processingInstruction(target, data);
        this.handleTextEscaping();
    }
    
    public void ignorableWhitespace(final char[] ch, final int start, final int length) throws SAXException {
        super.ignorableWhitespace(ch, start, length);
        this._textNodeToProcess = this.getNumberOfNodes();
    }
    
    public void startPrefixMapping(final String prefix, final String uri) throws SAXException {
        super.startPrefixMapping(prefix, uri);
        this.handleTextEscaping();
        this.definePrefixAndUri(prefix, uri);
    }
    
    private void definePrefixAndUri(final String prefix, final String uri) throws SAXException {
        final Integer eType = new Integer(this.getIdForNamespace(uri));
        if (this._nsIndex.get(eType) == null) {
            this._nsIndex.put(eType, new Integer(this._uriCount++));
        }
    }
    
    public void comment(final char[] ch, final int start, final int length) throws SAXException {
        super.comment(ch, start, length);
        this.handleTextEscaping();
    }
    
    public boolean setEscaping(final boolean value) {
        final boolean temp = this._escaping;
        this._escaping = value;
        return temp;
    }
    
    public void print(final int node, final int level) {
        switch (this.getNodeType(node)) {
            case 0:
            case 9: {
                this.print(this.getFirstChild(node), level);
                break;
            }
            case 3:
            case 7:
            case 8: {
                System.out.print(this.getStringValueX(node));
                break;
            }
            default: {
                final String name = this.getNodeName(node);
                System.out.print("<" + name);
                for (int a = this.getFirstAttribute(node); a != -1; a = this.getNextAttribute(a)) {
                    System.out.print("\n" + this.getNodeName(a) + "=\"" + this.getStringValueX(a) + "\"");
                }
                System.out.print('>');
                for (int child = this.getFirstChild(node); child != -1; child = this.getNextSibling(child)) {
                    this.print(child, level + 1);
                }
                System.out.println("</" + name + '>');
                break;
            }
        }
    }
    
    public String getNodeName(final int node) {
        final short type = this.getNodeType(node);
        switch (type) {
            case 0:
            case 3:
            case 8:
            case 9: {
                return "";
            }
            case 13: {
                return this.getLocalName(node);
            }
            default: {
                return super.getNodeName(node);
            }
        }
    }
    
    public String getNamespaceName(final int node) {
        if (node == -1) {
            return "";
        }
        final String s;
        return ((s = this.getNamespaceURI(node)) == null) ? "" : s;
    }
    
    public int getAttributeNode(final int type, final int element) {
        for (int attr = this.getFirstAttribute(element); attr != -1; attr = this.getNextAttribute(attr)) {
            if (this.getExpandedTypeID(attr) == type) {
                return attr;
            }
        }
        return -1;
    }
    
    public String getAttributeValue(final int type, final int element) {
        final int attr = this.getAttributeNode(type, element);
        return (attr != -1) ? this.getStringValueX(attr) : "";
    }
    
    public String getAttributeValue(final String name, final int element) {
        return this.getAttributeValue(this.getGeneralizedType(name), element);
    }
    
    public DTMAxisIterator getChildren(final int node) {
        return new ChildrenIterator(this).setStartNode(node);
    }
    
    public DTMAxisIterator getTypedChildren(final int type) {
        return new TypedChildrenIterator(this, type);
    }
    
    public DTMAxisIterator getAxisIterator(final int axis) {
        switch (axis) {
            case 13: {
                return new SingletonIterator(this);
            }
            case 3: {
                return new ChildrenIterator(this);
            }
            case 10: {
                return new ParentIterator(this);
            }
            case 0: {
                return new AncestorIterator(this);
            }
            case 1: {
                return new AncestorIterator(this).includeSelf();
            }
            case 2: {
                return new AttributeIterator(this);
            }
            case 4: {
                return new DescendantIterator(this);
            }
            case 5: {
                return new DescendantIterator(this).includeSelf();
            }
            case 6: {
                return new FollowingIterator(this);
            }
            case 11: {
                return new PrecedingIterator(this);
            }
            case 7: {
                return new FollowingSiblingIterator(this);
            }
            case 12: {
                return new PrecedingSiblingIterator(this);
            }
            case 9: {
                return new NamespaceIterator(this);
            }
            default: {
                BasisLibrary.runTimeError("AXIS_SUPPORT_ERR", Axis.names[axis]);
                return null;
            }
        }
    }
    
    public DTMAxisIterator getTypedAxisIterator(final int axis, final int type) {
        if (axis == 3) {
            return new TypedChildrenIterator(this, type);
        }
        if (type == -1) {
            return SAXImpl.EMPTYITERATOR;
        }
        switch (axis) {
            case 13: {
                return new TypedSingletonIterator(this, type);
            }
            case 3: {
                return new TypedChildrenIterator(this, type);
            }
            case 10: {
                return new ParentIterator(this).setNodeType(type);
            }
            case 0: {
                return new TypedAncestorIterator(this, type);
            }
            case 1: {
                return new TypedAncestorIterator(this, type).includeSelf();
            }
            case 2: {
                return new TypedAttributeIterator(this, type);
            }
            case 4: {
                return new TypedDescendantIterator(this, type);
            }
            case 5: {
                return new TypedDescendantIterator(this, type).includeSelf();
            }
            case 6: {
                return new TypedFollowingIterator(this, type);
            }
            case 11: {
                return new TypedPrecedingIterator(this, type);
            }
            case 7: {
                return new TypedFollowingSiblingIterator(this, type);
            }
            case 12: {
                return new TypedPrecedingSiblingIterator(this, type);
            }
            case 9: {
                return new TypedNamespaceIterator(type);
            }
            default: {
                BasisLibrary.runTimeError("TYPED_AXIS_SUPPORT_ERR", Axis.names[axis]);
                return null;
            }
        }
    }
    
    public DTMAxisIterator getNamespaceAxisIterator(final int axis, final int ns) {
        final DTMAxisIterator iterator = null;
        if (ns == -1) {
            return SAXImpl.EMPTYITERATOR;
        }
        switch (axis) {
            case 3: {
                return new NamespaceChildrenIterator(ns);
            }
            case 2: {
                return new NamespaceAttributeIterator(ns);
            }
            default: {
                return new NamespaceWildcardIterator(axis, ns);
            }
        }
    }
    
    public DTMAxisIterator getTypedDescendantIterator(final int type) {
        return new TypedDescendantIterator(this, type);
    }
    
    public DTMAxisIterator getNthDescendant(final int type, final int n, final boolean includeself) {
        final DTMAxisIterator source = new TypedDescendantIterator(this, type);
        return new NthDescendantIterator(this, n);
    }
    
    public void characters(final int node, final SerializationHandler handler) throws TransletException {
        if (node != -1) {
            try {
                this.dispatchCharactersEvents(node, handler, false);
            }
            catch (SAXException e) {
                throw new TransletException(e);
            }
        }
    }
    
    public void copy(final DTMAxisIterator nodes, final SerializationHandler handler) throws TransletException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: goto            9
        //     3: aload_0         /* this */
        //     4: iload_3        
        //     5: aload_2         /* handler */
        //     6: invokevirtual   org/apache/xalan/xsltc/dom/SAXImpl.copy:(ILorg/apache/xml/serializer/SerializationHandler;)V
        //     9: aload_1         /* nodes */
        //    10: invokeinterface org/apache/xml/dtm/DTMAxisIterator.next:()I
        //    15: dup            
        //    16: istore_3        /* node */
        //    17: iconst_m1      
        //    18: if_icmpne       3
        //    21: return         
        //    Exceptions:
        //  throws org.apache.xalan.xsltc.TransletException
        //    LocalVariableTable:
        //  Start  Length  Slot  Name     Signature
        //  -----  ------  ----  -------  ------------------------------------------------
        //  0      22      0     this     Lorg/apache/xalan/xsltc/dom/SAXImpl;
        //  0      22      1     nodes    Lorg/apache/xml/dtm/DTMAxisIterator;
        //  0      22      2     handler  Lorg/apache/xml/serializer/SerializationHandler;
        //  17     4       3     node     I
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public void copy(final SerializationHandler handler) throws TransletException {
        this.copy(this.getDocument(), handler);
    }
    
    public void copy(final int node, final SerializationHandler handler) throws TransletException {
        this.copy(node, handler, false);
    }
    
    private final void copy(final int node, final SerializationHandler handler, final boolean isChild) throws TransletException {
        final int nodeID = this.makeNodeIdentity(node);
        final int eType = this._exptype2(nodeID);
        final int type = this._exptype2Type(eType);
        try {
            switch (type) {
                case 0:
                case 9: {
                    for (int c = this._firstch2(nodeID); c != -1; c = this._nextsib2(c)) {
                        this.copy(this.makeNodeHandle(c), handler, true);
                    }
                    break;
                }
                case 7: {
                    this.copyPI(node, handler);
                    break;
                }
                case 8: {
                    handler.comment(this.getStringValueX(node));
                    break;
                }
                case 3: {
                    boolean oldEscapeSetting = false;
                    boolean escapeBit = false;
                    if (this._dontEscape != null) {
                        escapeBit = this._dontEscape.getBit(this.getNodeIdent(node));
                        if (escapeBit) {
                            oldEscapeSetting = handler.setEscaping(false);
                        }
                    }
                    this.copyTextNode(nodeID, handler);
                    if (escapeBit) {
                        handler.setEscaping(oldEscapeSetting);
                        break;
                    }
                    break;
                }
                case 2: {
                    this.copyAttribute(nodeID, eType, handler);
                    break;
                }
                case 13: {
                    handler.namespaceAfterStartElement(this.getNodeNameX(node), this.getNodeValue(node));
                    break;
                }
                default: {
                    if (type == 1) {
                        final String name = this.copyElement(nodeID, eType, handler);
                        this.copyNS(nodeID, handler, !isChild);
                        this.copyAttributes(nodeID, handler);
                        for (int c2 = this._firstch2(nodeID); c2 != -1; c2 = this._nextsib2(c2)) {
                            this.copy(this.makeNodeHandle(c2), handler, true);
                        }
                        handler.endElement(name);
                        break;
                    }
                    final String uri = this.getNamespaceName(node);
                    if (uri.length() != 0) {
                        final String prefix = this.getPrefix(node);
                        handler.namespaceAfterStartElement(prefix, uri);
                    }
                    handler.addAttribute(this.getNodeName(node), this.getNodeValue(node));
                    break;
                }
            }
        }
        catch (Exception e) {
            throw new TransletException(e);
        }
    }
    
    private void copyPI(final int node, final SerializationHandler handler) throws TransletException {
        final String target = this.getNodeName(node);
        final String value = this.getStringValueX(node);
        try {
            handler.processingInstruction(target, value);
        }
        catch (Exception e) {
            throw new TransletException(e);
        }
    }
    
    public String shallowCopy(final int node, final SerializationHandler handler) throws TransletException {
        final int nodeID = this.makeNodeIdentity(node);
        final int exptype = this._exptype2(nodeID);
        final int type = this._exptype2Type(exptype);
        try {
            switch (type) {
                case 1: {
                    final String name = this.copyElement(nodeID, exptype, handler);
                    this.copyNS(nodeID, handler, true);
                    return name;
                }
                case 0:
                case 9: {
                    return "";
                }
                case 3: {
                    this.copyTextNode(nodeID, handler);
                    return null;
                }
                case 7: {
                    this.copyPI(node, handler);
                    return null;
                }
                case 8: {
                    handler.comment(this.getStringValueX(node));
                    return null;
                }
                case 13: {
                    handler.namespaceAfterStartElement(this.getNodeNameX(node), this.getNodeValue(node));
                    return null;
                }
                case 2: {
                    this.copyAttribute(nodeID, exptype, handler);
                    return null;
                }
                default: {
                    final String uri1 = this.getNamespaceName(node);
                    if (uri1.length() != 0) {
                        final String prefix = this.getPrefix(node);
                        handler.namespaceAfterStartElement(prefix, uri1);
                    }
                    handler.addAttribute(this.getNodeName(node), this.getNodeValue(node));
                    return null;
                }
            }
        }
        catch (Exception e) {
            throw new TransletException(e);
        }
    }
    
    public String getLanguage(final int node) {
        for (int parent = node; -1 != parent; parent = this.getParent(parent)) {
            if (1 == this.getNodeType(parent)) {
                final int langAttr = this.getAttributeNode(parent, "http://www.w3.org/XML/1998/namespace", "lang");
                if (-1 != langAttr) {
                    return this.getNodeValue(langAttr);
                }
            }
        }
        return null;
    }
    
    public DOMBuilder getBuilder() {
        return this;
    }
    
    public SerializationHandler getOutputDomBuilder() {
        return new ToXMLSAXHandler(this, "UTF-8");
    }
    
    public DOM getResultTreeFrag(final int initSize, final int rtfType) {
        return this.getResultTreeFrag(initSize, rtfType, true);
    }
    
    public DOM getResultTreeFrag(final int initSize, final int rtfType, final boolean addToManager) {
        if (rtfType == 0) {
            if (addToManager) {
                final int dtmPos = this._dtmManager.getFirstFreeDTMID();
                final SimpleResultTreeImpl rtf = new SimpleResultTreeImpl(this._dtmManager, dtmPos << 16);
                this._dtmManager.addDTM(rtf, dtmPos, 0);
                return rtf;
            }
            return new SimpleResultTreeImpl(this._dtmManager, 0);
        }
        else {
            if (rtfType != 1) {
                return (DOM)this._dtmManager.getDTM(null, true, super.m_wsfilter, true, false, false, initSize, super.m_buildIdIndex);
            }
            if (addToManager) {
                final int dtmPos = this._dtmManager.getFirstFreeDTMID();
                final AdaptiveResultTreeImpl rtf2 = new AdaptiveResultTreeImpl(this._dtmManager, dtmPos << 16, super.m_wsfilter, initSize, super.m_buildIdIndex);
                this._dtmManager.addDTM(rtf2, dtmPos, 0);
                return rtf2;
            }
            return new AdaptiveResultTreeImpl(this._dtmManager, 0, super.m_wsfilter, initSize, super.m_buildIdIndex);
        }
    }
    
    public Hashtable getElementsWithIDs() {
        if (super.m_idAttributes == null) {
            return null;
        }
        final Enumeration idValues = super.m_idAttributes.keys();
        if (!idValues.hasMoreElements()) {
            return null;
        }
        final Hashtable idAttrsTable = new Hashtable();
        while (idValues.hasMoreElements()) {
            final Object idValue = idValues.nextElement();
            idAttrsTable.put(idValue, super.m_idAttributes.get(idValue));
        }
        return idAttrsTable;
    }
    
    public String getUnparsedEntityURI(final String name) {
        if (this._document != null) {
            String uri = "";
            final DocumentType doctype = this._document.getDoctype();
            if (doctype != null) {
                final NamedNodeMap entities = doctype.getEntities();
                if (entities == null) {
                    return uri;
                }
                final Entity entity = (Entity)entities.getNamedItem(name);
                if (entity == null) {
                    return uri;
                }
                final String notationName = entity.getNotationName();
                if (notationName != null) {
                    uri = entity.getSystemId();
                    if (uri == null) {
                        uri = entity.getPublicId();
                    }
                }
            }
            return uri;
        }
        return super.getUnparsedEntityURI(name);
    }
    
    static {
        EMPTYITERATOR = EmptyIterator.getInstance();
        SAXImpl._documentURIIndex = 0;
    }
    
    public class TypedNamespaceIterator extends NamespaceIterator
    {
        private String _nsPrefix;
        
        public TypedNamespaceIterator(final int nodeType) {
            if (SAXImpl.this.m_expandedNameTable != null) {
                this._nsPrefix = SAXImpl.this.m_expandedNameTable.getLocalName(nodeType);
            }
        }
        
        public int next() {
            if (this._nsPrefix == null || this._nsPrefix.length() == 0) {
                return -1;
            }
            int node;
            for (node = -1, node = super.next(); node != -1; node = super.next()) {
                if (this._nsPrefix.compareTo(SAXImpl.this.getLocalName(node)) == 0) {
                    return this.returnNode(node);
                }
            }
            return -1;
        }
    }
    
    private final class NodeValueIterator extends InternalAxisIteratorBase
    {
        private DTMAxisIterator _source;
        private String _value;
        private boolean _op;
        private final boolean _isReverse;
        private int _returnType;
        
        public NodeValueIterator(final DTMAxisIterator source, final int returnType, final String value, final boolean op) {
            this._returnType = 1;
            this._source = source;
            this._returnType = returnType;
            this._value = value;
            this._op = op;
            this._isReverse = source.isReverse();
        }
        
        public boolean isReverse() {
            return this._isReverse;
        }
        
        public DTMAxisIterator cloneIterator() {
            try {
                final NodeValueIterator clone = (NodeValueIterator)super.clone();
                clone._isRestartable = false;
                clone._source = this._source.cloneIterator();
                clone._value = this._value;
                clone._op = this._op;
                return clone.reset();
            }
            catch (CloneNotSupportedException e) {
                BasisLibrary.runTimeError("ITERATOR_CLONE_ERR", e.toString());
                return null;
            }
        }
        
        public void setRestartable(final boolean isRestartable) {
            super._isRestartable = isRestartable;
            this._source.setRestartable(isRestartable);
        }
        
        public DTMAxisIterator reset() {
            this._source.reset();
            return this.resetPosition();
        }
        
        public int next() {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: goto            53
            //     3: aload_0         /* this */
            //     4: getfield        org/apache/xalan/xsltc/dom/SAXImpl$NodeValueIterator.this$0:Lorg/apache/xalan/xsltc/dom/SAXImpl;
            //     7: iload_1        
            //     8: invokevirtual   org/apache/xml/dtm/ref/sax2dtm/SAX2DTM2.getStringValueX:(I)Ljava/lang/String;
            //    11: astore_2        /* val */
            //    12: aload_0         /* this */
            //    13: getfield        org/apache/xalan/xsltc/dom/SAXImpl$NodeValueIterator._value:Ljava/lang/String;
            //    16: aload_2         /* val */
            //    17: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
            //    20: aload_0         /* this */
            //    21: getfield        org/apache/xalan/xsltc/dom/SAXImpl$NodeValueIterator._op:Z
            //    24: if_icmpne       53
            //    27: aload_0         /* this */
            //    28: getfield        org/apache/xalan/xsltc/dom/SAXImpl$NodeValueIterator._returnType:I
            //    31: ifne            40
            //    34: aload_0         /* this */
            //    35: iload_1        
            //    36: invokevirtual   org/apache/xml/dtm/ref/DTMAxisIteratorBase.returnNode:(I)I
            //    39: ireturn        
            //    40: aload_0         /* this */
            //    41: aload_0         /* this */
            //    42: getfield        org/apache/xalan/xsltc/dom/SAXImpl$NodeValueIterator.this$0:Lorg/apache/xalan/xsltc/dom/SAXImpl;
            //    45: iload_1        
            //    46: invokevirtual   org/apache/xml/dtm/ref/DTMDefaultBase.getParent:(I)I
            //    49: invokevirtual   org/apache/xml/dtm/ref/DTMAxisIteratorBase.returnNode:(I)I
            //    52: ireturn        
            //    53: aload_0         /* this */
            //    54: getfield        org/apache/xalan/xsltc/dom/SAXImpl$NodeValueIterator._source:Lorg/apache/xml/dtm/DTMAxisIterator;
            //    57: invokeinterface org/apache/xml/dtm/DTMAxisIterator.next:()I
            //    62: dup            
            //    63: istore_1        /* node */
            //    64: iconst_m1      
            //    65: if_icmpne       3
            //    68: iconst_m1      
            //    69: ireturn        
            //    LocalVariableTable:
            //  Start  Length  Slot  Name  Signature
            //  -----  ------  ----  ----  ------------------------------------------------------
            //  0      70      0     this  Lorg/apache/xalan/xsltc/dom/SAXImpl$NodeValueIterator;
            //  64     6       1     node  I
            //  12     41      2     val   Ljava/lang/String;
            // 
            // The error that occurred was:
            // 
            // java.lang.NullPointerException
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        public DTMAxisIterator setStartNode(final int node) {
            if (super._isRestartable) {
                this._source.setStartNode(super._startNode = node);
                return this.resetPosition();
            }
            return this;
        }
        
        public void setMark() {
            this._source.setMark();
        }
        
        public void gotoMark() {
            this._source.gotoMark();
        }
    }
    
    public final class NamespaceWildcardIterator extends InternalAxisIteratorBase
    {
        protected int m_nsType;
        protected DTMAxisIterator m_baseIterator;
        
        public NamespaceWildcardIterator(final int axis, final int nsType) {
            this.m_nsType = nsType;
            switch (axis) {
                case 2: {
                    this.m_baseIterator = SAXImpl.this.getAxisIterator(axis);
                }
                case 9: {
                    this.m_baseIterator = SAXImpl.this.getAxisIterator(axis);
                    break;
                }
            }
            this.m_baseIterator = SAXImpl.this.getTypedAxisIterator(axis, 1);
        }
        
        public DTMAxisIterator setStartNode(final int node) {
            if (super._isRestartable) {
                super._startNode = node;
                this.m_baseIterator.setStartNode(node);
                this.resetPosition();
            }
            return this;
        }
        
        public int next() {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: goto            24
            //     3: aload_0         /* this */
            //     4: getfield        org/apache/xalan/xsltc/dom/SAXImpl$NamespaceWildcardIterator.this$0:Lorg/apache/xalan/xsltc/dom/SAXImpl;
            //     7: iload_1        
            //     8: invokevirtual   org/apache/xalan/xsltc/dom/SAXImpl.getNSType:(I)I
            //    11: aload_0         /* this */
            //    12: getfield        org/apache/xalan/xsltc/dom/SAXImpl$NamespaceWildcardIterator.m_nsType:I
            //    15: if_icmpne       24
            //    18: aload_0         /* this */
            //    19: iload_1        
            //    20: invokevirtual   org/apache/xml/dtm/ref/DTMAxisIteratorBase.returnNode:(I)I
            //    23: ireturn        
            //    24: aload_0         /* this */
            //    25: getfield        org/apache/xalan/xsltc/dom/SAXImpl$NamespaceWildcardIterator.m_baseIterator:Lorg/apache/xml/dtm/DTMAxisIterator;
            //    28: invokeinterface org/apache/xml/dtm/DTMAxisIterator.next:()I
            //    33: dup            
            //    34: istore_1        /* node */
            //    35: iconst_m1      
            //    36: if_icmpne       3
            //    39: iconst_m1      
            //    40: ireturn        
            //    LocalVariableTable:
            //  Start  Length  Slot  Name  Signature
            //  -----  ------  ----  ----  --------------------------------------------------------------
            //  0      41      0     this  Lorg/apache/xalan/xsltc/dom/SAXImpl$NamespaceWildcardIterator;
            //  35     6       1     node  I
            // 
            // The error that occurred was:
            // 
            // java.lang.NullPointerException
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        public DTMAxisIterator cloneIterator() {
            try {
                final DTMAxisIterator nestedClone = this.m_baseIterator.cloneIterator();
                final NamespaceWildcardIterator clone = (NamespaceWildcardIterator)super.clone();
                clone.m_baseIterator = nestedClone;
                clone.m_nsType = this.m_nsType;
                clone._isRestartable = false;
                return clone;
            }
            catch (CloneNotSupportedException e) {
                BasisLibrary.runTimeError("ITERATOR_CLONE_ERR", e.toString());
                return null;
            }
        }
        
        public boolean isReverse() {
            return this.m_baseIterator.isReverse();
        }
        
        public void setMark() {
            this.m_baseIterator.setMark();
        }
        
        public void gotoMark() {
            this.m_baseIterator.gotoMark();
        }
    }
    
    public final class NamespaceChildrenIterator extends InternalAxisIteratorBase
    {
        private final int _nsType;
        
        public NamespaceChildrenIterator(final int type) {
            this._nsType = type;
        }
        
        public DTMAxisIterator setStartNode(int node) {
            if (node == 0) {
                node = SAXImpl.this.getDocument();
            }
            if (super._isRestartable) {
                super._startNode = node;
                super._currentNode = ((node == -1) ? -1 : -2);
                return this.resetPosition();
            }
            return this;
        }
        
        public int next() {
            if (super._currentNode != -1) {
                for (int node = (-2 == super._currentNode) ? SAXImpl.this._firstch(SAXImpl.this.makeNodeIdentity(super._startNode)) : SAXImpl.this._nextsib(super._currentNode); node != -1; node = SAXImpl.this._nextsib(node)) {
                    final int nodeHandle = SAXImpl.this.makeNodeHandle(node);
                    if (SAXImpl.this.getNSType(nodeHandle) == this._nsType) {
                        super._currentNode = node;
                        return this.returnNode(nodeHandle);
                    }
                }
            }
            return -1;
        }
    }
    
    public final class NamespaceAttributeIterator extends InternalAxisIteratorBase
    {
        private final int _nsType;
        
        public NamespaceAttributeIterator(final int nsType) {
            this._nsType = nsType;
        }
        
        public DTMAxisIterator setStartNode(int node) {
            if (node == 0) {
                node = SAXImpl.this.getDocument();
            }
            if (super._isRestartable) {
                final int nsType = this._nsType;
                super._startNode = node;
                for (node = SAXImpl.this.getFirstAttribute(node); node != -1 && SAXImpl.this.getNSType(node) != nsType; node = SAXImpl.this.getNextAttribute(node)) {}
                super._currentNode = node;
                return this.resetPosition();
            }
            return this;
        }
        
        public int next() {
            final int node = super._currentNode;
            final int nsType = this._nsType;
            if (node == -1) {
                return -1;
            }
            int nextNode;
            for (nextNode = SAXImpl.this.getNextAttribute(node); nextNode != -1 && SAXImpl.this.getNSType(nextNode) != nsType; nextNode = SAXImpl.this.getNextAttribute(nextNode)) {}
            super._currentNode = nextNode;
            return this.returnNode(node);
        }
    }
}
