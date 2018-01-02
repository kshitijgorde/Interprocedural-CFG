// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.stree;

import java.util.Hashtable;
import org.apache.xml.utils.SystemIDResolver;
import org.xml.sax.Attributes;
import java.util.Enumeration;
import java.util.Properties;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.apache.xpath.SourceTreeManager;
import javax.xml.transform.stream.StreamSource;
import org.xml.sax.ContentHandler;
import javax.xml.transform.Result;
import org.xml.sax.Locator;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import org.apache.xpath.XPathContext;
import org.apache.xpath.DOMHelper;
import org.apache.xml.utils.BoolStack;
import javax.xml.transform.Source;
import org.w3c.dom.DocumentFragment;
import org.apache.xml.utils.DOMBuilder;
import org.apache.xalan.transformer.TransformerImpl;
import org.xml.sax.DTDHandler;
import org.xml.sax.ext.DeclHandler;
import javax.xml.transform.sax.TransformerHandler;
import org.xml.sax.helpers.DefaultHandler;

public class SourceTreeHandler extends DefaultHandler implements TransformerHandler, DeclHandler, DTDHandler
{
    TransformerImpl m_transformer;
    private DOMBuilder m_sourceTreeHandler;
    private DocImpl m_root;
    private DocumentFragment m_docFrag;
    private boolean m_initedRoot;
    boolean m_shouldCheckWhitespace;
    boolean m_shouldTransformAtEnd;
    private Source m_inputSource;
    private boolean m_useMultiThreading;
    private static final boolean DEBUG = false;
    private boolean indexedLookup;
    private int m_eventsCount;
    private int m_maxEventsToNotify;
    private boolean m_isCData;
    private boolean m_shouldStripWS;
    private BoolStack m_shouldStripWhitespaceStack;
    
    public SourceTreeHandler() {
        this.m_shouldCheckWhitespace = false;
        this.m_shouldTransformAtEnd = true;
        this.m_useMultiThreading = false;
        this.indexedLookup = false;
        this.m_eventsCount = 0;
        this.m_maxEventsToNotify = 18;
        this.m_isCData = false;
        this.m_shouldStripWS = false;
        this.m_shouldStripWhitespaceStack = new BoolStack();
        this.m_root = new DocumentImpl(this);
        this.m_initedRoot = false;
    }
    
    public SourceTreeHandler(final TransformerImpl transformer) {
        this(transformer, false);
    }
    
    public SourceTreeHandler(final TransformerImpl transformer, final boolean doFragment) {
        this.m_shouldCheckWhitespace = false;
        this.m_shouldTransformAtEnd = true;
        this.m_useMultiThreading = false;
        this.indexedLookup = false;
        this.m_eventsCount = 0;
        this.m_maxEventsToNotify = 18;
        this.m_isCData = false;
        this.m_shouldStripWS = false;
        this.m_shouldStripWhitespaceStack = new BoolStack();
        this.m_transformer = transformer;
        final XPathContext xctxt = transformer.getXPathContext();
        xctxt.setDOMHelper(new StreeDOMHelper());
        if (doFragment) {
            this.m_root = new DocumentFragmentImpl(1024);
            this.m_docFrag = (DocumentFragmentImpl)this.m_root;
        }
        else {
            this.m_root = new DocumentImpl(this);
        }
        this.m_initedRoot = false;
        this.m_shouldCheckWhitespace = transformer.getStylesheet().shouldCheckWhitespace();
    }
    
    public void attributeDecl(final String eName, final String aName, final String type, final String valueDefault, final String value) throws SAXException {
    }
    
    public void characters(final char[] ch, final int start, final int length) throws SAXException {
        synchronized (this.m_root) {
            this.m_sourceTreeHandler.characters(ch, start, length);
        }
        // monitorexit(this.m_root)
        this.notifyWaiters();
    }
    
    public void charactersRaw(final char[] ch, final int start, final int length) throws SAXException {
        synchronized (this.m_root) {
            this.m_sourceTreeHandler.charactersRaw(ch, start, length);
        }
        // monitorexit(this.m_root)
        this.notifyWaiters();
    }
    
    public void comment(final char[] ch, final int start, final int length) throws SAXException {
        synchronized (this.m_root) {
            this.m_sourceTreeHandler.comment(ch, start, length);
        }
        // monitorexit(this.m_root)
        this.notifyWaiters();
    }
    
    public void elementDecl(final String name, final String model) throws SAXException {
    }
    
    public void endCDATA() throws SAXException {
        this.m_isCData = false;
    }
    
    public void endDTD() throws SAXException {
    }
    
    public void endDocument() throws SAXException {
        this.m_eventsCount = this.m_maxEventsToNotify;
        final Object synchObj = this.m_root;
        synchronized (synchObj) {
            this.m_sourceTreeHandler.endDocument();
            this.m_root.setComplete(true);
            this.popShouldStripWhitespace();
            if (!this.m_useMultiThreading && this.m_transformer != null && this.m_shouldTransformAtEnd) {
                try {
                    this.m_transformer.transformNode(this.m_root);
                }
                catch (TransformerException te) {
                    throw new SAXException(te);
                }
            }
        }
        // monitorexit(synchObj)
        this.m_eventsCount = this.m_maxEventsToNotify;
        this.notifyWaiters();
        if (this.m_useMultiThreading && this.m_transformer != null) {
            final Thread transformThread = this.m_transformer.getTransformThread();
            if (transformThread != null) {
                try {
                    transformThread.join();
                    this.m_transformer.setTransformThread(null);
                }
                catch (InterruptedException ex) {}
            }
        }
    }
    
    public void endElement(final String ns, final String localName, final String name) throws SAXException {
        synchronized (this.m_root) {
            final Parent myElement = (Parent)this.m_sourceTreeHandler.getCurrentNode();
            this.m_sourceTreeHandler.endElement(ns, localName, name);
            myElement.setComplete(true);
            this.m_shouldStripWS = this.m_shouldStripWhitespaceStack.popAndTop();
        }
        // monitorexit(this.m_root)
        this.notifyWaiters();
    }
    
    public void endEntity(final String name) throws SAXException {
        synchronized (this.m_root) {
            this.m_sourceTreeHandler.endEntity(name);
        }
        // monitorexit(this.m_root)
        this.notifyWaiters();
    }
    
    public void endPrefixMapping(final String prefix) throws SAXException {
        this.m_sourceTreeHandler.endPrefixMapping(prefix);
    }
    
    public void externalEntityDecl(final String name, final String publicId, final String systemId) throws SAXException {
    }
    
    public Source getInputSource() {
        return this.m_inputSource;
    }
    
    public Node getRoot() {
        return this.m_root;
    }
    
    boolean getShouldStripWhitespace() {
        return this.m_shouldStripWS;
    }
    
    public String getSystemId() {
        return this.m_transformer.getBaseURLOfSource();
    }
    
    public Transformer getTransformer() {
        return this.m_transformer;
    }
    
    TransformerImpl getTransformerImpl() {
        return this.m_transformer;
    }
    
    public boolean getUseMultiThreading() {
        return this.m_useMultiThreading;
    }
    
    public void ignorableWhitespace(final char[] ch, final int start, final int length) throws SAXException {
        synchronized (this.m_root) {
            if (this.m_shouldStripWS) {
                // monitorexit(this.m_root)
                return;
            }
            this.m_sourceTreeHandler.characters(ch, start, length);
        }
        // monitorexit(this.m_root)
        this.notifyWaiters();
    }
    
    public void internalEntityDecl(final String name, final String value) throws SAXException {
    }
    
    public void notationDecl(final String name, final String publicId, final String systemId) throws SAXException {
    }
    
    private void notifyWaiters() {
        if (this.m_useMultiThreading && this.m_eventsCount >= this.m_maxEventsToNotify) {
            final Object synchObj = this.m_root;
            synchronized (synchObj) {
                synchObj.notify();
            }
            // monitorexit(synchObj)
            this.m_eventsCount = 0;
        }
        else {
            ++this.m_eventsCount;
        }
    }
    
    void popShouldStripWhitespace() {
        this.m_shouldStripWS = this.m_shouldStripWhitespaceStack.popAndTop();
    }
    
    private void printTree(final Node n) {
        System.out.println("node: " + n.getNodeName());
        for (Node child = n.getFirstChild(); child != null; child = child.getNextSibling()) {
            this.printTree(child);
        }
    }
    
    public void processingInstruction(final String target, final String data) throws SAXException {
        synchronized (this.m_root) {
            this.m_sourceTreeHandler.processingInstruction(target, data);
        }
        // monitorexit(this.m_root)
        this.notifyWaiters();
    }
    
    void pushShouldStripWhitespace(final boolean shouldStrip) {
        this.m_shouldStripWS = shouldStrip;
        this.m_shouldStripWhitespaceStack.push(shouldStrip);
    }
    
    public void setDocumentLocator(final Locator locator) {
    }
    
    public void setExceptionThrown(final Exception e) {
        this.m_root.m_exceptionThrown = e;
    }
    
    public void setInputSource(final Source source) {
        this.m_inputSource = source;
    }
    
    public void setResult(final Result result) throws IllegalArgumentException {
        if (result == null) {
            throw new IllegalArgumentException("result should not be null");
        }
        try {
            final ContentHandler handler = this.m_transformer.createResultContentHandler(result);
            this.m_transformer.setContentHandler(handler);
        }
        catch (TransformerException ex) {
            throw new IllegalArgumentException("result could not be set");
        }
    }
    
    public void setRoot(final DocImpl root) {
        this.m_root = root;
    }
    
    void setShouldStripWhitespace(final boolean shouldStrip) {
        this.m_shouldStripWS = shouldStrip;
        this.m_shouldStripWhitespaceStack.setTop(shouldStrip);
    }
    
    public void setShouldTransformAtEnd(final boolean b) {
        this.m_shouldTransformAtEnd = b;
    }
    
    public void setSystemId(final String baseID) {
        this.m_transformer.setBaseURLOfSource(baseID);
        final XPathContext xctxt = this.m_transformer.getXPathContext();
        final SourceTreeManager stm = xctxt.getSourceTreeManager();
        this.m_inputSource = new StreamSource(baseID);
        stm.putDocumentInCache(this.m_root, this.m_inputSource);
    }
    
    public void setUseMultiThreading(final boolean b) {
        this.m_useMultiThreading = b;
    }
    
    public void skippedEntity(final String name) throws SAXException {
    }
    
    public void startCDATA() throws SAXException {
        this.m_isCData = true;
    }
    
    public void startDTD(final String name, final String publicId, final String systemId) throws SAXException {
        if (this.m_root instanceof DocumentImpl) {
            final DocumentImpl doc = (DocumentImpl)this.m_root;
            final DocumentTypeImpl dtd = new DocumentTypeImpl(doc, name, publicId, systemId);
            ((DocumentImpl)this.m_root).setDoctype(dtd);
        }
    }
    
    public void startDocument() throws SAXException {
        synchronized (this.m_root) {
            this.m_root.setSourceTreeHandler(this);
            this.m_root.setUid(1);
            this.m_root.setLevel((short)(Object)new Integer(1));
            this.m_root.setUseMultiThreading(this.getUseMultiThreading());
            if (this.m_docFrag != null) {
                this.m_sourceTreeHandler = new StreeDOMBuilder(this.m_root, this.m_docFrag);
            }
            else if (this.m_root.getNodeType() == 11) {
                this.m_sourceTreeHandler = new StreeDOMBuilder(this.m_root.getOwnerDocument(), (DocumentFragment)this.m_root);
            }
            else {
                this.m_sourceTreeHandler = new StreeDOMBuilder(this.m_root);
            }
            this.pushShouldStripWhitespace(false);
            this.m_sourceTreeHandler.startDocument();
        }
        // monitorexit(this.m_root)
        if (this.m_useMultiThreading && this.m_transformer != null && this.m_transformer.isParserEventsOnMain()) {
            final ContentHandler resultContentHandler = this.m_transformer.getContentHandler();
            if (resultContentHandler != null && resultContentHandler instanceof SourceTreeHandler) {
                final Properties myProps = this.m_transformer.getOutputProperties();
                final SourceTreeHandler resultHandler = (SourceTreeHandler)resultContentHandler;
                final Transformer resultTransformer = resultHandler.getTransformer();
                final Properties resultProps = resultTransformer.getOutputProperties();
                final Enumeration myKeys = myProps.keys();
                while (myKeys.hasMoreElements()) {
                    final Object key = myKeys.nextElement();
                    if (resultProps.get(key) == null) {
                        resultProps.put(key, ((Hashtable<K, Object>)myProps).get(key));
                    }
                }
                resultTransformer.setOutputProperties(resultProps);
            }
            if (this.m_docFrag != null) {
                this.m_transformer.setSourceTreeDocForThread(this.m_docFrag);
            }
            else {
                this.m_transformer.setSourceTreeDocForThread(this.m_root);
            }
            final Thread t = this.m_transformer.createTransformThread();
            this.m_transformer.setTransformThread(t);
            final int cpriority = Thread.currentThread().getPriority();
            t.setPriority(cpriority);
            t.start();
        }
        this.notifyWaiters();
    }
    
    public void startElement(final String ns, final String localName, final String name, final Attributes atts) throws SAXException {
        synchronized (this.m_root) {
            this.m_shouldStripWhitespaceStack.push(this.m_shouldStripWS);
            this.m_sourceTreeHandler.startElement(ns, localName, name, atts);
        }
        // monitorexit(this.m_root)
        this.notifyWaiters();
    }
    
    public void startEntity(final String name) throws SAXException {
        synchronized (this.m_root) {
            this.m_sourceTreeHandler.startEntity(name);
        }
        // monitorexit(this.m_root)
        this.notifyWaiters();
    }
    
    public void startPrefixMapping(final String prefix, final String uri) throws SAXException {
        synchronized (this.m_root) {
            this.m_sourceTreeHandler.startPrefixMapping(prefix, uri);
        }
        // monitorexit(this.m_root)
    }
    
    public void unparsedEntityDecl(final String name, final String publicId, String systemId, final String notationName) throws SAXException {
        try {
            if (this.m_inputSource != null) {
                systemId = SystemIDResolver.getAbsoluteURI(systemId, this.m_inputSource.getSystemId());
            }
        }
        catch (Exception e) {
            throw new SAXException(e);
        }
        final EntityImpl entity = new EntityImpl(name, notationName, publicId, systemId);
        this.m_root.getDoctype().getEntities().setNamedItem(entity);
    }
}
