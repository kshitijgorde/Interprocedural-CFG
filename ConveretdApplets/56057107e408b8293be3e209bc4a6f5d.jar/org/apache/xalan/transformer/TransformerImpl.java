// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.transformer;

import java.util.Hashtable;
import java.io.Writer;
import java.io.StringWriter;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.xalan.stree.Child;
import org.apache.xalan.templates.Stylesheet;
import org.apache.xalan.templates.StylesheetComposed;
import org.apache.xpath.SourceTreeManager;
import org.xml.sax.SAXParseException;
import org.apache.xml.utils.SAXSourceLocator;
import org.apache.xml.utils.WrappedRuntimeException;
import org.apache.xalan.res.XSLMessages;
import java.util.Enumeration;
import java.util.StringTokenizer;
import org.apache.xpath.XPath;
import org.apache.xalan.templates.ElemWithParam;
import org.apache.xpath.objects.XRTreeFrag;
import org.apache.xpath.objects.XString;
import org.apache.xalan.templates.ElemCallTemplate;
import org.apache.xpath.objects.XObject;
import org.apache.xalan.templates.XUnresolvedVariable;
import org.apache.xalan.templates.ElemVariable;
import org.apache.xalan.templates.AVT;
import org.apache.xalan.templates.ElemSort;
import org.apache.xalan.templates.ElemForEach;
import javax.xml.parsers.SAXParser;
import org.xml.sax.XMLReader;
import org.xml.sax.InputSource;
import org.xml.sax.DTDHandler;
import org.xml.sax.helpers.XMLReaderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.dom.DOMSource;
import org.apache.xalan.templates.ElemAttributeSet;
import javax.xml.transform.URIResolver;
import java.util.NoSuchElementException;
import org.apache.xpath.Arg;
import java.util.Properties;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.ext.DeclHandler;
import org.apache.xalan.stree.SourceTreeHandler;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXNotRecognizedException;
import org.apache.xalan.templates.ElemTemplate;
import org.w3c.dom.traversal.NodeIterator;
import javax.xml.transform.SourceLocator;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.OutputStream;
import org.apache.xalan.serialize.SerializerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.sax.SAXResult;
import org.apache.xml.utils.DOMBuilder;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Document;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.Result;
import org.apache.xpath.VariableStack;
import org.xml.sax.SAXException;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Attr;
import org.apache.xml.utils.QName;
import org.apache.xalan.templates.ElemTemplateElement;
import org.apache.xml.utils.PrefixResolver;
import org.apache.xml.utils.DefaultErrorHandler;
import java.util.Vector;
import org.w3c.dom.Node;
import javax.xml.transform.Source;
import org.apache.xalan.trace.TraceManager;
import javax.xml.transform.ErrorListener;
import org.apache.xml.utils.BoolStack;
import java.util.Stack;
import org.apache.xpath.XPathContext;
import org.apache.xalan.templates.StylesheetRoot;
import org.apache.xml.utils.NodeVector;
import org.apache.xml.utils.ObjectPool;
import javax.xml.parsers.DocumentBuilder;
import org.xml.sax.ContentHandler;
import org.apache.xalan.serialize.Serializer;
import org.apache.xalan.templates.OutputProperties;
import java.io.FileOutputStream;
import javax.xml.transform.Transformer;

public class TransformerImpl extends Transformer implements Runnable, TransformState
{
    private Boolean m_reentryGuard;
    private FileOutputStream m_outputStream;
    private boolean m_parserEventsOnMain;
    private Thread m_transformThread;
    private String m_urlOfSource;
    private OutputProperties m_outputFormat;
    private Serializer m_serializer;
    ContentHandler m_inputContentHandler;
    private ContentHandler m_outputContentHandler;
    DocumentBuilder m_docBuilder;
    private ObjectPool m_textResultHandlerObjectPool;
    private ObjectPool m_stringWriterObjectPool;
    private OutputProperties m_textformat;
    private NodeVector m_currentTemplateElements;
    private NodeVector m_currentMatchTemplates;
    private StylesheetRoot m_stylesheetRoot;
    private boolean m_quietConflictWarnings;
    private XPathContext m_xcontext;
    private StackGuard m_stackGuard;
    private ResultTreeHandler m_resultTreeHandler;
    private KeyManager m_keyManager;
    private Stack m_attrSetStack;
    private CountersTable m_countersTable;
    private BoolStack m_currentTemplateRuleIsNull;
    private MsgMgr m_msgMgr;
    public static boolean S_DEBUG;
    private ErrorListener m_errorHandler;
    private TraceManager m_traceManager;
    private Exception m_exceptionThrown;
    private Source m_xmlSource;
    private Node m_doc;
    private boolean m_isTransformDone;
    private boolean m_hasBeenReset;
    Vector m_userParams;
    
    static {
        TransformerImpl.S_DEBUG = false;
    }
    
    public TransformerImpl(final StylesheetRoot stylesheet) {
        this.m_reentryGuard = new Boolean(true);
        this.m_outputStream = null;
        this.m_parserEventsOnMain = true;
        this.m_urlOfSource = null;
        this.m_outputContentHandler = null;
        this.m_docBuilder = null;
        this.m_textResultHandlerObjectPool = new ObjectPool("org.apache.xalan.transformer.ResultTreeHandler");
        this.m_stringWriterObjectPool = new ObjectPool("java.io.StringWriter");
        this.m_textformat = new OutputProperties("text");
        this.m_currentTemplateElements = new NodeVector(64);
        this.m_currentMatchTemplates = new NodeVector();
        this.m_stylesheetRoot = null;
        this.m_quietConflictWarnings = true;
        this.m_stackGuard = new StackGuard();
        this.m_keyManager = new KeyManager();
        this.m_attrSetStack = null;
        this.m_countersTable = null;
        this.m_currentTemplateRuleIsNull = new BoolStack();
        this.m_errorHandler = new DefaultErrorHandler();
        this.m_traceManager = new TraceManager(this);
        this.m_exceptionThrown = null;
        this.m_isTransformDone = false;
        this.m_hasBeenReset = false;
        this.setStylesheet(stylesheet);
        this.setXPathContext(new XPathContext(this));
        this.getXPathContext().setNamespaceContext(stylesheet);
    }
    
    public boolean applyTemplateToNode(final ElemTemplateElement xslInstruction, ElemTemplateElement template, final Node child, final QName mode) throws TransformerException {
        final short nodeType = child.getNodeType();
        boolean isDefaultTextRule = false;
        if (template == null) {
            final boolean isApplyImports = xslInstruction != null && xslInstruction.getXSLToken() == 72;
            int maxImportLevel;
            if (isApplyImports) {
                maxImportLevel = xslInstruction.getStylesheetComposed().getImportCountComposed() - 1;
            }
            else {
                maxImportLevel = -1;
            }
            final XPathContext xctxt = this.getXPathContext();
            final PrefixResolver savedPrefixResolver = xctxt.getNamespaceContext();
            try {
                xctxt.setNamespaceContext(xslInstruction);
                template = this.m_stylesheetRoot.getTemplateComposed(xctxt, child, mode, maxImportLevel, this.m_quietConflictWarnings);
            }
            finally {
                xctxt.setNamespaceContext(savedPrefixResolver);
            }
            if (template == null) {
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
            this.pushPairCurrentMatched(template, child);
            if (isDefaultTextRule) {
                switch (nodeType) {
                    case 1:
                    case 2: {
                        this.m_resultTreeHandler.m_cloner.cloneToResultTree(child, false);
                        break;
                    }
                    case 0: {
                        final String val = ((Attr)child).getValue();
                        this.getResultTreeHandler().characters(val.toCharArray(), 0, val.length());
                        break;
                    }
                }
            }
            else {
                if (TransformerImpl.S_DEBUG) {
                    this.getTraceManager().fireTraceEvent(child, mode, template);
                }
                if (template.isCompiledTemplate()) {
                    template.execute(this, child, mode);
                }
                else {
                    this.executeChildTemplates(template, child, mode);
                }
            }
        }
        catch (SAXException se) {
            throw new TransformerException(se);
        }
        finally {
            this.popCurrentMatched();
        }
        return true;
    }
    
    public void clearParameters() {
        synchronized (this.m_reentryGuard) {
            final VariableStack varstack = new VariableStack();
            this.getXPathContext().setVarStack(varstack);
            this.m_userParams = null;
        }
        // monitorexit(this.m_reentryGuard)
    }
    
    public ContentHandler createResultContentHandler(final Result outputTarget) throws TransformerException {
        return this.createResultContentHandler(outputTarget, this.getOutputFormat());
    }
    
    public ContentHandler createResultContentHandler(final Result outputTarget, final OutputProperties format) throws TransformerException {
        Node outputNode = null;
        ContentHandler handler;
        if (outputTarget instanceof DOMResult) {
            outputNode = ((DOMResult)outputTarget).getNode();
            short type;
            Document doc;
            if (outputNode != null) {
                type = outputNode.getNodeType();
                doc = (Document)((type == 9) ? outputNode : outputNode.getOwnerDocument());
            }
            else {
                doc = (Document)(outputNode = this.getXPathContext().getDOMHelper().createDocument());
                type = outputNode.getNodeType();
                ((DOMResult)outputTarget).setNode(outputNode);
            }
            handler = ((type == 11) ? new DOMBuilder(doc, (DocumentFragment)outputNode) : new DOMBuilder(doc, outputNode));
        }
        else {
            if (!(outputTarget instanceof SAXResult)) {
                if (outputTarget instanceof StreamResult) {
                    final StreamResult sresult = (StreamResult)outputTarget;
                    final String method = format.getProperty("method");
                    try {
                        final Serializer serializer = SerializerFactory.getSerializer(format.getProperties());
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
                        handler = serializer.asContentHandler();
                        this.setSerializer(serializer);
                        return handler;
                    }
                    catch (UnsupportedEncodingException uee) {
                        throw new TransformerException(uee);
                    }
                    catch (IOException ioe) {
                        throw new TransformerException(ioe);
                    }
                }
                throw new TransformerException("Can't transform to a Result of type " + outputTarget.getClass().getName() + "!");
            }
            handler = ((SAXResult)outputTarget).getHandler();
            if (handler == null) {
                throw new IllegalArgumentException("handler can not be null for a SAXResult");
            }
        }
        return handler;
    }
    
    public Thread createTransformThread() {
        final Thread t = new Thread(this);
        return t;
    }
    
    public boolean currentTemplateRuleIsNull() {
        return !this.m_currentTemplateRuleIsNull.isEmpty() && this.m_currentTemplateRuleIsNull.peek();
    }
    
    public void executeChildTemplates(final ElemTemplateElement elem, final Node sourceNode, final QName mode) throws TransformerException {
        this.executeChildTemplates(elem, sourceNode, mode, true);
    }
    
    public void executeChildTemplates(final ElemTemplateElement elem, final Node sourceNode, final QName mode, final ContentHandler handler) throws TransformerException {
        final ContentHandler savedHandler = this.getContentHandler();
        try {
            this.getResultTreeHandler().flushPending();
            this.setContentHandler(handler);
            this.executeChildTemplates(elem, sourceNode, mode);
        }
        catch (SAXException se) {
            throw new TransformerException(se);
        }
        finally {
            this.setContentHandler(savedHandler);
        }
    }
    
    public void executeChildTemplates(final ElemTemplateElement elem, final Node sourceNode, final QName mode, final boolean shouldAddAttrs) throws TransformerException {
        final ElemTemplateElement firstChild = elem.getFirstChildElem();
        if (firstChild == null) {
            return;
        }
        final XPathContext xctxt = this.getXPathContext();
        final boolean check = StackGuard.m_recursionLimit > -1;
        if (check) {
            this.getStackGuard().push(elem, sourceNode);
        }
        final VariableStack varstack = this.getXPathContext().getVarStack();
        varstack.pushElemFrame();
        final SourceLocator savedLocator = xctxt.getSAXLocator();
        try {
            this.pushElemTemplateElement(null);
            for (ElemTemplateElement t = firstChild; t != null; t = t.getNextSiblingElem()) {
                if (shouldAddAttrs || t.getXSLToken() != 48) {
                    xctxt.setSAXLocator(t);
                    this.m_currentTemplateElements.setTail(t);
                    t.execute(this, sourceNode, mode);
                }
            }
        }
        catch (TransformerException ex) {
            throw ex;
        }
        finally {
            this.popElemTemplateElement();
            xctxt.setSAXLocator(savedLocator);
            varstack.popElemFrame();
        }
        if (check) {
            this.getStackGuard().pop();
        }
    }
    
    public String getBaseURLOfSource() {
        return this.m_urlOfSource;
    }
    
    public ContentHandler getContentHandler() {
        return this.m_outputContentHandler;
    }
    
    public NodeIterator getContextNodeList() {
        try {
            return this.getXPathContext().getContextNodeList().cloneWithReset();
        }
        catch (CloneNotSupportedException ex) {
            return null;
        }
    }
    
    public CountersTable getCountersTable() {
        if (this.m_countersTable == null) {
            this.m_countersTable = new CountersTable();
        }
        return this.m_countersTable;
    }
    
    public ElemTemplateElement getCurrentElement() {
        return (ElemTemplateElement)this.m_currentTemplateElements.peepTail();
    }
    
    public Node getCurrentNode() {
        return this.m_xcontext.getCurrentNode();
    }
    
    public ElemTemplate getCurrentTemplate() {
        ElemTemplateElement elem;
        for (elem = this.getCurrentElement(); elem != null && elem.getXSLToken() != 19; elem = elem.getParentElem()) {}
        return (ElemTemplate)elem;
    }
    
    public ErrorListener getErrorListener() {
        return this.m_errorHandler;
    }
    
    public Exception getExceptionThrown() {
        return this.m_exceptionThrown;
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
    
    public ContentHandler getInputContentHandler() {
        return this.getInputContentHandler(false);
    }
    
    public ContentHandler getInputContentHandler(final boolean doDocFrag) {
        if (this.m_inputContentHandler == null) {
            this.m_inputContentHandler = new SourceTreeHandler(this, doDocFrag);
            ((SourceTreeHandler)this.m_inputContentHandler).setUseMultiThreading(true);
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
    
    public KeyManager getKeyManager() {
        return this.m_keyManager;
    }
    
    public Node getMatchedNode() {
        return this.m_currentMatchTemplates.peepTail();
    }
    
    public ElemTemplate getMatchedTemplate() {
        return (ElemTemplate)this.m_currentMatchTemplates.peepTailSub1();
    }
    
    public MsgMgr getMsgMgr() {
        if (this.m_msgMgr == null) {
            this.m_msgMgr = new MsgMgr(this);
        }
        return this.m_msgMgr;
    }
    
    public OutputProperties getOutputFormat() {
        final OutputProperties format = (this.m_outputFormat == null) ? this.getStylesheet().getOutputComposed() : this.m_outputFormat;
        return format;
    }
    
    public Properties getOutputProperties() {
        return (Properties)this.getOutputFormat().getProperties().clone();
    }
    
    public String getOutputProperty(final String qnameString) throws IllegalArgumentException {
        String value = null;
        final OutputProperties props = this.getOutputFormat();
        value = props.getProperty(qnameString);
        if (value == null && !props.isLegalPropertyKey(qnameString)) {
            throw new IllegalArgumentException("output property not recognized: " + qnameString);
        }
        return value;
    }
    
    public String getOutputPropertyNoDefault(final String qnameString) throws IllegalArgumentException {
        String value = null;
        final OutputProperties props = this.getOutputFormat();
        value = ((Hashtable<K, String>)props.getProperties()).get(qnameString);
        if (value == null && !props.isLegalPropertyKey(qnameString)) {
            throw new IllegalArgumentException("output property not recognized: " + qnameString);
        }
        return value;
    }
    
    public Object getParameter(final String name) {
        try {
            final QName qname = QName.getQNameFromString(name);
            if (this.m_userParams == null) {
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
        catch (NoSuchElementException ex) {
            return null;
        }
    }
    
    public boolean getQuietConflictWarnings() {
        return this.m_quietConflictWarnings;
    }
    
    public int getRecursionLimit() {
        return this.m_stackGuard.getRecursionLimit();
    }
    
    public ResultTreeHandler getResultTreeHandler() {
        return this.m_resultTreeHandler;
    }
    
    public Serializer getSerializer() {
        return this.m_serializer;
    }
    
    public StackGuard getStackGuard() {
        return this.m_stackGuard;
    }
    
    public ObjectPool getStringWriterPool() {
        return this.m_stringWriterObjectPool;
    }
    
    public StylesheetRoot getStylesheet() {
        return this.m_stylesheetRoot;
    }
    
    public TraceManager getTraceManager() {
        return this.m_traceManager;
    }
    
    public Thread getTransformThread() {
        return this.m_transformThread;
    }
    
    public Transformer getTransformer() {
        return this;
    }
    
    public URIResolver getURIResolver() {
        return this.getXPathContext().getSourceTreeManager().getURIResolver();
    }
    
    public XPathContext getXPathContext() {
        return this.m_xcontext;
    }
    
    public boolean isParserEventsOnMain() {
        return this.m_parserEventsOnMain;
    }
    
    public boolean isRecursiveAttrSet(final ElemAttributeSet attrSet) {
        if (this.m_attrSetStack == null) {
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
    
    public boolean isTransformDone() {
        synchronized (this) {
            return this.m_isTransformDone;
        }
    }
    
    public Node parseToNode(final Source source) throws TransformerException {
        if (source instanceof DOMSource) {
            return ((DOMSource)source).getNode();
        }
        final InputSource xmlSource = SAXSource.sourceToInputSource(source);
        if (xmlSource == null) {
            throw new TransformerException("Can't transform a Source of type " + source.getClass().getName() + "!");
        }
        if (xmlSource.getSystemId() != null) {
            this.m_urlOfSource = xmlSource.getSystemId();
        }
        Node doc = null;
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
            ContentHandler inputHandler = this.getInputContentHandler();
            final Class inputHandlerClass = inputHandler.getClass();
            inputHandler = inputHandlerClass.newInstance();
            reader.setContentHandler(inputHandler);
            if (inputHandler instanceof DTDHandler) {
                reader.setDTDHandler((DTDHandler)inputHandler);
            }
            try {
                if (inputHandler instanceof LexicalHandler) {
                    reader.setProperty("http://xml.org/sax/properties/lexical-handler", inputHandler);
                }
                if (inputHandler instanceof DeclHandler) {
                    reader.setProperty("http://xml.org/sax/properties/declaration-handler", inputHandler);
                }
            }
            catch (SAXNotRecognizedException ex4) {}
            try {
                if (inputHandler instanceof LexicalHandler) {
                    reader.setProperty("http://xml.org/sax/handlers/LexicalHandler", inputHandler);
                }
                if (inputHandler instanceof DeclHandler) {
                    reader.setProperty("http://xml.org/sax/handlers/DeclHandler", inputHandler);
                }
            }
            catch (SAXNotRecognizedException ex5) {}
            this.getXPathContext().setPrimaryReader(reader);
            if (inputHandler instanceof SourceTreeHandler) {
                reader.parse(xmlSource);
                doc = ((SourceTreeHandler)inputHandler).getRoot();
            }
        }
        catch (IllegalAccessException iae) {
            throw new TransformerException(iae);
        }
        catch (InstantiationException ie) {
            throw new TransformerException(ie);
        }
        catch (SAXException se) {
            throw new TransformerException(se);
        }
        catch (IOException e) {
            throw new TransformerException(e);
        }
        return doc;
    }
    
    public void popCurrentMatched() {
        this.m_currentMatchTemplates.popPair();
    }
    
    public void popCurrentTemplateRuleIsNull() {
        this.m_currentTemplateRuleIsNull.pop();
    }
    
    public void popElemAttributeSet() {
        this.m_attrSetStack.pop();
    }
    
    public void popElemTemplateElement() {
        this.m_currentTemplateElements.pop();
    }
    
    void postExceptionFromThread(final Exception e) {
        if (this.m_inputContentHandler instanceof SourceTreeHandler) {
            final SourceTreeHandler sth = (SourceTreeHandler)this.m_inputContentHandler;
            sth.setExceptionThrown(e);
        }
        final ContentHandler ch = this.getContentHandler();
        if (ch instanceof SourceTreeHandler) {
            final SourceTreeHandler sth2 = (SourceTreeHandler)ch;
            ((TransformerImpl)sth2.getTransformer()).postExceptionFromThread(e);
        }
        this.m_isTransformDone = true;
        this.m_exceptionThrown = e;
        synchronized (this) {
            final String msg = e.getMessage();
            this.notifyAll();
            if (msg == null) {
                e.printStackTrace();
            }
        }
    }
    
    public Vector processSortKeys(final ElemForEach foreach, final Node sourceNodeContext) throws TransformerException {
        Vector keys = null;
        final XPathContext xctxt = this.getXPathContext();
        final int nElems = foreach.getSortElemCount();
        if (nElems > 0) {
            keys = new Vector();
        }
        for (int i = 0; i < nElems; ++i) {
            final ElemSort sort = foreach.getSortElem(i);
            final String langString = (sort.getLang() != null) ? sort.getLang().evaluate(xctxt, sourceNodeContext, foreach) : null;
            final String dataTypeString = sort.getDataType().evaluate(xctxt, sourceNodeContext, foreach);
            if (dataTypeString.indexOf(":") >= 0) {
                System.out.println("TODO: Need to write the hooks for QNAME sort data type");
            }
            else if (!dataTypeString.equalsIgnoreCase("text") && !dataTypeString.equalsIgnoreCase("number")) {
                foreach.error(93, new Object[] { "data-type", dataTypeString });
            }
            final boolean treatAsNumbers = dataTypeString != null && dataTypeString.equals("number");
            final String orderString = sort.getOrder().evaluate(xctxt, sourceNodeContext, foreach);
            if (!orderString.equalsIgnoreCase("ascending") && !orderString.equalsIgnoreCase("descending")) {
                foreach.error(93, new Object[] { "order", orderString });
            }
            final boolean descending = orderString != null && orderString.equals("descending");
            final AVT caseOrder = sort.getCaseOrder();
            boolean caseOrderUpper;
            if (caseOrder != null) {
                final String caseOrderString = caseOrder.evaluate(xctxt, sourceNodeContext, foreach);
                if (!caseOrderString.equalsIgnoreCase("upper-first") && !caseOrderString.equalsIgnoreCase("lower-first")) {
                    foreach.error(93, new Object[] { "case-order", caseOrderString });
                }
                caseOrderUpper = (caseOrderString != null && caseOrderString.equals("upper-first"));
            }
            else {
                caseOrderUpper = false;
            }
            keys.addElement(new NodeSortKey(this, sort.getSelect(), treatAsNumbers, descending, langString, caseOrderUpper, foreach));
        }
        return keys;
    }
    
    public void pushCurrentTemplateRuleIsNull(final boolean b) {
        this.m_currentTemplateRuleIsNull.push(b);
    }
    
    public void pushElemAttributeSet(final ElemAttributeSet attrSet) {
        this.m_attrSetStack.push(attrSet);
    }
    
    public void pushElemTemplateElement(final ElemTemplateElement elem) {
        this.m_currentTemplateElements.push(elem);
    }
    
    protected void pushGlobalVars(final Node contextNode) throws TransformerException {
        final XPathContext xctxt = this.getXPathContext();
        final VariableStack vs = xctxt.getVarStack();
        final StylesheetRoot sr = this.getStylesheet();
        final Vector vars = sr.getVariablesAndParamsComposed();
        final int startGlobals = vs.size();
        int i = vars.size();
        while (--i >= 0) {
            final ElemVariable v = vars.elementAt(i);
            if (!vs.variableIsDeclared(v.getName())) {
                final XObject xobj = new XUnresolvedVariable(v, contextNode, this, vs.getSearchStartOrTop(), 0, true);
                vs.pushVariable(v.getName(), xobj);
                vs.markGlobalStackFrame();
            }
        }
        vs.markGlobalStackFrame();
        int endGlobals;
        for (endGlobals = vs.size(), i = startGlobals; i < endGlobals; ++i) {
            final Arg arg = vs.elementAt(i);
            final XUnresolvedVariable uv = (XUnresolvedVariable)arg.getVal();
            uv.setVarStackPos(endGlobals);
        }
        vs.pushContextMarker();
    }
    
    public void pushPairCurrentMatched(final ElemTemplateElement template, final Node child) {
        this.m_currentMatchTemplates.pushPair(template, child);
    }
    
    public void pushParams(final XPathContext xctxt, final ElemCallTemplate xslCallTemplateElement, final Node sourceNode, final QName mode) throws TransformerException {
        final VariableStack vars = xctxt.getVarStack();
        final int n = xslCallTemplateElement.getParamElemCount();
        final int paramDeclareContext = vars.getSearchStartOrTop();
        vars.pushContextMarker();
        final int paramReferenceContext = -1;
        for (int i = 0; i < n; ++i) {
            vars.setSearchStart(paramDeclareContext);
            final ElemWithParam xslParamElement = xslCallTemplateElement.getParamElem(i);
            final XPath param = xslParamElement.getSelect();
            XObject var;
            if (param != null) {
                var = param.execute(this.getXPathContext(), sourceNode, xslParamElement);
            }
            else if (xslParamElement.getFirstChild() == null) {
                var = XString.EMPTYSTRING;
            }
            else {
                final DocumentFragment df = this.transformToRTF(xslParamElement, sourceNode, mode);
                var = new XRTreeFrag(df);
            }
            vars.setSearchStart(paramReferenceContext);
            vars.pushVariableArg(new Arg(xslParamElement.getName(), var, true));
        }
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
    
    public void reset() {
        if (!this.m_hasBeenReset) {
            this.m_hasBeenReset = true;
            if (this.m_outputStream != null) {
                try {
                    this.m_outputStream.close();
                }
                catch (IOException ex) {}
            }
            this.m_outputStream = null;
            this.m_countersTable = null;
            this.m_stackGuard = new StackGuard();
            this.getXPathContext().reset();
            this.getXPathContext().getVarStack().setSize(1);
            this.m_currentTemplateElements.removeAllElements();
            this.m_currentMatchTemplates.removeAllElements();
            this.m_resultTreeHandler = null;
            this.m_keyManager = new KeyManager();
            this.m_attrSetStack = null;
            this.m_countersTable = null;
            this.m_currentTemplateRuleIsNull = new BoolStack();
            this.m_xmlSource = null;
            this.m_doc = null;
            this.m_isTransformDone = false;
            this.m_inputContentHandler = null;
            this.getXPathContext().getSourceTreeManager().reset();
        }
    }
    
    public void run() {
        this.m_hasBeenReset = false;
        try {
            if (this.isParserEventsOnMain()) {
                try {
                    try {
                        this.m_isTransformDone = false;
                        this.transformNode(this.m_doc);
                    }
                    catch (Exception e) {
                        this.postExceptionFromThread(e);
                    }
                    return;
                }
                finally {
                    this.m_isTransformDone = true;
                    synchronized (this) {
                        this.notifyAll();
                    }
                }
            }
            final InputSource isource = SAXSource.sourceToInputSource(this.m_xmlSource);
            this.getXPathContext().getPrimaryReader().parse(isource);
        }
        catch (Exception e2) {
            this.postExceptionFromThread(e2);
        }
    }
    
    public void setBaseURLOfSource(final String base) {
        this.m_urlOfSource = base;
    }
    
    public void setContentHandler(final ContentHandler handler) {
        if (handler == null) {
            throw new NullPointerException("Null content handler");
        }
        this.m_outputContentHandler = handler;
        if (this.m_resultTreeHandler == null) {
            this.m_resultTreeHandler = new ResultTreeHandler(this, handler);
        }
        else {
            this.m_resultTreeHandler.setContentHandler(handler);
        }
    }
    
    public void setCurrentElement(final ElemTemplateElement e) {
        this.m_currentTemplateElements.setTail(e);
    }
    
    public void setErrorListener(final ErrorListener listener) throws IllegalArgumentException {
        synchronized (this.m_reentryGuard) {
            if (listener == null) {
                throw new IllegalArgumentException("Null error handler");
            }
            this.m_errorHandler = listener;
        }
        // monitorexit(this.m_reentryGuard)
    }
    
    public void setOutputFormat(final OutputProperties oformat) {
        this.m_outputFormat = oformat;
    }
    
    public void setOutputProperties(final Properties oformat) {
        synchronized (this.m_reentryGuard) {
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
            this.m_outputFormat.copyFrom(this.m_stylesheetRoot.getOutputProperties());
        }
        // monitorexit(this.m_reentryGuard)
    }
    
    public void setOutputProperty(final String name, final String value) throws IllegalArgumentException {
        synchronized (this.m_reentryGuard) {
            if (this.m_outputFormat == null) {
                this.m_outputFormat = (OutputProperties)this.getStylesheet().getOutputComposed().clone();
            }
            if (!this.m_outputFormat.isLegalPropertyKey(name)) {
                throw new IllegalArgumentException("output property not recognized: " + name);
            }
            this.m_outputFormat.setProperty(name, value);
        }
        // monitorexit(this.m_reentryGuard)
    }
    
    public void setParameter(final String name, final Object value) {
        final StringTokenizer tokenizer = new StringTokenizer(name, "{}", false);
        try {
            final String s1 = tokenizer.nextToken();
            final String s2 = tokenizer.hasMoreTokens() ? tokenizer.nextToken() : null;
            if (this.m_userParams == null) {
                this.m_userParams = new Vector();
            }
            if (s2 == null) {
                this.replaceOrPushUserParam(new QName(s1), new XObject(value));
                this.setParameter(s1, null, value);
            }
            else {
                this.replaceOrPushUserParam(new QName(s1, s2), new XObject(value));
                this.setParameter(s2, s1, value);
            }
        }
        catch (NoSuchElementException ex) {}
    }
    
    public void setParameter(final String name, final String namespace, final Object value) {
        final VariableStack varstack = this.getXPathContext().getVarStack();
        final QName qname = new QName(namespace, name);
        final XObject xobject = XObject.create(value);
        varstack.pushOrReplaceVariable(qname, xobject);
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
                if (s2 == null) {
                    this.setParameter(s1, null, params.getProperty(name));
                }
                else {
                    this.setParameter(s2, s1, params.getProperty(name));
                }
            }
            catch (NoSuchElementException ex) {}
        }
    }
    
    public void setQuietConflictWarnings(final boolean b) {
        this.m_quietConflictWarnings = b;
    }
    
    public void setRecursionLimit(final int limit) {
        this.m_stackGuard.setRecursionLimit(limit);
    }
    
    public void setSerializer(final Serializer s) {
        this.m_serializer = s;
    }
    
    public void setSourceTreeDocForThread(final Node doc) {
        this.m_doc = doc;
    }
    
    public void setStylesheet(final StylesheetRoot stylesheetRoot) {
        this.m_stylesheetRoot = stylesheetRoot;
    }
    
    public void setTransformThread(final Thread t) {
        this.m_transformThread = t;
    }
    
    public void setURIResolver(final URIResolver resolver) {
        synchronized (this.m_reentryGuard) {
            this.getXPathContext().getSourceTreeManager().setURIResolver(resolver);
        }
        // monitorexit(this.m_reentryGuard)
    }
    
    public void setXPathContext(final XPathContext xcontext) {
        this.m_xcontext = xcontext;
    }
    
    public void transform(final Source source) throws TransformerException {
        if (source instanceof DOMSource) {
            final DOMSource dsource = (DOMSource)source;
            this.m_urlOfSource = dsource.getSystemId();
            final Node dNode = dsource.getNode();
            if (dNode != null) {
                if (this.m_urlOfSource != null) {
                    this.getXPathContext().getSourceTreeManager().putDocumentInCache(dNode, dsource);
                }
                this.transformNode(dsource.getNode());
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
            while (true) {
                if (xmlSource.getSystemId() != null) {
                    this.m_urlOfSource = xmlSource.getSystemId();
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
                        final ContentHandler inputHandler = this.getInputContentHandler();
                        reader.setContentHandler(inputHandler);
                        if (inputHandler instanceof DTDHandler) {
                            reader.setDTDHandler((DTDHandler)inputHandler);
                        }
                        try {
                            if (inputHandler instanceof LexicalHandler) {
                                reader.setProperty("http://xml.org/sax/properties/lexical-handler", inputHandler);
                            }
                            if (inputHandler instanceof DeclHandler) {
                                reader.setProperty("http://xml.org/sax/properties/declaration-handler", inputHandler);
                            }
                        }
                        catch (SAXException ex4) {}
                        try {
                            if (inputHandler instanceof LexicalHandler) {
                                reader.setProperty("http://xml.org/sax/handlers/LexicalHandler", inputHandler);
                            }
                            if (inputHandler instanceof DeclHandler) {
                                reader.setProperty("http://xml.org/sax/handlers/DeclHandler", inputHandler);
                            }
                        }
                        catch (SAXNotRecognizedException ex5) {}
                        this.getXPathContext().setPrimaryReader(reader);
                        this.m_exceptionThrown = null;
                        if (inputHandler instanceof SourceTreeHandler) {
                            final SourceTreeHandler sth = (SourceTreeHandler)inputHandler;
                            sth.setInputSource(source);
                            sth.setUseMultiThreading(true);
                            final Node doc = sth.getRoot();
                            if (doc != null) {
                                final SourceTreeManager stm = this.getXPathContext().getSourceTreeManager();
                                stm.putDocumentInCache(doc, source);
                                this.m_xmlSource = source;
                                this.m_doc = doc;
                                if (this.isParserEventsOnMain()) {
                                    this.m_isTransformDone = false;
                                    this.getXPathContext().getPrimaryReader().parse(xmlSource);
                                }
                                else {
                                    final Thread t = this.createTransformThread();
                                    t.start();
                                    this.transformNode(doc);
                                }
                            }
                        }
                        else {
                            reader.parse(xmlSource);
                        }
                        final Exception e = this.getExceptionThrown();
                        if (e != null) {
                            if (e instanceof TransformerException) {
                                throw (TransformerException)e;
                            }
                            if (e instanceof WrappedRuntimeException) {
                                throw new TransformerException(((WrappedRuntimeException)e).getException());
                            }
                            throw new TransformerException(e);
                        }
                        else if (this.m_resultTreeHandler != null) {
                            this.m_resultTreeHandler.endDocument();
                        }
                    }
                    catch (WrappedRuntimeException wre) {
                        for (Throwable throwable = wre.getException(); throwable instanceof WrappedRuntimeException; throwable = ((WrappedRuntimeException)throwable).getException()) {}
                        throw new TransformerException(wre.getException());
                    }
                    catch (SAXParseException spe) {
                        final String msg = spe.getMessage();
                        final SAXSourceLocator loc = new SAXSourceLocator(spe);
                        this.m_errorHandler.fatalError(new TransformerException(msg, loc));
                    }
                    catch (SAXException se) {
                        this.m_errorHandler.fatalError(new TransformerException(se));
                    }
                    catch (IOException ioe) {
                        this.m_errorHandler.fatalError(new TransformerException(ioe));
                    }
                    finally {
                        this.reset();
                    }
                    return;
                }
                continue;
            }
        }
    }
    
    public void transform(final Source xmlSource, final Result outputTarget) throws TransformerException {
        synchronized (this.m_reentryGuard) {
            final ContentHandler handler = this.createResultContentHandler(outputTarget);
            this.setContentHandler(handler);
            this.transform(xmlSource);
        }
        // monitorexit(this.m_reentryGuard)
    }
    
    public void transformNode(final Node node) throws TransformerException {
        synchronized (this.m_outputContentHandler) {
            this.m_hasBeenReset = false;
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
                this.applyTemplateToNode(null, null, node, null);
                if (this.m_resultTreeHandler != null) {
                    this.m_resultTreeHandler.endDocument();
                }
            }
            catch (Exception e) {
                if (this.m_resultTreeHandler != null) {
                    try {
                        this.m_resultTreeHandler.endDocument();
                    }
                    catch (Exception ex) {}
                }
                throw new TransformerException(e.getMessage(), e);
            }
            finally {
                this.reset();
            }
        }
        // monitorexit(this.m_outputContentHandler)
    }
    
    public void transformNode(final Node node, final Result outputTarget) throws TransformerException {
        final ContentHandler handler = this.createResultContentHandler(outputTarget);
        this.setContentHandler(handler);
        this.transformNode(node);
    }
    
    public DocumentFragment transformToRTF(final ElemTemplateElement templateParent, final Node sourceNode, final QName mode) throws TransformerException {
        final boolean isSTree = sourceNode instanceof Child;
        ContentHandler rtfHandler;
        DocumentFragment resultFragment;
        if (isSTree) {
            rtfHandler = new SourceTreeHandler(this, true);
            ((SourceTreeHandler)rtfHandler).setUseMultiThreading(false);
            ((SourceTreeHandler)rtfHandler).setShouldTransformAtEnd(false);
            resultFragment = (DocumentFragment)((SourceTreeHandler)rtfHandler).getRoot();
        }
        else {
            if (this.m_docBuilder == null) {
                try {
                    final DocumentBuilderFactory dfactory = DocumentBuilderFactory.newInstance();
                    dfactory.setNamespaceAware(true);
                    dfactory.setValidating(true);
                    this.m_docBuilder = dfactory.newDocumentBuilder();
                }
                catch (ParserConfigurationException pce) {
                    throw new TransformerException(pce);
                }
            }
            final Document docFactory = this.m_docBuilder.newDocument();
            resultFragment = docFactory.createDocumentFragment();
            rtfHandler = new DOMBuilder(docFactory, resultFragment);
        }
        final ResultTreeHandler savedRTreeHandler = this.m_resultTreeHandler;
        this.m_resultTreeHandler = new ResultTreeHandler(this, rtfHandler);
        try {
            this.m_resultTreeHandler.startDocument();
            this.executeChildTemplates(templateParent, sourceNode, mode);
            this.m_resultTreeHandler.flushPending();
            this.m_resultTreeHandler.endDocument();
        }
        catch (SAXException se) {
            throw new TransformerException(se);
        }
        finally {
            this.m_resultTreeHandler = savedRTreeHandler;
        }
        return resultFragment;
    }
    
    public String transformToString(final ElemTemplateElement elem, final Node sourceNode, final QName mode) throws TransformerException {
        final ResultTreeHandler savedRTreeHandler = this.m_resultTreeHandler;
        final StringWriter sw = (StringWriter)this.m_stringWriterObjectPool.getInstance();
        this.m_resultTreeHandler = (ResultTreeHandler)this.m_textResultHandlerObjectPool.getInstance();
        Serializer serializer = this.m_resultTreeHandler.getSerializer();
        try {
            if (serializer == null) {
                serializer = SerializerFactory.getSerializer(this.m_textformat.getProperties());
                this.m_resultTreeHandler.setSerializer(serializer);
                serializer.setWriter(sw);
                final ContentHandler shandler = serializer.asContentHandler();
                this.m_resultTreeHandler.init(this, shandler);
            }
        }
        catch (IOException ioe) {
            throw new TransformerException(ioe);
        }
        String result;
        try {
            this.m_resultTreeHandler.startDocument();
            this.executeChildTemplates(elem, sourceNode, mode);
            this.m_resultTreeHandler.endDocument();
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
            this.m_textResultHandlerObjectPool.freeInstance(this.m_resultTreeHandler);
            this.m_resultTreeHandler.reset();
            this.m_resultTreeHandler = savedRTreeHandler;
        }
        return result;
    }
}
