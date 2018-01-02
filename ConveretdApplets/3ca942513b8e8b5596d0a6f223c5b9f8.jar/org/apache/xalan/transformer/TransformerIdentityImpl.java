// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.transformer;

import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import java.util.Properties;
import org.xml.sax.XMLReader;
import org.xml.sax.InputSource;
import org.apache.xml.utils.WrappedRuntimeException;
import org.xml.sax.SAXNotRecognizedException;
import org.apache.xml.utils.XMLReaderManager;
import org.xml.sax.SAXException;
import org.apache.xml.serializer.TreeWalker;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.Source;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Node;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.xml.serializer.SerializerFactory;
import javax.xml.transform.stream.StreamResult;
import org.apache.xml.utils.DOMBuilder;
import org.w3c.dom.DocumentFragment;
import javax.xml.transform.TransformerException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.sax.SAXResult;
import org.apache.xalan.res.XSLMessages;
import org.apache.xml.utils.DefaultErrorHandler;
import org.apache.xalan.templates.OutputProperties;
import javax.xml.transform.URIResolver;
import javax.xml.transform.ErrorListener;
import java.util.Hashtable;
import javax.xml.transform.Result;
import org.apache.xml.serializer.Serializer;
import org.xml.sax.DTDHandler;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.ContentHandler;
import java.io.FileOutputStream;
import org.xml.sax.ext.DeclHandler;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.Transformer;

public class TransformerIdentityImpl extends Transformer implements TransformerHandler, DeclHandler
{
    boolean m_flushedStartDoc;
    private FileOutputStream m_outputStream;
    private ContentHandler m_resultContentHandler;
    private LexicalHandler m_resultLexicalHandler;
    private DTDHandler m_resultDTDHandler;
    private DeclHandler m_resultDeclHandler;
    private Serializer m_serializer;
    private Result m_result;
    private String m_systemID;
    private Hashtable m_params;
    private ErrorListener m_errorListener;
    URIResolver m_URIResolver;
    private OutputProperties m_outputFormat;
    boolean m_foundFirstElement;
    private boolean m_isSecureProcessing;
    
    public TransformerIdentityImpl(final boolean isSecureProcessing) {
        this.m_flushedStartDoc = false;
        this.m_outputStream = null;
        this.m_errorListener = new DefaultErrorHandler(false);
        this.m_isSecureProcessing = false;
        this.m_outputFormat = new OutputProperties("xml");
        this.m_isSecureProcessing = isSecureProcessing;
    }
    
    public TransformerIdentityImpl() {
        this(false);
    }
    
    public void setResult(final Result result) throws IllegalArgumentException {
        if (null == result) {
            throw new IllegalArgumentException(XSLMessages.createMessage("ER_RESULT_NULL", null));
        }
        this.m_result = result;
    }
    
    public void setSystemId(final String systemID) {
        this.m_systemID = systemID;
    }
    
    public String getSystemId() {
        return this.m_systemID;
    }
    
    public Transformer getTransformer() {
        return this;
    }
    
    public void reset() {
        this.m_flushedStartDoc = false;
        this.m_foundFirstElement = false;
        this.m_outputStream = null;
        this.m_params.clear();
        this.m_result = null;
        this.m_resultContentHandler = null;
        this.m_resultDeclHandler = null;
        this.m_resultDTDHandler = null;
        this.m_resultLexicalHandler = null;
        this.m_serializer = null;
        this.m_systemID = null;
        this.m_URIResolver = null;
        this.m_outputFormat = new OutputProperties("xml");
    }
    
    private void createResultContentHandler(final Result outputTarget) throws TransformerException {
        if (outputTarget instanceof SAXResult) {
            final SAXResult saxResult = (SAXResult)outputTarget;
            this.m_resultContentHandler = saxResult.getHandler();
            this.m_resultLexicalHandler = saxResult.getLexicalHandler();
            if (this.m_resultContentHandler instanceof Serializer) {
                this.m_serializer = (Serializer)this.m_resultContentHandler;
            }
        }
        else if (outputTarget instanceof DOMResult) {
            final DOMResult domResult = (DOMResult)outputTarget;
            Node outputNode = domResult.getNode();
            short type;
            Document doc;
            if (null != outputNode) {
                type = outputNode.getNodeType();
                doc = (Document)((9 == type) ? outputNode : outputNode.getOwnerDocument());
            }
            else {
                try {
                    final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    dbf.setNamespaceAware(true);
                    if (this.m_isSecureProcessing) {
                        try {
                            dbf.setFeature("http://javax.xml.XMLConstants/feature/secure-processing", true);
                        }
                        catch (ParserConfigurationException ex) {}
                    }
                    final DocumentBuilder db = dbf.newDocumentBuilder();
                    doc = db.newDocument();
                }
                catch (ParserConfigurationException pce) {
                    throw new TransformerException(pce);
                }
                outputNode = doc;
                type = outputNode.getNodeType();
                ((DOMResult)outputTarget).setNode(outputNode);
            }
            final DOMBuilder domBuilder = (11 == type) ? new DOMBuilder(doc, (DocumentFragment)outputNode) : new DOMBuilder(doc, outputNode);
            try {
                final Node nextSibling = domResult.getNextSibling();
                if (nextSibling != null) {
                    domBuilder.setNextSibling(nextSibling);
                }
            }
            catch (NoSuchMethodError noSuchMethodError) {}
            this.m_resultContentHandler = domBuilder;
            this.m_resultLexicalHandler = domBuilder;
        }
        else {
            if (!(outputTarget instanceof StreamResult)) {
                throw new TransformerException(XSLMessages.createMessage("ER_CANNOT_TRANSFORM_TO_RESULT_TYPE", new Object[] { outputTarget.getClass().getName() }));
            }
            final StreamResult sresult = (StreamResult)outputTarget;
            final String method = this.m_outputFormat.getProperty("method");
            try {
                final Serializer serializer = SerializerFactory.getSerializer(this.m_outputFormat.getProperties());
                this.m_serializer = serializer;
                if (null != sresult.getWriter()) {
                    serializer.setWriter(sresult.getWriter());
                }
                else if (null != sresult.getOutputStream()) {
                    serializer.setOutputStream(sresult.getOutputStream());
                }
                else {
                    if (null == sresult.getSystemId()) {
                        throw new TransformerException(XSLMessages.createMessage("ER_NO_OUTPUT_SPECIFIED", null));
                    }
                    String fileURL = sresult.getSystemId();
                    if (fileURL.startsWith("file:///")) {
                        if (fileURL.substring(8).indexOf(":") > 0) {
                            fileURL = fileURL.substring(8);
                        }
                        else {
                            fileURL = fileURL.substring(7);
                        }
                    }
                    else if (fileURL.startsWith("file:/")) {
                        if (fileURL.substring(6).indexOf(":") > 0) {
                            fileURL = fileURL.substring(6);
                        }
                        else {
                            fileURL = fileURL.substring(5);
                        }
                    }
                    serializer.setOutputStream(this.m_outputStream = new FileOutputStream(fileURL));
                }
                this.m_resultContentHandler = serializer.asContentHandler();
            }
            catch (IOException ioe) {
                throw new TransformerException(ioe);
            }
        }
        if (this.m_resultContentHandler instanceof DTDHandler) {
            this.m_resultDTDHandler = (DTDHandler)this.m_resultContentHandler;
        }
        if (this.m_resultContentHandler instanceof DeclHandler) {
            this.m_resultDeclHandler = (DeclHandler)this.m_resultContentHandler;
        }
        if (this.m_resultContentHandler instanceof LexicalHandler) {
            this.m_resultLexicalHandler = (LexicalHandler)this.m_resultContentHandler;
        }
    }
    
    public void transform(Source source, final Result outputTarget) throws TransformerException {
        this.createResultContentHandler(outputTarget);
        Label_0145: {
            if ((!(source instanceof StreamSource) || source.getSystemId() != null || ((StreamSource)source).getInputStream() != null || ((StreamSource)source).getReader() != null) && (!(source instanceof SAXSource) || ((SAXSource)source).getInputSource() != null || ((SAXSource)source).getXMLReader() != null)) {
                if (!(source instanceof DOMSource) || ((DOMSource)source).getNode() != null) {
                    break Label_0145;
                }
            }
            try {
                final DocumentBuilderFactory builderF = DocumentBuilderFactory.newInstance();
                final DocumentBuilder builder = builderF.newDocumentBuilder();
                final String systemID = source.getSystemId();
                source = new DOMSource(builder.newDocument());
                if (systemID != null) {
                    source.setSystemId(systemID);
                }
            }
            catch (ParserConfigurationException e) {
                throw new TransformerException(e.getMessage());
            }
            try {
                if (source instanceof DOMSource) {
                    final DOMSource dsource = (DOMSource)source;
                    this.m_systemID = dsource.getSystemId();
                    final Node dNode = dsource.getNode();
                    if (null != dNode) {
                        try {
                            if (dNode.getNodeType() == 2) {
                                this.startDocument();
                            }
                            try {
                                if (dNode.getNodeType() == 2) {
                                    final String data = dNode.getNodeValue();
                                    final char[] chars = data.toCharArray();
                                    this.characters(chars, 0, chars.length);
                                }
                                else {
                                    final TreeWalker walker = new TreeWalker(this, this.m_systemID);
                                    walker.traverse(dNode);
                                }
                            }
                            finally {
                                if (dNode.getNodeType() == 2) {
                                    this.endDocument();
                                }
                            }
                        }
                        catch (SAXException se) {
                            throw new TransformerException(se);
                        }
                        return;
                    }
                    final String messageStr = XSLMessages.createMessage("ER_ILLEGAL_DOMSOURCE_INPUT", null);
                    throw new IllegalArgumentException(messageStr);
                }
                else {
                    final InputSource xmlSource = SAXSource.sourceToInputSource(source);
                    if (null == xmlSource) {
                        throw new TransformerException(XSLMessages.createMessage("ER_CANNOT_TRANSFORM_SOURCE_TYPE", new Object[] { source.getClass().getName() }));
                    }
                    if (null != xmlSource.getSystemId()) {
                        this.m_systemID = xmlSource.getSystemId();
                    }
                    XMLReader reader = null;
                    boolean managedReader = false;
                    try {
                        if (source instanceof SAXSource) {
                            reader = ((SAXSource)source).getXMLReader();
                        }
                        if (null == reader) {
                            try {
                                reader = XMLReaderManager.getInstance().getXMLReader();
                                managedReader = true;
                            }
                            catch (SAXException se2) {
                                throw new TransformerException(se2);
                            }
                        }
                        else {
                            try {
                                reader.setFeature("http://xml.org/sax/features/namespace-prefixes", true);
                            }
                            catch (SAXException ex) {}
                        }
                        reader.setContentHandler(this);
                        if (this instanceof DTDHandler) {
                            reader.setDTDHandler(this);
                        }
                        try {
                            if (this instanceof LexicalHandler) {
                                reader.setProperty("http://xml.org/sax/properties/lexical-handler", this);
                            }
                            if (this instanceof DeclHandler) {
                                reader.setProperty("http://xml.org/sax/properties/declaration-handler", this);
                            }
                        }
                        catch (SAXException ex2) {}
                        try {
                            if (this instanceof LexicalHandler) {
                                reader.setProperty("http://xml.org/sax/handlers/LexicalHandler", this);
                            }
                            if (this instanceof DeclHandler) {
                                reader.setProperty("http://xml.org/sax/handlers/DeclHandler", this);
                            }
                        }
                        catch (SAXNotRecognizedException ex3) {}
                        reader.parse(xmlSource);
                    }
                    catch (WrappedRuntimeException wre) {
                        for (Throwable throwable = wre.getException(); throwable instanceof WrappedRuntimeException; throwable = ((WrappedRuntimeException)throwable).getException()) {}
                        throw new TransformerException(wre.getException());
                    }
                    catch (SAXException se3) {
                        throw new TransformerException(se3);
                    }
                    catch (IOException ioe) {
                        throw new TransformerException(ioe);
                    }
                    finally {
                        if (managedReader) {
                            XMLReaderManager.getInstance().releaseXMLReader(reader);
                        }
                    }
                }
            }
            finally {
                if (null != this.m_outputStream) {
                    try {
                        this.m_outputStream.close();
                    }
                    catch (IOException ex4) {}
                    this.m_outputStream = null;
                }
            }
        }
    }
    
    public void setParameter(final String name, final Object value) {
        if (value == null) {
            throw new IllegalArgumentException(XSLMessages.createMessage("ER_INVALID_SET_PARAM_VALUE", new Object[] { name }));
        }
        if (null == this.m_params) {
            this.m_params = new Hashtable();
        }
        this.m_params.put(name, value);
    }
    
    public Object getParameter(final String name) {
        if (null == this.m_params) {
            return null;
        }
        return this.m_params.get(name);
    }
    
    public void clearParameters() {
        if (null == this.m_params) {
            return;
        }
        this.m_params.clear();
    }
    
    public void setURIResolver(final URIResolver resolver) {
        this.m_URIResolver = resolver;
    }
    
    public URIResolver getURIResolver() {
        return this.m_URIResolver;
    }
    
    public void setOutputProperties(final Properties oformat) throws IllegalArgumentException {
        if (null != oformat) {
            final String method = ((Hashtable<K, String>)oformat).get("method");
            if (null != method) {
                this.m_outputFormat = new OutputProperties(method);
            }
            else {
                this.m_outputFormat = new OutputProperties();
            }
            this.m_outputFormat.copyFrom(oformat);
        }
        else {
            this.m_outputFormat = null;
        }
    }
    
    public Properties getOutputProperties() {
        return (Properties)this.m_outputFormat.getProperties().clone();
    }
    
    public void setOutputProperty(final String name, final String value) throws IllegalArgumentException {
        if (!OutputProperties.isLegalPropertyKey(name)) {
            throw new IllegalArgumentException(XSLMessages.createMessage("ER_OUTPUT_PROPERTY_NOT_RECOGNIZED", new Object[] { name }));
        }
        this.m_outputFormat.setProperty(name, value);
    }
    
    public String getOutputProperty(final String name) throws IllegalArgumentException {
        String value = null;
        final OutputProperties props = this.m_outputFormat;
        value = props.getProperty(name);
        if (null == value && !OutputProperties.isLegalPropertyKey(name)) {
            throw new IllegalArgumentException(XSLMessages.createMessage("ER_OUTPUT_PROPERTY_NOT_RECOGNIZED", new Object[] { name }));
        }
        return value;
    }
    
    public void setErrorListener(final ErrorListener listener) throws IllegalArgumentException {
        if (listener == null) {
            throw new IllegalArgumentException(XSLMessages.createMessage("ER_NULL_ERROR_HANDLER", null));
        }
        this.m_errorListener = listener;
    }
    
    public ErrorListener getErrorListener() {
        return this.m_errorListener;
    }
    
    public void notationDecl(final String name, final String publicId, final String systemId) throws SAXException {
        if (null != this.m_resultDTDHandler) {
            this.m_resultDTDHandler.notationDecl(name, publicId, systemId);
        }
    }
    
    public void unparsedEntityDecl(final String name, final String publicId, final String systemId, final String notationName) throws SAXException {
        if (null != this.m_resultDTDHandler) {
            this.m_resultDTDHandler.unparsedEntityDecl(name, publicId, systemId, notationName);
        }
    }
    
    public void setDocumentLocator(final Locator locator) {
        try {
            if (null == this.m_resultContentHandler) {
                this.createResultContentHandler(this.m_result);
            }
        }
        catch (TransformerException te) {
            throw new WrappedRuntimeException(te);
        }
        this.m_resultContentHandler.setDocumentLocator(locator);
    }
    
    public void startDocument() throws SAXException {
        try {
            if (null == this.m_resultContentHandler) {
                this.createResultContentHandler(this.m_result);
            }
        }
        catch (TransformerException te) {
            throw new SAXException(te.getMessage(), te);
        }
        this.m_flushedStartDoc = false;
        this.m_foundFirstElement = false;
    }
    
    protected final void flushStartDoc() throws SAXException {
        if (!this.m_flushedStartDoc) {
            if (this.m_resultContentHandler == null) {
                try {
                    this.createResultContentHandler(this.m_result);
                }
                catch (TransformerException te) {
                    throw new SAXException(te);
                }
            }
            this.m_resultContentHandler.startDocument();
            this.m_flushedStartDoc = true;
        }
    }
    
    public void endDocument() throws SAXException {
        this.flushStartDoc();
        this.m_resultContentHandler.endDocument();
    }
    
    public void startPrefixMapping(final String prefix, final String uri) throws SAXException {
        this.flushStartDoc();
        this.m_resultContentHandler.startPrefixMapping(prefix, uri);
    }
    
    public void endPrefixMapping(final String prefix) throws SAXException {
        this.flushStartDoc();
        this.m_resultContentHandler.endPrefixMapping(prefix);
    }
    
    public void startElement(final String uri, final String localName, final String qName, final Attributes attributes) throws SAXException {
        if (!this.m_foundFirstElement && null != this.m_serializer) {
            this.m_foundFirstElement = true;
            Serializer newSerializer;
            try {
                newSerializer = SerializerSwitcher.switchSerializerIfHTML(uri, localName, this.m_outputFormat.getProperties(), this.m_serializer);
            }
            catch (TransformerException te) {
                throw new SAXException(te);
            }
            if (newSerializer != this.m_serializer) {
                try {
                    this.m_resultContentHandler = newSerializer.asContentHandler();
                }
                catch (IOException ioe) {
                    throw new SAXException(ioe);
                }
                if (this.m_resultContentHandler instanceof DTDHandler) {
                    this.m_resultDTDHandler = (DTDHandler)this.m_resultContentHandler;
                }
                if (this.m_resultContentHandler instanceof LexicalHandler) {
                    this.m_resultLexicalHandler = (LexicalHandler)this.m_resultContentHandler;
                }
                this.m_serializer = newSerializer;
            }
        }
        this.flushStartDoc();
        this.m_resultContentHandler.startElement(uri, localName, qName, attributes);
    }
    
    public void endElement(final String uri, final String localName, final String qName) throws SAXException {
        this.m_resultContentHandler.endElement(uri, localName, qName);
    }
    
    public void characters(final char[] ch, final int start, final int length) throws SAXException {
        this.flushStartDoc();
        this.m_resultContentHandler.characters(ch, start, length);
    }
    
    public void ignorableWhitespace(final char[] ch, final int start, final int length) throws SAXException {
        this.m_resultContentHandler.ignorableWhitespace(ch, start, length);
    }
    
    public void processingInstruction(final String target, final String data) throws SAXException {
        this.flushStartDoc();
        this.m_resultContentHandler.processingInstruction(target, data);
    }
    
    public void skippedEntity(final String name) throws SAXException {
        this.flushStartDoc();
        this.m_resultContentHandler.skippedEntity(name);
    }
    
    public void startDTD(final String name, final String publicId, final String systemId) throws SAXException {
        this.flushStartDoc();
        if (null != this.m_resultLexicalHandler) {
            this.m_resultLexicalHandler.startDTD(name, publicId, systemId);
        }
    }
    
    public void endDTD() throws SAXException {
        if (null != this.m_resultLexicalHandler) {
            this.m_resultLexicalHandler.endDTD();
        }
    }
    
    public void startEntity(final String name) throws SAXException {
        if (null != this.m_resultLexicalHandler) {
            this.m_resultLexicalHandler.startEntity(name);
        }
    }
    
    public void endEntity(final String name) throws SAXException {
        if (null != this.m_resultLexicalHandler) {
            this.m_resultLexicalHandler.endEntity(name);
        }
    }
    
    public void startCDATA() throws SAXException {
        if (null != this.m_resultLexicalHandler) {
            this.m_resultLexicalHandler.startCDATA();
        }
    }
    
    public void endCDATA() throws SAXException {
        if (null != this.m_resultLexicalHandler) {
            this.m_resultLexicalHandler.endCDATA();
        }
    }
    
    public void comment(final char[] ch, final int start, final int length) throws SAXException {
        this.flushStartDoc();
        if (null != this.m_resultLexicalHandler) {
            this.m_resultLexicalHandler.comment(ch, start, length);
        }
    }
    
    public void elementDecl(final String name, final String model) throws SAXException {
        if (null != this.m_resultDeclHandler) {
            this.m_resultDeclHandler.elementDecl(name, model);
        }
    }
    
    public void attributeDecl(final String eName, final String aName, final String type, final String valueDefault, final String value) throws SAXException {
        if (null != this.m_resultDeclHandler) {
            this.m_resultDeclHandler.attributeDecl(eName, aName, type, valueDefault, value);
        }
    }
    
    public void internalEntityDecl(final String name, final String value) throws SAXException {
        if (null != this.m_resultDeclHandler) {
            this.m_resultDeclHandler.internalEntityDecl(name, value);
        }
    }
    
    public void externalEntityDecl(final String name, final String publicId, final String systemId) throws SAXException {
        if (null != this.m_resultDeclHandler) {
            this.m_resultDeclHandler.externalEntityDecl(name, publicId, systemId);
        }
    }
}
