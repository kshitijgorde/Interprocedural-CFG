// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.trax;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import org.xml.sax.XMLFilter;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.sax.TemplatesHandler;
import java.io.File;
import org.apache.xalan.xsltc.compiler.XSLTC;
import javax.xml.transform.TransformerException;
import java.util.Vector;
import javax.xml.transform.Templates;
import java.util.Properties;
import javax.xml.transform.Transformer;
import javax.xml.parsers.SAXParser;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.xml.utils.StopParseException;
import org.xml.sax.helpers.XMLReaderFactory;
import org.xml.sax.SAXException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.sax.SAXSource;
import org.xml.sax.ContentHandler;
import javax.xml.transform.dom.DOMSource;
import org.apache.xml.utils.StylesheetPIHandler;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerConfigurationException;
import org.apache.xalan.xsltc.compiler.util.ErrorMsg;
import org.apache.xalan.xsltc.dom.XSLTCDTMManager;
import java.util.Hashtable;
import javax.xml.transform.URIResolver;
import javax.xml.transform.ErrorListener;
import org.apache.xalan.xsltc.compiler.SourceLoader;
import javax.xml.transform.sax.SAXTransformerFactory;

public class TransformerFactoryImpl extends SAXTransformerFactory implements SourceLoader, ErrorListener
{
    public static final String TRANSLET_NAME = "translet-name";
    public static final String DESTINATION_DIRECTORY = "destination-directory";
    public static final String PACKAGE_NAME = "package-name";
    public static final String JAR_NAME = "jar-name";
    public static final String GENERATE_TRANSLET = "generate-translet";
    public static final String AUTO_TRANSLET = "auto-translet";
    public static final String USE_CLASSPATH = "use-classpath";
    public static final String DEBUG = "debug";
    public static final String ENABLE_INLINING = "enable-inlining";
    public static final String INDENT_NUMBER = "indent-number";
    public static final String FEATURE_SERIALIZABLE = "http://www.ibm.com/xmlns/prod/xslt4j/serializable-templates";
    private ErrorListener _errorListener;
    private URIResolver _uriResolver;
    protected static final String DEFAULT_TRANSLET_NAME = "GregorSamsa";
    private String _transletName;
    private String _destinationDirectory;
    private String _packageName;
    private String _jarFileName;
    private Hashtable _piParams;
    private boolean _serializable;
    private boolean _debug;
    private boolean _enableInlining;
    private boolean _generateTranslet;
    private boolean _autoTranslet;
    private boolean _useClasspath;
    private int _indentNumber;
    private boolean _isSecureProcessing;
    private Class m_DTMManagerClass;
    
    public TransformerFactoryImpl() {
        this._errorListener = this;
        this._uriResolver = null;
        this._transletName = "GregorSamsa";
        this._destinationDirectory = null;
        this._packageName = null;
        this._jarFileName = null;
        this._piParams = null;
        this._serializable = false;
        this._debug = false;
        this._enableInlining = false;
        this._generateTranslet = false;
        this._autoTranslet = false;
        this._useClasspath = false;
        this._indentNumber = -1;
        this._isSecureProcessing = false;
        this.m_DTMManagerClass = XSLTCDTMManager.getDTMManagerClass();
    }
    
    public void setErrorListener(final ErrorListener listener) throws IllegalArgumentException {
        if (listener == null) {
            final ErrorMsg err = new ErrorMsg("ERROR_LISTENER_NULL_ERR", "TransformerFactory");
            throw new IllegalArgumentException(err.toString());
        }
        this._errorListener = listener;
    }
    
    public ErrorListener getErrorListener() {
        return this._errorListener;
    }
    
    public Object getAttribute(final String name) throws IllegalArgumentException {
        if (name.equals("translet-name")) {
            return this._transletName;
        }
        if (name.equals("generate-translet")) {
            return new Boolean(this._generateTranslet);
        }
        if (name.equals("auto-translet")) {
            return new Boolean(this._autoTranslet);
        }
        if (name.equals("http://www.ibm.com/xmlns/prod/xslt4j/serializable-templates")) {
            return new Boolean(this._serializable);
        }
        final ErrorMsg err = new ErrorMsg("JAXP_INVALID_ATTR_ERR", name);
        throw new IllegalArgumentException(err.toString());
    }
    
    public void setAttribute(final String name, final Object value) throws IllegalArgumentException {
        if (name.equals("translet-name") && value instanceof String) {
            this._transletName = (String)value;
            return;
        }
        if (name.equals("destination-directory") && value instanceof String) {
            this._destinationDirectory = (String)value;
            return;
        }
        if (name.equals("package-name") && value instanceof String) {
            this._packageName = (String)value;
            return;
        }
        if (name.equals("jar-name") && value instanceof String) {
            this._jarFileName = (String)value;
            return;
        }
        Label_0445: {
            if (name.equals("generate-translet")) {
                if (value instanceof Boolean) {
                    this._generateTranslet = (boolean)value;
                    return;
                }
                if (value instanceof String) {
                    this._generateTranslet = ((String)value).equalsIgnoreCase("true");
                    return;
                }
            }
            else if (name.equals("auto-translet")) {
                if (value instanceof Boolean) {
                    this._autoTranslet = (boolean)value;
                    return;
                }
                if (value instanceof String) {
                    this._autoTranslet = ((String)value).equalsIgnoreCase("true");
                    return;
                }
            }
            else if (name.equals("use-classpath")) {
                if (value instanceof Boolean) {
                    this._useClasspath = (boolean)value;
                    return;
                }
                if (value instanceof String) {
                    this._useClasspath = ((String)value).equalsIgnoreCase("true");
                    return;
                }
            }
            else if (name.equals("debug")) {
                if (value instanceof Boolean) {
                    this._debug = (boolean)value;
                    return;
                }
                if (value instanceof String) {
                    this._debug = ((String)value).equalsIgnoreCase("true");
                    return;
                }
            }
            else if (name.equals("enable-inlining")) {
                if (value instanceof Boolean) {
                    this._enableInlining = (boolean)value;
                    return;
                }
                if (value instanceof String) {
                    this._enableInlining = ((String)value).equalsIgnoreCase("true");
                    return;
                }
            }
            else if (name.equals("indent-number")) {
                if (value instanceof String) {
                    try {
                        this._indentNumber = Integer.parseInt((String)value);
                        return;
                    }
                    catch (NumberFormatException e) {
                        break Label_0445;
                    }
                }
                if (value instanceof Integer) {
                    this._indentNumber = (int)value;
                    return;
                }
            }
            else if (name.equals("http://www.ibm.com/xmlns/prod/xslt4j/serializable-templates")) {
                if (value instanceof Boolean) {
                    this._serializable = (boolean)value;
                    return;
                }
                if (value instanceof String) {
                    this._serializable = ((String)value).equalsIgnoreCase("true");
                    return;
                }
            }
        }
        final ErrorMsg err = new ErrorMsg("JAXP_INVALID_ATTR_ERR", name);
        throw new IllegalArgumentException(err.toString());
    }
    
    public boolean getFeature(final String name) {
        if (name == null) {
            final ErrorMsg err = new ErrorMsg("JAXP_GET_FEATURE_NULL_NAME");
            throw new NullPointerException(err.toString());
        }
        final String[] features = { "http://javax.xml.transform.dom.DOMSource/feature", "http://javax.xml.transform.dom.DOMResult/feature", "http://javax.xml.transform.sax.SAXSource/feature", "http://javax.xml.transform.sax.SAXResult/feature", "http://javax.xml.transform.stream.StreamSource/feature", "http://javax.xml.transform.stream.StreamResult/feature", "http://javax.xml.transform.sax.SAXTransformerFactory/feature", "http://javax.xml.transform.sax.SAXTransformerFactory/feature/xmlfilter" };
        for (int i = 0; i < features.length; ++i) {
            if (name.equals(features[i])) {
                return true;
            }
        }
        try {
            return name.equals("http://javax.xml.XMLConstants/feature/secure-processing") && this._isSecureProcessing;
        }
        catch (NoClassDefFoundError e) {
            return false;
        }
    }
    
    public void setFeature(final String name, final boolean value) throws TransformerConfigurationException {
        if (name == null) {
            final ErrorMsg err = new ErrorMsg("JAXP_SET_FEATURE_NULL_NAME");
            throw new NullPointerException(err.toString());
        }
        if (name.equals("http://javax.xml.XMLConstants/feature/secure-processing")) {
            this._isSecureProcessing = value;
            return;
        }
        final ErrorMsg err = new ErrorMsg("JAXP_UNSUPPORTED_FEATURE", name);
        throw new TransformerConfigurationException(err.toString());
    }
    
    public URIResolver getURIResolver() {
        return this._uriResolver;
    }
    
    public void setURIResolver(final URIResolver resolver) {
        this._uriResolver = resolver;
    }
    
    public Source getAssociatedStylesheet(final Source source, final String media, final String title, final String charset) throws TransformerConfigurationException {
        XMLReader reader = null;
        InputSource isource = null;
        final StylesheetPIHandler _stylesheetPIHandler = new StylesheetPIHandler(null, media, title, charset);
        try {
            if (source instanceof DOMSource) {
                final DOMSource domsrc = (DOMSource)source;
                final String baseId = domsrc.getSystemId();
                final Node node = domsrc.getNode();
                final DOM2SAX dom2sax = new DOM2SAX(node);
                _stylesheetPIHandler.setBaseId(baseId);
                dom2sax.setContentHandler(_stylesheetPIHandler);
                dom2sax.parse();
            }
            else {
                isource = SAXSource.sourceToInputSource(source);
                final String baseId = isource.getSystemId();
                final SAXParserFactory factory = SAXParserFactory.newInstance();
                factory.setNamespaceAware(true);
                if (this._isSecureProcessing) {
                    try {
                        factory.setFeature("http://javax.xml.XMLConstants/feature/secure-processing", true);
                    }
                    catch (SAXException ex) {}
                }
                final SAXParser jaxpParser = factory.newSAXParser();
                reader = jaxpParser.getXMLReader();
                if (reader == null) {
                    reader = XMLReaderFactory.createXMLReader();
                }
                _stylesheetPIHandler.setBaseId(baseId);
                reader.setContentHandler(_stylesheetPIHandler);
                reader.parse(isource);
            }
            if (this._uriResolver != null) {
                _stylesheetPIHandler.setURIResolver(this._uriResolver);
            }
        }
        catch (StopParseException e2) {}
        catch (ParserConfigurationException e) {
            throw new TransformerConfigurationException("getAssociatedStylesheets failed", e);
        }
        catch (SAXException se) {
            throw new TransformerConfigurationException("getAssociatedStylesheets failed", se);
        }
        catch (IOException ioe) {
            throw new TransformerConfigurationException("getAssociatedStylesheets failed", ioe);
        }
        return _stylesheetPIHandler.getAssociatedStylesheet();
    }
    
    public Transformer newTransformer() throws TransformerConfigurationException {
        final TransformerImpl result = new TransformerImpl(new Properties(), this._indentNumber, this);
        if (this._uriResolver != null) {
            result.setURIResolver(this._uriResolver);
        }
        if (this._isSecureProcessing) {
            result.setSecureProcessing(true);
        }
        return result;
    }
    
    public Transformer newTransformer(final Source source) throws TransformerConfigurationException {
        final Templates templates = this.newTemplates(source);
        final Transformer transformer = templates.newTransformer();
        if (this._uriResolver != null) {
            transformer.setURIResolver(this._uriResolver);
        }
        return transformer;
    }
    
    private void passWarningsToListener(final Vector messages) throws TransformerException {
        if (this._errorListener == null || messages == null) {
            return;
        }
        for (int count = messages.size(), pos = 0; pos < count; ++pos) {
            final ErrorMsg msg = messages.elementAt(pos);
            if (msg.isWarningError()) {
                this._errorListener.error(new TransformerConfigurationException(msg.toString()));
            }
            else {
                this._errorListener.warning(new TransformerConfigurationException(msg.toString()));
            }
        }
    }
    
    private void passErrorsToListener(final Vector messages) {
        try {
            if (this._errorListener == null || messages == null) {
                return;
            }
            for (int count = messages.size(), pos = 0; pos < count; ++pos) {
                final String message = messages.elementAt(pos).toString();
                this._errorListener.error(new TransformerException(message));
            }
        }
        catch (TransformerException ex) {}
    }
    
    public Templates newTemplates(final Source source) throws TransformerConfigurationException {
        if (this._useClasspath) {
            String transletName = this.getTransletBaseName(source);
            if (this._packageName != null) {
                transletName = this._packageName + "." + transletName;
            }
            try {
                final Class clazz = ObjectFactory.findProviderClass(transletName, ObjectFactory.findClassLoader(), true);
                this.resetTransientAttributes();
                if (this._serializable) {
                    return new TemplatesImpl(new Class[] { clazz }, transletName, null, this._indentNumber, this);
                }
                return new TemplatesImplProxy(new Class[] { clazz }, transletName, null, this._indentNumber, this);
            }
            catch (ClassNotFoundException cnfe) {
                final ErrorMsg err = new ErrorMsg("CLASS_NOT_FOUND_ERR", transletName);
                throw new TransformerConfigurationException(err.toString());
            }
            catch (Exception e) {
                final ErrorMsg err2 = new ErrorMsg(new ErrorMsg("RUNTIME_ERROR_KEY") + e.getMessage());
                throw new TransformerConfigurationException(err2.toString());
            }
        }
        if (this._autoTranslet) {
            byte[][] bytecodes = null;
            String transletClassName = this.getTransletBaseName(source);
            if (this._packageName != null) {
                transletClassName = this._packageName + "." + transletClassName;
            }
            if (this._jarFileName != null) {
                bytecodes = this.getBytecodesFromJar(source, transletClassName);
            }
            else {
                bytecodes = this.getBytecodesFromClasses(source, transletClassName);
            }
            if (bytecodes != null) {
                if (this._debug) {
                    if (this._jarFileName != null) {
                        System.err.println(new ErrorMsg("TRANSFORM_WITH_JAR_STR", transletClassName, this._jarFileName));
                    }
                    else {
                        System.err.println(new ErrorMsg("TRANSFORM_WITH_TRANSLET_STR", transletClassName));
                    }
                }
                this.resetTransientAttributes();
                if (this._serializable) {
                    return new TemplatesImpl(bytecodes, transletClassName, null, this._indentNumber, this);
                }
                return new TemplatesImplProxy(bytecodes, transletClassName, null, this._indentNumber, this);
            }
        }
        final XSLTC xsltc = new XSLTC();
        if (this._debug) {
            xsltc.setDebug(true);
        }
        if (this._enableInlining) {
            xsltc.setTemplateInlining(true);
        }
        if (this._isSecureProcessing) {
            xsltc.setSecureProcessing(true);
        }
        xsltc.init();
        if (this._uriResolver != null) {
            xsltc.setSourceLoader(this);
        }
        if (this._piParams != null && this._piParams.get(source) != null) {
            final PIParamWrapper p = this._piParams.get(source);
            if (p != null) {
                xsltc.setPIParameters(p._media, p._title, p._charset);
            }
        }
        int outputType = 2;
        if (this._generateTranslet || this._autoTranslet) {
            xsltc.setClassName(this.getTransletBaseName(source));
            if (this._destinationDirectory != null) {
                xsltc.setDestDirectory(this._destinationDirectory);
            }
            else {
                final String xslName = this.getStylesheetFileName(source);
                if (xslName != null) {
                    final File xslFile = new File(xslName);
                    final String xslDir = xslFile.getParent();
                    if (xslDir != null) {
                        xsltc.setDestDirectory(xslDir);
                    }
                }
            }
            if (this._packageName != null) {
                xsltc.setPackageName(this._packageName);
            }
            if (this._jarFileName != null) {
                xsltc.setJarFileName(this._jarFileName);
                outputType = 5;
            }
            else {
                outputType = 4;
            }
        }
        final InputSource input = Util.getInputSource(xsltc, source);
        final byte[][] bytecodes2 = xsltc.compile(null, input, outputType);
        final String transletName2 = xsltc.getClassName();
        if ((this._generateTranslet || this._autoTranslet) && bytecodes2 != null && this._jarFileName != null) {
            try {
                xsltc.outputToJar();
            }
            catch (IOException ex) {}
        }
        this.resetTransientAttributes();
        if (this._errorListener != this) {
            try {
                this.passWarningsToListener(xsltc.getWarnings());
            }
            catch (TransformerException e2) {
                throw new TransformerConfigurationException(e2);
            }
        }
        else {
            xsltc.printWarnings();
        }
        if (bytecodes2 == null) {
            final ErrorMsg err3 = new ErrorMsg("JAXP_COMPILE_ERR");
            final TransformerConfigurationException exc = new TransformerConfigurationException(err3.toString());
            if (this._errorListener != null) {
                this.passErrorsToListener(xsltc.getErrors());
                try {
                    this._errorListener.fatalError(exc);
                }
                catch (TransformerException ex2) {}
            }
            else {
                xsltc.printErrors();
            }
            throw exc;
        }
        if (this._serializable) {
            return new TemplatesImpl(bytecodes2, transletName2, xsltc.getOutputProperties(), this._indentNumber, this);
        }
        return new TemplatesImplProxy(bytecodes2, transletName2, xsltc.getOutputProperties(), this._indentNumber, this);
    }
    
    public TemplatesHandler newTemplatesHandler() throws TransformerConfigurationException {
        final TemplatesHandlerImpl handler = new TemplatesHandlerImpl(this._indentNumber, this);
        if (this._uriResolver != null) {
            handler.setURIResolver(this._uriResolver);
        }
        return handler;
    }
    
    public TransformerHandler newTransformerHandler() throws TransformerConfigurationException {
        final Transformer transformer = this.newTransformer();
        if (this._uriResolver != null) {
            transformer.setURIResolver(this._uriResolver);
        }
        return new TransformerHandlerImpl((TransformerImpl)transformer);
    }
    
    public TransformerHandler newTransformerHandler(final Source src) throws TransformerConfigurationException {
        final Transformer transformer = this.newTransformer(src);
        if (this._uriResolver != null) {
            transformer.setURIResolver(this._uriResolver);
        }
        return new TransformerHandlerImpl((TransformerImpl)transformer);
    }
    
    public TransformerHandler newTransformerHandler(final Templates templates) throws TransformerConfigurationException {
        final Transformer transformer = templates.newTransformer();
        final TransformerImpl internal = (TransformerImpl)transformer;
        return new TransformerHandlerImpl(internal);
    }
    
    public XMLFilter newXMLFilter(final Source src) throws TransformerConfigurationException {
        final Templates templates = this.newTemplates(src);
        if (templates == null) {
            return null;
        }
        return this.newXMLFilter(templates);
    }
    
    public XMLFilter newXMLFilter(final Templates templates) throws TransformerConfigurationException {
        try {
            return new TrAXFilter(templates);
        }
        catch (TransformerConfigurationException e1) {
            if (this._errorListener != null) {
                try {
                    this._errorListener.fatalError(e1);
                    return null;
                }
                catch (TransformerException e2) {
                    new TransformerConfigurationException(e2);
                }
            }
            throw e1;
        }
    }
    
    public void error(final TransformerException e) throws TransformerException {
        final Throwable wrapped = e.getException();
        if (wrapped != null) {
            System.err.println(new ErrorMsg("ERROR_PLUS_WRAPPED_MSG", e.getMessageAndLocation(), wrapped.getMessage()));
        }
        else {
            System.err.println(new ErrorMsg("ERROR_MSG", e.getMessageAndLocation()));
        }
        throw e;
    }
    
    public void fatalError(final TransformerException e) throws TransformerException {
        final Throwable wrapped = e.getException();
        if (wrapped != null) {
            System.err.println(new ErrorMsg("FATAL_ERR_PLUS_WRAPPED_MSG", e.getMessageAndLocation(), wrapped.getMessage()));
        }
        else {
            System.err.println(new ErrorMsg("FATAL_ERR_MSG", e.getMessageAndLocation()));
        }
        throw e;
    }
    
    public void warning(final TransformerException e) throws TransformerException {
        final Throwable wrapped = e.getException();
        if (wrapped != null) {
            System.err.println(new ErrorMsg("WARNING_PLUS_WRAPPED_MSG", e.getMessageAndLocation(), wrapped.getMessage()));
        }
        else {
            System.err.println(new ErrorMsg("WARNING_MSG", e.getMessageAndLocation()));
        }
    }
    
    public InputSource loadSource(final String href, final String context, final XSLTC xsltc) {
        try {
            if (this._uriResolver != null) {
                final Source source = this._uriResolver.resolve(href, context);
                if (source != null) {
                    return Util.getInputSource(xsltc, source);
                }
            }
        }
        catch (TransformerException ex) {}
        return null;
    }
    
    private void resetTransientAttributes() {
        this._transletName = "GregorSamsa";
        this._destinationDirectory = null;
        this._packageName = null;
        this._jarFileName = null;
    }
    
    private byte[][] getBytecodesFromClasses(final Source source, final String fullClassName) {
        if (fullClassName == null) {
            return null;
        }
        final String xslFileName = this.getStylesheetFileName(source);
        File xslFile = null;
        if (xslFileName != null) {
            xslFile = new File(xslFileName);
        }
        final int lastDotIndex = fullClassName.lastIndexOf(46);
        String transletName;
        if (lastDotIndex > 0) {
            transletName = fullClassName.substring(lastDotIndex + 1);
        }
        else {
            transletName = fullClassName;
        }
        String transletPath = fullClassName.replace('.', '/');
        if (this._destinationDirectory != null) {
            transletPath = this._destinationDirectory + "/" + transletPath + ".class";
        }
        else if (xslFile != null && xslFile.getParent() != null) {
            transletPath = xslFile.getParent() + "/" + transletPath + ".class";
        }
        else {
            transletPath += ".class";
        }
        final File transletFile = new File(transletPath);
        if (!transletFile.exists()) {
            return null;
        }
        if (xslFile != null && xslFile.exists()) {
            final long xslTimestamp = xslFile.lastModified();
            final long transletTimestamp = transletFile.lastModified();
            if (transletTimestamp < xslTimestamp) {
                return null;
            }
        }
        final Vector bytecodes = new Vector();
        final int fileLength = (int)transletFile.length();
        if (fileLength <= 0) {
            return null;
        }
        FileInputStream input = null;
        try {
            input = new FileInputStream(transletFile);
        }
        catch (FileNotFoundException e) {
            return null;
        }
        final byte[] bytes = new byte[fileLength];
        try {
            this.readFromInputStream(bytes, input, fileLength);
            input.close();
        }
        catch (IOException e2) {
            return null;
        }
        bytecodes.addElement(bytes);
        String transletParentDir = transletFile.getParent();
        if (transletParentDir == null) {
            transletParentDir = System.getProperty("user.dir");
        }
        final File transletParentFile = new File(transletParentDir);
        final String transletAuxPrefix = transletName + "$";
        final File[] auxfiles = transletParentFile.listFiles(new FilenameFilter() {
            public boolean accept(final File dir, final String name) {
                return name.endsWith(".class") && name.startsWith(transletAuxPrefix);
            }
        });
        for (int i = 0; i < auxfiles.length; ++i) {
            final File auxfile = auxfiles[i];
            final int auxlength = (int)auxfile.length();
            if (auxlength > 0) {
                FileInputStream auxinput = null;
                try {
                    auxinput = new FileInputStream(auxfile);
                }
                catch (FileNotFoundException e3) {
                    continue;
                }
                final byte[] bytes2 = new byte[auxlength];
                try {
                    this.readFromInputStream(bytes2, auxinput, auxlength);
                    auxinput.close();
                }
                catch (IOException e4) {
                    continue;
                }
                bytecodes.addElement(bytes2);
            }
        }
        final int count = bytecodes.size();
        if (count > 0) {
            final byte[][] result = new byte[count][1];
            for (int j = 0; j < count; ++j) {
                result[j] = bytecodes.elementAt(j);
            }
            return result;
        }
        return null;
    }
    
    private byte[][] getBytecodesFromJar(final Source source, final String fullClassName) {
        final String xslFileName = this.getStylesheetFileName(source);
        File xslFile = null;
        if (xslFileName != null) {
            xslFile = new File(xslFileName);
        }
        String jarPath = null;
        if (this._destinationDirectory != null) {
            jarPath = this._destinationDirectory + "/" + this._jarFileName;
        }
        else if (xslFile != null && xslFile.getParent() != null) {
            jarPath = xslFile.getParent() + "/" + this._jarFileName;
        }
        else {
            jarPath = this._jarFileName;
        }
        final File file = new File(jarPath);
        if (!file.exists()) {
            return null;
        }
        if (xslFile != null && xslFile.exists()) {
            final long xslTimestamp = xslFile.lastModified();
            final long transletTimestamp = file.lastModified();
            if (transletTimestamp < xslTimestamp) {
                return null;
            }
        }
        ZipFile jarFile = null;
        try {
            jarFile = new ZipFile(file);
        }
        catch (IOException e) {
            return null;
        }
        final String transletPath = fullClassName.replace('.', '/');
        final String transletAuxPrefix = transletPath + "$";
        final String transletFullName = transletPath + ".class";
        final Vector bytecodes = new Vector();
        final Enumeration entries = jarFile.entries();
        while (entries.hasMoreElements()) {
            final ZipEntry entry = entries.nextElement();
            final String entryName = entry.getName();
            if (entry.getSize() > 0L) {
                if (!entryName.equals(transletFullName)) {
                    if (!entryName.endsWith(".class") || !entryName.startsWith(transletAuxPrefix)) {
                        continue;
                    }
                }
                try {
                    final InputStream input = jarFile.getInputStream(entry);
                    final int size = (int)entry.getSize();
                    final byte[] bytes = new byte[size];
                    this.readFromInputStream(bytes, input, size);
                    input.close();
                    bytecodes.addElement(bytes);
                }
                catch (IOException e2) {
                    return null;
                }
            }
        }
        final int count = bytecodes.size();
        if (count > 0) {
            final byte[][] result = new byte[count][1];
            for (int i = 0; i < count; ++i) {
                result[i] = bytecodes.elementAt(i);
            }
            return result;
        }
        return null;
    }
    
    private void readFromInputStream(final byte[] bytes, final InputStream input, final int size) throws IOException {
        for (int n = 0, offset = 0, length = size; length > 0 && (n = input.read(bytes, offset, length)) > 0; offset += n, length -= n) {}
    }
    
    private String getTransletBaseName(final Source source) {
        String transletBaseName = null;
        if (!this._transletName.equals("GregorSamsa")) {
            return this._transletName;
        }
        final String systemId = source.getSystemId();
        if (systemId != null) {
            String baseName = Util.baseName(systemId);
            if (baseName != null) {
                baseName = Util.noExtName(baseName);
                transletBaseName = Util.toJavaName(baseName);
            }
        }
        return (transletBaseName != null) ? transletBaseName : "GregorSamsa";
    }
    
    private String getStylesheetFileName(final Source source) {
        final String systemId = source.getSystemId();
        if (systemId == null) {
            return null;
        }
        final File file = new File(systemId);
        if (file.exists()) {
            return systemId;
        }
        URL url = null;
        try {
            url = new URL(systemId);
        }
        catch (MalformedURLException e) {
            return null;
        }
        if ("file".equals(url.getProtocol())) {
            return url.getFile();
        }
        return null;
    }
    
    protected Class getDTMManagerClass() {
        return this.m_DTMManagerClass;
    }
    
    private static class PIParamWrapper
    {
        public String _media;
        public String _title;
        public String _charset;
        
        public PIParamWrapper(final String media, final String title, final String charset) {
            this._media = null;
            this._title = null;
            this._charset = null;
            this._media = media;
            this._title = title;
            this._charset = charset;
        }
    }
}
