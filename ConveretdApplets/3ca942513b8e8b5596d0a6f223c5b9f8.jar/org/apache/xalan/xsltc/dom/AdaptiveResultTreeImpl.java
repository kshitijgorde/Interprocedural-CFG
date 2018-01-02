// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.dom;

import javax.xml.transform.SourceLocator;
import org.xml.sax.ext.DeclHandler;
import org.xml.sax.ErrorHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.ContentHandler;
import org.apache.xml.utils.XMLString;
import org.apache.xml.dtm.DTMAxisTraverser;
import org.apache.xalan.xsltc.runtime.BasisLibrary;
import javax.xml.transform.Source;
import org.xml.sax.SAXException;
import org.xml.sax.Attributes;
import org.apache.xalan.xsltc.runtime.Hashtable;
import org.apache.xalan.xsltc.StripFilter;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.apache.xalan.xsltc.TransletException;
import org.apache.xml.serializer.SerializationHandler;
import org.apache.xml.dtm.DTMAxisIterator;
import org.apache.xalan.xsltc.DOM;
import org.apache.xalan.xsltc.runtime.AttributeList;
import org.apache.xml.dtm.DTMWSFilter;

public class AdaptiveResultTreeImpl extends SimpleResultTreeImpl
{
    private static int _documentURIIndex;
    private SAXImpl _dom;
    private DTMWSFilter _wsfilter;
    private int _initSize;
    private boolean _buildIdIndex;
    private final AttributeList _attributes;
    private String _openElementName;
    
    public AdaptiveResultTreeImpl(final XSLTCDTMManager dtmManager, final int documentID, final DTMWSFilter wsfilter, final int initSize, final boolean buildIdIndex) {
        super(dtmManager, documentID);
        this._attributes = new AttributeList();
        this._wsfilter = wsfilter;
        this._initSize = initSize;
        this._buildIdIndex = buildIdIndex;
    }
    
    public DOM getNestedDOM() {
        return this._dom;
    }
    
    public int getDocument() {
        if (this._dom != null) {
            return this._dom.getDocument();
        }
        return super.getDocument();
    }
    
    public String getStringValue() {
        if (this._dom != null) {
            return this._dom.getStringValue();
        }
        return super.getStringValue();
    }
    
    public DTMAxisIterator getIterator() {
        if (this._dom != null) {
            return this._dom.getIterator();
        }
        return super.getIterator();
    }
    
    public DTMAxisIterator getChildren(final int node) {
        if (this._dom != null) {
            return this._dom.getChildren(node);
        }
        return super.getChildren(node);
    }
    
    public DTMAxisIterator getTypedChildren(final int type) {
        if (this._dom != null) {
            return this._dom.getTypedChildren(type);
        }
        return super.getTypedChildren(type);
    }
    
    public DTMAxisIterator getAxisIterator(final int axis) {
        if (this._dom != null) {
            return this._dom.getAxisIterator(axis);
        }
        return super.getAxisIterator(axis);
    }
    
    public DTMAxisIterator getTypedAxisIterator(final int axis, final int type) {
        if (this._dom != null) {
            return this._dom.getTypedAxisIterator(axis, type);
        }
        return super.getTypedAxisIterator(axis, type);
    }
    
    public DTMAxisIterator getNthDescendant(final int node, final int n, final boolean includeself) {
        if (this._dom != null) {
            return this._dom.getNthDescendant(node, n, includeself);
        }
        return super.getNthDescendant(node, n, includeself);
    }
    
    public DTMAxisIterator getNamespaceAxisIterator(final int axis, final int ns) {
        if (this._dom != null) {
            return this._dom.getNamespaceAxisIterator(axis, ns);
        }
        return super.getNamespaceAxisIterator(axis, ns);
    }
    
    public DTMAxisIterator getNodeValueIterator(final DTMAxisIterator iter, final int returnType, final String value, final boolean op) {
        if (this._dom != null) {
            return this._dom.getNodeValueIterator(iter, returnType, value, op);
        }
        return super.getNodeValueIterator(iter, returnType, value, op);
    }
    
    public DTMAxisIterator orderNodes(final DTMAxisIterator source, final int node) {
        if (this._dom != null) {
            return this._dom.orderNodes(source, node);
        }
        return super.orderNodes(source, node);
    }
    
    public String getNodeName(final int node) {
        if (this._dom != null) {
            return this._dom.getNodeName(node);
        }
        return super.getNodeName(node);
    }
    
    public String getNodeNameX(final int node) {
        if (this._dom != null) {
            return this._dom.getNodeNameX(node);
        }
        return super.getNodeNameX(node);
    }
    
    public String getNamespaceName(final int node) {
        if (this._dom != null) {
            return this._dom.getNamespaceName(node);
        }
        return super.getNamespaceName(node);
    }
    
    public int getExpandedTypeID(final int nodeHandle) {
        if (this._dom != null) {
            return this._dom.getExpandedTypeID(nodeHandle);
        }
        return super.getExpandedTypeID(nodeHandle);
    }
    
    public int getNamespaceType(final int node) {
        if (this._dom != null) {
            return this._dom.getNamespaceType(node);
        }
        return super.getNamespaceType(node);
    }
    
    public int getParent(final int nodeHandle) {
        if (this._dom != null) {
            return this._dom.getParent(nodeHandle);
        }
        return super.getParent(nodeHandle);
    }
    
    public int getAttributeNode(final int gType, final int element) {
        if (this._dom != null) {
            return this._dom.getAttributeNode(gType, element);
        }
        return super.getAttributeNode(gType, element);
    }
    
    public String getStringValueX(final int nodeHandle) {
        if (this._dom != null) {
            return this._dom.getStringValueX(nodeHandle);
        }
        return super.getStringValueX(nodeHandle);
    }
    
    public void copy(final int node, final SerializationHandler handler) throws TransletException {
        if (this._dom != null) {
            this._dom.copy(node, handler);
        }
        else {
            super.copy(node, handler);
        }
    }
    
    public void copy(final DTMAxisIterator nodes, final SerializationHandler handler) throws TransletException {
        if (this._dom != null) {
            this._dom.copy(nodes, handler);
        }
        else {
            super.copy(nodes, handler);
        }
    }
    
    public String shallowCopy(final int node, final SerializationHandler handler) throws TransletException {
        if (this._dom != null) {
            return this._dom.shallowCopy(node, handler);
        }
        return super.shallowCopy(node, handler);
    }
    
    public boolean lessThan(final int node1, final int node2) {
        if (this._dom != null) {
            return this._dom.lessThan(node1, node2);
        }
        return super.lessThan(node1, node2);
    }
    
    public void characters(final int node, final SerializationHandler handler) throws TransletException {
        if (this._dom != null) {
            this._dom.characters(node, handler);
        }
        else {
            super.characters(node, handler);
        }
    }
    
    public Node makeNode(final int index) {
        if (this._dom != null) {
            return this._dom.makeNode(index);
        }
        return super.makeNode(index);
    }
    
    public Node makeNode(final DTMAxisIterator iter) {
        if (this._dom != null) {
            return this._dom.makeNode(iter);
        }
        return super.makeNode(iter);
    }
    
    public NodeList makeNodeList(final int index) {
        if (this._dom != null) {
            return this._dom.makeNodeList(index);
        }
        return super.makeNodeList(index);
    }
    
    public NodeList makeNodeList(final DTMAxisIterator iter) {
        if (this._dom != null) {
            return this._dom.makeNodeList(iter);
        }
        return super.makeNodeList(iter);
    }
    
    public String getLanguage(final int node) {
        if (this._dom != null) {
            return this._dom.getLanguage(node);
        }
        return super.getLanguage(node);
    }
    
    public int getSize() {
        if (this._dom != null) {
            return this._dom.getSize();
        }
        return super.getSize();
    }
    
    public String getDocumentURI(final int node) {
        if (this._dom != null) {
            return this._dom.getDocumentURI(node);
        }
        return "adaptive_rtf" + AdaptiveResultTreeImpl._documentURIIndex++;
    }
    
    public void setFilter(final StripFilter filter) {
        if (this._dom != null) {
            this._dom.setFilter(filter);
        }
        else {
            super.setFilter(filter);
        }
    }
    
    public void setupMapping(final String[] names, final String[] uris, final int[] types, final String[] namespaces) {
        if (this._dom != null) {
            this._dom.setupMapping(names, uris, types, namespaces);
        }
        else {
            super.setupMapping(names, uris, types, namespaces);
        }
    }
    
    public boolean isElement(final int node) {
        if (this._dom != null) {
            return this._dom.isElement(node);
        }
        return super.isElement(node);
    }
    
    public boolean isAttribute(final int node) {
        if (this._dom != null) {
            return this._dom.isAttribute(node);
        }
        return super.isAttribute(node);
    }
    
    public String lookupNamespace(final int node, final String prefix) throws TransletException {
        if (this._dom != null) {
            return this._dom.lookupNamespace(node, prefix);
        }
        return super.lookupNamespace(node, prefix);
    }
    
    public final int getNodeIdent(final int nodehandle) {
        if (this._dom != null) {
            return this._dom.getNodeIdent(nodehandle);
        }
        return super.getNodeIdent(nodehandle);
    }
    
    public final int getNodeHandle(final int nodeId) {
        if (this._dom != null) {
            return this._dom.getNodeHandle(nodeId);
        }
        return super.getNodeHandle(nodeId);
    }
    
    public DOM getResultTreeFrag(final int initialSize, final int rtfType) {
        if (this._dom != null) {
            return this._dom.getResultTreeFrag(initialSize, rtfType);
        }
        return super.getResultTreeFrag(initialSize, rtfType);
    }
    
    public SerializationHandler getOutputDomBuilder() {
        return this;
    }
    
    public int getNSType(final int node) {
        if (this._dom != null) {
            return this._dom.getNSType(node);
        }
        return super.getNSType(node);
    }
    
    public String getUnparsedEntityURI(final String name) {
        if (this._dom != null) {
            return this._dom.getUnparsedEntityURI(name);
        }
        return super.getUnparsedEntityURI(name);
    }
    
    public Hashtable getElementsWithIDs() {
        if (this._dom != null) {
            return this._dom.getElementsWithIDs();
        }
        return super.getElementsWithIDs();
    }
    
    private void maybeEmitStartElement() throws SAXException {
        if (this._openElementName != null) {
            final int index;
            if ((index = this._openElementName.indexOf(":")) < 0) {
                this._dom.startElement(null, this._openElementName, this._openElementName, this._attributes);
            }
            else {
                final String uri = this.getNamespaceURIFromPrefix(this._openElementName.substring(0, index));
                this._dom.startElement(uri, this._openElementName.substring(index + 1), this._openElementName, this._attributes);
            }
            this._openElementName = null;
        }
    }
    
    private void prepareNewDOM() throws SAXException {
        (this._dom = (SAXImpl)super._dtmManager.getDTM(null, true, this._wsfilter, true, false, false, this._initSize, this._buildIdIndex)).startDocument();
        for (int i = 0; i < super._size; ++i) {
            final String str = super._textArray[i];
            this._dom.characters(str.toCharArray(), 0, str.length());
        }
        super._size = 0;
    }
    
    public void startDocument() throws SAXException {
    }
    
    public void endDocument() throws SAXException {
        if (this._dom != null) {
            this._dom.endDocument();
        }
        else {
            super.endDocument();
        }
    }
    
    public void characters(final String str) throws SAXException {
        if (this._dom != null) {
            this.characters(str.toCharArray(), 0, str.length());
        }
        else {
            super.characters(str);
        }
    }
    
    public void characters(final char[] ch, final int offset, final int length) throws SAXException {
        if (this._dom != null) {
            this.maybeEmitStartElement();
            this._dom.characters(ch, offset, length);
        }
        else {
            super.characters(ch, offset, length);
        }
    }
    
    public boolean setEscaping(final boolean escape) throws SAXException {
        if (this._dom != null) {
            return this._dom.setEscaping(escape);
        }
        return super.setEscaping(escape);
    }
    
    public void startElement(final String elementName) throws SAXException {
        if (this._dom == null) {
            this.prepareNewDOM();
        }
        this.maybeEmitStartElement();
        this._openElementName = elementName;
        this._attributes.clear();
    }
    
    public void startElement(final String uri, final String localName, final String qName) throws SAXException {
        this.startElement(qName);
    }
    
    public void startElement(final String uri, final String localName, final String qName, final Attributes attributes) throws SAXException {
        this.startElement(qName);
    }
    
    public void endElement(final String elementName) throws SAXException {
        this.maybeEmitStartElement();
        this._dom.endElement(null, null, elementName);
    }
    
    public void endElement(final String uri, final String localName, final String qName) throws SAXException {
        this.endElement(qName);
    }
    
    public void addUniqueAttribute(final String qName, final String value, final int flags) throws SAXException {
        this.addAttribute(qName, value);
    }
    
    public void addAttribute(final String name, final String value) {
        if (this._openElementName != null) {
            this._attributes.add(name, value);
        }
        else {
            BasisLibrary.runTimeError("STRAY_ATTRIBUTE_ERR", name);
        }
    }
    
    public void namespaceAfterStartElement(final String prefix, final String uri) throws SAXException {
        if (this._dom == null) {
            this.prepareNewDOM();
        }
        this._dom.startPrefixMapping(prefix, uri);
    }
    
    public void comment(final String comment) throws SAXException {
        if (this._dom == null) {
            this.prepareNewDOM();
        }
        this.maybeEmitStartElement();
        final char[] chars = comment.toCharArray();
        this._dom.comment(chars, 0, chars.length);
    }
    
    public void comment(final char[] chars, final int offset, final int length) throws SAXException {
        if (this._dom == null) {
            this.prepareNewDOM();
        }
        this.maybeEmitStartElement();
        this._dom.comment(chars, offset, length);
    }
    
    public void processingInstruction(final String target, final String data) throws SAXException {
        if (this._dom == null) {
            this.prepareNewDOM();
        }
        this.maybeEmitStartElement();
        this._dom.processingInstruction(target, data);
    }
    
    public void setFeature(final String featureId, final boolean state) {
        if (this._dom != null) {
            this._dom.setFeature(featureId, state);
        }
    }
    
    public void setProperty(final String property, final Object value) {
        if (this._dom != null) {
            this._dom.setProperty(property, value);
        }
    }
    
    public DTMAxisTraverser getAxisTraverser(final int axis) {
        if (this._dom != null) {
            return this._dom.getAxisTraverser(axis);
        }
        return super.getAxisTraverser(axis);
    }
    
    public boolean hasChildNodes(final int nodeHandle) {
        if (this._dom != null) {
            return this._dom.hasChildNodes(nodeHandle);
        }
        return super.hasChildNodes(nodeHandle);
    }
    
    public int getFirstChild(final int nodeHandle) {
        if (this._dom != null) {
            return this._dom.getFirstChild(nodeHandle);
        }
        return super.getFirstChild(nodeHandle);
    }
    
    public int getLastChild(final int nodeHandle) {
        if (this._dom != null) {
            return this._dom.getLastChild(nodeHandle);
        }
        return super.getLastChild(nodeHandle);
    }
    
    public int getAttributeNode(final int elementHandle, final String namespaceURI, final String name) {
        if (this._dom != null) {
            return this._dom.getAttributeNode(elementHandle, namespaceURI, name);
        }
        return super.getAttributeNode(elementHandle, namespaceURI, name);
    }
    
    public int getFirstAttribute(final int nodeHandle) {
        if (this._dom != null) {
            return this._dom.getFirstAttribute(nodeHandle);
        }
        return super.getFirstAttribute(nodeHandle);
    }
    
    public int getFirstNamespaceNode(final int nodeHandle, final boolean inScope) {
        if (this._dom != null) {
            return this._dom.getFirstNamespaceNode(nodeHandle, inScope);
        }
        return super.getFirstNamespaceNode(nodeHandle, inScope);
    }
    
    public int getNextSibling(final int nodeHandle) {
        if (this._dom != null) {
            return this._dom.getNextSibling(nodeHandle);
        }
        return super.getNextSibling(nodeHandle);
    }
    
    public int getPreviousSibling(final int nodeHandle) {
        if (this._dom != null) {
            return this._dom.getPreviousSibling(nodeHandle);
        }
        return super.getPreviousSibling(nodeHandle);
    }
    
    public int getNextAttribute(final int nodeHandle) {
        if (this._dom != null) {
            return this._dom.getNextAttribute(nodeHandle);
        }
        return super.getNextAttribute(nodeHandle);
    }
    
    public int getNextNamespaceNode(final int baseHandle, final int namespaceHandle, final boolean inScope) {
        if (this._dom != null) {
            return this._dom.getNextNamespaceNode(baseHandle, namespaceHandle, inScope);
        }
        return super.getNextNamespaceNode(baseHandle, namespaceHandle, inScope);
    }
    
    public int getOwnerDocument(final int nodeHandle) {
        if (this._dom != null) {
            return this._dom.getOwnerDocument(nodeHandle);
        }
        return super.getOwnerDocument(nodeHandle);
    }
    
    public int getDocumentRoot(final int nodeHandle) {
        if (this._dom != null) {
            return this._dom.getDocumentRoot(nodeHandle);
        }
        return super.getDocumentRoot(nodeHandle);
    }
    
    public XMLString getStringValue(final int nodeHandle) {
        if (this._dom != null) {
            return this._dom.getStringValue(nodeHandle);
        }
        return super.getStringValue(nodeHandle);
    }
    
    public int getStringValueChunkCount(final int nodeHandle) {
        if (this._dom != null) {
            return this._dom.getStringValueChunkCount(nodeHandle);
        }
        return super.getStringValueChunkCount(nodeHandle);
    }
    
    public char[] getStringValueChunk(final int nodeHandle, final int chunkIndex, final int[] startAndLen) {
        if (this._dom != null) {
            return this._dom.getStringValueChunk(nodeHandle, chunkIndex, startAndLen);
        }
        return super.getStringValueChunk(nodeHandle, chunkIndex, startAndLen);
    }
    
    public int getExpandedTypeID(final String namespace, final String localName, final int type) {
        if (this._dom != null) {
            return this._dom.getExpandedTypeID(namespace, localName, type);
        }
        return super.getExpandedTypeID(namespace, localName, type);
    }
    
    public String getLocalNameFromExpandedNameID(final int ExpandedNameID) {
        if (this._dom != null) {
            return this._dom.getLocalNameFromExpandedNameID(ExpandedNameID);
        }
        return super.getLocalNameFromExpandedNameID(ExpandedNameID);
    }
    
    public String getNamespaceFromExpandedNameID(final int ExpandedNameID) {
        if (this._dom != null) {
            return this._dom.getNamespaceFromExpandedNameID(ExpandedNameID);
        }
        return super.getNamespaceFromExpandedNameID(ExpandedNameID);
    }
    
    public String getNamespaceURIFromPrefix(final String prefix) {
        if (this._dom != null) {
            return this._dom.getNamespaceURI(prefix);
        }
        return super.getNamespaceURIFromPrefix(prefix);
    }
    
    public String getLocalName(final int nodeHandle) {
        if (this._dom != null) {
            return this._dom.getLocalName(nodeHandle);
        }
        return super.getLocalName(nodeHandle);
    }
    
    public String getPrefix(final int nodeHandle) {
        if (this._dom != null) {
            return this._dom.getPrefix(nodeHandle);
        }
        return super.getPrefix(nodeHandle);
    }
    
    public String getNamespaceURI(final int nodeHandle) {
        if (this._dom != null) {
            return this._dom.getNamespaceURI(nodeHandle);
        }
        return super.getNamespaceURI(nodeHandle);
    }
    
    public String getNodeValue(final int nodeHandle) {
        if (this._dom != null) {
            return this._dom.getNodeValue(nodeHandle);
        }
        return super.getNodeValue(nodeHandle);
    }
    
    public short getNodeType(final int nodeHandle) {
        if (this._dom != null) {
            return this._dom.getNodeType(nodeHandle);
        }
        return super.getNodeType(nodeHandle);
    }
    
    public short getLevel(final int nodeHandle) {
        if (this._dom != null) {
            return this._dom.getLevel(nodeHandle);
        }
        return super.getLevel(nodeHandle);
    }
    
    public boolean isSupported(final String feature, final String version) {
        if (this._dom != null) {
            return this._dom.isSupported(feature, version);
        }
        return super.isSupported(feature, version);
    }
    
    public String getDocumentBaseURI() {
        if (this._dom != null) {
            return this._dom.getDocumentBaseURI();
        }
        return super.getDocumentBaseURI();
    }
    
    public void setDocumentBaseURI(final String baseURI) {
        if (this._dom != null) {
            this._dom.setDocumentBaseURI(baseURI);
        }
        else {
            super.setDocumentBaseURI(baseURI);
        }
    }
    
    public String getDocumentSystemIdentifier(final int nodeHandle) {
        if (this._dom != null) {
            return this._dom.getDocumentSystemIdentifier(nodeHandle);
        }
        return super.getDocumentSystemIdentifier(nodeHandle);
    }
    
    public String getDocumentEncoding(final int nodeHandle) {
        if (this._dom != null) {
            return this._dom.getDocumentEncoding(nodeHandle);
        }
        return super.getDocumentEncoding(nodeHandle);
    }
    
    public String getDocumentStandalone(final int nodeHandle) {
        if (this._dom != null) {
            return this._dom.getDocumentStandalone(nodeHandle);
        }
        return super.getDocumentStandalone(nodeHandle);
    }
    
    public String getDocumentVersion(final int documentHandle) {
        if (this._dom != null) {
            return this._dom.getDocumentVersion(documentHandle);
        }
        return super.getDocumentVersion(documentHandle);
    }
    
    public boolean getDocumentAllDeclarationsProcessed() {
        if (this._dom != null) {
            return this._dom.getDocumentAllDeclarationsProcessed();
        }
        return super.getDocumentAllDeclarationsProcessed();
    }
    
    public String getDocumentTypeDeclarationSystemIdentifier() {
        if (this._dom != null) {
            return this._dom.getDocumentTypeDeclarationSystemIdentifier();
        }
        return super.getDocumentTypeDeclarationSystemIdentifier();
    }
    
    public String getDocumentTypeDeclarationPublicIdentifier() {
        if (this._dom != null) {
            return this._dom.getDocumentTypeDeclarationPublicIdentifier();
        }
        return super.getDocumentTypeDeclarationPublicIdentifier();
    }
    
    public int getElementById(final String elementId) {
        if (this._dom != null) {
            return this._dom.getElementById(elementId);
        }
        return super.getElementById(elementId);
    }
    
    public boolean supportsPreStripping() {
        if (this._dom != null) {
            return this._dom.supportsPreStripping();
        }
        return super.supportsPreStripping();
    }
    
    public boolean isNodeAfter(final int firstNodeHandle, final int secondNodeHandle) {
        if (this._dom != null) {
            return this._dom.isNodeAfter(firstNodeHandle, secondNodeHandle);
        }
        return super.isNodeAfter(firstNodeHandle, secondNodeHandle);
    }
    
    public boolean isCharacterElementContentWhitespace(final int nodeHandle) {
        if (this._dom != null) {
            return this._dom.isCharacterElementContentWhitespace(nodeHandle);
        }
        return super.isCharacterElementContentWhitespace(nodeHandle);
    }
    
    public boolean isDocumentAllDeclarationsProcessed(final int documentHandle) {
        if (this._dom != null) {
            return this._dom.isDocumentAllDeclarationsProcessed(documentHandle);
        }
        return super.isDocumentAllDeclarationsProcessed(documentHandle);
    }
    
    public boolean isAttributeSpecified(final int attributeHandle) {
        if (this._dom != null) {
            return this._dom.isAttributeSpecified(attributeHandle);
        }
        return super.isAttributeSpecified(attributeHandle);
    }
    
    public void dispatchCharactersEvents(final int nodeHandle, final ContentHandler ch, final boolean normalize) throws SAXException {
        if (this._dom != null) {
            this._dom.dispatchCharactersEvents(nodeHandle, ch, normalize);
        }
        else {
            super.dispatchCharactersEvents(nodeHandle, ch, normalize);
        }
    }
    
    public void dispatchToEvents(final int nodeHandle, final ContentHandler ch) throws SAXException {
        if (this._dom != null) {
            this._dom.dispatchToEvents(nodeHandle, ch);
        }
        else {
            super.dispatchToEvents(nodeHandle, ch);
        }
    }
    
    public Node getNode(final int nodeHandle) {
        if (this._dom != null) {
            return this._dom.getNode(nodeHandle);
        }
        return super.getNode(nodeHandle);
    }
    
    public boolean needsTwoThreads() {
        if (this._dom != null) {
            return this._dom.needsTwoThreads();
        }
        return super.needsTwoThreads();
    }
    
    public ContentHandler getContentHandler() {
        if (this._dom != null) {
            return this._dom.getContentHandler();
        }
        return super.getContentHandler();
    }
    
    public LexicalHandler getLexicalHandler() {
        if (this._dom != null) {
            return this._dom.getLexicalHandler();
        }
        return super.getLexicalHandler();
    }
    
    public EntityResolver getEntityResolver() {
        if (this._dom != null) {
            return this._dom.getEntityResolver();
        }
        return super.getEntityResolver();
    }
    
    public DTDHandler getDTDHandler() {
        if (this._dom != null) {
            return this._dom.getDTDHandler();
        }
        return super.getDTDHandler();
    }
    
    public ErrorHandler getErrorHandler() {
        if (this._dom != null) {
            return this._dom.getErrorHandler();
        }
        return super.getErrorHandler();
    }
    
    public DeclHandler getDeclHandler() {
        if (this._dom != null) {
            return this._dom.getDeclHandler();
        }
        return super.getDeclHandler();
    }
    
    public void appendChild(final int newChild, final boolean clone, final boolean cloneDepth) {
        if (this._dom != null) {
            this._dom.appendChild(newChild, clone, cloneDepth);
        }
        else {
            super.appendChild(newChild, clone, cloneDepth);
        }
    }
    
    public void appendTextChild(final String str) {
        if (this._dom != null) {
            this._dom.appendTextChild(str);
        }
        else {
            super.appendTextChild(str);
        }
    }
    
    public SourceLocator getSourceLocatorFor(final int node) {
        if (this._dom != null) {
            return this._dom.getSourceLocatorFor(node);
        }
        return super.getSourceLocatorFor(node);
    }
    
    public void documentRegistration() {
        if (this._dom != null) {
            this._dom.documentRegistration();
        }
        else {
            super.documentRegistration();
        }
    }
    
    public void documentRelease() {
        if (this._dom != null) {
            this._dom.documentRelease();
        }
        else {
            super.documentRelease();
        }
    }
    
    static {
        AdaptiveResultTreeImpl._documentURIIndex = 0;
    }
}
