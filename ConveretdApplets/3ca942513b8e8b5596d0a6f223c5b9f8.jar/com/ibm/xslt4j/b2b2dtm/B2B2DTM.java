// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.b2b2dtm;

import org.apache.xml.dtm.DTMException;
import org.apache.xml.dtm.ref.EmptyIterator;
import org.apache.xalan.xsltc.dom.AdaptiveResultTreeImpl;
import org.apache.xalan.xsltc.dom.SAXImpl;
import org.apache.xalan.xsltc.dom.SimpleResultTreeImpl;
import org.apache.xalan.xsltc.DOM;
import java.util.Enumeration;
import org.apache.xalan.xsltc.runtime.BasisLibrary;
import org.apache.xml.dtm.Axis;
import org.apache.xalan.xsltc.dom.DupFilterIterator;
import org.apache.xml.dtm.ref.DTMAxisIterNodeList;
import org.apache.xml.dtm.ref.DTMNodeProxy;
import org.apache.xalan.xsltc.StripFilter;
import org.apache.xalan.xsltc.TransletException;
import org.apache.xml.serializer.SerializationHandler;
import com.ibm.xml.b2b.util.QName;
import org.apache.xml.utils.XMLStringDefault;
import javax.xml.transform.SourceLocator;
import org.xml.sax.ext.DeclHandler;
import org.xml.sax.ErrorHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ext.LexicalHandler;
import org.apache.xml.utils.NodeVector;
import org.apache.xml.dtm.ref.DTMManagerDefault;
import org.xml.sax.SAXException;
import org.apache.xml.dtm.DTM;
import org.xml.sax.ContentHandler;
import org.apache.xml.res.XMLMessages;
import java.io.IOException;
import javax.xml.transform.Source;
import org.apache.xml.dtm.DTMManager;
import org.apache.xml.utils.XMLStringFactory;
import org.apache.xml.dtm.DTMWSFilter;
import javax.xml.transform.stream.StreamSource;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.apache.xalan.xsltc.dom.XSLTCDTMManager;
import org.apache.xalan.xsltc.dom.BitArray;
import org.apache.xml.dtm.DTMAxisIterator;
import org.apache.xml.utils.XMLString;
import java.util.Vector;
import org.apache.xml.dtm.ref.ExtendedType;
import org.apache.xml.dtm.ref.DTMTreeWalker;
import org.apache.xml.utils.IntStack;
import org.apache.xml.utils.SuballocatedIntVector;
import org.apache.xml.utils.FastStringBuffer;
import java.util.Hashtable;
import com.ibm.xml.b2b.scan.DocumentScannerSupport;
import com.ibm.xml.b2b.util.SymbolTable;
import org.apache.xalan.xsltc.DOMEnhancedForDTM;
import org.apache.xml.dtm.ref.DTMDefaultBaseIterators;

public class B2B2DTM extends DTMDefaultBaseIterators implements DOMEnhancedForDTM
{
    private static final boolean DEBUG = false;
    private static final String EMPTY_STRING;
    static ThreadLocal m_staticReaderParser;
    static ThreadLocal m_staticStreamParser;
    private B2BParser m_parser;
    private SymbolTable m_symbolTable;
    private DocumentScannerSupport m_scannerSupport;
    private Hashtable m_unparsedEntities;
    protected FastStringBuffer m_chars;
    protected SuballocatedIntVector m_data;
    protected transient IntStack m_parents;
    protected transient int m_previous;
    protected transient int m_textType;
    protected transient int m_coalescedTextType;
    protected DTMTreeWalker m_walker;
    protected boolean m_endDocumentOccured;
    protected SuballocatedIntVector m_dataOrQName;
    protected Hashtable m_idAttributes;
    static final String[] m_fixednames;
    protected int m_textPendingStart;
    boolean m_pastFirstElement;
    private int[] m_exptype_map0;
    private int[] m_nextsib_map0;
    private int[] m_firstch_map0;
    private int[] m_parent_map0;
    private int[][] m_exptype_map;
    private int[][] m_nextsib_map;
    private int[][] m_firstch_map;
    private int[][] m_parent_map;
    protected ExtendedType[] m_extendedTypes;
    protected Vector m_values;
    private int m_valueIndex;
    private int m_maxNodeIndex;
    protected int m_SHIFT;
    protected int m_MASK;
    protected int m_blocksize;
    protected static final int TEXT_LENGTH_BITS = 10;
    protected static final int TEXT_OFFSET_BITS = 21;
    protected static final int TEXT_LENGTH_MAX = 1023;
    protected static final int TEXT_OFFSET_MAX = 2097151;
    protected boolean m_buildIdIndex;
    private static final String EMPTY_STR = "";
    private static final XMLString EMPTY_XML_STR;
    private int _uriCount;
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
    private org.apache.xalan.xsltc.runtime.Hashtable _nsIndex;
    private int _size;
    private BitArray _dontEscape;
    private String _documentURI;
    private static int _documentURIIndex;
    private org.apache.xalan.xsltc.runtime.Hashtable _node2Ids;
    private boolean _hasDOMSource;
    private XSLTCDTMManager _dtmManager;
    private Node[] _nodes;
    private NodeList[] _nodeLists;
    private static final String XML_LANG_ATTRIBUTE = "http://www.w3.org/XML/1998/namespace:@lang";
    
    public B2B2DTM(final XSLTCDTMManager mgr, final StreamSource source, final int dtmIdentity, final DTMWSFilter whiteSpaceFilter, final XMLStringFactory xstringfactory, final boolean doIndexing) {
        super(mgr, source, dtmIdentity, whiteSpaceFilter, xstringfactory, doIndexing, 512, false, false);
        this.m_previous = 0;
        this.m_textType = 3;
        this.m_coalescedTextType = 3;
        this.m_walker = new DTMTreeWalker();
        this.m_endDocumentOccured = false;
        this.m_idAttributes = new Hashtable();
        this.m_textPendingStart = -1;
        this.m_pastFirstElement = false;
        this.m_valueIndex = 0;
        this.m_buildIdIndex = true;
        this._uriCount = 0;
        this._idx = 1;
        this._preserve = false;
        this._escaping = true;
        this._disableEscaping = false;
        this._textNodeToProcess = -1;
        this._namesSize = 0;
        this._nsIndex = new org.apache.xalan.xsltc.runtime.Hashtable();
        this._size = 0;
        this._dontEscape = null;
        this._documentURI = null;
        this._node2Ids = null;
        this._hasDOMSource = false;
        int blocksize = 512;
        if (blocksize <= 64) {
            this.m_data = new SuballocatedIntVector(blocksize, 4);
            this.m_dataOrQName = new SuballocatedIntVector(blocksize, 4);
            this.m_chars = new FastStringBuffer(7, 10);
            this.m_parents = new IntStack(4);
        }
        else {
            this.m_data = new SuballocatedIntVector(blocksize, 32);
            this.m_dataOrQName = new SuballocatedIntVector(blocksize, 32);
            this.m_chars = new FastStringBuffer(10, 13);
            this.m_parents = new IntStack();
        }
        this.m_data.addElement(0);
        int shift = 0;
        while ((blocksize >>>= 1) != 0) {
            ++shift;
        }
        this.m_blocksize = 1 << shift;
        this.m_SHIFT = shift;
        this.m_MASK = this.m_blocksize - 1;
        this.m_buildIdIndex = true;
        this.m_values = new Vector(32, 512);
        this.m_maxNodeIndex = 65536;
        this.m_exptype_map0 = super.m_exptype.getMap0();
        this.m_nextsib_map0 = super.m_nextsib.getMap0();
        this.m_firstch_map0 = super.m_firstch.getMap0();
        this.m_parent_map0 = super.m_parent.getMap0();
        this._dtmManager = mgr;
        this._size = blocksize;
        (this._xmlSpaceStack = new int[(blocksize <= 64) ? 4 : 64])[0] = 0;
    }
    
    public static boolean checkSource(final StreamSource source) {
        B2BParser parser;
        if (source.getReader() != null) {
            parser = B2B2DTM.m_staticReaderParser.get();
            if (parser == null) {
                B2B2DTM.m_staticReaderParser.set(parser = new B2BParser(true));
            }
        }
        else {
            parser = B2B2DTM.m_staticStreamParser.get();
            if (parser == null) {
                B2B2DTM.m_staticStreamParser.set(parser = new B2BParser(false));
            }
        }
        try {
            return parser.checkSource(source);
        }
        catch (IOException ex) {
            return false;
        }
    }
    
    public void parse(final StreamSource source) {
        if (source.getReader() != null) {
            this.m_parser = B2B2DTM.m_staticReaderParser.get();
            if (this.m_parser == null) {
                B2B2DTM.m_staticReaderParser.set(this.m_parser = new B2BParser(true));
            }
        }
        else {
            this.m_parser = B2B2DTM.m_staticStreamParser.get();
            if (this.m_parser == null) {
                B2B2DTM.m_staticStreamParser.set(this.m_parser = new B2BParser(false));
            }
        }
        this.m_symbolTable = this.m_parser.getSymbolTable();
        this.m_scannerSupport = this.m_parser.getScannerSupport();
        this.m_unparsedEntities = this.m_parser.getUnparsedEntities();
        this.m_parser.setDTM(this);
        this.m_parser.parse(source);
    }
    
    protected int _dataOrQName(final int identity) {
        if (identity < super.m_size) {
            return this.m_dataOrQName.elementAt(identity);
        }
        while (true) {
            final boolean isMore = this.nextNode();
            if (!isMore) {
                return -1;
            }
            if (identity < super.m_size) {
                return this.m_dataOrQName.elementAt(identity);
            }
        }
    }
    
    public boolean isAttributeSpecified(final int attributeHandle) {
        return true;
    }
    
    public String getDocumentTypeDeclarationSystemIdentifier() {
        this.error(XMLMessages.createXMLMessage("ER_METHOD_NOT_SUPPORTED", null));
        return null;
    }
    
    protected int getNextNodeIdentity(int identity) {
        if (++identity >= super.m_size) {
            return -1;
        }
        return identity;
    }
    
    public void dispatchToEvents(final int nodeHandle, final ContentHandler ch) throws SAXException {
        DTMTreeWalker treeWalker = this.m_walker;
        if (null != treeWalker.getcontentHandler()) {
            treeWalker = new DTMTreeWalker();
        }
        treeWalker.setcontentHandler(ch);
        treeWalker.setDTM(this);
        try {
            treeWalker.traverse(nodeHandle);
        }
        finally {
            treeWalker.setcontentHandler(null);
        }
    }
    
    public int getNumberOfNodes() {
        return super.m_size;
    }
    
    protected boolean nextNode() {
        return false;
    }
    
    private final boolean isTextType(final int type) {
        return 3 == type || 4 == type;
    }
    
    protected void addNewDTMID(final int nodeIndex) {
        try {
            if (super.m_mgr == null) {
                throw new ClassCastException();
            }
            final DTMManagerDefault mgrD = (DTMManagerDefault)super.m_mgr;
            final int id = mgrD.getFirstFreeDTMID();
            mgrD.addDTM(this, id, nodeIndex);
            super.m_dtmIdent.addElement(id << 16);
        }
        catch (ClassCastException e) {
            this.error(XMLMessages.createXMLMessage("ER_NO_DTMIDS_AVAIL", null));
        }
    }
    
    public void migrateTo(final DTMManager manager) {
        super.migrateTo(manager);
        final int numDTMs = super.m_dtmIdent.size();
        int dtmId = super.m_mgrDefault.getFirstFreeDTMID();
        int nodeIndex = 0;
        for (int i = 0; i < numDTMs; ++i) {
            super.m_dtmIdent.setElementAt(dtmId << 16, i);
            super.m_mgrDefault.addDTM(this, dtmId, nodeIndex);
            ++dtmId;
            nodeIndex += 65536;
        }
        if (manager instanceof XSLTCDTMManager) {
            this._dtmManager = (XSLTCDTMManager)manager;
        }
    }
    
    public String getPrefix(final int nodeHandle) {
        final int identity = this.makeNodeIdentity(nodeHandle);
        final int type = this._type(identity);
        if (1 != type) {
            if (2 == type) {
                int prefixIndex = this._dataOrQName(identity);
                if (prefixIndex < 0) {
                    prefixIndex = this.m_data.elementAt(-prefixIndex);
                    final String qname = this.m_symbolTable.toString(prefixIndex);
                    return this.getPrefix(qname);
                }
            }
            return "";
        }
        int prefixIndex = this._dataOrQName(identity);
        if (0 == prefixIndex) {
            return "";
        }
        final String qname = this.m_symbolTable.toString(prefixIndex);
        return this.getPrefix(qname);
    }
    
    public int getAttributeNode(final int nodeHandle, final String namespaceURI, final String name) {
        for (int attrH = this.getFirstAttribute(nodeHandle); -1 != attrH; attrH = this.getNextAttribute(attrH)) {
            final String attrNS = this.getNamespaceURI(attrH);
            final String attrName = this.getLocalName(attrH);
            final boolean nsMatch = namespaceURI == attrNS || (namespaceURI != null && namespaceURI.equals(attrNS));
            if (nsMatch && name.equals(attrName)) {
                return attrH;
            }
        }
        return -1;
    }
    
    public String getDocumentTypeDeclarationPublicIdentifier() {
        this.error(XMLMessages.createXMLMessage("ER_METHOD_NOT_SUPPORTED", null));
        return null;
    }
    
    public String getNamespaceURI(final int nodeHandle) {
        return super.m_expandedNameTable.getNamespace(this._exptype(this.makeNodeIdentity(nodeHandle)));
    }
    
    public boolean isWhitespace(final int nodeHandle) {
        final int identity = this.makeNodeIdentity(nodeHandle);
        int type;
        if (identity == -1) {
            type = -1;
        }
        else {
            type = this._type(identity);
        }
        if (this.isTextType(type)) {
            final int dataIndex = this._dataOrQName(identity);
            final int offset = this.m_data.elementAt(dataIndex);
            final int length = this.m_data.elementAt(dataIndex + 1);
            return this.m_chars.isWhitespace(offset, length);
        }
        return false;
    }
    
    public int getElementById(final String elementId) {
        boolean isMore = true;
        Integer intObj;
        do {
            intObj = this.m_idAttributes.get(elementId);
            if (null != intObj) {
                return this.makeNodeHandle(intObj);
            }
            if (!isMore) {
                break;
            }
            if (this.m_endDocumentOccured) {
                break;
            }
            isMore = this.nextNode();
        } while (null == intObj);
        return -1;
    }
    
    public NodeVector getElementByIdref(final String elementIdref) {
        return null;
    }
    
    public String getPrefix(final String qname) {
        String prefix;
        if (null != qname) {
            final int indexOfNSSep = qname.indexOf(58);
            if (indexOfNSSep > 0) {
                if (qname.startsWith("xmlns:")) {
                    prefix = qname.substring(indexOfNSSep + 1);
                }
                else {
                    prefix = qname.substring(0, indexOfNSSep);
                }
            }
            else if (qname.equals("xmlns")) {
                prefix = "";
            }
            else {
                prefix = null;
            }
        }
        else {
            prefix = null;
        }
        return prefix;
    }
    
    public void setIDAttribute(final String id, final int elem) {
        this.m_idAttributes.put(id, new Integer(elem));
    }
    
    public void endDocument_super() {
        this.charactersFlush();
        super.m_nextsib.setElementAt(-1, 0);
        if (super.m_firstch.elementAt(0) == -2) {
            super.m_firstch.setElementAt(-1, 0);
        }
        if (-1 != this.m_previous) {
            super.m_nextsib.setElementAt(-1, this.m_previous);
        }
        this.m_parents = null;
        this.m_endDocumentOccured = true;
    }
    
    public void characters(final char[] ch, final int start, final int length) {
        if (this.m_textPendingStart == -1) {
            this.m_textPendingStart = this.m_chars.size();
            this.m_coalescedTextType = this.m_textType;
        }
        else if (this.m_textType == 3) {
            this.m_coalescedTextType = 3;
        }
        this.m_chars.append(ch, start, length);
    }
    
    public void character(final int ch) {
        if (this.m_textPendingStart == -1) {
            this.m_textPendingStart = this.m_chars.size();
            this.m_coalescedTextType = this.m_textType;
        }
        else if (this.m_textType == 3) {
            this.m_coalescedTextType = 3;
        }
        if (ch < 65536) {
            this.m_chars.append((char)ch);
        }
        else {
            final int excess = ch - 65536;
            this.m_chars.append((char)(55296 + (excess >> 10)));
            this.m_chars.append((char)(56320 + (excess & 0x3FF)));
        }
    }
    
    public void ignorableWhitespace(final char[] ch, final int start, final int length) {
        this.characters(ch, start, length);
    }
    
    public void skippedEntity(final String name) {
    }
    
    public void startCDATA() {
        this.m_textType = 4;
    }
    
    public void endCDATA() {
        this.m_textType = 3;
    }
    
    public void setProperty(final String property, final Object value) {
    }
    
    public String getUnparsedEntityURI(final String name) {
        final String uri = this.m_unparsedEntities.get(name);
        return (uri != null) ? uri : "";
    }
    
    public boolean needsTwoThreads() {
        return false;
    }
    
    public ContentHandler getContentHandler() {
        return null;
    }
    
    public LexicalHandler getLexicalHandler() {
        return null;
    }
    
    public EntityResolver getEntityResolver() {
        return null;
    }
    
    public DTDHandler getDTDHandler() {
        return null;
    }
    
    public ErrorHandler getErrorHandler() {
        return null;
    }
    
    public DeclHandler getDeclHandler() {
        return null;
    }
    
    public SourceLocator getSourceLocatorFor(final int node) {
        return null;
    }
    
    public final int _exptype(final int identity) {
        return super.m_exptype.elementAt(identity);
    }
    
    public final int _exptype2(final int identity) {
        if (identity < this.m_blocksize) {
            return this.m_exptype_map0[identity];
        }
        return this.m_exptype_map[identity >>> this.m_SHIFT][identity & this.m_MASK];
    }
    
    public final int _nextsib2(final int identity) {
        if (identity < this.m_blocksize) {
            return this.m_nextsib_map0[identity];
        }
        return this.m_nextsib_map[identity >>> this.m_SHIFT][identity & this.m_MASK];
    }
    
    public final int _firstch2(final int identity) {
        if (identity < this.m_blocksize) {
            return this.m_firstch_map0[identity];
        }
        return this.m_firstch_map[identity >>> this.m_SHIFT][identity & this.m_MASK];
    }
    
    public final int _parent2(final int identity) {
        if (identity < this.m_blocksize) {
            return this.m_parent_map0[identity];
        }
        return this.m_parent_map[identity >>> this.m_SHIFT][identity & this.m_MASK];
    }
    
    public final int _type2(final int identity) {
        int eType;
        if (identity < this.m_blocksize) {
            eType = this.m_exptype_map0[identity];
        }
        else {
            eType = this.m_exptype_map[identity >>> this.m_SHIFT][identity & this.m_MASK];
        }
        if (-1 != eType) {
            return this.m_extendedTypes[eType].getNodeType();
        }
        return -1;
    }
    
    public final int getExpandedTypeID2(final int nodeHandle) {
        final int nodeID = this.makeNodeIdentity(nodeHandle);
        if (nodeID == -1) {
            return -1;
        }
        if (nodeID < this.m_blocksize) {
            return this.m_exptype_map0[nodeID];
        }
        return this.m_exptype_map[nodeID >>> this.m_SHIFT][nodeID & this.m_MASK];
    }
    
    public final int _exptype2Type(final int exptype) {
        if (-1 != exptype) {
            return this.m_extendedTypes[exptype].getNodeType();
        }
        return -1;
    }
    
    public int getIdForNamespace(final String uri) {
        final int index = this.m_values.indexOf(uri);
        if (index < 0) {
            this.m_values.addElement(uri);
            return this.m_valueIndex++;
        }
        return index;
    }
    
    public void startDocument() {
        final int doc = this.addNode(9, 9, -1, -1, 0, true);
        this.m_parents.push(doc);
        this.m_previous = -1;
    }
    
    public void endDocument() {
        this.endDocument_super();
        super.m_exptype.addElement(-1);
        super.m_parent.addElement(-1);
        super.m_nextsib.addElement(-1);
        super.m_firstch.addElement(-1);
        this.m_extendedTypes = super.m_expandedNameTable.getExtendedTypes();
        this.m_exptype_map = super.m_exptype.getMap();
        this.m_nextsib_map = super.m_nextsib.getMap();
        this.m_firstch_map = super.m_firstch.getMap();
        this.m_parent_map = super.m_parent.getMap();
    }
    
    protected final int addNode(final int type, final int expandedTypeID, final int parentIndex, final int previousSibling, final int dataOrPrefix, final boolean canHaveFirstChild) {
        final int nodeIndex = super.m_size++;
        if (nodeIndex == this.m_maxNodeIndex) {
            this.addNewDTMID(nodeIndex);
            this.m_maxNodeIndex += 65536;
        }
        super.m_firstch.addElement(-1);
        super.m_nextsib.addElement(-1);
        super.m_parent.addElement(parentIndex);
        super.m_exptype.addElement(expandedTypeID);
        this.m_dataOrQName.addElement(dataOrPrefix);
        if (super.m_prevsib != null) {
            super.m_prevsib.addElement(previousSibling);
        }
        switch (type) {
            case 13: {
                this.declareNamespaceInContext(parentIndex, nodeIndex);
                break;
            }
            case 2: {
                break;
            }
            default: {
                if (-1 != previousSibling) {
                    super.m_nextsib.setElementAt(nodeIndex, previousSibling);
                    break;
                }
                if (-1 != parentIndex) {
                    super.m_firstch.setElementAt(nodeIndex, parentIndex);
                    break;
                }
                break;
            }
        }
        return nodeIndex;
    }
    
    protected final void charactersFlush() {
        if (this.m_textPendingStart >= 0) {
            final int length = this.m_chars.size() - this.m_textPendingStart;
            boolean doStrip = false;
            if (this.getShouldStripWhitespace()) {
                doStrip = this.m_chars.isWhitespace(this.m_textPendingStart, length);
            }
            if (doStrip) {
                this.m_chars.setLength(this.m_textPendingStart);
            }
            else if (length <= 1023 && this.m_textPendingStart <= 2097151) {
                this.m_previous = this.addNode(this.m_coalescedTextType, 3, this.m_parents.peek(), this.m_previous, length + (this.m_textPendingStart << 10), false);
            }
            else {
                final int dataIndex = this.m_data.size();
                this.m_previous = this.addNode(this.m_coalescedTextType, 3, this.m_parents.peek(), this.m_previous, -dataIndex, false);
                this.m_data.addElement(this.m_textPendingStart);
                this.m_data.addElement(length);
            }
            this.m_textPendingStart = -1;
            final int n = 3;
            this.m_coalescedTextType = n;
            this.m_textType = n;
        }
    }
    
    public final int getFirstAttribute(final int nodeHandle) {
        int nodeID = this.makeNodeIdentity(nodeHandle);
        if (nodeID == -1) {
            return -1;
        }
        int type = this._type2(nodeID);
        if (1 == type) {
            do {
                ++nodeID;
                type = this._type2(nodeID);
                if (type == 2) {
                    return this.makeNodeHandle(nodeID);
                }
            } while (13 == type);
        }
        return -1;
    }
    
    protected int getFirstAttributeIdentity(int identity) {
        int type = this._type2(identity);
        if (1 == type) {
            do {
                ++identity;
                type = this._type2(identity);
                if (type == 2) {
                    return identity;
                }
            } while (13 == type);
        }
        return -1;
    }
    
    protected int getNextAttributeIdentity(int identity) {
        while (true) {
            ++identity;
            final int type = this._type2(identity);
            if (type == 2) {
                return identity;
            }
            if (type != 13) {
                return -1;
            }
        }
    }
    
    public final int getTypedAttribute(final int nodeHandle, final int attType) {
        int nodeID = this.makeNodeIdentity(nodeHandle);
        if (nodeID == -1) {
            return -1;
        }
        int type = this._type2(nodeID);
        if (1 == type) {
            while (true) {
                ++nodeID;
                final int expType = this._exptype2(nodeID);
                if (expType == -1) {
                    return -1;
                }
                type = this.m_extendedTypes[expType].getNodeType();
                if (type == 2) {
                    if (expType == attType) {
                        return this.makeNodeHandle(nodeID);
                    }
                    continue;
                }
                else {
                    if (13 != type) {
                        break;
                    }
                    continue;
                }
            }
        }
        return -1;
    }
    
    public String getLocalName(final int nodeHandle) {
        final int expType = this._exptype(this.makeNodeIdentity(nodeHandle));
        if (expType == 7) {
            int dataIndex = this._dataOrQName(this.makeNodeIdentity(nodeHandle));
            dataIndex = this.m_data.elementAt(-dataIndex);
            return this.m_symbolTable.toString(dataIndex);
        }
        return super.m_expandedNameTable.getLocalName(expType);
    }
    
    public final String getNodeNameX(final int nodeHandle) {
        final int nodeID = this.makeNodeIdentity(nodeHandle);
        final int eType = this._exptype2(nodeID);
        if (eType == 7) {
            int dataIndex = this._dataOrQName(nodeID);
            dataIndex = this.m_data.elementAt(-dataIndex);
            return this.m_symbolTable.toString(dataIndex);
        }
        final ExtendedType extType = this.m_extendedTypes[eType];
        if (extType.getNamespace().length() == 0) {
            return extType.getLocalName();
        }
        int qnameIndex = this.m_dataOrQName.elementAt(nodeID);
        if (qnameIndex == 0) {
            return extType.getLocalName();
        }
        if (qnameIndex < 0) {
            qnameIndex = -qnameIndex;
            qnameIndex = this.m_data.elementAt(qnameIndex);
        }
        return this.m_symbolTable.toString(qnameIndex);
    }
    
    public String getNodeName_super(final int nodeHandle) {
        final int nodeID = this.makeNodeIdentity(nodeHandle);
        final int eType = this._exptype2(nodeID);
        final ExtendedType extType = this.m_extendedTypes[eType];
        if (extType.getNamespace().length() == 0) {
            final int type = extType.getNodeType();
            final String localName = extType.getLocalName();
            if (type == 13) {
                if (localName.length() == 0) {
                    return "xmlns";
                }
                return "xmlns:" + localName;
            }
            else {
                if (type == 7) {
                    int dataIndex = this._dataOrQName(nodeID);
                    dataIndex = this.m_data.elementAt(-dataIndex);
                    return this.m_symbolTable.toString(dataIndex);
                }
                if (localName.length() == 0) {
                    return B2B2DTM.m_fixednames[type];
                }
                return localName;
            }
        }
        else {
            int qnameIndex = this.m_dataOrQName.elementAt(nodeID);
            if (qnameIndex == 0) {
                return extType.getLocalName();
            }
            if (qnameIndex < 0) {
                qnameIndex = -qnameIndex;
                qnameIndex = this.m_data.elementAt(qnameIndex);
            }
            return this.m_symbolTable.toString(qnameIndex);
        }
    }
    
    public XMLString getStringValue(final int nodeHandle) {
        int identity = this.makeNodeIdentity(nodeHandle);
        if (identity == -1) {
            return B2B2DTM.EMPTY_XML_STR;
        }
        int type = this._type2(identity);
        if (type == 1 || type == 9) {
            final int startNode = identity;
            identity = this._firstch2(identity);
            if (-1 == identity) {
                return B2B2DTM.EMPTY_XML_STR;
            }
            int offset = -1;
            int length = 0;
            do {
                type = this._exptype2(identity);
                if (type == 3 || type == 4) {
                    final int dataIndex = this.m_dataOrQName.elementAt(identity);
                    if (dataIndex > 0) {
                        if (-1 == offset) {
                            offset = dataIndex >>> 10;
                        }
                        length += (dataIndex & 0x3FF);
                    }
                    else {
                        if (-1 == offset) {
                            offset = this.m_data.elementAt(-dataIndex);
                        }
                        length += this.m_data.elementAt(-dataIndex + 1);
                    }
                }
                ++identity;
            } while (this._parent2(identity) >= startNode);
            if (length <= 0) {
                return B2B2DTM.EMPTY_XML_STR;
            }
            if (super.m_xstrf != null) {
                return super.m_xstrf.newstr(this.m_chars, offset, length);
            }
            return new XMLStringDefault(this.m_chars.getString(offset, length));
        }
        else if (3 == type || 4 == type) {
            final int dataIndex2 = this.m_dataOrQName.elementAt(identity);
            if (dataIndex2 > 0) {
                if (super.m_xstrf != null) {
                    return super.m_xstrf.newstr(this.m_chars, dataIndex2 >>> 10, dataIndex2 & 0x3FF);
                }
                return new XMLStringDefault(this.m_chars.getString(dataIndex2 >>> 10, dataIndex2 & 0x3FF));
            }
            else {
                if (super.m_xstrf != null) {
                    return super.m_xstrf.newstr(this.m_chars, this.m_data.elementAt(-dataIndex2), this.m_data.elementAt(-dataIndex2 + 1));
                }
                return new XMLStringDefault(this.m_chars.getString(this.m_data.elementAt(-dataIndex2), this.m_data.elementAt(-dataIndex2 + 1)));
            }
        }
        else {
            int dataIndex2 = this.m_dataOrQName.elementAt(identity);
            if (dataIndex2 < 0) {
                dataIndex2 = -dataIndex2;
                dataIndex2 = this.m_data.elementAt(dataIndex2 + 1);
            }
            if (super.m_xstrf != null) {
                return super.m_xstrf.newstr(this.m_values.elementAt(dataIndex2));
            }
            return new XMLStringDefault(this.m_values.elementAt(dataIndex2));
        }
    }
    
    public final String getStringValueX(final int nodeHandle) {
        int identity = this.makeNodeIdentity(nodeHandle);
        if (identity == -1) {
            return "";
        }
        int type = this._type2(identity);
        if (type == 1 || type == 9) {
            final int startNode = identity;
            identity = this._firstch2(identity);
            if (-1 == identity) {
                return "";
            }
            int offset = -1;
            int length = 0;
            do {
                type = this._exptype2(identity);
                if (type == 3 || type == 4) {
                    final int dataIndex = this.m_dataOrQName.elementAt(identity);
                    if (dataIndex > 0) {
                        if (-1 == offset) {
                            offset = dataIndex >>> 10;
                        }
                        length += (dataIndex & 0x3FF);
                    }
                    else {
                        if (-1 == offset) {
                            offset = this.m_data.elementAt(-dataIndex);
                        }
                        length += this.m_data.elementAt(-dataIndex + 1);
                    }
                }
                ++identity;
            } while (this._parent2(identity) >= startNode);
            if (length > 0) {
                return this.m_chars.getString(offset, length);
            }
            return "";
        }
        else {
            if (3 != type && 4 != type) {
                int dataIndex2 = this.m_dataOrQName.elementAt(identity);
                if (dataIndex2 < 0) {
                    dataIndex2 = -dataIndex2;
                    dataIndex2 = this.m_data.elementAt(dataIndex2 + 1);
                }
                return this.m_values.elementAt(dataIndex2);
            }
            int dataIndex2 = this.m_dataOrQName.elementAt(identity);
            if (dataIndex2 > 0) {
                return this.m_chars.getString(dataIndex2 >>> 10, dataIndex2 & 0x3FF);
            }
            return this.m_chars.getString(this.m_data.elementAt(-dataIndex2), this.m_data.elementAt(-dataIndex2 + 1));
        }
    }
    
    public final void dispatchCharactersEvents(final int nodeHandle, final ContentHandler ch, final boolean normalize) throws SAXException {
        int identity = this.makeNodeIdentity(nodeHandle);
        if (identity == -1) {
            return;
        }
        int type = this._type2(identity);
        if (type == 1 || type == 9) {
            final int startNode = identity;
            identity = this._firstch2(identity);
            if (-1 != identity) {
                int offset = -1;
                int length = 0;
                do {
                    type = this._exptype2(identity);
                    if (type == 3 || type == 4) {
                        final int dataIndex = this.m_dataOrQName.elementAt(identity);
                        if (dataIndex > 0) {
                            if (-1 == offset) {
                                offset = dataIndex >>> 10;
                            }
                            length += (dataIndex & 0x3FF);
                        }
                        else {
                            if (-1 == offset) {
                                offset = this.m_data.elementAt(-dataIndex);
                            }
                            length += this.m_data.elementAt(-dataIndex + 1);
                        }
                    }
                    ++identity;
                } while (this._parent2(identity) >= startNode);
                if (length > 0) {
                    if (normalize) {
                        this.m_chars.sendNormalizedSAXcharacters(ch, offset, length);
                    }
                    else {
                        this.m_chars.sendSAXcharacters(ch, offset, length);
                    }
                }
            }
        }
        else if (3 == type || 4 == type) {
            final int dataIndex2 = this.m_dataOrQName.elementAt(identity);
            if (dataIndex2 > 0) {
                if (normalize) {
                    this.m_chars.sendNormalizedSAXcharacters(ch, dataIndex2 >>> 10, dataIndex2 & 0x3FF);
                }
                else {
                    this.m_chars.sendSAXcharacters(ch, dataIndex2 >>> 10, dataIndex2 & 0x3FF);
                }
            }
            else if (normalize) {
                this.m_chars.sendNormalizedSAXcharacters(ch, this.m_data.elementAt(-dataIndex2), this.m_data.elementAt(-dataIndex2 + 1));
            }
            else {
                this.m_chars.sendSAXcharacters(ch, this.m_data.elementAt(-dataIndex2), this.m_data.elementAt(-dataIndex2 + 1));
            }
        }
        else {
            int dataIndex2 = this.m_dataOrQName.elementAt(identity);
            if (dataIndex2 < 0) {
                dataIndex2 = -dataIndex2;
                dataIndex2 = this.m_data.elementAt(dataIndex2 + 1);
            }
            final String str = this.m_values.elementAt(dataIndex2);
            if (normalize) {
                FastStringBuffer.sendNormalizedSAXcharacters(str.toCharArray(), 0, str.length(), ch);
            }
            else {
                ch.characters(str.toCharArray(), 0, str.length());
            }
        }
    }
    
    public String getNodeValue(final int nodeHandle) {
        final int identity = this.makeNodeIdentity(nodeHandle);
        final int type = this._type2(identity);
        if (type == 3 || type == 4) {
            final int dataIndex = this._dataOrQName(identity);
            if (dataIndex > 0) {
                return this.m_chars.getString(dataIndex >>> 10, dataIndex & 0x3FF);
            }
            return this.m_chars.getString(this.m_data.elementAt(-dataIndex), this.m_data.elementAt(-dataIndex + 1));
        }
        else {
            if (1 == type || 11 == type || 9 == type) {
                return null;
            }
            int dataIndex = this.m_dataOrQName.elementAt(identity);
            if (dataIndex < 0) {
                dataIndex = -dataIndex;
                dataIndex = this.m_data.elementAt(dataIndex + 1);
            }
            return this.m_values.elementAt(dataIndex);
        }
    }
    
    public void endElement(final String uri, final String localName, final String qName) {
        this.charactersFlush();
        this.m_previous = this.m_parents.pop();
        this.popShouldStripWhitespace();
    }
    
    public void processingInstruction(final int target, final String data) {
        this.charactersFlush();
        final int dataIndex = this.m_data.size();
        this.m_previous = this.addNode(7, 7, this.m_parents.peek(), this.m_previous, -dataIndex, false);
        this.m_data.addElement(target);
        this.m_values.addElement(data);
        this.m_data.addElement(this.m_valueIndex++);
    }
    
    public void comment(final String content) {
        this.charactersFlush();
        this.m_values.addElement(content);
        final int dataIndex = this.m_valueIndex++;
        this.m_previous = this.addNode(8, 8, this.m_parents.peek(), this.m_previous, dataIndex, false);
    }
    
    public int createElementNode(final QName elementType) {
        this.charactersFlush();
        final String uri = elementType.namespaceURI;
        final String localName = elementType.localPart;
        final String qName = elementType.str;
        final String prefix = elementType.prefix;
        final int exName = super.m_expandedNameTable.getExpandedTypeID(uri, localName, 1, false);
        final int prefixIndex = (B2B2DTM.EMPTY_STRING != prefix) ? elementType.handle : 0;
        final int elemNode = this.addNode(1, exName, this.m_parents.peek(), this.m_previous, prefixIndex, true);
        if (super.m_indexing) {
            this.indexNode(exName, elemNode);
        }
        this.m_parents.push(elemNode);
        return elemNode;
    }
    
    public void createNamespaceNodes(final int elemNode) {
        int startDecls = this.m_scannerSupport.firstMapping;
        final int nDecls = this.m_scannerSupport.lastMapping;
        if (!this.m_pastFirstElement) {
            startDecls = 0;
            this.m_pastFirstElement = true;
        }
        for (int i = startDecls; i < nDecls; ++i) {
            final String prefix = this.m_scannerSupport.prefixes[i];
            final String declURL = this.m_scannerSupport.namespaceURIs[i];
            final int exName = super.m_expandedNameTable.getExpandedTypeID(null, prefix, 13);
            this.m_values.addElement(declURL);
            final int val = this.m_valueIndex++;
            this.addNode(13, exName, elemNode, -1, val, false);
        }
    }
    
    public void createAttributeNode(final int elemNode, final QName attributeName, final boolean attTypeIsID, String valString) {
        final String attrUri = attributeName.namespaceURI;
        final String attrLocalName = attributeName.localPart;
        final String attrQName = attributeName.str;
        final String prefix = attributeName.prefix;
        final int nodeType = 2;
        if (this.m_buildIdIndex && attTypeIsID) {
            this.setIDAttribute(valString, elemNode);
        }
        if (null == valString) {
            valString = "";
        }
        this.m_values.addElement(valString);
        int val = this.m_valueIndex++;
        if (B2B2DTM.EMPTY_STRING != prefix) {
            final int prefixIndex = attributeName.handle;
            final int dataIndex = this.m_data.size();
            this.m_data.addElement(prefixIndex);
            this.m_data.addElement(val);
            val = -dataIndex;
        }
        final int exName = super.m_expandedNameTable.getExpandedTypeID(attrUri, attrLocalName, nodeType, false);
        this.addNode(nodeType, exName, elemNode, -1, val, false);
    }
    
    public void finishStartElement(final int elemNode) {
        if (null != super.m_wsfilter) {
            final short wsv = super.m_wsfilter.getShouldStripSpace(this.makeNodeHandle(elemNode), this);
            final boolean shouldStrip = (3 == wsv) ? this.getShouldStripWhitespace() : (2 == wsv);
            this.pushShouldStripWhitespace(shouldStrip);
        }
        this.m_previous = -1;
    }
    
    protected final void copyTextNode(final int nodeID, final SerializationHandler handler) throws SAXException {
        if (nodeID != -1) {
            final int dataIndex = this.m_dataOrQName.elementAt(nodeID);
            if (dataIndex > 0) {
                this.m_chars.sendSAXcharacters(handler, dataIndex >>> 10, dataIndex & 0x3FF);
            }
            else {
                this.m_chars.sendSAXcharacters(handler, this.m_data.elementAt(-dataIndex), this.m_data.elementAt(-dataIndex + 1));
            }
        }
    }
    
    protected final String copyElement(final int nodeID, final int exptype, final SerializationHandler handler) throws SAXException {
        final ExtendedType extType = this.m_extendedTypes[exptype];
        final String uri = extType.getNamespace();
        final String name = extType.getLocalName();
        if (uri.length() == 0) {
            handler.startElement(name);
            return name;
        }
        int qnameIndex = this.m_dataOrQName.elementAt(nodeID);
        if (qnameIndex == 0) {
            handler.startElement(name);
            handler.namespaceAfterStartElement("", uri);
            return name;
        }
        if (qnameIndex < 0) {
            qnameIndex = -qnameIndex;
            qnameIndex = this.m_data.elementAt(qnameIndex);
        }
        final String qName = this.m_symbolTable.toString(qnameIndex);
        handler.startElement(qName);
        final int prefixIndex = qName.indexOf(58);
        String prefix;
        if (prefixIndex > 0) {
            prefix = qName.substring(0, prefixIndex);
        }
        else {
            prefix = null;
        }
        handler.namespaceAfterStartElement(prefix, uri);
        return qName;
    }
    
    protected final void copyAttribute(final int nodeID, final int exptype, final SerializationHandler handler) throws SAXException {
        final ExtendedType extType = this.m_extendedTypes[exptype];
        final String uri = extType.getNamespace();
        final String localName = extType.getLocalName();
        String prefix = null;
        String qname = null;
        int valueIndex;
        final int dataIndex = valueIndex = this._dataOrQName(nodeID);
        if (uri.length() != 0) {
            if (dataIndex <= 0) {
                final int prefixIndex = this.m_data.elementAt(-dataIndex);
                valueIndex = this.m_data.elementAt(-dataIndex + 1);
                qname = this.m_symbolTable.toString(prefixIndex);
                final int colonIndex = qname.indexOf(58);
                if (colonIndex > 0) {
                    prefix = qname.substring(0, colonIndex);
                }
            }
            handler.namespaceAfterStartElement(prefix, uri);
        }
        final String nodeName = (prefix != null) ? qname : localName;
        final String nodeValue = this.m_values.elementAt(valueIndex);
        handler.addAttribute(nodeName, nodeValue);
    }
    
    public void setDocumentURI(final String uri) {
        this.setDocumentBaseURI(uri);
        this._documentURI = uri;
    }
    
    public String getDocumentURI() {
        final String baseURI = this.getDocumentBaseURI();
        return (baseURI != null) ? baseURI : ("rtf" + B2B2DTM._documentURIIndex++);
    }
    
    public String getDocumentURI(final int node) {
        return this.getDocumentURI();
    }
    
    public void setupMapping(final String[] names, final String[] uris, final int[] types, final String[] namespaces) {
    }
    
    public void setupMapping(final String[] names, final String[] uris) {
    }
    
    public String lookupNamespace(final int node, final String prefix) throws TransletException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Lcom/ibm/xslt4j/b2b2dtm/B2B2DTM$AncestorIterator;
        //     3: dup            
        //     4: aload_0         /* this */
        //     5: invokespecial   com/ibm/xslt4j/b2b2dtm/B2B2DTM$AncestorIterator.<init>:(Lcom/ibm/xslt4j/b2b2dtm/B2B2DTM;)V
        //     8: astore          ancestors
        //    10: aload_0         /* this */
        //    11: iload_1         /* node */
        //    12: invokevirtual   com/ibm/xslt4j/b2b2dtm/B2B2DTM.isElement:(I)Z
        //    15: ifeq            24
        //    18: aload           ancestors
        //    20: invokevirtual   org/apache/xml/dtm/ref/DTMAxisIteratorBase.includeSelf:()Lorg/apache/xml/dtm/DTMAxisIterator;
        //    23: pop            
        //    24: aload           ancestors
        //    26: iload_1         /* node */
        //    27: invokevirtual   com/ibm/xslt4j/b2b2dtm/B2B2DTM$AncestorIterator.setStartNode:(I)Lorg/apache/xml/dtm/DTMAxisIterator;
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
        //    57: invokevirtual   com/ibm/xslt4j/b2b2dtm/B2B2DTM.getLocalName:(I)Ljava/lang/String;
        //    60: aload_2         /* prefix */
        //    61: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    64: ifeq            74
        //    67: aload_0         /* this */
        //    68: iload           4
        //    70: invokevirtual   com/ibm/xslt4j/b2b2dtm/B2B2DTM.getNodeValue:(I)Ljava/lang/String;
        //    73: areturn        
        //    74: aload           namespaces
        //    76: invokevirtual   org/apache/xml/dtm/ref/DTMDefaultBaseIterators$NamespaceIterator.next:()I
        //    79: dup            
        //    80: istore          nsnode
        //    82: iconst_m1      
        //    83: if_icmpne       54
        //    86: aload           ancestors
        //    88: invokevirtual   com/ibm/xslt4j/b2b2dtm/B2B2DTM$AncestorIterator.next:()I
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
        //  0      105     0     this        Lcom/ibm/xslt4j/b2b2dtm/B2B2DTM;
        //  0      105     1     node        I
        //  0      105     2     prefix      Ljava/lang/String;
        //  93     12      3     anode       I
        //  82     23      4     nsnode      I
        //  10     95      5     ancestors   Lcom/ibm/xslt4j/b2b2dtm/B2B2DTM$AncestorIterator;
        //  44     42      6     namespaces  Lorg/apache/xml/dtm/ref/DTMDefaultBaseIterators$NamespaceIterator;
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
        return (this._nodes[index] != null) ? this._nodes[index] : (this._nodes[index] = new DTMNodeProxy(this, index));
    }
    
    public Node makeNode(final DTMAxisIterator iter) {
        return this.makeNode(iter.next());
    }
    
    public NodeList makeNodeList(final int index) {
        if (this._nodeLists == null) {
            this._nodeLists = new NodeList[this._namesSize];
        }
        return (this._nodeLists[index] != null) ? this._nodeLists[index] : (this._nodeLists[index] = new DTMAxisIterNodeList(this, new SingletonIterator(this, index)));
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
    
    private int[] setupMapping(final String[] namesArray, final int nNames) {
        final int[] types = new int[super.m_expandedNameTable.getSize()];
        for (int i = 0; i < nNames; ++i) {
            final int type = this.getGeneralizedType(namesArray[i]);
            types[type] = type;
        }
        return types;
    }
    
    public int getGeneralizedType(final String name) {
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
        return this.getExpandedTypeID(ns, lName, code);
    }
    
    public short[] getMapping(final String[] names, final String[] uris, final int[] types) {
        final int namesLength = names.length;
        final int exLength = super.m_expandedNameTable.getSize();
        int[] generalizedTypes = null;
        if (namesLength > 0) {
            generalizedTypes = new int[namesLength];
        }
        int resultLength = exLength;
        for (int i = 0; i < namesLength; ++i) {
            generalizedTypes[i] = super.m_expandedNameTable.getExpandedTypeID(uris[i], names[i], types[i]);
            if (this._namesSize == 0 && generalizedTypes[i] >= resultLength) {
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
            if (this._namesSize > 0) {
                if (genType < result.length) {
                    result[genType] = (short)(i + 14);
                }
            }
            else {
                result[genType] = (short)(i + 14);
            }
        }
        return result;
    }
    
    public short[] getMapping(final String[] names) {
        final int namesLength = names.length;
        final int exLength = super.m_expandedNameTable.getSize();
        int[] generalizedTypes = null;
        if (namesLength > 0) {
            generalizedTypes = new int[namesLength];
        }
        int resultLength = exLength;
        for (int i = 0; i < namesLength; ++i) {
            generalizedTypes[i] = this.getGeneralizedType(names[i]);
            if (this._namesSize == 0 && generalizedTypes[i] >= resultLength) {
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
            if (this._namesSize > 0) {
                if (genType < result.length) {
                    result[genType] = (short)(i + 14);
                }
            }
            else {
                result[genType] = (short)(i + 14);
            }
        }
        return result;
    }
    
    public short[] getMapping(final int[] reverse) {
        final int exLength = super.m_expandedNameTable.getSize();
        final short[] result = new short[exLength];
        for (int i = 0; i < 14; ++i) {
            result[i] = (short)i;
        }
        for (int i = 14; i < exLength; ++i) {
            result[i] = super.m_expandedNameTable.getType(i);
        }
        for (int revLength = reverse.length, i = 14; i < revLength; ++i) {
            final int genType = reverse[i];
            if (genType >= 0 && genType < exLength) {
                result[genType] = (short)i;
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
            final int type = super.m_expandedNameTable.getExpandedTypeID(uris[i], names[i], types[i]);
            result[i + 14] = type;
        }
        return result;
    }
    
    public int[] getReverseMapping(final String[] names) {
        final int[] result = new int[names.length + 14];
        for (int i = 0; i < 14; ++i) {
            result[i] = i;
        }
        for (int i = 0; i < names.length; ++i) {
            final int type = this.getGeneralizedType(names[i]);
            result[i + 14] = type;
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
    
    public boolean hasDOMSource() {
        return this._hasDOMSource;
    }
    
    protected boolean getShouldStripWhitespace() {
        return !this._preserve && super.getShouldStripWhitespace();
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
                return this.getNodeName_super(node);
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
        return new ChildrenIterator().setStartNode(node);
    }
    
    public DTMAxisIterator getTypedChildren(final int type) {
        return new TypedChildrenIterator(type);
    }
    
    public DTMAxisIterator getAxisIterator(final int axis) {
        switch (axis) {
            case 13: {
                return new SingletonIterator(this);
            }
            case 3: {
                return new ChildrenIterator();
            }
            case 10: {
                return new ParentIterator();
            }
            case 0: {
                return new AncestorIterator();
            }
            case 1: {
                return new AncestorIterator().includeSelf();
            }
            case 2: {
                return new AttributeIterator();
            }
            case 4: {
                return new DescendantIterator();
            }
            case 5: {
                return new DescendantIterator().includeSelf();
            }
            case 6: {
                return new FollowingIterator();
            }
            case 11: {
                return new PrecedingIterator();
            }
            case 7: {
                return new FollowingSiblingIterator();
            }
            case 12: {
                return new PrecedingSiblingIterator();
            }
            case 9: {
                return new NamespaceIterator(this);
            }
            default: {
                BasisLibrary.runTimeError("AXIS_SUPPORT_ERR", Axis.getNames(axis));
                return null;
            }
        }
    }
    
    public DTMAxisIterator getTypedAxisIterator(final int axis, final int type) {
        if (axis == 3) {
            return new TypedChildrenIterator(type);
        }
        if (type == -1) {
            return B2B2DTM.EMPTYITERATOR;
        }
        switch (axis) {
            case 13: {
                return new TypedSingletonIterator(type);
            }
            case 3: {
                return new TypedChildrenIterator(type);
            }
            case 10: {
                return new ParentIterator().setNodeType(type);
            }
            case 0: {
                return new TypedAncestorIterator(type);
            }
            case 1: {
                return new TypedAncestorIterator(type).includeSelf();
            }
            case 2: {
                return new TypedAttributeIterator(type);
            }
            case 4: {
                return new TypedDescendantIterator(type);
            }
            case 5: {
                return new TypedDescendantIterator(type).includeSelf();
            }
            case 6: {
                return new TypedFollowingIterator(type);
            }
            case 11: {
                return new TypedPrecedingIterator(type);
            }
            case 7: {
                return new TypedFollowingSiblingIterator(type);
            }
            case 12: {
                return new TypedPrecedingSiblingIterator(type);
            }
            case 9: {
                return (type == 1) ? new NamespaceIterator(this) : new TypedNamespaceIterator(type);
            }
            default: {
                BasisLibrary.runTimeError("TYPED_AXIS_SUPPORT_ERR", Axis.getNames(axis));
                return null;
            }
        }
    }
    
    public DTMAxisIterator getNamespaceAxisIterator(final int axis, final int ns) {
        final DTMAxisIterator iterator = null;
        if (ns == -1) {
            return B2B2DTM.EMPTYITERATOR;
        }
        switch (axis) {
            case 3: {
                return new NamespaceChildrenIterator(this, ns);
            }
            case 2: {
                return new NamespaceAttributeIterator(this, ns);
            }
            default: {
                BasisLibrary.runTimeError("TYPED_AXIS_SUPPORT_ERR", Axis.getNames(axis));
                return null;
            }
        }
    }
    
    public DTMAxisIterator getTypedDescendantIterator(final int type) {
        return new TypedDescendantIterator(type);
    }
    
    public DTMAxisIterator getNthDescendant(final int type, final int n, final boolean includeself) {
        final DTMAxisIterator source = new TypedDescendantIterator(type);
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
        //     6: invokevirtual   com/ibm/xslt4j/b2b2dtm/B2B2DTM.copy:(ILorg/apache/xml/serializer/SerializationHandler;)V
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
        //  0      22      0     this     Lcom/ibm/xslt4j/b2b2dtm/B2B2DTM;
        //  0      22      1     nodes    Lorg/apache/xml/dtm/DTMAxisIterator;
        //  0      22      2     handler  Lorg/apache/xml/serializer/SerializationHandler;
        //  17     4       3     node     I
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
    
    public void copy(final int node, final SerializationHandler handler) throws TransletException {
        final int nodeID = this.makeNodeIdentity(node);
        int eType = this._exptype(nodeID);
        int type = this._type(nodeID);
        try {
            switch (type) {
                case 0:
                case 9: {
                    for (int c = this._firstch(nodeID); c != -1; c = this._nextsib(c)) {
                        this.copy(this.makeNodeHandle(c), handler);
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
                        int current = nodeID;
                        while (true) {
                            ++current;
                            eType = this._exptype(current);
                            type = this._type(current);
                            if (type == 2) {
                                this.copyAttribute(current, eType, handler);
                            }
                            else {
                                if (type != 13) {
                                    break;
                                }
                                handler.namespaceAfterStartElement(this.getNodeNameX(this.makeNodeHandle(current)), this.getNodeValue(this.makeNodeHandle(current)));
                            }
                        }
                        for (int c2 = this._firstch(nodeID); c2 != -1; c2 = this._nextsib(c2)) {
                            this.copy(this.makeNodeHandle(c2), handler);
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
        final int exptype = this._exptype(nodeID);
        final int type = this._type(nodeID);
        try {
            switch (type) {
                case 1: {
                    return this.copyElement(nodeID, exptype, handler);
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
    
    public org.apache.xalan.xsltc.runtime.Hashtable getElementsWithIDs() {
        if (this.m_idAttributes == null) {
            return null;
        }
        final Enumeration idValues = this.m_idAttributes.keys();
        if (!idValues.hasMoreElements()) {
            return null;
        }
        final org.apache.xalan.xsltc.runtime.Hashtable idAttrsTable = new org.apache.xalan.xsltc.runtime.Hashtable();
        while (idValues.hasMoreElements()) {
            final Object idValue = idValues.nextElement();
            idAttrsTable.put(idValue, this.m_idAttributes.get(idValue));
        }
        return idAttrsTable;
    }
    
    public int stylesheetTypeToDocumentType(final int atWhichNode, final int xsltc_node_type) {
        return xsltc_node_type;
    }
    
    public SerializationHandler getOutputDomBuilder() {
        return null;
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
                return (SAXImpl)this._dtmManager.getDTM(null, true, super.m_wsfilter, true, false, false, initSize, false);
            }
            if (addToManager) {
                final int dtmPos = this._dtmManager.getFirstFreeDTMID();
                final AdaptiveResultTreeImpl rtf2 = new AdaptiveResultTreeImpl(this._dtmManager, dtmPos << 16, super.m_wsfilter, initSize, false);
                this._dtmManager.addDTM(rtf2, dtmPos, 0);
                return rtf2;
            }
            return new AdaptiveResultTreeImpl(this._dtmManager, 0, super.m_wsfilter, initSize, false);
        }
    }
    
    public String getStringValue() {
        return this.getStringValueX(this.getDocument());
    }
    
    static {
        EMPTY_STRING = "".intern();
        B2B2DTM.m_staticReaderParser = new ThreadLocal();
        B2B2DTM.m_staticStreamParser = new ThreadLocal();
        m_fixednames = new String[] { null, null, null, "#text", "#cdata_section", null, null, null, "#comment", "#document", null, "#document-fragment", null };
        EMPTY_XML_STR = new XMLStringDefault("");
        EMPTYITERATOR = EmptyIterator.getInstance();
        B2B2DTM._documentURIIndex = 0;
    }
    
    public final class ChildrenIterator extends InternalAxisIteratorBase
    {
        public DTMAxisIterator setStartNode(int node) {
            if (node == 0) {
                node = B2B2DTM.this.getDocument();
            }
            if (super._isRestartable) {
                super._startNode = node;
                super._currentNode = ((node == -1) ? -1 : B2B2DTM.this._firstch2(B2B2DTM.this.makeNodeIdentity(node)));
                return this.resetPosition();
            }
            return this;
        }
        
        public int next() {
            if (super._currentNode != -1) {
                final int node = super._currentNode;
                super._currentNode = B2B2DTM.this._nextsib2(node);
                return this.returnNode(B2B2DTM.this.makeNodeHandle(node));
            }
            return -1;
        }
    }
    
    public final class ParentIterator extends InternalAxisIteratorBase
    {
        private int _nodeType;
        
        public ParentIterator() {
            this._nodeType = -1;
        }
        
        public DTMAxisIterator setStartNode(int node) {
            if (node == 0) {
                node = B2B2DTM.this.getDocument();
            }
            if (super._isRestartable) {
                if ((super._startNode = node) != -1) {
                    super._currentNode = B2B2DTM.this._parent2(B2B2DTM.this.makeNodeIdentity(node));
                }
                else {
                    super._currentNode = -1;
                }
                return this.resetPosition();
            }
            return this;
        }
        
        public DTMAxisIterator setNodeType(final int type) {
            this._nodeType = type;
            return this;
        }
        
        public int next() {
            final int result = super._currentNode;
            if (result == -1) {
                return -1;
            }
            if (this._nodeType == -1) {
                super._currentNode = -1;
                return this.returnNode(B2B2DTM.this.makeNodeHandle(result));
            }
            if (this._nodeType >= 14) {
                if (this._nodeType == B2B2DTM.this._exptype2(result)) {
                    super._currentNode = -1;
                    return this.returnNode(B2B2DTM.this.makeNodeHandle(result));
                }
            }
            else if (this._nodeType == B2B2DTM.this._type2(result)) {
                super._currentNode = -1;
                return this.returnNode(B2B2DTM.this.makeNodeHandle(result));
            }
            return -1;
        }
    }
    
    public final class TypedChildrenIterator extends InternalAxisIteratorBase
    {
        private final int _nodeType;
        
        public TypedChildrenIterator(final int nodeType) {
            this._nodeType = nodeType;
        }
        
        public DTMAxisIterator setStartNode(int node) {
            if (node == 0) {
                node = B2B2DTM.this.getDocument();
            }
            if (super._isRestartable) {
                super._startNode = node;
                super._currentNode = ((node == -1) ? -1 : B2B2DTM.this._firstch2(B2B2DTM.this.makeNodeIdentity(super._startNode)));
                return this.resetPosition();
            }
            return this;
        }
        
        public int next() {
            int node = super._currentNode;
            if (node == -1) {
                return -1;
            }
            final int nodeType = this._nodeType;
            if (nodeType != 1) {
                while (node != -1) {
                    if (B2B2DTM.this._exptype2(node) == nodeType) {
                        break;
                    }
                    node = B2B2DTM.this._nextsib2(node);
                }
            }
            else {
                while (node != -1) {
                    final int eType = B2B2DTM.this._exptype2(node);
                    if (eType >= 14) {
                        break;
                    }
                    node = B2B2DTM.this._nextsib2(node);
                }
            }
            if (node == -1) {
                return super._currentNode = -1;
            }
            super._currentNode = B2B2DTM.this._nextsib2(node);
            return this.returnNode(B2B2DTM.this.makeNodeHandle(node));
        }
        
        public int getNodeByPosition(final int position) {
            if (position <= 0) {
                return -1;
            }
            int node = super._currentNode;
            int pos = 0;
            final int nodeType = this._nodeType;
            if (nodeType != 1) {
                while (node != -1) {
                    if (B2B2DTM.this._exptype2(node) == nodeType && ++pos == position) {
                        return B2B2DTM.this.makeNodeHandle(node);
                    }
                    node = B2B2DTM.this._nextsib2(node);
                }
                return -1;
            }
            while (node != -1) {
                if (B2B2DTM.this._exptype2(node) >= 14 && ++pos == position) {
                    return B2B2DTM.this.makeNodeHandle(node);
                }
                node = B2B2DTM.this._nextsib2(node);
            }
            return -1;
        }
    }
    
    public class TypedRootIterator extends RootIterator
    {
        private final int _nodeType;
        
        public TypedRootIterator(final int nodeType) {
            this._nodeType = nodeType;
        }
        
        public int next() {
            if (super._startNode == super._currentNode) {
                return -1;
            }
            final int node = super._startNode;
            final int expType = B2B2DTM.this._exptype2(B2B2DTM.this.makeNodeIdentity(node));
            super._currentNode = node;
            if (this._nodeType >= 14) {
                if (this._nodeType == expType) {
                    return this.returnNode(node);
                }
            }
            else if (expType < 14) {
                if (expType == this._nodeType) {
                    return this.returnNode(node);
                }
            }
            else if (B2B2DTM.this.m_extendedTypes[expType].getNodeType() == this._nodeType) {
                return this.returnNode(node);
            }
            return -1;
        }
    }
    
    public class FollowingSiblingIterator extends InternalAxisIteratorBase
    {
        public DTMAxisIterator setStartNode(int node) {
            if (node == 0) {
                node = B2B2DTM.this.getDocument();
            }
            if (super._isRestartable) {
                super._startNode = node;
                super._currentNode = B2B2DTM.this.makeNodeIdentity(node);
                return this.resetPosition();
            }
            return this;
        }
        
        public int next() {
            super._currentNode = ((super._currentNode == -1) ? -1 : B2B2DTM.this._nextsib2(super._currentNode));
            return this.returnNode(B2B2DTM.this.makeNodeHandle(super._currentNode));
        }
    }
    
    public final class TypedFollowingSiblingIterator extends FollowingSiblingIterator
    {
        private final int _nodeType;
        
        public TypedFollowingSiblingIterator(final int type) {
            this._nodeType = type;
        }
        
        public int next() {
            if (super._currentNode == -1) {
                return -1;
            }
            int node = super._currentNode;
            final int nodeType = this._nodeType;
            if (nodeType != 1) {
                while ((node = B2B2DTM.this._nextsib2(node)) != -1) {
                    if (B2B2DTM.this._exptype2(node) == nodeType) {
                        break;
                    }
                }
            }
            else {
                while ((node = B2B2DTM.this._nextsib2(node)) != -1 && B2B2DTM.this._exptype2(node) < 14) {}
            }
            return ((super._currentNode = node) == -1) ? -1 : this.returnNode(B2B2DTM.this.makeNodeHandle(node));
        }
    }
    
    public final class AttributeIterator extends InternalAxisIteratorBase
    {
        public DTMAxisIterator setStartNode(int node) {
            if (node == 0) {
                node = B2B2DTM.this.getDocument();
            }
            if (super._isRestartable) {
                super._startNode = node;
                super._currentNode = B2B2DTM.this.getFirstAttributeIdentity(B2B2DTM.this.makeNodeIdentity(node));
                return this.resetPosition();
            }
            return this;
        }
        
        public int next() {
            final int node = super._currentNode;
            if (node != -1) {
                super._currentNode = B2B2DTM.this.getNextAttributeIdentity(node);
                return this.returnNode(B2B2DTM.this.makeNodeHandle(node));
            }
            return -1;
        }
    }
    
    public final class TypedAttributeIterator extends InternalAxisIteratorBase
    {
        private final int _nodeType;
        
        public TypedAttributeIterator(final int nodeType) {
            this._nodeType = nodeType;
        }
        
        public DTMAxisIterator setStartNode(final int node) {
            if (super._isRestartable) {
                super._startNode = node;
                super._currentNode = B2B2DTM.this.getTypedAttribute(node, this._nodeType);
                return this.resetPosition();
            }
            return this;
        }
        
        public int next() {
            final int node = super._currentNode;
            super._currentNode = -1;
            return this.returnNode(node);
        }
    }
    
    public class PrecedingSiblingIterator extends InternalAxisIteratorBase
    {
        protected int _startNodeID;
        
        public boolean isReverse() {
            return true;
        }
        
        public DTMAxisIterator setStartNode(int node) {
            if (node == 0) {
                node = B2B2DTM.this.getDocument();
            }
            if (!super._isRestartable) {
                return this;
            }
            super._startNode = node;
            final int nodeIdentity = B2B2DTM.this.makeNodeIdentity(node);
            this._startNodeID = nodeIdentity;
            node = nodeIdentity;
            if (node == -1) {
                super._currentNode = node;
                return this.resetPosition();
            }
            final int type = B2B2DTM.this._type2(node);
            if (2 == type || 13 == type) {
                super._currentNode = node;
            }
            else {
                super._currentNode = B2B2DTM.this._parent2(node);
                if (-1 != super._currentNode) {
                    super._currentNode = B2B2DTM.this._firstch2(super._currentNode);
                }
                else {
                    super._currentNode = node;
                }
            }
            return this.resetPosition();
        }
        
        public int next() {
            if (super._currentNode == this._startNodeID || super._currentNode == -1) {
                return -1;
            }
            final int node = super._currentNode;
            super._currentNode = B2B2DTM.this._nextsib2(node);
            return this.returnNode(B2B2DTM.this.makeNodeHandle(node));
        }
    }
    
    public final class TypedPrecedingSiblingIterator extends PrecedingSiblingIterator
    {
        private final int _nodeType;
        
        public TypedPrecedingSiblingIterator(final int type) {
            this._nodeType = type;
        }
        
        public int next() {
            int node = super._currentNode;
            final int nodeType = this._nodeType;
            final int startNodeID = super._startNodeID;
            if (nodeType != 1) {
                while (node != -1 && node != startNodeID) {
                    if (B2B2DTM.this._exptype2(node) == nodeType) {
                        break;
                    }
                    node = B2B2DTM.this._nextsib2(node);
                }
            }
            else {
                while (node != -1 && node != startNodeID && B2B2DTM.this._exptype2(node) < 14) {
                    node = B2B2DTM.this._nextsib2(node);
                }
            }
            if (node == -1 || node == startNodeID) {
                return super._currentNode = -1;
            }
            super._currentNode = B2B2DTM.this._nextsib2(node);
            return this.returnNode(B2B2DTM.this.makeNodeHandle(node));
        }
        
        public int getLast() {
            if (super._last != -1) {
                return super._last;
            }
            this.setMark();
            int node = super._currentNode;
            final int nodeType = this._nodeType;
            final int startNodeID = super._startNodeID;
            int last = 0;
            if (nodeType != 1) {
                while (node != -1) {
                    if (node == startNodeID) {
                        break;
                    }
                    if (B2B2DTM.this._exptype2(node) == nodeType) {
                        ++last;
                    }
                    node = B2B2DTM.this._nextsib2(node);
                }
            }
            else {
                while (node != -1 && node != startNodeID) {
                    if (B2B2DTM.this._exptype2(node) >= 14) {
                        ++last;
                    }
                    node = B2B2DTM.this._nextsib2(node);
                }
            }
            this.gotoMark();
            return super._last = last;
        }
    }
    
    public class PrecedingIterator extends InternalAxisIteratorBase
    {
        private final int _maxAncestors = 8;
        protected int[] _stack;
        protected int _sp;
        protected int _oldsp;
        protected int _markedsp;
        protected int _markedNode;
        protected int _markedDescendant;
        
        public PrecedingIterator() {
            this._stack = new int[8];
        }
        
        public boolean isReverse() {
            return true;
        }
        
        public DTMAxisIterator cloneIterator() {
            super._isRestartable = false;
            try {
                final PrecedingIterator clone = (PrecedingIterator)super.clone();
                final int[] stackCopy = new int[this._stack.length];
                System.arraycopy(this._stack, 0, stackCopy, 0, this._stack.length);
                clone._stack = stackCopy;
                return clone;
            }
            catch (CloneNotSupportedException e) {
                throw new DTMException(XMLMessages.createXMLMessage("ER_ITERATOR_CLONE_NOT_SUPPORTED", null));
            }
        }
        
        public DTMAxisIterator setStartNode(int node) {
            if (node == 0) {
                node = B2B2DTM.this.getDocument();
            }
            if (super._isRestartable) {
                node = B2B2DTM.this.makeNodeIdentity(node);
                if (B2B2DTM.this._type2(node) == 2) {
                    node = B2B2DTM.this._parent2(node);
                }
                super._startNode = node;
                int index;
                this._stack[index = 0] = node;
                int parent = node;
                while ((parent = B2B2DTM.this._parent2(parent)) != -1) {
                    if (++index == this._stack.length) {
                        final int[] stack = new int[index * 2];
                        System.arraycopy(this._stack, 0, stack, 0, index);
                        this._stack = stack;
                    }
                    this._stack[index] = parent;
                }
                if (index > 0) {
                    --index;
                }
                super._currentNode = this._stack[index];
                final int n = index;
                this._sp = n;
                this._oldsp = n;
                return this.resetPosition();
            }
            return this;
        }
        
        public int next() {
            ++super._currentNode;
            while (this._sp >= 0) {
                if (super._currentNode < this._stack[this._sp]) {
                    final int type = B2B2DTM.this._type2(super._currentNode);
                    if (type != 2 && type != 13) {
                        return this.returnNode(B2B2DTM.this.makeNodeHandle(super._currentNode));
                    }
                }
                else {
                    --this._sp;
                }
                ++super._currentNode;
            }
            return -1;
        }
        
        public DTMAxisIterator reset() {
            this._sp = this._oldsp;
            return this.resetPosition();
        }
        
        public void setMark() {
            this._markedsp = this._sp;
            this._markedNode = super._currentNode;
            this._markedDescendant = this._stack[0];
        }
        
        public void gotoMark() {
            this._sp = this._markedsp;
            super._currentNode = this._markedNode;
        }
    }
    
    public final class TypedPrecedingIterator extends PrecedingIterator
    {
        private final int _nodeType;
        
        public TypedPrecedingIterator(final int type) {
            this._nodeType = type;
        }
        
        public int next() {
            int node = super._currentNode;
            final int nodeType = this._nodeType;
            if (nodeType >= 14) {
                while (true) {
                    ++node;
                    if (super._sp < 0) {
                        node = -1;
                        break;
                    }
                    if (node >= super._stack[super._sp]) {
                        if (--super._sp < 0) {
                            node = -1;
                            break;
                        }
                        continue;
                    }
                    else {
                        if (B2B2DTM.this._exptype2(node) == nodeType) {
                            break;
                        }
                        continue;
                    }
                }
            }
            else {
                while (true) {
                    ++node;
                    if (super._sp < 0) {
                        node = -1;
                        break;
                    }
                    if (node >= super._stack[super._sp]) {
                        if (--super._sp < 0) {
                            node = -1;
                            break;
                        }
                        continue;
                    }
                    else {
                        final int expType = B2B2DTM.this._exptype2(node);
                        if (expType < 14) {
                            if (expType == nodeType) {
                                break;
                            }
                            continue;
                        }
                        else {
                            if (B2B2DTM.this.m_extendedTypes[expType].getNodeType() == nodeType) {
                                break;
                            }
                            continue;
                        }
                    }
                }
            }
            super._currentNode = node;
            return (node == -1) ? -1 : this.returnNode(B2B2DTM.this.makeNodeHandle(node));
        }
    }
    
    public class FollowingIterator extends InternalAxisIteratorBase
    {
        public DTMAxisIterator setStartNode(int node) {
            if (node == 0) {
                node = B2B2DTM.this.getDocument();
            }
            if (super._isRestartable) {
                super._startNode = node;
                node = B2B2DTM.this.makeNodeIdentity(node);
                final int type = B2B2DTM.this._type2(node);
                if (2 == type || 13 == type) {
                    node = B2B2DTM.this._parent2(node);
                    final int first = B2B2DTM.this._firstch2(node);
                    if (-1 != first) {
                        super._currentNode = B2B2DTM.this.makeNodeHandle(first);
                        return this.resetPosition();
                    }
                }
                int first;
                do {
                    first = B2B2DTM.this._nextsib2(node);
                    if (-1 == first) {
                        node = B2B2DTM.this._parent2(node);
                    }
                } while (-1 == first && -1 != node);
                super._currentNode = B2B2DTM.this.makeNodeHandle(first);
                return this.resetPosition();
            }
            return this;
        }
        
        public int next() {
            final int node = super._currentNode;
            int current = B2B2DTM.this.makeNodeIdentity(node);
            while (true) {
                ++current;
                final int type = B2B2DTM.this._type2(current);
                if (-1 == type) {
                    super._currentNode = -1;
                    return this.returnNode(node);
                }
                if (2 == type) {
                    continue;
                }
                if (13 == type) {
                    continue;
                }
                super._currentNode = B2B2DTM.this.makeNodeHandle(current);
                return this.returnNode(node);
            }
        }
    }
    
    public final class TypedFollowingIterator extends FollowingIterator
    {
        private final int _nodeType;
        
        public TypedFollowingIterator(final int type) {
            this._nodeType = type;
        }
        
        public int next() {
            final int nodeType = this._nodeType;
            int currentNodeID = B2B2DTM.this.makeNodeIdentity(super._currentNode);
            int node;
            if (nodeType >= 14) {
                do {
                    int current;
                    node = (current = currentNodeID);
                    int type;
                    do {
                        ++current;
                        type = B2B2DTM.this._type2(current);
                    } while (type != -1 && (2 == type || 13 == type));
                    currentNodeID = ((type != -1) ? current : -1);
                    if (node != -1) {
                        continue;
                    }
                    break;
                } while (B2B2DTM.this._exptype2(node) != nodeType);
            }
            else {
                do {
                    int current;
                    node = (current = currentNodeID);
                    int type;
                    do {
                        ++current;
                        type = B2B2DTM.this._type2(current);
                    } while (type != -1 && (2 == type || 13 == type));
                    currentNodeID = ((type != -1) ? current : -1);
                } while (node != -1 && B2B2DTM.this._exptype2(node) != nodeType && B2B2DTM.this._type2(node) != nodeType);
            }
            super._currentNode = B2B2DTM.this.makeNodeHandle(currentNodeID);
            return (node == -1) ? -1 : this.returnNode(B2B2DTM.this.makeNodeHandle(node));
        }
    }
    
    public class AncestorIterator extends InternalAxisIteratorBase
    {
        private static final int m_blocksize = 32;
        int[] m_ancestors;
        int m_size;
        int m_ancestorsPos;
        int m_markedPos;
        int m_realStartNode;
        
        public AncestorIterator() {
            this.m_ancestors = new int[32];
            this.m_size = 0;
        }
        
        public int getStartNode() {
            return this.m_realStartNode;
        }
        
        public final boolean isReverse() {
            return true;
        }
        
        public DTMAxisIterator cloneIterator() {
            super._isRestartable = false;
            try {
                final AncestorIterator clone = (AncestorIterator)super.clone();
                clone._startNode = super._startNode;
                return clone;
            }
            catch (CloneNotSupportedException e) {
                throw new DTMException(XMLMessages.createXMLMessage("ER_ITERATOR_CLONE_NOT_SUPPORTED", null));
            }
        }
        
        public DTMAxisIterator setStartNode(int node) {
            if (node == 0) {
                node = B2B2DTM.this.getDocument();
            }
            this.m_realStartNode = node;
            if (!super._isRestartable) {
                return this;
            }
            int nodeID = B2B2DTM.this.makeNodeIdentity(node);
            if (nodeID == -1) {
                super._currentNode = -1;
                this.m_ancestorsPos = 0;
                return this;
            }
            if (!super._includeSelf) {
                nodeID = B2B2DTM.this._parent2(nodeID);
                node = B2B2DTM.this.makeNodeHandle(nodeID);
            }
            super._startNode = node;
            while (nodeID != -1) {
                if (this.m_size >= this.m_ancestors.length) {
                    final int[] newAncestors = new int[this.m_size * 2];
                    System.arraycopy(this.m_ancestors, 0, newAncestors, 0, this.m_ancestors.length);
                    this.m_ancestors = newAncestors;
                }
                this.m_ancestors[this.m_size++] = node;
                nodeID = B2B2DTM.this._parent2(nodeID);
                node = B2B2DTM.this.makeNodeHandle(nodeID);
            }
            this.m_ancestorsPos = this.m_size - 1;
            super._currentNode = ((this.m_ancestorsPos >= 0) ? this.m_ancestors[this.m_ancestorsPos] : -1);
            return this.resetPosition();
        }
        
        public DTMAxisIterator reset() {
            this.m_ancestorsPos = this.m_size - 1;
            super._currentNode = ((this.m_ancestorsPos >= 0) ? this.m_ancestors[this.m_ancestorsPos] : -1);
            return this.resetPosition();
        }
        
        public int next() {
            final int next = super._currentNode;
            final int ancestorsPos = this.m_ancestorsPos - 1;
            this.m_ancestorsPos = ancestorsPos;
            final int pos = ancestorsPos;
            super._currentNode = ((pos >= 0) ? this.m_ancestors[this.m_ancestorsPos] : -1);
            return this.returnNode(next);
        }
        
        public void setMark() {
            this.m_markedPos = this.m_ancestorsPos;
        }
        
        public void gotoMark() {
            this.m_ancestorsPos = this.m_markedPos;
            super._currentNode = ((this.m_ancestorsPos >= 0) ? this.m_ancestors[this.m_ancestorsPos] : -1);
        }
    }
    
    public final class TypedAncestorIterator extends AncestorIterator
    {
        private final int _nodeType;
        
        public TypedAncestorIterator(final int type) {
            this._nodeType = type;
        }
        
        public DTMAxisIterator setStartNode(int node) {
            if (node == 0) {
                node = B2B2DTM.this.getDocument();
            }
            super.m_realStartNode = node;
            if (!super._isRestartable) {
                return this;
            }
            int nodeID = B2B2DTM.this.makeNodeIdentity(node);
            if (nodeID == -1) {
                super._currentNode = -1;
                super.m_ancestorsPos = 0;
                return this;
            }
            final int nodeType = this._nodeType;
            if (!super._includeSelf) {
                nodeID = B2B2DTM.this._parent2(nodeID);
                node = B2B2DTM.this.makeNodeHandle(nodeID);
            }
            super._startNode = node;
            if (nodeType >= 14) {
                while (nodeID != -1) {
                    final int eType = B2B2DTM.this._exptype2(nodeID);
                    if (eType == nodeType) {
                        if (super.m_size >= super.m_ancestors.length) {
                            final int[] newAncestors = new int[super.m_size * 2];
                            System.arraycopy(super.m_ancestors, 0, newAncestors, 0, super.m_ancestors.length);
                            super.m_ancestors = newAncestors;
                        }
                        super.m_ancestors[super.m_size++] = B2B2DTM.this.makeNodeHandle(nodeID);
                    }
                    nodeID = B2B2DTM.this._parent2(nodeID);
                }
            }
            else {
                while (nodeID != -1) {
                    final int eType = B2B2DTM.this._exptype2(nodeID);
                    if ((eType < 14 && eType == nodeType) || (eType >= 14 && B2B2DTM.this.m_extendedTypes[eType].getNodeType() == nodeType)) {
                        if (super.m_size >= super.m_ancestors.length) {
                            final int[] newAncestors = new int[super.m_size * 2];
                            System.arraycopy(super.m_ancestors, 0, newAncestors, 0, super.m_ancestors.length);
                            super.m_ancestors = newAncestors;
                        }
                        super.m_ancestors[super.m_size++] = B2B2DTM.this.makeNodeHandle(nodeID);
                    }
                    nodeID = B2B2DTM.this._parent2(nodeID);
                }
            }
            super.m_ancestorsPos = super.m_size - 1;
            super._currentNode = ((super.m_ancestorsPos >= 0) ? super.m_ancestors[super.m_ancestorsPos] : -1);
            return this.resetPosition();
        }
        
        public int getNodeByPosition(final int position) {
            if (position > 0 && position <= super.m_size) {
                return super.m_ancestors[position - 1];
            }
            return -1;
        }
        
        public int getLast() {
            return super.m_size;
        }
    }
    
    public class DescendantIterator extends InternalAxisIteratorBase
    {
        public DTMAxisIterator setStartNode(int node) {
            if (node == 0) {
                node = B2B2DTM.this.getDocument();
            }
            if (super._isRestartable) {
                node = B2B2DTM.this.makeNodeIdentity(node);
                super._startNode = node;
                if (super._includeSelf) {
                    --node;
                }
                super._currentNode = node;
                return this.resetPosition();
            }
            return this;
        }
        
        protected final boolean isDescendant(final int identity) {
            return B2B2DTM.this._parent2(identity) >= super._startNode || super._startNode == identity;
        }
        
        public int next() {
            final int startNode = super._startNode;
            if (startNode == -1) {
                return -1;
            }
            if (super._includeSelf && super._currentNode + 1 == startNode) {
                return this.returnNode(B2B2DTM.this.makeNodeHandle(++super._currentNode));
            }
            int node = super._currentNode;
            if (startNode == 0) {
                int eType;
                int type;
                do {
                    ++node;
                    eType = B2B2DTM.this._exptype2(node);
                    if (-1 == eType) {
                        return super._currentNode = -1;
                    }
                } while (eType == 3 || (type = B2B2DTM.this.m_extendedTypes[eType].getNodeType()) == 2 || type == 13);
            }
            else {
                int type;
                do {
                    ++node;
                    type = B2B2DTM.this._type2(node);
                    if (-1 == type || !this.isDescendant(node)) {
                        return super._currentNode = -1;
                    }
                } while (2 == type || 3 == type || 13 == type);
            }
            super._currentNode = node;
            return this.returnNode(B2B2DTM.this.makeNodeHandle(node));
        }
        
        public DTMAxisIterator reset() {
            final boolean temp = super._isRestartable;
            super._isRestartable = true;
            this.setStartNode(B2B2DTM.this.makeNodeHandle(super._startNode));
            super._isRestartable = temp;
            return this;
        }
    }
    
    public final class TypedDescendantIterator extends DescendantIterator
    {
        private final int _nodeType;
        
        public TypedDescendantIterator(final int nodeType) {
            this._nodeType = nodeType;
        }
        
        public int next() {
            final int startNode = super._startNode;
            if (super._startNode == -1) {
                return -1;
            }
            int node = super._currentNode;
            final int nodeType = this._nodeType;
            if (nodeType != 1) {
                int expType;
                do {
                    ++node;
                    expType = B2B2DTM.this._exptype2(node);
                    if (-1 == expType || (B2B2DTM.this._parent2(node) < startNode && startNode != node)) {
                        return super._currentNode = -1;
                    }
                } while (expType != nodeType);
            }
            else if (startNode == 0) {
                int expType;
                do {
                    ++node;
                    expType = B2B2DTM.this._exptype2(node);
                    if (-1 == expType) {
                        return super._currentNode = -1;
                    }
                } while (expType < 14 || B2B2DTM.this.m_extendedTypes[expType].getNodeType() != 1);
            }
            else {
                int expType;
                do {
                    ++node;
                    expType = B2B2DTM.this._exptype2(node);
                    if (-1 == expType || (B2B2DTM.this._parent2(node) < startNode && startNode != node)) {
                        return super._currentNode = -1;
                    }
                } while (expType < 14 || B2B2DTM.this.m_extendedTypes[expType].getNodeType() != 1);
            }
            super._currentNode = node;
            return this.returnNode(B2B2DTM.this.makeNodeHandle(node));
        }
    }
    
    public final class TypedSingletonIterator extends SingletonIterator
    {
        private final int _nodeType;
        
        public TypedSingletonIterator(final int nodeType) {
            this._nodeType = nodeType;
        }
        
        public int next() {
            final int result = super._currentNode;
            if (result == -1) {
                return -1;
            }
            super._currentNode = -1;
            if (this._nodeType >= 14) {
                if (B2B2DTM.this._exptype2(B2B2DTM.this.makeNodeIdentity(result)) == this._nodeType) {
                    return this.returnNode(result);
                }
            }
            else if (B2B2DTM.this._type2(B2B2DTM.this.makeNodeIdentity(result)) == this._nodeType) {
                return this.returnNode(result);
            }
            return -1;
        }
    }
    
    public class TypedNamespaceIterator extends NamespaceIterator
    {
        private final int _nodeType;
        
        public TypedNamespaceIterator(final int nodeType) {
            this._nodeType = nodeType;
        }
        
        public int next() {
            for (int node = super.next(); node != -1; node = super.next()) {
                if (B2B2DTM.this.getExpandedTypeID(node) == this._nodeType || B2B2DTM.this.getNodeType(node) == this._nodeType || B2B2DTM.this.getIdForNamespace(B2B2DTM.this.getStringValueX(node)) == this._nodeType) {
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
            //     4: getfield        com/ibm/xslt4j/b2b2dtm/B2B2DTM$NodeValueIterator.this$0:Lcom/ibm/xslt4j/b2b2dtm/B2B2DTM;
            //     7: iload_1        
            //     8: invokevirtual   com/ibm/xslt4j/b2b2dtm/B2B2DTM.getStringValueX:(I)Ljava/lang/String;
            //    11: astore_2        /* val */
            //    12: aload_0         /* this */
            //    13: getfield        com/ibm/xslt4j/b2b2dtm/B2B2DTM$NodeValueIterator._value:Ljava/lang/String;
            //    16: aload_2         /* val */
            //    17: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
            //    20: aload_0         /* this */
            //    21: getfield        com/ibm/xslt4j/b2b2dtm/B2B2DTM$NodeValueIterator._op:Z
            //    24: if_icmpne       53
            //    27: aload_0         /* this */
            //    28: getfield        com/ibm/xslt4j/b2b2dtm/B2B2DTM$NodeValueIterator._returnType:I
            //    31: ifne            40
            //    34: aload_0         /* this */
            //    35: iload_1        
            //    36: invokevirtual   org/apache/xml/dtm/ref/DTMAxisIteratorBase.returnNode:(I)I
            //    39: ireturn        
            //    40: aload_0         /* this */
            //    41: aload_0         /* this */
            //    42: getfield        com/ibm/xslt4j/b2b2dtm/B2B2DTM$NodeValueIterator.this$0:Lcom/ibm/xslt4j/b2b2dtm/B2B2DTM;
            //    45: iload_1        
            //    46: invokevirtual   org/apache/xml/dtm/ref/DTMDefaultBase.getParent:(I)I
            //    49: invokevirtual   org/apache/xml/dtm/ref/DTMAxisIteratorBase.returnNode:(I)I
            //    52: ireturn        
            //    53: aload_0         /* this */
            //    54: getfield        com/ibm/xslt4j/b2b2dtm/B2B2DTM$NodeValueIterator._source:Lorg/apache/xml/dtm/DTMAxisIterator;
            //    57: invokeinterface org/apache/xml/dtm/DTMAxisIterator.next:()I
            //    62: dup            
            //    63: istore_1        /* node */
            //    64: iconst_m1      
            //    65: if_icmpne       3
            //    68: iconst_m1      
            //    69: ireturn        
            //    LocalVariableTable:
            //  Start  Length  Slot  Name  Signature
            //  -----  ------  ----  ----  --------------------------------------------------
            //  0      70      0     this  Lcom/ibm/xslt4j/b2b2dtm/B2B2DTM$NodeValueIterator;
            //  64     6       1     node  I
            //  12     41      2     val   Ljava/lang/String;
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
}
