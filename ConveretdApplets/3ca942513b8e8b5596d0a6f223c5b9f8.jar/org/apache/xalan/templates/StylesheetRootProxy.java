// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import javax.xml.transform.TransformerException;
import org.w3c.dom.Element;
import org.apache.xpath.XPathContext;
import java.util.Properties;
import javax.xml.transform.Transformer;
import org.apache.xalan.processor.XSLTSchema;
import org.apache.xml.utils.DOMHelper;
import org.xml.sax.ContentHandler;
import org.apache.xml.utils.TreeWalker;
import org.apache.xml.utils.DOM2Helper;
import org.w3c.dom.Node;
import java.io.IOException;
import org.xml.sax.SAXException;
import javax.xml.transform.sax.TemplatesHandler;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.ErrorListener;
import org.apache.xml.utils.PrefixResolver;
import org.apache.xpath.WhitespaceStrippingElementMatcher;
import javax.xml.transform.Templates;

public final class StylesheetRootProxy implements Templates, XSLTVisitable, WhitespaceStrippingElementMatcher, PrefixResolver
{
    private StylesheetRoot m_root;
    
    public StylesheetRootProxy(final ErrorListener errorListener) throws TransformerConfigurationException {
        this.m_root = null;
        this.m_root = new StylesheetRoot(errorListener);
    }
    
    public StylesheetRootProxy(final XMLReader reader, final InputSource source, final boolean isSecureProcessing) throws SAXException, IOException {
        this.m_root = null;
        reader.parse(source);
        (this.m_root = (StylesheetRoot)((TemplatesHandler)reader.getContentHandler()).getTemplates()).setSecureProcessing(isSecureProcessing);
    }
    
    public StylesheetRootProxy(final TemplatesHandler builder, final Node node, final boolean isSecureProcessing) throws SAXException, IOException {
        this.m_root = null;
        final TreeWalker walker = new TreeWalker(builder, new DOM2Helper(), builder.getSystemId());
        walker.traverse(node);
        (this.m_root = (StylesheetRoot)builder.getTemplates()).setSecureProcessing(isSecureProcessing);
    }
    
    public StylesheetRootProxy(final XSLTSchema schema, final ErrorListener listener) throws TransformerConfigurationException {
        this.m_root = null;
        this.m_root = new StylesheetRoot(schema, listener);
    }
    
    public Transformer newTransformer() {
        return this.m_root.newTransformer();
    }
    
    public Properties getDefaultOutputProps() {
        return this.m_root.getDefaultOutputProps();
    }
    
    public Properties getOutputProperties() {
        return (Properties)this.getDefaultOutputProps().clone();
    }
    
    public void callVisitors(final XSLTVisitor visitor) {
        this.m_root.callVisitors(visitor);
    }
    
    public boolean shouldStripWhiteSpace(final XPathContext support, final Element targetElement) throws TransformerException {
        return this.m_root.shouldStripWhiteSpace(support, targetElement);
    }
    
    public boolean canStripWhiteSpace() {
        return this.m_root.canStripWhiteSpace();
    }
    
    public String getBaseIdentifier() {
        return this.m_root.getBaseIdentifier();
    }
    
    public String getNamespaceForPrefix(final String prefix, final Node context) {
        return this.m_root.getNamespaceForPrefix(prefix, context);
    }
    
    public String getNamespaceForPrefix(final String prefix) {
        return this.m_root.getNamespaceForPrefix(prefix);
    }
    
    public boolean handlesNullPrefixes() {
        return this.m_root.handlesNullPrefixes();
    }
    
    public StylesheetRoot getStylesheetRoot() {
        return this.m_root;
    }
}
