// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.transformer;

import javax.xml.parsers.SAXParser;
import org.xml.sax.XMLReader;
import org.xml.sax.InputSource;
import org.apache.xml.utils.WrappedRuntimeException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.ext.DeclHandler;
import org.xml.sax.helpers.XMLReaderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.sax.SAXSource;
import org.apache.xalan.res.XSLMessages;
import org.apache.xml.utils.TreeWalker;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.Source;
import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import java.util.Properties;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Node;
import org.apache.xml.utils.DOMBuilder;
import org.w3c.dom.DocumentFragment;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import java.io.IOException;
import java.io.OutputStream;
import javax.xml.transform.TransformerException;
import org.apache.xalan.serialize.SerializerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.sax.SAXResult;
import org.xml.sax.SAXException;
import org.apache.xml.utils.DefaultErrorHandler;
import org.apache.xalan.templates.OutputProperties;
import javax.xml.transform.URIResolver;
import javax.xml.transform.ErrorListener;
import java.util.Hashtable;
import javax.xml.transform.Result;
import org.apache.xalan.serialize.Serializer;
import org.xml.sax.DTDHandler;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.ContentHandler;
import java.io.FileOutputStream;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.Transformer;

public class TransformerIdentityImpl extends Transformer implements TransformerHandler
{
    private FileOutputStream m_outputStream;
    private ContentHandler m_resultContentHandler;
    private LexicalHandler m_resultLexicalHandler;
    private DTDHandler m_resultDTDHandler;
    private Serializer m_serializer;
    private Result m_result;
    private String m_systemID;
    private Hashtable m_params;
    private ErrorListener m_errorListener;
    URIResolver m_URIResolver;
    private OutputProperties m_outputFormat;
    boolean m_foundFirstElement;
    
    public TransformerIdentityImpl() {
        this.m_outputStream = null;
        this.m_errorListener = new DefaultErrorHandler();
        this.m_outputFormat = new OutputProperties("xml");
    }
    
    public void characters(final char[] ch, final int start, final int length) throws SAXException {
        this.m_resultContentHandler.characters(ch, start, length);
    }
    
    public void clearParameters() {
        if (this.m_params == null) {
            return;
        }
        this.m_params.clear();
    }
    
    public void comment(final char[] ch, final int start, final int length) throws SAXException {
        if (this.m_resultLexicalHandler != null) {
            this.m_resultLexicalHandler.comment(ch, start, length);
        }
    }
    
    private void createResultContentHandler(final Result outputTarget) throws TransformerException {
        Label_0428: {
            if (outputTarget instanceof SAXResult) {
                final SAXResult saxResult = (SAXResult)outputTarget;
                this.m_resultContentHandler = saxResult.getHandler();
                this.m_resultLexicalHandler = saxResult.getLexicalHandler();
                if (this.m_resultContentHandler instanceof Serializer) {
                    this.m_serializer = (Serializer)this.m_resultContentHandler;
                }
            }
            else {
                if (!(outputTarget instanceof DOMResult)) {
                    if (outputTarget instanceof StreamResult) {
                        final StreamResult sresult = (StreamResult)outputTarget;
                        final String method = this.m_outputFormat.getProperty("method");
                        try {
                            final Serializer serializer = SerializerFactory.getSerializer(this.m_outputFormat.getProperties());
                            this.m_serializer = serializer;
                            if (sresult.getWriter() != null) {
                                serializer.setWriter(sresult.getWriter());
                            }
                            else if (sresult.getOutputStream() != null) {
                                serializer.setOutputStream(sresult.getOutputStream());
                            }
                            else {
                                if (sresult.getSystemId() == null) {
                                    throw new TransformerException("No output specified!");
                                }
                                String fileURL = sresult.getSystemId();
                                if (fileURL.startsWith("file:///")) {
                                    fileURL = fileURL.substring(8);
                                }
                                serializer.setOutputStream(this.m_outputStream = new FileOutputStream(fileURL));
                            }
                            this.m_resultContentHandler = serializer.asContentHandler();
                            break Label_0428;
                        }
                        catch (IOException ioe) {
                            throw new TransformerException(ioe);
                        }
                    }
                    throw new TransformerException("Can't transform to a Result of type " + outputTarget.getClass().getName() + "!");
                }
                final DOMResult domResult = (DOMResult)outputTarget;
                Node outputNode = domResult.getNode();
                short type;
                Document doc;
                if (outputNode != null) {
                    type = outputNode.getNodeType();
                    doc = (Document)((type == 9) ? outputNode : outputNode.getOwnerDocument());
                }
                else {
                    try {
                        final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                        dbf.setNamespaceAware(true);
                        final DocumentBuilder db = dbf.newDocumentBuilder();
                        doc = db.newDocument();
                    }
                    catch (ParserConfigurationException e) {
                        throw new TransformerException(e);
                    }
                    outputNode = doc;
                    type = outputNode.getNodeType();
                    ((DOMResult)outputTarget).setNode(outputNode);
                }
                this.m_resultContentHandler = ((type == 11) ? new DOMBuilder(doc, (DocumentFragment)outputNode) : new DOMBuilder(doc, outputNode));
                this.m_resultLexicalHandler = (LexicalHandler)this.m_resultContentHandler;
            }
        }
        if (this.m_resultContentHandler instanceof DTDHandler) {
            this.m_resultDTDHandler = (DTDHandler)this.m_resultContentHandler;
        }
        if (this.m_resultLexicalHandler == null && this.m_resultContentHandler instanceof LexicalHandler) {
            this.m_resultLexicalHandler = (LexicalHandler)this.m_resultContentHandler;
        }
    }
    
    public void endCDATA() throws SAXException {
        if (this.m_resultLexicalHandler != null) {
            this.m_resultLexicalHandler.endCDATA();
        }
    }
    
    public void endDTD() throws SAXException {
        if (this.m_resultLexicalHandler != null) {
            this.m_resultLexicalHandler.endDTD();
        }
    }
    
    public void endDocument() throws SAXException {
        this.m_resultContentHandler.endDocument();
    }
    
    public void endElement(final String uri, final String localName, final String qName) throws SAXException {
        this.m_resultContentHandler.endElement(uri, localName, qName);
    }
    
    public void endEntity(final String name) throws SAXException {
        if (this.m_resultLexicalHandler != null) {
            this.m_resultLexicalHandler.endEntity(name);
        }
    }
    
    public void endPrefixMapping(final String prefix) throws SAXException {
        this.m_resultContentHandler.endPrefixMapping(prefix);
    }
    
    public ErrorListener getErrorListener() {
        return this.m_errorListener;
    }
    
    public Properties getOutputProperties() {
        return (Properties)this.m_outputFormat.getProperties().clone();
    }
    
    public String getOutputProperty(final String name) throws IllegalArgumentException {
        String value = null;
        final OutputProperties props = this.m_outputFormat;
        value = props.getProperty(name);
        if (value == null && !props.isLegalPropertyKey(name)) {
            throw new IllegalArgumentException("output property not recognized: " + name);
        }
        return value;
    }
    
    public Object getParameter(final String name) {
        if (this.m_params == null) {
            return null;
        }
        return this.m_params.get(name);
    }
    
    public String getSystemId() {
        return this.m_systemID;
    }
    
    public Transformer getTransformer() {
        return this;
    }
    
    public URIResolver getURIResolver() {
        return this.m_URIResolver;
    }
    
    public void ignorableWhitespace(final char[] ch, final int start, final int length) throws SAXException {
        this.m_resultContentHandler.ignorableWhitespace(ch, start, length);
    }
    
    public void notationDecl(final String name, final String publicId, final String systemId) throws SAXException {
        if (this.m_resultDTDHandler != null) {
            this.m_resultDTDHandler.notationDecl(name, publicId, systemId);
        }
    }
    
    public void processingInstruction(final String target, final String data) throws SAXException {
        this.m_resultContentHandler.processingInstruction(target, data);
    }
    
    public void setDocumentLocator(final Locator locator) {
        this.m_resultContentHandler.setDocumentLocator(locator);
    }
    
    public void setErrorListener(final ErrorListener listener) throws IllegalArgumentException {
        this.m_errorListener = listener;
    }
    
    public void setOutputProperties(final Properties oformat) throws IllegalArgumentException {
        if (oformat != null) {
            final String method = ((Hashtable<K, String>)oformat).get("method");
            if (method != null) {
                this.m_outputFormat = new OutputProperties(method);
            }
            else {
                this.m_outputFormat = new OutputProperties();
            }
        }
        if (oformat != null) {
            this.m_outputFormat.copyFrom(oformat);
        }
    }
    
    public void setOutputProperty(final String name, final String value) throws IllegalArgumentException {
        if (!this.m_outputFormat.isLegalPropertyKey(name)) {
            throw new IllegalArgumentException("output property not recognized: " + name);
        }
        this.m_outputFormat.setProperty(name, value);
    }
    
    public void setParameter(final String name, final Object value) {
        if (this.m_params == null) {
            this.m_params = new Hashtable();
        }
        this.m_params.put(name, value);
    }
    
    public void setResult(final Result result) throws IllegalArgumentException {
        if (result == null) {
            throw new IllegalArgumentException("Result should not be null");
        }
        this.m_result = result;
    }
    
    public void setSystemId(final String systemID) {
        this.m_systemID = systemID;
    }
    
    public void setURIResolver(final URIResolver resolver) {
        this.m_URIResolver = resolver;
    }
    
    public void skippedEntity(final String name) throws SAXException {
        this.m_resultContentHandler.skippedEntity(name);
    }
    
    public void startCDATA() throws SAXException {
        if (this.m_resultLexicalHandler != null) {
            this.m_resultLexicalHandler.startCDATA();
        }
    }
    
    public void startDTD(final String name, final String publicId, final String systemId) throws SAXException {
        if (this.m_resultLexicalHandler != null) {
            this.m_resultLexicalHandler.startDTD(name, publicId, systemId);
        }
    }
    
    public void startDocument() throws SAXException {
        try {
            if (this.m_resultContentHandler == null) {
                this.createResultContentHandler(this.m_result);
            }
        }
        catch (TransformerException te) {
            throw new SAXException(te.getMessage(), te);
        }
        this.m_resultContentHandler.startDocument();
        this.m_foundFirstElement = false;
    }
    
    public void startElement(final String uri, final String localName, final String qName, final Attributes attributes) throws SAXException {
        if (!this.m_foundFirstElement && this.m_serializer != null) {
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
        this.m_resultContentHandler.startElement(uri, localName, qName, attributes);
    }
    
    public void startEntity(final String name) throws SAXException {
        if (this.m_resultLexicalHandler != null) {
            this.m_resultLexicalHandler.startEntity(name);
        }
    }
    
    public void startPrefixMapping(final String prefix, final String uri) throws SAXException {
        this.m_resultContentHandler.startPrefixMapping(prefix, uri);
    }
    
    public void transform(final Source source, final Result outputTarget) throws TransformerException {
        this.createResultContentHandler(outputTarget);
        try {
            if (source instanceof DOMSource) {
                final DOMSource dsource = (DOMSource)source;
                this.m_systemID = dsource.getSystemId();
                final Node dNode = dsource.getNode();
                if (dNode != null) {
                    try {
                        if (dNode.getNodeType() != 9) {
                            this.startDocument();
                        }
                        try {
                            if (dNode.getNodeType() == 2) {
                                final String data = dNode.getNodeValue();
                                final char[] chars = data.toCharArray();
                                this.characters(chars, 0, chars.length);
                            }
                            else {
                                final TreeWalker walker = new TreeWalker(this);
                                walker.traverse(dNode);
                            }
                        }
                        finally {
                            if (dNode.getNodeType() != 9) {
                                this.endDocument();
                            }
                        }
                    }
                    catch (SAXException se) {
                        throw new TransformerException(se);
                    }
                    return;
                }
                final String messageStr = XSLMessages.createMessage(108, null);
                throw new IllegalArgumentException(messageStr);
            }
            else {
                final InputSource xmlSource = SAXSource.sourceToInputSource(source);
                if (xmlSource == null) {
                    throw new TransformerException("Can't transform a Source of type " + source.getClass().getName() + "!");
                }
                if (xmlSource.getSystemId() != null) {
                    this.m_systemID = xmlSource.getSystemId();
                }
                try {
                    XMLReader reader = null;
                    if (source instanceof SAXSource) {
                        reader = ((SAXSource)source).getXMLReader();
                    }
                    if (reader == null) {
                        try {
                            final SAXParserFactory factory = SAXParserFactory.newInstance();
                            factory.setNamespaceAware(true);
                            final SAXParser jaxpParser = factory.newSAXParser();
                            reader = jaxpParser.getXMLReader();
                        }
                        catch (ParserConfigurationException ex) {
                            throw new SAXException(ex);
                        }
                        catch (FactoryConfigurationError ex2) {
                            throw new SAXException(ex2.toString());
                        }
                        catch (NoSuchMethodError noSuchMethodError) {}
                        catch (AbstractMethodError abstractMethodError) {}
                    }
                    if (reader == null) {
                        reader = XMLReaderFactory.createXMLReader();
                    }
                    try {
                        reader.setFeature("http://xml.org/sax/features/namespace-prefixes", true);
                        reader.setFeature("http://apache.org/xml/features/validation/dynamic", true);
                    }
                    catch (SAXException ex3) {}
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
                    catch (SAXException ex4) {}
                    try {
                        if (this instanceof LexicalHandler) {
                            reader.setProperty("http://xml.org/sax/handlers/LexicalHandler", this);
                        }
                        if (this instanceof DeclHandler) {
                            reader.setProperty("http://xml.org/sax/handlers/DeclHandler", this);
                        }
                    }
                    catch (SAXNotRecognizedException ex5) {}
                    reader.parse(xmlSource);
                }
                catch (WrappedRuntimeException wre) {
                    for (Throwable throwable = wre.getException(); throwable instanceof WrappedRuntimeException; throwable = ((WrappedRuntimeException)throwable).getException()) {}
                    throw new TransformerException(wre.getException());
                }
                catch (SAXException se2) {
                    throw new TransformerException(se2);
                }
                catch (IOException e) {
                    throw new TransformerException(e);
                }
            }
        }
        finally {
            if (this.m_outputStream != null) {
                try {
                    this.m_outputStream.close();
                }
                catch (IOException ex6) {}
                this.m_outputStream = null;
            }
        }
    }
    
    public void unparsedEntityDecl(final String name, final String publicId, final String systemId, final String notationName) throws SAXException {
        if (this.m_resultDTDHandler != null) {
            this.m_resultDTDHandler.unparsedEntityDecl(name, publicId, systemId, notationName);
        }
    }
}
