// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.parsers;

import org.apache.xerces.xni.XNIException;
import org.apache.xerces.xni.Augmentations;
import java.io.Reader;
import java.io.StringReader;
import org.w3c.dom.Node;
import org.w3c.dom.ls.DOMInputSource;
import org.apache.xerces.dom3.DOMError;
import org.apache.xerces.dom.DOMErrorImpl;
import org.apache.xerces.xni.parser.XMLInputSource;
import org.w3c.dom.Document;
import org.w3c.dom.DOMException;
import java.util.Stack;
import org.w3c.dom.ls.DOMBuilderFilter;
import org.apache.xerces.dom3.DOMErrorHandler;
import org.apache.xerces.xni.parser.XMLConfigurationException;
import org.apache.xerces.util.DOMEntityResolverWrapper;
import org.w3c.dom.ls.DOMEntityResolver;
import org.apache.xerces.xni.grammars.XMLGrammarPool;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.util.ObjectFactory;
import org.apache.xerces.xni.parser.XMLParserConfiguration;
import org.apache.xerces.util.DOMErrorHandlerWrapper;
import org.w3c.dom.ls.DOMBuilder;

public class DOMBuilderImpl extends AbstractDOMParser implements DOMBuilder
{
    protected static final String NAMESPACES = "http://xml.org/sax/features/namespaces";
    protected static final String VALIDATION_FEATURE = "http://xml.org/sax/features/validation";
    protected static final String XMLSCHEMA = "http://apache.org/xml/features/validation/schema";
    protected static final String DYNAMIC_VALIDATION = "http://apache.org/xml/features/validation/dynamic";
    protected static final String NORMALIZE_DATA = "http://apache.org/xml/features/validation/schema/normalized-value";
    protected static final String XML_SCHEMA_VALIDATION = "http://www.w3.org/2001/XMLSchema";
    protected static final String DTD_VALIDATION = "http://www.w3.org/TR/REC-xml";
    protected String fSchemaType;
    protected static final boolean DEBUG = false;
    protected DOMErrorHandlerWrapper fErrorHandler;
    
    public DOMBuilderImpl(final String configuration, final String schemaType) {
        this((XMLParserConfiguration)ObjectFactory.createObject("org.apache.xerces.xni.parser.XMLParserConfiguration", configuration));
        this.fSchemaType = schemaType;
    }
    
    public DOMBuilderImpl(final XMLParserConfiguration config) {
        super(config);
        this.fSchemaType = "http://www.w3.org/2001/XMLSchema";
        this.fErrorHandler = null;
        final String[] domRecognizedFeatures = { "canonical-form", "cdata-sections", "charset-overrides-xml-encoding", "infoset", "namespace-declarations", "supported-mediatypes-only" };
        super.fConfiguration.addRecognizedFeatures(domRecognizedFeatures);
        super.fConfiguration.setFeature("http://apache.org/xml/features/dom/defer-node-expansion", false);
        super.fConfiguration.setFeature("canonical-form", false);
        super.fConfiguration.setFeature("cdata-sections", true);
        super.fConfiguration.setFeature("charset-overrides-xml-encoding", true);
        super.fConfiguration.setFeature("infoset", false);
        super.fConfiguration.setFeature("namespace-declarations", true);
        super.fConfiguration.setFeature("supported-mediatypes-only", false);
        super.fConfiguration.setFeature("http://apache.org/xml/features/validation/schema/normalized-value", false);
    }
    
    public DOMBuilderImpl(final SymbolTable symbolTable) {
        this((XMLParserConfiguration)ObjectFactory.createObject("org.apache.xerces.xni.parser.XMLParserConfiguration", "org.apache.xerces.parsers.IntegratedParserConfiguration"));
        super.fConfiguration.setProperty("http://apache.org/xml/properties/internal/symbol-table", symbolTable);
    }
    
    public DOMBuilderImpl(final SymbolTable symbolTable, final XMLGrammarPool grammarPool) {
        this((XMLParserConfiguration)ObjectFactory.createObject("org.apache.xerces.xni.parser.XMLParserConfiguration", "org.apache.xerces.parsers.StandardParserConfiguration"));
        super.fConfiguration.setProperty("http://apache.org/xml/properties/internal/symbol-table", symbolTable);
        super.fConfiguration.setProperty("http://apache.org/xml/properties/internal/grammar-pool", grammarPool);
    }
    
    public void reset() {
        super.reset();
        if (super.fSkippedElemStack != null) {
            super.fSkippedElemStack.removeAllElements();
        }
        super.fRejectedElement.clear();
        super.fFilterReject = false;
    }
    
    public DOMEntityResolver getEntityResolver() {
        DOMEntityResolver domEntityResolver = null;
        try {
            final DOMEntityResolver entityResolver = (DOMEntityResolver)super.fConfiguration.getProperty("http://apache.org/xml/properties/internal/entity-resolver");
            if (entityResolver != null && entityResolver instanceof DOMEntityResolverWrapper) {
                domEntityResolver = ((DOMEntityResolverWrapper)entityResolver).getEntityResolver();
            }
        }
        catch (XMLConfigurationException ex) {}
        return domEntityResolver;
    }
    
    public void setEntityResolver(final DOMEntityResolver entityResolver) {
        try {
            super.fConfiguration.setProperty("http://apache.org/xml/properties/internal/entity-resolver", new DOMEntityResolverWrapper(entityResolver));
        }
        catch (XMLConfigurationException ex) {}
    }
    
    public DOMErrorHandler getErrorHandler() {
        if (this.fErrorHandler != null) {
            return this.fErrorHandler.getErrorHandler();
        }
        return null;
    }
    
    public void setErrorHandler(final DOMErrorHandler errorHandler) {
        try {
            this.fErrorHandler = new DOMErrorHandlerWrapper(errorHandler);
            super.fConfiguration.setProperty("http://apache.org/xml/properties/internal/error-handler", this.fErrorHandler);
        }
        catch (XMLConfigurationException ex) {}
    }
    
    public DOMBuilderFilter getFilter() {
        return super.fDOMFilter;
    }
    
    public void setFilter(final DOMBuilderFilter filter) {
        super.fDOMFilter = filter;
        if (super.fSkippedElemStack == null) {
            super.fSkippedElemStack = new Stack();
        }
    }
    
    public void setFeature(final String name, final boolean state) throws DOMException {
        try {
            if (name.equals("comments")) {
                super.fConfiguration.setFeature("http://apache.org/xml/features/include-comments", state);
            }
            else if (name.equals("datatype-normalization")) {
                super.fConfiguration.setFeature("http://apache.org/xml/features/validation/schema/normalized-value", state);
            }
            else if (name.equals("entities")) {
                super.fConfiguration.setFeature("http://apache.org/xml/features/dom/create-entity-ref-nodes", state);
            }
            else if (name.equals("infoset") || name.equals("supported-mediatypes-only") || name.equals("canonical-form")) {
                if (state) {
                    throw new DOMException((short)9, "Feature \"" + name + "\" cannot be set to \"" + state + "\"");
                }
            }
            else if (name.equals("namespaces")) {
                super.fConfiguration.setFeature("http://xml.org/sax/features/namespaces", state);
            }
            else if (name.equals("cdata-sections") || name.equals("namespace-declarations")) {
                if (!state) {
                    throw new DOMException((short)9, "Feature \"" + name + "\" cannot be set to \"" + state + "\"");
                }
            }
            else if (name.equals("validate")) {
                super.fConfiguration.setFeature("http://xml.org/sax/features/validation", state);
                if (this.fSchemaType == null || this.fSchemaType.equals("http://www.w3.org/2001/XMLSchema")) {
                    super.fConfiguration.setFeature("http://apache.org/xml/features/validation/schema", state);
                }
            }
            else if (name.equals("validate-if-schema")) {
                super.fConfiguration.setFeature("http://apache.org/xml/features/validation/dynamic", state);
            }
            else if (name.equals("whitespace-in-element-content")) {
                super.fConfiguration.setFeature("http://apache.org/xml/features/dom/include-ignorable-whitespace", state);
            }
            else {
                super.fConfiguration.setFeature(name, state);
            }
        }
        catch (XMLConfigurationException e) {
            throw new DOMException((short)8, "Feature \"" + name + "\" not recognized");
        }
    }
    
    public boolean canSetFeature(final String name, final boolean state) {
        if (name.equals("infoset") || name.equals("supported-mediatypes-only") || name.equals("canonical-form")) {
            return !state;
        }
        if (name.equals("cdata-sections") || name.equals("namespace-declarations")) {
            return state;
        }
        if (name.equals("charset-overrides-xml-encoding") || name.equals("comments") || name.equals("datatype-normalization") || name.equals("entities") || name.equals("namespaces") || name.equals("validate") || name.equals("validate-if-schema") || name.equals("whitespace-in-element-content")) {
            return true;
        }
        try {
            super.fConfiguration.getFeature(name);
            return true;
        }
        catch (XMLConfigurationException e) {
            return false;
        }
    }
    
    public boolean getFeature(final String name) throws DOMException {
        if (name.equals("comments")) {
            return super.fConfiguration.getFeature("http://apache.org/xml/features/include-comments");
        }
        if (name.equals("datatype-normalization")) {
            return super.fConfiguration.getFeature("http://apache.org/xml/features/validation/schema/normalized-value");
        }
        if (name.equals("entities")) {
            return super.fConfiguration.getFeature("http://apache.org/xml/features/dom/create-entity-ref-nodes");
        }
        if (name.equals("namespaces")) {
            return super.fConfiguration.getFeature("http://xml.org/sax/features/namespaces");
        }
        if (name.equals("validate")) {
            return super.fConfiguration.getFeature("http://xml.org/sax/features/validation");
        }
        if (name.equals("validate-if-schema")) {
            return super.fConfiguration.getFeature("http://apache.org/xml/features/validation/dynamic");
        }
        if (name.equals("whitespace-in-element-content")) {
            return super.fConfiguration.getFeature("http://apache.org/xml/features/dom/include-ignorable-whitespace");
        }
        if (name.equals("namespace-declarations") || name.equals("cdata-sections") || name.equals("canonical-form") || name.equals("supported-mediatypes-only") || name.equals("infoset") || name.equals("charset-overrides-xml-encoding")) {
            return super.fConfiguration.getFeature(name);
        }
        throw new DOMException((short)8, "Feature \"" + name + "\" not recognized");
    }
    
    public Document parseURI(final String uri) {
        final XMLInputSource source = new XMLInputSource(null, uri, null);
        try {
            this.parse(source);
        }
        catch (Exception e) {
            if (this.fErrorHandler != null) {
                final DOMErrorImpl error = new DOMErrorImpl();
                error.fException = e;
                error.fMessage = e.getMessage();
                error.fSeverity = 1;
                this.fErrorHandler.getErrorHandler().handleError(error);
            }
        }
        return this.getDocument();
    }
    
    public Document parse(final DOMInputSource is) {
        final XMLInputSource xmlInputSource = this.dom2xmlInputSource(is);
        try {
            this.parse(xmlInputSource);
        }
        catch (Exception e) {
            if (this.fErrorHandler != null) {
                final DOMErrorImpl error = new DOMErrorImpl();
                error.fException = e;
                error.fMessage = e.getMessage();
                error.fSeverity = 1;
                this.fErrorHandler.getErrorHandler().handleError(error);
            }
        }
        return this.getDocument();
    }
    
    public void parseWithContext(final DOMInputSource is, final Node cnode, final short action) throws DOMException {
        throw new DOMException((short)9, "Not supported");
    }
    
    XMLInputSource dom2xmlInputSource(final DOMInputSource is) {
        XMLInputSource xis = null;
        if (is.getStringData() != null) {
            xis = new XMLInputSource(is.getPublicId(), is.getSystemId(), is.getBaseURI(), new StringReader(is.getStringData()), "UTF-16");
        }
        else if (is.getCharacterStream() != null) {
            xis = new XMLInputSource(is.getPublicId(), is.getSystemId(), is.getBaseURI(), is.getCharacterStream(), "UTF-16");
        }
        else if (is.getByteStream() != null) {
            xis = new XMLInputSource(is.getPublicId(), is.getSystemId(), is.getBaseURI(), is.getByteStream(), is.getEncoding());
        }
        else {
            xis = new XMLInputSource(is.getPublicId(), is.getSystemId(), is.getBaseURI());
        }
        return xis;
    }
    
    public void endDocument(final Augmentations augs) throws XNIException {
        super.endDocument(augs);
        if (super.fDocumentImpl != null) {
            super.fDocumentImpl.copyConfigurationProperties(super.fConfiguration);
        }
    }
    
    public void setProperty(final String propertyId, final Object value) throws DOMException {
        try {
            super.fConfiguration.setProperty(propertyId, value);
        }
        catch (XMLConfigurationException e) {
            final String message = e.getMessage();
            if (e.getType() == 0) {
                throw new DOMException((short)8, message);
            }
            throw new DOMException((short)9, message);
        }
    }
}
