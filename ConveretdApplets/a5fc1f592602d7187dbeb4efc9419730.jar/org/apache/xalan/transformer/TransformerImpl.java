// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.transformer;

import java.util.Hashtable;
import org.xml.sax.Attributes;
import org.apache.xalan.trace.GenerateEvent;
import org.apache.xalan.templates.WhiteSpaceInfo;
import org.apache.xml.utils.ThreadControllerWrapper;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXNotRecognizedException;
import org.apache.xalan.templates.ElemAttributeSet;
import org.apache.xalan.templates.AVT;
import org.apache.xalan.templates.ElemSort;
import org.apache.xalan.templates.ElemForEach;
import org.apache.xpath.NodeSetDTM;
import org.apache.xml.serializer.Serializer;
import java.io.Writer;
import org.apache.xml.serializer.ToTextStream;
import java.io.StringWriter;
import org.apache.xalan.templates.ElemTextLiteral;
import org.apache.xalan.processor.TransformerFactoryImpl;
import javax.xml.transform.URIResolver;
import org.apache.xalan.templates.XUnresolvedVariable;
import java.util.Enumeration;
import org.apache.xpath.Arg;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import org.apache.xpath.VariableStack;
import org.apache.xalan.templates.ElemVariable;
import org.apache.xpath.objects.XObject;
import org.apache.xml.utils.QName;
import org.xml.sax.ext.DeclHandler;
import org.apache.xml.dtm.DTMIterator;
import org.apache.xalan.templates.Stylesheet;
import org.apache.xalan.templates.StylesheetComposed;
import org.xml.sax.Locator;
import org.apache.xalan.templates.ElemTemplate;
import org.apache.xalan.templates.ElemTemplateElement;
import org.apache.xpath.axes.SelfIteratorNoPredicate;
import java.io.OutputStream;
import org.apache.xml.serializer.SerializerFactory;
import javax.xml.transform.stream.StreamResult;
import org.apache.xml.serializer.TransformStateSetter;
import org.apache.xml.serializer.ToSAXHandler;
import org.apache.xml.serializer.ToTextSAXHandler;
import org.apache.xml.serializer.ToHTMLSAXHandler;
import javax.xml.transform.sax.SAXResult;
import org.apache.xml.serializer.ToXMLSAXHandler;
import org.xml.sax.ext.LexicalHandler;
import org.apache.xml.utils.DOMBuilder;
import org.w3c.dom.DocumentFragment;
import org.apache.xml.utils.DOMHelper;
import org.w3c.dom.Document;
import javax.xml.transform.dom.DOMResult;
import java.util.Properties;
import org.apache.xalan.res.XSLMessages;
import javax.xml.transform.SourceLocator;
import org.apache.xml.utils.SAXSourceLocator;
import org.apache.xml.dtm.DTM;
import org.apache.xml.dtm.DTMManager;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.apache.xml.utils.WrappedRuntimeException;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Node;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;
import org.apache.xpath.functions.FuncExtFunction;
import javax.xml.transform.TransformerException;
import org.apache.xml.utils.PrefixResolver;
import org.apache.xml.utils.DefaultErrorHandler;
import java.util.Vector;
import org.apache.xalan.extensions.ExtensionsTable;
import javax.xml.transform.Source;
import org.apache.xalan.trace.TraceManager;
import javax.xml.transform.ErrorListener;
import org.apache.xml.utils.BoolStack;
import org.apache.xml.serializer.SerializationHandler;
import org.apache.xpath.XPathContext;
import org.apache.xalan.templates.StylesheetRoot;
import org.apache.xml.utils.NodeVector;
import java.util.Stack;
import org.apache.xml.utils.ObjectStack;
import org.apache.xml.utils.ObjectPool;
import javax.xml.parsers.DocumentBuilder;
import org.xml.sax.ContentHandler;
import org.apache.xalan.templates.OutputProperties;
import javax.xml.transform.Result;
import java.io.FileOutputStream;
import org.apache.xml.serializer.SerializerTrace;
import org.apache.xpath.ExtensionsProvider;
import org.apache.xml.dtm.DTMWSFilter;
import javax.xml.transform.Transformer;

public class TransformerImpl extends Transformer implements Runnable, DTMWSFilter, ExtensionsProvider, SerializerTrace
{
    private Boolean m_reentryGuard;
    private FileOutputStream m_outputStream;
    private boolean m_parserEventsOnMain;
    private Thread m_transformThread;
    private String m_urlOfSource;
    private Result m_outputTarget;
    private OutputProperties m_outputFormat;
    ContentHandler m_inputContentHandler;
    private ContentHandler m_outputContentHandler;
    DocumentBuilder m_docBuilder;
    private ObjectPool m_textResultHandlerObjectPool;
    private ObjectPool m_stringWriterObjectPool;
    private OutputProperties m_textformat;
    ObjectStack m_currentTemplateElements;
    Stack m_currentMatchTemplates;
    NodeVector m_currentMatchedNodes;
    private StylesheetRoot m_stylesheetRoot;
    private boolean m_quietConflictWarnings;
    private XPathContext m_xcontext;
    private StackGuard m_stackGuard;
    private SerializationHandler m_serializationHandler;
    private KeyManager m_keyManager;
    Stack m_attrSetStack;
    CountersTable m_countersTable;
    BoolStack m_currentTemplateRuleIsNull;
    ObjectStack m_currentFuncResult;
    private MsgMgr m_msgMgr;
    public static boolean S_DEBUG;
    private ErrorListener m_errorHandler;
    private TraceManager m_traceManager;
    private Exception m_exceptionThrown;
    private Source m_xmlSource;
    private int m_doc;
    private boolean m_isTransformDone;
    private boolean m_hasBeenReset;
    private boolean m_shouldReset;
    private Stack m_modes;
    private ExtensionsTable m_extensionsTable;
    private boolean m_hasTransformThreadErrorCatcher;
    Vector m_userParams;
    static /* synthetic */ Class class$org$apache$xml$serializer$ToTextStream;
    static /* synthetic */ Class class$java$io$StringWriter;
    
    public void setShouldReset(final boolean shouldReset) {
        this.m_shouldReset = shouldReset;
    }
    
    public TransformerImpl(final StylesheetRoot stylesheet) {
        this.m_reentryGuard = new Boolean(true);
        this.m_outputStream = null;
        this.m_parserEventsOnMain = true;
        this.m_urlOfSource = null;
        this.m_outputTarget = null;
        this.m_outputContentHandler = null;
        this.m_docBuilder = null;
        this.m_textResultHandlerObjectPool = new ObjectPool((TransformerImpl.class$org$apache$xml$serializer$ToTextStream == null) ? (TransformerImpl.class$org$apache$xml$serializer$ToTextStream = class$("org.apache.xml.serializer.ToTextStream")) : TransformerImpl.class$org$apache$xml$serializer$ToTextStream);
        this.m_stringWriterObjectPool = new ObjectPool((TransformerImpl.class$java$io$StringWriter == null) ? (TransformerImpl.class$java$io$StringWriter = class$("java.io.StringWriter")) : TransformerImpl.class$java$io$StringWriter);
        this.m_textformat = new OutputProperties("text");
        this.m_currentTemplateElements = new ObjectStack(4096);
        this.m_currentMatchTemplates = new Stack();
        this.m_currentMatchedNodes = new NodeVector();
        this.m_stylesheetRoot = null;
        this.m_quietConflictWarnings = true;
        this.m_keyManager = new KeyManager();
        this.m_attrSetStack = null;
        this.m_countersTable = null;
        this.m_currentTemplateRuleIsNull = new BoolStack();
        this.m_currentFuncResult = new ObjectStack();
        this.m_errorHandler = new DefaultErrorHandler();
        this.m_traceManager = new TraceManager(this);
        this.m_exceptionThrown = null;
        this.m_isTransformDone = false;
        this.m_hasBeenReset = false;
        this.m_shouldReset = true;
        this.m_modes = new Stack();
        this.m_extensionsTable = null;
        this.m_hasTransformThreadErrorCatcher = false;
        this.setStylesheet(stylesheet);
        this.setXPathContext(new XPathContext(this));
        this.getXPathContext().setNamespaceContext(stylesheet);
        this.m_stackGuard = new StackGuard(this);
    }
    
    public ExtensionsTable getExtensionsTable() {
        return this.m_extensionsTable;
    }
    
    void setExtensionsTable(final StylesheetRoot sroot) throws TransformerException {
        try {
            if (sroot.getExtensions() != null) {
                this.m_extensionsTable = new ExtensionsTable(sroot);
            }
        }
        catch (TransformerException te) {
            te.printStackTrace();
        }
    }
    
    public boolean functionAvailable(final String ns, final String funcName) throws TransformerException {
        return this.getExtensionsTable().functionAvailable(ns, funcName);
    }
    
    public boolean elementAvailable(final String ns, final String elemName) throws TransformerException {
        return this.getExtensionsTable().elementAvailable(ns, elemName);
    }
    
    public Object extFunction(final String ns, final String funcName, final Vector argVec, final Object methodKey) throws TransformerException {
        return this.getExtensionsTable().extFunction(ns, funcName, argVec, methodKey, this.getXPathContext().getExpressionContext());
    }
    
    public Object extFunction(final FuncExtFunction extFunction, final Vector argVec) throws TransformerException {
        return this.getExtensionsTable().extFunction(extFunction, argVec, this.getXPathContext().getExpressionContext());
    }
    
    public void reset() {
        if (!this.m_hasBeenReset && this.m_shouldReset) {
            this.m_hasBeenReset = true;
            if (this.m_outputStream != null) {
                try {
                    this.m_outputStream.close();
                }
                catch (IOException ex) {}
            }
            this.m_outputStream = null;
            this.m_countersTable = null;
            this.m_xcontext.reset();
            this.m_xcontext.getVarStack().reset();
            this.resetUserParameters();
            this.m_currentTemplateElements.removeAllElements();
            this.m_currentMatchTemplates.removeAllElements();
            this.m_currentMatchedNodes.removeAllElements();
            this.m_serializationHandler = null;
            this.m_outputTarget = null;
            this.m_keyManager = new KeyManager();
            this.m_attrSetStack = null;
            this.m_countersTable = null;
            this.m_currentTemplateRuleIsNull = new BoolStack();
            this.m_xmlSource = null;
            this.m_doc = -1;
            this.m_isTransformDone = false;
            this.m_transformThread = null;
            this.m_xcontext.getSourceTreeManager().reset();
        }
    }
    
    public boolean getProperty(final String property) {
        return false;
    }
    
    public void setProperty(final String property, final Object value) {
    }
    
    public boolean isParserEventsOnMain() {
        return this.m_parserEventsOnMain;
    }
    
    public Thread getTransformThread() {
        return this.m_transformThread;
    }
    
    public void setTransformThread(final Thread t) {
        this.m_transformThread = t;
    }
    
    public boolean hasTransformThreadErrorCatcher() {
        return this.m_hasTransformThreadErrorCatcher;
    }
    
    public void transform(final Source source) throws TransformerException {
        this.transform(source, true);
    }
    
    public void transform(Source source, final boolean shouldRelease) throws TransformerException {
        try {
            if (this.getXPathContext().getNamespaceContext() == null) {
                this.getXPathContext().setNamespaceContext(this.getStylesheet());
            }
            String base = source.getSystemId();
            if (null == base) {
                base = this.m_stylesheetRoot.getBaseIdentifier();
            }
            if (null == base) {
                String currentDir = "";
                try {
                    currentDir = System.getProperty("user.dir");
                }
                catch (SecurityException ex) {}
                if (currentDir.startsWith(File.separator)) {
                    base = "file://" + currentDir;
                }
                else {
                    base = "file:///" + currentDir;
                }
                base = base + File.separatorChar + source.getClass().getName();
            }
            this.setBaseURLOfSource(base);
            final DTMManager mgr = this.m_xcontext.getDTMManager();
            Label_0299: {
                if ((!(source instanceof StreamSource) || source.getSystemId() != null || ((StreamSource)source).getInputStream() != null || ((StreamSource)source).getReader() != null) && (!(source instanceof SAXSource) || ((SAXSource)source).getInputSource() != null || ((SAXSource)source).getXMLReader() != null)) {
                    if (!(source instanceof DOMSource) || ((DOMSource)source).getNode() != null) {
                        break Label_0299;
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
                    this.fatalError(e);
                }
            }
            final DTM dtm = mgr.getDTM(source, false, this, true, true);
            dtm.setDocumentBaseURI(base);
            final boolean hardDelete = true;
            try {
                this.transformNode(dtm.getDocument());
            }
            finally {
                if (shouldRelease) {
                    mgr.release(dtm, hardDelete);
                }
            }
            final Exception e2 = this.getExceptionThrown();
            if (null != e2) {
                if (e2 instanceof TransformerException) {
                    throw (TransformerException)e2;
                }
                if (!(e2 instanceof WrappedRuntimeException)) {
                    throw new TransformerException(e2);
                }
                this.fatalError(((WrappedRuntimeException)e2).getException());
            }
            else if (null != this.m_serializationHandler) {
                this.m_serializationHandler.endDocument();
            }
        }
        catch (WrappedRuntimeException wre) {
            Throwable throwable;
            for (throwable = wre.getException(); throwable instanceof WrappedRuntimeException; throwable = ((WrappedRuntimeException)throwable).getException()) {}
            this.fatalError(throwable);
        }
        catch (SAXParseException spe) {
            this.fatalError(spe);
        }
        catch (SAXException se) {
            this.m_errorHandler.fatalError(new TransformerException(se));
        }
        finally {
            this.m_hasTransformThreadErrorCatcher = false;
            this.reset();
        }
    }
    
    private void fatalError(final Throwable throwable) throws TransformerException {
        if (throwable instanceof SAXParseException) {
            this.m_errorHandler.fatalError(new TransformerException(throwable.getMessage(), new SAXSourceLocator((SAXParseException)throwable)));
        }
        else {
            this.m_errorHandler.fatalError(new TransformerException(throwable));
        }
    }
    
    public String getBaseURLOfSource() {
        return this.m_urlOfSource;
    }
    
    public void setBaseURLOfSource(final String base) {
        this.m_urlOfSource = base;
    }
    
    public Result getOutputTarget() {
        return this.m_outputTarget;
    }
    
    public void setOutputTarget(final Result outputTarget) {
        this.m_outputTarget = outputTarget;
    }
    
    public String getOutputProperty(final String qnameString) throws IllegalArgumentException {
        String value = null;
        final OutputProperties props = this.getOutputFormat();
        value = props.getProperty(qnameString);
        if (null == value && !OutputProperties.isLegalPropertyKey(qnameString)) {
            throw new IllegalArgumentException(XSLMessages.createMessage("ER_OUTPUT_PROPERTY_NOT_RECOGNIZED", new Object[] { qnameString }));
        }
        return value;
    }
    
    public String getOutputPropertyNoDefault(final String qnameString) throws IllegalArgumentException {
        String value = null;
        final OutputProperties props = this.getOutputFormat();
        value = ((Hashtable<K, String>)props.getProperties()).get(qnameString);
        if (null == value && !OutputProperties.isLegalPropertyKey(qnameString)) {
            throw new IllegalArgumentException(XSLMessages.createMessage("ER_OUTPUT_PROPERTY_NOT_RECOGNIZED", new Object[] { qnameString }));
        }
        return value;
    }
    
    public void setOutputProperty(final String name, final String value) throws IllegalArgumentException {
        synchronized (this.m_reentryGuard) {
            if (null == this.m_outputFormat) {
                this.m_outputFormat = (OutputProperties)this.getStylesheet().getOutputComposed().clone();
            }
            if (!OutputProperties.isLegalPropertyKey(name)) {
                throw new IllegalArgumentException(XSLMessages.createMessage("ER_OUTPUT_PROPERTY_NOT_RECOGNIZED", new Object[] { name }));
            }
            this.m_outputFormat.setProperty(name, value);
        }
    }
    
    public void setOutputProperties(final Properties oformat) throws IllegalArgumentException {
        synchronized (this.m_reentryGuard) {
            if (null != oformat) {
                final String method = ((Hashtable<K, String>)oformat).get("method");
                if (null != method) {
                    this.m_outputFormat = new OutputProperties(method);
                }
                else if (this.m_outputFormat == null) {
                    this.m_outputFormat = new OutputProperties();
                }
                this.m_outputFormat.copyFrom(oformat);
                this.m_outputFormat.copyFrom(this.m_stylesheetRoot.getOutputProperties());
            }
            else {
                this.m_outputFormat = null;
            }
        }
    }
    
    public Properties getOutputProperties() {
        return (Properties)this.getOutputFormat().getProperties().clone();
    }
    
    public SerializationHandler createSerializationHandler(final Result outputTarget) throws TransformerException {
        final SerializationHandler xoh = this.createSerializationHandler(outputTarget, this.getOutputFormat());
        return xoh;
    }
    
    public SerializationHandler createSerializationHandler(final Result outputTarget, final OutputProperties format) throws TransformerException {
        Node outputNode = null;
        SerializationHandler xoh;
        if (outputTarget instanceof DOMResult) {
            outputNode = ((DOMResult)outputTarget).getNode();
            short type;
            Document doc;
            if (null != outputNode) {
                type = outputNode.getNodeType();
                doc = (Document)((9 == type) ? outputNode : outputNode.getOwnerDocument());
            }
            else {
                doc = (Document)(outputNode = DOMHelper.createDocument());
                type = outputNode.getNodeType();
                ((DOMResult)outputTarget).setNode(outputNode);
            }
            final ContentHandler handler = (11 == type) ? new DOMBuilder(doc, (DocumentFragment)outputNode) : new DOMBuilder(doc, outputNode);
            final String encoding = format.getProperty("encoding");
            xoh = new ToXMLSAXHandler(handler, (LexicalHandler)handler, encoding);
        }
        else if (outputTarget instanceof SAXResult) {
            final ContentHandler handler2 = ((SAXResult)outputTarget).getHandler();
            if (null == handler2) {
                throw new IllegalArgumentException("handler can not be null for a SAXResult");
            }
            LexicalHandler lexHandler;
            if (handler2 instanceof LexicalHandler) {
                lexHandler = (LexicalHandler)handler2;
            }
            else {
                lexHandler = null;
            }
            final String encoding2 = format.getProperty("encoding");
            final String method = format.getProperty("method");
            if ("html".equals(method)) {
                xoh = new ToHTMLSAXHandler(handler2, lexHandler, encoding2);
            }
            else if ("text".equals(method)) {
                xoh = new ToTextSAXHandler(handler2, lexHandler, encoding2);
            }
            else {
                final ToXMLSAXHandler toXMLSAXHandler = new ToXMLSAXHandler(handler2, lexHandler, encoding2);
                toXMLSAXHandler.setShouldOutputNSAttr(false);
                xoh = toXMLSAXHandler;
            }
            final String publicID = format.getProperty("doctype-public");
            final String systemID = format.getProperty("doctype-system");
            if (systemID != null) {
                xoh.setDoctypeSystem(systemID);
            }
            if (publicID != null) {
                xoh.setDoctypePublic(publicID);
            }
            if (handler2 instanceof TransformerClient) {
                final XalanTransformState state = new XalanTransformState();
                ((TransformerClient)handler2).setTransformState(state);
                ((ToSAXHandler)xoh).setTransformState(state);
            }
        }
        else {
            if (!(outputTarget instanceof StreamResult)) {
                throw new TransformerException(XSLMessages.createMessage("ER_CANNOT_TRANSFORM_TO_RESULT_TYPE", new Object[] { outputTarget.getClass().getName() }));
            }
            final StreamResult sresult = (StreamResult)outputTarget;
            final String method2 = format.getProperty("method");
            try {
                final SerializationHandler serializer = (SerializationHandler)SerializerFactory.getSerializer(format.getProperties());
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
                    serializer.setOutputStream(this.m_outputStream = new FileOutputStream(fileURL));
                    xoh = serializer;
                }
                xoh = serializer;
            }
            catch (IOException ioe) {
                throw new TransformerException(ioe);
            }
        }
        xoh.setTransformer(this);
        final SourceLocator srcLocator = this.getStylesheet();
        xoh.setSourceLocator(srcLocator);
        return xoh;
    }
    
    public void transform(final Source xmlSource, final Result outputTarget) throws TransformerException {
        this.transform(xmlSource, outputTarget, true);
    }
    
    public void transform(final Source xmlSource, final Result outputTarget, final boolean shouldRelease) throws TransformerException {
        synchronized (this.m_reentryGuard) {
            final SerializationHandler xoh = this.createSerializationHandler(outputTarget);
            this.setSerializationHandler(xoh);
            this.m_outputTarget = outputTarget;
            this.transform(xmlSource, shouldRelease);
        }
    }
    
    public void transformNode(final int node, final Result outputTarget) throws TransformerException {
        final SerializationHandler xoh = this.createSerializationHandler(outputTarget);
        this.setSerializationHandler(xoh);
        this.m_outputTarget = outputTarget;
        this.transformNode(node);
    }
    
    public void transformNode(final int node) throws TransformerException {
        this.setExtensionsTable(this.getStylesheet());
        synchronized (this.m_serializationHandler) {
            this.m_hasBeenReset = false;
            final XPathContext xctxt = this.getXPathContext();
            final DTM dtm = xctxt.getDTM(node);
            try {
                this.pushGlobalVars(node);
                final StylesheetRoot stylesheet = this.getStylesheet();
                for (int n = stylesheet.getGlobalImportCount(), i = 0; i < n; ++i) {
                    final StylesheetComposed imported = stylesheet.getGlobalImport(i);
                    for (int includedCount = imported.getIncludeCountComposed(), j = -1; j < includedCount; ++j) {
                        final Stylesheet included = imported.getIncludeComposed(j);
                        included.runtimeInit(this);
                        for (ElemTemplateElement child = included.getFirstChildElem(); child != null; child = child.getNextSiblingElem()) {
                            child.runtimeInit(this);
                        }
                    }
                }
                final DTMIterator dtmIter = new SelfIteratorNoPredicate();
                dtmIter.setRoot(node, xctxt);
                xctxt.pushContextNodeList(dtmIter);
                try {
                    this.applyTemplateToNode(null, null, node);
                }
                finally {
                    xctxt.popContextNodeList();
                }
                if (null != this.m_serializationHandler) {
                    this.m_serializationHandler.endDocument();
                }
            }
            catch (Exception se) {
                while (se instanceof WrappedRuntimeException) {
                    final Exception e = ((WrappedRuntimeException)se).getException();
                    if (null != e) {
                        se = e;
                    }
                }
                if (null != this.m_serializationHandler) {
                    try {
                        if (se instanceof SAXParseException) {
                            this.m_serializationHandler.fatalError((SAXParseException)se);
                        }
                        else if (se instanceof TransformerException) {
                            final TransformerException te = (TransformerException)se;
                            final SAXSourceLocator sl = new SAXSourceLocator(te.getLocator());
                            this.m_serializationHandler.fatalError(new SAXParseException(te.getMessage(), sl, te));
                        }
                        else {
                            this.m_serializationHandler.fatalError(new SAXParseException(se.getMessage(), new SAXSourceLocator(), se));
                        }
                    }
                    catch (Exception ex) {}
                }
                if (se instanceof TransformerException) {
                    this.m_errorHandler.fatalError((TransformerException)se);
                }
                else if (se instanceof SAXParseException) {
                    this.m_errorHandler.fatalError(new TransformerException(se.getMessage(), new SAXSourceLocator((SAXParseException)se), se));
                }
                else {
                    this.m_errorHandler.fatalError(new TransformerException(se));
                }
            }
            finally {
                this.reset();
            }
        }
    }
    
    public ContentHandler getInputContentHandler() {
        return this.getInputContentHandler(false);
    }
    
    public ContentHandler getInputContentHandler(final boolean doDocFrag) {
        if (null == this.m_inputContentHandler) {
            this.m_inputContentHandler = new TransformerHandlerImpl(this, doDocFrag, this.m_urlOfSource);
        }
        return this.m_inputContentHandler;
    }
    
    public DeclHandler getInputDeclHandler() {
        if (this.m_inputContentHandler instanceof DeclHandler) {
            return (DeclHandler)this.m_inputContentHandler;
        }
        return null;
    }
    
    public LexicalHandler getInputLexicalHandler() {
        if (this.m_inputContentHandler instanceof LexicalHandler) {
            return (LexicalHandler)this.m_inputContentHandler;
        }
        return null;
    }
    
    public void setOutputFormat(final OutputProperties oformat) {
        this.m_outputFormat = oformat;
    }
    
    public OutputProperties getOutputFormat() {
        final OutputProperties format = (null == this.m_outputFormat) ? this.getStylesheet().getOutputComposed() : this.m_outputFormat;
        return format;
    }
    
    public void setParameter(final String name, final String namespace, final Object value) {
        final VariableStack varstack = this.getXPathContext().getVarStack();
        final QName qname = new QName(namespace, name);
        final XObject xobject = XObject.create(value, this.getXPathContext());
        final StylesheetRoot sroot = this.m_stylesheetRoot;
        final Vector vars = sroot.getVariablesAndParamsComposed();
        int i = vars.size();
        while (--i >= 0) {
            final ElemVariable variable = vars.elementAt(i);
            if (variable.getXSLToken() == 41 && variable.getName().equals(qname)) {
                varstack.setGlobalVariable(i, xobject);
            }
        }
    }
    
    public void setParameter(final String name, final Object value) {
        if (value == null) {
            throw new IllegalArgumentException(XSLMessages.createMessage("ER_INVALID_SET_PARAM_VALUE", new Object[] { name }));
        }
        final StringTokenizer tokenizer = new StringTokenizer(name, "{}", false);
        try {
            final String s1 = tokenizer.nextToken();
            final String s2 = tokenizer.hasMoreTokens() ? tokenizer.nextToken() : null;
            if (null == this.m_userParams) {
                this.m_userParams = new Vector();
            }
            if (null == s2) {
                this.replaceOrPushUserParam(new QName(s1), XObject.create(value, this.getXPathContext()));
                this.setParameter(s1, null, value);
            }
            else {
                this.replaceOrPushUserParam(new QName(s1, s2), XObject.create(value, this.getXPathContext()));
                this.setParameter(s2, s1, value);
            }
        }
        catch (NoSuchElementException ex) {}
    }
    
    private void replaceOrPushUserParam(final QName qname, final XObject xval) {
        final int n = this.m_userParams.size();
        for (int i = n - 1; i >= 0; --i) {
            final Arg arg = this.m_userParams.elementAt(i);
            if (arg.getQName().equals(qname)) {
                this.m_userParams.setElementAt(new Arg(qname, xval, true), i);
                return;
            }
        }
        this.m_userParams.addElement(new Arg(qname, xval, true));
    }
    
    public Object getParameter(final String name) {
        try {
            final QName qname = QName.getQNameFromString(name);
            if (null == this.m_userParams) {
                return null;
            }
            final int n = this.m_userParams.size();
            for (int i = n - 1; i >= 0; --i) {
                final Arg arg = this.m_userParams.elementAt(i);
                if (arg.getQName().equals(qname)) {
                    return arg.getVal().object();
                }
            }
            return null;
        }
        catch (NoSuchElementException nsee) {
            return null;
        }
    }
    
    private void resetUserParameters() {
        try {
            if (null == this.m_userParams) {
                return;
            }
            final int n = this.m_userParams.size();
            for (int i = n - 1; i >= 0; --i) {
                final Arg arg = this.m_userParams.elementAt(i);
                final QName name = arg.getQName();
                final String s1 = name.getNamespace();
                final String s2 = name.getLocalPart();
                this.setParameter(s2, s1, arg.getVal().object());
            }
        }
        catch (NoSuchElementException ex) {}
    }
    
    public void setParameters(final Properties params) {
        this.clearParameters();
        final Enumeration names = params.propertyNames();
        while (names.hasMoreElements()) {
            final String name = params.getProperty(names.nextElement());
            final StringTokenizer tokenizer = new StringTokenizer(name, "{}", false);
            try {
                final String s1 = tokenizer.nextToken();
                final String s2 = tokenizer.hasMoreTokens() ? tokenizer.nextToken() : null;
                if (null == s2) {
                    this.setParameter(s1, null, params.getProperty(name));
                }
                else {
                    this.setParameter(s2, s1, params.getProperty(name));
                }
            }
            catch (NoSuchElementException ex) {}
        }
    }
    
    public void clearParameters() {
        synchronized (this.m_reentryGuard) {
            final VariableStack varstack = new VariableStack();
            this.m_xcontext.setVarStack(varstack);
            this.m_userParams = null;
        }
    }
    
    protected void pushGlobalVars(final int contextNode) throws TransformerException {
        final XPathContext xctxt = this.m_xcontext;
        final VariableStack vs = xctxt.getVarStack();
        final StylesheetRoot sr = this.getStylesheet();
        final Vector vars = sr.getVariablesAndParamsComposed();
        int i = vars.size();
        vs.link(i);
        while (--i >= 0) {
            final ElemVariable v = vars.elementAt(i);
            final XObject xobj = new XUnresolvedVariable(v, contextNode, this, vs.getStackFrame(), 0, true);
            if (null == vs.elementAt(i)) {
                vs.setGlobalVariable(i, xobj);
            }
        }
    }
    
    public void setURIResolver(final URIResolver resolver) {
        synchronized (this.m_reentryGuard) {
            this.m_xcontext.getSourceTreeManager().setURIResolver(resolver);
        }
    }
    
    public URIResolver getURIResolver() {
        return this.m_xcontext.getSourceTreeManager().getURIResolver();
    }
    
    public void setContentHandler(final ContentHandler handler) {
        if (handler == null) {
            throw new NullPointerException(XSLMessages.createMessage("ER_NULL_CONTENT_HANDLER", null));
        }
        this.m_outputContentHandler = handler;
        if (null == this.m_serializationHandler) {
            final ToXMLSAXHandler h = new ToXMLSAXHandler();
            h.setContentHandler(handler);
            h.setTransformer(this);
            this.m_serializationHandler = h;
        }
        else {
            this.m_serializationHandler.setContentHandler(handler);
        }
    }
    
    public ContentHandler getContentHandler() {
        return this.m_outputContentHandler;
    }
    
    public int transformToRTF(final ElemTemplateElement templateParent) throws TransformerException {
        final DTM dtmFrag = this.m_xcontext.getRTFDTM();
        return this.transformToRTF(templateParent, dtmFrag);
    }
    
    public int transformToGlobalRTF(final ElemTemplateElement templateParent) throws TransformerException {
        final DTM dtmFrag = this.m_xcontext.getGlobalRTFDTM();
        return this.transformToRTF(templateParent, dtmFrag);
    }
    
    private int transformToRTF(final ElemTemplateElement templateParent, final DTM dtmFrag) throws TransformerException {
        final XPathContext xctxt = this.m_xcontext;
        final ContentHandler rtfHandler = dtmFrag.getContentHandler();
        final SerializationHandler savedRTreeHandler = this.m_serializationHandler;
        final ToSAXHandler h = new ToXMLSAXHandler();
        h.setContentHandler(rtfHandler);
        h.setTransformer(this);
        this.m_serializationHandler = h;
        final SerializationHandler rth = this.m_serializationHandler;
        int resultFragment;
        try {
            rth.startDocument();
            rth.flushPending();
            try {
                this.executeChildTemplates(templateParent, true);
                rth.flushPending();
                resultFragment = dtmFrag.getDocument();
            }
            finally {
                rth.endDocument();
            }
        }
        catch (SAXException se) {
            throw new TransformerException(se);
        }
        finally {
            this.m_serializationHandler = savedRTreeHandler;
        }
        return resultFragment;
    }
    
    public ObjectPool getStringWriterPool() {
        return this.m_stringWriterObjectPool;
    }
    
    public String transformToString(final ElemTemplateElement elem) throws TransformerException {
        final ElemTemplateElement firstChild = elem.getFirstChildElem();
        if (null == firstChild) {
            return "";
        }
        if (elem.hasTextLitOnly() && TransformerFactoryImpl.m_optimize) {
            return ((ElemTextLiteral)firstChild).getNodeValue();
        }
        final SerializationHandler savedRTreeHandler = this.m_serializationHandler;
        final StringWriter sw = (StringWriter)this.m_stringWriterObjectPool.getInstance();
        this.m_serializationHandler = (ToTextStream)this.m_textResultHandlerObjectPool.getInstance();
        if (null == this.m_serializationHandler) {
            final Serializer serializer = SerializerFactory.getSerializer(this.m_textformat.getProperties());
            this.m_serializationHandler = (SerializationHandler)serializer;
        }
        this.m_serializationHandler.setTransformer(this);
        this.m_serializationHandler.setWriter(sw);
        String result;
        try {
            this.executeChildTemplates(elem, true);
            this.m_serializationHandler.endDocument();
            result = sw.toString();
        }
        catch (SAXException se) {
            throw new TransformerException(se);
        }
        finally {
            sw.getBuffer().setLength(0);
            try {
                sw.close();
            }
            catch (Exception ex) {}
            this.m_stringWriterObjectPool.freeInstance(sw);
            this.m_serializationHandler.reset();
            this.m_textResultHandlerObjectPool.freeInstance(this.m_serializationHandler);
            this.m_serializationHandler = savedRTreeHandler;
        }
        return result;
    }
    
    public boolean applyTemplateToNode(final ElemTemplateElement xslInstruction, ElemTemplate template, final int child) throws TransformerException {
        final DTM dtm = this.m_xcontext.getDTM(child);
        final short nodeType = dtm.getNodeType(child);
        boolean isDefaultTextRule = false;
        boolean isApplyImports = false;
        if (null == template) {
            int endImportLevel = 0;
            isApplyImports = (xslInstruction != null && xslInstruction.getXSLToken() == 72);
            int maxImportLevel;
            if (isApplyImports) {
                maxImportLevel = xslInstruction.getStylesheetComposed().getImportCountComposed() - 1;
                endImportLevel = xslInstruction.getStylesheetComposed().getEndImportCountComposed();
            }
            else {
                maxImportLevel = -1;
            }
            if (isApplyImports && maxImportLevel == -1) {
                template = null;
            }
            else {
                final XPathContext xctxt = this.m_xcontext;
                try {
                    xctxt.pushNamespaceContext(xslInstruction);
                    final QName mode = this.getMode();
                    if (isApplyImports) {
                        template = this.m_stylesheetRoot.getTemplateComposed(xctxt, child, mode, maxImportLevel, endImportLevel, this.m_quietConflictWarnings, dtm);
                    }
                    else {
                        template = this.m_stylesheetRoot.getTemplateComposed(xctxt, child, mode, this.m_quietConflictWarnings, dtm);
                    }
                }
                finally {
                    xctxt.popNamespaceContext();
                }
            }
            if (null == template) {
                switch (nodeType) {
                    case 0:
                    case 10: {
                        template = this.m_stylesheetRoot.getDefaultRule();
                        break;
                    }
                    case 1:
                    case 2:
                    case 3: {
                        template = this.m_stylesheetRoot.getDefaultTextRule();
                        isDefaultTextRule = true;
                        break;
                    }
                    case 8: {
                        template = this.m_stylesheetRoot.getDefaultRootRule();
                        break;
                    }
                    default: {
                        return false;
                    }
                }
            }
        }
        try {
            this.pushElemTemplateElement(template);
            this.m_xcontext.pushCurrentNode(child);
            this.pushPairCurrentMatched(template, child);
            if (!isApplyImports) {
                final DTMIterator cnl = new NodeSetDTM(child, this.m_xcontext.getDTMManager());
                this.m_xcontext.pushContextNodeList(cnl);
            }
            if (isDefaultTextRule) {
                switch (nodeType) {
                    case 1:
                    case 2: {
                        ClonerToResultTree.cloneToResultTree(child, nodeType, dtm, this.getResultTreeHandler(), false);
                        break;
                    }
                    case 0: {
                        dtm.dispatchCharactersEvents(child, this.getResultTreeHandler(), false);
                        break;
                    }
                }
            }
            else {
                if (TransformerImpl.S_DEBUG) {
                    this.getTraceManager().fireTraceEvent(template);
                }
                this.m_xcontext.setSAXLocator(template);
                this.m_xcontext.getVarStack().link(template.m_frameSize);
                this.executeChildTemplates(template, true);
                if (TransformerImpl.S_DEBUG) {
                    this.getTraceManager().fireTraceEndEvent(template);
                }
            }
        }
        catch (SAXException se) {
            throw new TransformerException(se);
        }
        finally {
            if (!isDefaultTextRule) {
                this.m_xcontext.getVarStack().unlink();
            }
            this.m_xcontext.popCurrentNode();
            if (!isApplyImports) {
                this.m_xcontext.popContextNodeList();
                this.popCurrentMatched();
            }
            this.popElemTemplateElement();
        }
        return true;
    }
    
    public void executeChildTemplates(final ElemTemplateElement elem, final Node context, final QName mode, final ContentHandler handler) throws TransformerException {
        final XPathContext xctxt = this.m_xcontext;
        try {
            if (null != mode) {
                this.pushMode(mode);
            }
            xctxt.pushCurrentNode(xctxt.getDTMHandleFromNode(context));
            this.executeChildTemplates(elem, handler);
        }
        finally {
            xctxt.popCurrentNode();
            if (null != mode) {
                this.popMode();
            }
        }
    }
    
    public void executeChildTemplates(final ElemTemplateElement elem, final boolean shouldAddAttrs) throws TransformerException {
        ElemTemplateElement t = elem.getFirstChildElem();
        if (null == t) {
            return;
        }
        if (elem.hasTextLitOnly() && TransformerFactoryImpl.m_optimize) {
            final char[] chars = ((ElemTextLiteral)t).getChars();
            try {
                this.pushElemTemplateElement(t);
                this.m_serializationHandler.characters(chars, 0, chars.length);
            }
            catch (SAXException se) {
                throw new TransformerException(se);
            }
            finally {
                this.popElemTemplateElement();
            }
            return;
        }
        final XPathContext xctxt = this.m_xcontext;
        xctxt.pushSAXLocatorNull();
        final int currentTemplateElementsTop = this.m_currentTemplateElements.size();
        this.m_currentTemplateElements.push(null);
        try {
            while (t != null) {
                if (shouldAddAttrs || t.getXSLToken() != 48) {
                    xctxt.setSAXLocator(t);
                    this.m_currentTemplateElements.setElementAt(t, currentTemplateElementsTop);
                    t.execute(this);
                }
                t = t.getNextSiblingElem();
            }
        }
        catch (RuntimeException re) {
            final TransformerException te = new TransformerException(re);
            te.setLocator(t);
            throw te;
        }
        finally {
            this.m_currentTemplateElements.pop();
            xctxt.popSAXLocator();
        }
    }
    
    public void executeChildTemplates(final ElemTemplateElement elem, final ContentHandler handler) throws TransformerException {
        final SerializationHandler savedHandler;
        final SerializationHandler xoh = savedHandler = this.getSerializationHandler();
        try {
            xoh.flushPending();
            LexicalHandler lex = null;
            if (handler instanceof LexicalHandler) {
                lex = (LexicalHandler)handler;
            }
            (this.m_serializationHandler = new ToXMLSAXHandler(handler, lex, savedHandler.getEncoding())).setTransformer(this);
            this.executeChildTemplates(elem, true);
        }
        catch (TransformerException e) {
            throw e;
        }
        catch (SAXException se) {
            throw new TransformerException(se);
        }
        finally {
            this.m_serializationHandler = savedHandler;
        }
    }
    
    public Vector processSortKeys(final ElemForEach foreach, final int sourceNodeContext) throws TransformerException {
        Vector keys = null;
        final XPathContext xctxt = this.m_xcontext;
        final int nElems = foreach.getSortElemCount();
        if (nElems > 0) {
            keys = new Vector();
        }
        for (int i = 0; i < nElems; ++i) {
            final ElemSort sort = foreach.getSortElem(i);
            if (TransformerImpl.S_DEBUG) {
                this.getTraceManager().fireTraceEvent(sort);
            }
            final String langString = (null != sort.getLang()) ? sort.getLang().evaluate(xctxt, sourceNodeContext, foreach) : null;
            final String dataTypeString = sort.getDataType().evaluate(xctxt, sourceNodeContext, foreach);
            if (dataTypeString.indexOf(":") >= 0) {
                System.out.println("TODO: Need to write the hooks for QNAME sort data type");
            }
            else if (!dataTypeString.equalsIgnoreCase("text") && !dataTypeString.equalsIgnoreCase("number")) {
                foreach.error("ER_ILLEGAL_ATTRIBUTE_VALUE", new Object[] { "data-type", dataTypeString });
            }
            final boolean treatAsNumbers = null != dataTypeString && dataTypeString.equals("number");
            final String orderString = sort.getOrder().evaluate(xctxt, sourceNodeContext, foreach);
            if (!orderString.equalsIgnoreCase("ascending") && !orderString.equalsIgnoreCase("descending")) {
                foreach.error("ER_ILLEGAL_ATTRIBUTE_VALUE", new Object[] { "order", orderString });
            }
            final boolean descending = null != orderString && orderString.equals("descending");
            final AVT caseOrder = sort.getCaseOrder();
            boolean caseOrderUpper;
            if (null != caseOrder) {
                final String caseOrderString = caseOrder.evaluate(xctxt, sourceNodeContext, foreach);
                if (!caseOrderString.equalsIgnoreCase("upper-first") && !caseOrderString.equalsIgnoreCase("lower-first")) {
                    foreach.error("ER_ILLEGAL_ATTRIBUTE_VALUE", new Object[] { "case-order", caseOrderString });
                }
                caseOrderUpper = (null != caseOrderString && caseOrderString.equals("upper-first"));
            }
            else {
                caseOrderUpper = false;
            }
            keys.addElement(new NodeSortKey(this, sort.getSelect(), treatAsNumbers, descending, langString, caseOrderUpper, foreach));
            if (TransformerImpl.S_DEBUG) {
                this.getTraceManager().fireTraceEndEvent(sort);
            }
        }
        return keys;
    }
    
    public Vector getElementCallstack() {
        final Vector elems = new Vector();
        for (int nStackSize = this.m_currentTemplateElements.size(), i = 0; i < nStackSize; ++i) {
            final ElemTemplateElement elem = (ElemTemplateElement)this.m_currentTemplateElements.elementAt(i);
            if (null != elem) {
                elems.addElement(elem);
            }
        }
        return elems;
    }
    
    public int getCurrentTemplateElementsCount() {
        return this.m_currentTemplateElements.size();
    }
    
    public ObjectStack getCurrentTemplateElements() {
        return this.m_currentTemplateElements;
    }
    
    public void pushElemTemplateElement(final ElemTemplateElement elem) {
        this.m_currentTemplateElements.push(elem);
    }
    
    public void popElemTemplateElement() {
        this.m_currentTemplateElements.pop();
    }
    
    public void setCurrentElement(final ElemTemplateElement e) {
        this.m_currentTemplateElements.setTop(e);
    }
    
    public ElemTemplateElement getCurrentElement() {
        return (this.m_currentTemplateElements.size() > 0) ? ((ElemTemplateElement)this.m_currentTemplateElements.peek()) : null;
    }
    
    public int getCurrentNode() {
        return this.m_xcontext.getCurrentNode();
    }
    
    public Vector getTemplateCallstack() {
        final Vector elems = new Vector();
        for (int nStackSize = this.m_currentTemplateElements.size(), i = 0; i < nStackSize; ++i) {
            final ElemTemplateElement elem = (ElemTemplateElement)this.m_currentTemplateElements.elementAt(i);
            if (null != elem && elem.getXSLToken() != 19) {
                elems.addElement(elem);
            }
        }
        return elems;
    }
    
    public ElemTemplate getCurrentTemplate() {
        ElemTemplateElement elem;
        for (elem = this.getCurrentElement(); null != elem && elem.getXSLToken() != 19; elem = elem.getParentElem()) {}
        return (ElemTemplate)elem;
    }
    
    public void pushPairCurrentMatched(final ElemTemplateElement template, final int child) {
        this.m_currentMatchTemplates.push(template);
        this.m_currentMatchedNodes.push(child);
    }
    
    public void popCurrentMatched() {
        this.m_currentMatchTemplates.pop();
        this.m_currentMatchedNodes.pop();
    }
    
    public ElemTemplate getMatchedTemplate() {
        return this.m_currentMatchTemplates.peek();
    }
    
    public int getMatchedNode() {
        return this.m_currentMatchedNodes.peepTail();
    }
    
    public DTMIterator getContextNodeList() {
        try {
            final DTMIterator cnl = this.m_xcontext.getContextNodeList();
            return (cnl == null) ? null : cnl.cloneWithReset();
        }
        catch (CloneNotSupportedException cnse) {
            return null;
        }
    }
    
    public Transformer getTransformer() {
        return this;
    }
    
    public void setStylesheet(final StylesheetRoot stylesheetRoot) {
        this.m_stylesheetRoot = stylesheetRoot;
    }
    
    public final StylesheetRoot getStylesheet() {
        return this.m_stylesheetRoot;
    }
    
    public boolean getQuietConflictWarnings() {
        return this.m_quietConflictWarnings;
    }
    
    public void setQuietConflictWarnings(final boolean b) {
        this.m_quietConflictWarnings = b;
    }
    
    public void setXPathContext(final XPathContext xcontext) {
        this.m_xcontext = xcontext;
    }
    
    public final XPathContext getXPathContext() {
        return this.m_xcontext;
    }
    
    public StackGuard getStackGuard() {
        return this.m_stackGuard;
    }
    
    public int getRecursionLimit() {
        return this.m_stackGuard.getRecursionLimit();
    }
    
    public void setRecursionLimit(final int limit) {
        this.m_stackGuard.setRecursionLimit(limit);
    }
    
    public SerializationHandler getResultTreeHandler() {
        return this.m_serializationHandler;
    }
    
    public SerializationHandler getSerializationHandler() {
        return this.m_serializationHandler;
    }
    
    public KeyManager getKeyManager() {
        return this.m_keyManager;
    }
    
    public boolean isRecursiveAttrSet(final ElemAttributeSet attrSet) {
        if (null == this.m_attrSetStack) {
            this.m_attrSetStack = new Stack();
        }
        if (!this.m_attrSetStack.empty()) {
            final int loc = this.m_attrSetStack.search(attrSet);
            if (loc > -1) {
                return true;
            }
        }
        return false;
    }
    
    public void pushElemAttributeSet(final ElemAttributeSet attrSet) {
        this.m_attrSetStack.push(attrSet);
    }
    
    public void popElemAttributeSet() {
        this.m_attrSetStack.pop();
    }
    
    public CountersTable getCountersTable() {
        if (null == this.m_countersTable) {
            this.m_countersTable = new CountersTable();
        }
        return this.m_countersTable;
    }
    
    public boolean currentTemplateRuleIsNull() {
        return !this.m_currentTemplateRuleIsNull.isEmpty() && this.m_currentTemplateRuleIsNull.peek();
    }
    
    public void pushCurrentTemplateRuleIsNull(final boolean b) {
        this.m_currentTemplateRuleIsNull.push(b);
    }
    
    public void popCurrentTemplateRuleIsNull() {
        this.m_currentTemplateRuleIsNull.pop();
    }
    
    public void pushCurrentFuncResult(final Object val) {
        this.m_currentFuncResult.push(val);
    }
    
    public Object popCurrentFuncResult() {
        return this.m_currentFuncResult.pop();
    }
    
    public boolean currentFuncResultSeen() {
        return !this.m_currentFuncResult.empty() && this.m_currentFuncResult.peek() != null;
    }
    
    public MsgMgr getMsgMgr() {
        if (null == this.m_msgMgr) {
            this.m_msgMgr = new MsgMgr(this);
        }
        return this.m_msgMgr;
    }
    
    public void setErrorListener(final ErrorListener listener) throws IllegalArgumentException {
        synchronized (this.m_reentryGuard) {
            if (listener == null) {
                throw new IllegalArgumentException(XSLMessages.createMessage("ER_NULL_ERROR_HANDLER", null));
            }
            this.m_errorHandler = listener;
        }
    }
    
    public ErrorListener getErrorListener() {
        return this.m_errorHandler;
    }
    
    public TraceManager getTraceManager() {
        return this.m_traceManager;
    }
    
    public boolean getFeature(final String name) throws SAXNotRecognizedException, SAXNotSupportedException {
        if ("http://xml.org/trax/features/sax/input".equals(name)) {
            return true;
        }
        if ("http://xml.org/trax/features/dom/input".equals(name)) {
            return true;
        }
        throw new SAXNotRecognizedException(name);
    }
    
    public QName getMode() {
        return this.m_modes.isEmpty() ? null : this.m_modes.peek();
    }
    
    public void pushMode(final QName mode) {
        this.m_modes.push(mode);
    }
    
    public void popMode() {
        this.m_modes.pop();
    }
    
    public void runTransformThread(final int priority) {
        final Thread t = ThreadControllerWrapper.runThread(this, priority);
        this.setTransformThread(t);
    }
    
    public void runTransformThread() {
        ThreadControllerWrapper.runThread(this, -1);
    }
    
    public static void runTransformThread(final Runnable runnable) {
        ThreadControllerWrapper.runThread(runnable, -1);
    }
    
    public void waitTransformThread() throws SAXException {
        final Thread transformThread = this.getTransformThread();
        if (null != transformThread) {
            try {
                ThreadControllerWrapper.waitThread(transformThread, this);
                if (!this.hasTransformThreadErrorCatcher()) {
                    final Exception e = this.getExceptionThrown();
                    if (null != e) {
                        e.printStackTrace();
                        throw new SAXException(e);
                    }
                }
                this.setTransformThread(null);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public Exception getExceptionThrown() {
        return this.m_exceptionThrown;
    }
    
    public void setExceptionThrown(final Exception e) {
        this.m_exceptionThrown = e;
    }
    
    public void setSourceTreeDocForThread(final int doc) {
        this.m_doc = doc;
    }
    
    public void setXMLSource(final Source source) {
        this.m_xmlSource = source;
    }
    
    public boolean isTransformDone() {
        synchronized (this) {
            return this.m_isTransformDone;
        }
    }
    
    public void setIsTransformDone(final boolean done) {
        synchronized (this) {
            this.m_isTransformDone = done;
        }
    }
    
    void postExceptionFromThread(final Exception e) {
        this.m_isTransformDone = true;
        this.m_exceptionThrown = e;
        synchronized (this) {
            this.notifyAll();
        }
    }
    
    public void run() {
        this.m_hasBeenReset = false;
        try {
            try {
                this.m_isTransformDone = false;
                this.transformNode(this.m_doc);
            }
            catch (Exception e) {
                if (null == this.m_transformThread) {
                    throw new RuntimeException(e.getMessage());
                }
                this.postExceptionFromThread(e);
            }
            finally {
                this.m_isTransformDone = true;
                if (this.m_inputContentHandler instanceof TransformerHandlerImpl) {
                    ((TransformerHandlerImpl)this.m_inputContentHandler).clearCoRoutine();
                }
            }
        }
        catch (Exception e) {
            if (null == this.m_transformThread) {
                throw new RuntimeException(e.getMessage());
            }
            this.postExceptionFromThread(e);
        }
    }
    
    public TransformSnapshot getSnapshot() {
        return new TransformSnapshotImpl(this);
    }
    
    public void executeFromSnapshot(final TransformSnapshot ts) throws TransformerException {
        final ElemTemplateElement template = this.getMatchedTemplate();
        final int child = this.getMatchedNode();
        this.pushElemTemplateElement(template);
        this.m_xcontext.pushCurrentNode(child);
        this.executeChildTemplates(template, true);
    }
    
    public void resetToStylesheet(final TransformSnapshot ts) {
        ((TransformSnapshotImpl)ts).apply(this);
    }
    
    public void stopTransformation() {
    }
    
    public short getShouldStripSpace(final int elementHandle, final DTM dtm) {
        try {
            final WhiteSpaceInfo info = this.m_stylesheetRoot.getWhiteSpaceInfo(this.m_xcontext, elementHandle, dtm);
            if (null == info) {
                return 3;
            }
            return (short)(info.getShouldStripSpace() ? 2 : 1);
        }
        catch (TransformerException se) {
            return 3;
        }
    }
    
    public void init(final ToXMLSAXHandler h, final Transformer transformer, final ContentHandler realHandler) {
        h.setTransformer(transformer);
        h.setContentHandler(realHandler);
    }
    
    public void setSerializationHandler(final SerializationHandler xoh) {
        this.m_serializationHandler = xoh;
    }
    
    public void fireGenerateEvent(final int eventType, final char[] ch, final int start, final int length) {
        final GenerateEvent ge = new GenerateEvent(this, eventType, ch, start, length);
        this.m_traceManager.fireGenerateEvent(ge);
    }
    
    public void fireGenerateEvent(final int eventType, final String name, final Attributes atts) {
        final GenerateEvent ge = new GenerateEvent(this, eventType, name, atts);
        this.m_traceManager.fireGenerateEvent(ge);
    }
    
    public void fireGenerateEvent(final int eventType, final String name, final String data) {
        final GenerateEvent ge = new GenerateEvent(this, eventType, name, data);
        this.m_traceManager.fireGenerateEvent(ge);
    }
    
    public void fireGenerateEvent(final int eventType, final String data) {
        final GenerateEvent ge = new GenerateEvent(this, eventType, data);
        this.m_traceManager.fireGenerateEvent(ge);
    }
    
    public void fireGenerateEvent(final int eventType) {
        final GenerateEvent ge = new GenerateEvent(this, eventType);
        this.m_traceManager.fireGenerateEvent(ge);
    }
    
    public boolean hasTraceListeners() {
        return this.m_traceManager.hasTraceListeners();
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError(x.getMessage());
        }
    }
    
    static {
        TransformerImpl.S_DEBUG = false;
    }
}
