// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.runtime;

import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DocumentType;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import java.io.Writer;
import java.io.FileWriter;
import java.io.File;
import org.apache.xalan.xsltc.runtime.output.TransletOutputHandlerFactory;
import org.apache.xml.serializer.SerializationHandler;
import org.apache.xml.dtm.DTMAxisIterator;
import java.util.Enumeration;
import org.apache.xalan.xsltc.DOMEnhancedForDTM;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import org.apache.xalan.xsltc.TransletException;
import org.apache.xalan.xsltc.dom.DOMAdapter;
import org.apache.xalan.xsltc.DOM;
import org.w3c.dom.DOMImplementation;
import org.apache.xalan.xsltc.DOMCache;
import org.apache.xalan.xsltc.dom.KeyIndex;
import java.util.ArrayList;
import javax.xml.transform.Templates;
import java.util.Vector;
import org.apache.xalan.xsltc.Translet;

public abstract class AbstractTranslet implements Translet
{
    public String _version;
    public String _method;
    public String _encoding;
    public boolean _omitHeader;
    public String _standalone;
    public String _doctypePublic;
    public String _doctypeSystem;
    public boolean _indent;
    public String _mediaType;
    public Vector _cdata;
    public int _indentamount;
    public static final int FIRST_TRANSLET_VERSION = 100;
    public static final int VER_SPLIT_NAMES_ARRAY = 101;
    public static final int CURRENT_TRANSLET_VERSION = 101;
    protected int transletVersion;
    protected String[] namesArray;
    protected String[] urisArray;
    protected int[] typesArray;
    protected String[] namespaceArray;
    protected Templates _templates;
    protected boolean _hasIdCall;
    protected StringValueHandler stringValueHandler;
    private static final String EMPTYSTRING = "";
    private static final String ID_INDEX_NAME = "##id";
    protected int pbase;
    protected int pframe;
    protected ArrayList paramsStack;
    private MessageHandler _msgHandler;
    public Hashtable _formatSymbols;
    private Hashtable _keyIndexes;
    private KeyIndex _emptyKeyIndex;
    private int _indexSize;
    private DOMCache _domCache;
    private Hashtable _auxClasses;
    protected DOMImplementation _domImplementation;
    
    public AbstractTranslet() {
        this._version = "1.0";
        this._method = null;
        this._encoding = "UTF-8";
        this._omitHeader = false;
        this._standalone = null;
        this._doctypePublic = null;
        this._doctypeSystem = null;
        this._indent = false;
        this._mediaType = null;
        this._cdata = null;
        this._indentamount = -1;
        this.transletVersion = 100;
        this._templates = null;
        this._hasIdCall = false;
        this.stringValueHandler = new StringValueHandler();
        this.pbase = 0;
        this.pframe = 0;
        this.paramsStack = new ArrayList();
        this._msgHandler = null;
        this._formatSymbols = null;
        this._keyIndexes = null;
        this._emptyKeyIndex = null;
        this._indexSize = 0;
        this._domCache = null;
        this._auxClasses = null;
        this._domImplementation = null;
    }
    
    public void printInternalState() {
        System.out.println("-------------------------------------");
        System.out.println("AbstractTranslet this = " + this);
        System.out.println("pbase = " + this.pbase);
        System.out.println("vframe = " + this.pframe);
        System.out.println("paramsStack.size() = " + this.paramsStack.size());
        System.out.println("namesArray.size = " + this.namesArray.length);
        System.out.println("namespaceArray.size = " + this.namespaceArray.length);
        System.out.println("");
        System.out.println("Total memory = " + Runtime.getRuntime().totalMemory());
    }
    
    public final DOMAdapter makeDOMAdapter(final DOM dom) throws TransletException {
        return new DOMAdapter(dom, this.namesArray, this.urisArray, this.typesArray, this.namespaceArray);
    }
    
    public final void pushParamFrame() {
        this.paramsStack.add(this.pframe, new Integer(this.pbase));
        this.pbase = ++this.pframe;
    }
    
    public final void popParamFrame() {
        if (this.pbase > 0) {
            final ArrayList paramsStack = this.paramsStack;
            final int pbase = this.pbase - 1;
            this.pbase = pbase;
            final int oldpbase = paramsStack.get(pbase);
            for (int i = this.pframe - 1; i >= this.pbase; --i) {
                this.paramsStack.remove(i);
            }
            this.pframe = this.pbase;
            this.pbase = oldpbase;
        }
    }
    
    public final Object addParameter(String name, final Object value) {
        name = BasisLibrary.mapQNameToJavaName(name);
        return this.addParameter(name, value, false);
    }
    
    public final Object addParameter(final String name, final Object value, final boolean isDefault) {
        int i = this.pframe - 1;
        while (i >= this.pbase) {
            final Parameter param = this.paramsStack.get(i);
            if (param._name.equals(name)) {
                if (param._isDefault || !isDefault) {
                    param._value = value;
                    param._isDefault = isDefault;
                    return value;
                }
                return param._value;
            }
            else {
                --i;
            }
        }
        this.paramsStack.add(this.pframe++, new Parameter(name, value, isDefault));
        return value;
    }
    
    public void clearParameters() {
        final boolean b = false;
        this.pframe = (b ? 1 : 0);
        this.pbase = (b ? 1 : 0);
        this.paramsStack.clear();
    }
    
    public final Object getParameter(String name) {
        name = BasisLibrary.mapQNameToJavaName(name);
        for (int i = this.pframe - 1; i >= this.pbase; --i) {
            final Parameter param = this.paramsStack.get(i);
            if (param._name.equals(name)) {
                return param._value;
            }
        }
        return null;
    }
    
    public final void setMessageHandler(final MessageHandler handler) {
        this._msgHandler = handler;
    }
    
    public final void displayMessage(final String msg) {
        if (this._msgHandler == null) {
            System.err.println(msg);
        }
        else {
            this._msgHandler.displayMessage(msg);
        }
    }
    
    public void addDecimalFormat(String name, final DecimalFormatSymbols symbols) {
        if (this._formatSymbols == null) {
            this._formatSymbols = new Hashtable();
        }
        if (name == null) {
            name = "";
        }
        final DecimalFormat df = new DecimalFormat();
        if (symbols != null) {
            df.setDecimalFormatSymbols(symbols);
        }
        this._formatSymbols.put(name, df);
    }
    
    public final DecimalFormat getDecimalFormat(String name) {
        if (this._formatSymbols != null) {
            if (name == null) {
                name = "";
            }
            DecimalFormat df = (DecimalFormat)this._formatSymbols.get(name);
            if (df == null) {
                df = (DecimalFormat)this._formatSymbols.get("");
            }
            return df;
        }
        return null;
    }
    
    public final void prepassDocument(final DOM document) {
        this.setIndexSize(document.getSize());
        this.buildIDIndex(document);
    }
    
    private final void buildIDIndex(final DOM document) {
        if (document instanceof DOMEnhancedForDTM) {
            final DOMEnhancedForDTM enhancedDOM = (DOMEnhancedForDTM)document;
            if (enhancedDOM.hasDOMSource()) {
                this.buildKeyIndex("##id", document);
                return;
            }
            final Hashtable elementsByID = enhancedDOM.getElementsWithIDs();
            if (elementsByID == null) {
                return;
            }
            final Enumeration idValues = elementsByID.keys();
            boolean hasIDValues = false;
            while (idValues.hasMoreElements()) {
                final Object idValue = idValues.nextElement();
                final int element = (int)elementsByID.get(idValue);
                this.buildKeyIndex("##id", element, idValue);
                hasIDValues = true;
            }
            if (hasIDValues) {
                this.setKeyIndexDom("##id", document);
            }
        }
    }
    
    public final void postInitialization() {
        if (this.transletVersion < 101) {
            final int arraySize = this.namesArray.length;
            final String[] newURIsArray = new String[arraySize];
            final String[] newNamesArray = new String[arraySize];
            final int[] newTypesArray = new int[arraySize];
            for (int i = 0; i < arraySize; ++i) {
                final String name = this.namesArray[i];
                final int colonIndex = name.lastIndexOf(58);
                int lNameStartIdx = colonIndex + 1;
                if (colonIndex > -1) {
                    newURIsArray[i] = name.substring(0, colonIndex);
                }
                if (name.charAt(lNameStartIdx) == '@') {
                    ++lNameStartIdx;
                    newTypesArray[i] = 2;
                }
                else if (name.charAt(lNameStartIdx) == '?') {
                    ++lNameStartIdx;
                    newTypesArray[i] = 13;
                }
                else {
                    newTypesArray[i] = 1;
                }
                newNamesArray[i] = ((lNameStartIdx == 0) ? name : name.substring(lNameStartIdx));
            }
            this.namesArray = newNamesArray;
            this.urisArray = newURIsArray;
            this.typesArray = newTypesArray;
        }
        if (this.transletVersion > 101) {
            BasisLibrary.runTimeError("UNKNOWN_TRANSLET_VERSION_ERR", this.getClass().getName());
        }
    }
    
    public void setIndexSize(final int size) {
        if (size > this._indexSize) {
            this._indexSize = size;
        }
    }
    
    public KeyIndex createKeyIndex() {
        return new KeyIndex(this._indexSize);
    }
    
    public void buildKeyIndex(final String name, final int node, final Object value) {
        if (this._keyIndexes == null) {
            this._keyIndexes = new Hashtable();
        }
        KeyIndex index = (KeyIndex)this._keyIndexes.get(name);
        if (index == null) {
            this._keyIndexes.put(name, index = new KeyIndex(this._indexSize));
        }
        index.add(value, node);
    }
    
    public void buildKeyIndex(final String name, final DOM dom) {
        if (this._keyIndexes == null) {
            this._keyIndexes = new Hashtable();
        }
        KeyIndex index = (KeyIndex)this._keyIndexes.get(name);
        if (index == null) {
            this._keyIndexes.put(name, index = new KeyIndex(this._indexSize));
        }
        index.setDom(dom);
    }
    
    public KeyIndex getKeyIndex(final String name) {
        if (this._keyIndexes == null) {
            return (this._emptyKeyIndex != null) ? this._emptyKeyIndex : (this._emptyKeyIndex = new KeyIndex(1));
        }
        final KeyIndex index = (KeyIndex)this._keyIndexes.get(name);
        if (index == null) {
            return (this._emptyKeyIndex != null) ? this._emptyKeyIndex : (this._emptyKeyIndex = new KeyIndex(1));
        }
        return index;
    }
    
    public void buildKeys(final DOM document, final DTMAxisIterator iterator, final SerializationHandler handler, final int root) throws TransletException {
    }
    
    public void setKeyIndexDom(final String name, final DOM document) {
        this.getKeyIndex(name).setDom(document);
    }
    
    public void setDOMCache(final DOMCache cache) {
        this._domCache = cache;
    }
    
    public DOMCache getDOMCache() {
        return this._domCache;
    }
    
    public SerializationHandler openOutputHandler(final String filename, final boolean append) throws TransletException {
        try {
            final TransletOutputHandlerFactory factory = TransletOutputHandlerFactory.newInstance();
            final String dirStr = new File(filename).getParent();
            if (null != dirStr && dirStr.length() > 0) {
                final File dir = new File(dirStr);
                dir.mkdirs();
            }
            factory.setEncoding(this._encoding);
            factory.setOutputMethod(this._method);
            factory.setWriter(new FileWriter(filename, append));
            factory.setOutputType(0);
            final SerializationHandler handler = factory.getSerializationHandler();
            this.transferOutputSettings(handler);
            handler.startDocument();
            return handler;
        }
        catch (Exception e) {
            throw new TransletException(e);
        }
    }
    
    public SerializationHandler openOutputHandler(final String filename) throws TransletException {
        return this.openOutputHandler(filename, false);
    }
    
    public void closeOutputHandler(final SerializationHandler handler) {
        try {
            handler.endDocument();
            handler.close();
        }
        catch (Exception ex) {}
    }
    
    public abstract void transform(final DOM p0, final DTMAxisIterator p1, final SerializationHandler p2) throws TransletException;
    
    public final void transform(final DOM document, final SerializationHandler handler) throws TransletException {
        try {
            this.transform(document, document.getIterator(), handler);
        }
        finally {
            this._keyIndexes = null;
        }
    }
    
    public final void characters(final String string, final SerializationHandler handler) throws TransletException {
        if (string != null) {
            try {
                handler.characters(string);
            }
            catch (Exception e) {
                throw new TransletException(e);
            }
        }
    }
    
    public void addCdataElement(final String name) {
        if (this._cdata == null) {
            this._cdata = new Vector();
        }
        final int lastColon = name.lastIndexOf(58);
        if (lastColon > 0) {
            final String uri = name.substring(0, lastColon);
            final String localName = name.substring(lastColon + 1);
            this._cdata.addElement(uri);
            this._cdata.addElement(localName);
        }
        else {
            this._cdata.addElement(null);
            this._cdata.addElement(name);
        }
    }
    
    protected void transferOutputSettings(final SerializationHandler handler) {
        if (this._method != null) {
            if (this._method.equals("xml")) {
                if (this._standalone != null) {
                    handler.setStandalone(this._standalone);
                }
                if (this._omitHeader) {
                    handler.setOmitXMLDeclaration(true);
                }
                handler.setCdataSectionElements(this._cdata);
                if (this._version != null) {
                    handler.setVersion(this._version);
                }
                handler.setIndent(this._indent);
                handler.setIndentAmount(this._indentamount);
                if (this._doctypeSystem != null) {
                    handler.setDoctype(this._doctypeSystem, this._doctypePublic);
                }
            }
            else if (this._method.equals("html")) {
                handler.setIndent(this._indent);
                handler.setDoctype(this._doctypeSystem, this._doctypePublic);
                if (this._mediaType != null) {
                    handler.setMediaType(this._mediaType);
                }
            }
        }
        else {
            handler.setCdataSectionElements(this._cdata);
            if (this._version != null) {
                handler.setVersion(this._version);
            }
            if (this._standalone != null) {
                handler.setStandalone(this._standalone);
            }
            if (this._omitHeader) {
                handler.setOmitXMLDeclaration(true);
            }
            handler.setIndent(this._indent);
            handler.setDoctype(this._doctypeSystem, this._doctypePublic);
        }
    }
    
    public void addAuxiliaryClass(final Class auxClass) {
        if (this._auxClasses == null) {
            this._auxClasses = new Hashtable();
        }
        this._auxClasses.put(auxClass.getName(), auxClass);
    }
    
    public void setAuxiliaryClasses(final Hashtable auxClasses) {
        this._auxClasses = auxClasses;
    }
    
    public Class getAuxiliaryClass(final String className) {
        if (this._auxClasses == null) {
            return null;
        }
        return (Class)this._auxClasses.get(className);
    }
    
    public String[] getNamesArray() {
        return this.namesArray;
    }
    
    public String[] getUrisArray() {
        return this.urisArray;
    }
    
    public int[] getTypesArray() {
        return this.typesArray;
    }
    
    public String[] getNamespaceArray() {
        return this.namespaceArray;
    }
    
    public boolean hasIdCall() {
        return this._hasIdCall;
    }
    
    public Templates getTemplates() {
        return this._templates;
    }
    
    public void setTemplates(final Templates templates) {
        this._templates = templates;
    }
    
    public Document newDocument(final String uri, final String qname) throws ParserConfigurationException {
        if (this._domImplementation == null) {
            this._domImplementation = DocumentBuilderFactory.newInstance().newDocumentBuilder().getDOMImplementation();
        }
        return this._domImplementation.createDocument(uri, qname, null);
    }
    
    public abstract void transform(final DOM p0, final SerializationHandler[] p1) throws TransletException;
}
