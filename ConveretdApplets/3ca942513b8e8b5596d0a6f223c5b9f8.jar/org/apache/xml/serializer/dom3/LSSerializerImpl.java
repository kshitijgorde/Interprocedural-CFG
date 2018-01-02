// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.serializer.dom3;

import org.w3c.dom.Document;
import java.io.StringWriter;
import java.net.URLConnection;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.io.FileOutputStream;
import java.io.File;
import java.net.URL;
import org.apache.xml.serializer.utils.SystemIDResolver;
import org.apache.xml.serializer.Encodings;
import org.w3c.dom.ls.LSException;
import org.w3c.dom.DOMError;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.DOMStringList;
import org.w3c.dom.DOMException;
import org.apache.xml.serializer.utils.Utils;
import org.apache.xml.serializer.SerializerFactory;
import org.apache.xml.serializer.OutputPropertiesFactory;
import java.util.Properties;
import org.w3c.dom.DOMErrorHandler;
import org.w3c.dom.Node;
import org.w3c.dom.ls.LSSerializerFilter;
import org.apache.xml.serializer.DOM3Serializer;
import org.apache.xml.serializer.Serializer;
import org.w3c.dom.ls.LSSerializer;
import org.w3c.dom.DOMConfiguration;

public final class LSSerializerImpl implements DOMConfiguration, LSSerializer
{
    private Serializer fXMLSerializer;
    protected int fFeatures;
    private DOM3Serializer fDOMSerializer;
    private LSSerializerFilter fSerializerFilter;
    private Node fVisitedNode;
    private String fEndOfLine;
    private DOMErrorHandler fDOMErrorHandler;
    private Properties fDOMConfigProperties;
    private String fEncoding;
    private static final int CANONICAL = 1;
    private static final int CDATA = 2;
    private static final int CHARNORMALIZE = 4;
    private static final int COMMENTS = 8;
    private static final int DTNORMALIZE = 16;
    private static final int ELEM_CONTENT_WHITESPACE = 32;
    private static final int ENTITIES = 64;
    private static final int INFOSET = 128;
    private static final int NAMESPACES = 256;
    private static final int NAMESPACEDECLS = 512;
    private static final int NORMALIZECHARS = 1024;
    private static final int SPLITCDATA = 2048;
    private static final int VALIDATE = 4096;
    private static final int SCHEMAVALIDATE = 8192;
    private static final int WELLFORMED = 16384;
    private static final int DISCARDDEFAULT = 32768;
    private static final int PRETTY_PRINT = 65536;
    private static final int IGNORE_CHAR_DENORMALIZE = 131072;
    private static final int XMLDECL = 262144;
    private String[] fRecognizedParameters;
    
    public LSSerializerImpl() {
        this.fXMLSerializer = null;
        this.fFeatures = 0;
        this.fDOMSerializer = null;
        this.fSerializerFilter = null;
        this.fVisitedNode = null;
        this.fEndOfLine = ((System.getProperty("line.separator") != null) ? System.getProperty("line.separator") : "\n");
        this.fDOMErrorHandler = null;
        this.fDOMConfigProperties = null;
        this.fRecognizedParameters = new String[] { "canonical-form", "cdata-sections", "check-character-normalization", "comments", "datatype-normalization", "element-content-whitespace", "entities", "infoset", "namespaces", "namespace-declarations", "split-cdata-sections", "validate", "validate-if-schema", "well-formed", "discard-default-content", "format-pretty-print", "ignore-unknown-character-denormalizations", "xml-declaration", "error-handler" };
        this.fFeatures |= 0x2;
        this.fFeatures |= 0x8;
        this.fFeatures |= 0x20;
        this.fFeatures |= 0x40;
        this.fFeatures |= 0x100;
        this.fFeatures |= 0x200;
        this.fFeatures |= 0x800;
        this.fFeatures |= 0x4000;
        this.fFeatures |= 0x8000;
        this.fFeatures |= 0x40000;
        this.fDOMConfigProperties = new Properties();
        this.initializeSerializerProps();
        final Properties configProps = OutputPropertiesFactory.getDefaultMethodProperties("xml");
        (this.fXMLSerializer = SerializerFactory.getSerializer(configProps)).setOutputFormat(this.fDOMConfigProperties);
    }
    
    public void initializeSerializerProps() {
        this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}canonical-form", "default:no");
        this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}cdata-sections", "default:yes");
        this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}check-character-normalization", "default:no");
        this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}comments", "default:yes");
        this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}datatype-normalization", "default:no");
        this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}element-content-whitespace", "default:yes");
        this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}entities", "default:yes");
        if ((this.fFeatures & 0x80) != 0x0) {
            this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}namespaces", "default:yes");
            this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}namespace-declarations", "default:yes");
            this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}comments", "default:yes");
            this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}element-content-whitespace", "default:yes");
            this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}well-formed", "default:yes");
            this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}entities", "default:no");
            this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}cdata-sections", "default:no");
            this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}validate-if-schema", "default:no");
            this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}datatype-normalization", "default:no");
        }
        this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}namespaces", "default:yes");
        this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}namespace-declarations", "default:yes");
        this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}split-cdata-sections", "default:yes");
        this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}validate", "default:no");
        this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}validate-if-schema", "default:no");
        this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}well-formed", "default:yes");
        this.fDOMConfigProperties.setProperty("indent", "default:yes");
        this.fDOMConfigProperties.setProperty("{http://xml.apache.org/xalan}indent-amount", new Integer(2).toString());
        this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}discard-default-content", "default:yes");
        this.fDOMConfigProperties.setProperty("omit-xml-declaration", "no");
    }
    
    public boolean canSetParameter(final String name, final Object value) {
        if (value instanceof Boolean) {
            if (name.equalsIgnoreCase("cdata-sections") || name.equalsIgnoreCase("comments") || name.equalsIgnoreCase("entities") || name.equalsIgnoreCase("infoset") || name.equalsIgnoreCase("element-content-whitespace") || name.equalsIgnoreCase("namespaces") || name.equalsIgnoreCase("namespace-declarations") || name.equalsIgnoreCase("split-cdata-sections") || name.equalsIgnoreCase("well-formed") || name.equalsIgnoreCase("discard-default-content") || name.equalsIgnoreCase("format-pretty-print") || name.equalsIgnoreCase("xml-declaration")) {
                return true;
            }
            if (name.equalsIgnoreCase("canonical-form") || name.equalsIgnoreCase("check-character-normalization") || name.equalsIgnoreCase("datatype-normalization") || name.equalsIgnoreCase("validate-if-schema") || name.equalsIgnoreCase("validate")) {
                return !(boolean)value;
            }
            if (name.equalsIgnoreCase("ignore-unknown-character-denormalizations")) {
                return (boolean)value;
            }
        }
        else if ((name.equalsIgnoreCase("error-handler") && value == null) || value instanceof DOMErrorHandler) {
            return true;
        }
        return false;
    }
    
    public Object getParameter(final String name) throws DOMException {
        if (name.equalsIgnoreCase("comments")) {
            return ((this.fFeatures & 0x8) != 0x0) ? Boolean.TRUE : Boolean.FALSE;
        }
        if (name.equalsIgnoreCase("cdata-sections")) {
            return ((this.fFeatures & 0x2) != 0x0) ? Boolean.TRUE : Boolean.FALSE;
        }
        if (name.equalsIgnoreCase("entities")) {
            return ((this.fFeatures & 0x40) != 0x0) ? Boolean.TRUE : Boolean.FALSE;
        }
        if (name.equalsIgnoreCase("namespaces")) {
            return ((this.fFeatures & 0x100) != 0x0) ? Boolean.TRUE : Boolean.FALSE;
        }
        if (name.equalsIgnoreCase("namespace-declarations")) {
            return ((this.fFeatures & 0x200) != 0x0) ? Boolean.TRUE : Boolean.FALSE;
        }
        if (name.equalsIgnoreCase("split-cdata-sections")) {
            return ((this.fFeatures & 0x800) != 0x0) ? Boolean.TRUE : Boolean.FALSE;
        }
        if (name.equalsIgnoreCase("well-formed")) {
            return ((this.fFeatures & 0x4000) != 0x0) ? Boolean.TRUE : Boolean.FALSE;
        }
        if (name.equalsIgnoreCase("discard-default-content")) {
            return ((this.fFeatures & 0x8000) != 0x0) ? Boolean.TRUE : Boolean.FALSE;
        }
        if (name.equalsIgnoreCase("format-pretty-print")) {
            return ((this.fFeatures & 0x10000) != 0x0) ? Boolean.TRUE : Boolean.FALSE;
        }
        if (name.equalsIgnoreCase("xml-declaration")) {
            return ((this.fFeatures & 0x40000) != 0x0) ? Boolean.TRUE : Boolean.FALSE;
        }
        if (name.equalsIgnoreCase("element-content-whitespace")) {
            return ((this.fFeatures & 0x20) != 0x0) ? Boolean.TRUE : Boolean.FALSE;
        }
        if (name.equalsIgnoreCase("format-pretty-print")) {
            return ((this.fFeatures & 0x10000) != 0x0) ? Boolean.TRUE : Boolean.FALSE;
        }
        if (name.equalsIgnoreCase("ignore-unknown-character-denormalizations")) {
            return Boolean.TRUE;
        }
        if (name.equalsIgnoreCase("canonical-form") || name.equalsIgnoreCase("check-character-normalization") || name.equalsIgnoreCase("datatype-normalization") || name.equalsIgnoreCase("validate") || name.equalsIgnoreCase("validate-if-schema")) {
            return Boolean.FALSE;
        }
        if (name.equalsIgnoreCase("infoset")) {
            if ((this.fFeatures & 0x40) == 0x0 && (this.fFeatures & 0x2) == 0x0 && (this.fFeatures & 0x20) != 0x0 && (this.fFeatures & 0x100) != 0x0 && (this.fFeatures & 0x200) != 0x0 && (this.fFeatures & 0x4000) != 0x0 && (this.fFeatures & 0x8) != 0x0) {
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        }
        else {
            if (name.equalsIgnoreCase("error-handler")) {
                return this.fDOMErrorHandler;
            }
            if (name.equalsIgnoreCase("schema-location") || name.equalsIgnoreCase("schema-type")) {
                return null;
            }
            final String msg = Utils.messages.createMessage("FEATURE_NOT_FOUND", new Object[] { name });
            throw new DOMException((short)8, msg);
        }
    }
    
    public DOMStringList getParameterNames() {
        return new DOMStringListImpl(this.fRecognizedParameters);
    }
    
    public void setParameter(final String name, final Object value) throws DOMException {
        if (value instanceof Boolean) {
            final boolean state = (boolean)value;
            if (name.equalsIgnoreCase("comments")) {
                this.fFeatures = (state ? (this.fFeatures | 0x8) : (this.fFeatures & 0xFFFFFFF7));
                if (state) {
                    this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}comments", "explicit:yes");
                }
                else {
                    this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}comments", "explicit:no");
                }
            }
            else if (name.equalsIgnoreCase("cdata-sections")) {
                this.fFeatures = (state ? (this.fFeatures | 0x2) : (this.fFeatures & 0xFFFFFFFD));
                if (state) {
                    this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}cdata-sections", "explicit:yes");
                }
                else {
                    this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}cdata-sections", "explicit:no");
                }
            }
            else if (name.equalsIgnoreCase("entities")) {
                this.fFeatures = (state ? (this.fFeatures | 0x40) : (this.fFeatures & 0xFFFFFFBF));
                if (state) {
                    this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}entities", "explicit:yes");
                }
                else {
                    this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}entities", "explicit:no");
                }
            }
            else if (name.equalsIgnoreCase("namespaces")) {
                this.fFeatures = (state ? (this.fFeatures | 0x100) : (this.fFeatures & 0xFFFFFEFF));
                if (state) {
                    this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}namespaces", "explicit:yes");
                }
                else {
                    this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}namespaces", "explicit:no");
                }
            }
            else if (name.equalsIgnoreCase("namespace-declarations")) {
                this.fFeatures = (state ? (this.fFeatures | 0x200) : (this.fFeatures & 0xFFFFFDFF));
                if (state) {
                    this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}namespace-declarations", "explicit:yes");
                }
                else {
                    this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}namespace-declarations", "explicit:no");
                }
            }
            else if (name.equalsIgnoreCase("split-cdata-sections")) {
                this.fFeatures = (state ? (this.fFeatures | 0x800) : (this.fFeatures & 0xFFFFF7FF));
                if (state) {
                    this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}split-cdata-sections", "explicit:yes");
                }
                else {
                    this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}split-cdata-sections", "explicit:no");
                }
            }
            else if (name.equalsIgnoreCase("well-formed")) {
                this.fFeatures = (state ? (this.fFeatures | 0x4000) : (this.fFeatures & 0xFFFFBFFF));
                if (state) {
                    this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}well-formed", "explicit:yes");
                }
                else {
                    this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}well-formed", "explicit:no");
                }
            }
            else if (name.equalsIgnoreCase("discard-default-content")) {
                this.fFeatures = (state ? (this.fFeatures | 0x8000) : (this.fFeatures & 0xFFFF7FFF));
                if (state) {
                    this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}discard-default-content", "explicit:yes");
                }
                else {
                    this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}discard-default-content", "explicit:no");
                }
            }
            else if (name.equalsIgnoreCase("format-pretty-print")) {
                this.fFeatures = (state ? (this.fFeatures | 0x10000) : (this.fFeatures & 0xFFFEFFFF));
                this.fDOMConfigProperties.setProperty("indent", "explicit:yes");
                this.fDOMConfigProperties.setProperty("{http://xml.apache.org/xalan}indent-amount", new Integer(3).toString());
            }
            else if (name.equalsIgnoreCase("xml-declaration")) {
                this.fFeatures = (state ? (this.fFeatures | 0x40000) : (this.fFeatures & 0xFFFBFFFF));
                if (state) {
                    this.fDOMConfigProperties.setProperty("omit-xml-declaration", "no");
                }
                else {
                    this.fDOMConfigProperties.setProperty("omit-xml-declaration", "yes");
                }
            }
            else if (name.equalsIgnoreCase("element-content-whitespace")) {
                this.fFeatures = (state ? (this.fFeatures | 0x20) : (this.fFeatures & 0xFFFFFFDF));
                if (state) {
                    this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}element-content-whitespace", "explicit:yes");
                }
                else {
                    this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}element-content-whitespace", "explicit:no");
                }
            }
            else if (name.equalsIgnoreCase("ignore-unknown-character-denormalizations")) {
                if (!state) {
                    final String msg = Utils.messages.createMessage("FEATURE_NOT_SUPPORTED", new Object[] { name });
                    throw new DOMException((short)9, msg);
                }
                this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}ignore-unknown-character-denormalizations", "explicit:yes");
            }
            else if (name.equalsIgnoreCase("canonical-form") || name.equalsIgnoreCase("validate-if-schema") || name.equalsIgnoreCase("validate") || name.equalsIgnoreCase("check-character-normalization") || name.equalsIgnoreCase("datatype-normalization")) {
                if (state) {
                    final String msg = Utils.messages.createMessage("FEATURE_NOT_SUPPORTED", new Object[] { name });
                    throw new DOMException((short)9, msg);
                }
                if (name.equalsIgnoreCase("canonical-form")) {
                    this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}canonical-form", "explicit:no");
                }
                else if (name.equalsIgnoreCase("validate-if-schema")) {
                    this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}validate-if-schema", "explicit:no");
                }
                else if (name.equalsIgnoreCase("validate")) {
                    this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}validate", "explicit:no");
                }
                else if (name.equalsIgnoreCase("validate-if-schema")) {
                    this.fDOMConfigProperties.setProperty("check-character-normalizationcheck-character-normalization", "explicit:no");
                }
                else if (name.equalsIgnoreCase("datatype-normalization")) {
                    this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}datatype-normalization", "explicit:no");
                }
            }
            else if (name.equalsIgnoreCase("infoset")) {
                if (state) {
                    this.fFeatures &= 0xFFFFFFBF;
                    this.fFeatures &= 0xFFFFFFFD;
                    this.fFeatures &= 0xFFFFDFFF;
                    this.fFeatures &= 0xFFFFFFEF;
                    this.fFeatures |= 0x100;
                    this.fFeatures |= 0x200;
                    this.fFeatures |= 0x4000;
                    this.fFeatures |= 0x20;
                    this.fFeatures |= 0x8;
                }
                this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}namespaces", "explicit:yes");
                this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}namespace-declarations", "explicit:yes");
                this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}comments", "explicit:yes");
                this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}element-content-whitespace", "explicit:yes");
                this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}well-formed", "explicit:yes");
                this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}entities", "explicit:no");
                this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}cdata-sections", "explicit:no");
                this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}validate-if-schema", "explicit:no");
                this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}datatype-normalization", "explicit:no");
            }
        }
        else if (name.equalsIgnoreCase("error-handler")) {
            if (value != null && !(value instanceof DOMErrorHandler)) {
                final String msg2 = Utils.messages.createMessage("TYPE_MISMATCH_ERR", new Object[] { name });
                throw new DOMException((short)17, msg2);
            }
            this.fDOMErrorHandler = (DOMErrorHandler)value;
        }
        else {
            if (name.equalsIgnoreCase("schema-location") || (name.equalsIgnoreCase("schema-type") && value != null)) {
                final String msg2 = Utils.messages.createMessage("FEATURE_NOT_SUPPORTED", new Object[] { name });
                throw new DOMException((short)9, msg2);
            }
            final String msg2 = Utils.messages.createMessage("FEATURE_NOT_FOUND", new Object[] { name });
            throw new DOMException((short)8, msg2);
        }
    }
    
    public DOMConfiguration getDomConfig() {
        return this;
    }
    
    public LSSerializerFilter getFilter() {
        return this.fSerializerFilter;
    }
    
    public String getNewLine() {
        return this.fEndOfLine;
    }
    
    public void setFilter(final LSSerializerFilter filter) {
        this.fSerializerFilter = filter;
    }
    
    public void setNewLine(final String newLine) {
        this.fEndOfLine = ((newLine != null) ? newLine : this.fEndOfLine);
    }
    
    public boolean write(final Node nodeArg, final LSOutput destination) throws LSException {
        if (destination == null) {
            final String msg = Utils.messages.createMessage("no-output-specified", null);
            if (this.fDOMErrorHandler != null) {
                this.fDOMErrorHandler.handleError(new DOMErrorImpl((short)3, msg, "no-output-specified"));
            }
            throw new LSException((short)82, msg);
        }
        if (nodeArg == null) {
            return false;
        }
        final Serializer serializer = this.fXMLSerializer;
        serializer.reset();
        if (nodeArg != this.fVisitedNode) {
            final String xmlVersion = this.getXMLVersion(nodeArg);
            this.fEncoding = destination.getEncoding();
            if (this.fEncoding == null) {
                this.fEncoding = this.getInputEncoding(nodeArg);
                this.fEncoding = ((this.fEncoding != null) ? this.fEncoding : ((this.getXMLEncoding(nodeArg) == null) ? "UTF-8" : this.getXMLEncoding(nodeArg)));
            }
            if (!Encodings.isRecognizedEncoding(this.fEncoding)) {
                final String msg2 = Utils.messages.createMessage("unsupported-encoding", null);
                if (this.fDOMErrorHandler != null) {
                    this.fDOMErrorHandler.handleError(new DOMErrorImpl((short)3, msg2, "unsupported-encoding"));
                }
                throw new LSException((short)82, msg2);
            }
            serializer.getOutputFormat().setProperty("version", xmlVersion);
            this.fDOMConfigProperties.setProperty("{http://xml.apache.org/xerces-2j}xml-version", xmlVersion);
            this.fDOMConfigProperties.setProperty("encoding", this.fEncoding);
            if ((nodeArg.getNodeType() != 9 || nodeArg.getNodeType() != 1 || nodeArg.getNodeType() != 6) && (this.fFeatures & 0x40000) != 0x0) {
                this.fDOMConfigProperties.setProperty("omit-xml-declaration", "default:no");
            }
            this.fVisitedNode = nodeArg;
        }
        try {
            final Writer writer = destination.getCharacterStream();
            if (writer == null) {
                final OutputStream outputStream = destination.getByteStream();
                if (outputStream == null) {
                    final String uri = destination.getSystemId();
                    if (uri == null) {
                        final String msg3 = Utils.messages.createMessage("no-output-specified", null);
                        if (this.fDOMErrorHandler != null) {
                            this.fDOMErrorHandler.handleError(new DOMErrorImpl((short)3, msg3, "no-output-specified"));
                        }
                        throw new LSException((short)82, msg3);
                    }
                    final String absoluteURI = SystemIDResolver.getAbsoluteURI(uri);
                    final URL url = new URL(absoluteURI);
                    OutputStream urlOutStream = null;
                    final String protocol = url.getProtocol();
                    final String host = url.getHost();
                    if (protocol.equalsIgnoreCase("file") && (host == null || host.length() == 0 || host.equals("localhost"))) {
                        urlOutStream = new FileOutputStream(new File(url.getPath()));
                    }
                    else {
                        final URLConnection urlCon = url.openConnection();
                        urlCon.setDoInput(false);
                        urlCon.setDoOutput(true);
                        urlCon.setUseCaches(false);
                        urlCon.setAllowUserInteraction(false);
                        if (urlCon instanceof HttpURLConnection) {
                            final HttpURLConnection httpCon = (HttpURLConnection)urlCon;
                            httpCon.setRequestMethod("PUT");
                        }
                        urlOutStream = urlCon.getOutputStream();
                    }
                    serializer.setWriter(new OutputStreamWriter(urlOutStream));
                }
                else {
                    serializer.setWriter(new OutputStreamWriter(outputStream, this.fEncoding));
                }
            }
            else {
                serializer.setWriter(writer);
            }
            if (this.fDOMSerializer == null) {
                this.fDOMSerializer = (DOM3Serializer)serializer.asDOM3Serializer();
            }
            if (this.fDOMErrorHandler != null) {
                this.fDOMSerializer.setErrorHandler(this.fDOMErrorHandler);
            }
            if (this.fSerializerFilter != null) {
                this.fDOMSerializer.setNodeFilter(this.fSerializerFilter);
            }
            this.fDOMSerializer.setNewLine(this.fEndOfLine.toCharArray());
            this.fDOMSerializer.serializeDOM3(nodeArg);
        }
        catch (UnsupportedEncodingException ue) {
            final String msg2 = Utils.messages.createMessage("unsupported-encoding", null);
            if (this.fDOMErrorHandler != null) {
                this.fDOMErrorHandler.handleError(new DOMErrorImpl((short)3, msg2, "unsupported-encoding", ue));
            }
            throw new LSException((short)82, ue.getMessage());
        }
        catch (LSException lse) {
            throw lse;
        }
        catch (RuntimeException e) {
            e.printStackTrace();
            throw new LSException((short)82, (e != null) ? e.getMessage() : "NULL Exception");
        }
        catch (Exception e2) {
            if (this.fDOMErrorHandler != null) {
                this.fDOMErrorHandler.handleError(new DOMErrorImpl((short)3, e2.getMessage(), null, e2));
            }
            e2.printStackTrace();
            throw new LSException((short)82, e2.toString());
        }
        return true;
    }
    
    public String writeToString(final Node nodeArg) throws DOMException, LSException {
        if (nodeArg == null) {
            return null;
        }
        final Serializer serializer = this.fXMLSerializer;
        serializer.reset();
        if (nodeArg != this.fVisitedNode) {
            final String xmlVersion = this.getXMLVersion(nodeArg);
            serializer.getOutputFormat().setProperty("version", xmlVersion);
            this.fDOMConfigProperties.setProperty("{http://xml.apache.org/xerces-2j}xml-version", xmlVersion);
            this.fDOMConfigProperties.setProperty("encoding", "UTF-16");
            if ((nodeArg.getNodeType() != 9 || nodeArg.getNodeType() != 1 || nodeArg.getNodeType() != 6) && (this.fFeatures & 0x40000) != 0x0) {
                this.fDOMConfigProperties.setProperty("omit-xml-declaration", "default:no");
            }
            this.fVisitedNode = nodeArg;
        }
        final StringWriter output = new StringWriter();
        try {
            serializer.setWriter(output);
            if (this.fDOMSerializer == null) {
                this.fDOMSerializer = (DOM3Serializer)serializer.asDOM3Serializer();
            }
            if (this.fDOMErrorHandler != null) {
                this.fDOMSerializer.setErrorHandler(this.fDOMErrorHandler);
            }
            if (this.fSerializerFilter != null) {
                this.fDOMSerializer.setNodeFilter(this.fSerializerFilter);
            }
            this.fDOMSerializer.setNewLine(this.fEndOfLine.toCharArray());
            this.fDOMSerializer.serializeDOM3(nodeArg);
        }
        catch (LSException lse) {
            throw lse;
        }
        catch (RuntimeException e) {
            e.printStackTrace();
            throw new LSException((short)82, e.toString());
        }
        catch (Exception e2) {
            if (this.fDOMErrorHandler != null) {
                this.fDOMErrorHandler.handleError(new DOMErrorImpl((short)3, e2.getMessage(), null, e2));
            }
            e2.printStackTrace();
            throw new LSException((short)82, e2.toString());
        }
        return output.toString();
    }
    
    public boolean writeToURI(final Node nodeArg, final String uri) throws LSException {
        if (nodeArg == null) {
            return false;
        }
        final Serializer serializer = this.fXMLSerializer;
        serializer.reset();
        if (nodeArg != this.fVisitedNode) {
            final String xmlVersion = this.getXMLVersion(nodeArg);
            this.fEncoding = this.getInputEncoding(nodeArg);
            if (this.fEncoding == null) {
                this.fEncoding = ((this.fEncoding != null) ? this.fEncoding : ((this.getXMLEncoding(nodeArg) == null) ? "UTF-8" : this.getXMLEncoding(nodeArg)));
            }
            serializer.getOutputFormat().setProperty("version", xmlVersion);
            this.fDOMConfigProperties.setProperty("{http://xml.apache.org/xerces-2j}xml-version", xmlVersion);
            this.fDOMConfigProperties.setProperty("encoding", this.fEncoding);
            if ((nodeArg.getNodeType() != 9 || nodeArg.getNodeType() != 1 || nodeArg.getNodeType() != 6) && (this.fFeatures & 0x40000) != 0x0) {
                this.fDOMConfigProperties.setProperty("omit-xml-declaration", "default:no");
            }
            this.fVisitedNode = nodeArg;
        }
        try {
            if (uri == null) {
                final String msg = Utils.messages.createMessage("no-output-specified", null);
                if (this.fDOMErrorHandler != null) {
                    this.fDOMErrorHandler.handleError(new DOMErrorImpl((short)3, msg, "no-output-specified"));
                }
                throw new LSException((short)82, msg);
            }
            final String absoluteURI = SystemIDResolver.getAbsoluteURI(uri);
            final URL url = new URL(absoluteURI);
            OutputStream urlOutStream = null;
            final String protocol = url.getProtocol();
            final String host = url.getHost();
            if (protocol.equalsIgnoreCase("file") && (host == null || host.length() == 0 || host.equals("localhost"))) {
                urlOutStream = new FileOutputStream(new File(url.getPath()));
            }
            else {
                final URLConnection urlCon = url.openConnection();
                urlCon.setDoInput(false);
                urlCon.setDoOutput(true);
                urlCon.setUseCaches(false);
                urlCon.setAllowUserInteraction(false);
                if (urlCon instanceof HttpURLConnection) {
                    final HttpURLConnection httpCon = (HttpURLConnection)urlCon;
                    httpCon.setRequestMethod("PUT");
                }
                urlOutStream = urlCon.getOutputStream();
            }
            serializer.setWriter(new OutputStreamWriter(urlOutStream, this.fEncoding));
            if (this.fDOMSerializer == null) {
                this.fDOMSerializer = (DOM3Serializer)serializer.asDOM3Serializer();
            }
            if (this.fDOMErrorHandler != null) {
                this.fDOMSerializer.setErrorHandler(this.fDOMErrorHandler);
            }
            if (this.fSerializerFilter != null) {
                this.fDOMSerializer.setNodeFilter(this.fSerializerFilter);
            }
            this.fDOMSerializer.setNewLine(this.fEndOfLine.toCharArray());
            this.fDOMSerializer.serializeDOM3(nodeArg);
        }
        catch (LSException lse) {
            throw lse;
        }
        catch (RuntimeException e) {
            e.printStackTrace();
            throw new LSException((short)82, e.toString());
        }
        catch (Exception e2) {
            if (this.fDOMErrorHandler != null) {
                this.fDOMErrorHandler.handleError(new DOMErrorImpl((short)3, e2.getMessage(), null, e2));
            }
            e2.printStackTrace();
            throw new LSException((short)82, e2.toString());
        }
        return true;
    }
    
    protected String getXMLVersion(final Node nodeArg) {
        Document doc = null;
        if (nodeArg != null) {
            if (nodeArg.getNodeType() == 9) {
                doc = (Document)nodeArg;
            }
            else {
                doc = nodeArg.getOwnerDocument();
            }
            if (doc != null && doc.getImplementation().hasFeature("Core", "3.0")) {
                return doc.getXmlVersion();
            }
        }
        return "1.0";
    }
    
    protected String getXMLEncoding(final Node nodeArg) {
        Document doc = null;
        if (nodeArg != null) {
            if (nodeArg.getNodeType() == 9) {
                doc = (Document)nodeArg;
            }
            else {
                doc = nodeArg.getOwnerDocument();
            }
            if (doc != null && doc.getImplementation().hasFeature("Core", "3.0")) {
                return doc.getXmlEncoding();
            }
        }
        return "UTF-8";
    }
    
    protected String getInputEncoding(final Node nodeArg) {
        Document doc = null;
        if (nodeArg != null) {
            if (nodeArg.getNodeType() == 9) {
                doc = (Document)nodeArg;
            }
            else {
                doc = nodeArg.getOwnerDocument();
            }
            if (doc != null && doc.getImplementation().hasFeature("Core", "3.0")) {
                return doc.getInputEncoding();
            }
        }
        return null;
    }
    
    public DOMErrorHandler getErrorHandler() {
        return this.fDOMErrorHandler;
    }
}
