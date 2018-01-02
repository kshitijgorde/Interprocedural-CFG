// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.trax;

import org.xml.sax.Locator;
import org.xml.sax.Attributes;
import java.util.Vector;
import org.apache.xalan.xsltc.compiler.SyntaxTreeNode;
import org.apache.xalan.xsltc.compiler.CompilerException;
import org.xml.sax.SAXException;
import org.apache.xalan.xsltc.compiler.Stylesheet;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import org.xml.sax.InputSource;
import javax.xml.transform.Templates;
import org.apache.xalan.xsltc.compiler.XSLTC;
import org.apache.xalan.xsltc.compiler.Parser;
import javax.xml.transform.URIResolver;
import org.apache.xalan.xsltc.compiler.SourceLoader;
import javax.xml.transform.sax.TemplatesHandler;
import org.xml.sax.ContentHandler;

public class TemplatesHandlerImpl implements ContentHandler, TemplatesHandler, SourceLoader
{
    private String _systemId;
    private int _indentNumber;
    private URIResolver _uriResolver;
    private TransformerFactoryImpl _tfactory;
    private Parser _parser;
    private TemplatesImpl _templates;
    
    protected TemplatesHandlerImpl(final int indentNumber, final TransformerFactoryImpl tfactory) {
        this._uriResolver = null;
        this._tfactory = null;
        this._parser = null;
        this._templates = null;
        this._indentNumber = indentNumber;
        this._tfactory = tfactory;
        this._parser = new XSLTC().getParser();
    }
    
    public String getSystemId() {
        return this._systemId;
    }
    
    public void setSystemId(final String id) {
        this._systemId = id;
    }
    
    public void setURIResolver(final URIResolver resolver) {
        this._uriResolver = resolver;
    }
    
    public Templates getTemplates() {
        return this._templates;
    }
    
    public InputSource loadSource(final String href, final String context, final XSLTC xsltc) {
        try {
            final Source source = this._uriResolver.resolve(href, context);
            if (source != null) {
                return Util.getInputSource(xsltc, source);
            }
        }
        catch (TransformerException ex) {}
        return null;
    }
    
    public void startDocument() {
        final XSLTC xsltc = this._parser.getXSLTC();
        xsltc.init();
        xsltc.setOutputType(2);
        this._parser.startDocument();
    }
    
    public void endDocument() throws SAXException {
        this._parser.endDocument();
        try {
            final XSLTC xsltc = this._parser.getXSLTC();
            String transletName = null;
            if (this._systemId != null) {
                transletName = Util.baseName(this._systemId);
            }
            else {
                transletName = (String)this._tfactory.getAttribute("translet-name");
            }
            xsltc.setClassName(transletName);
            transletName = xsltc.getClassName();
            Stylesheet stylesheet = null;
            final SyntaxTreeNode root = this._parser.getDocumentRoot();
            if (!this._parser.errorsFound() && root != null) {
                stylesheet = this._parser.makeStylesheet(root);
                stylesheet.setSystemId(this._systemId);
                stylesheet.setParentStylesheet(null);
                if (this._uriResolver != null) {
                    stylesheet.setSourceLoader(this);
                }
                this._parser.setCurrentStylesheet(stylesheet);
                xsltc.setStylesheet(stylesheet);
                this._parser.createAST(stylesheet);
            }
            if (!this._parser.errorsFound() && stylesheet != null) {
                stylesheet.setMultiDocument(xsltc.isMultiDocument());
                stylesheet.setHasIdCall(xsltc.hasIdCall());
                synchronized (xsltc.getClass()) {
                    stylesheet.translate();
                }
            }
            if (this._parser.errorsFound()) {
                final StringBuffer errorMessage = new StringBuffer();
                final Vector errors = this._parser.getErrors();
                for (int count = errors.size(), i = 0; i < count; ++i) {
                    if (errorMessage.length() > 0) {
                        errorMessage.append('\n');
                    }
                    errorMessage.append(errors.elementAt(i).toString());
                }
                throw new SAXException("JAXP_COMPILE_ERR", new TransformerException(errorMessage.toString()));
            }
            final byte[][] bytecodes = xsltc.getBytecodes();
            if (bytecodes != null) {
                this._templates = new TemplatesImpl(xsltc.getBytecodes(), transletName, this._parser.getOutputProperties(), this._indentNumber, this._tfactory);
                if (this._uriResolver != null) {
                    this._templates.setURIResolver(this._uriResolver);
                }
            }
        }
        catch (CompilerException e) {
            throw new SAXException("JAXP_COMPILE_ERR", e);
        }
    }
    
    public void startPrefixMapping(final String prefix, final String uri) {
        this._parser.startPrefixMapping(prefix, uri);
    }
    
    public void endPrefixMapping(final String prefix) {
        this._parser.endPrefixMapping(prefix);
    }
    
    public void startElement(final String uri, final String localname, final String qname, final Attributes attributes) throws SAXException {
        this._parser.startElement(uri, localname, qname, attributes);
    }
    
    public void endElement(final String uri, final String localname, final String qname) {
        this._parser.endElement(uri, localname, qname);
    }
    
    public void characters(final char[] ch, final int start, final int length) {
        this._parser.characters(ch, start, length);
    }
    
    public void processingInstruction(final String name, final String value) {
        this._parser.processingInstruction(name, value);
    }
    
    public void ignorableWhitespace(final char[] ch, final int start, final int length) {
        this._parser.ignorableWhitespace(ch, start, length);
    }
    
    public void skippedEntity(final String name) {
        this._parser.skippedEntity(name);
    }
    
    public void setDocumentLocator(final Locator locator) {
        this.setSystemId(locator.getSystemId());
        this._parser.setDocumentLocator(locator);
    }
}
