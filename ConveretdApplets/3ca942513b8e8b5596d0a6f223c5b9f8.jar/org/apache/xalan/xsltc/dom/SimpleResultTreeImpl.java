// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.dom;

import org.apache.xml.dtm.ref.DTMAxisIteratorBase;
import org.apache.xml.dtm.DTMManager;
import javax.xml.transform.SourceLocator;
import org.xml.sax.ext.DeclHandler;
import org.xml.sax.ErrorHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.ContentHandler;
import org.apache.xml.utils.XMLStringDefault;
import org.apache.xml.utils.XMLString;
import org.apache.xml.dtm.DTMAxisTraverser;
import org.apache.xalan.xsltc.runtime.Hashtable;
import org.apache.xalan.xsltc.StripFilter;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import org.apache.xalan.xsltc.TransletException;
import org.apache.xml.serializer.SerializationHandler;
import org.apache.xml.dtm.ref.DTMManagerDefault;
import org.apache.xml.dtm.DTMAxisIterator;
import org.apache.xml.dtm.DTM;
import org.apache.xalan.xsltc.DOM;
import org.apache.xml.serializer.EmptySerializer;

public class SimpleResultTreeImpl extends EmptySerializer implements DOM, DTM
{
    private static final DTMAxisIterator EMPTY_ITERATOR;
    public static final int RTF_ROOT = 0;
    public static final int RTF_TEXT = 1;
    public static final int NUMBER_OF_NODES = 2;
    private static int _documentURIIndex;
    private static final String EMPTY_STR = "";
    private String _text;
    protected String[] _textArray;
    protected XSLTCDTMManager _dtmManager;
    protected int _size;
    private int _documentID;
    private BitArray _dontEscape;
    private boolean _escaping;
    
    public SimpleResultTreeImpl(final XSLTCDTMManager dtmManager, final int documentID) {
        this._size = 0;
        this._dontEscape = null;
        this._escaping = true;
        this._dtmManager = dtmManager;
        this._documentID = documentID;
        this._textArray = new String[4];
    }
    
    public DTMManagerDefault getDTMManager() {
        return this._dtmManager;
    }
    
    public int getDocument() {
        return this._documentID;
    }
    
    public String getStringValue() {
        return this._text;
    }
    
    public DTMAxisIterator getIterator() {
        return new SingletonIterator(this.getDocument());
    }
    
    public DTMAxisIterator getChildren(final int node) {
        return new SimpleIterator().setStartNode(node);
    }
    
    public DTMAxisIterator getTypedChildren(final int type) {
        return new SimpleIterator(1, type);
    }
    
    public DTMAxisIterator getAxisIterator(final int axis) {
        switch (axis) {
            case 3:
            case 4: {
                return new SimpleIterator(1);
            }
            case 0:
            case 10: {
                return new SimpleIterator(0);
            }
            case 1: {
                return new SimpleIterator(0).includeSelf();
            }
            case 5: {
                return new SimpleIterator(1).includeSelf();
            }
            case 13: {
                return new SingletonIterator();
            }
            default: {
                return SimpleResultTreeImpl.EMPTY_ITERATOR;
            }
        }
    }
    
    public DTMAxisIterator getTypedAxisIterator(final int axis, final int type) {
        switch (axis) {
            case 3:
            case 4: {
                return new SimpleIterator(1, type);
            }
            case 0:
            case 10: {
                return new SimpleIterator(0, type);
            }
            case 1: {
                return new SimpleIterator(0, type).includeSelf();
            }
            case 5: {
                return new SimpleIterator(1, type).includeSelf();
            }
            case 13: {
                return new SingletonIterator(type);
            }
            default: {
                return SimpleResultTreeImpl.EMPTY_ITERATOR;
            }
        }
    }
    
    public DTMAxisIterator getNthDescendant(final int node, final int n, final boolean includeself) {
        return null;
    }
    
    public DTMAxisIterator getNamespaceAxisIterator(final int axis, final int ns) {
        return null;
    }
    
    public DTMAxisIterator getNodeValueIterator(final DTMAxisIterator iter, final int returnType, final String value, final boolean op) {
        return null;
    }
    
    public DTMAxisIterator orderNodes(final DTMAxisIterator source, final int node) {
        return source;
    }
    
    public String getNodeName(final int node) {
        if (this.getNodeIdent(node) == 1) {
            return "#text";
        }
        return "";
    }
    
    public String getNodeNameX(final int node) {
        return "";
    }
    
    public String getNamespaceName(final int node) {
        return "";
    }
    
    public int getExpandedTypeID(final int nodeHandle) {
        final int nodeID = this.getNodeIdent(nodeHandle);
        if (nodeID == 1) {
            return 3;
        }
        if (nodeID == 0) {
            return 0;
        }
        return -1;
    }
    
    public int getNamespaceType(final int node) {
        return 0;
    }
    
    public int getParent(final int nodeHandle) {
        final int nodeID = this.getNodeIdent(nodeHandle);
        return (nodeID == 1) ? this.getNodeHandle(0) : -1;
    }
    
    public int getAttributeNode(final int gType, final int element) {
        return -1;
    }
    
    public String getStringValueX(final int nodeHandle) {
        final int nodeID = this.getNodeIdent(nodeHandle);
        if (nodeID == 0 || nodeID == 1) {
            return this._text;
        }
        return "";
    }
    
    public void copy(final int node, final SerializationHandler handler) throws TransletException {
        this.characters(node, handler);
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
        //     6: invokevirtual   org/apache/xalan/xsltc/dom/SimpleResultTreeImpl.copy:(ILorg/apache/xml/serializer/SerializationHandler;)V
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
        //  -----  ------  ----  -------  -------------------------------------------------
        //  0      22      0     this     Lorg/apache/xalan/xsltc/dom/SimpleResultTreeImpl;
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
    
    public String shallowCopy(final int node, final SerializationHandler handler) throws TransletException {
        this.characters(node, handler);
        return null;
    }
    
    public boolean lessThan(final int node1, final int node2) {
        return node1 != -1 && (node2 == -1 || node1 < node2);
    }
    
    public void characters(final int node, final SerializationHandler handler) throws TransletException {
        final int nodeID = this.getNodeIdent(node);
        if (nodeID == 0 || nodeID == 1) {
            boolean escapeBit = false;
            boolean oldEscapeSetting = false;
            try {
                for (int i = 0; i < this._size; ++i) {
                    if (this._dontEscape != null) {
                        escapeBit = this._dontEscape.getBit(i);
                        if (escapeBit) {
                            oldEscapeSetting = handler.setEscaping(false);
                        }
                    }
                    handler.characters(this._textArray[i]);
                    if (escapeBit) {
                        handler.setEscaping(oldEscapeSetting);
                    }
                }
            }
            catch (SAXException e) {
                throw new TransletException(e);
            }
        }
    }
    
    public Node makeNode(final int index) {
        return null;
    }
    
    public Node makeNode(final DTMAxisIterator iter) {
        return null;
    }
    
    public NodeList makeNodeList(final int index) {
        return null;
    }
    
    public NodeList makeNodeList(final DTMAxisIterator iter) {
        return null;
    }
    
    public String getLanguage(final int node) {
        return null;
    }
    
    public int getSize() {
        return 2;
    }
    
    public String getDocumentURI(final int node) {
        return "simple_rtf" + SimpleResultTreeImpl._documentURIIndex++;
    }
    
    public void setFilter(final StripFilter filter) {
    }
    
    public void setupMapping(final String[] names, final String[] uris, final int[] types, final String[] namespaces) {
    }
    
    public boolean isElement(final int node) {
        return false;
    }
    
    public boolean isAttribute(final int node) {
        return false;
    }
    
    public String lookupNamespace(final int node, final String prefix) throws TransletException {
        return null;
    }
    
    public int getNodeIdent(final int nodehandle) {
        return (nodehandle != -1) ? (nodehandle - this._documentID) : -1;
    }
    
    public int getNodeHandle(final int nodeId) {
        return (nodeId != -1) ? (nodeId + this._documentID) : -1;
    }
    
    public DOM getResultTreeFrag(final int initialSize, final int rtfType) {
        return null;
    }
    
    public DOM getResultTreeFrag(final int initialSize, final int rtfType, final boolean addToManager) {
        return null;
    }
    
    public SerializationHandler getOutputDomBuilder() {
        return this;
    }
    
    public int getNSType(final int node) {
        return 0;
    }
    
    public String getUnparsedEntityURI(final String name) {
        return null;
    }
    
    public Hashtable getElementsWithIDs() {
        return null;
    }
    
    public void startDocument() throws SAXException {
    }
    
    public void endDocument() throws SAXException {
        if (this._size == 1) {
            this._text = this._textArray[0];
        }
        else {
            final StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < this._size; ++i) {
                buffer.append(this._textArray[i]);
            }
            this._text = buffer.toString();
        }
    }
    
    public void characters(final String str) throws SAXException {
        if (this._size >= this._textArray.length) {
            final String[] newTextArray = new String[this._textArray.length * 2];
            System.arraycopy(this._textArray, 0, newTextArray, 0, this._textArray.length);
            this._textArray = newTextArray;
        }
        if (!this._escaping) {
            if (this._dontEscape == null) {
                this._dontEscape = new BitArray(8);
            }
            if (this._size >= this._dontEscape.size()) {
                this._dontEscape.resize(this._dontEscape.size() * 2);
            }
            this._dontEscape.setBit(this._size);
        }
        this._textArray[this._size++] = str;
    }
    
    public void characters(final char[] ch, final int offset, final int length) throws SAXException {
        if (this._size >= this._textArray.length) {
            final String[] newTextArray = new String[this._textArray.length * 2];
            System.arraycopy(this._textArray, 0, newTextArray, 0, this._textArray.length);
            this._textArray = newTextArray;
        }
        if (!this._escaping) {
            if (this._dontEscape == null) {
                this._dontEscape = new BitArray(8);
            }
            if (this._size >= this._dontEscape.size()) {
                this._dontEscape.resize(this._dontEscape.size() * 2);
            }
            this._dontEscape.setBit(this._size);
        }
        this._textArray[this._size++] = new String(ch, offset, length);
    }
    
    public boolean setEscaping(final boolean escape) throws SAXException {
        final boolean temp = this._escaping;
        this._escaping = escape;
        return temp;
    }
    
    public void setFeature(final String featureId, final boolean state) {
    }
    
    public void setProperty(final String property, final Object value) {
    }
    
    public DTMAxisTraverser getAxisTraverser(final int axis) {
        return null;
    }
    
    public boolean hasChildNodes(final int nodeHandle) {
        return this.getNodeIdent(nodeHandle) == 0;
    }
    
    public int getFirstChild(final int nodeHandle) {
        final int nodeID = this.getNodeIdent(nodeHandle);
        if (nodeID == 0) {
            return this.getNodeHandle(1);
        }
        return -1;
    }
    
    public int getLastChild(final int nodeHandle) {
        return this.getFirstChild(nodeHandle);
    }
    
    public int getAttributeNode(final int elementHandle, final String namespaceURI, final String name) {
        return -1;
    }
    
    public int getFirstAttribute(final int nodeHandle) {
        return -1;
    }
    
    public int getFirstNamespaceNode(final int nodeHandle, final boolean inScope) {
        return -1;
    }
    
    public int getNextSibling(final int nodeHandle) {
        return -1;
    }
    
    public int getPreviousSibling(final int nodeHandle) {
        return -1;
    }
    
    public int getNextAttribute(final int nodeHandle) {
        return -1;
    }
    
    public int getNextNamespaceNode(final int baseHandle, final int namespaceHandle, final boolean inScope) {
        return -1;
    }
    
    public int getOwnerDocument(final int nodeHandle) {
        return this.getDocument();
    }
    
    public int getDocumentRoot(final int nodeHandle) {
        return this.getDocument();
    }
    
    public XMLString getStringValue(final int nodeHandle) {
        return new XMLStringDefault(this.getStringValueX(nodeHandle));
    }
    
    public int getStringValueChunkCount(final int nodeHandle) {
        return 0;
    }
    
    public char[] getStringValueChunk(final int nodeHandle, final int chunkIndex, final int[] startAndLen) {
        return null;
    }
    
    public int getExpandedTypeID(final String namespace, final String localName, final int type) {
        return -1;
    }
    
    public String getLocalNameFromExpandedNameID(final int ExpandedNameID) {
        return "";
    }
    
    public String getNamespaceFromExpandedNameID(final int ExpandedNameID) {
        return "";
    }
    
    public String getLocalName(final int nodeHandle) {
        return "";
    }
    
    public String getPrefix(final int nodeHandle) {
        return null;
    }
    
    public String getNamespaceURI(final int nodeHandle) {
        return "";
    }
    
    public String getNodeValue(final int nodeHandle) {
        return (this.getNodeIdent(nodeHandle) == 1) ? this._text : null;
    }
    
    public short getNodeType(final int nodeHandle) {
        final int nodeID = this.getNodeIdent(nodeHandle);
        if (nodeID == 1) {
            return 3;
        }
        if (nodeID == 0) {
            return 0;
        }
        return -1;
    }
    
    public short getLevel(final int nodeHandle) {
        final int nodeID = this.getNodeIdent(nodeHandle);
        if (nodeID == 1) {
            return 2;
        }
        if (nodeID == 0) {
            return 1;
        }
        return -1;
    }
    
    public boolean isSupported(final String feature, final String version) {
        return false;
    }
    
    public String getDocumentBaseURI() {
        return "";
    }
    
    public void setDocumentBaseURI(final String baseURI) {
    }
    
    public String getDocumentSystemIdentifier(final int nodeHandle) {
        return null;
    }
    
    public String getDocumentEncoding(final int nodeHandle) {
        return null;
    }
    
    public String getDocumentStandalone(final int nodeHandle) {
        return null;
    }
    
    public String getDocumentVersion(final int documentHandle) {
        return null;
    }
    
    public boolean getDocumentAllDeclarationsProcessed() {
        return false;
    }
    
    public String getDocumentTypeDeclarationSystemIdentifier() {
        return null;
    }
    
    public String getDocumentTypeDeclarationPublicIdentifier() {
        return null;
    }
    
    public int getElementById(final String elementId) {
        return -1;
    }
    
    public boolean supportsPreStripping() {
        return false;
    }
    
    public boolean isNodeAfter(final int firstNodeHandle, final int secondNodeHandle) {
        return this.lessThan(firstNodeHandle, secondNodeHandle);
    }
    
    public boolean isCharacterElementContentWhitespace(final int nodeHandle) {
        return false;
    }
    
    public boolean isDocumentAllDeclarationsProcessed(final int documentHandle) {
        return false;
    }
    
    public boolean isAttributeSpecified(final int attributeHandle) {
        return false;
    }
    
    public void dispatchCharactersEvents(final int nodeHandle, final ContentHandler ch, final boolean normalize) throws SAXException {
    }
    
    public void dispatchToEvents(final int nodeHandle, final ContentHandler ch) throws SAXException {
    }
    
    public Node getNode(final int nodeHandle) {
        return this.makeNode(nodeHandle);
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
    
    public void appendChild(final int newChild, final boolean clone, final boolean cloneDepth) {
    }
    
    public void appendTextChild(final String str) {
    }
    
    public SourceLocator getSourceLocatorFor(final int node) {
        return null;
    }
    
    public void documentRegistration() {
    }
    
    public void documentRelease() {
    }
    
    public void migrateTo(final DTMManager manager) {
    }
    
    static {
        EMPTY_ITERATOR = new DTMAxisIteratorBase() {
            public DTMAxisIterator reset() {
                return this;
            }
            
            public DTMAxisIterator setStartNode(final int node) {
                return this;
            }
            
            public int next() {
                return -1;
            }
            
            public void setMark() {
            }
            
            public void gotoMark() {
            }
            
            public int getLast() {
                return 0;
            }
            
            public int getPosition() {
                return 0;
            }
            
            public DTMAxisIterator cloneIterator() {
                return this;
            }
            
            public void setRestartable(final boolean isRestartable) {
            }
        };
        SimpleResultTreeImpl._documentURIIndex = 0;
    }
    
    public final class SimpleIterator extends DTMAxisIteratorBase
    {
        static final int DIRECTION_UP = 0;
        static final int DIRECTION_DOWN = 1;
        static final int NO_TYPE = -1;
        int _direction;
        int _type;
        int _currentNode;
        
        public SimpleIterator() {
            this._direction = 1;
            this._type = -1;
        }
        
        public SimpleIterator(final int direction) {
            this._direction = 1;
            this._type = -1;
            this._direction = direction;
        }
        
        public SimpleIterator(final int direction, final int type) {
            this._direction = 1;
            this._type = -1;
            this._direction = direction;
            this._type = type;
        }
        
        public int next() {
            if (this._direction == 1) {
                while (this._currentNode < 2) {
                    if (this._type == -1) {
                        return this.returnNode(SimpleResultTreeImpl.this.getNodeHandle(this._currentNode++));
                    }
                    if ((this._currentNode == 0 && this._type == 0) || (this._currentNode == 1 && this._type == 3)) {
                        return this.returnNode(SimpleResultTreeImpl.this.getNodeHandle(this._currentNode++));
                    }
                    ++this._currentNode;
                }
                return -1;
            }
            while (this._currentNode >= 0) {
                if (this._type == -1) {
                    return this.returnNode(SimpleResultTreeImpl.this.getNodeHandle(this._currentNode--));
                }
                if ((this._currentNode == 0 && this._type == 0) || (this._currentNode == 1 && this._type == 3)) {
                    return this.returnNode(SimpleResultTreeImpl.this.getNodeHandle(this._currentNode--));
                }
                --this._currentNode;
            }
            return -1;
        }
        
        public DTMAxisIterator setStartNode(final int nodeHandle) {
            int nodeID = SimpleResultTreeImpl.this.getNodeIdent(nodeHandle);
            super._startNode = nodeID;
            if (!super._includeSelf && nodeID != -1) {
                if (this._direction == 1) {
                    ++nodeID;
                }
                else if (this._direction == 0) {
                    --nodeID;
                }
            }
            this._currentNode = nodeID;
            return this;
        }
        
        public void setMark() {
            super._markedNode = this._currentNode;
        }
        
        public void gotoMark() {
            this._currentNode = super._markedNode;
        }
    }
    
    public final class SingletonIterator extends DTMAxisIteratorBase
    {
        static final int NO_TYPE = -1;
        int _type;
        int _currentNode;
        
        public SingletonIterator() {
            this._type = -1;
        }
        
        public SingletonIterator(final int type) {
            this._type = -1;
            this._type = type;
        }
        
        public void setMark() {
            super._markedNode = this._currentNode;
        }
        
        public void gotoMark() {
            this._currentNode = super._markedNode;
        }
        
        public DTMAxisIterator setStartNode(final int nodeHandle) {
            final int nodeIdent = SimpleResultTreeImpl.this.getNodeIdent(nodeHandle);
            super._startNode = nodeIdent;
            this._currentNode = nodeIdent;
            return this;
        }
        
        public int next() {
            if (this._currentNode == -1) {
                return -1;
            }
            this._currentNode = -1;
            if (this._type == -1) {
                return SimpleResultTreeImpl.this.getNodeHandle(this._currentNode);
            }
            if ((this._currentNode == 0 && this._type == 0) || (this._currentNode == 1 && this._type == 3)) {
                return SimpleResultTreeImpl.this.getNodeHandle(this._currentNode);
            }
            return -1;
        }
    }
}
